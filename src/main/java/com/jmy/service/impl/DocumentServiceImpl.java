package com.jmy.service.impl;

import com.jmy.common.exception.CommonException;
import com.jmy.dao.DeptMapper;
import com.jmy.dao.DocumentMapper;
import com.jmy.model.entity.Document;
import com.jmy.model.entity.User;
import com.jmy.model.PageObject;
import com.jmy.model.ResultCode;
import com.jmy.service.DeptService;
import com.jmy.service.DocumentService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    ServletContext context;

    @Autowired
    private DeptService deptService;

    @Override
    @Transactional
    public void insert(Document document, MultipartFile multipartFile) throws CommonException {
        User Operator = (User) SecurityUtils.getSubject().getPrincipal();
        document.setPublisher(Operator.getUsername());
        document.setPublisherDate(new Date());
        document.setPublisherDept(Operator.getDeptId());
        uploadFile(multipartFile, document,"insert");
        documentMapper.insert(document);
    }

    @Override
    public Map<String, Object> findAllByUser(Integer pageCurrent) {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;
        List<Document> list =  documentMapper.findAllByUser(operator.getUsername(),startIndex, pageSize);
        int rowCount = documentMapper.findRowCountByUser(operator.getUsername());
        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        Map<String,Object> map=
                new HashMap<String,Object>();
        //3.1封装当前页数据
        map.put("list", list);
        //3.2封装分页对象信息
        map.put("pageObject", pageObject);

        return map;
    }

    @Override
    public Map<String, Object> findAllByPerm(Integer pageCurrent,
                                             Map<String, Object> params) {
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;

        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        List<Document> target;
        int rowCount = 0;
        params.put("startIndex",startIndex);
        params.put("pageSize",pageSize);
        if(operator == null){
            target = documentMapper.findAllPublic(params);
            rowCount = documentMapper.findRowCountPublic(params);
        }else{
            Integer deptId = operator.getDeptId();
            String userName = operator.getUsername();
            List<Integer> highDeptId = getHighDeptId(deptId);
            List<Integer> lowDeptId = getLowDeptId(deptId);
            params.put("userName",userName);
            params.put("deptId",deptId);
            params.put("highDeptId",highDeptId);
            params.put("lowDeptId",lowDeptId);
            target = documentMapper.findAllDocumentByPerm(params);
            rowCount = documentMapper.findRowCountByPerm(params);
        }

        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        Map<String,Object> map=
                new HashMap<String,Object>();
        //3.1封装当前页数据
        map.put("list", target);
        //3.2封装分页对象信息
        map.put("pageObject", pageObject);
        return map;
    }

    @Override
    public List<Document> findAllBySort(Integer documentType) {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        List<Document> target = new ArrayList<>();
        if(operator == null){
            target = documentMapper.findAllPublicBySort(documentType);
        }else{
            Integer deptId = operator.getDeptId();
            String userName = operator.getUsername();
            List<Integer> highDeptId = getHighDeptId(deptId);
            List<Integer> lowDeptId = getLowDeptId(deptId);
            target = documentMapper.findDocumentBySort(userName,deptId,highDeptId,lowDeptId,documentType);

        }
        return target;
    }

    private List<Integer> getHighDeptId(Integer deptId){
        List<Integer> list = new ArrayList<>();
        list.add(deptId);
        Integer parentId = deptMapper.findParentId(deptId);
        while (parentId != null && parentId != 0){
            list.add(parentId);
            parentId = deptMapper.findParentId(parentId);
        }
        return list;
    }

    private List<Integer> getLowDeptId(Integer deptId){
        List<Integer> list = new ArrayList<>();
        list.add(deptId);
        List<Integer> childIds = deptMapper.findChildId(deptId);
        while(childIds !=null && !childIds.isEmpty()){
            list.addAll(childIds);
            childIds = deptMapper.findChildIdByList(childIds);
        }
        return list;
    }

    @Override
    public Document findById(Integer id) {
        return documentMapper.findById(id);
    }

    @Override
    public void deleteById(Integer documentId) throws CommonException {
        Document document = documentMapper.findById(documentId);
        if(document == null){
            throw new CommonException(ResultCode.FILE_DELETE_ERROR);
        }
        documentMapper.deleteById(documentId);
        String filePath = document.getPath();
        File file = new File(filePath);
        if(!file.exists()){
            throw new CommonException(ResultCode.FILE_DELETE_ERROR);
        }
        FileUtils.deleteQuietly(file);


    }

    @Override
    @Transactional
    public void update(Document document,MultipartFile multipartFile) throws CommonException {
        if(document.getEnableDown() == null){
            document.setEnableDown(0);
        }
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        Document document2 = documentMapper.findDocumentByNameAndUser(operator.getUsername(), document.getName());
        document.setPublisher(operator.getUsername());
        document.setPublisherDate(new Date());
        document.setPublisherDept(operator.getDeptId());
        if(multipartFile == null){
            documentMapper.update(document);
            return;
        }
        if(document2 != null){
            String filePath = document2.getPath();
            File file = new File(filePath);
            if(file.exists()) {
                FileUtils.deleteQuietly(file);
            }
            uploadFile(multipartFile, document,"update");
            document.setViewCount(0);
            documentMapper.update(document);
        }else{
            uploadFile(multipartFile, document,"update");
            document.setViewCount(0);
            documentMapper.insert(document);
        }
    }

    @Override
    public void updateCount(Integer documentId) {
        documentMapper.updateCount(documentId);
    }

    private void uploadFile(MultipartFile multipartFile,Document document,String type) throws CommonException {
        //获取存放文件在服务器中的路径
        StringBuilder path = new StringBuilder();
        path.append(context.getRealPath("/")+"\\upload");
        String userName = document.getPublisher();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        path.append("\\").append(userName).append("\\").append(date);
        //判断文件夹是否存在，不存在则创建
        File file = new File(path.toString());
        if(!file.exists()) {
            file.mkdirs();
        }

        //获取上传文件的文件名字
        String fileName = multipartFile.getOriginalFilename();
        path.append("\\").append(fileName).toString();
        if("insert".equals(type)){
            Document temp = documentMapper.findDocumentByNameAndUser(userName,document.getName());
            if(temp != null){
                throw new CommonException(ResultCode.FILE_EXITS_ERROR);
            }
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            is = multipartFile.getInputStream();
            os = new FileOutputStream(new File(path.toString()));
            document.setSize(multipartFile.getSize());
            document.setPath(path.toString());
            //定义byte数组
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len=is.read(buffer))!=-1) {
                os.write(buffer, 0, len);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

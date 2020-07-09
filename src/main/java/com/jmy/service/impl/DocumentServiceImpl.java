package com.jmy.service.impl;

import com.jmy.common.exception.CommonException;
import com.jmy.dao.DocumentMapper;
import com.jmy.model.entity.Document;
import com.jmy.model.entity.User;
import com.jmy.model.PageObject;
import com.jmy.model.entity.ResultCode;
import com.jmy.service.DeptService;
import com.jmy.service.DocumentService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private DeptService deptService;

    @Override
    @Transactional
    public void insert(Document document, MultipartFile multipartFile) {
        User Operator = (User) SecurityUtils.getSubject().getPrincipal();
        document.setPublisher(Operator.getUsername());
        document.setPublisherDate(new Date());
        document.setPublisherDept(Operator.getDeptId());
        uploadFile(multipartFile, document);
        documentMapper.insert(document);
    }

    @Override
    public Map<String, Object> findAll(Integer pageCurrent) {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;
        List<Document> list =  documentMapper.findAll(operator.getUsername(),startIndex, pageSize);
        int rowCount = documentMapper.getRowCount(operator.getUsername());
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
//        List<Document> target = new ArrayList();
//        for(Document document : list){
//            if(operator.getDeptId().equals(document.getPublisherDept())){
//                target.add(document);
//            }
//            else if(document.getPermId() == 2){
//                Dept dept = deptService.findById(2);
//                Integer parentId = dept.getParentId();
//                List<Integer> deptIds = new ArrayList<>();
//                deptIds.add(dept.getId());
//                while(dept != null){
//                    dept = deptService.findById(parentId);
//                    parentId = dept.getId();
//                    deptIds.add(dept.getId());
//                }
//            }
//        }
        return map;
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
    public void update(Document document,MultipartFile multipartFile) {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        Document document2 = documentMapper.findById(document.getId());
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
            uploadFile(multipartFile, document);
            documentMapper.update(document);
        }else{
            uploadFile(multipartFile, document);
            documentMapper.insert(document);
        }
    }

    @Override
    public void updateCount(Integer documentId) {
        documentMapper.updateCount(documentId);
    }

    private void uploadFile(MultipartFile multipartFile,Document document){
        //获取存放文件在服务器中的路径
        String path = "D:\\upload\\";

        //判断文件夹是否存在，不存在则创建
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

        //获取上传文件的文件名字
        String fileName = multipartFile.getOriginalFilename();

        InputStream is = null;
        OutputStream os = null;
        try {
            is = multipartFile.getInputStream();
            os = new FileOutputStream(new File(path+"\\"+fileName));
            document.setSize(multipartFile.getSize());
            document.setPath(path+"\\"+fileName);
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

package com.jmy.controller;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.Document;
import com.jmy.model.Result;
import com.jmy.model.ResultCode;
import com.jmy.model.entity.User;
import com.jmy.service.DocumentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file/")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping("listUI")
    public String list(){
        return "attachment/attachment";
    }

    @RequestMapping("editUI")
    public String edit(){
        return "attachment/attachment_edit";
    }


    @RequestMapping(value="upload")
    @ResponseBody
    public Result upload( MultipartFile multipartFile, Document document) throws CommonException {
        if(multipartFile == null){
            throw new CommonException(ResultCode.FILE_NULL_ERROR);
        }
        if(document.getName() == null){
            throw new CommonException(ResultCode.FILE_NAME_ERROR);
        }
        documentService.insert(document,multipartFile);
        return Result.SUCCESS();
    }

    @RequestMapping(value="update")
    @ResponseBody
    public Result update( MultipartFile multipartFile, Document document) throws CommonException {
        documentService.update(document,multipartFile);
        return Result.SUCCESS();
    }

    @ResponseBody
    @RequestMapping("getAllDocument")
    public Result findAllByUser(Integer pageCurrent){
        return new Result(ResultCode.SUCCESS,documentService.findAllByUser(pageCurrent));
    }

    @ResponseBody
    @RequestMapping("getDocuments")
    public Result findAllByPerm(Integer pageCurrent,
                                String publisher,
                                Integer type,
                                String name){
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("type",type);
        searchParams.put("name",name);
        searchParams.put("publisher",publisher);
        return new Result(ResultCode.SUCCESS,documentService.findAllByPerm(pageCurrent,searchParams));
    }

    @ResponseBody
    @RequestMapping("getDocumentsSort")
    public Result findDocumentBySort(Integer documentType){
        return new Result(ResultCode.SUCCESS,documentService.findAllBySort(documentType));
    }

    @ResponseBody
    @RequestMapping("getDocument")
    public Result findById(Integer documentId){
        return new Result(ResultCode.SUCCESS,documentService.findById(documentId));
    }

    @ResponseBody
    @RequestMapping("deleteDocument")
    public Result deleteById(Integer documentId) throws CommonException {
        documentService.deleteById(documentId);
        return Result.SUCCESS();
    }

    @RequestMapping("doDownload")
    @ResponseBody
    public byte[] doDownload(Integer id,HttpServletResponse response)
            throws IOException, CommonException {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        if(operator == null){
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }

        //1.根据id执行查找操作
        Document document=documentService.findById(id);
        //2.设置下载内容类型以及响应头(固定格式)
        response.setContentType("appliction/octet-stream");
        String suffix = document.getPath().substring(document.getPath().lastIndexOf(".") + 1);
        String fileName=URLEncoder.encode(document.getName(),"utf-8");
        response.setHeader("Content-disposition",
                "attachment;filename="+fileName+"."+suffix);
        //3.获得指定文件的路径对象(java.nio.Path)
        Path path= Paths.get(document.getPath());
        //4.读取path路径对应的文件,并返回字节数组
        byte[] documentByte = Files.readAllBytes(path);
        documentService.updateCount(id);
        return documentByte;
    }
}

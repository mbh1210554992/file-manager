package com.jmy.controller;

import com.jmy.model.Document;
import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import com.jmy.service.DocumentService;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.apache.ibatis.builder.ResultMapResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

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
    public Result upload( MultipartFile multipartFile,
                         Document document,
                         HttpServletRequest request) {
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


        documentService.insert(document);

        return Result.SUCCESS();
    }

    @ResponseBody
    @RequestMapping("getAllDocument")
    public Result findAll(){
        return new Result(ResultCode.SUCCESS,documentService.findAll());
    }

    @RequestMapping("doDownload")
    @ResponseBody
    public byte[] doDownload(Integer id,HttpServletResponse response)
            throws IOException{
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
        return Files.readAllBytes(path);
    }
}

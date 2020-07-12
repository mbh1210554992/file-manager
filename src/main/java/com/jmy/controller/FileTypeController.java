package com.jmy.controller;

import com.jmy.model.entity.FileType;
import com.jmy.model.Result;
import com.jmy.model.ResultCode;
import com.jmy.service.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileTypeController {
    @Autowired
    private FileTypeService fileTypeService;

    @ResponseBody
    @RequestMapping(value = "/get/{typeId}")
    public Result findById(@PathVariable("typeId") Integer typeId){
        FileType fileType = fileTypeService.findById(typeId);
        return new Result(ResultCode.SUCCESS,fileType);
    }
}

package com.jmy.controller;

import com.jmy.common.exception.CommonException;
import com.jmy.dao.DocumentMapper;
import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import com.jmy.model.entity.User;
import com.jmy.service.DocumentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @Autowired
    private DocumentMapper documentMapper;

    @RequestMapping("/mainIndex")
    public String mainPage(){
        return "mainPage";
    }

    @RequestMapping("/mainIndex/attachmentList")
    public String attchmentList(){
        return "attachment/main_attachment";
    }

    @RequestMapping("/mainIndex/attachmentEdit")
    public String attchmentEdit(){
        return "attachment/main_attachment_edit";
    }

    @RequestMapping("/mainIndex/login")
    public String mainLogin(){
        return "mainLogin";
    }
    @RequestMapping("/mainIndex/attachmentSort")
    public String attachmentSort(){
        return "attachment/main_sort";
    }

    @ResponseBody
    @RequestMapping("/mainIndex/getAll")
    public Result getAll(){
        return new Result(ResultCode.SUCCESS,documentMapper.getALL());
    }

    @ResponseBody
    @RequestMapping("/mainIndex/isLogin")
    public Result isLogin() throws CommonException {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        if(operator == null){
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }
        return Result.SUCCESS();
    }


}

package com.jmy.controller;

import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import com.jmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("listUI")
    public String listUI(){
        return "/system/user_list";
    }

    @RequestMapping("editUI")
    public String editUI(){
        return "/system/user_edit";
    }

    @ResponseBody
    @RequestMapping("doGetUsers")
    public Result getAllUser(String username,Integer pageCurrent){
        return new Result(ResultCode.SUCCESS, userService.findAllUer(username, pageCurrent));
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("doValidById")
    @ResponseBody
    public Result doValidById(Integer userId,Integer valid){
        userService.validById(userId,valid);
        return new Result(ResultCode.SUCCESS);

    }


}

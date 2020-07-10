package com.jmy.controller;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.User;
import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import com.jmy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
@Slf4j
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

    @ResponseBody
    @RequestMapping("doFindUserById")
    public Result getUserById(Integer userId){
        return new Result(ResultCode.SUCCESS,userService.findUserById(userId));
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("doValidById")
    @ResponseBody
    public Result doValidById(Integer userId,Integer valid) throws Exception {
        userService.validById(userId,valid);
        return new Result(ResultCode.SUCCESS);

    }

    @RequestMapping("addUser")
    @ResponseBody
    public Result addUser(@RequestBody User user) throws CommonException {
        userService.intsertUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("updateUser")
    public Result updateUser(@RequestBody User user){
        return Result.SUCCESS();
    }


}

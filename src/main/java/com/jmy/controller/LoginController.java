package com.jmy.controller;

import com.jmy.common.CommonResult;
import com.jmy.common.exception.ServiceException;
import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }



    @RequestMapping("/user/login")
    @ResponseBody
    public Result userLogin(@RequestBody Map<String,String> loginMap)throws ServiceException {
        String username = loginMap.get("username");
        String password = loginMap.get("password");
        password = new Md5Hash(password,username,3).toString();
        //1.构造登陆令牌
        UsernamePasswordToken upToken = new UsernamePasswordToken(username,password);
        //2.获取subject
        Subject subject = SecurityUtils.getSubject();
        //3.调用login方法，进入realm完成认证
        subject.login(upToken);
        //4.获取sessionId
        String sessionId = (String) subject.getSession().getId();
        return new Result(ResultCode.SUCCESS);
    }
}

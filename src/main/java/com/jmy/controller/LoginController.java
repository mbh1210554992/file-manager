package com.jmy.controller;


import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import com.jmy.model.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }



    @RequestMapping("/user/login")
    @ResponseBody
    public Result userLogin(@RequestBody Map<String,String> loginMap,
                            HttpServletRequest request) {
        String username = loginMap.get("username");
        String password = loginMap.get("password");
        password = new Md5Hash(password,username,3).toString();
        //1.构造登陆令牌
        UsernamePasswordToken upToken = new UsernamePasswordToken(username,password);
        //2.获取subject
        Subject subject = SecurityUtils.getSubject();
        //3.调用login方法，进入realm完成认证
        subject.login(upToken);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("username",user.getUsername());
        return new Result(ResultCode.SUCCESS);
    }
}

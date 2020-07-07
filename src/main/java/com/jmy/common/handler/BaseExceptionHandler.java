package com.jmy.common.handler;


import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的公共异常处理器
 *      1.声明异常处理器
 *      2.对异常统一处理
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        if(e.getClass() == CommonException.class) {
            //类型转型
            CommonException ce = (CommonException) e;
            Result result = new Result(ce.getResultCode());
            return result;
        }else{
            Result result = new Result(ResultCode.SERVER_ERROR);
            return result;
        }
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response,UnauthorizedException e){
        return new Result(ResultCode.UNAUTHORISE);
    }


    @ExceptionHandler(value = UnknownAccountException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response,UnknownAccountException e){
        return new Result(ResultCode.MOBILE_PASSWORD_ERROR);
    }
}

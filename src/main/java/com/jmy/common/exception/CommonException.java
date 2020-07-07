package com.jmy.common.exception;


import com.jmy.model.entity.ResultCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception  {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}

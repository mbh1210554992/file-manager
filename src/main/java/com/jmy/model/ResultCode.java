package com.jmy.model;

public enum ResultCode {

    SUCCESS(true,10000,"操作成功！"),
    //---系统错误返回码-----
    FAIL(false,10001,"操作失败"),
    UNAUTHENTICATED(false,10002,"您还未登录"),
    UNAUTHORISE(false,10003,"权限不足"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),

    //---用户操作返回码----
    MOBILE_PASSWORD_ERROR(false,20001,"用户名或密码错误！"),
    USERNAME_ERROR(false,20002,"用户名已存在请重新填写"),
    USERNAME_VALID_ERROR(false,20003,"用户名已被禁用，请联系系统管理员！"),
    //---部门操作返回码----
    DEPT_CHIRD_ERROR(false,30001,"该部门具有下级部门不能删除"),
    //---权限操作返回码----
    //---其他操作返回码----
    FILE_DELETE_ERROR(false,40001,"文件不存在或已经被删除"),
    FILE_NULL_ERROR(false,40002,"请选择上传文件！"),
    FILE_EXITS_ERROR(false,40003,"文件已存在！"),
    FILE_NAME_ERROR(false,40004,"文件名不能为空！");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}

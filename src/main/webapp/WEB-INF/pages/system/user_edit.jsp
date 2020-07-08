<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<div class="container">
	<!-- 页面导航 -->
	<div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
			<ol class="breadcrumb">
				<li class="active" id="titleId">编辑用户11</li>
			</ol>
		</div>
	</div>
	 <div class="row col-md-12">
			<form class="form-horizontal" id="editUserForm">
				<div class="form-group">
					<div class="col-sm-2 control-label"><font color="red">*</font>用户名：</div>
					<div class="col-sm-10">
						<input type="text" name="userName" id="userName" placeholder="登录账号" class="form-control dynamicClear required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label"><font color="red">*</font>密码：</div>
					<div class="col-sm-10">
						<input type="password" name="password" id="password" placeholder="密码" class="form-control dynamicClear required">
					</div>
				</div>
				<div class="form-group" id="newPwdDiv" style="display:none">
					<div class="col-sm-2 control-label"><font color="red">*</font>新密码：</div>
					<div class="col-sm-10">
						<input type="password" name="newPwd" id="newPwd" placeholder="新密码" class="form-control dynamicClear required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">手机号：</div>
                        <div class="col-sm-10">
                            <input type="text" name = "telephone" id="telephone" placeholder="手机号" class="form-control dynamicClear">
                        </div>
				    </div>

				<div class="form-group">
                	<div class="col-sm-2 control-label">所属部门：</div>
                		<div class="col-sm-10">
                			<input type="text" id="deptName" readonly="readonly"
                				class="form-control required dynamicClear load-sys-menu" style="cursor: pointer;">
                		</div>
                	</div>

                <div class="form-group">
                    <div class="col-sm-2 control-label">备注：</div>
                    <div class="col-sm-10">
                        <input type="text" name="remark" id="remark" placeholder="备注说明" class="form-control dynamicClear required">
                    </div>
                </div>

				<div class="form-group">
					<div class="col-sm-2 control-label"></div>
					<input type="button" class="btn btn-primary" id="btn-ok" value="确定">
					&nbsp;&nbsp;
					<input type="button" value="返回" class="btn btn-warning" id="btn-return">
				</div>

			</form>
	 </div>
	 <!-- 选择菜单 -->
     	<div class="layui-layer layui-layer-page layui-layer-molv layer-anim" id="menuLayer" type="page" times="2" showtime="0" contype="object"
     		style="z-index: 19891016; width: 300px; height: 450px; top: 100px; left: 500px; display:none">
     		<div class="layui-layer-title" style="cursor: move;">选择部门</div>
     		<div class="layui-layer-content" style="height: 358px;">
     			<div style="padding: 10px;" class="layui-layer-wrap">
     				<ul id="menuTree" class="ztree"></ul>    <!-- 动态加载树 -->
     			</div>
     		</div>
     		<span class="layui-layer-setwin"> <a class="layui-layer-ico layui-layer-close layui-layer-close1 btn_cancle" ></a></span>
     		<div class="layui-layer-btn layui-layer-btn-">
     			<a class="layui-layer-btn0 btn-confirm">确定</a>
     			<a class="layui-layer-btn1 btn-cancle">取消</a>
     		</div>
     	</div>
 </div>
<script type="text/javascript" src="${basePath}/js/system/user_edit.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<div class="container">
	<!-- 页面导航 -->
	<div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
		  <ol class="breadcrumb">
			 <li class="active" id="titleId">编辑菜单</li>
		  </ol>
		</div>
	</div>
	<div class="row col-md-12">
		<form class="form-horizontal"  id="editMenuForm">
				<div class="form-group">
					<div class="col-sm-2 control-label">部门名称：</div>
					<div class="col-sm-10">
						<input type="text" id="deptName"  placeholder="请输入部门名称" class="form-control dynamicClear required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">上级部门：</div>
					<div class="col-sm-10">
						<input type="text" id="parentName" readonly="readonly" placeholder="无"
							class="form-control required dynamicClear load-sys-menu" style="cursor: pointer;">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">相关介绍：</div>
					<div class="col-sm-10">
						<input type="text" id="remark" placeholder="请输入相关介绍" class="form-control dynamicClear">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-5 control-label"></div>
					<input type="button" id="btn-save" class="btn btn-primary" value="确定">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="btn-return" value="返回" class="btn btn-warning">
				</div>
			</form>
	</div>
	<!-- 选择菜单 -->
	<div class="layui-layer layui-layer-page layui-layer-molv layer-anim" id="menuLayer" type="page" times="2" showtime="0" contype="object"
		style="z-index: 19891016; width: 300px; height: 450px; top: 100px; left: 500px; display:none">
		<div class="layui-layer-title" style="cursor: move;">选择菜单</div>
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
<script type="text/javascript" src="${basePath}/js/system/dept_edit.js"></script>
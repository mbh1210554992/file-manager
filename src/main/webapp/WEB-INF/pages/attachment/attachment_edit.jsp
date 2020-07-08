<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type"  content="multipart/form-data; charset=utf-8" />

<div class="container">
	<!-- 页面导航 -->
	<div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
		  <ol class="breadcrumb">
			 <li class="active" id="titleId">上传文档</li>
		  </ol>
		</div>
	</div>
	<div class="row col-md-12">
		<form class="form-horizontal"  id="uploadFormId" enctype="multipart/form-data">
				<div class="form-group">
					<div class="col-sm-2 control-label">文档名称</div>
					<div class="col-sm-10">
						<input type="text" id="name"  name = "name" placeholder="请输入文档名称" class="form-control dynamicClear required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">文档摘要：</div>
					<div class="col-sm-10">
						<input type="text" id="abstr" name = "abstr" placeholder="请输入相关摘要" class="form-control dynamicClear">
					</div>
				</div>
				<div class="form-group">
                    <div class="col-sm-2 control-label">文档类型：</div>
                    <div class="col-sm-10">
                        <div class="dropdown">
                            <select id="type" class="form-control" name = "type">
                                <option value="1">法律法规</option>
                                <option value="2">工作规范</option>
                                <option value="3">工作报表</option>
                                <option value="4">制度管理</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">文档访问权限：</div>
                    <div class="col-sm-10">
                        <div class="dropdown">
                            <select id="permId" class="form-control" name ="permId">
                                <option value="1">部门共享</option>
                                <option value="2">部门及以上共享</option>
                                <option value="3">部门及以下共享</option>
                                <option value="4">公开</option>
                                <option value="5">私有</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">选择文档：</div>
                    <div class="col-sm-10">
                        <input type="file" name="multipartFile" class="form-control">
                    </div>
                </div>

				<div class="form-group">
					<div class="col-sm-5 control-label"></div>
					<input type="button" id="btn-upload" class="btn btn-primary" value="上传">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="btn-return" value="返回" class="btn btn-warning">
				</div>
			</form>
	</div>

</div>

<script type="text/javascript" src="${basePath}/js/attach/attach.js">

<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/js/attach/main_attach.js"></script>
<script type="text/javascript" src="${basePath}/js/common/page.js"></script>
<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">查找文档</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="uploadFormId"
		      enctype="multipart/form-data">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li><input type="text" id="documentName" class="form-control" placeholder="文档名称"></li>
					<li><input type="text" id="publisher" class="form-control" placeholder="发布者"></li>
					<li>
						<select id="documentType" class="form-control" >
							<option value="5">所有类型</option>
							<option value="1">法律法规</option>
							<option value="2">工作规范</option>
							<option value="3">工作报表</option>
							<option value="4">制度管理</option>
						</select>
					</li>
					<li class='O1'><button type="button" class="btn btn-primary btn-search" >查询</button></li>
					<li class='O2'><button type="button" class="btn btn-primary btn-upload" >上传</button></li>
				</ul>
			  </div>
			</div>
			<!-- 列表显示内容 -->
			<div class="row col-md-12">
				<table class="table table-bordered" >
					<thead>
						<tr>
							<th>文件名</th>
							<th>文件类型</th>
							<th>文件摘要</th>
							<th>发布者</th>
							<th>发布部门</th>
							<th>发布日期</th>
							<th>浏览次数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId"></tbody>
				</table>
				<%@include file="../common/page.jsp" %>
			</div>
		</form>

	</div>


<script type="text/javascript">
    $('.btn-upload').click(function(){
        $.getJSON('mainIndex/isLogin',function(result){
            if(result.code == 10000){
                var url="mainIndex/attachmentEdit";
                $(".content").load(url);
            }else{
                alert("您还未登录！")
				location.href='mainIndex/login';
            }
        })

    })
</script>






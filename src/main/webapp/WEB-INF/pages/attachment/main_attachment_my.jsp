<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/js/attach/main_attach_my.js"></script>
<script type="text/javascript" src="${basePath}/js/common/page.js"></script>
<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">文档管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="uploadFormId" 
		      enctype="multipart/form-data">

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
        var url="file/editUI?t="+Math.random(1000);
        $(".content").load(url);
    })

    function setTableBodyRows(list){
    	var tBody=$("#tbodyId");
    	tBody.empty();
    	for(var i in list){
			var enableDown;
    		var tr=$("<tr></tr>");
    		tr.data("id",list[i].id);
    		tr.append("<td>"+list[i].name+"</td>");
    		tr.append("<td>"+list[i].typeName+"</td>");
    		tr.append("<td>"+list[i].abstr+"</td>");
    		tr.append("<td>"+list[i].publisher+"</td>");
    		tr.append("<td>"+list[i].deptName+"</td>");
    		tr.append("<td>"+list[i].publisherDate+"</td>");
    		tr.append("<td>"+list[i].viewCount+"</td>");
			if(list[i].enableDown == 1){
				enableDown = '<td><shiro:hasPermission name="file:download"><button type="button" class ="btn btn-default" id="download">下载</button></shiro:hasPermission>';
			}else{
				enableDown ='<td><shiro:hasPermission name="file:download"><button type="button" class ="btn btn-default" id="download" disabled="disabled">下载</button></shiro:hasPermission>';
			}

    		tr.append(enableDown+'<shiro:hasPermission name="file:update"><button type="button" class="btn btn-info" id="update">修改</button></shiro:hasPermission>' +
    			'<shiro:hasPermission name="file:delete"><button type="button" class="btn btn-danger" id="delete">删除</button></shiro:hasPermission></td>')
    	    tBody.append(tr);
    	}
    }
</script>






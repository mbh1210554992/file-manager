<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>
<div class="container">
   <!-- 页面导航 -->
    <div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
			<ol class="breadcrumb">
			  <li class="active" id="titleId">角色管理</li>
			</ol>
		</div>
	</div>
	<form method="post" id="userFormHead">
		<!-- 列表显示内容 -->
		<div class="row col-md-12">
			<table class="table table-bordered" id="userTable">
				<thead>
					<tr>
						<th>角色ID</th>
						<th>角色名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
			<%@include file="../common/page.jsp" %>
		</div>
	</form>
		<!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">对文档的操作权限</h4>
                </div>
                <div class="modal-body">
                    <label class="checkbox-inline">
                        <input type="checkbox" id="inlineCheckbox1" class="permCheckBox" value="1"> 删除文档
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" id="inlineCheckbox2" class="permCheckBox" value="2"> 删除文档评论
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" id="inlineCheckbox3" class="permCheckBox" value="3"> 上传文档
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" id="inlineCheckbox4" class="permCheckBox" value="4"> 下载文档
                    </label>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="perm">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>
<script type="text/javascript" src="${basePath}/js/system/role_list.js"></script>
<script type="text/javascript">
$('#perm').click(doPerm);

function doPerm(){
    var permIds="";
     $.each($('input:checkbox:checked'),function(){
        permIds+=$(this).val()+",";
    })
    var id = $('#myModal').data('id');
    permIds = permIds.substring(0,permIds.length-1);
    var params = {
        id : id,
        permIds : permIds
    }

    $.getJSON('role/setRolePerm',params,function(result){
           if(result.code == 10000){
                alert("分配成功！");
                 $('#myModal').modal('hide');
           }
    })
}
</script>
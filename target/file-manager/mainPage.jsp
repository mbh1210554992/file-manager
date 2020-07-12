<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta HTTP-EQUIV="Expires" CONTENT="0">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>Insert title here</title>

<link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap-datepicker.min.css" />
<link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap-table.min.css"/>
<link rel="stylesheet" href="${basePath}/bootstrap/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap-switch.css"/>
<link rel="stylesheet" href="${basePath}/dist/css/ionicons.min.css"/>
<link rel="stylesheet" href="${basePath}/dist/css/AdminLTE.min.css"/>
<link rel="stylesheet" href="${basePath}/dist/css/skins/_all-skins.min.css"/>
<link rel="stylesheet" href="${basePath}/bootstrap/plugins/ztree/css/metroStyle/metroStyle.css"/>
<link rel="stylesheet" href="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.css"/>

<script src="${basePath}/jquery/jquery-3.2.1.min.js"></script>
<script src="${basePath}/jquery/jquery-ui.min.js"></script>
<script src="${basePath}/jquery/jquery.validate.min.js"></script>
<script src="${basePath}/jquery/jquery.form.js"></script>
<script src="${basePath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/bootstrap/js/bootstrap-datepicker.min.js"></script>
<script src="${basePath}/bootstrap/js/bootstrap-table.min.js"></script>
<script src="${basePath}/bootstrap/js/bootstrap-switch.js"></script>
<script src="${basePath}/bootstrap/plugins/layer/layer.js"></script>
<script src="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.min.js"></script>
<script src="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script src="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.extension.js"></script>
<script src="${basePath}/bootstrap/plugins/treegrid/tree.table.js"></script>
<script src="${basePath}/bootstrap/plugins/ztree/jquery.ztree.all.min.js"></script>
<!-- AdminLTE App -->
<script src="${basePath}/dist/js/app.min.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini" id= "mainBody">
<div class="wrapper">
  <%@include file="WEB-INF/pages/main/header.jsp" %>
  <%@include file="WEB-INF/pages/main/left_main.jsp" %>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <div class="control-sidebar-bg"></div>
</div>

</body>
</html>






<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
         <li class="treeview">
                  <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>主要功能</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a id="load-upload-id"><i class="fa fa-circle-o"></i>查找文档</a></li>
                    <li><a id="load-sort-id"><i class="fa fa-circle-o"></i>文档排名</a></li>
                    <shiro:authenticated>
                        <li><a id="load-myFile-id"><i class="fa fa-circle-o"></i>我的文档</a></li>
                    </shiro:authenticated>
                  </ul>
                </li>
      </ul>
    </section>
    <!-- /.sidebar -->

  </aside>
<script type="text/javascript">



$('#load-sort-id').click(function(){
	var url="mainIndex/attachmentSort";
    $(".content").load(url);
})

$('#load-upload-id').click(function(){
	var url="mainIndex/attachmentList";
	$(".content").load(url);
})

$('#load-myFile-id').click(function(){
    var url="mainIndex/myAttachment";
    $(".content").load(url);
})

</script>
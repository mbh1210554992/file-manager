<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    <li><a id="load-upload-id"><i class="fa fa-circle-o"></i>查看文档</a></li>
                    <li><a id="load-user-id"><i class="fa fa-circle-o"></i>文档排名</a></li>
                    <li><a id="load-perm-id"><i class="fa fa-circle-o"></i>文档分类</a></li>
                  </ul>
                </li>
      </ul>
    </section>
    <!-- /.sidebar -->

  </aside>
<script type="text/javascript">
$('#load-project-id').click(function(){
	var url="project/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-team-id').click(function(){
	var url="team/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-type-id').click(function(){
	var url="type/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-product-id').click(function(){
	var url="product/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-attachment-id').click(function(){
	var url="attachment/attachmentUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-role-id').click(function(){
	var url="role/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-dept-id').click(function(){
	var url="dept/list?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-user-id').click(function(){
	var url="mainIndex/attachmentSort?t="+Math.random(1000);
    $(".content").load(url);
})

$('#load-upload-id').click(function(){
	var url="mainIndex/attachmentList?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-file-id').click(function(){
	var url="file/listUI?t="+Math.random(1000);
	$(".content").load(url);
})

$('#load-perm-id').click(function(){
	var url="perm/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
</script>
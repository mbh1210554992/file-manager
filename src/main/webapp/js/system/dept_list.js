/**
 * 初始化表格的列
 */
var colunms = [
{
	field : 'selectItem',
	radio : true
},
{
	title : '部门ID',
	field : 'id',
	visible : false,
	align : 'center',
	valign : 'middle',
	width : '80px'
},
{
	title : '部门名称',
	field : 'name',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
},
	{
		title: '上级部门',
		field: 'parent_name',
		align: 'center',
		valign: 'middle',
		sortable: true,
		width: '180px'
	}
	];


$(document).ready(function(){
	//编辑
	$('#formHead').on('click','.btn-add,.btn-update',doLoadEditPage)
	//删除
	$('#formHead').on('click','.btn-delete',doDeleteObject)
	doGetObjects();
})

function doGetObjects() {
	var tableId="DeptTable";
	//table.js中
	var table = new TreeTable(tableId,"dept/doFindObjects", colunms);
	table.setExpandColumn(2);
	table.setIdField("id");
	table.setCodeField("id");
	table.setParentCodeField("pid");
	table.setExpandAll(false);
	//初始化table对象发送异步请求
	table.init();
}
/**加载编辑页面到制定位置*/
  function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加部门信息";
	}
	if($(this).hasClass("btn-update")){
		title="修改部门信息"
	    var id=getSelectedId();//获得选中的记录id值
		if(id==-1){
		  alert("请先选择");
		  return;
		}
		$('.content').data('id',id);
	}
	var url="dept/editUI"
	$(".content").load(url,function(){
		$("#titleId").html(title)
	})
  }

  /*获得选中的id值*/
  function getSelectedId(){
	//1.1 获得选中的对象,默认返回值为一个对象数组
	var selections=$("#DeptTable")
	.bootstrapTreeTable("getSelections");
	if(selections.length==0){
	 return -1;//表示没选择任何对象
	}
	//1.2获得选中数组中下标为0的元素id的值
	return selections[0].id;
  }
  //删除菜单项
  function doDeleteObject(){
	var deptId=getSelectedId();
	if(deptId==-1){
		alert("请先选择");
		return;
	}
	var params = {'deptId':deptId};
	var url = 'dept/doDeleteById';
	$.post(url,params,function(result){
		if(result.code==10000){
			alert('删除成功！');
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}


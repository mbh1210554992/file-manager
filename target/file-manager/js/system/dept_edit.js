var zTree; 
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",  //节点数据中保存唯一标识的属性名称
			pIdKey : "parent_id",  //节点数据中保存其父节点唯一标识的属性名称
			rootPId : null  //根节点id
		}
	}
}
$(document).ready(function(){
	//点击上级菜单
	$('#editMenuForm').on('click','.load-sys-menu',doLoadZTreeNodes);
    $("#menuLayer").on("click",".btn-cancle",doHideZtree)
                   .on("click",".btn-confirm",doSetSelectedNode);
	//点击返回
	$('#btn-return').click(doBack);
	//点击确定按钮提交
	$('#btn-save').click(doSaveOrUpdate);
	
	var id = $('.content').data('id');
	if(id)doFindObjectById(id);
});

function doFindObjectById(id){
    console.log(id)
	var url = 'dept/doFindById?id='+id;
	$.post(url,function(result){
		if(result.code ==10000){
		doSetEditFormData(result.data);
		}else{
		alert(result.message);
		}
	})
}
//回显
function doSetEditFormData(dept){
	$('#editMenuForm').data('parentId',dept.parentId);
//	$('input[name="menuType"]').each(function(){
//		if($(this).val()==menu.type){
//			$(this).prop('checked',true);
//		}
//	});
	$('#deptName').val(dept.name);
	$('#parentName').val(dept.parentName);
	console.log("parentName="+dept.parentName);
	$('#remark').val(dept.remark);
}

//提交表单实现保存和修改操作
function doSaveOrUpdate(){
	if($('#editMenuForm').valid()){
		var params = getEditFormData();
		var btnVal = $(this).val();
		var menuId = $('.content').data('menuId');
		var url = menuId?'menu/doUpdateObject.do':'menu/doSaveObject.do';
		params.id = menuId;
		$.post(url,params,function(result){
			if(result.code == 10000){
				alert('操作成功');
				doClearData();
				$('.content').load('menu/list');
			}else{
				alert(result.message);
			}
		})
	}
}

//清空表单数据
function doClearData(){
	$('#editMenuForm .dynamicClear').val('');
	$('.content').data('id','');
	$('#editMenuForm').data('parent_id','');
	$('input[name="menuType"]:first').prop('checked',true);
}

//获取表单参数
function getEditFormData(){
	var params = {
			'type':$('input[name="menuType"]:checked').val(),
			'name':$('#menuName').val(),
			'parentId':$('#editMenuForm').data('parent_id'),
			'url':$('#menuUrl').val(),
			'permission':$('#menuPermission').val(),
			'sort':$('#menuSort').val()
	}
	console.log(params.parentId);
	console.log(params.parentname);
	return params;
}
//返回
function doBack(){
	doClearData();
	var url = 'dept/list';
	$('.content').load(url);
}
//隐藏选择菜单
function doHideZtree(){
	$('#menuLayer').css('display','none');
}

//显示选择菜单
function doLoadZTreeNodes(){
	$('#menuLayer').css('display','block');
	var url ='dept/doFindZtree';
	$.getJSON(url,function(result){
		if(result.code==10000){
			zTree = $.fn.zTree.init($("#menuTree"), setting,result.data);
		}else{
			alert(result.message);
		}
	})
}
//获取选择菜单选中项
function doSetSelectedNode(){
	 var selectedNodes = zTree.getSelectedNodes();
	 var node = selectedNodes[0];
	 $('#menuLayer').css('display','none');
	 $('#parentName').val(node.name);
	 $('#editMenuForm').data('parent_id',node.id);
}


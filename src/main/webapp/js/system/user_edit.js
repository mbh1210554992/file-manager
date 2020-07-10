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
	//点击返回按钮
	$('#btn-return').click(doBack);
	//点击保存/修改按钮
	$('#btn-ok').click(doSaveOrUpdate);
	var userId = $('.content').data('userId');
	//根据id查询用户信息
	if(userId){
	    $('#password').hide();
		doFindObjectById(userId);
	}

	$('#editUserForm').on('click','.load-sys-menu',doLoadZTreeNodes);
	$("#menuLayer").on("click",".btn-cancle",doHideZtree)
                       .on("click",".btn-confirm",doSetSelectedNode);
});
//点击保存/修改按钮
function doSaveOrUpdate(){
	if($('#editUserForm').valid()){   //用valid（），校验的文本框必须有name属性值
		var params = getEditFormData();
		var userId = $('.content').data('userId');
		//var userPwd = userId?$('#newPwd').val():$('#userPwd').val();
		//params.password = userPwd;
		params.id = userId;
		var url = userId?'user/updateUser':'user/addUser';
		$.ajax({
        　　type : "post",
        　　url : url,
        　　data : JSON.stringify(params),
        　　contentType:"application/json",
        　　dataType : "json",
        　　success : function(data) {
            　　if(data.code == 10000){
            　　　　alert('操作成功！');
                    doBack();
            　　　　}else{
            　　　　　alert(data.message);
            　　　　}
            　　}
        })
	}
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
//获取表单参数
function getEditFormData(){
	var userName = $('#userName').val();
	var password = $('#password').val();
	var telephone = $('#telephone').val();
	var remark = $('#remark').val();
	var deptId = $('#editUserForm').data('deptId');
	var roleId = $('#roleId').val();


	var params = {
		'username':userName,
		'password':password,
		'telephone':telephone,
		'deptId':deptId,
		'remark':remark,
		'roleId':roleId
	}
	return params;
}
//点击返回按钮
function doBack(){
	doClearData();
	$('.content').load('user/listUI');
}
//查询所有角色  -- 如果使修改，有roleIdList
function doLoadRoles(roleIdList){
	var url ='user/doFindRoles.do';
	$.getJSON(url,function(result){
		if(result.state==1){
			doSetRoleList(result.data);
			if(roleIdList){
				for(var i in roleIdList){
					$('#roleList input[name="checkedItem"]').each(function(){
						if($(this).val()==roleIdList[i]){
							$(this).prop('checked',true);
						}
					})
				}
			}
		}else{
			alert(result.message);
		}
	})
}
//加载填充角色列表
function doSetRoleList(roleList){
	var parentEle = $('#roleList');
	for(var i in roleList){
		var temp = '<input type="checkbox" name="checkedItem" value="[id]"/><span style="padding-right:30px;">[name]</span>';
		temp = temp.replace('[id]',roleList[i].id).replace('[name]',roleList[i].name);
		parentEle.append(temp);
	}
}
function doFindObjectById(userId){
	var params  = {'userId':userId};
	var url = 'user/doFindUserById';
	$.post(url,params,function(result){
		if(result.code==10000){
		 // console.log(JSON.stringify(result.data));
		  doSetEditFormData(result.data);
		}else{
		  alert(result.message);
		}
	});
}
function doSetEditFormData(user){
	$('#userName').val(user.username);
	$('#password').val(user.password);
	$('#newPwdDiv').css('display','block');
	$('#telephone').val(user.telephone);
	$('#remark').val(user.remark);
	$('#deptName').val(user.deptName);
	$('#editUserForm').data('deptId',user.dept_Id);
	//doLoadRoles(roleIds);
}
//点击返回，保存，修改按钮，清除editForm数据
function doClearData(){
	$('#editUserForm .dynamicClear').val('');
	$('#newPwdDiv').css('display','none');
	$('.content').removeData('userId');
}

//隐藏选择菜单
function doHideZtree(){
	$('#menuLayer').css('display','none');

}

//获取选择菜单选中项
function doSetSelectedNode(){
	 var selectedNodes = zTree.getSelectedNodes();
	 var node = selectedNodes[0];
	 $('#menuLayer').css('display','none');
	 $('#deptName').val(node.name);
	 $('#editUserForm').data('deptId',node.id);
}
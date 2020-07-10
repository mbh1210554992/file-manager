$(document).ready(function(){
//    $('#userFormHead').on('click','.btn-search',doQueryObjects)
//    $('#userFormHead').on('click','.btn-add,.btn-update',doLoadEditPage)
    doGetObjects();
    //$('#perm').click(doPerm);

    $('#tbody').on('click',"#setPerm",showModel);
    $("#myModal")
    	//hidden.bs.modal为固定写法表示模态框隐藏事件
    .on("hidden.bs.modal",function(){
        //.ok上移除click事件
        $(this).off("click","#perm");
        $(this).removeData("id");
    });
});

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
           }else{
                alert(result.message);
                $('#myModal').modal('hide');
           }
    })
}

function showModel(){
    $('#myModal').modal('show');
    var id = $(this).parent().parent().data("id");
    $('#myModal').data('id',id);
    var url = 'role/getPerm?roleId='+id;
    $.getJSON(url,function(result){
           if(result.code = 10000){
           $('.permCheckBox').prop("checked", false);
           var permIds = result.data;
            for(var i in permIds){
                var id = '#inlineCheckbox'+permIds[i];
                $(id).prop('checked',true);
                }
            }else{
                alert(result.message)
            }

           })
}
/*禁用启用用户信息*/
function doValidById(){
	var userId = $(this).parent().parent().data('userId');
	var valid = $(this).val();
	var params = {'userId':userId,'valid':valid};
	var url ='user/doValidById';
	$.post(url,params,function(result){
		if(result.state==10000){
		 alert("操作成功！");
		 doGetObjects();
		}else{
		 alert(result.message);
		 doGetObjects();
		}
	});
}
/*加载编辑页面到制定位置*/
function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加用户";
	}
	if($(this).hasClass("btn-update")){
	    title="修改用户信息"
	    var id=getCheckedId();//获得选中的记录id值
		if(!id){
		  alert("请先选择");return;
		}
		$('.content').data('userId',id);
		console.log("userId="+id);
	}
	var url="user/editUI"
	$(".content").load(url,function(){
		$(".panel-heading").html(title)
	})
}
//获得选中的id，然后拼接成字符串
function getCheckedId(){
	var checkedId;
	$('tbody input[name="checkedItem"]').each(function(){
		if($(this).is(':checked')){  //或者用prop('checked')
			checkedId=$(this).val();
		}
	})
	return checkedId;
}

//条件查询  -- 每次条件查询要将当前也设为1
function doQueryObjects(){
	$("#pageId").data('pageCurrent',1);
	doGetObjects();
}

function doGetObjects(){
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent){
		pageCurrent=1;
	}
	var params = getQueryFormData();
	params.pageCurrent=pageCurrent;
	var url = 'role/doGetRoles';
	$.post(url,params,function(result){
		if(result.code==10000){
			setTableBodyRows(result.data.list);
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	})
}

//初始化列表页面
function setTableBodyRows(list){
	var tBody=$('tbody');
	tBody.empty();
	for(var i in list){

    var tr=$("<tr></tr>");
    tr.data("id",list[i].id);
    tr.append("<td>"+list[i].id+"</td>");
    tr.append("<td>"+list[i].name+"</td>");
    tr.append('<td><button type="button" class ="btn btn-default" id="setPerm">分配权限</button>')
    tBody.append(tr);
    }
}
//获取条件查询条件
function getQueryFormData(){
	var params = {'username':$('#userName').val()}
	return params;
}
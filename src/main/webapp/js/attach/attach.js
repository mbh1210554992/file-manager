$(document).ready(function(){
   $("#uploadFormId")
   .on("click","#btn-upload",doUpload)
   .on("click","#btn-return",doBack)
   .on("click",".btn-down",doDownload);
   doGetObjects();
   //$('#tbodyId').on('click',"#update",doUpload);
});

function doUpload(){
    console.log("覆盖文档");
    $(".content").load("file/editUI");
}

function doBack(){
    $(".content").load("file/listUI");
}
function doDownload(){
	var id=$(this).parent().parent().data("id");
	var url="attachment/doDownload.do?id="+id;
	document.location.href=url;
}
function doGetObjects(){
	var url="file/getAllDocument";
	$.getJSON(url,function(result){
		if(result.code == 10000){
			setTableBodyRows(result.data);
		}else{
			alert(result.message);
		}
	});
}
function setTableBodyRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		tr.append("<td>"+list[i].name+"</td>");
		tr.append("<td>"+list[i].typeName+"</td>");
		tr.append("<td>"+list[i].abstr+"</td>");
		tr.append("<td>"+list[i].publisher+"</td>");
		tr.append("<td>"+list[i].deptName+"</td>");
		tr.append("<td>"+list[i].publisherDate+"</td>");
		tr.append('<td><button class="btn btn-primary">下载</button><button class="btn btn-info" id="update">覆盖</button></td>')
	    tBody.append(tr);
	}
}
/*点击文件上传按钮执行此函数*/
function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	url = "file/upload"
	params = getEditFormData();
//	$.ajax({
//         type: 'POST',
//         url: url,
//         data: params,
//         contentType: false,
//         processData: false,
//         success: function (data) {
//           if(data.code == 10000){
//                    alert("上传成功")
//                }else{
//                    alert("上传失败");
//                }
//         }
//        })

     	//异步提交表单($.ajaxSubmit为异步提交表单)
     	//使用此函数时需要在页面引入(jquery.form.js )
     	$("#uploadFormId").ajaxSubmit({
     		type:"post",
     		url:url,
     		dataType:"json",
     		success:function(data){
     		 if(data.code == 10000){
                alert("上传成功")
                $(".content").load("file/listUI");
            }else{
                alert("上传失败");
                $(".content").load("file/listUI");
            }
          }
     	});
	//$("#uploadFormId").resetForm();
	//return false;//防止表单重复提交的一种方式
}

//获取表单参数
function getEditFormData(){
	var name = $('#name').val();
	var abstr = $('#abstr').val();
	var type = $('#type').val();
	var permId = $('#permId').val();
	var multipartFile = $('#file').val();


	var params = {
		'name':name,
		'abstr':abstr,
		'type':type,
		'permId':permId,
		'multipartFile':multipartFile
	}
	return params;
}

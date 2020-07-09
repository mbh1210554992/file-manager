$(document).ready(function(){
    $("#uploadFormId")
    .on("click","#btn-upload",doUpload)
    .on("click","#btn-return",doBack)
    var documentId = $('.content').data('documentId');
    if(documentId){
        doFindObjectById(documentId);
    }
})

function doFindObjectById(documentId){
    var url = 'file/getDocument?documentId='+documentId

    $.getJSON(url,function(result){
        if(result.code == 10000){
            doSetEditFormData(result.data)
        }else{
            alert(result.message)
        }
    })
}

function doSetEditFormData(document){
	$('#name').val(document.name);
	$('#abstr').val(document.abstr);
	$('#type').val(document.type);
	$('#permId').val(document.permId);
}
function doBack(){
	doClearData();
	$('.content').load('mainIndex/attachmentList');
}

function doClearData(){
    $('.content').removeData('documentId');
}

function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	var url
	var documentId = $('.content').data('documentId');
	if(documentId){
	    url = "file/update?id="+documentId;
	}else{
	    url = "file/upload"
	}


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
                doBack();
            }else{
                alert("上传失败");
                doBack();
            }

          }
     	});
	$("#uploadFormId").resetForm();
	return false;//防止表单重复提交的一种方式
}

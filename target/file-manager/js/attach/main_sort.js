$(document).ready(function(){

    doGetObjects();
    //$('#tbodyId').on('click',".update",doUpdate);
    $('#tbodyId').on('click',".download",doDownload)
        //.on('click','.delete',doDelete);
    $('#typeSearch').click(doGetObjects);

});

function doDownload(){
    var id=$(this).parent().parent().data("id");
    var url="file/doDownload?id="+id;
    $.getJSON('mainIndex/isLogin',function(result){
        if(result.code == 10000){
            document.location.href=url;
        }else{
            alert("您还未登录！")
            location.href='mainIndex/login';
        }
    })


}
function doGetObjects(){
    var url="file/getDocumentsSort";
    var param = {'documentType':$('#documentType').val()}
    $.getJSON(url,param,function(result){
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
        tr.append("<td>"+list[i].viewCount+"</td>");
        if(list[i].enableDown == 1){
            tr.append('<td><button type="button" class ="btn btn-info" id="download"  >下载</button></td>')
        }else{
            tr.append('<td><button type="button" class ="btn btn-info" id="download" disabled="disabled" >下载</button></td>')
        }
        tBody.append(tr);
    }
}




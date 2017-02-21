<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0">	
<title>报名</title>

<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/style.css">
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${ctx }/resources/css/datepicker.css"/>
<style type="text/css">
body {
	background-color: #FFFFCC;
}
#submit1{
	background-color:#fd5e28;
}
input[type=number]::-webkit-inner-spin-button,
input[type=number]::-webkit-outer-spin-button {
-webkit-appearance: none;
}
#upload-image{
	background:url(${ctx}/resources/images/addimage.png);
	width:5em;
	height:5em;
	background-size:100%;
	cursor:cell;
	position:static;
	float:left;
}
.imgbox{
	position:static;
	width:5em;
	height:5em;
	background-size:100%;
	overflow:hidden;
	float:left;
	margin-right:5px;
	margin-bottom:5px;
	
	

}
.imgbox img{
	
	max-height:5em;
	
}
.deleteimg{
	position:absolute;
	margin-left:55px;
	margin-top:-10px;
}
</style>
 <script src="${ctx }/resources/js/bootstrap-datepicker.js"></script>
 <script src="${ctx }/resources/js/upload/jquery.ui.widget.js"></script>
<script src="${ctx }/resources/js/upload/jquery.fileupload.js"></script>
<script>
$(document).ready(function(e) {
   // is_weixn();
});

function is_weixn(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
		
        return true;
    } else {
		alert("请在微信里打开！");
		window.location="error.html";
        return false;
    }
}
</script>

</head>

<body>

<script>
$(function(){ 
     $(".span-left").click(function(){ 
        location.href="${ctx}/user/list?openId=${openId}"       //列表页
    }); 
}); 
</script>


</head>
<body>

<div class="class-top"><span class="top-fontsize">校园运动哒人</span></div>
<div class="span-left"></div>


<div  class="index_box  div-list">
<br>
<form id="regform" action="${ctx}/user/regist"  method="post">
 <div class=" form-group" style="display:table;">
 <p>报名须知：</p>
<p>1. 请填写真实的个人资料，以备后期领奖确认;</p>
<p>2. 上传照片务必是候选人本人运动照，数量为三至四张；为了达到最好的显示效果，图片分辨率为960*1280(3:4);</p>
<p>3. 活动举办方有权对用户的资料进行审核，资料一经确认虚假或不符合活动主题，将取消该用户的投票权。</p>
 <label >图片</label>
	<div id="imglist">
	</div>
	<div id="upload-image" >		
	</div>
	
	      
   <input type="file" style="display:none;"  name="files[]" id="fileImage" size="10" multiple >
   <div style="display:none" id="imgurl">
   </div>
            
  </div>
    <div class="form-group" >
        <label >姓名</label>
        <input type="text" class="form-control" id="name" name="name" autofocus width="50" tabindex="1" >
    </div>
    <div class="form-group  ">
    <label >性别</label><br>
   <select id="sex" name="sex" class="form-control">
   	<option value="3"></option>
  	<option value="1">男</option>
  	<option value="0">女</option>
	</select>
    </div>    
    <div class="form-group">
        <label>年龄</label>
        <input type="number" class="form-control" id="age" name="age"  min="18" max="99" tabindex="2" >
    </div>
    <div class="form-group">
        <label>手机号码</label>
        <input type="number" class="form-control" id="mobile" name="mobile" tabindex="3" >
    </div>
     <div class="form-group">
        <label>微信号</label>
        <input type="text" class="form-control" id="wechat" name="wechat" tabindex="4" >
    </div>  
    <div class="form-group">
        <label>个人简介</label>
        <textarea class="form-control" id="personalProfile" rows="5" cols="30" name="personalProfile" tabindex="5" ></textarea>
    </div>
     <div class="form-group">
     <input type="hidden" id="image_index" value="0">
 

     
       
        <input  class="btn-danger btn-block" id="submit1" type="button" title="提交" value="提交"  >
    
    
</form>
<script>
$(function(){
	

$('#fileImage').fileupload({
	autoUpload: true,//是否自动上传
	url: "${ctx}/upload",//上传地址
	type : "POST",
	dataType: 'json',
	done: function (e, data) {//设置文件上传完毕事件的回调函数
	//	alert(data.result.results.path[0]);
	// $("#image0").val(data.result.results.path[0]);
	var url= data.result.results.path[0];
	var checkurl=url.match("(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$").slice(1);
	
	if(checkurl.length>=1){
	 var html ="";
	 var index_ =$("#image_index").val();
	 
	 html+=" <input type=\"hidden\" id=\"image"+index_+"\" name=\"images["+index_+"].imageUrl\" value=\""+data.result.results.path[0]+"\">";
	 
	 $("#image_index").val(parseInt(index_)+1);
	 $("#imgurl").append(html);
	 
	 var html2 = "";
	 html2 = html2 + '<div id="uploadList_'+ index_ +'" class="imgbox">'
	 + '<div class="deleteimg"><a class="del" data-index="'+index_+'" ><img src="${ctx }/resources/images/del.png" width="20" height="20" /></a></div>'
	 +'<img id="uploadImage_' + index_ + '" src="http://image.dadasports.cn' + data.result.results.path[0] + '"  /></p>'+ '<span id="uploadProgress_' + index_ + '" class="upload_progress"></span>' 
	 +	'</div>';
	 $("#imglist").append(html2);

	}else{
		alert("请上传jpg、gif或png格式图片文件！");
	}
	}
	});
	
/* $("#fileImage").click(function(){
	$("#image_index").val(0);
	 $("#imgurl").html("");
	 $("#preview").html("");
}); */

$("#imglist").on("click",".del",function(){
	
	 var index1 = $(this).attr("data-index");
	$("#uploadList_" + index1).fadeOut();
	
	$("#image"+index1).remove();
	
	$("#uploadList_" + index1).remove();
	 var index_ =$("#image_index").val();
	 $("#image_index").val(parseInt(index_)-1); 
	  $("#imgurl input[type=hidden]").each(function(index, element) {
		    var id = element.id;
		    id = id.replace("image", "");
			if(id!=index){
				 $("#uploadList_"+id).find("a[class='del']").attr("data-index",index);
				var div = $("#uploadList_"+id);
				div.attr("id","uploadList_"+index)
				element.id="image"+index;
				element.name="images["+index+"].imageUrl";
			}
	    });
		
});





$("#submit1").click(function () {
  	var result =	check();
  	if(result==false){
  		return;
  	}	
	  $.ajax({
        url: "${ctx}/user/regist",
        dataType: "json",
        data: $('#regform').serialize(),
        type:"POST",
        success: function (data) {
           alert("报名成功！");
           location.href="${ctx}/user/queryPage";
        },
        error: function () {
           alert("提交失败！");
        }
    });
});

})

function check(){
	var name=document.getElementById("name").value;
	var age=document.getElementById("age").value;
	var sex=$("#sex").val();
	
	var txtphone=document.getElementById("mobile").value;
	var index_ =$("#image_index").val();
	var wechat =document.getElementById("wechat").value;
	
	var countimg=0;
	
	$("#imgurl >input").each(function(){
		countimg=countimg+1;
		});
	
	console.log("countimg="+countimg);
	
	var detail=document.getElementById("personalProfile").value;
	if 	(!index_ ||index_ ==null||index_==0 ){
		alert("请上传图片");
		return false;	
	} else if 	(index_>4 ){
		alert("最多上传4张图片哦");
		return false;	
	}else if 	(index_<3 ||countimg<3 ){
		alert("最小上传3张图片哦");
		return false;	
	}
	if 	(!name ||name ==null ){
		alert("请您输入姓名");
		 $("#age").focus();
		return false;	
	} 
	if 	(name.length>5 ){
		alert("您输入的名字超出5个字符！");
		 $("#age").focus();
		return false;	
	} 
	
	if 	(!sex ||sex ==null ||sex==3 ){
		alert("请选择您的性别");
		return false;	
	} 
	if 	(!age ||age ==null){
		alert("请选择您的年龄");
		return false;	
	} 
	

	if 	(!txtphone ||txtphone ==null|| txtphone.length!=11 ){
		alert("请输入11位有效的手机号码");
		return false;	
	} 
	if 	(!wechat ||wechat ==null ){
		alert("请输入微信号");
		return false;	
	} 


	if 	(!detail ||detail ==null ){
		alert("请输入您的个人简介");
		return false;	
	} 

	if 	(detail.length >200 ){
		alert("您输入的简介超过200字符");
		return false;	
	} 
	
}		
  </script>
  
  </div>

</body>
</html>
<script>
$(function(){ 
		$("#upload-image").on("click",function(){
				$("#fileImage[type=file]").click();
			})
});
</script>

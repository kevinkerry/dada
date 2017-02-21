document.write("<script src='../resources/js/path.js'></script>");

$(function(){
	
	$('#logoupload').fileupload({
		url : url+"upload",
		datatype: 'json',
		autoUpload : true,
		acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
		maxFileSize : 999000,
		disableImageResize : /Android(?!.*Chrome)|Opera/
		.test(window.navigator.userAgent),
		previewMaxWidth : 100,
		previewMaxHeight : 100,
		previewCrop : true
	}).on('fileuploaddone', function (e, data) {
	  		var result=data.result;
	  		var img="";
	  		img=image_url+result.results.path[0];
	  		//alert(img);
	  		var html='<div class="imgbox_logo" data-src="'+result.results.path[0]+'" style="width:170px;z-index:1;background-image: url('+img+');"><img src="'+url+'resources/images/del.png" width="20px" height="20px;" /></div>';
	  		$("#logoimage").html(html);
	  		$('.logo-upload').hide();
      		$("#logoimage").show(); 
	});
	
	
	 $('#logoimage').on("click","img",function(){
	   		$(this).parent(".imgbox_logo").remove();
	   		$('.btn-upload').show();
	   });
	 
	 
	   $("#sumit").on("click", addInviteFriend);

})


function addInviteFriend(){
	var dataSrc = $(".imgbox_logo").attr("data-src");
	if(!isEmpty(dataSrc)) {
			var logo ='<input type="hidden" name="cover" value="'+$(".imgbox_logo").attr("data-src")+'" />';
			$("#logo_image").html(logo);
	}else {
		swal("请上传一张活动封面图！");
    	return false;
	} 
	
	 
	var bTime = $("#bTime");
	var eTime = $("#eTime");
	
	if(bTime.val().length==16){
		$("#beginTime").val(new Date(bTime.val().replace("T"," ")+":00").getTime());
	}else{
		 swal("请输入活动开始时间");
		 return false;
	}
	
    if(eTime.val().length==16){
    	$("#endTime").val(new Date(eTime.val().replace("T"," ")+":00").getTime());
	}else{
		 swal("请输入活动结束时间");
		 return false;
	}

    
    if($("#yqjl").val().length==0){
    	 swal("请输入邀请奖励");
		 return false;
    }else{
    	if(parseInt($("#yqjl").val())<0){
    		swal("邀请奖励不能为负");
   		    return false;
    	}
    }
    
    if($("#zcjl").val().length==0){
    	swal("请输入注册奖励");
		 return false;
    }else{
    	if(parseInt($("#zcjl").val())<0){
    		swal("注册奖励不能为负");
   		    return false;
    	}
    }
	
   
   if($("#hdts").val().length==0){
	   	  swal("请输入推送内容");
		return false;
	}
   
   if($("#tssj").val().length!=16){
	   	  swal("请输入推送时间");
		return false;
	}else {
		$("#cronExpression").val($("#tssj").val().replace("T"," ")+":00");
	}
   
   if($("#gz").val().length==0){
	   	  swal("请输入活动规则");
		return false;
	}
   
   
   $("#expiryDay").val($("#yxSelect").val());
   
  //  return false;
}











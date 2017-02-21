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
	 
	 
	   $("#sumit").on("click", addSnatchActivity);

})


function addSnatchActivity(){
	
	if($("#hdmc").val().length==0){
    	swal("请输入活动名称");
		 return false;
    }
	
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

    
    if($("#csjjc").val().length==0){
    	swal("请输入初始奖金池");
		 return false;
    }else{
    	if(parseInt($("#csjjc").val())<0){
    		swal("初始奖金池不能为负");
   		    return false;
    	}
    }
	
    
    if($("#zscyr").val().length==0){
    	swal("请输入最少参与人");
		 return false;
    }else{
    	if(parseInt($("#zscyr").val())<0){
    		swal("最少参与人不能为负");
   		    return false;
    	}
    }
    
    
    if($("#jjczj").val().length==0){
    	swal("请输入奖金池");
		 return false;
    }else{
    	if(parseInt($("#jjczj").val())<0){
    		swal("奖金池不能为负");
   		    return false;
    	}
    }
    
    
    if($("#zfjy").val().length==0){
    	swal("请输入支付金");
		 return false;
    }else{
    	if(parseInt($("#zfjy").val())<0){
    		swal("支付金不能为负");
   		    return false;
    	}
    }
    if($("#zfjc").val().length==0){
    	swal("请输入支付金次数");
		 return false;
    }else{
    	if(parseInt($("#zfjc").val())<0){
    		swal("支付金次数不能为负");
   		    return false;
    	}
    }
    
    
    if($("#hjr").val().length==0){
    	swal("请输入每期获奖人数");
		 return false;
    }else{
    	if(parseInt($("#hjr").val())<0){
    		swal("每期获奖人数不能为负");
   		    return false;
    	}
    }
    
    if($("#plzs").val().length==0){
    	swal("请输入跑量折算");
		 return false;
    }else{
    	if(parseInt($("#plzs").val())<0){
    		swal("跑量折算不能为负");
   		    return false;
    	}
    }
    
    
   
    
    if($("#mrzdbs").val().length==0){
    	swal("请输入每日最多步数");
		 return false;
    }else{
    	if(parseInt($("#mrzdbs").val())<0){
    		swal("每日最多步数不能为负");
   		    return false;
    	}
    }
    
    if($("#mrzdpl").val().length==0){
    	swal("请输入每日最多跑量");
		 return false;
    }else{
    	if(parseInt($("#mrzdpl").val())<0){
    		swal("每日最多跑量不能为负");
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











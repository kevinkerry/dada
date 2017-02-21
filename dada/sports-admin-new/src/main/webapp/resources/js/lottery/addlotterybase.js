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
	 
	 
	   $("#sumit").on("click", addLotteryBase);

})


function addLotteryBase(){
 
	var dataSrc = $(".imgbox_logo").attr("data-src");
	if(!isEmpty(dataSrc)) {
			var logo ='<input type="hidden" name="logo" value="'+$(".imgbox_logo").attr("data-src")+'" />';
			$("#logo_image").html(logo);
	}else {
		swal("请上传一张封面图！");
    	return false;
	} 
	
	
    if($("#tzcs").val().length==0){
    	swal("请输入投注次数");
		 return false;
    }else{
    	if(parseInt($("#tzcs").val())<0){
    		swal("投注次数不能为负");
   		    return false;
    	}
    }
    
    if($("#tzje").val().length==0){
    	swal("请输入投注金额");
		 return false;
    }else{
    	if(parseInt($("#tzje").val())<0){
    		swal("投注金额不能为负");
   		    return false;
    	}
    }
    
    if($("#jj1").val().length==0){
    	swal("请输入一等奖");
		 return false;
    }else{
    	if(parseInt($("#jj1").val())<0){
    		swal("一等奖不能为负");
   		    return false;
    	}
    }
    
    if($("#jj2").val().length==0){
    	swal("请输入二等奖");
		 return false;
    }else{
    	if(parseInt($("#tzje").val())<0){
    		swal("二等奖不能为负");
   		    return false;
    	}
    }
    
    if($("#jj3").val().length==0){
    	swal("请输入三等奖");
		 return false;
    }else{
    	if(parseInt($("#tzje").val())<0){
    		swal("三等奖不能为负");
   		    return false;
    	}
    }
    
    if($("#jj4").val().length==0){
    	swal("请输入四等奖");
		 return false;
    }else{
    	if(parseInt($("#tzje").val())<0){
    		swal("四等奖不能为负");
   		    return false;
    	}
    }
    
    if($("#jj5").val().length==0){
    	swal("请输入五等奖");
		 return false;
    }else{
    	if(parseInt($("#tzje").val())<0){
    		swal("五等奖不能为负");
   		    return false;
    	}
    }
    
    if($("#ljqx").val().length==0){
    	swal("请输入领奖期限");
		 return false;
    }else{
    	if(parseInt($("#ljqx").val())<0){
    		swal("领奖期限不能为负");
   		    return false;
    	}
    }
    
    if($("#jqyxq").val().length==0){
    	swal("请输入奖券有效期");
		 return false;
    }else{
    	if(parseInt($("#jqyxq").val())<0){
    		swal("奖券有效期不能为负");
   		    return false;
    	}
    }
	 
	var bTime = $("#bTime");
	var eTime = $("#eTime");
	
	if(bTime.val().length==5){
		$("#beginTime").val(new Date("2016-01-01 "+bTime.val()+":00").getTime());
		console.log($("#beginTime").val());
	}else{
		 swal("请输入投注开始时间");
		 return false;
	}
	
    if(eTime.val().length==5){
    	$("#endTime").val(new Date("2030-01-01 "+eTime.val()+":00").getTime());
	}else{
		 swal("请输入投注结束时间");
		 return false;
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
   

}











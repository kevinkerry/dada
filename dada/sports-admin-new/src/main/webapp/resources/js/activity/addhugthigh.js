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
	 
	 
	   //保存抱大腿
	   $("#sumit").on("click",addHugThigh);

})


function addHugThigh(){
	var dataSrc = $(".imgbox_logo").attr("data-src");
	if(!isEmpty(dataSrc)) {
			var logo ='<input type="hidden" name="cover" value="'+$(".imgbox_logo").attr("data-src")+'" />';
			$("#logo_image").html(logo);
	}else {
		swal("请上传一张活动封面图！");
    	return false;
	} 
	
	var adate = $("#adate");
	var bTime = $("#bTime");
	var eTime = $("#eTime");
	
	if(adate.val().length==10){
		var dd = (new Date(adate.val()+" 00:00:00").getTime())/1000;
		$("#date").val(dd)
	}else{
		 swal("请选择活动日期");
		 return false;
	}
	
	if(bTime.val().length==5){
		$("#beginTime").val(new Date(adate.val()+" "+bTime.val()+":00").getTime());
	}else{
		 swal("请输入活动开始时间");
		 return false;
	}
	
    if(eTime.val().length==5){
    	$("#endTime").val(new Date(adate.val()+" "+eTime.val()+":59").getTime());
	}else{
		 swal("请输入活动结束时间");
		 return false;
	}

    if($("#dtme").val().length==0){
    	swal("请输入大腿名额");
		 return false;
    }else{
    	if(parseInt($("#dtme").val())<0){
    		swal("大腿名额不能为负");
   		    return false;
    	}
    }
    
    if($("#dtcs").val().length==0){
    	swal("请输入被抱次数");
		 return false;
    }else{
    	if(parseInt($("#dtcs").val())<0){
    		swal("被抱次数不能为负");
   		    return false;
    	}
    }
	
   for(var i=1;i<=7;i++){
	   if($("#tyj"+i).val().length==0){
	    	swal(i+"级体验金不能为空");
			 return false;
	    }else{
	    	if(parseInt($("#tyj"+i).val())<0){
	    		swal(i+"级体验金不能为负数");
	   		    return false;
	    	}
	    }
	   
	   
	   
	   if($("#yj"+i).val().length==0){
	    	swal(i+"级佣金不能为空");
			 return false;
	    }
   }

	
   if($("#pjbf").val().length==0){
   	    swal("请输入平均步幅");
	   return false;
   }
   
   if($("#pjsd").val().length==0){
	   	  swal("请输入平均速度");
		return false;
	}else{
		if(parseInt($("#pjsd").val())<0){
    		swal("平均速度不能为负数");
   		    return false;
    	}
	}
   
   if($("#zksd").val().length==0){
	   	  swal("请输入最快速度");
		return false;
	}else{
		if(parseInt($("#zksd").val())<0){
    		swal("最快速度不能为负数");
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
   
  //  return false;
}











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
	 
	 
	   $("#sumit").on("click", addRelayRaceActivity);

})


function addRelayRaceActivity(){
	
	 if($("#hdmc").val().length==0){
	   	  swal("请输入活动名称");
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
	
	var dataSrc = $(".imgbox_logo").attr("data-src");
	if(!isEmpty(dataSrc)) {
			var logo ='<input type="hidden" name="cover" value="'+$(".imgbox_logo").attr("data-src")+'" />';
			$("#logo_image").html(logo);
	}else {
		swal("请上传一张活动封面图！");
    	return false;
	}
	
	if($("#dwsl").val().length==0){
	   	  swal("请输入队伍数量");
		return false;
	}
	
	if($("#jlbsl").val().length==0){
	   	  swal("请输入接力棒数量");
		return false;
	}
	
	if($("#bmf").val().length==0){
	   	  swal("请输入报名费");
		return false;
	}
	
	if($("#qtbmf").val().length==0){
	   	  swal("请输入报名费");
		return false;
	}
	
	if($("#xxcj").val().length==0){
	   	  swal("请输入下线层级");
		return false;
	}
	
	if($("#yjqyjl").val().length==0){
	   	  swal("请输入一级邀请奖励");
		return false;
	}
	
	if($("#qtqyjl").val().length==0){
	   	  swal("请输入其他邀请奖励");
		return false;
	}
	
	if($("#jjc").val().length==0){
	   	  swal("请输入奖金池");
		return false;
	}
	
	if($("#bszs").val().length==0){
	   	  swal("请输入步数折算");
		return false;
	}
	
	if($("#mrzdbs").val().length==0){
	   	  swal("请输入每日最多步数");
		return false;
	}
	
	if($("#mrzdpl").val().length==0){
	   	  swal("请输入每日最多跑量");
		return false;
	}
	
	if($("#lxkm").val().length==0){
	   	  swal("请输入路线公里数");
		return false;
	}
	if($("#lxdd").val().length==0){
	   	  swal("请输入路线地点");
		return false;
	}
	
	if($("#yqrc").val().length==0){
	   	  swal("请输入邀请人次");
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
   
   
   
   
   // return false;
}











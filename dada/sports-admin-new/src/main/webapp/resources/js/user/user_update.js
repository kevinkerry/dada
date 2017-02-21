$(function() {
	var url='http://operate.dadasports.cn/upload';
	var image_url="http://image.dadasports.cn";
	$('#head_portrait_upload').fileupload({
		url : url,
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
      		var html='<div class="imgbox_logo" data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');"><img src="http://operate.dadasports.cn/resources/images/del.png" width="30px" height="30px;" /></div>';
      		$("#headPortraitImage").html(html);
    });
	
	 $('#headPortraitImage').on("click","img",function(){
	   	$(this).parent(".imgbox_logo").remove();
     });
	
	$('#user_update_submit').click(function() {
		var dataSrc = $(".imgbox_logo").attr("data-src");
  		if(!isEmpty(dataSrc)) {
  			var logo ='<input type="hidden" name="headPortrait" value="'+$(".imgbox_logo").attr("data-src")+'" />';
  			$("#head_portrait_image").html(logo);
		}else {
			swal("请上传一张封面图！");
	    	return false;
		}
  		
		$('#auth_list').empty();
		$("#auth_container input[type='checkbox']").each(function(index){
		     if($(this).prop("checked")){
		    	 $('#auth_list').append('<input type="hidden" name="authTrans['+$(this).val()+']" value="'+$(this).val()+'" />');
			 }
		 });
		
		$('#role_list').empty();
		$("#role_container input[type='checkbox']").each(function(index){
		     if($(this).prop("checked")){
		    	 $('#role_list').append('<input type="hidden" name="roleTrans['+$(this).val()+']" value="'+$(this).val()+'" />');
			 }
		 });
	});
});


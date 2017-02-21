$(function() {
	var url='http://admin.dadasports.cn/upload';
	var image_url="http://image.dadasports.cn";
	$('#logoupload').fileupload({
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
	  		var html='<div class="imgbox_logo" data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');"><img src="http://admin.dadasports.cn/resources/images/del.png" width="20px" height="20px;" /></div>';
	  		$("#logoimage").html(html);
	  		$('.logo-upload').hide();
      		$("#logoimage").show();
	});
	
   $('#logoimage').on("click","img",function(){
   		$(this).parent(".imgbox_logo").remove();
   		$('.btn-upload').show();
   });
   
   //俱乐部信息提交
   $("#sumit").on("click",function(){
	    var dataSrc = $(".imgbox_logo").attr("data-src");
  		if(!isEmpty(dataSrc)) {
  			var logo ='<input type="hidden" name="clubLogo" value="'+$(".imgbox_logo").attr("data-src")+'" />';
  			$("#logo_image").html(logo);
		}else {
			swal("请上传一张俱乐部封面图！");
	    	return false;
		}
   	});
});

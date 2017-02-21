$(function(){
var url='http://admin.dadasports.cn/upload';
var image_url="http://image.dadasports.cn";
	$('#image_upload').fileupload({
		url : url,
		datatype: 'json',
		autoUpload : true,
		acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
		maxFileSize : 999000,
		// Enable image resizing, except for Android and Opera,
		// which actually support image resizing, but fail to
		// send Blob objects via XHR requests:
		disableImageResize : /Android(?!.*Chrome)|Opera/
		.test(window.navigator.userAgent),
		previewMaxWidth : 100,
		previewMaxHeight : 100,
		previewCrop : true
	}).on('fileuploaddone', function (e, data) {
			var imgLength = $('.imgbox').length;
			if(imgLength >= 8) {
				swal('只能上传8张活动照片！');
				return;
			}
			
      		var result=data.result;
      		var img="";
      		img=image_url+result.results.path[0];
      		var html='<div class="imgbox col-sm-1" id='+imgLength+' data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');" draggable="true" ondragstart="drag(event);"><img src="http://admin.dadasports.cn/resources/images/del.png" width="20px" height="20px;" /></div>';
      		$("#imglist").attr('class', 'col-sm-'+imgLength-1);
      		$("#imglist").append(html);
        });
	
	$('#logoupload').fileupload({
		url : url,
		datatype: 'json',
		autoUpload : true,
		acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
		maxFileSize : 999000,
		// Enable image resizing, except for Android and Opera,
		// which actually support image resizing, but fail to
		// send Blob objects via XHR requests:
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


})

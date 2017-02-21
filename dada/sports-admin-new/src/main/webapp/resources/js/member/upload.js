$(function(){
//var url='http://admin.dadasports.cn/upload';
	 var url='http://operate.dadasports.cn/upload'
	 var image_url="http://image.dadasports.cn";
	//头像
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
  		var html='<div class="imgbox_logo" data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');"><input type="hidden" id="memberLogo2" value="'+result.results.path[0]+'" /><img src="http://operate.dadasports.cn/resources/images/del.png" width="20px" height="20px;" /></div>';
  		$("#logoimage").html(html);
  		$('.logo-upload').hide();
  		$("#logoimage").show();
	});
	
	//相册
	$('#image_upload').fileupload({
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
			var imgLength = $('.imgbox').length;
			if(imgLength >= 20) {
				swal('每个用户最多可以上传20张照片');
				return;
			}
			
      		var result=data.result;
      		var img="";
      		img=image_url+result.results.path[0];
      		var html='<div class="imgbox col-sm-1" id='+imgLength+' data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');" draggable="true" ondragstart="drag(event);"><img src="http://operate.dadasports.cn/resources/images/del.png" width="20px" height="20px;" /></div>';
      		$("#imglist").attr('class', 'col-sm-'+imgLength-1);
      		$("#imglist").append(html);
        });
	
	
	   $('#logoimage').on("click","img",function(){
	  		$(this).parent(".imgbox_logo").remove();
	  		$('.btn-upload').show();
	  });
	   
})




//拖放
function allowDrop(ev) {
	ev.preventDefault();
}

//拖放
function drop(ev) {
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Text");
	document.getElementById('imglist').insertBefore(document.getElementById(data), ev.target.closest('.imgbox')); 
	
}

//开始拖拽事件
function drag(ev){
	ev.dataTransfer.setData("Text",ev.target.id);
}

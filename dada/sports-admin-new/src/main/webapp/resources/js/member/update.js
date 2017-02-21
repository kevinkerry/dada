$(function(){
	//var url='http://admin.dadasports.cn/upload';
	var url='http://operate.dadasports.cn/upload'
	var image_url="http://image.dadasports.cn";
	$('#fileupload').fileupload({
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
      		var html='<div class="imgbox" data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');" draggable="true" ondragstart="drag(event);"><img src="http://operate.dadasports.cn/resources/images/del.png" width="20px" height="20px;" /></div>';
      		$("#imglist").append(html);
        });
	
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
		
		
	//图片删除
   $('#imglist').on("click","img",function(){
	   var object  = $(this).parent(".imgbox");
   		var imgId = object.attr("data-id");
   		if(isEmpty(imgId)) {
   			object.remove();
   		}else {
   			$.ajax({
   	   		   type: "GET",
   	   		   url:ctx +"/memberimage/delete",
   	   		   data: "imgId="+imgId,
   	   		   success: function(msg){
   	   			  object.remove();
   	   		   }
   	   		});
   		}
   });
   
   $('#logoimage').on("click","img",function(){
  		$(this).parent(".imgbox_logo").remove();
  		$('.btn-upload').show();
  });
   
   //表单提交
   $("#submit").on("click",function(){
	    var dataSrc = $(".imgbox_logo").attr("data-src");
 		if(!isEmpty(dataSrc)) {
 			var logo ='<input type="hidden" name="memberLogo" value="'+$(".imgbox_logo").attr("data-src")+'" />';
 			$("#logo_image").html(logo);
		}else {
			swal("请上传一张会员封面图！");
	    	return false;
		}
 		
   		/*//组装图片
   		var html="";
   		var i=0;
   		
   		if($(".imgbox").length <= 0) {
   			swal("请至少上传一张达人图片！");
	    	return false;
   		}else if($(".imgbox").length > 8) {
   			swal("只能上传8张图片，请删除"+($(".imgbox").length - 8)+"张图片！");
	    	return false;
   		}
   		
   		$(".imgbox").each(function(index){
   			html+='<input type="hidden" name="memberImages['+i+'].imgUrl" value="'+$(this).attr("data-src")+'" />';
   			if($(this).attr("data-id") != 'undefinded' && $(this).attr("data-id") != '' && $(this).attr("data-id") != null) {
   				html+='<input type="hidden" name="memberImages['+i+'].imgId" value="'+$(this).attr("data-id")+'" />';
   			}
   			html+='<input type="hidden" name="memberImages['+i+'].imgOrder" value="'+(index + 1)+'" />';
   			i++;
   		});
   		$("#img_list").html(html);*/
   });
});

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

//上传达人照片
function uploadImg() {
	$('#fileupload').click();
}

//上传会员照片
function uploadLogImg() {
	$('#logoupload').click();
}

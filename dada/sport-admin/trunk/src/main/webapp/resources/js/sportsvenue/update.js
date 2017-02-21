$(function() {
	var url='http://admin.dadasports.cn/upload';
	var image_url="http://image.dadasports.cn";
	$('#fileupload').fileupload({
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
			var imgLength = $("#imglist").find('.imgbox').length;
			if(imgLength >= 8) {
				swal('只能上传8张场馆照片！');
			}
	  		var result=data.result;
	  		var img="";
	  		img=image_url+result.results.path[0];
	  		var html='<div class="imgbox"  id="'+result.results.path[0]+'"  data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');" draggable="true" ondragstart="drag(event);"><img src="http://admin.dadasports.cn/resources/images/del.png" width="30px" height="30px;" /></div>';
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
	  		var html='<div class="imgbox_logo col-sm-1" data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');"><img src="http://admin.dadasports.cn/resources/images/del.png" width="30px" height="30px;" /></div>';
	  		$("#logoimage").html(html);
	  		$('.logo-upload').hide();
      		$("#logoimage").show();
	});
	
	//图片删除
   $('#imglist').on("click","img",function(){
	   var object  = $(this).parent(".imgbox");
   		var imgId = object.attr("data-id");
   		
   		$.ajax({
   		   type: "GET",
   		   url:ctx +"/sportvenueimage/delete",
   		   data: "imgId="+imgId,
   		   success: function(msg){
   			object.remove();
   		   }
   		});
   });
   
   $('#logoimage').on("click","img",function(){
   		$(this).parent(".imgbox_logo").remove();
   		$('.logo-upload').show();
   });
   
   //场馆信息提交
   $("#sumit").on("click",function(){
	    var dataSrc = $(".imgbox_logo").attr("data-src");
  		if(!isEmpty(dataSrc)) {
  			var logo ='<input type="hidden" name="venueLogo" value="'+$(".imgbox_logo").attr("data-src")+'" />';
  			$("#logo_image").html(logo);
		}else {
			swal("请上传一张场馆封面图！");
	    	return false;
		}
  		
		//组装图片
   		var html="";
   		var i=0;
   		
   		if($(".imgbox").length < 4) {
   			swal("请至少上传4张场馆图片！");
	    	return false;
   		}else if($(".imgbox").length > 8) {
   			swal("只能上传8张图片，请删除"+($(".imgbox").length - 8)+"张图片！");
	    	return false;
   		}
   		$(".imgbox").each(function(index){
   			html+='<input type="hidden" name="venueImages['+i+'].imgUrl" value="'+$(this).attr("data-src")+'" />';
   			if(!isEmpty($(this).attr("data-id"))) {
   				html+='<input type="hidden" name="venueImages['+i+'].imgId" value="'+$(this).attr("data-id")+'" />';
   			}
   			html+='<input type="hidden" name="venueImages['+i+'].imgOrder" value="'+(index + 1)+'" />';
   			i++;
   		});
   		
   		var venueGrade = $('#venue_form input[name="venueGrade"]');
   		if(isEmpty(venueGrade)) {
   			swal('请对场馆做出评价！');
   			return false;
   		}
   		$("#img_list").html(html);
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
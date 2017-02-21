function checkUrl(str) { 
	var RegUrl = new RegExp(); 
	RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");//www.dadasports.cn
	if (!RegUrl.test(str)) { 
		return false; 
	} 
	return true; 
} 

function validateTime() {
	var startApplyTime = $('#activity_form input[name="applyStartTime"]').val();
   	var endApplyTime = $('#activity_form input[name="applyEndTime"]').val();
   	var beginTime = $('#activity_form input[name="actStartTime"]').val();
    var endTime = $('#activity_form input[name="actEndTime"]').val();
    startApplyTime = startApplyTime.replace("T", " ");
    endApplyTime = endApplyTime.replace("T", " ");
    beginTime = beginTime.replace("T", " ");
    endTime = endTime.replace("T", " ");
    
    startApplyTime = new Date(startApplyTime).getTime();
    endApplyTime = new Date(endApplyTime).getTime();
    beginTime = new Date(beginTime).getTime();
    endTime = new Date(endTime).getTime();
    if(endApplyTime < startApplyTime) {
    	swal("报名结束时间不能在报名开始时间之前！");
    	return false;
    }
    
    if(beginTime < endApplyTime) {
    	swal("活动开始时间不能在报名结束时间之前！");
    	return false;
    }
    if(endTime < beginTime) {
    	swal("活动结束时间不能在活动开始时间之前！");
    	return false;
    }
    return true;
}

$(function(){
	//图片删除
   $('#imglist').on("click","img",function(){
	   var object  = $(this).parent(".imgbox");
   		var imgId = object.attr("data-id");
   		
   		$.ajax({
   		   type: "GET",
   		   url:ctx +"/activityimage/delete",
   		   data: "imgId="+imgId,
   		   success: function(msg){
   			object.remove();
   		   }
   		});
   });
   
    $('#logoimage').on("click","img",function(){
   		$(this).parent(".imgbox_logo").remove();
   		$('.btn-upload').toggle();
   });
   
   //表单提交
   $("#sumit").on("click",function(){
	   var typeSelect = $('#activity_form input[name="type"]').val();
	   var dataSrc = $(".imgbox_logo").attr("data-src");
		if(dataSrc != 'undefinded' && dataSrc != '' && dataSrc != null) {
			var logo ='<input type="hidden" name="logoUrl" value="'+$(".imgbox_logo").attr("data-src")+'" />';
			$("#logo_image").html(logo);
		}else {
			swal("请上传一张封面图！");
	    	return false;
		}
	   if(typeSelect == 1) {
		   validateTime();
		   var linkAddress = $('#activity_form input[name="linkAddress"]').val();
		   if(!checkUrl(linkAddress)) {
			   swal('链接地址格式不正确，请重新输入！');
			   return false;
		   }
		}else if(typeSelect == 0) {
	   		//组装图片
	   		var html="";
	   		var i=0;
	   		
	   		if($(".imgbox").length <= 0) {
	   			swal("请至少上传一张活动图片！");
		    	return false;
	   		}else if($(".imgbox").length > 8) {
	   			swal("只能上传8张图片，请删除"+($(".imgbox").length - 8)+"张图片！");
		    	return false;
	   		}
	   		
	   		$(".imgbox").each(function(index){
	   			html+='<input type="hidden" name="activityImages['+i+'].imgUrl" value="'+$(this).attr("data-src")+'" />';
	   			if(!isEmpty($(this).attr("data-id"))) {
	   				html+='<input type="hidden" name="activityImages['+i+'].imgId" value="'+$(this).attr("data-id")+'" />';
	   			}
	   			html+='<input type="hidden" name="activityImages['+i+'].imgOrder" value="'+(index + 1)+'" />';
	   			i++;
	   		});
	   		$("#img_list").html(html);
		}
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


$(function() {
	var url='http://admin.dadasports.cn/upload';
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
      		var html='<div class="imgbox_logo" data-src="'+result.results.path[0]+'" style="z-index:1;background-image: url('+img+');"><img src="http://admin.dadasports.cn/resources/images/del.png" width="30px" height="30px;" /></div>';
      		$("#headPortraitImage").html(html);
    });
	
	$('#user_submit').click(function() {
		var username = $('#user_add_form input[name="username"]').val();
		username = encodeURI(encodeURI(username));
		if(isEmpty(username)) {
			swal('用户名不能为空!');
			return false;
		}
		
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
		
		$.ajax({
			   type: "POST",
			   url: ctx+"/user/check",
			   data: 'username='+username,
			   success: function(data){
			   		if(data.state == 'failed') {
			   			swal(data.results.msg);
			   			return false;
			   		}
			   		$('#user_add_form').submit();
			   }
		});
		return false;
	});
});

//查询所有权限信息
function queryAuth() {
	$.ajax({
	   type: "GET",
	   url: ctx+"/auth/queryAll",
	   success: function(data){
	   		//console.info(data);
	   		if(data.state == 'success') {
	   			var authList = data.results.authList;
	   			if(authList != null && authList.length > 0) {
	   				$('#auth_container').empty();
	   				$.each(authList, function(index, auth) {
	   					$('#auth_container').append('<li class="checkbox-inline">'
			   					+'<input type="checkbox" value="'+auth.id+'"/>'+auth.name+''
								+'</li>');
	   					index = index + 1;
		   				if(index % 6 == 0) {
		   					$('#auth_container').append('<br/>');
		   				}
		   			});
	   			}
	   		}
	   }
	});
}

//查询所有角色信息
function queryRole() {
	$.ajax({
	   type: "GET",
	   url: ctx+"/role/queryAll",
	   success: function(data){
	   		//console.info(data);
	   		if(data.state == 'success') {
	   			var roleList = data.results.roleList;
	   			if(roleList != null && roleList.length > 0) {
	   				$('#role_container').empty();
	   				$.each(roleList, function(index, role) {
	   					$('#role_container').append('<li class="checkbox-inline">'
			   					+'<input type="checkbox" value="'+role.id+'"/>'+role.name+''
								+'</li>');
	   					index = index + 1;
		   				if(index % 6 == 0) {
		   					$('#role_container').append('<br/>');
		   				}
		   			});
	   			}
	   		}
	   }
	});
}

$(function() {
	//加载权限信息
	queryAuth();
	
	//加载角色信息
	queryRole();
});
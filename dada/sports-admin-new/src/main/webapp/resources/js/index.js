$(function () {
	var windowHeight = $(window).height();
	var headerHeight = $('#frame-header').height();
	var footerHeight = $('#frame-footer').height();
	$('#frame-center').height((windowHeight - headerHeight - footerHeight - 10) +"px");
	
	var activeIndex = null;
	
	$('.first-nav>li>a').click(function() {
		if($(this).closest('li').attr('data-id') != 'index') {
			$(this).closest('li').click();
			return false;
		}
	});
	$('.second-nav>div>li>a').click(function() {
		$(this).closest('li').click();
		return false;
	});
	
	//一级导航栏
	$('.first-nav > li').each(function(i) {
		$(this).on('click', function(e) {
			$('.first-nav > li').removeClass('active');
			$(this).toggleClass('active');
			$('.second-nav >div').css('display', 'none');
			var dataId = $(this).attr('data-id');
			if(dataId == 'index') {
				$(this).closest('a').click();
			}else {
				$('#'+dataId).css('display', 'block');
				
				//默认选中二级导航的第一列
				$('#'+dataId+'> li:eq(0)').click();
			}
			
			e.stopPropagation();
		});
	});
	
	//二级导航栏
	$('.second-nav >div> li').each(function(i) {
		$(this).on('click', function(e) {
			if($('.second-nav >div> li.active').next().hasClass('third-nav') && !$(this).hasClass('active') && $('.second-nav >div> li.active').find('i').hasClass('glyphicon-chevron-down')) {
				activeIndex = null;
				$('.second-nav >div> li.active').next('.third-nav').slideToggle("slow");
				$('.second-nav >div> li.active').find('i').toggleClass('glyphicon-chevron-down');
				$('.second-nav >div> li.active').next('.third-nav').find('li.active').removeClass('active');
			}
			$('.second-nav >div> li.active').removeClass('active');
			$(this).toggleClass('active');
			if($(this).next().hasClass('third-nav')) {
				if($(this).find('i').hasClass('glyphicon-chevron-down')) {
					$(this).next('.third-nav').slideUp("slow");
				}else {
					//三级导航选中的回显
					$(this).next('.third-nav').slideDown("slow");
					if(!isEmpty(activeIndex)) {
						$(this).next('.third-nav').children('li').eq(activeIndex).addClass('active');
					}
				}
				$(this).find('i').toggleClass('glyphicon-chevron-down');
				
			}else {
				window.parent.mainFrame.location.href = $(this).find('a').attr('href');
			}
			e.stopPropagation();
		});
	});
	
	//三级导航栏
	$('.third-nav > li').each(function(i) {
		$(this).on('click', function(e) {
			$('.third-nav > li').removeClass('active');
			$(this).toggleClass('active');
			activeIndex = $(this).index();
			e.stopPropagation();
		});
	});
	
	//默认选中一级导航的第一列
	$('.first-nav > li:eq(0)').click();
	
	//弹出用户设置
	$('#user_setting').on('click', userSetting);
	
	//验证用户密码
	$('#change_user_password_submit').click(function() {
		var oldPassword = $('#change_password_form input[name="oldPassword"]');
		var password = $('#change_password_form input[name="password"]');
		var repeatPassword = $('#change_password_form input[name="repeatPassword"]');
		
		if(isEmpty(oldPassword.val())) {
			swal('原始密码不能为空，请输入原始密码！');
			return false;
		}
		if(isEmpty(password.val())) {
			swal('新密码不能为空，请输入新密码！');
			return false;
		}
		if(isEmpty(repeatPassword.val())) {
			swal('确认密码不能为空，请输入确认密码！');
			return false;
		}
		if(oldPassword.val() == password.val()) {
			swal('新密码不能和原始密码相同，请重新输入新密码！');
			password.val('');
			return false;
		}
		if(repeatPassword.val() != password.val()) {
			swal('两次输入的密码不一样,请重新输入确认密码！');
			repeatPassword.val('');
			return false;
		}
		
		$.ajax({
			type: "POST",
		    url: ctx + "/adminuser/updatePassword",
		    data: "password="+password.val()+"&oldPassword="+oldPassword.val(),
		    success: function(data){
		      if(data.state == 'failed') {
		    	  swal(data.message);
		    	  oldPassword.val('');
		      }else {
		    	  swal('', '密码修改成功',"success");
		    	  password.val('');
		    	  oldPassword.val('');
		    	  repeatPassword.val('');
		      }
		    }
		});
	});
	
	$('#user_profile_current_change_button').on('click', function() {
		$('#fileupload').click();
	});
});

$(function() {
	var url='http://admin.dadasports.cn/upload';
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
  		var path=result.results.path[0];
  		
  		$.ajax({
			type: "GET",
		    url: ctx + "/adminuser/changeheadPortrait",
		    data: "userId="+$('#userId').val()+"&headPortrait="+path,
		    success: function(data){
		    	swal('', '图像更换成功',"success");
		    	$('.user_profile_current_head_img').attr('src', image_url+path);
		    }
		});
    });
});

/**
 * 用户设置
 */
function userSetting() {
	$('#user_setting_model').modal({
		keyboard: false,
		show: true
	});
}


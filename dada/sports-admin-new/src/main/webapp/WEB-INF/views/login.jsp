<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    <title>登录</title>
    <!-- Bootstrap -->
    <link href="${ctx }/resources/css/login.css" rel="stylesheet" media="screen"/>
  </head>
  <body>
    <div class="container">
    	<div class="login">
    		<form id="login_form" class="form-signin" action="${ctx }/adminuser/login" method="post">
    			<div class="form-signin-heading">登&nbsp;录</div>
		        <div class="input-group">
				  <span class="input-group-addon">
				  	<i class="glyphicon glyphicon-user"></i>
				  </span>
				  <input type="text" name="username" class="form-control" placeholder="用户名">
				</div>
				<div class="input-group">
				  <span class="input-group-addon">
				  	<i class="glyphicon glyphicon-lock"></i>
				  </span>
				  <input type="password" name="password" class="form-control" placeholder="密码"/>
				</div>
				<div class="checkbox"> 
					<label> 
						<input id="agree" type="checkbox"/>Remember me
					</label> 
				</div>
		        <button class="btn btn-primary btn-lg btn-block" id="submit" type="submit">登&nbsp;录</button>
		        <p class="login-validation" style="display: none;">
		        	<i class="glyphicon glyphicon-lock"></i>
		        	<span></span>
				</p>
		      </form>
    	</div>
    </div> 
     <footer id="login_footer">
	  	<div align="center" style="margin-top: 15px;">
	  		© 2015 GungZhou dadasports Co., Ltd. All Rights Reserved
	  	</div>
	  </footer> 
	  <script type="text/javascript">
	  		$(function() {
	  			var loginFilureInfo = '${loginFilureInfo}';
	  			var loginFilureReason = '${loginFilureReason}';
	  			if(loginFilureReason) {
	  				$('.login-validation').css('display', 'block');
	  				var html = "";
	  				if(loginFilureReason == 'IncorrectCredentials') {
	  					html = '<span style="color:red">'+loginFilureInfo+'</span>。<a href="#">忘记密码？</a>';
	  				}else {
	  					html = '<span style="color:red">'+loginFilureInfo+'</span>';
	  				}
	  				$('.login-validation').find('span').html(html);
	  			}
	  			
	  			$('#submit').click(function() {
	  				var username = $('#login_form input[name="username"]').val();
	  				var password = $('#login_form input[name="password"]').val();
	  				if(isEmpty(username)) {
	  					$('.login-validation').css('display', 'block');
	  					$('.login-validation').find('span').html('<span style="color:red">用户名不能为空</span>');
	  					return false;
	  				}
					if(isEmpty(password)) {
						$('.login-validation').css('display', 'block');
						$('.login-validation').find('span').html('<span style="color:red">密码不能为空</span>');
						return false;
	  				}
	  			});
	  		});
	  </script>
  </body>
</html>

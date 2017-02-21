<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增用户</title>
    <script type="text/javascript">
    	var roles = [];
    	var auths = [];
    	var ctx = '${ctx}';
    </script>
    <style type="text/css">
		#logoimage{
			display: inline-flex;
			
		}
		.imgbox_logo{
			width:100px;
			height:100px;
			background-size: cover;
			margin-right:15px;
		}
		.imgbox_logo img{
			z-index:100;float: right;margin-top: -15px;margin-right:-15px; 
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<form id="user_form" class="form-horizontal" action="${ctx}/adminuser/update" method="post">
			<input name="id" type="hidden" value="${user.id}"/>
			<div class="form-group">
				<label class="col-sm-1 control-label" >用户头像：</label>
				<div class="col-md-2">
					<div class="input-group">
				      <input type="text" class="form-control" placeholder="点击上传活动封面"  onclick="uploadLogImg();"/>
				      <span class="input-group-btn">
				        <label class="btn btn-default" onclick="uploadLogImg();"><span class="glyphicon glyphicon-folder-open" style="height: 20px;"></span></label>
				      </span>
				    </div>
					<input id="head_portrait_upload" type="file" style="display: none;"/>
				</div>
			</div>
			<div class="form-group" id="head_portrait_image" style="display: none">
				
			</div>
			<div class="form-group" id="headPortraitImage" style="margin-left: 11.5rem;">
				<c:if test="${!empty user.headPortrait}">
					<div class="imgbox_logo" data-src="${user.headPortrait}" style="z-index:1;background-image: url('${image}${user.headPortrait}');">
						<img src="${ctx}/resources/images/del.png" width="30px" height="30px;" />
					</div>
				</c:if>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >用户名：</label>
				<div class="col-sm-2">
					<input class="form-control" name="username" placeholder="请输入用户名" type="text" value="${user.username}" required="required" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >用户昵称：</label>
				<div class="col-sm-2">
					<input class="form-control" name="nickname" placeholder="请输入用户昵称" type="text" value="${user.nickname}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >Email：</label>
				<div class="col-sm-2">
					<input class="form-control" name="email" placeholder="请输入Email"  value="${user.email}" type="text" pattern="^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$" pattern-msg="邮箱格式不正确，请重新输入!"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1" >生日：</label>
				<div class="col-sm-1">
					<input class="input-sm form-control" name="birthday" type="date"  value="${user.birthday}" style="width: 170px;" required="required" />
				</div>
			</div>  
			<div class="form-group">
				<label class="col-sm-1 control-label" >性别：</label>
				<div class="col-sm-4">
					<label class="radio-inline">
					  <input type="radio" name="sex" value="1" required="required"/> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="sex" value="0" required="required"/> 女
					</label>
					<label class="radio-inline">
					  <input type="radio" name="sex" value="3"required="required"/> 保密
					</label>
				</div>
			</div>
			<div id="role_list" style="display: none;"></div>
			<div class="form-group">
				<label class="control-label col-sm-1" >角色：</label>
				<div class="col-sm-4" id="role_container">
				</div>
			</div>
			<div id="auth_list" style="display: none;"></div>
			<div class="form-group">
				<label class="control-label col-sm-1" >权限：</label>
				<div class="col-sm-5" id="auth_container">
				</div>
			</div>  
			<c:forEach items="${user.roles}" var="role">
				<script type="text/javascript">
					roles.push('${role.id}');
				</script>
			</c:forEach>
			<c:forEach items="${user.auths}" var="auth">
				<script type="text/javascript">
					auths.push('${auth.id}');
				</script>
			</c:forEach>
			<div class="form-group">
				<button class="btn btn-primary col-md-offset-2" value="保存" type="submit" id="user_update_submit">保存</button>
				<button class="btn btn-default" onclick="javascript :history.back(-1);">关闭</button>
			</div>
		</form>
	</div>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/user/user_update.js"></script>
    <script type="text/javascript">
		$(function() {
			$('#user_form input[value="${user.sex}"]').attr('checked', true);
			
			//加载权限信息
			queryAuth();
			//加载角色信息
			queryRole();
		});
		
		//上传用户封面照片
		function uploadLogImg() {
			$('#head_portrait_upload').click();
		}
		
		//修改用户时加载权限信息
		function loadAuth(auths) {
			$.each(auths, function(index, authId) {
				
				$("#auth_container input[type='checkbox']").each(function(index){
				     if($(this).val() == authId){
				    	 $(this).prop('checked', true);
					 }
				 });
			});
		}
		
		//修改用户时加载角色信息
		function loadRole(roles) {
			$.each(roles, function(index, roleId) {
				$("#role_container input[type='checkbox']").each(function(index){
				     if($(this).val() == roleId){
				    	 $(this).prop('checked', true);
					 }
				 });
			});
		}
		
		//查询所有权限信息
		function queryAuth() {
			$.ajax({
			   type: "GET",
			   url: "${ctx}/auth/queryAll",
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
			   				loadAuth(auths);
			   			}
			   		}
			   }
			});
		}

		//查询所有角色信息
		function queryRole() {
			$.ajax({
			   type: "GET",
			   url: "${ctx}/role/queryAll",
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
			   				
			   				loadRole(roles);
			   			}
			   		}
			   }
			});
		}
	</script>
</body>
</html>
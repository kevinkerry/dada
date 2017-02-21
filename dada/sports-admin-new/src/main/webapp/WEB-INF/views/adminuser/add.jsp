<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增用户</title>
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
		<form class="form-horizontal" action="${ctx}/adminuser/add" method="post" id="user_add_form">
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
				
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >用户名：</label>
				<div class="col-sm-2">
					<input class="form-control" name="username" placeholder="请输入用户名" type="text" required="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >用户昵称：</label>
				<div class="col-sm-2">
					<input class="form-control" name="nickname" placeholder="请输入用户昵称" type="text" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >Email：</label>
				<div class="col-sm-2">
					<input class="form-control" name="email" placeholder="请输入Email" type="email"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1" >生日：</label>
				<div class="col-sm-1">
					<input class="input-sm form-control" name="birthday" type="date" style="width: 170px;" required="required" />
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
			<div class="form-group">
				<button class="btn btn-primary col-md-offset-2" value="保存" type="submit" id="user_submit">保存</button>
				<button class="btn btn-default" onclick="javascript :history.back(-1);">关闭</button>
			</div>
		</form>
	</div>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/user/user_add.js"></script>
    <script type="text/javascript">
    	var ctx = '${ctx}';
    	
    	//上传用户封面照片
		function uploadLogImg() {
			$('#head_portrait_upload').click();
		}
    </script>
</body>
</html>
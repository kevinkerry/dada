<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增勋章</title>
	<style type="text/css">
		#mapContainer{height:480px;}
			#result1{
				position:absolute;
				z-index: 100;
				background-color: white;
			}
			#imglist{
				display: inline-flex;
				
			}
			.imgbox{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
				margin-left: 1rem;
			}
			.imgbox img{
				z-index:100;float: right;margin-top: -10px;margin-right:-25px; 
			}
			
			#logoimage{
				display: inline-flex;
				
			}
			.imgbox_logo{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
			}
			.imgbox_logo img{
				z-index:100;float: right;margin-top: -10px;margin-right:-10px; 
			}
			.imgbox_logo2{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
			}
			.imgbox_logo2 img{
				z-index:100;float: right;margin-top: -10px;margin-right:-10px; 
			}
			
			
			.btn-upload, .logo-image {
			    display: inline-block;
			    width: 72px;
			    height: 72px;
			    background: url(${ctx}/resources/images/addimage.png) no-repeat;
			    background-size: contain;
			    margin-left: 1rem;
			}
			.btn-upload>input {
			    opacity: 0;
			    height: 100%;
			    width: 100%;
			}
			
			.btn-upload2, .logo-image {
			    display: inline-block;
			    width: 72px;
			    height: 72px;
			    background: url(${ctx}/resources/images/addimage.png) no-repeat;
			    background-size: contain;
			    margin-left: 1rem;
			}
			.btn-upload2>input {
			    opacity: 0;
			    height: 100%;
			    width: 100%;
			}
	</style>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" action="${ctx}/medal/addMedal" method="post">
			<input type="hidden" id="category" name="category" />
			<input type="hidden" id="type" name="type" />
			
			<div class="form-group">
				<label class="col-sm-1 control-label" >勋章类型：</label>
				<div  class="col-sm-3  form-inline">
						<input   type="radio"   name="xz" value="2" checked="checked" onclick="xzType(1)" />活动勋章 &nbsp;
						<input   type="radio"   name="xz" value="1" onclick="xzType(1)" />日常勋章
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >勋章名称：</label>
				<div  class="col-sm-3  form-inline">
					 <input class="form-control" id="xzmc" name="name" maxlength="10" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >未点亮勋章：</label>
				<div class="col-md-2">
					    <div class="col-sm-0 btn-upload logo-upload">
							<input id="logoupload" type="file" />
						</div>
						<div id="logoimage" style="display: none;"></div>
						<div class="form-group" id="logo_image" style="display: none"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >已点亮勋章：</label>
				<div class="col-md-2">
					    <div class="col-sm-0 btn-upload2 logo-upload2">
							<input id="logoupload2" type="file" />
						</div>
						<div id="logoimage2" style="display: none;"></div>
						<div class="form-group" id="logo_image2" style="display: none"></div>
				</div>
			</div>
			
			<div id="rc" style="display: none">
				<div class="form-group" >
					<div  class="col-sm-3  form-inline">
						<input type="radio" name="rdo" id="r1"   />第一次完成：
						<input class="form-control" type="number"  id="v1"  style="width: 100px;"/>km
					</div>
				</div>
				<div class="form-group" >
					<div  class="col-sm-3  form-inline">
						<input type="radio" name="rdo"  id="r2"  />第一次完成：
						<input class="form-control" type="number"  id="v2" style="width: 100px;"/>步
					</div>
				</div>
				<div class="form-group" >
					<div  class="col-sm-3  form-inline">
						<input type="radio" name="rdo"  id="r3"  />累计跑步：
						<input class="form-control" type="number" id="v3"  style="width: 100px;"/>km
					</div>
				</div>
				<div class="form-group" >
					<div  class="col-sm-3  form-inline">
						<input type="radio" name="rdo"  id="r4" />累计走路：
						<input class="form-control" type="number" id="v4"  style="width: 100px;"/>步
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 " >勋章描述：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="note" name="note" style="width: 50%;height: 30%;" ></textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 500px;">
			    <button id="sumit" class="btn btn-primary" style="width: 150px;">添加</button>
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>
		</form>
	</div>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/medal/addmedal.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        
		$(function(){
			
			var result = '${result}';	
			if(result!=''){
				swal(result);
			} 
			 
		
		});
		
		function xzType(type){
			if(type==1){
				$("#rc").show();
			}
			if(type!=1){
				$("#rc").hide();
			}
		}
		
	 
		
	 
	 
    	
    </script>
</body>
</html>
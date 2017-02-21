<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>更新邀请注册</title>
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
	</style>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" action="${ctx}/activity/updateInviteFriend" method="post">
		
			<!-- 隐藏表单 -->
			<input type="hidden" name="id"  value="${activity.id}"/>
			<input type="hidden" name="couponsList[0].id" value="${activity.couponsList[0].id}" />
			<input type="hidden" name="couponsList[1].id" value="${activity.couponsList[1].id}" />
			
			<input type="hidden" name="inviteFriendActivity.id" value="${activity.inviteFriendActivity.id}" />
		
			<jsp:useBean id="myDate" class="java.util.Date" />
			
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动类型：</label>
				<label class="col-sm-0 control-label" >邀请注册</label>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >封面：</label>
				<div class="col-md-2">
					    <div class="col-sm-0 btn-upload logo-upload">
							<input id="logoupload" type="file" />
						</div>
						<div id="logoimage" style="display: none;"></div>
						<div class="form-group" id="logo_image" style="display: none"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >开始时间：</label>
				<div  class="col-sm-3  form-inline">
					<input class="form-control" id="bTime" type="datetime-local" />
					<c:set target="${myDate}" property="time" value="${activity.beginTime}" />
					<input type="hidden" id="beginTime" name="beginTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${myDate}" />"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >结束时间：</label>
				<div  class="col-sm-3  form-inline">
					<input class="form-control" id="eTime" type="datetime-local" />
					<c:set target="${myDate}" property="time" value="${activity.endTime}" />
					<input type="hidden" id="endTime" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${myDate}" />"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >邀请奖励：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="yqjl" name="couponsList[0].bonus" value="${activity.couponsList[0].bonus}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >注册奖励：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="zcjl" name="couponsList[1].bonus" value="${activity.couponsList[1].bonus}" /> 
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label">有效期：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="yxSelect" name="couponsList[0].expiryDay">
					</select>
					<input type="hidden"  id="expiryDay" name="couponsList[1].expiryDay" value="" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label" >消息推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="inviteFriendActivity.message" style="width: 100%;" value="${activity.inviteFriendActivity.message}" />
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" value="${activity.inviteFriendActivity.cronExpression}" />
					<input type="hidden" id="cronExpression" name="inviteFriendActivity.cronExpression" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="inviteFriendActivity.rule" style="width: 100%;height: 50%;" >${activity.inviteFriendActivity.rule}</textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 500px;">
			    <button id="sumit" class="btn btn-primary" style="width: 150px;">更新活动</button>
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>
		</form>
	</div>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/invitefriendactivity/updateinvitefriendactivity.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script src="${ctx }/resources/js/path.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        
		$(function(){
			 
			var imgUrl = image_url+'${activity.cover}';
		 	var html='<div class="imgbox_logo" data-src="${activity.cover}" style="width:170px;z-index:1;background-image: url('+imgUrl+');"><img src="'+url+'resources/images/del.png" width="20px" height="20px;" /></div>';
	  		$("#logoimage").html(html);
	  		$('.logo-upload').hide();
      		$("#logoimage").show();
			 
			
			var result = '${result}';	
			if(result!=''){
				swal(result);
			} 
			
			initData();
		
		});
		
		//上传封面照片
		function uploadLogImg() {
			$('#logoupload').click();
		}
		
		
		//初始化数据
		function initData(){
			
			$("#bTime").val($("#beginTime").val().replace(" ","T"));
			$("#eTime").val($("#endTime").val().replace(" ","T"));
			
			var ts = "${activity.couponsList[0].expiryDay}";
			
			for (var i = 1; i <=100; i++) {
				if(i==ts){
					$("#yxSelect").append("<option value='"+i+"' selected='selected'>"+i+"天</option>");
				}else{
					$("#yxSelect").append("<option value='"+i+"' >"+i+"天</option>");
				}
			}
			
		}
	 
    	
    </script>
</body>
</html>
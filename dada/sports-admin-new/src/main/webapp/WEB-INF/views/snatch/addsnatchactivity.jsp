<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增一元夺宝</title>
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
		<form class="form-horizontal" action="${ctx}/activity/addSnatchActivity" method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动类型：</label>
				<label class="col-sm-0 control-label" >一元夺宝</label>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动名称：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdmc" name="title"  style="width: 30%;" />
				</div>
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
					<input type="hidden" id="beginTime" name="beginTime" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >结束时间：</label>
				<div  class="col-sm-3  form-inline">
					<input class="form-control" id="eTime" type="datetime-local" />
					<input type="hidden" id="endTime" name="endTime" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label" >初始奖金池：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="csjjc" name="snatchActivity.initialBonus"  value="3000" placeholder="元体验金" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >最少参与人：</label>
				<div class="col-sm-1 form-inline">
				     <input class="form-control"  type="number" id="zscyr" name="snatchActivity.minNum" value="100" placeholder="人/次" />
				</div>
				<label class="col-sm-3 control-label" >达到之后，每多一人次，奖金池增加：</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control"  type="number" id="jjczj" name="snatchActivity.contributeBonus" value="3000" placeholder="体验金" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >参与支付金：</label>
				<div class="col-sm-3 form-inline">
				    <input class="form-control" style="width: 110px;" type="text" id="zfjy" name="snatchFeeList[0].money" />元
				    <input class="form-control" style="width: 110px;" type="number" id="zfjc" name="snatchFeeList[0].count" />次
				    <button type="button" class="btn btn-default" onclick="addNode()" >+</button>
				    <button type="button" class="btn btn-default" onclick="subNode()" >-</button>
				</div>
			</div>
			<div id="node">
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每期获奖人：</label>
				<div class="col-sm-2">
				    <input class="form-control" style="width: 170px;" type="number" id="hjr" name="snatchActivity.winNum" value="3" placeholder="数" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >跑量折算：</label>
				<div class="col-sm-1 form-inline">
				     <input class="form-control" style="width: 170px;" type="number" id="plzs" name="snatchActivity.stepToDistance" value="10000" placeholder="步" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每日最多步数：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="mrzdbs" name="snatchActivity.maxStep" value="100000" placeholder="步" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每日最多跑量：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="mrzdpl" name="snatchActivity.maxDistance" value="10" placeholder="km" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">活动勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="hdxzSelect" name="snatchActivity.activityMedal">
					</select>
				</div>
				<label class="col-sm-1 control-label">获奖勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="gjxzSelect" name="snatchActivity.winMedal">
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label">有效期：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="yxSelect" name="snatchActivity.expiryDay">
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label" >消息推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="snatchActivity.message"   style="width: 100%;" />
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" />
					<input type="hidden" id="cronExpression" name="snatchActivity.cronExpression" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="snatchActivity.rule" style="width: 100%;height: 50%;" ></textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 500px;">
			    <button id="sumit" class="btn btn-primary" style="width: 150px;">发布活动</button>
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>
		</form>
	</div>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/snatch/addsnatchactivity.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        
		$(function(){
			
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
			
			for (var i = 1; i <=100; i++) {
				if(i==7){
					$("#yxSelect").append("<option value='"+i+"' selected='selected'>"+i+"天</option>");
				}else{
					$("#yxSelect").append("<option value='"+i+"' >"+i+"天</option>");
				}
			}
			
			 $.ajax({
				   type: "GET",
				   url: "${ctx}/medal/getMedalList?category=2",
				   success: function(data){
					// console.log(data.resultMap.medallist);
					 var list = data.resultMap.medallist;
					 
					 for (var i = 0; i < list.length; i++) {
						 if(i==0){
							 $("#hdxzSelect").append("<option value='"+list[i].id+"' selected='selected'>"+list[i].name+"</option>");
							 $("#gjxzSelect").append("<option value='"+list[i].id+"' selected='selected'>"+list[i].name+"</option>");
							 $("#dzxzSelect").append("<option value='"+list[i].id+"' selected='selected'>"+list[i].name+"</option>");
						 }else{
							 $("#hdxzSelect").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
							 $("#gjxzSelect").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
							 $("#dzxzSelect").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
						 }
						
					 }
					 
				   }
			 });
			
		}
		
		var num = 0;
		function addNode(){
			num = num+1;
			var str = "<div class=\"form-group\" id='node"+num+"'>\n" +
			"					<label class=\"col-sm-1 control-label\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>\n" +
			"					<div class=\"col-sm-3 form-inline\">\n" +
			"					    <input class=\"form-control\" style=\"width: 110px;\" type=\"text\" id=\"\" name='snatchFeeList["+num+"].money'  />元\n" +
			"					    <input class=\"form-control\" style=\"width: 110px;\" type=\"number\" id=\"\" name='snatchFeeList["+num+"].count'  />次 \n" +
			"					</div>\n" +
			"				</div>";
			 $("#node").append(str);
		}
		
		function subNode(){
			$("#node"+num).remove();
			num = num-1;
			if(num<0){
				num = 0;
			}
		}
	 
    	
    </script>
</body>
</html>
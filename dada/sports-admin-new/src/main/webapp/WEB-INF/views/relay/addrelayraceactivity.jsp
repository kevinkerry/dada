<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增邀请注册</title>
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
		<form class="form-horizontal" action="${ctx}/activity/addRelayRace" method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动类型：</label>
				<label class="col-sm-0 control-label" >团队接力</label>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动名称：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdmc" name="title" maxlength="15" style="width: 30%;" />
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
				<label class="col-sm-1 control-label" >队伍数量：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="dwsl" name="relayRaceActivity.teamLimit" placeholder="个" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >接力棒数量：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="jlbsl" name="relayRaceActivity.relayBatonLimit" placeholder="个/人" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >报名费：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="bmf" name="relayRaceActivity.firstFee" placeholder="元(第一棒)" /> 
				</div>

				<div class="col-sm-2">
				    <input class="form-control" type="number" id="qtbmf" name="relayRaceActivity.otherFee" value="" placeholder="元(其他)" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >下线层级：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="xxcj" name="relayRaceActivity.levelLimit" value="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >一级邀请奖励：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="yjqyjl" name="relayRaceActivity.firstBonus" value="15000"  placeholder="体验金券(元)" /> 
				</div>
				<label class="col-sm-1 control-label" >其他邀请奖励：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="qtqyjl" name="relayRaceActivity.otherBonus" value="1000" placeholder="体验金券(元)" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">邀请奖励有效：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="yqjlSelect" name="relayRaceActivity.inviteExpiryDay">
					</select>
				</div>
				<label class="col-sm-1 control-label">冠军奖励有效：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="gjjlSelect" name="relayRaceActivity.leagueBonusExpiryDay">
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >奖金池：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="jjc" name="relayRaceActivity.contributeBonus" value="3000" placeholder="体验金券(元)/增加一人" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >步数折算：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="bszs" name="relayRaceActivity.stepToDistance" value="10000" placeholder="步折算成1km跑量" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每日最多步数：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="mrzdbs" name="relayRaceActivity.maxStep" value="100000" placeholder="步" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每日最多跑量：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="mrzdpl" name="relayRaceActivity.maxDistance" value="10" placeholder="km" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">活动勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="hdxzSelect" name="relayRaceActivity.activityMedal">
					</select>
				</div>
				<label class="col-sm-1 control-label">冠军勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="gjxzSelect" name="relayRaceActivity.leagueMedal">
					</select>
				</div>
				<label class="col-sm-1 control-label">队长勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="dzxzSelect" name="relayRaceActivity.teamLeaderMedal">
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >线路地点：</label>
				<div class="col-sm-4 form-inline">
				    <input class="form-control" style="width: 110px;" type="text" id="lxkm" name="relayLineList[0].distance" />km,到达
				    <input class="form-control" style="width: 200px;" type="text" id="lxdd" name="relayLineList[0].address" />
				    <input type="hidden" name="relayLineList[0].orders" value="1" />
				    <button type="button" class="btn btn-default" onclick="addNode()" >+</button>
				    <button type="button" class="btn btn-default" onclick="subNode()" >-</button>
				</div>
			</div>
			<div id="node">
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >邀请人次：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="yqrc" name="relayRaceActivity.pushCount" value="5" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >消息推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="relayRaceActivity.message"   style="width: 100%;" />
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" />
					<input type="hidden" id="cronExpression" name="relayRaceActivity.cronExpression" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="relayRaceActivity.rule" style="width: 100%;height: 50%;" ></textarea>
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
    <script src="${ctx }/resources/js/relay/addrelayraceactivity.js"></script>
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
				if(i==30){
					$("#yqjlSelect").append("<option value='"+i+"' selected='selected'>"+i+"天</option>");
					$("#gjjlSelect").append("<option value='"+i+"' selected='selected'>"+i+"天</option>");
				}else{
					$("#yqjlSelect").append("<option value='"+i+"' >"+i+"天</option>");
					$("#gjjlSelect").append("<option value='"+i+"' >"+i+"天</option>");
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
			var str = "<div class=\"form-group\"  id='node"+num+"'>\n" +
			"				<label class=\"col-sm-1 control-label\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>\n" +
			"				<div class=\"col-sm-4 form-inline\">\n" +
			"				    <input class=\"form-control\" style=\"width: 110px;\" type=\"text\"  name=\"relayLineList["+num+"].distance\" />km,到达\n" +
			"				    <input class=\"form-control\" style=\"width: 200px;\" type=\"text\"  name=\"relayLineList["+num+"].address\" />\n" +
			"				    <input type=\"hidden\" name=\"relayLineList["+num+"].orders\" value='"+(num+1)+"' />\n" +
			"				</div>\n" +
			"		  </div>"
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
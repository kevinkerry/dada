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
		<ul class="nav nav-tabs" id="myTab">
		  <li class="active"><a href="#activityInfo">活动详情</a></li>
		  <li><a href="#incomeInfo"  onclick="userInfo(${activity.id})" >用户详情</a></li>
		</ul>
	
		<form class="form-horizontal" action="${ctx}/activity/updateSnatchActivity" method="post">
			<!-- 隐藏表单 -->
			<input type="hidden" name="id"  value="${activity.id}"/>
			<input type="hidden" name="snatchActivity.id" value="${activity.snatchActivity.id}" />
		
			<jsp:useBean id="myDate" class="java.util.Date" />
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动类型：</label>
				<label class="col-sm-0 control-label" >一元夺宝</label>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动名称：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdmc" name="title" value="${activity.title}" style="width: 30%;" readonly="readonly" />
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
					<input class="form-control" id="bTime" type="datetime-local"  readonly="readonly"/>
					<c:set target="${myDate}" property="time" value="${activity.beginTime}" />
					<input type="hidden" id="beginTime" name="beginTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${myDate}" />"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >结束时间：</label>
				<div  class="col-sm-3  form-inline">
					<input class="form-control" id="eTime" type="datetime-local"  readonly="readonly"/>
					<c:set target="${myDate}" property="time" value="${activity.endTime}" />
					<input type="hidden" id="endTime" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${myDate}" />"/>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label" >初始奖金池：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="csjjc" name="snatchActivity.initialBonus"  value="${activity.snatchActivity.initialBonus}" placeholder="元体验金"  readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >最少参与人：</label>
				<div class="col-sm-1 form-inline">
				     <input class="form-control"  type="number" id="zscyr" name="snatchActivity.minNum" value="${activity.snatchActivity.minNum}" placeholder="人/次"  readonly="readonly"/>
				</div>
				<label class="col-sm-3 control-label" >达到之后，每多一人次，奖金池增加：</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control"  type="number" id="jjczj" name="snatchActivity.contributeBonus" value="${activity.snatchActivity.contributeBonus}" placeholder="体验金"  readonly="readonly"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >参与支付金：</label>
				<div class="col-sm-3 form-inline">
					<input type="hidden" name="snatchFeeList[0].id" value="${activity.snatchFeeList[0].id}" />
				    <input class="form-control" style="width: 110px;" type="text" id="zfjy" name="snatchFeeList[0].money" value="${activity.snatchFeeList[0].money}"  readonly="readonly"/>元
				    <input class="form-control" style="width: 110px;" type="number" id="zfjc" name="snatchFeeList[0].count" value="${activity.snatchFeeList[0].count}"  readonly="readonly"/>次
				</div>
			</div>
			<div id="node">
				<c:forEach items="${activity.snatchFeeList}" var="a" varStatus="status">
					<c:if test="${status.index>0}">
						<div class="form-group" id="node${status.index}">
							<label class="col-sm-1 control-label" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<div class="col-sm-3 form-inline">
								<input type="hidden" name="snatchFeeList[${status.index}].id" value="${activity.snatchFeeList[(status.index)].id}" />
								<input class="form-control" style="width: 110px;" type="text" id="zfjy" name="snatchFeeList[${(status.index)}].money" value="${a.money}" readonly="readonly" />元
							    <input class="form-control" style="width: 110px;" type="number" id="zfjc" name="snatchFeeList[${(status.index)}].count" value="${a.count}" readonly="readonly" />次
						    </div>
					    </div>
				    </c:if>
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每期获奖人：</label>
				<div class="col-sm-2">
				    <input class="form-control" style="width: 170px;" type="number" id="hjr" name="snatchActivity.winNum" value="${activity.snatchActivity.winNum}" placeholder="数" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >跑量折算：</label>
				<div class="col-sm-1 form-inline">
				     <input class="form-control" style="width: 170px;" type="number" id="plzs" name="snatchActivity.stepToDistance" value="${activity.snatchActivity.stepToDistance}" placeholder="步" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每日最多步数：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="mrzdbs" name="snatchActivity.maxStep" value="${activity.snatchActivity.maxStep}" placeholder="步" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >每日最多跑量：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="mrzdpl" name="snatchActivity.maxDistance" value="${activity.snatchActivity.maxDistance}" placeholder="km" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">活动勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="hdxzSelect" name="snatchActivity.activityMedal" disabled="disabled">
					</select>
				</div>
				<label class="col-sm-1 control-label">获奖勋章：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="gjxzSelect" name="snatchActivity.winMedal" disabled="disabled">
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label">有效期：</label>
				<div class="col-sm-2 form-inline">
					 <select class="form-control" id="yxSelect" name="snatchActivity.expiryDay" disabled="disabled">
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label" >消息推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="snatchActivity.message"  value="${activity.snatchActivity.message}" style="width: 100%;" readonly="readonly"/>
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" value="${activity.snatchActivity.cronExpression}" readonly="readonly"/>
					<input type="hidden" id="cronExpression" name="snatchActivity.cronExpression" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="snatchActivity.rule" style="width: 100%;height: 50%;" readonly="readonly">${activity.snatchActivity.rule}</textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 500px;">
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>
		</form>
	</div>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/snatch/updatesnatchactivity.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        
        var num = 0;
        
		$(function(){
			num = '${activity.snatchFeeList.size()}';
			num = parseInt(num)-1;
			
			var imgUrl = image_url+'${activity.cover}';
		 	var html='<div class="imgbox_logo" data-src="${activity.cover}" style="width:170px;z-index:1;background-image: url('+imgUrl+');"></div>';
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
			
			
			var yqjlts = "${activity.snatchActivity.expiryDay}";
			var hdxz = "${activity.snatchActivity.activityMedal}";
			var hjxz = "${activity.snatchActivity.winMedal}";
			
			for (var i = 1; i <=100; i++) {
				if(i==yqjlts){
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
						 if(list[i].id==hdxz){
							 $("#hdxzSelect").append("<option value='"+list[i].id+"' selected='selected'>"+list[i].name+"</option>");
						 }else{
							 $("#hdxzSelect").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
						 }
						 
						 if(list[i].id==hjxz){
							 $("#gjxzSelect").append("<option value='"+list[i].id+"' selected='selected'>"+list[i].name+"</option>");
						 }else{
							 $("#gjxzSelect").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
						 }
						
					 }
					 
				   }
			 });
			
		}
		
		
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
		
		function userInfo(activityId){
			location.href ="${ctx}/usersnatch/list?activityId="+activityId;
		}
	 
    	
    </script>
</body>
</html>
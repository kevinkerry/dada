<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>规则设置</title>
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
		<form class="form-horizontal" action="${ctx}/lottery/addLottery" method="post">
			<!-- 隐藏表单 -->
			<input type="hidden" name="id"  value="${lotteryBase.id}"/>
		
			 <jsp:useBean id="myDate" class="java.util.Date" />

			<div class="form-group"></div>
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
				<label class="col-sm-1 control-label" >投注次数：</label>
				<div class="col-sm-3 form-inline">
				    <input class="form-control" style="width: 110px;" type="number" id="tzcs" name="betCountBaseList[0].count" value="${lotteryBase.betCountBaseList[0].count}" />
				    <button type="button" class="btn btn-default" onclick="addBetCount()" >+</button>
				    <button type="button" class="btn btn-default" onclick="subBetCount()" >-</button>
				</div>
			</div>
			<div   id="betcountnode">
				<c:forEach items="${lotteryBase.betCountBaseList}" var="a" varStatus="status">
					<c:if test="${status.index>0}">
						<div class="form-group" id="betcountnode${status.index}">
							<label class="col-sm-1 control-label" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<div class="col-sm-3 form-inline">
							    <input class="form-control" style="width: 110px;" type="number" name="betCountBaseList[${(status.index)}].count" value="${a.count}" />
						    </div>
					    </div>
				    </c:if>
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >投注金额：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="tzje" name="goldBean"  value="${lotteryBase.goldBean}" placeholder="金豆/注" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >奖金设置：</label>
			</div>
			 <div class="form-group">
				<label class="col-sm-1 control-label" >一等奖</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control" type="number" id="jj1" name="lotteryWinBaseList[0].goldBean"  value="${lotteryBase.lotteryWinBaseList[0].goldBean}" placeholder="金豆/注" />
				    <input type="hidden" name="lotteryWinBaseList[0].level"  value="1"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >二等奖</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control" type="number" id="jj2" name="lotteryWinBaseList[1].goldBean"  value="${lotteryBase.lotteryWinBaseList[1].goldBean}" placeholder="金豆/注" />
				     <input type="hidden" name="lotteryWinBaseList[1].level"  value="2"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >三等奖</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control" type="number" id="jj3" name="lotteryWinBaseList[2].goldBean"  value="${lotteryBase.lotteryWinBaseList[2].goldBean}" placeholder="金豆/注" />
				     <input type="hidden" name="lotteryWinBaseList[2].level"  value="3"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >四等奖</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control" type="number" id="jj4" name="lotteryWinBaseList[3].goldBean"  value="${lotteryBase.lotteryWinBaseList[3].goldBean}" placeholder="金豆/注" />
				     <input type="hidden" name="lotteryWinBaseList[3].level"  value="4"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >五等奖</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control" type="number" id="jj5" name="lotteryWinBaseList[4].goldBean"  value="${lotteryBase.lotteryWinBaseList[4].goldBean}" placeholder="金豆/注" />
				     <input type="hidden" name="lotteryWinBaseList[4].level"  value="5"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >领奖期限：</label>
				<div class="col-sm-1 form-inline">
				     <input class="form-control"  type="number" id="ljqx" name="getExpiryDay" value="${lotteryBase.getExpiryDay}" placeholder="开奖后/天" />
				</div>
				<label class="col-sm-3 control-label" >奖券有效期：</label>
				<div class="col-sm-2 form-inline">
				    <input class="form-control"  type="number" id="jqyxq" name="bonusExpiryDay" value="${lotteryBase.bonusExpiryDay}" placeholder="领奖后/天" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >投注时间：</label>
				<div  class="col-sm-3  form-inline">
					<input class="form-control" id="bTime" type="time" />
					<c:if test="${lotteryBase.beginTime!=null}">
						<c:set target="${myDate}" property="time" value="${lotteryBase.beginTime}" />
					</c:if>
					<c:if test="${lotteryBase.beginTime==null}">
						<c:set target="${myDate}" property="time" value="" />
					</c:if>
					<input type="hidden" id="beginTime" name="beginTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${myDate}" />"/> -
					<input class="form-control" id="eTime" type="time" />
					
					<c:if test="${lotteryBase.endTime!=null}">
						<c:set target="${myDate}" property="time" value="${lotteryBase.endTime}" />
					</c:if>
					<c:if test="${lotteryBase.endTime==null}">
						<c:set target="${myDate}" property="time" value="" />
					</c:if>
					<input type="hidden" id="endTime" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${myDate}" />"/>
				</div>
			</div>
			  <div class="form-group">
				<label class="col-sm-1 control-label" >延期开奖：</label>
				<div class="col-sm-5 form-inline">
				    <input class="form-control"  type="number" id="zfjy" name="delayLotteryBaseList[0].lotteryNum" value="${lotteryBase.delayLotteryBaseList[0].lotteryNum}" />期
				     <c:if test="${lotteryBase.delayLotteryBaseList!=null&&lotteryBase.delayLotteryBaseList[0]!=null}">
				     	<c:set target="${myDate}" property="time" value="${lotteryBase.delayLotteryBaseList[0].lotteryTime}" /> 
				     </c:if>
				     <c:if test="${lotteryBase.delayLotteryBaseList==null&&lotteryBase.delayLotteryBaseList[0]==null}">
				     	<c:set target="${myDate}" property="time" value="" /> 
				     </c:if>
				    <input class="form-control"  type="date" id="zfjc" name="delayLotteryBaseList[0].lotteryTimeStr" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" />" />开奖
				    <button type="button" class="btn btn-default" onclick="addNode()" >+</button>
				    <button type="button" class="btn btn-default" onclick="subNode()" >-</button>
				</div>
			</div>  
			<div id="node">
				<c:forEach items="${lotteryBase.delayLotteryBaseList}" var="a" varStatus="status">
					<c:if test="${status.index>0}">
						<div class="form-group" id="node${status.index}">
							<label class="col-sm-1 control-label" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<div class="col-sm-5 form-inline">
								<input class="form-control"  type="number"   name="delayLotteryBaseList[${(status.index)}].lotteryNum" value="${a.lotteryNum}" />期
								<c:set target="${myDate}" property="time" value="${lotteryBase.delayLotteryBaseList[(status.index)].lotteryTime}" />
							    <input class="form-control"  type="date" name="delayLotteryBaseList[${(status.index)}].lotteryTimeStr" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" />" />开奖
						    </div>
					    </div>
				    </c:if>
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >消息推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="message"  value="${lotteryBase.message}" style="width: 100%;" />
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" value="${lotteryBase.cronExpression}"/>
					<input type="hidden" id="cronExpression" name="cronExpression" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="rule" style="width: 100%;height: 50%;" >${lotteryBase.rule}</textarea>
				</div>
			</div>  
			<div class="form-group" style="margin-left: 500px;">
			    <button id="sumit" class="btn btn-primary" style="width: 150px;">更新规则</button>
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>  
		</form>  
	</div>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/lottery/addlotterybase.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        
        var num = 0;
        
		$(function(){
			num = '${lotteryBase.delayLotteryBaseList.size()}';
			num = parseInt(num)-1;
			if(num<0){
				num = 0;
			}
			
			betcountnum = '${lotteryBase.betCountBaseList.size()}';
			betcountnum = parseInt(betcountnum)-1;
			if(betcountnum<0){
				betcountnum =0;
			}
			
			if('${lotteryBase.logo}'!=''){
				var imgUrl = image_url+'${lotteryBase.logo}';
			 	var html='<div class="imgbox_logo" data-src="${lotteryBase.logo}" style="width:170px;z-index:1;background-image: url('+imgUrl+');"><img src="'+url+'resources/images/del.png" width="20px" height="20px;" /></div>';
		  		$("#logoimage").html(html);
		  		$('.logo-upload').hide();
	      		$("#logoimage").show();
			}
			
			
			
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
			$("#bTime").val($("#beginTime").val().split(" ")[1]);
			$("#eTime").val($("#endTime").val().split(" ")[1]);
			
		}
		
		 
		function addNode(){
			num = num+1;
			var str = "<div class=\"form-group\" id='node"+num+"'>\n" +
			"					<label class=\"col-sm-1 control-label\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>\n" +
			"					<div class=\"col-sm-5 form-inline\">\n" +
			"					    <input class=\"form-control\"  type=\"number\"  name='delayLotteryBaseList["+num+"].lotteryNum'  />期\n" +
			"					    <input class=\"form-control\"  type=\"date\"  name='delayLotteryBaseList["+num+"].lotteryTimeStr'  />开奖 \n" +
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
		
		var betcountnum=0;
		function addBetCount(){
			betcountnum = betcountnum+1;
			var str = "<div class=\"form-group\" id='betcountnode"+betcountnum+"'>\n" +
			"					<label class=\"col-sm-1 control-label\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>\n" +
			"					<div class=\"col-sm-3 form-inline\">\n" +
			"					    <input class=\"form-control\" style=\"width: 110px;\" type=\"number\" id=\"\" name='betCountBaseList["+betcountnum+"].count'  />\n" +
			"					</div>\n" +
			"				</div>";
			 $("#betcountnode").append(str);
			
		}
		
		
		function subBetCount(){
			$("#betcountnode"+betcountnum).remove();
			betcountnum = betcountnum-1;
			if(betcountnum<0){
				betcountnum = 0;
			}
		}  
	 
    	
    </script>
</body>
</html>
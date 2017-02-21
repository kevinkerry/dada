<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增抱大腿</title>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" id="myTab">
		  <li><a href="#activityInfo" onclick="activityInfoPage(${activityId})">活动详情</a></li>
		  <li  class="active"><a href="#userInfo">用户详情</a></li>
		  <li><a href="#incomeInfo" onclick="incomeInfoPage(${activityId})">收支详情</a></li>
		</ul>
		<jsp:useBean id="myDate" class="java.util.Date" />
		  	<div class="form-group form-inline"></div> 
			<div class="form-group form-inline"><label>共${all}人申请，有效${valid}人，无效${invalid}人</label></div> 
			<div class="form-group form-inline"><label>1级：${hugthigh1}人     2级：${hugthigh2}人    3级：${hugthigh3}人    4级：${hugthigh4}人    5级：${hugthigh5}人    6级：${hugthigh6}人  7级：${hugthigh7}人</label></div> 
			<div class="form-group form-inline"><label>当前共抱大腿${number}次，${persons}人，累计支付佣金${commissionSum}元</label></div> 
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
					    <th>
					    	<a href="#" style="text-decoration: none" onclick="sq(this)" id="sqtag">申请时间</a>
							<input type="hidden" id="sq" value="" />
					    </th>
					    <th>
							<a href="#" style="text-decoration: none" onclick="dj(this)" id="djtag">大腿等级</a>
							<input type="hidden" id="dj" value="" />
						</th>
						<th>
							<a href="#" style="text-decoration: none" onclick="yh(this)" id="yhtag">用户ID</a>
							<input type="hidden" id="yh" value="" />
						</th>
						<th>用户昵称</th>
						<th>被抱次数</th>
						<th>获得佣金(元)</th>
						<th>大腿资格</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:forEach items="${page.result}" var="a" varStatus="status">
					<tbody>
						<tr>
						    <td>
						    	 <c:set target="${myDate}" property="time" value="${a.createTime}" /> 
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
						    </td>
						    <td>${a.type}</td>
						    <td>${a.user.id}</td>  
					 		<td>${a.user.nickname}</td>
							<td>
								<c:if test="${a.status==1}">${a.hugNum}</c:if>
								<c:if test="${a.status!=1}">0</c:if>
							</td>
							<td>
								<c:if test="${a.status==1}">${a.hugMoney}</c:if>
								<c:if test="${a.status!=1}">0</c:if>
							</td>
							<td>
								<c:if test="${a.status==1}">
								    有效
								</c:if>
 								<c:if test="${a.status!=1}">
								   无效
								</c:if>
							</td>
							<td>
								<button type="button" class="btn btn-default" onclick="openRun(${a.user.id})" >跑量</button>
								<c:if test="${a.status==1}">
									<button type="button" class="btn btn-default" onclick="toPage(${a.id})">详情</button>
								</c:if>
							</td> 
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<%@include file="/common/page.jsp"%>
			<div class="form-group" style="margin-left: 90%;">
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>
	</div>

	<!-- 跑量 开始 -->
	<div class="modal fade" id="runWindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: 400px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="max-height: 550px; overflow-y: auto">
					<div class="tab-content">
						<div class="row">
							<div class="form-group col-xs-10">跑量：<h id="pl"></h>km 用时：<h id="ys"></h>分</div>
							<div class="form-group col-xs-10">步数：<h id="bs"></h>步</div>
							<div class="form-group col-xs-10">平均步幅：<h id="bf"></h>米/步</div>
							<div class="form-group col-xs-10">平均速度：<h id="pjsd"></h>km/h 最快速度：<h id="zksd"></h>km/h</div>
							<div class="form-group col-xs-10">平均配速：<h id="pjps"></h>/km 最快配速:<h id="zkps"></h>/km</div>
						</div>
					</div>
					<div class="modal-footer">
		
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 跑量 结束 -->


	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        var currentPage = '${page.currentPage}';
		var pageSize = '${page.pageSize}';	
		var field='${page.params.field}';
		var sort='${page.params.sort}';
        var aId = '${activityId}';
        
		$(function(){
			$('#myTab a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		       })
			 
		       
		       if(field=="t.create_time"){
					 var str = "申请时间";
					if(sort=="desc"){
						$("#sqtag").text(str+"▼");
						$("#sq").val("desc");
					}
					if(sort=="asc"){
						$("#sqtag").text(str+"▲");
						$("#sq").val("asc");
					}
				 }
			
			if(field=="t.user_id"){
				 var str = "用户ID";
				if(sort=="desc"){
					$("#yhtag").text(str+"▼");
					$("#yh").val("desc");
				}
				if(sort=="asc"){
					$("#yhtag").text(str+"▲");
					$("#yh").val("asc");
				}
			 }
			
			if(field=="t.type"){
				 var str = "大腿等级";
				if(sort=="desc"){
					$("#djtag").text(str+"▼");
					$("#dj").val("desc");
				}
				if(sort=="asc"){
					$("#djtag").text(str+"▲");
					$("#dj").val("asc");
				}
			 }
		
		});
		
		
		function sq(value){
			field = "t.create_time";
			var str = "申请时间";
			var sq = $("#sq");
			if(sq.val()==""){
				value.text = str+"▼";
				sq.val("desc");
				sort = "desc";
				queryList();
				return;
			}
			if(sq.val()=="desc"){
				value.text = str+"▲";
				sq.val("asc");
				sort = "asc";
				queryList();
				return;
			}
			if($("#sq").val()=="asc"){
				value.text = str;
				sq.val("");
				sort = "";
				field ="";
				queryList();
				return;
			}
		}
		
		
		function yh(value){
			field = "t.user_id";
			var str = "用户ID";
			var yh = $("#yh");
			if(yh.val()==""){
				value.text = str+"▼";
				yh.val("desc");
				sort = "desc";
				queryList();
				return;
			}
			if(yh.val()=="desc"){
				value.text = str+"▲";
				yh.val("asc");
				sort = "asc";
				queryList();
				return;
			}
			if($("#yh").val()=="asc"){
				value.text = str;
				yh.val("");
				sort = "";
				field ="";
				queryList();
				return;
			}
		}
		
		function dj(value){
			field = "t.type";
			var str = "大腿等级";
			var dj = $("#dj");
			if(dj.val()==""){
				value.text = str+"▼";
				dj.val("desc");
				sort = "desc";
				queryList();
				return;
			}
			if(dj.val()=="desc"){
				value.text = str+"▲";
				dj.val("asc");
				sort = "asc";
				queryList();
				return;
			}
			if($("#dj").val()=="asc"){
				value.text = str;
				dj.val("");
				sort = "";
				field ="";
				queryList();
				return;
			}
		}
		
		function activityInfoPage(activityId){
				location.href ="${ctx}/activity/gotoPage?page=hugthighinfo&activityId="+activityId;
		}
		
		function incomeInfoPage(activityId){
			 var url="${ctx}/usercoupon/list?activityId="+activityId;
			 location.href = url;
		}
		
		function toPage(thighId){
			//alert(thighId);
			location.href ="${ctx}/hugthigh/list?thighId="+thighId;
		}
		
		
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/thigh/list?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+aId;
			if(!isEmpty(field)){
				url = url+"&field="+field+"&sort="+sort;
			}
			location.href = url;
		}

	   function goPage(num, size) {
		  currentPage = num;
		  pageSize = size;
		  queryList();
		}
	   
	   function goPage2(num) {
			currentPage = num;
			queryList();
		}
    	
	   function openRun(userId){
		   $('#runWindow').modal({
				keyboard: false,
				show: true
			}); 
		   
		   $.ajax({
			   type: "GET",
			   url: "${ctx}/thigh/getRunRecord?userId="+userId+"&activityId="+aId,
			   success: function(data){
			    var run = data.resultMap.run;
			     $("#pl").text(run.distance.toFixed(2));
			     $("#ys").text((run.totalTime/60).toFixed(2));
			     $("#bs").text(run.step);
			     $("#bf").text((run.distance/run.step).toFixed(4));
			     $("#pjsd").text(run.avspeed.toFixed(2));
			     $("#zksd").text(run.maxspeed.toFixed(2));
			     $("#pjps").text(run.maxspeed.toFixed(2));
			     $("#zkps").text(run.minspeed.toFixed(2));
			   }
		   });
		   
	   }
	   
    </script>
</body>
</html>
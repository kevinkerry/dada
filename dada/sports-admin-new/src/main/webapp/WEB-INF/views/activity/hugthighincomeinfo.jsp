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
		  <li><a href="#userInfo" onclick="userInfoPage(${activityId})">用户详情</a></li>
		  <li class="active"><a href="#incomeInfo">收支详情</a></li>
		</ul>
		<jsp:useBean id="myDate" class="java.util.Date" />
		  	<div class="form-group form-inline"></div> 
			<div class="form-group form-inline"><label>福利券：1级：${welfare1}张     2级：${welfare2}张   3级：${welfare3}张    4级：${welfare4}张   5级：${welfare5}张    6级：${welfare6}张  7级：${welfare7}张</label></div> 
			<div class="form-group form-inline"><label>已使用： ${used}张          未使用：    ${unused}张</label></div> 
			<div class="form-group form-inline"><label>累计支付佣金${commissionSum}元        懒虫累计结算收益：${earnings}元      平台佣金：${platformearnings}元   大腿收益：${thighearnings}元</label></div>
			<div class="form-group form-inline"><label>平台净利润 = ${platformearnings} - ${earnings}= ${profit}元</label></div>
			<div class="form-group form-inline">
				<c:if test="${page.params.type == 0}">
					<c:set var="se0" value="selected='selected'" />
				</c:if>
				<c:if test="${page.params.type == 1}">
					<c:set var="se1" value="selected='selected'" />
				</c:if>
				<c:if test="${page.params.type == 2}">
					<c:set var="se2" value="selected='selected'" />
				</c:if>
				<select class="form-control" id="selectzt" onchange="queryList()">
					<option value="0" ${se0}>全部</option>
					<option value="1" ${se1}>过期</option>
					<option value="2" ${se2}>已使用</option>
				</select>
			</div>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
					    <th>
					    	<a href="#" style="text-decoration: none" onclick="sj(this)" id="sjtag">使用日期</a>
							<input type="hidden" id="sj" value="" />
					    </th>
					    <th>
							<a href="#" style="text-decoration: none" onclick="yh(this)" id="yhtag">用户ID</a>
							<input type="hidden" id="yh" value="" />
						</th>
						<th>用户昵称</th>
					    <th>
							<a href="#" style="text-decoration: none" onclick="dj(this)" id="djtag">优惠券等级</a>
							<input type="hidden" id="dj" value="" />
						</th>
						<th>支付佣金</th>
						<th>结算收益</th>
						<th>使用状态</th>
					</tr>
				</thead>
				<c:forEach items="${page.result}" var="a" varStatus="status">
					<tbody>
						 <tr> 
							 <td>${a.id}</td>
							 <td>
								 <c:if test="${a.status == 1 || a.status == -1}">
								 	<c:if test="${a.date !=null}">
								 		 <c:set target="${myDate}" property="time" value="${a.date*1000}" /> 
										 <fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" />
								 	</c:if>
							 	 </c:if>
							 </td>
							 <td>${a.userId}</td>
							 <td>${a.nickname}</td>
							 <td>${a.coupon.type}</td>
							 <td>${a.coupon.commission}</td>
							 <td>${a.earnings}</td>
							 <td>
							 	<c:if test="${a.status == 1 || a.status == -1}">
							 		已使用
							 	</c:if>
								 <c:if test="${a.status == 0}">
							 		<c:if test="${a.expiryTime < currentTime}">
							 			过期
							 		</c:if>
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
			 
		       
		       if(field=="u.date"){
					 var str = "使用日期";
					if(sort=="desc"){
						$("#sjtag").text(str+"▼");
						$("#sj").val("desc");
					}
					if(sort=="asc"){
						$("#sjtag").text(str+"▲");
						$("#sj").val("asc");
					}
				 }
			
				if(field=="u.user_id"){
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
				
				if(field=="c.type"){
					 var str = "优惠券等级";
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
		
		
		function sj(value){
			field = "u.date";
			var str = "使用日期";
			var sj = $("#sj");
			if(sj.val()==""){
				value.text = str+"▼";
				sj.val("desc");
				sort = "desc";
				queryList();
				return;
			}
			if(sj.val()=="desc"){
				value.text = str+"▲";
				sj.val("asc");
				sort = "asc";
				queryList();
				return;
			}
			if($("#sj").val()=="asc"){
				value.text = str;
				sj.val("");
				sort = "";
				field ="";
				queryList();
				return;
			}
		}
		
		function yh(value){
			field = "u.user_id";
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
			field = "c.type";
			var str = "优惠券等级";
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
		
		
		
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/usercoupon/list?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+aId+"&type="+$("#selectzt").val();
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
		
		function userInfoPage(activityId){
		     var url="${ctx}/thigh/list?activityId="+activityId;
			 location.href = url;
		}
		function activityInfoPage(activityId){
			location.href ="${ctx}/activity/gotoPage?page=hugthighinfo&activityId="+activityId;
		}
    	
    	
    </script>
</body>
</html>
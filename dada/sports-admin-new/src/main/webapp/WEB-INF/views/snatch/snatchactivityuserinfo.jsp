<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>战队用户详情列表</title>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" id="myTab">
		  <li><a href="#activityInfo" onclick="activityInfo(${activityId})">活动详情</a></li>
		  <li class="active"><a href="#incomeInfo" >用户详情</a></li>
		</ul>
		
		<div class="form-group form-inline"></div>
		<div class="form-group form-inline"><label>共${numberPeople}人，共购买${numberBuy}次，奖金池${bonusPool}元体验金，收入0元，净利润0元</label></div> 
		<div class="form-group form-inline">
			 <input class="form-control col-md-offset-0" id="condition" name="condition" type="search" value="${page.params.condition}"
				placeholder="用户ID、昵称" />
			  <button id="search" type="button" class="btn btn-default">搜索</button>
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>参与活动时间</th>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>购买次数</th>
					<th>支付金额</th>
					<th>步数</th>
					<th>跑量(km)</th>
					<th>奖金数额</th>
					<th>收益核算</th>
					<th>使用状态</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>
							<c:set target="${myDate}" property="time" value="${u.createTime}" /> 
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
						</td>
						<td>${u.userId}</td>
						<td>${u.user.nickname}</td>
						<td>${u.count}</td>
						<td>${u.money}</td>
						<td>${u.step}</td>
						<td>${u.distance}</td>
						<td>${u.bonus}</td>
						<td>${u.earnings}</td>
						<td>
							<c:if test="${u.employ != null}">
								<c:if test="${u.employ == 1 || u.employ == -1}">
									已使用
								</c:if>
								<c:if test="${u.employ == 0}">
									未使用
								</c:if>
							</c:if>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<%@include file="/common/page.jsp"%>
	</div>

	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script type="text/javascript">
        
        var currentPage = '${page.currentPage}';
		var pageSize = '${page.pageSize}';
		var activityId = '${activityId}';
		 
		 
        
		$(function(){
			var result = '${result}';	
			if(result!=''){
				swal(result);
			}
			
			$("#search").on("click", searchs);
			
			 $('#condition').bind('keypress',function(event){
		            if(event.keyCode == "13")    
		            {
		            	searchs();
		            }
		        });
		});
		
		
		function searchs(){
			currentPage = 1;
			queryList();
		}
		
		/**
		 *查询
		 */
		function queryList() {
			var condition = $("#condition").val();
			var url="${ctx}/usersnatch/list?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+activityId;
			if(!isEmpty(condition)){
				url = url+"&condition="+condition;
			}
			
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		
		function activityInfo(aid){
			 location.href ="${ctx}/snatchactivity/gotoPage?page=snatchactivityinfo&activityId="+aid;
		}

	   
    </script>
</body>
</html>
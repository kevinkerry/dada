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
		  <li  class="active"><a href="#troopsInfo">战队详情</a></li>
		  <li><a href="#incomeInfo"  onclick="incomeInfo(${activityId})" >收支详情</a></li>
		</ul>
		<div class="form-group form-inline"></div>
		<div class="form-group form-inline">
			 <button class="btn" onclick="javascript:history.back(-1);">返回</button>
			 <button type="button" class="btn btn-default" onclick="userInfo(${activityId})" >用户详情</button>
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
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>步数创建时间</th>
					<th>步数</th>
					<th>室内跑步创建时间</th>
					<th>室内跑量</th>
					<th>室外跑步创建时间</th>
					<th>室外跑量</th>
					<th>有效运动量</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>${u.user.id}</td>
						<td>${u.user.nickname}</td>
						<td>
							<c:if test="${u.relayMemberSports.stepTime != null}">
								<c:set target="${myDate}" property="time" value="${u.relayMemberSports.stepTime}" /> 
						  		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
							</c:if>
						</td>
						<td>${u.relayMemberSports.step}</td>
						<td>
							<c:if test="${u.relayMemberSports.distanceTimeSN != null}">
								<c:set target="${myDate}" property="time" value="${u.relayMemberSports.distanceTimeSN}" /> 
						  		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
							</c:if>
						
						</td>
						<td>${u.relayMemberSports.distanceSN}</td>
						<td>
							<c:if test="${u.relayMemberSports.distanceTimeSW != null}">
								<c:set target="${myDate}" property="time" value="${u.relayMemberSports.distanceTimeSW}" /> 
						  		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
							</c:if>
						</td>
						<td>${u.relayMemberSports.distanceSW}</td>
						<td>${u.relayMemberSports.distanceSum}</td>
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
		var teamId = '${teamId}';
		 
        
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
			var url="${ctx}/relaymember/relayTeamSportslist?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+activityId+"&teamId="+teamId;
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
		
		function incomeInfo(activityId){
    		location.href ="${ctx}/relaymember/list?activityId="+activityId;
    	}
		
		function activityInfo(aid){
			 location.href ="${ctx}/relayraceactivity/gotoPage?page=relayraceactivityinfo&activityId="+aid;
		}

		function userInfo(activityId){
			location.href ="${ctx}/relaymember/relayTeamMemberlist?activityId="+activityId+"&teamId="+teamId;
		}
	   
    </script>
</body>
</html>
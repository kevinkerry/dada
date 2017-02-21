<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>邀请用户详情</title>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" id="myTab">
		  <li><a href="#activityInfo" onclick="activityInfoPage(${activityId})">活动详情</a></li>
		  <li  class="active"><a href="#userInfo">用户详情</a></li>
		</ul>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<div class="form-group form-inline">
			<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
		</div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>用户ID</th>
					<th>注册时间</th>
					<th>用户昵称</th>
					<th>获得奖励</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>
							<c:if test="${u.createTime != null}">
								<c:set target="${myDate}" property="time" value="${u.createTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" />
							</c:if>
						</td>
						<td>${u.nickname}</td>
						<td>${amount}元体验金</td>
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
		var userCode = '${userCode}';	
		var aId = '${activityId}';
        
		$(function(){
		 
			 
		});
		
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/invitefriendactivity/getUserInviteDetail?currentPage="+currentPage+"&pageSize="+pageSize+"&userCode="+userCode+"&activityId="+aId;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		
		function activityInfoPage(){
			location.href ="${ctx}/invitefriendactivity/gotoPage?page=inviteactivityinfo&activityId="+aId;
		}
	   
    </script>
</body>
</html>
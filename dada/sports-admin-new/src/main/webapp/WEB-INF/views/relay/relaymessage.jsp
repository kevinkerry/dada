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
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>发言时间</th>
					<th>发言内容</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>${u.user.id}</td>
						<td>${u.user.nickname}</td>
						<td>
							<c:set target="${myDate}" property="time" value="${u.createTime}" /> 
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
						</td>
						<td>${u.message}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<%@include file="/common/page.jsp"%>
	</div>

	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script type="text/javascript">
		var activityId = '${activityId}';
		var teamId = '${teamId}';
		var currentPage = '${page.currentPage}';
		var pageSize = '${page.pageSize}';
        
		$(function(){
			 
		});
		 
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/relaymessage/list?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+activityId+"&teamId="+teamId;
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
	   
    </script>
</body>
</html>
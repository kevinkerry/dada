<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
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
			<div class="form-group form-inline"><label>共${yq}人发出邀请，${jr}人输入邀请码，累计发放${sum}元体验金</label></div> 
			<div class="form-group form-inline">
				<input class="form-control col-md-offset-0" id="condition" type="search" value="${page.params.condition}" placeholder="ID、昵称、邀请码" />
				<button id="search" type="button" class="btn btn-default">搜索</button>
			</div> 
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
					    <th>用户ID</th>
						<th>用户昵称</th>
						<th>最后发出邀请时间</th>
						<th>累计体验金</th>
						<th>邀请人数</th>
						<th>邀请码</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:forEach items="${page.result}" var="u" varStatus="status">
					<tbody>
						<tr>
							<td>${u.id}</td>
							<td>${u.nickname}</td>
						    <td>
						    	<c:if test="${u.updateTime != null}">
						    		 <c:set target="${myDate}" property="time" value="${u.updateTime}" /> 
									 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" /> 
						    	</c:if>
						    </td>
						    <td>${u.countExperience}</td>
							<td>${u.inviteNum}</td>
							<td>${u.usercode}</td>
							<td>
							  <button type="button" class="btn btn-default" onclick="toPage(${u.usercode})">详情</button>
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
        var aId = '${activityId}';
        
		$(function(){
			 $('#condition').bind('keypress',function(event){
		            if(event.keyCode == "13")    
		            {
		            	queryList();
		            }
		        });
		
			 $("#search").on("click", search);
		});
		
		
		
		
		function activityInfoPage(activityId){
			location.href ="${ctx}/invitefriendactivity/gotoPage?page=inviteactivityinfo&activityId="+activityId;
		}
		
 
		
		function toPage(userCode){
			location.href ="${ctx}/invitefriendactivity/getUserInviteDetail?userCode="+userCode+"&activityId="+aId;
		}
		
		
		/**
		 *查询
		 */
		function queryList() {
			var condition = $("#condition").val();
			var url="${ctx}/invitefriendactivity/getUserInvite?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+aId;
			if(!isEmpty(condition)){
				url = url+"&condition="+condition;
			}
			location.href = url;
		}
		
		function search(){
		    currentPage = 1;
			queryList();   
		}

	   function goPage(num, size) {
		  currentPage = num;
		  pageSize = size;
		  queryList();
		}
 
	   
    </script>
</body>
</html>
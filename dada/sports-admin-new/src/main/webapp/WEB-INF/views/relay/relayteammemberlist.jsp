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
			 <button type="button" class="btn btn-default" onclick="sportsInfo(${activityId},${teamId})" >运动详情</button>
			 
			 <input class="form-control col-md-offset-0" id="condition" name="condition" type="search" value="${page.params.condition}"
				placeholder="用户ID、昵称" />
			  <button id="search" type="button" class="btn btn-default">搜索</button>
			  <button type="button" class="btn btn-default" onclick="message(${teamId})" >留言板</button>
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>入队时间</th>
					<th>上线人数</th>
					<th>一级下线</th>
					<th>其他下线</th>
					<th>获激励</th>
					<th>获赞</th>
					<th>所属位置</th>
					<th>发展下线奖金</th>
					<th>冠军奖金</th>
					<th>累计收益</th>
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
						<td>${u.topThreadNum}</td>
						<td>${u.stairTapeout}</td>
						<td>${u.otherTapeout}</td>
						<td>${u.stimulate}</td>
						<td>${u.praise}</td>
						<td>${u.level}</td>
						<td>${u.countBonus}</td>
						<td>${u.bonusFirst}</td>
						<td>${u.earnings}</td>
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
			var url="${ctx}/relaymember/relayTeamMemberlist?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+activityId+"&teamId="+teamId;
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

		function sportsInfo(activityId,teamId){
			location.href ="${ctx}/relaymember/relayTeamSportslist?activityId="+activityId+"&teamId="+teamId;
		}
	   
		
		function message(teamId){
			location.href ="${ctx}/relaymessage/list?activityId="+activityId+"&teamId="+teamId;
		}
    </script>
</body>
</html>
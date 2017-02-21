<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>战队详情</title>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" id="myTab">
		  <li><a href="#activityInfo" onclick="activityInfo(${activityId})">活动详情</a></li>
		  <li  class="active"><a href="#troopsInfo">战队详情</a></li>
		  <li><a href="#incomeInfo"  onclick="incomeInfo(${activityId})" >收支详情</a></li>
		</ul>
	
	
	
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<div class="form-group form-inline"><label>共${relayTeamNum}个队伍，累计${num}人参赛</label></div> 

		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>战队名称</th>
					<th>队长ID</th>
					<th>队长昵称</th>
					<th>建队时间</th>
					<th>战队人数</th>
					<th>累计跑量</th>
					<th>是否夺冠</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.name}</td>
						<td>${u.user.id}</td>
						<td>${u.user.nickname}</td>
						<td>
							<c:if test="${u.createTime != null}">
								<c:set target="${myDate}" property="time" value="${u.createTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" />
							</c:if>
						</td>
						<td>${u.num}</td>
						<td>
							${u.sumDistance}
						</td>
						<td>
							<c:if test="${u.settle == null || u.settle == 0}">未设置</c:if>
							<c:if test="${u.settle == -1}">未中奖</c:if>
							<c:if test="${u.settle == 1}">冠军</c:if>
						</td>
						<td>
						   <button type="button" class="btn btn-default" onclick="info(${u.id})" >详情</button>
						   <c:if test="${u.settle == 0 || u.settle ==null }">
						  	 <button type="button" class="btn btn-default" onclick="clearing(${u.id},${u.activityId})" >设为冠军</button>
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
		var activityId ='${activityId}';
		 
        
		$(function(){
			var result = '${result}';	
			if(result!=''){
				swal(result);
			}
			 
		});
		
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/relayteam/list?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+activityId;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		function activityInfo(aid){
			 location.href ="${ctx}/relayraceactivity/gotoPage?page=relayraceactivityinfo&activityId="+aid;
		}
		
		function clearing(id,activityId){
			var url="${ctx}/relayteam/clearing?id="+id+"&activityId="+activityId;
			swal({
				title : "冠军确认",
				text : "确定设为冠军吗？",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#337ab7",
				confirmButtonText : "确认",
				cancelButtonText : "取消",
				closeOnConfirm : false
			}, function(isConfirm) {
				if(isConfirm){
					location.href = url;
				}
			});
		}
	 
		function incomeInfo(activityId){
    		location.href ="${ctx}/relaymember/list?activityId="+activityId;
    	}
		
		function info(teamId){
			location.href ="${ctx}/relaymember/relayTeamMemberlist?activityId="+activityId+"&teamId="+teamId;
		}
	   
    </script>
</body>
</html>
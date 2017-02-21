<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>收支详情</title>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" id="myTab">
		  <li><a href="#activityInfo" onclick="activityInfo(${activityId})">活动详情</a></li>
		  <li><a href="#troopsInfo" onclick="relayTeamList(${activityId})">战队详情</a></li>
		  <li class="active"><a href="#incomeInfo">收支详情</a></li>
		</ul>
	
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<div class="form-group form-inline"><label>共有${relayTeamNum}人参赛，奖金池${bonusPool}元，发放体验金${countExperience}元，当前平台收益0.00元</label></div> 
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>所属战队</th>
					<th>累计获得下线奖金</th>
					<th>冠军奖金</th>
					<th>未使用体验金</th>
					<th>累计收益</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>${u.user.id}</td>
						<td>${u.user.nickname}</td>
						<td>${u.teamName}</td>
						<td>${u.countBonus}</td>
						<td>${u.bonusFirst}</td>
						<td>${u.unusedMoney}</td>
						<td></td>
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
			var url="${ctx}/relaymember/list?currentPage="+currentPage+"&pageSize="+pageSize+"&activityId="+activityId;
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
	 
		function relayTeamList(activityId){
    		location.href ="${ctx}/relayteam/list?activityId="+activityId;
    	}
	   
    </script>
</body>
</html>
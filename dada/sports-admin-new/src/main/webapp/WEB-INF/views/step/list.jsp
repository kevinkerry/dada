<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>步数排行</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>步数</th>
					<th>最后更新时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.userId}</td>
						<td>${u.user.nickname}</td>
						<td>${u.step}</td>
						<td>
							<c:if test="${u.updateTime != null}">
								<c:set target="${myDate}" property="time" value="${u.updateTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" />
							</c:if>
						</td>
						<td>
							<c:if test="${u.status == null || u.status == 0}">
								正常
							</c:if>
							<c:if test="${u.status == -1}">
								已冻结
							</c:if>
						</td>
						<td>
							<c:if test="${u.status != -1}">
								<button type="button" class="btn btn-default" onclick="freeze(${u.id})" >冻结利率</button>
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
			var url="${ctx}/step/list?currentPage="+currentPage+"&pageSize="+pageSize;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		function freeze(sid){
			var url="${ctx}/step/freeze?stepId="+sid;
			location.href = url;
		}
	 
	   
    </script>
</body>
</html>
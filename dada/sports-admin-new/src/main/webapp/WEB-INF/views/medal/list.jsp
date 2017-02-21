<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>勋章列表</title>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
			 <button type="button" class="btn btn-primary" onclick="add()"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button> 
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>勋章名称</th>
					<th>已点亮</th>
					<th>未点亮</th>
					<th>勋章类型</th>
					<th>关联活动</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>${u.name}</td>
						<td>
							<img alt="" src="${image}${u.logo}" style="width: 110px;height: 80px">
						</td>
						<td>
							<img alt="" src="${image}${u.notLightLogo}" style="width: 100px;height: 80px">
						</td>
						<td>
							<c:if test="${u.category == 1}">
								日常
							</c:if>
							<c:if test="${u.category == 2}">
								活动
							</c:if>
						</td>
						<td>
							${u.correlationActivity}
						</td>
						<td>
							 <button type="button" class="btn btn-default" onclick="update(${u.id})" >编辑</button>
							 <button type="button" class="btn btn-default" onclick="del(${u.id})" >删除</button>
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
			var url="${ctx}/medal/list?currentPage="+currentPage+"&pageSize="+pageSize;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		function update(id){
			var url="${ctx}/medal/gotoPage?page=updatemedal&id="+id;
			location.href = url;
		}
		
		function add(){
			var url="${ctx}/medal/gotoPage?page=addmedal";
			location.href = url;
		}
	 
		function del(id){
			var url="${ctx}/medal/delMedal?id="+id;
			swal({
				title : "删除确认",
				text : "确定删除该条勋章吗？",
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
	   
    </script>
</body>
</html>
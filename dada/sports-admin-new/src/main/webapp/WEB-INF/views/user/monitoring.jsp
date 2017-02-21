<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
</head>
<body>


	<div class="container-fluid">
		<div class="form-group form-inline">
		    <a class="btn btn-info" onclick="openWindow()">查看统计</a>
			<input class="form-control col-md-offset-0" style="width: 300px" id="clientId" type="search" value="${page.params.clientId}" placeholder="设备号进行搜索" />
			<button id="search" type="button" class="btn btn-default">搜索</button>
			<button class="btn btn-default" onclick="javascript :history.back(-1);">返回</button>
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>设备ID</th>
					<th>渠道号</th>
					<th>ID</th>
					<th>用户名</th>
					<th>昵称</th>
					<th>性别</th>
					<th>注册时间</th>
					<th>最后活跃时间</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody id="${u.id}">
					<tr>
						<td>${u.clientId}</td>
						<td>${u.channel}</td>
						<td>${u.id}</td>
						<td>${u.username}</td>
						<td>${u.nickname}</td>
						<td>
							<c:choose>
								<c:when test="${u.sex==1}">
					        		男
					        	</c:when>
								<c:when test="${u.sex==0}">
					        		女
					        	</c:when>
							</c:choose>
						</td>
						<td>
						    <c:if test="${u.registerTime != null}">
								<c:set target="${myDate}" property="time"
									value="${u.registerTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" />
							</c:if>
						</td>
						<td>
							<c:if test="${u.updateTime!=null}">
								<c:set target="${myDate}" property="time"
									value="${u.updateTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}"
									type="both" />
							</c:if>
						 </td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<%@include file="/common/page.jsp"%>
		
		<!-- 开始  -->
		<div class="modal fade" id="openWindow" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="height: 95%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">重复的设备ID</h4>
					</div>
				 <div class="modal-body" style="max-height: 90%; overflow-y: auto">  
						  <div class="tab-content">  
							<div class="row">
								<table class="table table-bordered table-hover">
										<thead>
											<tr>
											    <th>重复数量</th>
												<th>设备ID</th>
											</tr>
										</thead>
										<c:forEach items="${num}" var="c" varStatus="status">
											<tbody id="${c.clientId}">
												<tr>
													<td>${c.clientIdNum}</td>
													<td>${c.clientId}</td>
												</tr>
											</tbody>
										</c:forEach>
									</table>
							</div>
						  </div>  
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					  </div>  
				</div>
			</div>
		</div>
		<!-- 结束 -->
	</div>
	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script src="${ctx }/resources/js/utils/strUtil.js"></script>
	<script type="text/javascript">
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
		 
			var currentUserId=0;
			
			$(function(){
				$("#search").on("click", search);
				$('#clientId').bind('keypress',function(event){
			            if(event.keyCode == "13")    
			            {
			            	queryList();
			            }
			        });
				
			 	$("tbody").on("click",function(){
			 		$("#clientId").val($(this)[0].id)
			 		queryList();
				}); 
				
			});
			
			function search(){
				currentPage = 1;
				queryList();
			}
			
			/**
			 *查询
			 */
			function queryList() {
				var clientId = $("#clientId").val();
				
				var url="${ctx}/user/getClientIdList?currentPage="+currentPage+"&pageSize="+pageSize;
				
				if(!isEmpty(clientId)){
					url = url+"&clientId="+clientId;
				}
			    
				location.href = url;
			}
			

				function goPage(num, size) {
					currentPage = num;
					pageSize = size;
					queryList();
				}
				
				function goPage2(num) {
					currentPage = num;
					queryList();
				}
			
				
				function openWindow(){
					$('#openWindow').modal({
						keyboard: false,
						show: true
					});  
				}
				
				
			</script>
</body>
</html>
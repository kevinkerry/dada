<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>跑步列表</title>
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
					<th>时间</th>
					<th>步数</th>
					<th>跑步距离(km)</th>
					<th>跑步时长(分)</th>
					<th>平均速度(km/h)</th>
					<th>跑步类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.userId}</td>
						<td>${u.user.nickname}</td>
						<td><c:if test="${u.createTime != null}">
								<c:set target="${myDate}" property="time"
									value="${u.createTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" />
							</c:if></td>
						<td>${u.step}</td>
						<td><fmt:formatNumber type="number" value="${u.distance}"
								pattern="0.00" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber type="number" value="${u.totalTime/60}"
								pattern="0.00" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber type="number" value="${u.avspeed}"
								pattern="0.00" maxFractionDigits="2" /></td>
						<td><c:if test="${u.type == 1}">
								室内跑步
							</c:if> <c:if test="${u.type == 2}">
								室外跑步
							</c:if></td>
						<td><c:if test="${u.type == 1}">
								<button type="button" class="btn btn-default"
									onclick="showInfo(${u.id})">查看记录</button>
							</c:if></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<%@include file="/common/page.jsp"%>


		<!--  记录 开始 -->
		<div class="modal fade" id="recordWindow" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="height: 70%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">室内跑步记录</h4>
					</div>
					<div class="modal-body" style="max-height: 70%; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>时间</th>
												<th>速度(km/h)</th>
											</tr>
										</thead>
										<tbody id="recordBody">
										</tbody>
									</table>
									<!-- <div id="pg"></div> -->
								</div>
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
		<!--  记录 结束 -->
	</div>

	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script src="${ctx }/resources/js/utils/strUtil.js"></script>
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
			var url="${ctx}/run/list?currentPage="+currentPage+"&pageSize="+pageSize;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		function showInfo(rid){
			$('#recordWindow').modal("show");
			$("#recordBody").empty();
			  $.ajax({
				   type: "GET",
				   url: "${ctx}/runassist/getRunAssistList?runId="+rid,
				   success: function(data){
				    var result = data.resultMap.record;
				   // console.log(result);
				    var tr = "";
				    var td = "";
				    for (var x = 0; x < result.length; x++) {
				    	if(x!=0){
				    	   td = "";
				    	}
				      	td +="<td>"+timeToStr(result[x].createTime)+"</td>";
				    	td +="<td>"+result[x].avspeed.toFixed(2)+"</td>";  
				    	tr +="<tr>"+td+"</tr>";
					}
				    $("#recordBody").html(tr);
				   }
			 });
		}
	 
	   
    </script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提现列表</title>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
			<c:if test="${page.params.status == null}">
				<c:set var="s1" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == 0}">
				<c:set var="s2" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == 1}">
				<c:set var="s3" value="selected='selected'" />
			</c:if>
			<select class="form-control" id="selectStatus" style="width: 150px;" onchange="queryList()">
		          <option value="" ${s1}>--全部--</option>
		          <option value="0" ${s2}>未处理</option>
		          <option value="1" ${s3}>已处理</option>
		    </select>
		    <input class="form-control col-md-offset-0" id="condition" name="condition" type="search" value="${page.params.condition}"
				placeholder="ID、昵称、手机号进行搜索" />
			  <button id="search" type="button" class="btn btn-default">搜索</button> 
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
				    <th>时间</th>
					<th>用户ID</th>
					<th>昵称</th>
					<th>内容</th>
					<th>联系方式</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="f" varStatus="status"> 
				<tbody id="${f.id}">
					<tr>
					    <td>
						 	<c:set target="${myDate}" property="time" value="${f.createTime}" /> 
						 	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both" />
						</td>
						<td>${f.userId}</td>
						<td>${f.user.nickname}</td>
						<td>
							<a href="#" style="text-decoration: none" onclick="openContent('${f.content}')">
								<c:choose>
									<c:when test="${fn:length(f.content) > 15}">
										<c:out value="${fn:substring(f.content, 0, 15)}..." />
									</c:when>
									<c:otherwise>
										<c:out value="${f.content}" />
									</c:otherwise>
								</c:choose>
							</a>
						</td>  
						<td>${f.contact}</td>
						<td>
							<c:if test="${f.status==0}">
								未处理
							</c:if>
							<c:if test="${f.status==1}">
								已处理
							</c:if>
						</td>
						<td>
							<c:if test="${f.status==0}">
								<button type="button" class="btn btn-info" onclick="dispose(${f.id})"><i class="glyphicon glyphicon-pencil"></i>&nbsp;标记为已处理</button>
							</c:if>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>

		<div class="modal fade" id="contentWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">反馈详情</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content"><h id="contents"></h></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/common/page.jsp"%>
	</div>
	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	 <script src="${ctx }/resources/js/utils/strUtil.js"></script>
	<script type="text/javascript">
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			
			$(function(){
				$("#search").on("click", queryList);
				
				var result = '${result}';	
				if(result!=''){
					swal(result);
				}
				
				 $('#condition').bind('keypress',function(event){
			            if(event.keyCode == "13")    
			            {
			            	queryList();
			            }
			        });
			});
			
			/**
			 *查询
			 */
			function queryList() {
				var condition = $("#condition").val();
				var selectStatus = $("#selectStatus").val();
				var url="${ctx}/feedback/list?currentPage="+currentPage+"&pageSize="+pageSize;
				if(!isEmpty(condition)){
					url = url+"&condition="+condition;
				}
				if(!isEmpty(selectStatus)){
					url = url+"&status="+selectStatus;
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
				
				function dispose(fid){
					var url="${ctx}/feedback/dispose?fid="+fid;
					swal({
						title : "处理确认",
						text : "确认处理该反馈？",
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
				
				var content = "";
				
				function openContent(str){
					$('#contentWindow').modal("show");  
					
					$("#contents").text(str);
				}
				
			</script>
</body>
</html>
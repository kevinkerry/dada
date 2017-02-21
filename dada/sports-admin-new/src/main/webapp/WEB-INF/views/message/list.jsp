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
			<c:if test="${page.params.status == 1}">
				<c:set var="s2" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == 0}">
				<c:set var="s3" value="selected='selected'" />
			</c:if>
			<select class="form-control" id="selectStatus" style="width: 150px;" onchange="queryList()">
		          <option value="" ${s1}>--全部--</option>
		          <option value="1" ${s2}>已发送</option>
		          <option value="0" ${s3}>未发送</option>
		    </select>
		    <input class="form-control col-md-offset-0" id="condition" name="condition" type="search" value="${page.params.condition}"
				placeholder="关键字支持标题和内容详情进行搜索" />
			  <button id="search" type="button" class="btn btn-default">搜索</button>
			  <button type="button" class="btn btn-primary" onclick="addWindow()"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		  <table class="table table-bordered table-hover">
			<thead>
				<tr>
				    <th>建立时间</th>
					<th>发送时间</th>
					<th>标题</th>
					<th>发送人</th>
					<th>发送范围</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="m" varStatus="status">
				<tbody id="${m.id}">
					<tr>
					    <td>
					    	<c:if test="${m.createTime!=null}"> 
							 	<c:set target="${myDate}" property="time" value="${m.createTime}" /> 
							 	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both" />
						 	</c:if>
						</td>  
				 		<td>
				 			  <c:if test="${m.sendTime!=null}"> 
					 			 <c:set target="${myDate}" property="time" value="${m.sendTime}" /> 
							 	 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both" />  
				 			  </c:if>  
						</td>
						 <td>
							<a href="#" style="text-decoration: none" onclick="openContent('${m.title}','${m.content }')">
								<c:choose>
									<c:when test="${fn:length(m.title) > 15}">
										<c:out value="${fn:substring(m.title, 0, 15)}..." />
									</c:when>
									<c:otherwise>
										<c:out value="${m.title}" />
									</c:otherwise>
								</c:choose>
							</a>
						</td>
						<td>${user.nickname}</td>
						<td>
							<c:if test="${m.type==null || m.type==0}">
								全体用户
							</c:if>
							<c:if test="${m.type==1}">
								安卓用户
							</c:if>
							<c:if test="${m.type==2}">
								IOS用户
							</c:if>
						</td>
						<td>
							<c:if test="${m.status==0}">
								未发送
							</c:if>
							<c:if test="${m.status==1}">
								已发送
							</c:if>
						</td>
						<td>
							<c:if test="${m.status==0}">
								<button type="button" class="btn btn-info" onclick="send(${m.id})"><i class="glyphicon glyphicon-pencil"></i>&nbsp;立即发送</button>
							</c:if>
						    <button type="button" class="btn btn-info" onclick="openUpdate(${m.id},'${m.title}','${m.content}','${m.type}')"><i class="glyphicon glyphicon-pencil"></i>&nbsp;编辑</button>
							<button type="button" class="btn btn-danger" onclick="del(${m.id})"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button> 
						</td> 
					</tr>
				</tbody>
			</c:forEach>
		</table>   
		
        <!-- 详情 开始 -->
		<div class="modal fade" id="contentWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">推送详情</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12">
									<label>标题：</label>
									<!-- <h id="title"></h> -->
									<p class="form-control-static" id="title"></p>
								</div>
							</div>
						    <div class="row">
								<div class="form-group col-xs-12">
									<label>内容：</label>
									<!-- <h id="title"></h> -->
									<p class="form-control-static" id="contents"></p>
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
		<!-- 详情 结束 -->
		
		<!-- 更新 开始 -->
		<div class="modal fade" id="updateWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myUpdate">编辑消息</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12 form-inline">
									<div>
									  <input type="hidden" id="updatemid" value="" />
									  <input class="form-control" type="text" id="updatetitle" name="message.title" value="" />
									  <select class="form-control" id="updateSelect">
									  </select>
									</div>
								</div>
							</div>
						    <div class="row">
								<div class="form-group col-xs-12">
									<div>
									  <textarea class="form-control" type="text" id="updatecontent"  name="message.content" style="margin-top: 0px; margin-bottom: 0px; height: 144px;" ></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick="updateMessage()" >更新</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div> 
		<!-- 更新 结束 -->
		
		<!-- 新增 开始 -->
		<div class="modal fade" id="addWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myAdd">新增消息</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12 form-inline">
									  <input class="form-control" type="text" id="addtitle" />
									  	<select class="form-control" id="addSelect">
									  		<option value="0" selected="selected">全体用户</option>
									  		<option value="1">安卓用户</option>
									  		<option value="2">IOS用户</option>
										</select>
								</div>
							</div>
						    <div class="row">
								<div class="form-group col-xs-12">
									<div>
									  <textarea class="form-control" type="text" id="addcontent" style="margin-top: 0px; margin-bottom: 0px; height: 144px;" ></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick="addMessage()" >新增</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div> 
		<!-- 新增 结束 -->
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
				var url="${ctx}/message/list?currentPage="+currentPage+"&pageSize="+pageSize;
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
				
				function  send(mid){
					var url="${ctx}/message/send?mid="+mid;
					swal({
						title : "发送确认",
						text : "确定立刻发送该消息吗？",
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
				
				function del(mid){
					var url="${ctx}/message/del?mid="+mid;
					swal({
						title : "删除确认",
						text : "确定删除该条消息吗？",
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
				
				function openContent(str,content){
					$('#contentWindow').modal("show");  
					$("#title").text(str);
					$("#contents").text(content);
				}
				
				function openUpdate(mid,title,content,type){
					$("#updatemid").val(mid);
					$("#updatetitle").val(title);
					$("#updatecontent").val(content);
					var s0 = "";
					var s1 = "";
					var s2 = "";
					if(type==0){
						s0 = "selected='selected'";
					}
					if(type==1){
						s1 = "selected='selected'";					
					}
					if(type==2){
						s2 = "selected='selected'";
					}
					$("#updateSelect").append("<option value='0' "+s0+">全体用户</option>");
					$("#updateSelect").append("<option value='1' "+s1+">安卓用户</option>");
					$("#updateSelect").append("<option value='2' "+s2+">IOS用户</option>");
					$('#updateWindow').modal("show");  
				}
				
				function updateMessage(){
					if($("#updatetitle").val().length>0){
						var id = $("#updatemid").val();
						var title = $("#updatetitle").val();
						var content = $("#updatecontent").val();
						var type = $("#updateSelect").val();
						var url="${ctx}/message/update?id="+id+"&title="+title+"&content="+content+"&type="+type;
						location.href = url;
					}else{
						swal("标题不能为空");
						return;
					}
				}
				
				
				function addWindow(){
					$('#addWindow').modal("show");
				}
				
			  	function addMessage(){
					if($("#addtitle").val().length>0){
						var title = $("#addtitle").val();
						var content = $("#addcontent").val();
						var addSelect = $("#addSelect").val();
						var url="${ctx}/message/add?title="+title+"&content="+content+"&type="+addSelect;
						location.href = url;
					}else{
						swal("标题不能为空");
						return;
					}
				}  
			</script>
</body>
</html>
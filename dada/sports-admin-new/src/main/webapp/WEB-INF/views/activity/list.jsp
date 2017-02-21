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
			<%-- <c:if test="${page.params.status == null}">
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
			  <button id="search" type="button" class="btn btn-default">搜索</button>--%>
			  <button type="button" class="btn btn-primary" onclick="addWindow()"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button> 
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		  <table class="table table-bordered table-hover">
			<thead>
				<tr>
				    <th>活动ID</th>
					<th>活动名称</th>
					<th>日期</th>
					<th>活动开始时间</th>
					<th>活动结束时间</th>
					<th>活动类型</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="a" varStatus="status">
				<tbody id="${a.id}">
					<tr>
					    <td>${a.id}</td>
					    <td>${a.title}</td>  
				 		<td>
					 		 <c:set target="${myDate}" property="time" value="${a.date*1000}" /> 
							 <fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" />  
						</td>
						<td>
							<c:set target="${myDate}" property="time" value="${a.beginTime}" /> 
							<fmt:formatDate pattern="HH:mm" value="${myDate}"  />
						</td>
						<td>
						    <c:set target="${myDate}" property="time" value="${a.endTime}" /> 
							<fmt:formatDate pattern="HH:mm" value="${myDate}"  /> 
						</td>
						<td>
						    <c:if test="${a.type==1}">
								抱大腿
							</c:if>
							<c:if test="${a.type==2}">
								邀请注册
							</c:if>
							<c:if test="${a.type==3}">
								团队接力
							</c:if>
							<c:if test="${a.type==4}">
								<c:if test="${a.snatchActivity.settle==0}">
									一元夺宝(未开奖)
								</c:if>
								<c:if test="${a.snatchActivity.settle==1}">
									一元夺宝(已开奖)
								</c:if>
								<c:if test="${a.snatchActivity.settle==-1}">
									一元夺宝(退款)
								</c:if>
							</c:if>
						</td>
						<td>
								<c:if test="${currentTime>=a.beginTime&&currentTime<=a.endTime}">
								      进行中
								</c:if>
								<c:if test="${currentTime<a.beginTime}">
								     未开始
								</c:if>
								<c:if test="${currentTime>a.endTime}">
								    已结束
								</c:if>
						</td>
						<td>
							<c:if test="${a.type==4}">
								<c:if test="${a.snatchActivity.settle==0}">
									<button type="button" class="btn btn-default" onclick="lottery(${a.id})" >开奖</button>
									<button type="button" class="btn btn-default" onclick="refund(${a.id})" >退款</button>
								</c:if>
							</c:if>
							<button type="button" class="btn btn-default" onclick="showInfo(${a.id},${a.type})" >查看</button>
							<button type="button" class="btn btn-info" onclick="update(${a.id},${a.type})"><i class="glyphicon glyphicon-pencil"></i>&nbsp;编辑</button>
							<button type="button" class="btn btn-danger" onclick="del(${a.id})"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
						</td> 
					</tr>
				</tbody>
			</c:forEach>
		</table>   
		
        <!-- 详情 开始 -->
		<div class="modal fade" id="contentWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-content" style="height: 100%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">活动详情</h4>
					</div>
					<div class="modal-body" style="max-height: 90%; overflow-y: auto;">
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
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
		</div>
		<!-- 详情 结束 -->
		
		<!-- 新增 开始 -->
		<div class="modal fade" id="addWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 200px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myUpdate">选择活动类型</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-8">
									<div>
									  <select class="form-control" id="selectType" style="width: 150px;">
								          <option value="1">抱大腿</option>
								          <option value="2">邀请好友</option>
								          <option value="3">团队接力</option>
								          <option value="4">一元夺宝</option>
								      </select>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick="addActivity()" >确定</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
				//$("#search").on("click", queryList);
				
			    var result = '${result}';	
				if(result!=''){
					swal(result);
				} 
				
				/*  $('#condition').bind('keypress',function(event){
			            if(event.keyCode == "13")    
			            {
			            	queryList();
			            }
			        }); */
			});
			
			/**
			 *查询
			 */
			function queryList() {
				var url="${ctx}/activity/list?currentPage="+currentPage+"&pageSize="+pageSize;
				location.href = url;
			}

			function goPage(num, size) {
			   currentPage = num;
			   pageSize = size;
			   queryList();
			}
			
			function showInfo(id,type){
				if(type==1){
					location.href ="${ctx}/activity/gotoPage?page=hugthighinfo&activityId="+id;
				}
				if(type==2){
					location.href ="${ctx}/invitefriendactivity/gotoPage?page=inviteactivityinfo&activityId="+id;
				}
				if(type==3){
					location.href ="${ctx}/relayraceactivity/gotoPage?page=relayraceactivityinfo&activityId="+id;
				}
				
				if(type==4){
					location.href ="${ctx}/snatchactivity/gotoPage?page=snatchactivityinfo&activityId="+id;
				}
			}
			
			function addWindow(){
				$('#addWindow').modal({
					keyboard: false,
					show: true
				}); 
			}
			
			function addActivity(){
				location.href ="${ctx}/activity/addActivityPage?type="+$("#selectType").val();
			}
			
			
			function update(activityId,type){
				if(type==1){
					location.href ="${ctx}/activity/gotoPage?page=updatehugthigh&activityId="+activityId;
				}
				if(type==2){
					location.href ="${ctx}/invitefriendactivity/gotoPage?page=updateinviteactivity&activityId="+activityId;
				}
				if(type==3){
					location.href ="${ctx}/relayraceactivity/gotoPage?page=updaterelayraceactivity&activityId="+activityId;
				}
				if(type==4){
					location.href ="${ctx}/snatchactivity/gotoPage?page=updatesnatchactivity&activityId="+activityId;
				}
			}
			
			function del(activityId){
				var url ="${ctx}/activity/delActivity?activityId="+activityId;
				swal({
					title : "删除确认",
					text : "确定删除该条活动吗？",
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
			
			function lottery(activityId){
				var url = "${ctx}/activity/lottery?activityId="+activityId; 
				swal({
					title : "开奖确认",
					text : "确定开奖吗？",
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
			
			
			function refund(activityId){
				var url = "${ctx}/activity/refund?activityId="+activityId; 
				swal({
					title : "退款确认",
					text : "确定退款吗？",
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
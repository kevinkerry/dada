<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>约运动</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <form id="search_form" class="form-group form-inline" style="margin-bottom: 10px;" action="${ctx}/topic/list" method="post">
           		<button type="button" class="btn btn-info" id="update_topic"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;查看</button>
	           	<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
				<input class="form-control col-md-offset-1" id="userName" type="search" name="topicUser.userName" value="${page.params.topicUser.userName}" placeholder="话题发起人姓名" style="width: 150px;"/>
				<select id="parent_category" style="width:120px;" class="form-control" name="parentCategory">
		          <option value="">话题类型</option>
		        </select>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</form>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
		                <th></th>
		                <th>#</th>
		                <th>话题内容</th>
		                <th>发起人姓名</th>
		                <th>所属分类</th>
		                <th>所在城市</th>
		                <th>浏览量</th>
		                <th>评论数</th>
		        		<th>状态</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="topic" varStatus="status" > 
			        <tbody id="${topic.topicId}" user-name="${topic.topicUser.userName}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${topic.topicId}"/></td>
			        		<td>${topic.topicId}</td>
			        		<c:if test="${fn:length(topic.topicContent) gt 35}">
								<td width="400px;">
									<p style="margin: 0;">
										${fn:substring(topic.topicContent, 0, 35)}
										<a style="cursor: pointer;" onclick="topicMore(this);">查看更多</a>
									</p>
									<p style="display: none;margin: 0;">
										${topic.topicContent}
										<a style="cursor: pointer;" onclick="topicLess(this);">收起</a>
									</p>
								</td>
							</c:if>
							<c:if test="${fn:length(topic.topicContent) lt 35}">
								<td width="300px;">${topic.topicContent}</td>
							</c:if>
							<td>${topic.topicUser.userName}</td>
			        		<td>${topic.category.categoryName}</td>
			        		<td>${topic.city}</td>
			        		<td>${topic.viewQuantity}</td>
			        		<td><a href="#" onclick="viewComment(this, event);" title="点击查看话题评论">${topic.commentCount}</a></td>
			        		<c:choose>
			        			<c:when test="${topic.status eq 'A'}">
			        				<shiro:hasPermission name="update:topic">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">启用</button>
											  <button type="button" class="btn btn-danger" onclick="changeStatus('I', this, event);">冻结</button>
											</div>
					        			</td>
				        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:topic">
				        				<td>启用</td>
				        			</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:topic">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-defult" disabled="disabled">冻结</button>
											  <button type="button" class="btn btn-success" onclick="changeStatus('A', this, event);">启用</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:topic">
				        				<td>冻结</td>
				        			</shiro:lacksPermission>
			        			</c:otherwise>
			        		</c:choose>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
     	 </div>
     	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
     	<script src="${ctx }/resources/libs/jquery-cityselect/js/jquery.cityselect.js"></script>
		<script type="text/javascript">
			//定义全局变量
			var topicIds = [];//多选时记录话题id
			var selectedShowId = "";//单选时记录话题id
			
			$(function(){
				getCategory();
				
				//初始化查询条件
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$('#update_topic').on('click', updateShow);
				$('#selectAll').on('click', selectAll);
				
				$("#userName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#parent_category").on("change", queryList);
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedShowId) {
							$("#prev"+selectedShowId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedShowId = id;
						}else {
							selectedShowId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedShowId) {
						$("#prev"+selectedShowId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedShowId = "";
					}else {
						input.prop('checked', true);
						selectedShowId = $(this).attr('id');
					}
				});
			});
			
			function getCategory() {
				$.ajax({
				   url: "${ctx}/category/list",
				   success: function(msg){
					   var categories = msg.results.categories;
					   $.each( categories, function(i, category){
						   if(category.parentId == -1) {
							   $('#parent_category').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
						   }
						});
					   $('#parent_category').val('${page.params.parentCategory}');
				   }
				});
			}
			
			//查看评论列表
			function viewComment(dom, event) {
				var userName = $(dom).parent().parent().parent().attr('user-name');
				var topicId = $(dom).parent().parent().parent().attr('id');
				
				var url = "${ctx}/topiccomment/list?currentPage=1&topicId="+topicId+"&userName="+encodeURI(encodeURI(userName));
				location.href= url;
				event.stopPropagation();
			}
			
			//显示更多
			function topicMore(dom) {
				$(dom).parent().slideToggle("slow","linear");
				$(dom).parent().next().slideToggle("slow","linear");
			}
			
			//隐藏
			function topicLess(dom) {
				$(dom).parent().slideToggle("slow","linear");
				$(dom).parent().prev().slideToggle("slow","linear");
			}
			
			//话题全选
			function selectAll(event) {
				var target = $(event.target);
				if(target.prop('checked')) {
					$('tbody').find('input[type="checkbox"]').each(function() {
						$(this).prop('checked', true);
					});
				}else {
					$('tbody').find('input[type="checkbox"]').each(function() {
						$(this).prop('checked', false);
					});
				}
			}
			

			//启用/禁用话题
			function changeStatus(status, dom, event) {
				var topicId = $(dom).parent().parent().parent().parent().attr('id');
				if(status == 'I') {
					swal({
						title : "",
						text : "确认冻结该话题？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/topic/changeStatus?topicId=" + topicId+ "&status=" + status;
						}
					});
				}else {
					location.href = "${ctx}/topic/changeStatus?topicId=" + topicId+ "&status=" + status;
				}
				
				event.stopPropagation();
			}
			
			/**
			 *编辑话题信息
			 */
			function updateShow() {
				checkSelectShow();
				if(!isEmpty(topicIds) && topicIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedShowId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/topic/" + selectedShowId + "/update";
			}
			
			//检查选中行数
			function checkSelectShow() {
				topicIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						topicIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(topicIds) && topicIds.length == 1) {
					selectedShowId = topicIds[0];
				}
			}
			
			/**
			 *查询
			 */
			function queryList() {
				$('#search_form').submit();
			}

			function goPage(num, size) {
				$('#search_form input[name="currentPage"]').val(num);
				$('#search_form input[name="pageSize"]').val(size);
				queryList();
			}
		</script>
    </body>

</html>
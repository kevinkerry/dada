<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>获奖详情列表</title>
    </head>
    <body>
        <div class="container-fluid">
            <form id="search_form" class="form-group form-inline" style="margin-bottom: 10px;" action="${ctx}/indianadetail/list" method="post">
            	<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
           		<shiro:hasPermission name="add:indianaDetail">
            		<button type="button" class="btn btn-primary" id="add_indianaDetail"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:indianaDetail">
            		<button type="button" class="btn btn-info" id="update_indianaDetail"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:indianaDetail">
            		<button type="button" class="btn btn-danger" id="delete_indianaDetail"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
            	<input class="form-control col-md-offset-1" id="userName" name="user.userName" type="text" placeholder="获奖人账号或姓名" style="width: 150px;"/>	
            	<select class="form-control" name="status" id="status">
            		<option value="">奖品发放状态</option>
            		<option value="0">未发放</option>
            		<option value="1">已发放</option>
            	</select>
            	<input id="search" type="button" class="btn btn-default" value="搜索"/>
			</form>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
		                <th></th>
		                <th>#</th>
		                <th>宝箱名称</th>
		                <th>获奖账号</th>
		                <th>获奖人姓名</th>
		                <th>奖品名称</th>
		                <th>获奖时间</th>
		                <th>奖品发放状态</th>
		                <th>操作</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="indianaDetail" varStatus="status" > 
			        <tbody id="${indianaDetail.indianaDetailId}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${indianaDetail.indianaDetailId}"/></td>
			        		<td>${indianaDetail.indianaDetailId}</td>
			        		<td>${indianaDetail.indiana.title}</td>
							<td>${indianaDetail.user.userName}</td>
							<c:choose>
								<c:when test="${empty indianaDetail.user.member.memberName}">
									<td>${indianaDetail.user.member.memberAlias}</td>
								</c:when>
								<c:otherwise>
									<td>${indianaDetail.user.member.memberName}</td>
									<c:if test="${empty indianaDetail.user.member.memberName}">
										<td></td>
									</c:if>
								</c:otherwise>
							</c:choose>
							
			        		<td>${indianaDetail.gift.name}</td>
			        		<td><fmt:formatDate value="${indianaDetail.createTime}" pattern="yyyy-mm-dd"/></td>
			        		<c:if test="${indianaDetail.status eq 0}">
			        			<td>未发放</td>
			        		</c:if>
			        		<c:if test="${indianaDetail.status eq 1}">
			        			<td>已发放</td>
			        		</c:if>
			        		<td><button type="button" class="btn btn-success btn-sm" onclick="offerGift(this, event)">发放</button></td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
		</div>	
		<script type="text/javascript">
			//定义全局变量
			var indianaDetailIds = [];//多选时记录奖品id
			var selectedindianaDetailId = "";//单选时记录奖品id
			
			$(function(){
				//初始化查询条件
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$("#status").val('${page.params.status}');
				
				$("#userName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});
				$("#status").on("change",queryList);
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedindianaDetailId) {
							$("#prev"+selectedindianaDetailId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedindianaDetailId = id;
						}else {
							selectedindianaDetailId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedindianaDetailId) {
						$("#prev"+selectedindianaDetailId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedindianaDetailId = "";
					}else {
						input.prop('checked', true);
						selectedindianaDetailId = $(this).attr('id');
					}
				});
			});

			//奖品发放
			function offerGift(dom, event) {
				var indianaDetailId = $(dom).parent().parent().parent().attr('id');
				event.stopPropagation();
			}

			//检查选中行数
			function checkSelectShow() {
				indianaDetailIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						indianaDetailIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(indianaDetailIds) && indianaDetailIds.length == 1) {
					selectedindianaDetailId = indianaDetailIds[0];
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
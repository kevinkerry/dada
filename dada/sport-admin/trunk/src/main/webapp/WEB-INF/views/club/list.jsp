<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>俱乐部</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <form id="search_form" class="form-group form-inline" style="margin-bottom: 10px;" action="${ctx}/club/list" method="post">
            	<shiro:hasPermission name="add:club">
	           		<button type="button" class="btn btn-primary" id="add_club"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	           	</shiro:hasPermission>
            	<shiro:hasPermission name="update:club">
	           		<button type="button" class="btn btn-info" id="update_club"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
	           	</shiro:hasPermission>
	            <shiro:hasPermission name="update:club">
	           		<button type="button" class="btn btn-success" id="recommend_club"><i class="glyphicon glyphicon-send"></i>&nbsp;推荐</button>
	           	</shiro:hasPermission>
	           	<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
				<input class="form-control col-md-offset-1" id="clubName" type="search" name="clubName" value="${page.params.clubName}" placeholder="请输入俱乐部名称" style="width: 150px;"/>
				<select id="parent_category" style="width:120px;" class="form-control" name="parentCategory">
		          <option value="">俱乐部类型</option>
		        </select>
		        <select class="form-control" id="recommendFlag" style="width: 120px;" name="recommendFlag">
		          <option value="">是否推荐</option>
		          <option value="1">已推荐</option>
		          <option value="0">未推荐</option>
		        </select>
		        <select class="form-control" id="feeFlag" style="width: 120px;" name="feeFlag">
		          <option value="">是否收费</option>
		          <option value="0">收费</option>
		          <option value="1">不收费</option>
		        </select>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</form>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th><input type="checkbox" id="selectAll"/></th>
		                <th>#</th>
		                <th>名称</th>
		                <th>封面图</th>
		                <th>持有者</th>
		                <th>类型</th>
		                <th>地址</th>
		                <th>级别</th>
		                <th>活跃指数</th>
		                <th>是否收费</th>
		                <th>创建人</th>
		                <th>创建时间</th>
		                <th>成员数量</th>
		        		<th>是否推荐</th>
		        		<th>状态</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="club" varStatus="status" > 
			        <tbody id="${club.clubId}" club-name="${club.clubName}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${club.clubId}"/></td>
			        		<td>${club.clubId}</td>
							<td>${club.clubName}</td>
							<td>
			        			<a href="${image}${club.clubLogo}" data-lightbox="roadtrip">
			        				<img src="${image }${club.clubLogo}" width="32px" height="32px" style="margin-right:1px;border-radius: 5px;"/>
			        			</a>
			        		</td>
			        		<td>${club.owner.userName}</td>
			        		<td>${club.category.categoryName}</td>
			        		<td>${club.city}&nbsp;${club.district}</td>
			        		<td>${club.clubLevel}</td>
			        		<td>${club.activeIndex}</td>
			        		<c:choose>
			        			<c:when test="${club.feeFlag eq 0}">
			        				<td>收费</td>
			        			</c:when>
			        			<c:otherwise>
			        				<td>不收费</td>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${club.creator eq -1000}">
			        				<td>哒哒运动</td>
			        			</c:when>
			        			<c:otherwise>
			        				<td>${club.clubCreator.userName}</td>
			        			</c:otherwise>
			        		</c:choose>
			        		
			        		<td><fmt:formatDate value="${club.createdTime}" pattern="yyyy-MM-dd"/></td>
			        		<td><a href="#" onclick="viewMember(this, event);" title="点击查看俱乐部成员">${club.clubMemberCount}</a></td>
			        		<c:choose>
			        			<c:when test="${club.recommendFlag eq 1}">
			        				<shiro:hasPermission name="update:club">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-default" disabled="disabled">YES</button>
											  <button type="button" class="btn btn-danger" onclick="recommed(0, this, event);">NO</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:club">
				        				<td>已推荐</td>
			        				</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:club">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">NO</button>
											  <button type="button" class="btn btn-success" onclick="recommed(1, this, event);">YES</button>
											</div>
					        			</td>
					        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:club">
				        				<td>未推荐</td>
				        			</shiro:lacksPermission>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${club.status eq 'A'}">
			        				<shiro:hasPermission name="update:club">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">启用</button>
											  <button type="button" class="btn btn-danger" onclick="changeStatus('I', this, event);">冻结</button>
											</div>
					        			</td>
				        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:club">
				        				<td>启用</td>
				        			</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:club">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-defult" disabled="disabled">冻结</button>
											  <button type="button" class="btn btn-success" onclick="changeStatus('A', this, event);">启用</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:club">
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
			var clubIds = [];//多选时记录俱乐部id
			var selectedClubId = "";//单选时记录俱乐部id
			
			$(function(){
				getCategory();
				$('#recommendFlag').val('${page.params.recommendFlag}');
				$('#feeFlag').val('${page.params.feeFlag}');
				
				//初始化查询条件
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$('#add_club').on('click', addClub);
				$('#update_club').on('click', updateClub);
				$('#selectAll').on('click', selectAll);
				$('#recommend_club').on('click', batchRecommend);
				
				$("#userName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#parent_category").on("change", queryList);
				$("#recommendFlag").on("change", queryList);
				$("#feeFlag").on("change", queryList);
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						/* if(id != selectedClubId) {
							$("#prev"+selectedClubId).prop('checked', false);
						} */
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedClubId = id;
						}else {
							selectedClubId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					/* if($(this).attr('id') != selectedClubId) {
						$("#prev"+selectedClubId).prop('checked', false);
					} */
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedClubId = "";
					}else {
						input.prop('checked', true);
						selectedClubId = $(this).attr('id');
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
			
			//查看俱乐部成员列表
			function viewMember(dom, event) {
				var clubName = $(dom).parent().parent().parent().attr('club-name');
				var clubId = $(dom).parent().parent().parent().attr('id');
				
				var url = "${ctx}/clubmember/list?currentPage=1&clubId="+clubId+"&clubName="+encodeURI(encodeURI(clubName));
				location.href= url;
				event.stopPropagation();
			}
			
			//俱乐部全选
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
			
			//是否推荐
			function recommed(recommendFlag, dom, event) {
				var clubId = $(dom).parent().parent().parent().parent().attr('id');
				if(recommendFlag == 1) {
					location.href = "${ctx}/club/modify?clubId=" + clubId +"&recommendFlag=" + recommendFlag;
				}else {
					swal({
						title : "",
						text : "确认取消推荐？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/club/modify?clubId=" + clubId +"&recommendFlag=" + recommendFlag;
						}
					});
				}
				
				event.stopPropagation();
			}

			//启用/禁用俱乐部
			function changeStatus(clubStatus, dom, event) {
				var clubId = $(dom).parent().parent().parent().parent().attr('id');
				if(clubStatus == 'I') {
					swal({
						title : "",
						text : "确认冻结该俱乐部？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/club/modify?clubId=" + clubId+ "&clubStatus=" + clubStatus;
						}
					});
				}else {
					location.href = "${ctx}/club/modify?clubId=" + clubId+ "&clubStatus=" + clubStatus;
				}
				
				event.stopPropagation();
			}

			//批量推荐俱乐部
			function batchRecommend() {
				checkSelectShow();
				if(isEmpty(clubIds)) {
					swal('请选择需要推荐的俱乐部！');
					return false;
				}
				location.href = "${ctx}/club/batchRecommend?clubIds="+clubIds;
			}
			
			/**
			 *新增俱乐部
			 */
			function addClub() {
				location.href = "${ctx}/club/add";
			}
			
			/**
			 *编辑俱乐部信息
			 */
			function updateClub() {
				checkSelectShow();
				if(!isEmpty(clubIds) && clubIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedClubId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/club/" + selectedClubId + "/update";
			}
			
			//检查选中行数
			function checkSelectShow() {
				clubIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						clubIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(clubIds) && clubIds.length == 1) {
					selectedClubId = clubIds[0];
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
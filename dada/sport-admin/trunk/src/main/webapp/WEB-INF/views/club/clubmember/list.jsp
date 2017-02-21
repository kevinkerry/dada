<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>俱乐部成员列表</title>
    </head>
    <body>
        <div class="container-fluid">
			<form id="search_form" class="form-group form-inline" action="${ctx}/clubmember/list" method="post">	
				<legend>俱乐部名称：${clubName}</legend>
				<label style="float:left;font-size: 2rem;margin-top: 10px;">成员总数：${page.totalCount}</label>
				
				<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
	           	<input type="hidden" name="clubId" value="${clubId}"/>
	           	<select class="form-control col-md-1 col-md-offset-1" id="roleType" name="roleType" style="margin-right: 10px;">
		          <option value="">角色类型</option>
		          <option value="1">超级管理员</option>
		          <option value="2">管理员</option>
		          <option value="3">普通会员</option>
		        </select>
		        <select class="form-control col-md-1" id="relationFlag" name="relationFlag" style="margin-right: 10px;">
		          <option value="">审核状态</option>
		          <option value="1">未审核</option>
		          <option value="2">通过</option>
		          <option value="3">不通过</option>
		        </select>
		        <button id="search" type="button" class="btn btn-default" >搜索</button>
				<div style="float:right;margin-bottom: 15px;">
		        	<button type="button" class="btn btn-default" onclick="javascript :location.href='${ctx}/club/list'"><i class=""></i>&nbsp;返回</button>
				</div>
		   </form>
			<table cellpadding="0" cellspacing="0" class="table table-bordered">
		    	<thead>
		            <tr>
		                <th>#</th>
		                <th>编码</th>
		                <th>姓名</th>
		                <th>昵称</th>
		                <th>角色类型</th>
		                <th>活跃指数</th>
		                <th>加入时间</th>
		                <th>申请状态</th>
		                <th>成员状态</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="clubMember" varStatus="status" > 
			        <tbody id="${clubMember.clubMemberId}">
			        	<tr>
			        		<td>${clubMember.clubMemberId}</td>
			        		<td>${clubMember.clubMemberUser.userCode}</td>
			        		<td>${clubMember.clubMemberUser.userName}</td>
			        		<td>${clubMember.clubMemberUser.member.memberAlias}</td>
			        		<c:choose>
			        			<c:when test="${clubMember.roleType eq 1}">
			        				<td>超级管理员</td>
			        			</c:when>
			        			<c:when test="${clubMember.roleType eq 2}">
			        				<td>管理员</td>
			        			</c:when>
			        			<c:otherwise>
			        				<td>普通会员</td>
			        			</c:otherwise>
			        		</c:choose>
			        		<td>${clubMember.activeIndex}</td>
			        		<td><fmt:formatDate value="${clubMember.joinTime}" pattern="yyyy-MM-dd" /></td>
			        		<c:choose>
			        			<c:when test="${clubMember.relationFlag eq 1}">
			        				<td width="150px;">
			        					<shiro:hasPermission name="update:club">
			        						<div class="btn-group" role="group">
											  <button type="button" class="btn btn-success" onClick="check(2, this, event);">通过</button>
											  <button type="button" class="btn btn-warning" onclick="check(3, this, event);">不通过</button>
											</div>
			        					</shiro:hasPermission>
			        				</td>
			        			</c:when>
			        			<c:when test="${clubMember.relationFlag eq 2}">
			        				<td>通过</td>
			        			</c:when>
			        			<c:when test="${clubMember.relationFlag eq 3}">
			        				<td>不通过</td>
			        			</c:when>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${clubMember.status eq 'A'}">
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
    </body>
</html>
<script>
	$(function() {
		$('#relationFlag').val('${page.params.relationFlag}');
		$('#roleType').val('${page.params.roleType}');
		
		$("#relationFlag").on("change", queryList);
		$("#roleType").on("change", queryList);
		$("#search").on("click",queryList);
	});
	//启用/禁用俱乐部成员
	function changeStatus(memberStatus, dom, event) {
		var memberId = $(dom).parent().parent().parent().parent().attr('id');
		if(memberStatus == 'I') {
			swal({
				title : "",
				text : "确认冻结该成员？",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#337ab7",
				confirmButtonText : "确认",
				cancelButtonText : "取消",
				closeOnConfirm : true,
				closeOnCancel : true
			}, function(isConfirm) {
				if (isConfirm) {
					location.href = "${ctx}/clubmember/changeStatus?memberId=" + memberId+ "&memberStatus=" + memberStatus;
				}
			});
		}else {
			location.href = "${ctx}/clubmember/changeStatus?memberId=" + memberId+ "&memberStatus=" + memberStatus;
		}
		
		event.stopPropagation();
	}
	
	function check(result, dom, event) {
		var memberId = $(dom).parent().parent().parent().parent().attr('id');
		location.href = "${ctx}/clubmember/check?memberId=" + memberId+ "&result=" + result;
	}
	
	/**
	 *查询
	 */
	function queryList() {
		$('#search_form').submit();
	}
	
	function goPage(num, pageSize) {
		$('#search_form input[name="currentPage"]').val(num);
		$('#search_form input[name="pageSize"]').val(pageSize);
		queryList();
	}
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>约运动评论</title>
    </head>
    <body>
        <div class="container-fluid">
			<div class="form-group form-inline">	
				<legend>话题ID：${topicId}</legend>
				<label style="float:left;font-size: 2rem;margin-top: 10px;">评论总数：${page.totalCount}</label>
				<div style="float:right;margin-bottom: 15px;">
		        	<button type="button" class="btn btn-default" onclick="javascript :location.href='${ctx}/topic/list'"><i class=""></i>&nbsp;返回</button>
				</div>
		   </div>
			<table cellpadding="0" cellspacing="0" class="table table-bordered">
		    	<thead>
		            <tr>
		                <th>#</th>
		                <th>评论内容</th>
		                <th>评论人</th>
		                <th>评论时间</th>
		                <th>操作</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="comment" varStatus="status" > 
			        <tbody id="${comment.commentsId}">
			        	<tr>
			        		<td>${comment.commentsId}</td>
			        		<c:if test="${fn:length(comment.comments) gt 35}">
								<td width="400px;">
									<p>
										${fn:substring(comment.comments, 0, 35)}
										<a style="cursor: pointer;" onclick="showMore(this);">查看更多</a>
									</p>
									<p style="display: none;">
										${comment.comments}
										<a style="cursor: pointer;" onclick="showLess(this);">收起</a>
									</p>
								</td>
							</c:if>
							<c:if test="${fn:length(comment.comments) lt 35}">
								<td width="300px;">${comment.comments}</td>
							</c:if>
			        		<td>${comment.commentUser.userName}</td>
			        		<td><fmt:formatDate value="${comment.createdTime}" pattern="yyyy-MM-dd" /></td>
							<td width="50px;"><button type="button" class="btn btn-sm btn-danger" onclick="changeStatus('I', this, event)">禁用</button></td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
         </div>
    </body>
</html>
<script>
	//显示更多
	function showMore(dom) {
		$(dom).parent().slideToggle("slow","linear");
		$(dom).parent().next().slideToggle("slow","linear");
	}
	
	//隐藏
	function showLess(dom) {
		$(dom).parent().slideToggle("slow","linear");
		$(dom).parent().prev().slideToggle("slow","linear");
	}
	
	//修改评论状态
	function changeStatus(status, dom, event) {
		var commentId = $(dom).parent().parent().parent().attr('id');
		location.href = "${ctx}/topiccomment/changeStatus?commentId="+commentId+"&status="+status;
	}
	
	function goPage(num, pageSize) {
		location.href = "${ctx}/topiccomment/list?currentPage="+num+"&pageSize="+pageSize+"&showId=${showId}";
	}
</script>

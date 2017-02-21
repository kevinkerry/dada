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
		    <button class="btn" onclick="javascript :history.back(-1);">返回</button>
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		  <table class="table table-bordered table-hover">
			<thead>
				<tr>
				    <th>ID</th>
					<th>
						<a href="#" style="text-decoration: none" onclick="bb(this)" id="bbtag">被抱时间</a>
						<input type="hidden" id="bb" value="" />
					</th>
					<th>
						<a href="#" style="text-decoration: none" onclick="yh(this)" id="yhtag">用户ID</a>
						<input type="hidden" id="yh" value="" />
					</th>
					<th>用户昵称</th>
					<th>支付佣金(元)</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="a" varStatus="status">
				<tbody >
					<tr>
					    <td>${a.id}</td>
				 		<td>
							 <c:set target="${myDate}" property="time" value="${a.createTime}" />
							 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}"  />
						</td>
						<td>${a.userId}</td>
						<td>${a.user.nickname}</td>
						<td>${a.commission}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>   
		
        
		
		<%@include file="/common/page.jsp"%>
	</div>
	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	 <script src="${ctx }/resources/js/utils/strUtil.js"></script>
	<script type="text/javascript">
	
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			var field='${page.params.field}';
			var sort='${page.params.sort}';
			var thighId = '${page.params.thighId}';
			
			$(function(){
				if(field=="h.create_time"){
					 var str = "被抱时间";
					if(sort=="desc"){
						$("#bbtag").text(str+"▼");
						$("#bb").val("desc");
					}
					if(sort=="asc"){
						$("#bbtag").text(str+"▲");
						$("#bb").val("asc");
					}
				 }
				
				
				if(field=="h.user_id"){
					 var str = "用户ID";
					if(sort=="desc"){
						$("#yhtag").text(str+"▼");
						$("#yh").val("desc");
					}
					if(sort=="asc"){
						$("#yhtag").text(str+"▲");
						$("#yh").val("asc");
					}
				 }
				 
			});
			
			function bb(value){ 
				field = "h.create_time";
				var str = "被抱时间";
				var bb = $("#bb");
				if(bb.val()==""){
					value.text = str+"▼";
					bb.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(bb.val()=="desc"){
					value.text = str+"▲";
					bb.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if($("#bb").val()=="asc"){
					value.text = str;
					bb.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			
			function yh(value){
				field = "h.user_id";
				var str = "用户ID";
				var yh = $("#yh");
				if(yh.val()==""){
					value.text = str+"▼";
					yh.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(yh.val()=="desc"){
					value.text = str+"▲";
					yh.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if($("#yh").val()=="asc"){
					value.text = str;
					yh.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			/**
			 *查询
			 */
			function queryList() {
				var url="${ctx}/hugthigh/list?currentPage="+currentPage+"&pageSize="+pageSize+"&thighId="+thighId;
				if(!isEmpty(field)){
					url = url+"&field="+field+"&sort="+sort;
				}
				location.href = url;
			}

			function goPage(num, size) {
			   currentPage = num;
			   pageSize = size;
			   queryList();
			}
			
				
		 </script>
</body>
</html>
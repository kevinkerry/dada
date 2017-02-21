<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>片区排期表</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
	            <div class="form-group form-inline">
	            	<button type="button" class="btn btn-default" onclick="javascript :history.back(-1)" style="float: right;margin-bottom: 10px;"><i class=""></i>&nbsp;返回</button>
				</div>
				<table class="table table-bordered table-hover">
			    	<thead>
			            <tr>
			            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
			            	<th></th>
			                <th>#</th>
			                <th>开始时间</th>
			                <th>结束时间</th>
			                <th>市场价格</th>
			                <th>销售价格</th>
			                <th>预定状态</th>
			            </tr>
			        </thead>
			        <c:forEach items="${page.result}" var="venueSchedule" varStatus="status" > 
				        <tbody id="${venueSchedule.scheduleId}">
				        	<tr>
				        		<td width="30px;"><input type="checkbox" id="prev${venueSchedule.scheduleId}"/></td>
				        		<td>${venueSchedule.salePrice}</td>
				        		<td><fmt:formatDate value="${venueSchedule.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				        		<td><fmt:formatDate value="${venueSchedule.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				        		<td>${venueSchedule.scheduleId}</td>
				        		<td>${venueSchedule.maketingPrice}</td>
				        		<c:choose>
				        			<c:when test="${venueSchedule.bookingState eq 1}"><td>已预订</td></c:when>
				        			<c:otherwise><td>未预订</td></c:otherwise>
				        		</c:choose>
				        	</tr>
				        </tbody>
			        </c:forEach>
				</table>
				<%@include file="/common/page.jsp" %>
			</div>
          </div>
     	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
		<script type="text/javascript">
			//定义全局变量
			var venueScheduleIds = [];//多选时记录片区id
			var selectedVenueDistrictId = "";//单选时记录片区id
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			
			
			$(function(){
				//初始化查询条件
				$('#selectAll').on('click', selectAll);
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedVenueDistrictId) {
							$("#prev"+selectedVenueDistrictId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedVenueDistrictId = id;
						}else {
							selectedVenueDistrictId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedVenueDistrictId) {
						$("#prev"+selectedVenueDistrictId).prop('checked', false);
					};
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedVenueDistrictId = "";
					}else {
						input.prop('checked', true);
						selectedVenueDistrictId = $(this).attr('id');
					}
				});
			});
			
			//片区全选
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

			/**
			 *查询
			 */
			function queryList() {
				var url = "${ctx}/sportvenueschedule/list?districtId=${page.params.districtId}&currentPage="+currentPage+"&pageSize="+pageSize;

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
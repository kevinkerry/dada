<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>子场地列表</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
            <legend>场馆名称：${venueName}</legend>
                <!--/span-->
            <div class="form-group form-inline" style="margin-bottom: 10px;">
            	<shiro:hasPermission name="add:sportsVenue">
	            	<button type="button" class="btn btn-primary" id="add_childVenue"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	            </shiro:hasPermission>
            	<shiro:hasPermission name="update:sportsVenue">
            		<button type="button" class="btn btn-info" id="update_childVenue"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="delete:sportsVenue">
            		<button type="button" class="btn btn-danger" id="delete_childVenue"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
				<input class="form-control col-md-offset-1" id="childVenueName" type="search" placeholder="请输入子场地名称"/>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			  	<button type="button" class="btn btn-default" onclick="javascript :location.href='${ctx}/sportsvenues/list'" style="float: right;"><i class=""></i>&nbsp;返回</button>
			</div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
		            	<th></th>
		                <th>#</th>
		                <th>子场地名称</th>
		                <th>所属分类</th>
		                <th>联系方式</th>
		                <th>市场标准价格</th>
		                <th>促销价格</th>
		                <th>预定开始时间</th>
		                <th>预定截止时间</th>
		                <th>预定场地时长（分钟）</th>
		                <th>片区数量</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="childVenue" varStatus="status" > 
			        <tbody id="${childVenue.childVenueId}" child-venue-name="${childVenue.childVenueName}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${childVenue.childVenueId}"/></td>
			        		<td>${childVenue.childVenueId}</td>
			        		<td>${childVenue.childVenueName}</td>
			        		<td>${childVenue.category.categoryName}</td>
			        		<td>${childVenue.venueContact}</td>
			        		<td>${childVenue.maketingPrice}</td>
			        		<td>${childVenue.salePrice}</td>
			        		<td>${childVenue.bookingStartTime}</td>
			        		<td>${childVenue.bookingEndTime}</td>
			        		<td>${childVenue.bookingTimeInteral}</td>
			        		<td><a href="#" onclick="viewChildVenueDistrict(this, event);" title="查看子场地片区">${childVenue.venueChildDistrictCount}</a></td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
          </div>
     	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
		<script type="text/javascript">
			//定义全局变量
			var childVenueIds = [];//多选时记录场馆id
			var selectedChildVenueId = "";//单选时记录场馆id
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			
			
			$(function(){
				//初始化查询条件
				$('#childVenueName').val('${page.params.childVenueName}');
				$('#add_childVenue').on("click",addChildVenue);	
				$("#delete_childVenue").on("click",deleteChildVenue);	
				$('#update_childVenue').on('click', updateChildVenue);
				$('#selectAll').on('click', selectAll);
				
				$("#childVenueName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedChildVenueId) {
							$("#prev"+selectedChildVenueId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedChildVenueId = id;
						}else {
							selectedChildVenueId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedChildVenueId) {
						$("#prev"+selectedChildVenueId).prop('checked', false);
					};
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedChildVenueId = "";
					}else {
						input.prop('checked', true);
						selectedChildVenueId = $(this).attr('id');
					}
				});
			});
			
			//场馆全选
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
				var childVenueName = $('#childVenueName').val();
				var venueId = '${venueId}';
				
				var url = "${ctx}/sportsvenueschild/list?currentPage="+currentPage+"&pageSize="+pageSize;
				url = url + "&venueId=" + venueId;
				if (!isEmpty(childVenueName)) {
					childVenueName = encodeURI(encodeURI(childVenueName));
					url = url + "&childVenueName=" + childVenueName;
				}

				location.href = url;
			}

			//新增场馆信息
			function addChildVenue() {
				location.href = "${ctx}/sportsvenueschild/add?venueId=${venueId}";
			}
			
			/**
			 *编辑场馆信息
			 */
			function updateChildVenue() {
				checkSelectVenue();
				if(!isEmpty(childVenueIds) && childVenueIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedChildVenueId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/sportsvenueschild/" + selectedChildVenueId + "/update";
			}

			/**
			 *删除
			 */
			function deleteChildVenue() {
				checkSelectVenue();
				if(!isEmpty(childVenueIds) && childVenueIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedChildVenueId === '') {
					swal("请选择一条记录！");
					return;
				}
				swal({
					title : "",
					text : "确认删除该记录吗!",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#337ab7",
					confirmButtonText : "确认",
					cancelButtonText : "取消",
					closeOnConfirm : true,
					closeOnCancel : true
				}, function(isConfirm) {
					if (isConfirm) {
						location.href = "${ctx}/sportsvenueschild/" + selectedChildVenueId + "/delete";
					}
				});
			}
			
			//检查选中行数
			function checkSelectVenue() {
				childVenueIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						childVenueIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(childVenueIds) && childVenueIds.length == 1) {
					selectedChildVenueId = childVenueIds[0];
				}
			}
			
			//查看场馆片区列表
			function viewChildVenueDistrict(dom, event) {
				var childVenueName = $(dom).parent().parent().parent().attr('child-venue-name');
				var childVenueId = $(dom).parent().parent().parent().attr('id');
				
				var url = "${ctx}/sportvenuedistrict/list?currentPage=1&childVenueId="+childVenueId+"&childVenueName="+encodeURI(encodeURI(childVenueName));
				location.href= url;
				event.stopPropagation();
			}

			function goPage(num, size) {
				currentPage = num;
				pageSize = size;
				queryList();
			}
		</script>
    </body>

</html>
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
            <legend>
            	<c:if test="${not empty childVenueId}">
            		${childVenueName}
            	</c:if>
            </legend>
                <!--/span-->
            <div class="form-group form-inline" style="margin-bottom: 10px;">
           		<shiro:hasPermission name="add:sportsVenue">
	            	<button type="button" class="btn btn-primary" id="add_venueDistrict"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	            </shiro:hasPermission>
            	<shiro:hasPermission name="update:sportsVenue">
            		<button type="button" class="btn btn-info" id="update_venueDistrict"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="delete:sportsVenue">
            		<button type="button" class="btn btn-danger" id="delete_venueDistrict"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
            	<button type="button" class="btn btn-default" onclick="javascript :history.back(-1);" style="float: right;"><i class=""></i>&nbsp;返回</button>
			</div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
		            	<th></th>
		                <th>#</th>
		                <th>片区编号</th>
		                <th>限制人数</th>
		                <th>面积</th>
		                <th>灯光效果</th>
		                <th>高度</th>
		                <th>地板材质</th>
		                <th>状态</th>
		                <th>操作</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="venueDistrict" varStatus="status" > 
			        <tbody id="${venueDistrict.districtId}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${venueDistrict.districtId}"/></td>
			        		<td>${venueDistrict.districtId}</td>
			        		<td>${venueDistrict.districtCode}</td>
			        		<c:if test="${not empty venueId}">
			            		<td>${venueDistrict.sportsVenuesChild.childVenueName}</td>
			            	</c:if>
			        		<td>${venueDistrict.limitNumber}</td>
			        		<td>${venueDistrict.area}</td>
			        		<td>${venueDistrict.lights}</td>
			        		<td>${venueDistrict.height}</td>
			        		<td>${venueDistrict.material}</td>
			        		<c:choose>
			        			<c:when test="${venueDistrict.districtStatus eq 1}"><td>开放中</td></c:when>
			        			<c:otherwise><td>未开放</td></c:otherwise>
			        		</c:choose>
			        		<td width="180px;">
				        		<c:choose>
				        			<c:when test="${venueDistrict.districtStatus eq 1}">
				        				<button class="btn btn-warning" onclick="changeState(0, this, event);">关闭</button>
				        			</c:when>
				        			<c:otherwise>
				        				<button class="btn btn-success" onclick="changeState(1, this, event);">开放</button>
				        			</c:otherwise>
				        		</c:choose>
			        			<button class="btn btn-success" onclick="viewSchedule(this, event);"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;查看排期</button>
			        		</td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
          </div>
          
          <div class="modal fade" id="districtModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">新增</h4>
			      </div>
			      <div class="modal-body">
			        <form id="venue_district_form" class="form-horizontal"  action="${ctx}/sportvenuedistrict/add" method="post">
			        	<input type="hidden" name="childVenueId" value="${childVenueId}"/>
			        	<input type="hidden" name="districtId"/>
						<div class="form-group">
							<label class="col-sm-2 control-label col-sm-offset-1" >场地编号</label>
							<div class="col-sm-5">
								<input class="form-control" name="districtCode" type="text" required />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >限制人数</label>
							<div class="col-sm-5">
								<input class="form-control" name="limitNumber" required="required" placeholder="输入0代表不限制人数"/>
							</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >面积</label>
							<div class="col-sm-5">
								<input class="form-control" name="area"/>
							</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >灯光效果</label>
							<div class="col-sm-5">
								<input class="form-control" name="lights"/>
							</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >高度</label>
							<div class="col-sm-5">
								<input class="form-control" name="height"/>
							</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >地板材质</label>
							<div class="col-sm-5">
								<input class="form-control" name="material"/>
							</div>
						</div> 
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_district" class="btn btn-primary">保存</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
        	</div>
     	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
		<script type="text/javascript">
			//定义全局变量
			var venueDistrictIds = [];//多选时记录片区id
			var selectedVenueDistrictId = "";//单选时记录片区id
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			
			
			$(function(){
				//初始化查询条件
				$('#add_venueDistrict').on("click",addDistrict);	
				$("#delete_venueDistrict").on("click",deleteDistrict);	
				$('#update_venueDistrict').on('click', updateDistrict);
				$('#selectAll').on('click', selectAll);
				$('#save_district').bind('click', saveDistrict);
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
				var venueId = '${venueId}';
				var venueName= '${venueName}';
				var childVenueId = '${childVenueId}';
				
				var url = "${ctx}/sportvenuedistrict/list?currentPage="+currentPage+"&pageSize="+pageSize;
				if (!isEmpty(childVenueId)) {
					url = url + "&childVenueId=" + childVenueId;
				}

				location.href = url;
			}

			//新增片区信息
			function addDistrict() {
				$('#venue_district_form')[0].reset();
				$('#venue_district_form input[name="venueCode"]').attr('readonly', false);
				$('#districtModel').modal({
				  	keyboard: false,
					show: true
				});
			}
			
			/**
			 *编辑片区信息
			 */
			function updateDistrict() {
				checkSelectVenue();
				if(!isEmpty(venueDistrictIds) && venueDistrictIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedVenueDistrictId === '') {
					swal('请选择一条记录！');
					return;
				}
				
				$.ajax({
				   type: "GET",
				   url: "${ctx}/sportvenuedistrict/"+selectedVenueDistrictId+"/update",
				   success: function(data){
					   //console.info(data);
				   		if(data.state == 'success') {
				   			var venueDistrict = data.results.venueDistrict;
				   			$('#venue_district_form').attr('action', '${ctx}/sportvenuedistrict/update');
				   			$('#venue_district_form input[name="districtId"]').val(venueDistrict.districtId);
				   			$('#venue_district_form input[name="districtCode"]').val(venueDistrict.districtCode);
				   			$('#venue_district_form input[name="districtCode"]').attr('readonly', true);
				   			$('#venue_district_form input[name="limitNumber"]').val(venueDistrict.limitNumber);
				   			$('#venue_district_form input[name="area"]').val(venueDistrict.area);
							$('#venue_district_form input[name="lights"]').val(venueDistrict.lights);
							$('#venue_district_form input[name="height"]').val(venueDistrict.height);
							$('#venue_district_form input[name="material"]').val(venueDistrict.material);
							$('#myModalLabel').html('修改片区信息');
							$('#districtModel').modal({
							  	keyboard: false,
								show: true
							});
				   		}
				   }
				});
			}
			
			//保存信息
			function saveDistrict() {
				var districtId = $('#venue_district_form input[name="districtId"]').val();
				var districtCode = $('#venue_district_form input[name="districtCode"]').val();
				if(isEmpty(districtId)) {
					$.ajax({
					   type: "POST",
					   url: "${ctx}/sportvenuedistrict/check",
					   data: 'districtCode='+districtCode+"&childVenueId=${childVenueId}",
					   success: function(data){
					   		if(data.state == 'failed') {
					   			swal(data.results.msg);
					   			return false;
					   		}
					   		$('#venue_district_form').submit();
					   }
					});
				}else {
					$('#venue_district_form').submit();
				}
			}

			/**
			 *删除
			 */
			function deleteDistrict() {
				checkSelectVenue();
				if(!isEmpty(venueDistrictIds) && venueDistrictIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedVenueDistrictId === '') {
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
						var url = "${ctx}/sportvenuedistrict/" + selectedVenueDistrictId + "/delete?";
						var venueId = '${venueId}';
						var venueName= '${venueName}';
						if (!isEmpty(venueId)) {
							url = url + "&venueId=" + venueId;
						}
						if (!isEmpty(venueName)) {
							venueName = encodeURI(encodeURI(venueName));
							url = url + "&venueName=" + venueName;
						}
						location.href = url;
					}
				});
			}
			
			//检查选中行数
			function checkSelectVenue() {
				venueDistrictIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						venueDistrictIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(venueDistrictIds) && venueDistrictIds.length == 1) {
					selectedVenueDistrictId = venueDistrictIds[0];
				}
			}
			
			//修改片场状态
			function changeState(state, dom, event) {
				var districtId = $(dom).parent().parent().parent().attr('id');
				if(state == 0) {
					swal({
						title : "",
						text : "确认关闭该片区？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/sportvenuedistrict/modify?districtId="+districtId+"&districtStatus="+state;
						}
					});
				}else {
					location.href = "${ctx}/sportvenuedistrict/modify?districtId="+districtId+"&districtStatus="+state;
				}
				event.stopPropagation();
			}

			//查看片场排期
			function viewSchedule(dom, event) {
				var districtId = $(dom).parent().parent().parent().attr('id');
				location.href = "${ctx}/sportvenueschedule/list?districtId="+districtId;
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
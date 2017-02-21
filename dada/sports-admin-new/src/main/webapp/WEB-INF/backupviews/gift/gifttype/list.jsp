<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>奖品分类列表</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <form id="search_form" class="form-group form-inline" style="margin-bottom: 10px;" action="${ctx}/gifttype/list" method="post">
            	<shiro:hasPermission name="add:gift">
            		<button type="button" class="btn btn-primary" id="add_giftType"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:gift">
            		<button type="button" class="btn btn-info" id="update_giftType"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:gift">
            		<button type="button" class="btn btn-danger" id="delete_giftType"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
            	<select class="form-control col-md-offset-1" id="parentType"  name="parentTypeId" style="width: 120px;"></select>
            	<input id="search" type="button" class="btn btn-default" value="搜索"/>
	           	<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
			</form>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
		                <th></th>
		                <th>#</th>
		                <th>分类名称</th>
		                <th>所属父类</th>
		                <th>总数</th>
		                <th>剩余量</th>
		                <th>面值</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="giftType" varStatus="status" > 
		        	<c:if test="${empty giftType.parentTypeId }">
		        		<tbody id="${giftType.giftTypeId}">
				        	<tr>
				        		<td width="30px;"><input type="checkbox" id="prev${giftType.giftTypeId}"/></td>
				        		<td><span onclick="toggleChildType(this, event);" style="height: 15px;width: 15px;border: 1px solid #ddd;padding-left: 3px;padding-right: 3px;cursor: pointer;" title="点击查看子类型">+</span>${giftType.giftTypeId}</td>
								<td>${giftType.typeName}</td>
								<c:if test="${empty giftType.parentType.typeName}">
									<td>无</td>
								</c:if>
								<c:if test="${not empty giftType.parentType.typeName}">
									<td>${giftType.parentType.typeName}</td>
								</c:if>
				        		<c:if test="${empty giftType.limit}">
									<td>无</td>
								</c:if>
								<c:if test="${not empty giftType.limit}">
									<td>${giftType.limit}</td>
								</c:if>
								<c:if test="${empty giftType.surplus}">
									<td>无</td>
								</c:if>
								<c:if test="${not empty giftType.surplus}">
									<td>${giftType.surplus}</td>
								</c:if>
								<c:if test="${empty giftType.value}">
									<td>无</td>
								</c:if>
								<c:if test="${not empty giftType.value}">
									<td>${giftType.value}</td>
								</c:if>
				        	</tr>
				        </tbody>
				        <c:forEach items="${page.result}" var="childGiftType" varStatus="status" > 
				        	<c:if test="${childGiftType.parentTypeId eq giftType.giftTypeId}">
				        		<tbody id="${childGiftType.giftTypeId}" class="${giftType.giftTypeId}" style="display: none;">
						        	<tr class="info">
						        		<td width="30px;"><input type="checkbox" id="prev${childGiftType.giftTypeId}"/></td>
						        		<td>${childGiftType.giftTypeId}</td>
										<td>${childGiftType.typeName}</td>
										<c:if test="${empty childGiftType.parentType.typeName}">
											<td>无</td>
										</c:if>
										<c:if test="${not empty childGiftType.parentType.typeName}">
											<td>${childGiftType.parentType.typeName}</td>
										</c:if>
						        		<c:if test="${empty childGiftType.limit}">
											<td>无</td>
										</c:if>
										<c:if test="${not empty childGiftType.limit}">
											<td>${childGiftType.limit}</td>
										</c:if>
										<c:if test="${empty childGiftType.surplus}">
											<td>无</td>
										</c:if>
										<c:if test="${not empty childGiftType.surplus}">
											<td>${childGiftType.surplus}</td>
										</c:if>
										<c:if test="${empty childGiftType.value}">
											<td>无</td>
										</c:if>
										<c:if test="${not empty childGiftType.value}">
											<td>${childGiftType.value}</td>
										</c:if>
						        	</tr>
						        </tbody>
				        	</c:if>
				        </c:forEach>
		        	</c:if>
		        </c:forEach>
			</table>
			<%-- <%@include file="/common/page.jsp" %> --%>
		</div>
		<!-- 新增奖品分类 -->
		<div class="modal fade" id="gift_add_model" tabindex="-1" gift="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" gift="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增奖品分类</h4>
		      </div>
		      <div class="modal-body" style="max-height: 550px;overflow-y: auto">
		        <form id="gift_add_form" class="form-horizontal" method="post" action="${ctx}/gifttype/add">
		        	<input name="giftTypeId" type="hidden"/>
		        	<div class="form-group">
						<label class="control-label col-md-3 col-md-offset-1">是否父类：</label>
						<div class="col-sm-5">
							<label class="radio-inline">
							  <input type="radio" name="isParent" id="inlineRadio1" value="1"> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isParent" id="inlineRadio2" value="0"> 否
							</label>
						</div>
					</div>
					<div class="form-group add-child-type youyisi-hide ">
						<label class="control-label col-md-3 col-md-offset-1" >所属父类：</label>
						<div class="col-sm-5 ">
							<select class="form-control" name="parentTypeId" id="add_parentTypeId" required="required"></select>
						</div>
					</div>
					<div class="form-group add-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1">分类名：</label>
						<div class="col-sm-5">
							<input class="form-control" name="typeName" type="text" placeholder="请输入奖品分类名" required="required"/>
						</div>
					</div>
					<div class="form-group add-child-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1" >总数：</label>
						<div class="col-sm-5 ">
							<input class="form-control" name="limit" placeholder="请输入该分类奖品总数" type="number" required="required" min="0"/>
						</div>
					</div>
					<div class="form-group add-child-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1" >面值：</label>
						<div class="col-sm-5 ">
							<input class="form-control" name="value" placeholder="请输入该分类奖品面值" type="number" required="required" min="0"/>
						</div>
					</div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id="save_gift" class="btn btn-primary">保存</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>	  
		  <!-- 修改奖品分类 -->
		<div class="modal fade" id="gift_update_model" tabindex="-1" gift="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" gift="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">修改奖品分类</h4>
		      </div>
		      <div class="modal-body" style="max-height: 550px;overflow-y: auto">
		        <form id="gift_update_form" class="form-horizontal" method="post" action="${ctx}/gifttype/update">
		        	<input name="giftTypeId" type="hidden"/>
		        	<div class="form-group">
						<label class="control-label col-md-3 col-md-offset-1">是否父类：</label>
						<div class="col-sm-5">
							<label class="radio-inline">
							  <input type="radio" name="isParent" id="inlineRadio1" value="1"> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isParent" id="inlineRadio2" value="0"> 否
							</label>
						</div>
					</div>
					<div class="form-group update-child-type youyisi-hide ">
						<label class="control-label col-md-3 col-md-offset-1" >所属父类：</label>
						<div class="col-sm-5 ">
							<select class="form-control" name="parentTypeId" id="update_parentTypeId" required="required"></select>
						</div>
					</div>
					<div class="form-group update-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1">分类名：</label>
						<div class="col-sm-5">
							<input class="form-control" name="typeName" type="text" placeholder="请输入奖品分类名" required="required"/>
						</div>
					</div>
					<div class="form-group update-child-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1" >总数：</label>
						<div class="col-sm-5 ">
							<input class="form-control" name="limit" placeholder="请输入该分类奖品总数" type="number" required="required" min="0"/>
						</div>
					</div>
					<div class="form-group update-child-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1" >剩余量：</label>
						<div class="col-sm-5 ">
							<input class="form-control" name="surplus" type="number" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group update-child-type youyisi-hide">
						<label class="control-label col-md-3 col-md-offset-1" >面值：</label>
						<div class="col-sm-5 ">
							<input class="form-control" name="value" placeholder="请输入该分类奖品面值" type="number" required="required"/>
						</div>
					</div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id="modify_gift" class="btn btn-primary">保存</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>
     	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
     	<script src="${ctx }/resources/libs/jquery-cityselect/js/jquery.cityselect.js"></script>
		<script type="text/javascript">
			//定义全局变量
			var giftTypeIds = [];//多选时记录分类id
			var selectedGiftTypeId = "";//单选时记录分类id
			var giftAmount = "";//用于记录奖品总数
			var giftSpare = "";//用于记录奖品剩余量
			
			$(function(){
				getParentType();
				
				//初始化查询条件
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$('#add_giftType').on('click', addGiftType);
				$('#update_giftType').on('click', updateGiftType);
				$('#delete_giftType').on('click', deleteGiftType);
				$('#search').bind('click', queryList);
				$('#parentType').on('change', queryList);
				
				$('#save_gift').on('click', function() {
					if(checkSave()) {
						$('#gift_add_form').submit();
					}
				});
				$('#modify_gift').on('click', function() {
					if(checkUpdate()) {
						$('#gift_update_form').submit();
					}
				});
				
				$("#userName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$('#gift_update_form input[name="limit"]').on("change", function() {
					var limit = $(this).val();
					var modifyCount = giftAmount - limit;
					var surplus = giftSpare - modifyCount;
					if(surplus < 0) {
						swal('剩余量不能小于0');
						return false;
					}
					$('#gift_update_form input[name="surplus"]').val(surplus);
				});
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedGiftTypeId) {
							$("#prev"+selectedGiftTypeId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedGiftTypeId = id;
						}else {
							selectedGiftTypeId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedGiftTypeId) {
						$("#prev"+selectedGiftTypeId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedGiftTypeId = "";
					}else {
						input.prop('checked', true);
						selectedGiftTypeId = $(this).attr('id');
					}
				});
			});
			
			//查询父分类列表
			function getParentType() {
				$('#add_parentTypeId').html('');
				$('#add_parentTypeId').append('<option value="">请选择奖品父类型</option>');
				$('#update_parentTypeId').html('');
				$('#update_parentTypeId').append('<option value="">请选择奖品父类型</option>');
				$('#parentType').html('');
				$('#parentType').append('<option value="">奖品父类型</option>');
				$.ajax({
				   type: "POST",
				   url: "${ctx}/gifttype/getAll",
				   data: "isParent=true",
				   success: function(msg){
					   $.each(msg.results.typeList, function(index, type) {
						   $('#add_parentTypeId').append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
						   $('#update_parentTypeId').append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
						   $('#parentType').append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
					   });
					   $('#parentType').val('${page.params.parentTypeId}');
				   }
				});
			}
			
			//新增表单校验
			function checkSave() {
				var typeName = $('#gift_add_form input[name="typeName"]').val();
				var parentTypeId = $('#gift_add_form input[name="parentTypeId"]').val();
				var limit = $('#gift_add_form input[name="limit"]').val();
				var isParent = $('#gift_add_form input[name="isParent"]').val();
				
				if(isParent == 0) {
					if(isEmpty(parentTypeId)) {
						swal('父分类不能为空');
						return false;
					}
					if(isEmpty(typeName)) {
						swal('分类名称不能为空');
						return false;
					}
					if(isEmpty(limit)) {
						swal('该分类奖品总数不能为空');
						return false;
					}
				}else {
					if(isEmpty(typeName)) {
						swal('分类名称不能为空');
						return false;
					}
				}
				return true;
			}
			
			//编辑表单校验
			function checkUpdate() {
				var typeName = $('#gift_update_form input[name="typeName"]').val();
				var parentTypeId = $('#gift_update_form input[name="parentTypeId"]').val();
				var limit = $('#gift_update_form input[name="limit"]').val();
				var isParent = $('#gift_update_form input[name="isParent"]').val();
				
				if(isParent == 0) {
					if(isEmpty(parentTypeId)) {
						swal('父分类不能为空');
						return false;
					}
					if(isEmpty(typeName)) {
						swal('分类名称不能为空');
						return false;
					}
					if(isEmpty(limit)) {
						swal('该分类奖品总数不能为空');
						return false;
					}
				}else {
					if(isEmpty(typeName)) {
						swal('分类名称不能为空');
						return false;
					}
				}
				return true;
			}
			
			
			/**
			 *新增分类信息
			 */
			function addGiftType() {
				$('#gift_add_form')[0].reset();
				$('#gift_add_form input[name="isParent"]').attr('disabled',false);
				$('.add-type').hide();
				$('.add-child-type').hide();
				$('#gift_add_model').modal({
				  	keyboard: false,
					show: true
				});
				
				$('#gift_add_form input[name="isParent"]').on('change', function() {
					var isParent = $(this).val();
					if(isParent == 1) {
						$('#gift_add_form input[name="limit"]').val('');
						$('#gift_add_form input[name="value"]').val('');
						$('#add_parentTypeId').val('');
						$('.add-type').show();
						$('.add-child-type').slideUp();
					}else {
						$('.add-type').show();
						$('.add-child-type').slideDown();
					}
				});
			}
			
			/**
			 *编辑分类信息
			 */
			function updateGiftType() {
				checkSelectShow();
				if(!isEmpty(giftTypeIds) && giftTypeIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedGiftTypeId === '') {
					swal('请选择一条记录！');
					return;
				}
				$.ajax({
					   type: "GET",
					   url: "${ctx}/gifttype/" + selectedGiftTypeId + "/update",
					   success: function(msg){
						    var giftType = msg.results.giftType;
						    giftAmount = giftType.limit;
						    giftSpare = giftType.surplus;
						    
						    $('#gift_update_form input[name="giftTypeId"]').val(giftType.giftTypeId);
						    $('#gift_update_form input[name="typeName"]').val(giftType.typeName);
							$('#update_parentTypeId').val(giftType.parentTypeId);
							$('#gift_update_form input[name="limit"]').val(giftType.limit);
							$('#gift_update_form input[name="surplus"]').val(giftType.surplus);
							$('#gift_update_form input[name="value"]').val(giftType.value);
							$('#gift_update_form input[name="isParent"]').attr('disabled','disabled');
							if(isEmpty(giftType.parentTypeId)) {
								$('#gift_update_form input[name="isParent"]').eq(0).attr('checked', true);
								$('.update-child-type').hide();
								$('.update-type').show();
								
							}else {
								$('#gift_update_form input[name="isParent"]').eq(1).attr('checked', true);
								$('.update-type').show();
								$('.update-child-type').show();
							}
							$('#gift_update_model').modal({
							  	keyboard: false,
								show: true
							});
					   }
					});
			}
			
			/**
			 *删除分类信息
			 */
			function deleteGiftType() {
				checkSelectShow();
				if(!isEmpty(giftTypeIds) && giftTypeIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedGiftTypeId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/gifttype/" + selectedGiftTypeId + "/delete";
			}
			
			//检查选中行数
			function checkSelectShow() {
				giftTypeIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						giftTypeIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(giftTypeIds) && giftTypeIds.length == 1) {
					selectedGiftTypeId = giftTypeIds[0];
				}
			}
			
			//显示子类型
			function toggleChildType(dom, event) {
				var parentTypeId = $(dom).parent().parent().parent().attr('id');
				$('.' + parentTypeId).slideToggle();
				var html = $(dom).html();
				if(html == '+') {
					$(dom).html('-');
					$(dom).css({
						'padding-left' : '4.5px',
						'padding-right' : '4.5px'
					});
				}else {
					$(dom).html('+');
					$(dom).css({
						'padding-left' : '3px',
						'padding-right' : '3px'
					});
				}
				
				event.stopPropagation();
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
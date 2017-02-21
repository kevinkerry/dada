<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>奖品列表</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <form id="search_form" class="form-group form-inline" style="margin-bottom: 10px;" action="${ctx}/gift/list" method="post">
           		<shiro:hasPermission name="add:gift">
            		<button type="button" class="btn btn-primary" id="add_gift"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:gift">
            		<button type="button" class="btn btn-info" id="update_gift"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:gift">
            		<button type="button" class="btn btn-danger" id="delete_gift"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
	           	<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
			</form>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<!-- <th><input type="checkbox" id="selectAll"/></th> -->
		                <th></th>
		                <th>#</th>
		                <th>奖品名称</th>
		                <th>获奖率</th>
		                <th>奖品分类</th>
		                <th>奖品面值</th>
		                <th>奖品等级</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="gift" varStatus="status" > 
			        <tbody id="${gift.giftId}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${gift.giftId}"/></td>
			        		<td>${gift.giftId}</td>
							<td>${gift.name}</td>
			        		<td>${gift.probability}</td>
			        		<td>${gift.type.typeName}</td>
			        		<td>${gift.type.value}</td>
			        		<td>${gift.level}</td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
			</div>	
			<!-- 新增奖品模态窗体 -->
			<div class="modal fade" id="gift_add_model" tabindex="-1" gift="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" gift="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">新增奖品信息</h4>
			      </div>
			      <div class="modal-body" style="max-height: 550px;overflow-y: auto">
			        <form id="gift_add_form" class="form-horizontal" method="post" action="${ctx}/gift/add">
			        	<div class="form-group">
							<label class="control-label col-md-3 col-md-offset-1">奖品名称：</label>
							<div class="col-sm-6">
								<input class="form-control" name="name" type="text" placeholder="请输入奖品名称" required="required"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" >奖品所属分类：</label>
							<div class="col-sm-3">
								<select class="form-control" name="parentTypeId" id="add_parentTypeId"></select>
							</div>
							<div class="col-sm-3">
								<select class="form-control" name="typeId" id="add_typeId" required="required"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3 col-md-offset-1" >中奖概率：</label>
							<div class="col-sm-6 ">
								<input class="form-control" name="probability" placeholder="请输入0-100之间的数字" type="number" required="required"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3 col-md-offset-1" >奖品等级：</label>
							<div class="col-sm-6 ">
								<label class="checkbox-inline">
									<input type="checkbox" name="levels" id="level1" value="1">Lv1
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" name="levels" id="level2" value="2">Lv2
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" name="levels" id="level3" value="3">Lv3
								</label>
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
		   <!-- 修改奖品信息模态窗体 -->
		   <div class="modal fade" id="gift_update_model" tabindex="-1" gift="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" gift="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">修改奖品信息</h4>
			      </div>
				  <div class="modal-body" style="max-height: 550px;overflow-y: auto">
				        <form id="gift_update_form" class="form-horizontal" method="post" action="${ctx}/gift/update">
				        	<input type="hidden" name="giftId"/>
				        	<div class="form-group">
								<label class="control-label col-md-3 col-md-offset-1">奖品名称：</label>
								<div class="col-sm-6">
									<input class="form-control" name="name" type="text" placeholder="请输入奖品名称" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4" >奖品所属分类：</label>
								<div class="col-sm-3">
									<select class="form-control" name="parentTypeId" id="update_parentTypeId"></select>
								</div>
								<div class="col-sm-3">
									<select class="form-control" name="typeId" id="update_typeId" required="required"></select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-md-offset-1" >中奖概率：</label>
								<div class="col-sm-6 ">
								<input class="form-control" name="probability" placeholder="请输入0-100之间的数字" type="number" required="required"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3 col-md-offset-1" >奖品等级：</label>
							<div class="col-sm-6 ">
								<label class="checkbox-inline">
									<input type="checkbox" name="levels" id="level1" value="1">Lv1
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" name="levels" id="level2" value="2">Lv2
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" name="levels" id="level3" value="3">Lv3
								</label>
							</div>
							<input type="hidden" name="level"/> 
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
			var giftIds = [];//多选时记录奖品id
			var selectedGiftId = "";//单选时记录奖品id
			var giftTypes = [];//奖品类型
			
			$(function(){
				getGiftType();
				
				//初始化查询条件
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$('#add_gift').on('click', addGift);
				$('#update_gift').on('click', updateGift);
				$('#delete_gift').on('click', deleteGift);
				
				$("#search").on("click",queryList);
				
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
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedGiftId) {
							$("#prev"+selectedGiftId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedGiftId = id;
						}else {
							selectedGiftId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedGiftId) {
						$("#prev"+selectedGiftId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedGiftId = "";
					}else {
						input.prop('checked', true);
						selectedGiftId = $(this).attr('id');
					}
				});
				
				$('#add_parentTypeId').on('change', function() {
					var $typeId = $('#add_typeId');
					var parentTypeId = $(this).val();
					$typeId.html('');
					$.each(giftTypes, function(index, type) {
						if(!isEmpty(type.parentTypeId) && parentTypeId == type.parentTypeId) {
							$typeId.append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
						}
					});
				});
				$('#update_parentTypeId').on('change', function() {
					var $typeId = $('#update_typeId');
					var parentTypeId = $(this).val();
					$typeId.html('');
					$.each(giftTypes, function(index, type) {
						if(!isEmpty(type.parentTypeId) && parentTypeId == type.parentTypeId) {
							$typeId.append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
						}
					});
				});
			});
			
			function getGiftType() {
				$.ajax({
				   type: "POST",
				   url: "${ctx}/gifttype/getAll",
				   success: function(msg){
					   giftTypes = msg.results.typeList;
					   console.info(giftTypes);
				   }
				});
			}
			
			//保存验证
			function checkSave() {
				var name = $('#gift_add_form input[name="name"]').val();
				var probability = $('#gift_add_form input[name="probability"]').val();
				var type = $('#add_typeId').val();
				var levels = [];
				$("#gift_add_form input[type='checkbox']").each(function(index){
					if($(this).prop('checked')) {
						levels.push($(this).val());
					}
				 });
				if(isEmpty(name)) {
					swal('奖品名称不能为空');
					return false;
				}
				if(isEmpty(probability)) {
					swal('获奖概率不能为空');
					return false;
				}
				if(probability < 0 || probability > 100) {
					swal('中奖概率为0-100之间的数字');
					return false;
				}
				if(isEmpty(type)) {
					swal('奖品类型不能为空');
					return false;
				}
				if(isEmpty(levels)) {
					swal('奖品等级不能为空');
					return false;
				}
				
				return true;
			}
			
			//修改验证
			function checkUpdate() {
				var name = $('#gift_update_form input[name="name"]').val();
				var probability = $('#gift_update_form input[name="probability"]').val();
				var type = $('#update_typeId').val();
				
				if(isEmpty(name)) {
					swal('奖品名称不能为空');
					return false;
				}
				if(isEmpty(probability)) {
					swal('获奖概率不能为空');
					return false;
				}
				if(probability < 0 || probability > 100) {
					swal('中奖概率为0-100之间的数字');
					return false;
				}
				if(isEmpty(type)) {
					swal('奖品类型不能为空');
					return false;
				}
				return true;
			}
		
			/**
			 *新增分类信息
			 */
			function addGift() {
				$('#gift_add_form')[0].reset();
				var $parentTypeId = $('#add_parentTypeId');
				var $typeId = $('#add_typeId');
				$parentTypeId.html('');
				$typeId.html('');
				var parentTypeId = "";
				$.each(giftTypes, function(index, type) {
					if(isEmpty(type.parentTypeId)) {
						if(index == 0) {
							parentTypeId = type.giftTypeId;
						}
						$parentTypeId.append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
					}else if(parentTypeId == type.parentTypeId){
						$typeId.append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
					}
				});
				
				$('#gift_add_model').modal({
				  	keyboard: false,
					show: true
				});
			}
			
			/**
			 *编辑分类信息
			 */
			function updateGift() {
				checkSelectShow();
				if(!isEmpty(giftIds) && giftIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedGiftId === '') {
					swal('请选择一条记录！');
					return;
				}
				$('#gift_update_form')[0].reset();
				var $parentTypeId = $('#update_parentTypeId');
				$parentTypeId.html('');
				$.each(giftTypes, function(index, type) {
					if(isEmpty(type.parentTypeId)) {
						$parentTypeId.append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
					}
				});
				
				//奖品信息回显
				$.ajax({
					   type: "GET",
					   url: "${ctx}/gift/" + selectedGiftId + "/get",
					   success: function(msg){
						    var gift = msg.results.gift;
						    $('#gift_update_form input[name="giftId"]').val(gift.giftId);
						    $('#gift_update_form input[name="name"]').val(gift.name);
							$('#update_parentTypeId').val(gift.type.parentType.giftTypeId);
							var $typeId = $('#update_typeId');
							var parentTypeId = gift.type.parentType.giftTypeId;
							$typeId.html('');
							$.each(giftTypes, function(index, type) {
								if(!isEmpty(type.parentTypeId) && parentTypeId == type.parentTypeId) {
									$typeId.append('<option value="'+type.giftTypeId+'">'+type.typeName+'</option>');
								}
							});
							$typeId.val(gift.typeId);
							$('#gift_update_form input[name="level"]').val(gift.level);
							$('#gift_update_form input[name="probability"]').val(gift.probability);
							$("#gift_update_form input[type='checkbox']").attr('disabled', 'disabled');
							$("#gift_update_form input[type='checkbox']").each(function(index){
							     if($(this).val() == gift.level){
							    	 $(this).prop('checked', true);
								 }
							 });
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
			function deleteGift() {
				checkSelectShow();
				if(!isEmpty(giftIds) && giftIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedGiftId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/gift/" + selectedGiftId + "/delete";
			}
			
			//检查选中行数
			function checkSelectShow() {
				giftIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						giftIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(giftIds) && giftIds.length == 1) {
					selectedGiftId = giftIds[0];
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
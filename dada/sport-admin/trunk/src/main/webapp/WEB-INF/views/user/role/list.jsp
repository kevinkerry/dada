<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<%-- <%@include file="/common/common-header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>角色列表</title>
        <script type="text/javascript">
			//定义全局变量
			var selectedRoleId = "";
		</script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
            <div class="form-group form-inline" style="margin-bottom: 10px;">
	            <shiro:hasPermission name="add:role">
	            	<button type="button" class="btn btn-primary" id="add_role"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	            </shiro:hasPermission>
				 <shiro:hasPermission name="update:role">
	            	<button type="button" class="btn btn-info" id="update_role"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
	            </shiro:hasPermission>
	             <shiro:hasPermission name="delete:role">
	            	<button type="button" class="btn btn-danger" id="delete_role"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
	            </shiro:hasPermission>
				
				<input class="form-control col-md-offset-1" id="name" type="search" placeholder="请输入角色进行搜索"/>
				<input class="form-control" id="show_name" type="search" placeholder="请输入角色名进行搜索"/>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</div>
	         <div class="form-group">
	    		
	        </div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th></th>
		                <th>#</th>
		                <th>角色名</th>
		                <th>角色</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="n" varStatus="status" > 
			        <tbody id="${n.id}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${n.id}"/></td>
			        		<td>${n.id}</td>
			        		<td>${n.showName}</td>
			        		<td>${n.name}</td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
          </div>
          
          <div class="modal fade" id="role_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">新增角色</h4>
			      </div>
			      <div class="modal-body" style="max-height: 550px;overflow-y: auto">
			        <form id="role_form" class="form-horizontal" action="${ctx}/role/add" method="post">
			        	<input name="id" type="hidden"/>
						<div class="form-group">
							<label class="control-label col-md-2 col-md-offset-1">角色：</label>
							<div class="col-sm-5">
								<input class="form-control" name="name" type="text" placeholder="请输入角色" required="required"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3" >角色名称：</label>
							<div class="col-sm-5 ">
								<input class="form-control" name="showName" placeholder="请输入角色名称" type="text" required="required"/>
							</div>
						</div>
						<div id="auth_list" style="display: none;"></div>
						<div class="form-group">
							<label class="control-label col-md-3" >权限：</label>
							<div class="col-sm-9 " id="auth_container">
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_role" class="btn btn-primary">保存</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
      </div>

		<script type="text/javascript">
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';
		
			$(function(){
				//初始化查询条件
				$('#name').val('${page.params.name}');
				$('#show_name').val('${page.params.showName}');
				
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$("#add_role").on("click",addRole);	
				$('#update_role').bind('click', updateRole);
				$('#delete_role').bind('click', deleteRole);
				$('#save_role').bind('click', saveRole);
				
				$("#name").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#show_name").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});
					
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedRoleId) {
							$("#prev"+selectedRoleId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedRoleId = id;
						}else {
							selectedRoleId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedRoleId) {
						$("#prev"+selectedRoleId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedRoleId = "";
					}else {
						input.prop('checked', true);
						selectedRoleId = $(this).attr('id');
					}
					//loadRole($(this).attr("id"));
				});
				
			});
			
			/**
			 *查询
			 */
			function queryList() {
				var name = $('#name').val();
				var showName = $('#show_name').val();
				
				var url="${ctx}/role/list?currentPage="+currentPage+"&pageSize="+pageSize;
			    if(name!="" && name!=null){
			    	name = encodeURI(encodeURI(name));
			    	url=url+"&name="+name;
			    }
			    if(showName!="" && showName!=null){
			    	showName = encodeURI(encodeURI(showName));
			    	url=url+"&showName="+showName;
			    }
			    
				location.href = url;
			}
			
			//弹出新增角色模态窗体
			function addRole() {
				//查询所有的权限
				queryAuth();
				
				$('#role_form input[name="id"]').val("");
				$('#role_form input[name="name"]').removeAttr("readonly");
				$('#role_form')[0].reset();
				$('#role_model').modal({
				  	keyboard: false,
					show: true
				});
			}
			
			/**
			 *修改
			 */
			 function updateRole() {
				if(selectedRoleId === '') {
					swal("请选择一条记录！");
					return;
				}
				loadRole(selectedRoleId);
			 }
			
			/**
			 *删除
			 */
			 function deleteRole() {
				 if(selectedRoleId === '') {
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
					},
					function(isConfirm) {
						if (isConfirm) {
							location.href="${ctx}/role/"+selectedRoleId+"/delete";
						}
					});
			 }
			
			//查询所有权限信息
			function queryAuth() {
				$.ajax({
				   type: "GET",
				   url: "${ctx}/auth/queryAll",
				   success: function(data){
				   		//console.info(data);
				   		if(data.state == 'success') {
				   			var authList = data.results.authList;
				   			if(authList != null && authList.length > 0) {
				   				$('#auth_container').empty();
				   				$.each(authList, function(index, auth) {
				   					$('#auth_container').append('<li class="checkbox-inline">'
						   					+'<input type="checkbox" value="'+auth.id+'"/>'+auth.name+''
											+'</li>');
				   					index = index + 1;
					   				if(index % 4 == 0) {
					   					$('#auth_container').append('<br/>');
					   				}
					   			});
				   			}
				   		}
				   }
				});
			}
			
			//弹出修改角色模态窗体
			function loadRole(id) {
				queryAuth();
				
				$.ajax({
				   type: "GET",
				   url: "${ctx}/role/"+id+"/update",
				   success: function(msg){
					   //console.info(msg);
				   		if(msg.success) {
				   			$('#role_form').attr('action', '${ctx}/role/update');
				   			$('#role_form input[name="id"]').val(msg.role.id);
				   			$('#role_form input[name="name"]').val(msg.role.name);
				   			$('#role_form input[name="name"]').attr("readonly", "readonly");
							$('#role_form input[name="showName"]').val(msg.role.showName);
							$('#myModalLabel').html('修改角色');
							if(msg.role.auths.length > 0) {
								loadAuth(msg.role.auths);
							}
							$('#role_model').modal({
							  	keyboard: false,
								show: true
							});
				   		}
				   }
				});
			}
			
			//修改角色时加载权限信息
			function loadAuth(auths) {
				$.each(auths, function(index, auth) {
					$("#auth_container input[type='checkbox']").each(function(index){
					     if($(this).val() == auth.id){
					    	 $(this).prop('checked', true);
						 }
					 });
				});
			}
			
			//保存角色信息
			function saveRole() {
				var id = $('#role_form input[name="id"]').val();
				var name = $('#role_form input[name="name"]').val();
				var showName = $('#role_form input[name="showName"]').val();
				
				if(name == null || name == '' || name == 'undefined') {
					swal('角色不能为空，请输入角色！');
					return false;
				}
				if(showName == null || showName == '' || showName == 'undefined') {
					swal('角色名称不能为空，请输入角色名称！');
					return false;
				}
				var i = 0;
				$('#auth_list').empty();
				$("#auth_container input[type='checkbox']").each(function(index){
				     if($(this).prop("checked")){
				    	 $('#auth_list').append('<input type="hidden" name="authTrans['+$(this).val()+']" value="'+$(this).val()+'" />');
				    	 i++;
					 }
				 });
				name = encodeURI(encodeURI(name));
				showName = encodeURI(encodeURI(showName));
				if(id == null || id == '' || id == 'undefined') {
					$.ajax({
					   type: "POST",
					   url: "${ctx}/role/check",
					   data: 'name='+name+'&showName='+showName,
					   success: function(data){
					   		if(data.state == 'failed') {
					   			swal(data.results.msg);
					   			return false;
					   		}
					   		$('#role_form').submit();
					   }
					});
				}else {
					$('#role_form').submit();
				}
			}
				   	
			function goPage(num, size) {
				currentPage = num;
				pageSize = size;
				queryList();
			}
		</script>
    </body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>权限列表</title>
        <script type="text/javascript">
			//定义全局变量
			var selectedAuthId = "";
		</script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
            <div class="form-group form-inline" style="margin-bottom: 10px;">
            	<shiro:hasPermission name="add:auth">
            		<button type="button" class="btn btn-primary" id="add_auth"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="update:auth">
            		<button type="button" class="btn btn-info" id="update_auth"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="delete:auth">
            		<button type="button" class="btn btn-danger" id="delete_auth"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
				
				<input class="form-control col-md-offset-1" id="name" type="search" placeholder="请输入权限名进行搜索"/>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</div>
	         <div class="form-group">
	    		
	        </div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th></th>
		                <th>#</th>
		                <th>权限名</th>
		                <th>权限</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="n" varStatus="status" > 
			        <tbody id="${n.id}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${n.id}"/></td>
			        		<td>${n.id}</td>
			        		<td>${n.name}</td>
			        		<td>${n.permission}</td>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
          </div>
          
          <div class="modal fade" id="auth_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">新增权限</h4>
			      </div>
			      <div class="modal-body">
			        <form id="auth_form" class="form-horizontal" action="${ctx}/auth/add" method="post">
			        	<input name="id" type="hidden"/>
						<div class="form-group">
							<label class="control-label col-md-3">权限名称：</label>
							<div class="col-sm-5">
								<input class="form-control" name="name" type="text" placeholder="请输入权限名称" required="required"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-md-offset-1" >权限：</label>
							<div class="col-sm-5 ">
								<input class="form-control" name="permission" placeholder="请输入权限" type="text" required="required"/>
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_auth" class="btn btn-primary">保存</button>
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
				
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$("#add_auth").on("click",addAuth);	
				$("#update_auth").on("click",updateAuth);	
				$("#delete_auth").on("click",deleteAuth);	
				$('#save_auth').bind('click', saveAuth);
				
				$("#name").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#permission").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});
					
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedAuthId) {
							$("#prev"+selectedAuthId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedAuthId = id;
						}else {
							selectedAuthId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedAuthId) {
						$("#prev"+selectedAuthId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedAuthId = "";
					}else {
						input.prop('checked', true);
						selectedAuthId = $(this).attr('id');
					}
				});
			});
			
			/**
			 *查询
			 */
			function queryList() {
				var name = $('#name').val();
				
				var url="${ctx}/auth/list?currentPage="+currentPage+"&pageSize="+pageSize;
			    if(name!="" && name!=null){
			    	name = encodeURI(encodeURI(name));
			    	url=url+"&name="+name;
			    }
			    
				location.href = url;
			}
			
			//弹出新增权限模态窗体
			function addAuth() {
				$('#auth_form input[name="id"]').val("");
				$('#auth_form input[name="name"]').removeAttr("readonly");
				$('#auth_form')[0].reset();
				$('#auth_model').modal({
				  	keyboard: false,
					show: true
				});
			}
			
			/**
			 *修改
			 */
			 function updateAuth() {
				if(selectedAuthId === '') {
					swal('请选择一条记录！');
					return;
				}
				loadAuth(selectedAuthId);
			 }
			
			/**
			 *删除
			 */
			 function deleteAuth() {
				 if(selectedAuthId === '') {
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
							location.href="${ctx}/auth/"+selectedAuthId+"/delete";
						}
					});
			 }
			
			//弹出修改权限模态窗体
			function loadAuth(id) {
				$.ajax({
				   type: "GET",
				   url: "${ctx}/auth/"+id+"/update",
				   success: function(msg){
				   		if(msg.success) {
				   			$('#auth_form').attr('action', '${ctx}/auth/update');
				   			$('#auth_form input[name="id"]').val(msg.auth.id);
				   			$('#auth_form input[name="name"]').val(msg.auth.name);
				   			$('#auth_form input[name="name"]').attr("readonly", "readonly");
							$('#auth_form input[name="permission"]').val(msg.auth.permission);
							$('#myModalLabel').html('修改权限');
							$('#auth_model').modal({
							  	keyboard: false,
								show: true
							});
				   		}
				   }
				});
			}
			
			//保存权限信息
			function saveAuth() {
				var id = $('#auth_form input[name="id"]').val();
				var name = $('#auth_form input[name="name"]').val();
				var permission = $('#auth_form input[name="permission"]').val();
				
				if(permission == null || permission == '' || permission == 'undefined') {
					swal('权限不能为空，请输入权限！');
					return false;
				}
				if(name == null || name == '' || name == 'undefined') {
					swal('权限名称不能为空，请输入权限名称！');
					return false;
				}
				
				name = encodeURI(encodeURI(name));
				permission = encodeURI(encodeURI(permission));
				if(id == null || id == '' || id == 'undefined') {
					$.ajax({
					   type: "POST",
					   url: "${ctx}/auth/check",
					   data: 'name='+name+'&permission='+permission,
					   success: function(data){
					   		if(data.state == 'failed') {
					   			swal(data.results.msg);
					   			return false;
					   		}
					   		$('#auth_form').submit();
					   }
					});
				}else {
					$('#auth_form').submit();
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
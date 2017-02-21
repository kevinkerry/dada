<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>用户列表</title>
        <script type="text/javascript">
			//定义全局变量
			var selectedUserId = "";
		</script>
    </head>
    <body>
        <div class="container-fluid">
	        <div class="form-group form-inline" style="margin-bottom: 10px;">
				<shiro:hasPermission name="add:user">
					<button type="button" class="btn btn-primary" id="addUser"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="update:user">
					<button type="button" class="btn btn-info" id="updateUser"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="delete:user">
					<button type="button" class="btn btn-danger" id="deleteUser"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
				</shiro:hasPermission>
			  	<input class="form-control col-md-offset-1" id="user_name" type="search" placeholder="请输入用户名进行搜索"/>
			  	<input class="form-control" id="nick_name" type="search" placeholder="请输入昵称进行搜索"/>
			  	<select class="form-control" id="sex" style="width: 150px;">
		          <option value="">--请选择性别--</option>
		          <option value="1">男</option>
		          <option value="0">女</option>
		        </select>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th></th>
		                <th>#</th>
		                <th>用户名</th>
		                <th>昵称</th>
		                <th>性别</th>
		                <th>邮箱</th>
		                <th>生日</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="n" varStatus="status" > 
			        <tbody id="${n.id}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${n.id}"/></td>
			        		<td>${n.id}</td>
			        		<td>${n.username}</td>
			        		<td>${n.nickname}</td>
			        		<td>
				        		<c:choose>
					        		<c:when test="${n.sex==1}">
					        		男
					        		</c:when>
					        		<c:when test="${n.sex==0}">
					        		女
					        		</c:when>
						        	<c:otherwise>
						        	保密
						        	</c:otherwise>
				        		</c:choose>
			        		</td>
			        		<td>${n.email}</td>
			        		<td>${n.birthday}</td>
			        	</tr>
			        </tbody>
		        </c:forEach>
		
			</table>
	<%@include file="/common/page.jsp" %>
        </div>
		 <script type="text/javascript">
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
		 
			$(function(){
				//初始化查询条件
				$('#user_name').val('${page.params.username}');
				$('#nick_name').val('${page.params.nickname}');
				$('#sex').val('${page.params.sex}');
				
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$("#search").on("click", queryList);
				$('#addUser').on('click', addUser);	
				$('#updateUser').on('click', updateUser);	
				$('#deleteUser').on('click', deleteUser);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedUserId) {
							$("#prev"+selectedUserId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedUserId = id;
						}else {
							selectedUserId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedUserId) {
						$("#prev"+selectedUserId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedUserId = "";
					}else {
						input.prop('checked', true);
						selectedUserId = $(this).attr('id');
					}
					//alert(checked);
					
				});
				
				$("#user_name").on("keypress",function(event){
					if(event.which==13){
						queryList();	
					}
				});	
				
				$("#nick_name").on("keypress",function(event){
					if(event.which==13){
						queryList();	
					}
				});
				
				$("#sex").on("change",function(event){
					queryList();
				});
				
			});
			
			/**
			 *查询
			 */
			function queryList() {
				var username = $('#user_name').val();
				var nickname = $('#nick_name').val();
				var sex = $('#sex').val();
				
				var url="${ctx}/user/list?currentPage="+currentPage+"&pageSize="+pageSize;
			    if(!isEmpty(username)){
			    	username = encodeURI(encodeURI(username));
			    	url=url+"&username="+username;
			    }
			    if(!isEmpty(nickname)){
			    	nickname = encodeURI(encodeURI(nickname));
			    	url=url+"&nickname="+nickname;
			    }
			    if(sex !="" && sex != null){
			    	url=url+"&sex="+sex;
			    }
			    
				location.href = url;
			}
			
			/**
			 *新增
			 */
			function addUser() {
				location.href = '${ctx}/user/add';
			}
			
			/**
			 *修改
			 */
			function updateUser() {
				if(selectedUserId === '') {
					swal("请选择一条记录！");
					return;
				}
				location.href="${ctx}/user/"+selectedUserId+"/update";
			}
			
			/**
			 *删除
			 */
			 function deleteUser() {
				if(selectedUserId === '') {
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
							location.href = "${ctx}/user/" + selectedUserId + "/delete";
						}
					});
					
				}

				function goPage(num, size) {
					currentPage = num;
					pageSize = size;
					queryList();
				}
			</script>
    </body>
</html>
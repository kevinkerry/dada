<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Tables</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="${ctx }/resources/assets/styles.css" />
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
<style type="text/css">
        td img{
        	border-radius: 5px;
        }
        td{
        	font-family:"微软雅黑";
        	font-size:1.2em;
        }
        .box{
	margin-left:auto;
	margin-right:auto;
	margin-top:auto;
	margin-bottom:auto;
	position: fixed;
	display:table;
  	left: 0;
  	right: 0;
  	top: 0;
  	bottom: 0;
 	height: 120px;
 	z-index: 1000;
	width:500px;
	background-color:#E7E7E7;

}
.box textarea{
	margin-left:auto;
	margin-right:auto;
	margin-top:auto;
	margin-bottom:auto;
	height:300px;
	width:100%;
	font-family:"微软雅黑";
	font-size:2em;
	margin-bottom:5px;
}
.box input{
	margin-left:auto;
	margin-right:auto;
	margin-top:auto;
	margin-bottom:auto;
	float:right;
	display:inline-block;
	margin-left:5px;
}
        </style>
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script type="text/javascript">
			//定义全局变量
			var selectedIntelligentId = "";
		</script>
    </head>
    
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
	            <div class="form-group form-inline">
		            <!-- <button type="button" class="btn btn-primary" id="add_intelligent"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button> -->
					<shiro:hasPermission name="update:intelligent">
						<button type="button" class="btn btn-info" id="update_intelligent"><i class="glyphicon glyphicon-pencil"></i>&nbsp;审核</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="update:intelligent">
	            		<button type="button" class="btn btn-success" id="recommend_intelligent"><i class="glyphicon glyphicon-send"></i>&nbsp;推荐</button>
	            	</shiro:hasPermission>
					<!-- <button type="button" class="btn btn-danger" id="delete_intelligent"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button> -->
			    	<input class="form-control col-md-offset-1" id="username" type="text" placeholder="请输入达人姓名" style="width: 150px;"/>	
			    	<input class="form-control" id="mobile" type="text" placeholder="请输入手机号码" style="width: 150px;"/>
			    	<select class="form-control" id="type" style="width: 120px;">
			          <option value="">请选择类型</option>
			          <option value="1">颜值爆表</option>
					  <option value="2">低调奢华</option>
					  <option value="3">百科全书</option>
			        </select>	
			        <select class="form-control" id="status" style="width: 120px;">
			          <option value="">审核状态</option>
			          <option value="1">通过</option>
					  <option value="0">未审核</option>
					  <option value="-1">不通过</option>
			        </select>
			        <select class="form-control" id="recommendFlag" style="width: 120px;">
			          <option value="">是否推荐</option>
			          <option value="1">已推荐</option>
			          <option value="0">未推荐</option>
			        </select>
			        <input id="search" type="button" class="btn btn-default" value="搜索"/>
	       		 </div>
				<table class="table table-bordered table-hover">
			    	<thead>
			            <tr>
			            	<th><input type="checkbox" id="selectAll"/></th>
			                <th>#</th>
			                <th>达人照片</th>
			                <th>姓名</th>
			                <th>手机号码</th>
			                <th>微博</th>
			                <th>分类</th>  
			                <th>排序</th>
		                	<th>是否推荐</th>
			                <th>审核状态</th>  
							<th>审核意见</th> 
			            </tr>
			        </thead>
			        <c:forEach items="${page.result}" var="n" varStatus="status" > 
				        <tbody id="${n.id}">
				        	<tr>
				        		<td width="30px;"><input type="checkbox" id="prev${n.id}"/></td>
				        		<td>${n.id}</td>
				        		<td>
				        			<c:forEach items="${n.images}" var="o" varStatus="status" > 
				        			
									<c:set value="${fn:split(o.imageUrl, '.')}" var="imageUrl" />
				            		<c:set value="${fn:split(imageUrl[0], '!')}" var="imageName" />
				
				        			<a href="${image}${o.imageUrl}" data-lightbox="roadtrip">
				        				<img src="${image }${imageName[0]}!50x50.${imageUrl[1]}" width="32px" height="32px" style="margin-right:1px"/>
				        			</a>
				        			 </c:forEach>
				        		</td>
				        		<td>${n.username}</td>
				        		<td>${n.mobile}</td>
				        		<td>${n.weibo}</td>
				        		<c:if test="${empty n.category}">
				        			<td ></td>
				        		</c:if>      
								<c:if test="${n.category eq 1 }">
				        			<td >颜值爆表</td>
				        		</c:if>        			
				        		<c:if test="${n.category eq 2 }">
				        			<td >低调奢华</td>
				        		</c:if>    
				        		<c:if test="${n.category eq 3 }">
				        			<td>百科全书</td>
				        		</c:if>
				        		<c:choose>
				        			<c:when test="${n.recommendFlag eq 1}">
					        			<shiro:hasPermission name="update:intelligent">
						        			<td width="30px;" recommend-order="${n.orders}" align="center">
						        				<div onclick="editOrder(this, event);" style="width: 30px;height: 25px;text-align: center;color:#337ab7;cursor:pointer;" title="点击修改达人推荐顺序">${n.orders}</div>
						        			</td>
					        			</shiro:hasPermission>
					        			<shiro:lacksPermission name="update:intelligent">
					        				<td width="30px;" recommend-order="${n.orders}" align="center">
						        				<div style="width: 30px;height: 25px;text-align: center;">${member.orders}</div>
						        			</td>
					        			</shiro:lacksPermission>
				        			</c:when>
				        			<c:otherwise>
				        				<td width="30px;"></td>
				        			</c:otherwise>
				        		</c:choose>
				        		<c:choose>
				        			<c:when test="${n.recommendFlag eq 1}">
				        				<shiro:hasPermission name="update:intelligent">
						        			<td width="120px;">
						        				<div class="btn-group btn-group-sm" role="group">
						        				  <button type="button" class="btn btn-default" disabled="disabled">YES</button>
												  <button type="button" class="btn btn-danger" onclick="recommed(0, this, event);">NO</button>
												</div>
						        			</td>
						        		</shiro:hasPermission>
						        		<shiro:lacksPermission name="update:intelligent">
					        				<td>已推荐</td>
				        				</shiro:lacksPermission>
				        			</c:when>
				        			<c:otherwise>
				        				<shiro:hasPermission name="update:intelligent">
						        			<td width="120px;">
						        				<div class="btn-group btn-group-sm" role="group">
												  <button type="button" class="btn btn-default" disabled="disabled">NO</button>
												  <button type="button" class="btn btn-success" onclick="recommed(1, this, event);">YES</button>
												</div>
						        			</td>
						        			</shiro:hasPermission>
					        			<shiro:lacksPermission name="update:intelligent">
					        				<td>未推荐</td>
					        			</shiro:lacksPermission>
				        			</c:otherwise>
				        		</c:choose> 
						 		<c:if test="${n.status==0 || empty n.status}">
				        			<shiro:hasPermission name="update:intelligent">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-success" onclick="changeStatus(1, this, event);">通过</button>
											  <button type="button" class="btn btn-info" onclick="changeStatus(-1, this, event);">不通过</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:intelligent">
				        				<td>未审核</td>
				        			</shiro:lacksPermission>
				        		</c:if>
				           		<c:if test="${n.status==1}">
			        				<td>
			        					通过
			        				</td>
				        		</c:if>   
				        		<c:if test="${n.status<0}">
									<td>不通过</td>
								</c:if>  
								<c:if test="${fn:length(n.notReason) gt 28}">
									<td width="300px;">
										<p>
											${fn:substring(n.notReason, 0, 28)}
											<a style="cursor: pointer;" onclick="showMore(this);">查看更多</a>
										</p>
										<p style="display: none;">
											${n.notReason}
											<a style="cursor: pointer;" onclick="showLess(this);">收起</a>
										</p>
									</td>
								</c:if>
								<c:if test="${fn:length(n.notReason) lt 28}">
									<td width="300px;">${n.notReason}</td>
								</c:if>
				        	</tr>
				        </tbody>
			        </c:forEach>
				</table>
				<%@include file="/common/page.jsp" %>
            </div>
        </div>
        
        <div class="modal fade" id="pass_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">审核通过</h4>
			      </div>
			      <div class="modal-body" style="padding-bottom: 0px;">
			        <form class="form-horizontal" method="post">
						<div class="form-group">
							<label class="control-label col-md-3">达人类型：</label>
							<div class="col-sm-5">
								<select id="category" class="form-control">
									<option value="1">颜值爆表</option>
									<option value="2">低调奢华</option>
									<option value="3">百科全书</option>
								</select>
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_category" class="btn btn-primary">保存</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
      	  </div>
      	  
      	  <div class="modal fade" id="refuse_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">审核通过</h4>
			      </div>
			      <div class="modal-body" style="padding-bottom: 0px;">
			        <form class="form-horizontal" method="post">
						<div class="form-group">
							<label class="control-label col-md-3">审核意见：</label>
							<div class="col-sm-8">
								<textarea class="form-control" id="refuse_reason" rows="5"></textarea>
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_refuse_reason" class="btn btn-primary">保存</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
      	  </div>
      	  
      	   <div class="modal fade" id="recommend_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">达人推荐</h4>
			      </div>
			      <div class="modal-body" style="padding-bottom: 0px;">
			        <form class="form-horizontal" method="post">
						<div class="form-group">
							<label class="control-label col-md-3">推荐顺序：</label>
							<div class="col-sm-5">
								<input class="form-control" id="recommend_order" type="text" placeholder="请输入推荐顺序" required="required"/>
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_order" class="btn btn-primary">保存</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
     	 </div>
        <script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
        <script src="${ctx }/resources/assets/scripts.js"></script>
        <script src="${ctx }/resources/assets/DT_bootstrap.js"></script>
		 <script type="text/javascript">
			var intelligentIds = [];//多选时记录达人id
		 	var currentPage = '${page.currentPage}';
		 	var pageSize = '${page.pageSize}';	
		 	
		   	$(function(){
		   		//初始化查询条件
		   		$("#username").val('${page.params.username}');
		   		$("#mobile").val('${page.params.mobile}');
		   		$("#type").val('${page.params.category}');
		   		$("#status").val('${page.params.status}');
		   		$('#recommendFlag').val('${page.params.recommendFlag}');
		   		
		   		
		   		$("tbody").find('input:checkbox:checked').prop('checked', false);
		   		
		   		$('#search').bind('click', queryList);
				$('#add_intelligent').bind('click', addIntelligent);
				$('#update_intelligent').bind('click', updateIntelligent);
				$('#delete_intelligent').bind('click', deleteIntelligent);
				$('#recommend_intelligent').on('click', recommendIntelligent);
				$('#type').on('change', queryList);
				$('#status').on('change', queryList);
				$('#selectAll').on('click', selectAll);
				$("#recommendFlag").on("change", queryList);
				
				$("#username").on("keypress",function(event){
					if(event.which==13){
						$("#search").click();	
					}
				});	
				
				$("#mobile").on("keypress",function(event){
					if(event.which==13){
						$("#search").click();	
					}
				});	
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						if(id != selectedIntelligentId) {
							$("#prev"+selectedIntelligentId).prop('checked', false);
						}
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedIntelligentId = id;
						}else {
							selectedIntelligentId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					if($(this).attr('id') != selectedIntelligentId) {
						$("#prev"+selectedIntelligentId).prop('checked', false);
					}
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedIntelligentId = "";
					}else {
						input.prop('checked', true);
						selectedIntelligentId = $(this).attr('id');
					}
				});
		   	});
		   	
		   	/**
			 *查询
			 */
			function queryList() {
				var username = $('#username').val();
				var mobile = $('#mobile').val();
				var category = $('#type').val();
				var status = $('#status').val();
				var recommendFlag = $('#recommendFlag').val();
				
				var url="${ctx}/intelligent/list?currentPage="+currentPage+"&pageSize="+pageSize;
			    if(!isEmpty(username)){
			    	username = encodeURI(encodeURI(username));
			    	url=url+"&username="+username;
			    }
			    if(!isEmpty(mobile)){
			    	url=url+"&mobile="+mobile;
			    }
			    if(!isEmpty(category)){
			    	url=url+"&category="+category;
			    }
			    if(!isEmpty(status)){
			    	url=url+"&status="+status;
			    }
			    if (!isEmpty(recommendFlag)) {
					url = url + "&recommendFlag=" + recommendFlag;
				}
			    
				location.href = url;
			}
		   	
		   	
			function goPage(num, size) {
				currentPage = num;
				pageSize = size;
				queryList();
			}
			
			//批量推荐达人
			function recommendIntelligent() {
				checkSelectIntelligent();
				if(isEmpty(intelligentIds)) {
					swal('请选择需要推荐的达人！');
					return false;
				}
				location.href = "${ctx}/intelligent/batchRecommend?intelligentIds="+intelligentIds;
			}
			
			//达人审核，1通过，-1不通过
			function changeStatus(status, dom, event) {
				var intelligentId = $(dom).parent().parent().parent().parent().attr('id');
				if(status == 1) {
					$('#pass_model').modal({
						keyboard : false,
						show : true
					});
				}else if(status == -1) {
					$('#refuse_model').modal({
						keyboard : false,
						show : true
					});
				}
				
				$("#save_category").on("click",function() {
					var category = $('#category').val();
					$.ajax({
						url:'${ctx}/intelligent/pass',
						data:'id='+intelligentId+'&category='+category+"&status="+status,
						success:function(data){
							queryList();
						}
					});
				});
				
				$("#save_refuse_reason").on("click",function() {
					var notReason = $('#refuse_reason').val();
					
					if(isEmpty(notReason)) {
						swal('请输入不通过原因！');
						return false;
					}
					$.ajax({
						type: "POST",
						url:'${ctx}/intelligent/out',
						data:'id='+intelligentId+'&notReason='+notReason,
						success:function(data){
							queryList();
						}
					});
				});
				
				event.stopPropagation();
			}
			
			//新增
			function addIntelligent() {
				location.href = "${ctx}/intelligent/add";
			}
			
			/**
			 *修改
			 */
			 function updateIntelligent() {
				checkSelectIntelligent();
				if(!isEmpty(intelligentIds) && intelligentIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if(selectedIntelligentId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/intelligent/"+selectedIntelligentId+"/detail";
			 }
			
			/**
			 *删除
			 */
			 function deleteIntelligent() {
				checkSelectIntelligent();
				if(!isEmpty(intelligentIds) && intelligentIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if(selectedIntelligentId === '') {
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
							location.href="${ctx}/intelligent/"+selectedIntelligentId+"/delete";
						}
					});
			 }
			
			//检查选中行数
			function checkSelectIntelligent() {
				intelligentIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						intelligentIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(intelligentIds) && intelligentIds.length == 1) {
					selectedIntelligentId = intelligentIds[0];
				}
			}
			
			//显示更多
			function showMore(dom) {
				$(dom).parent().slideToggle("slow","linear");
				$(dom).parent().next().slideToggle("slow","linear");
			}
			
			//显示更多
			function showLess(dom) {
				$(dom).parent().slideToggle("slow","linear");
				$(dom).parent().prev().slideToggle("slow","linear");
			}
			
			//编辑order
			function editOrder(dom, event) {
				var recommendOrder = $(dom).parent().attr('recommend-order');
				$(dom).html('<input value="'+recommendOrder+'" class="recommendOrder" style="width: 30px;height: 25px;text-align: center;"/>');
				$('.recommendOrder').on('click', function(e) {
					e.stopPropagation();
				});
				$('.recommendOrder').focus();
				$('body').on('click', function(e) {
					$('.recommendOrder').off('focus');
					var target = $(e.target);
					if(target.attr('class') != 'recommendOrder') {
						var recommend = $('.recommendOrder');
						var intelligentId = recommend.parent().parent().parent().parent().attr('id');
						orders = recommend.val();
						if(isEmpty(orders)) {
							swal('推荐顺序不能为空，请重新输入！');
							return;
						}
						location.href = "${ctx}/intelligent/modify?intelligentId=" + intelligentId +"&orders="+ orders;
					}else {
						return false;
					}
					target = null;
					e.stopPropagation();
				});
				event.stopPropagation();
			}
			
			//是否推荐
			function recommed(recommendFlag, dom, event) {
				$('#recommend_order').val("");
				var intelligentId = $(dom).parent().parent().parent().parent().attr('id');
				var recommendOrder = null;
				if(recommendFlag == 1) {
					$('#recommend_model').modal({
						keyboard : false,
						show : true
					});
				}else {
					swal({
						title : "",
						text : "确认取消推荐？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/intelligent/modify?intelligentId=" + intelligentId +"&recommendFlag=" + recommendFlag;
						}
					});
				}
				
				$("#save_order").on("click",function() {
					recommendOrder = $('#recommend_order').val();
					if(isEmpty(recommendOrder)) {
						swal('推荐顺序不能为空，请重新输入！');
						return false;
					}
					location.href = "${ctx}/intelligent/modify?intelligentId=" + intelligentId +"&orders="+ recommendOrder +"&recommendFlag=" + recommendFlag;
				});
				
				event.stopPropagation();
			}
			
			//达人全选
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
		</script>
    </body>
</html>
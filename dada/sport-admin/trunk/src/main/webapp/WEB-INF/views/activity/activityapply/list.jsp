<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<%-- <%@include file="/common/common-header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Tables</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
		<style type="text/css">
		#mapContainer{height:480px;width:600px;}
		#result1{
			position:absolute;
			z-index: 100;
			background-color: white;
		}
		#imglist{
			display: inline-flex;
			
		}
		.imgbox{
			width:100px;
			height:100px;
			background-size: cover;
			margin-right:15px;
		}
		.imgbox img{
			z-index:100;float: right;margin-top: -15px;margin-right:-15px; 
		}
		</style>
         <script type="text/javascript">
			//定义全局变量
			var selectedActivityApplyId = "";
		</script>
    </head>
    
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
<!--内容开始-->
	<div class="form-group form-inline">	
		<legend>${activityTitle}</legend>
		<label style="float:left;font-size: 2rem;margin-top: 10px;">报名人数：${page.totalCount}</label>
		<div style="float:right;margin-bottom: 15px;">
		<%-- 	<shiro:hasPermission name="add:apply">
				<button type="button" class="btn btn-primary" id="add_apply"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增报名</button>
			</shiro:hasPermission> 
			<shiro:hasPermission name="update:apply">
				<button type="button" class="btn btn-info" id="update_apply"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改报名</button>
			</shiro:hasPermission>--%>
			<shiro:hasPermission name="delete:apply">
				<button type="button" class="btn btn-danger" id="delete_apply"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除报名</button>
			</shiro:hasPermission>
			<!-- <button type="button" class="btn btn-success" id="import"><i class="glyphicon glyphicon-import"></i>&nbsp;导入</button> -->
			<button type="button" class="btn btn-success" id="export"><i class="glyphicon glyphicon-export"></i>&nbsp;导出</button>
			<form id="apply_import_form" action="${ctx}/activityapply/importApply" enctype="multipart/form-data" style="display: none;" method="post">
				<input type="hidden" name="activityId" value="${activityId}"/>
				<input type="file" id="activity_apply_file" name="file"/> 
			</form>
        	<!-- <button type="button" class="btn btn-info" id="edit_activity"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改活动</button> -->
        	
        	<button type="button" class="btn btn-default" onclick="javascript :location.href='${ctx}/activity/list'"><i class=""></i>&nbsp;返回</button>
		</div>
   </div>
	<table cellpadding="0" cellspacing="0" class="table table-bordered">
    	<thead>
            <tr>
           		<th></th>
                <th>#</th>
                <th>姓名(昵称)</th>
                <th>手机</th>
                <!-- <th>微信号</th>
                <th>单位</th> -->
                 <th>备注</th>
                <th>报名时间</th>
                <th>审核状态 </th>
            </tr>
        </thead>
        <c:forEach items="${page.result}" var="activityApply" varStatus="status" >
	        <tbody id="${activityApply.applyId}">
	        	<tr>
	        		<td width="30px;"><input type="checkbox" id="prev${activityApply.applyId}"/></td>
	        		<td>${activityApply.applyId}</td>
	        		<td><c:if test="${empty activityApply.name}">(${activityApply.member.memberAlias})</c:if><c:if test="${not empty activityApply.name}">${activityApply.name}(${activityApply.member.memberAlias})</c:if></td>
	        		<td>${activityApply.phone}</td>
	        		<%-- <td>${activityApply.weixin}</td> 
	        		<td>${activityApply.workunit}</td>--%>
	        		<td>${activityApply.applyDesc}</td>
	        		<c:if test="${empty activityApply.applyTime}">
	        			<td></td>
	        		</c:if>
	        		<c:if test="${!empty activityApply.applyTime}">
	        			<td>
		        			<fmt:formatDate value="${activityApply.applyTime}" pattern="yyyy-MM-dd" /> 
		        		 </td>
	        		</c:if>
	        			<c:if test="${activityApply.auditStatus==1 || empty activityApply.auditStatus}">
				        			<shiro:hasPermission name="update:apply">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-success" onclick="changeStatus(${activityApply.activityId},2, this, event);">通过</button>
											  <button type="button" class="btn btn-info" onclick="changeStatus(${activityApply.activityId},3, this, event);">不通过</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:apply">
				        				<td>未审核</td>
				        			</shiro:lacksPermission>
				        		</c:if>
				           		<c:if test="${activityApply.auditStatus==2}">
			        				<td>
			        					通过
			        				</td>
				        		</c:if>   
				        		<c:if test="${activityApply.auditStatus==3}">
									<td>不通过</td>
								</c:if>  
	        	</tr>
	        </tbody>
        </c:forEach>
	</table>
	<%@include file="/common/page.jsp" %>
<!--内容结束-->
                </div>
            </div>
			<div class="modal fade" id="applyModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">新增报名</h4>
			      </div>
			      <div class="modal-body">
			        <form id="activity_apply_form" class="form-horizontal"  action="${ctx}/activityapply/add" method="post">
			        	<input type="hidden" name="activityId" value="${activityId}"/>
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1">姓名：</label>
							<div class="col-sm-5">
								<input class="input-xlarge form-control" name="userName" type="text" required />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >手机：</label>
							<div class="col-sm-5 ">
								<input class="input-xlarge form-control" name="telephone" type="text" required />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >微信号：</label>
							<div class="col-sm-5">
								<input class="input-xlarge form-control" name="weixin" type="text" required />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 col-sm-offset-1" >单位：</label>
							<div class="col-sm-5">
								<input class="input-xlarge form-control" name="org" type="text" required />
							</div>
						</div>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" id="save_apply" class="btn btn-primary">保存</button>
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
        </div>
        <!--/.fluid-container-->

        <script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
		
  <div class="modal fade" id="refuse_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">审核不通过</h4>
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
      	  
    </body>

</html>
<script>
	$(function() {
		$('#add_apply').bind('click', addApply);
		$('#update_apply').bind('click', updateApply);
		$('#edit_activity').bind('click', editAcvitity);
		$('#delete_apply').bind('click', deleteApply);
		$('#save_apply').bind('click', saveApply);
		$('#export').bind('click', exportExcell);
		
		if('${msg}') {
			swal('${msg}');
		}
		
		$("tbody").each(function(index) {
			var id = $(this).attr('id');
			$(this).find('input').on('click', function(e) {
				if(id != selectedActivityApplyId) {
					$("#prev"+selectedActivityApplyId).prop('checked', false);
				}
				var input = $(e.target);
				var checked = input.prop('checked');
				if(checked) {
					selectedActivityApplyId = id;
				}else {
					selectedActivityApplyId = "";
				}
				e.stopPropagation();
			});
		});
		
		$("tbody").on("click",function(){
			//只允许同时选中一个
			if($(this).attr('id') != selectedActivityApplyId) {
				$("#prev"+selectedActivityApplyId).prop('checked', false);
			}
			
			//设置选择或未选中
			var input = $(this).find('input');
			var checked = input.prop('checked');
			if(checked) {
				input.prop('checked', false);
				selectedActivityApplyId = "";
			}else {
				input.prop('checked', true);
				selectedActivityApplyId = $(this).attr('id');
			}
		});
		
		$('#import').on('click', importApply);
	});
	
	//弹出新增报名模态窗体
	function addApply() {
		$('#activity_apply_form')[0].reset();
		$('#applyModel').modal({
		  	keyboard: false,
			show: true
		});
	}
	
	//审核
	function changeStatus(activityId,auditStatus, dom, event) {
				var applyId = $(dom).parent().parent().parent().parent().attr('id');
				if(auditStatus == 3) {
					$('#refuse_model').modal({
						keyboard : false,
						show : true
					});
				}else {
					$.ajax({
						type: "POST",
						url:"${ctx}/activityapply/modify",
						data:'applyId='+applyId+'&auditComments=&auditStatus=2',
						success:function(data){
							location.href = "${ctx}/activityapply/list?activityId=" + activityId;
						}
					});
				}

				$("#save_refuse_reason").on("click",function() {
					var notReason = $('#refuse_reason').val();
					if(isEmpty(notReason)) {
						swal('请输入不通过原因！');
						return false;
					}
					$.ajax({
						type: "POST",
						url:"${ctx}/activityapply/modify",
						data:'applyId='+applyId+'&auditComments='+notReason+'&auditStatus=3',
						success:function(data){
							location.href = "${ctx}/activityapply/list?activityId=" + activityId;
						}
					});
				});
				
				event.stopPropagation();
			}
	
	//弹出修改报名模态窗体
	function updateApply() {
		if(selectedActivityApplyId === '') {
			swal('请选择一条记录！');
			return;
		}
		$.ajax({
			   type: "GET",
			   url: "${ctx}/activityapply/"+selectedActivityApplyId+"/update",
			   success: function(data){
				   //console.info(data);
			   		if(data.state == 'success') {
			   			var activityApply = data.results.activityApply;
			   			$('#activity_apply_form').attr('action', '${ctx}/activityapply/update');
			   			var username = "";
			   		    username = activityApply.name;
			   			
			   			$('#activity_apply_form input[name="userName"]').val(username);
			   			$('#activity_apply_form input[name="telephone"]').val(activityApply.member.mobile);
			   			$('#activity_apply_form input[name="weixin"]').val(activityApply.member.wechat);
						$('#activity_apply_form input[name="org"]').val(activityApply.member.workunit);
						$('#myModalLabel').html('修改报名信息');
						$('#applyModel').modal({
						  	keyboard: false,
							show: true
						});
			   		}
			   }
			});
	}
	
	//修改活动信息
	function editAcvitity() {
		location.href = "${ctx}/activity/update?activityId="+'${activityId}';
	}
	
	//保存报名信息
	function saveApply() {
		$('#activity_apply_form').submit();
	}
	
	/**
	 *删除
	 */
	 function deleteApply() {
		if(selectedActivityApplyId === '') {
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
					location.href="${ctx}/activityapply/"+selectedActivityApplyId+"/delete";
				}
			});
	 }
	
	//到处excell
	function exportExcell() {
		var excellName = encodeURI(encodeURI('${activityTitle}'+'活动报名详情'));
		
		location.href = "${ctx}/activityapply/exportExcel?activityId="+'${activityId}'+"&excellName="+excellName;
	}
	
/* 	function importApply() {
		$('#activity_apply_file').click();
		$('#activity_apply_file').on('change', function() {
			var fileName = $('#activity_apply_file').val();
			if(isEmpty(fileName)) {
				swal('请上传.xls或.xlsx格式活动报名文件！');
				return false;
			}
			fileName = fileName.substring(fileName.lastIndexOf('.'));
			if(fileName != '.xls' && fileName != '.xlsx') {
				swal('请上传.xls或.xlsx格式文件！');
				return false;
			}
			$('#apply_import_form').submit();
		});
	} */
	
	function goPage(num, pageSize) {
		location.href = "${ctx}/activityapply/list?currentPage="+num+"&pageSize="+pageSize+"&activityId=${activityId}";
	}
</script>

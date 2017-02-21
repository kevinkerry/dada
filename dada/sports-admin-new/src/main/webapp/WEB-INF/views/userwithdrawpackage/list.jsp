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
  	top: 0;.
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
        <script type="text/javascript">
		</script>
    </head>
    
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
               <div class="form-group form-inline" style="margin-bottom: 10px;">
	                <select class="form-control" id="status" style="width: 120px;" onchange="queryList()">
	                <c:if test="${page.params.status eq 0}">
	                   <c:set var="sl0" value="selected='selected'" />
	                </c:if>
	                <c:if test="${page.params.status eq 1}">
	                   <c:set var="sl1" value="selected='selected'" />
	                </c:if>
	                <c:if test="${page.params.status eq 2}">
	                   <c:set var="sl2" value="selected='selected'" />
	                </c:if>
			          <option value="">提现状态</option>
			          <option value="0" ${sl0 }>处理中</option>
			          <option value="1" ${sl1 }>已处理</option>
			          <option value="2" ${sl2 }>已退回</option>
			        </select>
					<input class="form-control " id="condition" type="search" value="${page.params.condition}" placeholder="请请输入昵称或者支付宝帐号进行搜索" style="width: 250px;" />
					<button id="search" type="button" class="btn btn-default" onclick="queryList()">搜索</button>
					<button type="button" class="btn btn-success" id="bh" >
					    <i class="glyphicon glyphicon-pencil"></i>&nbsp;已处理
					</button>
					  
				</div>	       		  
			   <table class="table table-bordered table-hover">
			    	<thead>
			            <tr>
			                <th><input type="checkbox" id="selectAll" /></th>
			                <th>序号</th>
			                <th>提现编号</th>
			                <th>提现帐号</th>
			                <th>手机号码</th>
			                <th>提现金额</th>
			                <th>支付宝帐号</th>
			                <th>真实姓名</th>
			                <th>提现状态</th>
			                <th>操作</th>
			            </tr>
			        </thead>
			        <c:forEach items="${page.result}" var="s" varStatus="status" > 
				        <tbody id="${s.withdrawPackageId}">
				        	<tr>
					        	<td width="30px;">
					        	   <c:if test="${s.status eq 0}">
				        				<input type="checkbox" id="prev${s.withdrawPackageId}"  />
				        		   </c:if>
								</td>
				        	    <td>${s.withdrawPackageId}</td>
				        		<td>${s.withdrawNum}</td>
				        		<td>${s.member.mobile}</td>
				        		<td>${s.member.mobile}</td>
				        		<td>${s.money}</td>
				        		<td>${s.member.alipay}</td>
				        		<td>${s.member.realName}</td>
				        		<td>
				        			<c:if test="${s.status eq 0}">
				        				处理中
				        			</c:if>
				        			<c:if test="${s.status eq 1}">
				        				已处理
				        			</c:if>
				        			<c:if test="${s.status eq 2}">
				        				已退回
				        			</c:if>
				        		</td>
				        		<td>
					        		<c:if test="${s.status eq 0}">
					        			  <c:set var="ds" value="" /> 
					        		</c:if>
					        		<c:if test="${s.status eq 1}">
					        			  <c:set var="ds" value="disabled='disabled'" />
					        		</c:if>
					        		<c:if test="${s.status eq 2}">
					        			  <c:set var="ds" value="disabled='disabled'" />
					        		</c:if>
					        		 
				        			<button type="button" class="btn btn-success" onclick="dispose('${s.withdrawPackageId}')" ${ds }>
						                   <i class="glyphicon glyphicon-pencil"></i>&nbsp;已处理
					          	    </button>
					          	    <button type="button"  class="btn btn-warning" onclick="openWindow('${s.withdrawPackageId}')" ${ds }>
						                   <i class="glyphicon glyphicon-pencil"></i>&nbsp;驳回
					          	    </button>
				        		</td>
				        	</tr>
				        </tbody>
			        </c:forEach>
				</table> 
				
				<%@include file="/common/page.jsp" %>
            </div>
        </div>
        
      	   <div class="modal fade" id="openMessage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content" style="width: 402px;height: 302px;">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">驳回处理</h4>
			      </div>
			       <div class="modal-body" style="max-height: 550px;overflow-y: auto">
				  <div class="tab-content">
						 <div id="user_information" class="tab-pane active">
							<form>
							<div class="row">
							 <label class="col-md-3 control-label" >驳回理由：</label>
							 </div>
								<div class="row">
									<div class="form-group col-xs-12">
										<textarea class="form-control"  maxlength="200" id="messageText" style="resize: none;" rows="5"></textarea>
									</div>
								</div>
							</form>
						</div>
			      </div>  
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			        <button type="button" class="btn btn-default" onclick="reject()">确认</button>
			      </div>
			    </div>  
			  </div>
			  </div>
			  
			  <div id="pay"></div>
			  
	   </div>
      	  
      	  
      	  
        <script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
        <script src="${ctx }/resources/assets/scripts.js"></script>
        <script src="${ctx }/resources/assets/DT_bootstrap.js"></script> 
 
<script type="text/javascript">
		
            var ids = [];//多选时记录id
		  	var currentPage = '${page.currentPage}';
		 	var pageSize = '${page.pageSize}';	
		 			 	
		 	
		   	$(function(){
		   		$('#selectAll').on('click', selectAll);
		   		$("#bh").on("click",dispose);	
		   		
		   		

		   	});
		   	
		   	//全选
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
		   	
			function checkSelect() {
				ids = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						ids.push($(this).parent().parent().parent().attr('id'));
					}
				});
			}
		   	
		   	function dispose(id){
		   		var pattern=/^[0-9]*[1-9][0-9]*$/;
		   		checkSelect();
		   		if (ids.length == 0 && !pattern.test(id)) {
					swal("至少选择一条记录！"); 
					return;
				}
		   		swal({
					title : "处理确认",
					text : "确认已处理该提现申请？",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#337ab7",
					confirmButtonText : "确认",
					cancelButtonText : "取消",
					closeOnConfirm : true,
					closeOnCancel : true
				}, function(isConfirm) {
					if(pattern.test(id)){
						ids = [];
						ids.push(id);
					}
					//console.log(ids);
					 if (isConfirm) {
						//location.href = "${ctx}/userwithdrawpackage/dispose?ids="+ids;
						 $.ajax({
								type: "GET",
								url:"${ctx}/userwithdrawpackage/dispose?ids="+ids,
							 	//data:ids,
								error: function(request) {
									console.log("error"+request);
								},
								success: function(data) {
									console.log(data);
									$("#pay").html(data.sHtmlText);
									/* if(data.code=="SUCCEED"){
										var url = "${ctx}/member/list?currentPage=1&recommendedCode="+${user.id};
										location.href = url;
									}else{
										swal("提交失败");
									} */
								}
							});
					} 
					 ids = [];
				});
		   	}
		   	
		   	/**
			 *查询
			 */
			function queryList() {
		   		
				var url="${ctx}/userwithdrawpackage/list?currentPage="+currentPage+"&pageSize="+pageSize+"&status="+$('#status').val()+"&condition="+$('#condition').val();
				location.href = url;
			}
		   	
		   	
			function goPage(num, size) {
				currentPage = num;
				pageSize = size;
				queryList();
			} 
			
			
			function reject(){
			 	if($('#messageText').val()==''){
					swal("请填写驳回理由,不多于200个汉字.");
					return ; 
				}
			 	var url="${ctx}/userwithdrawpackage/reject?id="+packageid+"&reason="+$('#messageText').val();
				location.href = url;
			}
			
			var packageid;
			function openWindow(id){
				packageid = id;
				  $('#openMessage').modal({
		   			keyboard : false,
		   			show : true
		   		});  
			}
</script>
    </body>
</html>
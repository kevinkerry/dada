<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提现列表</title>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
		      <input class="form-control" id="beginTime" type="datetime-local" />
			  <label>至</label>
			  <input class="form-control" id="endTime" type="datetime-local" />
		      <button type="button" onclick="download()" class="btn btn-default">导出报表</button>  
		</div>
		<div class="form-group form-inline">
			<c:if test="${page.params.status == null}">
				<c:set var="sn" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == 0}">
				<c:set var="s0" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == 1}">
				<c:set var="s1" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == 2}">
				<c:set var="s2" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.status == -1}">
				<c:set var="s3" value="selected='selected'" />
			</c:if>
			<select class="form-control" id="selectStatus" style="width: 150px;" onchange="searchs()">
		          <option value="" ${sn}>--全部--</option>
		          <option value="0" ${s0}>未处理</option>
		          <option value="1" ${s1}>提现中</option>
		          <option value="2" ${s2}>处理完成</option>
		          <option value="-1" ${s3}>驳回</option>
		    </select>
		    <input class="form-control col-md-offset-0" id="condition" name="condition" type="search" value="${page.params.condition}"
				placeholder="用户ID、昵称、提现账号" />
			  <button id="search" type="button" class="btn btn-default">搜索</button>
			  <button type="button" class="btn btn-info" onclick="passWithdraw()"><i class="glyphicon glyphicon-pencil"></i>&nbsp;批量提现</button>
			  <button type="button" class="btn btn-info" onclick="messageWindow(-1)"><i class="glyphicon glyphicon-pencil"></i>&nbsp;批量驳回</button>
			  <input class="form-control col-md-offset-0" id="moneyCount" style="width: 100px" value="0.00" readonly="readonly"/>
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectAll" /></th>
					<th>ID</th>
					<th>昵称</th>
					<th>
						<a href="#" style="text-decoration: none" onclick="yhpx(this)" id="yhtag">用户ID</a>
					</th>
					<th>
					    <a href="#" style="text-decoration: none" onclick="sqpx(this)" id="sqtag">申请时间</a>
					</th>
					<th>处理时间</th>
					<th>账户余额</th>
					<th>提现金额</th>
					<th>提现账号</th>
					<th>真实姓名</th>
					<th>状态</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="w" varStatus="status">
				<tbody id="${w.id}">
					<tr>
						<td width="30px;">
					        	   <c:if test="${w.status eq 0}">
				        				<input type="checkbox" id="prev${w.id}" value="${w.money}" />
				        		   </c:if>
					    </td>
						<td>${w.id}</td>
						<td>
							<a href="#" style="text-decoration: none" onclick="openRecord(${w.user.id},1,7)">
								${w.user.nickname}
							</a>
						</td>
						<td>${w.user.id}</td>
						<td>
							<c:set target="${myDate}" property="time" value="${w.createTime}" /> 
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}"  />
						</td>
						<td>
							<c:if test="${w.status!=0}">
								<c:set target="${myDate}" property="time" value="${w.updateTime}" /> 
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}"  />
							</c:if>
						</td>
						<td>${w.balance}</td>
						<td>${w.money}</td> 
						<td>${w.alipay.alipay}</td>
						<td>${w.alipay.realName}</td>
						<td>
							<c:if test="${w.status==0}">
							   <button type="button" class="btn btn-info" onclick="reset(${w.id})"><i class="glyphicon glyphicon-pencil"></i>&nbsp;处理提现</button>
							   <button type="button" class="btn btn-info" onclick="messageWindow(${w.id})"><i class="glyphicon glyphicon-pencil"></i>&nbsp;驳回</button>
							</c:if>
							<c:if test="${w.status==1}">
								提现中
							</c:if>
							<c:if test="${w.status==2}">
								处理完成
							</c:if>
							<c:if test="${w.status==-1}">
							   <a href="#" style="text-decoration: none" onclick="openReason('${w.note}')">已驳回 </a>
								
							</c:if>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		 <%-- <div>${alipay}</div> --%>
		<%@include file="/common/page.jsp"%>
		
		<!-- 新增 开始 -->
		<div class="modal fade" id="messageWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 400px;" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myAdd">选择驳回原因</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-8">
									<div>
									  <input  type="radio" id="radio1" name="situation" value="1" /><label for="radio1">未实名认证</label></br >
									  <input  type="radio" id="radio2" name="situation" value="2" /><label for="radio2">账户不存在或设置隐私保护</label></br >
									  <input  type="radio" id="radio3" name="situation" value="3" /><label for="radio3">账号与真实姓名不符</label></br >
									  <input  type="radio" id="radio4" name="situation" value="4" /><label for="radio4">账号或真实姓名错误</label></br >
									  <input  type="radio" id="radio5" name="situation" value="5" /><label for="radio5">多账号使用同一个支付宝提现</label></br >
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick="sendBack()" >确定</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div> 
		<!-- 新增 结束 -->
		<!-- 提现记录 开始 -->
		<div class="modal fade" id="recordWindow" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="height: 95%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">充值提现记录</h4>
					</div>
					<div class="modal-body" style="max-height: 90%; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12">
									<label>充值记录 累计充值:<h id="countPay"></h></label>
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>订单号</th>
												<th>流水号</th>
												<th>充值金额</th>
												<th>充值渠道</th>
												<th>充值时间</th>
											</tr>
										</thead>
										<tbody id="payBody">
										</tbody>
									</table>
									<div id="pg2"></div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
								   <label>提现记录 累计提现:<h id="sumWithdraw"></h></label>
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>昵称</th>
												<th>申请时间</th>
												<th>处理时间</th>
												<th>提现金额</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody id="recordBody">
										</tbody>
									</table>
									<div id="pg"></div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 提现记录 结束 -->
		 <!-- 驳回详情 开始 -->
		<div class="modal fade" id="reasonWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">驳回详情</h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12">
									<p class="form-control-static" id="title"></p>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 驳回详情 结束 -->
	</div>
	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script src="${ctx }/resources/js/utils/strUtil.js"></script>
	<script src="${ctx }/resources/js/utils/ajaxPage.js"></script>
	<script src="${ctx }/resources/js/utils/ajaxPage2.js"></script>
	<script type="text/javascript">
	//定义全局变量
	var selectedUserId = "";
	var ids = [];//多选时记录id
	
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';
			
			var field='${page.params.field}';
			var sort='${page.params.sort}';
			
			var yh="";
			var sq="";
			
			$(function(){
				$("#beginTime").val("2016-06-01T00:00");
				$("#endTime").val(new Date().format("yyyy-MM-ddThh:mm"));
				
				$("#search").on("click", searchs);
				$('#selectAll').on('click', selectAll);
				
				var result = '${result}';	
				if(result!=''){
					swal(result);
				}
				
				 $("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input[type="checkbox"]').on('click', function(e) {
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedUserId = id;
							//提现统计
							var inputCheckbox = $(this).find('input[type="checkbox"]');
							var moneyCount = $("#moneyCount");
							var d1 = parseFloat(inputCheckbox.context.value);
							var d2 = parseFloat(moneyCount.val());
							moneyCount.val(parseFloat(d1+d2).toFixed(2));   
							// console.log(inputCheckbox.context.value);
							 
						}else {
							selectedUserId = "";
							//提现统计
							var inputCheckbox = $(this).find('input[type="checkbox"]');
							var moneyCount = $("#moneyCount");
							var d1 = parseFloat(inputCheckbox.context.value);
							var d2 = parseFloat(moneyCount.val());
							var d3 = d2-d1;
							if(d3<0.00){
							   d3 = 0.00;
							}
							moneyCount.val(parseFloat(d3).toFixed(2));
						}
						e.stopPropagation();
					});
				}); 
				
			 	$("tbody").on("click",function(){
					//设置选择或未选中
					var inputCheckbox = $(this).find('input[type="checkbox"]');
					var checked = inputCheckbox.prop('checked');
					if(checked) {
						inputCheckbox.prop('checked', false);
						selectedUserId = "";
						
						//提现统计
						var inputCheckbox = $(this).find('input[type="checkbox"]');
						var moneyCount = $("#moneyCount");
						var d1 = parseFloat(inputCheckbox[0].value);
						var d2 = parseFloat(moneyCount.val());
						var d3 = d2-d1;
						if(d3<0.00){
						   d3 = 0.00;
						}
						moneyCount.val(parseFloat(d3).toFixed(2));
					}else {
						inputCheckbox.prop('checked', true);
						selectedUserId = $(this).attr('id');
						
						//提现统计
					  	var moneyCount = $("#moneyCount");
						var d1 = parseFloat(inputCheckbox[0].value);
						var d2 = parseFloat(moneyCount.val());
						moneyCount.val(parseFloat(d1+d2).toFixed(2));    
						//console.log(inputCheckbox[0].value);
					}
					
					
				}); 
				
				 if(field=="u.id"){
					 var str = "用户ID";
					if(sort=="desc"){
						$("#yhtag").text(str+"▼");
						  yh = "desc";
					}
					if(sort=="asc"){
						$("#yhtag").text(str+"▲");
						  yh = "asc";
					}
				 }
				 
				 
				 if(field=="w.create_time"){
					 var str = "申请时间";
					if(sort=="desc"){
						$("#sqtag").text(str+"▼");
						  sq = "desc";
					}
					if(sort=="asc"){
						$("#sqtag").text(str+"▲");
						  sq = "asc";
					}
				 }
				 
				 $('#condition').bind('keypress',function(event){
			            if(event.keyCode == "13")    
			            {
			            	searchs();
			            }
			        });
				 
			});
			
			
			function searchs(){
				currentPage = 1;
				queryList();
			}
			
			function yhpx(value){
				//console.log("11111111111111");
			 	field = "u.id";
				var str = "用户ID";
				if(yh==""){
					value.text = str+"▼";
					yh="desc"; 
					sort = "desc";
					queryList();
					return;
				}
				if(yh=="desc"){
					value.text = str+"▲";
					yh="asc"; 
					sort = "asc";
					queryList();
					return;
				}
				if(yh=="asc"){
					value.text = str;
					yh="";  
					sort = "";
					field ="";
					queryList();
					return;
				}  
			}
			
			function sqpx(value){
				field = "w.create_time";
				var str = "申请时间";
				if(sq==""){
					value.text = str+"▼";
					sq="desc"; 
					sort = "desc";
					queryList();
					return;
				}
				if(sq=="desc"){
					value.text = str+"▲";
					sq="asc"; 
					sort = "asc";
					queryList();
					return;
				}
				if(sq=="asc"){
					value.text = str;
					sq="";  
					sort = "";
					field ="";
					queryList();
					return;
				}  
			}
			
		 	//全选
			function selectAll(event) {
				var target = $(event.target);
				if(target.prop('checked')) {
					$('tbody').find('input[type="checkbox"]').each(function() {
						if(!$(this).prop('checked')){
							//提现处理
							var moneyCount = $("#moneyCount");
							var d1 = parseFloat($(this)[0].value);
							var d2 = parseFloat(moneyCount.val());
							moneyCount.val(parseFloat(d1+d2).toFixed(2));   
						}
						
						$(this).prop('checked', true);
						
					});
				}else {
					$('tbody').find('input[type="checkbox"]').each(function() {
						$(this).prop('checked', false);
						
						//提现处理
						var moneyCount = $("#moneyCount");
						var d1 = parseFloat($(this)[0].value);
						var d2 = parseFloat(moneyCount.val());
						var d3 = d2-d1;
						if(d3<0.00){
						   d3 = 0.00;
						}
						moneyCount.val(parseFloat(d3).toFixed(2));
					});
				}
				
				checkSelect();
			}
			
			function checkSelect() {
				ids = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						ids.push($(this).parent().parent().parent().attr('id'));
					}
				});
			}
		 	
			
			
		 	
			/**
			 *查询
			 */
			function queryList() {
				var condition = $("#condition").val();
				var selectStatus = $("#selectStatus").val();
				var url="${ctx}/withdraw/list?currentPage="+currentPage+"&pageSize="+pageSize;
				if(!isEmpty(condition)){
					url = url+"&condition="+condition;
				}
				if(!isEmpty(selectStatus)){
					url = url+"&status="+selectStatus;
				}
				if(!isEmpty(field)){
					url = url+"&field="+field+"&sort="+sort;
				}
				location.href = url;
			}

				function goPage(num, size) {
					currentPage = num;
					pageSize = size;
					queryList();
				}
				
				function goPage2(num) {
					currentPage = num;
					queryList();
				}
				
				function passWithdraw(){
			   		checkSelect();
			   		if (ids.length == 0) {
						swal("至少选择一条记录！"); 
						return;
					}
				//	 console.log(ids);
				  var condition = $("#condition").val();
				  var selectStatus = $("#selectStatus").val();
				  var url="${ctx}/withdraw/passWithdraw?wid="+ids+"&currentPage="+currentPage+"&pageSize="+pageSize;
				  
					swal({
						title : "处理确认",
						text : "确认处理该提现申请？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : false
					}, function(isConfirm) {
						if(isConfirm){
							if(!isEmpty(field)){
								url = url+"&field="+field+"&sort="+sort;
							}
							if(!isEmpty(condition)){
								url = url+"&condition="+condition;
							}
						  if(!isEmpty(selectStatus)){
								url = url+"&status="+selectStatus;
							}
							location.href = url;
						}
					});   
				}
				
				function reset(wid){
					var condition = $("#condition").val();
					var selectStatus = $("#selectStatus").val();
					var url="${ctx}/withdraw/reset?wid="+wid+"&currentPage="+currentPage+"&pageSize="+pageSize;
					
					swal({
						title : "处理确认",
						text : "确认处理该提现申请？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : false
					}, function(isConfirm) {
						if(isConfirm){
							if(!isEmpty(field)){
								url = url+"&field="+field+"&sort="+sort;
							}
							if(!isEmpty(condition)){
								url = url+"&condition="+condition;
							}
							if(!isEmpty(selectStatus)){
								url = url+"&status="+selectStatus;
							}
							location.href = url;
						}
					});
				}
				
				var withdrawId = null;
				function messageWindow(wid){
					withdrawId = wid;
					$('#messageWindow').modal("show");

				}
				
				function sendBack(){
					var situation = $("[name='situation']").filter(":checked"); 
					if(situation.attr("value")==null){
						swal("必须选择一种原因!");
						return;
					}
					var condition = $("#condition").val();
					var selectStatus = $("#selectStatus").val();
					var url="";
					if(withdrawId!=-1){
					     url="${ctx}/withdraw/sendBack?wid="+withdrawId+"&code="+situation.attr("value")+"&currentPage="+currentPage+"&pageSize="+pageSize;
					}else  {
						checkSelect();
				   		if (ids.length == 0) {
							swal("至少选择一条记录！"); 
							return;
						}
				   	  url="${ctx}/withdraw/batchReject?wid="+ids+"&code="+situation.attr("value")+"&currentPage="+currentPage+"&pageSize="+pageSize;
				   		//console.log(ids);
					}
					
					if(!isEmpty(condition)){
						url = url+"&condition="+condition;
					}
					if(!isEmpty(selectStatus)){
						url = url+"&status="+selectStatus;
					}
					if(!isEmpty(field)){
						url = url+"&field="+field+"&sort="+sort;
					}
					location.href = url; 
				}
				
				var userId;
				function openRecord(userId,currentPage,pageSize){
					this.userId = userId;
					$('#recordWindow').modal("show");
					getRecordList(userId,currentPage,pageSize);
				    getPayList(userId,currentPage,pageSize);
				}
				
				function getRecordList(userId,currentPage,pageSize){
					$("#recordBody").empty();
					$("#pg").empty();
				    $.ajax({
						   type: "GET",
						   url: "${ctx}/withdraw/getUserRecord?userId="+userId+"&currentPage="+currentPage+"&pageSize="+pageSize,
						   success: function(data){
						   //	console.log(data.resultMap);
						    var result = data.resultMap.record;
						    var sumWithdraw = data.resultMap.sumWithdraw;
						    $("#sumWithdraw").text(sumWithdraw);
						    var tr = "";
						    var td = "";
						    for (var x = 0; x < result.result.length; x++) {
						    	if(x!=0){
						    	   td = "";
						    	}
						    	td +="<td>"+result.result[x].user.nickname+"</td>";
						    	td +="<td>"+timeToStr(result.result[x].createTime)+"</td>";
						    	td +="<td>"+ (result.result[x].status!=0?timeToStr(result.result[x].updateTime):"")+"</td>";
						    	td +="<td>"+ result.result[x].money+"</td>";
						    	if(result.result[x].status==0){
						    		td +="<td>未处理</td>";
						    	}
						    	if(result.result[x].status==1){
						    		td +="<td>提现中</td>";
						    	}
						    	if(result.result[x].status==2){
						    		td +="<td>处理完成</td>";
						    	}
						    	if(result.result[x].status==-1){
						    		td +="<td>驳回</td>";
						    	}
						    	tr +="<tr>"+td+"</tr>";
							}
						    $("#recordBody").html(tr);
						  	$("#pg").html(ajaxPage(result.pageSize,result.currentPage,result.totalPages,result.totalCount));
						   }
					 });
				}
				
				function getPayList(userId,currentPage,pageSize){
					$("#payBody").empty();
					$("#pg2").empty();
					$.ajax({
						   type: "GET",
						   url: "${ctx}/orders/getOrderList?userId="+userId+"&currentPage="+currentPage+"&pageSize="+pageSize,
						   success: function(data){
						  
						    var result = data.resultMap.order;
						    var countPay = data.resultMap.countPay;
						    
						    //console.log(countPay);
						    $("#countPay").text(countPay);
						    var tr = "";
						    var td = "";
						    for (var x = 0; x < result.result.length; x++) {
						    	if(x!=0){
						    	   td = "";
						    	}
						    	td +="<td>"+result.result[x].orderNumber+"</td>";
						    	if(result.result[x].orderFlow != null){
						    		td +="<td>"+result.result[x].orderFlow.tradeNo+"</td>";
						    	}else {
						    		td +="<td></td>";
								}
						    	
						    	td +="<td>"+result.result[x].payAmount+"</td>";
						    	if(result.result[x].orderFlow != null){
						    		if(result.result[x].orderFlow.payType==1){
							    		td +="<td>支付宝</td>";
							    	}
							    	if(result.result[x].orderFlow.payType==2){
							    		td +="<td>微信</td>";
							    	}
						    	}else   {
						    		td +="<td></td>";
								}
						    	td +="<td>"+timeToStr(result.result[x].createTime)+"</td>";
						    	tr +="<tr>"+td+"</tr>";
							}
						    $("#payBody").html(tr);
						  	$("#pg2").html(ajaxPage2(result.pageSize,result.currentPage,result.totalPages,result.totalCount)); 
						   }
					 });
				}
				
				function ajaxGoPage(num, size){
					getRecordList(userId,num,size);
				}
				
				function ajaxGoPageSkip(num){
					getRecordList(userId,num,7);
				}
				
				function ajaxGoPage2(num, size){
					getPayList(userId,num,size);
				}
				
				function ajaxGoPage2Skip(num){
					getPayList(userId,num,7);
				}
				
				
				 function openReason(str){
					  $('#reasonWindow').modal("show");
					  $("#title").text(str);
			     }
				
				 
				 function download(){
					 if($("#beginTime").val()!=""){
						 if($("#beginTime").val().length!=16){
							 swal("开始时间不完整!");
							 return;
						 }
					 }else{
						 swal("开始时间不能为空!");
						 return;
					 }
					 
					 if($("#endTime").val()!=""){
						 if($("#endTime").val().length!=16){
							 swal("结束时间不完整!");
							 return;
						 }
					 }else{
						 swal("结束时间不能为空!");
						 return;
					 }
					 
					 var url="${ctx}/withdraw/exportReport?beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
					 location.href = url; 
				 }
				 
			</script>
</body>
</html>
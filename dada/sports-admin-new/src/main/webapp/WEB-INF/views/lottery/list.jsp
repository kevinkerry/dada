<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>彩票列表</title>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
			 <button type="button" class="btn btn-primary" onclick="add()"><i class="glyphicon glyphicon-plus"></i>&nbsp;规则设置</button> 
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>期号</th>
					<th>开奖日期</th>
					<th>开奖号码</th>
					<th>总投注</th>
					<th>一等奖</th>
					<th>二等奖</th>
					<th>三等奖</th>
					<th>四等奖</th>
					<th>五等奖</th>
					<th>总投注金豆</th>
					<th>总奖金</th>
					<th>收益核算</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>${u.lotteryNum}</td>
						<td>
							<c:set target="${myDate}" property="time" value="${u.lotteryTime}" /> 
						 	<fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" type="both" />
						</td>
						<td>${u.winNumber}</td>
						<td>${u.sumCount}</td>
						<td>${u.level1}</td>
						<td>${u.level2}</td>
						<td>${u.level3}</td>
						<td>${u.level4}</td>
						<td>${u.level5}</td>
						<td>${u.countGoldBean}</td>
						<td>${u.sumBonus}</td>
						<td>0</td>
						<td>
							<c:if test="${u.settle==0}">
								<button type="button" class="btn btn-default" onclick="lottery(${u.id},${u.lotteryNum})" >开奖</button>
							</c:if>
							 <button type="button" class="btn btn-default" onclick="info(${u.id})" >详情</button>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<%@include file="/common/page.jsp"%>
		
		
		<!-- 设置开奖 开始 -->
		<div class="modal fade" id="openSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="text-center" id="titleSet"></h4>
					</div>
					<div class="modal-body" style="max-height: 550px; overflow-y: auto">
						<div class="tab-content">
							<div class="row">
								<div class="form-group col-xs-12 form-inline text-center">
									  <input class="form-control text-center" id="num1" type="number" oninput="if(value.length>1)value=value.slice(0,1)" style="width: 72px; height: 60px;font-size:20px;font-weight:900;"/>&nbsp;&nbsp;&nbsp;&nbsp;
									  <input class="form-control text-center" id="num2" type="number" oninput="if(value.length>1)value=value.slice(0,1)" style="width: 72px; height: 60px;font-size:20px;font-weight:900;"/>&nbsp;&nbsp;&nbsp;&nbsp;
									  <input class="form-control text-center" id="num3" type="number" oninput="if(value.length>1)value=value.slice(0,1)" style="width: 72px; height: 60px;font-size:20px;font-weight:900;"/>&nbsp;&nbsp;&nbsp;&nbsp;
									  <input class="form-control text-center" id="num4" type="number" oninput="if(value.length>1)value=value.slice(0,1)" style="width: 72px; height: 60px;font-size:20px;font-weight:900;"/>&nbsp;&nbsp;&nbsp;&nbsp;
									  <input class="form-control text-center" id="num5" type="number" oninput="if(value.length>1)value=value.slice(0,1)" style="width: 72px; height: 60px;font-size:20px;font-weight:900;"/>
								</div>
							</div>
						    <div class="row">
						    	<label class="col-xs-12 control-label" >中奖通知：</label>
								<div class="form-group col-xs-12">
									<div>
									  <textarea class="form-control" type="text" id="contentSet" style="margin-top: 0px; margin-bottom: 0px; height: 70px;" ></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-12 form-inline">
									<label>发送时间：</label>
									<input class="form-control" id="tssj" type="datetime-local" />
								</div>
							</div>
						</div>
						<div class="text-center">
							<button type="button" class="btn btn-default" onclick="lotterySubmit()" >确认</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div> 
		<!-- 设置开奖  结束 -->
	</div>

	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script type="text/javascript">
        
        var currentPage = '${page.currentPage}';
		var pageSize = '${page.pageSize}';	
		 
        
		$(function(){
			var result = '${result}';	
			if(result!=''){
				swal(result);
			}
			 
		});
		
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/lottery/list?currentPage="+currentPage+"&pageSize="+pageSize;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
		var lotteryId = 0;
		
		function lottery(id,lotteryNum){
			lotteryId = id;
			$('#titleSet').text("第"+lotteryNum+"期开奖结果");
			$('#openSet').modal("show");
		}
		
		function lotterySubmit(){
			if($('#num1').val().length==0){
				swal("请输入第一个开奖号码");
				return;
			}else{
		    	if(parseInt($("#num1").val())<0){
		    		swal("开奖号码不能为负数");
		   		    return;
		    	}
		    }
			if($('#num2').val().length==0){
				swal("请输入第二个开奖号码");
				return;
			}else{
		    	if(parseInt($("#num2").val())<0){
		    		swal("开奖号码不能为负数");
		   		    return;
		    	}
		    }
			if($('#num3').val().length==0){
				swal("请输入第三个开奖号码");
				return;
			}else{
		    	if(parseInt($("#num3").val())<0){
		    		swal("开奖号码不能为负数");
		   		    return;
		    	}
		    }
			if($('#num4').val().length==0){
				swal("请输入第四个开奖号码");
				return;
			}else{
		    	if(parseInt($("#num4").val())<0){
		    		swal("开奖号码不能为负数");
		   		    return;
		    	}
		    }
			if($('#num5').val().length==0){
				swal("请输入第五个开奖号码");
				return;
			}else{
		    	if(parseInt($("#num5").val())<0){
		    		swal("开奖号码不能为负数");
		   		    return;
		    	}
		    }
			var winNum = $('#num1').val()+$('#num2').val()+$('#num3').val()+$('#num4').val()+$('#num5').val();
			 
			var contentSet = $("#contentSet").val();
			var tssj = $("#tssj").val();
			
			if(contentSet.length!=0){
				 if(tssj.length!=16){
					 swal("请输入正确的推送时间");
			   		 return;
				 }
			}
			
			swal({
				title : "开奖确认",
				text : "确定以"+winNum+"开奖吗？",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#337ab7",
				confirmButtonText : "确认",
				cancelButtonText : "取消",
				closeOnConfirm : false
			}, function(isConfirm) {
				if(isConfirm){
					 var url="${ctx}/lottery/runLottery?lotteryId="+lotteryId+"&winNumber="+winNum+"&content="+contentSet+"&pushTime="+tssj;
					location.href = url; 
				}
			});
			
		}
		
		function add(){
			var url="${ctx}/lottery/gotoPage?page=addlotterybase";
			location.href = url;
		}
	 
		function info(id){
			var url="${ctx}/userlottery/list?lotteryId="+id;
			location.href = url;
		}
	   
    </script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
		    <c:if test="${page.params.dateType == 1}">
				<c:set var="s1" value="selected='selected'" />
			</c:if>
			<c:if test="${page.params.dateType == 2}">
				<c:set var="s2" value="selected='selected'" />
			</c:if>
			<select class="form-control" id="selectStatus" >
		          <option value="1" ${s1}>注册时间</option>
		          <option value="2" ${s2}>最后活跃</option>
		    </select>
		    <input class="form-control" id="beginTime" type="datetime-local" />
		    <label>至</label>
		    <input class="form-control" id="endTime" type="datetime-local" />
		     
			<input class="form-control col-md-offset-0" id="condition" type="search" value="${page.params.condition}" placeholder="渠道号、昵称、手机号进行搜索" />
			<button id="search" type="button" class="btn btn-default">搜索</button>
			<!-- <button type="button" class="btn btn-primary" id="addUser"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
				<button type="button" class="btn btn-info" id="updateUser"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
				<button type="button" class="btn btn-danger" id="deleteUser"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button> -->
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>用户编号</th>
					<th>用户名</th>
					<th>昵称</th>
					<th>性别</th>
					<th><a href="#" style="text-decoration: none"
						onclick="ye(this)" id="yetag">账户余额（元）</a><input type="hidden"
						id="ye" value="" /></th>
					<th><a href="#" style="text-decoration: none"
						onclick="sy(this)" id="sytag">累计收益（元）</a><input type="hidden"
						id="sy" value="" /></th>
					<th><a href="#" style="text-decoration: none"
						onclick="zc(this)" id="zctag">注册时间</a><input type="hidden" id="zc"
						value="" /></th>
					<th><a href="#" style="text-decoration: none"
						onclick="zh(this)" id="zhtag">最后活跃时间</a> <input type="hidden"
						id="zh" value="" /></th>
					<th>渠道号</th>
					<th>设备ID数</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody id="${u.id}">
					<tr>
						<td>${u.id}</td>
						<td>${u.usercode}</td>
						<td>${u.username}</td>
						<td>${u.nickname}</td>
						<td><c:choose>
								<c:when test="${u.sex==1}">
					        		男
					        	</c:when>
								<c:when test="${u.sex==0}">
					        		女
					        	</c:when>
							</c:choose></td>
						<td>${u.totalAsset}</td>
						<td>${u.income}</td>
						<td><c:if test="${u.registerTime != null}">
								<c:set target="${myDate}" property="time"
									value="${u.registerTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" />
							</c:if></td>
						<td><c:if test="${u.updateTime!=null}">
								<c:set target="${myDate}" property="time"
									value="${u.updateTime}" />
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}"
									type="both" />
							</c:if></td>
					    <td>${u.channel}</td>
						<td>${u.clientId}<a class="btn btn-info" onclick="toMonitoring('${u.clientId}')">${u.clientIdNum}</a></td>
						<td>
							<button type="button" class="btn btn-info"
								onclick="userInfoWindow(${u.id})">
								<i class="glyphicon glyphicon-pencil"></i>&nbsp;详细资料
							</button>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		 
		<div class="modal fade" id="userInfoWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-content" style="height: 100%;">
				<div
					style="padding-top: 10px; padding-bottom: 10px; padding-left: 10px; padding-right: 10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul class="nav nav-tabs" id="myTab">
						<li><a href="#userInfo">用户详情</a></li>
						<li><a href="#sportData" onclick="yd()">运动数据</a></li>
						<li><a href="#moneyData" onclick="lc()">理财数据</a></li>
						<li><a href="#activityData" onclick="hdList()">活动数据</a></li>
					</ul>
				</div>
				<div class="modal-body" style="max-height: 90%; overflow-y: auto;">
					<div class="tab-content" style="height: 79%;">
						<div class="tab-pane" id="userInfo">
							<div class="row">
								<div class="form-group col-xs-4">
									<table class="table table-bordered table-hover">
										<tr>
											<td><label>ID：</label>
											<h id="userId"></h></td>
											<td rowspan="4"><img id="myImg"
												src="../resources/images/default.png" width="100px;"
												height="100px;" class="user_profile_current_head_img" /></td>
										</tr>
										<tr>
											<td><label>昵称：</label>
											<h id="nickname"></h></td>
										</tr>
										<tr>
											<td><label>性别：</label>
											<h id="sex"></h></td>
										</tr>
										<tr>
											<td><label>最后活跃时间：</label>
											<h id="updateTime"></h></td>
										</tr>
										<tr>
											<td><label>生日：</label>
											<h id="birthday"></h></td>
											<td><label>年龄：</label>
											<h id="age"></h></td>
										</tr>
										<tr>
											<td><label>体重：</label>
											<h id="weight"></h></td>
											<td><label>身高：</label>
											<h id="height"></h></td>
										</tr>
										<tr>
											<td><label>手机：</label>
											<h id="mobile"></h></td>
											<td><label>微信：</label></td>
										</tr>
										<tr>
											<td><label>所在地区：</label>
											<h id="city"></h></td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><label>设备机型：</label>
											<h id="phoneModel"></h></td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><label>设备号ID：</label><h id="clientId"></h></td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><label>注册渠道：</label><h id="channel"></h></td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><label>注册时间：</label>
											<h id="registerTime"></h></td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><label>App版本：</label>3.2.3</td>
											<td></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="sportData">
								<div class="row">
									<div class="form-group form-inline" style="padding-left: 10px;">
										<label class="col-md-offset-0">今日计步：<h id="todayStep"></h></label> 
										<label class="col-md-offset-1">今日跑量：<h id="todayDistance"></h>km</label> 
										<label class="col-md-offset-1">今日累计跑步时长：<h id="todayDistanceTime"></h></label>
									</div>
									<div class="form-group form-inline" style="padding-left: 10px;">
										<label class="col-md-offset-0">累计步数：<h id="countStep"></h>步</label> 
										<label class="col-md-offset-1">累计跑步：<h id="countDistance"></h>km</label> 
										<label class="col-md-offset-1">累计跑步时长：<h id="countDistanceTime"></h>分</label>
									</div>
									<div class="form-group form-inline" style="padding-left: 10px;">
										<label class="col-md-offset-0">5000步以下：<h id="step5000DN"></h>次</label> 
										<label class="col-md-offset-1">5000-9999步：<h id="step5000_9999"></h>次</label> 
										<label class="col-md-offset-1">10000-14999步：<h id="step10000_14999"></h>次</label>
										<label class="col-md-offset-1">15000-19999步：<h id="step15000_19999"></h>次</label>
										<label class="col-md-offset-1">20000步+：<h id="step20000UP"></h>次</label>
									</div>
									<div class="form-group form-inline" style="padding-left: 10px;">
										<label class="col-md-offset-0">0-3km：<h id="distance0_3KM"></h>次</label> 
										<label class="col-md-offset-1">3-5km：<h id="distance3_5KM"></h>次</label> 
										<label class="col-md-offset-1">5-8km：<h id="distance5_8KM"></h>次</label>
										<label class="col-md-offset-1">8-10km：<h id="distance8_10KM"></h>次</label>
										<label class="col-md-offset-1">10km+：<h id="distance10KMUP"></h>次</label>
									</div>
									<div class="form-group form-inline" style="padding-left: 10px;">
										<label class="col-md-offset-0">室内跑步：<h id="snCountRunning"></h>次</label> 
										<label class="col-md-offset-1">室外跑步：<h id="swCountRunning"></h>次</label> 
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>详细数据</label>
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>时间</th>
													<th>步数</th>
													<th>跑步距离(km)</th>
													<th>跑步时长(分)</th>
													<th>平均速度(km/h)</th>
													<th>跑步记录</th>
													<th>数据类型</th>
												</tr>
											</thead>
											<tbody id="sportBody">
											</tbody>
										</table>
										<div id="pg2"></div>
									</div>
								</div>
						</div>
						<div class="tab-pane" id="moneyData">
							<div class="row">
								<div class="form-group form-inline" style="padding-left: 10px;">
									<label class="col-md-offset-0">当前基础利率：<h
											id="annualYieldZ"></h>%
									</label> <label class="col-md-offset-1">总资产：<h id="totalAssetZ"></h>元
									</label> <label class="col-md-offset-1">总收益：<h id="incomeZ"></h>元
									</label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									<label class="col-md-offset-0">本金余额：<h id="principalZ"></h>元
									</label> <label class="col-md-offset-1">收益余额：<h id="earningsBalanceZ"></h>元
									</label> <label class="col-md-offset-1">总体验本金：<h id="experienceMoneyZ"></h>元
									</label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									<label class="col-md-offset-0">待提现金额：<h
											id="stayWithdrawZ"></h>元
									</label> <label class="col-md-offset-1">本金上限：无上限</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>账户明细</label>
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>时间</th>
												<th>金额（元）</th>
												<th>总资产（元）</th>
												<th>结算利率</th>
												<th>交易类型</th>
											</tr>
										</thead>
										<tbody id="recordBody">
										</tbody>
									</table>
									<div id="pg"></div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="activityData">
							<div class="row">
								<div class="form-group form-inline" style="padding-left: 10px;">
									<label class="col-md-offset-0">累计参加：<h id="ljcj"></h>次
									</label> 
									<label class="col-md-offset-1">累计创收：<h id="ljcs"></h>元
									</label> 
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>时间</th>
												<th>活动名称</th>
												<th>活动分类</th>
												<th>参加类型</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="activityBody">
										</tbody>
									</table>
									<div id="pg3"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 活动明细 开始 -->
		<div class="modal fade" id="activityDetail" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-content" style="height: 100%;">
				<div
					style="padding-top: 10px; padding-bottom: 10px; padding-left: 10px; padding-right: 10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="max-height: 90%; overflow-y: auto;">
					<div class="tab-content" style="height: 79%;">
						<div class="row">
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">参加时间：<h id="cjsj"></h></label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">参加方式：申请当大腿</label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">大腿等级：<h id="dtdj"></h></label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">被抱次数：<h id="bbcs"></h>次 </label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">创收：<h id="cs"></h></label>
								</div>
						</div>
					
						<div class="row">
							<div class="form-group col-xs-12">
								<label>详细数据</label>
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>被抱时间</th>
											<th>用户ID</th>
											<th>用户昵称</th>
											<th>支付佣金(元)</th>
										</tr>
									</thead>
									<tbody id="hugThighBody">
									</tbody>
								</table>
								<div id="pg4"></div>
							</div>
						</div>
						<div class="row" id="flq">
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">参加时间：<h id="flsj"></h></label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">参加方式：抱大腿</label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">支付佣金：<h id="zfyj"></h></label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">获得福利：<h id="hdfl"></h> </label>
								</div>
								<div class="form-group form-inline" style="padding-left: 10px;">
									 <label class="col-md-offset-0">使用状态：<h id="zt"></h></label>
								</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 活动明细  结束 -->
		
	<!-- 跑量 开始 -->
	<div class="modal fade" id="runWindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: 400px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="max-height: 550px; overflow-y: auto">
					<div class="tab-content">
						<div class="row">
							<div class="form-group col-xs-10">跑量：<h id="pl"></h>km 用时：<h id="ys"></h>分</div>
							<div class="form-group col-xs-10">步数：<h id="bs"></h>步</div>
							<div class="form-group col-xs-10">平均步幅：<h id="bf"></h>米/步</div>
							<div class="form-group col-xs-10">平均速度：<h id="pjsd"></h>km/h 最快速度：<h id="zksd"></h>km/h</div>
							<div class="form-group col-xs-10">平均配速：<h id="pjps"></h>/km 最快配速:<h id="zkps"></h>/km</div>
						</div>
					</div>
					<div class="modal-footer">
		
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 跑量 结束 -->
		
		<%@include file="/common/page.jsp"%>
	</div>
	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script src="${ctx }/resources/js/utils/strUtil.js"></script>
	<script src="${ctx }/resources/js/utils/ajaxPage.js"></script>
	<script src="${ctx }/resources/js/utils/ajaxPage2.js"></script>
	<script src="${ctx }/resources/js/utils/ajaxPage3.js"></script>
	<script src="${ctx }/resources/js/utils/ajaxPage4.js"></script>
	<script type="text/javascript">
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
		 
			var field='${page.params.field}';
			var sort='${page.params.sort}';
			
	
			var beginTime = '${page.params.beginTime}';
			var endTime ='${page.params.endTime}';
			var dateType;
			
			var currentUserId=0;
			
			$(function(){
				$("#beginTime").val(beginTime);
				$("#endTime").val(endTime);
			/* 	if(time1==''){
					$("#beginTime").val("2016-01-01T00:00");
				}else{
					$("#beginTime").val(time1);
				}
				if(time2==''){
					$("#endTime").val(new Date().format("yyyy-MM-ddThh:mm"));
				}else{
					$("#endTime").val(time2);
				} */
				
				 $('#myTab a:first').tab('show');//初始化显示哪个tab 
			     $('#myTab a').click(function (e) { 
			          e.preventDefault();//阻止a链接的跳转行为 
			          $(this).tab('show');//显示当前选中的链接及关联的content 
			       })
				
				$("#search").on("click", search);
				
				//console.log(field);
				//console.log(sort);
				
				 if(field=="w.total_asset"){
					 var str = "账户余额（元）";
					if(sort=="desc"){
						$("#yetag").text(str+"▼");
						$("#ye").val("desc");
					}
					if(sort=="asc"){
						$("#yetag").text(str+"▲");
						$("#ye").val("asc");
					}
				 }
				 
				 if(field=="w.income"){
					 var str = "累计收益（元）";
						if(sort=="desc"){
							$("#sytag").text(str+"▼");
							$("#sy").val("desc");
						}
						if(sort=="asc"){
							$("#sytag").text(str+"▲");
							$("#sy").val("asc");
						}
				}
				 
				 if(field=="u.register_time"){
					 var str = "注册时间";
						if(sort=="desc"){
							$("#zctag").text(str+"▼");
							$("#zc").val("desc");
						}
						if(sort=="asc"){
							$("#zctag").text(str+"▲");
							$("#zc").val("asc");
						}
				}
				 if(field=="u.update_time"){
					 var str = "最后活跃时间";
						if(sort=="desc"){
							$("#zhtag").text(str+"▼");
							$("#zh").val("desc");
						}
						if(sort=="asc"){
							$("#zhtag").text(str+"▲");
							$("#zh").val("asc");
						}
				}
				 if(field=="u.clientIdNum"){
					 var str = "设备ID数";
						if(sort=="desc"){
							$("#gttag").text(str+"▼");
							$("#gt").val("desc");
						}
						if(sort=="asc"){
							$("#gttag").text(str+"▲");
							$("#gt").val("asc");
						}
				}
				 
				 
				 $('#condition').bind('keypress',function(event){
			            if(event.keyCode == "13")    
			            {
			            	queryList();
			            }
			        });
				 
			});
			
			
			function ye(value){
				field = "w.total_asset";
				var str = "账户余额（元）";
				var ye = $("#ye");
				if(ye.val()==""){
					value.text = str+"▼";
					ye.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(ye.val()=="desc"){
					value.text = str+"▲";
					ye.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if($("#ye").val()=="asc"){
					value.text = str;
					ye.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			function sy(value){
				field = "w.income";
				var str = "累计收益（元）";
				var sy = $("#sy");
				if(sy.val()==""){
					value.text = str+"▼";
					sy.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(sy.val()=="desc"){
					value.text = str+"▲";
					sy.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if(sy.val()=="asc"){
					value.text = str;
					sy.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			function zc(value){
				field = "u.register_time";
				var str = "注册时间";
				var zc = $("#zc");
				if(zc.val()==""){
					value.text = str+"▼";
					zc.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(zc.val()=="desc"){
					value.text = str+"▲";
					zc.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if(zc.val()=="asc"){
					value.text = str;
					zc.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			function zh(value){
				field = "u.update_time";
				var str = "最后活跃时间";
				var zh = $("#zh");
				if(zh.val()==""){
					value.text = str+"▼";
					zh.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(zh.val()=="desc"){
					value.text = str+"▲";
					zh.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if(zh.val()=="asc"){
					value.text = str;
					zh.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			function gt(value){
				field = "u.clientIdNum";
				var str = "设备ID数";
				var gt = $("#gt");
				if(gt.val()==""){
					value.text = str+"▼";
					gt.val("desc");
					sort = "desc";
					queryList();
					return;
				}
				if(gt.val()=="desc"){
					value.text = str+"▲";
					gt.val("asc");
					sort = "asc";
					queryList();
					return;
				}
				if(gt.val()=="asc"){
					value.text = str;
					gt.val("");
					sort = "";
					field ="";
					queryList();
					return;
				}
			}
			
			
			function search(){
			    currentPage = 1;
				queryList();   
			}
			
			/**
			 *查询
			 */
			function queryList() {
				var condition = $("#condition").val();
				var url="${ctx}/user/list?currentPage="+currentPage+"&pageSize="+pageSize;
				if(!isEmpty(condition)){
					url = url+"&condition="+condition;
				}
				if(!isEmpty(field)){
					url = url+"&field="+field+"&sort="+sort;
				}
				if($("#beginTime").val()!="" && $("#endTime").val()!=""){
					if($("#beginTime").val().length==16&&$("#endTime").val().length==16){
						url = url+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
					}
				}
					url = url+"&dateType="+$("#selectStatus").val();
				
				//console.log(url);
				
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
				
				
				function userInfoWindow(uid){
					if(currentUserId!=uid){
						$('#myTab a:first').tab('show');//初始化显示哪个tab 
					     $('#myTab a').click(function (e) { 
					          e.preventDefault();//阻止a链接的跳转行为 
					          $(this).tab('show');//显示当前选中的链接及关联的content 
					       })
					       
					       currentUserId = uid;
					}
					
				 	$('#userInfoWindow').modal({
						keyboard: false,
						show: true
					});  
				 	
					  $.ajax({
						   type: "GET",
						   url: "${ctx}/user/getUserById?userId="+uid,
						   success: function(data){
						   	//console.log(data.resultMap.user);
						   	var us = data.resultMap.user;
						   	$("#userId").text(us.id);
						   	$("#nickname").text(us.nickname);
						   	if(us.sex==0){$("#sex").text("女");}
						   	if(us.sex==1){$("#sex").text("男");}
						   
						   	$("#updateTime").text(timeToStr(us.updateTime));
						   	$("#birthday").text(us.birthday);
						   	$("#age").text(us.age);
						   	$("#weight").text(us.weight);
						   	$("#height").text(us.height);
						   	$("#mobile").text(us.mobile);
						   	$("#city").text((us.province!=null?us.province:"")+us.city);
						   	$("#phoneModel").text(us.phoneModel);
						   	if(us.headPortrait!=null){
						   		$("#myImg").attr('src',imgPath(us.headPortrait)); 
						   	}
						   	$("#clientId").text(us.clientId);
						    $("#registerTime").text(timeToStr(us.registerTime));
						   	$("#channel").text(us.channel);
						   }
						});  
				}
				
				var lcid = 0;
				function lc(){
					if(lcid!=currentUserId){
						//alert(currentUserId);
						$.ajax({
							   type: "GET",
							   url: "${ctx}/user/getManageMoney?userId="+currentUserId,
							   success: function(data){
							   	console.log(data.resultMap.manageMoney);
							    var manageMoney = data.resultMap.manageMoney
							   	
							    $("#annualYieldZ").text(manageMoney.annualYield);
							    $("#earningsBalanceZ").text(manageMoney.earningsBalance);
							    $("#experienceMoneyZ").text(manageMoney.experienceMoney);
							    $("#incomeZ").text(manageMoney.income);
							    $("#principalZ").text(manageMoney.principal);
							    $("#stayWithdrawZ").text(manageMoney.stayWithdraw);
							    $("#totalAssetZ").text(manageMoney.totalAsset);
							   }
							});
						lcid=currentUserId;
						lcList(currentUserId, 1, 10);
					}
				}
				
				var ydid = 0;
				function yd(){
					if(ydid!=currentUserId){
						 
					  	$.ajax({
							   type: "GET",
							   url: "${ctx}/user/getSportRecord?userId="+currentUserId,
							   success: function(data){
							   	console.log(data.resultMap.sportRecord);
							    var sportRecord = data.resultMap.sportRecord
							    $("#todayStep").text(sportRecord.todayStep);
							    $("#todayDistance").text(sportRecord.todayDistance);
							    $("#todayDistanceTime").text(sportRecord.todayDistanceTime);
							    $("#countStep").text(sportRecord.countStep);
							    $("#countDistance").text(sportRecord.countDistance);
							    $("#countDistanceTime").text(sportRecord.countDistanceTime);
							    $("#step5000DN").text(sportRecord.step5000DN);
							    $("#step5000_9999").text(sportRecord.step5000_9999);
							    $("#step10000_14999").text(sportRecord.step10000_14999);
							    $("#step15000_19999").text(sportRecord.step15000_19999);
							    $("#step20000UP").text(sportRecord.step20000UP);
							    $("#distance0_3KM").text(sportRecord.distance0_3KM);
							    $("#distance3_5KM").text(sportRecord.distance3_5KM);
							    $("#distance5_8KM").text(sportRecord.distance5_8KM);
							    $("#distance8_10KM").text(sportRecord.distance8_10KM);
							    $("#distance10KMUP").text(sportRecord.distance10KMUP);
							    $("#snCountRunning").text(sportRecord.snCountRunning);
							    $("#swCountRunning").text(sportRecord.swCountRunning);
							   }
							});  
						ydid=currentUserId;
						ydList(currentUserId, 1, 10);
					}
				}
				
				function lcList(userId,currentPage,pageSize){
					$("#recordBody").empty();
					$("#pg").empty();
				    $.ajax({
						   type: "GET",
						   url: "${ctx}/walletdetail/getListByUserId?userId="+userId+"&currentPage="+currentPage+"&pageSize="+pageSize,
						   success: function(data){
						 
						    var result = data.resultMap.record;
						   // console.log(result);
						    var tr = "";
						    var td = "";
						    for (var x = 0; x < result.result.length; x++) {
						    	if(x!=0){
						    	   td = "";
						    	}
						    	td +="<td>"+ timeToStr(result.result[x].createTime)+"</td>";
						    	var types = parseInt(result.result[x].type);
						     
						    	if(types==1||types==3||types==4||types==5||types==7||types==10){
						    		td +="<td>+"+result.result[x].money+"</td>";
						    	}else{
						    		td +="<td>-"+result.result[x].money+"</td>";
						    	}
						    	
						    	td +="<td>"+result.result[x].result+"</td>";
						    	
						    	if(result.result[x].type==3){
						    		td +="<td>"+result.result[x].annualYield+"%</td>";
						    	}else {
						    		td +="<td></td>";
								}
						    	
						    	if(result.result[x].type==1){
						    		td +="<td>充值</td>";
						    	}
						    	if(result.result[x].type==2){
						    		td +="<td>提现</td>";
						    	}
						    	if(result.result[x].type==3){
						    		td +="<td>日收益</td>";
						    	}
						    	if(result.result[x].type==4){
						    		td +="<td>提现退回</td>";
						    	}
						    	if(result.result[x].type==5){
						    		td +="<td>大腿收入</td>";
						    	}
						    	if(result.result[x].type==6){
						    		td +="<td>抱大腿支出</td>";
						    	}
						    	if(result.result[x].type==7){
						    		td +="<td>使用券的日收益</td>";
						    	}
						    	if(result.result[x].type==8){
						    		td +="<td>接力赛支出</td>";
						    	}
						    	if(result.result[x].type==9){
						    		td +="<td>一元夺宝支出</td>";
						    	}
						    	if(result.result[x].type==10){
						    		td +="<td>一元夺宝退款</td>";
						    	}
						    	if(result.result[x].type==11){
						    		td +="<td>购买金豆支出</td>";
						    	}
						    	tr +="<tr>"+td+"</tr>";
							}
						    $("#recordBody").html(tr);
						  	$("#pg").html(ajaxPage(result.pageSize,result.currentPage,result.totalPages,result.totalCount));
						   }
					 });
				}
				
				function ajaxGoPage(num, size){
					lcList(currentUserId,num,size);
				}
				function ajaxGoPageSkip(num){
					lcList(currentUserId,num,7);
				}
				
				
				function ydList(userId,currentPage,pageSize){
					$("#sportBody").empty();
					$("#pg2").empty();
				    $.ajax({
						   type: "GET",
						   url: "${ctx}/run/getListByUserId?userId="+userId+"&currentPage="+currentPage+"&pageSize="+pageSize,
						   success: function(data){
						    var result = data.resultMap.run;
						    //console.log(result);
						    var tr = "";
						    var td = "";
						    for (var x = 0; x < result.result.length; x++) {
						    	if(x!=0){
						    	   td = "";
						    	}
						    	td +="<td>"+ timeToStr(result.result[x].createTime)+"</td>";
						    	td +="<td>"+result.result[x].step+"</td>";
						    	if(result.result[x].distance!=null){
						    		td +="<td>"+result.result[x].distance.toFixed(2)+"</td>";
						    	}else{
						    		td +="<td></td>";
						    	}
						    	td +="<td>"+Math.ceil((result.result[x].totalTime/60))+"</td>";
						    	if(result.result[x].avspeed!=null){
						    		td +="<td>"+result.result[x].avspeed.toFixed(2)+"</td>";
						    	}else{
						    		td +="<td></td>";
						    	}
						    	
						    	td +="<td></td>";
						    	if(result.result[x].type==1){
						    		td +="<td>室内跑步</td>";
						    	}
						    	if(result.result[x].type==2){
						    		td +="<td>室外跑步</td>";
						    	}
						    	tr +="<tr>"+td+"</tr>";
							}
						    $("#sportBody").html(tr);
						  	$("#pg2").html(ajaxPage2(result.pageSize,result.currentPage,result.totalPages,result.totalCount));
						   }
					 });
				}
				
				function ajaxGoPage2(num, size){
					ydList(currentUserId,num,size);
				}
				function ajaxGoPage2Skip(num){
					ydList(currentUserId,num,7);
				}
				
				
				function toMonitoring(cid){
					location.href = "${ctx}/user/getClientIdList?clientId="+cid;
				}
				
				
				var hdid = 0;
				function hdList(userId,currentPage,pageSize){
					if(userId == null){
						userId = currentUserId;
					}
					if(currentPage == null){
						currentPage = 1;
					}
					if(pageSize == null){
						pageSize = 7;
					}
				 
					if(hdid!=currentUserId){
						$("#activityBody").empty();
						$("#pg3").empty();
						hdid=currentUserId;
						   $.ajax({
							   type: "GET",
							   url: "${ctx}/thigh/getThighRecordByUserId?userId="+userId+"&currentPage="+currentPage+"&pageSize="+pageSize,
							   success: function(data){
							    var result = data.resultMap.activityRecord;
							    var numbers = data.resultMap.number;
							    var earnings = data.resultMap.earnings;
								$("#ljcj").text(numbers);
								$("#ljcs").text(earnings);
							  //  console.log(result);
							    var tr = "";
							    var td = "";
							    for (var x = 0; x < result.result.length; x++) {
							    	if(x!=0){
							    	   td = "";
							    	}
							    	td +="<td>"+ timeToStr(result.result[x].createTime)+"</td>";
							    	td +="<td>"+result.result[x].activity.title+"</td>";
							    	td +="<td>抱大腿</td>";
							    	td +="<td>"+(result.result[x].userId==userId?"大腿":"懒虫")+"</td>";
							    	td +="<td>"+(result.result[x].userId==userId?"<button type='button' class='btn btn-default' onclick='openRun("+userId+","+result.result[x].activityId+")' >跑量</button>":"")+
							    	"  <button type='button' class='btn btn-default' onclick='activityInfo("+result.result[x].id+",1,7)' >详情</button></td>";
							    	tr +="<tr>"+td+"</tr>";
								}
							    $("#activityBody").html(tr);
							  	$("#pg3").html(ajaxPage3(result.pageSize,result.currentPage,result.totalPages,result.totalCount)); 
							   }
						 });
					}
				}
				
				
				function ajaxGoPage3(num, size){
					hdList(currentUserId,num,size);
				}
				function ajaxGoPage3Skip(num){
					hdList(currentUserId,num,7);
				}
				
				var thighId = null;
				function activityInfo(hId,currentPage,pageSize){
					if(hId!=null){
						thighId = hId;
					}
					$('#activityDetail').modal({
						keyboard: false,
						show: true
					}); 
					
					 $.ajax({
						   type: "GET",
						   url: "${ctx}/thigh/getThighOrSlackerInfo?userId="+currentUserId+"&currentPage="+currentPage+"&pageSize="+pageSize+"&thighId="+thighId,
						   success: function(data){
						   var result = data.resultMap.page;
						   var thigh = data.resultMap.thigh;
						   var commission =data.resultMap.commission;
						   var coupon = data.resultMap.coupon;
						   var hugThigh = data.resultMap.hugThigh;
						   var userCoupon = data.resultMap.userCoupon;
						   
						    // console.log(hugThigh!=null);
						   
						     $("#cjsj").text(timeToStr(thigh.createTime));
						     $("#dtdj").text(thigh.type);
						     $("#bbcs").text(thigh.hugNum);
						     $("#cs").text(thigh.hugMoney.toFixed(2));
						     
						     if(hugThigh!=null){
						    		$("#flq").show();
							  		$("#flsj").text(timeToStr(hugThigh.createTime));
							  		$("#zfyj").text(commission);
							  		$("#hdfl").text(coupon.annualYield+"%利率+"+coupon.bonus+"体验金,"+timeToStr(userCoupon.expiryTime)+"到期");
							  		$("#zt").text((userCoupon.status==1||userCoupon.status==-1?"已使用":"未使用"));
							  	}else{
							  		 $("#flq").hide();
							  	}
						     
						        var tr = "";
							    var td = "";
							    for (var x = 0; x < result.result.length; x++) { 
							    	if(x!=0){
							    	   td = "";
							    	}
							    	td +="<td>"+result.result[x].id+"</td>";
							    	td +="<td>"+ timeToStr(result.result[x].createTime)+"</td>";
							    	td +="<td>"+result.result[x].user.id+"</td>";
							    	td +="<td>"+result.result[x].user.nickname+"</td>";
							    	td +="<td>"+commission+"</td>";
							    	tr +="<tr>"+td+"</tr>";
								}
							    $("#hugThighBody").html(tr);
							  	$("#pg4").html(ajaxPage4(result.pageSize,result.currentPage,result.totalPages,result.totalCount)); 
						   }						   
					 });
					
				}
				
				
				function ajaxGoPage4(num, size){
					activityInfo(null,num,size);
				}
				function ajaxGoPage4Skip(num){
					activityInfo(null,num,7);
				}
				
				
				 function openRun(userId,activityId){
					 //alert(userId+" "+activityId);
					   $('#runWindow').modal({
							keyboard: false,
							show: true
						}); 
					   $.ajax({
						   type: "GET",
						   url: "${ctx}/thigh/getRunRecord?userId="+userId+"&activityId="+activityId,
						   success: function(data){
						    var run = data.resultMap.run;
						     $("#pl").text(run.distance.toFixed(2));
						     $("#ys").text((run.totalTime/60).toFixed(2));
						     $("#bs").text(run.step);
						     $("#bf").text((run.distance/run.step).toFixed(4));
						     $("#pjsd").text(run.avspeed.toFixed(2));
						     $("#zksd").text(run.maxspeed.toFixed(2));
						     $("#pjps").text(run.maxspeed.toFixed(2));
						     $("#zkps").text(run.minspeed.toFixed(2));
						   }
					   });
					   
				   }
			</script>
</body>
</html>
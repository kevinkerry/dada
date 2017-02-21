<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>会员列表</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
        <script type="text/javascript">
			//定义全局变量
			var selectedMemberId = "";
		</script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
            <div class="form-group form-inline" style="margin-bottom: 10px;">
            	<shiro:hasPermission name="update:member">
            		<button type="button" class="btn btn-info" id="update_member"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            		<button type="button" class="btn btn-success" id="recommend_member"><i class="glyphicon glyphicon-send"></i>&nbsp;推荐</button>
            	</shiro:hasPermission>
            	<shiro:hasPermission name="delete:member">
            		<button type="button" class="btn btn-danger" id="delete_member"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
				<input class="form-control col-md-offset-1" id="memberName" type="search" placeholder="请输入会员名" style="width:150px;"/>
				<input class="form-control" id="memberAlias" type="search" placeholder="请输入会员昵称" style="width:150px;"/>
				<input class="form-control" id="city" type="search" placeholder="请输入城市名" style="width:150px;"/>
				<select class="form-control" id="sex" style="width: 120px;">
		          <option value="">请选择性别</option>
		          <option value="男">男</option>
		          <option value="女">女</option>
		        </select>
		        <select class="form-control" id="recommendFlag" style="width: 120px;">
		          <option value="">是否推荐</option>
		          <option value="1">已推荐</option>
		          <option value="0">未推荐</option>
		        </select>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th><input type="checkbox" id="selectAll"/></th>
		                <th>#</th>
		                <th>会员头像</th>
		                <th>会员编码</th>
		                <th>会员名称</th>
		                <th>会员昵称</th>
		                <th>微信号</th>
		                <th>性别</th>
		                <th>所在城市</th>
		                <th>联系电话</th>
		                <th>邮箱</th>
		                <th>会员等级</th>
		                <th>荣誉称号</th>
		                <th>排序</th>
		                <th>是否推荐</th>
		                <th>会员状态</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="member" varStatus="status" > 
			        <tbody id="${member.memberId}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${member.memberId}"/></td>
			        		<td>${member.memberId}</td>
			        		<td>
			        			<c:if test="${empty member.memberLogo}">
			        				<c:set var="logoUrl" value="${ctx}/resources/images/default.png"></c:set>
			        			</c:if>
			        			<c:if test="${not empty member.memberLogo}">
			        				<c:set var="logoUrl" value="${image}${member.memberLogo}"></c:set>
			        			</c:if>
			        			<a href="${logoUrl}" data-lightbox="roadtrip">
			        				<img src="${logoUrl}" width="32px" height="32px" style="margin-right:1px;border-radius: 5px;"/>
			        			</a>
			        		</td>
			        		<td>${member.memberCode}</td>
			        		<td>${member.memberName}</td>
			        		<td>${member.memberAlias}</td>
			        		<td>${member.wechat}</td>
			        		<td>${member.sex}</td>
			        		<td>${member.city}</td>
			        		<td>${member.mobile}</td>
			        		<td>${member.email}</td>
			        		<td>${member.memberLevel}</td>
			        		<c:choose>
			        			<c:when test="${member.memberLevel eq 'Lv.1'}">
			        				<td>菜鸟</td>
			        			</c:when>
			        			<c:when test="${member.memberLevel eq 'Lv.2'}">
			        				<td>高手</td>
			        			</c:when>
			        			<c:when test="${member.memberLevel eq 'Lv.3'}">
			        				<td>大神</td>
			        			</c:when>
			        			<c:otherwise>
			        				<td>修炼中</td>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${member.recommendFlag eq 1}">
				        			<shiro:hasPermission name="update:member">
					        			<td width="30px;" recommend-order="${member.recommendOrder}" align="center">
					        				<div onclick="editOrder(this, event);" style="width: 30px;height: 25px;text-align: center;color:#337ab7;cursor:pointer;" title="点击修改会员推荐顺序">${member.recommendOrder}</div>
					        			</td>
				        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:member">
				        				<td width="30px;" recommend-order="${member.recommendOrder}" align="center">
					        				<div style="width: 30px;height: 25px;text-align: center;">${member.recommendOrder}</div>
					        			</td>
				        			</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<td width="30px;"></td>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${member.recommendFlag eq 1}">
			        				<shiro:hasPermission name="update:member">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-default" disabled="disabled">YES</button>
											  <button type="button" class="btn btn-danger" onclick="recommed(0, this, event);">NO</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:member">
				        				<td>已推荐</td>
			        				</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:member">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">NO</button>
											  <button type="button" class="btn btn-success" onclick="recommed(1, this, event);">YES</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:member">
				        				<td>未推荐</td>
				        			</shiro:lacksPermission>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${member.memberState eq 1}">
			        				<shiro:hasPermission name="update:member">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">启用</button>
											  <button type="button" class="btn btn-danger" onclick="changeState(0, this, event);">冻结</button>
											</div>
					        			</td>
				        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:member">
				        				<td>启用</td>
				        			</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:member">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-defult" disabled="disabled">冻结</button>
											  <button type="button" class="btn btn-success" onclick="changeState(1, this, event);">启用</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:member">
				        				<td>冻结</td>
				        			</shiro:lacksPermission>
			        			</c:otherwise>
			        		</c:choose>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
          </div>
          
          <div class="modal fade" id="recommend_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">会员推荐</h4>
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
     	 <script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
		<script type="text/javascript">
			var memberIds = [];//多选时记录会员id
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			
			
			$(function(){
				//初始化查询条件
				$('#memberName').val('${page.params.memberName}');
				$('#memberAlias').val('${page.params.memberAlias}');
				$('#city').val('${page.params.city}');
				$('#sex').val('${page.params.sex}');
				$('#recommendFlag').val('${page.params.recommendFlag}');
				
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$("#delete_member").on("click",deleteMember);	
				$('#update_member').on('click', updateMember);
				$('#selectAll').on('click', selectAll);
				$('#recommend_member').on('click', recommendMember);
				
				$("#memberName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#memberAlias").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});
				$("#city").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});
				
				$("#sex").on("change",function(event){
					queryList();
				});
				
				$("#recommendFlag").on("change",function(event){
					queryList();
				});
					
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						/* if(id != selectedMemberId) {
							$("#prev"+selectedMemberId).prop('checked', false);
						} */
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedMemberId = id;
						}else {
							selectedMemberId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					/* if($(this).attr('id') != selectedMemberId) {
						$("#prev"+selectedMemberId).prop('checked', false);
					} */
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedMemberId = "";
					}else {
						input.prop('checked', true);
						selectedMemberId = $(this).attr('id');
					}
				});
			});
			
			//会员全选
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
			
			//是否推荐
			function recommed(recommendFlag, dom, event) {
				$('#recommend_order').val("");
				var memberId = $(dom).parent().parent().parent().parent().attr('id');
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
							location.href = "${ctx}/member/modify?memberId=" + memberId +"&recommendFlag=" + recommendFlag;
						}
					});
				}
				
				$("#save_order").on("click",function() {
					recommendOrder = $('#recommend_order').val();
					if(isEmpty(recommendOrder)) {
						swal('推荐顺序不能为空，请重新输入！');
						return false;
					}
					location.href = "${ctx}/member/modify?memberId=" + memberId +"&recommendOrder="+ recommendOrder +"&recommendFlag=" + recommendFlag;
				});
				
				event.stopPropagation();
			}

			//启用/禁用会员
			function changeState(memberState, dom, event) {
				var memberId = $(dom).parent().parent().parent().parent().attr('id');
				if(memberState == 0) {
					swal({
						title : "",
						text : "确认冻结该会员？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/member/modify?memberId=" + memberId+ "&memberState=" + memberState;
						}
					});
				}else {
					location.href = "${ctx}/member/modify?memberId=" + memberId+ "&memberState=" + memberState;
				}
				
				event.stopPropagation();
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
						var memberId = recommend.parent().parent().parent().parent().attr('id');
						recommendOrder = recommend.val();
						if(isEmpty(recommendOrder)) {
							swal('推荐顺序不能为空，请重新输入！');
							return;
						}
						location.href = "${ctx}/member/modify?memberId=" + memberId +"&recommendOrder="+ recommend.val();
					}else {
						return false;
					}
					target = null;
					e.stopPropagation();
				});
				event.stopPropagation();
			}

			/**
			 *查询
			 */
			function queryList() {
				var memberName = $('#memberName').val();
				var memberAlias = $('#memberAlias').val();
				var city = $('#city').val();
				var sex = $('#sex').val();
				var recommendFlag = $('#recommendFlag').val();

				var url = "${ctx}/member/list?currentPage="+currentPage+"&pageSize="+pageSize;
				if (!isEmpty(memberName)) {
					memberName = encodeURI(encodeURI(memberName));
					url = url + "&memberName=" + memberName;
				}
				if (!isEmpty(memberAlias)) {
					memberAlias = encodeURI(encodeURI(memberAlias));
					url = url + "&memberAlias=" + memberAlias;
				}
				if (!isEmpty(city)) {
					city = encodeURI(encodeURI(city));
					url = url + "&city=" + city;
				}
				if (!isEmpty(sex)) {
					sex = encodeURI(encodeURI(sex));
					url = url + "&sex=" + sex;
				}
				if (!isEmpty(recommendFlag)) {
					url = url + "&recommendFlag=" + recommendFlag;
				}

				location.href = url;
			}

			//批量推荐会员
			function recommendMember() {
				checkSelectMember();
				if(isEmpty(memberIds)) {
					swal('请选择需要推荐的会员！');
					return false;
				}
				location.href = "${ctx}/member/batchRecommend?memberIds="+memberIds;
			}
			
			/**
			 *编辑会员信息
			 */
			function updateMember() {
				checkSelectMember();
				if(!isEmpty(memberIds) && memberIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedMemberId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/member/" + selectedMemberId + "/update";
			}

			/**
			 *删除
			 */
			function deleteMember() {
				checkSelectMember();
				if(!isEmpty(memberIds) && memberIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedMemberId === '') {
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
				}, function(isConfirm) {
					if (isConfirm) {
						location.href = "${ctx}/member/" + selectedMemberId + "/delete";
					}
				});
			}
			
			//检查选中行数
			function checkSelectMember() {
				memberIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						memberIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(memberIds) && memberIds.length == 1) {
					selectedMemberId = memberIds[0];
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
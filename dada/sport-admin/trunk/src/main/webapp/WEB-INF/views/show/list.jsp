<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>达人秀</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <form id="search_form" class="form-group form-inline" style="margin-bottom: 10px;" action="${ctx}/show/list" method="post">
            	<shiro:hasPermission name="update:show">
	           		<button type="button" class="btn btn-info" id="update_show"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
	           	</shiro:hasPermission>
	            <shiro:hasPermission name="update:show">
	           		<button type="button" class="btn btn-success" id="recommend_show"><i class="glyphicon glyphicon-send"></i>&nbsp;推荐</button>
	           	</shiro:hasPermission>
	           	<input type="hidden" name="currentPage" value="${page.currentPage}"/>
	           	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
				<input class="form-control col-md-offset-1" id="userName" type="search" name="member.memberAlias" value="${page.params.member.memberAlias}" placeholder="请输入哒人姓名" style="width: 150px;"/>
				<select id="parent_category" style="width:120px;" class="form-control" name="parentCategory">
		          <option value="">哒人秀类型</option>
		        </select>
		        <select class="form-control" id="recommendFlag" style="width: 120px;" name="recommendFlag">
		          <option value="">是否推荐</option>
		          <option value="1">已推荐</option>
		          <option value="0">未推荐</option>
		        </select>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</form>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th><input type="checkbox" id="selectAll"/></th>
		                <th>#</th>
		                <th>照片</th>
		                <th>秀描述</th>
		                <th>哒人姓名</th>
		                <th>所属分类</th>
		                <th>所在城市</th>
		                <th>浏览量</th>
		                <th>评论数</th>
		                <th>排序</th>
		        		<th>是否推荐</th>
		        		<th>状态</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="show" varStatus="status" > 
			        <tbody id="${show.showId}" user-name="${show.showUser.userName}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${show.showId}"/></td>
			        		<td>${show.showId}</td>
			        		<td>
				        			<c:forEach items="${show.showImages}" var="o" varStatus="status" > 
				        			
									<c:set value="${fn:split(o.imgUrl, '.')}" var="imageUrl" />
				            		<c:set value="${fn:split(imageUrl[0], '!')}" var="imageName" />
				
				        			<a href="${image}${o.imgUrl}" data-lightbox="roadtrip">
				        				<img src="${image }${imageName[0]}!50x50.${imageUrl[1]}" width="32px" height="32px" style="margin-right:1px"/>
				        			</a>
				        			 </c:forEach>
				        		</td>
			        		
			        		<c:if test="${fn:length(show.showDesc) gt 35}">
								<td width="400px;">
									<p style="margin: 0;">
										${fn:substring(show.showDesc, 0, 35)}
										<a style="cursor: pointer;" onclick="showMore(this);">查看更多</a>
									</p>
									<p style="display: none;margin: 0;">
										${show.showDesc}
										<a style="cursor: pointer;" onclick="showLess(this);">收起</a>
									</p>
								</td>
							</c:if>
							<c:if test="${fn:length(show.showDesc) lt 35}">
								<td width="300px;">${show.showDesc}</td>
							</c:if>
							<td>${show.member.memberAlias}</td>
			        		<td>${show.category.categoryName}</td>
			        		<td>${show.city}</td>
			        		<td>${show.viewQuantity}</td>
			        		<td><a href="#" onclick="viewComment(this, event);" title="点击查看哒人秀评论">${show.commentCount}</a></td>
			        		<c:choose>
			        			<c:when test="${show.recommendFlag eq 1}">
				        			<shiro:hasPermission name="update:show">
					        			<td width="30px;" recommend-order="${show.focusWeight}" align="center">
					        				<div onclick="editOrder(this, event);" style="width: 30px;height: 25px;text-align: center;color:#337ab7;cursor:pointer;" title="点击修改哒人推荐顺序">${show.focusWeight}</div>
					        			</td>
				        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:show">
				        				<td width="30px;" recommend-order="${show.focusWeight}" align="center">
					        				<div style="width: 30px;height: 25px;text-align: center;">${show.focusWeight}</div>
					        			</td>
				        			</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<td width="30px;"></td>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${show.recommendFlag eq 1}">
			        				<shiro:hasPermission name="update:show">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-default" disabled="disabled">YES</button>
											  <button type="button" class="btn btn-danger" onclick="recommed(0, this, event);">NO</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:show">
				        				<td>已推荐</td>
			        				</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:show">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">NO</button>
											  <button type="button" class="btn btn-success" onclick="recommed(1, this, event);">YES</button>
											</div>
					        			</td>
					        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:show">
				        				<td>未推荐</td>
				        			</shiro:lacksPermission>
			        			</c:otherwise>
			        		</c:choose>
			        		<c:choose>
			        			<c:when test="${show.status eq 'A'}">
			        				<shiro:hasPermission name="update:show">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
											  <button type="button" class="btn btn-default" disabled="disabled">启用</button>
											  <button type="button" class="btn btn-danger" onclick="changeState('I', this, event);">冻结</button>
											</div>
					        			</td>
				        			</shiro:hasPermission>
				        			<shiro:lacksPermission name="update:show">
				        				<td>启用</td>
				        			</shiro:lacksPermission>
			        			</c:when>
			        			<c:otherwise>
			        				<shiro:hasPermission name="update:show">
					        			<td width="120px;">
					        				<div class="btn-group btn-group-sm" role="group">
					        				  <button type="button" class="btn btn-defult" disabled="disabled">冻结</button>
											  <button type="button" class="btn btn-success" onclick="changeState('A', this, event);">启用</button>
											</div>
					        			</td>
					        		</shiro:hasPermission>
					        		<shiro:lacksPermission name="update:show">
				        				<td>冻结</td>
				        			</shiro:lacksPermission>
			        			</c:otherwise>
			        		</c:choose>
			        	</tr>
			        </tbody>
		        </c:forEach>
			</table>
			<%@include file="/common/page.jsp" %>
			
			<div class="modal fade" id="recommend_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">哒人秀推荐</h4>
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
     	<script src="${ctx }/resources/libs/jquery-cityselect/js/jquery.cityselect.js"></script>
		<script type="text/javascript">
			//定义全局变量
			var showIds = [];//多选时记录达人秀id
			var selectedShowId = "";//单选时记录达人秀id
			
			$(function(){
				getCategory();
				$('#recommendFlag').val('${page.params.recommendFlag}');
				
				//初始化查询条件
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$('#update_show').on('click', updateShow);
				$('#selectAll').on('click', selectAll);
				$('#recommend_show').on('click', batchRecommend);
				
				$("#userName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				$("#parent_category").on("change", queryList);
				$("#recommendFlag").on("change", queryList);
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						/* if(id != selectedShowId) {
							$("#prev"+selectedShowId).prop('checked', false);
						} */
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedShowId = id;
						}else {
							selectedShowId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					 if($(this).attr('id') != selectedShowId) {
						$("#prev"+selectedShowId).prop('checked', false);
					} 
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedShowId = "";
					}else {
						input.prop('checked', true);
						selectedShowId = $(this).attr('id');
					}
				});
			});
			
			function getCategory() {
				$.ajax({
				   url: "${ctx}/category/list",
				   success: function(msg){
					   var categories = msg.results.categories;
					   $.each( categories, function(i, category){
						   if(category.parentId == -1) {
							   $('#parent_category').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
						   }
						});
					   $('#parent_category').val('${page.params.parentCategory}');
				   }
				});
			}
			
			//查看评论列表
			function viewComment(dom, event) {
				var userName = $(dom).parent().parent().parent().attr('user-name');
				var showId = $(dom).parent().parent().parent().attr('id');
				
				var url = "${ctx}/showcomment/list?currentPage=1&showId="+showId+"&userName="+encodeURI(encodeURI(userName));
				location.href= url;
				event.stopPropagation();
			}
			
			//显示更多
			function showMore(dom) {
				$(dom).parent().slideToggle("slow","linear");
				$(dom).parent().next().slideToggle("slow","linear");
			}
			
			//隐藏
			function showLess(dom) {
				$(dom).parent().slideToggle("slow","linear");
				$(dom).parent().prev().slideToggle("slow","linear");
			}
			
			//达人秀全选
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
				var showId = $(dom).parent().parent().parent().parent().attr('id');
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
							location.href = "${ctx}/show/modify?showId=" + showId +"&recommendFlag=" + recommendFlag;
						}
					});
				}
				
				$("#save_order").on("click",function() {
					recommendOrder = $('#recommend_order').val();
					if(isEmpty(recommendOrder)) {
						swal('推荐顺序不能为空，请重新输入！');
						return false;
					}
					location.href = "${ctx}/show/modify?showId=" + showId +"&recommendOrder="+ recommendOrder +"&recommendFlag=" + recommendFlag;
				});
				
				event.stopPropagation();
			}

			//启用/禁用达人秀
			function changeState(status, dom, event) {
				var showId = $(dom).parent().parent().parent().parent().attr('id');
				if(status == 'I') {
					swal({
						title : "",
						text : "确认冻结该达人秀？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/show/modify?showId=" + showId+ "&status=" + status;
						}
					});
				}else {
					location.href = "${ctx}/show/modify?showId=" + showId+ "&status=" + status;
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
						var showId = recommend.parent().parent().parent().parent().attr('id');
						recommendOrder = recommend.val();
						if(isEmpty(recommendOrder)) {
							swal('推荐顺序不能为空，请重新输入！');
							return;
						}
						location.href = "${ctx}/show/modify?showId=" + showId +"&recommendOrder="+ recommend.val();
					}else {
						return false;
					}
					target = null;
					e.stopPropagation();
				});
				event.stopPropagation();
			}

			//批量推荐达人秀
			function batchRecommend() {
				checkSelectShow();
				if(isEmpty(showIds)) {
					swal('请选择需要推荐的达人秀！');
					return false;
				}
				location.href = "${ctx}/show/batchRecommend?showIds="+showIds;
			}
			
			/**
			 *编辑达人秀信息
			 */
			function updateShow() {
				checkSelectShow();
				if(!isEmpty(showIds) && showIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedShowId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/show/" + selectedShowId + "/update";
			}
			
			//检查选中行数
			function checkSelectShow() {
				showIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						showIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(showIds) && showIds.length == 1) {
					selectedShowId = showIds[0];
				}
			}
			
			/**
			 *查询
			 */
			function queryList() {
				$('#search_form').submit();
			}

			function goPage(num, size) {
				$('#search_form input[name="currentPage"]').val(num);
				$('#search_form input[name="pageSize"]').val(size);
				queryList();
			}
		</script>
    </body>

</html>
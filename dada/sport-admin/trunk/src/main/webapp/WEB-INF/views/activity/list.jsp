<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<%-- <%@include file="/common/common-header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Tables</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="${ctx }/resources/assets/styles.css" />
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
<style type="text/css">
 body{margin:0;height:100%;width:100%;position:absolute;}
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
        <script src="${ctx }/resources/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <script type="text/javascript">
			//定义全局变量
			var selectedActivityId = "";
		</script>
    </head>
    
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
                <div class="span9" id="content">
<!--内容开始-->
	<div class="form-group form-inline">
		<shiro:hasPermission name="add:activity">
			<button type="button" class="btn btn-primary" id="add_activity"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="update:activity">
			<button type="button" class="btn btn-info" id="update_activity"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="update:activity">
       		<button type="button" class="btn btn-success" id="recommend_activity"><i class="glyphicon glyphicon-send"></i>&nbsp;推荐</button>
       	</shiro:hasPermission>
		<shiro:hasPermission name="delete:activity">
			<button type="button" class="btn btn-danger" id="delete_activity"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
		</shiro:hasPermission>
		
    	<input class="form-control col-md-offset-1" id="activity_title" type="text" placeholder="请输入活动名称" style="width: 150px;"/>
    	<input class="form-control" id="activity_org" type="text" placeholder="请输入举办单位" style="width: 150px;"/>
        <select id="parent_category" style="width:120px;" class="form-control">
          <option value="">活动类型</option>
        </select>
        <select class="form-control" id="recommendFlag" style="width: 120px;">
          <option value="">是否推荐</option>
          <option value="1">已推荐</option>
          <option value="0">未推荐</option>
        </select>
        <!-- <input id="begin_time" type="date" style="width:150px;" class="form-control"/>
        <lable>—</lable>
        <input id="end_time" type="date" style="width:150px;" class="form-control"/> -->
        <input id="search" type="button" class="btn btn-default" value="搜索"/>
   </div>
	<table cellpadding="0" cellspacing="0" border="0" class="table table-bordered">
    	<thead>
            <tr>
            	<th><input type="checkbox" id="selectAll"/></th>
                <th>#</th>
                <th>活动名称</th>
                <th>活动时间</th>
                <th>举办单位</th>
                <th>活动类型</th>
                <th>创建人</th>
                <th>活动状态</th>
                <th>报名数</th>
                <th>评论数</th>
                <th>排序</th>
		        <th>是否推荐</th>
            </tr>
        </thead>
        <c:forEach items="${page.result}" var="activity" varStatus="status" > 
	        <tbody id="${activity.activityId}" activity-title="${activity.activityTitle}">
	        	<tr>
	        		<td width="30px;"><input type="checkbox" id="prev${activity.activityId}"/></td>
	        		<td>${activity.activityId}</td>
	        		<td>${activity.activityTitle}</td>
	        		<td>
	        			<fmt:formatDate value="${activity.beginTime}" pattern="yyyy-MM-dd" />
	        			<c:if test="${!empty  activity.endTime}">
	        			~ 
	        			</c:if>
	        			<fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd" /> 
	        		 </td>
	        		<td>${activity.activityOrg}</td>
	        		<td>${activity.category.categoryName}</td>
	        		<td>哒哒运动</td>
	        		<c:if test="${curDate lt activity.startApplyTime}">
	        			<td >报名准备中</td>
	        		</c:if> 
	        		<c:if test="${curDate gt activity.startApplyTime}">
	        			<c:if test="${curDate lt activity.endApplyTime}">
		        			<td >报名进行中</td>
		        		</c:if> 
				 		<c:if test="${curDate gt activity.endApplyTime && curDate lt activity.beginTime && curDate lt activity.endTime}">
		        			<td >报名截止</td>
		        		</c:if>   
		        		<c:if test="${curDate gt activity.beginTime && curDate lt activity.endTime}">
		        			<td >活动进行中</td>
		        		</c:if>     
		        		<c:if test="${curDate gt activity.endTime}">
		        			<td >活动已结束</td>
		        		</c:if>
	        		</c:if>
	        		<td><a href="#" onclick="viewApply(this, event);" title="点击查看活动报名情况">${activity.applyCount}</a></td>
	        		<td><a href="#" onclick="viewComment(this, event);" title="点击查看活动评论">${activity.commentCount}</a></td>
	        		<c:choose>
	        			<c:when test="${activity.recommendFlag eq 1}">
		        			<shiro:hasPermission name="update:activity">
			        			<td width="30px;" recommend-order="${activity.orders}" align="center">
			        				<div onclick="editOrder(this, event);" style="width: 30px;height: 25px;text-align: center;color:#337ab7;cursor:pointer;" title="点击修改活动推荐顺序">${activity.orders}</div>
			        			</td>
		        			</shiro:hasPermission>
		        			<shiro:lacksPermission name="update:activity">
		        				<td width="30px;" recommend-order="${activity.orders}" align="center">
			        				<div style="width: 30px;height: 25px;text-align: center;">${activity.orders}</div>
			        			</td>
		        			</shiro:lacksPermission>
	        			</c:when>
	        			<c:otherwise>
	        				<td width="30px;"></td>
	        			</c:otherwise>
	        		</c:choose>
	        		<c:choose>
	        			<c:when test="${activity.recommendFlag eq 1}">
	        				<shiro:hasPermission name="update:activity">
			        			<td width="120px;">
			        				<div class="btn-group btn-group-sm" role="group">
			        				  <button type="button" class="btn btn-default" disabled="disabled">YES</button>
									  <button type="button" class="btn btn-danger" onclick="recommed(0, this, event);">NO</button>
									</div>
			        			</td>
			        		</shiro:hasPermission>
			        		<shiro:lacksPermission name="update:activity">
		        				<td>已推荐</td>
	        				</shiro:lacksPermission>
	        			</c:when>
	        			<c:otherwise>
	        				<shiro:hasPermission name="update:activity">
			        			<td width="120px;">
			        				<div class="btn-group btn-group-sm" role="group">
									  <button type="button" class="btn btn-default" disabled="disabled">NO</button>
									  <button type="button" class="btn btn-success" onclick="recommed(1, this, event);">YES</button>
									</div>
			        			</td>
			        			</shiro:hasPermission>
		        			<shiro:lacksPermission name="update:activity">
		        				<td>未推荐</td>
		        			</shiro:lacksPermission>
	        			</c:otherwise>
	        		</c:choose>
	        	</tr>
	        </tbody>
        </c:forEach>
	</table>
	<%@include file="/common/page.jsp" %>
<!--内容结束-->
                </div>
            </div>
		
			<div class="modal fade" id="recommend_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">活动推荐</h4>
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
        <!--/.fluid-container-->

        <script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
        <script src="${ctx }/resources/assets/scripts.js"></script>
    </body>

</html>
<script>
	var activityIds = [];
 	var currentPage = '${page.currentPage}';
 	var pageSize = '${page.pageSize}';	
	
	$(function() {
		//初始化查询条件
		getCategory();
   		$('#activity_title').val('${page.params.activityTitle}');
   		var beginTime = '${page.params.beginTime}';
   		var endTime = '${page.params.endTime}';
   		if(!isEmpty(beginTime)) {
   			beginTime = new Date(beginTime).Format("yyyy-MM-dd");
   		}
   		if(!isEmpty(endTime)) {
   			endTime = new Date(endTime).Format("yyyy-MM-dd");
   		}
   		$('#begin_time').val(beginTime);
   		$('#end_time').val(endTime);
   		$('#recommendFlag').val('${page.params.recommendFlag}');
		
		$("tbody").find('input:checkbox:checked').prop('checked', false);
		$('#search').bind('click', queryList);
		$('#add_activity').bind('click', addActivity);
		$('#update_activity').bind('click', updateActivity);
		$('#delete_activity').bind('click', deleteActivity);
		$('#selectAll').on('click', selectAll);
		$('#recommend_activity').on('click', recommendActivity);
		
		$("#activity_title").on("keypress",function(event){
			queryList();
		});	
		$("#parent_category").on("change",function(event){
			queryList();
		});
		$("#recommendFlag").on("change",function(event){
			queryList();
		});
		
		$("tbody").each(function(index) {
			var id = $(this).attr('id');
			$(this).find('input').on('click', function(e) {
				/* if(id != selectedActivityId) {
					$("#prev"+selectedActivityId).prop('checked', false);
				} */
				var input = $(e.target);
				var checked = input.prop('checked');
				if(checked) {
					selectedActivityId = id;
				}else {
					selectedActivityId = "";
				}
				e.stopPropagation();
			});
		});
		
		$("tbody").on("click",function(){
			//只允许同时选中一个
			/* if($(this).attr('id') != selectedActivityId) {
				$("#prev"+selectedActivityId).prop('checked', false);
			} */
			
			//设置选择或未选中
			var input = $(this).find('input');
			var checked = input.prop('checked');
			if(checked) {
				input.prop('checked', false);
				selectedActivityId = "";
			}else {
				input.prop('checked', true);
				selectedActivityId = $(this).attr('id');
			}
		});
	});
	
	//活动全选
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
	
	//检查选中行数
	function checkSelectActivity() {
		activityIds = [];
		$('tbody').find('input[type="checkbox"]').each(function() {
			if($(this).prop('checked')) {
				activityIds.push($(this).parent().parent().parent().attr('id'));
			}
		});
		if(!isEmpty(activityIds) && activityIds.length == 1) {
			selectedActivityId = activityIds[0];
		}
	}
	
	//批量推荐会员
	function recommendActivity() {
		checkSelectActivity();
		if(isEmpty(activityIds)) {
			swal('请选择需要推荐的活动！');
			return false;
		}
		location.href = "${ctx}/activity/batchRecommend?activityIds="+activityIds;
	}
	
	//查看报名列表
	function viewApply(dom, event) {
		var activityTitle = $(dom).parent().parent().parent().attr('activity-title');
		var activityId = $(dom).parent().parent().parent().attr('id');
		
		var url = "${ctx}/activityapply/list?currentPage=1&activityId="+activityId+"&activityTitle="+encodeURI(encodeURI(activityTitle));
		location.href= url;
		event.stopPropagation();
	}
	
	//查看评论列表
	function viewComment(dom, event) {
		var activityTitle = $(dom).parent().parent().parent().attr('activity-title');
		var activityId = $(dom).parent().parent().parent().attr('id');
		
		var url = "${ctx}/comment/list?currentPage=1&activityId="+activityId+"&activityTitle="+encodeURI(encodeURI(activityTitle));
		location.href= url;
		event.stopPropagation();
	}
	
	//编辑order
	function editOrder(dom, event) {
		var orders = $(dom).parent().attr('recommend-order');
		$(dom).html('<input value="'+orders+'" class="orders" style="width: 30px;height: 25px;text-align: center;"/>');
		$('.orders').on('click', function(e) {
			e.stopPropagation();
		});
		$('.orders').focus();
		$('body').on('click', function(e) {
			$('.orders').off('focus');
			var target = $(e.target);
			if(target.attr('class') != 'orders') {
				var recommend = $('.orders');
				var activityId = recommend.parent().parent().parent().parent().attr('id');
				orders = recommend.val();
				if(isEmpty(orders)) {
					swal('推荐顺序不能为空，请重新输入！');
					return;
				}
				location.href = "${ctx}/activity/modify?activityId=" + activityId +"&orders="+ orders;
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
		var activityId = $(dom).parent().parent().parent().parent().attr('id');
		var orders = null;
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
					location.href = "${ctx}/activity/modify?activityId=" + activityId +"&recommendFlag=" + recommendFlag;
				}
			});
		}
		
		$("#save_order").on("click",function() {
			orders = $('#recommend_order').val();
			if(isEmpty(orders)) {
				swal('推荐顺序不能为空，请重新输入！');
				return false;
			}
			location.href = "${ctx}/activity/modify?activityId=" + activityId +"&orders="+ orders +"&recommendFlag=" + recommendFlag;
		});
		
		event.stopPropagation();
	}
	
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
	
	/**
	 *查询
	 */
	function queryList() {
		var activityTitle = $('#activity_title').val();
		var parentCategory = $('#parent_category').val();
		var beginTime = $('#begin_time').val();
		var endTime = $('#end_time').val();
		var recommendFlag = $('#recommendFlag').val();
		var activityOrg = $('#activity_org').val();
		
		
		var url="${ctx}/activity/list?currentPage="+currentPage+"&pageSize="+pageSize;
	    if(activityTitle!="" && activityTitle!=null){
	    	activityTitle = encodeURI(encodeURI(activityTitle));
	    	url=url+"&activityTitle="+activityTitle;
	    }
	    if(parentCategory!="" && parentCategory!=null){
	    	url=url+"&parentCategory="+parentCategory;
	    }
	    if(beginTime !="" && beginTime != null){
	    	url=url+"&beginTime="+beginTime;
	    }
	    if(endTime !="" && endTime != null){
	    	url=url+"&endTime="+endTime;
	    }
	    if (!isEmpty(recommendFlag)) {
			url = url + "&recommendFlag=" + recommendFlag;
		}
	    if(activityOrg!="" && activityOrg!=null){
	    	activityOrg = encodeURI(encodeURI(activityOrg));
	    	url=url+"&activityOrg="+activityOrg;
	    }
	    
		location.href = url;
	}
	
	//新增
	function addActivity() {
		location.href = "${ctx}/activity/add";
	}
	
	/**
	 *修改
	 */
	 function updateActivity() {
		checkSelectActivity();
		if(!isEmpty(activityIds) && activityIds.length > 1) {
			swal('您选择了多条记录，请只选择一条记录！');
			return;
		}
		if(selectedActivityId === '') {
			swal('请选择一条记录！');
			return;
		}
		location.href = "${ctx}/activity/update?activityId="+selectedActivityId;
	 }
	
	/**
	 *删除
	 */
	 function deleteActivity() {
		 checkSelectActivity();
		 if(!isEmpty(activityIds) && activityIds.length > 1) {
			swal('您选择了多条记录，请只选择一条记录！');
			return;
		 }
		 if(selectedActivityId === '') {
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
					location.href="${ctx}/activity/"+selectedActivityId+"/delete";
				}
			});
	 }
	
	function goPage(num, size) {
		currentPage = num;
		pageSize = size;
		queryList();
	}
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>场馆列表</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/jquery-cityselect/css/main.css" />
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
            <div class="form-group form-inline" style="margin-bottom: 10px;">
            	<shiro:hasPermission name="add:sportsVenue">
	            	<button type="button" class="btn btn-primary" id="add_sportsVenue"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	            </shiro:hasPermission>
            	<shiro:hasPermission name="update:sportsVenue">
            		<button type="button" class="btn btn-info" id="update_sportsVenue"><i class="glyphicon glyphicon-pencil"></i>&nbsp;修改</button>
            		<!-- <button type="button" class="btn btn-success" id="recommend_sportsVenue"><i class="glyphicon glyphicon-send"></i>&nbsp;推荐</button> -->
            	</shiro:hasPermission>
            	<shiro:hasPermission name="delete:sportsVenue">
            		<button type="button" class="btn btn-danger" id="delete_sportsVenue"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</button>
            	</shiro:hasPermission>
				<input class="form-control col-md-offset-1" id="venueName" type="search" placeholder="请输入场馆名" style="width: 150px;"/>
				<span id="city_select">
					<select class="form-control prov" id="province">
						<option value="">请选择省份</option>
					</select> 
					<select class="form-control city" id="city" disabled="disabled">
						<option value="">请选择城市</option>
					</select>
					<select class="form-control dist" id="district" disabled="disabled">
						<option value="">请选择区县</option>
					</select>
				</span>
			  	<button id="search" type="button" class="btn btn-default" >搜索</button>
			</div>
			<table class="table table-bordered table-hover">
		    	<thead>
		            <tr>
		            	<th><input type="checkbox" id="selectAll"/></th>
		                <th>#</th>
		                <th>场馆名称</th>
		                <th>标志图</th>
		                <th>所在城市</th>
		                <th>详细地址</th>
		                <th>联系方式</th>
		                <th>场馆评分</th>
		                <th>场馆渠道</th>
		                <th>子场地数</th>
		                <th>场馆片区数</th>
		            </tr>
		        </thead>
		        <c:forEach items="${page.result}" var="sportsVenue" varStatus="status" > 
			        <tbody id="${sportsVenue.venueId}" venue-name="${sportsVenue.venueName}">
			        	<tr>
			        		<td width="30px;"><input type="checkbox" id="prev${sportsVenue.venueId}"/></td>
			        		<td>${sportsVenue.venueId}</td>
			        		<td>${sportsVenue.venueName}</td>
			        		<td>
			        			<a href="${image}${sportsVenue.venueLogo}" data-lightbox="roadtrip">
			        				<img src="${image }${sportsVenue.venueLogo}" width="32px" height="32px" style="margin-right:1px;border-radius: 5px;"/>
			        			</a>
			        		</td>
			        		<td>${sportsVenue.province}&nbsp;${sportsVenue.city}&nbsp;${sportsVenue.district}</td>
			        		<td>${sportsVenue.address}</td>
			        		<td>${sportsVenue.venueContact}</td>
			        		<td>${sportsVenue.venueGrade}</td>
			        		<td>
			        			<c:if test="${sportsVenue.venueChannel eq 1}">
			        				自营
			        			</c:if>
			        			<c:if test="${sportsVenue.venueChannel eq 2}">
			        				合约制
			        			</c:if>
			        			<c:if test="${sportsVenue.venueChannel eq 3}">
			        				第三方
			        			</c:if>
			        		</td>
			        		<td><a href="#" onclick="viewChildVenue(this, event);" title="查看子场地">${sportsVenue.venueChileCount}</a></td>
			        		<td width="260px;">
				        		<c:forEach items="${sportsVenue.venueDistrictCounts}" var="venueDistrict" >
				        			${ venueDistrict.childCenueName }<a href="#" child-venue-id="${ venueDistrict.chidVenueId }" child-venue-name="${ venueDistrict.childCenueName }" onclick="viewVenueDistrict(this, event);" title="查看场馆片区">(${venueDistrict.districtCount})&nbsp;</a>
				        		</c:forEach>
			        		</td>
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
			        <h4 class="modal-title" id="myModalLabel">场馆推荐</h4>
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
			var sportsVenueIds = [];//多选时记录场馆id
			var selectedSportsVenueId = "";//单选时记录场馆id
			var currentPage = '${page.currentPage}';
			var pageSize = '${page.pageSize}';	
			
			
			$(function(){
				//初始化查询条件
				$('#venueName').val('${page.params.venueName}');
				var province = '${page.params.province}';
				var city = '${page.params.city}';
				var district = '${page.params.district}';
				if(isEmpty(province)) {
					province = "广东省";
				}
				if(isEmpty(city)) {
					//city = "广州";
				}
				if(isEmpty(district)) {
					//district = "天河区";
				}
				
				$("#city_select").citySelect({
					url:"${ctx }/resources/libs/jquery-cityselect/city.json",
					prov: province, 
					city: city,
					dist: district,
					required: false,
					nodata:"none"
				});
				
				$("tbody").find('input:checkbox:checked').prop('checked', false);
				$('#add_sportsVenue').on("click",addSportsVenue);	
				$("#delete_sportsVenue").on("click",deleteSportsVenue);	
				$('#update_sportsVenue').on('click', updateSportsVenue);
				$('#selectAll').on('click', selectAll);
				//$('#recommend_sportsVenue').on('click', updateSportsVenue);
				
				$("#venueName").on("keypress",function(event){
					if(event.which==13){
						queryList();
					}
				});	
				//$("#province").on("change",queryList);
				$("#district").on("change",queryList);
				$("#city").on("change",queryList);
				$("#search").on("click",queryList);
				
				$("tbody").each(function(index) {
					var id = $(this).attr('id');
					$(this).find('input').on('click', function(e) {
						/* if(id != selectedSportsVenueId) {
							$("#prev"+selectedSportsVenueId).prop('checked', false);
						} */
						var input = $(e.target);
						var checked = input.prop('checked');
						if(checked) {
							selectedSportsVenueId = id;
						}else {
							selectedSportsVenueId = "";
						}
						e.stopPropagation();
					});
				});
				
				$("tbody").on("click",function(){
					//只允许同时选中一个
					/* if($(this).attr('id') != selectedSportsVenueId) {
						$("#prev"+selectedSportsVenueId).prop('checked', false);
					} */
					
					//设置选择或未选中
					var input = $(this).find('input');
					var checked = input.prop('checked');
					if(checked) {
						input.prop('checked', false);
						selectedSportsVenueId = "";
					}else {
						input.prop('checked', true);
						selectedSportsVenueId = $(this).attr('id');
					}
				});
			});
			
			//场馆全选
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
				var sportsVenueId = $(dom).parent().parent().parent().parent().attr('id');
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
							location.href = "${ctx}/sportsvenues/modify?sportsVenueId=" + sportsVenueId +"&recommendFlag=" + recommendFlag;
						}
					});
				}
				
				$("#save_order").on("click",function() {
					recommendOrder = $('#recommend_order').val();
					if(isEmpty(recommendOrder)) {
						swal('推荐顺序不能为空，请重新输入！');
						return false;
					}
					location.href = "${ctx}/sportsvenues/modify?sportsVenueId=" + sportsVenueId +"&recommendOrder="+ recommendOrder +"&recommendFlag=" + recommendFlag;
				});
				
				event.stopPropagation();
			}

			//启用/禁用场馆
			function changeState(sportsVenueState, dom, event) {
				var sportsVenueId = $(dom).parent().parent().parent().parent().attr('id');
				if(sportsVenueState == 0) {
					swal({
						title : "",
						text : "确认冻结该场馆？",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#337ab7",
						confirmButtonText : "确认",
						cancelButtonText : "取消",
						closeOnConfirm : true,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "${ctx}/sportsvenues/modify?sportsVenueId=" + sportsVenueId+ "&sportsVenueState=" + sportsVenueState;
						}
					});
				}else {
					location.href = "${ctx}/sportsvenues/modify?sportsVenueId=" + sportsVenueId+ "&sportsVenueState=" + sportsVenueState;
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
						var sportsVenueId = recommend.parent().parent().parent().parent().attr('id');
						recommendOrder = recommend.val();
						if(isEmpty(recommendOrder)) {
							swal('推荐顺序不能为空，请重新输入！');
							return;
						}
						location.href = "${ctx}/sportsvenues/modify?sportsVenueId=" + sportsVenueId +"&recommendOrder="+ recommend.val();
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
				var venueName = $('#venueName').val();
				var province = $('#province').val();
				var city = $('#city').val();
				var district = $('#district').val();

				var url = "${ctx}/sportsvenues/list?currentPage="+currentPage+"&pageSize="+pageSize;
				if (!isEmpty(venueName)) {
					venueName = encodeURI(encodeURI(venueName));
					url = url + "&venueName=" + venueName;
				}
				if (!isEmpty(province)) {
					province = encodeURI(encodeURI(province));
					url = url + "&province=" + province;
				}
				if (!isEmpty(city)) {
					city = encodeURI(encodeURI(city));
					url = url + "&city=" + city;
				}
				if (!isEmpty(district)) {
					district = encodeURI(encodeURI(district));
					url = url + "&district=" + district;
				}

				location.href = url;
			}

			/* //批量推荐场馆
			function updateSportsVenue() {
				checkSelectVenue();
				if(isEmpty(sportsVenueIds)) {
					swal('请选择需要推荐的场馆！');
					return false;
				}
				location.href = "${ctx}/sportsvenues/batchRecommend?sportsVenueIds="+sportsVenueIds;
			} */
			
			//新增场馆信息
			function addSportsVenue() {
				location.href = "${ctx}/sportsvenues/add";
			}
			
			/**
			 *编辑场馆信息
			 */
			function updateSportsVenue() {
				checkSelectVenue();
				if(!isEmpty(sportsVenueIds) && sportsVenueIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedSportsVenueId === '') {
					swal('请选择一条记录！');
					return;
				}
				location.href = "${ctx}/sportsvenues/" + selectedSportsVenueId + "/update";
			}

			/**
			 *删除
			 */
			function deleteSportsVenue() {
				checkSelectVenue();
				if(!isEmpty(sportsVenueIds) && sportsVenueIds.length > 1) {
					swal('您选择了多条记录，请只选择一条记录！');
					return;
				}
				if (selectedSportsVenueId === '') {
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
						location.href = "${ctx}/sportsvenues/" + selectedSportsVenueId + "/delete";
					}
				});
			}
			
			//检查选中行数
			function checkSelectVenue() {
				sportsVenueIds = [];
				$('tbody').find('input[type="checkbox"]').each(function() {
					if($(this).prop('checked')) {
						sportsVenueIds.push($(this).parent().parent().parent().attr('id'));
					}
				});
				
				if(!isEmpty(sportsVenueIds) && sportsVenueIds.length == 1) {
					selectedSportsVenueId = sportsVenueIds[0];
				}
			}
			
			//查看子场地列表
			function viewChildVenue(dom, event) {
				var venueName = $(dom).parent().parent().parent().attr('venue-name');
				var venueId = $(dom).parent().parent().parent().attr('id');
				
				var url = "${ctx}/sportsvenueschild/list?currentPage=1&venueId="+venueId+"&venueName="+encodeURI(encodeURI(venueName));
				location.href= url;
				event.stopPropagation();
			}
			
			//查看场馆片区列表
			function viewVenueDistrict(dom, event) {
				var childVenueName = $(dom).attr('child-venue-name');
				var childVenueId = $(dom).attr('child-venue-id');
				
				var url = "${ctx}/sportvenuedistrict/list?currentPage=1&childVenueId="+childVenueId+"&childVenueName="+encodeURI(encodeURI(childVenueName));
				location.href= url;
				event.stopPropagation();
			}

			function goPage(num, size) {
				currentPage = num;
				pageSize = size;
				queryList();
			}
		</script>
    </body>

</html>
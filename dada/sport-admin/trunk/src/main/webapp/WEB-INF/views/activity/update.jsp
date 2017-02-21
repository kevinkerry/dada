<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改活动信息</title>
        <link rel="stylesheet" href="${ctx }/resources/assets/styles.css" />
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
		<style type="text/css">
			#mapContainer{height:480px;}
			#result1{
				position:absolute;
				z-index: 100;
				background-color: white;
			}
			#imglist{
				display: inline-flex;
				
			}
			.imgbox{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
				margin-left: 1rem;
			}
			.imgbox img{
				z-index:100;float: right;margin-top: -10px;margin-right:-25px; 
			}
			
			#logoimage{
				display: inline-flex;
				
			}
			.imgbox_logo{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
			}
			.imgbox_logo img{
				z-index:100;float: right;margin-top: -10px;margin-right:-10px; 
			}
			.btn-upload, .logo-image {
			    display: inline-block;
			    width: 72px;
			    height: 72px;
			    background: url(${ctx}/resources/images/addimage.png) no-repeat;
			    background-size: contain;
			    margin-left: 1rem;
			}
			.btn-upload>input {
			    opacity: 0;
			    height: 100%;
			    width: 100%;
			}
		</style>
		<script src="${ctx }/resources/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
		<script type="text/javascript">
			var ctx = '${ctx}';
		</script>
	</head>
	<body>
   		<div class="container-fluid">
	        <div class="row-fluid">
				<div id="content">
					<form id="activity_form" class="form-horizontal"  action="${ctx}/activity/update" method="post">
						<input type="hidden" name="activityId" value="${activity.activityId}">
						<input type="hidden" name="type" value="${activity.type}"/>
						<div class="form-group">
							<label class="col-sm-1 control-label" >主题</label>
							<div class="col-sm-2">
								<input class="input-xlarge form-control" name="activityTitle" type="text" value="${activity.activityTitle}" required />
							</div>
							<label class="col-sm-1 control-label" >活动渠道</label>
							<div class="col-sm-1" style="margin-top: 5px;">
								<c:if test="${activity.type eq 0}">
									内部活动
								</c:if>
								<c:if test="${activity.type eq 1}">
									外部活动
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label" >活动封面</label>
							<c:if test="${!empty activity.logoUrl}">
								<div class="col-sm-1 btn-upload logo-upload" style="display: none;">
									<input id="logoupload" type="file" />
								</div>
								<div class="col-sm-1" id="logoimage">
									<div class="imgbox_logo" data-src="${activity.logoUrl}" style="z-index:1;background-image: url('${image}${activity.logoUrl}');">
										<img src="${ctx}/resources/images/del.png" width="20px" height="20px;" />
									</div>
								</div>
							</c:if>
							<c:if test="${empty activity.logoUrl}">
								<div class="col-sm-1 btn-upload logo-upload">
									<input id="logoupload" type="file" />
								</div>
								<div class="col-sm-1" id="logoimage" style="display: none;">
									<div class="imgbox_logo" data-src="${activity.logoUrl}" style="z-index:1;background-image: url('${image}${activity.logoUrl}');">
										<img src="${ctx}/resources/images/del.png" width="20px" height="20px;" />
									</div>
								</div>
							</c:if>
							<div class="form-group" id="logo_image" style="display: none"></div>
							<c:if test="${activity.type eq 0}">
								<label class="col-sm-1 control-label activityImage" >活动照片</label>
								<div id="imglist" ondrop="drop(event);" ondragover="allowDrop(event);" style="float: left;">
									<c:forEach items="${activity.activityImages}" var="activityImage" varStatus="status">
										<div class="imgbox col-sm-1" id='imgBox${status.index}' data-src="${activityImage.imgUrl}" data-id="${activityImage.imgId}" style="z-index:1;background-image: url('${image}${activityImage.imgUrl}');" draggable="true" ondragstart="drag(event);">
											<c:if test="${not empty activityImage}">
												<img src="${ctx }/resources/images/del.png" width="20px" height="20px;" />
											</c:if>
										</div>
									</c:forEach>
								</div>
								<div class="col-sm-1 btn-upload image-upload activityImage" style="margin-top: 0px;">
									<input id="image_upload" type="file" multiple="multiple"/>
								</div>
								<div class="form-group" id="img_list" style="display: none"></div>
							</c:if>
						</div>
						<c:if test="${activity.type eq 0}">
							<div class="form-group">
								<label class="control-label col-sm-1" >活动详情</label>
								<div class="col-sm-4">
									<textarea class="form-control" maxlength="200" name="activityDesc" style="resize: none;" rows="5">${activity.activityDesc}</textarea>
								</div>
							</div>  	
					   </c:if>
					   <div class="form-group">
							<label class="control-label col-sm-1" >活动分类</label>
							<div class="col-sm-2">
								<select class="form-control" id="parent_category" name="parentCategory" required>
						          <option value="">--请选择活动大类--</option>
						        </select>
					        </div>
					        <div class="col-sm-2">
						        <select class="form-control" id="category_code" name="categoryCode" required>
						          <option value="">--请选择活动小类--</option>
						        </select>
					        </div>
						</div>  
						<div class="form-group">
							<label class="control-label col-sm-1" >报名时间</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="applyStartTime" type="datetime-local" style="width: 170px;" value="<fmt:formatDate value="${activity.startApplyTime}" pattern='yyyy-MM-dd'/>T<fmt:formatDate value="${activity.startApplyTime}" pattern='HH:mm'/>" required />
							</div>
							<label class="control-label col-sm-1" style="margin-left:42px;width: 10px;">至</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="applyEndTime" type="datetime-local" style="width: 170px;" value="<fmt:formatDate value="${activity.endApplyTime}" pattern='yyyy-MM-dd'/>T<fmt:formatDate value="${activity.endApplyTime}" pattern='HH:mm'/>" required />
							</div>
						</div>   
						<div class="form-group">
							<label class="control-label col-sm-1" >活动时间</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="actStartTime" type="datetime-local" style="width:170px ;" value="<fmt:formatDate value="${activity.beginTime}" pattern='yyyy-MM-dd'/>T<fmt:formatDate value="${activity.beginTime}" pattern='HH:mm'/>" required />
							</div>
							<label class="control-label col-sm-1" style="margin-left:42px;width: 10px;">至</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="actEndTime" type="datetime-local" style="width:170px ;" value="<fmt:formatDate value="${activity.endTime}" pattern='yyyy-MM-dd'/>T<fmt:formatDate value="${activity.endTime}" pattern='HH:mm'/>" required />
							</div>
						</div>   
						<div class="form-group">
							<label class="control-label col-sm-1" >地点</label>
							<div class="col-sm-1">
								<select class="input-sm form-control" name="province" id="province"></select>	
							</div>
							<div class="col-sm-1">
								<select class="input-sm form-control" style="margin-left: -20px;" name="city" id="city"></select>
							</div>
							<div class="col-sm-1">
								<select class="input-sm form-control" style="margin-left: -40px;" name="district" id="area"></select>
							</div>
							<div class="col-sm-2">
								<input  class="input-sm form-control" type="text" style="margin-left:-60px;" id="keyword" placeholder="具体路名、街道、门牌" name="address" value="${activity.address}" onkeydown='keydown(event)' autocomplete="off"/>
							</div>
							<div class="col-sm-1">
								<i class="glyphicon glyphicon-map-marker" id="marker" style="margin-left: -80px;font-size: 2.5rem;color: #CD6839;"></i>
							</div>
						</div>   
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-3" >
								<div id="result1" name="result1" style="margin-left: 7rem;margin-top: -1rem;"></div>
							</div>
						</div>
						<div class="form-group map-container" style="display: none;">
							<div id="mapContainer" class="col-sm-5 col-sm-offset-1"></div>
						</div>
						<%-- <div class="form-group">
							<div class="col-sm-2 col-sm-offset-1">
								<input class="form-control" type="hidden" value="${activity.gpsX}" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
							</div>
							<div class="col-sm-2">
				    			<input class="form-control" type="hidden" value="${activity.gpsY}" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
				    		</div>
						</div> --%>
						<input class="form-control" type="hidden" value="${activity.gpsX}" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
						<input class="form-control" type="hidden" value="${activity.gpsY}" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
						<c:if test="${activity.type eq 0}">
							<div class="form-group">
								<label class="control-label col-sm-1" >交通路线</label>
								<div class="col-sm-4">
									<textarea class="form-control" name="busInformation" maxlength="200" style="resize: none;" rows="3">${activity.busInformation}</textarea>
								</div>
							</div>  
						</c:if>	
						<div class="form-group">
							<label class="control-label col-sm-1" >主办方</label>
							<div class="col-sm-3">
								<input class="input-xlarge form-control" name="activityOrg" type="text" value="${activity.activityOrg}"/>
							</div>
						</div>
						<c:if test="${activity.type eq 0}">
							<div class="form-group">
								<label class="control-label col-sm-1" >联系电话</label>
								<div class="col-sm-2">
									<input class="input-xlarge form-control" name="contact" type="tel" value="${activity.contact}"/>
								</div>
							</div>   
						</c:if>	
						
						<c:if test="${activity.type eq 0}">
							<div class="form-group">
								<label class="control-label col-sm-1" >友情提示</label>
								<div class="col-sm-4">
									<textarea class="input-xlarge form-control" maxlength="200" name="tips" style="resize: none;" rows="4">${activity.tips}</textarea>
								</div>
							</div>  
					   </c:if>
					   <c:if test="${activity.type eq 1}">
						   <div class="form-group">
								<label class="col-sm-1 control-label" >活动链接地址</label>
								<div class="col-sm-3">
									<input class="input-xlarge form-control" name="linkAddress" type="text" value="${activity.linkAddress}" required />
								</div>
							</div>
						</c:if>
						<div class="form-group" style="margin-left: 400px;">
							<button id="sumit" class="btn btn-primary">提交</button>
							<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
						</div>
				</form>
					<!--内容结束-->
                </div>
            </div>
        </div>
        
		<script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
		<script src="${ctx }/resources/assets/scripts.js"></script>
		<script src="${ctx }/resources/js/activity/region_select.js"></script>
		<script >
			var categories;
			new PCAS($('.form-horizontal #province')[0], $('.form-horizontal #city')[0],
				$('.form-horizontal #area')[0], '${activity.province}', '${activity.city}', '${activity.district}');
			
			$(function() {
				 $('#parent_category').bind("change", getChildCategory);
				 getParentCategory();
				 
				 $('#marker').on('mouseover', function() {
					 $('.map-container').show();
				 });
				 $(document).on('click', function() {
					 $('.map-container').hide();
				 });
				 $('#mapContainer').on('click', function(e) {
					 e.stopPropagation();
				 });
				 $('#province').on('click', function(e) {
					 e.stopPropagation();
				 });
				 $('#city').on('click', function(e) {
					 e.stopPropagation();
				 });
				 $('#area').on('click', function(e) {
					 e.stopPropagation();
				 });
				 $('#keyword').on('click', function(e) {
					 e.stopPropagation();
				 });
				 $('#result1').on('click', function(e) {
					 e.stopPropagation();
				 });
			})
		
			function initForm() {
				$('#parent_category').val('${activity.parentCategory}');
				$('#category_code').val('${activity.categoryCode}');
				
				//重新在地图上显示定位
				addMarker_location();
			}
			
			function getParentCategory() {
				$.ajax({
				   url: "${ctx}/category/list",
				   success: function(msg){
					   categories = msg.results.categories;
					   $.each( categories, function(i, category){
						   if(category.parentId == -1) {
							   $('#parent_category').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
						   }else if(category.parentCode == '${activity.parentCategory}'){
							   $('#category_code').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
						   }
						});
					   
					   initForm();
				   }
				});
			}
			
			function getChildCategory(e) {
				var parentCategory = $(this).children('option:selected').val();
				$('#category_code').html("");
				$('#category_code').append('<option value="">--请选择活动小类--</option>');
				$.each( categories, function(i, category){
				   if(category.parentId != -1 && category.parentCode == parentCategory) {
					   $('#category_code').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
				   }
				});
			}
			
			//上传活动封面照片
			function uploadLogImg() {
				$('#logoupload').click();
			}
			
			//上传活动照片
			function uploadImgs() {
				$('#fileupload').click();
			}
		</script>	
	</body>
	<script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
	<script src="${ctx }/resources/js/activity/upload.js"></script>
	<script src="http://webapi.amap.com/maps?v=1.3&key=20090d8e72bfdc1311d91cdc596ec543"></script>
	<script src="${ctx }/resources/js/activity/map.js"></script>
	<script src="${ctx }/resources/js/activity/update.js"></script>
</html>
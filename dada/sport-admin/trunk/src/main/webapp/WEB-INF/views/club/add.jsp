<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<html>
    <head>
        <title>新增俱乐部</title>
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
    </head>
    
    <body>
        <div class="container-fluid" style="margin-top: 30px;">
            <div class="row-fluid">
				<form id="club_form" class="form-horizontal"  action="${ctx}/club/add" method="post">
					<div class="form-group">
						<label class="col-md-1 control-label" >俱乐部名称：</label>
						<div class="col-md-2">
							<input class="form-control" name="clubName" type="text" required/>
						</div>
						<label class="control-label col-md-1" >俱乐部文化：</label>
						<div class="col-md-3">
							<input class="form-control" name="clubCulture" type="text" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-1" >是否收费：</label>
						<div class="col-md-2">
							<label class="radio-inline">
							  <input type="radio" name="feeFlag" id="feeFlag1" value="1" required="required"> 收费
							</label>
							<label class="radio-inline">
							  <input type="radio" name="feeFlag" id="feeFlag2" value="0" required="required"> 不收费
							</label>
						</div>
						<label class="control-label col-md-1">收费备注：若收费请填写费用格式如：38.00</label>
						<div class="col-md-3">
							<input class="form-control" name="feeComments" type="text"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label" >俱乐部标志图：</label>
						<div class="col-sm-1 btn-upload logo-upload">
							<input id="logoupload" type="file" />
						</div>
						<div class="col-sm-1" id="logoimage" style="display: none;"></div>
						<div class="form-group" id="logo_image" style="display: none"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-1" >俱乐部简介：</label>
						<div class="col-md-5">
							<textarea class="form-control" maxlength="200" name="clubDesc" style="resize: none;" rows="5"></textarea>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-md-1" >俱乐部创建者id：</label>
						<div class="col-md-3">
							<input class="form-control" name="creator" type="text"/>
						</div>
					</div> 
					
					<div class="form-group">
						<label class="control-label col-sm-1" >俱乐部分类</label>
						<div class="col-sm-2">
							<select class="form-control" id="parent_category" name="parentCategory" required>
					          <option value="">--请选择大类--</option>
					        </select>
				        </div>
				        <div class="col-sm-2">
					        <select class="form-control" id="category_code" name="categoryCode" required>
					          <option value="">--请选择小类--</option>
					        </select>
				        </div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-1" >俱乐部位置：</label>
						<div class="col-sm-1">
							<select class="input-sm form-control" name="province" id="province" disabled="disabled"></select>	
						</div>
						<div class="col-sm-1">
							<select class="input-sm form-control" style="margin-left: -20px;" name="city" id="city"></select>
						</div>
						<div class="col-sm-1">
							<select class="input-sm form-control" style="margin-left: -40px;" name="district" id="area"></select>
						</div>
						<div class="col-sm-2">
							<input class="input-sm form-control" style="margin-left: -60px;" type="text" id="keyword" placeholder="具体路名、街道、门牌" name="address" onkeydown='keydown(event)' autocomplete="off"/>
						</div>
						<div class="col-sm-1">
							<i class="glyphicon glyphicon-map-marker" id="marker" style="margin-left: -80px;font-size: 2.5rem;color: #CD6839;"></i>
						</div>
					</div>   
					<div class="form-group">
						<div class="col-sm-3 col-sm-offset-3" >
							<div id="result1" name="result1" style="margin-left: 7rem;margin-top: -1rem;"></div>
						</div>
					</div>
					
					<div class="form-group map-container" style="display: none;">
						<div id="mapContainer" class="col-sm-5 col-sm-offset-1"></div>
					</div>
					<input class="form-control" type="hidden" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
					<input class="form-control" type="hidden" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
					<div class="form-group" style="margin-left: 400px;">
						<button id="sumit" class="btn btn-primary">提交</button>
						<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
					</div>
				</form>
            </div>
        </div>
        
		<script src="${ctx }/resources/libs/jquery-raty/jquery.raty.js" type="text/javascript"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
 		<script src="${ctx }/resources/js/club/region_select.js"></script>
 		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
		<script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
		<script src="http://webapi.amap.com/maps?v=1.3&key=20090d8e72bfdc1311d91cdc596ec543"></script>
		<script src="${ctx }/resources/js/club/map.js"></script>
		<script src="${ctx }/resources/js/club/add.js"></script>
		<script >
			var ctx = '${ctx}';
			$(function() {
				new PCAS($('.form-horizontal #province')[0], $('.form-horizontal #city')[0],
					$('.form-horizontal #area')[0], '广东省', '广州市', '不限');
				
				getParentCategory();
				$('#parent_category').bind("change", getChildCategory);
				$('#club_form input[name="feeFlag"]').on('change',function() {
					var feeFlag = $(this).val();
					if(feeFlag == 1) {
						$('#club_form input[name="feeComments"]').attr('readonly', false);
					}else {
						$('#club_form input[name="feeComments"]').attr('readonly', true);
					}
				});
				
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
			});
			
			function getParentCategory() {
				$.ajax({
				   url: "${ctx}/category/list",
				   success: function(msg){
					   categories = msg.results.categories;
					   $.each( categories, function(i, category){
						   if(category.parentId == -1) {
							   $('#parent_category').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
						   }
						});
				   }
				});
			}
			
			function getChildCategory(e) {
				var parentCategory = $(this).children('option:selected').val();
				$('#category_code').html("");
				$('#category_code').append('<option value="">--请选择小类--</option>');
				$.each( categories, function(i, category){
					   if(category.parentId != -1 && category.parentCode == parentCategory) {
						   $('#category_code').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
					   }
					});
			}
			
			//上传俱乐部封面照片
			function uploadLogImg() {
				$('#logoupload').click();
			}
		</script>	
    </body>
</html>

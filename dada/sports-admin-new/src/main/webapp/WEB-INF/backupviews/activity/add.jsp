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
                <!--/span-->
                <div id="content">
				<!--内容开始 action="${ctx}/activity/add"-->
					<form id="activity_form" class="form-horizontal"  action="${ctx}/activity/add" method="post">
						<div class="form-group">
							<label class="col-sm-1 control-label" >主题</label>
							<div class="col-sm-2">
								<input class="input-xlarge form-control" name="activityTitle" type="text" required />
							</div>
							<label class="col-sm-1 control-label" >活动渠道</label>
							<div class="col-sm-1">
								<select id="typeSelect" class="form-control" name="type">
									<option value="0">内部活动</option>
									<option value="1">外部活动</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label" >活动封面</label>
							<div class="col-sm-1 btn-upload logo-upload">
								<input id="logoupload" type="file" />
							</div>
							<div class="col-sm-1" id="logoimage" style="display: none;"></div>
							<div class="form-group" id="logo_image" style="display: none"></div>
							
							<label class="col-sm-1 control-label activityImage" >活动照片</label>
							<div id="imglist" ondrop="drop(event);" ondragover="allowDrop(event);" style="float: left;">
							</div>
							<div class="col-sm-1 btn-upload image-upload activityImage" style="margin-top: 0px;">
								<input id="image_upload" type="file" multiple="multiple"/>
							</div>
							<div class="form-group" id="img_list" style="display: none"></div>
						</div>
						<div class="form-group" id="activityDesc">
							<label class="control-label col-sm-1" >活动详情</label>
							<div class="col-sm-4">
								<textarea class="form-control" maxlength="200" name="activityDesc" style="resize: none;" rows="5"></textarea>
							</div>
						</div>  
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
								<input class="input-sm form-control" name="applyStartTime" type="datetime-local" style="width: 170px;" required />
							</div>
							<label class="control-label col-sm-1" style="margin-left:42px;width: 10px;">至</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="applyEndTime" type="datetime-local" style="width: 170px;" required />
							</div>
						</div>   
					 	
						<div class="form-group">
							<label class="control-label col-sm-1" >活动时间</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="actStartTime" type="datetime-local" style="width:170px ;" required />
							</div>
							<label class="control-label col-sm-1" style="margin-left:42px;width: 10px;">至</label>
							<div class="col-sm-1">
								<input class="input-sm form-control" name="actEndTime" type="datetime-local" style="width:170px ;" required />
							</div>
						</div>   
						
						<div class="form-group">
							<label class="control-label col-sm-1" >详细地址</label>
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
						<!-- <div class="form-group" >
							<div class="col-sm-2 col-sm-offset-1">
								<input class="form-control" type="hidden" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
							</div>
							<div class="col-sm-2">
				    			<input class="form-control" type="hidden" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
				    		</div>
						</div> -->
						<input class="form-control" type="hidden" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
						<input class="form-control" type="hidden" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
						<div class="form-group" id="busInformation">
							<label class="control-label col-sm-1" >交通路线</label>
							<div class="col-sm-4">
								<textarea class="input-xlarge form-control" name="busInformation" maxlength="200" style="resize: none;" rows="3"></textarea>
							</div>
						</div>  	
						<div class="form-group" id="activityOrg">
							<label class="control-label col-sm-1" >主办方</label>
							<div class="col-sm-2">
								<input class="input-xlarge form-control" name="activityOrg" type="text"/>
							</div>
						</div>
						<div class="form-group" id="contact">
							<label class="control-label col-sm-1" >联系电话</label>
							<div class="col-sm-2">
								<input class="input-xlarge form-control" name="contact" type="tel"/>
							</div>
						</div>   
					
						<div class="form-group" id="tips">
							<label class="control-label col-sm-1" >友情提示</label>
							<div class="col-sm-4">
								<textarea class="input-xlarge form-control" maxlength="200" name="tips" style="resize: none;" rows="5"></textarea>
							</div>
						</div> 
						<div class="form-group" id="linkAddress" style="display: none;">
							<label class="col-sm-1 control-label" >活动链接地址</label>
							<div class="col-sm-2">
								<input class="input-xlarge form-control" name="linkAddress" type="text"/>
							</div>
						</div>
						<div class="form-group" style="margin-left: 400px;">
							<button id="sumit" class="btn btn-primary">提交</button>
							<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
						</div>
					</form>
                </div>
            </div>
        </div>
        <!--/.fluid-container-->

        <script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
        <script src="${ctx }/resources/assets/scripts.js"></script>
 		<script src="${ctx }/resources/js/activity/region_select.js"></script>
<script >
		var ctx = "${ctx}";
		var categories;
		new PCAS($('.form-horizontal #province')[0], $('.form-horizontal #city')[0],
			$('.form-horizontal #area')[0], '广东省', '广州市', '不限');
		
		$(function() {
			 $('#parent_category').bind("change", getChildCategory);
			 $('#typeSelect').bind("change", changeType);
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
		});
	
		function changeType(e) {
			var typeSelect = $(this).val();
			if(typeSelect == 0) {
				//$('#activity_form')[0].reset();
				$('#imglist').css('display', 'block');
				$('.activityImage').css('display', 'block');
				$('#activityOrg').css('display', 'block');
				$('#contact').css('display', 'block');
				$('#activityDesc').css('display', 'block');
				$('#tips').css('display', 'block');
				$('#busInformation').css('display', 'block');
				$('#linkAddress').css('display', 'none');
			}else if(typeSelect == 1) {
				//$('#activity_form')[0].reset();
				$('#imglist').css('display', 'none');
				$('.activityImage').css('display', 'none');
				$('#activityOrg').css('display', 'block');
				$('#contact').css('display', 'none');
				$('#activityDesc').css('display', 'none');
				$('#tips').css('display', 'none');
				$('#busInformation').css('display', 'none');
				$('#linkAddress').css('display', 'block');
			}
		}
		
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

</html>


<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
<script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>

<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
<script src="${ctx }/resources/js/activity/upload.js"></script>

<script src="http://webapi.amap.com/maps?v=1.3&key=20090d8e72bfdc1311d91cdc596ec543"></script>
<script src="${ctx }/resources/js/activity/map.js"></script>

<script src="${ctx }/resources/js/activity/app.js"></script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增场馆</title>
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
				<form id="venue_form" class="form-horizontal"  action="${ctx}/sportsvenues/add" method="post">
					<div class="form-group">
						<label class="col-md-1 control-label" >场馆名称</label>
						<div class="col-md-2">
							<input class="form-control" name="venueName" type="text" required />
						</div>
						<label class="control-label col-md-1" >联系电话</label>
						<div class="col-md-2">
							<input class="form-control" name="venueContact" type="tel" required="required"/>
						</div>
						<label class="col-md-1 control-label" >场馆评价</label>
						<div class="col-md-3">
							<span id="star"></span>
							<span id="mouseover-target" style="color: #FFA54F;size: 24;"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label" >场馆标志图</label>
						<div class="col-sm-1 btn-upload logo-upload">
							<input id="logoupload" type="file" />
						</div>
						<div class="col-sm-1" id="logoimage" style="display: none;"></div>
						<div class="form-group" id="logo_image" style="display: none"></div>
						
						<label class="col-md-1 control-label" >场馆照片</label>
						<div id="imglist" ondrop="drop(event);" ondragover="allowDrop(event);" style="float: left;">
						</div>
						<div class="col-sm-1 btn-upload image-upload" style="margin-top: 0px;">
							<input id="image_upload" type="file" multiple="multiple"/>
						</div>
						<div class="form-group" id="img_list" style="display: none"></div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-1" >场馆服务</label>
						<div class="col-md-8">
							<label class="checkbox-inline">
								<input type="checkbox" id="facility1" name="facility" value="wifi"> wifi
							</label>
							<label class="checkbox-inline">
							  	<input type="checkbox" id="facility2" name="facility" value="淋浴"> 淋浴
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="facility3" name="facility" value="停车"> 停车
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="facility4" name="facility" value="餐饮"> 餐饮
							</label>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-md-1" >场馆简介</label>
						<div class="col-md-4">
							<textarea class="form-control" maxlength="200" name="venueDesc" style="resize: none;" rows="5"></textarea>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-md-1" >场馆位置</label>
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
					<!-- <div class="form-group">
						<div class="col-md-2 col-md-offset-1">
							<input class="form-control" type="hidden" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
						</div>
						<div class="col-md-2">
			    			<input class="form-control" type="hidden" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
			    		</div>
					</div> -->
					<input class="form-control" type="hidden" id="GPS_X" name="gpsX" placeholder="X" readOnly="true"/>
					<input class="form-control" type="hidden" id="GPS_Y" name="gpsY" placeholder="Y" readOnly="true"/>
					<div class="form-group" id="busInformation">
						<label class="control-label col-md-1" >交通路线</label>
						<div class="col-md-4">
							<textarea class="form-control" name="busInformation" maxlength="200" style="resize: none;" rows="4"></textarea>
						</div>
					</div>  	
					<div class="form-group" style="margin-left: 400px;">
						<button id="sumit" class="btn btn-primary">提交</button>
						<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
					</div>
				</form>
            </div>
        </div>
        
		<script src="${ctx }/resources/libs/jquery-raty/jquery.raty.js" type="text/javascript"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
 		<script src="${ctx }/resources/js/sportsvenue/region_select.js"></script>
 		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
		<script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
		<script src="http://webapi.amap.com/maps?v=1.3&key=20090d8e72bfdc1311d91cdc596ec543"></script>
		<script src="${ctx }/resources/js/sportsvenue/map.js"></script>
		<script src="${ctx }/resources/js/sportsvenue/add.js"></script>
		<script >
			var ctx = '${ctx}';
			$(function() {
				new PCAS($('.form-horizontal #province')[0], $('.form-horizontal #city')[0],
					$('.form-horizontal #area')[0], '广东省', '广州市', '不限');
				
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
				
				 //星级评分
				 $('#star').raty({
					 path: '${ctx}/resources/libs/jquery-raty/img',
					 hints: ['', '', '', '', ''],
					 //starHalf: 'star-half-big.png', 
					 starOff: 'star-off-big.png',
					 starOn: 'star-on-big.png',
					 size: 24,
					 //half: true,
					 scoreName: 'venueGrade',
					 mouseover : function(score, evt) {
					    var target = $('#mouseover-target');

					    if (score === null) {
					      target.html('Boring!');
					    } else if (score === undefined) {
					      target.empty();
					    } else {
					      target.html(score+"分");
					    }
					  }
				 });
			});
			
			//上传场馆封面照片
			function uploadLogImg() {
				$('#logoupload').click();
			}
			
			//上传场馆照片
			function uploadImgs() {
				$('#fileupload').click();
			}
		</script>	
    </body>
</html>

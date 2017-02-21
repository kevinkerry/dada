<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看会员信息</title>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
        <link rel="stylesheet" href="${ctx}/resources/libs/carousel/css/owl.carousel.css">
		<link rel="stylesheet" href="${ctx}/resources/libs/carousel/css/owl.theme.css">
        <style type="text/css">
        	#imglist{
				display: inline-flex;
				
			}
			.imgbox{
				width:100px;
				height:100px;
				background-size: cover;
				margin-right:35px;
			}
			.imgbox img{
				z-index:100;float: right;margin-top: -15px;margin-right:-15px; 
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
			#shade {
				position: absolute;
				opacity: 0.5;
				left: 0px;
				right: 0px;
				background-color: black;
				width: 100%;
				height: 100%;
				z-index: 10;
			}
			#member_images {
			    width: 30rem;
			    height: 25rem;
			    margin-left: auto;
			    margin-right: auto;
			    left: 40%;
			    top: 20%;
			    position: absolute;
			    z-index: 12;
			    display: none;
			}
			#member_images .item {
			    display: block;
			}
			#member_images img {
			    display: block;
			    width: 100%;
			    max-height: 40rem;
			}
			#removeImage {
				color: #eb5f25;
				font-size: 2rem;
				left: calc(40% + 28.5rem);
				top: calc(18% + 1rem);
				z-index: 14;
				display: none;
				position: absolute;
				cursor: pointer;
			}
        </style>
	</head>
	<body>
		<div id="shade" style="display: none;"><div class="glyphicon glyphicon-remove" style="z-index: 11;float: right;color: white;cursor: pointer;" id="close_shade" title="关闭"></div></div>
    	<div id="member_images" class="owl-carousel">
			<c:forEach items="${member.memberImages}" var="memberImage" varStatus="status">
				<a class="item" data-id="${memberImage.imgId}"><img src="${image}${memberImage.imgUrl}"/></a>
			</c:forEach>
		</div>
		<div id="removeImage" class="glyphicon glyphicon-remove-circle"></div>
   		<div class="container-fluid" style="overflow-y: auto;">
	        <div class="row-fluid">
				<div id="content">
					<form class="form-horizontal" action="${ctx}/member/update" method="post">
						<input type="hidden" name="memberId" value="${member.memberId}"/>
						<div class="form-group">
							<label class="col-sm-1 control-label" >会员编码：</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" value="${member.memberCode}" disabled="disabled"/> 
							</div>
							<label class="col-sm-1 control-label" >名称：</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" value="${member.memberName}" disabled="disabled"/> 
							</div>
							<label class="col-sm-1 control-label" >昵称：</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" value="${ member.memberAlias }" disabled="disabled"/> 
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label" >是否推荐：</label>
							<div class="col-sm-2" style="margin-top: 8px;">
								<c:if test="${member.recommendFlag eq 0}">
									<input type="text" class="form-control" value="未推荐" disabled="disabled"/> 
								</c:if>
								<c:if test="${member.recommendFlag eq 1}">
									<input type="text" class="form-control" value="已推荐" disabled="disabled"/> 
								</c:if>
							</div>
							<c:if test="${member.recommendFlag eq 1}">
								<label class="col-sm-1 control-label" >推荐顺序：</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" value="${ member.recommendOrder }" disabled="disabled"/> 
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label" >会员图像：</label>
							<c:if test="${!empty member.memberLogo}">
								<div class="col-sm-1 btn-upload logo-upload" style="display: none;">
									<input id="logoupload" type="file" />
								</div>
								<div class="col-sm-1" id="logoimage">
									<div class="imgbox_logo" data-src="${member.memberLogo}" style="z-index:1;background-image: url('${image}${member.memberLogo}');">
										<img src="${ctx}/resources/images/del.png" width="20px" height="20px;" />
									</div>
								</div>
							</c:if>
							<c:if test="${empty member.memberLogo}">
								<div class="col-sm-1 btn-upload logo-upload">
									<input id="logoupload" type="file" />
								</div>
								<div class="col-sm-1" id="logoimage" style="display: none;">
									<div class="imgbox_logo" data-src="${member.memberLogo}" style="z-index:1;background-image: url('${image}${member.memberLogo}');">
										<img src="${ctx}/resources/images/del.png" width="20px" height="20px;" />
									</div>
								</div>
							</c:if>
							<div class="form-group" id="logo_image" style="display: none"></div>
							<label class="col-sm-1 control-label">会员相册：&nbsp;&nbsp;<i class="glyphicon glyphicon-eye-open view-images" title="点击查看会员相册" style="color: #337ab7;"></i></label>
							<div class="col-sm-1">
								
							</div>
						</div>
					<!-- <div class="form-group">
						<label class="col-sm-1 control-label" >达人照片</label>
						<div class="col-sm-2">
							<div class="input-group">
						      <input type="text" class="form-control" placeholder="点击上传达人照片"  onclick="uploadImg();"/>
						      <span class="input-group-btn">
						        <label class="btn btn-default" onclick="uploadImg();"><span class="glyphicon glyphicon-folder-open" style="height: 20px;"></span></label>
						      </span>
						    </div>
							<input class="btn" id="fileupload" type="file" multiple="multiple" style="display: none;"/>
						</div>
					</div>
					<div class="form-group" id="img_list" style="display: none">
						
					</div> -->
					<%-- <div class="form-group" id="imglist" ondrop="drop(event);" ondragover="allowDrop(event);"  style="margin-left: 11.5rem;">
						<c:forEach items="${member.memberImages}" var="memberImage" varStatus="status">
							<div class="imgbox" id='imgBox${status.index}' data-src="${memberImage.imgUrl}" data-id="${memberImage.imgId}" style="z-index:1;background-image: url('${image}${memberImage.imgUrl}');" draggable="true" ondragstart="drag(event);">
								<c:if test="${not empty memberImage}">
									<img src="${ctx }/resources/images/del.png" width="30px" height="30px;" />
								</c:if>
							</div>
						</c:forEach>
					</div> --%>
					<div class="form-group">
						<label class="col-sm-1 control-label" >性别：</label>
						<div class="col-sm-2">
							<c:if test="${member.sex eq '男'}">
								<label class="radio-inline">
								  <input type="radio" value="男"  name="sex" checked="checked" disabled="disabled"/> 男
								</label>
								<label class="radio-inline">
								  <input type="radio" value="女" name="sex" disabled="disabled"/> 女
								</label>
							</c:if> 
							<c:if test="${member.sex eq '女'}">
								<label class="radio-inline">
								  <input type="radio" value="男" name="sex" disabled="disabled"/> 男
								</label>
								<label class="radio-inline">
								  <input type="radio" value="女" name="sex" checked="checked" disabled="disabled"/> 女
								</label>
							</c:if> 
						</div>
						<label class="col-sm-1 control-label" >出生年月：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="<fmt:formatDate value="${ member.birthday }" pattern="yyyy-mm-dd"/>" disabled="disabled"/> 
						</div>
						<label class="col-sm-1 control-label" >微信号：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.wechat }" disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >所在城市：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.city }" disabled="disabled"/> 
						</div>
						<label class="col-sm-1 control-label" >联系电话：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control"value="${ member.mobile }" disabled="disabled"/> 
						</div>
						<label class="col-sm-1 control-label" >邮件地址：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.email }" disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >会员星座：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.constellation }" disabled="disabled"/> 
						</div>
						<label class="col-sm-1 control-label" >感情状态：</label>
						<div class="col-sm-2" style="margin-top: 8px;">
							<c:choose>
								<c:when test="${ member.maritalStatus eq 1}">
									<input type="text" class="form-control" value="未婚" disabled="disabled"/> 
								</c:when>
								<c:when test="${ member.maritalStatus eq 2}">
									<input type="text" class="form-control" value="已婚" disabled="disabled"/> 
								</c:when>
								<c:when test="${ member.maritalStatus eq 3}">
									<input type="text" class="form-control" value="离异" disabled="disabled"/> 
								</c:when>
								<c:when test="${ member.maritalStatus eq 4}">
									<input type="text" class="form-control" value="丧偶" disabled="disabled"/> 
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" value="保密" disabled="disabled"/> 
								</c:otherwise>
							</c:choose>
						</div>
						<label class="col-sm-1 control-label" >会员职业：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.occupation }" disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >毕业学校：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.school }" disabled="disabled"/> 
						</div>
						<label class="col-sm-1 control-label" >学历：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.degree }" disabled="disabled"/> 
						</div>
						<label class="col-sm-1 control-label" >工作单位：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.workunit }" disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >推荐人：</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" value="${ member.recommendedMember.memberName }" disabled="disabled"/> 
							</div>
						<label class="col-sm-1 control-label" >从事工作：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.profession }" disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >身高：</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" value="${ member.memberHeight }" disabled="disabled"/> 
							</div>
						<label class="col-sm-1 control-label" >体重：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${ member.memberWeight }" disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >兴趣爱好：</label>
						<div class="col-sm-5" id="catogary">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >活动范围：</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="3" disabled="disabled">${ member.livingScope }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label" >个人说明：</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="3" disabled="disabled">${ member.memberDesc }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div></div>
						<button class="btn btn-primary col-md-offset-3" id="submit">保存</button>
						<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
					</div>
				</form>
					<!--内容结束-->
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${ctx}/resources/libs/carousel/js/owl.carousel.min.js"></script>
        <script type="text/javascript">
        	var ctx = '${ctx}';
        	$(function() {
        		getCategory();
        		$('.view-images').on('click', function() {
        			$('#member_images').owlCarousel({
				        items: 1,
				        autoHeight: false,
				        addClassActive: true
				    });
        			if($('#member_images div.owl-item').length <= 0 ) {
        				swal('该会员尚未上传照片！');
        				return;
        			}
        			$('#member_images').show();
        			$('#removeImage').show();
        			$('#shade').show();
        		});
        		$('#close_shade').on('click', function() {
        			$('#shade').hide();
        			$('#removeImage').hide();
        			$('#member_images').hide();
        		});
        		$('#removeImage').on('click', function() {
        			var imgId = $('#member_images div.owl-item.active>a').attr('data-id');
        			if(isEmpty(imgId)) {
        				return;
        			}
        			swal({
        				title : "",
        				text : "确认删除该照片？",
        				type : "warning",
        				showCancelButton : true,
        				confirmButtonColor : "#337ab7",
        				confirmButtonText : "确认",
        				cancelButtonText : "取消",
        				closeOnConfirm : true,
        				closeOnCancel : true
        			}, function(isConfirm) {
        				if (isConfirm) {
        					$.ajax({
                				url: '${ctx}/memberimage/delete',
                				type: 'POST',
                				data: "imgId="+imgId,
                				success: function(msg){
               					 	$('#member_images div.owl-item.active').remove();
               					 	$('#member_images div.owl-page.active').remove();
               	        			if($('#member_images div.owl-item').length > 0) {
               	        				$('#member_images div.owl-item').eq(0).addClass('active');
               	        				if($('#member_images div.owl-page').length > 0) {
               	        					$('#member_images div.owl-page').eq(0).addClass('active');
                   	        			}
               	        			}else {
               	        				$('#shade').hide();
               	            			$('#removeImage').hide();
               	            			$('#member_images').hide();
               	        			}
                				}
                			}); 
        				}
        			});
        		});
        	});
        	
        	function getCategory() {
        		$.ajax({
        			   url: "${ctx}/category/list",
        			   success: function(msg){
        				   var categories = msg.results.categories;
        				   var categoryNames = "";
        				   $.each( categories, function(i, category){
        					   var catrgoryCode = category.categoryCode;
        					   if(catrgoryCode =='${member.sportCategory01}') {
        						   categoryNames += category.categoryName;
        					   }
        					   if(catrgoryCode =='${member.sportCategory02}') {
        						   categoryNames += "，" + category.categoryName;
        					   }
        					   if(catrgoryCode =='${member.sportCategory03}') {
        						   categoryNames += "，" + category.categoryName;
        					   }
        					   if(catrgoryCode =='${member.sportCategory04}') {
        						   categoryNames += "，" + category.categoryName;
        					   }
        					   if(catrgoryCode =='${member.sportCategory05}') {
        						   categoryNames += "，" + category.categoryName;
        					   }
        					});
        				   $('#catogary').html('<input type="text" class="form-control" value="'+categoryNames+'" disabled="disabled"/>' );
        			   }
        			});
        		}
        </script>
	</body>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
	<script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
	<script src="${ctx }/resources/js/member/update.js"></script>
</html>
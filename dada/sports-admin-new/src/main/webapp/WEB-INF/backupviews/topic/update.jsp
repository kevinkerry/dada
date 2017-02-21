<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改话题</title>
	<link rel="stylesheet" href="${ctx}/resources/libs/carousel/css/owl.carousel.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/carousel/css/owl.theme.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
	<style type="text/css">
		#topic_iamge {
		    width: 25rem;
		    height: 11rem;
		    margin-left: auto;
		    margin-right: auto;
		}
		#topic_iamge .item {
		    display: block;
		}
		#topic_iamge img {
		    display: block;
		    width: 100%;
		    height: 100%;
		}
		#removeImage {
			color: #eb5f25;
			font-size: 2rem;
			cursor: pointer;
			margin-left: -2rem;
			margin-top: -1rem;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" action="${ctx}/topic/update">
			<input type="hidden" name="topicId" value="${topic.topicId}"/>
			<div class="form-group">
				<label class="col-md-1 control-label" >话题发起人编码：</label>
				<div class="col-md-2">
					<input class="form-control" name="topicUser.userCode" type="text" value="${topic.topicUser.userCode}" disabled="disabled" />
				</div>
				<label class="control-label col-md-1" >话题发起人昵称：</label>
				<div class="col-md-2">
					<input class="form-control" name="topicUser.member.memberAlias" value="${topic.topicUser.member.memberAlias}"  type="text" disabled="disabled"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-1" >话题类型：</label>
				<div class="col-md-2">
					<input class="form-control" name="category.categoryName" value="${topic.category.categoryName}"  type="text" disabled="disabled"/>
				</div>
				<label class="control-label col-md-1" >浏览量：</label>
				<div class="col-md-2">
					<input class="form-control" name="viewQuantity" value="${topic.viewQuantity}"  type="text" disabled="disabled"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-1" >所在城市：</label>
				<div class="col-md-2">
					<input class="form-control" name="city" value="${topic.city}"  type="text" disabled="disabled"/>
				</div>
				<label class="control-label col-md-1" >所在位置：</label>
				<div class="col-md-2">
					<input class="form-control" name="location" value="${topic.location}"  type="text" disabled="disabled"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" >话题内容：</label>
				<div class="col-md-5">
					<textarea class="form-control" rows="4" disabled="disabled" style="resize:none;">${topic.topicContent}</textarea>
				</div>
			</div>
			<c:if test="${fn:length(topic.topicImages) > 0}">
				<div class="form-group topic-image">
					<label class="col-md-1 control-label" >话题照片：</label>
					<div id="topic_iamge" class="owl-carousel col-md-4">
						<c:forEach items="${topic.topicImages}" var="topicImage" varStatus="status">
							<a class="item" data-id="${topicImage.imgId}" href="${image}${topicImage.imgUrl}" data-lightbox="roadtrip"><img src="${image}${topicImage.imgUrl}" title="点击查看大图"></a>
						</c:forEach>
					</div>
					<!-- <div id="removeImage" class="glyphicon glyphicon-remove-circle"></div> -->
				</div>
			</c:if>
		</form>
	</div>
	<script type="text/javascript" src="${ctx}/resources/libs/carousel/js/owl.carousel.min.js"></script>
	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
	<script type="text/javascript">
	$(function(){
	    $('#topic_iamge').owlCarousel({
	        items: 1,
	        autoHeight: false,
	        addClassActive: true
	    });
	    
	    $('#removeImage').on('click', function() {
			var imgId = $('#topic_iamge div.owl-item.active>a').attr('data-id');
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
        				url: '${ctx}/topicimage/delete',
        				type: 'POST',
        				data: "imgId="+imgId,
        				success: function(msg){
       					 	$('#topic_iamge div.owl-item.active').remove();
       						$('#topic_iamge div.owl-page.active').remove();
       	        			if($('#topic_iamge div.owl-item').length > 0) {
       	        				$('#topic_iamge div.owl-item').eq(0).addClass('active');
       	        				if($('#topic_iamge div.owl-page').length > 0) {
       	        					$('#topic_iamge div.owl-page').eq(0).addClass('active');
           	        			}
       	        			}else {
       	        				$('.topic-image').hide();
       	        			}
        				}
        			});
				}
			});
		});
	});
	</script>
</body>
</html>
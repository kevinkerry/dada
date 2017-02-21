<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改哒人秀</title>
	<link rel="stylesheet" href="${ctx}/resources/libs/carousel/css/owl.carousel.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/carousel/css/owl.theme.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
	<style type="text/css">
		#show_iamge {
		    width: 25rem;
		    height: 15rem;
		    margin-left: auto;
		    margin-right: auto;
		}
		#show_iamge .item {
		    display: block;
		}
		#show_iamge img {
		    display: block;
		    width: 100%;
		    height: 150px;
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
		<form class="form-horizontal" action="${ctx}/show/update">
			<input type="hidden" name="showId" value="${show.showId}"/>
			<div class="form-group">
				<label class="col-md-1 control-label" >哒人编码：</label>
				<div class="col-md-2">
					<input class="form-control" name="showUser.userCode" type="text" value="${show.showUser.userCode}" disabled="disabled" />
				</div>
				<label class="col-md-1 control-label" >哒人姓名：</label>
				<div class="col-md-2">
					<input class="form-control" name="showUser.userName" type="text" value="${show.showUser.userName}" disabled="disabled" />
				</div>
				<label class="control-label col-md-1" >哒人昵称：</label>
				<div class="col-md-2">
					<input class="form-control" name="showUser.member.memberAlias" value="${show.showUser.member.memberAlias}"  type="text" disabled="disabled"/>
				</div>
			</div>
			<c:if test="${fn:length(show.showImages) > 0}">
				<div class="form-group show-image">
					<label class="col-md-1 control-label" >哒人秀照片：</label>
					<div id="show_iamge" class="owl-carousel col-md-1">
						<c:forEach items="${show.showImages}" var="showImage" varStatus="status">
							<a class="item" data-id="${showImage.imgId}" href="${image}${showImage.imgUrl}" data-lightbox="roadtrip"><img src="${image}${showImage.imgUrl}" title="点击查看大图"></a>
						</c:forEach>
					</div>
					<div id="removeImage" class="glyphicon glyphicon-remove-circle"></div>
				</div>
			</c:if>
			<div class="form-group">
				<label class="control-label col-md-1" >浏览量：</label>
				<div class="col-md-2">
					<input class="form-control" name="viewQuantity" value="${show.viewQuantity}"  type="text" disabled="disabled"/>
				</div>
				<label class="col-md-1 control-label" >是否推荐：</label>
				<c:if test="${show.recommendFlag eq 0}">
					<c:set var="isRecomment" value="未推荐"></c:set>
				</c:if>
				<c:if test="${show.recommendFlag eq 1}">
					<c:set var="isRecomment" value="已推荐"></c:set>
				</c:if>
				<div class="col-md-2">
					<input class="form-control" name="recommendFlag" type="text" value="${isRecomment}" disabled="disabled" />
				</div>
				<c:if test="${show.recommendFlag eq 1}">
					<label class="col-md-1 control-label" >推荐顺序：</label>
					<div class="col-md-2">
						<input class="form-control" name="focusWeight" type="text" value="${show.focusWeight}" disabled="disabled" />
					</div>
				</c:if>
			</div>
			<div class="form-group">
				<label class="control-label col-md-1" >所在城市：</label>
				<div class="col-md-2">
					<input class="form-control" name="city" value="${show.city}"  type="text" disabled="disabled"/>
				</div>
				<label class="control-label col-md-1" >所在位置：</label>
				<div class="col-md-2">
					<input class="form-control" name="location" value="${show.location}"  type="text" disabled="disabled"/>
				</div>
				<label class="control-label col-md-1" >所属分类：</label>
				<div class="col-md-2">
					<input class="form-control" name="category.categoryName" value="${show.category.categoryName}"  type="text" disabled="disabled"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" >哒人秀描述：</label>
				<div class="col-md-5">
					<textarea class="form-control" rows="4" disabled="disabled" style="resize:none;">${show.showDesc}</textarea>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${ctx}/resources/libs/carousel/js/owl.carousel.min.js"></script>
	<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
	<script type="text/javascript">
	$(function(){
	    $('#show_iamge').owlCarousel({
	        items: 1,
	        autoHeight: false,
	        addClassActive: true
	    });
	    
	    $('#removeImage').on('click', function() {
			var imgId = $('#show_iamge div.owl-item.active>a').attr('data-id');
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
        				url: '${ctx}/showimage/delete',
        				type: 'POST',
        				data: "imgId="+imgId,
        				success: function(msg){
       					 	$('#show_iamge div.owl-item.active').remove();
       						$('#show_iamge div.owl-page.active').remove();
       	        			if($('#show_iamge div.owl-item').length > 0) {
       	        				$('#show_iamge div.owl-item').eq(0).addClass('active');
       	        				if($('#show_iamge div.owl-page').length > 0) {
       	        					$('#show_iamge div.owl-page').eq(0).addClass('active');
           	        			}
       	        			}else {
       	        				$('.show-image').hide();
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
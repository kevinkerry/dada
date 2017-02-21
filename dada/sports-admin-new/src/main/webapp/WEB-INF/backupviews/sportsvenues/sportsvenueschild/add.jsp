<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<%-- <%@include file="/common/common-header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增子场地</title>
    </head>
    <body>
        <div class="container-fluid" style="margin-top: 30px;">
            <div class="row-fluid">
				<form id="child_venue_form" class="form-horizontal"  action="${ctx}/sportsvenueschild/add" method="post">
					<input type="hidden" name="venueId" value="${venueId}"/>
					<div class="form-group">
						<label class="col-sm-1 control-label" >场地名称</label>
						<div class="col-sm-4">
							<input class="form-control" name="childVenueName" type="text" required />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-1" >所属分类</label>
						<div class="col-sm-2">
							<select class="form-control" id="parent_category" name="parentCategory" required/>
					          <option value="">--请选择所属大类--</option>
					        </select>
				        </div>
				        <div class="col-sm-2">
					        <select class="form-control" id="category_code" name="categoryCode" required/>
					          <option value="">--请选择所属小类--</option>
					        </select>
				        </div>
					</div> 
					<div class="form-group">
						<label class="control-label col-sm-1" >联系电话</label>
						<div class="col-sm-2">
							<input class="form-control" name="venueContact" type="tel"/>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-sm-1" >市场价格</label>
						<div class="col-sm-2">
							<input class="form-control" name="maketingPrice"/>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-sm-1" >促销价格</label>
						<div class="col-sm-2">
							<input class="form-control" name="salePrice"/>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-sm-1" >场地预约时间</label>
						<div class="col-sm-1">
							<input class="form-control" name="bookingStartTime" type="time" value="00:00" required/>
						</div>
						<div class="col-sm-1">
							<input class="form-control" name="bookingEndTime" type="time" value="00:00" required />
						</div>
					</div>  
					<div class="form-group">
						<label class="control-label col-sm-1" >预定场地时长（分钟）</label>
						<div class="col-sm-2">
							<input class="form-control" name="bookingTimeInteral"/>
						</div>
					</div> 
					<div class="form-group">
						<label class="control-label col-sm-1" >子场地描述</label>
						<div class="col-sm-4">
							<textarea class="form-control" maxlength="200" name="childVenueDesc" style="resize: none;" rows="8"></textarea>
						</div>
					</div>  	
					<div class="form-group" style="margin-left: 400px;">
						<button id="sumit" class="btn btn-primary">提交</button>
						<button class="btn" onclick="javascript :history.back(-1);">关闭</button>
					</div>
				</form>
            </div>
        </div>
		<script src="${ctx }/resources/js/sportsvenue/sportsvenueschild/add.js"></script>
		<script >
			var categories;
			
			$(function() {
				 $('#parent_category').bind("change", getChildCategory);
				 getParentCategory();
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
				$('#category_code').append('<option value="">--请选择所属小类--</option>');
				$.each( categories, function(i, category){
					   if(category.parentId != -1 && category.parentCode == parentCategory) {
						   $('#category_code').append("<option value='"+category.categoryCode+"'>"+category.categoryName+"</option>");
					   }
					});
			}
		</script>	
    </body>
</html>

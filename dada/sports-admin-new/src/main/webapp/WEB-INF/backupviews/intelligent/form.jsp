<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<%-- <%@include file="/common/common-header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Tables</title>
        <!-- Bootstrap -->
        <link href="${ctx }/resources/assets/DT_bootstrap.css" rel="stylesheet" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${ctx }/resources/libs/lightbox/lightbox.css"/>
<style type="text/css">
        td img{
        	border-radius: 5px;
        }
        td{
        	font-family:"微软雅黑";
        	font-size:1.2em;
        }
        .box{
	margin-left:auto;
	margin-right:auto;
	margin-top:auto;
	margin-bottom:auto;
	position: fixed;
	display:table;
  	left: 0;
  	right: 0;
  	
 	height: 120px;
 	z-index: 1000;
	width:500px;
	background-color:#E7E7E7;

}
.box textarea{
	margin-left:auto;
	margin-right:auto;
	margin-top:auto;
	margin-bottom:auto;
	height:300px;
	width:100%;
	font-family:"微软雅黑";
	font-size:2em;
	margin-bottom:5px;
}
.box input{
	margin-left:auto;
	margin-right:auto;
	margin-top:auto;
	margin-bottom:auto;
	float:right;
	display:inline-block;
	margin-left:5px;
}
#type{
	margin-top:20px;
	width:100%;
}
        </style>
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="${ctx }/resources/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
     
    <link href="${ctx}/resources/css/intelligent/intelligent-add.min.css?v=${ctx}"
          rel="stylesheet">
    </head>
    
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
                <div id="content">
 <!-- 页面开始 -->            
<div class="item-list image-container">
    <div class="item-head">
        <span class="title">哒人形象</span>
    </div>
    <div class="image-list clearfix">
    <c:forEach items="${intelligent.images}" var="i" varStatus="status" >
    
   	 <div data-id="${i.id}" class="img" data-src="${i.imageUrl}" style="background-image: url(${image}${i.imageUrl})">
   	 
		<i class="iconfont icon-shanchu"></i>
		
		</div>
		
	</c:forEach>
        <div class="btn-upload">
            <input type="file" class="form-upload form-control" multiple="multiple" accept="image/*" id="uploadBtn">
        </div>
    </div>
</div>
<div class="item-list base-info">
	<input type="hidden" class="form-input" required="true" id="id"  name="id" value="${intelligent.id}" >
    <input type="hidden" class="form-input" required="true" id="status"  name="status" value="${intelligent.status}" >
    <div class="item-head">
        <span class="title">基本信息</span>
        <button type="button" class="btn-more" id="moreBtn">更多</button>
    </div>
    <div class="item">
        <label class="label">姓名：</label>
        <input type="text" class="form-input" required="true" id="username"  value="${intelligent.username}" placeholder="请填写姓名">
    </div>
    <div class="item">
        <label class="label">手机：</label>
        <input type="number" class="form-input" required="true" pattern="^0?1\d{10}$" pattern-msg="手机号码不合法!" id="mobile"
               placeholder="请填写手机号码"  value="${intelligent.mobile}" >
    </div>
    <div class="item">
        <label class="label">微博：</label>
        <input type="text" class="form-input" required="true" id="weibo" placeholder="请填写微博"  value="${intelligent.weibo}" >
    </div>
    <div class="item">
        <label class="label">分类：</label>
                    <select name="category" id="category" class="form-input" style="width: 160px;">
                    <c:if test="${intelligent.category eq 0 || empty intelligent.category }">
         				<option value="0" selected = "selected">请选择</option>
                		<option value="1" >颜值报表</option>
                		<option  value="2" >低调奢华</option>
                		<option  value="3" >百科全书</option>
                	</c:if>
         			<c:if test="${intelligent.category eq 1 }">
         				<option value="0">请选择</option>
                		<option value="1" selected = "selected">颜值报表</option>
                		<option  value="2" >低调奢华</option>
                		<option  value="3" >百科全书</option>
                	</c:if>
         			<c:if test="${intelligent.category eq 2 }">
         				<option value="0">请选择</option>
                		<option value="1" >颜值报表</option>
                		<option  value="2" selected = "selected" >低调奢华</option>
                		<option  value="3">百科全书</option>
                	</c:if>
         		<c:if test="${intelligent.category eq 3 }">
         				<option value="0">请选择</option>
                		<option value="1">颜值报表</option>
                		<option  value="2">低调奢华</option>
                		<option  value="3" selected = "selected" >百科全书</option>
                	</c:if>
            </select>
    </div>
    <div class="item">
        <label class="label">审核：</label>
        <select name="status" id="status" class="form-input" style="width: 160px;">
        <c:if test="${intelligent.status eq 0 }">
        	<option value="0" selected = "selected">未审核</option>
            <option value="1">已通过</option>
            <option  value="-1">不通过</option>
        </c:if>
        <c:if test="${intelligent.status eq 1 }">
        	<option value="0" >未审核</option>
            <option value="1" selected = "selected">已通过</option>
            <option  value="-1">不通过</option>
        </c:if>
        <c:if test="${intelligent.status < 0 }">
        	<option value="0" >未审核</option>
            <option value="1">已通过</option>
            <option  value="-1" selected = "selected">不通过</option>
        </c:if>
        </select>  
     </div>
         <div class="item">
        <label class="label">权重：</label>
		   <input type="number" name="orders" class="form-input" required="true" pattern="^\d+$" pattern-msg="请输入有效的数字" id="orders" value="${intelligent.orders}" style="width: 160px;"></input>
     </div>
</div>
<div class="item-list item-list-more" id="moreInfo">
    <div class="item">
        <label class="label">性别：</label>
           <select id="sex" class="form-input" style="width: 160px;">
        		<c:if test="${intelligent.sex eq 1 }">
               		<option value="1" selected = "selected">男</option>
               		<option  value="0" >女</option>
               		<option  value="3" >保密</option>
               	</c:if>
               	<c:if test="${intelligent.sex eq 0 }">
               		<option value="1">男</option>
               		<option  value="0" selected = "selected">女</option>
               		<option  value="3" >保密</option>
               	</c:if>
               	<c:if test="${intelligent.sex eq 3 }">
               		<option value="1">男</option>
               		<option  value="0">女</option>
               		<option  value="3" selected = "selected" >保密</option>
               	</c:if>
           </select>
    </div>
    <div class="item">
        <label class="label">出生日期：</label>

        <div class="u-time">
            <input type="date" class="time" id="birthday" placeholder="请选择出生日期" value="${intelligent.birthday}">
        </div>
    </div>
    <div class="item">
        <label class="label">单位/院校：</label>
        <input type="text" class="form-input" id="workUnit" placeholder="请输入单位/院校"  value="${intelligent.workUnit}">
    </div>
    <div class="item">
        <label class="label">身高(CM)：</label>
        <input type="number" class="form-input" id="height" placeholder="请输入身高" value="${intelligent.height}">
    </div>
    <div class="item">
        <label class="label">体重(KG)：</label>
        <input type="number" class="form-input" id="weight" placeholder="请输入体重" value="${intelligent.weight}">
    </div>
    <div class="item">
        <label class="label">三围：</label>

        <div class="san-wei">
            <div class="form-input-container"><input type="number" id="bust" class="form-input" placeholder="胸围" value="${intelligent.bust}"></div>
            <div class="form-input-container"><input type="number" id="waist" class="form-input" placeholder="腹围" value="${intelligent.waist}"></div>
            <div class="form-input-container"><input type="number" id="hips" class="form-input" placeholder="臀围" value="${intelligent.hips}"></div>
        </div>
    </div>
    <div class="item">
        <label class="label">微信号：</label>
        <input type="text" class="form-input" id="wechat" placeholder="请输入微信号" value="${intelligent.wechat}">
    </div>
    <div class="item">
        <label class="label">个人博客：</label>
        <input type="text" class="form-input" id="blog" placeholder="请输入个人博客"  value="${intelligent.blog}">
    </div>
    <div class="item">
        <label class="label">俱乐部：</label>
        <select id="joinClub">
           	<c:if test="${intelligent.joinClub eq 0 }">
        		<option  value="0" selected = "selected" >否</option>
        		<option value="1">是</option>
          	</c:if>
          	<c:if test="${intelligent.joinClub eq 1 }">
          		<option  value="0" >否</option>
          		<option value="1" selected = "selected">是</option>
          	</c:if>
        </select>
    </div>
    <div class="item club">
        <label class="label">俱乐部名称：</label>
        <input type="text" id="clubName" class="form-input" placeholder="请输入俱乐部名称" value="${intelligent.clubName}">
    </div>
</div>

<div class="course-container">
<c:forEach items="${intelligent.items}" var="o" varStatus="status" >
    <div class="item-list item-list-course">
        <div class="item-head">
            <span class="title">哒人运动历程</span>
	<input type="hidden" name="id"  class="form-input" value="${o.id}" placeholder="请填写您参与的运动项目">
            <div class="buttons">
                <i class="iconfont icon-tianjia"></i>
                <i class="iconfont icon-jianshao"></i>
            </div>
        </div>
        <div class="item">
            <label class="label">运动项目：</label>
            <input type="text" name="name" class="form-input" value="${o.name}" required="required" placeholder="请填写您参与的运动项目">
        </div>
        <div class="item">
            <label class="label">开始时间：</label>

            <div class="u-time">
                <input type="date" class="time" name="startTime" required="required" value="${o.startTime}" placeholder="请选择开始时间">
            </div>
        </div>
        <div class="item">
            <label class="label">结束时间：</label>

            <div class="u-time">
                <input type="date" class="time" name="endTime" required="required" value="${o.endTime}" placeholder="请选择结束时间">
            </div>
        </div>
        <div class="item">
            <label class="label">运动成果：</label>
            <input type="text" class="form-input" required="required" name="achievement" value="${o.achievement}" placeholder="获得XXX荣誉称号or证书">
        </div>
    </div>
    </c:forEach>
</div>
<button type="button" class="btn-submit" id="saveBtn">提交</button>
<div class="padding"></div>
<div class="u-loading">
    <img class="loading-img" src="${ctx}/resources/images/icon_loading.gif">
</div>
<div class="u-message">
    <div class="u-message-title">哒人形象照最多上传8张</div>
</div>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/resources/js/intelligent/megapix-image.js?v=1.0"></script>
<script src="${ctx}/resources/js/intelligent/exif.min.js?v=1.0"></script>
<script src="${ctx}/resources/js/intelligent/user-add.min.js?v=<%=System.currentTimeMillis()%>"></script>			
			



 <!-- 页面结束 -->
                </div>
            </div>
            <hr>

        </div>
        <!--/.fluid-container-->

        <script src="${ctx }/resources/vendors/datatables/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/resources/libs/lightbox/lightbox.min.js"></script>
        <script src="${ctx }/resources/assets/scripts.js"></script>
        <script src="${ctx }/resources/assets/DT_bootstrap.js"></script>
 		<script>
 		$(function(){
 			$("#goback").on("click",function(){
 				history.go(-1);
 			})
 			$('.image-list .img .icon-shanchu').on('click', function(){
 				var self=$(this).closest('.img');
 				if(self.attr("data-id")){
 					
 				$.ajax({
 					url:'${ctx}/image/delete',
 					data:'id='+$(this).closest('.img').attr("data-id"),
 					success:function(data){
 						self.remove();
 					
 					}
 				
 				});
 				}else{
 					self.remove();
 				}
 					//$(this).closest('.img').remove();
 			})
 			$('.item-list .item-head .buttons .icon-jianshao').on('click', function(){
 				$(this).closest('.item-list').remove();
 			})
 			
 			
 			
 		})
 		</script>
 
    </body>

</html>
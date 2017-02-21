<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>互联英雄联盟</title>
<style type="text/css">
*{
	padding:0;
	border:0;
	margin:0;
}
body {
	
}


	
.outer{
	
	text-align:center;
	background-image:url(${ctx }/resources/images/background.png);
	background-size:100% ;
	background-repeat:no-repeat;
}
.table{
	width:300px;
	height:300px;
	position:absolute;
	left:50%;
	top:38%;
	margin-left:-150px;
	margin-top:-150px;
	color:#FFFFFF;
	background-image:url(${ctx }/resources/images/input.png);
	background-repeat:no-repeat;
	background-size:100% 100%;
	
	/*
	margin-top:auto;
	margin-left:auto;
	margin-right:auto;
	margin-bottom:auto;
	*/
	
}
.table tr{
	height:30px;
}
.form-control{
	margin-bottom:20px;
	border:0;
	background:none;
	background-image:url(${ctx }/resources/images/input.png);
	background-repeat:no-repeat;
	background-size:100% 100%;
	
	color:#FFFFFF;
}
#btn{
	
	margin-top:20px;
}
.alert{
	position:absolute;
	width:300px;
	left:50%;
	margin-left:-150px;
	top:50%;
	margin-top:-35px;
	background-color:#FFFFFF;
	display:none;
	
}

</style>
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>
<script>
$(function () {
		$(".outer").css("width",$(window).width());
		$(".outer").css("height",$(window).height());
})
</script>
</head>
<body>

        
        

<div class="outer" >
<table class="table">
    	<thead>
            <tr>
                <th>#</th>
                <th>奖牌</th>
                <th>姓名</th>

                <th>手机号码</th>
  
            </tr>
        </thead>
        <c:forEach items="${spots}" var="n" varStatus="status" > 
        <tbody>
        	<tr>
            	<td>${status.index+1}</td>
            	<c:if test="${n.spotA=='A' && n.spotB!='B'}">
            	 <td>霸王令</td>
            	</c:if>
               <c:if test="${n.spotB=='B'  && n.spotA != 'A' }">
            	 <td>聚义令</td>
            	</c:if>
            	<c:if test="${n.spotB == 'B'  && n.spotA =='A' }">
            	 <td>霸王令、聚义令</td>
            	</c:if>
                <td>${n.name}</td>
                <td> ${n.mobile}</td>
                
            </tr>
            </tbody>
            </c:forEach>
        
        
        
</table>
</div>




</body>

</html>

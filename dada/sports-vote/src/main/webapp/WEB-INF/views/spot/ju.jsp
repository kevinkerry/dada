<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>互联英雄联盟</title>
<style type="text/css">
*{
	margin:0;
	padding:0;
}
div {
	background-image: url(${ctx }/resources/images/ju.png);
	background-repeat: no-repeat;
	background-size:100% 100%;
	display:block;
	width:100%;
	height:123px;
}


</style>
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script>
	$(document).ready(function(e) {
        $("#div1").css("height",$(window).height());
    })
</script>
</head>

<body>
<div id="div1">
</div>
</body>

</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>报名</title>



<style type="text/css">

</style>
<script>
$(function(){ 
     $(".span-left").click(function(){ 
        location.href="${ctx}/user/list?openId=${openId}"       //列表页
    }); 
}); 
</script>

<script>
$(function(){ 
     $(".span-right").click(function(){ 
        location.href="${ctx}/user/regist"    //报名页
    }); 
}); 
</script>
</head>
<body>

<div class="class-top"><span class="top-fontsize">校园运动哒人</span></div>
<div class="span-left"></div>
<div class="span-right"></div>

</body>
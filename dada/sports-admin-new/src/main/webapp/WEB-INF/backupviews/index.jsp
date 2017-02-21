<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="dadaSports">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="{{app.description}}">
    <meta name="keywords" content="哒哒运动,哒哒">
    <title>哒哒运动－后台管理系统</title>
    <!-- Bootstrap styles-->
    <script>
        if (location.href.indexOf('login') != -1) {
            location.href = '${ctx}/index'
        }
    </script>
    <link rel="shortcut icon" href="${ctx}/resources/app/img/dada.ico"/>
    <link rel="stylesheet" href="${ctx}/resources/vendor/carousel/owl.carousel.css" >
    <link rel="stylesheet" href="${ctx}/resources/vendor/carousel/owl.theme.css" >
    <link rel="stylesheet" href="${ctx}/resources/vendor/carousel/owl.transitions.css" >
    <link rel="stylesheet" href="${ctx}/resources/app/css/bootstrap.css" data-ng-if="!app.layout.isRTL">
    <link rel="stylesheet" href="${ctx}/resources/app/css/bootstrap-rtl.css" data-ng-if="app.layout.isRTL">
    <!-- Application styles-->
    <link rel="stylesheet" href="${ctx}/resources/app/css/app.css?v=${version}" data-ng-if="!app.layout.isRTL">
    <link rel="stylesheet" href="${ctx}/resources/app/css/app-rtl.css?v=${version}" data-ng-if="app.layout.isRTL">
    <!-- Themes-->
    <link rel="stylesheet" ng-href="${ctx}/resources/{{app.layout.theme}}?v=${version}" data-ng-if="app.layout.theme">
    <style>
        .img-circle {
            overflow: hidden;
        }
    </style>
</head>

<body data-ng-class="{ 'layout-fixed' : app.layout.isFixed, 'aside-collapsed' : app.layout.isCollapsed, 'layout-boxed' : app.layout.isBoxed, 'layout-fs': app.useFullLayout, 'hidden-footer': app.hiddenFooter, 'layout-h': app.layout.horizontal, 'aside-float': app.layout.isFloat, 'offsidebar-open': app.offsidebarOpen, 'aside-toggled': app.asideToggled}">
<div data-ui-view="" data-autoscroll="false" class="wrapper"></div>
<script>
    var contextPath = '${ctx}';
    var imageUrl = '${imageUrl}'
    var userId = '${user.id}'
    var gUser = {
        name: '${user.nickname}',
        headPortrait: '${image}${user.headPortrait}',
        email: '${user.email}'
    }
</script>
<script src="${ctx}/resources/vendor/kindeditor-4.1.10/kindeditor-all.js"></script>
<script src="${ctx}/resources/vendor/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script src="${ctx}/resources/app/js/base.js?v=${version}"></script>
<script src="${ctx}/resources/app/js/app.js?v=${version}"></script>
<script src="${ctx}/resources/vendor/carousel/owl.carousel.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=20090d8e72bfdc1311d91cdc596ec543"></script>


	
</body>

</html>
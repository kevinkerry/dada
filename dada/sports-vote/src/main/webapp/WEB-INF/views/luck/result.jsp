<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>获奖名单</title>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <link rel="shortcut icon" href="${ctx }/resources/images/ico/dada.ico"/>
    <link href="${ctx}/resources/app/css/dest/base.min.css?v=${version}" rel="stylesheet">
    <script>
        (function (doc, win) {
            var docEl = doc.documentElement,
                    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                    recalc = function () {
                        var clientWidth = docEl.clientWidth;
                        if (!clientWidth) return;
                        window.fontSize = 100 * (clientWidth / 1080);
                        docEl.style.fontSize = 100 * (clientWidth / 1080) + 'px';
                    };
            recalc()
            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
        })(document, window);
    </script>
    <style>
        body {
            background-color: #000;
            padding: .95rem .35rem;
        }
        .u-head {
            display: block;
            width: 100%;
            margin-bottom: .5rem;
        }
        .title {
            position: relative;
            margin-bottom: .72rem;
        }
        .title .title-line {
            border-bottom: 0.05rem dashed #FF2408;
        }
        .title .title-name {
            position: absolute;
            left: 50%;
            top: 50%;
            color: #FF2408;
            font-size: .5rem;
            font-weight: bold;
            font-style: italic;
            padding-left: .1rem;
            padding-right: .1rem;
            text-align: center;
            background-color: #000;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
        }
        .title .title-name span {
            color: #ffdc44;
            font-size: .65rem;
        }
        .item-list {
            margin-bottom: .75rem;
            min-height: 1rem;
        }
        .item-list li {
            position: relative;
            padding-left: 2.3rem;
            padding-right: 4.3rem;
            height: 1.1rem;
            line-height: 1.1rem;
            font-size: .46rem;
            color: #fff;
            margin-bottom: .35rem;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .item-list li .user-icon {
            position: absolute;
            left: .8rem;
            top: 50%;
            width: 1.1rem;
            height: 1.1rem;
            transform: translateY(-50%);
            -webkit-transform: translateY(-50%);
        }
        .item-list li .user-prize {
            position: absolute;
            right: .8rem;
            top: .26rem;
            width: 3.6rem;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            font-size: .4rem;
            letter-spacing: .02rem;
            line-height: 1;
            text-align: right;
        }
        .item-list li .user-prize span {
            color: #FF2408;
            margin-left: .22rem;
        }
        .item-list li .create-time {
            position: absolute;
            bottom: 0;
            right: .8rem;
            font-size: .3rem;
            line-height: 1;
            color: #fff;
        }
    </style>
</head>
<body class="page3">
<img class="u-head" src="${ctx}/resources/app/img/result.jpg">
<div class="title">
    <div class="title-line"></div>
    <div class="title-name">最受好评奖</div>
</div>
<ul class="item-list">
    <c:forEach var="luckUser" items="${luckUsers}">
        <c:if test="${luckUser.luck.id == 1}">
        <li>
            <img class="user-icon" src="${luckUser.userInfo.headimgurl}">
                ${luckUser.userInfo.nickname}
            <div class="user-prize">抽到<span>${luckUser.luck.name}</span></div>
            <div class="create-time" data-time="${luckUser.createTime}">

            </div>
        </li>
        </c:if>
    </c:forEach>
</ul>
<div class="title">
    <div class="title-line"></div>
    <div class="title-name">最佳技术成就奖</div>
</div>
<ul class="item-list">
    <c:forEach var="luckUser" items="${luckUsers}">
        <c:if test="${luckUser.luck.id == 2}">
        <li>
            <img class="user-icon" src="${luckUser.userInfo.headimgurl}">
                ${luckUser.userInfo.nickname}
            <div class="user-prize">抽到<span>${luckUser.luck.name}</span></div>
            <div class="create-time" data-time="${luckUser.createTime}">

            </div>
        </li>
        </c:if>
    </c:forEach>
</ul>
<div class="title">
    <div class="title-line"></div>
    <div class="title-name">最快上镜奖</div>
</div>
<ul class="item-list">
    <c:forEach var="luckUser" items="${luckUsers}">
        <c:if test="${luckUser.luck.id == 3}">
        <li>
            <img class="user-icon" src="${luckUser.userInfo.headimgurl}">
                ${luckUser.userInfo.nickname}
            <div class="user-prize">抽到<span>${luckUser.luck.name}</span></div>
            <div class="create-time" data-time="${luckUser.createTime}">

            </div>
        </li>
        </c:if>
    </c:forEach>
</ul>
<div class="title">
    <div class="title-line"></div>
    <div class="title-name">特别贡献奖</div>
</div>
<ul class="item-list">
    <c:forEach var="luckUser" items="${luckUsers}">
        <c:if test="${luckUser.luck.id == 4}">
        <li>
            <img class="user-icon" src="${luckUser.userInfo.headimgurl}">
                ${luckUser.userInfo.nickname}
            <div class="user-prize"><span>${luckUser.luck.name}</span></div>
            <div class="create-time" data-time="${luckUser.createTime}">

            </div>
        </li>
        </c:if>
    </c:forEach>
</ul>
<div class="title">
    <div class="title-line"></div>
    <div class="title-name">未中奖</div>
</div>
<ul class="item-list">
    <c:forEach var="luckUser" items="${luckUsers}">
        <c:if test="${luckUser.luck.id == 5}">
            <li>
                <img class="user-icon" src="${luckUser.userInfo.headimgurl}">
                    ${luckUser.userInfo.nickname}
                <div class="user-prize"><span>${luckUser.luck.name}</span></div>
                <div class="create-time" data-time="${luckUser.createTime}">

                </div>
            </li>
        </c:if>
    </c:forEach>
</ul>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script>
    var times = document.querySelectorAll('.create-time')
    for (var i = 0, j = times.length; i < j; i++) {
        var time = times[i].getAttribute('data-time');
        times[i].innerHTML = getTime(time)
    }
    function getTime(time) {
        var date = new Date(parseFloat(time))
        var month = date.getMonth() + 1;
        if (month < 10) {
            month = '0' + month;
        }
        var day = date.getDate()
        if (day < 10) {
            day = '0' + day;
        }
        var hours = date.getHours()
        if (hours < 10) {
            hours = '0' + hours;
        }
        var minutes = date.getMinutes()
        if (minutes < 10) {
            minutes = '0' + minutes;
        }
        return month + '-' + day + ' ' + hours + ':' + minutes;
    }
    var ctx = '${ctx}', imageServerUrl = '${image}',
            appDataUrl = '${appDataUrl}';
    wx.config({
        debug: false,
        appId: 'wx20d7737dce82964e',
        timestamp: '${sign.timestamp}',
        nonceStr: '${sign.nonceStr}',
        signature: '${sign.signature}',
        jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ']
    });
    wx.ready(function () {
        var data = {
            "imgUrl": '${ctx}/resources/app/img/logo.jpg',
            "link": location.href,
            "desc": "“哒”星人们!考验人品的时刻到啦!",
            "title": "“哒”星人们!考验人品的时刻到啦!"
        }
        share(data)
    })
    function share(data) {
        wx.onMenuShareTimeline({
            title: data.desc, // 分享标题
            link: data.link, // 分享链接
            imgUrl: data.imgUrl, // 分享图标
            success: function () {

            },
            cancel: function () {

            }
        });
        //分享到微信联系人
        wx.onMenuShareAppMessage({
            title: data.title, // 分享标题
            desc: data.desc, // 分享描述
            link: data.link, // 分享链接
            imgUrl: data.imgUrl, // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享到QQ
        wx.onMenuShareQQ({
            title: data.title, // 分享标题
            desc: data.desc, // 分享描述
            link: data.link, // 分享链接
            imgUrl: data.imgUrl, // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    }
</script>
</body>
</html>

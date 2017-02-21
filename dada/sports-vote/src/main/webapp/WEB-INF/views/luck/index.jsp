<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>哒哒运动</title>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <link rel="shortcut icon" href="${ctx }/resources/images/ico/dada.ico"/>
    <link href="${ctx}/resources/app/css/dest/base.min.css?v=${version}" rel="stylesheet">
    <link href="${ctx}/resources/app/css/dest/shake-index.min.css?v=${version}" rel="stylesheet">
    <%
        String tips = "1. 活动时间：2015年10月30日 20:30-21:30<br/>\n" +
                "2. 每个用户仅限参与一次<br/>\n" +
                "3. 你抽到的奖品，我们将在现场发放。<br/>\n" +
                "4.本次活动最终解释权归广州有意思网络科技有限公司所有。";
    %>
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
</head>
<body>
<div id="page1">
    <div class="u-body">
        <div class="btn-play" id="playBtn"></div>
        <ul class="user-list">
            <c:forEach var="luck" items="${luckList}" varStatus="status">
                <c:if test="${status.index < 3}">
                    <li>
                        <img class="user-icon" src="${luck.userInfo.headimgurl}">
                            ${luck.userInfo.nickname}
                        <div class="user-prize">抽到<span>${luck.luck.name}</span></div>
                        <div class="create-time" data-time="${luck.createTime}">

                        </div>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <div class="title">
        <div class="title-line"></div>
        <div class="title-name">活动规则</div>
    </div>
    <div class="u-footer">
        <%=tips%>
    </div>
</div>
<div id="page2">
    <img class="shake-img" id="shake" src="${ctx}/resources/app/img/shake.png">

    <div class="lottery-tip" id="lotteryTip">摇一摇，有惊喜</div>
    <div class="title">
        <div class="title-line"></div>
        <div class="title-name">当前参与活动的微信用户</div>
    </div>
    <div class="user-name">${userInfo.nickname}</div>
</div>
<div id="page3">
    <div class="prize-info">
        <img class="user-icon" src="${userInfo.headimgurl}">

        <div class="prize-title" id="prizeTitle">
            恭喜您抽到
        </div>
        <div class="prize-name" id="prizeName">&nbsp;
            <c:if test="${isLottery}">
                ${luckUser.luck.name}
            </c:if>
        </div>
        <div class="cut-image" id="cutImage"></div>
    </div>
    <div class="prize-tip">温馨提示：中奖后赶紧去签到处认领吧</div>
    <div class="title">
        <div class="title-name">活动现场</div>
    </div>
    <ul class="item-list">
        <c:forEach var="luck" items="${luckList}" varStatus="status">
            <c:if test="${status.index < 3}">
            <li>
                <img class="user-icon" src="${luck.userInfo.headimgurl}">
                    ${luck.userInfo.nickname}
                <div class="user-prize">抽到<span>${luck.luck.name}</span></div>
                <div class="create-time" data-time="${luck.createTime}">

                </div>
            </li>
            </c:if>
        </c:forEach>
    </ul>
    <div class="title">
        <div class="title-line"></div>
        <div class="title-name">活动规则</div>
    </div>
    <div class="u-footer">
        <%=tips%>
    </div>
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date startDate = sdf.parse("2015-09-29 9:00:00");
        Date endDate = sdf.parse("2015-11-10 23:00:00");
    %>
    var ctx = '${ctx}', imageServerUrl = '${image}', userId = '${userInfo.id}',
            appDataUrl = '${appDataUrl}',
            currentTime = parseInt('<%=System.currentTimeMillis()%>'),
            activityStartTime = parseInt('<%=startDate.getTime()%>'),
            activityEndTime = parseInt('<%=endDate.getTime()%>'),
            isLottery = '${isLottery}',
            imageUrl = '${userInfo.imageurl}';
    wx.config({
        debug: false,
        appId: 'wx20d7737dce82964e',
        timestamp: '${sign.timestamp}',
        nonceStr: '${sign.nonceStr}',
        signature: '${sign.signature}',
        jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'hideAllNonBaseMenuItem']
    });
    wx.ready(function () {
        wx.hideAllNonBaseMenuItem();
        var data = {
            "imgUrl": '${ctx}/resources/app/img/tuwen.png',
            "link": location.href,
            "desc": "“羽”人们!考验人品的时刻到啦!",
            "title": "“羽”人们!考验人品的时刻到啦!"
        }
        share(data)
    })
</script>
<script src="${ctx}/resources/app/js/dest/shake-index.min.js?v=${version}"></script>
</body>
</html>

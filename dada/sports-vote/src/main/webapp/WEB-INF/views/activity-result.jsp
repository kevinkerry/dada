<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta charset="utf-8">
    <title>校园运动哒人</title>
    <style>
        html, body {
            height: 100%;
            background-color: #ddd;
        }

        * {
            padding: 0;
            margin: 0;
            border: none;
        }

        @-webkit-keyframes zoomIn {
            0% {
                opacity: 0;
                -webkit-transform: scale3d(.3, .3, .3);
                transform: scale3d(.3, .3, .3)
            }
            50% {
                opacity: 1
            }
        }

        @keyframes zoomIn {
            0% {
                opacity: 0;
                -webkit-transform: scale3d(.3, .3, .3);
                transform: scale3d(.3, .3, .3)
            }
            50% {
                opacity: 1
            }
        }
        .content-container {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 95%;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
        }
        .content {
            line-height: 1.7;
            font-size: 1.2rem;
            padding: 1rem;
            border-radius: 5px;
            text-indent: 2rem;
            background-color: #fff;
            animation-duration: .6s;
            -webkit-animation: zoomIn .6s ease 0s 1 both;
            -webkit-animation-timing-function: ease;
            animation-timing-function: ease;
            -webkit-animation-delay: 0s;
            animation-delay: 0s;
            -webkit-animation-iteration-count: 1;
            animation-iteration-count: 1;
            -webkit-animation-direction: initial;
            animation-direction: initial;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
            animation-play-state: initial;
            -webkit-animation-play-state: initial;
            -webkit-animation-name: zoomIn;
            animation-name: zoomIn
        }
        .title {
            position: relative;
            font-size: 1.6rem;
            padding-bottom: 0.5rem;
            margin-bottom: 1rem;
            text-align: center;
            text-indent: 0;
        }
        .title:after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            border-bottom: 1px solid #ddd;
            transform: scaleY(.5);
            -webkit-transform: scaleY(.5);
        }
        .logo {
            display: block;
            width: 100%;
        }
    </style>
</head>

<body>
<div class="content-container">
    <div class="content">
        <img class="logo" src="${ctx}/resources/images/head.png">
        <div class="title">活动公告</div>
        哒哒运动校园运动哒人大赛投票圆满结束，
        封神榜公布名单票数，获奖者近期将收到颁奖通知。
        更多运动资讯，敬请关注哒哒运动！
    </div>
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: 'wx20d7737dce82964e', // 必填，公众号的唯一标识
        timestamp: '${sign.timestamp}', // 必填，生成签名的时间戳
        nonceStr: '${sign.nonceStr}', // 必填，生成签名的随机串
        signature: '${sign.signature}',// 必填，签名，见附录1
        jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareWeibo', 'onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    var imgUrl = "${ctx }/resources/images/gonggao-s.jpg";

    var wxData = {
        "imgUrl": imgUrl,
        "link": "${ctx}/index",
        "title": "哒哒运动校园运动哒人大赛投票圆满结束",
        "desc": "哒哒运动校园运动哒人大赛投票圆满结束，封神榜公布名单票数，获奖者近期将收到颁奖通知。更多运动资讯，敬请关注哒哒运动！"
    };

    wx.ready(function () {
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: data.desc, // 分享标题
            link: data.link, // 分享链接
            imgUrl: data.imgUrl, // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
                //alert("123");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
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
        //分享到QQ微博
        wx.onMenuShareWeibo({
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
    })
</script>
</body>
</html>

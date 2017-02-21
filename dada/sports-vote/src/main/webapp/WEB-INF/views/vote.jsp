<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<meta charset="utf-8">
<title>校园运动哒人</title>
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">
<style type="text/css">
.baoming{
	float:left;
	position:fixed;

	width:6em;
	height:6em;
	bottom:5em;
	right:2em;
	background:url(${ctx }/resources/images/baoming3.png)  0 0 no-repeat;
	background-size:100%;
}

.toupiao{
	
	float:left;
	position:fixed;

	width:6em;
	height:6em;
	bottom:12em;
	right:2em;
	background:url(${ctx }/resources/images/toupiao3.png) 0 0 no-repeat;
	background-size:100%;
}

</style>
<script>
	$(function(){
		$(".baoming").click(function(){
			 location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri=http%3A%2F%2Fvote.dadasports.cn%2Fuser%2Fregist&response_type=code&scope=snsapi_base&state=#wechat_redirect";
			}
		
		);
	})
		$(function(){
		$(".toupiao").click(function(){
		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri=http%3A%2F%2Fvote.dadasports.cn%2Fuser%2FqueryPage&response_type=code&scope=snsapi_base&state=#wechat_redirect";
			}
		
		);
	})
</script>






</head>

<body>
<div class="baoming"></div>
<div class="toupiao"></div>
<div class="container">
<img src="${ctx }/resources/images/v1.jpg" width="100%" height="auto" alt=""/>
<img src="${ctx }/resources/images/v2.jpg" width="100%" height="auto"alt=""/>
<img src="${ctx }/resources/images/v3.jpg" width="100%" height="auto" alt=""/>
<img src="${ctx }/resources/images/v4-gai.jpg" width="100%" height="auto" alt=""/>
</div>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wx20d7737dce82964e', // 必填，公众号的唯一标识
    timestamp: '${sign.timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${sign.nonceStr}', // 必填，生成签名的随机串
    signature: '${sign.signature}',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

var imgUrl="${ctx }/resources/images/v1.jpg";

var wxData = {
        "imgUrl": imgUrl,
        "link": "${ctx}/index",
       
        "desc": "校园哒人封神榜火热开启，就等你来！",
        "title": "大奖在这里，好胆就来拿！"
    };
    
$(document).ready(function(e) {  
initWeixinApi(wxData);
});
    
function initWeixinApi(data) {    
wx.ready(function(){
	//分享到朋友圈
	wx.onMenuShareTimeline({
	    title:data.title, // 分享标题
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

}
</script>
</body>
</html>

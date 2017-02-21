<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">



<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>校园运动哒人大赛</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/swiper.min.css">
<style type="text/css">
.span-right-baoming{
	position: absolute;
	cursor: pointer;
	z-index: 99;
	float: right;
	top: 3px;
	right:10px;
	color:#FFFFFF;
	font-size:14px;
	border:1px solid;
	padding-left:15px;
	padding-right:15px;
	padding-top:5px;
	padding-bottom:5px;
	text-align:center;
	margin-right:5px;
	-moz-border-radius:8px;
	-webkit-border-radius: 8px;
	border-radius:8px
}
.swiper-slide img{
	width:100%;
}

</style>
</head>
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>

<script src="${ctx }/resources/js/swiper.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>


<script>

$(function() {
	
	$(".ui-loader").hide();
	

	
	
	$('.carousel').carousel('pause');
	$("#love").click(function(){
		var myDate = new Date();
		$.ajax({
			url : "${ctx }/user/vote?wechat=${openId}&userId=${user.id}&time="+myDate.getTime(),
			type : "GET",
			success : function(data){
				if(data=="havenovote"){
					//alert("你今天的三次投票已经用完哦！");
					$("#vote-fail").show();
					setTimeout('$("#vote-fail").hide("slow")',2000);
				}else if(data=="havenoattention"){
					//alert("请先关注订阅号才能投票哦！");
					$("#vote-guanzhu").show();
					
				}else if(data=="over"){
					//alert("请先关注订阅号才能投票哦！");
					alert("活动已经结束了哦!");
					
				}else{
					$("#love").html("<span style=\"font-size:2em;line-height: 1.5em;\">"+data+"</span>");
					$("#vote-success").show();
					setTimeout('$("#vote-success").hide("slow")',2000);
				}
				
			},
			error : function(){
				alert("出错啦!");
			}
			
		});
		
	  });
	$("#btn-guanzhu").on("click",function(){
		location.href="http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=208720679&idx=1&sn=5623afd7b0c4c0ceacbd5eed0892bf06#rd";
		});
})
</script>

<body>


<div class="alert alert-success" role="alert" id="vote-success"  style="display: none;">投票成功</div>
<div class="alert alert-danger" role="alert" id="vote-fail"  style="display: none;">你今天的投票已经用完哦！</div>
 <div class="alert alert-danger" role="alert" id="vote-guanzhu"  style="display: none;">
 <span>您好,请进入哒哒运动官方订阅号进行投票！</span><br>
 <span><input type="button" class="btn btn-success" value="进入指引" id="btn-guanzhu"></span>
 </div>

<script>
$(function(){ 
     $(".span-left").click(function(){
         location.href="${ctx}/user/list?openId=${openId}&currentPage=${currentPage}#vote_${user.id}"       //列表页
    }); 
}); 
</script>

<script>
$(function(){ 
     $(".span-right-baoming").click(function(){ 
        location.href="${ctx}/user/userregist?openId=${openId}"    //报名页
    }); 
}); 
</script>

<div class="class-top"><span class="top-fontsize">${user.name}</span></div>
<div class="span-left"></div>
<div class="span-right-baoming">报名</div>





<div id="love" class="btn love"><span style="font-size:2em;line-height: 1.5em;">${user.voteNum}</span></div>

 
<div class="swiper-container">
  <div class="swiper-wrapper">

 	<c:forEach items="${user.images}" var="n" varStatus="status" >
 	
 		<div class="swiper-slide"><img src="${image }${n.imageUrl}"  alt=""></div>
    	
    	</c:forEach>
    
    
  </div>
  <div class="swiper-pagination"></div>
</div>
    
 <%--  <ol class="carousel-indicators">
   <c:forEach items="${user.images}" var="n" varStatus="status" >
   <c:choose>
   <c:otherwise>
    <li data-target="#myCarousel" data-slide-to="${status.index}"></li>
   </c:otherwise>
   </c:choose>
    </c:forEach>
  </ol>
  <!-- Carousel items -->
  <div class="carousel-inner">
   <c:forEach items="${user.images}" var="n" varStatus="status" >
   <c:choose>
   <c:when test="${status.index eq 0}">
    <div class="active item"><img src="${image }${n.imageUrl}"  alt=""> </div>
   </c:when>
   <c:otherwise>
       <div class="item"><img src="${image }${n.imageUrl}"  alt=""> </div>
   </c:otherwise>
   </c:choose>
    </c:forEach>
   
  </div>
  <!-- Carousel nav -->

</div> --%>
		
<div class="detail-box div-list">

	
	
 <br>  
 	
  
	<ul>
    	 <li class="li1"><h3>ID.&nbsp;${user.id}  </h3></li>
        <li class="li1"><h3>${user.name}  </h3></li>
        <li class="li1"><h3>${user.age}岁     </h3></li>
        <c:if test="${not empty user.wechat}">
        <li class="li1"><h4>微信号:${user.wechat}</h4></li>
        </c:if>
        <li class="li1"><h4>${user.personalProfile}</h4></li>
       <li id="spText"></li>

        
        
   	</ul>
	
</div>


</body>
</html>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

   <script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true
    });
    </script>
<script>

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wx20d7737dce82964e', // 必填，公众号的唯一标识
    timestamp: '${sign.timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${sign.nonceStr}', // 必填，生成签名的随机串
    signature: '${sign.signature}',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ','showAllNonBaseMenuItem','showMenuItems'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

var imgUrl="${image }/${user.images[0].imageUrl}";

var wxData = {
        "imgUrl": imgUrl,
        "link": "${ctx}/user/share?id=${user.id}",
       
        "desc": "欢迎参加哒哒运动运动哒人评选，我是${user.name},请投我一票，谢谢！",
        "title": "哒哒运动——欢迎参加哒哒运动运动哒人评选，我是${user.name},请投我一票，谢谢！"
    };
    
$(document).ready(function(e) {  
	wx.showMenuItems({
	    menuList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 要显示的菜单项，所有menu项见附录3
	});
	setTimeout(function(){
		wx.showAllNonBaseMenuItem();
		wx.showMenuItems({
		    menuList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 要显示的菜单项，所有menu项见附录3
		});
	},3000)
	setTimeout(function(){
		wx.showAllNonBaseMenuItem();
		wx.showMenuItems({
		    menuList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 要显示的菜单项，所有menu项见附录3
		});
	},300)
	setTimeout(function(){
		wx.showAllNonBaseMenuItem();
		wx.showMenuItems({
		    menuList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 要显示的菜单项，所有menu项见附录3
		});
	},100)
	setTimeout(function(){
		wx.showAllNonBaseMenuItem();
		wx.showMenuItems({
		    menuList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 要显示的菜单项，所有menu项见附录3
		});
	},10)
	wx.showAllNonBaseMenuItem();
	
initWeixinApi(wxData);
wx.showAllNonBaseMenuItem();
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

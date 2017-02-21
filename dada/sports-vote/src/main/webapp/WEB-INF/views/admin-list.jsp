<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">


<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>校园运动哒人</title>
<link rel="shortcut icon" href="${ctx }/resources/images/dada.ico"/>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/style.css">
<style type="text/css">
body {
	background-color: #FFFFCC;
}



</style>
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>
<script>
/*
wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wx20d7737dce82964e', // 必填，公众号的唯一标识
    timestamp: '${sign.timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${sign.nonceStr}', // 必填，生成签名的随机串
    signature: '${sign.signature}',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
*/
</script>
<script type="text/javascript">



/*
wx.ready(function(){
	//分享到朋友圈
	wx.onMenuShareTimeline({
	    title: '', // 分享标题
	    link: '', // 分享链接
	    imgUrl: '', // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	//分享到微信联系人
	wx.onMenuShareAppMessage({
	    title: '', // 分享标题
	    desc: '', // 分享描述
	    link: '', // 分享链接
	    imgUrl: '', // 分享图标
	    type: '', // 分享类型,music、video或link，不填默认为link
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
	    title: '', // 分享标题
	    desc: '', // 分享描述
	    link: '', // 分享链接
	    imgUrl: '', // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	       // 用户取消分享后执行的回调函数
	    }
	});
	//分享到QQ微博
	wx.onMenuShareWeibo({
	    title: '', // 分享标题
	    desc: '', // 分享描述
	    link: '', // 分享链接
	    imgUrl: '', // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
})

*/
</script>
<script>

$(function() {
	var myDate = new Date();
	
	$("#search").on("click",function(){
		var keyword=$("#keyword").val();
		location.href="${ctx }/user/admin/list?keyword="+keyword;
	});
	
	$(".close_it").on("click",function(){
		if(window.confirm('你确定要屏蔽此用户吗？')){
			var userId = $(this).attr("index");
			var keyword=$("#keyword").val();
			$.ajax({
				url : "${ctx }/user/"+userId+"/displaynone?time="+myDate.getTime(),
				type : "GET",
				success : function(data){
					if(data=="success"){
						alert("修改成功！");
						location.href="${ctx }/user/admin/list?keyword="+keyword;
					}
				},
				error : function(){
					alert("出错啦!");
				}
				
			});
            return true;
         }else{
            return false;
        }
		
		
	  });
		
	$("#btn-guanzhu").on("click",function(){
		location.href="http://mp.weixin.qq.com/s?__biz=MzA5OTU3OTM3NQ==&mid=208720679&idx=1&sn=5623afd7b0c4c0ceacbd5eed0892bf06#rd";
		});
	
	$("#loadLI").on("click",function(){
                    var currentPage = $("#currentPage").val();
                    if(currentPage>="${page.totalPages}"){
                    	$("#loadLI").hide();
                    	alert("已经没有了哦!");
                    	return;
                    }
                    currentPage = parseInt(currentPage)+1 ;
                  //  alert(currentPage);
                    $.ajax({
                        type: "get",
                        url: "${ctx}/user/queryPage.json?currentPage=" +currentPage,
                        success: function(data) {
                        	$("#currentPage").val(data.currentPage);
                        	html="";
                        	for(var index=0;index<data.result.length;index++){
                        		html+="<li>"+
                        		
                        		"<div id=\"box\" >"+
                        		"<div id=\"img\"><a href=\"${ctx }/user/"+data.result[index].id+"/detail\"><img class=\"class-img\" src=\"${image }/"+data.result[index].images[0].imageUrl+"\" alt=\"图片\" ></a></div>"+
                        		" <div id=\"content\">"+
                        		"<h4 class=\"vote-love vote\" \""+data.result[index].voteNum+"\"  index=\""+data.result[index].id+"\" >"+data.result[index].voteNum+"</h4>"+
                        		
                        		
                        			"		<h4>"+data.result[index].name+"</h4>"+
                        				"    		<h4>"+data.result[index].age+"岁</h4>"+
                        				
                        					"  </div>"+
                        					" <div class=\"vote-love\"><span id=\""+data.result[index].voteNum+"\"  index=\""+data.result[index].id+"\" class=\"vote\" >"+data.result[index].voteNum+"</span></div>"+
                        						" </div>"+
                        							"  </li>";
                        	}
                        	//alert(html);
                            $('#p').append(html);
                        },
                        error: function() {
                            alert("参数出错，刷新后重试");
                            return false;
                        }
                    });
                });
})

</script>


</head>


<body>



<div style="background-color:#fd5e28;"><input type="search" class="form-control visible-lg-inline" id="keyword" value="" style="width:300px;"> <input type="button" id="search" value="查找" class="btn btn-success" > </div>
<div id="contrainer"  class="container-fluid div-list" style="padding-left: 0px;padding-right: 0px;">


<input type="hidden" id="currentPage"value="${page.currentPage}" >
     	<ul id="p">
      <c:forEach items="${page.result}" var="n" varStatus="status" >       
        	<li>
        		
            		<div id="box" >
            		<c:set value="${ fn:split(n.images[0].imageUrl, '.') }" var="imageUrl" />
            		<c:set value="${ fn:split(imageUrl[0], '!') }" var="imageName" />
                    	<div id="img"><a href="${ctx }/user/${n.id}/detail?openId=${openId}"><img class="class-img" src="${image }${imageName[0]}.${imageUrl[1]}" alt="图片"  ></a></div>
                        <div id="content">
                        		<button class="btn btn-danger close_it" id="vote_${n.id}"  index="${n.id}" style="width:50px;height:50px" >屏蔽</button>
                        		
								<h4>${n.name}</h4>
                          		<h4>${n.age}岁</h4>
                        </div>
                    
                    </div>
            </li>         
</c:forEach>
        </ul>
        <input type="button" id="loadLI" value="加载更多" class="btn btn-block btn-danger" >
 </div>

</body>
</html>

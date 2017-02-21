<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">


<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>哒人排行榜</title>
<link rel="shortcut icon" href="${ctx }/resources/images/dada.ico"/>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/style.css">
<style type="text/css">
body {
	
}
#IDsearch{
	position:fixed;
	width:100%;
	
	bottom:0;
	background-color:#fd5e28;
	padding:2px;
	display:none;
	
}
.searchICO{
	float:left;
	position:fixed;
	cursor: pointer;
	width:4em;	
	height:4em;
	bottom:3em;
	right:2em;

	background-image:url(${ctx }/resources/images/search.png);
	background-size:100%;

}
.IDsearch *{
	padding-left:0;
	padding-right:0;
}
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
#loadLI{
	background-color:#fd5e28
}
</style>
<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

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

<script>
var stop_up=true;//触发开关，防止多次调用事件  
var stop_down=true;//触发开关，防止多次调用事件  
$(window).scroll(function() {
	//console.log("$(document).scrollTop()="+$(document).scrollTop());
	//console.log(" $(window).height()="+ $(window).height());
	//console.log("$(document).height())="+$(document).height());
	//console.log("stop="+stop);
	if(stop_up==true && $(window).scrollTop()<50){
		stop_up=false;
		//console.log("up!");
		 
			
			  loadupimg();
			
		
		 $("#loadpage").hide(); 
	}
	
	 if (stop_down==true && $(document).scrollTop() + $(window).height() >= $(document).height()) {
		 	
		 $("#loadpage").show(); 
        	//console.log("down!");
        	//console.log("11111111111");
        	
            loadimg();	
            
        
        $("#loadpage").hide(); 
    }
     
});


function loadimg(){
	stop_down=false;
	var currentPage=0;
      currentPage = $("#currentPage").val();
     var keyword = $("#keyword").val();
     var a = "${page.totalPages}";
    
                if(currentPage>parseInt(a)){
                	// console.log("currentPage2:"+currentPage);
                	$("#loadpage").hide(); 
                	//alert("已经没有了哦!");
                	return;
                }
              
              //  alert(currentPage);
                $.ajax({
                    type: "get",
                    url: "${ctx}/user/queryPage.json?currentPage=" +(parseInt(currentPage)+1)+"&keyword="+keyword,
                    success: function(data) {
                    	//alert("stop_down="+stop_down)
                    	if(currentPage!=data.currentPage){
                    	
                    	//alert("123");
                    	html="";
                    	// console.log("222233333333333222222222currentPage:"+currentPage);
                    	for(var index=0;index<data.result.length;index++){
                    		
                    		var imageurl = data.result[index].images[0].imageUrl;
                    		
                    		var images = imageurl.split("\.");
                    		
                    		var imagename = images[0].split("\!");
                    		html+="<li>"+
                    		
                    		"<div id=\"box\" >"+
                    		"<div id=\"img\"><a  keyword=\""+keyword+"\" data-index=\""+data.result[index].id+"\" currentpage=\""+data.currentPage+"\" href=\"http://vote.dadasports.cn/user/"+data.result[index].id+"/detail?openId=${openId}&currentPage="+currentPage+"\"><img class=\"class-img\" src=\"http://image.dadasports.cn/"+imagename[0]+"!480x640."+images[1]+"\" alt=\"图片\" ></a></div>"+
                    		" <div id=\"content\">"+
                    		"<h4 class=\"vote-love vote\" id=\"vote_"+data.result[index].id+"\"  index=\""+data.result[index].id+"\" >"+data.result[index].voteNum+"</h4>"+
                    		
                    			"<h4>ID.&nbsp;"+data.result[index].id+"</h4>"+
                    			"		<h4>"+data.result[index].name+"</h4>"+
                    				"    		<h4>"+data.result[index].age+"岁</h4>"+
                    				
                    					"  </div>"+
                    				//	" <div class=\"vote-love\"><span id=\""+data.result[index].voteNum+"\"  index=\""+data.result[index].id+"\" class=\"vote\" >"+data.result[index].voteNum+"</span></div>"+
                    						" </div>"+
                    							"  </li>";
                    	}
                    	//alert(html);
                    	$("#loadpage").hide(); 
                        $('#p').append(html);
                        currentPage = parseInt(currentPage)+1 ;
                        $("#currentPage").val(data.currentPage);
                    	}
                    },
                    error: function() {
                        alert("参数出错，刷新后重试");
                        return false;
                    }
                });	
                setTimeout('stop_down=true',2000);
               		
}

function loadupimg(){
	var keyword = $("#keyword").val();
    var currentPageup =0;
    currentPageup=parseInt($("#currentPageup").val());
   // console.log("currentPageup<=1?="+currentPageup<=1)
               if(currentPageup<=1){
              // 	$("#loadpage").hide(); 
               	//alert("已经没有了哦!");
               	
               }else{
               currentPageup = currentPageup-1 ;
             // console.log("currentPageup="+currentPageup)
             //  alert(currentPage);
               $.ajax({
                   type: "get",
                   url: "http://vote.dadasports.cn/user/queryPage.json?currentPage=" +currentPageup+"&keyword="+keyword,
                   success: function(data) {
                   	$("#currentPageup").val(data.currentPage);
                   	html="";
                   	for(var index=0;index<data.result.length;index++){
                   		
                   		var imageurl = data.result[index].images[0].imageUrl;
                   		
                   		var images = imageurl.split("\.");
                   		
                   		var imagename = images[0].split("\!");
                   		html+="<li>"+
                   		
                   		"<div id=\"box\" >"+
                   		"<div id=\"img\"><a keyword=\""+keyword+"\"  data-index=\""+data.result[index].id+"\" currentpage=\""+data.currentPage+"\" href=\"http://vote.dadasports.cn/user/"+data.result[index].id+"/detail?openId=&currentPage="+currentPageup+"\"><img class=\"class-img\" src=\"http://image.dadasports.cn/"+imagename[0]+"!480x640."+images[1]+"\" alt=\"图片\" ></a></div>"+
                   		" <div id=\"content\">"+
                   		"<h4 class=\"vote-love vote\" id=\"vote_"+data.result[index].id+"\"  index=\""+data.result[index].id+"\" >"+data.result[index].voteNum+"</h4>"+
                   		"<h4>ID.&nbsp;"+data.result[index].id+"</h4>"+
                   		
                   			"		<h4>"+data.result[index].name+"</h4>"+
                   				"    		<h4>"+data.result[index].age+"岁</h4>"+
                   				
                   					"  </div>"+
                   				//	" <div class=\"vote-love\"><span id=\""+data.result[index].voteNum+"\"  index=\""+data.result[index].id+"\" class=\"vote\" >"+data.result[index].voteNum+"</span></div>"+
                   						" </div>"+
                   							"  </li>";
                   	}
                   	//alert(html);
                   	//$("#loadpage").hide(); 
                       //$('#p').append(html);
						$('#p').prepend(html);
						console.log("outerHeight="+$(window).outerHeight());
						
                   },
                   error: function() {
                       console.log("参数出错，刷新后重试");
                       return false;
                   }
               }); 
               }
               stop_down=true;
}



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
	
	var width = window.screen.width;
	
	
	
	var myDate = new Date();
	
	$("#p").on("click",".vote",function(){
		var userId = $(this).attr("index");
		$.ajax({
			url : "${ctx }/user/vote?wechat=${openId}&userId="+userId+"&time="+myDate.getTime(),
			type : "GET",
			success : function(data){
				if(data=="havenovote"){
					//alert("你今天的三次投票已经用完哦！");
					$("#vote-fail").show();
					setTimeout('$("#vote-fail").hide("slow")',2000);
				}else if(data=="havenoattention"){
					//alert("请先关注订阅号才能投票哦！");
					$("#vote-guanzhu").show();
					
				}else{
					$("#vote_"+userId).val(data);
					$("#vote-success").show();
					setTimeout('$("#vote-success").hide("slow");location.href="${ctx }/user/list?openId=${openId}"',2000);
					
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
	
	$(".close").on("click",function(){
		$("#IDsearch").hide();
	});	
	
	var icount=0;
	$(".searchICO").on("click",function(){
		if(icount==0){
			$("#IDsearch").fadeIn();
			icount=1
		}else{
			$("#IDsearch").fadeOut();
			icount=0
		}
	});	
	
	
	$("#loadLI").on("click",function(){
		            var keyword = $("#keyword").val();
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
                        url: "${ctx}/user/queryPage.json?currentPage=" +currentPage+"&keyword="+keyword,
                        success: function(data) {
                        	$("#currentPage").val(data.currentPage);
                        	html="";
                        	for(var index=0;index<data.result.length;index++){
                        		
                        		var imageurl = data.result[index].images[0].imageUrl;
                        		
                        		var images = imageurl.split("\.");
                        		
                        		var imagename = images[0].split("\!");
                        		html+="<li>"+
                        		
                        		"<div id=\"box\" >"+
                        		"<div id=\"img\"><a href=\"${ctx }/user/"+data.result[index].id+"/detail?openId=${openId}&currentPage="+currentPage+"\"><img class=\"class-img\" src=\"${image }/"+imagename[0]+"!480x640."+images[1]+"\" alt=\"图片\" ></a></div>"+
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
	
	
	
	$("#search").click(function(){ 
      var keyword = $("#keyword").val();
        $.ajax({
            type: "get",
            url: "${ctx}/user/queryPage.json?currentPage=1&keyword="+keyword,
            success: function(data) {
            	html="";
            	for(var index=0;index<data.result.length;index++){
            		var imageurl = data.result[index].images[0].imageUrl;
            		
            		var images = imageurl.split("\.");
            		
            		var imagename = images[0].split("\!");
            		html+="<li>"+
            		
            		"<div id=\"box\" >"+
            		"<div id=\"img\"><a data-index=\""+data.result[index].id+"\" currentpage=\"1\" href=\"${ctx }/user/"+data.result[index].id+"/detail?openId=${openId}&currentPage=1\"><img class=\"class-img\" src=\"${image }/"+imagename[0]+"!480x640."+images[1]+"\" alt=\"图片\" ></a></div>"+
            		" <div id=\"content\">"+
            		"<h4 class=\"vote-love vote\" \""+data.result[index].voteNum+"\"  index=\""+data.result[index].id+"\" >"+data.result[index].voteNum+"</h4>"+
            		
            		"<h4>ID.&nbsp;"+data.result[index].id+"</h4>"+
            			"		<h4>"+data.result[index].name+"</h4>"+
            				"    		<h4>"+data.result[index].age+"岁</h4>"+
            				
            					"  </div>"+
            					
            						" </div>"+
            							"  </li>";
            	}
            	//alert(html);
                $('#p').html(html);
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
<div class="alert alert-success" role="alert" id="vote-success"  style="display: none;">投票成功</div>
<div class="alert alert-danger" role="alert" id="vote-fail"  style="display: none;">你今天的投票机会已经用完哦！</div>
 <div class="alert alert-danger" role="alert" id="vote-guanzhu"  style="display: none;">
 <span>您好,请进入哒哒运动官方订阅号进行投票！</span><br>
 <span><input type="button" class="btn btn-success" value="进入指引" id="btn-guanzhu"></span>
 </div>

<script>
$(function(){ 
     $(".span-left-refresh").click(function(){ 
    	 location.reload();       //列表页
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

<div class="class-top"><span class="top-fontsize">哒人排行榜</span></div>
<div class="span-left-refresh"></div>
<div class="span-right-baoming">报名</div>



<div id="contrainer"  class="container-fluid div-list" style="padding-left: 0px;padding-right: 0px;">
<input type="hidden" id="currentPage"value="${page.currentPage}" >
<input type="hidden" id="currentPageup"value="${page.currentPage}" >
     	<ul id="p">
      <c:forEach items="${page.result}" var="n" varStatus="status" >       
        	<li>
        		
            		<div id="box" >
            		<c:set value="${ fn:split(n.images[0].imageUrl, '.') }" var="imageUrl" />
            		<c:set value="${ fn:split(imageUrl[0], '!') }" var="imageName" />
                    	<div id="img"><a data-index="${n.id}" currentpage="${page.currentPage}" href="${ctx }/user/${n.id}/detail?openId=${openId}&currentPage=${page.currentPage}"><img class="class-img" src="${image }${imageName[0]}!480x640.${imageUrl[1]}" alt="图片"  ></a></div>
                        <div id="content">
                        		<h4 class="vote-love vote" id="vote_${n.id}"  index="${n.id}" >${n.voteNum}</h4>
                        		<h4>第${(page.currentPage-1)*page.pageSize+status.index+1}名</h4>
                        		<h4>ID.&nbsp;${n.id}</h4>
								<h4>${n.name}</h4>
                          		<h4>${n.age}岁</h4>
                        </div>
                    
                    </div>
            </li>         
</c:forEach>
        </ul>
        <!-- 
        <input type="button" id="loadLI" value="加载更多" class="btn btn-block btn-danger" >
         -->
         
 </div>
  <div id="loadpage" style=" width:200px;margin-left:auto;margin-right:auto;display:none;"><img src="${ctx }/resources/images/loading.gif" width="200px"  height="10" alt=""/></div>
 <div class="searchICO"></div>
<div id="IDsearch">
 	<div style="text-align:center;margin-left:auto;margin-right:auto;">
	<input type="search" id="keyword"class="form-control" style="width:80%;display:inline;margin-left:auto;overflow:hidden;" placeholder="请输入哒人名称进行搜索">
    <input type="button" class="btn btn-default"  value="搜索" id="search">
  </div>
</div>
</body>
</html>
<script>
	$(function(){
		$("#p").on("click","a",function(){
				var uid=$(this).attr("data-index");
				var page=$(this).attr("currentpage");
				//alert("uid="+uid+",page="+page);
				var keyword=$(this).attr("keyword");
				var stateObj = { foo: "bar" };
				window.history.pushState(stateObj, "page", "${ctx}/user/list?openId=${openId}&currentPage="+page+"#vote_"+uid);
			})
	})
</script>


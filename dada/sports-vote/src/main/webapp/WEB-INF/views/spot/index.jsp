<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/bootstrap.min.css">


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>互联英雄联盟</title>

<style type="text/css">
*{
	padding:0;
	border:0;
	margin:0;
}
body {
	
}


/* demo1 */
#demo1 { 
	position:absolute;
	top:30%;
	bottom:0;
	width: 280px;
	left:50%;
	margin-left:-140px;
	
	
	height:150px;
	
	-webkit-transform-style:preserve-3d;
	-moz-transform-style:preserve-3d;
	-ms-transform-style:preserve-3d;
	-o-transform-style:preserve-3d;
	transform-style:preserve-3d;
}
#demo1 dd,#demo1 dt { 
	border:1px solid #fafafa;
	box-shadow:2px 2px 4px #000;
	text-align:center;
	float:left;
	height:50px;
	margin-left:13px;
	position:relative;
	border-radius:10px;
	font-size:25px;
	font-family:"微软雅黑", "Microsoft Sans Serif", Arial;
	
	background:-webkit-linear-gradient( top, #fafafa 0%, #dcdcdc 100% );
	background:-moz-linear-gradient( top, #fafafa 0%, #dcdcdc 100% );
	background:-ms-linear-gradient( top, #fafafa 0%, #dcdcdc 100% );
	background:-o-linear-gradient( top, #fafafa 0%, #dcdcdc 100% );
	background:linear-gradient( top, #fafafa 0%, #dcdcdc 100% );
}
#demo1 dd {
	width: 20px; 
	line-height:50px; 
	
	-webkit-transform-style:preserve-3d;
	-moz-transform-style:preserve-3d;
	-ms-transform-style:preserve-3d;
	-o-transform-style:preserve-3d;
	transform-style:preserve-3d;
}
#demo1 dd div,#demo1 dt div {
	position:absolute;
	left:0;
	
	height:60px; /* height:150px; */
	width:120px;
	border-radius:10px;
	opacity:.3;
	-webkit-transform:rotateX(180deg);	
	-moz-transform:rotateX(180deg);	
	-ms-transform:rotateX(180deg);	
	-o-transform:rotateX(180deg);	
	transform:rotateX(180deg);	
	
	background:-webkit-linear-gradient( top, rgba( 35,35,35,0 ) 0%, rgba( 220,220,220,.6 ) 100% );
	background:-moz-linear-gradient( top, rgba( 35,35,35,0 ) 0%, rgba( 220,220,220,.6 ) 100% );
	background:-ms-linear-gradient( top, rgba( 35,35,35,0 ) 0%, rgba( 220,220,220,.6 ) 100% );
	background:-o-linear-gradient( top, rgba( 35,35,35,0 ) 0%, rgba( 220,220,220,.6 ) 100% );
	background:linear-gradient( top, rgba( 35,35,35,0 ) 0%, rgba( 220,220,220,.6 ) 100% );
	
	color:transparent;
}
#demo1 dt div {
	width:80px;
}
#demo1 dt { 
	line-height:50px; 
	width:20px; 
} 
.move {
	-webkit-transition:all 400ms ease;	
	-moz-transition:all 400ms ease;	
	-ms-transition:all 400ms ease;	
	-o-transition:all 400ms ease;	
	transition:all 400ms ease;	
}
.modeX {
	-webkit-transform:rotateX(360deg);	
	-moz-transform:rotateX(360deg);	
	-ms-transform:rotateX(360deg);	
	-o-transform:rotateX(360deg);	
	transform:rotateX(360deg);	
}
.modeY {
	-webkit-transform:rotateY(360deg);	
	-moz-transform:rotateY(360deg);	
	-ms-transform:rotateY(360deg);	
	-o-transform:rotateY(360deg);	
	transform:rotateY(360deg);	
}
dl h2{
	color:#FFFFFF;
	font-family:"微软雅黑", "Microsoft Sans Serif", Arial;
	top:60px;
	position:absolute;
		background-image:url(${ctx }/resources/images/input.png);
	background-repeat:no-repeat;
	background-size:100% 100%;
	width:100%;
	height:70px;
	line-height:70px;
	text-align:center;
	font-size:26px;
}
	
.outer{
	
	text-align:center;
	background-image:url(${ctx }/resources/images/background.png);
	background-size:100% 100%;
	background-repeat:no-repeat;
}
.inner{
	width:240px;
	height:300px;
	position:absolute;
	left:50%;
	top:60%;
	margin-left:-120px;
	margin-top:-150px;

	
	/*
	margin-top:auto;
	margin-left:auto;
	margin-right:auto;
	margin-bottom:auto;
	*/
	
}
.form-control{
	margin-bottom:20px;
	border:0;
	background:none;
	background-image:url(${ctx }/resources/images/input.png);
	background-repeat:no-repeat;
	background-size:100% 100%;
	
	color:#FFFFFF;
}
#btn{
	
	margin-top:20px;
}

</style>
</head>

<body>
<script type="text/javascript" src="${ctx }/resources/js/Aui-core-1.42-min.js"></script>

<script type="text/javascript">
(function(w){
	w.adam = function( id )
	{
		return new adamClock( id );
	};
	
	var adamClock = function( id )
	{
		this.id = id;
		
		this.def = false;
		
		this.timer = null;
	};
	
	adamClock.prototype = {
		
		_getTime : function( o )
		{
			var h = this._toString( o.getHours() ),
				m = this._toString( o.getMinutes() ),
				s = this._toString( o.getSeconds() );
			
			return [
				h.charAt( 0 ),
				h.charAt( 1 ),
				m.charAt( 0 ),
				m.charAt( 1 ),
				s.charAt( 0 ),
				s.charAt( 1 )
		    ];
		},
		
		_toString : function( num )
		{
			return ( num > 9  ? num : "0" + num ).toString();
		},
		
		init : function( speed, mode, move )
		{
			speed = speed || 100;
			mode = mode || "modeY";
			move = move || "move";
			
			var _this = this;
			
			Aui("body").append("<dl id=" + _this.id + "><dd><span>0</span></dd><dd><span>0</span></dd><dt><span>:</span></dt><dd><span>0</span></dd><dd><span>0</span></dd><dt><span>:</span></dt><dd><span>0</span></dd><dd><span>0</span></dd><h2 id='h2'>活动将于18:50开始</h2></dl>");
			
			var oDD = Aui( "#" + _this.id ).find("dd");
			
			_this.timer = setInterval( function()
			{
				var t = _this._getTime( new Date() ), i;
				
				for( i = 5 ; i >= 0; i-= 1)
				{
					var thisDD = oDD.eq( i );
					
					if( thisDD.find("span").text() == t[ i ] && _this.def ) break;
					
					//console.log( thisDD )
					thisDD.addClass( move + " " + mode ).find("span").text( t[ i ] ); //.next().text( t[ i ] )
					
					( function( o )
					{
						setTimeout(function()
						{
							o.removeClass( move + " " + mode );
						}, speed + 10 );
						
					})( thisDD )
					
				};
				
				_this.def = true;
				
			}, 1000 );
			
			return this;
		}
		
	};
})( window );

Aui.ready( function()
{
	if( /ie/g.test( Aui.browser() ) )
	{
		Aui("body").html("Adam CSS 3.0 effect，支持非IE浏览器。你懂的！")
				   .setStyle(
				   {
				   	"color" : "#fff",
				   	"text-align" : "center",
				    "font-size" : "50px",
				    "font-weight" : "bolder",
				    "line-height" : "500px"
				   });
	}
	else
	{
		adam("demo1").init( 500, "move", "modeY" );
	};

});
</script>

	




<div class="outer" >
	<div class="inner">
    <form class="form-group">
    	<input name="name" id="name" type="text" class="form-control" placeholder="请输入您的姓名" autocomplete="off" >
        <input mobile="mobile" id="mobile" type="tel" class="form-control" placeholder="请输入您的手机号码" pattern="[0-9]*" autocomplete="off"  maxlength=11>
      <img id="btn" src="${ctx }/resources/images/buttom.png"  width="100%" alt=""/>
    </form>
	</div>

</div>
</body>
</html>

<script src="${ctx }/resources/js/jquery-1.11.2.min.js"></script>
<script src="${ctx }/resources/js/bootstrap.min.js"></script>
<script>

$(function () {
	var name = "";
	var mobile = "";
	
	
		
		
		
		$(".outer").css("width",$(window).width());
		$(".outer").css("height",$(window).height());

	$("#name").on("focusout", function () {
		name = $(this).val();
		if (name == null || name.length == 0) {
			alert("请输入您的姓名");
		}
	})

	$("#mobile").on("focusout", function () {
		mobile = $(this).val();
		if (mobile == null || mobile.length != 11) {
			alert("请输11位手机号码");
		}
				 var reg = new RegExp("^[0-9]*$"); 
			 ;
		if(!reg.test(mobile)){
			alert("您输入的手机号码不合法");
		}
		
		
	})
	
	$("#btn").on("mousedown",function(){
		$("#btn").css({
				"-webkit-filter": "grayscale(1)",/* Webkit */ 
					"filter":"gray",/* IE6-9 */ 
					"filter":" grayscale(1)"/* W3C */ 
				});
	})
	
	
	$("#btn").on("mouseup",function(){
		setTimeout('$("#btn").removeAttr("style")',500);
		
	})
	
	
	$("#btn").on("click", function () {
		//$(this).removeAttr("style")
		console.log(name.length+'-'+mobile.length);
		tj();

	})
	
	function tj(){
		
		name = $("#name").val();
		mobile = $("#mobile").val();
		if (name.length > 0 && mobile.length == 11 ) {
			$.ajax({
				url:'http://vote.dadasports.cn/spot/lucky?name='+name+'&mobile='+mobile,
				//url:'lucky.jsp?name='+name+'&mobile='+mobile,
				success: function(json) {
					var data="";
					data=json.replace(/\s+/g,"");

					
					if(data=='aend_bend'){
						alert("Sorry~活动已经结束了~");
					}
					if(data=='aend_bnostart'){
						alert("Sorry~第一场活动结束了，请期待第二场活动~");
					}
					 if(data=='anostart'){
						alert("英雄，此令牌与你无缘！请节哀！");
					}
					 if(data=='havespotA'||data=='havespotB'){
						alert("Sorry~您已经抽过奖了哦~");
					}
					 if(data=='noneA'||data=='noneB'){
						alert("英雄，此令牌与你无缘！请节哀！");
					}
					 if(data=='A'){
						location.href='${ctx }/spot/ba';
					}
					 if(data=='B'){
						location.href='${ctx }/spot/ju';
					}
					
					 setTimeout('$("#btn").removeAttr("style")',500);
				}
			})
				
		}
	}
	
	
})


</script>
<script src="${ctx }/resources/js/moment.min.js"></script>
<script>
	$(document).ready(function(e) {
        //$("#demo1").hide();
		//$(".outer").show();
		$(".inner").hide();
		comptime();
		
    });
	function comptime() {
		var now = moment().format('YYYY-MM-DD HH:mm:ss'); //现在时间
		var startTimeA = moment('2015-06-28 09:40:00').format('YYYY-MM-DD HH:mm:ss');//第一场活动开始时间
		var endTimeA = moment('2015-06-28 10:10:00').format('YYYY-MM-DD HH:mm:ss');//第一场活动结束时间
		var startTimeB = moment('2015-06-28 10:30:00').format('YYYY-MM-DD HH:mm:ss');//第二场活动开始时间
		var endTimeB = moment('2015-06-28 11:00:00').format('YYYY-MM-DD HH:mm:ss');	//第二场活动结束时间
		console.log(now);
		
		//第一场开始
		if(now>startTimeA&&now<endTimeA){
			$("#demo1").hide();
			$(".inner").show();
		}	
		
		//第二场开始
		if(now>startTimeB&&now<endTimeB){
			$("#demo1").hide();
			$(".inner").show();	
		}
		
		//第一场结束，第二场未开始
		if(now>endTimeA&&now<startTimeB){
			$("#h2").html("");
			$("#h2").append("活动将于10点30开始");
		
	 		//setTimeout('comptime()',2000);
		}
		
		//第一未开始
		if(now<startTimeA){
			//setTimeout('comptime()',2000);
			$("#h2").html("");
			$("#h2").append("活动将于09点40开始");
		}
		
		//两场结束
		if(now>endTimeB){
			$("#h2").html("");
			$("#h2").append("活动已经结束");
		}
		setTimeout('comptime()',2000);
}
	
</script>
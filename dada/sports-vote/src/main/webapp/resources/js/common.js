//layer下级菜单的内容是否会被上级菜单清空 =1 清空下级菜单 =0不清空
//省市县联动
function getAddrChildren(proId, cityId, selectName, layer) {
    $.ajax({
        type: "POST",
        url: "?action=Public&command=PorCityCountry",
        data: { proId: proId, cityId: cityId },
        dataType: "JSON",
        success: function(msg) {
            msg = "<option value='0' >请选择</option>" + msg;
            var child = $("select[name='" + selectName + "']");
            if (layer == 1) {
                $('#addr_' + selectName).html("<option value='0'>请选择</option>");
            }
            child.html(msg);
        }
    });
}
//产品分类联动菜单
function getCategoryChildren(pid, type, selectName, layer) {
    $.ajax({
        type: "POST",
        url: "?action=Public&command=getCategory",
        data: { pid: pid, type: type },
        dataType: "JSON",
        success: function(msg) {
            msg = "<option value='0' >请选择</option>" + msg;
            var child = $("select[name='" + selectName + "']");
            if (layer == 1) {
                $('#addr_' + selectName).html("<option value='0'>请选择</option>");
            }
            child.html(msg);
        }
    });
}
//验证码 5,4,2
function _changeImg() {
    $("#verify").attr('src', '?action=Public&command=Verify&' + Math.random());
}
//获取url参数
function getRequest(arg) {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest[arg];
}
//跳转页面
function goto_page() {
    var page_id = $('input[name="go_page"]').val();
    var str = location.toString();
    var pos = str.indexOf('page_id=');
    if (pos == -1) {
        if (location.search.length < 1) {
            str += "?page_id=" + page_id;
        } else {
            str = "?page_id=" + page_id + "&" + location.search.substr(1);
        }
        location = str;
        return false;
    } else {
        var pos2 = str.indexOf('&');
        if (pos2 != -1) {
            var old_page_id = str.substring(pos, pos2);
        } else {
            var old_page_id = str.substr(pos, str.length);
        }
        str = str.replace(old_page_id, "page_id=" + page_id)
        location = str;
        return false;
    }
}
//网站头部下拉效果
$(function() {
    $(".show-hd").hover(function() {
        $(this).addClass("menu-show");
    }, function() { $(this).removeClass("menu-show"); });
    $(".q_nav,.user_nav").hover(function() {
        $(this).addClass("showdl");
    }, function() { $(this).removeClass("showdl"); });
})
//输入框提示语 
function inputTip(id, vWord) {
    var vWord;
	$(".tip_" + id).css("color","#000");
    if ($(".tip_" + id).val() == vWord) {
		
        $(".tip_" + id).attr("value", "");
		
    }
    $(".tip_" + id).focusout(function() {
		
        if ($(".tip_" + id).val() == "") {
			$(this).css("color","#C9C9C9 ");
            $(".tip_" + id).attr("value", vWord);
        }
    });
}
//文体输入提示
$(function() {
    //$(".prompt").siblings("input:text").val("");
    $(".prompt").each(function() {
        if ($(this).siblings("input:text,input:password").val() != "") {
            $(this).hide();
        }
    });
    $(".prompt").click(function() {
        $(this).css("color", "#dddddd");
        $(this).siblings("input:text,input:password").focus();
    });
    $(".prompt").siblings("input:text,input:password").focus(function() {
        $(this).siblings(".prompt").css("color", "#dddddd");
    });
    $(".prompt").siblings("input:text,input:password").keyup(function() {
        $(this).siblings(".prompt").hide();
    });
    $(".prompt").siblings("input:text,input:password").blur(function() {
        $(this).siblings(".prompt").hide();
        if ($(this).val() == "") {
            $(this).siblings(".prompt").show().css("color", "");
        } else {
            $(this).siblings(".prompt").hide();
        }
    });
	

	
})
//所在地
$(function() {
    $(".select-s").live("mouseenter", function() {
        $(this).addClass("show_ul");
    });
    $(".select-s").live("mouseleave", function() {
        $(this).removeClass("show_ul");
    });
    $(".select-s ul li").live("mouseenter", function() {
        $(this).addClass("li_hover");
    });
    $(".select-s ul li").live("mouseleave", function() {
        $(this).removeClass("li_hover");
    });
    $(".select-s ul li").live("click", function() {
        var value = $(this).attr("value");
        $(this).parent().siblings("span").html($(this).html());
        $(this).parent().siblings("span").attr("pid", value);
        $(this).parent().parent().removeClass("show_ul");
        $(this).addClass("selected").siblings().removeClass("selected")
    });
})
//设为首页
function set_home_page() {
    try {
        document.body.style.behavior = 'url(#default#homepage)';
        document.body.setHomePage('http://www.trlie.com');
    } catch (e) {
        if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入 about:config 并回车 \n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
            }
            var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
            prefs.setCharPref('browser.startup.homepage', 'http://www.trlie.com/');
        }
    }
}
//添加收藏
function addFavorite() {
    var aUrls = document.URL.split("/");
    var vDomainName = "http://" + aUrls[2] + "/";
    var description = document.title;
    try {
        window.external.addFavorite(vDomainName, description);
    } catch (e) {
        try {
            window.sidebar.addPanel(description, vDomainName, "");
        } catch (e) {
            alert("您的浏览器不支持，请使用Ctrl+D进行添加");
        }
    }
}
//操作提示
function success(class_name, notice) {
    //class_name = icon1正确、icon3提醒、icon2错误
    $(document.body).append('<div class="operate"><div class="bg_l ' + class_name + '"></div><div class="text_mes">' + notice + '</div><div class="bg_r"></div></div>').show()
    setTimeout('$(".operate").remove()', 3000);
}
//倒计时
var SysSecond;
var InterValObj;
$(function() {
    if ($("#remainTime,.remainTime").hasClass("ApplyforSuccess")) {
        SysSecond = parseInt(432000); //申请退款时间
    } else if ($("#remainTime,.remainTime").hasClass("RefundSuccess")) {
        SysSecond = parseInt(864000); //成功退货时间
    } else if ($("#remainTime,.remainTime").hasClass("to_fac")) {
        SysSecond = parseInt(1296000); //打款给厂家
    } else if ($("#remainTime,.remainTime").hasClass("selltime")) {
        SysSecond = parseInt(432000); //特价销售时间
    }
    InterValObj = window.setInterval(SetRemainTime, 1000); //间隔函数，1秒执行
});
function SetRemainTime() {
    if (SysSecond > 0) {
        SysSecond = SysSecond - 1;
        var second = Math.floor(SysSecond % 60);
        var minite = Math.floor((SysSecond / 60) % 60);
        var hour = Math.floor((SysSecond / 3600) % 24);
        var day = Math.floor((SysSecond / 3600) / 24);
        if (second < 10) {
            second = "0" + second;
        }
        $("#remainTime,.remainTime").html("<em>" + day + "</em>" + "天" + "<em>" + hour + "</em>" + "小时" + "<em>" + minite + "</em>" + "分" + "<em>" + second + "</em>" + "秒");
    }
}




//显示品牌介绍
$(function() {
    /*   $(".gobrandRefe").hover(function() {
        $(this).parent().addClass("showtext");
    }, function() {
		$(this).parent().removeClass("showtext");
    });*/
    //人气指数排行
    $(".brand_floorTop ol li").mouseenter(function() {
        $(this).addClass("mood_expand").siblings().removeClass("mood_expand");
    });
})
/*专场详情广告图片切换*/
$(function() {
    $(".show_pecial").hover(function() {
        $(this).find(".showNum").addClass("show");
    }, function() {
        $(this).find(".showNum").removeClass("show");
    });
    var len = $(".showNum > span").length;
    var index = 0;
    var adTimer;
    $(".showNum span").hover(function() {
        index = $(".showNum span").index(this);
        showImg(index);
    });
    $('.show_pecial').hover(function() {
        clearInterval(adTimer);
    }, function() {
        adTimer = setInterval(function() {
            showImg(index)
            index++;
            if (index == len) { index = 0; }
        }, 4000);
    }).trigger("mouseleave");
})
function showImg(index) {
    $(".showNum span").removeClass("on").eq(index).addClass("on");
    $(".show_pecial li").stop(true, false).eq(index).fadeTo(1000, 1).css("z-index", 2).siblings().fadeTo(1000, 0).css("z-index", 1);
}
//品牌特价跳窗显示--square
$(function() {
    var showt;
    var showt2;
    $(".squareBrandJump li a").live("mouseenter", function() {
        var id = $(this).attr("id");
        var id = $(this).attr("id");
        var offset = $(this).offset();
        var xwidth = $(this).parents(".w1000").offset().left + 1000;
        if (xwidth - offset.left < 465) {
            left = offset.left;
        } else {
            left = offset.left + 555;
        }
        $(this).parent().addClass("hover").siblings().removeClass("hover");
        $("#leap_" + id).siblings(".brand_leap").hide();
        showt = setTimeout(function() {
            if ($("#leap_" + id).hasClass("brand_leap") == false && !id == "") {
                $("body").append('<div class="brand_leap" id="leap_' + id + '" style="left:' + left + 'px; top:' + offset.top + 'px">\
<div class="leap_main">\
<div class="pic">\
<a href="#" target="_blank"><img src="http://static.shaixiuban.com/adimg/ad03.jpg"></a>\
</div>\
<div class="text">\
<div class="left">美妆品牌混合特价专场美妆品场美妆品牌</div>\
<span class="left">提成5%-16%</span>\
<span class="right"><a href="#" target="_blank">品牌介绍>></a></span>\
</div>\
</div>\
</div>');
            } else {
                $("#leap_" + id).css("display", "block");
            }
            $("#leap_" + id).mouseenter(function() {
                clearTimeout(showt2);
                $(this).show();
                $("#" + id).parent().addClass("hover");
            }).mouseleave(function() {
                $(this).hide();
                $("#" + id).parent().removeClass("hover");
            });
        }, 600);
    }).mouseleave(function() {
        clearTimeout(showt);
        var id = $(this).attr("id");
        showt2 = setTimeout(function() {
            $("#" + id).parent().removeClass("hover");
            $("#leap_" + id).hide();
        }, 300)
    });
})
/**查看大图*/
$(function() {
    $(".prove_ul a").click(function() {
        var eqnum = $(this).attr("eqnum");
        show_bigImg(".prove_ul", eqnum);
    });
    $(".get_zs a").click(function() {
        var eqnum = $(this).attr("eqnum");
        show_bigImg(".get_zs", eqnum);
    });
    $(".product_info .look_bigimg").click(function() {
        var eqnum = $(this).attr("eqnum");
        show_bigImg(".product_info .other_list", eqnum);
    });
    $(".info-pic-list .look_bigimg").click(function() {
        var eqnum = $(this).attr("eqnum");
        show_bigImg(".info-pic-list .other_list", eqnum);
    });
	$(".companySlide .contList a").click(function() {
        var eqnum = $(this).attr("eqnum");
        show_bigImg(".companySlide .contList", eqnum);
    });
})
function show_bigImg(obj, index) {
    $('body').append('<div class="show_bigImgBg"></div>\
<div class="show_bigImgCon">\
	<div class="close bigbg"> <a href="javascript:;" title="关闭" class="bigbg"></a> </div>\
	<div class="show_bigImg_main">\
		<div class="bigImg_top"> <a href="javascript:;" class="bigbg turn prev"></a> <a href="javascript:;" class="bigbg turn next"></a>\
			<div class="bigimg_box"><div></div><img src="images/??.jpg" id="bigimage" /></div>\
		</div>\
		<div class="bigImg_list"> <a href="javascript:;" class="bigbg turn_left aleft"></a> <a href="javascript:;" class="bigbg turn_right aright"></a>\
			<div class="list_box"><div>\
				<ul>\
				</ul>\
			</div></div>\
		</div>\
	</div>\
</div>');
    var len = $(obj).find('img').length;
    var myturn;
    $(document.body).parent().css({ "overflow": "hidden", "padding-right": "17px" });
    function jump_size() {
        var kuana = $(window).width();
        var gaoa = $(window).height();
        var img_h = gaoa - 168;
        $(".show_bigImgBg,.show_bigImgCon").css({ 'width': kuana, 'height': gaoa });
        $(".show_bigImg_main").css('height', gaoa)
        $(".bigImg_top,.bigimg_box").css('height', gaoa )
    }
    jump_size();
    window.onresize = function() {
        jump_size();
        imgsize();
    }
    $(obj).find("img").each(function() {
        var text = "";
        for (var i = 0; i < len; i++) {
            var src = $(obj).find("img").eq(i).attr("src")
            var o_guid = $(obj).find("img").eq(i).attr("o_guid")
			if(o_guid == undefined){
				o_guid = src;
				}
            text += "<li><p><img src='" + src + "' o_guid='" + o_guid + "' /></p></li>";
        }
        $(".bigImg_list ul").html(text);
    });
    var mylength = $(".bigImg_list ul img").length;
    var ul_width = $(".bigImg_list ul").width();
    if (len <= 8) {
        $(".bigImg_list .bigbg").hide();
        $(".bigImg_list ul").css("margin-left", (880 - ul_width) / 2);
    }
    function showpic(index) {
        $(".show_bigImg_main .bigimg_box div").show();
        var laodsrc = $(".bigImg_list ul").find("img").eq(index).attr("o_guid");
        $(".show_bigImg_main .bigimg_box img").attr("src", laodsrc);
        $(".show_bigImg_main .bigimg_box img").hide();
        $(".show_bigImg_main .bigimg_box img").fadeIn(600);
        $(".bigImg_list ul").find("img").eq(index).parents("li").addClass("showbig").siblings().removeClass("showbig");
        myturn = index;
    }
    document.getElementById("bigimage").onload = function() {
        imgsize();
    }
    showpic(index);
    $(".bigImg_list ul").find("li").each(function(index) {
        $(this).bind({ 'click': function() {
            if (!$(this).parents("li").hasClass("showbig")) {
                showpic(index);
            }
        }
        });
    });
    function imgsize() {
        $(".show_bigImg_main .bigimg_box img").removeAttr("style")
        var img_h = $(".show_bigImg_main .bigimg_box").height();
        var height = $(".show_bigImg_main .bigimg_box img").height();
        var width = $(".show_bigImg_main .bigimg_box img").width();
        if (height / width > img_h / 852) {
            if (height > img_h) {
                $(".show_bigImg_main .bigimg_box img").height(img_h);
            }
        } else {
            if (width > 852) {
                $(".show_bigImg_main .bigimg_box img").width(852);
            }
        }
		if(heigth < img_h){
			$(".show_bigImg_main .bigimg_box img").css("margin-top", (img_h - height) / 2);
			}
        $(".show_bigImg_main .bigimg_box div").hide();
    }
    $(".show_bigImg_main a").mouseenter(function() {
        $(this).animate({ "opacity": 1 }, "fast");
    }).mouseleave(function() {
        $(this).animate({ "opacity": 0.5 }, "fast");
    });
    $(".show_bigImg_main .next").click(function() {
        myturn++;
        if (myturn > (mylength - 1)) {
            myturn = 0;
        }
        showpic(myturn);
        if (len > 8) {
            $('.bigImg_list .list_box div').animate({ left: '-' + 110 * myturn }, "slow");
        }
    });
    $(".show_bigImg_main .prev").click(function() {
        myturn--;
        if (myturn < 0) {
            myturn = mylength - 1;
        }
        showpic(myturn);
        if (len > 8) {
            $('.bigImg_list .list_box div').animate({ left: '-' + 110 * myturn }, "slow");
        }
    });
    $(".show_bigImgCon .close a").click(function() {
        $(document.body).parent().removeAttr("style");
        $(".show_bigImgBg,.show_bigImgCon").remove();
    });
    $(".bigImg_list").Xslider({
        unitdisplayed: 8,
        numtoMove: 8,
        unitlen: 110,
        scrollobj: ".list_box div"
    });
}
/*快捷导航*/
$(function() {
    $(".top_state_bar .esb").hover(function() {
        $(this).addClass("hover");
    }, function() {
        $(this).removeClass("hover");
    });
})
/*品牌外站--查看图片*/
$(function() {
    $(".cart_imglist").each(function() {
        $(this).find("li a").click(function() {
            var eqnum = $(this).attr("eqnum");
            show_bigImg($(this).parents(".cart_imglist"), eqnum);
        });
    });
})
//阻止事件冒泡
function stopPropagation(event) {
    var e = window.event || event;
    if (e.stopPropagation) {
        e.stopPropagation();
    } else {
        e.cancelBubble = true;
    }
}
/*IE执行pNG透明*/
if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
	$('<script type="text/javascript" src="/resources/js/DD_belatedPNG.js"></script><script>DD_belatedPNG.fix(".pngFix");</script>').appendTo("head");

} 
/*文本字数计算*/
function countNum(num, obj) {
	var len = Math.round($(obj).val().replace(/[^\x00-\xff]/g, '**').length / 2);
	if (len >= num) {
		$(obj).val($(obj).val().substring(0, num));
	}
	var count = num - len;
	if (count < 0) {
		count = 0
	}
	$(obj).siblings().find(".count").html(count);
}
/*顶部导航条 new 2014-09-25*/
$(function(){
$(".mobileApp").hover(
 function () {
    $(this).addClass("hover4");
	$(this).children(".othermenu").show();
  },
  function () {
    $(this).removeClass("hover4");
	$(this).children(".othermenu").hide();
  }

);	
$(".shop-cart").hover(
 function () {
    $(this).addClass("hover2");
	$(this).children(".othermenu").show();
  },
  function () {
    $(this).removeClass("hover2");
	$(this).children(".othermenu").hide();
  }

);	
$(".username").hover(
 function () {
    $(this).addClass("hover1");
	$(this).children(".othermenu").show();
  },
  function () {
    $(this).removeClass("hover1");
	$(this).children(".othermenu").hide();
  }

);	
$(".navigation").hover(
 function () {
    $(this).addClass("hover3");
	$(this).children(".othermenu").show();
  },
  function () {
    $(this).removeClass("hover3");
	$(this).children(".othermenu").hide();
  }
);
})



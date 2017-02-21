
$(function() {
	$('#messageText').keydown(function(e) {
		if (e.ctrlKey && e.which == 13 || e.which == 10) {
			sendMessage();
			document.body.focus();
		} else if (e.shiftKey && e.which == 13 || e.which == 10) {
			sendMessage();
			document.body.focus();
		}
	})

});

function bindingEvent() {
	var activeIndex = null;
	// 二级导航栏
	$('.second-nav >div> li').each(
			function(i) {
				$(this).on(
						'click',
						function(e) {
							if ($('.second-nav >div> li.active').next()
									.hasClass('third-nav')
									&& !$(this).hasClass('active')
									&& $('.second-nav >div> li.active').find(
											'i').hasClass(
											'glyphicon-chevron-down')) {
								activeIndex = null;
								$('.second-nav >div> li.active').next(
										'.third-nav').slideToggle("slow");
								$('.second-nav >div> li.active').find('i')
										.toggleClass('glyphicon-chevron-down');
								$('.second-nav >div> li.active').next(
										'.third-nav').find('li.active')
										.removeClass('active');
							}
							$('.second-nav >div> li.active').removeClass(
									'active');
							$(this).toggleClass('active');
							if ($(this).next().hasClass('third-nav')) {
								if ($(this).find('i').hasClass(
										'glyphicon-chevron-down')) {
									$(this).next('.third-nav').slideUp("slow");
								} else {
									// 三级导航选中的回显
									$(this).next('.third-nav')
											.slideDown("slow");
									if (!isEmpty(activeIndex)) {
										$(this).next('.third-nav').children(
												'li').eq(activeIndex).addClass(
												'active');
									}
								}
								$(this).find('i').toggleClass(
										'glyphicon-chevron-down');

							} else {
								window.parent.mainFrame.location.href = $(this)
										.find('a').attr('href');
							}
							e.stopPropagation();
						});
			});

	// 三级导航栏
	$('.third-nav > li').each(function(i) {
		$(this).on('click', function(e) {
			// openMessage($(this).val());
			$('.third-nav > li').removeClass('active');
			$(this).toggleClass('active');
			activeIndex = $(this).index();
			e.stopPropagation();
		});
	});
}
    var istrue = true;
	var myuserId; //自己的ID 
	var touserId; //对方的ID
	var index1 = 0;
	var index2 = 0;
	var num = 0;
	var dateFormat="yyyy-MM-dd hh:mm:ss";
	//聊天窗口
	var messageWindow = $("#messageWindow");
	
function openMessage(id1,id2,index,x,y) {
	var chatLog = null;
	$("#newNum"+index).hide();
	myuserId = id1;
	touserId = id2;
	index1 = x;
	index2 = y;
	num = index;
	var m = result[index1][index2];
	messageWindow.empty();
	var img;
	var count=true;
	var count2=true;
	var log;
	istrue = true;
	$("#myModalLabel").empty();
	$("#myModalLabel").append("正在和"+m.member.memberAlias+"聊天");
	
	$.ajax({
		type : "GET",
		url : ctx+"/message/getChatLog?fromUserId="+myuserId+"&toUserId="+touserId,
		error : function(request) {
			console.log("error");
			swal("请求消息出错:"+request);
		},
		success : function(data) {
			chatLog =  data.resultMap.page;
			for (var i = 0; i < chatLog.length; i++) {
				log  = chatLog[i];
				if(log.userId == m.fromUserId){
					if(count){
						var time;
						if(log.time!=null&&log.time!=''){
							 time = new Date(log.time).format(dateFormat);
						}else {
							 time = new Date().format(dateFormat);
						}
						messageWindow.append("<li style='text-align:center;'>"+time.toString()+"</li>");
						count=false;
					}
					img = imgPath(m.member.memberLogo);
					messageWindow.append("<li style='padding:2;'><img src="+img+" width='42px' height='42px' style='margin-right: 1px; border-radius: 5px;' />&nbsp;&nbsp;"+log.message+"</li>"); 
				}
				if(log.userId == m.myMember.userId){
					if(count2){
						var time;
						if(log.time!=null&&log.time!=''){
							 time = new Date(log.time).format(dateFormat);
						}else {
							 time = new Date().format(dateFormat);
						}
						messageWindow.append("<li style='text-align:center;'>"+time.toString()+"</li>");
						count2=false;
					}
					img = imgPath(m.myMember.memberLogo);
					messageWindow.append("<li style='padding:2;text-align:right;'>"+log.message+"&nbsp;&nbsp;<img src="+img+" width='42px' height='42px' style='margin-right: 1px; border-radius: 5px;' /></li>");
				}
				
			}
			istrue = true;
		}
	});
	
	$('#openMessage').modal({
		keyboard : false,
		show : true
	});
	
}


function im() {
	var chatLog = null;
	var m = result[index1][index2];
	messageWindow.empty();
	var img;
	var count=true;
	var count2=true;
	var log;
	
	$("#myModalLabel").empty();
	$("#myModalLabel").append("正在和"+m.member.memberAlias+"聊天");
	
	$.ajax({
		type : "GET",
		url : ctx+"/message/inTimeChat?fromUserId="+myuserId+"&toUserId="+touserId,
		error : function(request) {
			console.log("error");
			swal("请求消息出错:"+request);
		},
		success : function(data) {
			chatLog =  data.resultMap.page;
			for (var i = 0; i < chatLog.length; i++) {
				log  = chatLog[i];
				if(log.userId == m.fromUserId){
					if(count){
						var time;
						if(log.time!=null&&log.time!=''){
							 time = new Date(log.time).format(dateFormat);
						}else {
							 time = new Date().format(dateFormat);
						}
						messageWindow.append("<li style='text-align:center;'>"+time.toString()+"</li>");
						count=false;
					}
					
					img = imgPath(m.member.memberLogo);
					messageWindow.append("<li style='padding:2;'><img src="+img+" width='42px' height='42px' style='margin-right: 1px; border-radius: 5px;' />&nbsp;&nbsp;"+log.message+"</li>"); 
				}
				
				if(log.userId == m.myMember.userId){
					if(count2){
						var time;
						if(log.time!=null&&log.time!=''){
							 time = new Date(log.time).format(dateFormat);
						}else {
							 time = new Date().format(dateFormat);
						}
						messageWindow.append("<li style='text-align:center;'>"+time.toString()+"</li>");
						count2=false;
					}
					img = imgPath(m.myMember.memberLogo);
					messageWindow.append("<li style='padding:2;text-align:right;'>"+log.message+"&nbsp;&nbsp;<img src="+img+" width='42px' height='42px' style='margin-right: 1px; border-radius: 5px;' /></li>");
				}
				
			}
			istrue = true;
			
		}
	});
	
}



function imgPath(img){
	if (img != '' && img != null) {
		if (!isContains(img, "http://")) {
			img =  "http://image.dadasports.cn"  + img;
		}
	} else {
		img = ctx+"/resources/images/default.png";
	}
	return img;
}

function isContains(str, substr) {
	return str.indexOf(substr) >= 0;
}

function checkContent() {
	var messageText = $("#messageText");
	if (messageText.val() != '') {
		$("#tishi").hide();
	}
	
}
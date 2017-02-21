<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>哒哒运动</title>
		<link rel="stylesheet" href="${ctx }/resources/css/index.css" />
		<script type="text/javascript">
			var ctx = '${ctx}';
		</script>
	</head>
	<body style="overflow:hidden;">
		<header id="frame-header">
			<nav class="navbar navbar-default navbar-fixed-top">
			  <div class="frame-header-container">
			      <a class="navbar-brand" href="#">
			        	<img alt="Brand" src="${ctx }/resources/images/logo.png" style="margin-top: -10px;margin-left: 10px;">
			      </a>
			      <p class="navbar-text"><font size="4rem;" text-shadow="5px 2px 6px #000;">DaDa Sports</font></p>
			      <ul class="nav navbar-nav navbar-left first-nav">
			     		 <li role="presentation" data-id="work_station">
	                         <a href="#" target="mainFrame"><font>首页</font></a>
	                      </li>
			      	  <%-- <shiro:hasPermission name="view:myWorkstation"> --%>
						  <li role="presentation" data-id="work_station">
	                         <a href="#"><font>我的工作台</font></a>
	                      </li>
                      <%-- </shiro:hasPermission> --%>
                      <shiro:hasPermission name="manage:system">
                      	<li role="presentation" data-id="system_manage">
	                         <a href="#"><font>系统管理</font></a>
	                    </li>
                      </shiro:hasPermission>   
				  </ul>
			      <ul class="nav navbar-nav navbar-right">
			      	<%--  <li role="presentation">
			      	 	<c:if test="${ not empty user.headPortrait}">
			      	 		<img src="${image }${user.headPortrait}" id="user_profile_image">
			      	 	</c:if>
	                 </li> --%>
			      	 <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
                        	<i class="glyphicon glyphicon-user"></i>  
                        	<shiro:principal/> 
                        	<i class="caret"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="#" id="user_setting">用户设置</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a tabindex="-1" href="${ctx}/adminuser/logout">退出</a>
                            </li>
                        </ul>
                     </li>
			      </ul>
			  </div>
			</nav>
		</header>
		<div id="frame-center">
			<div id="left-nav">
				<div class="list-group second-nav">
                    <%-- <a href="${ctx}/user/dashboard.html" class="list-group-item" target="mainFrame"><font color="#337ab7">Dashboard</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right" style="float: right;"></i> </a>
                    <a href="${ctx}/user/calendar.html" class="list-group-item" target="mainFrame"><font color="#337ab7">Calendar</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right"></i> </a>
                    <a href="${ctx}/user/stats.html" class="list-group-item" target="mainFrame"> <font color="#337ab7">Statistics (Charts)</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right"></i></a>
                    <a href="${ctx}/user/form.html" class="list-group-item" target="mainFrame"> <font color="#337ab7">Forms</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right"></i></a>
                    <a href="${ctx}/user/tables.html" class="list-group-item" target="mainFrame"> <font color="#337ab7">Tables</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right"></i></a>
                    <a href="${ctx}/user/buttons.html" class="list-group-item" target="mainFrame"> <font color="#337ab7">Buttons & Icons</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right"></i></a>
                    <a href="${ctx}/user/editors.html" class="list-group-item" target="mainFrame"> <font color="#337ab7">WYSIWYG Editors</font><i class="glyphicon glyphglyphicon glyphicon-chevron-right"></i></a>
                    <a href="${ctx}/user/interface.html" class="list-group-item" target="mainFrame"><font color="#337ab7">UI & Interface</font><i class="glyphicon glyphicon-chevron-right"></i> </a> --%>
                   	<div id="work_station" style="display: none;">
	                 <%--   	<shiro:hasPermission name="manage:intelligent">
	                   		<li class="list-group-item">
		                 		<a href="${ctx}/intelligent/list">
				                    <font>社会达人</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                </shiro:hasPermission> --%>
		              <%--   <shiro:hasPermission name="manage:activity">
		                   	<li class="list-group-item">
		                 		<a href="${ctx}/activity/list">
				                    <font>活动管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                </shiro:hasPermission>  --%>
		                <%--  <shiro:hasPermission name="manage:member">
		                   	<li class="list-group-item">
		                 		<a href="#">
				                    <font>会员管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                    <ul class="nav third-nav" role="navigation">
		                    	<shiro:hasPermission name="manage:member">
		                   			<li><a href="${ctx}/member/list" target="mainFrame"><font>会员</font></a></li>
		                   		</shiro:hasPermission>
		                   		<shiro:hasPermission name="manage:member">
		                   			<li><a href="${ctx}/show/list" target="mainFrame"><font>哒人秀</font></a></li>
		                   		</shiro:hasPermission>
		                   		<shiro:hasPermission name="manage:member">
		                   			<li><a href="${ctx}/topic/list" target="mainFrame"><font>约运动</font></a></li>
		                   		</shiro:hasPermission>
		                   	</ul>
		                </shiro:hasPermission> --%> 
		                <%-- <shiro:hasPermission name="manage:sportsVenue">
		                   	<li class="list-group-item">
		                 		<a href="${ctx}/sportsvenues/list">
				                    <font>场馆管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                </shiro:hasPermission> --%>
		                <%-- <shiro:hasPermission name="manage:club">
		                   	<li class="list-group-item">
		                 		<a href="${ctx}/club/list">
				                    <font>部落管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                </shiro:hasPermission> --%>
		                <%-- <shiro:hasPermission name="manage:gift">
		                   	<li class="list-group-item">
		                 		<a href="#">
				                    <font>趣跑</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   	<ul class="nav third-nav" role="navigation">
		                   		<li><a href="${ctx}/gifttype/list" target="mainFrame"><font>奖品分类</font></a></li>
	                   			<li><a href="${ctx}/gift/list" target="mainFrame"><font>奖品</font></a></li>
	                   			<li><a href="${ctx}/indianadetail/list" target="mainFrame"><font>获奖列表</font></a></li>
		                   	</ul>
		                  </shiro:hasPermission> --%>
		                    <li class="list-group-item">
		                 		<a href="${ctx}/overview/list">
				                    <font>首页</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                    <li class="list-group-item">
		                 		<a href="#">
				                    <font>用户管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   	<ul class="nav third-nav" role="navigation">
		                   		<li><a href="${ctx}/user/list" target="mainFrame"><font>用户列表</font></a></li>
		                   		<li><a href="${ctx}/step/list" target="mainFrame"><font>步数排行</font></a></li>
		                   		<li><a href="${ctx}/run/list" target="mainFrame"><font>跑步列表</font></a></li>
		                   	</ul>
		                   	<li class="list-group-item">
		                 		<a href="#">
				                    <font>运营管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   	<ul class="nav third-nav" role="navigation">
		                   		 <shiro:hasPermission name="manage:withdrawback"> 
		                   		<li><a href="${ctx}/withdraw/list" target="mainFrame"><font>提现处理</font></a></li>
		                   		</shiro:hasPermission> 
	                   			<li><a href="${ctx}/feedback/list" target="mainFrame"><font>意见反馈</font></a></li>
	                   			<li><a href="${ctx}/message/list" target="mainFrame"><font>消息群发</font></a></li>
	                   			<li><a href="${ctx}/user/getClientIdList" target="mainFrame"><font>设备监控</font></a></li>
	                   			<li><a href="${ctx}/goldbeanrecharge/list" target="mainFrame"><font>汇率设定</font></a></li>
		                   	</ul>
		                   	<li class="list-group-item">
		                 		<a href="#">
				                    <font>活动管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   	<ul class="nav third-nav" role="navigation">
		                   		<li><a href="${ctx}/activity/list" target="mainFrame"><font>活动列表</font></a></li>
		                   		<li><a href="${ctx}/medal/list" target="mainFrame"><font>勋章列表</font></a></li>
		                   		<li><a href="${ctx}/lottery/list" target="mainFrame"><font>彩票管理</font></a></li>
		                   	</ul>
		                  <%--  <li class="list-group-item">
		                 		<a href="${ctx}/message/list?userId=${user.id }">
				                    <font>私信管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   	
		                    <li class="list-group-item">
		                 		<a href="#">
				                    <font>运营数据</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   	<ul class="nav third-nav" role="navigation">
		                   		<li><a href="${ctx}/statistics/list?adminUserId=${user.id }" target="mainFrame"><font>实时统计</font></a></li>
		                   		<shiro:hasPermission name="manage:listcount">
	                   			<li><a href="${ctx}/statistics/listcount" target="mainFrame"><font>整体统计</font></a></li>
	                   			</shiro:hasPermission>
		                   	</ul>
		                   	<shiro:hasPermission name="manage:withdrawback">
		                   	<li class="list-group-item">
		                 		<a href="${ctx}/userwithdrawpackage/list">
				                    <font>提现管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                   
		                </shiro:hasPermission>
		                <shiro:hasPermission name="manage:topicappeal">
		                	<li class="list-group-item">
		                 		<a href="${ctx}/topicappeal/list">
				                    <font>申诉管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
		                 </shiro:hasPermission>
		                 <shiro:hasPermission name="manage:question">
			                   	<li class="list-group-item">
			                 		<a href="${ctx}/question/list">
					                    <font>智能问答</font>
					                    <i class="glyphicon glyphicon-chevron-right"></i>
			                		 </a>
			                   	</li>
		                 </shiro:hasPermission>
		                
			                   	<li class="list-group-item">
			                 		<a href="${ctx}/question/list">
					                    <font>审核管理</font>
					                    <i class="glyphicon glyphicon-chevron-right"></i>
			                		 </a>
			                   	</li>
								<ul class="nav third-nav" role="navigation">
									<li>
									 <a href="${ctx}/userrealauth/list" target="mainFrame"><font>真实性认证审核</font></a>
									</li>
								</ul> --%>

				</div>
                	<div id="system_manage" style="display: none;">
                   		<shiro:hasPermission name="manage:user">
	                   		<li class="list-group-item">
		                 		<a href="${ctx}/adminuser/list">
				                    <font>系统用户</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
	                   	</shiro:hasPermission>
	                   	  
	                   	<shiro:hasPermission name="manage:role">
		                   	<li class="list-group-item">
		                 		<a href="${ctx }/role/list">
				                    <font>角色管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
                   		</shiro:hasPermission>
                   		<shiro:hasPermission name="manage:auth">
		                   	<li class="list-group-item">
		                 		<a href="${ctx}/auth/list">
				                    <font>权限管理</font>
				                    <i class="glyphicon glyphicon-chevron-right"></i>
		                		 </a>
		                   	</li>
	                   	</shiro:hasPermission>
                   	</div>
                </div>
			</div>
			<div id="main-content">
				<iframe id="mainFrame" name="mainFrame" src="${ctx}/adminuser/dashboard.html" style="overflow:hidden;" scrolling="no" frameborder="no"></iframe>
			</div>
		</div>
		<footer id="frame-footer">
		  <div class="container">
		  	<div align="center" style="margin-top: 15px;">
		  		© 2015 GungZhou dadasports Co., Ltd. All Rights Reserved
		  	</div>
		  </div>
		 </footer>
		 
		  <div class="modal fade" id="user_setting_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">用户设置</h4>
			      </div>
			      <div class="modal-body" style="max-height: 550px;overflow-y: auto">
		      		<ul id="user_setting_nav" class="nav nav-tabs" style="width: 92px;">
						<li class="active" ><a href="#user_information" data-toggle="tab">个人信息</a></li>
						<li><a href="#change_password" data-toggle="tab">修改密码</a></li>
						<li><a href="#change_head_portrait" data-toggle="tab">头像设置</a></li>
					</ul>
					<div class="tab-content">
						<div id="user_information" class="tab-pane active">
							<form>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>姓名：</label>
										<p class="form-control-static">${adminUser.nickname}</p>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>邮箱：</label>
										<p class="form-control-static">${adminUser.email}</p>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>用户名：</label>
										<p class="form-control-static">${adminUser.username}</p>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>生日：</label>
										<p class="form-control-static">
											${adminUser.birthday}
										</p>
									</div>
								</div>
							</form>
						</div>
						<div id="change_password" class="tab-pane">
							<form id="change_password_form">
								<div class="row">
									<div class="form-group col-xs-6">
										<label>原密码：</label>
										<div>
											<input class="form-control" type="password" name="oldPassword" placeholder="原密码...">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label>新密码：</label>
										<div>
											<input class="form-control" type="password" name="password" placeholder="新密码...">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label>确认新密码：</label>
										<div>
											<input class="form-control check-new-password" type="password" name="repeatPassword" placeholder="重复新密码以确认...">
										</div>
									</div>
								</div>
							</form>
							<button id="change_user_password_submit" class="btn btn-success" type="button">提交</button>
						</div>
						<div id="change_head_portrait" class="tab-pane">
							<div id="user_profile_current_head">
								<div id="user_profile_current_head_title">
									<p class="user-profile-head-p">当前头像</p>
									<div >
										<img src="${image }${adminUser.headPortrait}" width="100px;" height="100px;" class="user_profile_current_head_img"/>
									</div>
									<div id="user_profile_current_change">
										<a href="#" id="user_profile_current_change_button">更换头像</a>
									</div>
									<input type="hidden" value="${adminUser.id}" id="userId"/>
									<input class="btn" id="fileupload" type="file" style="display: none;"/>
								</div>
							</div>
					  </div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
			    </div>
			  </div>
			  </div>
			  </div>
			  
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
		<script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
		<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/index.js" ></script>
	</body>
</html>
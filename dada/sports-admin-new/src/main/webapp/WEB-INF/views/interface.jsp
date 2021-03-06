<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%-- <%@include file="/common/common-header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <title>Interface</title>
        <!-- Bootstrap -->
        <link href="${ctx }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="${ctx }/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="${ctx }/resources/assets/styles.css" rel="stylesheet" media="screen">
        <link href="${ctx }/resources/vendors/jGrowl/jquery.jgrowl.css" rel="stylesheet" media="screen">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="${ctx }/resources/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">Alerts</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
									<div class="alert">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>Warning!</strong> This is a warning message.
									</div>
									<div class="alert alert-success">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>Success!</strong> This is a success message.
									</div>
									<div class="alert alert-info">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>Info!</strong> This is an information message.
									</div>
									<div class="alert alert-error">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>Error!</strong> This is an error message.
									</div>
									<h4>Block Alerts</h4>
									<div class="alert alert-block">
										<a class="close" data-dismiss="alert" href="#">&times;</a>
										<h4 class="alert-heading">Warning!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>
									<div class="alert alert-success alert-block">
										<a class="close" data-dismiss="alert" href="#">&times;</a>
										<h4 class="alert-heading">Success!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">&times;</a>
										<h4 class="alert-heading">Info!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>
									<div class="alert alert-error alert-block">
										<a class="close" data-dismiss="alert" href="#">&times;</a>
										<h4 class="alert-heading">Error!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>

								</div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>

                    <div class="row-fluid">
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">Progress Bars</div>
                                </div>
                                <div class="block-content collapse in">
                                    <div class="span12">
										<h4>Basic progress bars</h4>
										<div class="progress">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-success">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-warning">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-danger">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<h4>Striped progress bars</h4>
										<div class="alert">
											Internet Explorer doesn't support striped progress bars!
										</div>
										<div class="progress progress-striped">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-striped progress-success">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-striped progress-warning">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-striped progress-danger">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<h4>Animated progress bars</h4>										
										<div class="alert">
											Internet Explorer doesn't support animated progress bars!
										</div>
										<div class="progress progress-striped active">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-striped progress-success active">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-striped progress-warning active">
											<div style="width: 60%;" class="bar"></div>
										</div>
										<div class="progress progress-striped progress-danger active">
											<div style="width: 60%;" class="bar"></div>
										</div>
                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">Labels & Badges</div>
                                </div>
                                <div class="block-content collapse in">
                                    <div class="span12">

										<h4>Available labels</h4>
										<table class="table table-bordered table-striped">
											<thead>
											  <tr>
												<th>Labels</th>
												<th>Markup</th>
											  </tr>
											</thead>
											<tbody>
											  <tr>
												<td>
												  <span class="label">Default</span>
												</td>
												<td>
												  <code>&lt;span class="label"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  <span class="label label-success">Success</span>
												</td>
												<td>
												  <code>&lt;span class="label label-success"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  <span class="label label-warning">Warning</span>
												</td>
												<td>
												  <code>&lt;span class="label label-warning"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  <span class="label label-important">Important</span>
												</td>
												<td>
												  <code>&lt;span class="label label-important"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  <span class="label label-info">Info</span>
												</td>
												<td>
												  <code>&lt;span class="label label-info"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  <span class="label label-inverse">Inverse</span>
												</td>
												<td>
												  <code>&lt;span class="label label-inverse"&gt;</code>
												</td>
											  </tr>
											</tbody>
										  </table>
											<h4>Available badges</h4>
											<table class="table table-bordered table-striped">
											<thead>
											  <tr>
												<th>Name</th>
												<th>Example</th>
												<th>Markup</th>
											  </tr>
											</thead>
											<tbody>
											  <tr>
												<td>
												  Default
												</td>
												<td>
												  <span class="badge">1</span>
												</td>
												<td>
												  <code>&lt;span class="badge"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  Success
												</td>
												<td>
												  <span class="badge badge-success">2</span>
												</td>
												<td>
												  <code>&lt;span class="badge badge-success"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  Warning
												</td>
												<td>
												  <span class="badge badge-warning">4</span>
												</td>
												<td>
												  <code>&lt;span class="badge badge-warning"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  Important
												</td>
												<td>
												  <span class="badge badge-important">6</span>
												</td>
												<td>
												  <code>&lt;span class="badge badge-important"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  Info
												</td>
												<td>
												  <span class="badge badge-info">8</span>
												</td>
												<td>
												  <code>&lt;span class="badge badge-info"&gt;</code>
												</td>
											  </tr>
											  <tr>
												<td>
												  Inverse
												</td>
												<td>
												  <span class="badge badge-inverse">10</span>
												</td>
												<td>
												  <code>&lt;span class="badge badge-inverse"&gt;</code>
												</td>
											  </tr>
											</tbody>
										  </table>
                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">Tooltips, Popovers & Pagination</div>
                                </div>
                                <div class="block-content collapse in">
                                    <div class="span12">
                                    	<h4></h4>
	                                	<div style="padding:50px;">
	                                		<button class="btn tooltip-top" data-original-title="Tooltip in top">Top</button>
											<button class="btn tooltip-left" data-original-title="Tooltip in left">Left</button>
											<button class="btn tooltip-right" data-original-title="Tooltip in right">Right</button>
											<button class="btn tooltip-bottom" data-original-title="Tooltip in bottom">Bottom</button>
	                                	</div>

	                                	<div style="padding-left:50%;">
	                                		<button class="btn popover-top" data-original-title="Popover in top">Top</button>
											<button class="btn popover-left" data-original-title="Popover in left">Left</button>
	                                	</div>

	                                	<div style="padding-left:20%;">
	                                		<button class="btn popover-right" data-original-title="Popover in right">Right</button>
											<button class="btn popover-bottom" data-original-title="Popover in bottom">Bottom</button>
	                                	</div>

	                                	<div class="pagination">
											<ul>
												<li><a href="#">Prev</a></li>
												<li class="active">
													<a href="#">1</a>
												</li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">Next</a></li>
											</ul>
										</div>

										<div class="pagination pagination-small">
											<ul>
												<li class="disabled"><a href="#">Prev</a></li>
												<li class="active">
													<a href="#">1</a>
												</li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">Next</a></li>
											</ul>
										</div>

										<div class="pagination pagination-mini">
											<ul>
												<li class="disabled"><a href="#">Prev</a></li>
												<li class="active">
													<a href="#">1</a>
												</li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">Next</a></li>
											</ul>
										</div>
                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">Modals & Notifications</div>
                                </div>
                                <div class="block-content collapse in">
                                    <div class="span12">
										<a href="#myModal" data-toggle="modal" class="btn btn-primary">Modal dialog</a>
										<a href="#myAlert" data-toggle="modal" class="btn btn-danger">Alert</a>
										
										<div id="myModal" class="modal hide">
											<div class="modal-header">
												<button data-dismiss="modal" class="close" type="button">&times;</button>
												<h3>Modal header</h3>
											</div>
											<div class="modal-body">
												<p>Modal Example Body</p>
											</div>
										</div>
										<div id="myAlert" class="modal hide">
											<div class="modal-header">
												<button data-dismiss="modal" class="close" type="button">&times;</button>
												<h3>Alert modal</h3>
											</div>
											<div class="modal-body">
												<p>Lorem ipsum dolor sit amet...</p>
											</div>
											<div class="modal-footer">
												<a data-dismiss="modal" class="btn btn-primary" href="#">Confirm</a>
												<a data-dismiss="modal" class="btn" href="#">Cancel</a>
											</div>
										</div>

										<div style="margin-top: 20px;">
											<button class="btn notification" id="notification">Notification</button><br />
											<button class="btn notification" id="notification-sticky">Sticky Notification</button><br />
											<button class="btn notification" id="notification-header">Notification With Header</button>
										</div>

                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                    </div>

                </div>
            </div>
            <hr>
            <footer>
                <p>&copy; Vincent Gabriel 2013</p>
            </footer>
        </div>
        <!--/.fluid-container-->
        <script src="${ctx }/resources/vendors/jquery-1.9.1.min.js"></script>
        <script src="${ctx }/resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="${ctx }/resources/vendors/jGrowl/jquery.jgrowl.js"></script>
        <script src="${ctx }/resources/assets/scripts.js"></script>
        <script>
        $(function() {
            $('.tooltip').tooltip();	
			$('.tooltip-left').tooltip({ placement: 'left' });	
			$('.tooltip-right').tooltip({ placement: 'right' });	
			$('.tooltip-top').tooltip({ placement: 'top' });	
			$('.tooltip-bottom').tooltip({ placement: 'bottom' });

			$('.popover-left').popover({placement: 'left', trigger: 'hover'});
			$('.popover-right').popover({placement: 'right', trigger: 'hover'});
			$('.popover-top').popover({placement: 'top', trigger: 'hover'});
			$('.popover-bottom').popover({placement: 'bottom', trigger: 'hover'});

			$('.notification').click(function() {
				var $id = $(this).attr('id');
				switch($id) {
					case 'notification-sticky':
						$.jGrowl("Stick this!", { sticky: true });
					break;

					case 'notification-header':
						$.jGrowl("A message with a header", { header: 'Important' });
					break;

					default:
						$.jGrowl("Hello world!");
					break;
				}
			});
        });
        </script>
    </body>

</html>
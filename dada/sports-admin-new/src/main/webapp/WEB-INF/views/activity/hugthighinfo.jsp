<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新增抱大腿</title>
	<style type="text/css">
		#mapContainer{height:480px;}
			#result1{
				position:absolute;
				z-index: 100;
				background-color: white;
			}
			#imglist{
				display: inline-flex;
				
			}
			.imgbox{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
				margin-left: 1rem;
			}
			.imgbox img{
				z-index:100;float: right;margin-top: -10px;margin-right:-25px; 
			}
			
			#logoimage{
				display: inline-flex;
				
			}
			.imgbox_logo{
				width:72px;
				height:72px;
				background-size: cover;
				margin-right:15px;
			}
			.imgbox_logo img{
				z-index:100;float: right;margin-top: -10px;margin-right:-10px; 
			}
			.btn-upload, .logo-image {
			    display: inline-block;
			    width: 72px;
			    height: 72px;
			    background: url(${ctx}/resources/images/addimage.png) no-repeat;
			    background-size: contain;
			    margin-left: 1rem;
			}
			.btn-upload>input {
			    opacity: 0;
			    height: 100%;
			    width: 100%;
			}
	</style>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" id="myTab">
		  <li class="active"><a href="#activityInfo">活动详情</a></li>
		  <li><a href="#userInfo" onclick="userInfoPage(${activity.id})">用户详情</a></li>
		  <li><a href="#incomeInfo" onclick="incomeInfoPage(${activity.id})">收支详情</a></li>
		</ul>
		<form class="form-horizontal" action="${ctx}/activity/updateHugThigh" method="post">
			
			<!-- 隐藏表单 -->
			<input type="hidden" name="id"  value="${activity.id}"/>
			<input type="hidden" name="couponsList[0].id" value="${activity.couponsList[0].id}" />
			<input type="hidden" name="couponsList[1].id" value="${activity.couponsList[1].id}" />
			<input type="hidden" name="couponsList[2].id" value="${activity.couponsList[2].id}" />
			<input type="hidden" name="couponsList[3].id" value="${activity.couponsList[3].id}" />
			<input type="hidden" name="couponsList[4].id" value="${activity.couponsList[4].id}" />
			<input type="hidden" name="couponsList[5].id" value="${activity.couponsList[5].id}" />
			<input type="hidden" name="couponsList[6].id" value="${activity.couponsList[6].id}" />
			
			<input type="hidden" name="hugThighActivity.id" value="${activity.hugThighActivity.id}" />
			
			<jsp:useBean id="myDate" class="java.util.Date" />
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动类型：</label>
				<label class="col-sm-0 control-label" >抱大腿</label>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动日期：</label>
				<div class="col-sm-2">
					<c:set target="${myDate}" property="time" value="${activity.date*1000}" /> 
					<input class="form-control" type="text" id="adate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" />" readonly="readonly" />
				</div>
				<label class="col-sm-1 control-label" >封面：</label>
				<div class="col-md-2">
					    <div class="col-sm-0 btn-upload logo-upload">
							<input id="logoupload" type="file" />
						</div>
						<div id="logoimage" style="display: none;">
							 
						</div>
						<div class="form-group" id="logo_image" style="display: none"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动时间：</label>
				<div  class="col-sm-3  form-inline">
					<c:set target="${myDate}" property="time" value="${activity.beginTime}" /> 
					<input class="form-control" id="bTime" type="time" value="<fmt:formatDate pattern="HH:mm" value="${myDate}" />" style="width: 115px;" readonly="readonly" /> 至
					<c:set target="${myDate}" property="time" value="${activity.endTime}" /> 
					<input class="form-control" id="eTime" type="time" value="<fmt:formatDate pattern="HH:mm" value="${myDate}" />" style="width: 115px;" readonly="readonly"/>
					<input type="hidden" id="beginTime" name="beginTime" />
					<input type="hidden" id="endTime" name="endTime" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >大腿名额：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="dtme" name="hugThighActivity.thighLimit" value="${activity.hugThighActivity.thighLimit}" readonly="readonly" />
				</div>
				<label class="col-sm-1 control-label" >被抱次数：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="dtcs" name="hugThighActivity.hugThighLimit" value="${activity.hugThighActivity.hugThighLimit}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >佣金分配：</label>
				<div class="col-sm-2 form-inline">
				            用户获取佣金的
					<select class="form-control" id="yjSelect" onchange="yj(this)" disabled="disabled">
					
				    </select>
				</div>
				<label class="col-sm-1 control-label" >平台获得：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="text" id="pthd" value="" readonly="readonly" />
				</div>
			</div>
			<div class="form-group" style="padding-left: 50px;">
				<table class="table table-bordered table-hover" style="width: 80%;">
				<thead>
					<tr>
					    <th>大腿等级</th>
						<th>单次跑量</th>
						<th>结算利率</th>
						<th>体验金补贴(元)</th>
						<th>支付佣金(元)</th>
						<th>用户获得佣金(元/100人)</th>
						<th>平台获得佣金(元/100人)</th>
					</tr>
				</thead>
						<tbody>
							<tr>
							    <td>一级<input type="hidden" name="couponsList[0].type" value="1" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL1" name="couponsList[0].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL1" name="couponsList[0].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj1" name="couponsList[0].bonus" value="${activity.couponsList[0].bonus}" readonly="readonly" /></td>
							    <td><input class="form-control" type="text" id="yj1" name="couponsList[0].commission" value="${activity.couponsList[0].commission}" onchange="zfyj(this,'label1','pt1')" readonly="readonly" /></td>
							    <td><label id="label1">0.00</label></td>
							    <td><label id="pt1">0.00</label></td>  
							</tr>
							<tr>
							    <td>二级<input type="hidden" name="couponsList[1].type" value="2" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL2" name="couponsList[1].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL2" name="couponsList[1].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj2" name="couponsList[1].bonus" value="${activity.couponsList[1].bonus}" readonly="readonly"/></td>
							    <td><input class="form-control" type="text" id="yj2" name="couponsList[1].commission" value="${activity.couponsList[1].commission}" onchange="zfyj(this,'label2','pt2')" readonly="readonly" /></td>
							    <td><label id="label2">0.00</label></td>
							    <td><label id="pt2">0.00</label></td>  
							</tr>
							<tr>
							    <td>三级<input type="hidden" name="couponsList[2].type" value="3" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL3" name="couponsList[2].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL3" name="couponsList[2].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj3" name="couponsList[2].bonus" value="${activity.couponsList[2].bonus}" readonly="readonly"/></td>
							    <td><input class="form-control" type="text" id="yj3" name="couponsList[2].commission" value="${activity.couponsList[2].commission}" onchange="zfyj(this,'label3','pt3')" readonly="readonly" /></td>
							    <td><label id="label3">0.00</label></td>
							    <td><label id="pt3">0.00</label></td>  
							</tr>
							<tr>
							    <td>四级<input type="hidden" name="couponsList[3].type" value="4" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL4" name="couponsList[3].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL4" name="couponsList[3].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj4" name="couponsList[3].bonus" value="${activity.couponsList[3].bonus}" readonly="readonly"/></td>
							    <td><input class="form-control" type="text" id="yj4" name="couponsList[3].commission" value="${activity.couponsList[3].commission}" onchange="zfyj(this,'label4','pt4')" readonly="readonly"/></td>
							    <td><label id="label4">0.00</label></td>
							    <td><label id="pt4">0.00</label></td>  
							</tr>
							<tr>
							    <td>五级<input type="hidden" name="couponsList[4].type" value="5" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL5" name="couponsList[4].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL5" name="couponsList[4].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj5" name="couponsList[4].bonus" value="${activity.couponsList[4].bonus}" readonly="readonly"/></td>
							    <td><input class="form-control" type="text" id="yj5" name="couponsList[4].commission" value="${activity.couponsList[4].commission}" onchange="zfyj(this,'label5','pt5')" readonly="readonly"/></td>
							    <td><label id="label5">0.00</label></td>
							    <td><label id="pt5">0.00</label></td>  
							</tr>
							<tr>
							    <td>六级<input type="hidden" name="couponsList[5].type" value="6" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL6" name="couponsList[5].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL6" name="couponsList[5].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj6" name="couponsList[5].bonus" value="${activity.couponsList[5].bonus}" readonly="readonly"/></td>
							    <td><input class="form-control" type="text" id="yj6" name="couponsList[5].commission" value="${activity.couponsList[5].commission}" onchange="zfyj(this,'label6','pt6')" readonly="readonly"/></td>
							    <td><label id="label6">0.00</label></td>
							    <td><label id="pt6">0.00</label></td>  
							</tr>
							<tr>
							    <td>七级<input type="hidden" name="couponsList[6].type" value="7" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL7" name="couponsList[6].distance" disabled="disabled"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL7" name="couponsList[6].annualYield" disabled="disabled"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj7" name="couponsList[6].bonus" value="${activity.couponsList[6].bonus}" readonly="readonly"/></td>
							    <td><input class="form-control" type="text" id="yj7" name="couponsList[6].commission" value="${activity.couponsList[6].commission}" onchange="zfyj(this,'label7','pt7')" readonly="readonly"/></td>
							    <td><label id="label7">0.00</label></td>
							    <td><label id="pt7">0.00</label></td>  
							</tr>
						</tbody>
				</table> 
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >福利有效期：</label>
				<div class="col-sm-2 form-inline">
					<select class="form-control" id="flSelect" onchange="fl(this)" disabled="disabled">
					
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >平均步幅不超过</label>
				<div class="col-sm-2 form-inline">
					<input class="form-control" type="text" id="pjbf" name="hugThighActivity.stride" value="${activity.hugThighActivity.stride}" style="width: 100px;" readonly="readonly"/>米
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >平均速度不超过</label>
				<div class="col-sm-2 form-inline">
					<input class="form-control" type="number" id="pjsd" name="hugThighActivity.avspeed" value="${activity.hugThighActivity.avspeed}" style="width: 100px;" readonly="readonly"/>km/h
				</div>
				<label class="col-sm-1 control-label" >最快速度不超过</label>
				<div class="col-sm-2 form-inline">
					<input class="form-control" type="number" id="zksd" name="hugThighActivity.maxspeed" value="${activity.hugThighActivity.maxspeed}" style="width: 100px;" readonly="readonly"/>km/h
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动开始推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="hugThighActivity.message" value="${activity.hugThighActivity.message}" style="width: 100%;" readonly="readonly"/>
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" name="hugThighActivity.cronExpression"  value="${activity.hugThighActivity.cronExpression}" readonly="readonly"/>
					<!-- <input type="hidden" id="cronExpression" name="hugThighActivity.cronExpression" /> -->
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="hugThighActivity.rule" style="width: 100%;height: 50%;" readonly="readonly">${activity.hugThighActivity.rule}</textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 90%;">
			    <button class="btn" onclick="javascript :history.back(-1);">关闭</button>
			</div>
			<div id="yhyjbl">
			</div>
			<div id="flts">
			</div>
		</form>
	</div>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.ui.widget.js"></script>
    <script src="${ctx }/resources/libs/load-image/load-image.all.min.js"></script>
    <script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-process.js"></script>
	<script src="${ctx }/resources/libs/jquery-fileupload/jquery.fileupload-image.js"></script>
    <script src="${ctx }/resources/js/activity/updatehugthigh.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
       
		$(function(){
			$('#myTab a').click(function (e) { 
		          e.preventDefault();//阻止a链接的跳转行为 
		          $(this).tab('show');//显示当前选中的链接及关联的content 
		       })
			// alert($("#adate").val()); 
			//alert("${activity.couponsList[0].distance}");  
			
			var imgUrl = image_url+'${activity.cover}';
		 	var html='<div class="imgbox_logo" data-src="${activity.cover}" style="width:170px;z-index:1;background-image: url('+imgUrl+');"></div>';
	  		$("#logoimage").html(html);
	  		$('.logo-upload').hide();
      		$("#logoimage").show(); 
			
			
			var result = '${result}';	
			if(result!=''){
				swal(result);
			} 
			
			initData();
			
			 
		
		});
		
		//上传封面照片
		function uploadLogImg() {
			$('#logoupload').click();
		}
		
		
		var yjSelect = $("#yjSelect");
		
		
		
		//初始化数据
		function initData(){
		
			var htyj = '${activity.couponsList[0].commissionRate}';
	
			
			$("#pthd").val((100-(htyj*100))+"%");
			
			for (var i = 1; i <=100; i++) {
				if(i==(htyj*100)){
					yjSelect.append("<option value='"+i+"' selected='selected'>"+i+"%</option>");
					for (var k = 0; k < 7; k++) {
						//批量设置佣金比例
						$("#yhyjbl").append("<input type='hidden' name='couponsList["+k+"].commissionRate' value='"+htyj+"' />");
					}
				}else{
					yjSelect.append("<option value='"+i+"'>"+i+"%</option>");
				}
			}
			
			
			var arrDistance = new Array();
			arrDistance[0] = "${activity.couponsList[0].distance}";
			arrDistance[1] = "${activity.couponsList[1].distance}";
			arrDistance[2] = "${activity.couponsList[2].distance}";
			arrDistance[3] = "${activity.couponsList[3].distance}";
			arrDistance[4] = "${activity.couponsList[4].distance}";
			arrDistance[5] = "${activity.couponsList[5].distance}";
			arrDistance[6] = "${activity.couponsList[6].distance}";
			
			var arrAnnualYield = new Array();
			arrAnnualYield[0] = "${activity.couponsList[0].annualYield}";
			arrAnnualYield[1] = "${activity.couponsList[1].annualYield}";
			arrAnnualYield[2] = "${activity.couponsList[2].annualYield}";
			arrAnnualYield[3] = "${activity.couponsList[3].annualYield}";
			arrAnnualYield[4] = "${activity.couponsList[4].annualYield}";
			arrAnnualYield[5] = "${activity.couponsList[5].annualYield}";
			arrAnnualYield[6] = "${activity.couponsList[6].annualYield}";
			
			for (var i = 0; i < arrDistance.length; i++) {
				var name="selectDCPL"+(i+1);
				var name2="selectJSLL"+(i+1);
				
				for(var y = 2; y <=10; y++) {
					if(y==arrDistance[i]){
						$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
					}else{
						$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
					}
				}
				var j = 6.0;
				for(;j<=11;) {
					if(j==arrAnnualYield[i]){
						$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
					}else  {
						$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
					}
					
					j=j+0.5;
				}
				
			}
			
			var htflts = "${activity.couponsList[0].expiryDay}";
			for (var i = 1; i <=30; i++) {
				if(i==htflts){
					$("#flSelect").append("<option value='"+i+"' selected='selected'>"+i+"天</option>");
					//设置福利天数
					for (var k = 0; k < 7; k++) {
						$("#flts").append("<input type='hidden' name='couponsList["+k+"].expiryDay' value='7' />");
					}
				}else{
					$("#flSelect").append("<option value='"+i+"' >"+i+"天</option>");
				}
			}
			
			var arrCommission = new Array();
			arrCommission[0] = "${activity.couponsList[0].commission}";
			arrCommission[1] = "${activity.couponsList[1].commission}";
			arrCommission[2] = "${activity.couponsList[2].commission}";
			arrCommission[3] = "${activity.couponsList[3].commission}";
			arrCommission[4] = "${activity.couponsList[4].commission}";
			arrCommission[5] = "${activity.couponsList[5].commission}";
			arrCommission[6] = "${activity.couponsList[6].commission}";
			for (var i = 0; i < arrCommission.length; i++) {
				var count = arrCommission[i]*(yjSelect.val()/100)*100;
				$("#label"+(i+1)).text(parseFloat(count).toFixed(2));
				
				var pt = arrCommission[i]*((100-yjSelect.val())/100)*100;
				$("#pt"+(i+1)).text(parseFloat(pt).toFixed(2));
				
			}
			
		}
		
		//选择佣金比例
		function yj(se){
			$("#pthd").val((100-se.value)+"%");
			$("#yhyjbl").empty();
			for (var i = 1; i <= 7; i++) {
				var yjs = $("#yj"+i).val();
				//alert(yjs);
				var count  = yjs*(se.value/100)*100;
				$("#label"+i).text(parseFloat(count).toFixed(2));
				
				var pt = yjs*((100-se.value)/100)*100;
				$("#pt"+i).text(parseFloat(pt).toFixed(2));
				
				//批量设置佣金比例
				$("#yhyjbl").append("<input type='hidden' name='couponsList["+(i-1)+"].commissionRate' value='"+(se.value/100)+"' />");
			}
		}
		
		//计算支付佣金
		function zfyj(v1,v2,v3){
			//console.log(v1.value);
			var count = v1.value*(yjSelect.val()/100)*100;
			$("#"+v2).text(parseFloat(count).toFixed(2));
			
			var pt = v1.value*((100-yjSelect.val())/100)*100;
			$("#"+v3).text(parseFloat(pt).toFixed(2));
		}
		
		
		//设置福利天数
		function fl(v){
			$("#flts").empty();
			//设置福利天数
			for (var k = 0; k < 7; k++) {
				$("#flts").append("<input type='hidden' name='couponsList["+k+"].expiryDay' value='"+v.value+"' />");
			}
		}
		
		function userInfoPage(activityId){
		     var url="${ctx}/thigh/list?activityId="+activityId;
			 location.href = url;
		}
		
		function incomeInfoPage(activityId){
			 var url="${ctx}/usercoupon/list?activityId="+activityId;
			 location.href = url;
		}
    	
    </script>
</body>
</html>
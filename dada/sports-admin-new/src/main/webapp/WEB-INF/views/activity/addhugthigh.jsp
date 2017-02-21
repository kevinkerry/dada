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
		<form class="form-horizontal" action="${ctx}/activity/addHugThigh" method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动类型：</label>
				<label class="col-sm-0 control-label" >抱大腿</label>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动日期：</label>
				<div class="col-sm-2">
					<input class="form-control" id="adate" type="date"  />
					<input type="hidden" id="date" name="date" />
				</div>
				<label class="col-sm-1 control-label" >封面：</label>
				<div class="col-md-2">
					    <div class="col-sm-0 btn-upload logo-upload">
							<input id="logoupload" type="file" />
						</div>
						<div id="logoimage" style="display: none;"></div>
						<div class="form-group" id="logo_image" style="display: none"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动时间：</label>
				<div  class="col-sm-3  form-inline">
					<input class="form-control" id="bTime" type="time" style="width: 115px;" /> 至
					<input class="form-control" id="eTime" type="time" style="width: 115px;" />
					<input type="hidden" id="beginTime" name="beginTime" />
					<input type="hidden" id="endTime" name="endTime" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >大腿名额：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="dtme" name="hugThighActivity.thighLimit" value="100" />
				</div>
				<label class="col-sm-1 control-label" >被抱次数：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="number" id="dtcs" name="hugThighActivity.hugThighLimit" value="100" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >佣金分配：</label>
				<div class="col-sm-2 form-inline">
				            用户获取佣金的
					<select class="form-control" id="yjSelect" onchange="yj(this)">
					
				    </select>
				</div>
				<label class="col-sm-1 control-label" >平台获得：</label>
				<div class="col-sm-2">
				    <input class="form-control" type="text" id="pthd" value="20%" readonly="readonly" />
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
							    	<select class="form-control" id="selectDCPL1" name="couponsList[0].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL1" name="couponsList[0].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj1" name="couponsList[0].bonus" value="2000"/></td>
							    <td><input class="form-control" type="text" id="yj1" name="couponsList[0].commission" value="0.15" onchange="zfyj(this,'label1','pt1')" /></td>
							    <td><label id="label1">12.00</label></td>
							    <td><label id="pt1">3.00</label></td>  
							</tr>
							<tr>
							    <td>二级<input type="hidden" name="couponsList[1].type" value="2" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL2" name="couponsList[1].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL2" name="couponsList[1].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj2" name="couponsList[1].bonus" value="2500"/></td>
							    <td><input class="form-control" type="text" id="yj2" name="couponsList[1].commission" value="0.20" onchange="zfyj(this,'label2','pt2')" /></td>
							    <td><label id="label2">16.00</label></td>
							    <td><label id="pt2">4.00</label></td>  
							</tr>
							<tr>
							    <td>三级<input type="hidden" name="couponsList[2].type" value="3" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL3" name="couponsList[2].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL3" name="couponsList[2].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj3" name="couponsList[2].bonus" value="3000"/></td>
							    <td><input class="form-control" type="text" id="yj3" name="couponsList[2].commission" value="0.25" onchange="zfyj(this,'label3','pt3')" /></td>
							    <td><label id="label3">20.00</label></td>
							    <td><label id="pt3">5.00</label></td>  
							</tr>
							<tr>
							    <td>四级<input type="hidden" name="couponsList[3].type" value="4" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL4" name="couponsList[3].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL4" name="couponsList[3].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj4" name="couponsList[3].bonus" value="3500"/></td>
							    <td><input class="form-control" type="text" id="yj4" name="couponsList[3].commission" value="0.30" onchange="zfyj(this,'label4','pt4')" /></td>
							    <td><label id="label4">24.00</label></td>
							    <td><label id="pt4">6.00</label></td>  
							</tr>
							<tr>
							    <td>五级<input type="hidden" name="couponsList[4].type" value="5" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL5" name="couponsList[4].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL5" name="couponsList[4].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj5" name="couponsList[4].bonus" value="4000"/></td>
							    <td><input class="form-control" type="text" id="yj5" name="couponsList[4].commission" value="0.35" onchange="zfyj(this,'label5','pt5')" /></td>
							    <td><label id="label5">28.00</label></td>
							    <td><label id="pt5">7.00</label></td>  
							</tr>
							<tr>
							    <td>六级<input type="hidden" name="couponsList[5].type" value="6" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL6" name="couponsList[5].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL6" name="couponsList[5].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj6" name="couponsList[5].bonus" value="4500"/></td>
							    <td><input class="form-control" type="text" id="yj6" name="couponsList[5].commission" value="0.4" onchange="zfyj(this,'label6','pt6')" /></td>
							    <td><label id="label6">32.00</label></td>
							    <td><label id="pt6">8.00</label></td>  
							</tr>
							<tr>
							    <td>七级<input type="hidden" name="couponsList[6].type" value="7" /></td>
							    <td>
							    	<select class="form-control" id="selectDCPL7" name="couponsList[6].distance"> </select>
							    </td>
							    <td>
							    	<select class="form-control" id="selectJSLL7" name="couponsList[6].annualYield"> </select>
							    </td>
							    <td><input class="form-control" type="number" id="tyj7" name="couponsList[6].bonus" value="5000"/></td>
							    <td><input class="form-control" type="text" id="yj7" name="couponsList[6].commission" value="0.5" onchange="zfyj(this,'label7','pt7')" /></td>
							    <td><label id="label7">40.00</label></td>
							    <td><label id="pt7">10.00</label></td>  
							</tr>
						</tbody>
				</table> 
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >福利有效期：</label>
				<div class="col-sm-2 form-inline">
					<select class="form-control" id="flSelect" onchange="fl(this)">
					
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >平均步幅不超过</label>
				<div class="col-sm-2 form-inline">
					<input class="form-control" type="text" id="pjbf" value="1.8" name="hugThighActivity.stride" style="width: 100px;" />米
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >平均速度不超过</label>
				<div class="col-sm-2 form-inline">
					<input class="form-control" type="number" id="pjsd" value="20" name="hugThighActivity.avspeed" style="width: 100px;" />km/h
				</div>
				<label class="col-sm-1 control-label" >最快速度不超过</label>
				<div class="col-sm-2 form-inline">
					<input class="form-control" type="number" id="zksd" value="30" name="hugThighActivity.maxspeed" style="width: 100px;" />km/h
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" >活动开始推送：</label>
				<div class="col-sm-7 form-inline">
					 <input class="form-control" id="hdts" name="hugThighActivity.message" value="今日抱大腿活动将于10:00开始，准备好磨拳擦腿抢福利吧！" style="width: 100%;" />
				</div>
				<div class="col-sm-2 form-inline">
					<input class="form-control" id="tssj" type="datetime-local" />
					<input type="hidden" id="cronExpression" name="hugThighActivity.cronExpression" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2" >App内玩法规则说明：</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10 form-inline">
					 <textarea class="form-control" id="gz" name="hugThighActivity.rule" style="width: 100%;height: 50%;" ></textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 500px;">
			    <button id="sumit" class="btn btn-primary" style="width: 150px;">发布活动</button>
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
    <script src="${ctx }/resources/js/activity/addhugthigh.js"></script>
    <script src="${ctx }/resources/js/utils/dateUtil.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}';
        
		$(function(){
			
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
			var curDate = new Date();
			var dateStr = new Date(curDate.setDate(curDate.getDate()+1)).format('yyyy-MM-dd');
			$("#adate").val(dateStr);
			$("#bTime").val("10:00");
			$("#eTime").val("23:59");
			
			$("#tssj").val(dateStr+"T09:30");
			
			for (var i = 1; i <=100; i++) {
				if(i==80){
					yjSelect.append("<option value='"+i+"' selected='selected'>"+i+"%</option>");
					for (var k = 0; k < 7; k++) {
						//批量设置佣金比例
						$("#yhyjbl").append("<input type='hidden' name='couponsList["+k+"].commissionRate' value='0.8' />");
					}
				}else{
					yjSelect.append("<option value='"+i+"'>"+i+"%</option>");
				}
			}
			
			for (var i = 1; i <=7; i++) {
				var name="selectDCPL"+i;
				var name2="selectJSLL"+i;
				for(var y = 2; y <=10; y++) {
					if(i==1){
						if(y==3){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
					if(i==2){
						if(y==3){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
					if(i==3){
						if(y==5){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
					if(i==4){
						if(y==5){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
					if(i==5){
						if(y==8){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
					if(i==6){
						if(y==8){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
					if(i==7){
						if(y==10){
							$("#"+name).append("<option value='"+y+"' selected='selected'>"+y+"km</option>");
						}else{
							$("#"+name).append("<option value='"+y+"'>"+y+"km</option>");
						}
					}
					
				}
				var j = 6.0;
				for(;j<=11;) {
					if(i==1){
						if(j==8){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					
					if(i==2){
						if(j==8.5){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					
					if(i==3){
						if(j==9){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					
					if(i==4){
						if(j==9.5){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					if(i==5){
						if(j==10){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					
					if(i==6){
						if(j==10.5){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					
					if(i==7){
						if(j==11){
							$("#"+name2).append("<option value='"+j+"' selected='selected'>"+j+"%</option>");
						}else{
							$("#"+name2).append("<option value='"+j+"'>"+j+"%</option>");
						}
					}
					
					j=j+0.5;
				}
				
			}
			 
			
			
			for (var i = 1; i <=30; i++) {
				if(i==7){
					$("#flSelect").append("<option value='"+i+"' selected='selected'>"+i+"天</option>");
					//设置福利天数
					for (var k = 0; k < 7; k++) {
						$("#flts").append("<input type='hidden' name='couponsList["+k+"].expiryDay' value='7' />");
					}
				}else{
					$("#flSelect").append("<option value='"+i+"' >"+i+"天</option>");
				}
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
    	
    </script>
</body>
</html>
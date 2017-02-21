<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>汇率设定</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<form  action="${ctx}/goldbeanrecharge/saveGoldBeanRecharge" method="post">
			<div class="form-group form-inline">
				 <button id="sumit" class="btn btn-primary" style="width: 150px;">保存</button>
			</div>
			<div class="form-group form-inline"><label >批量购买设定：</label></div>
			
			
				<div class="form-group">
					<div class="col-sm-8 form-inline">
						<input type="hidden" name="goldBeanRechargeList[0].orders" value="1"/>
					    <input class="form-control" style="width: 110px;" type="text" id="je" name="goldBeanRechargeList[0].money" value="${list[0].money}" />元购买
					    <input class="form-control" style="width: 110px;" type="number" id="jd" name="goldBeanRechargeList[0].goldBean" value="${list[0].goldBean}" />金豆
					    <button type="button" class="btn btn-default" onclick="addNode()" >+</button>
					    <button type="button" class="btn btn-default" onclick="subNode()" >-</button>
					</div>
				</div>
				<div id="node">
					<c:forEach items="${list}" var="a" varStatus="status">
						<c:if test="${status.index>0}">
							<div class="form-group" id="node${status.index}">
								<div class="col-sm-8 form-inline">
									<br />
									<input type="hidden" name="goldBeanRechargeList[${(status.index)}].orders" value="${a.orders}"/>
									<input class="form-control" style="width: 110px;" type="text"   name="goldBeanRechargeList[${(status.index)}].money" value="${a.money}" />元购买
								    <input class="form-control" style="width: 110px;" type="number" name="goldBeanRechargeList[${(status.index)}].goldBean" value="${a.goldBean}" />金豆
							    </div>
						    </div>
					    </c:if>
					</c:forEach>
				</div>
		
		</form>
		
	</div>

	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script type="text/javascript">
        
	  var num = 0;
	  
	  $(function(){
			num = '${list.size()}';
			num = parseInt(num)-1;
			
			
			var result = '${result}';	
			if(result!=''){
				swal(result);
			}
			
			
			 $("#sumit").on("click", saveGoldBeanRecharge);
		});
	 
	  
	  function saveGoldBeanRecharge(){
		
		  if($("#je").val().length==0){
			  swal("请输入金额");
			  return false;
		  }
		  
		  if($("#jd").val().length==0){
			  swal("请输入金 豆数");
			  return false;
		  }
	  }
	  
	  
		function addNode(){
			num = num+1;
			var str = "<div class=\"form-group\" id='node"+num+"'>\n" +
			"					<div class=\"col-sm-8 form-inline\">\n" +
			"					   <br />\n" +
			"						<input type='hidden' name='goldBeanRechargeList["+num+"].orders' value='"+(num+1)+"'/>"+
			"						<input class=\"form-control\" style=\"width: 110px;\" type=\"text\"   name='goldBeanRechargeList["+num+"].money'  />元购买\n" +
			"					    <input class=\"form-control\" style=\"width: 110px;\" type=\"number\" name='goldBeanRechargeList["+num+"].goldBean'  />金豆 \n" +
			"					</div>\n" +
			"				</div>";
			 $("#node").append(str);
		}
		
		function subNode(){
			$("#node"+num).remove();
			num = num-1;
			if(num<0){
				num = 0;
			}
		}
		
	 
	   
    </script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提现列表</title>
<style>
.table th, .table td {
    text-align: center;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<!-- 用户 开始 -->
		<div class="form-inline">
			<label>用户汇总：共${countUser}</label>
		</div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th colspan="3">新增用户</th>
					<th colspan="3">活跃用户</th>
					<th colspan="3">启动次数</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
				</tr>
				<tr>
					<td>今日</td>
					<td>${todayAndroid}</td>
					<td>${todayIOS}</td>
					<td>${todaySum}</td>
					<td>${todayActiveUserAndroid}</td>
					<td>${todayActiveUserIOS}</td>
					<td>${todayActiveUserSum}</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>昨日</td>
					<td>${yesterdayAndroid}</td>
					<td>${yesterdayIOS}</td>
					<td>${yesterdaySum}</td>
					<td>${yesterdayActiveUserAndroid}</td>
					<td>${yesterdayActiveUserIOS}</td>
					<td>${yesterdayActiveUserSum}</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>7日累计</td>
					<td>${sevenDaysAndroid}</td>
					<td>${sevenDaysIOS}</td>
					<td>${sevenDaysSum}</td>
					<td>${sevenDaysActiveUserAndroid}</td>
					<td>${sevenDaysActiveUserIOS}</td>
					<td>${sevenDaysActiveUserSum}</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>30日累计</td>
					<td>${thirtyDaysAndroid}</td>
					<td>${thirtyDaysIOS}</td>
					<td>${thirtyDaysSum}</td>
					<td>${thirtyDaysActiveUserAndroid}</td>
					<td>${thirtyDaysActiveUserIOS}</td>
					<td>${thirtyDaysActiveUserSum}</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<!-- 用户 结束 -->
		
		<!-- 交易 开始 -->
		<div class="form-inline">
			<label>交易汇总：累计充值${countPay}元，提现${countWithdraw}元</label>
		</div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th colspan="3">充值次数</th>
					<th colspan="3">充值总额（元）</th>
					<th colspan="3">提现次数</th>
					<th colspan="3">提现总额（元）</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
				</tr>
				<tr>
					<td>今日</td>
					<td>${todayWalletCsAndroid}</td>
					<td>${todayWalletCsIOS}</td>
					<td>${todayWalletCsSum}</td>
					<td>${todayWalletZeAndroid}</td>
					<td>${todayWalletZeIOS}</td>
					<td>${todayWalletZeSum}</td>
					<td>${todayWalletTxCsAndroid}</td>
					<td>${todayWalletTxCsIOS}</td>
					<td>${todayWalletTxCsSum}</td>
					<td>${todayWalletTxZeAndroid}</td>
					<td>${todayWalletTxZeIOS}</td>
					<td>${todayWalletTxZeSum}</td>
				</tr>
				<tr>
					<td>昨日</td>
					<td>${yesterdayWalletCsAndroid}</td>
					<td>${yesterdayWalletCsIOS}</td>
					<td>${yesterdayWalletCsSum}</td>
					<td>${yesterdayWalletZeAndroid}</td>
					<td>${yesterdayWalletZeIOS}</td>
					<td>${yesterdayWalletZeSum}</td>
					<td>${yesterdayWalletTxCsAndroid}</td>
					<td>${yesterdayWalletTxCsIOS}</td>
					<td>${yesterdayWalletTxCsSum}</td>
					<td>${yesterdayWalletTxZeAndroid}</td>
					<td>${yesterdayWalletTxZeIOS}</td>
					<td>${yesterdayWalletTxZeSum}</td>
				</tr>
			</tbody>
		</table>
		<!-- 交易 结束-->
		
		<!-- 运动汇总  开始 -->
		<div class="form-inline">
			<label>运动汇总</label>
		</div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th colspan="3">跑步人数</th>
					<th colspan="3">总跑量（km）</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
				</tr>
				<tr>
					<td>今日</td>
					<td>${todayDistanceRSAndroid}</td>
					<td>${todayDistanceRSIOS}</td>
					<td>${todayDistanceRSSum}</td>
					<td>${todayDistanceZLAndroid}</td>
					<td>${todayDistanceZLIOS}</td>
					<td>${todayDistanceZLSum}</td>
				</tr>
				<tr>
					<td>昨日</td>
					<td>${yesterdayDistanceRSAndroid}</td>
					<td>${yesterdayDistanceRSIOS}</td>
					<td>${yesterdayDistanceRSSum}</td>
					<td>${yesterdayDistanceZLAndroid}</td>
					<td>${yesterdayDistanceZLIOS}</td>
					<td>${yesterdayDistanceZLSum}</td>
				</tr>
			</tbody>
		</table>
		<!-- 运动汇总  结束 -->

		<!-- 抱大腿汇总  开始 -->
		<div class="form-inline">
			<label>抱大腿汇总</label>
		</div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th colspan="3">参与人数</th>
					<th colspan="3">交易金额(元)</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
					<td>Android</td>
					<td>IOS</td>
					<td>总和</td>
				</tr>
				<tr>
					<td>今日</td>
					<td>${todayHugThighCountAndroid}</td>
					<td>${todayHugThighCountIOS}</td>
					<td>${todayHugThighCountSum}</td>
					<td>${todayCountWalletHugThighAndroid}</td>
					<td>${todayCountWalletHugThighIOS}</td>
					<td>${todayCountWalletHugThighSum}</td>
				</tr>
				<tr>
					<td>昨日</td>
					<td>${yesterdayHugThighCountAndroid}</td>
					<td>${yesterdayHugThighCountIOS}</td>
					<td>${yesterdayHugThighCountSum}</td>
					<td>${yesterdayCountWalletHugThighAndroid}</td>
					<td>${yesterdayCountWalletHugThighIOS}</td>
					<td>${yesterdayCountWalletHugThighSum}</td>
				</tr>
			</tbody>
		</table>
		<!-- 抱大腿汇总  结束 -->

	</div>



	<script type="text/javascript">
	
				 
	</script>
</body>
</html>
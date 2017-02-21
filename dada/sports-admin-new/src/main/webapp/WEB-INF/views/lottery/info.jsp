<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>彩票详情</title>
</head>
<body>
	<div class="container-fluid">
		<div class="form-group form-inline">
			 
		</div>
		<jsp:useBean id="myDate" class="java.util.Date" />
		<div class="form-group form-inline"></div>
		<div class="form-group form-inline"><label style="font-size: 20px">第${lottery.lotteryNum}期</label></div>
		<div class="form-group form-inline"><label>开奖号码：${lottery.winNumber} 投注次数：${lottery.sumCount}注</label></div>
		<div class="form-group form-inline"><label>中奖人数：${lottery.level1+lottery.level2+lottery.level3+lottery.level4+lottery.level5}人       一等奖：${lottery.level1}人         二等奖：${lottery.level2}人      三等奖：${lottery.level3}人       四等奖： ${lottery.level4}人     五等奖：${lottery.level5}人</label></div>
		<div class="form-group form-inline"><label>总收入：${lottery.countGoldBean}金豆</label></div>
		<div class="form-group form-inline"><label>奖金总额：${lottery.sumBonus}金豆  已发放金豆：${grant}金豆</label></div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>投注时间</th>
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>投注号码</th>
					<th>投注</th>
					<th>中奖等级</th>
					<th>奖金</th>
					<th>领奖状态</th>
					<th>使用状态</th>
					<th>成本核算</th>
				</tr>
			</thead>
			<c:forEach items="${page.result}" var="u" varStatus="status">
				<tbody>
					<tr>
						<td>${u.id}</td>
						<td>
							<c:set target="${myDate}" property="time" value="${u.createTime}" /> 
						 	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both" />
						</td>
						<td>${u.userId}</td>
						<td>${u.nickname}</td>
						<td>${u.myNumber}</td>
						<td>${u.betCount}</td>
						<td>
							<c:if test="${u.userLotteryWin == null}">
								未中奖
							</c:if>
							<c:if test="${u.userLotteryWin != null}">
								<c:if test="${u.userLotteryWin.lotteryWin.level == 1}">
									一
								</c:if>
								<c:if test="${u.userLotteryWin.lotteryWin.level == 2}">
									二
								</c:if>
								<c:if test="${u.userLotteryWin.lotteryWin.level == 3}">
									三
								</c:if>
								<c:if test="${u.userLotteryWin.lotteryWin.level == 4}">
									四
								</c:if>
								<c:if test="${u.userLotteryWin.lotteryWin.level == 5}">
									五
								</c:if>
								等奖
							</c:if>
						</td>
						<td>
							<c:if test="${u.userLotteryWin == null}">
								0
							</c:if>
							<c:if test="${u.userLotteryWin != null}">
								${u.userLotteryWin.lotteryWin.goldBean}
							</c:if>
						</td>
						<td>
							<c:if test="${u.userLotteryWin == null}">
								0
							</c:if>
							<c:if test="${u.userLotteryWin != null}">
								<c:if test="${u.userLotteryWin.status==0}">
									未领奖
								</c:if>
								<c:if test="${u.userLotteryWin.status==1}">
									已领奖
								</c:if>
							</c:if>
						</td>
						<td>
							<c:if test="${u.userLotteryWin == null}">
								未使用
							</c:if>
							<c:if test="${u.userLotteryWin != null}">
								<c:if test="${u.userLotteryWin.userCoupon.status == 0}">
									未使用
								</c:if>
								<c:if test="${u.userLotteryWin.userCoupon.status != 0}">
									已使用
								</c:if>
							</c:if>
						</td>
						<td>0</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<%@include file="/common/page.jsp"%>
	</div>

	<script src="${ctx }/resources/js/utils/dateUtil.js"></script>
	<script type="text/javascript">
        
        var currentPage = '${page.currentPage}';
		var pageSize = '${page.pageSize}';	
		var lotteryId = '${lotteryId}';
        
		$(function(){
			var result = '${result}';	
			if(result!=''){
				swal(result);
			}
			 
		});
		
		/**
		 *查询
		 */
		function queryList() {
			var url="${ctx}/userlottery/list?currentPage="+currentPage+"&pageSize="+pageSize+"&lotteryId="+lotteryId;
			location.href = url;
		}

		function goPage(num, size) {
		   currentPage = num;
		   pageSize = size;
		   queryList();
		}
		
	   
    </script>
</body>
</html>
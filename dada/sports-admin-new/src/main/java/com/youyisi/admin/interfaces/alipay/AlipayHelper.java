package com.youyisi.admin.interfaces.alipay;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youyisi.admin.application.alipay.AlipayService;
import com.youyisi.admin.application.withdraw.WithdrawService;
import com.youyisi.admin.domain.alipay.Alipay;
import com.youyisi.admin.domain.withdraw.Withdraw;
import com.youyisi.admin.infrastructure.alipay.config.AlipayConfig;
import com.youyisi.admin.infrastructure.alipay.util.AlipaySubmit;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.RedisClient;
import com.youyisi.admin.infrastructure.utils.DateUtil;

@Component
public class AlipayHelper {

	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private AlipayService alipayService;

	private String getNumber() {
		Long id = RedisClient.increment(Constant.WITHDRAW_BATCH_KEY, 1l);
		return new SimpleDateFormat("yyyyMMdd").format(new Date())
				+ new DecimalFormat("00000000").format(id);

	}

	public String alipay(Long[] ids) {
		String detail = "";
		BigDecimal b1 = new BigDecimal("0.0");
		int count = 0;
		for (Long id : ids) {
			Withdraw withDraw = withdrawService.get(id);
			Alipay alipay = alipayService.getByUserId(withDraw.getUserId());
			if(withDraw.getStatus()==0){
				count++;
				BigDecimal b2 = new BigDecimal(withDraw.getMoney());
				b1 = b1.add(b2);
				detail+=withDraw.getWithdrawNumber()+"^"+alipay.getAlipay()+"^"+alipay.getRealName()+"^"+withDraw.getMoney()+"^提现"+"|";
				withDraw.setStatus(1);
				withdrawService.update(withDraw);
			}
		}
		// //////////////////////////////////请求参数//////////////////////////////////////

		// 服务器异步通知页面路径
		String notify_url = "http://operate.dadasports.cn/async";
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 付款账号
		String email = "managerman@dadasports.cn";
		// 必填

		// 付款账户名
		String account_name = "广州有意思网络科技有限公司";
		// 必填，个人支付宝账号是真实姓名公司支付宝账号是公司名称

		// 付款当天日期
		String pay_date = new String(DateUtil.dateToStr(new Date(), "yyyyMMdd"));
		// 必填，格式：年[4位]月[2位]日[2位]，如：20100801

		// 批次号
		String batch_no = getNumber();
		// 必填，格式：当天日期[8位]+序列号[3至16位]，如：201008010000001

		// 付款总金额
		String batch_fee = b1.doubleValue()+"";
		// 必填，即参数detail_data的值中所有金额的总和

		// 付款笔数
		String batch_num = count + "";
		// 必填，即参数detail_data的值中，“|”字符出现的数量加1，最大支持1000笔（即“|”字符出现的数量999个）

		// 付款详细数据
		String detail_data = detail.substring(0, detail.length()-1);
		// 必填，格式：流水号1^收款方帐号1^真实姓名^付款金额1^备注说明1|流水号2^收款方帐号2^真实姓名^付款金额2^备注说明2....

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "batch_trans_notify");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("email", email);
		sParaTemp.put("account_name", account_name);
		sParaTemp.put("pay_date", pay_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_fee", batch_fee);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		// 建立请求
		return AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

	}
	
}

package com.youyisi.sports.interfaces.controller.pay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.goldbean.UserGoldBeanServiceRemote;
import com.youyisi.app.soa.remote.orders.OrderFlowServiceRemote;
import com.youyisi.app.soa.remote.orders.OrdersServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.app.soa.remote.snatch.UserSnatchServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighServiceRemote;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.app.soa.remote.wallet.WalletServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.orders.OrderFlow;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.gexinpush.PushToSingleHelper;
import com.youyisi.sports.gexinpush.TransmissionContent;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.interfaces.helper.pay.alipay.config.AlipayConfig;
import com.youyisi.sports.interfaces.helper.pay.alipay.util.AlipayNotify;
import com.youyisi.sports.interfaces.helper.pay.alipay.util.SignUtils;
import com.youyisi.sports.interfaces.helper.pay.tenpay.PayReq;
import com.youyisi.sports.interfaces.helper.pay.tenpay.util.Constants;
import com.youyisi.sports.interfaces.helper.pay.tenpay.util.MD5;
import com.youyisi.sports.interfaces.helper.pay.tenpay.util.Util;

/**
 * 支付
 * 
 * @author shuye
 * @date 2015年4月27日 上午9:23:53
 * @version 1.0
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {
	@Autowired
	private OrdersServiceRemote orderServiceRemote;
	@Autowired
	private OrderFlowServiceRemote orderFlowServiceRemote;
	@Autowired
	private WalletServiceRemote walletServiceRemote;
	@Autowired
	private UserServiceRemote userServiceRemote;
	@Autowired
	private HugThighServiceRemote hugThighServiceRemote;
	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	@Autowired
	private UserSnatchServiceRemote userSnatchServiceRemote;
	@Autowired
	private UserGoldBeanServiceRemote userGoldBeanServiceRemote;
	
	private static String NAME = "mobile";
	private Logger log = LoggerFactory.getLogger(PayController.class);
	private static int PAYSTATUS = 1;
	private static final Log logger = LogFactory.getLog(PayController.class);

	@RequestMapping("/{id}/weixin")
	@ResponseBody
	public WebResultInfoWrapper weinXinPay(@PathVariable("id") Long id, String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		User user = getUserByToken(token);
		Orders order = orderServiceRemote.get(id);
		logger.debug("微信支付请求:id=" + id + "token" + token);
		if (order != null) {
			if (order.getCreateTime() + 30 * 60 * 1000 < System.currentTimeMillis()) {
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("订单已经过期");
				return webResultInfoWrapper;
			}
			//createPayDetail(order, 2, user.getId());
			if (user.getId().equals(order.getUserId())) {
					// 接收财付通通知的URL
					String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
					String entity = genProductArgs(order);
					byte[] buf = Util.httpPost(url, entity);
					String content = new String(buf);
					Map<String, String> xml = decodeXml(content);
					webResultInfoWrapper.addResult("weixinPayXml", genPayReq(xml));
					webResultInfoWrapper.setState(SUCCEED);
			}
		}
		} catch (Exception e) {
			logger.error(e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
			e.printStackTrace();
		}
		return webResultInfoWrapper;
	}

	/**
	 * 生成签名
	 */

	private String genPackageSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constants.API_KEY);

		String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		return packageSign;
	}

	private String genAppSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constants.API_KEY);

		String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		return appSign;
	}

	private String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");

			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");

		return sb.toString();
	}

	public Map<String, String> decodeXml(String content) {

		try {
			Map<String, String> xml = new HashMap<String, String>();
			// 获得pull解析器工厂
			XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
			// 获取XmlPullParser的实例
			XmlPullParser parser = pullParserFactory.newPullParser();
			parser.setInput(new StringReader(content));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				String nodeName = parser.getName();
				switch (event) {
				case XmlPullParser.START_DOCUMENT:

					break;
				case XmlPullParser.START_TAG:

					if ("xml".equals(nodeName) == false) {
						// 实例化student对象
						xml.put(nodeName, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				event = parser.next();
			}

			return xml;
		} catch (Exception e) {
		}
		return null;

	}

	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}

	private long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	//
	private String genProductArgs(Orders order) {

		try {
			String nonceStr = genNonceStr();

			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
			packageParams.add(new BasicNameValuePair("body", "哒哒订单"));
			packageParams.add(new BasicNameValuePair("limit_pay", "no_credit"));
			packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
			packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
			packageParams.add(new BasicNameValuePair("notify_url",
					"http://api.dadasports.cn/pay/weixin_notify"));
			packageParams.add(new BasicNameValuePair("out_trade_no", order.getOrderNumber()));
			packageParams.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));
			packageParams.add(new BasicNameValuePair("total_fee",
					new BigDecimal(order.getPayAmount() + "").multiply(new BigDecimal(100)).longValue() + ""));
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));
			
			String sign = genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));

			String xmlstring = toXml(packageParams);

			return new String(xmlstring.toString().getBytes(), "ISO8859-1");

		} catch (Exception e) {
			return null;
		}

	}

	private PayReq genPayReq(Map<String, String> map) {
		PayReq req = new PayReq();
		req.setAppId(Constants.APP_ID);
		req.setPartnerId(Constants.MCH_ID);
		req.setPrepayId(map.get("prepay_id"));
		req.setPackageValue("Sign=WXPay");
		req.setNonceStr(genNonceStr());
		req.setTimeStamp(String.valueOf(genTimeStamp()));

		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appid", req.getAppId()));
		signParams.add(new BasicNameValuePair("noncestr", req.getNonceStr()));
		signParams.add(new BasicNameValuePair("package", req.getPackageValue()));
		signParams.add(new BasicNameValuePair("partnerid", req.getPartnerId()));
		signParams.add(new BasicNameValuePair("prepayid", req.getPrepayId()));
		signParams.add(new BasicNameValuePair("timestamp", req.getTimeStamp()));

		req.setSign(genAppSign(signParams));
		return req;
	}

	@RequestMapping("/weixin_notify")
	@ResponseBody
	public String weixinNotify(HttpServletRequest request, HttpServletResponse response) {
		List<NameValuePair> returnParams = new LinkedList<NameValuePair>();

		try {
			String xml = inputStreamTOString(request.getInputStream(), "utf-8");
			System.out.println("微信通知11111111111" + xml);
			Map<String, String> map = decodeXml(xml);
			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			List<String> keys = new ArrayList<String>(map.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				if (!"sign".equals(key)) {
					packageParams.add(new BasicNameValuePair(key, map.get(key)));
				}
			}
			String sign = genPackageSign(packageParams);
			if (sign.equals(map.get("sign"))) {
				Orders order = orderServiceRemote.getByOrderNumber(map.get("out_trade_no"));
				System.out.println("微信支付结果通知签名验证通过,订单号："+order.getOrderNumber()+"流水号："+map.get("transaction_id")+"支付状态："+map.get("result_code"));
				String total_fee = map.get("total_fee");
				if (map.get("result_code").equalsIgnoreCase("success") && total_fee.equals((new BigDecimal(order.getPayAmount() + "").multiply(new BigDecimal(100)).longValue()) + "")&&order.getPayStatus().intValue()!=PAYSTATUS) {
					order.setPayStatus(PAYSTATUS);
					createOrderFlow(order, "微信异步通知更新支付状态为", 2, map.get("transaction_id"));
					
					if (order.getType().intValue() == 1) {
						walletServiceRemote.recharge(order);
						User user = userServiceRemote.get(order.getUserId());
						pushMessage(user,order);
					}else if(order.getType().intValue() == 2) {
						hugThighServiceRemote.otherPay(order);
					}else if(order.getType().intValue() == 3){
						relayMemberServiceRemote.otherpay(order);
					}else if(order.getType().intValue() == 4){
						userSnatchServiceRemote.otherpay(order);
					}else if(order.getType().intValue() == 5){
						userGoldBeanServiceRemote.otherpay(order);
					}
					
					orderServiceRemote.update(order);
					returnParams.add(new BasicNameValuePair("return_code", "SUCCESS"));
					returnParams.add(new BasicNameValuePair("return_msg", "OK"));
				}
			}
		} catch (Exception e) {
			returnParams.add(new BasicNameValuePair("return_code", "FAIL"));
			returnParams.add(new BasicNameValuePair("return_msg", "ERROR"));
			e.printStackTrace();
		}

		return toXml(returnParams);

	}

	/**
	 * InputStream转换成Byte 注意:流关闭需要自行处理
	 * 
	 * @param in
	 * @return byte
	 * @throws Exception
	 */
	private byte[] inputStreamTOByte(InputStream in) throws IOException {

		int BUFFER_SIZE = 4096;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;

		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		byte[] outByte = outStream.toByteArray();
		outStream.close();

		return outByte;
	}

	/**
	 * InputStream转换成String 注意:流关闭需要自行处理
	 * 
	 * @param in
	 * @param encoding
	 *            编码
	 * @return String
	 * @throws Exception
	 */
	private String inputStreamTOString(InputStream in, String encoding) throws IOException {

		return new String(inputStreamTOByte(in), encoding);

	}

	@RequestMapping(value = "/{id}/alipay")
	@ResponseBody
	public WebResultInfoWrapper alipay(@PathVariable("id") Long id, String token) {
		logger.debug("支付宝支付请求:id=" + id + "token" + token);
		WebResultInfoWrapper response = new WebResultInfoWrapper();
		try {
		User user = getUserByToken(token);
		Orders order = orderServiceRemote.get(id);
		if (order != null) {
			if (order.getCreateTime() + 30 * 60 * 1000 < System.currentTimeMillis()) {
				response.setState(ERROR);
				response.setMessage("订单已经过期");
				return response;
			}
			if (user.getId().equals(order.getUserId())) {

				// 订单
				String orderInfo = getOrderInfo("哒哒活动", "哒哒活动订单", order.getPayAmount().toString(),
						order.getOrderNumber());

				// 对订单做RSA 签名
				String sign = sign(orderInfo);
				
					// 仅需对sign 做URL编码
					sign = URLEncoder.encode(sign, "UTF-8");
				

				// 完整的符合支付宝参数规范的订单信息
				final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

				response.addResult("page", payInfo);
				response.setState(SUCCEED);
			}
		} else {
			logger.debug("参数错误id=" + id + "token" + token);
			response.setState(ERROR);
			response.setMessage("订单不存在");
		}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			response.setState(ERROR);
			response.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return response;

	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String body, String price, String orderNum) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"managerman@dadasports.cn\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNum + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + "http://api.dadasports.cn/pay/ali_async" + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, AlipayConfig.private_key);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/ali_async")
	@ResponseBody
	public String async(HttpServletRequest request) {
		System.out.println("收到支付宝通知..........");
		try{
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = request.getParameter("out_trade_no");

		// 支付宝交易号

		String trade_no = request.getParameter("trade_no");

		// 交易状态
		String trade_status = request.getParameter("trade_status");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		System.out.println("收到支付宝通知待验证签名..........");
		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码
			System.out.println("收到支付宝通知验证签名成功..........trade_status" + trade_status);
			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			Orders order = orderServiceRemote.getByOrderNumber(out_trade_no);
			System.out.println("订单号："+order.getOrderNumber()+"支付宝trade_no:"+trade_no+"支付宝trade_status:" + trade_status);
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				if (order.getPayStatus().intValue() != PAYSTATUS) {
					order.setPayStatus(PAYSTATUS);
					createOrderFlow(order, "阿里异步通知更新支付状态为", 1, trade_no);
					if (order.getType().intValue() == 1) {
						walletServiceRemote.recharge(order);
						User user = userServiceRemote.get(order.getUserId());
						pushMessage(user,order);
					}else if(order.getType().intValue() == 2) {
						hugThighServiceRemote.otherPay(order);
					}else if(order.getType().intValue() == 3){
						relayMemberServiceRemote.otherpay(order);
					}else if(order.getType().intValue() == 4){
						userSnatchServiceRemote.otherpay(order);
					}else if(order.getType().intValue() == 5){
						userGoldBeanServiceRemote.otherpay(order);
					}
					orderServiceRemote.update(order);
				}
				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				if (order.getPayStatus().intValue() != PAYSTATUS) {
					order.setPayStatus(PAYSTATUS);
					createOrderFlow(order, "阿里异步通知更新支付状态为", 1, trade_no);
					if (order.getType().intValue() == 1) {
						walletServiceRemote.recharge(order);
						User user = userServiceRemote.get(order.getUserId());
						pushMessage(user,order);
					}else if(order.getType().intValue() == 2) {
						hugThighServiceRemote.otherPay(order);
					}else if(order.getType().intValue() == 3){
						relayMemberServiceRemote.otherpay(order);
					}else if(order.getType().intValue() == 4){
						userSnatchServiceRemote.otherpay(order);
					}else if(order.getType().intValue() == 5){
						userGoldBeanServiceRemote.otherpay(order);
					}
					
					orderServiceRemote.update(order);
				}
				// 注意：
				// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			return "success"; // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			System.out.println("收到支付宝通知4444444444验证失败..........trade_status" + trade_status);
			return "fail";
		}
		}catch (SoaException e) {
			log.error("----message:" + e.getMessage());
		}
		return "fail";
	}

	

	private void createOrderFlow(Orders order, String note, Integer type, String trade_no) throws SoaException {
		OrderFlow orderFlow = new OrderFlow();
		orderFlow.setCreateTime(System.currentTimeMillis());
		orderFlow.setOrderId(order.getId());
		orderFlow.setUserId(order.getUserId());
		orderFlow.setNote(note + order.getPayStatus());
		orderFlow.setPayType(type);
		orderFlow.setTradeNo(trade_no);
		orderFlowServiceRemote.save(orderFlow);

	}
	
	private void pushMessage(User toUser,Orders order) {
		TransmissionContent tc = new TransmissionContent();
		tc.setTitle("充值成功提醒！");
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("order", order);
		tc.setEntity(entity);
		tc.setType("TOP_UP_SUCCESS");
		tc.setToUserId(toUser.getId());
		PushToSingleHelper.push(toUser, tc);
	}

}

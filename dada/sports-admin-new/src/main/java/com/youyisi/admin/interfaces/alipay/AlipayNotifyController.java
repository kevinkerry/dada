package com.youyisi.admin.interfaces.alipay;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.application.withdraw.WithdrawService;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.withdraw.Withdraw;
import com.youyisi.admin.infrastructure.alipay.util.AlipayNotify;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;

@Controller
public class AlipayNotifyController {

	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private UserService userService;

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/async", method = RequestMethod.POST)
	@ResponseBody
	public String async(HttpServletRequest request) throws UnsupportedEncodingException {
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
		// 批量付款数据中转账成功的详细信息
		String success = request.getParameter("success_details");
		String success_details=null;
		String fail = request.getParameter("fail_details");
		String fail_details=null;
		if(StringUtils.isNotBlank(success)){
			 success_details = new String(success.getBytes("ISO-8859-1"), "UTF-8");
		}
		
		if(StringUtils.isNotBlank(fail)){
			  fail_details = new String(fail.getBytes("ISO-8859-1"), "UTF-8");
		}

		// 批量付款数据中转账失败的详细信息

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码
			if (StringUtils.isNotBlank(success_details)) {
				System.out.println("-------------------"+success_details);
				String[] drawbacks = success_details.split("\\|");
				for (String d : drawbacks) {
					String[] detals = d.split("\\^");
					Withdraw userWithDrawPackage = withdrawService.getByDrawbackNum(detals[0]);
					if (userWithDrawPackage != null) {
						userWithDrawPackage.setStatus(2);
						withdrawService.update(userWithDrawPackage);
						pushMessage(userWithDrawPackage);
					}
				}
			}
			
			if (StringUtils.isNotBlank(fail_details)) {
				System.out.println("-------------------"+fail_details);
				String[] drawbacks = fail_details.split("\\|");
				for (String d : drawbacks) {
					String[] detals = d.split("\\^");
					Withdraw userWithDrawPackage = withdrawService.getByDrawbackNum(detals[0]);
					if (userWithDrawPackage != null) {
						userWithDrawPackage.setStatus(0);
						withdrawService.update(userWithDrawPackage);
						pushMessage(userWithDrawPackage);
					}
				}
			}
			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			// 判断是否在商户网站中已经做过了这次通知返回的处理
			// 如果没有做过处理，那么执行商户的业务程序
			// 如果有做过处理，那么不执行商户的业务程序

			return "success"; // 请不要修改或删除

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			return "fail";
		}
	}

	private void pushMessage(Withdraw userWithDrawPackage) {

		User sportUser = userService.get(userWithDrawPackage.getUserId());
		TransmissionContent content = new TransmissionContent();
		content.setTitle("您的提现申请已处理，请注意查收");
		content.setType("DRAWBACK_PACKAGE_SUCCESS");
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("content", "您的提现申请已处理，请注意查收");
		entity.put("sendTime", System.currentTimeMillis());
		content.setEntity(entity);
		content.setToUserId(userWithDrawPackage.getUserId());
		PushToSingleHelper.push(sportUser, content);
	}
}

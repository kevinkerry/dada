package com.youyisi.admin.infrastructure.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.youyisi.admin.infrastructure.constant.Constant;

public class OpenFireHelper {

	private static final String OPENFIRE_SERVICE_PATH = Constant.OPENFIRE_SERVICE_PATH + "/plugins/";

	public static final String DADA_SERVER_PATH = "dadaservice/rest/";
	public static final String PUSH_SERVER_PATH = "push/subm";

	/**
	 * 
	 * @param method
	 *            接口方法
	 * @param serverPath
	 *            接口地址
	 * @param params
	 *            参数
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws CustomException
	 */
	public static void post(final String method, final String serverPath, final Map<String, String> params) {
		new Thread() {
			public void run() {
				try {
					HttpClient httpClient = new HttpClient();
					httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
					String url = OPENFIRE_SERVICE_PATH + serverPath;
					if (method != null && !"".equals(method)) {
						url = url + method;
					}
					PostMethod postMethod = new PostMethod(url);
					Set<String> keySet = params.keySet();
					for (String key : keySet) {
						postMethod.addParameter(key, params.get(key));
					}
					int state = httpClient.executeMethod(postMethod);
					if (state == 200) {
						String str = new String(postMethod.getResponseBodyAsString().getBytes("utf-8"));
						postMethod.releaseConnection();
						// return str;
					} else {
						throw new Exception("连接通信服务器失败：" + OPENFIRE_SERVICE_PATH + method);
					}
					System.out.println("kkkkkkkkkkkkkkkk888"+url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
System.out.println("kkkkkkkkkkkkkkkk");
	}

	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "201");
		params.put("password", "E10ADC3949BA59ABBE56E057F20F883E");
		params.put("name", "15818832115");
		OpenFireHelper.post("user.createUser", OpenFireHelper.DADA_SERVER_PATH, params);
	}
}

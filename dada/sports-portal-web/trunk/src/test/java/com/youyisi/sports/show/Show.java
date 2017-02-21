package com.youyisi.sports.show;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 活动
 * 
 * @author hetao
 * @date 2015年4月27日 上午9:23:53
 * @version 1.0
 */
public class Show {
	@Test
	public void addShow() {
		String url = "http://localhost:8080/sports-portal-web/show/addSportShow";
		Map<String, String> map = new HashMap<String, String>();
		map.put("showDesc", "么么哒");
		map.put("auditor", "1");
		// String result = HttpHelper.post(url, map);
		// System.out.println("---------------------------" + result);
		// ResponseModel responseModel = new Gson().fromJson(result,
		// ResponseModel.class);
		// Assert.assertSame(0, responseModel.getCode());

	}
}

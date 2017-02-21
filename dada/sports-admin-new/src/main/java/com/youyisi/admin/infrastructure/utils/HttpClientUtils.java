package com.youyisi.admin.infrastructure.utils;

import java.io.File;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.lang.helper.JsonHelper;

/**
 * 
 * @author hetao
 * @date 2016年4月13日 下午4:42:56
 * @version 1.0
 */
public class HttpClientUtils {

	@SuppressWarnings("unchecked")
	public static List<String> getImgPath(File[] files, String serviceUrl) {
		List<String> paths = null;
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httppost = new HttpPost(serviceUrl);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			for (int i = 0; i < files.length; i++) {
				builder.addPart("file" + i, new FileBody(files[i]));
			}
			httppost.setEntity(builder.build());
			HttpResponse response = httpClient.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				// httpclient自带的工具类读取返回数据
				WebResultInfoWrapper wrapper = JsonHelper.fromJsonString(EntityUtils.toString(resEntity),
						WebResultInfoWrapper.class);
				paths = (List<String>) wrapper.getResults().get("path");
				EntityUtils.consume(resEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paths;
	}
}

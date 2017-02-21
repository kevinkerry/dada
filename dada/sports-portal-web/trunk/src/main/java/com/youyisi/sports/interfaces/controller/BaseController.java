package com.youyisi.sports.interfaces.controller;

import com.google.gson.Gson;
import com.youyisi.sports.constant.Constants;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.helper.RedisClient;

public class BaseController {

	protected static Gson gson = new Gson();

	/**
	 * 成功
	 */
	protected final String SUCCEED = "success";
	/**
	 * 失败
	 */
	protected final String ERROR = "error";
	
	/**
	 * 失败
	 */
	protected final String TOKEN_ERROR = "token_error";

	/**
	 * 根据Token 获取用户
	 * 
	 * @param token
	 * @return User
	 */
	public User getUserByToken(String token) {
		User user = null;
		String us = RedisClient.get(Constants.USER_KEY + token);
		if (us != null) {
			user = gson.fromJson(us, User.class);
		}
		return user;
	}
}

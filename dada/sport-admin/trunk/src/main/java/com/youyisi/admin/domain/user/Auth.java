package com.youyisi.admin.domain.user;

import com.youyisi.lang.domain.BaseObject;

public class Auth extends BaseObject<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1554690122999348374L;
	/**
	 * 权限
	 */
	private String permission;
	/**
	 * 权限名称
	 */
	private String name;
	
	public Auth(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}

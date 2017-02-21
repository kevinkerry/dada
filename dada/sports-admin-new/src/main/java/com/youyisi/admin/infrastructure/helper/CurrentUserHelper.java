package com.youyisi.admin.infrastructure.helper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.youyisi.admin.domain.adminuser.AdminUser;
import com.youyisi.admin.infrastructure.shiro.SubjectAwareConstants;

public class CurrentUserHelper {

	/**
	 * get current user
	 * 
	 * @return
	 */
	public static AdminUser getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		AdminUser user = (AdminUser) subject.getSession().getAttribute(
				SubjectAwareConstants.USER_AWARE_CONSTANT);
		return user;
	}
	
	
	public static void setCurrentUser(AdminUser user) {
		Subject subject = SecurityUtils.getSubject();
		 subject.getSession().setAttribute(
				SubjectAwareConstants.USER_AWARE_CONSTANT,user);
	}
}
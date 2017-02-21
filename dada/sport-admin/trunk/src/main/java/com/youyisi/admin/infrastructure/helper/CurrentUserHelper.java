package com.youyisi.admin.infrastructure.helper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.shiro.SubjectAwareConstants;

public class CurrentUserHelper {

	/**
	 * get current user
	 * 
	 * @return
	 */
	public static User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(
				SubjectAwareConstants.USER_AWARE_CONSTANT);
		return user;
	}
	
	
	public static void setCurrentUser(User user) {
		Subject subject = SecurityUtils.getSubject();
		 subject.getSession().setAttribute(
				SubjectAwareConstants.USER_AWARE_CONSTANT,user);
	}
}
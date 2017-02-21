package com.youyisi.admin.application.adminuser;

import java.util.List;

import com.youyisi.admin.domain.adminuser.AdminUser;
import com.youyisi.lang.Page;

public interface AdminUserService {

	/**
	 * @param id
	 * @return
	 */
	AdminUser get(Integer id);

	/**
	 * @param username
	 * @return
	 */
	AdminUser getByUsername(String username);

	/**
	 * 
	 * @param admin
	 * @return
	 */
	AdminUser save(AdminUser user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	int delete(AdminUser user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	int update(AdminUser user);

	/**
	 * 
	 * @param username
	 */
	void lockUser(AdminUser user);

	/**
	 * 获得所有超级管理员用户
	 * 
	 * @return
	 */
	List<AdminUser> getSuperAdmin();

	/**
	 * query all
	 * 
	 * @param entity
	 * @return
	 */
	List<AdminUser> query(AdminUser user);

	/**
	 * query for page
	 * 
	 * @param page
	 * @return
	 */
	Page<AdminUser> queryPage(Page<AdminUser> page);

	boolean notExistUser(AdminUser user);

}

package com.youyisi.admin.application.user;

import java.util.List;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.Page;

public interface UserService {

	/**
	 * @param id
	 * @return
	 */
	User get(Integer id);
	
	/**
	 * @param username
	 * @return
	 */
	User getByUsername(String username);
	
	/**
	 * 
	 * @param admin
	 * @return
	 */
	User save(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	int delete(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	int update(User user);
	
	/**
	 * 
	 * @param username
	 */
	void lockUser(User user);
	
	/**
	 * 获得所有超级管理员用户
	 * @return
	 */
	List<User> getSuperAdmin();
	
	/**
	 * query all
	 * 
	 * @param entity
	 * @return
	 */
	List<User> query(User user);

	/**
	 * query for page
	 * 
	 * @param page
	 * @return
	 */
	Page<User> queryPage(Page<User> page);

	boolean notExistUser(User user);
}

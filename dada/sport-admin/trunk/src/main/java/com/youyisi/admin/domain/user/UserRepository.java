package com.youyisi.admin.domain.user;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

public interface UserRepository extends MybatisRepository<Integer, User> {

	/**
	 * 
	 * @param username
	 * @return
	 */
	User getByUsername(String username);
	
	/**
	 * 
	 * @param username
	 */
	void LockUser(User user);
	
	/**
	 * 获得所有是管理员角色的用户
	 * @return
	 */
	List<User> getSuperAdmin();

	boolean notExistUser(User user);
}

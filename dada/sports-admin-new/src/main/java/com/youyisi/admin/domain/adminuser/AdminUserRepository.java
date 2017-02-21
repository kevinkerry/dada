package com.youyisi.admin.domain.adminuser;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

public interface AdminUserRepository extends MybatisRepository<Integer, AdminUser> {

	/**
	 * 
	 * @param username
	 * @return
	 */
	AdminUser getByUsername(String username);

	/**
	 * 
	 * @param username
	 */
	void LockUser(AdminUser user);

	/**
	 * 获得所有是管理员角色的用户
	 * 
	 * @return
	 */
	List<AdminUser> getSuperAdmin();

	boolean notExistUser(AdminUser user);

}

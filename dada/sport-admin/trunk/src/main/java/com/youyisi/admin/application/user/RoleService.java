package com.youyisi.admin.application.user;

import java.util.List;

import com.youyisi.admin.domain.user.Role;
import com.youyisi.lang.Page;


public interface RoleService {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Role get(Integer id);

	/**
	 * 
	 * @param role
	 * @return
	 */
	Role save(Role role);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	int update(Role role);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	int delete(Role role);
	
	/**
	 * query all
	 * 
	 * @param entity
	 * @return
	 */
	List<Role> query(Role role);

	/**
	 * query for page
	 * 
	 * @param page
	 * @return
	 */
	Page<Role> queryPage(Page<Role> page);
	
	/**
     * 检查角色是否已存在
     */
    boolean isExistRole(Role role);

}

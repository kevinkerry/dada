package com.youyisi.admin.application.adminuser;

import java.util.List;

import com.youyisi.admin.domain.adminuser.Auth;
import com.youyisi.lang.Page;


public interface AuthService {

	/**
	 * @param id
	 * @return
	 */
	Auth get(Integer id);
	
	/**
	 * 
	 * @param auth
	 * @return
	 */
	Auth save(Auth auth);
	
	/**
	 * 
	 * @param auth
	 * @return
	 */
	int delete(Auth auth);
	
	/**
	 * 
	 * @param auth
	 * @return
	 */
	int update(Auth auth);
	
	/**
	 * query all
	 * 
	 * @param entity
	 * @return
	 */
	List<Auth> query(Auth auth);

	/**
	 * query for page
	 * 
	 * @param page
	 * @return
	 */
	Page<Auth> queryPage(Page<Auth> page);
	
	/**
     * 检查权限是否已存在
     */
    boolean isExistAuth(Auth auth);
}

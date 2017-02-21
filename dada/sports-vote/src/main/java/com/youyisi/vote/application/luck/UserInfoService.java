package com.youyisi.vote.application.luck;

import com.youyisi.lang.Page;
import com.youyisi.vote.domain.luck.UserInfo;
/**
 * 
 * @author shuye
 *
 */
public interface UserInfoService {
	/**
	 * 
	 * @param user
	 */
	public void save(UserInfo user);

	/**
	 * 
	 * @param id
	 */
	public UserInfo get(Long id);

	/**
	 * 
	 * @param user
	 */
	public void delete(UserInfo user);

	/**
	 * 
	 * @param user
	 */
	public void update(UserInfo user);
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Page<UserInfo> queryPage(Page<UserInfo> page);

	public UserInfo getByOpenId(String openid);
	
}

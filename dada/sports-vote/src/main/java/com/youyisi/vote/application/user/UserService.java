package com.youyisi.vote.application.user;

import com.youyisi.lang.Page;
import com.youyisi.vote.domain.user.User;
/**
 * 
 * @author shuye
 *
 */
public interface UserService {
	/**
	 * 
	 * @param user
	 */
	public void save(User user);

	/**
	 * 
	 * @param id
	 */
	public User get(Long id);

	/**
	 * 
	 * @param user
	 */
	public void delete(User user);

	/**
	 * 
	 * @param user
	 */
	public void update(User user);
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Page<User> queryPage(Page<User> page);

	public void regist(User user);

	public void updateVoteNum(User user);

	public String vote(String wechat, Long userId);
	
	
}

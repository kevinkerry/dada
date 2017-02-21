package com.youyisi.vote.application.user;

import com.youyisi.lang.Page;
import com.youyisi.vote.domain.user.LeadUser;
/**
 * 
 * @author shuye
 *
 */
public interface LeadUserService {
	/**
	 * 
	 * @param user
	 */
	public void save(LeadUser user);

	/**
	 * 
	 * @param id
	 */
	public LeadUser get(Long id);

	/**
	 * 
	 * @param user
	 */
	public void delete(LeadUser user);

	/**
	 * 
	 * @param user
	 */
	public void update(LeadUser user);
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Page<LeadUser> queryPage(Page<LeadUser> page);
	
}

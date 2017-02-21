package com.youyisi.vote.application.luck;

import com.youyisi.lang.Page;
import com.youyisi.vote.domain.luck.LuckUser;

/**
 * @author shuye
 * @time 2015-09-15
 */
public interface LuckUserService {

	LuckUser save(LuckUser entity);

	LuckUser get(Long id);

	Integer delete(LuckUser entity);

	Integer update(LuckUser entity);

	Page<LuckUser> queryPage(Page<LuckUser> page);

	LuckUser getByUserId(Long userId);

}


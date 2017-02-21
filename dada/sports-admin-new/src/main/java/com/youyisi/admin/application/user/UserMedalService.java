package com.youyisi.admin.application.user;

import com.youyisi.admin.domain.user.UserMedal;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-08
 */
public interface UserMedalService {

	UserMedal save(UserMedal entity);

	UserMedal get(Long id);

	Integer delete(UserMedal entity);

	Integer update(UserMedal entity);

	Page<UserMedal> queryPage(Page<UserMedal> page);

}


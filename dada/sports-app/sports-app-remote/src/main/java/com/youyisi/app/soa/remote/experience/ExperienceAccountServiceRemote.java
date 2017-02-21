package com.youyisi.app.soa.remote.experience;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserMoreInfo;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface ExperienceAccountServiceRemote extends BaseServiceInterface<Long, ExperienceAccount> {

	ExperienceAccount getByUserId(Long userId) throws SoaException;

	UserMoreInfo addExperienceAccount(User user) throws SoaException;
}


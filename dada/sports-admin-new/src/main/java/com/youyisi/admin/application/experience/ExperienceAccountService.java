package com.youyisi.admin.application.experience;

import com.youyisi.admin.domain.experience.ExperienceAccount;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface ExperienceAccountService {

	ExperienceAccount save(ExperienceAccount entity);

	ExperienceAccount get(Long id);

	Integer delete(ExperienceAccount entity);

	Integer update(ExperienceAccount entity);

	Page<ExperienceAccount> queryPage(Page<ExperienceAccount> page);

	ExperienceAccount getByUserId(Long userId);

}


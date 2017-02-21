package com.youyisi.admin.application.club;

import com.youyisi.admin.domain.club.Club;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public interface ClubService {

	Club save(Club club);

	Club get(Long id);

	Integer delete(Club club);

	Integer update(Club club);

	Page<Club> queryPage(Page<Club> page);
	
	Integer modify(Club club);

}


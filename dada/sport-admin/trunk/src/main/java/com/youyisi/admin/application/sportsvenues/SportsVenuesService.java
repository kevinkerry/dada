package com.youyisi.admin.application.sportsvenues;

import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public interface SportsVenuesService {

	SportsVenues save(SportsVenues entity);

	SportsVenues get(Long id);

	Integer delete(SportsVenues entity);

	Integer update(SportsVenues entity);

	Page<SportsVenues> queryPage(Page<SportsVenues> page);

}


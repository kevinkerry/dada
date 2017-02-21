package com.youyisi.admin.application.sportsvenues.sportsvenueschild;

import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public interface SportsVenuesChildService {

	 SportsVenuesChild save(SportsVenuesChild entity);

	 SportsVenuesChild get(Long id);

	 Integer delete(SportsVenuesChild entity);

	 Integer update(SportsVenuesChild entity);

	 Page<SportsVenuesChild> queryPage(Page<SportsVenuesChild> page);

}


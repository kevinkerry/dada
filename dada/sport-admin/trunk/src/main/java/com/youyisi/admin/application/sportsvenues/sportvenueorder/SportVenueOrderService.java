package com.youyisi.admin.application.sportsvenues.sportvenueorder;

import com.youyisi.admin.domain.sportsvenues.sportvenueorder.SportVenueOrder;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
public interface SportVenueOrderService {

	SportVenueOrder save(SportVenueOrder entity);

	SportVenueOrder get(Long id);

	Integer delete(SportVenueOrder entity);

	Integer update(SportVenueOrder entity);

	Page<SportVenueOrder> queryPage(Page<SportVenueOrder> page);

}


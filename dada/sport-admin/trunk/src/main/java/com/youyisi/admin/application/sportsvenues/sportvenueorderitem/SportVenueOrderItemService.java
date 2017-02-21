package com.youyisi.admin.application.sportsvenues.sportvenueorderitem;

import com.youyisi.admin.domain.sportsvenues.sportvenueorderitem.SportVenueOrderItem;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
public interface SportVenueOrderItemService {

	SportVenueOrderItem save(SportVenueOrderItem entity);

	SportVenueOrderItem get(Long id);

	Integer delete(SportVenueOrderItem entity);

	Integer update(SportVenueOrderItem entity);

	Page<SportVenueOrderItem> queryPage(Page<SportVenueOrderItem> page);

}


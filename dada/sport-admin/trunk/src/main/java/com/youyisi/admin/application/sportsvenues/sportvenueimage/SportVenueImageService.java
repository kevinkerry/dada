package com.youyisi.admin.application.sportsvenues.sportvenueimage;

import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImage;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public interface SportVenueImageService {

	SportVenueImage save(SportVenueImage entity);

	SportVenueImage get(Long id);

	Integer delete(SportVenueImage entity);

	Integer update(SportVenueImage entity);

	Page<SportVenueImage> queryPage(Page<SportVenueImage> page);

}


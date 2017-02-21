package com.youyisi.admin.application.sportsvenues.sportvenuedistrict;

import com.youyisi.admin.domain.sportsvenues.sportvenuedistrict.SportVenueDistrict;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public interface SportVenueDistrictService {

	SportVenueDistrict save(SportVenueDistrict entity);

	SportVenueDistrict get(Long id);

	Integer delete(SportVenueDistrict entity);

	Integer update(SportVenueDistrict entity);

	Page<SportVenueDistrict> queryPage(Page<SportVenueDistrict> page);

	/**
     * 检查片区是否已存在
     */
    boolean isExistDistrict(SportVenueDistrict sportVenueDistrict);
}


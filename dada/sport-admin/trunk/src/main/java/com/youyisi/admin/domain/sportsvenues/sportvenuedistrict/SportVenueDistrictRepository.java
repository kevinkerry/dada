package com.youyisi.admin.domain.sportsvenues.sportvenuedistrict;

import com.youyisi.mybatis.MybatisRepository;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public interface SportVenueDistrictRepository extends MybatisRepository<Long, SportVenueDistrict> {
    
    /**
     * 检查片区是否已存在
     */
    boolean isExistDistrict(SportVenueDistrict sportVenueDistrict);
}


package com.youyisi.admin.infrastructure.persist.sportsvenues.sportvenuedistrict;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.sportsvenues.sportvenuedistrict.SportVenueDistrict;
import com.youyisi.admin.domain.sportsvenues.sportvenuedistrict.SportVenueDistrictRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Repository
public class MybatisSportVenueDistrictRepositoryImpl extends MybatisOperations<Long, SportVenueDistrict> implements SportVenueDistrictRepository {

    @Override
    public boolean isExistDistrict(SportVenueDistrict sportVenueDistrict) {
        Long count = getSqlSession().selectOne(getNamespace().concat(".count"), sportVenueDistrict);
        if(count>0){
            return true;
        }
        return false;
    }
}


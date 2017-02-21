package com.youyisi.admin.infrastructure.persist.sportsvenues.sportvenueimage;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImage;
import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImageRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Repository
public class MybatisSportVenueImageRepositoryImpl extends MybatisOperations<Long, SportVenueImage> implements SportVenueImageRepository {
}


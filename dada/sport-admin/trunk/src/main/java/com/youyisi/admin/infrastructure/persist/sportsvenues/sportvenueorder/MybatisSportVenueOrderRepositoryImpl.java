package com.youyisi.admin.infrastructure.persist.sportsvenues.sportvenueorder;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.sportsvenues.sportvenueorder.SportVenueOrder;
import com.youyisi.admin.domain.sportsvenues.sportvenueorder.SportVenueOrderRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Repository
public class MybatisSportVenueOrderRepositoryImpl extends MybatisOperations<Long, SportVenueOrder> implements SportVenueOrderRepository {
}


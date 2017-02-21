package com.youyisi.admin.infrastructure.persist.sportsvenues.sportvenueorderitem;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.sportsvenues.sportvenueorderitem.SportVenueOrderItem;
import com.youyisi.admin.domain.sportsvenues.sportvenueorderitem.SportVenueOrderItemRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Repository
public class MybatisSportVenueOrderItemRepositoryImpl extends MybatisOperations<Long, SportVenueOrderItem> implements SportVenueOrderItemRepository {
}


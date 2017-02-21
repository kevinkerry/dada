package com.youyisi.admin.infrastructure.persist.gold;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.gold.GoldBeanDetail;
import com.youyisi.admin.domain.gold.GoldBeanDetailRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanDetailRepositoryImpl extends MybatisOperations<Long, GoldBeanDetail> implements GoldBeanDetailRepository {
}


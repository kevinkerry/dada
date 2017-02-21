package com.youyisi.admin.infrastructure.persist.gold;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.gold.GoldBeanCash;
import com.youyisi.admin.domain.gold.GoldBeanCashRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanCashRepositoryImpl extends MybatisOperations<Long, GoldBeanCash> implements GoldBeanCashRepository {
}


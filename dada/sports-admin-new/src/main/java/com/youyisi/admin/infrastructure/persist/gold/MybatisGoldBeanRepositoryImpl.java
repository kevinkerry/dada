package com.youyisi.admin.infrastructure.persist.gold;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.gold.GoldBean;
import com.youyisi.admin.domain.gold.GoldBeanRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanRepositoryImpl extends MybatisOperations<Long, GoldBean> implements GoldBeanRepository {
}


package com.youyisi.admin.infrastructure.persist.gift;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.gift.Gift;
import com.youyisi.admin.domain.gift.GiftRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Repository
public class MybatisGiftRepositoryImpl extends MybatisOperations<Long, Gift> implements GiftRepository {
}


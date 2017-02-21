package com.youyisi.admin.infrastructure.persist.gift.gifttype;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.gift.gifttype.GiftType;
import com.youyisi.admin.domain.gift.gifttype.GiftTypeRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Repository
public class MybatisGiftTypeRepositoryImpl extends MybatisOperations<Long, GiftType> implements GiftTypeRepository {

    @Override
    public List<GiftType> getAll(Map<String, Object> params) {
        return getSqlSession().selectList(getNamespace().concat(".queryAll"), params);
    }
}


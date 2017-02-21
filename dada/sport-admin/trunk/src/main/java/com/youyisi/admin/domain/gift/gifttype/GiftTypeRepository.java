package com.youyisi.admin.domain.gift.gifttype;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public interface GiftTypeRepository extends MybatisRepository<Long, GiftType> {
    
    /**
     * 查询所有的记录
     * 
     * @param params查询参数
     * @return List<GiftType>
     */
    List<GiftType> getAll(Map<String, Object> params);
}


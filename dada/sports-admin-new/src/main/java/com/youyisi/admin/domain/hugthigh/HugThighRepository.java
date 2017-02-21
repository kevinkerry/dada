package com.youyisi.admin.domain.hugthigh;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-07-19
 */
public interface HugThighRepository extends MybatisRepository<Long, HugThigh> {

	Integer countHugThigh(Map<String, Object> map);

	Integer countHugThighByUserId(Long userId);
}

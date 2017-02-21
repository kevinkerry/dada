package com.youyisi.admin.domain.thigh;

import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-07-18
 */
public interface ThighRepository extends MybatisRepository<Long, Thigh> {

	Integer countThigh(Map<String, Object> map);

	Integer countThighByActivityId(Map<String, Object> map);

	Page<Thigh> queryPageByUserId(Page<Thigh> page);

	Integer countThighByUserId(Long userId);
}

package com.youyisi.app.soa.infrastructure.persist.thigh;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.thigh.HugThigh;
import com.youyisi.sports.domain.thigh.HugThighWithUser;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface HugThighRepository extends MybatisRepository<Long, HugThigh> {

	Integer getHugThighCount(Map<String, Object> map);

	HugThigh getByUserAndThighId(Map<String, Object> map);

	List<HugThighWithUser> getListByThighId(Long thighId);

	Integer getByUserIdAndThighId(Map<String, Object> map);

	Integer getCount(Map<String, Object> map);
}


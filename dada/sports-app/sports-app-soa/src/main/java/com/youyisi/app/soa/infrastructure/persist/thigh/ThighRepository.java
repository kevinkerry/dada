package com.youyisi.app.soa.infrastructure.persist.thigh;

import java.util.List;
import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.thigh.MyThigh;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.thigh.ThighMoreInfo;
import com.youyisi.sports.domain.thigh.ThighRank;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface ThighRepository extends MybatisRepository<Long, Thigh> {

	Thigh getByActivityIdAndUserId(Map<String, Object> map);

	List<ThighMoreInfo> getList(Long activityId);

	Long thighCount(Map<String, Object> map);

	Page<MyThigh> queryPageMyThigh(Page<MyThigh> page);

	List<ThighRank> getListThighRank(Page<ThighRank> page);
}


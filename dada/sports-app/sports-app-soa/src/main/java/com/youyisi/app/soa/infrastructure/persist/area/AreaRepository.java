package com.youyisi.app.soa.infrastructure.persist.area;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.area.Area;

/**
 * @author shuye
 * @time 2016-05-30
 */
public interface AreaRepository extends MybatisRepository<Long, Area> {

	List<Area> getByParentId(Long parentId);
}


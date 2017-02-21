package com.youyisi.admin.domain.snatch;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface SnatchWinRepository extends MybatisRepository<Long, SnatchWin> {

	Double getSnatchWinByActivityIdAndUserId(Map<String, Object> map);
}

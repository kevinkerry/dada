package com.youyisi.admin.domain.medal;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-07
 */
public interface MedalRepository extends MybatisRepository<Long, Medal> {

	List<Medal> getMedalList(Map<String, Object> map);

	Integer delMedalById(Long id);
}

package com.youyisi.vote.domain.luck;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2015-09-15
 */
public interface LuckRepository extends MybatisRepository<Long, Luck> {

	List<Luck> getLuck();
}


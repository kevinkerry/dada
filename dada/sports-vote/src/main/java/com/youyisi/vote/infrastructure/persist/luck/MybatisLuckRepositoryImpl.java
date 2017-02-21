package com.youyisi.vote.infrastructure.persist.luck;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.luck.Luck;
import com.youyisi.vote.domain.luck.LuckRepository;

/**
 * @author shuye
 * @time 2015-09-15
 */
@Repository
public class MybatisLuckRepositoryImpl extends MybatisOperations<Long, Luck> implements LuckRepository {

	@Override
	public List<Luck> getLuck() {
		// TODO Auto-generated method stub
		 return getSqlSession().selectList(getNamespace().concat(".getLuck"));
	}
}


package com.youyisi.admin.infrastructure.persist.intelligent;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.intelligent.Intelligent;
import com.youyisi.admin.domain.intelligent.IntelligentRepository;
import com.youyisi.mybatis.MybatisOperations;


@Repository
public class MybatisIntelligentRepositoryImpl  extends MybatisOperations<Long, Intelligent> implements IntelligentRepository{

	@Override
	public Intelligent getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean notExistUser(Intelligent user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer out(Intelligent user) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getNamespace().concat(".out"), user);
	}

	@Override
	public void pass(Intelligent intelligent) {
		// TODO Auto-generated method stub
		getSqlSession().update(getNamespace().concat(".pass"), intelligent);
	}
}

package com.youyisi.admin.domain.intelligent;

import com.youyisi.mybatis.MybatisRepository;

public interface IntelligentRepository extends MybatisRepository<Long, Intelligent> {

	/**
	 * 
	 * @param username
	 * @return
	 */
	Intelligent getByUsername(String username);

	boolean notExistUser(Intelligent user);

	Integer out(Intelligent user);

	void pass(Intelligent intelligent);
}

package com.youyisi.vote.domain.spot;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

public interface SpotRepository extends MybatisRepository<Long,Spot>{

	Spot getByMobile(String mobile);

	List<Spot> queryAllSpot();

}

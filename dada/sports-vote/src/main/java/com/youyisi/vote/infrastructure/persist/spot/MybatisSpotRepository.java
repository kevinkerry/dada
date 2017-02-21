package com.youyisi.vote.infrastructure.persist.spot;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.spot.Spot;
import com.youyisi.vote.domain.spot.SpotRepository;
/**
 * 
 * @author shuye
 *
 */
@Repository
public class MybatisSpotRepository extends MybatisOperations<Long,Spot> implements SpotRepository {

	@Override
	public Spot getByMobile(String mobile) {
		return getSqlSession().selectOne(getNamespace().concat(".getByMobile"), mobile);
	}

	@Override
	public List<Spot> queryAllSpot() {
		// TODO Auto-generated method stub
		 return getSqlSession().selectList(getNamespace().concat(".queryAllSpot"));
	}

}

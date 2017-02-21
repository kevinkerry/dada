package com.youyisi.app.soa.infrastructure.persist.annual.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldDetailRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisAnnualYieldDetailRepositoryImpl extends MybatisOperations<Long, AnnualYieldDetail>
		implements AnnualYieldDetailRepository {

	@Override
	public AnnualYieldDetail getCurrentDay(Map<String, Object> map) {
		List<AnnualYieldDetail> list = getSqlSession().selectList(getNamespace().concat(".getCurrentDay"), map);
		if(list.isEmpty()){
			return null;
		}else{
			if(list.size()>1){
				AnnualYieldDetail ad = list.get(0);
				list.remove(0);
				for(AnnualYieldDetail a:list){
					super.delete(a);
				}
				return ad;
			}else{
				return list.get(0);
			}
		}
		 
	}

	@Override
	public Double getSumCurrentDayDistance(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getSumCurrentDayDistance"), map);
	}
}

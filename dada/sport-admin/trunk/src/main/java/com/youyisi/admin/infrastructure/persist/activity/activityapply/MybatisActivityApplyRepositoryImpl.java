package com.youyisi.admin.infrastructure.persist.activity.activityapply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.activity.activityapply.ActivityApply;
import com.youyisi.admin.domain.activity.activityapply.ActivityApplyRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Repository
public class MybatisActivityApplyRepositoryImpl extends MybatisOperations<Long, ActivityApply> implements ActivityApplyRepository {

    @Override
    public ActivityApply getByUserIdAndActivityId(Long userId, Long activityId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("activityId", activityId);
        return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndActivityId"), param);
    }

	@Override
	public List<ActivityApply> queryAll(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".queryAll"), activityId);
	}
}


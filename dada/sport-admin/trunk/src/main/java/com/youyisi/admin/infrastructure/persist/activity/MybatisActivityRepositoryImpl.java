package com.youyisi.admin.infrastructure.persist.activity;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.ActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 16, 2015
 */
@Repository
public class MybatisActivityRepositoryImpl extends MybatisOperations<Long, Activity> implements ActivityRepository {

    @Override
    public int delete(Activity activity) {
        getSqlSession().delete(getNamespace().concat(".deleteApplys"), activity);
        return super.delete(activity);
    }
    
}


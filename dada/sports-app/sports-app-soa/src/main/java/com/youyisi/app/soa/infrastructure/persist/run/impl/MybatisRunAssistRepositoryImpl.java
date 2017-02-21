package com.youyisi.app.soa.infrastructure.persist.run.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.run.RunAssist;
import com.youyisi.app.soa.infrastructure.persist.run.RunAssistRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-08-16
 */
@Repository
public class MybatisRunAssistRepositoryImpl extends MybatisOperations<Long, RunAssist> implements RunAssistRepository {
}


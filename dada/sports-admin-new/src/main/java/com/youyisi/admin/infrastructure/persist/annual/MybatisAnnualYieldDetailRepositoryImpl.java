package com.youyisi.admin.infrastructure.persist.annual;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.annual.AnnualYieldDetail;
import com.youyisi.admin.domain.annual.AnnualYieldDetailRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Repository
public class MybatisAnnualYieldDetailRepositoryImpl extends MybatisOperations<Long, AnnualYieldDetail> implements AnnualYieldDetailRepository {
}


package com.youyisi.admin.infrastructure.persist.indiana.indianadetail;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.indiana.indianadetail.IndianaDetail;
import com.youyisi.admin.domain.indiana.indianadetail.IndianaDetailRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Repository
public class MybatisIndianaDetailRepositoryImpl extends MybatisOperations<Long, IndianaDetail> implements IndianaDetailRepository {
}


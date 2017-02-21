package com.youyisi.admin.infrastructure.persist.indiana;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.indiana.Indiana;
import com.youyisi.admin.domain.indiana.IndianaRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Repository
public class MybatisIndianaRepositoryImpl extends MybatisOperations<Long, Indiana> implements IndianaRepository {
}


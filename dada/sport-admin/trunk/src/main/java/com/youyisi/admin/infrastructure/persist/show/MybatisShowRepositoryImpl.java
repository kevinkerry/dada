package com.youyisi.admin.infrastructure.persist.show;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.show.Show;
import com.youyisi.admin.domain.show.ShowRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Repository
public class MybatisShowRepositoryImpl extends MybatisOperations<Long, Show> implements ShowRepository {
}


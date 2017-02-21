package com.youyisi.admin.infrastructure.persist.sportsvenues;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.admin.domain.sportsvenues.SportsVenuesRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Repository
public class MybatisSportsVenuesRepositoryImpl extends MybatisOperations<Long, SportsVenues> implements SportsVenuesRepository {
}


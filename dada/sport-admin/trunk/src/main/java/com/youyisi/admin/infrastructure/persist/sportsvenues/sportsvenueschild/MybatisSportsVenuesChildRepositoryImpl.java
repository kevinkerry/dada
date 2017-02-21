package com.youyisi.admin.infrastructure.persist.sportsvenues.sportsvenueschild;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChildRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Repository
public class MybatisSportsVenuesChildRepositoryImpl extends MybatisOperations<Long, SportsVenuesChild> implements SportsVenuesChildRepository {
}


package com.youyisi.admin.infrastructure.persist.club;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.club.Club;
import com.youyisi.admin.domain.club.ClubRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Repository
public class MybatisClubRepositoryImpl extends MybatisOperations<Long, Club> implements ClubRepository {
}


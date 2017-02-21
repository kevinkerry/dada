package com.youyisi.vote.infrastructure.persist.user;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.user.LeadUser;
import com.youyisi.vote.domain.user.LeadUserRepository;
/**
 * 
 * @author shuye
 *
 */
@Repository
public class MybatisLeadUserRepository extends MybatisOperations<Long,LeadUser> implements LeadUserRepository {

}

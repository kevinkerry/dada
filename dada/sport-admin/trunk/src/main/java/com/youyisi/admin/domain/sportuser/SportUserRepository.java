package com.youyisi.admin.domain.sportuser;

import com.youyisi.mybatis.MybatisRepository;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public interface SportUserRepository extends MybatisRepository<Long, SportUser> {
    
    /**
     * 
     * @param userName
     * @return
     */
    SportUser getByUsername(String userName);
}


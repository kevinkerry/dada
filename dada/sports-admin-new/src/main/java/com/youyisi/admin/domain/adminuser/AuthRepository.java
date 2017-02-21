package com.youyisi.admin.domain.adminuser;

import com.youyisi.mybatis.MybatisRepository;

/**
 * 
 * @author yinjunfeng
 * @date Jul 16, 2015
 */
public interface AuthRepository extends MybatisRepository<Integer, Auth> {

    /**
     * 检查权限是否已存在
     */
    boolean isExistAuth(Auth auth);
}

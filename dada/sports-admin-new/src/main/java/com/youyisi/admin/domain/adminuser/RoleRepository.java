package com.youyisi.admin.domain.adminuser;

import com.youyisi.mybatis.MybatisRepository;

/**
 * 
 * @author yinjunfeng
 * @date Jul 16, 2015
 */
public interface RoleRepository extends MybatisRepository<Integer, Role> {
    
    /**
     * 检查角色是否已存在
     */
    boolean isExistRole(Role role);
}

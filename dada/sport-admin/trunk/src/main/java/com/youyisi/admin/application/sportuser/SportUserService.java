package com.youyisi.admin.application.sportuser;

import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public interface SportUserService {
    
    SportUser save(SportUser entity);
    
    SportUser get(Long id);
    
    Integer delete(SportUser entity);
    
    Integer update(SportUser entity);
    
    Page<SportUser> queryPage(Page<SportUser> page);
    
    /**
     * @param userName
     * @return
     */
    SportUser getByUserName(String userName);
}


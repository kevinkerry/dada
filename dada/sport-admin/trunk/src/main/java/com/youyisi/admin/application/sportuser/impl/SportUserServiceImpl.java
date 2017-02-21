package com.youyisi.admin.application.sportuser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportuser.SportUserService;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.admin.domain.sportuser.SportUserRepository;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Service
public class SportUserServiceImpl implements SportUserService {
    
        @Autowired
        private SportUserRepository repository;
        
        @Override
        public SportUser get(Long id) {
            return repository.get(id);
        }
        
        @Override
        public SportUser save(SportUser entity) {
            return repository.save(entity);
        }
        
        @Override
        public Integer delete(SportUser entity) {
            return repository.delete(entity);
        }
        
        @Override
        public Integer update(SportUser entity) {
            return repository.update(entity);
        }
        
        @Override
        public Page<SportUser> queryPage(Page<SportUser> page) {
            return repository.queryPage(page);
        }

        @Override
        public SportUser getByUserName(String userName) {
            return repository.getByUsername(userName);
        }
}


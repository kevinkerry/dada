package com.youyisi.admin.application.category.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.category.CategoryService;
import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.category.CategoryRepository;
import com.youyisi.lang.Page;
/**
 * @author shuye
 * @time 2015-07-07 18 11 46
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository repository;
    
    @Override
    public Category get(Long categoryId) {
        return repository.get(categoryId);
    }
    
    @Override
    public Category save(Category category) {
        return repository.save(category);
    }
    
    @Override
    public Integer delete(Category category) {
        return repository.delete(category);
    }
    
    @Override
    public Integer update(Category category) {
        return repository.update(category);
    }
    
    @Override
    public Page<Category> queryPage(Page<Category> page) {
        return repository.queryPage(page);
    }

    @Override
    public List<Category> queryAll(Category category) {
        return repository.query(category);
    }
}


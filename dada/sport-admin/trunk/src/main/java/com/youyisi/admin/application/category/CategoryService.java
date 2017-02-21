package com.youyisi.admin.application.category;

import java.util.List;

import com.youyisi.admin.domain.category.Category;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 8, 2015
 */
public interface CategoryService {
    
    Category save(Category category);
    
    Category get(Long categoryId);
    
    Integer delete(Category category);
    
    Integer update(Category category);
    
    Page<Category> queryPage(Page<Category> page);
    
    List<Category> queryAll(Category category);
}


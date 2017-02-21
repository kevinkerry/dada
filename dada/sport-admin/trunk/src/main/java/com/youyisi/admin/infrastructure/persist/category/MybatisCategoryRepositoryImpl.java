package com.youyisi.admin.infrastructure.persist.category;
import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.category.CategoryRepository;
import com.youyisi.mybatis.MybatisOperations;
/**
 * @author shuye
 * @time 2015-07-07 18 11 46
 */
@Repository
public class MybatisCategoryRepositoryImpl extends MybatisOperations<Long, Category> implements CategoryRepository {
}


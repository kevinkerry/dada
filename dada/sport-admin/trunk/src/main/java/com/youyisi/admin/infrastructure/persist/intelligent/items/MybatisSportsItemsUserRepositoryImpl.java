package com.youyisi.admin.infrastructure.persist.intelligent.items;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.intelligent.items.SportsItems;
import com.youyisi.admin.domain.intelligent.items.SportsItemsRepository;
import com.youyisi.mybatis.MybatisOperations;


@Repository
public class MybatisSportsItemsUserRepositoryImpl  extends MybatisOperations<Long, SportsItems> implements SportsItemsRepository{
	
}

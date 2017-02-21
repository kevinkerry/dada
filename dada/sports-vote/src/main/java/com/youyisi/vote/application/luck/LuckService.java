package com.youyisi.vote.application.luck;

import java.util.List;

import com.youyisi.lang.Page;
import com.youyisi.vote.domain.luck.Luck;

/**
 * @author shuye
 * @time 2015-09-15
 */
public interface LuckService {

	Luck save(Luck entity);

	Luck get(Long id);

	Integer delete(Luck entity);

	Integer update(Luck entity);

	Page<Luck> queryPage(Page<Luck> page);

	List<Luck> getLuck();

}


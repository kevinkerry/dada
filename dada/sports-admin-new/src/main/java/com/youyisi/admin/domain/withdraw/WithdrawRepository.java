package com.youyisi.admin.domain.withdraw;

import java.util.List;
import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-21
 */
public interface WithdrawRepository extends MybatisRepository<Long, Withdraw> {

	Page<Withdraw> queryPageWithdraw(Page<Withdraw> page);

	Withdraw getByDrawbackNum(String withdrawNumber);

	Double sumWithdraw(Map<String, Object> map);

	List<Withdraw> getWithdrawList(Map<String, Object> map);
}

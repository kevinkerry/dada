package com.youyisi.admin.application.withdraw;

import org.springframework.http.ResponseEntity;

import com.youyisi.admin.domain.withdraw.Withdraw;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-21
 */
public interface WithdrawService {

	Withdraw save(Withdraw entity);

	Withdraw get(Long id);

	Integer delete(Withdraw entity);

	Integer update(Withdraw entity);

	Page<Withdraw> queryPage(Page<Withdraw> page);

	Page<Withdraw> queryPageWithdraw(Page<Withdraw> page);

	Withdraw getByDrawbackNum(String withdrawNumber);

	Integer reset(Long[] wid);

	Integer sendBack(Long wid, Integer code);

	Double sumWithdraw(Long userId, Integer status);

	Integer batchReject(Long[] wid, Integer code);

	ResponseEntity<byte[]> exportReport(String path, Long beginTime, Long endTime);

}

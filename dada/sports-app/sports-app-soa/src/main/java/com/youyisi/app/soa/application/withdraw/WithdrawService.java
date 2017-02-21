package com.youyisi.app.soa.application.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.wallet.WalletDetailService;
import com.youyisi.app.soa.application.wallet.WalletService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.withdraw.WithdrawRepository;
import com.youyisi.app.soa.remote.withdraw.WithdrawServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.domain.withdraw.Withdraw;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class WithdrawService implements WithdrawServiceRemote {

	@Autowired
	private WithdrawRepository repository;
	@Autowired
	private WalletDetailService walletDetailService;
	@Autowired
	private WalletService walletService;

	@Override
	public Withdraw get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Withdraw save(Withdraw entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Withdraw entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Withdraw entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Withdraw> queryPage(Page<Withdraw> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Withdraw add(Withdraw withdraw, Wallet wallet) throws SoaException {
		WalletDetail walletDetail = new WalletDetail();
		walletDetail.setCreateTime(System.currentTimeMillis());
		walletDetail.setDate(DateUtil.currentDateForDay());
		walletDetail.setMoney(withdraw.getMoney());
		walletDetail.setType(2);
		walletDetail.setUserId(withdraw.getUserId());
		
		Double totalAsset = wallet.getTotalAsset();
		wallet.setTotalAsset(ArithHelper.sub(wallet.getTotalAsset(),withdraw.getMoney()));
		walletDetail.setResult(wallet.getTotalAsset());
		walletDetail = walletDetailService.save(walletDetail);
		
		if(wallet.getTotalAsset()<wallet.getPrincipal()){
			withdraw.setPrincipal(ArithHelper.sub(wallet.getPrincipal(),wallet.getTotalAsset()));
			wallet.setPrincipal(wallet.getTotalAsset());
		}else{
			withdraw.setPrincipal(0.0);
		}
		int count = walletService.updateByVersion(wallet,totalAsset);
		if(count==1){
			return repository.save(withdraw);
		}else{
			walletDetailService.delete(walletDetail);
			return null;
		}
	}

	@Override
	public Double currentWithdraw(Long userId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.currentWithdraw(userId);
	}
}


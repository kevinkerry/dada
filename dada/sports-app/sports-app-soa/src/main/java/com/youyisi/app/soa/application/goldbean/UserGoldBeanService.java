package com.youyisi.app.soa.application.goldbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.UserGoldBeanRepository;
import com.youyisi.app.soa.infrastructure.persist.orders.OrderFlowRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletRepository;
import com.youyisi.app.soa.remote.goldbean.UserGoldBeanServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.goldbean.GoldBeanDetail;
import com.youyisi.sports.domain.goldbean.UserGoldBean;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class UserGoldBeanService implements UserGoldBeanServiceRemote {

	@Autowired
	private UserGoldBeanRepository repository;
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private WalletDetailRepository walletDetailRepository;
	@Autowired
	private GoldBeanRepository goldBeanRepository;
	@Autowired
	private OrderFlowRepository orderFlowRepository;
	
	@Autowired
	private GoldBeanDetailRepository goldBeanDetailRepository;
	@Override
	public UserGoldBean get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public UserGoldBean save(UserGoldBean entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(UserGoldBean entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserGoldBean entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<UserGoldBean> queryPage(Page<UserGoldBean> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public int pay(Long id) throws SoaException {
		UserGoldBean userGoldBean= repository.get(id);
		if(userGoldBean.getPayStatus().intValue()==1){
			return -1;
		}
		WalletDetail walletDetail = new WalletDetail();
		walletDetail.setCreateTime(System.currentTimeMillis());
		walletDetail.setDate(DateUtil.currentDateForDay());
		walletDetail.setMoney(userGoldBean.getGoldBeanRecharge().getMoney());
		walletDetail.setType(11);
		walletDetail.setUserId(userGoldBean.getUserId());
		
		
		Wallet wallet = walletRepository.getByUserId(userGoldBean.getUserId());
		if(wallet.getTotalAsset()<walletDetail.getMoney()){
			return -2;
		}
		wallet.setTotalAsset(ArithHelper.sub(wallet.getTotalAsset(),walletDetail.getMoney()));
		if(wallet.getTotalAsset()<wallet.getPrincipal()){
			wallet.setPrincipal(wallet.getTotalAsset());
		}
		walletDetail.setResult(wallet.getTotalAsset());
		walletDetailRepository.save(walletDetail);
		walletRepository.update(wallet);
		
		
		updateGoldBean(userGoldBean,"余额购买");
		userGoldBean.setPayStatus(1);
		userGoldBean.setStatus(1);
		repository.update(userGoldBean);
		return 1;
	}

	private void updateGoldBean(UserGoldBean userGoldBean,String note) {
		GoldBeanDetail entity = new GoldBeanDetail();
		entity.setCategory(1);
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		entity.setGoldBean(userGoldBean.getGoldBeanRecharge().getGoldBean());
		
		entity.setNote(note);
		entity.setProductId(userGoldBean.getId());
		
		GoldBean goldBean = goldBeanRepository.getByUserId(userGoldBean.getUserId());
		if(goldBean==null){
			goldBean = new GoldBean();
			goldBean.setUserId(userGoldBean.getUserId());
			goldBean.setGoldBean(0.0);
			goldBean = goldBeanRepository.save(goldBean);
		}
		goldBean.setGoldBean(ArithHelper.add(goldBean.getGoldBean(), entity.getGoldBean()));
		entity.setResult(goldBean.getGoldBean());
		entity.setType(1);
		entity.setUserId(userGoldBean.getUserId());
		
		goldBeanDetailRepository.save(entity);
		goldBeanRepository.update(goldBean);
	}

	@Override
	public void otherpay(Orders order) {
		// TODO Auto-generated method stub
		UserGoldBean userGoldBean= repository.get(order.getProductId());
		updateGoldBean(userGoldBean,"购买");
		userGoldBean.setPayStatus(1);
		userGoldBean.setStatus(1);
		repository.update(userGoldBean);
	}
}


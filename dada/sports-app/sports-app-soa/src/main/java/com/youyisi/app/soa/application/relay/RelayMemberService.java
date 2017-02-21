package com.youyisi.app.soa.application.relay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.application.coupon.UserCouponService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.activity.ActivityRepository;
import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.medal.UserMedalRepository;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayMemberFavourRepository;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayMemberRepository;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayTeamRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletRepository;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.medal.UserMedal;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMemberFavour;
import com.youyisi.sports.domain.relay.RelayMemberWithChildren;
import com.youyisi.sports.domain.relay.RelayMemberWithChildrenAndUser;
import com.youyisi.sports.domain.relay.RelayMemberWithMore;
import com.youyisi.sports.domain.relay.RelayMemberWithParent;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Service
public class RelayMemberService implements RelayMemberServiceRemote {

	@Autowired
	private RelayMemberRepository repository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private RelayTeamRepository relayTeamRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private UserCouponService userCouponService;
	@Autowired
	private WalletDetailRepository walletDetailRepository;
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private UserMedalRepository userMedalRepository;
	@Autowired
	private RelayMemberFavourRepository relayMemberFavourRepository;
	@Override
	public RelayMember get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RelayMember save(RelayMember entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayMember entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayMember entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RelayMember> queryPage(Page<RelayMember> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public RelayMember getByActivityIdAndUserId(Long activityId, Long userId)
			throws SoaException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.getByActivityIdAndUserId(map);
	}

	@Override
	public Integer getCountByActivityId(Long activityId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getCountByActivityId(activityId);
	}

	@Override
	public Page<RelayMemberWithMore> queryPageForTeam(
			Page<RelayMemberWithMore> page,Long currentUserId) throws SoaException {
		page = repository.queryPageForTeam(page);
		List<RelayMemberWithMore> list = page.getResult();
		ActivityWithRelayRaceActivity activity = null;
		if(!list.isEmpty()){
			activity = activityRepository.getActivityWithRelayRaceActivityById(list.get(0).getActivityId());
		}
		for(RelayMemberWithMore r : list){
			r.setRelayBatonCount(getRelayBatonCount(r,activity));
			r.setDistance(getSumDistance(r.getUserId(),activity));
			
			r.setHaveCheer(haveCheer(currentUserId,r.getId()));
			r.setHavePraise(havePraise(currentUserId,r.getId()));
		}
		
		return page;
	}
	
	private Integer havePraise(Long currentUserId, Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",currentUserId);
		map.put("type",1);
		map.put("date",DateUtil.currentDateForDay());
		map.put("relayMemberId",id);
		RelayMemberFavour rmf = relayMemberFavourRepository.getByMap(map);
		if(rmf==null){
			return 0;
		}
		return 1;
	}

	private Integer haveCheer(Long currentUserId, Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",currentUserId);
		map.put("type",2);
		map.put("date",DateUtil.currentDateForDay());
		map.put("relayMemberId",id);
		RelayMemberFavour rmf = relayMemberFavourRepository.getByMap(map);
		if(rmf==null){
			return 0;
		}
		return 1;
	}

	private Integer getRelayBatonCount(RelayMemberWithMore r,ActivityWithRelayRaceActivity activity) {
		List<RelayMemberWithChildren> list = repository.getByParentId(r.getId());
		Integer count = list.size();
		return activity.getRelayRaceActivity().getRelayBatonLimit()-count>=0 ? activity.getRelayRaceActivity().getRelayBatonLimit()-count : 0;
	}

	private Integer getCount(List<RelayMemberWithChildren> list) {
		Integer count = list.size();
		for(RelayMemberWithChildren r : list){
			if(r.getChildren()!=null){
				count += getCount(r.getChildren());
			}
		}
		return count;
	}

	@Override
	public Double getSumDistance(Long userId,ActivityWithRelayRaceActivity activity) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("begin", DateUtil.getDateForDay(activity.getBeginTime()));
		map.put("end", DateUtil.getDateForDay(activity.getEndTime()));
		map.put("userId",userId);
		map.put("maxDistance",activity.getRelayRaceActivity().getMaxDistance());
		map.put("maxStep",activity.getRelayRaceActivity().getMaxStep());
		Double distance = distanceRepository.getUserDistance(map);
		if(distance==null){
			distance=0.0;
		}
		Integer step = stepRepository.getUserStep(map);
		if(step==null){
			step = 0;
		}
		Double _step = step*1.0/activity.getRelayRaceActivity().getStepToDistance();
		return ArithHelper.add(distance, _step);
	}

	@Override
	public RelayMemberWithMore getMyRelayMemberWithMore(Map<String,Object> map)
			throws SoaException {
		// TODO Auto-generated method stub
		RelayMemberWithMore r = repository.getMyRelayMemberWithMore(map);
		if(r!=null){
			ActivityWithRelayRaceActivity activity = activityRepository.getActivityWithRelayRaceActivityById(r.getActivityId());
			r.setRelayBatonCount(getRelayBatonCount(r,activity));
			r.setDistance(getSumDistance(r.getUserId(),activity));
		}
		return r;
	}
	
	

	@Override
	public List<RelayMemberWithChildrenAndUser> getRelayMemberWithChildrenAndUserByParentId(
			Long id) throws SoaException {
		
		return repository.getRelayMemberWithChildrenAndUserByParentId(id);
	}
	@Override
	public Map<String, Object> mylist(Long id) throws SoaException {
		Map<String,Object> map = new HashMap<String, Object>();
		List<RelayMemberWithChildrenAndUser> list = repository.getRelayMemberWithChildrenAndUserByParentId(id);
		List<RelayMemberWithChildrenAndUser> result = new ArrayList<RelayMemberWithChildrenAndUser>();
		Double sumBonus = 0.0;
		if(!list.isEmpty()){
			Integer first = list.size();
			Integer count = 0;
			int level = 0;
			ActivityWithRelayRaceActivity activity = activityRepository.getActivityWithRelayRaceActivityById(list.get(0).getActivityId());
			count+=foreachRelayMemberWithChildrenAndUser(list,result,map,level,activity.getRelayRaceActivity().getLevelLimit());
			count = count-first;
			sumBonus = ArithHelper.add(ArithHelper.mul(count.doubleValue(), activity.getRelayRaceActivity().getOtherBonus()), ArithHelper.mul(first.doubleValue(), activity.getRelayRaceActivity().getFirstBonus()));
		}
		map.put("list",result);
		map.put("sumBonus",sumBonus);
		return map;
	}
	Integer foreachRelayMemberWithChildrenAndUser(List<RelayMemberWithChildrenAndUser> list,List<RelayMemberWithChildrenAndUser> result,Map<String,Object> map,int level,Integer levelLimit){
		Integer count =0;
		level++;
		for(RelayMemberWithChildrenAndUser r : list){
		if(r.getChildren()!=null&&level<levelLimit){
			count+=foreachRelayMemberWithChildrenAndUser(r.getChildren(),result,map,level,levelLimit);
		}
		r.setChildren(null);
		result.add(r);
		if(r.getLevel().intValue()!=1){
			count++;
		}
	}
	return count;
	}

	@Override
	public void otherpay(Orders order) throws SoaException {
		RelayMember relayMember = repository.get(order.getProductId());
		ActivityWithRelayRaceActivity activity = activityRepository.getActivityWithRelayRaceActivityById(relayMember.getActivityId());
		getMedal(activity,relayMember);
		updateStatusAndGetCoupon(relayMember,activity);
		
	}
	
	
	
	private void getCouponForOther(RelayMemberWithParent parent,int count, Integer levelLimit) throws SoaException {
		getCoupon(parent,false);
		count++;
		if(parent.getParent()!=null&&count<levelLimit-1){
			getCouponForOther(parent.getParent(),count,levelLimit);
		}
		
	}
	

	private void getCoupon(RelayMemberWithParent relayMemberWithParent,Boolean first) throws SoaException {
		
		ActivityWithRelayRaceActivity activity = activityRepository.getActivityWithRelayRaceActivityById(relayMemberWithParent.getActivityId());
		Map<String,Object> cmap = new HashMap<String,Object>();
		cmap.put("category",3);
		cmap.put("userId", relayMemberWithParent.getUserId());
		cmap.put("time",System.currentTimeMillis());
		cmap.put("activityId",activity.getId());
		
		UserCoupon uc = userCouponService.getByCategoryAndUser(cmap);
		Coupon c = null;
		if(uc!=null){
			c = couponRepository.get(uc.getCouponId());
			if(first){
				c.setBonus(ArithHelper.add(c.getBonus(),activity.getRelayRaceActivity().getFirstBonus()));
			}else{
				c.setBonus(ArithHelper.add(c.getBonus(),activity.getRelayRaceActivity().getOtherBonus()));
			}
			couponRepository.update(c);
		}else{
			c = new Coupon();
			if(first){
				c.setBonus(activity.getRelayRaceActivity().getFirstBonus());
			}else{
				c.setBonus(activity.getRelayRaceActivity().getOtherBonus());
			}
			c.setExpiryDay(activity.getRelayRaceActivity().getInviteExpiryDay());
			c.setType(11);
			c.setActivityId(relayMemberWithParent.getActivityId());
			c = couponRepository.save(c);
			UserCoupon entity = new UserCoupon();
			entity.setCouponId(c.getId());
			entity.setCreateTime(System.currentTimeMillis());
			entity.setDate(DateUtil.currentDateForDay());
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DATE, c.getExpiryDay());
			entity.setExpiryTime(calendar.getTime().getTime());
			entity.setStatus(0);
			entity.setUpdateTime(System.currentTimeMillis());
			entity.setUserId(relayMemberWithParent.getUserId());
			entity.setCategory(3);
			userCouponService.save(entity);
		}
		
	}

	@Override
	public int pay(RelayMember relayMember) throws SoaException {
		ActivityWithRelayRaceActivity activity = activityRepository.getActivityWithRelayRaceActivityById(relayMember.getActivityId());
		WalletDetail walletDetail = new WalletDetail();
		walletDetail.setCreateTime(System.currentTimeMillis());
		walletDetail.setDate(DateUtil.currentDateForDay());
		if(relayMember.getLevel().intValue()==1&&relayMember.getParentId().intValue()==0){
		walletDetail.setMoney(activity.getRelayRaceActivity().getFirstFee());
		}else{
			walletDetail.setMoney(activity.getRelayRaceActivity().getOtherFee());
		}
		walletDetail.setType(8);
		walletDetail.setUserId(relayMember.getUserId());
		
		
		Wallet wallet = walletRepository.getByUserId(relayMember.getUserId());
		if(wallet.getTotalAsset()<walletDetail.getMoney()){
			return -1;
		}
		wallet.setTotalAsset(ArithHelper.sub(wallet.getTotalAsset(),walletDetail.getMoney()));
		if(wallet.getTotalAsset()<wallet.getPrincipal()){
			wallet.setPrincipal(wallet.getTotalAsset());
		}
		walletDetail.setResult(wallet.getTotalAsset());
		walletDetailRepository.save(walletDetail);
		walletRepository.update(wallet);
		getMedal(activity,relayMember);
		updateStatusAndGetCoupon(relayMember,activity);
		return 0;
	}

	private void getMedal(ActivityWithRelayRaceActivity activity,RelayMember relayMember) {
		/*Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", relayMember.getUserId());
		if(relayMember.getLevel().intValue()==1&&relayMember.getParentId().intValue()==0){
			map.put("type",0);
			map.put("category",2);
		}else{
			map.put("type",1);
			map.put("category",2);
		}
		
		UserMedal userMedal = userMedalRepository.getByUserIdAndTypeCategory(map);
		if(userMedal==null){*/
			UserMedal um = new UserMedal();
			um.setCreateTime(System.currentTimeMillis());
			um.setUserId(relayMember.getUserId());
			if(relayMember.getLevel().intValue()==1&&relayMember.getParentId().intValue()==0){
				um.setMedalId(activity.getRelayRaceActivity().getTeamLeaderMedal());
			}else{
				um.setMedalId(activity.getRelayRaceActivity().getActivityMedal());
			}
			userMedalRepository.save(um);
		//}
		
		
	}

	private void updateStatusAndGetCoupon(RelayMember relayMember, ActivityWithRelayRaceActivity activity)
			throws SoaException {
		if(relayMember.getPayStatus().intValue()==0){
			
			if(relayMember.getLevel().intValue()==1&&relayMember.getParentId().intValue()==0){
				RelayTeam relayTeam = relayTeamRepository.get(relayMember.getTeamId());
				relayTeam.setStatus(1);
				relayTeamRepository.update(relayTeam);
				
				
			}else{
				RelayMemberWithParent relayMemberWithParent = repository.getParents(relayMember.getParentId());
				getCoupon(relayMemberWithParent,true);
				if(relayMemberWithParent.getParent()!=null){
					int count = 0;
					getCouponForOther(relayMemberWithParent.getParent(),count,activity.getRelayRaceActivity().getLevelLimit());
				}
				
			}
			relayMember.setPayStatus(1);
			relayMember.setStatus(1);
			repository.update(relayMember);
		}
	}

	@Override
	public RelayMember getByTeamIdAndUserId(Long teamId, Long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("teamId", teamId);
		map.put("userId", userId);
		return repository.getByTeamIdAndUserId(map);
	}
}


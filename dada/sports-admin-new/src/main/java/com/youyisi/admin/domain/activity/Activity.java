package com.youyisi.admin.domain.activity;

import java.util.List;

import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.hugthigh.HugThighActivity;
import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivity;
import com.youyisi.admin.domain.relay.RelayLine;
import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.admin.domain.snatch.SnatchActivity;
import com.youyisi.admin.domain.snatch.SnatchFee;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-07-18
 */

public class Activity extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 795178552664092711L;
	private String title; //
	private String cover; //
	private Long hot; //
	private Integer type; //
	private Long beginTime; //
	private Long endTime; //
	private Long date; //
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //

	/** 抱大腿 **/
	private HugThighActivity hugThighActivity;
	private List<Coupon> couponsList;

	/** 邀请注册 **/
	private InviteFriendActivity inviteFriendActivity;
	/** 团队接力 **/
	private RelayRaceActivity relayRaceActivity;
	/** 团队接力 路线 **/
	private List<RelayLine> relayLineList;

	/** 一元夺宝 **/
	private SnatchActivity snatchActivity;
	/** 一元夺宝 支付次数 **/
	private List<SnatchFee> snatchFeeList;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCover() {
		return cover;
	}

	public void setHot(Long hot) {
		this.hot = hot;
	}

	public Long getHot() {
		return hot;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public HugThighActivity getHugThighActivity() {
		return hugThighActivity;
	}

	public void setHugThighActivity(HugThighActivity hugThighActivity) {
		this.hugThighActivity = hugThighActivity;
	}

	public List<Coupon> getCouponsList() {
		return couponsList;
	}

	public void setCouponsList(List<Coupon> couponsList) {
		this.couponsList = couponsList;
	}

	public InviteFriendActivity getInviteFriendActivity() {
		return inviteFriendActivity;
	}

	public void setInviteFriendActivity(InviteFriendActivity inviteFriendActivity) {
		this.inviteFriendActivity = inviteFriendActivity;
	}

	public RelayRaceActivity getRelayRaceActivity() {
		return relayRaceActivity;
	}

	public void setRelayRaceActivity(RelayRaceActivity relayRaceActivity) {
		this.relayRaceActivity = relayRaceActivity;
	}

	public SnatchActivity getSnatchActivity() {
		return snatchActivity;
	}

	public void setSnatchActivity(SnatchActivity snatchActivity) {
		this.snatchActivity = snatchActivity;
	}

	public List<SnatchFee> getSnatchFeeList() {
		return snatchFeeList;
	}

	public void setSnatchFeeList(List<SnatchFee> snatchFeeList) {
		this.snatchFeeList = snatchFeeList;
	}

	public List<RelayLine> getRelayLineList() {
		return relayLineList;
	}

	public void setRelayLineList(List<RelayLine> relayLineList) {
		this.relayLineList = relayLineList;
	}

	@Override
	public String toString() {
		return "Activity [title=" + title + ", cover=" + cover + ", hot=" + hot + ", type=" + type + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", date=" + date + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", hugThighActivity=" + hugThighActivity
				+ ", couponsList=" + couponsList + ", inviteFriendActivity=" + inviteFriendActivity + "]";
	}

}

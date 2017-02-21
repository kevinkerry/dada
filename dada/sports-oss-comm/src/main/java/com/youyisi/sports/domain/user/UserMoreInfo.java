package com.youyisi.sports.domain.user;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.goldbean.GoldBeanMore;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.wallet.Wallet;

/**
 * @author shuye
 * @time 2016-05-10
 */

/** 排除以下字段 不返回为Json **/
public class UserMoreInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9067720393446189950L;

	private String username; //
	private String usercode; //
	private String password; //
	private String payPassword; //
	private String nickname; //
	private String headPortrait; //
	private Integer sex; //
	private Integer age; //
	private Integer height; //
	private Double weight; //
	private String mobile; //
	private String city; //
	private String province; //
	private String birthday; //
	private String phoneModel;
	private Integer userType; //
	private Integer clientType; //
	private String clientId; //
	private Long registerTime; //
	private Long createTime; //
	private Long updateTime; //
	private Integer status; //
	private Integer bigDealsRemind;
	private Integer performanceRemind;
	private Integer praiseRemind;
	private Integer cheerRemind;
	private Integer voiceRemind;
	private Integer activityInviteRemind;
	private Double indoorRunning;
	private Double currentWithdraw;
	private Double basisWealth;
	private String channel;
	private String recommendCode; //
	/** 附加字段 **/
	private AnnualYield annualYield;

	/** 是否达到今日最大公里 **/
	private Boolean distanceMax = false;
	/** 是否达到今日最大步数 **/
	private Boolean stepMax = false;
	/** 附加字段 **/
	private Wallet wallet;
	
	private GoldBeanMore goldBean;
	/** 附加字段 **/
	private Step step;
	/** 附加字段 **/
	private Distance distance;
	
	/** 附加字段 **/
	private Integer yestodayStep;
	/** 附加字段 **/
	private Double yestodayDistance;
	
	private String myNumber;
	
	/** 附加字段 **/
	private ExperienceAccount experienceAccount;
	/** 预计财富 **/
	private Double predictFortune;
	/** 能否领取体验金 false不能 true能 **/
	private Boolean isExperienceAccount = false;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return sex;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeight() {
		return height;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWeight() {
		return weight;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

	public Long getRegisterTime() {
		return registerTime;
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

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public AnnualYield getAnnualYield() {
		return annualYield;
	}

	public void setAnnualYield(AnnualYield annualYield) {
		this.annualYield = annualYield;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Boolean getDistanceMax() {
		return distanceMax;
	}

	public void setDistanceMax(Boolean distanceMax) {
		this.distanceMax = distanceMax;
	}

	public Boolean getStepMax() {
		return stepMax;
	}

	public void setStepMax(Boolean stepMax) {
		this.stepMax = stepMax;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public ExperienceAccount getExperienceAccount() {
		return experienceAccount;
	}

	public void setExperienceAccount(ExperienceAccount experienceAccount) {
		this.experienceAccount = experienceAccount;
	}

	public Double getPredictFortune() {
		return predictFortune;
	}

	public void setPredictFortune(Double predictFortune) {
		this.predictFortune = predictFortune;
	}

	public Boolean getIsExperienceAccount() {
		return isExperienceAccount;
	}

	public void setIsExperienceAccount(Boolean isExperienceAccount) {
		this.isExperienceAccount = isExperienceAccount;
	}

	@Override
	public String toString() {
		return "UserMoreInfo [username=" + username + ", password=" + password + ", payPassword=" + payPassword
				+ ", nickname=" + nickname + ", headPortrait=" + headPortrait + ", sex=" + sex + ", age=" + age
				+ ", height=" + height + ", weight=" + weight + ", mobile=" + mobile + ", city=" + city + ", province="
				+ province + ", birthday=" + birthday + ", userType=" + userType + ", clientType=" + clientType
				+ ", clientId=" + clientId + ", registerTime=" + registerTime + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", status=" + status + ", annualYield=" + annualYield
				+ ", distanceMax=" + distanceMax + ", stepMax=" + stepMax + ", wallet=" + wallet + ", step=" + step
				+ ", distance=" + distance + ", experienceAccount=" + experienceAccount + "]";
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public Integer getBigDealsRemind() {
		return bigDealsRemind;
	}

	public void setBigDealsRemind(Integer bigDealsRemind) {
		this.bigDealsRemind = bigDealsRemind;
	}

	public Integer getPerformanceRemind() {
		return performanceRemind;
	}

	public void setPerformanceRemind(Integer performanceRemind) {
		this.performanceRemind = performanceRemind;
	}

	public Double getIndoorRunning() {
		return indoorRunning;
	}

	public void setIndoorRunning(Double indoorRunning) {
		this.indoorRunning = indoorRunning;
	}

	public Double getCurrentWithdraw() {
		return currentWithdraw;
	}

	public void setCurrentWithdraw(Double currentWithdraw) {
		this.currentWithdraw = currentWithdraw;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getRecommendCode() {
		return recommendCode;
	}

	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}

	public Integer getPraiseRemind() {
		return praiseRemind;
	}

	public void setPraiseRemind(Integer praiseRemind) {
		this.praiseRemind = praiseRemind;
	}

	public Integer getCheerRemind() {
		return cheerRemind;
	}

	public void setCheerRemind(Integer cheerRemind) {
		this.cheerRemind = cheerRemind;
	}

	public Integer getVoiceRemind() {
		return voiceRemind;
	}

	public void setVoiceRemind(Integer voiceRemind) {
		this.voiceRemind = voiceRemind;
	}

	public Integer getActivityInviteRemind() {
		return activityInviteRemind;
	}

	public void setActivityInviteRemind(Integer activityInviteRemind) {
		this.activityInviteRemind = activityInviteRemind;
	}

	public Integer getYestodayStep() {
		return yestodayStep;
	}

	public void setYestodayStep(Integer yestodayStep) {
		this.yestodayStep = yestodayStep;
		setMyNumber(MyNumber());
	}

	private String MyNumber() {
		int first = 0;
		if(getYestodayDistance()>0.0){
			first = getYestodayDistance().intValue();
		}
		String _first = first+"";
		_first = _first.substring(_first.length()-1);
		String other = getYestodayStep().toString();
		if(other.length()>=4){
			other = other.substring(other.length()-4);
		}else{
			other = String.format("%04d", getYestodayStep()); 
		}
		return _first+other;
	}

	public Double getYestodayDistance() {
		return yestodayDistance;
	}

	public void setYestodayDistance(Double yestodayDistance) {
		this.yestodayDistance = yestodayDistance;
	}

	public String getMyNumber() {
		
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public GoldBeanMore getGoldBean() {
		return goldBean;
	}

	public void setGoldBean(GoldBeanMore goldBean) {
		this.goldBean = goldBean;
	}

	public Double getBasisWealth() {
		return basisWealth;
	}

	public void setBasisWealth(Double basisWealth) {
		this.basisWealth = basisWealth;
	}
	

}

package com.youyisi.admin.domain.user;

import com.youyisi.admin.domain.experience.ExperienceAccount;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-16
 */

public class User extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5634969437255369752L;
	private String username; //
	private String usercode;
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
	private Integer userType; //
	private Integer clientType; //
	private String clientId; //
	private Long registerTime; //
	private Long createTime; //
	private Long updateTime; //
	private Integer status; //
	private String phoneModel;

	private Integer bigDealsRemind;
	private Integer performanceRemind;
	private Double indoorRunning;
	private String channel;

	/** 附加字段 体验金 **/
	private ExperienceAccount experienceAccount;

	/** 附加字段 总资产 **/
	private Double totalAsset = 0.0; //
	/** 附加字段 本金 **/
	private Double principal = 0.0; //
	/** 附加字段 累计收益 **/
	private Double income = 0.0; //

	/** 附加字段 个推ID数量 **/
	private Integer clientIdNum; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public ExperienceAccount getExperienceAccount() {
		return experienceAccount;
	}

	public void setExperienceAccount(ExperienceAccount experienceAccount) {
		this.experienceAccount = experienceAccount;
	}

	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
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

	public Integer getClientIdNum() {
		return clientIdNum;
	}

	public void setClientIdNum(Integer clientIdNum) {
		this.clientIdNum = clientIdNum;
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
}

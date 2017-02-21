package com.youyisi.admin.domain.member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.youyisi.admin.domain.member.memberimage.MemberImage;
import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public class Member implements Serializable{
    
	private static final long serialVersionUID = 2090669947728933527L;
	
	@Id
    private Long memberId; //会员ID
	private String memberCode; //会员编码
	private Long userId; //用户ID
	private String userCode; //用户编码
	private String memberName; //会员名称
	private String memberAlias; //会员昵称
	private String sex; //会员性别 男  女
	private Date birthday; //出生年月
	private String memberLogo; //用户图像
	private String backdropImg; //用户背景图片路径
	private String city; //所在城市
	private String mobile; //移动电话
	private String email; //邮件地址
	private String memberState; //会员状态
	private String memberLevel; //会员等级
	private String constellation; //会员星座
	private String maritalStatus; //感情状态 1 未婚 2 已婚 3离异 4丧偶 5保密
	private String hometown; //会员籍贯
	private String occupation; //会员职业
	private String livingScope; //活动范围
	private String memberDesc; //个人说明
	private String school; //毕业学校
	private String workunit; //工作单位
	private String profession; //从事工作
	private String degree; //学历
	private String sportCategory01; //兴趣爱好01
	private String sportCategory02; //兴趣爱好02
	private String sportCategory03; //兴趣爱好03
	private String sportCategory04; //兴趣爱好04
	private String sportCategory05; //兴趣爱好05
	private String recommendedCode; //推荐人编码
	private String wechat; //微信号
	private String comments; //备注
	private Date createdTime; //创建时间
	private String modifier; //修改人
	private Date updatedTime; //修改时间
	private Integer recommendOrder; //会员推荐顺序
	private Integer recommendFlag; //会员推荐 0不推荐 1推荐
	private Integer memberHeight; //会员身高
	private Double memberWeight; //会员体重
	private Member recommendedMember;//推荐人
	private List<MemberImage> memberImages;//会员图片
	
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getMemberCode() {
        return memberCode;
    }
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getMemberAlias() {
        return memberAlias;
    }
    public void setMemberAlias(String memberAlias) {
        this.memberAlias = memberAlias;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getMemberLogo() {
        return memberLogo;
    }
    public void setMemberLogo(String memberLogo) {
        this.memberLogo = memberLogo;
    }
    public String getBackdropImg() {
        return backdropImg;
    }
    public void setBackdropImg(String backdropImg) {
        this.backdropImg = backdropImg;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMemberState() {
        return memberState;
    }
    public void setMemberState(String memberState) {
        this.memberState = memberState;
    }
    public String getMemberLevel() {
        return memberLevel;
    }
    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }
    public String getConstellation() {
        return constellation;
    }
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getHometown() {
        return hometown;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public String getOccupation() {
        return occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getLivingScope() {
        return livingScope;
    }
    public void setLivingScope(String livingScope) {
        this.livingScope = livingScope;
    }
    public String getMemberDesc() {
        return memberDesc;
    }
    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getWorkunit() {
        return workunit;
    }
    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getSportCategory01() {
        return sportCategory01;
    }
    public void setSportCategory01(String sportCategory01) {
        this.sportCategory01 = sportCategory01;
    }
    public String getSportCategory02() {
        return sportCategory02;
    }
    public void setSportCategory02(String sportCategory02) {
        this.sportCategory02 = sportCategory02;
    }
    public String getSportCategory03() {
        return sportCategory03;
    }
    public void setSportCategory03(String sportCategory03) {
        this.sportCategory03 = sportCategory03;
    }
    public String getSportCategory04() {
        return sportCategory04;
    }
    public void setSportCategory04(String sportCategory04) {
        this.sportCategory04 = sportCategory04;
    }
    public String getSportCategory05() {
        return sportCategory05;
    }
    public void setSportCategory05(String sportCategory05) {
        this.sportCategory05 = sportCategory05;
    }
    public String getRecommendedCode() {
        return recommendedCode;
    }
    public void setRecommendedCode(String recommendedCode) {
        this.recommendedCode = recommendedCode;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public String getModifier() {
        return modifier;
    }
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    public Integer getRecommendOrder() {
        return recommendOrder;
    }
    public void setRecommendOrder(Integer recommendOrder) {
        this.recommendOrder = recommendOrder;
    }
    public Integer getRecommendFlag() {
        return recommendFlag;
    }
    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }
    public Integer getMemberHeight() {
        return memberHeight;
    }
    public void setMemberHeight(Integer memberHeight) {
        this.memberHeight = memberHeight;
    }
    public Double getMemberWeight() {
        return memberWeight;
    }
    public void setMemberWeight(Double memberWeight) {
        this.memberWeight = memberWeight;
    }
    public Member getRecommendedMember() {
        return recommendedMember;
    }
    public void setRecommendedMember(Member recommendedMember) {
        this.recommendedMember = recommendedMember;
    }
    public List<MemberImage> getMemberImages() {
        return memberImages;
    }
    public void setMemberImages(List<MemberImage> memberImages) {
        this.memberImages = memberImages;
    }
}


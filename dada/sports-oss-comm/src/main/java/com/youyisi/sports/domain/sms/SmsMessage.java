package com.youyisi.sports.domain.sms;

import java.util.Date;
/**
 * 发送短信webservice的Bean
 * @author 
 *
 */
public class SmsMessage implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6510524523149302500L;

	//短信主键.msgId
	private long id;
	
	//seqId
	private long seqId;
	
	//主题.
	private String subject;
	
	//手机号码,用英文";"分隔多个手机号码.
	private String telephone;
	
	//短信内容.
	private String body;
	
	//发送结果.
	private int resultFlag;

	//创建人.
	private String creator;
	
	private Date createDate;
	/**
	 * 发送短信返回的流水号
	 */
	private String resultNum;
	

	public String getResultNum() {
		return resultNum;
	}

	public void setResultNum(String resultNum) {
		this.resultNum = resultNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(int resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public long getSeqId() {
		return seqId;
	}

	public void setSeqId(long seqId) {
		this.seqId = seqId;
	}
}

package com.youyisi.vote.domain.weixin;

/**
 * 娑堟伅鍩虹被锛堝叕浼楀笎鍙� -> 鏅�氱敤鎴凤級
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class BaseMessage {
	// 鎺ユ敹鏂瑰笎鍙凤紙鏀跺埌鐨凮penID锛�
	private String ToUserName;
	// 寮�鍙戣�呭井淇″彿
	private String FromUserName;
	// 娑堟伅鍒涘缓鏃堕棿 锛堟暣鍨嬶級
	private long CreateTime;
	// 娑堟伅绫诲瀷锛坱ext/music/news锛�
	private String MsgType;
	// 浣�0x0001琚爣蹇楁椂锛屾槦鏍囧垰鏀跺埌鐨勬秷鎭�
	private int FuncFlag;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}
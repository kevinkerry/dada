package com.youyisi.admin.infrastructure.constant;

public class Constant {
	public static final String JMS_QUEUE_TOPIC = "DaDa.Queue.Topic";
	public static final String JMS_QUEUE_CLUB = "DaDa.Queue.Club";
	public static final String JMS_QUEUE_SHOW = "DaDa.Queue.Show";
	public static final String JMS_QUEUE_MEMBER = "DaDa.Queue.Member";
	public static final String JMS_QUEUE_VENUES = "DaDa.Queue.Venues";
	public static final String OPENFIRE_SERVICE_PATH = "http://10.1.80.200:9090";

	/** 聊天记录 **/
	public static final String CHAT_MESSAGE = "dada:chatmessage:";
	/** 聊天列表 **/
	public static final String CHAT_LIST = "dada:chatlist:";
	/** 新消息条数 **/
	public static final String CHAT_MESSAGE_NUM = "dada:chatmessagenum:";

	public static final String TOPICADDLOG = "dada:topicaddlog:";

	public static final String WITHDRAW_KEY = "dada:withdraw:withdraworder";
	public static final String WITHDRAW_BATCH_KEY = "dada:withdraw:withdrawbatch";

	/** 推送系统消息 **/
	public static final String PUSH_SYS_MESSAGE = "sys_message";

	/** 彩票下注推送 **/
	public static final String PUSH_SYS_LOTTERY = "sys_lottery";

	/** 彩票中奖推送 **/
	public static final String PUSH_SYS_WINNING = "sys_winning";

	/** 彩票提示过期推送 **/
	public static final String PUSH_SYS_OVERDUE = "sys_overdue";

}
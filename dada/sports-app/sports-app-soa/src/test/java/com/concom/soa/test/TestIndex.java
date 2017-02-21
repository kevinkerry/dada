package com.concom.soa.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 * 
 * @author hetao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:soa-servlet.xml" })
public class TestIndex {

	/*@Resource
	ActiveMqSender activeMqSender;
	@Resource
	ActivityMapper activityMapper;

	@Resource
	ClubMapper clubMapper;

	@Resource
	ShowMapper showMapper;

	@Resource
	TopicMapper topicMapper;

	@Resource
	MemberMapper memberMapper;

	@Test
	public void CreateIndex() {
		// 活动索引
		List<Long> activityId = activityMapper.getAllPrimaryKey();
		for (Long aid : activityId) {
			activeMqSender.send(aid.toString(), null);
		}

		// 俱乐部索引
		List<Long> clubId = clubMapper.getAllPrimaryKey();
		for (Long cid : clubId) {
			activeMqSender.send(cid.toString(), Constants.JMS_QUEUE_CLUB);
		}

		// 哒人秀索引
		List<Long> showId = showMapper.getAllPrimaryKey();
		for (Long sid : showId) {
			activeMqSender.send(sid.toString(), Constants.JMS_QUEUE_SHOW);
		}

		// 约运动索引
		List<Long> topicId = topicMapper.getAllPrimaryKey();
		for (Long tid : topicId) {
			activeMqSender.send(tid.toString(), Constants.JMS_QUEUE_TOPIC);
		}

		// 会员索引
		List<Long> userId = memberMapper.getAllUserId();
		for (Long mid : userId) {
			activeMqSender.send(mid.toString(), Constants.JMS_QUEUE_MEMBER);
		}
	}

	@Test
	public void test1() {
		List<Long> activityId = activityMapper.getAllPrimaryKey();
		System.out.println(activityId);
	}*/

}

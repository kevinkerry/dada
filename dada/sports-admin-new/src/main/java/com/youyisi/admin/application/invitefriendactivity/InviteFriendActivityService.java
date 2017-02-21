package com.youyisi.admin.application.invitefriendactivity;

import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivity;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-11
 */
public interface InviteFriendActivityService {

	InviteFriendActivity save(InviteFriendActivity entity);

	InviteFriendActivity get(Long id);

	Integer delete(InviteFriendActivity entity);

	Integer update(InviteFriendActivity entity);

	Page<InviteFriendActivity> queryPage(Page<InviteFriendActivity> page);

	Integer delByActivityId(Long activityId);

	InviteFriendActivity getByActivityId(Long activityId);

}

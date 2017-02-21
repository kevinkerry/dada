package com.youyisi.app.soa.infrastructure.persist.user;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserLessInfo;
import com.youyisi.sports.domain.user.UserMoreInfo;

/**
 * @author shuye
 * @time 2016-05-10
 */
public interface UserRepository extends MybatisRepository<Long, User> {

	User getByUserName(String userName);

	UserMoreInfo login(String userName);

	UserMoreInfo saveUserMoreInfo(UserMoreInfo userMoreInfo);

	UserMoreInfo updateUserMoreInfo(UserMoreInfo userMoreInfo);

	User getByUserNameOrMobile(String userName);

	User getByClientId(String clientId);

	User getByUsercode(String recommendCode);

	Page<UserLessInfo> queryPageForPush(Page<UserLessInfo> page);
}

package com.youyisi.app.soa.remote.user;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserLessInfo;
import com.youyisi.sports.domain.user.UserMoreInfo;

/**
 * @author shuye
 * @time 2016-05-10
 */
public interface UserServiceRemote extends BaseServiceInterface<Long, User> {

	User getByUserName(String userName) throws SoaException;

	UserMoreInfo login(UserMoreInfo user) throws SoaException;

	User getByUserNameOrMobile(String userName) throws SoaException;

	User bindingMobile(User user) throws SoaException;

	UserMoreInfo getMyUserInfo(User user) throws SoaException;

	User getByClientId(String clientId)throws SoaException;

	User getByUsercode(String recommendCode)throws SoaException;

	Page<UserLessInfo> queryPageForPush(Page<UserLessInfo> page)throws SoaException;

}

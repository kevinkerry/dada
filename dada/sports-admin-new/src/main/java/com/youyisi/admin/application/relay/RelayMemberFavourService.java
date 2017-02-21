package com.youyisi.admin.application.relay;

import com.youyisi.admin.domain.relay.RelayMemberFavour;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
public interface RelayMemberFavourService {

	RelayMemberFavour save(RelayMemberFavour entity);

	RelayMemberFavour get(Long id);

	Integer delete(RelayMemberFavour entity);

	Integer update(RelayMemberFavour entity);

	Page<RelayMemberFavour> queryPage(Page<RelayMemberFavour> page);

	/**
	 * 统计激励和赞的次数
	 * 
	 * @param relayMemberId
	 * @param type
	 *            1:点赞，2：激励
	 * @return Integer
	 */
	Integer countNum(Long relayMemberId, Integer type);

}

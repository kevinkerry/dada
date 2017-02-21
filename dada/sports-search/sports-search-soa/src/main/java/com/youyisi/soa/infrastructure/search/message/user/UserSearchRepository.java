package com.youyisi.soa.infrastructure.search.message.user;

import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.youyisi.lang.EsPage;
import com.youyisi.lang.Page;
import com.youyisi.soa.infrastructure.persist.user.UserRepository;
import com.youyisi.soa.infrastructure.search.support.EsOperations;
import com.youyisi.sports.domain.user.User;

@Repository
public class UserSearchRepository extends EsOperations<Long, User> {

	@Autowired
	private UserRepository userRepository;
	

	public void update(User activity) {
		super.update(activity);
	}

	public Page<User> search(Page<User> page) {

		String keyword = (String) page.getParams().get("keyword");
		EsPage<User> espage = new EsPage<User>();
		try {
			SearchRequestBuilder searchRequestBuilder = client
					.prepareSearch(getIndexName(User.class))
					.setQuery(getQuery(page)).setPreference("_primary_first")
					.addSort("recommendFlag", SortOrder.DESC)
					.addSort("orders", SortOrder.DESC)
					.addSort("startApplyTime", SortOrder.DESC)
					.setFrom(page.getFirst() - 1).setSize(page.getPageSize());
			if (StringUtils.isNotBlank(keyword)) {
				searchRequestBuilder.addHighlightedField("activityTitle")
						.setHighlighterPreTags("<font color='red'>")
						.setHighlighterPostTags("</font>");
			}
			addFilter(page, searchRequestBuilder);
			// addAggregation(AGGREGATION_FIELD,page, searchRequestBuilder);
			SearchResponse response = searchRequestBuilder.execute()
					.actionGet();
			if (isEmptyResult(response)) {
				return espage.setResult(Collections.<User> emptyList());
			}
			espage = parseResult(response, page);
			// espage.setFacets(parseAggregation(response.getAggregations()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espage;
	}

	private QueryBuilder getQuery(Page<User> page) {
		// TODO Auto-generated method stub
		return null;
	}


}

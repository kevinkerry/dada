package com.youyisi.soa.infrastructure.search.support;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.inject.internal.Nullable;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.youyisi.lang.EsPage;
import com.youyisi.lang.Page;
import com.youyisi.lang.annotation.Document;
import com.youyisi.lang.annotation.Id;
import com.youyisi.lang.helper.GodHands;
import com.youyisi.lang.helper.JsonHelper;
import com.youyisi.soa.infrastructure.persist.exception.DatabaseException;
import com.youyisi.sports.domain.RangeParam;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * 
 * @param <T>
 */
public abstract class EsOperations<ID extends Serializable, T> {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	protected Client client;

	protected Class<T> entityClass;

	protected String idName;

	@SuppressWarnings("unchecked")
	public EsOperations() {
		entityClass = (Class<T>) GodHands.genericsTypes(getClass())[1];
		Field[] fields = GodHands.getAccessibleFields(entityClass);
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)
					|| StringUtils.equalsIgnoreCase("id", field.getName())) {
				idName = field.getName();
			}
		}
	}

	/**
	 * get the result by id
	 * 
	 * @param id
	 * @return
	 */
	public T get(ID id) {
		Validate.notNull(id, "id must not be null");
		try {
			GetResponse response = client
					.prepareGet(getIndexName(entityClass),
							getType(entityClass), stringIdRepresentation(id))
					.execute().actionGet();
			return JsonHelper.fromJsonString(response.getSourceAsString(),
					entityClass);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * update the entity to database
	 * 
	 * @param entity
	 * @return
	 */
	public void updateBatch(List<T> entities) {
		Validate.notNull(entities, "entity must not be null");
		try {
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (T entity : entities) {
				bulkRequest.add(client.prepareIndex(getIndexName(entityClass),
						getType(entityClass),
						GodHands.getFieldValue(entity, idName).toString())
						.setSource(JsonHelper.toJsonString(entity)));
			}
			bulkRequest.execute().actionGet();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * update the entity to database
	 * 
	 * @param entity
	 * @return
	 */
	public void update(T entity) {
		Validate.notNull(entity, "entity must not be null");
		try {
			client.prepareIndex(getIndexName(entityClass),
					getType(entityClass),
					GodHands.getFieldValue(entity, idName).toString())
					.setSource(JsonHelper.toJsonString(entity)).execute()
					.actionGet();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public void delete(ID id) {
		Validate.notNull(id, "id must not be null");
		client.prepareDelete(getIndexName(entityClass), getType(entityClass),
				stringIdRepresentation(id)).execute().actionGet();
	}

	protected EsPage<T> parseResult(SearchResponse response, Page<?> page) {
		EsPage<T> facetPage = new EsPage<T>();
		SearchHits hits = response.getHits();
		long totalHits = hits.totalHits();
		facetPage.setTotalCount((int) totalHits);
		List<T> results = new ArrayList<T>();
		for (SearchHit hit : hits) {
			if (hit != null) {
				T entity = JsonHelper.fromJsonString(hit.sourceAsString(),
						entityClass);
				handleHighlightFields(hit.getHighlightFields(), entity);
				results.add(entity);
			}
		}
		facetPage.setResult(results);
		// facetPage.setFacets(parseFacet(response.getFacets()));//Determined by
		// the user
		if (null != page) {
			facetPage.setPageSize(page.getPageSize()).setCurrentPage(
					page.getCurrentPage());
		}
		return facetPage;
	}

	protected void addFilter(Page<T> page,SearchRequestBuilder searchRequestBuilder) {
		AndFilterBuilder andFilterBuilder = new AndFilterBuilder();
		boolean isNotBlank = termFilter(page, andFilterBuilder);

		isNotBlank = geoFilter(page, searchRequestBuilder, andFilterBuilder,isNotBlank);
		isNotBlank = termOrFilter(page, andFilterBuilder,isNotBlank);
		isNotBlank = geoDistanceFilter(page,andFilterBuilder,isNotBlank);
		isNotBlank = gtFilter(page,andFilterBuilder,isNotBlank);
		isNotBlank = notFilter(page,andFilterBuilder,isNotBlank);
		@SuppressWarnings("unchecked")
		Map<String, RangeParam> rangeFilter =   (Map<String, RangeParam>) page.getParams().get("rangeFilter");
		if(rangeFilter!=null){
			for (String key : rangeFilter.keySet()) {
				if (rangeFilter.get(key)!=null) {
					RangeParam rangeParam =  rangeFilter.get(key);
					isNotBlank = true;
					if(key.equalsIgnoreCase("createdTime")||key.contains("birthday")||key.equalsIgnoreCase("updatedTime")){
						andFilterBuilder.add(FilterBuilders.rangeFilter(key).from(DateUtil.strToDate((String) rangeParam.getFrom())).to(DateUtil.strToDate((String) rangeParam.getTo())));

					}else{
						
						andFilterBuilder.add(FilterBuilders.rangeFilter(key).from( rangeParam.getFrom()).to( rangeParam.getTo()));
					}
				}
			}
		}

		if (isNotBlank) {
			searchRequestBuilder.setPostFilter(andFilterBuilder);

		}

	}

	private boolean notFilter(Page<T> page, AndFilterBuilder andFilterBuilder,
			boolean isNotBlank) {
		Map<String, Object> gtFilter =   (Map<String, Object>) page.getParams().get("notFilter");
		if(gtFilter!=null){
			for (String key : gtFilter.keySet()) {
				if (gtFilter.get(key)!=null) {
					isNotBlank = true;
					andFilterBuilder.add(FilterBuilders.notFilter(FilterBuilders.termFilter(key,
							gtFilter.get(key))));
				}
			}
		}
		return isNotBlank;
	}

	private boolean termFilter(Page<T> page, AndFilterBuilder andFilterBuilder) {
		@SuppressWarnings("unchecked")
		Map<String, Object> filter = (Map<String, Object>) page.getParams().get("filter");
		boolean isNotBlank = false;
		if (filter != null) {
			for (String key : filter.keySet()) {
				if (filter.get(key)!=null) {
					isNotBlank = true;
					andFilterBuilder.add(FilterBuilders.termFilter(key,
							filter.get(key)));
				}
			}

		}
		return isNotBlank;
	}
	

	private boolean gtFilter(Page<T> page, AndFilterBuilder andFilterBuilder,boolean isNotBlank) {
		@SuppressWarnings("unchecked")
		Map<String, Object> gtFilter =   (Map<String, Object>) page.getParams().get("gtFilter");
		if(gtFilter!=null){
			for (String key : gtFilter.keySet()) {
				if (gtFilter.get(key)!=null) {
					isNotBlank = true;
					if(key.equalsIgnoreCase("createdTime")||key.contains("birthday")||key.equalsIgnoreCase("updatedTime")||key.equalsIgnoreCase("beginTime")){
						andFilterBuilder.add(FilterBuilders.rangeFilter(key).from(DateUtil.strToDate((String) gtFilter.get(key))));

					}else{
						
						andFilterBuilder.add(FilterBuilders.rangeFilter(key).from(gtFilter.get(key)));
					}
				}
			}
		}
		return isNotBlank;
	}
	
	private boolean termOrFilter(Page<T> page, AndFilterBuilder andFilterBuilder,boolean isNotBlank) {
		@SuppressWarnings("unchecked")
		Map<String, List<String>> filter = (Map<String, List<String>>) page.getParams().get("Orfilter");
		if (filter != null) {
			for (String key : filter.keySet()) {
				if (filter.get(key)!=null) {
					isNotBlank = true;
					List<String> list = (List<String>) filter.get(key);
					andFilterBuilder.add(FilterBuilders.termsFilter(key, list).execution("or"));
				}
			}

		}
		return isNotBlank;
	}

	private boolean geoFilter(Page<T> page,
			SearchRequestBuilder searchRequestBuilder,
			AndFilterBuilder andFilterBuilder, boolean isNotBlank) {
		@SuppressWarnings("unchecked")
		Map<String, Object> geo = (Map<String, Object>) page.getParams().get(
				"geoFilter");
		if (geo != null && geo.get("gpsX")!=null&& geo.get("gpsY")!=null) {
			Double lat = Double.parseDouble((String) geo.get("gpsX")) ;
			Double lon = Double.parseDouble((String) geo.get("gpsY"));
			if(lat!=null&&lon!=null){
				isNotBlank = true;
				FilterBuilder geofilter = FilterBuilders
						.geoDistanceFilter("location").point(lon, lat)//may api have a bug
						.distance(200, DistanceUnit.KILOMETERS)
						.optimizeBbox("memory").geoDistance(GeoDistance.SLOPPY_ARC);
				andFilterBuilder.add(geofilter);
				GeoDistanceSortBuilder sort = SortBuilders
						.geoDistanceSort("location").point(lon, lat)
						.order(SortOrder.ASC).unit(DistanceUnit.KILOMETERS);
				searchRequestBuilder.addSort(sort);
			}
			
			
		}
		return isNotBlank;
	}
	
	private boolean geoDistanceFilter(Page<T> page,
			AndFilterBuilder andFilterBuilder, boolean isNotBlank){
		Map<String, Object> geo = (Map<String, Object>) page.getParams().get(
				"geoDistanceFilter");
		
		if (geo != null && geo.get("gpsX")!=null&& geo.get("gpsY")!=null) {
			Double lat = Double.parseDouble((String) geo.get("gpsX")) ;
			Double lon = Double.parseDouble((String) geo.get("gpsY"));
			if(lat!=null&&lon!=null){
				String from = (String) geo.get("from");
				String to = (String) geo.get("to");
				isNotBlank = true;
				FilterBuilder geofilter = FilterBuilders
						.geoDistanceRangeFilter("location").point(lon, lat)//may api have a bug
						.from(from).to(to)
						.optimizeBbox("memory").geoDistance(GeoDistance.SLOPPY_ARC);
				andFilterBuilder.add(geofilter);
				/*GeoDistanceSortBuilder sort = SortBuilders
						.geoDistanceSort("location").point(lon, lat)
						.order(SortOrder.ASC).unit(DistanceUnit.KILOMETERS);
				searchRequestBuilder.addSort(sort);*/
			}
			
			
		}
		return isNotBlank;
	}

	protected void addGeoDistanceFilter(Page<T> page,
			SearchRequestBuilder searchRequestBuilder) {

	}

	@SuppressWarnings("unchecked")
	protected AndFilterBuilder getFilter(Page<T> page) {
		AndFilterBuilder andFilterBuilder = new AndFilterBuilder();
		Map<String, String> filter = (Map<String, String>) page.getParams()
				.get("filter");
		for (String key : filter.keySet()) {
			if (StringUtils.isNotBlank(filter.get(key))) {
				andFilterBuilder.add(FilterBuilders.termFilter(key,
						filter.get(key)));
			}
		}
		return andFilterBuilder;
	}

	protected boolean isEmptyResult(SearchResponse response) {
		if (response.getHits().getTotalHits() > 0) {
			return false;
		}
		return true;
	}

	protected void addAggregation(String[] aggregationFiledNames, Page<T> page,
			SearchRequestBuilder searchRequestBuilder) {
		for (String fieldName : aggregationFiledNames) {
			@SuppressWarnings("unchecked")
			Map<String, String> filter = (Map<String, String>) page.getParams()
					.get("filter");
			if (filter != null && StringUtils.isBlank(filter.get(fieldName))) {
				searchRequestBuilder.addAggregation(AggregationBuilders
						.terms(fieldName).field(fieldName)
						.size(Integer.MAX_VALUE));
			}
		}
	}

	protected Map<String, Map<String, Integer>> parseFacet(Facets facets) {
		Map<String, Map<String, Integer>> facetMap = new HashMap<String, Map<String, Integer>>();
		if (null != facets) {
			Map<String, Facet> facetsMap = facets.facetsAsMap();
			for (String key : facetsMap.keySet()) {
				TermsFacet f = (TermsFacet) facetsMap.get(key);
				Map<String, Integer> terms = new HashMap<String, Integer>();
				for (TermsFacet.Entry entry : f) {
					terms.put(entry.getTerm().string(), entry.getCount());
				}
				facetMap.put(key, terms);
			}
		}
		return facetMap;
	}

	protected Map<String, Map<String, Integer>> parseAggregation(
			Aggregations aggregations) {
		Map<String, Map<String, Integer>> facetMap = new HashMap<String, Map<String, Integer>>();
		if (null != aggregations) {
			Map<String, Aggregation> aggregationMap = aggregations.asMap();
			for (String key : aggregationMap.keySet()) {
				Terms aggregationTerms = (Terms) aggregationMap.get(key);
				Map<String, Integer> terms = new HashMap<String, Integer>();
				for (Bucket b : aggregationTerms.getBuckets()) {
					terms.put(b.getKey(), (int) b.getDocCount());
				}
				facetMap.put(key, terms);
			}
		}
		return facetMap;
	}

	public List<String> suggest(String prefix, Integer size,
			@Nullable String type) {
		List<String> list = new ArrayList<String>();
		SearchRequestBuilder searchRequestBuilder = client
				.prepareSearch(getIndexName(entityClass));
		if (StringUtils.isNotBlank(type)) {
			searchRequestBuilder.setTypes(type);
		}
		SearchResponse suggestResponse = searchRequestBuilder
				.addSuggestion(
						new CompletionSuggestionBuilder("suggestions")
								.field("suggest").text(prefix).size(size))
				.execute().actionGet();
		for (Entry<? extends Option> e : suggestResponse.getSuggest()
				.getSuggestion("suggestions").getEntries()) {
			for (Option o : e.getOptions()) {
				list.add(o.getText().string());
			}
		}

		return list;
	}

	protected T handleHighlightFields(
			Map<String, HighlightField> highlightFields, T entity) {
		for (String key : highlightFields.keySet()) {
			String value = highlightFields.get(key).fragments()[0].string();
			GodHands.setFieldValue(entity, key, value);
		}
		return entity;
	}

	/**
	 * 将id转化为String类型，请确保该id唯一和返回非null
	 * 
	 * @param id
	 * @return
	 */
	protected String stringIdRepresentation(ID id) {
		if (null == id)
			return null;
		return String.valueOf(id);
	}

	/**
	 * @param clazz
	 * @return
	 */
	public String getIndexName(Class<?> clazz) {

		Document document = GodHands.getAccessibleAnnotation(clazz,
				Document.class);
		if (null == document) {
			return clazz.getSimpleName().toLowerCase();
		}
		return document.index();
	}

	/**
	 * @param clazz
	 * @return
	 */
	public String getType(Class<?> clazz) {
		Document document = GodHands.getAccessibleAnnotation(clazz,
				Document.class);
		if (null == document) {
			return clazz.getSimpleName().toLowerCase();
		}
		return document.type();
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

}
package com.youyisi.vote.infrastructure.cache.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.youyisi.lang.helper.DemonPredict;
import com.youyisi.lang.helper.GodHands;
import com.youyisi.lang.helper.JsonHelper;


/**
 * @author shuye
 * @time 2014-7-8
 */
public abstract class RedisOperations<K,V>{
	private Class<V> entityClass;

	@SuppressWarnings("unchecked")
	public RedisOperations() {
		entityClass = (Class<V>) GodHands.genericsTypes(getClass())[1];
	}
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	private final static Logger LOG = LoggerFactory.getLogger(RedisOperations.class);

	/**
	 * 从redis中得到的是一个json格式的字符串，转化为想要的实体
	 */
	public V get(K key) {
		DemonPredict.notNull(key, "key must not be null...");
		String json = redisTemplate.opsForValue().get(key);
		V entity = null;
		try {
			entity = JsonHelper.fromJsonString(json, entityClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(LOG.isDebugEnabled()){
			LOG.debug("get the entity is " + entity);
		}
		return entity;
	}
	
	
	public Map<Object, Object> hget(K key) {
		DemonPredict.notNull(key, "key must not be null...");
		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		redisTemplate.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		Map<Object, Object> map = redisTemplate.opsForHash().entries((String) key);
		return map;
	}
	
	/**
	 * 从redis中得到的是多个json格式的字符串，转化为想要的实体列表
	 */
	public List<V> getList(K pattern) {
		DemonPredict.notNull(pattern, "key must not be null...");
		List<V> entitys = new ArrayList<V>();
		Set<String> keys = redisTemplate.keys((String) pattern);
		List<String> values = redisTemplate.opsForValue().multiGet(keys);
		for(String v:values){
			V entity = null;
			try {
				entity = JsonHelper.fromJsonString(v, entityClass);
				entitys.add(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(LOG.isDebugEnabled()){
				LOG.debug("get the entity is " + entity);
			}
		}
		
		
		return entitys;
	}
	
	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到redis
	 * @param key
	 * @param entity
	 */
	public  void save(K key,V entity) {
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+entity+" to redis");
		}
		try {
			String json = JsonHelper.toJsonString(entity);
			redisTemplate.opsForValue().set((String) key,json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到redis
	 * @param key
	 * @param entity
	 */
	public  void lpush(String key,V entity) {
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+entity+" to redis");
		}
		try {
			String json = JsonHelper.toJsonString(entity);
			redisTemplate.opsForList().leftPush(key, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到redis
	 * @param key
	 * @param entity
	 */
	public  void lpush(String key,String entity) {
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+entity+" to redis");
		}
		try {
			redisTemplate.opsForList().leftPush(key, entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到redis
	 * @param key
	 * @param entity
	 */
	public  String rightPop(String key) {
		DemonPredict.notNull(key, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+key+" to redis");
		}
		try {
			return redisTemplate.opsForList().rightPop(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param autoRemoveTime
	 */
	public void save(K key,V entity,long autoRemoveTime){
		DemonPredict.notNull(entity, "entity must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin save the "+entity+" to redis");
		}
		try {
			redisTemplate.opsForValue().set((String) key, JsonHelper.toJsonString(entity), autoRemoveTime,TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	/**
	 * 根据标识，从redis中删除
	 */
	public void remove(K key) {
		DemonPredict.notNull(key, "key must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin remove the "+key+" from redis");
		}
		redisTemplate.delete((String) key);
	}
	
	/**
	 * 根据标识，从redis中删除
	 */
	public void removeByPattern(K pattern) {
		DemonPredict.notNull(pattern, "key must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("begin remove the "+pattern+" from redis");
		}
		Set<String> keys = redisTemplate.keys((String) pattern);
		redisTemplate.delete(keys);
	}
	
	/**
	 * 根据标识，从redis中删除
	 * @return 
	 */
	public Set<String> getKeyByPattern(K pattern) {
		DemonPredict.notNull(pattern, "key must not be null...");
		if(LOG.isDebugEnabled()){
			LOG.debug("select keys for "+pattern+" from redis");
		}
		Set<String> keys = redisTemplate.keys((String) pattern);
		return keys;
	}
	

}

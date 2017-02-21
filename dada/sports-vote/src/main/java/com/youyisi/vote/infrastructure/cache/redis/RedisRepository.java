package com.youyisi.vote.infrastructure.cache.redis;


/**
 * @author shuye
 * @time 2014-7-8
 */
public interface RedisRepository<K,V> {

	/**
	 * 从Redis中得到的是一个json格式的字符串，转化为想要的实体
	 */
	 V get(K key) ;

	/**
	 * 为了不同类型的转换，保存的时候以json格式保存到Redis,默认12小时后自动从Redis删除
	 * @param key
	 * @param entity
	 */
	 void save(K key,V entity) ;
	/**
	 * 
	 * @param key
	 * @param entity
	 * @param autoRemoveTime
	 */
	void save(K key,V entity,long autoRemoveTime);

	/**
	 * 根据标识，从Redis中删除
	 */
	void remove(K key);
}

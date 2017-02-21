package com.youyisi.sports.interfaces.helper;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.google.gson.Gson;
import com.youyisi.lang.helper.DemonPredict;

@SuppressWarnings("unchecked")
public class RedisClient {

	private static final Logger LOG = Logger.getLogger(RedisClient.class);
	private static final String REDIS_CONFIG = "classpath:/spring-redis.xml";
	private static RedisTemplate<String, String> redisTemplate;

	static {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(REDIS_CONFIG);
		redisTemplate = (RedisTemplate<String, String>) appContext.getBean("redisTemplate");
	}

	public static void set(String key, Object value, long timeout, TimeUnit unit) {
		DemonPredict.notEmpty(key);
		LOG.info("set key:" + key + " to redis");
		redisTemplate.opsForValue().set(key, new Gson().toJson(value), timeout, unit);
	}

	public static void set(String key, String value, long timeout, TimeUnit unit) {
		DemonPredict.notEmpty(key);
		LOG.info("set key:" + key + " to redis");
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	public static Long increment(String key, Long delta, long timeout, TimeUnit unit) {
		DemonPredict.notEmpty(key);
		LOG.info("increment key:" + key + " to redis");
		redisTemplate.expire(key, timeout, unit);
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public static Long increment(String key, Long delta) {
		DemonPredict.notEmpty(key);
		LOG.info("increment key:" + key + " to redis");
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public static void set(String key, Object value) {
		DemonPredict.notEmpty(key);
		redisTemplate.opsForValue().set(key, new Gson().toJson(value));
	}

	public static void set(String key, String value) {
		DemonPredict.notEmpty(key);
		redisTemplate.opsForValue().set(key, value);
	}

	public static void set(String key, String value, long timeout) {
		set(key, value, timeout, TimeUnit.MINUTES);
	}

	public static String get(String key) {
		DemonPredict.notEmpty(key);
		return redisTemplate.opsForValue().get(key);
	}

	public static void delete(String key) {
		DemonPredict.notEmpty(key);
		LOG.info("delete key:" + key + " from redis");
		redisTemplate.delete(key);
	}

	public static void deletes(String pattern) {
		Set<String> keys = getKeys(pattern);
		if (!keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}

	public static Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	public static void zSetAdd(String key, Object value, double score) {
		DemonPredict.notEmpty(key);
		LOG.info("add zSetkey:" + key + " from redis");
		redisTemplate.opsForZSet().add(key, new Gson().toJson(value), score);
	}

	public static Long zSetCount(String key, double min, double max) {
		DemonPredict.notEmpty(key);
		LOG.info("count zSetkey:" + key + " from redis");
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	public static Set<String> zSetRangeByScore(String key, double min, double max, long offset, long count) {
		DemonPredict.notEmpty(key);
		LOG.info("count zSetkey:" + key + " from redis");
		return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
	}

}

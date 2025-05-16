package com.navin.redis.redis_app;

import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

	private final StringRedisTemplate template;

	// Key-Value operations

	public void save(String key, String value) {
		template.opsForValue().set(key, value);
	}

	public String get(String key) {
		return template.opsForValue().get(key);
	}

	public void delete(String key) {
		template.delete(key);
	}

	// Redis List operations

	public void pushToListLeft(String key, String value) {
		template.opsForList().leftPush(key, value);
	}

	public void pushToListRight(String key, String value) {
		template.opsForList().rightPush(key, value);
	}

	public String popFromListLeft(String key) {
		return template.opsForList().leftPop(key);
	}

	public String popFromListRight(String key) {
		return template.opsForList().rightPop(key);
	}

	public List<String> getListRange(String key, long start, long end) {
		return template.opsForList().range(key, start, end);
	}

}

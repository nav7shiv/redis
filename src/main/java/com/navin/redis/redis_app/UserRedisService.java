package com.navin.redis.redis_app;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRedisService {
	
	private final RedisTemplate<String, User> template;
	
	public void saveUser(User user) {
		template.opsForValue().set("user:" + user.getId(), user);
	}
	
	public User getUser(String id) {
		return template.opsForValue().get("user:" + id);
	}
	
	public void deleteUser(String id) {
		template.delete("user:" + id);
	}

}

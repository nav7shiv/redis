package com.navin.redis.redis_app;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * This class works exclusively with Spring Cache system
 */
@Service
public class CachedUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CachedUserService.class);

	private final Map<String, User> dummyDb = Map.of(
			"u1", new User("u1", "Alice", 32), 
			"u2", new User("u2", "Mike", 27), 
			"u3", new User("u3", "Bob", 26), 
			"u4", new User("u4", "Newton", 29), 
			"u5", new User("u5", "Clarke", 32));
	
	// value represents the name given to the cache where the data is stored
	// key represents the specific entry inside the named cache
	
	@Cacheable(value = "userCache", key = "#id")
	public User getUserById(String id) {
		LOGGER.info("Fetching from DB!");
		return dummyDb.get(id);
	}
	
	@CachePut(value = "userCache", key = "#user.id")
	public User updateUser(User user) {
		LOGGER.info("Updating cache!");
		return user;
	}
	
	@CacheEvict(value = "userCache", key = "#id")
	public void evictUser(String id) {
		LOGGER.info("User evicted from cache!");
	}
}

package com.navin.redis.redis_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisScanService {
	
	private final StringRedisTemplate template;
	
	public List<String> scanKeys(String pattern) {
		List<String> result = new ArrayList<>();
		
		ScanOptions options = ScanOptions.scanOptions() // provides builder
				.match(pattern) // e.g. "*" or "user:*"
				.count(10) // batch-size
				.build();
		
		Cursor<byte[]> cursor = template.getRequiredConnectionFactory()
				.getConnection()
				.scan(options);
		
		while (cursor.hasNext()) {
			result.add(new String(cursor.next()));
		}
		
		return result;
	}

	public Set<String> getAllKeys() {
		return template.keys("*");
	}
}

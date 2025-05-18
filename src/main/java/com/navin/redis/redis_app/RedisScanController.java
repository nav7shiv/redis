package com.navin.redis.redis_app;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis/scan")
public class RedisScanController {
	
	private final RedisScanService service;
	
	@GetMapping
	public ResponseEntity<List<String>> scanKeys(@RequestParam(defaultValue = "*") String pattern) {
		return ResponseEntity.ok(service.scanKeys(pattern));
	}
	
	@GetMapping("/get-all-keys")
	public ResponseEntity<Set<String>> getAllKeys() {
		return ResponseEntity.ok(service.getAllKeys());
	}

}

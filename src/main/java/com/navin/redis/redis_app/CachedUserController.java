package com.navin.redis.redis_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cache/user")
public class CachedUserController {
	
	private final CachedUserService service;
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable String id) {
		return service.getUserById(id);
	}
	
	@PostMapping("/update")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> evictUser(@PathVariable String id) {
		service.evictUser(id);
		return ResponseEntity.ok("Cache evicted!");
	}

}

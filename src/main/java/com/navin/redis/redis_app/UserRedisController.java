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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRedisController {
	
	private final UserRedisService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		service.saveUser(user);
		return ResponseEntity.ok("User saved to Redis!");
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		return ResponseEntity.ok(service.getUser(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		service.deleteUser(id);
		return ResponseEntity.ok("User deleted from Redis!");
	}
	
}

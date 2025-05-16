package com.navin.redis.redis_app;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis")
public class RedisController {

	private final RedisService redisService;

	// Key-Value end-points

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestParam String key, @RequestParam String value) {
		redisService.save(key, value);
		return ResponseEntity.ok("Key saved!");
	}

	@GetMapping("/get")
	public ResponseEntity<String> get(@RequestParam String key) {
		String value = redisService.get(key);
		return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam String key) {
		String value = redisService.get(key);
		if (value != null) {
			redisService.delete(key);
			return ResponseEntity.ok("Key deleted!");
		}
		return ResponseEntity.notFound().build();
	}

	// Redis List end-points

	@PostMapping("/list/lpush")
	public ResponseEntity<String> leftPush(@RequestParam String key, @RequestParam String value) {
		redisService.pushToListLeft(key, value);
		return ResponseEntity.ok("Left pushed!");
	}

	@PostMapping("/list/rpush")
	public ResponseEntity<String> rightPush(@RequestParam String key, @RequestParam String value) {
		redisService.pushToListRight(key, value);
		return ResponseEntity.ok("Right-pushed");
	}

	@GetMapping("/list/lpop")
	public ResponseEntity<String> leftPop(@RequestParam String key) {
		return ResponseEntity.ok(redisService.popFromListLeft(key));
	}

	@GetMapping("/list/rpop")
	public ResponseEntity<String> rightPop(@RequestParam String key) {
		return ResponseEntity.ok(redisService.popFromListRight(key));
	}
	
    @GetMapping("/list/range")
    public ResponseEntity<List<String>> getListRange(@RequestParam String key,
                                                     @RequestParam(defaultValue = "0") long start,
                                                     @RequestParam(defaultValue = "-1") long end) {
        return ResponseEntity.ok(redisService.getListRange(key, start, end));
    }

}

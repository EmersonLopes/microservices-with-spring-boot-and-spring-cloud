package com.example.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	private int i;
	
	@GetMapping("/sample-api")
//	@Retry(name = "default")
//	@Retry(name = "sample-api", fallbackMethod = "hardcodeResponse")
//	@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodeResponse")
	@RateLimiter(name = "default")
	public String sampleApi() {
		logger.info("Sample API call received " +i);
		i++;
		
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		
		return forEntity.getBody();
	}
	
	public String hardcodeResponse(Exception ex) {
		return "fallback-response";
	}
	
}

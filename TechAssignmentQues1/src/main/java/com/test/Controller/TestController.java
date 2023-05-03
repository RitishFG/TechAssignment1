package com.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public ResponseEntity<String> testData()
	{
		testService.testGetData();
		return new ResponseEntity<>("done",HttpStatus.OK);
	}
}

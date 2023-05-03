package com.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {
	@Autowired
	private RestTemplate restTemplate;
	
	public void testGetData()
	{
		String url="https://api.publicapis.org";
		Object obj=restTemplate.exchange(url, HttpMethod.GET,null,Object.class).getBody();
		System.out.println(obj);
	}
}

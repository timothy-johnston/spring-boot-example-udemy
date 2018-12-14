package com.tj.service;

import org.springframework.stereotype.Component;

@Component		//Could also use @Service instead
public class WelcomeService {

	public String retrieveWelcomeMessage() {
		return "Good Morning World!!";
	}
	
}

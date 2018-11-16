package com.tj.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {

		System.out.println("ABOUT TO SAVE TO REPOSITORY-------------");
		
		repository.save(new User("Ranga", "Admin"));
		repository.save(new User("Ravi", "User"));
		repository.save(new User("Satish", "Admin"));
		repository.save(new User("Raghu", "User"));
		
		
		System.out.println("SAVED TO REPOSITORY-------------");

		for (User user : repository.findAll()) {
			log.info(user.toString());
			System.out.println("Looping through the for each loop, so there must be users in here.");
		}
		
		System.out.println("Out of the for each loop");

		log.info("Admin users are.....");
		log.info("____________________");
		for (User user : repository.findByRole("Admin")) {
			log.info(user.toString());
		}

	}
	
}

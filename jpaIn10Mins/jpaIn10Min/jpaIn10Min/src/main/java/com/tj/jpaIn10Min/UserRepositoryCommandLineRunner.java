package com.tj.jpaIn10Min;

import java.awt.List;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tj.jpaIn10Min.entity.User;
import com.tj.jpaIn10Min.service.UserDAOService;
import com.tj.jpaIn10Min.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		User user = new User("Jack", "Admin");
		//New user is created
		userRepository.save(user);
		log.info("New User is created : " + user);
		
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		log.info("User is retrived: " + users);
	}
	
}

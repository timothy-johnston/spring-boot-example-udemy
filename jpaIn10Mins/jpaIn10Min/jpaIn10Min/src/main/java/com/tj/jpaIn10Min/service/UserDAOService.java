package com.tj.jpaIn10Min.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tj.jpaIn10Min.entity.User;

//To manage user entity (save and retrieve users from database)

@Repository
@Transactional
public class UserDAOService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user) {
		//Open transaction				//Handled by @Transactional
		entityManager.persist(user);
		//Close transaction				//Handled by @Transactional
		return user.getId();
	}
	
}

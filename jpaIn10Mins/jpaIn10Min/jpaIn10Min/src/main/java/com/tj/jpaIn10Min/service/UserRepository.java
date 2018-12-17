package com.tj.jpaIn10Min.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tj.jpaIn10Min.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}

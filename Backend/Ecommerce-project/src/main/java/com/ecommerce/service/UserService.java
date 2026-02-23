package com.ecommerce.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(User user)
	{
		return userRepository.save(user);
	}
	
	public User getUserByEmail(String email)
	{
		return userRepository.findByEmail(email).orElse(null);
	}
	
	public User login(String email, String password) {
	    return userRepository.findByEmailAndPassword(email, password);
	}
}

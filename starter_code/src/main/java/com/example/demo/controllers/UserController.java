package com.example.demo.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		boolean isUser = userRepository.findById(id).isPresent();
		if (isUser) {
			return ResponseEntity.ok(userRepository.findById(id).get());
		}
		logger.error("Can not find user by id: " + id);
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				return ResponseEntity.ok(user);
			}
			logger.error("Can not find user by username: " + username);
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/create")
	public ResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest) {
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		if (userRepository.findByUsername(createUserRequest.getUsername()) != null) {
			logger.info("Can not create user, username is exist, {}", createUserRequest.getUsername());
			return ResponseEntity.badRequest().body("Can not create user, username is exist " + createUserRequest.getUsername());
		}
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);

		if (createUserRequest.getPassword().length() < 7 ||
				!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
			logger.error("Can not create user: password less than 7 letter or confirm password does not match");
			return ResponseEntity.badRequest().body("Can not create user: user name less than 7 letter or confirm password does not match");
		}
		user.setPassword(this.bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		userRepository.save(user);

		logger.info("Create user success: " + createUserRequest.getUsername());
		return ResponseEntity.ok(user);
	}
	
}

package com.mahindra.finance.jsonwebtoken.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahindra.finance.jsonwebtoken.models.User;
import com.mahindra.finance.jsonwebtoken.services.UserService;

@RestController	
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/home/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/current-user")
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}
}

package com.mahindra.finance.jsonwebtoken.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mahindra.finance.jsonwebtoken.models.User;

@Service
public class UserService {

	private List<User> store = new ArrayList<>();

	public UserService() {
		store.add(new User(UUID.randomUUID().toString(),"deepak","deepak1987rajput@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"neha","neha1987rajput@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"yuvu","yuvu1987rajput@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"akku","akku1987rajput@gmail.com"));
	}
	
	public List<User> getUsers()
	{
		return store;
	}
	
	
}

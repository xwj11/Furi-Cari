package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

public interface UserService {

	void create(User user);
	
	Map<String,Object> loginData(User user);
	
	List<User> getAll();
	
}

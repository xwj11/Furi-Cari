package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

	void create(User user);
	
	List<User> getAll();
}

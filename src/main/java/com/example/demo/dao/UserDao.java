package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.User;

public interface UserDao {
	
	void createUser(User user);
	
	List<User> getAll();
}

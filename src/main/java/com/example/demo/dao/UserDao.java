package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

public interface UserDao {
	
	void createUser(User user);
	
	Map<String,Object> loginDataUser(User user);
	
	List<User> getAll();
	
}

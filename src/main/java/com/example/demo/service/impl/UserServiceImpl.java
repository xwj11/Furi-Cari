package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userdao;
	
	@Autowired 
	UserServiceImpl(UserDao userdao){
		this.userdao = userdao;
	}
	
	public void create(User user) {
		userdao.createUser(user);
	}

	public List<User> getAll() {
		return userdao.getAll();
	}
	
	public Map<String,Object> loginData(User user) {
		return userdao.loginDataUser(user);
		
	}

}

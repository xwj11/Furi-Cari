




package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void createUser(User user) {
		jdbcTemplate.update("INSERT INTO user(nickname, mail, password) VALUES(?, ?, ?)",
				user.getNickname(), user.getMail(), user.getPassword());
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT id, nickname, mail, password FROM user";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<User> list = new ArrayList<User>();
		for(Map<String, Object> result : resultList) {
			User user = new User();
			user.setId((int)result.get("id"));
			user.setNickname((String)result.get("nickname"));
			user.setMail((String)result.get("mail"));
			user.setPassword((String)result.get("password"));			
			list.add(user);
		}
		return list;
	}
	

	public Map<String, Object> loginDataUser(User user){
		
		//String sql = "SELECT * FROM user WHERE mail = 'user.getMail()' AND password = 'user.getPassword()'";
		Map<String, Object> loginUserData = jdbcTemplate.queryForMap("SELECT * FROM user WHERE mail = ? AND password = ?",user.getMail(),user.getPassword());
		
		Map<String, Object> getLogin = new HashMap<>();
		User userLogin = new User();
		userLogin.setMail((String)loginUserData.get("mail"));
		userLogin.setPassword((String)loginUserData.get("password"));		
		getLogin.put("mail",userLogin.getMail());
		getLogin.put("password",userLogin.getPassword());
		return getLogin;
	
	}

}

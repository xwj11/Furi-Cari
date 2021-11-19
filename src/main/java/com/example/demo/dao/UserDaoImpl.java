




package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.UserForm;
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
	public void updateUser(User user) {
		jdbcTemplate.update("UPDATE user SET nickname = ?, mail = ? WHERE id = ?",
				user.getNickname(), user.getMail(), user.getId());
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
		//Map<String, Object> inMail_inPw_Count = jdbcTemplate.queryForMap("SELECT EXISTS(SELECT * FROM user WHERE mail = ? AND password = ?)",user.getMail(),user.getPassword());
		
		try {
			Map<String, Object> loginUserData = jdbcTemplate.queryForMap("SELECT * FROM user WHERE mail = ? AND password = ?",user.getMail(),user.getPassword());
			Map<String, Object> getLogin = new HashMap<>();
			User userLogin = new User();
			userLogin.setNickname((String)loginUserData.get("nickname"));
			userLogin.setMail((String)loginUserData.get("mail"));
			userLogin.setPassword((String)loginUserData.get("password"));
			userLogin.setId((int)loginUserData.get("id"));
			 
			getLogin.put("nickname",userLogin.getNickname());
			getLogin.put("mail",userLogin.getMail());
			getLogin.put("password",userLogin.getPassword());
			getLogin.put("id",userLogin.getId());
			return getLogin;
		} catch (EmptyResultDataAccessException e) {
          System.out.println("��O���������܂���");
          Map<String, Object> notLogin = new HashMap<>();
          return notLogin;
        }
	}

}

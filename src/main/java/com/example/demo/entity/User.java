package com.example.demo.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
	
	private int id;
	private String nickname;
	private String mail;
	private String password;
	
	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(Object object) {
		this.id = (Integer) object;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

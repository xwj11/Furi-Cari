package com.example.demo.app;

import javax.validation.constraints.NotNull;

public class UserForm {
	
	@NotNull
	private String nickname;
	@NotNull
	private String mail;
	@NotNull
	private String password;
	
	public UserForm() {
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

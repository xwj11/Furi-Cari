package com.example.demo.app;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	@NotBlank
	private String mail;
	@NotBlank
	private String password;
	
	public LoginForm() {
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

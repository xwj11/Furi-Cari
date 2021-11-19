package com.example.demo.app;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
	
	private int id;
	
	@NotBlank
	@Size(min = 1,max = 4,message = "１～４文字で入力してください")
	private String nickname;
	@NotBlank
	@Email(message="emailを入力してください")
	private String mail;
	@NotBlank
	@Size(min = 4,max = 8,message = "4文字以上8文字以内の半角英数字混合で入力してください")
	private String password;
	
	public UserForm() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

package com.example.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.service.UserService;

@Controller
@RequestMapping("/information")
@SessionAttributes("userForm")
public class MyInformation {
	@Autowired
	private UserService userService;
	@ModelAttribute("userForm")
	  public UserForm setuserForm() { 
		  return new UserForm(); 
  }
	
	@ResponseBody
	@RequestMapping("/a")
	public String update(){
		
		
		return "";
	}
	
	
}

package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/furicari")
public class MainController {

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "FuriCari");
		return "index";
	}
	
	//êVãKìoò^
	@GetMapping("/nuser")
	public String new_user(UserForm userForm,
			Model model) {
		return "nuser";
	}
	
	//ämîFÉyÅ[ÉW
	@PostMapping("/finish")
	public String finish(@Validated UserForm userForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return "/nuser";
		}
		return "/finish";
	}
	
	//êVãKìoò^Ç…ñﬂÇÈ
	@PostMapping("/nuser")
	public String go_back(UserForm userForm,
			Model model) {
		return "nuser";
	}
	

	@GetMapping("/mypage")
	public String mypage(Model model) {
		return "mypage";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}

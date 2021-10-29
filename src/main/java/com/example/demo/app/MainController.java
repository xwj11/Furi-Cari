package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/furicali")
public class MainController {

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "FuriCali");
		return "index";
	}
	@GetMapping("/nuser")
	public String new_user(UserForm userform,
			Model model) {
		return "nuser";
	}
	@PostMapping("/finish")
	public String finish(@Validated UserForm userForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return "/nuser";
		}
		return "/finish";
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

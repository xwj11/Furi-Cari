package com.example.demo.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/furicari")
public class MainController {

	private final UserService userService;
	
	@Autowired
	public MainController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		List<User> list = userService.getAll();
		
		model.addAttribute("userList", list);
		model.addAttribute("title", "FuriCari");
		return "index";
	}
	
	//VKo^
	@GetMapping("/nuser")
	public String new_user(UserForm userForm,
			Model model) {
		return "nuser";
	}
	
	//mFy[W
	@PostMapping("/finish")
	public String finish(@Validated UserForm userForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return "/nuser";
		}
		return "/finish";
	}
	
	//VKo^Éßé
	@PostMapping("/nuser")
	public String go_back(UserForm userForm,
			Model model) {
		return "nuser";
	}
	
	//DBÖo^
	@PostMapping("/complete")
	public String complete(@Validated UserForm userForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "nuser";
		}
		//DB
		User user = new User();
		user.setNickname(userForm.getNickname());
		user.setMail(userForm.getMail());
		user.setPassword(userForm.getPassword());
		
		userService.create(user);
		redirectAttributes.addFlashAttribute("complete", "®¹µÜµ½");
		return "redirect:/furicari/nuser";
	}
	

	@GetMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("title", "}Cy[W");
		
		return "mypage";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}

package com.example.demo.app;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

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
	
	//新規登録
	@GetMapping("/nuser")
	public String new_user(UserForm userForm,
			Model model) {
		return "nuser";
	}
	
	//確認ページ
	@PostMapping("/finish")
	public String finish(@Validated UserForm userForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return "/nuser";
		}
		return "/finish";
	}
	
	//新規登録に戻る
	@PostMapping("/nuser")
	public String go_back(UserForm userForm,
			Model model) {
		return "nuser";
	}
	
	//DBへ登録
	@PostMapping("/complete")
	public String complete(@Validated UserForm userForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "nuser";
		}
		//DB処理
		User user = new User();
		user.setNickname(userForm.getNickname());
		user.setMail(userForm.getMail());
		user.setPassword(userForm.getPassword());
		
		userService.create(user);
		redirectAttributes.addFlashAttribute("complete", "完了しました");
		return "redirect:/furicari/nuser";
	}
	

	@GetMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("title", "マイページ");
		
		return "mypage";
	}
	
	@GetMapping("/login")
	public String login(LoginForm loginForm,Model model) {
		return "login";
	}
	/**/
	@PostMapping("/index")
	public String index(@Validated LoginForm loginForm,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {	
			//エラー時	
			return "login";
		}
		//DB処理
		User user = new User();
		user.setMail(loginForm.getMail());
		user.setPassword(loginForm.getPassword());
		
		Map<String,Object> getLogin = userService.loginData(user);
		boolean isEmpty = getLogin.isEmpty();
		model.addAttribute("getLogin", getLogin);
		if(isEmpty) {
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return "redirect:/furicari/login";
		}else {
			return "index";
		}
		
	}
	
}

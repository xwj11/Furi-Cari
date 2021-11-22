package com.example.demo.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/furicari")
@SessionAttributes("userForm")
public class MainController {
	
	private final UserService userService;
	
	
	@ModelAttribute("userForm")
	  public UserForm setuserForm() { 
		  return new UserForm(); 
    }
	

	
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
			return "nuser";
		}
		return "finish";
	}
	
	//新規登録に戻る
	@PostMapping("/nuser")
	public String go_back(UserForm userForm,
			Model model) {
		return "nuser";
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus ) {
		sessionStatus.setComplete();
		return "redirect:/furicari/index";
	}
	//DBへ登録
	@PostMapping("/complete")
	public String complete(@Validated UserForm userForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes,
			SessionStatus sessionStatus) {
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
		sessionStatus.setComplete();
		return "register";
	}
	

	@GetMapping("/mypage")
	public String mypage(UserForm userForm,
			Model model) {
		model.addAttribute("title", "マイページ");
		
		return "mypage";
	}
	
	//ユーザー情報の確認ページ
	@GetMapping("/user-update")
	public String update(UserForm userForm,
			Model model) {
		return "/user-update";
	}
	
	//ユーザー情報の更新
	@PostMapping("/user-complete")
	public String update(@Validated UserForm userForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "user-update";
		}
		//DB処理
		User user = new User();
		user.setNickname(userForm.getNickname());
		user.setMail(userForm.getMail());
		user.setPassword(userForm.getPassword());
		user.setId(userForm.getId());
		
		userService.update(user);
		redirectAttributes.addFlashAttribute("complete", "完了しました");
		return "redirect:/furicari/mypage";
	}
	
	
	
	
	
	
	@GetMapping("/login")
	public String login(LoginForm loginForm,Model model) {
		return "login";
	}
	
	@PostMapping("/index")
	public String index(@Validated LoginForm loginForm,
			UserForm userForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		//バリデーション
		final int checkNumber = 0;
		int checker = checkNumber;
		if(loginForm.getMail() == null) {
			redirectAttributes.addFlashAttribute("notMail", "メールを入力してください");	
			checker ++;
		}else {
			int checkMail = loginForm.getMail().indexOf("@");
			if(checkMail == -1) {
				redirectAttributes.addFlashAttribute("notMail", "正しいメールアドレスを入力してください"); 
				checker ++;
			}
		}
		if(loginForm.getPassword() == null) {
			redirectAttributes.addFlashAttribute("nullPassword", "パスワードを入力してください");
			checker ++;
		}
		if(checker > checkNumber) {
			checker = checkNumber;
			return "redirect:/furicari/login";
		}
			
		//DB処理
		User user = new User();
		
		user.setMail(loginForm.getMail());
		user.setPassword(loginForm.getPassword());
		
		Map<String,Object> getLogin = userService.loginData(user);
		
		//セッションのuserFormにセット↓
		userForm.setNickname((String)getLogin.get("nickname"));
		userForm.setMail((String)getLogin.get("mail"));
		userForm.setPassword((String)getLogin.get("password"));
		userForm.setId(getLogin.get("id"));
		//セッションのuserFormにセット↑
    
		boolean isEmpty = getLogin.isEmpty();
		model.addAttribute("getLogin", getLogin);		
		if(isEmpty) {
			redirectAttributes.addFlashAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return "redirect:/furicari/login";
		}else {
			return "redirect:/furicari/index";
		}
		
	}
	
}

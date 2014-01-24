package com.milgo.cubby.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milgo.cubby.dao.RoleDao;
import com.milgo.cubby.dao.UserDao;
import com.milgo.cubby.model.User;


@Controller
public class MainController {
	
	@Autowired
	public UserDao userDao;
	@Autowired
	public RoleDao roleDao;
	
	/*@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model, Principal principal ) {
 
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "welcome";
	}*/
	@RequestMapping({"/admin"})
	public String showAdminPage(Map<String, Object> model){
		model.put("usersList", userDao.getAllUsers());
		return "admin";
	}

	@RequestMapping({"/login"})
	public String showLoginPage(Map<String, Object> model){
		return "login";
	}
	
	@RequestMapping({"/logout"})
	public String logout(Map<String, Object> model){
		return "login";
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String showLoginFailedPage(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
	
	@RequestMapping(value="/home")
	public String showHomePage(){
		return "home";
	}
	
	@RequestMapping({"/register"})
	public String showRegisterPage(ModelMap model){//czy tu musi byc model?
		User user = new User();
		model.addAttribute(user);
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }			
			return "register";
		}
		
		if(userDao.isLoginUsed(user.login)){
			bindingResult.rejectValue("login", "loginUsedErrorMessage", "Login used!");
			return "register";
		}
		
		user.setRole(roleDao.getRoleByName("USER"));
		userDao.addUser(user);
		
		System.out.println("New user created!");
		return "user_added";
	}
}

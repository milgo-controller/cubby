package com.milgo.cubby.mvc;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milgo.cubby.model.UserDetails;


@Controller
public class MainController {
	
	@RequestMapping({"/login"})
	public String showLoginPage(Map<String, Object> model){
		System.out.println("welcome");
		return "login";
	}

	
	@RequestMapping({"/register"})
	public String showNewUserEditPage(Model model){
		model.addAttribute(new UserDetails());
		return "newUserEdit";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String addNewUser(@Valid UserDetails userDetails, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			return "newUserEdit";
		}
		//bugFix change 2
		System.out.println("newUser");
		return "";
	}
}

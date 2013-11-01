package com.milgo.cubby.mvc;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milgo.cubby.dao.UserDetailsDao;
import com.milgo.cubby.model.UserDetails;


@Controller
public class MainController {
	
	@Autowired
	UserDetailsDao userDetailsDao;
	
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
			
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			
			System.out.println("nope!");
			return "newUserEdit";
		}
		
		userDetailsDao.addUser(userDetails);
		
		System.out.println("newUser");
		return "userAdded";
	}
}

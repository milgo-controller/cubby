package com.milgo.cubby.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.milgo.cubby.dao.RoleDao;
import com.milgo.cubby.dao.UserDao;
import com.milgo.cubby.model.Role;
import com.milgo.cubby.model.User;


@Controller
public class MainController {
	
	@Autowired
	public UserDao userDao;
	@Autowired
	public RoleDao roleDao;
	
	@RequestMapping({"/admin"})
	public String showAdminPage(Map<String, Object> model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
		
	    List<?> userList = userDao.getAllUsers();
		Iterator<?> iterator = userList.iterator();
	    
		//powielanie kodu do refaktoryzacji
	    if(userDao.getUserByLogin(name).getRole().getRoleName().compareTo("ADMIN") == 0){
	    	while (iterator.hasNext()) {
				User user = (User)iterator.next();
				if(user.getLogin().compareTo(name) == 0)
					iterator.remove();
				
				user.setRoleName(user.getRole().getRoleName());
	    	}
	    }else{
	    	while (iterator.hasNext()) {
				User user = (User)iterator.next();
				if((user.getRole().getRoleName().compareTo("ADMIN") == 0) ||
					(user.getRole().getRoleName().compareTo("MODERATOR") == 0)	)
					iterator.remove();
				
				user.setRoleName(user.getRole().getRoleName());
	    	}
	    }
	    
		model.put("usersList", userList);
		return "admin";
	}
	
	@RequestMapping(value="/admin/remove/user/{login}", method=RequestMethod.GET)
	public String adminRemoveUser(@PathVariable String login){
		User deletedUser = userDao.getUserByLogin(login);
		if(deletedUser != null)
			userDao.removeUser(userDao.getUserByLogin(login));
		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.GET)
	public ModelAndView showAdminEditUser(@PathVariable String login){		
		ModelAndView mav = new ModelAndView("user_edit");
		
		User userForm = userDao.getUserByLogin(login);
		
		List<?> roles = roleDao.getAllRoles();

		HashMap<String, String> roleNames = new LinkedHashMap<String,String>();
		
		for(Object role: roles){
			String roleName = ((Role)role).getRoleName();
			if(roleName.compareTo("ADMIN") == 0)continue;
			roleNames.put(roleName, roleName);
		}
		
		userForm.setRoleName(userForm.getRole().getRoleName());
		userForm.setRoleNames(roleNames);
		mav.addObject("user", userForm);
		return mav;
	}
	
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.POST)
	public ModelAndView adminEditUser(@PathVariable String login, @Valid User userForm, BindingResult bindingResult){
		ModelAndView mav = new ModelAndView("user_edit");
		
		if(bindingResult.hasErrors()){
			mav.addObject("user", userForm);
			return mav;
		}
		
		mav.setViewName("redirect:/admin");
		
		User updatedUser = userDao.getUserByLogin(login);
		updatedUser.setAddress(userForm.getAddress());
		updatedUser.setEmail(userForm.getEmail());
		updatedUser.setFirstName(userForm.getFirstName());
		updatedUser.setLastName(userForm.getLastName());
		updatedUser.setBirthDate(userForm.getBirthDate());
		
		Integer enabled = userForm.getEnabled();
		if(enabled != null)
			updatedUser.setEnabled(enabled);
		else
			updatedUser.setEnabled(0);
		
		Role role = roleDao.getRoleByName(userForm.getRoleName());
		System.out.println(role.getRoleName());
		updatedUser.setRole(role);
		
		updatedUser.setConfirmPassword(updatedUser.getPassword());
		userDao.modifyUser(updatedUser);
		
		return mav;
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
	
	//admin cannot go to register page! now he can
	@RequestMapping({"/register"})
	public String showRegisterPage(ModelMap model){
		User user = new User();
		model.addAttribute(user);
		return "user_edit";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@Valid User userForm, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }			
			return "register";
		}
		
		if(userDao.isLoginUsed(userForm.login)){
			bindingResult.rejectValue("login", "loginUsedErrorMessage", "Login used!");
			return "register";
		}
		
		userForm.setRole(roleDao.getRoleByName("USER"));
		userForm.setEnabled(0);
		userDao.addUser(userForm);
		
		System.out.println("New user created!");
		return "user_added";
	}
}

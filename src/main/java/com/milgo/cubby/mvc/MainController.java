package com.milgo.cubby.mvc;

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

import com.milgo.cubby.bo.RoleBo;
import com.milgo.cubby.bo.TrainingBo;
import com.milgo.cubby.bo.UserBo;
import com.milgo.cubby.model.Role;
import com.milgo.cubby.model.Training;
import com.milgo.cubby.model.User;
import com.milgo.cubby.model.UserTrainings;
import com.milgo.cubby.model.UserTrainingsId;


@Controller
public class MainController {
	
	@Autowired
	public UserBo userBo;
	@Autowired
	public RoleBo roleBo;
	@Autowired
	public TrainingBo trainingBo;
	
	@RequestMapping({"/admin"})
	public String showAdminPage(Map<String, Object> model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
		
	    List<?> userList = userBo.getAllUsers();
		Iterator<?> iterator = userList.iterator();

	    while (iterator.hasNext()) {
			User user = (User)iterator.next();
				
			if(loggedUser.getRole().getRoleName().compareTo("ADMIN") == 0)
			{
				if(user.getRole().getRoleName().compareTo("ADMIN") == 0)
						iterator.remove();
			}
			else
			{
				if((user.getRole().getRoleName().compareTo("ADMIN") == 0) ||
					(user.getRole().getRoleName().compareTo("MODERATOR") == 0)	)
						iterator.remove();
			}
				
			user.setRoleName(user.getRole().getRoleName());
	    }
	    
		model.put("usersList", userList);
		
		List<?> trList =  trainingBo.getAllTrainings();
		model.put("trainingsList", trList);
		
		return "admin";
	}
	
	@RequestMapping(value="/admin/remove/user/{login}", method=RequestMethod.GET)
	public String adminRemoveUser(@PathVariable String login){
		User deletedUser = userBo.getUserByLogin(login);
		if(deletedUser != null)
			userBo.removeUser(deletedUser);
		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.GET)
	public ModelAndView showAdminEditUser(@PathVariable String login){		
		ModelAndView mav = new ModelAndView("user_edit");
		
		User userForm = userBo.getUserByLogin(login);
		
		List<?> roles = roleBo.getAllRoles();

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
		
		User updatedUser = userBo.getUserByLogin(login);
		updatedUser.setAddress(userForm.getAddress());
		updatedUser.setEmail(userForm.getEmail());
		updatedUser.setFirstName(userForm.getFirstName());
		updatedUser.setLastName(userForm.getLastName());
		updatedUser.setBirthDate(userForm.getBirthDate());
		
		if(userForm.getEnabled() == null)
			updatedUser.setEnabled(0);
		else updatedUser.setEnabled(1);
			
		Role role = roleBo.getRoleByName(userForm.getRoleName());
		System.out.println(role.getRoleName());
		updatedUser.setRole(role);
		
		updatedUser.setConfirmPassword(updatedUser.getPassword());
		userBo.modifyUser(updatedUser);
		
		return mav;
	}
	
	@RequestMapping({"admin/add/training"})
	public String showAddTrainingPage(ModelMap model){
		model.addAttribute(new Training());
		return "training_edit";
	}
	
	@RequestMapping(value="admin/add/training", method=RequestMethod.POST)
	public String adminEditTraining(@Valid Training training, BindingResult bindingResult){
		
		if(training.getOnline() == null)
			training.setOnline(0);
		else training.setOnline(1);
		
		if(bindingResult.hasErrors())
		{
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }						    		    		    
			return "training_edit";
		}
		
	    if(training.getOnline() == 0){
	    	if(training.getStartDate() == null){
	    		bindingResult.rejectValue("startDate", "", "Please enter date of training!");
	    		return "training_edit";
	    	}
	    	
			if(training.getPlace().isEmpty()){
				bindingResult.rejectValue("place", "", "Please enter place of training!");
				return "training_edit";
	    	}
	    }
	    else{
	    	if(training.getUrl().isEmpty()){
				bindingResult.rejectValue("url", "", "Please enter url!");
				return "redirect:/admin";
	    	}
	    }
		
		trainingBo.addTraining(training);
		
		return "redirect:/admin";
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
	public String showHomePage(Map<String, Object> model){
		//user training list
		
		//available list
		List<?> trList =  trainingBo.getAllTrainings();
		model.put("trainingsList", trList);
		return "home";
	}
	
	@RequestMapping(value="/home/training/join/{id}", method=RequestMethod.GET)
	public String homeTrainingJoin(@PathVariable Integer id){
		
		String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();

	    User loggedUser = userBo.getUserByLogin(userName);
	    
		Training training = trainingBo.getTrainingById(id);
		System.out.println(training.getName());
		
		UserTrainings userTraining = new UserTrainings();
		userTraining.setActive(0);
		userTraining.setTraining(training);
		userTraining.setUser(loggedUser);
		
		loggedUser.getUserTrainings().add(userTraining);		
		System.out.println(loggedUser.getUserTrainings().size());
		userBo.modifyUser(loggedUser);
		
		//List<?> trList =  trainingBo.getAllTrainings();
		//model.put("trainingsList", trList);
		return "home";
	}
	
	@RequestMapping({"/register"})
	public String showRegisterPage(ModelMap model)
	{
		SecurityContextHolder.getContext().setAuthentication(null);					
		User user = new User();
		model.addAttribute(user);
		return "register";
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
		
		if(userBo.isLoginUsed(userForm.login)){
			bindingResult.rejectValue("login", "loginUsedErrorMessage", "Login used!");
			return "register";
		}
		
		userForm.setRole(roleBo.getRoleByName("USER"));
		userForm.setEnabled(0);
		userBo.addUser(userForm);
		
		System.out.println("New user created!");
		return "user_added";
	}
}

package com.milgo.cubby.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.milgo.cubby.dao.UserDetailsDao;
import com.milgo.cubby.model.UserDetails;
import com.milgo.cubby.validator.annotations.UniqueField;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object>{

	@Autowired
	private UserDetailsDao userDetailsDao;
	
	String fieldName;
	String errorMessage;
	
	public void initialize(UniqueField arg0) {
		errorMessage = arg0.message();
		fieldName = arg0.fieldName();
	}

	public boolean isValid(Object bean, ConstraintValidatorContext constraintValidatorContext) {

		if(userDetailsDao == null)return false;
		
		String fieldValue = "";
		try {
			fieldValue = BeanUtils.getProperty(bean, fieldName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserDetails user = null;   
		System.out.println(fieldValue);
        user = userDetailsDao.getUserByLogin(fieldValue);
		
		/*if(bean == null){ //bean null when testing?
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate("Error #1").addPropertyNode(fieldName).addConstraintViolation();
			System.out.println("tutaj1");
			return false;
		}*/
		
		/*if(userDetailsDao == null){ //bean null when testing?
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate("Error #2").addPropertyNode(fieldName).addConstraintViolation();
			System.out.println("tutaj2");
			return false;
		}*/
		
		/*if(userDetailsDao == null)System.out.println("userDetailsDao == null");
		System.out.println(fieldValue);
		UserDetails user = null;
		userDetailsDao.getUserByLogin(fieldValue);
		if(user == null)System.out.println("oh shit null!");*/

		if(user != null){
			//System.out.println("shit!");
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(fieldName).addConstraintViolation();
			return false;
		}
		
		return true;
	}

}

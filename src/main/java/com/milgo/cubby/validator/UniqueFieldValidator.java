package com.milgo.cubby.validator;

import java.lang.reflect.InvocationTargetException;

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
		
		String fieldValue = "";
		try {
			fieldValue = BeanUtils.getProperty(bean, fieldName);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(bean == null || userDetailsDao == null){
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate("Error!").addPropertyNode(fieldName).addConstraintViolation();
			return false;
		}
		
		System.out.println(fieldValue);
		UserDetails user = userDetailsDao.getUserByLogin(fieldValue);

		if(user == null){
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(fieldName).addConstraintViolation();
			return false;
		}
		
		return false;
	}

}

package com.milgo.cubby.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;




import org.apache.commons.beanutils.BeanUtils;

import com.milgo.cubby.validator.annotations.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

	private String firstFieldName;
	private String secondFieldName;
	private String errorMessage;

	@Override
	public void initialize(FieldMatch arg0) {
		firstFieldName = arg0.first();
		secondFieldName = arg0.second();
		errorMessage = arg0.message();
	}
	
	public boolean isValid(Object bean, ConstraintValidatorContext context) {
		boolean fieldsMatch = false;
		try{
			Object firstObj = BeanUtils.getProperty(bean, firstFieldName);
			Object secondObj = BeanUtils.getProperty(bean, secondFieldName);
			
			fieldsMatch = (firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj));
		}
		catch(final Exception ignore){
			System.out.println("error: " + ignore.getMessage());
		}
		
		if(!fieldsMatch) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(firstFieldName).addConstraintViolation();
			
			return false;
		}
		
		return true;
	}

}

package com.milgo.cubby.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.milgo.cubby.validator.UniqueFieldValidator;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldValidator.class)
@Documented
public @interface UniqueField {

	String message() default "{com.milgo.cubby.validator.annotations.UniqueField}";
	String fieldName();

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}

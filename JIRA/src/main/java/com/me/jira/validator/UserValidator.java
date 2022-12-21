package com.me.jira.validator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.me.jira.pojo.User;

import java.util.regex.Pattern;

import org.springframework.validation.*;
/**
 *
 * @author abhishekvenkata
 */
public class UserValidator implements Validator {
    

    @Override
    public boolean supports(Class<?> type) {   
        return User.class.isAssignableFrom(type); //True if object is from User class
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        
        //validations
    	User user = (User) o;
    	if(user.getEmail_id().equals(""))
    	{
    		errors.rejectValue("email_id", "error-email-id", "EmailId cannot be empty");
    	}
    	if(user.getPassword().equals(""))
    	{
    		errors.rejectValue("password", "error-password", "Password cannot be empty");
    	}
    	if(user.getFirst_name().equals(""))
    	{
    		errors.rejectValue("first_name", "error-first_name", "First name cannot be empty");
    	}
    	if(user.getLast_name().equals(""))
    	{
    		errors.rejectValue("last_name", "error-last_name", "Last name cannot be empty");
    	}
    	String email = user.getEmail_id();
    	
    	if(!isValid(email))
    	{
    		errors.rejectValue("email_id","error-email-id","Your email id is not valid");
    	}

    	/*
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email_id","error-email_id","Email id cant be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","error-password","password cant be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"first_name","error-first_name","first name cant be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"last_name","error-last_name","last name cant be empty");
        */
    }
}

package com.me.jira.validator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.me.jira.pojo.Task;
import com.me.jira.pojo.User;

import org.springframework.validation.*;
/**
 *
 * @author abhishekvenkata
 */
public class LoginValidator implements Validator {
    

    @Override
    public boolean supports(Class<?> type) {   
        return User.class.isAssignableFrom(type); //True if object is from User class
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        //validations
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email_id","error-email_id","Email ID cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","error-issue_type","Password cannot be empty");
    }
}

package com.me.jira.validator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.me.jira.pojo.Task;
import org.springframework.validation.*;
/**
 *
 * @author abhishekvenkata
 */
public class TaskValidator implements Validator {
    

    @Override
    public boolean supports(Class<?> type) {   
        return Task.class.isAssignableFrom(type); //True if object is from User class
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        //validations
        ValidationUtils.rejectIfEmpty(errors,"summary","error-summary","Summary cannot be empty");
        ValidationUtils.rejectIfEmpty(errors,"description","error-description","Description cannot be empty");
        ValidationUtils.rejectIfEmpty(errors,"assigned_to","error-assigned_to","Assigned to cannot be empty");
        ValidationUtils.rejectIfEmpty(errors,"issue_type","error-issue_type","Issue type cannot be empty");
        ValidationUtils.rejectIfEmpty(errors,"priority","error-priority","Priority cannot be empty");
        
    }
}

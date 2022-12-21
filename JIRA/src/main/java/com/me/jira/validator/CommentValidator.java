package com.me.jira.validator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.me.jira.pojo.Comment;
import org.springframework.validation.*;
/**
 *
 * @author abhishekvenkata
 */
public class CommentValidator implements Validator {
    

    @Override
    public boolean supports(Class<?> type) {   
        return Comment.class.isAssignableFrom(type); //True if object is from User class
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        //validations
        ValidationUtils.rejectIfEmpty(errors,"column_text","errors-column_text","Column text cannot be empty");
        
    }
}

package com.me.jira.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.me.jira.dao.DAO;
import com.me.jira.dao.UserDAO;
import com.me.jira.Exception.*;
import com.me.jira.pojo.User;
import com.me.jira.validator.LoginValidator;
import com.me.jira.validator.UserValidator;

@Controller
public class UserController extends DAO {
		
	
	@GetMapping("/showuser.htm")
	public String showuserGet(ModelMap model, User user, HttpSession session, HttpServletRequest request)
	{
		user = (User) session.getAttribute("current-user");
		model.addAttribute(user);
		request.setAttribute("user",user);
		return "userhome";
	}
	@GetMapping("/adduser.htm")
	public String addUserGet(ModelMap model, User user) {
		// command object
		model.addAttribute("user", user);
		
		// return form view
		return "signup-form";
	}

	@PostMapping("/adduser.htm")
	public String addUserPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, 
			UserDAO userdao, HttpServletRequest request, HttpSession session, UserValidator validator) {
		
		try {
			validator.validate(user,result);
	        if(result.hasErrors())
	        {
	            return "signup-form";
	        }
			userdao.save(user);
		} catch (AdvertException e) {
			request.setAttribute("error", "Duplicate entry of email ID "+e.getMessage());
			System.out.println("User cannot be Added: " + e.getMessage());
			return "signup-form";
		}
		session.setAttribute("current-user", user);
		status.setComplete(); //mark it complete
		return "index";
	}
	
	@GetMapping("/loginas.htm")
	public String loginasGet(ModelMap model, User user) {
		// command object
		model.addAttribute("user", user);
		
		// return form view
		return "loginas";
	}
	
	@PostMapping("/loginas.htm")
	public String loginasPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, UserDAO userdao, 
							HttpServletRequest request, HttpSession session, LoginValidator validator) 
	{
		try {
			validator.validate(user,result);
		    if(result.hasErrors())
		    {
		        return "loginas";
		    }
			user = userdao.validate(user.getEmail_id(), user.getPassword());
		} catch (AdvertException e) {
			System.out.println("User has entered wrong credentials: " + e.getMessage());
			request.setAttribute("error", "User has entered wrong credentials: "+e.getMessage());
			return "loginas";
		}
		if(user!=null)
		{
			session.setAttribute("current-user", user);
			request.setAttribute("all_tasks", user.getTasks());
			
			User login_user = (User) getSession().get(User.class, user.getUser_id());
			System.out.println(login_user.getUser_id());
			System.out.println(login_user.getFirst_name());
			System.out.println(login_user.getLast_name());
			request.setAttribute("login_user", login_user);
			status.setComplete(); //mark it complete
			return "userhome";
		}
		request.setAttribute("error", "User has entered wrong credentials");
		return "loginas";
	}
	
	
}
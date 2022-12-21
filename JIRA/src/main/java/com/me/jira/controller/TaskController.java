package com.me.jira.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;

import com.me.jira.Exception.AdvertException;
import com.me.jira.dao.TaskDAO;
import com.me.jira.dao.UserDAO;
import com.me.jira.dao.ActivityDAO;
import com.me.jira.dao.CommentDAO;
import com.me.jira.dao.DAO;
import com.me.jira.pojo.*;
import com.me.jira.validator.CommentValidator;
import com.me.jira.validator.TaskValidator;
import com.me.jira.validator.UserValidator;

@Controller
public class TaskController extends DAO{
		
	
	
	@GetMapping("/showcalendar.htm")
	public String showAllTasksget(ModelMap model, TaskDAO taskdao, UserDAO userdao, HttpServletRequest request) throws AdvertException
	{
	
		
		List<User> full_users = (List<User>) userdao.list();
		
		List<Task> all_tasks = new ArrayList<Task>();
		LinkedHashMap<User,List<Task>> user_task = new LinkedHashMap<>();
		Iterator<User> it = full_users.iterator();
		while(it.hasNext())
		{
			User user_it = it.next();
			String email = user_it.getEmail_id();
			List<Task> tasks = taskdao.assignList(email);

			user_task.put(user_it, tasks);
		}
		boolean home = true;
		List<String> all_users = userdao.getUserList(all_tasks);
		request.setAttribute("all_tasks",all_tasks);
		request.setAttribute("all_users",all_users);
		request.setAttribute("full_users", full_users);
		request.setAttribute("user_tasks", user_task);
		request.setAttribute("home",home );
		request.setAttribute("date", new Date(System.currentTimeMillis()));
		return "showalltasks";	
	}
	
	@GetMapping("/showcalendar/{first_name}+{Id}.htm")
	public String showUserTasksget(ModelMap model, TaskDAO taskdao, UserDAO userdao, HttpServletRequest request,
			@PathVariable Long Id, @PathVariable String first_name) throws AdvertException
	{
		User user = getSession().get(User.class, Id);
		List<User> full_users = new ArrayList<User>();
		full_users.add(user);
		
		List<Task> all_tasks = new ArrayList<Task>();
		Map<User,List<Task>> user_task = new HashMap<>();
		Iterator<User> it = full_users.iterator();
		while(it.hasNext())
		{
			User user_it = it.next();
			String email = user_it.getEmail_id();
			List<Task> tasks = taskdao.assignList(email);
			user_task.put(user_it, tasks);
		}
		boolean home = false;
		List<String> all_users = userdao.getUserList(all_tasks);
		request.setAttribute("all_tasks",all_tasks);
		request.setAttribute("all_users",all_users);
		request.setAttribute("full_users", full_users);
		request.setAttribute("user_tasks", user_task);
		request.setAttribute("home",home);
		return "showalltasks";
	}
	
	
	@GetMapping("/showmytasks.htm")
	public String showTaskGet(ModelMap model, Task task,TaskDAO taskdao, UserDAO userdao, HttpServletRequest request, HttpSession session) throws AdvertException {
		// command object
		
		
		User user = (User) session.getAttribute("current-user");
		if(user==null)
		{
			return "error-page";
		}
		Long id = user.getUser_id();
		List<Task> all_tasks = taskdao.list(id);
		List<String> all_users = userdao.getUserList(all_tasks);
		request.setAttribute("all_tasks",all_tasks);
		request.setAttribute("all_users",all_users);
		return "showmytasks";
	}
	
	@GetMapping("/showassigntasks.htm")
	public String showAssignTasks(ModelMap model, Task task,TaskDAO taskdao, UserDAO userdao, HttpServletRequest request, HttpSession session) throws AdvertException 
	{
		User user = (User) session.getAttribute("current-user");
		String email = user.getEmail_id();
		List<Task> all_tasks = taskdao.assignList(email);
		List<String> all_users = userdao.getAssignList(all_tasks);
		request.setAttribute("all_tasks", all_tasks);
		request.setAttribute("all_users", all_users);
		return "showassigntasks";
	}
	
	@GetMapping("/add-tasks.htm")
	public String addTaskGet(ModelMap model, Task task, User user, CommentDAO commentdao, HttpServletRequest request, HttpSession session) {
		// command object
		model.addAttribute("task", task);
		UserDAO userdao = new UserDAO();
		java.util.List<User> userList = userdao.list();
		request.setAttribute("users", userList);
		user = (User) session.getAttribute("current-user");
		if(user==null)
		{
			request.setAttribute("val", false);
			return "add-tasks";
		}
		request.setAttribute("val",true);
		return "add-tasks";
	}
	
	
	@PostMapping("/add-tasks.htm")
	public String addTaskPost(@ModelAttribute("task")Task task, BindingResult result, SessionStatus status, TaskDAO taskdao, 
			HttpServletRequest request, HttpSession session, TaskValidator validator) throws AdvertException, ParseException 
	{
		
		User user = null;
		user = (User) session.getAttribute("current-user");
		if(user==null)
		{
			request.setAttribute("val",false);

			return "add-tasks";
		}
		try {
			request.setAttribute("val", true);
			task.setDue_date(new Date(System.currentTimeMillis()));
			validator.validate(task, result);
			if(result.hasErrors())
	        {
				System.out.println(result);
				UserDAO userdao = new UserDAO();
				java.util.List<User> userList = userdao.list();
				request.setAttribute("users", userList);
	            return "add-tasks";
	        }
			user = (User) session.getAttribute("current-user");
			Long val = user.getUser_id();
			System.out.println(val);
			User user1 = (User) getSession().load(User.class, val);
			Date date_now = new Date(System.currentTimeMillis());
			task.setCreate_date(date_now);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(request.getParameter("due"));
			//task.setDue_date(sdf.parse(request.getParameter("date")));
			task.setDue_date(parsed);
			task.setUser(user1);
			taskdao.save(task);
			return "addedtask";
		} catch (AdvertException e) {
			System.out.println("Task cannot be Added: " + e.getMessage());
		}
		status.setComplete(); //mark it complete
		return "addedtask";
	}
	
	@GetMapping("/logout.htm")
	public String logoutGet(HttpSession session)
	{
		session.invalidate();
		return "index";
	}
	
	/*
	
	@GetMapping("/showcalendar/{first_name}+{Id}.htm")
	public String showTasksGet(ModelMap model,  TaskDAO taskdao, @PathVariable("Id") Long Id, @PathVariable("first_name") String first_name,
	Comment comment, CommentDAO commentdao, HttpSession session, HttpServletRequest request, UserDAO userdao) throws AdvertException
	{
		//user = (User) getSession().load(User.class, user_id);
		//Task task2 = (Task) getSession().load(Task.class, id);
		
		
		User user = (User) getSession().get(User.class, Id);
		String email = user.getEmail_id();
		List<Task> all_tasks = taskdao.assignList(email);
		List<String> all_users = userdao.getAssignList(all_tasks);
		request.setAttribute("all_tasks", all_tasks);
		request.setAttribute("all_users", all_users);
		return "showassigntasks";
	}
	
	*/
	@GetMapping("/updatetask/{id}.htm")
	public String updateTaskGet(ModelMap model, Task task, User user, TaskDAO taskdao, @PathVariable Long id, 
			HttpSession session, HttpServletRequest request, UserDAO userdao) throws AdvertException
	{
		task = (Task) getSession().load(Task.class, id);
		user = (User) session.getAttribute("current-user");
		java.util.List<User> userList = userdao.list();
		request.setAttribute("users", userList);
		//request.setAttribute("task",task);
		model.addAttribute("task",task);
		model.addAttribute("user",task.getUser());
		return "updatetask";
	}
	
	@PostMapping("/updatetask/{id}.htm")
	public String updateTaskPost(ModelMap model,@ModelAttribute("task")Task task, @ModelAttribute("user")User user, TaskDAO taskdao
			,BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, 
			@PathVariable Long id, Activity activity, ActivityDAO activitydao, TaskValidator validator) throws AdvertException, ParseException 
	{
		try {
			User user1  = (User) session.getAttribute("current-user");

			Task task2 = (Task) getSession().load(Task.class, id);
			User user2 = (User) getSession().load(User.class, user1.getUser_id());
			activity.setActivity_text("Summary updated from "+task2.getSummary()+" to "+task.getSummary()+"\n");
			activity.setActivity_text(activity.getActivity_text()+"\n"+"Issue type updated from "+task2.getIssue_type()+" to "+task.getIssue_type());
			activity.setActivity_text(activity.getActivity_text()+"\n"+ "Description updated from "+task2.getDescription()+" to "+task.getDescription());
			activity.setActivity_text(activity.getActivity_text()+"\n"+" Priority updated from "+task2.getPriority()+" to "+task.getPriority());
			activity.setActivity_text(activity.getActivity_text()+"\n"+" State updated from "+task2.getState()+" to "+task.getState());
			activity.setUpdate_time(new Date(System.currentTimeMillis()));
			activity.setTask(task2);
			activity.setUser(user2);
			activitydao.save(activity);
			task2.setSummary(task.getSummary());
			task2.setIssue_type(task.getIssue_type());
			task2.setDescription(task.getDescription());
			task2.setPriority(task.getPriority());
			task2.setState(task.getState());
			taskdao.update(task2);
			request.setAttribute("task2",task2);
			request.setAttribute("task2_id", task2.getTask_id());
			
		} catch (AdvertException e) {
			System.out.println("Task cannot be Added: " + e.getMessage());
		}
		status.setComplete(); //mark it complete
		return "updatedtask";
	}
	@GetMapping("/viewtask/{id}.htm")
	public String viewTaskGet(ModelMap model, Task task, User user, TaskDAO taskdao, @PathVariable Long id, 
			Comment comment, CommentDAO commentdao, HttpSession session, HttpServletRequest request, UserDAO userdao) throws AdvertException
	{
		task = (Task) taskdao.getTask(task,id);
		List<Comment> comments = (List<Comment>) commentdao.getComment(task);
		List<String> UserEmail = (List<String>) commentdao.getUserList(comments);
	    user = (User) session.getAttribute("current-user");
	    boolean val = true;
	    if(user==null)
	    {
	    	val = false;
	    	return "viewtask";
	    }
	    request.setAttribute("val", val);
		model.addAttribute("task",task);
		model.addAttribute("comment",comment);
		model.addAttribute("user",user);
		List<User> userlist = userdao.list();
		request.setAttribute("users",userlist);
		request.setAttribute("comments", comments);
		request.setAttribute("UserEmail",UserEmail);
		return "viewtask";
	}
	
	@PostMapping("/viewtask/{id}.htm")
	public String viewTaskPost(ModelMap model, @ModelAttribute("user")User user, @ModelAttribute("comment")Comment comment, Task task, TaskDAO taskdao, CommentDAO commentdao, @PathVariable Long id,
			BindingResult result, Activity activity,  SessionStatus status, HttpSession session, CommentValidator validator, HttpServletRequest request, UserDAO userdao, ActivityDAO activitydao) throws AdvertException
	{
		User user1 = null;

		try {

			if (request.getParameter("Comment") != null)
			{
		    	task = (Task) taskdao.getTask(task,id);
				
				user1 = (User) session.getAttribute("current-user");

				User user2 = (User) getSession().load(User.class, user1.getUser_id());
				Task task2 = (Task) getSession().load(Task.class, id);
		    	comment.setUser(user2);
		    	comment.setTask(task2);
		    	commentdao.saveOrUpdate(comment);
		    	activity.setActivity_text("\""+comment.getColumn_text()+"\""+ " comment is added");
		    	activity.setUpdate_time(new Date(System.currentTimeMillis()));
		    	activity.setTask(task2);
		    	activity.setUser(user2);
		    	activitydao.save(activity);
		    	String email_set = user.getEmail_id();
		    	System.out.println(email_set);
			}
		    
			if(request.getParameter("Update")!=null)
		    {
		    	task = (Task) taskdao.getTask(task,id);		
				user1 = (User) session.getAttribute("current-user");
				task.setAssigned_to(user.getEmail_id());
				User user2 = (User) getSession().load(User.class, user1.getUser_id());
				Task task2 = (Task) getSession().load(Task.class, id);
		    	activity.setActivity_text("Updated task assignment to "+user.getEmail_id());
		    	activity.setUpdate_time(new Date(System.currentTimeMillis()));
		    	activity.setTask(task2);
		    	activity.setUser(user2);
		    	activitydao.save(activity);
			
		    }
			//taskdao.updateAssign(user.getEmail_id());	
			
			
		} catch (AdvertException e) {	
			System.out.println("Comment cannot be Added: " + e.getMessage());
		}

		List<User> userlist = userdao.list();
		request.setAttribute("users",userlist);
	    request.setAttribute("val", true);
		List<Comment> comments = (List<Comment>) commentdao.getComment(task);
		List<String> UserEmail = (List<String>) commentdao.getUserList(comments);
	    user = (User) session.getAttribute("current-user");
		model.addAttribute("task",task);
		/*model.addAttribute("comment",comment);*/
		request.setAttribute("comments", comments);
		request.setAttribute("UserEmail",UserEmail);
		model.addAttribute("comment");
		//model.addAttribute("comment",comment);
		if(user==null) 
		{System.out.println("User is not set in session");}
		status.setComplete(); //mark it complete
		return "viewtask";
	}
	
		
	@GetMapping("/taskactivity/{id}.htm")
	public String taskActivityGet(ModelMap model, Task task, User user, Activity activity, @PathVariable Long id, 
			 HttpSession session, HttpServletRequest request, ActivityDAO activitydao, UserDAO userdao) throws AdvertException
	{
		
		activity = (Activity) getSession().load(Activity.class, id);
		user = (User) session.getAttribute("current-user");
		if(user==null)
		{
			request.setAttribute("val", false);
			return "activitytask";
		}
		else
		{
	
			request.setAttribute("val", true);
			task = (Task) getSession().load(Task.class, id);
			List<Activity> activities = (List<Activity>) activitydao.getActivity(task);
			List<String> UserEmail = (List<String>) activitydao.getUserList(activities);
			System.out.println(activities);
			System.out.println(UserEmail);
			request.setAttribute("id", id);
			request.setAttribute("activities",activities);
			request.setAttribute("UserEmail", UserEmail);
			return "activitytask";
		}
	}
	/*
	
	@GetMapping("/showmytasks.htm")
	public String showTasks(User user, UserDAO userdao, HttpServletRequest request, HttpSession session) throws AdvertException
	{
		User user1 = (User) session.getAttribute("current-user");
		if(user1!=null)
		{
			request.setAttribute("all_tasks", user1.getTasks());
			return "userhome";
		}
		return "loginas";	
	}
	*/
	


}

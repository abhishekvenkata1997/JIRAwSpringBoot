package com.me.jira.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import org.springframework.stereotype.Component;

import com.me.jira.pojo.Task;
import com.me.jira.pojo.Comment;
import com.me.jira.pojo.Activity;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.*;

@Component
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	@Column(nullable=false, unique=true, length=150)
	private String email_id;
	
	@Column(nullable=false, length=50)
	private String password;
	
	@Column(name="first_name", nullable=false, length=30)
	private String first_name;
	
	@Column(name="last_name", nullable=false, length=30)
	private String last_name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Task> tasks;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Comment> comments;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Activity> activities;

	
	public User() {
		super();
	}


	public User(Long user_id, String email_id, String password, String first_name, String last_name, List<Task> tasks,
			List<Comment> comments, List<Activity> activities) {
		super();
		this.user_id = user_id;
		this.email_id = email_id;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.tasks = tasks;
		this.comments = comments;
		this.activities = activities;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Activity> getActivities() {
		return activities;
	}


	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	
	
}

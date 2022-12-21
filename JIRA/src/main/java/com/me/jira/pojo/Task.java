package com.me.jira.pojo;


import java.util.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.me.jira.pojo.User;
import com.me.jira.pojo.Activity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Component
@Entity
public class Task {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long task_id;
	
	@Column(nullable=false, length=100)
	private String issue_type;
	
	@Column(nullable=false, length=200)
	private String summary;
	
	@Column(nullable=false, length=1000)
	private String description;
	
	@Column(nullable=false, length=30)
	private String priority;
	
	@Column(nullable=false, length=150)
	private String assigned_to;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, length=150)
	private Date due_date;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, length=150)
	private Date create_date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
    private User user;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="task")
	private List<Comment> comments;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="task")
	private List<Activity> activities;
	
	@Column(nullable=false, length=150)
	private String state;

	
	public Task() {
		super();
	}


	public Task(Long task_id, String issue_type, String summary, String description, String priority,
			String assigned_to, Date due_date, Date create_date, User user, List<Comment> comments,
			List<Activity> activities, String state) {
		super();
		this.task_id = task_id;
		this.issue_type = issue_type;
		this.summary = summary;
		this.description = description;
		this.priority = priority;
		this.assigned_to = assigned_to;
		this.due_date = due_date;
		this.create_date = create_date;
		this.user = user;
		this.comments = comments;
		this.activities = activities;
		this.state = state;
	}


	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public String getIssue_type() {
		return issue_type;
	}

	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}

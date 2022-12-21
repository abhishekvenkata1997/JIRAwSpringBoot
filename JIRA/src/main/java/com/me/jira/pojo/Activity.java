package com.me.jira.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.me.jira.pojo.User;

import com.me.jira.pojo.Task;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Activity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long activity_id;
	
	@Column(nullable=false, length=1500)
	private String activity_text;
	
	@Column(nullable=false)
	private Date update_time;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="task_id")
    private Task task;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="activity_user_id")
    private User user;

	
	public Activity	() {
		super();
	}

	public Activity(Long activity_id, String activity_text, Date update_time, Task task, User user) {
		super();
		this.activity_id = activity_id;
		this.activity_text = activity_text;
		this.update_time = update_time;
		this.task = task;
		this.user = user;
	}

	public Long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Long activity_id) {
		this.activity_id = activity_id;
	}

	public String getActivity_text() {
		return activity_text;
	}

	public void setActivity_text(String activity_text) {
		this.activity_text = activity_text;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}

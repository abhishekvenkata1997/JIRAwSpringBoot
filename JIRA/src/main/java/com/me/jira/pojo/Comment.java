	package com.me.jira.pojo;
	
	import java.util.List;
	
	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.ManyToOne;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.Table;
	import com.me.jira.pojo.User;
	import com.me.jira.pojo.Task;
	
	import org.springframework.stereotype.Component;
	
	
	@Component
	@Entity
	public class Comment {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long comment_id;
		
		@Column(nullable=false, length=1500)
		private String column_text;
		
		@ManyToOne(cascade = {CascadeType.ALL})
		@JoinColumn(name="task_id")
	    private Task task;
		
		@ManyToOne(cascade = {CascadeType.ALL})
		@JoinColumn(name="comment_user_id")
	    private User user;
		
		public Comment() {
			super();
		}
		
		public Comment(Long comment_id, String column_text, Task task, User user) {
			super();
			this.comment_id = comment_id;
			this.column_text = column_text;
			this.task = task;
			this.user = user;
		}
	
		public Long getComment_id() {
			return comment_id;
		}
	
		public void setComment_id(Long comment_id) {
			this.comment_id = comment_id;
		}
	
		public String getColumn_text() {
			return column_text;
		}
	
		public void setColumn_text(String column_text) {
			this.column_text = column_text;
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

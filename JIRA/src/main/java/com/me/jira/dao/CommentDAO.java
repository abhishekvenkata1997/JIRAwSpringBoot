package com.me.jira.dao;

import com.me.jira.dao.*;
import com.me.jira.pojo.Comment;
import com.me.jira.Exception.AdvertException;
import com.me.jira.pojo.Task;
import com.me.jira.pojo.User;
import com.me.jira.pojo.Comment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;


@SuppressWarnings("deprecation")
@Component
public class CommentDAO extends DAO{
	
	public CommentDAO()
	{
		
	}
	
    public void save(Comment comment) throws AdvertException {
        try {
            begin();
            getSession().save(comment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not add Comment " + comment.getColumn_text()+ e);
        }
    }
    
    public void saveOrUpdate(Comment comment) throws AdvertException {
        try {
            begin();
            getSession().saveOrUpdate(comment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not add Comment " + comment.getColumn_text()+ e);
        }
    }
    
    public void delete(Comment comment) throws AdvertException {
    	begin();
    	getSession().delete(comment);
    	commit();
    }
    
    public List<Comment> getComment(Task task) throws AdvertException {
    	
    	begin();
    	String hql = "FROM Comment C WHERE task_id=:task_id";
    	
    	Query<Comment> query = getSession().createQuery(hql).setParameter("task_id",task.getTask_id());
		List<Comment> comment_list = query.list();
		commit();
    	return comment_list;
	}
    
    public List<String> getUserList(List<Comment> comment_list) throws AdvertException {
    	
    	
    	Iterator<Comment> i = comment_list.iterator();
    	List<String> UserEmail = new ArrayList<>();
    	while(i.hasNext())	
    	{
    		Comment comment = i.next();
    		User user = (User) comment.getUser();
    		UserEmail.add(user.getEmail_id());
    	}

		return UserEmail;
    	
    }
}

package com.me.jira.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.jira.Exception.AdvertException;
import com.me.jira.pojo.Comment;
import com.me.jira.pojo.Task;
import com.me.jira.pojo.User;

@SuppressWarnings("deprecation")
@Component
public class UserDAO extends DAO {

    public UserDAO() {
    }

    public void save(User user) throws AdvertException {
        try {
            begin();
            getSession().save(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not add user " + user.getFirst_name(), e);
        }
    }
    
    public void delete(User user) throws AdvertException {
    	begin();
    	getSession().delete(user);
    	commit();
    }
    
	public List<User> list() {
		Query<User> query = getSession().createQuery("FROM User ORDER BY user_id");
		List<User> list = query.list();
		return list;
	}
    
	
    public List<String> getUserList(List<Task> task_list) throws AdvertException {
    	
    	
    	Iterator<Task> i = task_list.iterator();
    	List<String> UserEmail = new ArrayList<>();
    	while(i.hasNext())
    	{
    		Task task = i.next();
    		User user = (User) task.getUser();
    		UserEmail.add(user.getEmail_id());
    	}

		return UserEmail;

    }

    
    public List<String> getAssignList(List<Task> task_list) throws AdvertException {
    	
    	
    	Iterator<Task> i = task_list.iterator();
    	List<String> UserEmail = new ArrayList<>();
    	while(i.hasNext())
    	{
    		Task task = i.next();
    		UserEmail.add(task.getUser().getEmail_id());
    	}
		return UserEmail;
    }
	
    public User getUser(String email_id) throws AdvertException
    {
    	try 
    	{
    		begin();
    		User user = null;
    	
    		user = (User) getSession().createQuery("FROM User WHERE email_id = :email_id").setParameter("email_id",email_id).uniqueResult();
    		return user;
    	}
    	catch(HibernateException e)
    	{
    		rollback();
    		throw new AdvertException("Could not validate user " + email_id, e);
    	}
    }
    public User validate(String email_id, String password) throws AdvertException
    {
    	try 
    	{
    		begin();
    		User user = null;
    	
    		user = (User) getSession().createQuery("FROM User WHERE email_id = :email_id").setParameter("email_id",email_id).uniqueResult();
		
    		if(user != null && user.getPassword().equals(password))
    		{
    			System.out.println("User validated");
    			commit();
    			return user;		
    		}
    	}
    	catch(HibernateException e)
    	{
    		rollback();
    		throw new AdvertException("Could not validate user " + email_id, e);
    	}
    	
    	return null;
    }
}
package com.me.jira.dao;

import com.me.jira.dao.*;
import com.me.jira.pojo.Activity;
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
public class ActivityDAO extends DAO{
	
	public ActivityDAO()
	{
		
	}
	
    public void save(Activity activity) throws AdvertException {
        try {
            begin();
            getSession().save(activity);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not add activity " + activity.getActivity_text()+ e);
        }
    }
    
    public void saveOrUpdate(Activity activity) throws AdvertException {
        try {
            begin();
            getSession().saveOrUpdate(activity);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not add Comment " + activity.getActivity_text()+ e);
        }
    }
    
    public List<Activity> getActivity(Task task) throws AdvertException {
    	
    	begin();
    	String hql = "FROM Activity A WHERE task_id=:task_id";
    
    	Query<Activity> query = getSession().createQuery(hql).setParameter("task_id",task.getTask_id());
		List<Activity> activity_list = query.list();
		commit();
    	return activity_list;
    }
    
    public void delete(Activity activity) throws AdvertException {
    	begin();
    	getSession().delete(activity);
    	commit();
    }

	public List<String> getUserList(List<Activity> activity_list) throws AdvertException {
    	
    	
    	Iterator<Activity> i = activity_list.iterator();
    	List<String> UserEmail = new ArrayList<>();
    	while(i.hasNext())	
    	{
    		Activity activity = i.next();
    		User user = (User) activity.getUser();
    		UserEmail.add(user.getEmail_id());
    	}
		return UserEmail;
    	
    }
    
}

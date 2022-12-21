package com.me.jira.dao;

import com.me.jira.dao.*;
import com.me.jira.Exception.AdvertException;
import com.me.jira.pojo.Task;
import com.me.jira.pojo.User;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;



@SuppressWarnings("deprecation")
@Component
public class TaskDAO extends DAO {

    public TaskDAO() {
    }
    
    public void save(Task task) throws AdvertException {
        try {
            begin();
            getSession().save(task);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not add task " + task.getSummary()+ e);
        }
    }
    
    public void update(Task task) throws AdvertException {
        try {
            begin();
            getSession().update(task);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not update task " + task.getSummary()+ e);
        }
    }
    
    public void saveOrUpdate(Task task) throws AdvertException {
        try {
            begin();
            getSession().saveOrUpdate(task);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not update/save task " + task.getSummary()+ e);
        }
    }
    
    public void delete(Task task) throws AdvertException {
    	begin();
    	getSession().delete(task);
    	commit();
    }
    
	public List<Task> list(Long user_id) {
		
		String hql = "FROM Task WHERE user_id = :user_id";
    	
    	Query<Task> query = getSession().createQuery(hql).setParameter("user_id", user_id);
		List<Task> list = query.list();
		return list;
	}
	
	public List<Task> assignList(String email)
	{
		
		String hql = "FROM Task T WHERE T.assigned_to=:email";
		
		Query<Task> query = getSession().createQuery(hql).setParameter("email", email);
		List<Task> list = query.list();
		return list;
	}
	
	public List<Task> fulllist() {
		
		String hql = "FROM Task";
    	
    	Query<Task> query = getSession().createQuery(hql);
		List<Task> list = query.list();
		return list;
	}
	
    
    public Task getTask(Task task, Long id) throws AdvertException {
    	
    	
    	String hql = "FROM Task T WHERE T.id = :task_id";
    	
    	task = (Task) getSession().createQuery(hql).setParameter("task_id",id).uniqueResult();
    	return task;
	}

}
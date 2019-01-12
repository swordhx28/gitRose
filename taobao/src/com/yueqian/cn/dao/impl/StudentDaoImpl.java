package com.yueqian.cn.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yueqian.cn.dao.entity.User;

public class StudentDaoImpl implements StudentDao {

	public int save(User user) {
		int rows = -1;
		Configuration config= null;
		SessionFactory sf = null;
		Transaction tx= null;
		Session session=null;
		
		try {
			 config = new Configuration().configure("hibernateStudent.cfg.xml");
			 sf = config.buildSessionFactory();
			session = sf.openSession();
			 tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			rows = 1;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.rollback();
			rows = -1;
		}finally {
			session.close(); 
		}
		
		
		return rows;
	}

	@Override
	public int delete(User user) {
		int rows = -1;
		Configuration config= null;
		SessionFactory sf = null;
		Transaction tx= null;
		Session session=null;
		
		try {
			 config = new Configuration().configure("hibernateStudent.cfg.xml");
			 sf = config.buildSessionFactory();
			session = sf.openSession();
			 tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			rows = 1;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.rollback();
			rows = -1;
		}finally {
			session.close(); 
		}
		
		return rows;
	}

	@Override
	public int update(User user) {
		int rows = -1;
		Configuration config= null;
		SessionFactory sf = null;
		Transaction tx= null;
		Session session=null;
		
		try {
			 config = new Configuration().configure("hibernateStudent.cfg.xml");
			 sf = config.buildSessionFactory();
			session = sf.openSession();
			 tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			rows = 1;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.rollback();
			rows = -1;
		}finally {
			session.close(); 
		}
		
		return rows;
	}

	@Override
	public User getById(int id) {
		User user = null;
		Configuration config= null;
		SessionFactory sf = null;
		Transaction tx= null;
		Session session=null;
		
		try {
			 config = new Configuration().configure("hibernateStudent.cfg.xml");
			 sf = config.buildSessionFactory();
			session = sf.openSession();
			 tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			tx.commit();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.rollback();
			
		}finally {
			session.close(); 
		}
		
		return user;
	}

}

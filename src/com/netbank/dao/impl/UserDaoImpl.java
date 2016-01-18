package com.netbank.dao.impl;

import java.sql.SQLException;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.netbank.dao.UserDao;
import com.netbank.entity.*;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	


	public boolean updateAccount(Account account) {
		super.getHibernateTemplate().merge(account);
		return true;
	}


	public Admin getAdmin(String username) {
		String hql="from Admin as a where a.username=:username";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString("username", username);
		return (Admin) query.uniqueResult();
	}
	

	public boolean modifyAdmin(Admin admin) {
		super.getHibernateTemplate().update(admin);
		return true;
	}
	
	

	public Account searchAccounts(final Account account){
		
	 return	(Account) super.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c=session.createCriteria(Account.class);
				if(account.getUsername()!=null&&!"".equals(account.getUsername())){
					c.add(Restrictions.like("username", account.getUsername(),MatchMode.ANYWHERE));
				}
				c.addOrder(Order.asc("accountid"));
				return c.list();
			}
			
		});
	}
	

	public Account getAccount(int id){
		return (Account) super.getHibernateTemplate().get(Account.class, id);
	}
	
	

	public Account getAccount(String username) {
		String hql="from Account as a where a.username=:username";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString("username", username);
		return (Account) query.uniqueResult();
	}
	

	public List getAllAcconts() {
		String hql="from Account ";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
		
	}

	public boolean addAccount(Account account) {
		super.getHibernateTemplate().save(account);
		return true;
	}

	public boolean delAccount(Account account) {
		super.getHibernateTemplate().delete(account);
		return true;
	}
	

	public Status getStatus(String name) {
		String hql="from Status as s where s.name=:name";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString("name", name);
		return (Status) query.uniqueResult();
	}

	public Status getStatus(int id) {
		return (Status) super.getHibernateTemplate().get(Status.class, id);
	}
	

	public void reflush(Account account){
		super.getHibernateTemplate().refresh(account);
	}
}

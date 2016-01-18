package com.netbank.dao.impl;

import java.sql.SQLException;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.netbank.dao.TransactionDao;
import com.netbank.entity.*;


public class TransactionDaoImpl extends HibernateDaoSupport implements TransactionDao{
	/* (non-Javadoc)
	 * @see com.mybank.dao.TransactionDao#addLog(com.mybank.entity.TransactionLog)
	 */
	

	public boolean addLog(TransactionLog log) {
		super.getHibernateTemplate().save(log);
		return true;
	}
	

	public List getLogs(final Account account,final int page) {
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		Criteria c=session.createCriteria(TransactionLog.class);
		c.add(Restrictions.or(Restrictions.eq("account", account), Restrictions.eq("otherid", account.getAccountid())));
		c.addOrder(Order.desc("id"));
		c.setFirstResult(10*(page-1));
		c.setMaxResults(10);
		return c.list();
		}
	} );
	}
	

	public TransactionType getTransactionType(String name) {
		String hql="from TransactionType t where t.name=:name";
		Query query=super.getSession().createQuery(hql);
		query.setString("name",name);
		return (TransactionType) query.uniqueResult();		
	}
	

	public Integer getCountOfLogs(Account account){
		String sql="select count(*) from Transaction_Log where (accountid="
			+account.getAccountid()+" or otherid="+account.getAccountid()+")";
		Query query=this.getSession().createSQLQuery(sql);
		Integer count= Integer.parseInt(query.uniqueResult().toString()) ;
		return count;
	}	
}

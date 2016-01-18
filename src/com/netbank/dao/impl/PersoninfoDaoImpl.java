package com.netbank.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.netbank.dao.PersoninfoDao;
import com.netbank.entity.*;

public class PersoninfoDaoImpl extends HibernateDaoSupport implements PersoninfoDao {


	public void modifyPersoninfo(Personinfo personinfo) {
		super.getHibernateTemplate().update(personinfo);		
	}


	public boolean add(Personinfo personinfo) {
		super.getHibernateTemplate().save(personinfo);
		return true;
	}
	

	public List getAllPersoninfo() {
		String hql="from Personinfo";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	

	public List searchPersoninfo(Status status) {
		String hql="from Personinfo p where p.account.status.id="+status.getId();
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}		
	

	public List searchPersoninfo(final Personinfo personinfo) {
		 return	 super.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c=session.createCriteria(Personinfo.class);
				if(personinfo.getRealname()!=null&&!"".equals(personinfo.getRealname())){
					if(personinfo.getCardid()!=null){
						c.add(Restrictions.or(Restrictions.eq("realname",personinfo.getRealname()),Restrictions.eq("cardid",personinfo.getCardid())));
					}else{
						c.add(Restrictions.like("realname",personinfo.getRealname(),MatchMode.ANYWHERE));
					}					
				}
				c.addOrder(Order.asc("id"));
				return c.list();
			}			
		});
	}

}

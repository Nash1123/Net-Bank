package com.netbank.biz.impl;

import java.util.ArrayList;
import java.util.List;
import com.netbank.biz.*;
import com.netbank.dao.*;
import com.netbank.entity.*;

public class PersoninfoBizImpl implements PersoninfoBiz {

	PersoninfoDao personinfoDao;
	public void setPersoninfoDao(PersoninfoDao personinfoDao) {
		this.personinfoDao = personinfoDao;
	}

	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	

	public boolean modifyPersoninfo(Personinfo personinfo) {
		personinfoDao.modifyPersoninfo(personinfo);
		return true;
	}
	

	public boolean add(Personinfo personinfo) {
		return personinfoDao.add(personinfo);
	}
	

	public List getAllPersoninfo() {
		return personinfoDao.getAllPersoninfo();
	}
	

	public List searchPersoninfo(Personinfo personinfo) {
		return personinfoDao.searchPersoninfo(personinfo);
	}
	

	public List searchPersoninfo(Status status)
	{
		List users =new ArrayList();
		if(status.getId()!=0){		

			status=userDao.getStatus(status.getId());
			users=personinfoDao.searchPersoninfo(status);
		}else{

			users= personinfoDao.getAllPersoninfo();
		}
		return users;
	}
}

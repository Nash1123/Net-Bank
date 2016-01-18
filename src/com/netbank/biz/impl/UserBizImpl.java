package com.netbank.biz.impl;

import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netbank.biz.UserBiz;
import com.netbank.dao.UserDao;
import com.netbank.entity.*;

@Transactional
public class UserBizImpl implements UserBiz {

	UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public boolean modifyAccount(Account account) {
		return userDao.updateAccount(account);
	}
	

	@Transactional(readOnly=true)
	public Account getAccount(int accountid) {
		return userDao.getAccount(accountid);
	}
	

	public Account getAccount(String username) {
		return userDao.getAccount(username);
	}
	

	public Admin getAdmin(String username) {
		return userDao.getAdmin(username);
	}
	

	public List getAllAccounts() {
		return userDao.getAllAcconts();
	}
	

	public boolean addAccount(Account account) {
		Status status=userDao.getStatus("啟用");
		account.setStatus(status);
		return userDao.addAccount(account);
	}
	

	public boolean modifyAdmin(Admin admin) {
		return userDao.modifyAdmin(admin);
	}

	public boolean delAccount(int id) {

		Account account=userDao.getAccount(id);

		return userDao.delAccount(account);
	}

	public Account searchAccounts(Account account) {
		return userDao.searchAccounts(account);
	}

	public Status getStatus(String name) {
		return userDao.getStatus(name);
	}

	public Status getStatus(int id) {
		return userDao.getStatus(id);
	}
	

	public void enabled(int id) {

		Account account = userDao.getAccount(id);

		Status status = userDao.getStatus("啟用");
		account.setStatus(status);

		userDao.updateAccount(account);		
	}
	

	public void locking(int id) {

		Account account = userDao.getAccount(id);

		Status status = userDao.getStatus("凍結");		
		account.setStatus(status);

		userDao.updateAccount(account);
		
	}

	public void reflush(Account account) {
		userDao.reflush(account);		
	}

}

package com.netbank.biz.impl;

import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.netbank.biz.*;
import com.netbank.dao.*;
import com.netbank.entity.*;

public class TransactionBizImpl implements TransactionBiz {

	private TransactionDao transactionDao;
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	

	public List getLogs(Account account,int page) {		
		return transactionDao.getLogs(account,page);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public boolean transfer(TransactionLog log) {

		Account other=userDao.getAccount(log.getOtherid());

		Account self=log.getAccount();
		if(other!=null){

			self.setBalance(log.getAccount().getBalance()-log.getTrMoney());

			other.setBalance(other.getBalance()+log.getTrMoney());

			userDao.updateAccount(self);

			userDao.updateAccount(other);

			TransactionType type = transactionDao.getTransactionType("轉賬");
			log.setTransactionType(type);

			return  transactionDao.addLog(log);
		}
		return false;		
	}
	

	public boolean deposit(TransactionLog log) {

		Account self=log.getAccount();

		self.setBalance(log.getAccount().getBalance()+log.getTrMoney());

		userDao.updateAccount(self);

		TransactionType type = transactionDao.getTransactionType("存款");
		log.setTransactionType(type);
		log.setOtherid(self.getAccountid());

		return transactionDao.addLog(log);
	}
	

	public boolean withdrawal(TransactionLog log){

		Account self=log.getAccount();

		self.setBalance(log.getAccount().getBalance()-log.getTrMoney());

		userDao.updateAccount(self);

		TransactionType type = transactionDao.getTransactionType("取款");
		log.setTransactionType(type);
		log.setOtherid(self.getAccountid());

		return transactionDao.addLog(log);
	}
	

	public Pager getPagerOfLogs(Account account) {

		int count=transactionDao.getCountOfLogs(account);

		Pager pager=new Pager();

		pager.setPerPageRows(10);

		pager.setRowCount(count);

		return pager;
	}
	
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
}

package com.netbank.biz;

import java.util.List;

import com.netbank.entity.*;



public interface TransactionBiz {


	public List getLogs(Account account,int page);


	public boolean transfer(TransactionLog log);
	

	public boolean deposit(TransactionLog log);
	

	public boolean withdrawal(TransactionLog log);	
	

	public Pager getPagerOfLogs(Account account);
	
}
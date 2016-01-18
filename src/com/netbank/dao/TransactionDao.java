package com.netbank.dao;

import java.util.List;

import com.netbank.entity.*;


public interface TransactionDao {


	public boolean addLog(TransactionLog log);


	public List getLogs(final Account account,int page);
	

	public TransactionType getTransactionType(String name);
	

	public Integer getCountOfLogs(Account account);
}
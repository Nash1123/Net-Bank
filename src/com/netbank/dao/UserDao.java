package com.netbank.dao;

import java.util.List;

import com.netbank.entity.*;


public interface UserDao {


	public  Account getAccount(int accountid);
	

	public Account getAccount(String username);
	
	public List getAllAcconts();
	
	public Account searchAccounts(final Account account);
	

	public boolean updateAccount(Account account);
	

	public boolean addAccount(Account account);
	

	public boolean delAccount(Account account);
	

	public void reflush(Account account);
	

	public Status getStatus(String name);
	
	public Status getStatus(int id);
	

	public Admin getAdmin(String username);
	
	
	public boolean modifyAdmin(Admin admin);
}
package com.netbank.biz;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netbank.entity.*;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public interface UserBiz {


	public boolean addAccount(Account account);
	

	public boolean delAccount(int id);


	public abstract boolean modifyAccount(Account account);
	
	

	public Account searchAccounts(final Account account);
	

	@Transactional(readOnly = true)
	public abstract Account getAccount(int accountid);
	

	public abstract Account getAccount(String username);
	
	public List getAllAccounts();

	public void enabled(int id);

	public void locking(int id);
	

	public Status getStatus(String name);
	
	public Status getStatus(int id);
	


	public boolean modifyAdmin(Admin admin);
	

	public abstract Admin getAdmin(String username);

	public abstract void reflush(Account account);

}
package com.netbank.action;

import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.netbank.biz.*;
import com.netbank.entity.*;
import com.opensymphony.xwork2.ActionSupport;

public class Transaction extends ActionSupport implements RequestAware,SessionAware {

	private UserBiz userBiz;
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	private TransactionBiz transactionBiz;
	public void setTransactionBiz(TransactionBiz transactionBiz) {
		this.transactionBiz = transactionBiz;
	}	
	private Map<String, Object> request;
	private Map<String, Object> session;

	private Account account;

	private TransactionLog log;
	public TransactionLog getLog() {
		return log;
	}
	public void setLog(TransactionLog log) {
		this.log = log;
	}

	private Pager pager;
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	

	public String list(){

		int curPage=pager.getCurPage();

		List<TransactionLog> logs = transactionBiz.getLogs(account,curPage);		

		pager=transactionBiz.getPagerOfLogs(account);

		pager.setCurPage(curPage);
		request.put("logs", logs);
		return "success";
	}
	

	public String transfer(){

		if(isEnable()){

			log.setAccount(account);
			session.put("user", account);

			return isSuccess(transactionBiz.transfer(log));
		}
		return "message";		
	}
	

	public String deposit(){

		if(isEnable()){

			log.setAccount(account);
			session.put("user", account);

			return isSuccess(transactionBiz.deposit(log));
		}
		return "message";
	}
	

	public String withdrawal(){

		if(isEnable()){

			log.setAccount(account);
			session.put("user", account);

			return isSuccess(transactionBiz.withdrawal(log));
		}
		return "message";
	}
	

	private boolean isEnable(){

		userBiz.reflush(account);
		if(account.getStatus().getName().equals("reject!")){
			request.put("message", "error<br>");
			return false;
		}
		return true;
	}
	
	
	private String isSuccess(boolean flag){
			if(flag)
			{ 
				request.put("message","success!");
				return "message";
			}
			request.put("message","failed！<a href='javascript:history.go(-1)'>return</a>");
			return "message";
		
	}
	
	@Override
	public void validate() {
		super.validate();
	}
	
	public void validateWithdrawal(){
	
		if(log.getTrMoney()>account.getBalance()){
			this.addFieldError("log.trMoney","error");
		}
	}
	
	
	public void validateTransfer(){
		if(log.getOtherid().intValue()==account.getAccountid().intValue()){
			this.addFieldError("log.otherid","error！");
		}
		if(userBiz.getAccount(log.getOtherid())==null){
			this.addFieldError("log.otherid","error！");
		}
		if(log.getTrMoney()>account.getBalance()){
			this.addFieldError("log.trMoney","error！");
		}
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
		account=(Account)session.get("user");
	}
	public TransactionBiz getTransactionBiz() {
		return transactionBiz;
	}	
	public UserBiz getUserBiz() {
		return userBiz;
	}

}

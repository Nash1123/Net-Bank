package com.netbank.action;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.netbank.biz.UserBiz;
import com.netbank.entity.*;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements RequestAware,SessionAware {

	@Resource private UserBiz userBiz;
	Map<String, Object> request;
	Map<String, Object> session;

	private Account account;
	private Personinfo personinfo;
	private Password pwd;
	
	public String logout(){
		session.remove("user");
		session.remove("personinfo");
		return "success";
	}
	


	public String login(){

		personinfo=(Personinfo) account.getPersoninfos().iterator().next();

		session.put("user", account);

		session.put("personinfo",personinfo);

		return "success";
	}
	

	public String changepwd(){
			account.setPassword(pwd.getNewpwd());
			if(userBiz.modifyAccount(account)){
				session.put("user", account);
				request.put("message", "success！");
				return "message";
			}
			request.put("message", "error！");
			return "message";
		
	}
	
	
	public void validateChangepwd(){
		account=(Account) session.get("user");
		if(!pwd.getOldpwd().equals(account.getPassword())){
			this.addFieldError("pwd.oldpwd", "error");
		}
		if(!pwd.getNewpwd().equals(pwd.getConfirmpwd())){
			this.addFieldError("pwd.confirmpwd", "error");
		}
	}
	
	
	public void validateLogin(){
		Account a = userBiz.getAccount(account.getUsername());
		if(a==null){
			this.addFieldError("username", "error");
		}else{
			if(!account.getPassword().equals(a.getPassword())){
				this.addFieldError("password", "error");
			}
		}
		account=a;		
	}

	public Personinfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Password getPwd() {
		return pwd;
	}

	public void setPwd(Password pwd) {
		this.pwd = pwd;
	}

	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}

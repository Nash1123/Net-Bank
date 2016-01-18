package com.netbank.action;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.netbank.biz.*;
import com.netbank.entity.*;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements RequestAware,
		SessionAware {
	
	@Resource private UserBiz userBiz;
	
	@Resource private PersoninfoBiz personinfoBiz;
	Map<String, Object> request;
	Map<String, Object> session;
	
	private Admin admin;	
	private Account account;
	
	private Personinfo personinfo;
	private Password pwd;
	private int id;
	private Status status;
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public void validateLogin(){
	
		Admin a=userBiz.getAdmin(admin.getUsername());
		if(a==null){
			this.addFieldError("username", "error");
		}else{
			if(!admin.getPassword().equals(a.getPassword())){
				this.addFieldError("password", "error");
			}
			admin=a;
		}
	}	
	
	public String login(){
		
		if(admin!=null){
			session.put("admin",admin);
			return "success";
		}
		return "login";
	}
	
	
	public String listUsers(){
	
		List users=personinfoBiz.searchPersoninfo(status);
		request.put("users",users);
		return "users";
		
	}
	
	
	public String logout(){
		session.remove("admin");
		return "login";
	}
	
	
	public void validateKaihu(){
		if(userBiz.getAccount(account.getUsername())!=null){
			request.put("message", "error");
		}
	
		List list = personinfoBiz.searchPersoninfo(personinfo);
	
		if(list.size()>0){
			this.addFieldError("personinfo.cardid", "error");
		}		
	}	
	
	public String kaihu(){
	
		userBiz.addAccount(account);
	
		account = userBiz.getAccount(account.getUsername());
		personinfo.setAccount(account);
		personinfoBiz.add(personinfo);
		request.put("message", "success");
		return "message";		
	}
	
	
	public void validateChangepwd(){
		admin=(Admin)session.get("admin");
		if(!pwd.getOldpwd().equals(admin.getPassword())){
			this.addFieldError("pwd.oldpwd", "error");
		}
		if(!pwd.getNewpwd().equals(pwd.getConfirmpwd())){
			this.addFieldError("pwd.confirmpwd", "error");
		}
	}	
	
	public String changepwd(){
		admin.setPassword(pwd.getNewpwd());
		if(userBiz.modifyAdmin(admin)){
			session.put("admin",admin);
			request.put("message", "success!");
			return "message";
		}
		request.put("message", "failed!");
		return "message";
	}
	
		
	public String del(){
	
		userBiz.delAccount(id);
		return "list";
	}
		
	public String search(){
		List users=personinfoBiz.searchPersoninfo(personinfo);
		request.put("users",users);
		return "users";
	}
	
	public String enabled(){
		userBiz.enabled(id);
		return "list";
	}
	
	public String locking(){
		userBiz.locking(id);
		return "list";
	}	
	
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Personinfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
	}
	public Password getPwd() {
		return pwd;
	}
	public void setPwd(Password pwd) {
		this.pwd = pwd;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}

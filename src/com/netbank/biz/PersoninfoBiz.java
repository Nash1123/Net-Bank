package com.netbank.biz;

import java.util.List;

import com.netbank.entity.*;


public interface PersoninfoBiz {

	public boolean modifyPersoninfo(Personinfo personinfo);
	

	public boolean add(Personinfo personinfo);


	public List getAllPersoninfo();
	

	public List searchPersoninfo(Personinfo personinfo);
	

	public List searchPersoninfo(Status status);
}

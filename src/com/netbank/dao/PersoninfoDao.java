package com.netbank.dao;

import java.util.List;

import com.netbank.entity.*;

public interface PersoninfoDao {

	public void modifyPersoninfo(Personinfo personinfo);
	

	public List getAllPersoninfo();
	

	public boolean add(Personinfo personinfo);


	public List searchPersoninfo(Personinfo personinfo);
	

	public List searchPersoninfo(Status status);
}

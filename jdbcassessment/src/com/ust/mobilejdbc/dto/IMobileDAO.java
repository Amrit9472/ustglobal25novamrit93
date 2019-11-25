package com.ust.mobilejdbc.dto;

import java.util.List;

import com.ust.mobilejdbc.dao.ContactInfo;

public interface IMobileDAO {
	public  int addContact(ContactInfo info);
	public  List<ContactInfo> getConatct();
	public  ContactInfo searchContact(String name);
	public int deleteContact(String name);
	public int editContact(ContactInfo info,String name);
}

package com.capgemini.contactbook.service;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.capgemini.contactbook.dao.ContactBookDao;
import com.capgemini.contactbook.dao.ContactBookDaoImpl;
import com.capgemini.contactbook.exceptions.ContactBookException;
import com.igate.contactbook.bean.EnquiryBean;

public class ContactBookServiceImpl implements ContactBookService{
	ContactBookDao dao=new ContactBookDaoImpl();
	@Override
	public int addEnquiry(EnquiryBean enqry) throws SQLException, ContactBookException {
		int enqryId;
//		if(enqry.getfName() != null && enqry.getpLocation()!=null && enqry.getpDomain()!=null && enqry.getContactNo()!=null){
		enqryId=dao.addEnquiry(enqry);
			return enqryId;
	}

	@Override
	public EnquiryBean getEnquiryDetails(int EnquiryId) throws SQLException {
		EnquiryBean enquiryBean = null;
		try {
			enquiryBean = dao.getEnquiryDetails(EnquiryId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Server down");
		}
		return enquiryBean;
	}

	@Override
	public boolean ValidEnquiry(EnquiryBean enqry) {
		String contact=enqry.getContactNo();
		boolean c=ValidatecontactNo(contact);
		String fname=enqry.getfName();
		boolean d=ValidateFirstName(fname);
		String lname=enqry.getlName();
		boolean e=ValidateLastName(lname);
		String pLocation=enqry.getpLocation();
		boolean f=ValidatePLocation(pLocation);
		String pDomain=enqry.getpDomain();
		boolean t=ValidatePDomain(pDomain);
		if(c==true&&d==true&&e==true&&f==true&&t==true)
		return true;
		else
			return false;
	}
	public boolean ValidatecontactNo(String contactNo){
		boolean c;
		if(contactNo.length()==10)
			return true;
		else
			return false;
	}
	public boolean ValidateFirstName(String fName){
	if(fName.length()==0)
		return false;
	else
		return true;
	}
	public boolean ValidateLastName(String lName){
		if(lName.length()==0)
			return false;
		else
			return true;
	}
	public boolean ValidatePLocation(String pLocation){
		if(pLocation.length()==0)
			return false;
		else
			return true;
	
	}
	public boolean ValidatePDomain(String pDomain){
		if(pDomain.length()==0)
			return false;
		else
			return true;
	}
}

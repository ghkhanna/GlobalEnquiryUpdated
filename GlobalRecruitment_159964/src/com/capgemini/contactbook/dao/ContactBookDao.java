package com.capgemini.contactbook.dao;

import java.sql.SQLException;

import com.capgemini.contactbook.exceptions.ContactBookException;
import com.igate.contactbook.bean.EnquiryBean;

public interface ContactBookDao {
	public int addEnquiry(EnquiryBean enqry) throws SQLException, ContactBookException;
	public EnquiryBean getEnquiryDetails(int EnquiryId) throws SQLException, ContactBookException;
}

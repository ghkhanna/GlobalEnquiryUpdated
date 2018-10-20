package com.capgemini.contactbook.service;
import java.sql.SQLException;

import com.capgemini.contactbook.exceptions.ContactBookException;
import com.igate.contactbook.bean.EnquiryBean;
public interface ContactBookService {
	public int addEnquiry(EnquiryBean enqry) throws SQLException, ContactBookException;
	public EnquiryBean getEnquiryDetails(int EnquiryId) throws SQLException;
	public boolean ValidEnquiry(EnquiryBean enqry);
}

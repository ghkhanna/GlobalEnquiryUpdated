package com.capgemini.contactbook.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.capgemini.contactbook.exceptions.ContactBookException;
import com.capgemini.contactbook.util.ConnectionProvider;
import com.igate.contactbook.bean.EnquiryBean;
public class ContactBookDaoImpl implements ContactBookDao{
	private Connection conn = new ConnectionProvider().getDBConnection();
	private static final Logger logger= org.apache.log4j.Logger.getLogger(ContactBookDaoImpl.class);
	@Override
	public int addEnquiry(EnquiryBean enqry) throws SQLException,
			ContactBookException {
		try{
			conn.setAutoCommit(false);
			String sql = "INSERT INTO enquiry(enqryid,firstname,lastname,contactno,domain,city)"
					+ "VALUES(enquiries.nextval,?,?,?,?,?)";
					PreparedStatement pstmt1 = conn.prepareStatement(sql);
			
					pstmt1.setString(1, enqry.getfName());
					pstmt1.setString(2, enqry.getlName());
					pstmt1.setString(3, enqry.getContactNo());
					pstmt1.setString(4, enqry.getpDomain());
					pstmt1.setString(5, enqry.getpLocation());
					pstmt1.executeUpdate();
					conn.commit();
					PreparedStatement pstmt2 = conn.prepareStatement("SELECT MAX(enqryid) FROM enquiry");
					ResultSet rs = pstmt2.executeQuery();
					rs.next();
					int enquiryId= rs.getInt(1);
					logger.info("Add Enquiry implemented");
					return enquiryId;
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
			logger.error(e.getMessage()+" "+e.getCause()+" "+e.getErrorCode());
			throw e;
		}finally{
			conn.setAutoCommit(true);
		}
	}
	@Override
	public EnquiryBean getEnquiryDetails(int EnquiryId) throws SQLException,
			ContactBookException {
		try {
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM enquiry WHERE enqryid="+EnquiryId;
			PreparedStatement pstmt2 = conn.prepareStatement(sql);
			ResultSet demandRS = pstmt2.executeQuery();
			if(demandRS.next()){
				String fName = demandRS.getString("firstname");
				String lName = demandRS.getString("lastname");
				String contactNo = demandRS.getString("contactno");
				String pDomain = demandRS.getString("domain");
				String pLocation = demandRS.getString("city");
				EnquiryBean enquirybean1=new EnquiryBean(EnquiryId, fName, lName, contactNo, pLocation, pDomain);
				
				System.out.println(enquirybean1);
				logger.info("Get Enquiry implemented");
				return enquirybean1;
			}
		} catch (SQLException e) {
			try {
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e.getMessage()+" "+e.getCause()+" "+e.getErrorCode());
				e1.printStackTrace();
			}
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
		}
		return null;
	}
}

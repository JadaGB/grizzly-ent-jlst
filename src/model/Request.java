package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DBConnection;

public class Request {
	
	private int reqID;
	private Customer cName;
	private Equipment eName;
	private Date requestDate;
	private float quotation;
	private boolean confirmed;
	
	private PreparedStatement del;
	private String query;
	private Connection myConn;
	
	public Request() {
		this.reqID = 0000;
		this.cName = new Customer();
		this.eName = new Equipment();
		this.requestDate = new Date();
		this.quotation = 0;
		this.confirmed = false;
		
		query="Delete request = ? WHERE id = ?";
		myConn= DBConnection.getConnection();
		try {
			del=myConn.prepareStatement(query);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

	public Request(int reqID, Customer cName, Equipment eName, Date requestDate, float quotation, boolean confirmed) {
		this.reqID = reqID;
		this.cName = cName;
		this.eName = eName;
		this.requestDate = requestDate;
		this.quotation = quotation;
		this.confirmed = confirmed;
	}
	
	public Request(Request obj) {
		this.reqID = obj.reqID;
		this.cName = obj.cName;
		this.eName = obj.eName;
		this.requestDate = obj.requestDate;
		this.quotation = obj.quotation;
		this.confirmed = obj.confirmed;
	}

	public int getReqID() {
		return reqID;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}

	public Customer getcName() {
		return cName;
	}

	public void setcName(Customer cName) {
		this.cName = cName;
	}

	public Equipment geteName() {
		return eName;
	}

	public void seteName(Equipment eName) {
		this.eName = eName;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public float getQuotation() {
		return quotation;
	}

	public void setQuotation(float quotation) {
		this.quotation = quotation;
	}
	
	public boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public void delete(String reqId) {
		try {
			del.setString(1, reqId);
			del.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Request ID: " + reqID + ", Customer Name: " + cName + ", Equipment Name: " + eName + ", Request Date: " + requestDate
				+ ", Quotation; " + quotation + ", Confirmed; " + confirmed +"\n";
	}
	
	
	
}

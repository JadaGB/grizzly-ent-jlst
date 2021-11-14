package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
//import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Scheduled extends Request {
	
	private Date returnDate;
	//private String invoice; //???
	private static Connection connection = null;
	private Statement stmt = null;	

	
	
	public Scheduled() {
		super();
		this.returnDate = new Date();
		//this.invoice = "";
	}
	
	public Scheduled(int reqID, int cusID, int eid, Date requestDate, float quotation, boolean confirmed, Date returnDate) 
	{
		super( cusID,  eid, requestDate);
		quotation = super.getQuotation();
		confirmed = super.getConfirmed();
		this.returnDate = returnDate;
		//this.invoice = invoice;
	}
	
	public Scheduled(Scheduled obj) {
		super(obj);
		this.returnDate = obj.returnDate;
	
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
//	public String getInvoice() {
//		return invoice;
//	}
//	public void setInvoice(String invoice) {
//		this.invoice = invoice;
//	}

	@Override
	public String toString() {
		return "Scheduled Request: \n " +super.toString() + " Return Date: " + returnDate +"\n";
	}
	
	
	public void create(Scheduled scheduled, Connection myConn) {
		
		returnDate = scheduled.getReturnDate();
		int reqID = scheduled.getReqID();
		int cusID = Request.getCusID();
		int eid = scheduled.getEid();
		Date requestDate = scheduled.getRequestDate();
		float quotation = Request.getQuotation();
		boolean confirmed = Request.getConfirmed();

	
		
			try {
				stmt = myConn.createStatement();
				stmt.executeUpdate("INSERT INTO Request(ID, Name, Customer Name, Equipment Name, Request Date, Quotation, Confirmation) values('"+reqID+"','"+cusID+"','"+eid+"', '"+requestDate+"','"+quotation+"','"+confirmed+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NullPointerException np) {
				System.out.println("Null Expection.");
				np.getStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
	}
	
	
	

}

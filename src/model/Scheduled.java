package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public Scheduled(int reqID, Customer cName, Equipment eName, Date requestDate, float quotation, boolean confirmed, Date returnDate) 
	{
		super(reqID, cName, eName, requestDate, quotation, confirmed);
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
	public void create(Scheduled scheduled) {
		
		returnDate = scheduled.getReturnDate();
		reqID = scheduled.getReqID();
//		cName = Request.getcName();
//		eName = scheduled.geteName();
//		requestDate = scheduled.getRequestDate();
//		quotation = request.getQuotation();
//		confirmed = request.getConfirmed();
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO Request(ID, Name, Customer Name, Equipment Name, Request Date, Quotation, Confirmation) values('"+reqID+"','"+cName+"','"+eName+"', '"+requestDate+"','"+quotation+"','"+confirmed+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		return "Scheduled Request: \n " +super.toString() + " Return Date: " + returnDate +"\n";
	}
	
	
	
	

}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DBConnection;



public class Request {
	

	private int reqID;
	private Customer cName;
	private Equipment eName;
	private Date requestDate;
	private float quotation;
	private boolean confirmed;
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;
	
	private static final Logger Logger=LogManager.getLogger(Request.class);
	
	public Request() {
		this.reqID = 0000;
		this.cName = new Customer();
		this.eName = new Equipment();
		this.requestDate = new Date();
		this.quotation = 0;
		this.confirmed = false;
		connection = DBConnection.getConnection();
		
		
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
	
	public void readAll() {
		String selectSql = "SELECT * FROM ((customer c inner join request r on c.cid = r.cid)inner  join employee) ";
		
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				int reqID = result.getInt("reqID");
				String cName = result.getString("customer.FirstName"+"customer.LastName");
				String eName= result.getString("employee.FirstName"+"employee.LastName");
				float quotation=Float.parseFloat(result.getString("quotation"));
				Boolean confirmed = result.getBoolean("confirmed");
			   System.out.println("Request ID: "+reqID+"\t Customer Name "+cName+
					   "\t Employee Name: "+eName+"\t Quotation: "+quotation+"\tConfirmed: "+confirmed);
			   
			}
			//Might need this??	
			Logger.info("Queried Equipment table for all records");
			
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
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
	public void create(Connection myConn, Request request) {
		
		reqID = request.getReqID();
		cName = request.getcName();
		eName = request.geteName();
		requestDate = request.getRequestDate();
		quotation = request.getQuotation();
		confirmed = request.getConfirmed();
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate("INSERT INTO Request(ID, Name, Customer Name, Equipment Name, Request Date, Quotation, Confirmation) values('"+reqID+"','"+cName+"','"+eName+"', '"+requestDate+"','"+quotation+"','"+confirmed+"')");
			
			//Might be sensible
			Logger.info("Request record created");
			
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
			Logger.error("Error: ",np.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}
		
	}
	public void delete(String reqId, Connection myConn) {
		String query="Delete request where id = ?";
		
		try {
			PreparedStatement del = myConn.prepareStatement(query);
			del.setString(1, reqId);
			del.execute();
			//Might be sensible
			Logger.info("Request record was deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error("Error: ", e.getMessage());
		} catch (NullPointerException np) {
			System.out.println("Null Expectation.");
			np.getStackTrace();
			Logger.error("Error: ", np.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("Error: ", e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Request ID: " + reqID + ", Customer Name: " + cName + ", Equipment Name: " + eName + ", Request Date: " + requestDate
				+ ", Quotation; " + quotation + ", Confirmed; " + confirmed +"\n";
	}
	
	
	
}

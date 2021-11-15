package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DBConnection;



public class Request {
	

	private int reqID;
	private static int cusID;
	private int eid;
	private Date requestDate;
	private static float quotation;
	private static boolean confirmed;
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;
	
	private static final Logger Logger=LogManager.getLogger(Request.class);
	
	public Request() {
		//this.reqID = 0000;
		this.cusID = 0000;
		this.eid = 0000;
		this.requestDate = new Date();
		this.quotation = 0;
		this.confirmed = false;
		connection = DBConnection.getConnection();
		
		
	}

	public Request( int cusID, int eid, Date requestDate) {
		//this.reqID = reqID;
		this.cusID = cusID;
		this.eid = eid;
		this.requestDate = requestDate;
		//this.quotation = quotation;
		//this.confirmed = confirmed;
	}
	
	public Request(Request obj) {
		//this.reqID = obj.reqID;
		this.cusID = obj.cusID;
		this.eid = obj.eid;
		this.requestDate = obj.requestDate;
		this.quotation = obj.quotation;
		this.confirmed = obj.confirmed;
	}
	
	
	public int getReqID() {
		return reqID;
	}

	public void setReqID(Connection myConn) {
		String selectSql = "SELECT last_insert_id() as last_id from request";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				reqID = result.getInt("last_id");
						
			   //System.out.println("ID: "+ reqID);
				
			}
			Logger.info("Queried Requset Table for unique ID");
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		
	}

	public static int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public static float getQuotation() {
		return quotation;
	}

	public void setQuotation(float quotation) {
		this.quotation = quotation;
	}
	
	public static boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public void create(Connection myConn, Request request) {
		
		//reqID = request.getReqID();
		cusID = request.getCusID();
		eid = request.getEid();
		requestDate = request.getRequestDate();
		quotation = request.getQuotation();
		confirmed = request.getConfirmed();
		int confirmed1 = 0;
		if (confirmed == false) {
			confirmed1 = 0;
		}else if (confirmed == true) {
			confirmed1 = 1;
		}
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate("INSERT INTO request(cid, eqid, reqDate, quotation, confirmed) values('"+cusID+"','"+eid+"', '"+requestDate+"','"+quotation+"','"+confirmed1+"')");
			
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
	//For Customer - Update
	public void updateCustRequestInfo( int reqID, int eid, Date requestDate, Connection myConn) {
		String query = "update request set eid = ?, reqDate = ? where reqID = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setFloat(1, eid);
			//ps.setDate(2, requestDate);
			
			
			ps.execute();
			
			Logger.info("Request Info Updated");
		} catch (SQLException e) {
			
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
			Logger.error("Error: ",np.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}
	}
	
	//For Employee - Update
	public void updateRequestInfo(int reqID, float quotation, boolean confirmed, Connection myConn) {
		String query = "update request set quotation = ?, confirmed = ? where reqID = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setFloat(1, quotation);
			ps.setBoolean(2, confirmed);
			ps.setInt(3, reqID);
			
			ps.execute();
			
			Logger.info("Request Info Updated");
		} catch (SQLException e) {
			
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
			Logger.error("Error: ",np.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}
	}
	
	public void delete(int reqID, Connection myConn) {
		String query="Delete from request where reqID = ?";
		
		try {
			PreparedStatement del = myConn.prepareStatement(query);
			del.setInt(1, reqID);
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
	
	//This Method Shows General Requests -Employee View
	public ResultSet readAll(Connection myConn) {
		//String selectSql = "SELECT * FROM ((customer c inner join request r on c.cid = r.cid)inner  join employee) ";
//		String selectSql = "SELECT * FROM request"; //update
		String selectSql =  "SELECT  equipment.NAme,customer.LastName,customer.FirstName,request.reqDate,request.quotation,request.confirmed,equipmenttype.type"+
" FROM ((request INNER JOIN customer ON request.cid =customer.cid) INNER JOIN equipmenttype ON request.eqID = equipmenttype.eqTypeID)"+
				" INNER JOIN equipment ON equipment.TypeID = equipmenttype.eqTypeID";
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);

			
			//Might need this??	
			Logger.info("Queried Request table for all records");
			
			
			
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		
		return result;
		
	}
	
	//This Method Shows Personal Requests - Customer View
		public ResultSet custReq(Connection myConn,int id) {
//			String selectSql = "SELECT customer.cid,equipmenttype.Type FROM (( request INNER JOIN customer on request.cid = customer.cid)  inner join request on request.eqTypeID=equipmenttype.eqTypeID)";
			//update
			String selectSql =  "SELECT equipment.Name,customer.LastName,customer.FirstName,request.reqDate,request.quotation,request.confirmed,equipmenttype.type FROM ((request INNER JOIN customer ON request.cid ="+id+")"+
			" INNER JOIN equipmenttype ON request.eqID = equipmenttype.eqTypeID) INNER JOIN equipment ON equipment.TypeID = equipmenttype.eqTypeID";
			try {
				stmt =myConn.createStatement();
				result = stmt.executeQuery(selectSql);
				
			}catch(SQLException e) {
				System.err.println("Error Selecting all" + e.getMessage());
				Logger.error("Error: ",e.getMessage());
			}
			
			return result;
		}
		
//	public String readReqDate(Connection myConn) {
//		//String selectSql = "SELECT * FROM ((customer c inner join request r on c.cid = r.cid)inner  join employee) ";
//				String selectSql = "SELECT reqDate FROM request";
//				
//				String requestDate = "";
//				try {
//					stmt = myConn.createStatement();
//					result = stmt.executeQuery(selectSql);
//					while (result.next()) {
//						
//						requestDate = result.getString("reqDate");
//						
//					   System.out.println("Request Date: "+requestDate);
//					  
//					   
//					   
//					}
//					
//					//Might need this??	
//					Logger.info("Queried Request table for all request date records");
//				}catch(SQLException e) {
//					System.err.println("Error Selecting all" + e.getMessage());
//					Logger.error("Error: ",e.getMessage());
//				}
//		
//		return requestDate;
//	}

	@Override
	public String toString() {
		return "Request ID: " + reqID + ", Customer ID: " + cusID + ", Equipment Name: " + eid + ", Request Date: " + requestDate
				+ ", Quotation; " + quotation + ", Confirmed; " + confirmed +"\n";
	}
	
	
	
}

package model;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Equipment {

	private int eqID;
	private String eqName;
	private int eqTypeID;
	private String rentalStatus;
	private float cost;

	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private static final Logger Logger= LogManager.getLogger(Equipment.class);

	public Equipment() {

		this.eqID = 0000;
		this.eqName = "";
		this.eqTypeID = 00;
		this.rentalStatus = "";
		this.cost = 0;

	}

	public Equipment(String eqName, int eqTypeID, String rentalStatus, float cost) {
		//this.eqID = eqID;
		this.eqName = eqName;
		this.eqTypeID = eqTypeID;
		this.rentalStatus = rentalStatus;
		this.cost = cost;
	}
	public Equipment(Equipment obj) {
		//this.eqID = obj.eqID;
		this.eqName = obj.eqName;
		this.eqTypeID = obj.eqTypeID;
		this.rentalStatus = obj.rentalStatus;
		this.cost = obj.cost;
		
	
	}
	

	


	//Todo method to search by type using SQL LIKE ??
	//Rental Status
	
	public int getEqID() {
		return eqID;
	}

	public void setEqID(Connection myConn) {
		String selectSql = "SELECT last_insert_id() as last_id from equipment";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				eqID = result.getInt("last_id");
						
			   //System.out.println("ID: "+ eqID);
				
			}
			Logger.info("Queried Equipment Table for unique ID");
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public int getEqTypeID() {
		return eqTypeID;
	}

	public void setEqType(int eqTypeID) {
		this.eqTypeID = eqTypeID;
	}

	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public void create(Equipment equipment, Connection myConn) {
		
		//eqID = equipment.getEqID();
		eqName = equipment.getEqName();
		eqTypeID = equipment.getEqTypeID();
		rentalStatus = equipment.getRentalStatus();
		cost = equipment.getCost();
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate("INSERT INTO equipment(Name, TypeID, rentalStatus, cost) values('"+eqName+"','"+eqTypeID+"', '"+rentalStatus+"','"+cost+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void readAll(Connection myConn) {
		String selectSql = "SELECT * FROM equipment";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				int eqId = result.getInt("eqID");
				String eqName = result.getString("Name");
				//Get Type for id and display type namee
				String rentalStatus= result.getString("rentalStatus");
				float cost=Float.parseFloat(result.getString("cost"));			
			    System.out.println("Equipment ID: "+eqId+"\t Equipment Name: "+eqName+"\t Rental Status: "+rentalStatus+
			    		"\tcost"+cost);
			}
		//Might need this??	
		Logger.info("Queried Equipment table for all records");
		
		}catch(NullPointerException e) {
			System.err.println(e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		catch(Exception e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
	}
	
	public void delete(String eqId, Connection myConn) {
		String query="Delete from equipment where eqID = ?";
		
		try {
			PreparedStatement del = myConn.prepareStatement(query);
			del.setString(1, eqId);
			del.execute();
			Logger.info("Equipment Record deleted");
		
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		} catch (NullPointerException np) {
			System.out.println("Null Expectation.");
			Logger.error("Error: ",np.getMessage());
			np.getStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}
	}

	public void updateEquipmentInfo(int eqID, String eqName, int eqTypeID, String rentalStatus, float cost, Connection myConn) {
		String query = "update equipment set Name = ?, TypeID = ?, rentalStatus = ?, cost = ? where eqID = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, eqName);
			ps.setInt(2, eqTypeID);
			ps.setString(3, rentalStatus);
			ps.setFloat(4, cost);
			ps.setInt(5, eqID);
			ps.execute();
			
			Logger.info("Equipment Info Updated");
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

	@Override
	public String toString() {
		return "Equipment ID: " + eqID + ", Equipment Name: " + eqName + ", Equipment Type: " + eqTypeID + ", Rental Status: "
				+ rentalStatus + ", Cost:" + cost + "\n";
	}
	
	
	
	
}

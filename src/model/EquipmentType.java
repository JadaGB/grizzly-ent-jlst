package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EquipmentType {
	
	private int eqTypeID;
	private String type;
	
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private static final Logger Logger= LogManager.getLogger(EquipmentType.class);
	
	public EquipmentType() {
		this.eqTypeID = 00;
		this.type = "";
	}
	
	public EquipmentType(String type) {
		
		//this.eqTypeID = eqTypeID;
		this.type = type;
	}

	public int getEqTypeID() {
		
		
		
		return eqTypeID;
	}

	public void setEqTypeID(Connection myConn) {
		
		String selectSql = "SELECT last_insert_id() as last_id from equipmenttype";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				this.eqTypeID = result.getInt("last_id");
						
			   //System.out.println("ID: "+ eqTypeID);
				
			}
			Logger.info("Queried Euipment Type Table for unique ID");
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		//this.eqTypeID = eqTypeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void create(EquipmentType eqType, Connection myConn) {
		
		eqTypeID = eqType.getEqTypeID();
		type = eqType.getType();
		
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate("INSERT INTO equipmenttype(eqTypeID, Type) values('"+eqTypeID+"','"+type+"')");
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
		String selectSql = "SELECT * FROM equipmenttype";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				int eqTypeID = result.getInt("eqTypeID");
				String type = result.getString("Type");
				
					
			    System.out.println("Equipment Type ID: "+eqTypeID+"\t Equipment Name: "+type);
			}
		//Might need this??	
		Logger.info("Queried Equipment Type table for all records");
		
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
	
	

}

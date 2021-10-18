package model;
import java.sql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DBConnection;



public class Equipment {

	private int eqID;
	private String eqName;
	private String eqType;
	private String rentalStatus;
	private float cost;
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;

	
	public Equipment() {

		this.eqID = 0000;
		this.eqName = "";
		this.eqType = "";
		this.rentalStatus = "";
		this.cost = 0;
	}

	public Equipment(int eqID, String eqName, String eqType, String rentalStatus, float cost) {
		this.eqID = eqID;
		this.eqName = eqName;
		this.eqType = eqType;
		this.rentalStatus = rentalStatus;
		this.cost = cost;
	}
	public Equipment(Equipment obj) {
		this.eqID = obj.eqID;
		this.eqName = obj.eqName;
		this.eqType = obj.eqType;
		this.rentalStatus = obj.rentalStatus;
		this.cost = obj.cost;
		connection = DBConnection.getConnection();
	
	}
	
	
		public void create(Connection myConn, Equipment equipment) {
			
			eqID = equipment.getEqID();
			eqName = equipment.getEqName();
			eqType = equipment.getEqType();
			rentalStatus = equipment.getRentalStatus();
			cost = equipment.getCost();
			try {
				stmt = myConn.createStatement();
				stmt.executeUpdate("INSERT INTO equipments(ID, Name, Type, Rental Status, Cost) values('"+eqID+"','"+eqName+"','"+eqType+"', '"+rentalStatus+"','"+cost+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NullPointerException np) {
				System.out.println("Null Expection.");
				np.getStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	
	public void readAll() {
		String selectSql = "SELECT * FROM equipment";
		
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				int eqId = result.getInt(eqID);
				String eqName = result.getString("Name");
				String rentalStatus= result.getString("rentalStatus");
				float cost=Float.parseFloat(result.getString("cost"));			
			    System.out.println("Equipment ID: "+eqId+"\t Equipment Name: "+eqName+"\t Result: "+rentalStatus+
			    		"\tcost"+cost);
			}
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
		}
	}


	
	public int getEqID() {
		return eqID;
	}

	public void setEqID(int eqID) {
		this.eqID = eqID;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
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
	
	public void delete(String eqId) {
		String query="Delete request where id = ?";
		
		try {
			PreparedStatement del = connection.prepareStatement(query);
			del.setString(1, eqId);
			del.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException np) {
			System.out.println("Null Expectation.");
			np.getStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		return "Equipment ID: " + eqID + ", Equipment Name: " + eqName + ", Equipment Type: " + eqType + ", Rental Status: "
				+ rentalStatus + ", Cost:" + cost + "\n";
	}
	
	
	
	
}

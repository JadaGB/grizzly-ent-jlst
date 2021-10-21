package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DBConnection;

public class Event {
	
	private int evID;
	private String eventName;
	private Statement stmt = null;
	private ResultSet result = null;
	private static Connection connection = null;
	
	public Event() {
		this.evID = 0000;
		this.eventName = "";
	}

	
	
	public void readall() {
		String selectSql = "SELECT * FROM event";
		connection =DBConnection.getConnection();
		try {
			 stmt=connection.createStatement();
			 result=stmt.executeQuery(selectSql);
			 
			 while(result.next()) {
				 System.out.println("\nEvent Id: "+result.getString("evID")+
						 "\nEvent Name: "+result.getString("eventName"));
			 }
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Event(int evID, String eventName) {
		
		this.evID = evID;
		this.eventName = eventName;
	}
	
	public Event(Event obj) {
		this.evID = obj.evID;
		this.eventName = obj.eventName;
	}

	public int getEvID() {
		return evID;
	}

	public void setEvID(int evID) {
		this.evID = evID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "Event ID: " + evID + ", Event Name: " + eventName + "\n";
	}
	
	
}

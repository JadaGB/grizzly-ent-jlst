package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Event {
	
	private int evID;
	private String eventName;
	private static Connection connection = null;
	private Statement stmt = null;
	
	public Event() {
		this.evID = 0000;
		this.eventName = "";
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
	
	public void create(Event event) {
		
		evID = event.getEvID();
		eventName = event.getEventName();
		
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO Event(ID, Name) values('"+evID+"','"+eventName+"')");
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
		return "Event ID: " + evID + ", Event Name: " + eventName + "\n";
	}
	
	
}

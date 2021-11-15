package model;

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.SessionFactoryBuilder;

/*
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DBConnection;*/

@Entity(name="event")
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="evID")
	private int evID;
	@Column(name="eventName")
	private String eventName;
/*	
	private Statement stmt = null;
	private ResultSet result = null;
	private static Connection connection = null;
	
	private static final Logger Logger=LogManager.getLogger(Event.class);
*/	
	public Event() {
		this.evID = 0000;
		this.eventName = "";
	}

	public Event( String eventName) {
		
		//this.evID = evID;
		this.eventName = eventName;
	}
	
	public Event(Event obj) {
		//this.evID = obj.evID;
		this.eventName = obj.eventName;
	}

	public int getEvID() {
		return evID;
	}

/*	public void setEvID(Connection myConn) {
		
		String selectSql = "SELECT last_insert_id() as last_id from event";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				evID = result.getInt("last_id");
						
			  // System.out.println("ID: "+ evID);
				
			}
			Logger.info("Queried Event Table for unique ID");
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		
		//this.evID = evID;
	}
*/
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
/*	
	public void create(Event event, Connection myConn) {
		
		evID = event.getEvID();
		eventName = event.getEventName();
		
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate("INSERT INTO Event(evID, eventName) values('"+evID+"','"+eventName+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
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
	
	public void delete(int evID, Connection myConn) {
		String query = "delete from event where evID = ?"; //check database
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			
			ps.setInt(1, evID);
			ps.execute();
			Logger.info("Event Record Deleted");
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
*/
	public void create() {
		Session session = SessionFactoryBuilder
							.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> read_All(){
		List<Event> evid=new ArrayList<>();
		Session session = SessionFactoryBuilder
							.getSessionFactory()
							.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		evid = (List<Event>) session.createQuery("FROM event")
											 .getResultList();
		
		transaction.commit();
        session.close();
		return evid;
				
	}
	
	public void delete() {
		Session session = SessionFactoryBuilder
							.getSessionFactory()
							.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Event stu = (Event) session.get(Event.class, this.evID);
		session.delete(stu);
		transaction.commit();
        session.close();
        
	}
	public Event read() {
		Session session = SessionFactoryBuilder
				.getSessionFactory()
				.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Event stu = (Event) session.get(Event.class, this.evID);
		transaction.commit();
        session.close();
        return stu;
	}
	
	
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer {
	
	private int cusID;
	private String Fname;
	private String LName;
	private String email;
	private String phoneNum;
	private String password;
	private String evName;  
	
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;

	private static final Logger Logger=LogManager.getLogger(Customer.class);
	
	public Customer() {
		this.cusID = 0000;
		this.Fname = "John";
		this.LName = "Doe";
		this.email = "example@gmail.com";
		this.phoneNum = "000-0000";
		this.password = "";
		this.evName = "";
	}

	public Customer(int cusID, String Fname, String LName, String email, String phoneNum, String password, String evName) {
		this.cusID = cusID;
		this.Fname = Fname;
		this.LName = LName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.password = password;
		this.evName = evName;
	}
	
	public Customer(Customer obj) {
		this.cusID = obj.cusID;
		this.Fname = obj.Fname;
		this.LName = obj.LName;
		this.email = obj.email;
		this.phoneNum = obj.phoneNum;
		this.password = obj.password;
		this.evName = obj.evName;
		
	}
	public void readAll() {
		String selectSql = "SELECT * FROM customer";
		
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				/*int eqId = result.getInt("eqID");
				String eqName = result.getString("Name");
				String rentalStatus= result.getString("rentalStatus");
				float cost=Float.parseFloat(result.getString("cost"));			
			   */ System.out.println("Customer Name: "+result.getString("FirstName")+" "+
				result.getString("LastName")+"\tEmail: "+result.getString("Email")+
				"\tPhoneNumber: "+result.getString("PhoneNumber"));
			}
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
	}

	
	/*public void getEvent() {
		String selectSql ="SELECT * FROM (customer c inner join event e on c.cid = e.cid) ";
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				int eqId = result.getInt("eqID");
				String eqName = result.getString("Name");
				String rentalStatus= result.getString("rentalStatus");
				float cost=Float.parseFloat(result.getString("cost"));			
			    System.out.println("Equipment ID: "+eqId+"\t Equipment Name: "+eqName+"\t Result: "+rentalStatus+
			    		"\tcost"+cost);
			}
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
		}
	}*/
	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEvName() {
		return evName;
	}

	public void setEvName(Event evName) {
		this.evName = evName.getEventName();
	}

	@Override
	public String toString() {
		return "Customer ID: : " + cusID + ", First Name: " + Fname + ", Last Name: " + LName + ", Email: " + email + ", Phone Number:"
				+ phoneNum + ", Password: " + password + ", Event Name: " + evName + "\n";
	}

	
	public void create(Connection myConn, Customer customer) {
		
		String FirstName = customer.getFname();
		String LastName = customer.getLName();
		String phoneNum = customer.getPhoneNum();
		String password = customer.getPassword();
		String email = customer.getEmail();
		//String eventName = customer //Fix
		
		try {
			stmt= myConn.createStatement();
			stmt.executeUpdate("INSERT INTO customer(Email, FirstName, LastName, Password, PhoneNumber) values('"+email+"','"+FirstName+"','"+LastName+"','"+password+"','"+phoneNum+"')");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePersonalInfo(String fName, String lName, String email, String phoneNum, int cid, Connection myConn) {
		String query = "update customer set FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ? where cid = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3, email);
			ps.setString(4, phoneNum);
			ps.setInt(5, cid);
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePassword(String password, int cid, Connection myConn) {
		String query = "update customer set Password = ? where cid = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, password);
			ps.setInt(2, cid);
			ps.execute();
			Logger.info("Customer Password Updated");
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
	
	public void delete(int cid, Connection myConn) {
		String query = "delete from customer where cid = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			
			ps.setInt(1, cid);
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}

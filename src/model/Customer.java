package model;

import java.sql.*;

import controller.DBConnection;

public class Customer {
	
	private int cusID;
	private String Fname;
	private String LName;
	private String email;
	private String phoneNum;
	private String password;
	private Event evName;
	
	

	
	//Constructors
	public Customer() {
		this.cusID = 0000;
		this.Fname = "John";
		this.LName = "Doe";
		this.email = "example@gmail.com";
		this.phoneNum = "000-0000";
		this.password = "";
		this.evName = new Event();
	}

	public Customer(int cusID, String Fname, String LName, String email, String phoneNum, String password, Event evName) {
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
	
	
	public void readAll(){
		
		 String query="SELECT * FROM Customer";
		 String records=" ";
		 Connection myconn=DBConnection.getConnection();
	     try {
			PreparedStatement read=myconn.prepareStatement(query);
			ResultSet results=read.executeQuery();
			
			while(results.next()) {
				records+="\nCustomer First Name: "+results.getString("FirstName")
				+"Customer Last Name: "+results.getString("LastName")+"Customer Email: "+results.getString("Email")+
				"Customer Phone#: "+results.getString("PhoneNumber: ");
			}	
			
		}
	     catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void read(int cid) {
		 String query="SELECT FirstName,LastName,Email,PhoneNumber FROM Customer WHERE cid='"+cid+"'";
		 
		 Connection myconn=DBConnection.getConnection();
	     try {
			Statement read=myconn.createStatement();
			ResultSet results=read.executeQuery(query);
			while(results.next()) {
				System.out.println("\nCustomer First Name: "+results.getString("FirstName")
				+"Customer Last Name: "+results.getString("LastName")+
				"Customer Email: "+results.getString("Email")+
				"Customer Phone#: "+results.getString("PhoneNumber"));
			}	
			
		}
	     catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Getters and Setters
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

	public Event getEvName() {
		return evName;
	}

	public void setEvName(Event evName) {
		this.evName = evName;
	}

	@Override
	public String toString() {
		return "Customer ID: : " + cusID + ", First Name: " + Fname + ", Last Name: " + LName + ", Email: " + email + ", Phone Number:"
				+ phoneNum + ", Password: " + password + ", Event Name: " + evName + "\n";
	}

	
	
	

}

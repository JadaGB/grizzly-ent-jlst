package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DBConnection;

public class Employee {
	private int empID;
	private String Fname;
	private String Lname;
	private Equipment responsibility; //of Event Type
	private String passsword;
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;

	
	public Employee() {
		this.empID = 0000;
		this.Fname = "";
		this.Lname = "";
		this.responsibility = new Equipment();
		this.passsword = "";
		connection = DBConnection.getConnection();
	}
	
	public void readAll() {
		String selectSql = "SELECT * FROM employee";
		
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				int empID = result.getInt("empId");
				String Fname = result.getString("FirstName");
				String Lname= result.getString("LastName");
				String responsibility=result.getString("Responsibility");			
			   System.out.println("Employee ID: "+empID+"\t First Name: "+Fname+
					   "\t Last Name: "+Lname+"\t Responsibility: "+responsibility);
			   
			}
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
		}
	}
	
	
	public Employee(int empID, String Fname, String Lname, Equipment responsibility, String passsword) {
		this.empID = empID;
		this.Fname = Fname;
		this.Lname = Fname;
		this.responsibility = responsibility;
		this.passsword = passsword;
	}


	public Employee(Employee obj) {
		this.empID = obj.empID;
		this.Fname = obj.Fname;
		this.Lname = obj.Lname;
		this.responsibility = obj.responsibility;
		this.passsword = obj.passsword;
	}


	public int getEmpID() {
		return empID;
	}


	public void setEmpID(int empID) {
		this.empID = empID;
	}


	public String getFname() {
		return Fname;
	}


	public void setFname(String fname) {
		Fname = fname;
	}


	public String getLname() {
		return Lname;
	}


	public void setLname(String lname) {
		Lname = lname;
	}


	public Equipment getResponsibility() {
		return responsibility;
	}


	public void setResponsibility(Equipment responsibility) {
		this.responsibility = responsibility;
	}


	public String getPasssword() {
		return passsword;
	}


	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}


	@Override
	public String toString() {
		return "Employee ID: " + empID + ", First Name: " + Fname + ", Last Name: " + Lname + ", Responsibility: "
				+ responsibility + ", Passsword: " + passsword + "\n";
	}
	
	
}

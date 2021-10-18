package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
	private int empID;
	private String Fname;
	private String Lname;
	private String responsibility; //of Event Type //changed to String
	private String password;
	
	private Statement st;
	
	public Employee() {
		this.empID = 0000;
		this.Fname = "";
		this.Lname = "";
		this.responsibility = "";
		this.password = "";
	}
	
	
	public Employee(int empID, String Fname, String Lname, String responsibility, String password) {
		this.empID = empID;
		this.Fname = Fname;
		this.Lname = Fname;
		this.responsibility = responsibility;
		this.password = password;
	}


	public Employee(Employee obj) {
		this.empID = obj.empID;
		this.Fname = obj.Fname;
		this.Lname = obj.Lname;
		this.responsibility = obj.responsibility;
		this.password = obj.password;
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


	public String getResponsibility() {
		return responsibility;
	}


	public void setResponsibility(Equipment responsibility) {
		this.responsibility = responsibility.getEqType();
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Employee ID: " + empID + ", First Name: " + Fname + ", Last Name: " + Lname + ", Responsibility: "
				+ responsibility + ", Passsword: " + password + "\n";
	}
	
	public void create(Connection myConn, Employee employee) {
		
		String FirstName = employee.getFname();
		String LastName = employee.getLname();
		String password = employee.getPassword();
		String responsibility = employee.getResponsibility();
		
		try {
			st = myConn.createStatement();
			st.executeUpdate("INSERT INTO employee(FirstName, LastName, Password, Responsibility) values('"+FirstName+"','"+LastName+"','"+password+"','"+responsibility+"')");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePersonalInfo(String fName, String lName, String responsibility, int empId, Connection myConn) {
		String query = "update employee set FirstName = ?, LastName = ?, Responsibility = ? where empId = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(1, responsibility);
			ps.setInt(4, empId);
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
	
	public void updatePassword(String password, int empId, Connection myConn) {
		String query = "update employee set Password = ? where empId = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, password);
			ps.setInt(2, empId);
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
	
	public void delete(int empID, Connection myConn) {
		String query = "delete from employee where empId = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			
			ps.setInt(1, empID);
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

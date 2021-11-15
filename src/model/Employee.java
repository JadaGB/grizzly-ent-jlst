package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee {
	private int empID;
	private String Fname;
	private String Lname;
	private int eqTypeID; //of Event Type //changed to String
	private String password;
	
	private static Connection connection = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private static final Logger Logger=LogManager.getLogger(Employee.class);
	private Statement st;
	
	public Employee() {
		this.empID = 0000;
		this.Fname = "";
		this.Lname = "";
		this.eqTypeID = 00;
		this.password = "";
	}
	
	
	public Employee(String Fname, String Lname, int eqTypeID, String password) {
		//this.empID = empID;
		this.Fname = Fname;
		this.Lname = Lname;
		this.eqTypeID = eqTypeID;
		this.password = password;
	}


	public Employee(Employee obj) {
		this.empID = obj.empID;
		this.Fname = obj.Fname;
		this.Lname = obj.Lname;
		this.eqTypeID = obj.eqTypeID;
		this.password = obj.password;
	}


	public int getEmpID() {
		return empID;
	}


	public void setEmpID(Connection myConn) {
		String selectSql = "SELECT last_insert_id() as last_id from employee";
		
		try {
			stmt = myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				empID = result.getInt("last_id");
						
			   //System.out.println("ID: "+ eqID);
				
			}
			Logger.info("Queried Equipment Table for unique ID");
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
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


	public int getEqTypeID() {
		return eqTypeID;
	}


	public void setEqTypeID(int eqTypeID) {
		this.eqTypeID = eqTypeID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Employee ID: " + empID + ", First Name: " + Fname + ", Last Name: " + Lname + ", Equip Type ID: "
				+ eqTypeID + ", Passsword: " + password + "\n";
	}
	
	public void create(Connection myConn, Employee employee) {
		
		String FirstName = employee.getFname();
		String LastName = employee.getLname();
		String password = employee.getPassword();
		int eqTypeID = employee.getEqTypeID();
		
		try {
			st = myConn.createStatement();
			st.executeUpdate("INSERT INTO employee(FirstName, LastName, Password, eTypeID) values('"+FirstName+"','"+LastName+"','"+password+"','"+eqTypeID+"')");
		} catch (SQLException e) {
			
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		} catch(NullPointerException np) {
			System.out.println("Null Expection.");
			np.getStackTrace();
			Logger.error("Error: ",np.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Error: ",e.getMessage());
		}
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
				int eqTypeID=result.getInt("eTypeID");			
			   System.out.println("Employee ID: "+empID+"\t First Name: "+Fname+
					   "\t Last Name: "+Lname+"\t Eq Type ID: "+eqTypeID);

			}
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
	}
	
	public void readEquipType(Connection myConn,int id) {
		String selectSql = "SELECT Type FROM equipmenttype t1 INNER JOIN employee t2 ON t1.eqTypeID = "+id;
 
		try {
			stmt =myConn.createStatement();
			result = stmt.executeQuery(selectSql);
			while (result.next()) {
				String equipType= result.getString("Type");	
			   System.out.println("This Employee is Responsible For: "+equipType);
			   break;
			}
			Logger.info("Queried Equipment Table for Type of Equipment managed by employee");

		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
	}
	public void updatePersonalInfo(String fName, String lName, int eqTypeID, int empId, Connection myConn) {
		String query = "update employee set FirstName = ?, LastName = ?, eTypeID = ? where empId = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setInt(3, eqTypeID);
			ps.setInt(4, empId);
			ps.execute();
			
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
	
	public void updatePassword(String password, int empId, Connection myConn) {
		String query = "update employee set Password = ? where empId = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			ps.setString(1, password);
			ps.setInt(2, empId);
			ps.execute();
			
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
	
	public void delete(int empID, Connection myConn) {
		String query = "delete from employee where empId = ?";
		
		try {
			PreparedStatement ps = myConn.prepareStatement(query);
			
			
			ps.setInt(1, empID);
			ps.execute();
			
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
	
	public boolean authenticateEmployee(int empID, String ePass, Connection myConn ) {
		String selectSql = "SELECT * FROM employee WHERE empId = ? AND Password = ?";
		boolean exist = false;
		
		try {
			PreparedStatement ps = myConn.prepareStatement(selectSql);
			
			
			ps.setInt(1, empID);
			ps.setString(2, ePass);
			ps.execute();
			
			result = ps.getResultSet();
			
			if(result.next() == true) {
				exist = true;
				System.out.println("P: "+result.getString(1)); //test
			} else {

				System.out.println("Not Found"); //test
			}
			
		}catch(SQLException e) {
			System.err.println("Error Selecting all" + e.getMessage());
			Logger.error("Error: ",e.getMessage());
		}
		
		return exist;
	}
	
}

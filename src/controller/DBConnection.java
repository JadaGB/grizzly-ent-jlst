package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection myConn;
	
	public static Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/grizzly_ent";
		
		if (myConn == null) {
			try {
				myConn = DriverManager.getConnection(url, "root", "");
			} catch (SQLException e) {
				//
				e.printStackTrace();
			}
		}else {
			return myConn;
		}
		
		return myConn;
		
	}

}

package model;

import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
//import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.SystemController;

public class Server extends Thread {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	
	
	Date date=new Date();  
	private int clientCount;//This int variable keeps track of clients and number of threads created
	
	public Server() {
		start();
		try {
			
			serverSocket=new ServerSocket(8000);
			clientCount=0;
			System.out.println("The server has started "+date);
			
			while(true){
				
				connectionSocket=serverSocket.accept();
				System.out.print("\nNew client connected: "+ connectionSocket.getInetAddress().getHostAddress());
				
				clientCount+=1;
				System.out.println("\nServer is starting a thread for Client#:"+clientCount+"\nDate: "+date);
				
				ClientHandler ch=new ClientHandler(connectionSocket);
				Thread th=new Thread(ch);
				th.start();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void start() {			
		try {
			//createConnection();
			connectionSocket=serverSocket.accept();
			System.out.println("New client connected: ");
				
		} catch (IOException e) {
			e.printStackTrace();
		}
			//waitForRequests();
	}
	
	public static void main (String []args) {
		new Server();
	}
}	

class ClientHandler implements Runnable{
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection myConn  = null;
	private static final Logger LOGGER = LogManager.getLogger(Server.class);
	
	private Statement stmt = null;
	
	private PrintWriter pw;
	private BufferedReader reader;
	private Socket connectionSocket;
	private ServerSocket serverSocket;
		
	
	public	ClientHandler(Socket socket){
		try {
			createConnection();
			connectionSocket=socket;
			pw=new PrintWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
			reader=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e) {
			closeConnection();
		}
		
	}
	
	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static Connection getDatbaseConnection() {
		if(myConn==null) {
			try {
				String url="jdbc:mysql://localhost:3306/grizzly_ent";
				myConn = DriverManager.getConnection(url,"root","");
				//JOptionPane.showMessageDialog(null, "DB Connection Established", "CONNECTION STATUS", JOptionPane.INFORMATION_MESSAGE);	
				LOGGER.info("Database Connection Established");
			}catch(SQLException e) {
				//JOptionPane.showMessageDialog(null, "Could not connect to database\n" + e, "CONNECTION Failure", JOptionPane.ERROR_MESSAGE);
				LOGGER.error("Could not connect to database");
			}	
		}
		return myConn;
	}
	private void configureStreams()  {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
			
	}	
	
	//Check login info received from client against 
	//in database
	private  void authenticateCus() { 
		try {
			getDatbaseConnection();
			String username = reader.readLine();
		    String password = reader.readLine();
		    
		    LOGGER.info("Connection made through " + connectionSocket);
			LOGGER.info("Connection made successfully.");
//          System.out.println("Username Received: " + username);
//		    System.out.println("Password Received: " + password);
		    
		    //pw.println("Server Received without errors");
		    
		    pw.flush();
			pw.close();
			
			String query="SELECT * FROM customer WHERE Password= "+password+"AND cid= "+username;
			try {
				if(stmt.executeUpdate(query)==1) {
				     pw.println(true);
				}else{
					pw.println(false);
					System.out.println("Authentification Failed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 pw.flush();
			 pw.close();


		}catch (IOException e) {
			System.out.println("Error in Client Handler"+e.getMessage());
			e.printStackTrace();
			closeConnection();
		}
			
     }

	private  void authenticateEmp() { 
		try {
			getDatbaseConnection();
			String username = reader.readLine();
			String password = reader.readLine();
			LOGGER.info("Connection made through " + connectionSocket);
			LOGGER.info("Connection made successfully.");
//          System.out.println("Username Received: " + username);
//		    System.out.println("Password Received: " + password);
		    
		    //pw.println("Server Received without errors");
		    
		    pw.flush();
			pw.close();
		     
//		    checkEmployee(username,password);
		    String query="SELECT * FROM employee WHERE Password= "+password+"AND empId= "+username;
			try {
				if(stmt.executeUpdate(query)==1) {
				     pw.println(true);
				        }else{
				            pw.println(false);
				        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 pw.flush();
			 pw.close();
		   
		}catch (IOException e) {
			System.out.println("Error in Client Handler"+e.getMessage());
			e.printStackTrace();
			closeConnection();
		}
			
     }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String action= "";
		getDatbaseConnection();
		SystemController loginObj = null;
		try {
			while(true) {
				connectionSocket = serverSocket.accept();
				
                LOGGER.info("Connection made successfully.");
                this.configureStreams();
				try {
					action = (String) objIs.readObject();
					
					if (action.equals("customer")) {
						loginObj = (SystemController) objIs.readObject();
						authenticateCus();
						objOs.writeObject(loginObj);	
						
					}else if (action.equals("employee")) {	
						loginObj = (SystemController) objIs.readObject();
						authenticateEmp();
						//checkEmployee(action, action);
						objOs.writeObject(loginObj);	
					}
				}catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch (ClassCastException ex) {
					ex.printStackTrace();
				}
				closeConnection();
			}
		}catch (EOFException ex) {
			System.out.println("Client terminated connections with server");
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}

//private void waitForRequests() {
//	// TODO Auto-generated method stub
//	String action= "";
//	getDatbaseConnection();
//	SystemController loginObj = null;
//	try {
//		while(true) {
//			connectionSocket = serverSocket.accept();
//			this.configureStreams();
//			try {
//				action = (String) objIs.readObject();
//				
//				if (action.equals("customer")) {
//					loginObj = (SystemController) objIs.readObject();
//					authenticateCus();
//					objOs.writeObject(loginObj);	
//					
//				}else if (action.equals("employee")) {	
//					loginObj = (SystemController) objIs.readObject();
//					authenticateEmp();
//					//checkEmployee(action, action);
//					objOs.writeObject(loginObj);	
//				}
//			}catch (ClassNotFoundException ex) {
//				ex.printStackTrace();
//			}catch (ClassCastException ex) {
//				ex.printStackTrace();
//			}
//			closeConnection();
//		}
//	}catch (EOFException ex) {
//		System.out.println("Client terminated connections with server");
//		ex.printStackTrace();
//	}catch (IOException ex) {
//		ex.printStackTrace();
//	}
//}
//??Fix user input authen	
//private void checkCustomer(String user,String pass) {
//	try {
//		stmt=connection.createStatement();
//		String username;
//		try {
//			username = reader.readLine();
//			System.out.println("Username Received: " + username);
//		    String password = reader.readLine();
//		    System.out.println("Password Received: " + password);
//		    pw.println("Server Received without errors");
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Error in Client Handler"+e.getMessage());
//			closeConnection();
//		}
//	    
//		String query="SELECT * FROM customer WHERE Password= "+pass+"AND LastName + cid= "+user;
//		if(stmt.executeUpdate(query)==1) {
//		     pw.println(true);
//		}else{
//			pw.println(false);
//			System.out.println("Authentification Failed");
//		}
//		 pw.flush();
//		 pw.close();
//
//	}catch (SQLException e) {
//		e.printStackTrace();
//	
//	}	
//}
//	private void checkEmployee(String user,String pass) {
//		try {
//			stmt=connection.createStatement();
//			String username;
//			try {
//				username = reader.readLine();
//				System.out.println("Username Received: " + username);
//			    String password = reader.readLine();
//			    System.out.println("Password Received: " + password);
//			    pw.println("Server Received without errors");
//			}catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Error in Client Handler"+e.getMessage());
//				closeConnection();
//			}
//			String query="SELECT * FROM employee WHERE Password= "+pass+"AND LastName + cid= "+user;
//			if(stmt.executeUpdate(query)==1) {
//			     pw.println(true);
//			        }else{
//			            pw.println(false);
//			        }
//			 pw.flush();
//			 pw.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
		

	
		
		

	
//	
//	private void addCustomerToFile (Customer customer) {
//		String sql ="INSERT INTO grizzly_ent.customers (cid, Email, eID, FirstName, LastName, Password, PhoneNumber)"
//				+"VALUES (" +customer.getCusID() + ",'" + customer.getEmail() + "', '" + customer.getFname()
//				+ "', '" + customer.getLName() +"','" + customer.getPassword() + customer.getPhoneNum() +"');";
//		try {
//			stmt = myConn.createStatement();
//			
//			if ((stmt.executeUpdate(sql)==1)) {
//				objOs.writeObject(true);//returns true if client is successful
//			}else {
//				objOs.writeObject(false);
//			}
//		
//		}catch(IOException ioe) {
//			ioe.printStackTrace();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private Customer findCustomerById(String cusID) {
//		Customer cusObj = new Customer();
//		String query = "SELECT * FROM grizzly_ent.customer WHERE cid = " + cusID;
//		try {
//			stmt = myConn.createStatement();
//			result = stmt.executeQuery(query);
//			if(result.next()) {
//				cusObj.setCusID(myConn);
//				cusObj.setEmail(result.getString(1));
//				cusObj.setEvID(result.getInt(2));//??
//				cusObj.setFname(result.getString(3));
//				cusObj.setLName(result.getString(4));
//				cusObj.setEmail(result.getString(5));
//				cusObj.setPassword(result.getString(6));
//				cusObj.setPhoneNum(result.getString(7));;
//			
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return cusObj;
//	}
//	
//	private void addRequestToFile (Request request) {
//		String sql ="INSERT INTO grizzly_ent.requests (reqID, quotation, reqDate, confirmed, cid, eqID)"
//				+"VALUES (" +request.getReqID() + ",'"  + request.getQuotation()
//				+ "', '" + request.getRequestDate() +"','" + request.getConfirmed() + request.getCusID() + request.getEid() +"');";
//		try {
//			stmt = myConn.createStatement();
//			
//			if ((stmt.executeUpdate(sql)==1)) {
//				objOs.writeObject(true);//returns true if client is successful
//			}else {
//				objOs.writeObject(false);
//			}
//		
//		}catch(IOException ioe) {
//			ioe.printStackTrace();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private Request findRequestById(String reqID) {
//		Request reqObj = new Request();
//		String query = "SELECT * FROM grizzly_ent.request WHERE reqID = " + reqID;
//		try {
//			stmt = myConn.createStatement();
//			result = stmt.executeQuery(query);
//			if(result.next()) {
//				reqObj.setReqID(myConn);;
//				reqObj.setQuotation(result.getFloat(2));;
//				//reqObj.setRequestDate();
//				reqObj.setConfirmed(result.getBoolean(1));
//				reqObj.setCusID(0);;
//				reqObj.setEid(0);
//				
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return reqObj;
//	}
//	
	



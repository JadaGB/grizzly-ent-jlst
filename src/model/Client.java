package model;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.OutputStream;
import java.net.Socket;
//import java.sql.Connection;

import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import controller.DBConnection;
import controller.SystemController;


public class Client {
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private Socket connectionSocket;
	
	//private static Connection myConn;
	private static String action = "";
	SystemController login = new SystemController();
	//private InputStream inStream;
	private static final Logger LOGGER = LogManager.getLogger(Client.class);

	public Client()  {
		this.createConnection();
		this.configureStreams();
		
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1",13000);
            connectionSocket.getOutputStream();
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
            new BufferedReader(new InputStreamReader(objIs));
		}
		catch (IOException ex) {
			ex.printStackTrace();
			LOGGER.error("Error Occured");
		}
	}
	
	private void configureStreams()  {
		try {
			//create input stream to receive data from server
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			//send data to server
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Error Occured");
		}
		
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		}catch (IOException ex) {
			ex.printStackTrace();
			LOGGER.error("Error Occured");
		}
			
	}
	

	public void sendAction(String action) {
		Client.action = action;
		try {
			objOs.writeObject(action);
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

	public void receiveResponse() {
		try {
			if (action.equalsIgnoreCase("customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					//JOptionPane.showMessageDialog(null, "Login successful", "Login Status", JOptionPane.INFORMATION_MESSAGE);
					LOGGER.info("Login successful");
				}else {
					//JOptionPane.showMessageDialog(null, "Login failed", "Login Status", JOptionPane.INFORMATION_MESSAGE);
					LOGGER.info("Login successful");
				}
				
			}else if(action.equalsIgnoreCase("employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "Login successful", "Login Status", JOptionPane.INFORMATION_MESSAGE);
					LOGGER.info("Login successful");
				}else {
					JOptionPane.showMessageDialog(null, "Login failed", "Login Status", JOptionPane.INFORMATION_MESSAGE);
					LOGGER.info("Login failed");
				}
			}
			
		}catch (ClassCastException ex) {
			ex.printStackTrace();
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
//	public boolean login(String userName, String password) throws IOException {
//  String command = "login " + userName + " " + password + "\n";
//  outStream.write(command.getBytes());
//
//  String response = reader.readLine();
//  System.out.println("Status: " + response);
//  LOGGER.info("Login Status received");
//
//  if ("Login Successful".equalsIgnoreCase(response)) {
//      receiveResponse();
//      return true;
//  } else {
//      return false;
//  }
//}
//
//public void sendCustomer(Customer cusObj) {
//	try {
//		objOs.writeObject(cusObj);
//	}catch (IOException ex) {
//		ex.printStackTrace();
//	}
//}
//
//public void sendCustomerID(String cusId) {
//	try {
//		objOs.writeObject(cusId);
//	}catch (IOException ex) {
//		ex.printStackTrace();
//	}
//}

	public static void main(String[] args) {
		Client client = new Client();
		client.sendAction(action);;
		client.receiveResponse();
	}

	
}




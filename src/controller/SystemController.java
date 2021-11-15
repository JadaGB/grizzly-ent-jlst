package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.Employee;
import model.Equipment;
import model.EquipmentType;
import model.Request;
import view.EmpDash;
import view.Login1;
import view.NewRequestForm;
import view.SignIn;

public class SystemController {
	
	DBConnection dbc = new DBConnection();
	Connection myConn = dbc.getConnection();
	 
	private Employee employeeModel;
	private Customer customerModel;
	private Equipment eqModel;
	private EquipmentType eqTypeModel;
	private Login1	empLogin;
	private Request requestModel;
	//private SignIn empSignin;
	
	ResultSet result;
	
	public SystemController() {
		employeeModel = new Employee();
		customerModel = new Customer();
		eqModel = new Equipment();
		eqTypeModel = new EquipmentType();
		empLogin = new Login1(this);
		requestModel = new Request();
		//empSignin = new SignIn(this);
	}
	
	
	public void login1(String userType) {
		
		if (userType.equalsIgnoreCase("employee")) {
			SignIn.getUserType("employee");
			
		}else if (userType.equalsIgnoreCase("customer")) {
			SignIn.getUserType("customer");
		}
		
	}
	
	public boolean signin(String userType, int id, String password) {
	
		boolean doesExist = false;
		
		
		if (userType == "employee") {
			
			doesExist = employeeModel.authenticateEmployee(id, password, myConn);
			
			
		}else if (userType == "customer"){
			doesExist = customerModel.authenticateCustomer(id, password, myConn);
			
		}
		
		
		return doesExist;
	}

	public static void main(String[] args) {
		new SystemController();

	}


	public void getAllEquipmentTypes() {
		result = eqTypeModel.readAll(myConn);
		int i = 0;
		String type;
		try {
			while(result.next()) {
				type = result.getString("Type");
				i+=1;
				NewRequestForm.populateEqTypeList(type);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Count"+i);
	}


	public void getAllEquipmentofEqType(String sel) {
		 result = eqModel.readAllEquipNames(sel, myConn);
		 String name;
		 
		 try {
			while(result.next()) {
				 name = result.getString("Name");
				 NewRequestForm.populateEqNamesList(name);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
//	public void getAllRequestsInfo() {
//		result = requestModel.readAll(myConn);
//			try {
//				while (result.next()) {
//					//to be updated
//					int reqID= result.getInt("reqID");
//					int cid = result.getInt("cid");
//					int eid= result.getInt("eqID");
//					String requestDate = result.getString("reqDate");
//					Float quotation=Float.parseFloat(result.getString("quotation"));
//					Boolean confirmed = result.getBoolean("confirmed");
//				   //System.out.println("Request ID: "+reqID+"\t Customer Name "+cid+ "\t Employee Name: "+eid+"\t Quotation: "+quotation+"\tConfirmed: "+confirmed);
//					EmpDash.populateGeneralRequestTable();
//				}
//			} catch (NumberFormatException e) {
//
//				e.printStackTrace();
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//			
//		   
//	}
		
	


	

}
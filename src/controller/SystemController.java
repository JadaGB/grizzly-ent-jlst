package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.Customer;
import model.Employee;
import model.Equipment;
import model.EquipmentType;
import model.Request;

import view.ParentWindow;
import view.CusDash;
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
//		empSignin = new SignIn(this);
	}
	
	
	
	public void login1(String userType) {
		
		if (userType.equalsIgnoreCase("employee")) {
			SignIn.getUserType( userType);
			
		}else if (userType.equalsIgnoreCase("customer")) {
			SignIn.getUserType( userType);
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
	
	//To be called in EmpDash
	public void getAllRequestsInfo() {
		result = requestModel.readAll(myConn);
			try {
				while (result.next()) {
					
					String customerName= result.getString("FirstName")+" "+result.getString("LastName");
					String equipType= result.getString("Type");	
					String equipName= result.getString("Name");	
					String reqDate=result.getString("reqDate");
					Float quotation=Float.parseFloat(result.getString("quotation"));
					String confirmed = result.getBoolean("confirmed")==true?"confirmed":"denied";

//					//to test
//					System.out.println("Customer Name "+customerName+
//							   "\tRequested Equipment Type: "+equipType+"\tEquipment Name: "+equipName+"for "+reqDate+"\tQuotation: "+quotation+"\tConfirmed: "+confirmed);   
//					
					//create below function in EmpDash
//					EmpDash.populateGeneralRequestTable(customerName, equipType, equipName, reqDate, quotation, confirmed);
				}
				
				
			} catch (NumberFormatException e) {

				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public void getAllCustRequestsInfo(int cid) {
		try {
				
//		int index=stm.executeUpdate(query);
//		System.out.println("RowCount"+index);
		result = requestModel.custReq(myConn, cid);
		String data[][] = new String[100][6];
		
		int i=0;
		while (result.next()) {
				String customerName= result.getString("FirstName")+" "+result.getString("LastName");
				String equipType= result.getString("Type");	
				String equipName= result.getString("Name");	
				String reqDate=result.getString("reqDate");
				Float quotation=Float.parseFloat(result.getString("quotation"));
				String confirmed = result.getBoolean("confirmed")==true?"confirmed":"denied";
				 data[i][0] = customerName;
		         data[i][1] = equipType;
		         data[i][2] = equipName;
		         data[i][3] =quotation+" ";
		         data[i][4] =reqDate;
		         data[i][5] =confirmed;
		         i++;
//			   index++;
				//to test
				//System.out.println(customerName+" your request for the: "+equipType+" equipment"+equipName+" for "+"qoutation"+rentDate+" has been: "+confirmed);
					CusDash.populateCustomerRequestTable(data);

//				CusDash.populateCustomerRequestTable(index,customerName, equipType, equipName, reqDate, quotation, confirmed);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getAllCustPastTransactions(int cid) {
		try {
	
		result = requestModel.custReq(myConn, cid);
		String data[][] = new String[100][6];
		
		int i=0;
		while (result.next()) {
				String customerName= result.getString("FirstName")+" "+result.getString("LastName");
				String equipType= result.getString("Type");	
				String equipName= result.getString("Name");	
				String reqDate=result.getString("reqDate");
				Float quotation=Float.parseFloat(result.getString("quotation"));
				String confirmed = result.getBoolean("confirmed")==true?"confirmed":"denied";
				 data[i][0] = customerName;
		         data[i][1] = equipType;
		         data[i][2] = equipName;
		         data[i][3] =quotation+" ";
		         data[i][4] =reqDate;
		         data[i][5] =confirmed;
		         i++;

				//to test
				//System.out.println(customerName+" your request for the: "+equipType+" equipment"+equipName+" for "+"qoutation"+rentDate+" has been: "+confirmed);
					CusDash.populateCustomerTransactionTable(data);

//CusDash.populateCustomerRequestTable(index,customerName, equipType, equipName, reqDate, quotation, confirmed);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getAllScheduled(int cid) {
		try {
	
		result = requestModel.custReq(myConn, cid);
		String data[][] = new String[100][6];
		int i=0;
		while (result.next()) {
				String customerName= result.getString("FirstName")+" "+result.getString("LastName");
				String equipType= result.getString("Type");	
				String equipName= result.getString("Name");	
				String reqDate=result.getString("reqDate");
				Float quotation=Float.parseFloat(result.getString("quotation"));
				String confirmed = result.getBoolean("confirmed")==true?"confirmed":"denied";
				if(confirmed.equalsIgnoreCase("confirmed")) {
					 data[i][0] = customerName;
			         data[i][1] = equipType;
			         data[i][2] = equipName;
			         data[i][3] =quotation+" ";
			         data[i][4] =reqDate;
			         data[i][5] =confirmed;
			         i++;
				}
				else if(confirmed.equalsIgnoreCase("denied")) {
					continue;
				}
				
		       
//			   index++;
				//to test
				//System.out.println(customerName+" your request for the: "+equipType+" equipment"+equipName+" for "+"qoutation"+rentDate+" has been: "+confirmed);
					CusDash.populatescheduledTable(data);

//				CusDash.populateCustomerRequestTable(index,customerName, equipType, equipName, reqDate, quotation, confirmed);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
		
	


	

}

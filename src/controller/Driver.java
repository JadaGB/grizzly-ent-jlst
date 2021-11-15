package controller;

import java.sql.Connection;

import model.Customer;
import model.Date;
import model.Employee;
import model.Equipment;
import model.EquipmentType;
import model.Event;
import model.PastTransaction;
import model.Request;
import model.Scheduled;
 
public class Driver {

	public static void main(String[] args) {
		DBConnection dbc = new DBConnection();
		 Connection myConn = dbc.getConnection();
		 
		//test CRUD functions
//		Customer  cus = new Customer("examaple@gmail.com", "John", "Doe", "123456", "000-0000"); 
//		cus.create(myConn, cus); //+
//		cus.setCusID(myConn);
//		cus.getCusID(); //test

//		cus.delete(5, myConn); //+
		
//		cus.updatePersonalInfo("Jane", "Doe", "example1@gmail.com", "000-1001", 6, myConn); //+
		
//		cus.updatePassword("654321", 6, myConn); //+
//		
//		cus.readAll(myConn); //+
//		
//-----------------------------------------------------
//		
//		Date d = new Date();
//		
//-------------------------------------------------------------		
//		Employee e = new Employee("dummy", "Johnson",1, "00000");
		
//		e.create(myConn, e); //+
//		e.setEmpID(myConn); //+
//		System.out.println(e.getEmpID());  //+
		
//		e.updatePersonalInfo("Angella", "Brown", 2, 3, myConn); //+
//		e.readAll();// show names from id
//		
//		e.updatePassword("A108642", 3, myConn); //+
//		e.delete(7, myConn); //+
		
//-----------------------------------------------------------		
		
//		EquipmentType et = new EquipmentType("lighting");
//		et.create(et, myConn);
//		et.create(); "hibernate write"		
//		et.setEqTypeID(myConn);
//		System.out.println(et.getEqTypeID());
//		et.readAll(myConn); //+
//		
//		Equipment eq = new Equipment("example ",1, "no",0.00f); //primary constructor
//		eq.create(eq, myConn); //+
//		eq.setEqID(myConn); //+
//		System.out.println(eq.getEqID()); //+
		
//		eq.delete("4", myConn); //+
//		eq.readAll(myConn); //+
//		eq.updateEquipmentInfo(3, "Umberella Soft Box", 1, "no", 25000f, myConn); //+
//		eq.readAll(myConn); //+ //!show names from id
//	-------------------------------------------------------	
		
//		Event ev = new Event("Dummy Event"); //primary constructor
//		ev.create(); //+
//		ev.create(); "hibernate method"
//		ev.setEvID(myConn); //+
//		System.out.println(ev.getEvID()); //+
		
//		ev.readall();
//		ev.delete(102,myConn); //+
//		ev.readall();
//	-----------------------------------------------------------
		 
//		Request r = new Request(6, 2, new Date(26,11,2021)); //primary constructor
//		r.create(myConn, r); //+
//		r.setReqID(myConn); //+
//		System.out.println(r.getReqID()); //+
		
//		r.delete(3, myConn); //+
//		r.readAll(myConn); //+ //! show names from id
		
		//update for customer
		//r.updateCustRequestInfo( 2, 1, new Date(16,12,2021), myConn); //!
		
//		//update for employee
//		r.updateRequestInfo(2, 14580.25f, true, myConn); //+
//		
//		PastTransaction pt = new PastTransaction();
//		pt.create(myConn, pt);
//		pt.readAll();
//		pt.delete("123", myConn);
//		
//		
//		Scheduled sch = new Scheduled();
//		sch.create(sch);
//		//readAll, delete
//		
//		

	}

}

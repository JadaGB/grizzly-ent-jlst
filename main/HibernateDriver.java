package main;

import controller.SessionFactoryBuilder;

import model.EquipmentType;
import model.Event;


public class HibernateDriver {
	
	public static void main(String[] args) {
		EquipmentType eq = new EquipmentType("Speaker1");
		EquipmentType emp2=new EquipmentType("Microphone2");
		EquipmentType emp3=new EquipmentType("Monitor2");
				
		Event eq1 = new Event("C");
		Event emp12=new Event("P");
		Event emp13=new Event("S");
		
		
		eq1.create();
		emp12.create();
		emp13.create();
		eq1.read_All();
		eq.create();
		emp2.create();
		emp3.create();
		
		SessionFactoryBuilder.closeSessionFactory();
	}
	
}

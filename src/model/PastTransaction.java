package model;

import java.sql.Connection;
import java.time.LocalDate;

public class PastTransaction extends Request{
	
	private boolean completed;
	
	public PastTransaction() {
		super();
		this.completed = false;
	}

	public PastTransaction(int reqID, int cusID, int eid, Date requestDate, float quotation, boolean confirmed, boolean completed) {
		super( cusID,  eid, requestDate);
		quotation = super.getQuotation();
		confirmed = super.getConfirmed();
		this.completed = completed;
	}
	
	public PastTransaction( PastTransaction obj) {
		super(obj); //??
		this.completed = obj.completed;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
	public void create(PastTransaction pasttrans, Connection myConn) {
	//	//if(assertThat(((ChronoLocalDate) requestDate).isAfter(LocalDate.now()), is(true)))
	//	
	//	//Date d = new Date();
	//	//LocalDate.now();
	//	
		Date requestDate = pasttrans.getRequestDate();
		
		int rd = requestDate.getDay();
		int rm = requestDate.getMonth();
		int ry = requestDate.getYear();
		LocalDate reqD = LocalDate.of(ry, rm, rd);
		
		//reqD = (LocalDate)requestDate;
		if(reqD.isBefore(LocalDate.now())){
			
		}
		
	}
	
	@Override
	public String toString() {
		return "PastTransaction \n Request Information: \n" + super.toString()
				+ ", Completed: " + completed +  "\n";
	}
	
	
	
	
	
	
	

}

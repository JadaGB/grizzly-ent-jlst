package model;

public class PastTransaction extends Request{
	
	private boolean completed;
	
	public PastTransaction() {
		super();
		this.completed = false;
	}

	public PastTransaction(int reqID, Customer cName, Equipment eName, Date requestDate, float quotation, boolean confirmed, boolean completed) {
		super(reqID, cName, eName, requestDate, quotation, confirmed);
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

	@Override
	public String toString() {
		return "PastTransaction= \n Request Information: \n" + super.toString()
				+ ", Completed: " + completed +  "\n";
	}
	
	
	
	
	
	
	

}

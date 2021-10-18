package model;

public class Scheduled extends Request {
	
	private Date returnDate;
	//private String invoice; //???

	
	public Scheduled() {
		super();
		this.returnDate = new Date();
		//this.invoice = "";
	}
	
	public Scheduled(int reqID, Customer cName, Equipment eName, Date requestDate, float quotation, boolean confirmed, Date returnDate) 
	{
		super(reqID, cName, eName, requestDate, quotation, confirmed);
		this.returnDate = returnDate;
		//this.invoice = invoice;
	}
	
	public Scheduled(Scheduled obj) {
		super(obj);
		this.returnDate = obj.returnDate;
	
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
//	public String getInvoice() {
//		return invoice;
//	}
//	public void setInvoice(String invoice) {
//		this.invoice = invoice;
//	}

	@Override
	public String toString() {
		return "Scheduled Request: \n " +super.toString() + " Return Date: " + returnDate +"\n";
	}
	
	
	
	

}

package model;

public class Customer {
	
	private int cusID;
	private String Fname;
	private String LName;
	private String email;
	private String phoneNum;
	
	public Customer() {
		this.cusID = 0000;
		this.Fname = "John";
		this.LName = "Doe";
		this.email = "example@gmail.com";
		this.phoneNum = "000-0000";
	}

	public Customer(int cusID, String Fname, String LName, String email, String phoneNum) {
		this.cusID = cusID;
		this.Fname = Fname;
		this.LName = LName;
		this.email = email;
		this.phoneNum = phoneNum;
	}
	
	public Customer(Customer obj) {
		this.cusID = obj.cusID;
		this.Fname = obj.Fname;
		this.LName = obj.LName;
		this.email = obj.email;
		this.phoneNum = obj.phoneNum;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "Customer ID: " + cusID + ", First Name: " + Fname + ", Last Name: " + LName + ", Email: " + email + ", Phone Number: "
				+ phoneNum + "\n";
	}
	
	

}

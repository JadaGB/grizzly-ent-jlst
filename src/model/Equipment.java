package model;

public class Equipment {

	private int eqID;
	private String eqName;
	private String eqType;
	private String rentalStatus;
	private float cost;
	
	public Equipment() {
		this.eqID = 0000;
		this.eqName = "";
		this.eqType = "";
		this.rentalStatus = "";
		this.cost = 0;
	}

	public Equipment(int eqID, String eqName, String eqType, String rentalStatus, float cost) {
		this.eqID = eqID;
		this.eqName = eqName;
		this.eqType = eqType;
		this.rentalStatus = rentalStatus;
		this.cost = cost;
	}
	public Equipment(Equipment obj) {
		this.eqID = obj.eqID;
		this.eqName = obj.eqName;
		this.eqType = obj.eqType;
		this.rentalStatus = obj.rentalStatus;
		this.cost = obj.cost;
	}

	public int getEqID() {
		return eqID;
	}

	public void setEqID(int eqID) {
		this.eqID = eqID;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Equipment ID: " + eqID + ", Equipment Name: " + eqName + ", Equipment Type: " + eqType + ", Rental Status: "
				+ rentalStatus + ", Cost:" + cost + "\n";
	}
	
	
	
	
}

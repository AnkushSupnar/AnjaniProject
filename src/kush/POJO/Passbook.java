package kush.POJO;

public class Passbook {
	int ID, CID;
	Double Amount;
	String Mode;
	int TId;
	String Date;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getCID() {
		return CID;
	}
	public void setCID(int cID) {
		CID = cID;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double double1) {
		Amount = double1;
	}
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}
	public int getTId() {
		return TId;
	}
	public void setTId(int tId) {
		TId = tId;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	@Override
	public String toString() {
		return "Passbook [ID=" + ID + ", CID=" + CID + ", Amount=" + Amount + ", Mode=" + Mode + ", TId=" + TId
				+ ", Date=" + Date + "]";
	}
}

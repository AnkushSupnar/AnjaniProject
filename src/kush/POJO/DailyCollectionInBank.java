package kush.POJO;

public class DailyCollectionInBank {
	int ID,BankID;
	double Amount;
	String Date;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getBankID() {
		return BankID;
	}
	public void setBankID(int bankID) {
		BankID = bankID;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	@Override
	public String toString() {
		return "DailyCollectionInBank [ID=" + ID + ", BankID=" + BankID + ", Amount=" + Amount + ", Date=" + Date + "]";
	}
}

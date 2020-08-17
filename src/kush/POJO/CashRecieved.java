package kush.POJO;

public class CashRecieved {
	int id, CID;
	Double Amount;
	String Date;
	int BankId;
	int ChequeNo;
	String Note, BankName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getBankId() {
		return BankId;
	}
	public void setBankId(int bankId) {
		BankId = bankId;
	}
	public int getChequeNo() {
		return ChequeNo;
	}
	public void setChequeNo(int chequeNo) {
		ChequeNo = chequeNo;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	@Override
	public String toString() {
		return "CashRecieved [id=" + id + ", CID=" + CID + ", Amount=" + Amount + ", Date=" + Date + ", BankId="
				+ BankId + ", ChequeNo=" + ChequeNo + ", Note=" + Note + ", BankName=" + BankName + "]";
	}
}

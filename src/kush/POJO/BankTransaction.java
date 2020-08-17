package kush.POJO;

import java.sql.Date;
public class BankTransaction {

	int Id;
	String Particulars;
	Date Date;
	String ChequeNo;
	int BankId;
	Double Deposite, Withdraw;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getParticulars() {
		return Particulars;
	}

	public void setParticulars(String particulars) {
		Particulars = particulars;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date2) {
		Date = date2;
	}

	public String getChequeNo() {
		return ChequeNo;
	}

	public void setChequeNo(String chequeNo) {
		ChequeNo = chequeNo;
	}

	public int getBankId() {
		return BankId;
	}

	public void setBankId(int bankId) {
		BankId = bankId;
	}

	public Double getDeposite() {
		return Deposite;
	}

	public void setDeposite(Double deposite) {
		Deposite = deposite;
	}

	public Double getWithdraw() {
		return Withdraw;
	}

	public void setWithdraw(Double withdraw) {
		Withdraw = withdraw;
	}

	@Override
	public String toString() {
		return "BankTransaction [Id=" + Id + ", Particulars=" + Particulars + ", Date=" + Date + ", ChequeNo="
				+ ChequeNo + ", BankId=" + BankId + ", Deposite=" + Deposite + ", Withdraw=" + Withdraw + "]";
	}

}

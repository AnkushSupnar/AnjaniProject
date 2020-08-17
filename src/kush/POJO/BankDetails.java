package kush.POJO;

public class BankDetails {
	int ID;
	String BankName, AccountNo, AccountType, IFC, PersonName, BankAddress;
	Double BankBalance;
	String BankCode;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getIFC() {
		return IFC;
	}
	public void setIFC(String iFC) {
		IFC = iFC;
	}
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public String getBankAddress() {
		return BankAddress;
	}
	public void setBankAddress(String bankAddress) {
		BankAddress = bankAddress;
	}
	public Double getBankBalance() {
		return BankBalance;
	}
	public void setBankBalance(Double bankBalance) {
		BankBalance = bankBalance;
	}
	public String getBankCode() {
		return BankCode;
	}
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	@Override
	public String toString() {
		return "BankDetails [ID=" + ID + ", BankName=" + BankName + ", AccountNo=" + AccountNo + ", AccountType="
				+ AccountType + ", IFC=" + IFC + ", PersonName=" + PersonName + ", BankAddress=" + BankAddress
				+ ", BankBalance=" + BankBalance + ", BankCode=" + BankCode + "]";
	}

}

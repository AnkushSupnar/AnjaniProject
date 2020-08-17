package kush.POJO;

public class Customer {
	int ID;
	String CustomerKey, FName, MName, LName, MobileNo, EmailId;
	int FlatNo;
	String StreetName, City, District, Taluka;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCustomerKey() {
		return CustomerKey;
	}
	public void setCustomerKey(String customerKey) {
		CustomerKey = customerKey;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public String getMName() {
		return MName;
	}
	public void setMName(String mName) {
		MName = mName;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public int getFlatNo() {
		return FlatNo;
	}
	public void setFlatNo(int flatNo) {
		FlatNo = flatNo;
	}
	public String getStreetName() {
		return StreetName;
	}
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getTaluka() {
		return Taluka;
	}
	public void setTaluka(String taluka) {
		Taluka = taluka;
	}
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", CustomerKey=" + CustomerKey + ", FName=" + FName + ", MName=" + MName
				+ ", LName=" + LName + ", MobileNo=" + MobileNo + ", EmailId=" + EmailId + ", FlatNo=" + FlatNo
				+ ", StreetName=" + StreetName + ", City=" + City + ", District=" + District + ", Taluka=" + Taluka
				+ "]";
	}
}

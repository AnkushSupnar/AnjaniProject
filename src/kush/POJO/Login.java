package kush.POJO;

public class Login {
	int ID;
	String UserName,Password;
	int EmployeeId;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	@Override
	public String toString() {
		return "Login [ID=" + ID + ", UserName=" + UserName + ", Password=" + Password + ", EmployeeId=" + EmployeeId
				+ "]";
	}

}

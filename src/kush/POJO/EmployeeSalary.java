package kush.POJO;

import java.sql.Date;

public class EmployeeSalary {
	int ID,EmpId;
	Double SalaryPaid;
	int Month, Year;
	Date date;
	int payId;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getEmpId() {
		return EmpId;
	}
	public void setEmpId(int empId) {
		EmpId = empId;
	}

	public Double getSalaryPaid() {
		return SalaryPaid;
	}

	public void setSalaryPaid(Double salaryPaid) {
		SalaryPaid = salaryPaid;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}
	@Override
	public String toString() {
		return "EmployeeSalary [ID=" + ID + ", EmpId=" + EmpId + ", SalaryPaid=" + SalaryPaid + ", Month=" + Month
				+ ", Year=" + Year + ", date=" + date + "]";
	}
}

package kush.POJO;

import java.time.LocalDate;

public class EmployeeAttendance {
	int id;
	int employeeId;
	LocalDate date;
	String attendance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [id=" + id + ", employeeId=" + employeeId + ", date=" + date + ", attendance="
				+ attendance + "]";
	}

}

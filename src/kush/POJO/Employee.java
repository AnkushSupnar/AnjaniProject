package kush.POJO;

public class Employee 
{
	public int id;
	public String fname;
	public String mname;
	public String lname;
	public String address;
	public String cno;
	public String designation;
	float salary;
	String salrytype,status;
	

	public int getId() {
		return id;
	}


	public String getFname() {
		return fname;
	}


	public String getMname() {
		return mname;
	}


	public String getLname() {
		return lname;
	}
	public String getAddress() {
		return address;
	}

	public String getCno() {
		return cno;
	}


	public String getDesignation() {
		return designation;
	}


	public float getSalary() {
		return salary;
	}


	public String getSalrytype() {
		return salrytype;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public void setSalary(float salary) {
		this.salary = salary;
	}


	public void setSalrytype(String salrytype) {
		this.salrytype = salrytype;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", address="
				+ address + ", cno=" + cno + ", designation=" + designation + ", salary=" + salary + ", salrytype="
				+ salrytype + ", status=" + status + "]";
	}


	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Employee e = new Employee();
		e.setId(1);
		e.setFname("Ankush");
		e.setMname("Sawaleram");
		e.setLname("Supnar");
		e.setAddress("Amalner");
		e.setCno("9960855742");
		e.setDesignation("Manager");
		e.setSalary(5000.0f);
		e.setSalrytype("Monthly");
System.out.println("Employee"+e.toString());
	}

}

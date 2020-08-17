package kush.POJO;

import java.sql.ResultSet;

import ankush.CommonMethods;

public class AddBean 
{
	public static ResultSet rs;
	public static int addEmployeeSalary(kush.POJO.EmployeeSalary emp)
	{
		try {
			return CommonMethods.addRecord("insert into employeesalary "
					+ "(ID,EmpId,SalaryPaid,Month,Year,Date) values("
					+emp.getID()+","
					+emp.getEmpId()+","
					+emp.getSalaryPaid()+","
					+emp.getMonth()+","
					+emp.getYear()+",STR_TO_DATE('"
					+emp.getDate()+"','%d/%m/%Y'))");
		} catch (Exception e) {
			System.out.println("Error i Saving Employee Salary "+e.getMessage());
			return 0;
		}
	}
	public static int addPurchaseparty(PurchaseParty p)
	{
	try {
		rs = CommonMethods.selectQuery("select Id from purchaseparty where ID="+p.getId());
		if(rs.next())
		{
			return 2;
		}
		return CommonMethods.addRecord("insert into purchaseparty"
				+ "(Id, Name, Address, Contact)values("
				+p.getId()+",'"
				+p.getName()+"','"
				+p.getAddress()+"','"
				+p.getContact()+"')");
	} catch (Exception e) {
		System.out.println("Error in SavePurchasseParty "+e.getMessage());
		return 0;
	}}

	public static int addPurchaseTransaction(PurchaseTransaction p)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from purchasetransaction where Id="+p.getId());
			if(rs.next())
			{
				return 2;
			}
			return CommonMethods.addRecord("insert into purchasetransaction"
					+ "(ID,ItemName,QTY,Rate,Amount,BillNo) values("
					+p.getId()+",'"
					+p.getItemName()+"',"
					+p.getQty()+","
					+p.getRate()+","
					+p.getAmount()+","
					+p.getBillNo()+")");
		} catch (Exception e) {
			System.out.println("Error in savePurchaseTransaction");
			return 0;
		}
	}
	public static int addCategoryMaster(CategoryMaster c)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from categorymaster where Id="+c.getId());
			if(rs.next())
			{
			return 2;	
			}
			else
			{
			return CommonMethods.addRecord("insert into CategoryMaster values(" 
					+ c.getId() + ",'" 
					+ c.getName()+"','"
					+c.getStock()+"')");
			}
		} catch (Exception e) {
			System.out.println("Error in Saving category master"+e.getMessage());
			return 0;
		}
	}
	public static int addTempTransaction(TempTransaction t)
	{
		try {
			
			rs = CommonMethods.selectQuery("Select Id from TempTransaction where ItemName='"+t.getItemName()+"' AND TableNo="+t.getTableNo()+"");
			if(rs.next())
			{
				return 2;
			}
			else
			return CommonMethods.addRecord("insert into TempTransaction"
					+ "(ID,ItemName,qty,Rate,amt,tableno,waitorid,printqty) values("
					+t.getId()+",'"
					+t.getItemName()+"',"
					+t.getQty()+","
					+t.getRate()+","
					+t.getAmt()+","
					+t.getTableNo()+","
					+t.getWaitorId()+","
					+t.getPrintQty()+")");
		} catch (Exception e) {
			System.out.println("Error in Adding TempTransaction" +e.getMessage());
			return 0;
		}
	}
	public static int addBill(Bill b)
	{
		try {
			rs = CommonMethods.selectQuery("select BillNo from Bill where BillNo="+b.getBillNO());
			if(rs.next())
			{
				//exist
				return 2;
			}
			else
			{
				//add
				//System.out.println("Add = "+b);
				//return 1;
				return CommonMethods.addRecord("insert into Bill"
						+ "(BillNO, BillAmt, Discount, CUstomerID, Waitorid, Tableno, userid, BillDate, Paymode, status, Time)"
						+ "values("+b.getBillNO()+","
						+b.getBillAmt()+","
						+b.getDiscount()+","
						+b.getCUstomerID()+","
						+b.getWaitorid()+","
						+b.getTableno()+","
						+b.getUserid()+","
						+"DATE('"+b.getBillDate()+"'),'"
						+b.getPaymode()+"','"
						+b.getStatus()+"',"
						+"DATE('"+b.getTime()+"'))");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addTransaction(Transaction t)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from Transaction where Id="+t.getId());
			if(rs.next())
			{
				//exist
				return 2;
			}
			else
			{
				//add New
				//return 1;
				return CommonMethods.addRecord("insert into Transaction "
						+ "(id, ItemName, qty, rate, amt, bill) values("
						+t.getId()+",'"
						+t.getItemName()+"',"
						+t.getQty()+","
						+t.getRate()+","
						+t.getAmt()+","
						+t.getBill()+")");
			}
		} catch (Exception e) {
			System.out.println("Error in saveTransaction= "+e.getMessage());
			return 0;
		}
	}
	public static int addBankDetails(BankDetails b)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from BankDetails where Id="+b.getID());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into BankDetails"
						+ "(ID, BankName, AccountNo, AccountType, IFC, PersonName, BankAddress, BankBalance, BankCode)"
						+ "values("+b.getID()+",'"
						+b.getBankName()+"','"
						+b.getAccountNo()+"','"
						+b.getAccountType()+"','"
						+b.getIFC()+"','"
						+b.getIFC()+"','"
						+b.getPersonName()+"','"
						+b.getBankAddress()+"',"
						+b.getBankBalance()+",'"
						+b.getBankCode()+"')");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addCashReceived(CashRecieved c)
	{
		try {//id, CID, Amount, Date, BankId, ChequeNo, Note, BankName
			rs = CommonMethods.selectQuery("select ID from cashrecieved where ID="+c.getId());
			if(rs.next())
			{
				//alredy Exist
				
				return 2;
			}
			else
			{
				//Add New
				return CommonMethods.addRecord("insert into cashrecieved "
						+ "(id, CID, Amount, Date, BankId, ChequeNo, Note, BankName) values("
						+c.getId()+","
						+c.getCID()+","
						+c.getAmount()+","
						+" DATE('"+c.getDate()+"'),"
						+c.getBankId()+","
						+c.getChequeNo()+",'"
						+c.getNote()+"','"
						+c.getBankName()+"')");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addCustomer(Customer c)
	{
		try {//
			rs = CommonMethods.selectQuery("select Id from Customer where ID="+c.getID());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into Customer"
						+ "(ID, CustomerKey, FName, MName, LName, MobileNo, EmailId, "
						+ "FlatNo, StreetName, City, District, Taluka) values("
						+c.getID()+",'"
						+c.getCustomerKey()+"','"
						+c.getFName()+"','"
						+c.getMName()+"','"
						+c.getLName()+"','"
						+c.getMobileNo()+"','"
						+c.getEmailId()+"',"
						+c.getFlatNo()+",'"
						+c.getStreetName()+"','"
						+c.getCity()+"','"
						+c.getDistrict()+"','"
						+c.getTaluka()+"')");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addDailyCollectionInBank(DailyCollectionInBank d)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from dailycollectioninbank where Id="+d.getID());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into dailycollectioninbank"
						+ "(ID, BankID, Amount, Date) values("
						+d.getID()+","
						+d.getBankID()+","
						+d.getAmount()+",'"
						+"DATE('"+d.getDate()+"'))");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addEmployee(Employee e)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from Employee where Id="+e.getId());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into Employee"
						+ "(Id,FNAME,MNAME,LNAME,ADDRESS,CONTACT,DESIGNATION,SALARY,SALARYTYPE,status)"
						+ "values("+e.getId()+",'"
						+e.getFname()+"','"
						+e.getMname()+"','"
						+e.getLname()+"','"
						+e.getAddress()+"','"
						+e.getCno()+"','"
						+e.getDesignation()+"',"
						+e.getSalary()+",'"
						+e.getSalrytype()+"','"
						+e.getStatus()+"')");
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			return 0;
		}
	}
	public static int addItem(Item i)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from itemmaster where Id="+i.getID());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into ItemMaster"
						+ "(ID, ItemName, Catid, Rate, ItemCode) Values("
						+i.getID()+",'"
						+i.getItemName()+"',"
						+i.getCatid()+","
						+i.getRate()+","
						+i.getItemCode()+")");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addItemStock(ItemStock i)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from ItemStock where id="+i.getId());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into ItemStock"
						+ "(Id, ItemName, Stock)values("
						+i.getId()+",'"
						+i.getItemName()+"',"
						+i.getStock());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addLogin(Login l)
	{
		try {
			rs = CommonMethods.selectQuery("select id from Login where Id="+l.getID());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into Login"
						+ " (ID, UserName, Password, EmployeeId)values("
						+l.getID()+",'"
						+l.getUserName()+"','"
						+l.getPassword()+"',"
						+l.getEmployeeId()+")");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static int addPassbook(Passbook p)
	{
		try {
			rs = CommonMethods.selectQuery("select id from passbook where Tid="+p.getTId());
			if(rs.next())
			{
				//update transaction
				//System.out.println("updating passbook");
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into Passbook"
						+ " (ID, CID, Amount, Mode, TId, Date)values("
						+p.getID()+","
						+p.getCID()+","
						+p.getAmount()+",'"
						+p.getMode()+"',"
						+p.getTId()+","
						+"DATE('"+p.getDate()+"'))");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public static int addTable(Table t)
	{
		try {
			rs = CommonMethods.selectQuery("select ID from tablemaster where ID="+t.getId());
			if(rs.next())
			{
				return 2;
			}
			else
			{
				return CommonMethods.addRecord("insert into TableMaster "
						+ "(id, TableName, DESCRIPTION) values("
						+t.getId()+",'"
						+t.getTableName()+"','"
						+t.getDESCRIPTION()+"')");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}




}
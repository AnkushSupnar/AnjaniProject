
package ankush;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import kush.POJO.BankTransaction;
import kush.POJO.Bill;
import kush.POJO.EmployeeAttendance;
import kush.POJO.EmployeeSalary;
import kush.POJO.PurchaseBill;
import kush.POJO.TempTransaction;
import kush.POJO.Transaction;

public class GetData {

	public static ResultSet rs;
	public static List<TempTransaction>getTableData(int tid)
	{
		try {
			List<TempTransaction> list = new ArrayList<>();
			rs = CommonMethods.selectQuery("select "
					+ "ID,ItemName,qty,Rate,amt,tableno,waitorid,printqty"
					+ " from TempTransaction where tableno="+tid);
			while(rs.next())
			{
				TempTransaction t = new TempTransaction();
				t.setId(rs.getInt(1));
				t.setItemName(rs.getString(2));
				t.setQty(rs.getFloat(3));
				t.setRate(rs.getFloat(4));
				t.setAmt(rs.getFloat(5));
				t.setTableNo(rs.getInt(6));
				t.setWaitorId(rs.getInt(7));
				t.setPrintQty(rs.getFloat(8));
				list.add(t);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getTempTransactionId(String itemName, int tid,float rate)
	{
		try {
			
			rs = CommonMethods.selectQuery("select id from TempTransaction "
					+ "where TableNo="+tid+" AND "
							+ "ItemName='"+itemName+"' AND "
									+ " rate="+rate);
			if(rs.next())
				return rs.getInt(1);
			else
				return 0;
		} catch (Exception e) {
			System.out.println("Error in Getting TempTransaction ID "+e.getMessage());
			return 0;
		}
	}

	public static int getNewBillNo(int tid)
	{
		try {
			//check bill in Bill Table
			rs = CommonMethods.selectQuery("select bill.BillNo "
					+ "from bill,Transaction where "
					+ "transaction.bill=bill.billno AND "
					+ "bill.status='no' AND bill.tableNo="+tid);
			if(rs.next())
			{
				System.out.println("Getting Old billno");
				return rs.getInt(1);
			}
			else {
				System.out.println("Getting New Billno");
				return CommonMethods.getId("Select BillNo from Bill order by BillNo");
			}
		} catch (Exception e) {
			System.out.println("Error in getting new BillNo "+e.getMessage());
			return 0;
		}
	}
	public static List<Transaction> getClosedTableData(int tid)
	{
		try {
			List<Transaction> list = new ArrayList<>();
			Transaction tr;
			rs = CommonMethods.selectQuery("select"
					+ " Transaction.id,Transaction.ItemName,Transaction.qty,Transaction.rate,"
					+ "Transaction.amt,Transaction.bill "
					+ "from bill,transaction "
					+ "where transaction.bill=bill.billno "
					+ "AND bill.status='no' "
					+ "and bill.tableNo="+tid);
			while(rs.next())
			{
				tr = new Transaction();
				tr.setId(rs.getInt(1));
				tr.setItemName(rs.getString(2));
				tr.setQty(rs.getFloat(3));
				tr.setRate(rs.getFloat(4));
				tr.setAmt(rs.getFloat(5));
				tr.setBill(rs.getInt(6));
				list.add(tr);
			}
			rs.close();
			return list;	
		} catch (Exception e) {
			System.out.println("Error in Getting Closed Table Data "+e.getMessage());
			return null;
		}
	}
	public static int checkTableClosed(int tid)
	{
		try {
			rs = CommonMethods.selectQuery("select Id from TempTransaction where tableno="+tid);
			if(rs.next()){
				return 1;//not closed
			}
			else
				return 0;//closed
		} catch (Exception e) {
			System.out.println("error in getting table closed "+e.getMessage());
			return 2;
		}
	}
	public static int getUnpaidBillNo(int tid)
	{
		try {
			rs = CommonMethods.selectQuery("select BillNo from Bill where TableNo="+tid+" and status='no'");
			if(rs.next())
				return rs.getInt(1);
			else
				return 0;
		} catch (Exception e) {
			System.out.println("Error in Getting UnpaidBillNo "+e.getMessage());
			return 0;
		}
	}
	public static List<Bill>getAllBill(int billno,int limit)
	{
		try {
			//18637;
			List<Bill> list = new ArrayList<>();
			Bill bill;
			//18636 18637  
			rs = CommonMethods.selectQuery("select "
					+ "BillNO,BillAmt,Discount,CUstomerID,Waitorid,Tableno,userid,Date_Format(BillDate,'%d-%m-%Y'),"
					+ "Paymode,status,Time from bill where billno>="+(billno-limit)+" AND status='paid'");
			while(rs.next())
			{
				bill = new Bill();
				bill.setBillNO(rs.getInt(1));
				bill.setBillAmt(rs.getFloat(2));
				bill.setDiscount(rs.getFloat(3));
				bill.setCUstomerID(rs.getInt(4));
				bill.setWaitorid(rs.getInt(5));
				bill.setTableno(rs.getInt(6));
				bill.setUserid(rs.getInt(7));
				bill.setBillDate(rs.getString(8));
				bill.setPaymode(rs.getString(9));
				bill.setStatus(rs.getString(10));
				bill.setTime(rs.getString(11));
				list.add(bill);
			}
			
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("Getting All Bill "+e.getMessage());
			return null;
		}
	}

	public static Bill getBill(int billno)
	{
		try {
			Bill bill = new Bill();
			rs = CommonMethods.selectQuery("select "
					+ "BillNO,BillAmt,Discount,CUstomerID,Waitorid,Tableno,userid,BillDate,"
					+ "Paymode,status,Time from bill where billno>="+(billno));
			if(rs.next())
			{
				bill = new Bill();
				bill.setBillNO(rs.getInt(1));
				bill.setBillAmt(rs.getFloat(2));
				bill.setDiscount(rs.getFloat(3));
				bill.setCUstomerID(rs.getInt(4));
				bill.setWaitorid(rs.getInt(5));
				bill.setTableno(rs.getInt(6));
				bill.setUserid(rs.getInt(7));
				bill.setBillDate(rs.getString(8));
				bill.setPaymode(rs.getString(9));
				bill.setStatus(rs.getString(10));
				bill.setTime(rs.getString(11));
			}
			rs.close();
			return bill;
			
		} catch (Exception e) {
			System.out.println("Error in Getting Bill "+e.getMessage());
			return null;
		}
	}
	public static List<Transaction> getTransactionList(int billno)
	{
		try {
			Transaction tr;
			List<Transaction>list = new ArrayList<>();
			rs = CommonMethods.selectQuery("select "
					+ "id, ItemName, qty, rate, amt, bill from Transaction where bill="+billno);
			while(rs.next())
			{
				tr = new Transaction();
				tr.setId(rs.getInt(1));
				tr.setItemName(rs.getString(2));
				tr.setQty(rs.getFloat(3));
				tr.setRate(rs.getFloat(4));
				tr.setAmt(rs.getFloat(5));
				tr.setBill(rs.getInt(6));
				list.add(tr);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("Error in Getting Transaction List "+e.getMessage());
			return null;
		}
	}
	
	public static List<Bill> getPeriodBills(LocalDate fromDate, LocalDate toDate, int customerId)
	{
		try {
			List<Bill> list = new ArrayList<>();
			Bill bill;
			String query = "select "
					+ "BillNO,BillAmt,Discount,CUstomerID,Waitorid,Tableno,"
					+ "userid,Date_Format(BillDate,'%d-%m-%Y'),"
					+ "Paymode,status,Time from Bill" + " where BillDate BETWEEN '" + fromDate
					+ "' AND '"
					+ toDate
					+ "' AND CUstomerID=" + customerId;
			System.out.println(query);
			rs = CommonMethods.selectQuery(query);
			while (rs.next()) {
				bill=new Bill();
				bill.setBillNO(rs.getInt("BillNO"));
				bill.setBillAmt(rs.getFloat("BillAmt"));
				bill.setDiscount(rs.getFloat("Discount"));
				bill.setCUstomerID(rs.getInt("CUstomerID"));
				bill.setWaitorid(rs.getInt("Waitorid"));
				bill.setTableno(rs.getInt("Tableno"));
				bill.setUserid(rs.getInt("userid"));
				bill.setBillDate(rs.getString(8));
				bill.setPaymode(rs.getString("Paymode"));
				bill.setStatus(rs.getString("status"));
				bill.setTime(rs.getString("Time"));
				list.add(bill);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("Error in Fetching Data in getPeriodBills "+e.getMessage());
			return null;
		}
	}

	public static List<BankTransaction> getBankTransactionList(String particular) {
		try {
			List<BankTransaction> list = new ArrayList<>();
			BankTransaction bt;
			rs = CommonMethods.selectQuery("select "
					+ "Id,Particulars,Date,ChequeNo,BankId,Deposite,Withdraw from BankTransaction"
					+ " where Particulars='" + particular + "'");
			while (rs.next()) {
				bt = new BankTransaction();
				bt.setId(rs.getInt(1));
				bt.setParticulars(rs.getString(2));
				bt.setDate(rs.getDate(3));
				bt.setChequeNo(rs.getString(4));
				bt.setBankId(rs.getInt(5));
				bt.setDeposite(rs.getDouble(6));
				bt.setWithdraw(rs.getDouble(7));
				list.add(bt);
			}
			rs.close();
			return list;

		} catch (Exception e) {
			System.out.println("Error in Getting BankTransaction List " + e.getMessage());
			return null;
		}
	}

	public static BankTransaction getBankTransaction(int id) {
		try {
			BankTransaction bt;
			rs = CommonMethods.selectQuery("select "
					+ "Id,Particulars,Date,ChequeNo,BankId,Deposite,Withdraw from BankTransaction where Id=" + id);
			if (rs.next()) {
				bt = new BankTransaction();
				bt.setId(rs.getInt(1));
				bt.setParticulars(rs.getString(2));
				bt.setDate(rs.getDate(3));
				bt.setChequeNo(rs.getString(4));
				bt.setBankId(rs.getInt(5));
				bt.setDeposite(rs.getDouble(6));
				bt.setWithdraw(rs.getDouble(7));
				return bt;
			} else
				return null;

		} catch (Exception e) {
			System.out.println("Error in getting Bank Transaction " + e.getMessage());
			return null;
		}
	}

	public static PurchaseBill getPurchaseBill(int billNo) {
		try {
			PurchaseBill bill;
			rs = CommonMethods.selectQuery("select " + "BillNo, PartyId, Amount, Date, GST, "
					+ "OtherTax, ReffNo, Pay, PayId " + "from PurchaseBill where BillNo=" + billNo);
			if (rs.next()) {
				bill = new PurchaseBill();
				bill.setBillNo(rs.getInt(1));
				bill.setPartyId(rs.getInt(2));
				bill.setAmount(rs.getDouble(3));
				bill.setDate(rs.getDate(4));
				bill.setGST(rs.getDouble(5));
				bill.setOtherTax(rs.getDouble(6));
				bill.setReffNo(rs.getString(7));
				bill.setPay(rs.getString(8));
				bill.setPayId(rs.getInt(9));
				return bill;
			}
			else
				return null;
		} catch (Exception e) {
			System.out.println("Error in Getting Purchase Bill " + e.getMessage());
			return null;
		}
	}

	public static List<PurchaseBill> getPurchaseBillList() {
		try {
			PurchaseBill bill;
			List<PurchaseBill> list = new ArrayList<>();
			rs = CommonMethods.selectQuery("select " + "BillNo, PartyId, Amount, Date, GST, "
					+ "OtherTax, ReffNo, Pay, PayId " + "from PurchaseBill order by BillNo");
			while (rs.next()) {
				bill = new PurchaseBill();
				bill.setBillNo(rs.getInt(1));
				bill.setPartyId(rs.getInt(2));
				bill.setAmount(rs.getDouble(3));
				bill.setDate(rs.getDate(4));
				bill.setGST(rs.getDouble(5));
				bill.setOtherTax(rs.getDouble(6));
				bill.setReffNo(rs.getString(7));
				bill.setPay(rs.getString(8));
				bill.setPayId(rs.getInt(9));
				list.add(bill);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("error in Getting Purchase Bill List " + e.getMessage());
			return null;
		}
	}

	public static int getPurchaseBillPayId(int billno) {
		try {
			rs = CommonMethods.selectQuery("select PayId from PurchaseBill where BillNo=" + billno);
			if (rs.next())
				return rs.getInt(1);
			else
				return 0;
		} catch (Exception e) {
			System.out.println("Error in Getting Pay Id " + e.getMessage());
			return 0;
		}
	}

	public static List<String> getParticularsList()
	{
		try {
			List<String> list = new ArrayList<>();
			rs = CommonMethods.selectQuery("select DISTINCT(Particulars) from BankTransaction");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("Error in getting Particular List=" + e.getMessage());
			return null;
		}
	}

	public static List<EmployeeSalary> getEmployeeSalaryMonthWise(int month, int year) {
		try {
			List<EmployeeSalary> list = new ArrayList<>();
			EmployeeSalary es = null;
			rs = CommonMethods.selectQuery(
					"select ID, EmpId, SalaryPaid, Month, Year,DATE_FORMAT(Date,'%d-%m-%Y')  from employeesalary where Month="
							+ month + " AND Year=" + year);
				while (rs.next()) {
					es = new EmployeeSalary();
					es.setID(rs.getInt(1));
					es.setDate(rs.getDate(6));
					es.setEmpId(rs.getInt(2));
					es.setMonth(rs.getInt(4));
					es.setYear(rs.getInt(5));
					es.setSalaryPaid(rs.getDouble(3));
					list.add(es);

			}
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("Error in Getting Employee Salary Month Wise " + e.getMessage());
			return null;
		}
	}

	public static List<EmployeeAttendance> getEmployeeAttendanceList(LocalDate date) {
		try {

			EmployeeAttendance ea = null;
			List<EmployeeAttendance> list = new ArrayList<>();
			rs = CommonMethods.selectQuery(
					"select Id, EmpId, Date, Attendance from EmployeeAttendance where Date='" + date + "'");
			while (rs.next()) {
				ea = new EmployeeAttendance();
				ea.setId(rs.getInt(1));
				ea.setEmployeeId(rs.getInt(2));
				ea.setDate(rs.getDate(3).toLocalDate());
				ea.setAttendance(rs.getString(4));
				list.add(ea);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			System.out.println("Error in Getting employee Attendance List " + e.getMessage());
			return null;
		}
	}

	public static List<String[]> getEnglishItemName(int catid) {
		try {
			List<String[]> list = new ArrayList<>();
			rs = CommonMethods.selectQuery(
					"select ItemMaster.Itemname,ItemEnglishName.EnglishName from ItemMaster,CategoryMaster,ItemEnglishName where ItemMaster.Id=ItemEnglishname.ItemId AND CategoryMaster.id=Itemmaster.CatId and CategoryMaster.id="
							+ catid);

			while (rs.next()) {
				String[] obj = new String[2];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				list.add(obj);

			}
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Object[]> getCategoryEnglishName() {
		try {
			List<Object[]> list = new ArrayList<>();
			rs = CommonMethods.selectQuery("select Id, CatId, EnglishName from categoryenglishname");
			while (rs.next()) {
				Object[] obj = new Object[3];
				obj[0] = rs.getInt(1);
				obj[1] = CommonLogic.getCategoryName(rs.getInt(2));
				obj[2] = rs.getString(3);
				list.add(obj);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Object[]> getTodaysMenu(int catid) {
		try {
			List<Object[]> list = new ArrayList<>();
			rs = CommonMethods.selectQuery("select ItemId, CatId, Status from todaysmenu where CatId=" + catid);
			while (rs.next()) {
				Object[] obj = new Object[3];
				obj[0] = rs.getInt(1);
				obj[1] = rs.getInt(2);
				obj[2] = rs.getString(3);
				// System.out.println(obj[0] + "\t" + obj[1] + "\t" + obj[2]);
				list.add(obj);
			}
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

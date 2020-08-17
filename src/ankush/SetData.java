package ankush;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import kush.POJO.BankTransaction;
import kush.POJO.Bill;
import kush.POJO.EmployeeAttendance;
import kush.POJO.EmployeeSalary;
import kush.POJO.PurchaseBill;
import kush.POJO.PurchaseTransaction;
import kush.POJO.TempTransaction;
import kush.POJO.Transaction;

public class SetData {
	public static ResultSet rs;

	public static int addInTempTransaction(TempTransaction t) {
		try {
			rs = CommonMethods.selectQuery("select id from TempTransaction where " + "ItemName='" + t.getItemName()
					+ "' AND " + "Rate=" + t.getRate() + " ANd " + "TableNo=" + t.getTableNo());
			if (rs.next()) {
				t.setId(rs.getInt(1));
			}
			return AddPOJO.saveTempTransaction(t);

		} catch (Exception e) {
			System.out.println("Error in Adding TempTransaction " + e.getMessage());
			return 0;
		}
	}

	public static int saveBill(Bill bill) {
		try {
			rs = CommonMethods.selectQuery("select Billno from Bill where Billno=" + bill.getBillNO());
			if (rs.next()) {
				return CommonMethods.updateRecord("update Bill set " + "BillAmt=" + bill.getBillAmt() + ","
						+ "Discount=" + bill.getDiscount() + "," + "CUstomerID=" + bill.getCUstomerID() + ","
						+ "Waitorid=" + bill.getWaitorid() + "," + "Tableno=" + bill.getTableno() + "," + "userid="
						+ bill.getUserid() + "," + "BillDate=NOW(),Paymode='Cash',status='no', Time=CURDATE() "
						+ "where billno=" + bill.getBillNO());
			} else
				return CommonMethods.addRecord(" insert into Bill(BillNO, BillAmt, Discount, CUstomerID, "
						+ "Waitorid, Tableno, userid, BillDate, Paymode, status, Time)values(" + +bill.getBillNO() + ","
						+ bill.getBillAmt() + "," + bill.getDiscount() + "," + bill.getCUstomerID() + ","
						+ bill.getWaitorid() + "," + bill.getTableno() + "," + bill.getUserid() + "," + "NOW(),'"
						+ bill.getPaymode() + "','" + bill.getStatus() + "'," + "CURDATE())");
		} catch (Exception e) {
			System.out.println("Error in saving bill " + e.getMessage());
			return 0;
		}
	}

	public static int saveTransaction(List<TempTransaction> list, Bill bill) {
		try {
			Iterator<TempTransaction> i = list.iterator();
			TempTransaction temp;
			while (i.hasNext()) {
				temp = i.next();
				int f = CommonMethods
						.addRecord("insert into Transaction" + "(id, ItemName, qty, rate, amt, bill) values("
								+ CommonMethods.getId("select Id from Transaction order by ID") + ",'"
								+ temp.getItemName() + "'," + temp.getQty() + "," + temp.getRate() + "," + temp.getAmt()
								+ "," + bill.getBillNO() + ")");
				if (f == 1) {
					CommonMethods.delete(temp.getId());
				}
			}
			return 1;
		} catch (Exception e) {
			System.out.println("Error in Saving Transaction " + e.getMessage());
			return 0;
		}
	}

	public static int saveTransaction(Transaction tr) {
		try {
			return CommonMethods.addRecord("insert into Transaction" + "(id, ItemName, qty, rate, amt, bill)"
					+ "Values(" + tr.getId() + ",'" + tr.getItemName() + "'," + tr.getQty() + "," + tr.getRate() + ","
					+ tr.getAmt() + "," + tr.getBill() + ")");
		} catch (Exception e) {
			System.out.println("Error in Saving Transaction " + e.getMessage());
			return 0;
		}
	}

	public static int updateBill(Bill bill) {
		try {
			rs = CommonMethods.selectQuery("select Billno from Bill where Billno=" + bill.getBillNO());
			if (rs.next()) {
				System.out.println("Bill For Update " + bill.getBillNO());
				return CommonMethods.updateRecord("update Bill set " + "BillAmt=" + bill.getBillAmt() + ","
						+ "Discount=" + bill.getDiscount() + "," + "CUstomerID=" + bill.getCUstomerID() + ","
						+ "Waitorid=" + bill.getWaitorid() + "," + "Tableno=" + bill.getTableno() + "," + "userid="
						+ bill.getUserid() + "," + "Paymode='Cash',status='" + bill.getStatus() + "' "
						+ "where billno=" + bill.getBillNO());
			} else
				return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int saveBankTransaction(BankTransaction bt) {
		try {
			return CommonMethods.addRecord(
					"insert into BankTransaction "
							+ "(id,Particulars,Date,ChequeNo,BankId,Deposite,Withdraw) values("
							+ bt.getId() + ",'" + bt.getParticulars() + "','" + bt.getDate() + "','" + bt.getChequeNo()
							+ "',"
							+ bt.getBankId() + "," + bt.getDeposite() + "," + bt.getWithdraw() + ")");
		} catch (Exception e) {
			System.out.println("Error in Saving Bank Transaction " + e.getMessage());
			return 0;
		}
	}

	public static int updateBankTransactiob(BankTransaction bt) {
		try {
			rs = CommonMethods.selectQuery("select Id from BankTransaction where Id=" + bt.getId());
			if (rs.next()) {
				return CommonMethods.updateRecord("Update BankTransaction set " + "Particulars='" + bt.getParticulars()
						+ "'," + "Date='" + bt.getDate() + "'," + "ChequeNo='" + bt.getChequeNo() + "'," + "BankId="
						+ bt.getBankId() + "," + "Deposite=" + bt.getDeposite() + "," + "Withdraw=" + bt.getWithdraw()
						+ " where Id=" + bt.getId());
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Error in Updating BankTransaction " + e.getMessage());
			return 0;
		}
	}

	public static int savePurchaseBill(PurchaseBill bill)
	{
		try {
			return CommonMethods
					.addRecord(
					"insert into PurchaseBill " + "(BillNo, PartyId, Amount, Date, GST, OtherTax, ReffNo, Pay, PayId)"
									+ "values(" + bill.getBillNo() + "," + bill.getPartyId() + "," + bill.getAmount()
									+ ",'" + bill.getDate() + "'," + bill.getGST() + "," + bill.getOtherTax() + ",'"
									+ bill.getReffNo()
							+ "','" + bill.getPay() + "'," + bill.getPartyId() + ")");
		} catch (Exception e) {
			System.out.println("Error in save PurchaseBill "+e.getMessage());
			return 0;
		}
	}

	public static int updatePurchaseBill(PurchaseBill bill) {
		try {
			return CommonMethods.updateRecord("update PurchaseBill " + "set PartyId=" + bill.getPartyId() + ","
					+ "Amount=" + bill.getAmount() + "," + "Date='" + bill.getDate() + "'," + "GST=" + bill.getGST()
					+ ","
					+ "OtherTax=" + bill.getOtherTax() + "," + "ReffNo='" + bill.getReffNo() + "'," + "Pay='"
					+ bill.getPay() + "'," + "PayId=" + bill.getPayId() + " " + "where BillNo=" + bill.getBillNo());
		} catch (Exception e) {
			System.out.println("Error in Updating PurchaseBill " + e.getMessage());
			return 0;
		}
	}

	public static int savePurchaseTransaction(PurchaseTransaction p) {
		try {
			return CommonMethods.addRecord("insert into purchasetransaction"
					+ "(ID,ItemName,QTY,Rate,Amount,BillNo) values(" + p.getId() + ",'" + p.getItemName() + "',"
					+ p.getQty() + "," + p.getRate() + "," + p.getAmount() + "," + p.getBillNo() + ")");
		} catch (Exception e) {
			System.out.println("Error in savePurchaseTransaction");
			return 0;
		}
	}

	public static int setPayIdToPurchaseBill(int billno, int id) {
		try {
			return CommonMethods.updateRecord("update PurchaseBill set " + "PayId=" + id + " where BillNo=" + billno);
		} catch (Exception e) {
			System.out.println("Error in Setting PayId to Purchase Bill " + e.getMessage());
			return 0;
		}
	}

	public static int saveEmployeeAttendance(EmployeeAttendance ea) {
		try {
			return CommonMethods.addRecord("insert into employeeattendance" + " (Id, EmpId, Date, Attendance)values("
					+ ea.getId() + "," + ea.getEmployeeId() + ",'" + java.sql.Date.valueOf(ea.getDate()) + "','"
					+ ea.getAttendance()
					+ "')");
		} catch (Exception e) {
			System.out.println("error in Saving Empoyee Attendance " + e.getMessage());
			return 0;
		}
	}

	public static int updateEmployeeAttendance(EmployeeAttendance ea) {
		try {
			return CommonMethods.updateRecord(
					"update EmployeeAttendance " + " set EmpId=" + ea.getEmployeeId() + "," + " Date='"
							+ java.sql.Date.valueOf(ea
									.getDate())
							+ "'," + " Attendance='" + ea.getAttendance() + "' " + "where Id=" + ea.getId());
		} catch (Exception e) {
			System.out.println("Error in updating Employee Attendance " + e.getMessage());
			return 0;
		}
	}

	public static int saveEmployeeSalasry(EmployeeSalary esal) {
		try {
			return CommonMethods.addRecord("insert into Employeesalary"
					+ "(ID, EmpId, SalaryPaid, Month, Year, Date,payId)values(" + esal.getID() + "," + esal.getEmpId()
					+ "," + esal.getSalaryPaid() + "," + esal.getMonth() + "," + esal.getYear()
					+ ",'"
					+ esal.getDate()
					+ "'," + esal.getPayId() + ")");
		} catch (Exception e) {
			System.out.println("errror in Saving Employee salary");
			return 0;
		}
	}

	public static int updateEmployeesalary(EmployeeSalary esal) {
		try {
			return CommonMethods.updateRecord("update Employeesalary set " + " EmpId=" + esal.getID() + ","
					+ " SalaryPaid=" + esal.getSalaryPaid() + "," + " Month=" + esal.getMonth() + "," + " Year="
					+ esal.getYear() + "," + " Date='" + esal.getDate() + "',payId=" + esal.getPayId()
					+ "where Id ="
					+ esal.getID());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static int saveEnglishName(List<Object[]> list) {
		try {
			for (Object[] obj : list) {
				int itemId = CommonLogic.getItemId(obj[1].toString());
				int id = findItemInEnglishName(itemId);
				if (id == 0) {
					CommonMethods.addRecord("insert into itemenglishname " + "(ItemId, EnglishName,EnglishCatId)values("
							+ itemId + ",'" + obj[2].toString() + "'," + Integer.parseInt(obj[3].toString()) + ")");
				} else {
					CommonMethods.updateRecord("update itemenglishname set " + "ItemId=" + itemId + ", EnglishName='"
							+ obj[2].toString() + "', EnglishCatId=" + Integer.parseInt(obj[3].toString())
							+ " where Id="
							+ id);
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			return 0;
		}
	}

	public static int findItemInEnglishName(int itemId) {
		try {
			rs = CommonMethods.selectQuery("select Id from itemenglishname where ItemId=" + itemId);
			if (rs.next()) {
				return rs.getInt(1);
			} else
				return 0;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}

	public static int saveCategoryEnglishName(List<Object[]> list) {
		try {
			for (Object[] obj : list) {
				CommonMethods.updateRecord("update categoryenglishname set " + "EnglishName='" + obj[2]
						.toString()
						+ "' where id=" + Integer.parseInt(obj[0].toString()) + " And CatId="
						+ Integer.parseInt(obj[0].toString()));
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int saveTodaysMenu(Iterator<Object[]> it)
	{
		try {
			while(it.hasNext())
			{
				Object[] obj = it.next();
				CommonMethods.updateRecord("update todaysmenu set status='" + obj[2] + "' where " + "ItemId="
						+ (int) obj[0] + " AND CatId=" + (int) obj[1]);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}

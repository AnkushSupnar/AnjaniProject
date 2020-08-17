package Demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ankush.CommonMethods;
import kush.POJO.AddBean;
import kush.POJO.Bill;
import kush.POJO.Transaction;

public class WriteBill {

	public WriteBill() {
		readBillFile("C:\\Users\\Ankush\\Desktop\\Bk\\Backup\\");
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		new WriteBill();
		// Writebills();
	}

	private int readBillFile(String path) {
		try {
			String file = path + "BillBackUp.txt";
			File f = new File(file);
			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Bill bill;
				int count = 0;
				while ((line = read.readLine()) != null) {
					bill = new Bill();
					String[] b = line.split("\\|");
					// bill.setBillNO(Integer.parseInt(b[0]));
					bill.setBillNO(CommonMethods.getId("select BillNo from Bill order by BillNo"));
					bill.setBillAmt(Float.parseFloat(b[1]));
					bill.setDiscount(Float.parseFloat(b[2]));
					bill.setCUstomerID(Integer.parseInt(b[3]));
					bill.setWaitorid(Integer.parseInt(b[4]));
					bill.setTableno(Integer.parseInt(b[5]));
					bill.setUserid(Integer.parseInt(b[6]));
					bill.setBillDate(b[7]);
					bill.setPaymode(b[8]);
					bill.setStatus(b[9]);
					bill.setTime(b[10]);
					
					
					Iterator<Transaction> i = readTransactionFile(path,Integer.parseInt(b[0])).iterator();
					Transaction t=null;
						if (AddBean.addBill(bill) == 1) {
							System.out.println(bill);
							count++;
						
					while(i.hasNext())
					{
						t = i.next();
						t.setBill(bill.getBillNO());
						System.out.println(t);
						AddBean.addTransaction(t);
					}
						}
					
					// System.out.println(bill);
					//if (AddBean.addBill(bill) == 1) {
						//count++;
					//}
					//System.out.println(bill);
					b = null;
				}
				System.out.println("Count " + count);
				// do something
			} else {
				System.out.println("No Backup found");
			}
			return 1;
		} catch (Exception e) {
			System.out.println("Error in Getting BillFile " + e.getMessage());
			return 0;
		}
	}

	List<Transaction> readTransactionFile(String path, int billno) {
		List<Transaction> list = new ArrayList<>();
		try {
			String file = path + "TransactionBackup.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				String line;
				@SuppressWarnings({ "resource" })
				BufferedReader read = new BufferedReader(new FileReader(f));
				Transaction t;
				//int count = 0;
				
				while ((line = read.readLine()) != null) {
					// id, ItemName, qty, rate, amt, bill
					String[] b = line.split("\\|");
					if (Integer.parseInt(b[5]) == billno) {
						t = new Transaction();
						t.setId(Integer.parseInt(b[0]));
						t.setItemName(b[1]);
						t.setQty(Float.parseFloat(b[2]));
						t.setRate(Float.parseFloat(b[3]));
						t.setAmt(Float.parseFloat(b[4]));
						t.setBill(Integer.parseInt(b[5]));
						list.add(t);
					}

				}
				

			} else {
				System.out.println("No Backup found");
			}
			return list;
	
		} catch (Exception e) {
			System.out.println("Error in read Transaction " + e.getMessage());
			return null;
		}
	}
}
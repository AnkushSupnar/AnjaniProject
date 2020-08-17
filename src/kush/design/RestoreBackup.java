package kush.design;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;

import ankush.CommonMethods;
import ankush.SetData;
import kush.POJO.AddBean;
import kush.POJO.BankDetails;
import kush.POJO.Bill;
import kush.POJO.CashRecieved;
import kush.POJO.CategoryMaster;
import kush.POJO.Customer;
import kush.POJO.DailyCollectionInBank;
import kush.POJO.Employee;
import kush.POJO.Item;
import kush.POJO.ItemStock;
import kush.POJO.Login;
import kush.POJO.Passbook;
import kush.POJO.PurchaseBill;
import kush.POJO.PurchaseParty;
import kush.POJO.PurchaseTransaction;
import kush.POJO.Table;
import kush.POJO.Transaction;

public class RestoreBackup 
{

	public RestoreBackup(String path)
	{
		System.out.println(path);
		//String filepath="C:\\Users\\Ankush\\Desktop\\Backup\\";
		String filepath=path;
		readBillFile(filepath);
		readTransactionFile(filepath);
		
		  readBankDetailsFile(filepath); readCashRecieved(filepath);
		  readCategorymaster(filepath); readCustomerFile(filepath);
		  readDailyCollectionInBank(filepath); readEmployeeFile(filepath);
		  readItemFile(filepath); readItemStoockFile(filepath);
		  readLoginFile(filepath); readPassbookFile(filepath);
		  readPaymentRecivedFile(filepath); readPurchaseBillBackupFile(filepath);
		  readPurchasePartyBackupFile(filepath);
		  readPurchaseTransactionBackup(filepath); readTableBackupBackup(filepath);
		  readTransactionBackup(filepath);
		 
		
	}
	public static void main(String[] args)
	{
		CommonMethods.openConnection();
		new RestoreBackup("C:\\Users\\Ankush\\Desktop\\14 03 backup\\Backup\\");

	}
	@SuppressWarnings("resource")
	private int readBillFile(String path)
	{
		try {
			String file = path+"BillBackUp.txt";
        	
        	File f = new File(file);
        	
        	if(f.exists() && !f.isDirectory()) 
        	{
        		System.out.println("Exist");
        		String line;
        		BufferedReader read = new BufferedReader(new FileReader(f));
        		Bill bill;
        		int count=0;
        		while((line=read.readLine())!=null)
        		{
//BillNO, BillAmt, Discount, CUstomerID, Waitorid, Tableno, userid, BillDate, Paymode, status, Time        			
        			//System.out.println(line);
        			bill = new Bill();
        			String[] b=line.split("\\|");
        			bill.setBillNO(Integer.parseInt(b[0]));
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
        			//System.out.println(bill);
        			if(AddBean.addBill(bill)==1)
        				{
        				count++;
        				}
        			
        			b=null;
        		}
        		System.out.println("Count "+count);
        	    // do something
        	}else
        	{
        		System.out.println("No Backup found");
        	}
			return 1;
		} catch (Exception e) {
			System.out.println("Error in Getting BillFile "+e.getMessage());
			return 0;
		}
	}
	private int readTransactionFile(String path)
	{
		try {
				String file = path+"TransactionBackup.txt";
        	
				File f = new File(file);
        	
				if(f.exists() && !f.isDirectory()) 
				{
					System.out.println("Exist");
					String line;
					@SuppressWarnings({ "resource" })
					BufferedReader read = new BufferedReader(new FileReader(f));
					Transaction t;
					int count=0;
					while((line=read.readLine())!=null)
					{
						//id, ItemName, qty, rate, amt, bill
						String[] b=line.split("\\|");
						t = new Transaction();
						t.setId(Integer.parseInt(b[0]));
						t.setItemName(b[1]);
						t.setQty(Float.parseFloat(b[2]));
						t.setRate(Float.parseFloat(b[3]));
						t.setAmt(Float.parseFloat(b[4]));
						t.setBill(Integer.parseInt(b[5]));
					System.out.println(t);
						
						if(AddBean.addTransaction(t)==1)
						{
						System.out.println(t);
						count++;
						}
						t=null;
					}
					System.out.println("Count to Add ==>"+count);
				}
				else
				{
					System.out.println("No Backup found");
				}
				
				return 1;
		} catch (Exception e) {
			System.out.println("Error in read Transaction "+e.getMessage());
			return 0;
		}
	}
	private int readBankDetailsFile(String path)
	{
		try {
			String file = path+"BankDetailsBackUp.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				BankDetails d;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, BankName, AccountNo, AccountType, IFC, PersonName, BankAddress, BankBalance, BankCode
					String[] b=line.split("\\|");
					d = new BankDetails();
					d.setID(Integer.parseInt(b[0]));
					d.setBankName(b[1]);
					d.setAccountNo(b[2]);
					d.setAccountType(b[3]);
					d.setIFC(b[4]);
					d.setPersonName(b[5]);
					d.setBankAddress(b[6]);
					d.setBankBalance(Double.parseDouble(b[7]));
					d.setBankCode(b[8]);
					if(AddBean.addBankDetails(d)==1)
					{
						count++;
					}					
					d=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		return 0;
	}
	}
	public static int readCashRecieved(String path)
	{
		try {
			String file = path+"CashreceivedBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				CashRecieved c;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//id, CID, Amount, Date, BankId, ChequeNo, Note, BankName
					String[] b=line.split("\\|");
					c = new CashRecieved();
					c.setId(Integer.parseInt(b[0]));
					c.setCID(Integer.parseInt(b[1]));
					c.setAmount(Double.parseDouble(b[2]));
					c.setDate(b[3]);
					c.setBankId(Integer.parseInt(b[4]));
					c.setChequeNo(Integer.parseInt(b[5]));
					if(b[6].length()==0)
					{
						b[6]=" ";
					}
					c.setNote(b[6]);
					c.setBankName(b[7]);
					
					//System.out.println(c);
				if(AddBean.addCashReceived(c)==1)
					{
						count++;
						System.out.println(c);
					}					
					c=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		return 0;
	}

	}
	public static int readCategorymaster(String path)
	{
		try {
			String file = path+"CategorymasterBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				CategoryMaster c;
				int count=0;
				while((line=read.readLine())!=null)
				{
					
					String[] b=line.split("\\|");
					c = new CategoryMaster();
					c.setId(Integer.parseInt(b[0]));
					c.setName(b[1]);
					c.setStock(b[2]);
					
					//System.out.println(c);
				if(AddBean.addCategoryMaster(c)==1)
					{
						count++;
						System.out.println(c);
					}					
					c=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		return 0;
	}

	}
	public int readCustomerFile(String path)
	{
		try {
			String file = path+"CustomerBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Customer c;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, CustomerKey, FName, MName, LName, MobileNo,
					//EmailId, FlatNo, StreetName, City, District, Taluka
					String[] b=line.split("\\|");
					c = new Customer();
					c.setID(Integer.parseInt(b[0]));
					c.setCustomerKey(b[1]);
					c.setFName(b[2]);
					c.setMName(b[3]);
					c.setLName(b[4]);
					c.setMobileNo(b[5]);
					c.setEmailId(b[6]);
					c.setFlatNo(Integer.parseInt(b[7]));
					c.setStreetName(b[8]);
					c.setCity(b[9]);
					c.setDistrict(b[10]);
					c.setTaluka(b[11]);
					
					
					//System.out.println(c);
				if(AddBean.addCustomer(c)==1)
					{
						count++;
						System.out.println(c);
					}					
					c=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
		} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		return 0;
		}
	}
	public int readDailyCollectionInBank(String path)
	{
		try {
			String file = path+"dailycollectioninbankBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				DailyCollectionInBank c;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, BankID, Amount, Date
					String[] b=line.split("\\|");
					c = new DailyCollectionInBank();
					c.setID(Integer.parseInt(b[0]));
					c.setBankID(Integer.parseInt(b[1]));
					c.setAmount(Double.parseDouble(b[2]));
					c.setDate(b[3]);
					
					
					
					//System.out.println(c);
				if(AddBean.addDailyCollectionInBank(c)==1)
					{
						count++;
						System.out.println(c);
					}					
					c=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}

	}
	public int readEmployeeFile(String path)
	{
		try {
			String file = path+"employeeBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Employee e;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//Id, FNAME, MNAME, LNAME, ADDRESS, CONTACT, DESIGNATION, 
					//SALARY, SALARYTYPE, status
					String[] b=line.split("\\|");
					e = new Employee();
					e.setId(Integer.parseInt(b[0]));
					e.setFname(b[1]);
					e.setMname(b[2]);
					e.setLname(b[3]);
					e.setAddress(b[4]);
					e.setCno(b[5]);
					e.setDesignation(b[6]);
					e.setSalary(Float.parseFloat(b[7]));
					e.setSalrytype(b[8]);
					e.setStatus(b[9]);
					//System.out.println(c);
					if(AddBean.addEmployee(e)==1)
					{
						count++;
						System.out.println(e);
					}					
					e=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
		} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
		}
	}
	public int readItemFile(String path)
	{
		try {
			String file = path+"ItemMasterBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Item i;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, ItemName, Catid, Rate, ItemCode
					String[] b=line.split("\\|");
					i = new Item();
					i.setID(Integer.parseInt(b[0]));
					i.setItemName(b[1]);
					i.setCatid(Integer.parseInt(b[2]));
					i.setRate(Float.parseFloat(b[3]));
					i.setItemCode(Integer.parseInt(b[4]));
					
				if(AddBean.addItem(i)==1)
					{
						count++;
						System.out.println(i);
					}					
					i=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}

	}
	public int readItemStoockFile(String path)
	{
		try {
			String file = path+"ItemStockBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				ItemStock i;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//Id, ItemName, Stock
					String[] b=line.split("\\|");
					i = new ItemStock();
					i.setId(Integer.parseInt(b[0]));
					i.setItemName(b[1]);
					i.setStock(Float.parseFloat(b[2]));
				if(AddBean.addItemStock(i)==1)
					{
						count++;
						System.out.println(i);
					}					
					i=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}		
	}
	public int readLoginFile(String path)
	{
		try {
			String file = path+"LoginBackup.txt";
        	
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Login l;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, UserName, Password, EmployeeId
					String[] b=line.split("\\|");
					l = new Login();
					l.setID(Integer.parseInt(b[0]));
					l.setUserName(b[1]);
					l.setPassword(b[2]);
					l.setEmployeeId(Integer.parseInt(b[3]));
				if(AddBean.addLogin(l)==1)
					{
						count++;
						System.out.println(l);
					}					
					l=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}

	}
	public int readPassbookFile(String path)
	{
		try {
			String file = path+"PassbookBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Passbook p;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, CID, Amount, Mode, TId, Date
					String[] b=line.split("\\|");
					p = new Passbook();
					p.setID(Integer.parseInt(b[0]));
					p.setCID(Integer.parseInt(b[1]));
					p.setAmount(Double.parseDouble(b[2]));
					p.setMode(b[3]);
					p.setTId(Integer.parseInt(b[4]));
					p.setDate(b[5]);
					
				if(AddBean.addPassbook(p)==1)
					{
						count++;
						System.out.println(p);
					}					
					p=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}
	public int readPaymentRecivedFile(String path)
	{
		try {
			String file = path+"paymentrecivedBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				CashRecieved c;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, CID, Amount, Date, BankId, ChequeNo, Note
					String[] b=line.split("\\|");
					c = new CashRecieved();
					c.setId(Integer.parseInt(b[0]));
					c.setCID(Integer.parseInt(b[1]));
					c.setAmount(Double.parseDouble(b[2]));
					c.setDate(b[3]);
					c.setBankId(Integer.parseInt(b[4]));
					c.setChequeNo(Integer.parseInt(b[5]));
					c.setNote(b[6]);
					
					
				if(AddBean.addCashReceived(c)==1)
					{
						count++;
						System.out.println(c);
					}					
					c=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}
	public int readPurchaseBillBackupFile(String path)
	{
		try {
			String file = path+"PurchaseBillBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				PurchaseBill p;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//BillNo, PartyId, Amount, Date, BankId,
					//ChequeNo, GST, OtherTax, ReffNo
					String[] b=line.split("\\|");
					p = new PurchaseBill();
					p.setBillNo(Integer.parseInt(b[0]));
					p.setPartyId(Integer.parseInt(b[1]));
					p.setAmount(Double.parseDouble(b[2]));
					p.setDate(java.sql.Date.valueOf(LocalDate.parse(b[3])));

					p.setGST(Double.parseDouble(b[6]));
					p.setOtherTax(Double.parseDouble(b[7]));
					p.setReffNo(b[8]);
					
					
					
					if (SetData.savePurchaseBill(p) == 1)
					{
						count++;
						System.out.println(p);
					}					
					p=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}
	
	public int readPurchasePartyBackupFile(String path)
	{
		try {
			String file = path+"PurchasePartyBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				PurchaseParty p;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//Id, Name, Address, Contact
					String[] b=line.split("\\|");
					p = new PurchaseParty();
					p.setId(Integer.parseInt(b[0]));
					p.setName(b[1]);
					p.setAddress(b[2]);
					p.setContact(b[3]);
					
					
					
				if(AddBean.addPurchaseparty(p)==1)
					{
						count++;
						System.out.println(p);
					}					
					p=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}
	public int readPurchaseTransactionBackup(String path)
	{
		try {
			String file = path+"PurchaseTransactionBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				PurchaseTransaction p;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//ID, ItemName, QTY, Rate, Amount, BillNo
					String[] b=line.split("\\|");
					p = new PurchaseTransaction();
					p.setId(Integer.parseInt(b[0]));
					p.setItemName(b[1]);
					p.setQty(Integer.parseInt(b[2]));
					p.setRate(Float.parseFloat(b[3]));
					p.setAmount(Float.parseFloat(b[4]));
					p.setBillNo(Integer.parseInt(b[5]));
					
					
					
				if(AddBean.addPurchaseTransaction(p)==1)
					{
						count++;
						System.out.println(p);
					}					
					p=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}

	public int readTableBackupBackup(String path)
	{
		try {
			String file = path+"TableBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Table p;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//id, TableName, DESCRIPTION
					String[] b=line.split("\\|");
					p = new Table();
					p.setId(Integer.parseInt(b[0]));
					p.setTableName(b[1]);
					p.setDESCRIPTION(b[2]);
				if(AddBean.addTable(p)==1)
					{
						count++;
						System.out.println(p);
					}					
					p=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}
	public int readTransactionBackup(String path)
	{
		try {
			String file = path+"TransactionBackup.txt";
        	System.out.println("Read data from "+file);
			File f = new File(file);
    	
			if(f.exists() && !f.isDirectory()) 
			{
				System.out.println("Exist");
				String line;
				@SuppressWarnings("resource")
				BufferedReader read = new BufferedReader(new FileReader(f));
				Transaction p;
				int count=0;
				while((line=read.readLine())!=null)
				{
					//id, ItemName, qty, rate, amt, bill
					String[] b=line.split("\\|");
					p = new Transaction();
					p.setId(Integer.parseInt(b[0]));
					p.setItemName(b[1]);
					p.setQty(Float.parseFloat(b[2]));
					p.setRate(Float.parseFloat(b[3]));
					p.setAmt(Float.parseFloat(b[4]));
					p.setBill(Integer.parseInt(b[5]));
				if(AddBean.addTransaction(p)==1)
					{
						count++;
						System.out.println(p);
					}					
					p=null;
				}
				System.out.println("Count to Add ==>"+count);
			}
			else
			{
				System.out.println("No Backup found");
			}
			return 1;
	} catch (Exception e) {
		System.out.println("Error in read Transaction "+e.getMessage());
		e.printStackTrace();
		return 0;
	}
	}
}

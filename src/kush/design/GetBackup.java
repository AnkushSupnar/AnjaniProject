package kush.design;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;

import ankush.CommonMethods;

public class GetBackup 
{
	public GetBackup()
	{
		//
		String filepath="D:\\LocalBackup\\";
		writeBillTable(filepath);
		writebankDetails(filepath);
		cashReceivedBackup(filepath);
		categoryMasterBackup(filepath);
		customerBackup(filepath);
		dailycollectioninbankBackup(filepath);
		employeeBackup(filepath);
		itemBackup(filepath);
		loginBackup(filepath);
		passbookBackup(filepath);
		paymentrecivedBackup(filepath);
		tableBackup(filepath);
		transactionBackup(filepath);
		ItemStockBackup(filepath);
		employeeSalaryBackup(filepath);
		purchaseBillBackUp(filepath);
		purchasePartyBackup(filepath);
		purchaseTransactionBackup(filepath);
	}

	public static void main(String[] args)
	{
		CommonMethods.openConnection();
		System.out.println("getting backup");
		new GetBackup();
		// TODO Auto-generated method stub

	}
	public int writebankDetails(String path)
	{
		int flag=0;
		try 
        {
        	
        	String file = path+"BankDetailsBackUp.txt";
        	
        	File f = new File(file);
        	
        	if(f.exists() && !f.isDirectory()) 
        	{
        		System.out.println("Exist");
        	    // do something
        	}
        	else
        	{
        		 System.out.println("Not found. Creating new File");
        		 f.getParentFile().mkdirs();
        		 f.createNewFile();
        	}
        	
        	//Writing Data in File
        	 
        	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        	String query = "SELECT ID, BankName, AccountNo,"+
        			"AccountType,IFC,PersonName,BankAddress,"+
        			"BankBalance,BankCode FROM bankdetails";
        	ResultSet rs = CommonMethods.selectQuery(query);
        	while(rs.next())
        	{
        		
        		    writer.write(""+rs.getInt(1));//ID
        		    writer.write("|"+rs.getString(2));//BankName
        		    writer.write("|"+rs.getString(3));//AccountNo
        		    writer.write("|"+rs.getString(4));//Account Type
        		    writer.write("|"+rs.getString(5));//IFSC
        		    writer.write("|"+rs.getString(6));//PersonName
        		    writer.write("|"+rs.getString(7));//BankAddress
        		    writer.write("|"+rs.getFloat(8));//Bank Balance
        		    writer.write("|"+rs.getString(9));//bank Code
        		   // writer.write("|"+rs.getString(10));//status
        		   // writer.write("|"+rs.getDate(11));//Time
        		    
        		    writer.newLine();
        	}

        	rs.close();
        	writer.newLine();
        	writer.close();
        	flag=1;;
        } catch (Exception e) 
        {
        	System.out.println("Error "+e.getMessage());
            e.printStackTrace();
        }
		return flag;


	}
	public int writeBillTable(String filepath)
	{
		int flag=0;
		 try 
	        {
	        	
	        	String file=filepath+"BillBackUp.txt";
	        	File f = new File(file);
	        	if(f.exists() && !f.isDirectory()) 
	        	{
	        		System.out.println("Exist");
	        	    // do something
	        	}
	        	else
	        	{
	        		 System.out.println("Not found. Creating new File");
	        		 f.getParentFile().mkdirs();
	        		 f.createNewFile();
	        	}
	        	
	        	//Writing Data in File
	        	 
	        	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	        	String query = "select billno,billAmt,Discount,"
	        			+"CustomerId,Waitorid,Tableno,Userid,"
	        			+"BillDate,Paymode,status,Time from Bill";
	        	ResultSet rs = CommonMethods.selectQuery(query);
	        	while(rs.next())
	        	{
	        		
	        		    writer.write(""+rs.getInt(1));//billno
	        		    writer.write("|"+rs.getFloat(2));//billamt
	        		    writer.write("|"+rs.getFloat(3));//discount
	        		    writer.write("|"+rs.getInt(4));//customer id
	        		    writer.write("|"+rs.getInt(5));//WaitorId
	        		    writer.write("|"+rs.getInt(6));//Table no
	        		    writer.write("|"+rs.getInt(7));//UserId
	        		    writer.write("|"+rs.getDate(8));//BillDate
	        		    writer.write("|"+rs.getString(9));//Paymode
	        		    writer.write("|"+rs.getString(10));//status
	        		    writer.write("|"+rs.getDate(11));//Time
	        		    
	        		    writer.newLine();
	        	}

	        	rs.close();
	        	writer.newLine();
	        	writer.close();
	        	flag=1;
	        } catch (Exception e) 
	        {
	        	System.out.println("Error "+e.getMessage());
	            e.printStackTrace();
	        }

		 return flag;
	}
	public int cashReceivedBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"CashreceivedBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID,CID,Amount,Date,BankID,ChequeNo,"+
    	 "Note,BankName FROM cashrecieved";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getInt(2));//CID
    		    writer.write("|"+rs.getFloat(3));//Amount
    		    writer.write("|"+rs.getDate(4));//Date
    		    writer.write("|"+rs.getInt(5));//BankId
    		    writer.write("|"+rs.getInt(6));//Cheque No
    		    writer.write("|"+rs.getString(7));//Note
    		    writer.write("|"+rs.getString(8));//BankName
    		    
    		    
    		    writer.newLine();
    	}

    	rs.close();
    	writer.newLine();
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }

	return flag;

}
	public int categoryMasterBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"CategorymasterBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT Id,Category,Stock FROM categorymaster";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//Category
    		    writer.write("|"+rs.getString(3));//Stock
    		    
    		    
    		    writer.newLine();
    	}

    	rs.close();
    	writer.newLine();
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int customerBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"CustomerBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT id,CustomerKey,FName,MName,LName,MobileNo,"+
    	 "EmailId,FlatNo,StreetName,City,District, Taluka FROM customer";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//Category
    		    writer.write("|"+rs.getString(3));//Fname
    		    writer.write("|"+rs.getString(4));//MName
    		    writer.write("|"+rs.getString(5));//LName
    		    writer.write("|"+rs.getString(6));//MobileNo
    		    writer.write("|"+rs.getString(7));//Email Id
    		    writer.write("|"+rs.getInt(8));//Flat No
    		    writer.write("|"+rs.getString(9));//StreetName
    		    writer.write("|"+rs.getString(10));//City
    		    writer.write("|"+rs.getString(11));//District
    		    writer.write("|"+rs.getString(12));//Taluka
    		    
    		    writer.newLine();
    	}

    	rs.close();
    	writer.newLine();
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
	
}
	public int dailycollectioninbankBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"dailycollectioninbankBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT Id,BankId,Amount,Date FROM dailycollectioninbank";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getInt(2));//BankId
    		    writer.write("|"+rs.getFloat(3));//Amount
    		    writer.write("|"+rs.getDate(4));//Date
    		    
    		    writer.newLine();
    	}

    	rs.close();
    	writer.newLine();
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int employeeBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"employeeBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT id,FName,MName,LName,Address,Contact,"+
    	 "Designation,Salary,SalaryType,Status FROM employee";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//FName
    		    writer.write("|"+rs.getString(3));//MName
    		    writer.write("|"+rs.getString(4));//LName
    		    writer.write("|"+rs.getString(5));//Address
    		    writer.write("|"+rs.getString(6));//Contact
    		    writer.write("|"+rs.getString(7));//Designation
    		    writer.write("|"+rs.getFloat(8));//Salary
    		    writer.write("|"+rs.getString(9));//SalaryType
    		    writer.write("|"+rs.getString(10));//Status
    		    
    		    writer.newLine();
    	}

    	rs.close();
    	writer.newLine();
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int itemBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"ItemMasterBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID,ItemName,Catid,Rate,ItemCode FROM itemmaster";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//ItemName
    		    writer.write("|"+rs.getInt(3));//CatId
    		    writer.write("|"+rs.getFloat(4));//Rate
    		    writer.write("|"+rs.getInt(5));//ItemCode
    		    
    		    writer.newLine();
    	}

    	rs.close();
    
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int loginBackup(String filepath)
	{
		//int flag=0;
	try 
    {
    	
    	String file = filepath+"LoginBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID,UserName,Password,EmployeeId FROM login";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//UserName
    		    writer.write("|"+rs.getString(3));//Password
    		    writer.write("|"+rs.getInt(4));//EmployeeId
    		 
    		    
    		    writer.newLine();
    	}

    	rs.close();
    
    	writer.close();
    	//flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return 0;
}
	public int passbookBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"PassbookBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID,CID,Amount,Mode,TId,Date FROM passbook ";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getInt(2));//CID
    		    writer.write("|"+rs.getFloat(3));//Amount
    		    writer.write("|"+rs.getString(4));//Mode
    		    writer.write("|"+rs.getInt(5));//TId
    		    writer.write("|"+rs.getDate(6));//date
    		 
    		    
    		    writer.newLine();
    	}

    	rs.close();
    
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int paymentrecivedBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"paymentrecivedBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID, CID,Amount,Date,BankId,ChequeNo,Note,BankName FROM cashrecieved";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getInt(2));//CID
    		    writer.write("|"+rs.getFloat(3));//Amount
    		    writer.write("|"+rs.getDate(4));//Date
    		    writer.write("|"+rs.getInt(5));//BankId
    		    writer.write("|"+rs.getInt(6));//Cheque No
    		    writer.write("|"+rs.getString(7));//Note
    		    writer.write("|"+rs.getString(8));//BankName
    		    writer.newLine();
    	}

    	rs.close();
    
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int tableBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"TableBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID,TableName,Description FROM tablemaster";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//Table Name
    		    writer.write("|"+rs.getString(3));//Description
    		 
    		    
    		    writer.newLine();
    	}

    	rs.close();
    
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int transactionBackup(String filepath)
	{
		int flag=0;
	try 
    {
    	
    	String file = filepath+"TransactionBackup.txt";
    	File f = new File(file);
    	if(f.exists() && !f.isDirectory()) 
    	{
    		System.out.println("Exist");
    	    // do something
    	}
    	else
    	{
    		 System.out.println("Not found. Creating new File");
    		 f.getParentFile().mkdirs();
    		 f.createNewFile();
    	}
    	
    	//Writing Data in File
    	 
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
    	String query = "SELECT ID,ItemName,qty,rate,amt,bill FROM transaction";
    	ResultSet rs = CommonMethods.selectQuery(query);
    	while(rs.next())
    	{
    		
    		    writer.write(""+rs.getInt(1));//ID
    		    writer.write("|"+rs.getString(2));//ItemName
    		    writer.write("|"+rs.getFloat(3));//qty
    		    writer.write("|"+rs.getFloat(4));//rate
    		    writer.write("|"+rs.getFloat(5));//amt
    		    writer.write("|"+rs.getInt(6));//bill no
    		    writer.newLine();
    	}

    	rs.close();
    
    	writer.close();
    	flag=1;
    } catch (Exception e) 
    {
    	System.out.println("Error "+e.getMessage());
        e.printStackTrace();
        return 0;
    }
	return flag;
}
	public int ItemStockBackup(String filepath)
	{
		try {
			String file = filepath+"ItemStockBackup.txt";
	    	File f = new File(file);
	    	if(f.exists() && !f.isDirectory()) 
	    	{
	    		System.out.println("Exist");
	    	    // do something
	    	}
	    	else
	    	{
	    		 System.out.println("Not found. Creating new File");
	    		 f.getParentFile().mkdirs();
	    		 f.createNewFile();
	    	}
	    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	    	String query = "select Id, ItemName, Stock from ItemStock ";
	    	ResultSet rs = CommonMethods.selectQuery(query);
	    	while(rs.next())
	    	{
	    		writer.write(""+rs.getInt(1));
	    		writer.write("|"+rs.getString(2));
	    		writer.write("|"+rs.getFloat(3));
	    		writer.newLine();
	    	}
	    	rs.close();
	    	writer.close();
	    	return 1;
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
			return 0;
		}
	}
	public int employeeSalaryBackup(String filepath)
	{
		try {
			String file = filepath+"EmployeeSalaryBackup.txt";
	    	File f = new File(file);
	    	if(f.exists() && !f.isDirectory()) 
	    	{
	    		System.out.println("Exist");
	    	    // do something
	    	}
	    	else
	    	{
	    		 System.out.println("Not found. Creating new File");
	    		 f.getParentFile().mkdirs();
	    		 f.createNewFile();
	    	}
	    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	    	 String query = "Select ID,EmpId,SalaryPaid,Month,Year,Date from EmployeeSalary";
	    	 ResultSet rs  = CommonMethods.selectQuery(query);
	    	 while(rs.next())
	    	 {
	    		 writer.write(""+rs.getInt(1));
	    		 writer.write("|"+rs.getInt(2));
	    		 writer.write("|"+rs.getFloat(3));
	    		 writer.write(""+rs.getInt(4));
	    		 writer.write(""+rs.getInt(5));
	    		 writer.write(""+rs.getDate(6));
	    		 
	    		 writer.newLine();
	    	 }
	    	 rs.close();
	    	 writer.close();
	    	return 1;
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
			return 0;
		}
	}
	public int purchaseBillBackUp(String filepath)
	{
		try {
			String file = filepath+"PurchaseBillBackup.txt";
	    	File f = new File(file);
	    	if(f.exists() && !f.isDirectory()) 
	    	{
	    		System.out.println("Exist");
	    	    // do something
	    	}
	    	else
	    	{
	    		 System.out.println("Not found. Creating new File");
	    		 f.getParentFile().mkdirs();
	    		 f.createNewFile();
	    	}
	    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	    	 String query="select "
	    	 		+ "BillNo, PartyId, Amount, Date, BankId, ChequeNo, GST, OtherTax, ReffNo "
	    	 		+ "from purchasebill";
	    	 ResultSet rs = CommonMethods.selectQuery(query);
	    	 while(rs.next())
	    	 {
	    		 writer.write(""+rs.getInt(1));
	    		 writer.write("|"+rs.getInt(2));
	    		 writer.write("|"+rs.getFloat(3));
	    		 writer.write("|"+rs.getDate(4));
	    		 writer.write("|"+rs.getInt(5));
	    		 writer.write("|"+rs.getString(6));
	    		 writer.write("|"+rs.getFloat(7));
	    		 writer.write("|"+rs.getFloat(8));
	    		 writer.write("|"+rs.getString(9));
	    		 writer.newLine();
	    		 
	    	 }
	    	 rs.close();
	    	 writer.close();
	    	 return 1;
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
			return 0;
		}
	}
	public int purchasePartyBackup(String filepath)
	{
		try {
			String file = filepath+"PurchasePartyBackup.txt";
	    	File f = new File(file);
	    	if(f.exists() && !f.isDirectory()) 
	    	{
	    		System.out.println("Exist");
	    	    // do something
	    	}
	    	else
	    	{
	    		 System.out.println("Not found. Creating new File");
	    		 f.getParentFile().mkdirs();
	    		 f.createNewFile();
	    	}
	    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	    	 String query="select Id, Name, Address, Contact from purchaseparty";
	    	 ResultSet rs = CommonMethods.selectQuery(query);
	    	 while(rs.next())
	    	 {
	    		 writer.write(""+rs.getInt(1));
	    		 writer.write("|"+rs.getString(2));
	    		 writer.write("|"+rs.getString(3));
	    		 writer.write("|"+rs.getString(4));
	    		 writer.newLine();
	    	 }
	    	 rs.close();
	    	 writer.close();
	    	 return 1;
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
			return 0;
		}
	}
	public int purchaseTransactionBackup(String filepath)
	{
		try {
			String file = filepath+"PurchaseTransactionBackup.txt";
	    	File f = new File(file);
	    	if(f.exists() && !f.isDirectory()) 
	    	{
	    		System.out.println("Exist");
	    	    // do something
	    	}
	    	else
	    	{
	    		 System.out.println("Not found. Creating new File");
	    		 f.getParentFile().mkdirs();
	    		 f.createNewFile();
	    	}
	    	 BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	    	 String query="select ID, ItemName, QTY, Rate, Amount, BillNo from purchasetransaction";
	    	 ResultSet rs = CommonMethods.selectQuery(query);
	    	 while(rs.next())
	    	 {
	    		 writer.write(""+rs.getInt(1));
	    		 writer.write("|"+rs.getString(2));
	    		 writer.write("|"+rs.getInt(3));
	    		 writer.write("|"+rs.getFloat(4));
	    		 writer.write("|"+rs.getFloat(5));
	    		 writer.write("|"+rs.getInt(6));
	    		 writer.newLine();
	    	 }
	    	 rs.close();
	    	 writer.close();
	    	 return 1;
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
			return 0;
		}
	}
}
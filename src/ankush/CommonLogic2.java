package ankush;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CommonLogic2
{
	public static Font font = new Font("Kiran", Font.PLAIN, 20);
	//public static Font font = new Font("Times New Roman", Font.BOLD, 16);
	public static Font btnfont = new Font("Kiran", Font.BOLD, 25);
	public static Font lblfont = new Font("Kiran", Font.PLAIN, 25);
	public static Font txtfont = new Font("kiran", Font.PLAIN, 25);
	
	public static String getFirstname(String name)
	{
		String first="";
		int i=0;
		do
		{
			first=first+name.charAt(i);
			i++;
		}while(name.charAt(i)!='-');
		
		return first;
	}
	public static String getMiddleName(String name)
	{
		String middle="";
		int i=0,flag=0;
		for(i=0;i<name.length();i++)
		{
		
			if(name.charAt(i)=='-')
			{
				flag=flag+1;
			}
			if(flag==1)
			{
				if(name.charAt(i)!='-')
				middle=middle+name.charAt(i);
			}
		}
		return middle;
		
	}
	public static String getLastName(String name)
	{
		String last="";
		int i=0,flag=0;
		for(i=0;i<name.length();i++)
		{
			
			if(name.charAt(i)=='-')
			{
				flag=flag+1;
			}
			if(flag==2)
			{
				if(name.charAt(i)!='-')
				last=last+name.charAt(i);
			}
		}
		return last;
	}
	public static int getUsingKey(String key)
    {
    	int id=0;
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("select id from Customer where key='"+key+"'");
    		while(rs.next())
    		{
    			id=rs.getInt(1);
    		}
    		rs.close();
    	}catch(Exception e)
    	{
    		System.out.println("Error in getUsingKey methos "+e);
    		return 0;
    	}
    	
    	return id;
    	
    }
	public static String getFullName(String name)
	{
		String fname="";
		for(int i=0;i<name.length();i++)
		{
			if(name.charAt(i)=='-')
			{
				fname=fname+' ';
			}
			else
			{
				fname=fname+name.charAt(i);
			}
			
		}
		
		return fname;
	}
	public static String getNumberDate()
	{
		 java.util.Date d=new java.util.Date();
		 System.out.println("Original Date "+d);
         @SuppressWarnings("deprecation")
		int day=d.getDate();
	     @SuppressWarnings("deprecation")
		int mnt=d.getMonth()+1;
	     if(mnt==1)
	     {
	    	 mnt=01;
	     }
	     if(mnt==6)
	     {
	    	 mnt=06;
	     }
	     
	    
	     @SuppressWarnings("deprecation")
		int year=(d.getYear()-100);
	    return day+"-"+mnt+"-"+year;
	}
	public static String getDate()
	{
		 java.util.Date d=new java.util.Date();
		// System.out.println("Original Date "+d);
         @SuppressWarnings("deprecation")
		int day=d.getDate();
	     @SuppressWarnings("deprecation")
		int mnt=d.getMonth()+1;
	    
	     @SuppressWarnings("deprecation")
		int year=(d.getYear()-100);
	     year = year+2000;
	     //System.out.println("only Date"+day+"/"+mnt+"/"+year);	
	     String month="";
	     if(mnt==1)
	     {
	    	 month="JAN";
	    	
	     }
	     if(mnt==2)
	     {
	    	 month="FEB";
	    	
	     }
	     if(mnt==3)
	     {
	    	 month="MAR";
	    	
	     }
	     if(mnt==4)
	     {
	    	 month="APl";
	    	
	     }
	     if(mnt==5)
	     {
	    	 month="MAY";
	    	
	     }
	     if(mnt==6)
	     {
	    	 month="JUN";
	    	
	     }
	     if(mnt==7)
	     {
	    	 month="JUL";
	    	
	     }
	     if(mnt==8)
	     {
	    	 month="AUG";
	    	
	     }
	     if(mnt==9)
	     {
	    	 month="SEP";
	    	
	     }if(mnt==10)
	     {
	    	 month="OCT";
	    	
	     }
	     if(mnt==11)
	     {
	    	 month="NOV";
	    	
	     }
	     if(mnt==12)
	     {
	    	 month="DEC";
	    	
	     }
	     
	     
	     
	     String date=day+"-"+month+"-"+year;
	return date;
	    // return day+"/"+mnt+"/"+year;
	}
	@SuppressWarnings("deprecation")
	public static String getDateTime()
	{
		 java.util.Date d=new java.util.Date();
		// System.out.println("Original Date "+d);
         int day=d.getDate();
	     int mnt=d.getMonth()+1;
	    
	     int year=(d.getYear()-100);
	     year = year+2000;
	     //System.out.println("only Date"+day+"/"+mnt+"/"+year);	
	     String month="";
	     @SuppressWarnings("unused")
		int hours = d.getHours();
	     @SuppressWarnings("unused")
		int min = d.getMinutes();
	     if(mnt==1)
	     {
	    	 month="JAN";
	    	
	     }
	     if(mnt==2)
	     {
	    	 month="FEB";
	    	
	     }
	     if(mnt==3)
	     {
	    	 month="MAR";
	    	
	     }
	     if(mnt==4)
	     {
	    	 month="APl";
	    	
	     }
	     if(mnt==5)
	     {
	    	 month="MAY";
	    	
	     }
	     if(mnt==6)
	     {
	    	 month="JUN";
	    	
	     }
	     if(mnt==7)
	     {
	    	 month="JUL";
	    	
	     }
	     if(mnt==8)
	     {
	    	 month="AUG";
	    	
	     }
	     if(mnt==9)
	     {
	    	 month="SEP";
	    	
	     }if(mnt==10)
	     {
	    	 month="OCT";
	    	
	     }
	     if(mnt==11)
	     {
	    	 month="NOV";
	    	
	     }
	     if(mnt==12)
	     {
	    	 month="DEC";
	    	
	     }
	     
	     
	     
	     String date=day+"-"+month+"-"+year;
	return date;
	    // return day+"/"+mnt+"/"+year;
	}
	public static String getDate(int d,int m,int y)
	{
		 //java.util.Date d=new java.util.Date();
		 //System.out.println("Original Date "+d);
         int day=d;
	     int mnt = m;
	    
	     int year = y;
	     year = year+2000;
	     System.out.println("only Date"+day+"/"+mnt+"/"+year);	
	     String month="";
	     if(mnt==1)
	     {
	    	 month="JAN";
	    	
	     }
	     if(mnt==2)
	     {
	    	 month="FEB";
	    	
	     }
	     if(mnt==3)
	     {
	    	 month="MAR";
	    	
	     }
	     if(mnt==4)
	     {
	    	 month="APl";
	    	
	     }
	     if(mnt==5)
	     {
	    	 month="MAY";
	    	
	     }
	     if(mnt==6)
	     {
	    	 month="JUN";
	    	
	     }
	     if(mnt==7)
	     {
	    	 month="JUL";
	    	
	     }
	     if(mnt==8)
	     {
	    	 month="AUG";
	    	
	     }
	     if(mnt==9)
	     {
	    	 month="SEP";
	    	
	     }if(mnt==10)
	     {
	    	 month="OCT";
	    	
	     }
	     if(mnt==11)
	     {
	    	 month="NOV";
	    	
	     }
	     if(mnt==12)
	     {
	    	 month="DEC";
	    	
	     }
	     
	     
	     
	     String date=day+"-"+month+"-"+year;
	return date;
	    // return day+"/"+mnt+"/"+year;
	}
	
	
	
	public static int getIdUsingName(String Name)
	{
		int id=0;
		if(Name.equals(""))
		{
			return 0;
		}
		ResultSet rs = CommonMethods.selectQuery("select id from Customer where name='"+Name+"'");
		try {
			rs.next();
			id = rs.getInt(1);
			return id;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	public static int idValidaton(String id)
	{
		for(int i=0;i<id.length();i++)
	      {
	          if((id.charAt(i))>='0'&&(id.charAt(i)<='9'))
	           {
	        	
	        	}
	        	else
	        	{
	        	  
	    	      return 0;
	            }
      
	      } 
			return 1;
	}
	public static int qtyValidation(String qty)
	{
		for(int i=0;i<qty.length();i++)
	      {
	          if((qty.charAt(i))>='0'&&(qty.charAt(i)<='9'))
	           {
	        	
	        	}
	        	else
	        	{
	        	  
	    	      return 0;
	            }
    
	      } 
			return 1;
	}
	public static int rateValidation(String rate)
	{
		for(int i=0;i<rate.length();i++)
	      {
	          if((rate.charAt(i))>='0'&&(rate.charAt(i)<='9'))
	           {
	        	
	        	}
	        	else
	        	{
	        	  
	    	      return 0;
	            }
    
	      } 
			return 1;
	}
	public static int getRate(String Name)
	{
		try 
		{
			if(Name.equals(""))
			{
				//System.out.println("Empty Name");
				return 0;
			}
			
			ResultSet rs = CommonMethods.selectQuery("select rate from Item where name = '"+Name+"'");
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) 
		{
			//System.out.println("Error in gettting Item rate "+e);
			return 0;
			// TODO: handle exception
		}
		
	}

	public static String getRoomNo(String add)
	{
		String room="";
		int i=0;
		do
		{
			room=room+add.charAt(i);
			i++;
		}while(add.charAt(i)!='-');
		
		return room;

	}
	public static String getAddress(String add,int pos)
	{
		String street="";
		int i=0,flag=1;
		for(i=0;i<add.length();i++)
		{
		
			if(add.charAt(i)=='-')
			{
				flag=flag+1;
			}
			if(flag==pos)
			{
				if(add.charAt(i)!='-')
				street=street+add.charAt(i);
			}
		}
		return street;

	}
	public static String getName(String name,int pos)
	{
		//System.out.println("to split "+name);
		String gname="";
		int i=0,flag=1;
		for(i=0;i<name.length();i++)
		{
		
			if(name.charAt(i)==' ')
			{
				flag=flag+1;
			}
			if(flag==pos)
			{
				//if(name.charAt(i)!='-')
				gname=gname+name.charAt(i);
			}
		}
		return gname;


	}
	public static int getItemId(String name)
	{
		
		try 
		{
			ResultSet rs = CommonMethods.selectQuery("select ID from Item where name='"+name+"'");
			rs.next();
			return rs.getInt(1); 
			
		} catch (Exception e) 
		{
			// TODO: handle exception
		}
		return 0;
	}
	public static int getCustomerPurchaseAmount(int id)
	{
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select sum(grandtotal) from Bill where "
					+ "customerid="+id+" and paymode='Credit'");
			rs.next();
			return rs.getInt(1);
		}catch(Exception e)
		{
			System.out.println("Error in Customer Purchase Amount :"+e);
			return 0;
		}
	
	}
	public static int getCashRecieved(int id)
	{
		try {
			ResultSet rs = CommonMethods.selectQuery("select sum(amount) from CASHRECIEVED where Customerid="+id);
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("Error in Get Cash Received :"+e);
			return 0;
			// TODO: handle exception
		}
		
	}
	public static String getFullCustomerName(int id)//get customer name using customer id
	{
		try {
			ResultSet rs = CommonMethods.selectQuery("select name from customer where id="+id);
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			System.out.println("Error in getFullCustomerName() "+e.getMessage());
			return "";
			// TODO: handle exception
		}
		
		
	}
	public static String getFullCustomerAddress(int id)//get customeraddress using his id
	{
		try {
			ResultSet rs = CommonMethods.selectQuery("select Localaddress from customer where id="+id);
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			System.out.println("Error in getFullCustomerAddress() "+e.getMessage());
			return "";
			// TODO: handle exception
		}
		
		
	}
	public static String getCustomerContact(int id)//get customer contact using his id
	{
		try {
			ResultSet rs = CommonMethods.selectQuery("select MOBILENO from customer where id="+id);
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			System.out.println("Error in getCustomerContact() "+e.getMessage());
			return "";
			// TODO: handle exception
		}
		
		
	}
	public static int getCustomerIdBill(int id)//get customer contact using his id
	{
		try {
			ResultSet rs = CommonMethods.selectQuery("select CUSTOMERID from bill where BILLNO="+id);
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			System.out.println("Error in getCustomerIdBill() "+e.getMessage());
			e.printStackTrace();
			return 0;
			// TODO: handle exception
		}
		
		
	}
	
	public static int getGrandTotal(int id)//get customer contact using his id
	{
		try {
			ResultSet rs = CommonMethods.selectQuery("select GRANDTOTAL from bill where BILLNO="+id);
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			System.out.println("Error in getGrandTotal() "+e.getMessage());
			return 0;
			// TODO: handle exception
		}		
	}
	public static String getBillDate(int id)//get customer contact using his id
	{
		try {
			
			ResultSet rs = CommonMethods.selectQuery("select TO_CHAR(billdate,'dd-MM-yy') from bill where BILLNO="+id);
			rs.next();
		return rs.getString(1);
			//Date d = rs.getString(1);
		} catch (Exception e) {
			System.out.println("Error in getGrandTotal() "+e.getMessage());
			return "";
			// TODO: handle exception
		}		
	}
	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		//System.out.println(" "+getNumberDate());
	//	System.out.println("Total credit = "+getCustomerPurchaseAmount(1));
		//System.out.println("Total Cash Received = "+getCashRecieved(1));
		//System.out.println("From GetRoom :"+getRoomNo("10-Rahuri Road-Amalner-Nawasa-Ahmednagar"));
		//System.out.println("From 1 :"+getName("Ankush sawaleram Supnar",1));
		//System.out.println("From 2 :"+getName("Ankush sawaleram Supnar",2));
		//System.out.println("From 3 :"+getName("Ankush sawaleram Supnar",3));
		//System.out.println("From 4 :"+getName("Ankush sawaleram Supnar",1));
		//System.out.println("From 5 :"+getName("Ankush sawaleram Supnar",1));
		
		//System.out.println(getFirstname("Ankush-sawaleram-Supnar"));
		//System.out.println(getMiddleName("Ankush-sawaleram-Supnar"));
		//System.out.println(getLastName("Ankush-sawaleram-Supnar"));
		//System.out.println(getFullName("Ankush-sawaleram-Supnar"));
		// TODO Auto-generated method stub
		System.out.println("Bill No:-"+CommonMethods.getId("select BillNo from Bill"));

	}

//Only for Hotel
	public static List<String> getCategory()
	{
		List<String> list = new ArrayList<String>();
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select Category from CategoryMaster order by Category");
		while(rs.next())
		{
			list.add(rs.getString(1));
		}
		rs.close();
		}catch(Exception e)
		{
			System.out.println("Error in geting Category "+e);
			return null;
		}
		return list;
	}
	public static List<String>getItemName(String CatName)
	{
		//System.out.println("i get Category "+CatName);
		List<String> item = new ArrayList<>();
		try
		{
String query ="select  Itemmaster.ItemName from ItemMaster,CategoryMaster where Itemmaster.CatId=CategoryMaster.Id and CategoryMaster.Category='"+CatName+"' order by ItemMaster.ItemName"; 			
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next())
			{
				item.add(rs.getString(1));
				
			}
			rs.close();
		}catch(Exception e)
		{
			System.out.println("Error in Geting Items"+e.getMessage());
			return null;
		}
		return item;
	}
	public static List<Object> getItemCode(String Cat)
	{
		List<Object> code = new ArrayList<Object>();
		try {
		String query = "select ItemMaster.itemcode from itemmaster,CategoryMaster where CategoryMaster.id=Itemmaster.catid and CategoryMaster.category='"+Cat+"'";
		ResultSet rs = CommonMethods.selectQuery(query);
		while(rs.next())
		{
			code.add(rs.getInt(1));
		}
		}catch(Exception e)
		{
			System.out.println("Error in gettin Item Code "+e.getMessage());
			return null;
		}
		return code;
	}
	public static List<Object> getAllWaitorName()
	{
		List<Object> list = new ArrayList<Object>();
		try {
		ResultSet rs = CommonMethods.selectQuery("select fname from Employee where Designation='Waitor' order by ID");
		while(rs.next())
		{
			list.add(rs.getString(1));
		}
		rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in getAllWaitorName "+e.getMessage());
			return null;
			// TODO: handle exception
		}
		return list;
	}
	public static List<Object> getAllTable()
	{
		List<Object> list = new ArrayList<Object>();
		try {
		ResultSet rs = CommonMethods.selectQuery("select tablename from TableMaster order by ID");
		while(rs.next())
		{
			list.add(rs.getString(1));
		}
		rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in getAllWaitorName "+e.getMessage());
			return null;
			// TODO: handle exception
		}
		return list;
	}
	public static List<String> getAllEmployee()
	{
		List<String> list = new ArrayList<>();
		try {
		ResultSet rs = CommonMethods.selectQuery("select fname,mname,lname from Employee order by FName");
		while(rs.next())
		{
			list.add(rs.getString(1).trim()+" "+rs.getString(2).trim()+" "+rs.getString(3).trim());
		}
		rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in getAllWaitorName "+e.getMessage());
			return null;
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<String> getAllUserName()
	{
		List<String> list = new ArrayList<>();
		try {
		ResultSet rs = CommonMethods.selectQuery("select UserName from Login order by UserName");
		while(rs.next())
		{
			list.add(rs.getString(1).trim());
		}
		rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in getAllWaitorName "+e.getMessage());
			return null;
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<String> getAllCustomerFullName()
	{
		List<String> list = new ArrayList<>();
		try {
		ResultSet rs = CommonMethods.selectQuery("select FName,MName,LName from Customer order by FName");
		while(rs.next())
		{
			list.add(rs.getString(1).trim()+" "+rs.getString(2).trim()+" "+rs.getString(3).trim());
		}
		rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in getAllWaitorName "+e.getMessage());
			return null;
			// TODO: handle exception
		}
		return list;
	}
	public static List<String> getBankName()
	{
		List<String> list = new ArrayList<>();
		try {
		ResultSet rs = CommonMethods.selectQuery("select BankName from Bankdetails order by BankName");
		while(rs.next())
		{
			list.add(rs.getString(1).trim());
		}
		rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in getAllWaitorName "+e.getMessage());
			return null;
			// TODO: handle exception
		}
		return list;
	}
	
	
	public static int getCatId(String name)
	{
	
		try 
		{
			ResultSet rs = CommonMethods.selectQuery("select ID from CategoryMaster where Category='"+name+"'");
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) 
		{
			System.out.println("Error in gettin catid "+e.getMessage());
			return 0;
			// TODO: handle exception
		}
		
	
	}
	public static int checkName(String name)
	{
		int flag=0;
		try 
		{
			ResultSet rs = CommonMethods.selectQuery("select Itemname,ItemCode from ItemMaster where Itemname='"+name+"'");
			rs.next();
			if(rs.getString(1).equals(name))
			{
				flag=1;
			}
		} catch (Exception e)
		{
			System.out.println("Error in checkNameCode "+e.getMessage());
			return 0;
		}
		return flag;
	}
	public static int checkCode(int code)
	{
		
		int flag=0;
		try 
		{
			ResultSet rs = CommonMethods.selectQuery("select ItemCode from ItemMaster ");
		while(rs.next())
		{
			if(rs.getInt(1)==code)
			{
				flag=1;
				return flag;
			}
		}
		rs.close();
		} catch (Exception e)
		{
			System.out.println("Error in checkNameCode "+e.getMessage());
			return 0;
		}
		return flag;
	}
	public static String getNameusingCode(int code,String cat)
	{
		String name="";
		try 
		{
String query = "select ItemMaster.Itemname from ItemMaster,CategoryMaster where ItemMaster.CatId=CategoryMaster.ID and CategoryMaster.Category='"+cat+"' and ItemMaster.ItemCode="+code;
//System.out.println(query);
ResultSet rs = CommonMethods.selectQuery(query);
		if(!rs.next())
		{
			return "";
		}
			
			name = rs.getString(1);
		} catch (Exception e) 
		{
			System.out.println("Error in getNameusingCode "+e.getMessage());
			e.printStackTrace();
			return "";
		}
				return name;
	}
	public static float getRateUsingName(String name)
	{
		float rate = 0.0f;
		
		if(name.equals("")||name.equals(null))
		{
			return 0.0f;
			
		}
		try {
			ResultSet rs = CommonMethods.selectQuery("select rate from Itemmaster where Itemname='"+name+"'");
		//	rs.next();
			if (!rs.next() )
			{
			   // System.out.println("no data");
			} else 
			{
		    do
			    {
			    	rate = rs.getFloat(1);
			    } while (rs.next());
			}
			//rate = rs.getFloat(1);
			//System.out.println("rate "+rate);
		} catch (Exception e) {
			System.out.println("Error in getRateUsingName "+e.getMessage());
			return 0.0f;
		}
		return rate;
	}
	public static int getTableId(String table)
	{
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select ID from TableMaster where TableName='"+table+"'");
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) 
		{
			return 0;
		}
	}
	public static int getWaitorid(String waitor)
	{
		try 
		{
		ResultSet rs = CommonMethods.selectQuery("select id from Employee where fname='"+waitor+"'");
		rs.next();
		return rs.getInt(1);
		} catch (Exception e)
		{
			System.out.println("Error "+e.getMessage());
			//e.printStackTrace();
			return 0;
			// TODO: handle exception
		}
	}
	public static String getWaitorName(int id)
	{
		try 
		{
		ResultSet rs = CommonMethods.selectQuery("select fname from Employee where ID="+id);
		rs.next();
		return rs.getString(1);
		} catch (Exception e)
		{
			return "";
			// TODO: handle exception
		}
	}

	public static String getTableName(int id)
	{
		try 
		{
		ResultSet rs = CommonMethods.selectQuery("select TABLENAME from TableMaster where ID="+id);
		rs.next();
		return rs.getString(1);
		} catch (Exception e)
		{
			return "";
			// TODO: handle exception
		}
	}

	
	//Temp Transaction
	public static void addTempTransaction(String item,float qty,float rate,float amt,int tableno,int waitorid,float pqty)
	{
		try 
		{
			int id = CommonMethods.getId("select id from TempTransaction order by id");
String query = "insert into TempTransaction values("+id+",'"+item+"',"+qty+","+rate+","+amt+","+tableno+","+waitorid+","+pqty+")";
			int i = CommonMethods.addRecord(query);
			if(i==1)
			{
				System.out.println("Record Saved "+i);
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}
    public static void updateTemp(String item,int table,float qty,float amt,float pqty)
    {
    	try 
    	
    	{
    		//System.out.println("minus qty"+ pqty);
    		float npq=0.0f;
    	
    		if(pqty<0)
    			{
    			System.out.println("minus qty"+ pqty);	
    			ResultSet rs = CommonMethods.selectQuery("select printqty from TempTransaction where ItemName='"+item+"' and TableNo="+table);
    			rs.next();
    			float opq = rs.getFloat(1);
    		
    			System.out.println("old print qty "+opq +"New print qty "+pqty+" New addes print qty "+(opq+(pqty)));
    			npq = opq+(pqty);
    			if(npq<0)
    				{
    					System.out.println("New qty is minus"+npq);
    					npq=0.0f;
    				}
    				else
    				{
    					System.out.println("New Print qty plus "+npq);
    				}
    			}
    			else
    			{
    				npq=pqty;
    			}
    		
    	
 String query = "update TempTransaction set QTY=(qty+("+pqty+")), AMT="+amt+", printqty="+npq+" where ItemName='"+item+"' and Tableno="+table;
		 CommonMethods.updateRecord(query);
 System.out.println(query);
		
		} catch (Exception e) 
    	{
			System.out.println("Error in updateTemp "+e.getMessage());
			// TODO: handle exception
		}
    }
    
    public static List<String> getRunningTable()
    {
    	List<String> table = new ArrayList<>();
    	try {
			
			String query = "select TableMaster.TableName from TableMaster,TempTransaction where TableMaster.id=TempTransaction.TABLENO";
			ResultSet rs = CommonMethods.selectQuery(query);
			while(rs.next())
			{
				String name = rs.getString(1);
				
				if(!table.contains(name))
				{
					table.add(name);
					//System.out.println("Add in list name = "+name);
				}
				
			}
			rs.close();
		} catch (Exception e) 
    	{
			System.out.println("Error getRunningTable "+e.getMessage());
			// TODO: handle exception
		}
		return table;
    	
    }
    public static List<String> getRunningTableFromBill()
    {
    	List<String> table = new ArrayList<>();
    	try {
			
			String query = "select TableMaster.TableName from TableMaster,Bill where TableMaster.id=Bill.TABLENO and status='no'";
			ResultSet rs = CommonMethods.selectQuery(query);
			while(rs.next())
			{
				String name = rs.getString(1);
				
				if(!table.contains(name))
				{
					table.add(name);
					//System.out.println("Add in list name = "+name);
				}
				
			}
			rs.close();
		} catch (Exception e) 
    	{
			System.out.println("Error getRunningTable "+e.getMessage());
			// TODO: handle exception
		}
		return table;
    	
    }
    public static List<String> getAllCategory()
    {
    	List<String> category = new ArrayList<>();
    	try {
			
			String query = "select Category from CategoryMaster order by ID ";
			ResultSet rs = CommonMethods.selectQuery(query);
			while(rs.next())
			{
				String name = rs.getString(1);
				
				category.add(name);
				
			}
			rs.close();
		} catch (Exception e) 
    	{
			System.out.println("Error getRunningTable "+e.getMessage());
			// TODO: handle exception
		}
		return category;
    }
    public static int FindTableinTemp(String table)
    {
    	int flag=0;
    	try 
    	{
ResultSet rs = CommonMethods.selectQuery("select TempTransaction.Id from TempTransaction,tableMaster where TempTransaction.tableno=TableMaster.id and TableMaster.TableName='"+table+"'");
		rs.next();
		if(rs!=null )
		{
			
		flag=1;
		}
    	} catch (Exception e)
    	{
			System.out.println("Error in FindTableinTemp "+e.getMessage());
			return 0;
		}
    	return flag;
    }
    public static int checkitemInTemp(String name,int table)
    {
    	System.out.println("Name "+name +"Table "+table);
    	int id=0;
    	try 
    	{
    	
    		ResultSet rs = CommonMethods.selectQuery("select ItemName,ID from TempTransaction where  tableno="+table);
    		while(rs.next())
    		{
    			if(rs.getString(1).equals(name))
    			{
    				id=rs.getInt(2);
    			}
    		}
    		
		} catch (Exception e) 
    	{
		System.out.println("Error in checkitemInTemp "+e.getMessage());
		e.printStackTrace();
		return 0;
		}
    	return id;
    }
    public static int checkitemInTrans(String name,int billno)
    {    	
    	int id=0;
    	try 
    	{
    	
    		ResultSet rs = CommonMethods.selectQuery("select ItemName,ID from Transaction where  bill="+billno);
    		while(rs.next())
    		{
    			if(rs.getString(1).equals(name))
    			{
    				id=rs.getInt(2);
    			}
    		}
    		
		} catch (Exception e) 
    	{
		System.out.println("Error in checkitemInTemp "+e.getMessage());
	
		return 0;
		}
    	return id;
    }
    public static int getEmpidFullName(String fname,String mname,String lname)
	{
		try 
		{
		ResultSet rs = CommonMethods.selectQuery("select id from Employee where fname='"+fname+"' and lname='"+lname+"'");
		rs.next();
		return rs.getInt(1);
		} catch (Exception e)
		{
			System.out.println("Error "+e.getMessage());
			//e.printStackTrace();
			return 0;
			// TODO: handle exception
		}
	}
    
    public static String getPassword(String UserName)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("select Password from Login where UserName='"+UserName+"'");
    		rs.next();
    		return rs.getString(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getPassword "+e.getMessage());
    		return "";
    	}
    }
    public static int getUserId(String UserName)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("select Id from Login where UserName='"+UserName+"'");
    		rs.next();
    		return rs.getInt(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getuserId"+e.getMessage());
    		return 0;
    	}
    }

    public static String getUserName(int id)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("select UserName from Login where ID="+id);
    		rs.next();
    		return rs.getString(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getuserId"+e.getMessage());
    		return "";
    	}
    }
    public static int getCodeFromName(String name)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("select itemCode from Itemmaster where ItemName='"+name+"'");
    		rs.next();
    		return rs.getInt(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getCodeFromName "+e.getMessage());
    		return 0;
    	}
    }

    public static String getCustomerNameUsingCode(String Code)
    {
    	String name="";
    	try 
    	{
    		ResultSet rs = CommonMethods.selectQuery("select FName,MName,LName from Customer where CustomerKey='"+Code+"'");
    		while(rs.next())
    		{
    			name = rs.getString(1).trim()+" "+rs.getString(2).trim()+" "+rs.getString(3).trim();
    		}
		} catch (Exception e)
    	{
		System.out.println("Error in getCustomerNameUsingCode "+e.getMessage());
		return "";
		}
    	return name;
    	
    }
    public static String getCustomerNameUsingId(int id)
    {
    	String name="";
    	try 
    	{
    		ResultSet rs = CommonMethods.selectQuery("select FName,MName,LName from Customer where id="+id);
    		while(rs.next())
    		{
    			name = rs.getString(1).trim()+" "+rs.getString(2).trim()+" "+rs.getString(3).trim();
    		}
		} catch (Exception e)
    	{
		System.out.println("Error in getCustomerNameUsingCode "+e.getMessage());
		return "";
		}
    	return name;
    	
    }
    
    public static int getCustomerIdUsingName(String name)
    {
    	//System.out.println("first iGot name to find "+name);
    	int  id=0;
    	try
    	{
    		String fname = CommonLogic.getName(name, 1);
    		String mname = CommonLogic.getName(name, 2);
    		String lname = CommonLogic.getName(name, 3);
    		//System.out.println("Got name Fname-"+fname.trim()+" Maname-"+mname.trim()+" Lanme-"+lname.trim());
    		//ResultSet rs = CommonMethods.selectQuery("select id, lname from Customer where FName='"+fname.trim()+"' and MName='"+mname.trim()+"'");
    		ResultSet rs = CommonMethods.selectQuery("select id, Mname from Customer where FName='"+fname.trim()+"' and LName='"+lname.trim()+"'");
    		while(rs.next())
    		{
    			//id = rs.getInt(1);
    			
    			//System.out.println("id  in resultset= "+rs.getInt(1)+" and MName="+rs.getString(2)+" and check with-"+mname);
    			if(rs.getString(2).equals(mname.trim()))
    			{
    				//System.out.println("id in resultset = "+rs.getInt(1));
    				//return rs.getInt(1);
    				id = rs.getInt(1);
    			}
    				
    		}
    		rs.close();
    	}catch(Exception e)
    	{
    		System.out.println("Error "+e.getMessage());
    		e.printStackTrace();
    		return 0;
    	}
		return id;
    }
    public static String getCustomerKeyUsingName(String name)
    {
    	String  id="";
    	try
    	{
    		String fname = CommonLogic.getName(name, 1);
    		String mname = CommonLogic.getName(name, 2);
    		String lname = CommonLogic.getName(name, 3);
    		//System.out.println("Got name "+fname+""+mname+""+lname);
    		ResultSet rs = CommonMethods.selectQuery("select CustomerKey, lname from Customer where FName='"+fname.trim()+"' and MName='"+mname.trim()+"'");
    		while(rs.next())
    		{
    			//id = rs.getInt(1);
    			if(rs.getString(2).equals(lname.trim()))
    			{
    				//return rs.getInt(1);
    				id = rs.getString(1);
    			}
    				
    		}
    		rs.close();
    	}catch(Exception e)
    	{
    		System.out.println("Error "+e.getMessage());
    		e.printStackTrace();
    		return "";
    	}
		return id;
    }
   
    public static String getCustomerAddress(int id)
    {
    	try
    	{
    		//System.out.println("Got id "+id);
    		if(id<=0)
    		{
    			System.out.println("Find Address id is zero");
    		}
    		ResultSet rs = CommonMethods.selectQuery("SELECT FlatNo,StreetName,City,District,Taluka,mobileNo from Customer where id="+id);
    		//rs.next();
    		if(rs.next()!=false)
    		return "Gar k`. r]ma naM.: "+rs.getInt(1)+"\nrstaa :"+rs.getString(2)+"\ngaava Sahr :"+rs.getString(3)+"\ntaalauka :"+rs.getString(5)+"\nijalha :"+rs.getString(4)+"\nmaao. naM:"+rs.getString(6);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getCustomerAddress "+e.getMessage());
    		e.printStackTrace();
    		return "";
    	}
		return null;
    	
    }
    public static String getCustomerAddress2(int id)
    {
    	try
    	{
    		//System.out.println("Got id "+id);
    		if(id<=0)
    		{
    			System.out.println("Find Address id is zero");
    		}
    		ResultSet rs = CommonMethods.selectQuery("SELECT FlatNo,StreetName,City,District,Taluka,mobileNo from Customer where id="+id);
    		//rs.next();
    		if(rs.next()!=false)
    		return "Gar k`. r]ma naM.: "+rs.getInt(1)+" rstaa :"+rs.getString(2)+" gaava Sahr :"+rs.getString(3)+"\n                   taalauka :"+rs.getString(5)+" ijalha :"+rs.getString(4)+"    maao. naM:"+rs.getString(6);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getCustomerAddress "+e.getMessage());
    		e.printStackTrace();
    		return "";
    	}
		return null;
    	
    }
    
    
    
    public static String getCustomerAddress1(int id)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("SELECT FlatNo,StreetName,City,District,Taluka from Customer where id="+id);
    		//rs.next();
    	if(rs.next()!=false)
    		return "rstaa "+rs.getString(2)+" gaava Sahr "+rs.getString(3)+" taalauka"+rs.getString(5)+" ijalha "+rs.getString(4);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getCustomerAddress123 "+e.getMessage());
    		return "";
    	}
		return null;
    	
    	
    }

    
    public static float getAllCredit(int id)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("SELECT sum(BillAmt-Discount) from Bill where CustomerID="+id+" and Paymode='Credit'");
    		rs.next();
    		return rs.getFloat(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getAllCredit "+e.getMessage());
    		return 0.0f;
    	}
    }
    
    public static float getAllRecieved(int id)
    {
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("select sum(cashrecieved.Amount) from cashrecieved,Customer where Customer.ID=cashrecieved.cid and CID="+id);
    		rs.next();
    		return rs.getFloat(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in getAllRecieved "+e.getMessage());
    		return 0.0f;
    	}
    }
    
    public static int checkAccountNo(String account,String ifc)
    {
    	int flag=0;
    	try
    	{
    		
    		ResultSet rs = CommonMethods.selectQuery("select AccountNo,IFC from BankDetails order by ID");
    		while(rs.next())
    		{
    			if(rs.getString(1).equals(account)&& rs.getString(2).equals(ifc) )
    			{
    				flag=1;
    			}
    			
    		}
    		rs.close();
    	}catch (Exception e) 
    	{
			System.out.println("Error in checkAccountNo "+e.getMessage());
			return 0;
		}
    	return flag;
    }
    public static List<String>getSearchName(String query )
	{
		//System.out.println("i get Category "+CatName);
		List<String> item = new ArrayList<>();
		try
		{
//String query ="select BankName from BankDetails order by BankName"; 			
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next())
			{
				item.add(rs.getString(1));
				
			}
			rs.close();
		}catch(Exception e)
		{
			System.out.println("Error in Geting Items"+e.getMessage());
			return null;
		}
		return item;
	}
    public static String getBankNameUsingCode(String code)
    {
    	try {
			ResultSet rs = CommonMethods.selectQuery("select BankName from BankDetails where BankCode like'%"+code+"%'");
			rs.next();
			
			return rs.getString(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return "";
    }
    public static String getBankAccountNOName(String name)
    {
    	try {
			ResultSet rs = CommonMethods.selectQuery("select AccountNo from BankDetails where BankName like'%"+name+"%'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e)
    	{
			// TODO: handle exception
		}
    	return "";
    }
    public static String getBankCode(String name)
    {
    	try {
			ResultSet rs = CommonMethods.selectQuery("select BankCode from BankDetails where BankName='"+name+"'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e)
    	{
			// TODO: handle exception
		}
    	return "";
    }
    public static int getBankID(String name)
    {
    	try {
			ResultSet rs = CommonMethods.selectQuery("select ID from BankDetails where BankName='"+name+"'");
			rs.next();
			return rs.getInt(1);
		} catch (Exception e)
    	{
			// TODO: handle exception
		}
    	return 0;
    }
    public static float getBillAmount(int billno)
    {
    	float amt=0.0f;
    	try
    	{
    		ResultSet rs = CommonMethods.selectQuery("SELECT BillAmt from Bill where billno = "+billno);
    		rs.next();
    		amt = rs.getFloat(1);
    	}catch(Exception e)
    	{
    		System.out.println("Error in  getBillAmount "+e.getMessage());
    		return 0.0f;
    	}
    	return amt;
    }
}


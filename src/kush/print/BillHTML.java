package kush.print;

import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.ResultSet;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import ankush.CommonLogic;
import ankush.CommonMethods;

public class BillHTML 
{
	public BillHTML(int bill)
	{
		try
		{
			File file = new File("D:\\Hotel Software\\billhtml.html");
			//file.mkdirs();
			if(!file.exists())
			{
				System.out.println("New File Create");
				
				file.createNewFile();
			}else
			{
				System.out.println("File already exists");
			}
			ResultSet r = CommonMethods.selectQuery("select BillAmt,Discount, CustomerID, WaitorId,TableNo,Userid,BillDate,Paymode from Bill where Billno="+bill);
			r.next();
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write("<HTML>");
			out.write("<BODY>");
			//out.write("<BODY>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 40px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write(" &emsp;  ha^Tola AMjanaI</br>");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("&emsp;&emsp;&emsp;&emsp;f^imalaI rosTa^rMT");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 15px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("mau.paosT.saaonaJ-.ta.naovaasaa.ija.Ahmadnagar 414105");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 15px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("&emsp;&emsp;&emsp;maaobaa[la naM.9860419230&emsp;9404978630");
			out.write("<p style=\"font-family: 'Times New ROman', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("---------------------------------------");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("ibala naM."+bill+"&emsp;&emsp; idnaaMk&emsp;"+ConvertDate(r.getDate(7)));
			out.write("</br>naava");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 15px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("<table style=\"border-bottom: 1px solid black\"");
			out.write("<tr>");
			out.write("<td>tpiSala &emsp;&emsp;&emsp;&emsp;");
			out.write("<td>naga     ");
			out.write("<td>&emsp;dr  ");
			out.write("<td>&emsp;r@kma&emsp;&emsp;");
			out.write("</tr>");
			out.write("</table>");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			//Write Data in table from here
			ResultSet rs = CommonMethods.selectQuery("select ItemName,Qty,rate,amt from Transaction where Bill="+bill);
			out.write("<table ");
			while(rs.next())
			{
			
			out.write("<tr>");
			out.write("<td>"+rs.getString(1));
			out.write("<td>"+rs.getFloat(2));
			out.write("<td>&emsp;"+rs.getFloat(3));
			out.write("<td>&emsp;"+rs.getFloat(4));
			out.write("</tr>");
			}
			rs.close();
			out.write("</table>");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Times New ROman', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("---------------------------------------");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Shivaji02', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			//out.write("k^Sa ibala &emsp; To naM. ");//+CommonLogic.getTableName(r.getInt(5))+"  &emsp;&emsp;"+r.getFloat(1)+"</br>");
			
			out.write("k^Sa ibala &emsp;vaoTr :"+CommonLogic.getWaitorName(r.getInt(4))+"  &emsp;&emsp;"+r.getFloat(1)+"</br>");
			
			
			out.write("<p style=\"font-family: 'Times New ROman', 'MS Sans Serif', arial; font-size: 20px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("Table:-"+CommonLogic.getTableName(r.getInt(5)));
			out.write("</p>");
			out.write("<p style=\"font-family: 'Times New ROman', 'MS Sans Serif', arial; font-size: 15px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("Thanks for Visit..... HAVE A NICE DAY");
			out.write("</p>");
			out.write("<p style=\"font-family: 'Times New ROman', 'MS Sans Serif', arial; font-size: 12px; text-align:left; line-height:100%; margin:0px; color:#000000; clear:both;\">");
			out.write("___________________________________________________</br>");
			out.write("Developed by:- Ankush Supnar(9960855742)");
			out.write("</p>");
			
			out.write("</BODY>");
			out.write("</HTML>");
			
			out.close();
			printBIll();
		}catch(Exception e)
		{
			System.out.println("Error in BIllHTML "+e.getMessage());
			return ;
		}
	}

	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		new BillHTML(8);

	}
	@SuppressWarnings({ "unused", "deprecation" })
	String ConvertDate(java.sql.Date date)
	   {
		   String date1="";
		   int d = date.getDate();
		   int m = date.getMonth();
		   int y = date.getYear();
		   
		   return ""+d+"."+m+"."+(y+1900); 
		   
	   }
	void printBIll()
	{
		try
		{
						
			//PrintService ps = findPrintService("Microsoft XPS Document Writer");
			PrintService ps = findPrintService("Microsoft XPS Document Writer");
		 	//File file = new File("D:\\Hotel Software\\billhtml.html"); 
			 DocPrintJob job = ps.createPrintJob();
			 DocAttributeSet das = new HashDocAttributeSet();
			 Doc document = new SimpleDoc(new FileInputStream(new File("D:\\Hotel Software\\billhtml.html")), DocFlavor.INPUT_STREAM.AUTOSENSE, das);
			 PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			 job.print(document, pras);
		}catch(Exception e)
		{
			System.out.println("Error in printing "+e.getMessage());
			return;
		}
		
		
	}
	 public PrintService findPrintService(String printerName)
	    {
	        for (PrintService service : PrinterJob.lookupPrintServices())
	        {
	            if (service.getName().equalsIgnoreCase(printerName))
	                return service;
	        }

	        return null;
	    }


}

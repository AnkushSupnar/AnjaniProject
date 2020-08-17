package kush.print;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonLogic;
import ankush.CommonMethods;

public class MultipleBills
{

	List<Integer> bills;
	public MultipleBills(List<Integer> bills)
	{
		Document doc = new Document();
		doc.setPageSize(PageSize.A4);
		doc.setMargins(5, 1, 30, 0);		
		
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("D:\\Hotel Software\\Multiple Bills.pdf"));
	        doc.open();
	      
	        PdfPTable table = new PdfPTable(3);
	      //First Row
	        table.setWidths(new float[] { 80f, 80f, 80f});
			this.bills = bills;
			int limit=3;
			for(int i=0;i<bills.size();i++)
			{
				if(i<limit)
				{
					System.out.println("Limit not cross");
					table.addCell(getTable(bills.get(i)));
				}
				else
				{
					table.addCell(getTable(bills.get(i)));
				}
						
			}
			table.completeRow();
		
			 
        doc.add(table);
		 } catch (DocumentException | FileNotFoundException e) {
	            e.printStackTrace();
	        } finally {
	            doc.close();
	        }
	}
	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		List<Integer> bill = new ArrayList<Integer>();
		bill.add(1);
		bill.add(2);
		bill.add(23);
		bill.add(24);
		bill.add(140);
		bill.add(240);
		bill.add(150);
		bill.add(149);
	
		new MultipleBills(bill);
		// TODO Auto-generated method stub

	}
	
	public PdfPTable getTable(int bill)
	{
		//Logger logger = Logger.getLogger(BillPdf.class);
		//final String fontname ="C:\\Windows\\Fonts\\Kiran.ttf";
		final String fontname = CommonMethods.fontName;
		 Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 25.8f, Font.NORMAL, BaseColor.BLACK);
		 Font f3 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15f, Font.NORMAL, BaseColor.BLACK);
		 Font f4 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
		 Font f12 = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8f, Font.NORMAL, BaseColor.BLACK);
		 Font smallfont = new Font(FontFamily.HELVETICA, 7);
		 PdfPTable table = new PdfPTable(4);
		
		 Font f2 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12f, Font.NORMAL, BaseColor.BLACK);
		try
		{
			ResultSet r = CommonMethods.selectQuery("select BillAmt,Discount, CustomerID, WaitorId,TableNo,Userid,BillDate,Paymode from Bill where Billno="+bill);
			r.next();
			 table.setWidths(new float[] { 10f, 6f, 6f, 6f});
			 

			 PdfPCell cell = new PdfPCell(new Phrase("      ha^Tola AMjanaI   ",font));
			 cell.setColspan(4);
			 cell.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell);
			 table.completeRow();
			
			 PdfPCell cell2 = new PdfPCell(new Phrase("           f^imalaI rosTa^rMT   ",f3));
			 cell2.setColspan(4);
			 cell2.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell2);
			 table.completeRow();

			 PdfPCell cell3 = new PdfPCell(new Phrase("mau.paosT.saaonaJ-.ta.naovaasaa.ija.Ahmadnagar 414105   ",f4));
			 cell3.setColspan(4);
			 cell3.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell3);
			 table.completeRow();
			
			 PdfPCell cell4 = new PdfPCell(new Phrase("maaobaa[la naM.9860419230   8552803030",f4));
			 cell4.setColspan(4);
			 cell4.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell4);
			 table.completeRow();
			
			 PdfPCell cell5 = new PdfPCell(new Phrase("GSTIN:- 27AGKPL2419AIZR",f12));
			 cell5.setColspan(4);
			 cell5.setBorder(Rectangle.BOTTOM);
			 table.addCell(cell5);
			 table.completeRow();
			
			 PdfPCell cell6 = new PdfPCell(new Phrase("ibala naM."+bill+"  idnaaMk "+ConvertDate(r.getDate(7)),f3));
			 cell6.setColspan(4);
			 cell6.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell6);
			 table.completeRow();
			
			 PdfPCell cell7 = new PdfPCell(new Phrase("naava : "+CommonLogic.getCustomerNameUsingId(r.getInt(3)),f3));
			 cell7.setColspan(4);
			 cell7.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell7);
			 table.completeRow();
			
			 PdfPCell cell8 = new PdfPCell(new Phrase("tapiSala   ",f2));
			 cell8.setBorder(Rectangle.TOP);
			 table.addCell(cell8);
			 
			 PdfPCell cell9 = new PdfPCell(new Phrase("naga",f2));
			 cell9.setBorder(Rectangle.TOP);
			 table.addCell(cell9);
			 
			 PdfPCell cell10 = new PdfPCell(new Phrase("dr",f2));
			 cell10.setBorder(Rectangle.TOP);
			 table.addCell(cell10);
			 
			 PdfPCell cell11 = new PdfPCell(new Phrase("r@kma",f2));
			 cell11.setBorder(Rectangle.TOP);
			 table.addCell(cell11);
			 
			 table.completeRow();
			 ResultSet rs = CommonMethods.selectQuery("select ItemName,Qty,rate,amt from Transaction where Bill="+bill);
			 while(rs.next())
			 {
				 PdfPCell cell12 = new PdfPCell(new Phrase(""+rs.getString(1),f2));
				 cell12.setBorder(Rectangle.NO_BORDER);
				 table.addCell(cell12);
				 
				 PdfPCell cell13 = new PdfPCell(new Phrase(""+rs.getFloat(2),f2));
				 cell13.setBorder(Rectangle.NO_BORDER);
				 table.addCell(cell13);
				 
				 PdfPCell cell14 = new PdfPCell(new Phrase(""+rs.getFloat(3),f2));
				 cell14.setBorder(Rectangle.NO_BORDER);
				 table.addCell(cell14);
				 
				 PdfPCell cell15 = new PdfPCell(new Phrase(""+rs.getFloat(4),f2));
				 cell15.setBorder(Rectangle.NO_BORDER);
				 table.addCell(cell15);
				 table.completeRow();
			 }
			 rs.close();
			 PdfPCell cell16 = new PdfPCell(new Phrase("k`oiDT ibala",f2));
			 cell16.setBorder(Rectangle.TOP);
			 table.addCell(cell16);
			 
			 PdfPCell cell17 = new PdfPCell(new Phrase(""+CommonLogic.getTableName(r.getInt(5))));
			 cell17.setBorder(Rectangle.TOP);
			 table.addCell(cell17);
			 
			 
			 PdfPCell cell18 = new PdfPCell(new Phrase("TaoTla",f2));
			 cell18.setBorder(Rectangle.TOP);
			 table.addCell(cell18);
			 
			 
			 PdfPCell cell19 = new PdfPCell(new Phrase(""+r.getFloat(1),f2));
			 cell19.setBorder(Rectangle.TOP);
			 table.addCell(cell19);
			 
			 PdfPCell cell20 = new PdfPCell(new Phrase("vaoTr :"+CommonLogic.getWaitorName(r.getInt(4)),f2));
			 cell20.setColspan(4);
			 cell20.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell20);
			 
			 table.completeRow();
			 PdfPCell cell21 = new PdfPCell(new Phrase("Thanks for visit.....HAVE A NICE DAY",smallfont));
			 cell21.setBorder(Rectangle.NO_BORDER);
			 cell21.setColspan(4);
			 table.addCell(cell21);
			 table.completeRow();
			 
			 
			 PdfPCell cell22 = new PdfPCell(new Phrase("Developed by Ankush(9960855742)",smallfont));
			 cell22.setColspan(4);
			 cell22.setBorder(Rectangle.NO_BORDER);
			 table.addCell(cell22);
			 table.completeRow();
			 
		}catch(Exception e)
		{
			System.out.println("Error in Getting Bill Table");
		}
		return table;
	}

	@SuppressWarnings("deprecation")
	String ConvertDate(java.sql.Date date)
	   {
		 //System.out.println("Getted date "+date);
		   int d = date.getDate();
		   int m = date.getMonth();
		   int y = date.getYear();
		  // System.out.println("Getted date "+d+"/"+(m+1)+"/"+(y+1900));
		   return ""+d+"."+(m+1)+"."+(y+1900); 
		   
	   }

}

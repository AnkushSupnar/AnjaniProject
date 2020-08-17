package kush.print;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonLogic;
import ankush.CommonMethods;

public class ReceiptPdf
{
	final static Logger logger = Logger.getLogger(BillPdf.class);
	public static final String RESULT = "D:\\Hotel Software\\Receipt.pdf";
	public static String fontname = CommonMethods.fontName;
	int id;
	public ReceiptPdf(int id)
	{
		 PropertyConfigurator.configure("Log.properties");
		 logger.debug("DEBUG MESSAGE: ");
		 BasicConfigurator.configure();
		
		try 
		{
			Properties prop = CommonMethods.loadPropertiesFile();
			fontname = prop.getProperty("Bill.Font");
			//String date;
			float amt;
			int bankid;
			Float totalRemaining=0.0f;
			Document document = new Document();
			document.setPageSize(PageSize.A4);
			document.setMargins(5, 1, 30, 0);
			ResultSet rs = CommonMethods.selectQuery("SELECT cid,Amount,Date,BankId,ChequeNo,Note,BankName FROM cashrecieved where id="+id);
			rs.next();
			
				int cid = rs.getInt(1);
				amt = rs.getFloat(2);
				//String name = CommonLogic.getCustomerNameUsingId(cid);
				totalRemaining = (CommonLogic.getAllCredit(cid)-CommonLogic.getAllRecieved(cid))+amt;
				bankid = rs.getInt(4); 
			
			 //Rectangle pagesize = new Rectangle(550f, 500f);
		  //	Document document = new Document(pagesize, 3f, 3f, 20f, 180f);
		 
		  	PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		   
		  	document.open();
		     
		    Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 28.8f, Font.NORMAL, BaseColor.BLACK);
		       // BaseFont base = font.getBaseFont();
		        
		       Paragraph p = new Paragraph("                                   ha^Tola AMjanaI",font);
		       p.setLeading(2);
		       
		      
		       document.add(p);
		      //  document.add(new Paragraph("          ha^Tola AMjanaI",font));
		        Font f3 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 18f, Font.NORMAL, BaseColor.BLACK);
		        Font f12 = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8f, Font.NORMAL, BaseColor.BLACK);
		    		        
		        Paragraph p2 = new Paragraph("                                                            f^imalaI rosTa^rMT",f3);
		        p2.setLeading(15);
		        document.add(p2);
		            
		        Font f4 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16f, Font.NORMAL, BaseColor.BLACK);
		        Paragraph p3 = new Paragraph("                                                 mau.paosT.saaonaJ-.ta.naovaasaa.ija.Ahmadnagar 414105",f4);
		        p3.setLeading(15);
		        document.add(p3);
		        Paragraph p4 = new Paragraph("                                                      maaobaa[la naM.9860419230   8552803030",f4);
		        p4.setLeading(15);
		        document.add(p4);
		        Paragraph p5 = new Paragraph("                                                                                                      GSTIN:- 27AGKPL2419AIZR",f12);
		        document.add(p5);
		        
		       // document.add(new Paragraph("_______________________________________________________________"));
		       
		        Paragraph p10 = new Paragraph("                                                         jamaa paavataI    ",f3);
		        p10.setLeading(15);
		        document.add(p10);
		        
		        
		        Paragraph p6 = new Paragraph("          paavataI k`. "+id+"                                                                         idnaaMk :"+getDate(rs.getString(3)),f4);
		        document.add(p6);
		        
		        Paragraph p7 = new Paragraph("          naava : "+CommonLogic.getCustomerNameUsingId(cid),f4);
		        document.add(p7);
		        String mode="k^Sa";
		        if(bankid!=0)
		        {
		        	mode="ba^Mk";
		        }
		        Paragraph p14 = new Paragraph("                                                                                        jamaa pawta: "+mode,f4);
		        document.add(p14);
		        Paragraph p11 = new Paragraph("          _____________________________________________________________");
		        p11.setLeading(2);
		        document.add(p11);
		        
		        Paragraph p8 = new Paragraph("          maaigala baakI :                   AajacaI jamaa :              ekuNa baakI : ",f4);
		        document.add(p8);
		        Paragraph p9 = new Paragraph("             "+totalRemaining+"                        "+amt+"                           "+(totalRemaining-amt));
		        document.add(p9);
		        Paragraph p12 =new Paragraph("          _____________________________________________________________");
		        p12.setLeading(5);
		        document.add(p12);
		        
		        if(bankid!=0)
		        {
		        	Paragraph p15 = new Paragraph("             ba^Mkocao naava                               caok naM                      ",f4);
			        document.add(p15);
			        
			        Paragraph p16 = new Paragraph("            "+rs.getString("BankName")+"                      "+CommonLogic.getChequeNo(id)+"                          ",f4);
			        p16.setLeading(12);
			        document.add(p16);
			        Paragraph p17 =new Paragraph("          _____________________________________________________________");
			        p17.setLeading(12);
			        document.add(p17);
		        }
		     
		        Paragraph p13 = new Paragraph("         jamaa ktaa-                                                                             GaoNaar",f4);
		        p13.setLeading(15);
		        document.add(p13);
		        
		        
		        document.close();
		        File htmlFile = new File("D:\\\\Hotel Software\\\\Receipt.pdf");
				Desktop.getDesktop().browse(htmlFile.toURI());
		        
		        
		        
		        
		} catch (Exception e)
		{
			System.out.println("Error generating PDF "+e.getMessage());
		}
	}
	public static void main(String[] args)
	{
		CommonMethods.openConnection();
		new ReceiptPdf(3);
		System.out.println("Start");
	}
	String getDate(String d)
	{
		String a[]=d.split("-");
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		String b[]=a[2].split(" ");
		return b[0]+":"+a[1]+":"+a[0];
	}
}
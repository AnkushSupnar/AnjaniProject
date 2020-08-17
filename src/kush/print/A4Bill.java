package kush.print;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import kush.POJO.Transaction;

public class A4Bill {
	final static Logger logger = Logger.getLogger(BillPdf.class);
	public static final String RESULT = "D:\\Hotel Software\\A4bill2.pdf";
	public static String fontname= CommonMethods.fontName;
	int billno;
	public A4Bill(int billno)
	{
		this.billno=billno;
		
		 PropertyConfigurator.configure("Log.properties");
		 logger.debug("DEBUG MESSAGE: ");
		 BasicConfigurator.configure();	
		try {
			 Properties prop = CommonMethods.loadPropertiesFile();
			 fontname=prop.getProperty("Bill.Font");
			 Font f2 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20f, Font.NORMAL, BaseColor.BLACK);
			 PdfPTable table = new PdfPTable(5);
			 table.setTotalWidth(new float[]{30,300,50,50,50 });
		     table.setLockedWidth(true);
		     kush.POJO.Bill bill = GetData.getBill(billno);
		     
		     System.out.println(bill);
		     PdfPCell cell0 = new PdfPCell(new Phrase("A.k` ",f2));
		        cell0.setFixedHeight(30);
		        cell0.setBorder(Rectangle.BOX);
		        table.addCell(cell0);
		     
		     PdfPCell cell = new PdfPCell(new Phrase("            tapiSala   ",f2));
		        cell.setFixedHeight(30);			        
		        cell.setBorder(Rectangle.BOX);
		        table.addCell(cell);
		        
		        PdfPCell cell2 = new PdfPCell(new Phrase("naga",f2));
		        cell2.setFixedHeight(30);
		        cell2.setVerticalAlignment(Element.ALIGN_RIGHT);
		        cell2.setBorder(Rectangle.BOX);
		        table.addCell(cell2);
		        
		        PdfPCell cell3 = new PdfPCell(new Phrase("dr",f2));
		        cell3.setFixedHeight(30);
		        cell3.setBorder(Rectangle.BOX);
		        table.addCell(cell3);
		        
		        PdfPCell cell4 = new PdfPCell(new Phrase("r@kma",f2));
		        cell4.setVerticalAlignment(Element.ALIGN_LEFT);
		        cell4.setFixedHeight(30);
		        cell4.setBorder(Rectangle.BOX);
		        table.addCell(cell4);
		        //GETTING TRANSACTIONS
		        Iterator<Transaction> it = GetData.getTransactionList(billno).iterator();
		        Transaction tr;
		        int sr=0;
		        float total=0.0f;
		        while(it.hasNext())
		        {
		        	tr = it.next();
		        	PdfPCell srno = new PdfPCell(new Phrase(""+(++sr),f2));
			        srno.setFixedHeight(25);
			        srno.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        srno.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        srno.setBorder(Rectangle.BOX);
			        table.addCell(srno);
		        	
			        PdfPCell particular = new PdfPCell(new Phrase(""+tr.getItemName(),f2));
			        particular.setFixedHeight(25);			        
			        particular.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        particular.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        particular.setBorder(Rectangle.BOX);
			        table.addCell(particular);
			        
			        PdfPCell qty = new PdfPCell(new Phrase(""+tr.getQty(),f2));
			        qty.setFixedHeight(25);			        
			        qty.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        qty.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        qty.setBorder(Rectangle.BOX);
			        table.addCell(qty);
			        
			        PdfPCell rate = new PdfPCell(new Phrase(""+tr.getRate(),f2));
			        rate.setFixedHeight(25);
			        rate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        rate.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        rate.setBorder(Rectangle.BOX);
			        table.addCell(rate);
			        
			        PdfPCell amt = new PdfPCell(new Phrase(""+tr.getAmt(),f2));
			        amt.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        amt.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        amt.setFixedHeight(25);			        
			        amt.setBorder(Rectangle.BOX);
			        table.addCell(amt);
			        total = total+tr.getAmt();
		        }
		        PdfPCell srno = new PdfPCell(new Phrase("",f2));
		        srno.setFixedHeight(25);
		        //srno.setColspan(2);
		        srno.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        srno.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        srno.setBorder(Rectangle.BOX);
		        table.addCell(srno);
	        	
		        PdfPCell particular = new PdfPCell(new Phrase("",f2));
		        particular.setFixedHeight(25);			        
		        particular.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        particular.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        particular.setBorder(Rectangle.BOX);
		        table.addCell(particular);
		        
		        PdfPCell qty = new PdfPCell(new Phrase("",f2));
		        qty.setFixedHeight(25);			        
		        qty.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        qty.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        qty.setBorder(Rectangle.BOX);
		        table.addCell(qty);
		        
		        PdfPCell rate = new PdfPCell(new Phrase("ekuNa",f2));
		        rate.setFixedHeight(25);
		        rate.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        rate.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        rate.setBorder(Rectangle.BOX);
		        table.addCell(rate);
		        
		        PdfPCell amt = new PdfPCell(new Phrase(""+total,f2));
		        amt.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        amt.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        amt.setFixedHeight(25);			        
		        amt.setBorder(Rectangle.BOX);
		        table.addCell(amt);
		       
		     Rectangle pagesize = new Rectangle(616f, 600f+table.getTotalHeight());
		  	 Document document = new Document(pagesize, 3f, 3f, 20f, 180f);
		     
		  	 PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		     document.open();
		     
		     //WRITING HEADING
		     Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 35.8f, Font.NORMAL, BaseColor.BLACK);
		     Paragraph p = new Paragraph("                               ha^Tola AMjanaI",font);
		       p.setLeading(15);  
		       document.add(p);
		       Font f3 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 25f, Font.NORMAL, BaseColor.BLACK);
		       Paragraph p2 = new Paragraph("                                                f^imalaI rosTa^rMT",f3);
		       p.setLeading(8);  
		       document.add(p2);
		       
		       Font f4 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20f, Font.NORMAL, BaseColor.BLACK);
		       Font f12 = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15f, Font.NORMAL, BaseColor.BLACK); 
		        document.add(new Paragraph("                                          mau.paosT.saaonaJ-.ta.naovaasaa.ija.Ahmadnagar 414105",f4));
		        document.add(new Paragraph("                                          maaobaa[la naM.9860419230,8552803030",f4));
		        document.add(new Paragraph("                                                GSTIN:- 27AGKPL2419AIZR",f12));
		        document.add(new Paragraph("_________________________________________________________________________________________"));
		        document.add(new Paragraph("     ibala naM."+billno+"                                                                    idnaaMk   "+convertDate(bill.getBillDate()),f2));
		        System.out.println();
		        document.add(new Paragraph("         naava : "+CommonLogic.getCustomerNameUsingId(bill.getCUstomerID()),f2));
		        document.add(new Paragraph("    ",f2));
		        
		     document.add(table);
		     		     
		     //WRITTING BOTTOM INFO
		     String mode = (bill.getPaymode().equals("Cash")?"        k^Sa ibala":"         baakI");
		     document.add(new Paragraph(""+mode+"          vaoTr :"+CommonLogic.getWaitorName(bill.getWaitorid()),f2));
		     document.close();
		     try {
					
					File htmlFile = new File(RESULT);
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (Exception e1) {
					System.out.println("Error in printing " + e1.getMessage());
				}
		     //new PrintingExample(RESULT);
		     
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		CommonMethods.openConnection();
		new A4Bill(20554);

	}

	public String convertDate(String date)
	{
		String [] temp = date.split("-");
		System.out.println(temp[0]);
		String temp2[] = temp[2].split(" ");
		//return temp[0]+"/"+temp[1]+"/"+temp2[0];
		return temp2[0]+"."+temp[1]+"."+temp[0];
	}
}

package kush.print;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Properties;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
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

public class BillPdf 
{
	final static Logger logger = Logger.getLogger(BillPdf.class);
	public static final String RESULT = "D:\\Hotel Software\\bill.pdf";
	//public static final String fontname ="C:\\Windows\\Fonts\\Kiran.ttf"; 
	//public static String fontname ="C:\\Users\\Ankush\\AppData\\Local\\Microsoft\\Windows\\Fonts\\Kiran.ttf";
	public static String fontname= CommonMethods.fontName;
	//fontname=fname;
	//hotel Server Font
	//public static final String fontname="C:\\Users\\hotel\\AppData\\Local\\Microsoft\\Windows\\Fonts\\Kiran.ttf";
	int billno;
	public BillPdf(int billno)
	{
		 this.billno=billno;
		
		 PropertyConfigurator.configure("Log.properties");
		 logger.debug("DEBUG MESSAGE: ");
		 BasicConfigurator.configure();		 
		try {
			System.out.println("Creating ");
			 Properties prop = CommonMethods.loadPropertiesFile();
			 fontname=prop.getProperty("Bill.Font");
			 Font f2 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12f, Font.NORMAL, BaseColor.BLACK);
			ResultSet r = CommonMethods.selectQuery("select BillAmt,Discount, CustomerID, WaitorId,TableNo,Userid,BillDate,Paymode from Bill where Billno="+billno);
			r.next();	
			 //Bill Table
	        PdfPTable table = new PdfPTable(4);
	        //table.setTotalWidth(new float[]{150, 120,120,120 });
	        table.setTotalWidth(new float[]{100,30,30,50 });
	        table.setLockedWidth(true);
	       // PdfContentByte cb = writer.getDirectContent();
	        PdfPCell cell = new PdfPCell(new Phrase("                      tapiSala   ",f2));
	        cell.setFixedHeight(20);			        
	        cell.setBorder(Rectangle.BOTTOM);
	        //cell.setColspan(2);
	        table.addCell(cell);
	        
	        PdfPCell cell2 = new PdfPCell(new Phrase("naga",f2));
	        cell2.setFixedHeight(20);
	        cell2.setVerticalAlignment(Element.ALIGN_RIGHT);
	        cell2.setBorder(Rectangle.BOTTOM);
	        //cell2.setColspan(2);
	        table.addCell(cell2);
	        
	        PdfPCell cell3 = new PdfPCell(new Phrase("dr",f2));
	        cell3.setFixedHeight(20);
	        cell3.setBorder(Rectangle.BOTTOM);
	        //cell3.setColspan(2);
	        table.addCell(cell3);
	        
	        PdfPCell cell4 = new PdfPCell(new Phrase("r@kma",f2));
	        cell4.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell4.setFixedHeight(20);
	        cell4.setBorder(Rectangle.BOTTOM);
	      //  cell4.setColspan(2);
	        table.addCell(cell4);
	        
	      /*  PdfPCell cell5 = new PdfPCell(new Phrase("good",f2));
	        cell5.setVerticalAlignment(Element.ALIGN_LEFT);
	        cell5.setFixedHeight(30);
	        cell5.setBorder(Rectangle.BOTTOM);
	      //  cell4.setColspan(2);
	        table.addCell(cell5);*/
	        ResultSet rs = CommonMethods.selectQuery("select ItemName,Qty,rate,amt from Transaction where Bill="+billno);
			while(rs.next())
			{
				 PdfPCell c1 = new PdfPCell(new Phrase("                    "+rs.getString(1),f2));
				 c1.setFixedHeight(20);			        
				 c1.setBorder(Rectangle.NO_BORDER);
			        //cell.setColspan(2);
			        table.addCell(c1);
			        
			        PdfPCell c2 = new PdfPCell(new Phrase(""+rs.getFloat(2),f2));
			        c2.setFixedHeight(20);
			       c2.setBorder(Rectangle.NO_BORDER);
			        //cell2.setColspan(2);
			        table.addCell(c2);
			        
			        PdfPCell c3 = new PdfPCell(new Phrase(""+rs.getFloat(3),f2));
			        c3.setFixedHeight(20);
			        c3.setBorder(Rectangle.NO_BORDER);
			        //cell3.setColspan(2);
			        table.addCell(c3);
			        
			        PdfPCell c4 = new PdfPCell(new Phrase(""+rs.getFloat(4),f2));
			        c4.setFixedHeight(20);
			        c4.setBorder(Rectangle.NO_BORDER);
			      //  cell4.setColspan(2);
			        table.addCell(c4);
			        
			       /* PdfPCell c5 = new PdfPCell(new Phrase(""+rs.getFloat(4),f2));
			        c5.setFixedHeight(30);
			        c5.setBorder(Rectangle.NO_BORDER);
			      //  cell4.setColspan(2);
			        table.addCell(c5);*/
	        
			}
	        rs.close();
	        String mode="";
	        if(r.getString(8).equals("Cash"))
	        {
	        	mode="k^Sa ibala";
	        }
	        else if(r.getString(8).equals("Credit"))
	        {
	        	mode="k`oiDT ibala";
	        }
	        	
	        PdfPCell c1 = new PdfPCell(new Phrase("                       "+mode+"   To naM.",f2));
			 c1.setFixedHeight(20);	
			 c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 c1.setBorder(Rectangle.TOP);
		        //cell.setColspan(2);
		        table.addCell(c1);
		        
		        PdfPCell c2 = new PdfPCell(new Phrase(""+CommonLogic.getTableName(r.getInt(5))));
		        c2.setFixedHeight(20);
		        c2.setBorder(Rectangle.TOP);
		        c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        //cell2.setColspan(2);
		        table.addCell(c2);
		        
		        PdfPCell c3 = new PdfPCell(new Phrase("              TaoTla",f2));
		        c3.setFixedHeight(20);
		        c3.setBorder(Rectangle.TOP);
		        c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        //cell3.setColspan(2);
		        table.addCell(c3);
		        
		        PdfPCell c4 = new PdfPCell(new Phrase(""+r.getFloat(1)));
		        c4.setFixedHeight(20);
		        c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        c4.setBorder(Rectangle.TOP);
		      //  cell4.setColspan(2);
		        table.addCell(c4);
       
		        
		        PdfPCell c5 = new PdfPCell(new Phrase("                        vaoTr :",f2));
		        c5.setFixedHeight(20);	
		        c5.setVerticalAlignment(Element.ALIGN_RIGHT);
		          c5.setBorder(Rectangle.NO_BORDER);
			       // cell.setColspan(2);
			        table.addCell(c5);
			        
			        PdfPCell c6 = new PdfPCell(new Phrase(""+CommonLogic.getWaitorName(r.getInt(4)),f2));
			        c6.setFixedHeight(20);
			        c6.setBorder(Rectangle.NO_BORDER);
			        c6.setVerticalAlignment(Element.ALIGN_LEFT);
			        //cell2.setColspan(2);
			        table.addCell(c6);
			        
			        PdfPCell c7 = new PdfPCell(new Phrase(""));
			        c7.setFixedHeight(20);
			        c7.setBorder(Rectangle.NO_BORDER);
			        c7.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        //cell3.setColspan(2);
			        table.addCell(c7);
			        
			        PdfPCell c8 = new PdfPCell(new Phrase(""));
			        c8.setFixedHeight(20);
			        c8.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        c8.setBorder(Rectangle.NO_BORDER);
			      //  cell4.setColspan(2);
			        table.addCell(c8);
	       

		       Font smallfont = new Font(FontFamily.HELVETICA, 7);
			        PdfPCell c9 = new PdfPCell(new Phrase("                              Thanks for visit.....HAVE A NICE DAY\n___________________________________________________\n                                 Developed by Ankush(8329394603)",smallfont));
			        c9.setFixedHeight(50);
			        c9.setVerticalAlignment(Element.ALIGN_TOP);
			        c9.setBorder(Rectangle.NO_BORDER);
			        c9.setColspan(4);
			        
			        table.addCell(c9);
	      
						
			  		 Rectangle pagesize = new Rectangle(216f, 400f+table.getTotalHeight());
			      //  Document document = new Document(pagesize, 3f, 3f, 10f, 180f);
			  		Document document = new Document(pagesize, 3f, 3f, 20f, 180f);
			        
			        
			        //PdfWriter writer =
			        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
			        document.open();
			      //  PdfContentByte cb = writer.getDirectContent();
			       // Font f=new Font(FontFamily.valueOf("Shivaji02"),50.0f,Font.UNDERLINE,BaseColor.RED);
			      //  Paragraph p = new Paragraph("googd");
			       // p.setFont(new Font());
			        Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 25.8f, Font.NORMAL, BaseColor.BLACK);
			       // BaseFont base = font.getBaseFont();
			        
			       Paragraph p = new Paragraph("             ha^Tola AMjanaI",font);
			       p.setLeading(2);
			       
			      
			       document.add(p);
			      //  document.add(new Paragraph("          ha^Tola AMjanaI",font));
			        Font f3 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15f, Font.NORMAL, BaseColor.BLACK);
			        Font f12 = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8f, Font.NORMAL, BaseColor.BLACK);
			        // document.add(new Paragraph("                    f^imalaI rosTa^rMT",f3));
			        
			        Paragraph p2 = new Paragraph("                     f^imalaI rosTa^rMT",f3);
			        p2.setLeading(15);
			        document.add(p2);
			        
			        
			        Font f4 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
			        
			        document.add(new Paragraph("                              mau.paosT.saaonaJ-.ta.naovaasaa.ija.Ahmadnagar 414105",f4));
			        document.add(new Paragraph("                              maaobaa[la naM.9860419230   8552803030",f4));
			        document.add(new Paragraph("                            GSTIN:- 27AGKPL2419AIZR",f12));
			        document.add(new Paragraph("______________________________"));
			        document.add(new Paragraph("                ibala naM."+billno+"  idnaaMk "+ConvertDate(r.getDate(7)),f3));
			        if(r.getInt(3)!=1)
			        {
			        document.add(new Paragraph("                 naava : "+CommonLogic.getCustomerNameUsingId(r.getInt(3)),f3));
			        }
			        else {document.add(new Paragraph("                 naava : ",f3));}
			        document.add(new Paragraph("______________________________"));
			       
			        
			     document.add(table);  
			        document.close();
			        //new PDFPrinter(new File("D:\\Hotel Software\\bill.pdf"));
			        new PrintingExample("D:\\Hotel Software\\bill.pdf");
			        // printBIll();
			        //PrintBill2();
					
		}catch(Exception e)
		{
			System.out.println("Error in billodf "+e.getMessage());
			return;
		}finally
		{
			//log4j.logger.org.apache.pdfbox.io.ScratchFileBuffer=WARN;
			
		}
	 
	}
	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		new BillPdf(20663);

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

	void printBIll()
	{
		FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(RESULT);
            } catch (FileNotFoundException ffne) 
        		{
              ffne.printStackTrace();
            }
            if (psStream == null) {
                return;
            }
        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);
     
       
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
         
        // this step is necessary because I have several printers configured
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++){
            System.out.println("service found: ");
            String svcName = services[i].toString();           
            if (svcName.contains("Microsoft XPS Document Writer")){
                myPrinter = services[i];
                System.out.println("my printer found: "+svcName);
                break;
            }
        }
         
        if (myPrinter != null) {            
            DocPrintJob job = myPrinter.createPrintJob();
            try {
            job.print(myDoc, aset);
             
            } catch (Exception pe) {pe.printStackTrace();}
        } else {
            System.out.println("no printer services found");
        }
   }			
	
	
	
	void PrintBill2()
	{
		try {
			System.out.println("Print bill 2");
		 DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		    PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		    patts.add(Sides.DUPLEX);
		    PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		    if (ps.length == 0) {
		        throw new IllegalStateException("No Printer found");
		    }
		    System.out.println("Available printers: " + Arrays.asList(ps));

		    PrintService myService = null;
		    for (PrintService printService : ps)
		    {
		        if (printService.getName().equals("Microsoft XPS Document Writer")) 
		        {
		            myService = printService;
		            break;
		        }
		    }
		    if (myService == null) {
		        throw new IllegalStateException("Printer not found");
		    }

		    FileInputStream fis = new FileInputStream(RESULT);
		    Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		    DocPrintJob printJob = myService.createPrintJob();
		    printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
		    fis.close();
		}catch(Exception e)
			{
		
			}
	}
	 public PrintService findPrintService(String printerName)
	    {
		 System.out.println("find priter service");
	        for (PrintService service : PrinterJob.lookupPrintServices())
	        {       
	        	if (service.getName().equalsIgnoreCase(printerName))
	                return service;
	        }

	        return null;
	    }

}

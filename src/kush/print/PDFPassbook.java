package kush.print;

import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Arrays;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonLogic;
import ankush.CommonMethods;

public class PDFPassbook 
{
	final static Logger logger = Logger.getLogger(PDFPassbook.class);
	public static final String RESULT = "D:\\Hotel Software\\Passbook\\bill.pdf";
	public static final String fontname ="C:\\Windows\\Fonts\\Kiran.ttf"; 
	int cno;
	public PDFPassbook(int cno)
	{
		this.cno=cno;
		 try
		 {
		 PropertyConfigurator.configure("Log.properties");
		 logger.debug("DEBUG MESSAGE: ");
		 BasicConfigurator.configure();      
		   						
			        
			        //Creating Table
			        Font f2 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16f, Font.NORMAL, BaseColor.BLACK);
			        PdfPTable table = new PdfPTable(6);
			        
			        // table.setTotalWidth(new float[]{10,30,30,50 });
			        //table.setLockedWidth(true);
			       
			        
			        PdfPCell cellSr = new PdfPCell(new Phrase("A.k`.",f2));
			        cellSr.setFixedHeight(20);
			        table.setWidths(new int[]{1, 2, 2,2,2,2});
			        cellSr.setBorder(Rectangle.BOTTOM);
			        //cellSr.setBorder(Rectangle.TOP);
			        //cell.setColspan(2);
			        table.addCell(cellSr);
			       
			        
			        
			        PdfPCell cellParticular = new PdfPCell(new Phrase("tapiSala",f2));
			        cellParticular.setFixedHeight(20);			        
			        cellParticular.setBorder(Rectangle.BOTTOM);
			        //cell.setColspan(2);
			        table.addCell(cellParticular);
			       
			        PdfPCell cellDate = new PdfPCell(new Phrase("taairKa",f2));
			        cellDate.setFixedHeight(20);			        
			        cellDate.setBorder(Rectangle.BOTTOM);
			        //cell.setColspan(2);
			        table.addCell(cellDate);
			        
			        PdfPCell cellCredit = new PdfPCell(new Phrase("{QaarI",f2));
			        cellCredit.setFixedHeight(20);			        
			        cellCredit.setBorder(Rectangle.BOTTOM);
			        //cell.setColspan(2);
			        table.addCell(cellCredit);
			        
			        PdfPCell cellCash = new PdfPCell(new Phrase("k^Sa jamaa",f2));
			        cellCash.setFixedHeight(20);			        
			        cellCash.setBorder(Rectangle.BOTTOM);
			        //cell.setColspan(2);
			        table.addCell(cellCash);
			      
			        PdfPCell cellRemain = new PdfPCell(new Phrase("baaik",f2));
			        cellRemain.setFixedHeight(20);			        
			        cellRemain.setBorder(Rectangle.BOTTOM);
			        table.addCell(cellRemain);
			        //cell.setColspan(2);
			      //Set Data 
			        try {
						
						@SuppressWarnings("unused")
						String htmstart = "<html><font face=\"kiran\" size=\"5\">";
						@SuppressWarnings("unused")
						String mode,credit="",cash="";
						int i=0;
						float baki=0.0f,jama=0.0f,udhar=0.0f;
						ResultSet rs = CommonMethods.selectQuery("select * from Passbook where CID="+cno);
					while(rs.next())
					{
						System.out.println(rs);
						mode = rs.getString(4);
						if(mode.equals("Cash"))
						{
							jama=rs.getFloat(3);
							cash="jamaa paavataI k`  "+rs.getInt(5);
							credit="";
							udhar=0.0f;
							baki=baki-jama;
						}
						if(mode.equals("Credit"))
						{
							udhar=rs.getFloat(3);
							cash = "ibala naM "+rs.getInt(5);
							jama=0.0f;
							baki=baki+udhar;
							//cash="";
						}
						
						PdfPCell cell1 = new PdfPCell(new Phrase(""+(++i),f2));
						cell1.setFixedHeight(20);
						cell1.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell1);
				        
				        PdfPCell cell2 = new PdfPCell(new Phrase(""+cash,f2));
				        cell2.setFixedHeight(20);			        
				        cell2.setBorder(Rectangle.NO_BORDER);
				        table.addCell(cell2);
				        
				        PdfPCell cell3 = new PdfPCell(new Phrase(""+rs.getDate(6)));
				        cell3.setFixedHeight(20);			        
				        cell3.setBorder(Rectangle.NO_BORDER);
				        table.addCell(cell3);
				        
				        PdfPCell cell4 = new PdfPCell(new Phrase(""+udhar,f2));
				        cell4.setFixedHeight(20);			        
				        cell4.setBorder(Rectangle.NO_BORDER);
				        table.addCell(cell4);
				        
				        PdfPCell cell5 = new PdfPCell(new Phrase(""+jama,f2));
				        cell5.setFixedHeight(20);			        
				        cell5.setBorder(Rectangle.NO_BORDER);
				        table.addCell(cell5);
				        
				        PdfPCell cell6 = new PdfPCell(new Phrase(""+baki,f2));
				        cell6.setFixedHeight(20);			        
				        cell6.setBorder(Rectangle.NO_BORDER);
				        table.addCell(cell6);
						
						//tmodel.addRow(new Object[] {++i,htmstart+cash,rs.getDate(6),udhar,jama,baki});
						//tmodel.addRow(new Object[] {++i,"jamaa paavataI k`"+rs.getInt(5),rs.getDate(6),"",rs.getFloat(3)});
						//tmodel.addRow(new Object[] {++i});
					}
					} catch (Exception e)
					{
						System.out.println("Error in getAllData "+e.getMessage());
						// TODO: handle exception
					}
			        
			        
			        
			  		// Rectangle pagesize = new Rectangle(716f, 800f);
			        Rectangle pagesize = new Rectangle(PageSize.A4.getWidth(),PageSize.A4.getHeight());  
			        //  Document document = new Document(pagesize, 3f, 3f, 10f, 180f);
				  		Document document = new Document(pagesize, 3f, 3f, 20f, 180f);
				        
				        
				    
				        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
				        document.open();
				        Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 25.8f, Font.NORMAL, BaseColor.BLACK);
				       Paragraph p = new Paragraph("                                               ha^Tola AMjanaI",font);
				       p.setLeading(2);
				       
				      
				       document.add(p);
				    
				        Font f3 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15f, Font.NORMAL, BaseColor.BLACK);
				        Font f12 = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8f, Font.NORMAL, BaseColor.BLACK);
				        
				        Paragraph p2 = new Paragraph("                                                                                     f^imalaI rosTa^rMT",f3);
				        p2.setLeading(15);
				        document.add(p2);
				        
				        
				        Font f4 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
				        
				        Paragraph p3 = new Paragraph("                                                                                                                 mau.paosT.saaonaJ-.ta.naovaasaa.ija.Ahmadnagar 414105",f4);
				       // p3.setLeading(1);
				       
				        document.add(p3);
				        Paragraph p4 = new Paragraph("                                                                                                                   maaobaa[la naM.9860419230   8552803030",f4);
				       // p4.setLeading(1);
				        document.add(p4);
				        Paragraph p5 = new Paragraph("                                                                                                                    GSTIN:- 27AGKPL2419AIZR",f12);
				        document.add(p5);
				        Paragraph p6 = new Paragraph("                  naava : "+CommonLogic.getCustomerNameUsingId(cno),f3);
				        p6.setLeading(15);
				        document.add(p6);
				        Paragraph p7 = new Paragraph("                  pattaa : "+CommonLogic.getCustomerAddress2(cno),f3);
				        p7.setLeading(15);
				        document.add(p7);
				        Paragraph p8 = new Paragraph("                  maaobaa[la naM.: "+CommonLogic.getCustomerContact(cno),f3);
				        p8.setLeading(15);
				        document.add(p8);
				        Paragraph p9 = new Paragraph("                  _______________________________________________________________________");
				        p9.setLeading(2);
				        document.add(p9);
			        
			        
			        document.add(table);
			        
			        document.close();
			        
					
		}catch(Exception e)
		{
			System.out.println("Error in passbookPdf "+e.getMessage());
			logger.error("Error in passbookpdf"+e.getMessage());
			return;
		}
	 
	}
	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		new PDFPassbook(4);

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
	        for (PrintService service : PrinterJob.lookupPrintServices())
	        {       
	        	if (service.getName().equalsIgnoreCase(printerName))
	                return service;
	        }

	        return null;
	    }

}

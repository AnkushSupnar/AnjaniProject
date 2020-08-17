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

public class QuotationPDF 
{

	public static final String RESULT = "D:\\Hotel Software\\qt.pdf";
	//public static final String fontname ="C:\\Windows\\Fonts\\Kiran.ttf";
	public static final String fontname =CommonMethods.fontName;
	int billno;
	String TableName;
	public QuotationPDF(String TableName)
	{
		// this.billno=billno;
		this.TableName=TableName;
		try {
			System.out.println("Creating ");
			 Font f2 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14f, Font.NORMAL, BaseColor.BLACK);
			 //Bill Table
	        PdfPTable table = new PdfPTable(2);
	        //table.setTotalWidth(new float[]{150, 120,120,120 });
	        //table.setTotalWidth(new float[]{100,30,30,50 });
	        table.setTotalWidth(new float[]{100,30 });
	        table.setLockedWidth(true);
	       // PdfContentByte cb = writer.getDirectContent();
	        PdfPCell cell = new PdfPCell(new Phrase("      tapiSala   ",f2));
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
	        
	       
	        int TableNo = CommonLogic.getTableId(TableName);
	        String query = "select Itemname,printqty,Waitorid from TempTransaction where tableno="+TableNo+" and Printqty>0";
	        ResultSet rs = CommonMethods.selectQuery(query);
	        String wname = null;
			while(rs.next())
			{
				 PdfPCell c1 = new PdfPCell(new Phrase("      "+rs.getString(1),f2));
				 c1.setFixedHeight(20);			        
				 c1.setBorder(Rectangle.NO_BORDER);
			        //cell.setColspan(2);
			        table.addCell(c1);
			        
			        PdfPCell c2 = new PdfPCell(new Phrase(""+rs.getFloat(2),f2));
			        c2.setFixedHeight(20);
			       c2.setBorder(Rectangle.NO_BORDER);
			        //cell2.setColspan(2);
			        table.addCell(c2);
			        
			        wname = CommonLogic.getWaitorName(rs.getInt(3));       
			}
			
	        rs.close();
	     
	        
	           
		        PdfPCell c2 = new PdfPCell(new Phrase("      TableNo. "+TableName));
		        c2.setFixedHeight(20);
		        c2.setBorder(Rectangle.TOP);
		        c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        //cell2.setColspan(2);
		        table.addCell(c2);
		        
		        
		        PdfPCell c5 = new PdfPCell(new Phrase("                     vaoTr :",f2));
		        c5.setFixedHeight(20);	
		        c5.setVerticalAlignment(Element.ALIGN_RIGHT);
		          c5.setBorder(Rectangle.NO_BORDER);
			       // cell.setColspan(2);
			        table.addCell(c5);
			        
			        PdfPCell c6 = new PdfPCell(new Phrase("      vaoTr "+wname,f2));
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
	       

		           			
			  		 Rectangle pagesize = new Rectangle(216f, 400f+table.getTotalHeight());
			   
			  		Document document = new Document(pagesize, 3f, 3f, 20f, 180f);
			        
			        
			        //PdfWriter writer =
			        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
			        document.open();
			        Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 25.8f, Font.NORMAL, BaseColor.BLACK);
			        Font font10 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20f, Font.NORMAL, BaseColor.BLACK);
			        Paragraph p1 = new Paragraph("                  ha^Tola AMjanaI",font10);
			      p1.setLeading(2);
			      document.add(p1);
			        Paragraph p = new Paragraph("             "+getOrder(TableName),font);
			       p.setLeading(21);
			       
			      
			       document.add(p);
			    
			      //  Font f3 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 17f, Font.NORMAL, BaseColor.BLACK);
			     //   Font f12 = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 8f, Font.NORMAL, BaseColor.BLACK);
			    
			        
			        
			        
			        
			        
			        
			       /* Paragraph p2 = new Paragraph("                     ",f3);
			        p2.setLeading(15);
			        document.add(p2);
			        */
			        
			       // Font f4 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
			        
			      
			        
			     document.add(table);  
			        document.close();
			        //new PDFPrinter(new File("D:\\Hotel Software\\bill.pdf"));
			        new PrintingExample("D:\\Hotel Software\\qt.pdf");
			        // printBIll();
			        //PrintBill2();
					
		}catch(Exception e)
		{
			System.out.println("Error in billodf "+e.getMessage());
			return;
		}
	 
	}
	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		 BasicConfigurator.configure();
		new QuotationPDF("A8");

	}
	@SuppressWarnings("deprecation")
	String ConvertDate(java.sql.Date date)
	   {
		 
		   int d = date.getDate();
		   int m = date.getMonth();
		   int y = date.getYear();
		   
		   return ""+d+"."+m+"."+(y+1900); 
		   
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

	 public String  getOrder(String Table)
	 {
		 int f=0;
		 try {
			 for(int i=0;i<Table.length();i++)
			 {
				 if(Table.charAt(i)=='P'||Table.charAt(i)=='p')
				 {
					f=1; 
				 }
				
			 }
			 if(f==1)
			 {
				 return "paasa-la Aa^D-r"; 
			 }
			 else
			 {
				 return "   Aa^D-r";
			 }
			 //
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return "";
	 }
}

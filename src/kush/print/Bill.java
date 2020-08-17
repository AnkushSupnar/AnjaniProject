/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kush.print;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;

import ankush.CommonLogic;
import ankush.CommonMethods;

import java.awt.FontMetrics;

/**
 *
 * @author mic
 */
public class Bill extends javax.swing.JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int bill;
    /**
     * Creates new form bill_form
     */
    public Bill(int bill)
    {
    	this.bill=bill;
       // initComponents();
    	Print123();
    }
  
    public PageFormat getPageFormat(PrinterJob pj)
    {
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}






public class BillPrintable implements Printable 
{    
  @SuppressWarnings("unused")
public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
 
      int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Shivaji02",Font.BOLD,7));
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
             int billno;
             String date;
String q = "select BillAmt,Discount, CustomerID, WaitorId,TableNo,Userid,BillDate,Paymode from Bill where Billno="+bill;
ResultSet r = CommonMethods.selectQuery(q);          
                String query = "select Itemname, qty,Rate,amt from Transaction where bill="+bill;
 //String query = "select Itemname,printqty,rate,amt from TempTransaction where tableno="+tableno+" and Printqty>0";
                ResultSet rs = CommonMethods.selectQuery(query);
             while(r.next())
             {    
            
            ///////////////// Product price Get ///////////
                //int pp1a=Integer.valueOf(pp1.getText());
                int pp1a=1;
                //int pp2a=Integer.valueOf(pp2.getText());
                int pp2a = 2;
                //int pp3a=Integer.valueOf(pp3.getText());
                int pp3a=3;
                //int pp4a=Integer.valueOf(pp4.getText());
                int pp4a=4;
                int sum=pp1a+pp2a+pp3a+pp4a;
            ///////////////// Product price Get ///////////
               
                String space=" ";
             g2d.setFont(new Font("Shivaji02",Font.PLAIN,9));
           // g2d.drawString("-------------------------------------",12,y);y+=yShift;
            //g2d.drawString("      Restaurant Bill Receipt        ",12,y);y+=yShift;
            g2d.drawString("      Hotel Anjani                   ",12,y);y+=yShift;
            g2d.setFont(new Font("Shivaji02",Font.PLAIN,5));
            g2d.drawString("       Family Restaurant",12,y);y+=yShift;
           // g2d.drawString("   Family Restaurant",12,y);y+=headerRectHeight;      
           g2d.drawString("    At/Post- Sonai,Tal-Newasa, Dist-Ahmednagar 414105",10,y);y+=yShift;
           g2d.drawString("_________________________________________________",12,y);y+=yShift;
           g2d.setFont(new Font("Shivaji02",Font.PLAIN,10));
           g2d.drawString("Bill No:"+bill+"  Date:-"+r.getString(7),12,y);y+=yShift;
            //g2d.drawString(" Food Name                 T.Price   ",10,y);y+=yShift;
            g2d.drawString("maalaacaa tpSaIla       naga   dr    r@kma",10,y);y+=yShift;
            g2d.drawString("______________________________________",10,y);y+=headerRectHeight;
            while(rs.next())
            {
            	//String c = rs.getString(1);
            	String c = Convertname(rs.getString(1)); 
            	if(c.length()<=30)
            	{
            		//c="";
            		for(int i=0;i<=30;i++)
            		{
            			//if(i==c.length())
            			//{
            			//System.out.println("in c "+c.charAt(i-1));
            			//}
            		}
            	String s=" "+c+"  "+rs.getInt(2)+"   "+rs.getFloat(3)+"   "+rs.getFloat(4)+"     ";
            	g2d.drawString(s,10,y);y+=yShift;
            	}
            //	System.out.println(c);
           // 	System.out.println("Converted lenght"+c.length());
             //g2d.drawString(" "+c+"  "+rs.getInt(2)+"   "+rs.getFloat(3)+"   "+rs.getFloat(4)+"     ",10,y);y+=yShift;
            	//System.out.println(" "+c+"  "+rs.getInt(2)+"   "+rs.getFloat(3)+"   "+rs.getFloat(4)+"     ");
            	 //g2d.drawString(" "+c+"  "+rs.getInt(2)+"   "+rs.getFloat(3)+"   "+rs.getFloat(4)+"     ",10,y);y+=yShift;
            }
            rs.close();
            
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("Mode:-"+Convertname(r.getString(8))+"     Total:"+r.getFloat(1)+"",10,y);y+=yShift;
            g2d.drawString("Waitor:-"+Convertname(CommonLogic.getWaitorName(r.getInt(4))),10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("          Free Home Delivery         ",10,y);y+=yShift;
         //   g2d.drawString("             03111111111             ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
           }
             r.close();
        }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }

public void Print123()
{
    PrinterJob pj = PrinterJob.getPrinterJob();        
    pj.setPrintable(new BillPrintable(),getPageFormat(pj));
    try {
         pj.print();
      
    }
     catch (PrinterException ex)
    {
             ex.printStackTrace();
    }	
}
  
    
        public static void main(String args[])
         {
        	CommonMethods.openConnection();
        	new Bill(1);
        }
        String Convertname(String name)
        {
        	String Converted="";
        	int f = name.length();
        	//System.out.println("Getted length "+name+" "+name.length());
        	if(f>25)
        	{
        	 //System.out.println("Found More than 30==>"+name);
        	}
        	for(int i=0;i<25;i++)
        	{
        		if(i< f)
        		{
        			//System.out.print("i="+i+" "+name.charAt(i));
        			Converted = Converted+name.charAt(i);
        		
        		}
        		else
        		{
        			Converted = Converted+".";
        			f++;
        			//System.out.print("big i="+i+" "+Converted.charAt(i));
        		}
        		if(i>=f)
        		{
        			
        			//System.out.println("Reach the max"+Converted.length());
        		}
        		
        		
        		
        		//if(i>name.length())
        		//{
        			//Converted = Converted+".";
        		//}
        	}
        	//System.out.println("Converted Size "+Converted.length() );
        	//System.out.println("Get the lenght "+Converted.length()+"String is"+Converted+"|");
        	//System.out.println("Converted String "+Converted);
        	return Converted;
        }
}
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

import ankush.CommonMethods;

import java.awt.FontMetrics;

/**
 *
 * @author mic
 */
public class PrintQuatation extends javax.swing.JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int table;
    /**
     * Creates new form bill_form
     */
    public PrintQuatation(int table)
    {
    	this.table=table;
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
      int tableno=table;
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
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
            
            ///////////////// Product names Get ///////////
                //String  pn1a=pn1.getText();
                String pn1a = "Name1";
                //String pn2a=pn2.getText();
                String pn2a = "Name2";
               // String pn3a=pn3.getText();
                String pn3a = "Name3";
                //String pn4a=pn4.getText();
                String pn4a = "Name4";
            ///////////////// Product names Get ///////////
      //getValues from TempTransaction
                String query = "select Itemname,printqty,rate,amt from TempTransaction where tableno=1 and Printqty>0";
 //String query = "select Itemname,printqty,rate,amt from TempTransaction where tableno="+tableno+" and Printqty>0";
                ResultSet rs = CommonMethods.selectQuery(query);
             
                	
                
            
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
                int sr=1;
                String space=" ";
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
           // g2d.drawString("-------------------------------------",12,y);y+=yShift;
            //g2d.drawString("      Restaurant Bill Receipt        ",12,y);y+=yShift;
            g2d.drawString("            Hotel Anjani                   ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
      
           // g2d.drawString("-------------------------------------",10,y);y+=yShift;
            //g2d.drawString(" Food Name                 T.Price   ",10,y);y+=yShift;
            g2d.drawString("No  Food Name    qty   rate   T.Price",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            while(rs.next())
            {
             g2d.drawString((sr++)+space+" "+Convertname(rs.getString(1))+"  "+rs.getInt(2)+"   "+rs.getFloat(3)+"   "+rs.getFloat(4)+"     ",10,y);y+=yShift;
            }
            rs.close();
           /* g2d.drawString((sr++)+space+" "+pn1a+"     "+pp1a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn2a+"     "+pp2a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn3a+"     "+pp3a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn4a+"     "+pp4a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn4a+"     "+pp4a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn4a+"     "+pp4a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn4a+"     "+pp4a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn4a+"     "+pp4a+"  ",10,y);y+=yShift;
            g2d.drawString((sr++)+space+" "+pn4a+"     "+pp4a+"  ",10,y);y+=yShift;*/
            if(sr>9)
            {
            	space="";
            }
            
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount: "+sum+"               ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("          Free Home Delivery         ",10,y);y+=yShift;
            g2d.drawString("             03111111111             ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

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
        	new PrintQuatation(1);
        }
        String Convertname(String name)
        {
        	String Converted="";
        	for(int i=0;i<15;i++)
        	{
        		if(i< name.length())
        		{
        			Converted = Converted+name.charAt(i);
        		}
        		if(i>name.length())
        		{
        			Converted = Converted+" ";
        		}
        	}
        	return Converted;
        }
}
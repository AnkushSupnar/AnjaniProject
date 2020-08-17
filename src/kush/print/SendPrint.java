package kush.print;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class SendPrint {

	public SendPrint(String filePath) {
		try
		{
			PDDocument document = PDDocument.load(new File(filePath));

			
			//PrintService myPrintService = findPrintService("Microsoft XPS Document Writer");
	     //PrintService myPrintService = findPrintService("Star BSC10");
			PrintService[] printerList = PrintServiceLookup.lookupPrintServices(null, null);
			for(PrintService printer:printerList)
			{
				System.out.println(printer.getName());
			}					
			
	        PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPageable(new PDFPageable(document));
	        //job.setPrintService(myPrintService);
	        //job.setPrintService(myPrintService);
	        job.print();
	        document.close();
		}catch(Exception e)
		{
			System.out.println();
		}
	}
	public static void main(String[] args) {
		new SendPrint("D:\\Hotel Software\\bill.pdf");
	}

}

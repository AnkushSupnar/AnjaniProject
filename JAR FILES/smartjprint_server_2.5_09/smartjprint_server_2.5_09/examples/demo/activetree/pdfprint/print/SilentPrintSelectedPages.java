/**
 * Copyright © Activetree, Inc.  All rights reserved.
 * http://www.activetree.com
 *
 * This is a sample program demonstrating the usage of the relevant APIs and carries no warranty.
 */
package demo.activetree.pdfprint.print;

import com.activetree.common.print.SilentPrint;
import com.activetree.pdfprint.SilentPrintPdf;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 * This document processor program is for silently printing
 * a PDF document located anywhere in the world and then print only
 * the selected pages from the document (or list of documents).
 * All you need to do is to attach a DocListener implementation and
 * start receiving the events at run time. Based on the events you receive
 * make a decison what you need to filter out or simply track it for your
 * statistical and other purpose.
 * <p>
 * This is how you add a DocListener interface to receive events.
 *
 * <code>
 * <pre>
 * ...
 * String docListener = "demo.activetree.pdfprint.print.SelectPagesDocListener";
 * silentPrint.setAttribute(SilentPrint.DOC_LISTENER, docListener);
 * ...
 * </pre>
 * </code>
 *
 * <p>
 * Look at the SelectPagesDocListener DocListener sample. 
 * </p>
 * </p>
 */
public class SilentPrintSelectedPages {
  public static final String usage =
      "****************************************************************************" +
      "\nArguments: docList [docPassword] [urlAuthId] [urlAuthPassword]" +
      "\nARGUMENTS (arguments withing [ and ] char are optional):" +
      "\ndocList - '[' and ']' char enclosed list of documents; e.g. [doc-1][doc-2]...[doc-N]." +
      "\ndocPassword - (Optional) password for the document." +
      "\nurlAuthId - (Optional) URL access (server directory) ID." +
      "\nurlAuthPassword - (Optional) URL access password." +
      "\n(i)For Wondows local file URL: \"[c:/pdf/sample1.pdf]\" (or other appropriate syntax for the OS)  " +
      "\n(ii)Web URL: \"[http://www.activetree.com/jprint/demo/sample_pdf/sample1.pdf]\"" +
      "\nFor more than one file at a time:" +
      "\n[Here is a complete example of printing two PDF files (one local file and one web URL)]" +
      "\nSilentPrintSelectedPages.bat [file:/c:/temp/sample1.pdf][http://www.activetree.com/jprint/demo/sample_pdf/sample2.pdf]" +
      "\n[Here is how to pass parameters for the PDF dynamically generated from an URL.]" +
      "\nSilentPrintSelectedPages.bat [c:/temp/sample1.pdf][http://host:port/pdfgen.jsp?param1=value1&param2=value2&param3=value3]" +
      "\n[This example below using document password as well as URL auth ID/Password for the web docs.]" +
      "\nSilentPrintSelectedPages.bat [file:/c:/temp/sample1.pdf][http://www.activetree.com/jprint/demo/sample_pdf/sample2.pdf] docPassword urlAuthId urlAuthPassword" +
      "\n*****************************************************************************";


  public static void main(String[] args) {
    System.out.println(usage);

    String docList = null;
		if (args != null && args.length >= 1) {
      docList = args[0];
    }
		else {
      System.out.println("--- Insufficient arguments ---");
			System.exit(0);
		}
    //optional args
    String docPassword = null;
    if (args.length >= 2) {
      docPassword = args[1];
    }
    String urlAuthId = null;
    String urlAuthPassword = null;
    if (args.length >= 4) {
      urlAuthId = args[2];
      urlAuthPassword = args[3];
    }

    //need a DocListener to filter out those undesireable pages
    //see next -- add this DocListener to receive event where one can filter out the pages.
    String docListener = "demo.activetree.pdfprint.print.SelectPagesDocListener";

    //Printer
    SilentPrint silentPrint = new SilentPrintPdf();
    //default media size
    String paperSize = "(0, 0, 0, 0, 612, 792)";
    silentPrint.setAttribute(SilentPrint.PAPER, paperSize);
    //printer to print
    PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
    if (defaultPrintService == null) {
      System.out.println("No printers found.");
      System.exit(1);
    }
    String printerName = defaultPrintService.getName();
    silentPrint.setAttribute(SilentPrint.PRINTER_NAME, printerName);
    //auto rotate and center
    silentPrint.setAttribute(SilentPrint.AUTO_ROTATE_AND_CENTER, Boolean.TRUE);
    //page scaling
    silentPrint.setAttribute(SilentPrint.PAGE_SCALING, SilentPrint.FIT_TO_PRINTABLE_AREA);
    //collate
    silentPrint.setAttribute(SilentPrint.COLLATE_COPIES, Boolean.TRUE);
    //copies defaut 1; can make it to N copies
    silentPrint.setAttribute(SilentPrint.COPIES, new Integer(1));
    //debug it
    //silentPrint.setAttribute(SilentPrint.DEBUG, Boolean.TRUE);
    //job name
    silentPrint.setAttribute(SilentPrint.JOB_NAME, "PDFSilentPrint");
    //document
    silentPrint.setAttribute(SilentPrint.DOC_LIST, docList);
    //Add a docListener
    //must have a default constructor for this class.
    silentPrint.setAttribute(SilentPrint.DOC_LISTENER, docListener); // "demo.activetree.pdfprint.PdfDocListener");
    //doc password protected if any
    silentPrint.setAttribute(SilentPrint.PASSWORD, docPassword);
    //url protection if any
    silentPrint.setAttribute(SilentPrint.URL_AUTH_ID, urlAuthId);
    silentPrint.setAttribute(SilentPrint.URL_AUTH_PASSWORD, urlAuthPassword);
    try {
      silentPrint.start();
    }catch(Throwable t) {
      t.printStackTrace();
    }
    System.out.println("Printing launched, please wait...");
  }

}

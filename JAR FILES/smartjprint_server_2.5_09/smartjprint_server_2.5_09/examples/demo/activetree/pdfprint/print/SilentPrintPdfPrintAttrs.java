package demo.activetree.pdfprint.print; /**
 * Copyright © Activetree, Inc.  All rights reserved.
 * http://www.activetree.com
 *
 * This is a sample program demonstrating the usage of the relevant APIs and carries no warranty.
 */
import com.activetree.common.print.SilentPrint;
import com.activetree.pdfprint.SilentPrintPdf;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.print.attribute.standard.RequestingUserName;
import java.util.Locale;

/**
 * This is yet another example demonstrating how to set the silent print
 * parameters for printing of your PDF documents. It shows how
 * to set custom paper size and few other parameters. See other examples
 * in the downloaded archive. 
 */
public class SilentPrintPdfPrintAttrs {
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
      "\nSilentPrintPdfPrintAttrs.bat [file:/c:/temp/sample1.pdf][http://www.activetree.com/jprint/demo/sample_pdf/sample2.pdf]" +
      "\n[Here is how to pass parameters for the PDF dynamically generated from an URL.]" +
      "\nSilentPrintPdfPrintAttrs.bat [c:/temp/sample1.pdf][http://host:port/pdfgen.jsp?param1=value1&param2=value2&param3=value3]" +
      "\n[This example below using document password as well as URL auth ID/Password for the web docs.]" +
      "\nSilentPrintPdfPrintAttrs.bat [file:/c:/temp/sample1.pdf][http://www.activetree.com/jprint/demo/sample_pdf/sample2.pdf] docPassword urlAuthId urlAuthPassword" +
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

    SilentPrint silentPrint = new SilentPrintPdf();

    //Printing attribute set - subjected to support from JRE
    HashPrintRequestAttributeSet pSet = new HashPrintRequestAttributeSet();
    //TODO -- set your printing attributes based on JPS APIs.
    //Example:
    //pSet.add(javax.print.attribute.standard.MediaTray.ENVELOPE);
    //...
    //pSet.add(new JobOriginatingUserName("loggedInUserID", Locale.getDefault()));
    //pSet.add(new RequestingUserName("loggedInUserID", Locale.getDefault()));
    silentPrint.setAttribute(SilentPrint.PRINT_REQUEST_ATTRIBUTE_SET, pSet);

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
    //auto-match paper based on PDF page size.
    silentPrint.setAttribute(SilentPrint.AUTO_MATCH_PAPER, Boolean.FALSE);
    //collate
    silentPrint.setAttribute(SilentPrint.COLLATE_COPIES, Boolean.TRUE);
    //copies defaut 1; can make it to N copies
    silentPrint.setAttribute(SilentPrint.COPIES, new Integer(1));
    //print all docs as one print job
    //silentPrint.setAttribute(SilentPrint.SINGLE_PRINT_JOB, Boolean.TRUE);
    //debug it
    //silentPrint.setAttribute(SilentPrint.DEBUG, Boolean.TRUE);
    //job name
    silentPrint.setAttribute(SilentPrint.JOB_NAME, "CloudDoc-PrintJob");
    //document
    silentPrint.setAttribute(SilentPrint.DOC_LIST, docList);
    //Add a docListener
    //must have a default constructor for this class.
    silentPrint.setAttribute(SilentPrint.DOC_LISTENER, "demo.activetree.pdfprint.PdfDocListener");
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

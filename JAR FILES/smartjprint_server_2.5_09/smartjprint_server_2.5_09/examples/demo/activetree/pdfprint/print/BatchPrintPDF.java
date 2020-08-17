/**
 * Copyright © Activetree, Inc.  All rights reserved.
 * http://www.activetree.com
 *
 * This is a sample program demonstrating the usage of the relevant APIs and carries no warranty.
 */
package demo.activetree.pdfprint.print;

import com.activetree.common.print.SilentPrint;
import com.activetree.common.media.AtMediaSizeObject;
import com.activetree.pdfprint.SilentPrintPdf;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.standard.MediaSizeName;
import java.io.File;
import java.net.URL;

/**
 * Silent print PDF located any where in the world as a batch. This demo
 * shows you can simply specify a directory location where your PDFs are located
 * so that when it runs it can pick up all the PDFs from the directory and
 * silently print to a printer of your choice. You may choose to print them all
 * as as single print job or each as an individual print job depends on your
 * parameter setting in then setAttribute().
 */
public class BatchPrintPDF {
  public static void main(String[] args) throws Throwable {
    System.out.println(
        "Arguments: pdfDirNameForPdfFiles [docPassword] [urlAuthId] [urlAuthPassword] " +
            "\nWHERE:" +
            "\npdfDirNameForPdfFiles - is the directory where all files are considered to be PDFs" +
            "\nOPTIONAL arguments:" +
            "\ndocPassword - (Optional) password for the document." +
            "\nurlAuthId - (Optional) URL access (server directory) ID." +
            "\nurlAuthPassword - (Optional) URL access password." +
            "\nExample: BatchPrintPDF.bat c:/test/pdfs/ " +
            "\n[Above examples shows all PDFs in the dir. are unprotected.]" +
            "\nExample: BatchPrintPDF.bat c:/test/pdfs/ myDocPassword" +
            "\n[This example is using a common password as additional argument for documents (assumtion is that all PDFs are of same password protected).]" +
            "\nExample: BatchPrintPDF.bat c:/temp/pdfs/ myDocPassword myUrlAuthId myUrlAuthPassword" +
            "[This eaxmple used a common password, and URL protection ID/Password (assuming all docs are located under the same web dir).]" +
            "");

    String dir = null;
    if (args != null && args.length >= 1) {
      dir = args[0];
    }
    else {
      System.out.println("--- Insufficient arguments ---");
      System.exit(1);
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

    //Printer
    SilentPrint silentPrint = new SilentPrintPdf();
    //default media size
    String paperSize = new AtMediaSizeObject(MediaSizeName.NA_LETTER).getMediaSizePixelString(); // or in this format "(0, 0, 0, 0, 612, 792)"; => (top. left, bot, right, width, height)
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
    //print all docs as one print job
    //    silentPrint.setAttribute(SilentPrint.SINGLE_PRINT_JOB, Boolean.TRUE);
    //debug it
    //    silentPrint.setAttribute(SilentPrint.DEBUG, Boolean.TRUE);
    //job name
    silentPrint.setAttribute(SilentPrint.JOB_NAME, "BatchPdfPrint");
    //document
    String docList = createDocListFromDirectory(dir);
    silentPrint.setAttribute(SilentPrint.DOC_LIST, docList);
    System.out.println("DOC_LIST=\"" + docList +"\"");
    if (docList == null || docList.length() == 0) {
      System.out.println("No documents found.");
      System.exit(2);
    }
    //Add a doc listener - for error processing if any
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
  }

  public static String createDocListFromDirectory(String dir) {
    File pdfDirectory = new File(dir);
    if (!pdfDirectory.isDirectory()) {
      System.out.println(dir + "- not a directory.\n" +
          "Enter a directory name where all PDF files are located.");
      System.exit(2);
    }
    File files[] = pdfDirectory.listFiles();

    StringBuffer buf = new StringBuffer();
    for (int i=0; i < files.length; i++) {
      File aPdfFile = files[i];
      if (aPdfFile.isDirectory()) {
        continue; //Not a file.
      }
      try {
        URL url = aPdfFile.toURL();
        String fileUrl = url.toString();
        buf.append("[" + fileUrl +"]");
      }catch(Throwable t) {
        //ok - for this file only.
        System.out.println(aPdfFile + " - " + t.getMessage());
      }
    }
    String s = buf.toString();
    s = s.trim();
    return s;
  }
}



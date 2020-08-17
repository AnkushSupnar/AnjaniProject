/**
 * Copyright © by ActiveTree Inc., California, USA.
 * All rights reserved.
 */
package demo.activetree.pdfprint.print;

import com.activetree.common.doc.DocUtil;
import com.activetree.common.doc.DocListener;
import com.activetree.common.media.AtMediaSizeObject;
import com.activetree.common.print.SilentPrint;
import com.activetree.common.web.WebContext;
import com.activetree.common.web.WebInterface;
import com.activetree.common.web.WebDocAttributeParser;
import com.activetree.common.attr.reader.CommonAttributeReader;
import com.activetree.pdfprint.SilentPrintPdf;

import javax.print.attribute.standard.MediaSizeName;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.Vector;
import java.util.StringTokenizer;

/**
 * <p>
 * From your Java program track silent printing of PDF while printing.
 * </p>
 * <p>
 * Silent print PDF - this sample demonstrates how the retrieve the PDF DocListener.
 * You may write your own doc listener or use the existing PdfDocListener comes as
 * an example in the downloaded archive.
 * </p>
 * <p>
 * While the program is running in a thread a lot of time you may directly look at the
 * WebContext where runtime status is retrieved from and persisted. It is suggested not
 * to modify the values but may look at it for tracking and other purpose. We strongly
 * recommend to use DocListener for all tracking and filtering purpose.
 * </p>
 */
public class SilentPrintContextHandlerDemo {
  public static final String usage = "Arguments: docList [docPassword] [urlAuthId] [urlAuthPassword]" +
      "\nARGUMENTS (arguments withing [ and ] char are optional):" +
      "\ndocList - '[' and ']' char enclised list of documents; e.g. [doc-1][doc-2]...[doc-N]." +
      "\nOPTIONAL arguments:" +
      "\ndocPassword - (Optional) password for the document." +
      "\nurlAuthId - (Optional) URL access (server directory) ID." +
      "\nurlAuthPassword - (Optional) URL access password." +
      "\nEXAMPLES:" +
      "\nSilentPrintBytesDemo.bat [c:/test/pdfs/sample1.pdf][http://localhost:8080/sjPDFbrowser_2.4_trial/demo/sample_pdf/sample4.pdf] " +
      "\n[Example above shows none are are password or URL protected.]" +
      "\nSilentPrintBytesDemo.bat [c:/test/pdfs/sample1.pdf][http://localhost:8080/sjPDFbrowser_2.4_trial/demo/sample_pdf/sample4.pdf] myDocPassword" +
      "\n[Example above is using a password for those password protected docs assuming password is same for all protected docs.]" +
      "\nSilentPrintBytesDemo.bat [c:/test/pdfs/sample1.pdf][http://localhost:8080/sjPDFbrowser_2.4_trial/demo/sample_pdf/sample4.pdf] myDocPassword myUrlAuthId myUrlAuthPassword" +
      "\n[Example here shows some or all of the documents might be combination of doc password and URL protected.]" +
      "";
  public static void main(String[] args) {
    System.out.println(usage);
    String docListStr = null;
    if (args != null && args.length >= 1) {
      docListStr = args[0];
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

    //get the content from source
    //since it supports more than one doc, put the byte[] into a list
    //where each byte[] element in the list represents one document.
    java.util.List docs = getDocListParameter(docListStr);
    System.out.println("---Getting byte[] from the docs ---");
    java.util.List docList = new java.util.Vector();
    for (int i=0; i < docs.size(); i++) {
      String aDoc = (String) docs.get(i);
      byte[] data = null;
      try {
        data = DocUtil.getDocBytes(aDoc);
      }catch(Throwable t) {
        System.out.println("\"" + aDoc + "\" - " + t.getMessage());
        System.out.println("Skip - \"" + aDoc + "\"");
      }
      //String dataStr = new String(data);
      //System.out.println(dataStr);
      if (data != null) {
        docList.add(data);
      }
    }
    System.out.println("---Got byte[] content from docs----");

    if (docList.size() == 0) {
      System.out.println("No docs - quit.");
      System.exit(1);
    }

    //Printer
    //SilentPrint silentPrint = new SilentPrintPdf();

    //Get the handle to context has all the runtime info (changes time to time as it makes the progress).
    MyCustomSilentPrintPDF silentPrint = new MyCustomSilentPrintPDF();

    //default media size
    String paperSize = new AtMediaSizeObject(MediaSizeName.NA_LETTER).getMediaSizePixelString();  // or manually do this "(0, 0, 0, 0, 612, 792)"; => (top, left, bot, right, width, height)
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
    //debug it
    //silentPrint.setAttribute(SilentPrint.DEBUG, Boolean.TRUE);
    //job name
    silentPrint.setAttribute(SilentPrint.JOB_NAME, "SilentPrintPDF");
    //document
    silentPrint.setAttribute(SilentPrint.DOC_LIST, docList);
    //Add a docListener
    //must have a default constructor for this class.
    silentPrint.setAttribute(SilentPrint.DOC_LISTENER, "demo.activetree.pdfprint.PdfDocListener");
    //OR your custom DocListener for your own tracking.
    //silentPrint.setAttribute(SilentPrint.DOC_LISTENER, "silentprintpdf.MyPdfSilentPrintDocListener");
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

    //Get the handle to the print context.
    WebContext printContext = silentPrint.getPrintContext();
    System.out.println("Print context: " + printContext);
    CommonAttributeReader contextReader = printContext.getWebDoc();
    DocListener docListener = (DocListener)contextReader.getAttribute(SilentPrint.DOC_LISTENER);
    System.out.println("DocListener=" + docListener);
    System.out.println("Printing launched, please wait...");
  }

  static class MyCustomSilentPrintPDF extends SilentPrintPdf {
    WebContext printContext = null;

    public void start() {
      WebInterface target = createTarget();
      target.init();
      //Get handle to the printing context right after the init is done
      //It is single threaded until this point
      WebDocAttributeParser targetHandler = (WebDocAttributeParser) target;
      printContext = targetHandler.getWebContext();

      //This process will actually launch the job in a new Thread.
      target.start();
    }

    public WebContext getPrintContext() {
      return printContext;
    }
  }

  public static java.util.List getDocListParameter(String docs) {
    Vector values = new Vector();
    if (docs == null) {
      docs = "";
    }
    StringTokenizer tk = new StringTokenizer(docs, "[]");
    while(tk.hasMoreTokens()) {
      String t = tk.nextToken();
      t = t.trim();
      values.add(t);
      System.out.println("\"" + t + "\"");
    }
    System.out.println("DOC_LIST=" + values);
    return values;
  }
}

package demo.activetree.pdfprint.print;

import com.activetree.pdfprint.common.AtPdfStreamPrinter;
import com.activetree.common.utils.MediaUtil;
import com.activetree.common.doc.DocPageable;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

/**
 * A different approach to PDF printing.
 */
public class PrintPageableDemo {

  public static void main(String[] args) throws Throwable {
    new PrintPageableDemo().silentPrint(args);
  }

  public void silentPrint(String[] args) throws Throwable {
    String pdfSource = args[0];
    int pageScaling = AtPdfStreamPrinter.PAGE_SCALING_FIT_TO_PRINTABLE_AREA;
    //Locale specific default PF (e.g. LETTER in US, ISO_A4 in EUROPE)
    PageFormat defaultPageFormat = MediaUtil.getDefaultPrinterPageFormat();
    PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
    AtPdfStreamPrinter printer = new AtPdfStreamPrinter();
    Pageable pdfPages = printer.getPageable(pdfSource, defaultPageFormat, defaultPrintService, pageScaling);
    //Separate or eliminate the pages depending on if all pages or a range of pages be printed.
    Pageable pagesToPrint = getPagesToPrint(pdfPages);
    //Set print attrs; e.g. tray, duplex, etc.
    HashPrintRequestAttributeSet pSet = new HashPrintRequestAttributeSet();
    //pSet.add(javax.print.attribute.standard.MediaTray.ENVELOPE);

    PrinterJob pj = PrinterJob.getPrinterJob();
    pj.setPageable(pagesToPrint);
    pj.print(pSet);
  }

  public Pageable getPagesToPrint(Pageable pages) {
    //TODO -- filter out those pages you DO NOT need to print or put into a different Pageable
    //TODO print as a different print job
    DocPageable pagesToPrint = new DocPageable();
    for (int i=0; pages != null && i < pages.getNumberOfPages(); i++) {
      Printable aPage = pages.getPrintable(i);
      PageFormat aPageFormat = pages.getPageFormat(i);
      //Check if you want to print this page
      //Assumune: printing all pages
      pagesToPrint.append(aPage, aPageFormat);
    }
    return pagesToPrint;
  }
}

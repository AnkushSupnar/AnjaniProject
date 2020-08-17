/**
 * Copyright © by ActiveTree Inc., California, USA.
 * All rights reserved.
 */
package demo.activetree.pdfprint.print;

import demo.activetree.pdfprint.PdfDocListener;
import com.activetree.common.doc.DocEvent;
import com.activetree.common.doc.DocPageable;
import com.activetree.common.page.AbstractPage;

import java.awt.print.Pageable;
import java.awt.print.PageFormat;

/**
 * This sample shows how to selectively print pages from a document
 * or a list of documents passed on using the DOC_LIST parameter.
 * During printing or viewing process DocListener is called for you
 * to filter out those you do not want to print or view.
 * This sample filters out alternative pages which you can customize
 * to fit for your requirement.
 *
 * A DocListener (like this one) is set calling
 * the setAttribute(DOC_LISTENER, "docListener") in the printing or viewing
 * program. Look at the program samples about how DOC_LISTENER attribute
 * is used to receive the events at run time. DocListener is loaded
 * at runtime using reflection API and its interface APIs are called at
 * run time depending on activities and attribute settings at the document
 * processor program.
 */
public class SelectPagesDocListener extends PdfDocListener {
  protected void filterPageable(DocEvent evt, Object docList) {
    int evtType = evt.getType();
    Object aDoc = evt.getSource();
    int pageCount = evt.getValue();
    Pageable docPages = (Pageable) evt.getDetails();

    //TODO -- here is an example of how to filter our pages and process selected page
    //todo -- filter out those pages -- if not needed
    //Since you are filtering out some pages -- recount the pages you are processing
    if (docPages != null && docPages.getNumberOfPages() > 0) {
      //todo: trim first page -- example -- remove later
      DocPageable newPages = new DocPageable();
      for (int page = 0; page < docPages.getNumberOfPages(); page++) {
        //put your filtering criteria; e.g. page index etc.; e.g. alternative pages.
        //Example: let's take odd pages
        AbstractPage aPage = (AbstractPage) docPages.getPrintable(page);
        PageFormat aPageFormat = docPages.getPageFormat(page);
//        int rem = page % 2;
//        if (rem != 1) {
          newPages.append(aPage, aPageFormat);
        break;
//        }
      }
      //set it back to the event
      evt.setDetails(newPages);
      //recount the pages here.
      System.out.println("DOC PAGEABLE (filtered out " + (pageCount - newPages.getNumberOfPages()) + " of " + pageCount + " pages [doc=" + aDoc +"])");
      pageCount = newPages.getNumberOfPages();
    }
    totalPages += pageCount;
    //TODO -end example
  }
}
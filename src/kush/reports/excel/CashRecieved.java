package kush.reports.excel;

public class CashRecieved
{
	CashRecieved()
	{
//		try {
//			FileInputStream fin = new FileInputStream(new File("itemlist1.xlsx"));
//			System.out.println("reading");
//			XSSFWorkbook book = new XSSFWorkbook(fin);
//			XSSFSheet sheet = book.getSheetAt(0);
//			int row = sheet.getLastRowNum();
//			for(int i=0;i<row;i++)
//			{
//				System.out.println(sheet.getRow(i).getCell(0));
//				System.out.print("  "+sheet.getRow(i).getCell(1));
//				System.out.print("  "+sheet.getRow(i).getCell(2));
//				System.out.print("  "+sheet.getRow(i).getCell(3));
//				System.out.print("  "+sheet.getRow(i).getCell(4));
//			}
//			//((Closeable) book).close();
//			fin.close();
//			System.out.println(sheet.getRow(1).getCell(0));
//			
//		} catch (Exception e) 
//		{
//			System.out.println("Error: "+e.getMessage());
//			e.printStackTrace();
//		}
	}
	public static void main(String[] args) 
	{
		new CashRecieved();
	}
}
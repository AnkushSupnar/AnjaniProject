package kush.reports;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonMethods;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class MonthlySalesReport extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6091753566407252269L;
	JComboBox<String> cmbMonth,cmbYear;
	private JTable table;
	private DefaultTableModel model;
	Calendar calendar;
	public MonthlySalesReport()
	{
		setTitle("Monthly Sales Report");
		setSize(662, 758);
		setLocation(212, 5);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 498, 49);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSelectMonth = new JLabel("Select Month");
		lblSelectMonth.setFont(new Font("Lao UI", Font.PLAIN, 18));
		lblSelectMonth.setBounds(10, 6, 104, 25);
		panel.add(lblSelectMonth);
		
		cmbMonth = new JComboBox<>();
		cmbMonth.setFont(new Font("Lao UI", Font.BOLD, 14));
		cmbMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		cmbMonth.setBounds(126, 5, 125, 35);
			
		//Date date = new Date();
				 calendar = Calendar.getInstance(); 
				//System.out.println(calendar.get(Calendar.MONTH));
				System.out.println("Year "+calendar.get(Calendar.YEAR));
				//System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
				//System.out.println(Calendar.FEBRUARY);
		cmbMonth.setSelectedIndex(calendar.get(Calendar.MONTH)-1);		
		panel.add(cmbMonth);
		
		cmbYear = new JComboBox<>();
		cmbYear.setFont(new Font("Lao UI", Font.BOLD, 14));
		cmbYear.addItem(""+(calendar.get(Calendar.YEAR)-1));
		cmbYear.addItem(""+(calendar.get(Calendar.YEAR)));
		cmbYear.addItem(""+(calendar.get(Calendar.YEAR)+1));
		
		cmbYear.setSelectedItem(""+calendar.get(Calendar.YEAR));
		cmbYear.setBounds(263, 5, 104, 35);
		panel.add(cmbYear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 498, 652);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		model.addColumn("SrNo.");
		model.addColumn("Date");
		model.addColumn("Cash Amount");
		model.addColumn("Credit Amount");
		table = new JTable(model);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table.setRowHeight(25);
		
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(509, 11, 128, 703);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.setBounds(6, 5, 116, 56);
		panel_1.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				load();
			}
		});
		btnLoad.setFont(new Font("Lao UI", Font.BOLD, 16));
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					String name=""+cmbMonth.getSelectedItem()+"-"+cmbYear.getSelectedItem();
		            Document doc = new Document();
		            PdfWriter.getInstance(doc, new FileOutputStream("D:\\Hotel Software\\"+name+".pdf"));
		            doc.open();
		            
		            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
		            //adding table headers
		            for (int i = 0; i < table.getColumnCount(); i++) {
		                pdfTable.addCell(table.getColumnName(i));
		            }
		            //extracting data from the JTable and inserting it to PdfPTable
		            for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
		                for (int cols = 0; cols < table.getColumnCount(); cols++) {
		                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

		                }
		            }
 
		            doc.add(new Paragraph("            Hotel Anjani "+name));
			        doc.add(new Paragraph("            Sales Report Of the month "+name));
			       doc.add(new Paragraph("     "));
		            
		            
		            
		            doc.add(pdfTable);
		            doc.close();
		            System.out.println("done");
		            JOptionPane.showMessageDialog(null, "PDF File Created\n to see the file click on Print button","Information",JOptionPane.INFORMATION_MESSAGE);
		        } catch (DocumentException ex) 
				{
		        	System.out.println("Document Exception "+ex.getMessage());
		        //    Logger.getLogger(super.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (FileNotFoundException ex) 
				{
		        	System.out.println("FIle IO Exception "+ex.getMessage());
		           // Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		        }

		    }

			
		});
		btnPdf.setFont(new Font("Lao UI", Font.BOLD, 16));
		btnPdf.setBounds(6, 70, 116, 56);
		panel_1.add(btnPdf);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					String name=""+cmbMonth.getSelectedItem()+"-"+cmbYear.getSelectedItem();
					File htmlFile = new File("D:\\Hotel Software\\"+name+".pdf");
					Desktop.getDesktop().browse(htmlFile.toURI());
					}catch(Exception e)
				{
					System.out.println("Error in printing "+e.getMessage());
				}
				
				
				
			}
		});
		btnPrint.setFont(new Font("Lao UI", Font.BOLD, 16));
		btnPrint.setBounds(6, 138, 116, 56);
		panel_1.add(btnPrint);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Lao UI", Font.BOLD, 16));
		btnExit.setBounds(6, 205, 116, 56);
		panel_1.add(btnExit);
		
		
		
		
		setVisible(true);
	}
	public static void main(String[] args)
	{
		CommonMethods.openConnection();
		try {
    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            break;
    	        }
    	    }
    	} catch (Exception e) {
    	    // If Nimbus is not available, you can set the GUI to another look and feel.
    	}
		new MonthlySalesReport();
		
		// TODO Auto-generated method stub

	}
	public void load()
	{
		model.setRowCount(0);
		int year = Integer.parseInt(""+cmbYear.getSelectedItem());
		int days= getMonthDays(cmbMonth.getSelectedIndex()+1, year);
		String date="",date2="";
		float cash=0.0f;
		float credit=0.0f;
		float totalcash=0.0f,totalcredit=0.0f;
		for(int i=1;i<=days;i++)
		{
			try {
			date2 = year+"-"+(cmbMonth.getSelectedIndex()+1)+"-"+i;
			date=i+"-"+(cmbMonth.getSelectedIndex()+1)+"-"+year;
			ResultSet rs = CommonMethods.selectQuery("select sum(BillAmt)from Bill where paymode='cash' and Date(Billdate)=Date('"+date2+"')");
				rs.next();
				cash=rs.getFloat(1);
				totalcash=totalcash+cash;
				rs=null;
			rs = CommonMethods.selectQuery("select sum(BillAmt-discount) from Bill where paymode='credit' and Date(Billdate)=Date('"+date2+"')");
				rs.next();
				credit=rs.getFloat(1);
				totalcredit=totalcredit+credit;
			model.addRow(new Object[] {i,date,cash,credit});
			}catch(Exception e)
			{
				System.out.println("Erroor in Loading Relort"+e.getMessage());
				return;
			}
		}
				model.addRow(new Object[] {" ","Total",totalcash,totalcredit});
				model.addRow(new Object[] {});
	}
	public int getMonthDays(int month, int year) 
	{
	    int daysInMonth ;
	    if (month == 4 || month == 6 || month == 9 || month == 11) {
	        daysInMonth = 30;
	    }
	    else {
	        if (month == 2) 
	        {
	            daysInMonth = (year % 4 == 0) ? 29 : 28;
	        } else 
	        {
	            daysInMonth = 31;
	        }
	    }
	    return daysInMonth;
	}
	
}

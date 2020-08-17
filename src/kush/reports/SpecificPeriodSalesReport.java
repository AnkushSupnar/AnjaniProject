package kush.reports;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonMethods;

public class SpecificPeriodSalesReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -866674205478846676L;
	private DatePicker toDate, fromDate;
	private JTable table;
	private DefaultTableModel model;

	public SpecificPeriodSalesReport() {
		setTitle("Time Period sales Report");
		setSize(710, 760);
		setLocation(212, 10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 568, 52);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblFrom = new JLabel("From Date");
		lblFrom.setFont(new Font("Lao UI", Font.PLAIN, 16));
		lblFrom.setBounds(6, 8, 75, 21);
		panel.add(lblFrom);

		fromDate = new DatePicker();
		fromDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fromDate.setDateToToday();
		fromDate.setBounds(99, 4, 189, 35);
		panel.add(fromDate);

		toDate = new DatePicker();
		toDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toDate.setDateToToday();
		toDate.setBounds(368, 6, 181, 35);
		panel.add(toDate);

		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setFont(new Font("Lao UI", Font.PLAIN, 16));
		lblToDate.setBounds(300, 9, 56, 21);
		panel.add(lblToDate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 568, 650);
		getContentPane().add(scrollPane);

		model = new DefaultTableModel();
		model.addColumn("Sr.No.");
		model.addColumn("Date");
		model.addColumn("Total Bill");
		model.addColumn("Cash Amount");
		model.addColumn("Credit Amount");
		model.addColumn("Total");

		table = new JTable(model);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table.setRowHeight(25);

		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(580, 11, 116, 705);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton button = new JButton("LOAD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();

			}
		});
		button.setFont(new Font("Lao UI", Font.BOLD, 16));
		button.setBounds(0, 6, 116, 56);
		panel_1.add(button);

		JButton button_1 = new JButton("PDF");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = "Time Period Report ";
					Document doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("D:\\Hotel Software\\" + name + ".pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
					for (int i = 0; i < table.getColumnCount(); i++) {
						pdfTable.addCell(table.getColumnName(i));
					}

					for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
						for (int cols = 0; cols < table.getColumnCount(); cols++) {
							System.out.print("     " + model.getValueAt(rows, cols));
							// System.out.println(rows+" "+cols);
							// System.out.println(table.getModel().getValueAt(rows, cols).toString());
							pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

						}
						System.out.println();
					}
					final String date1 = "" + fromDate.getDate();
					String date2 = "" + toDate.getDate();
					doc.add(new Paragraph("            Hotel Anjani "));
					doc.add(new Paragraph("            Sales Report Of the Date " + date1 + " to " + date2));
					doc.add(new Paragraph("     "));
					doc.add(pdfTable);
					doc.close();

				} catch (DocumentException ex) {
					System.out.println("Document Exception " + ex.getMessage());
					// Logger.getLogger(super.class.getName()).log(Level.SEVERE, null, ex);
				} catch (FileNotFoundException ex) {
					System.out.println("FIle IO Exception " + ex.getMessage());
					// Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		});
		button_1.setFont(new Font("Lao UI", Font.BOLD, 16));
		button_1.setBounds(0, 71, 116, 56);
		panel_1.add(button_1);

		JButton btnOpenPdf = new JButton("OPEN PDF");
		btnOpenPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = "Time Period Report ";
					File htmlFile = new File("D:\\Hotel Software\\" + name + ".pdf");
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (Exception e1) {
					System.out.println("Error in printing " + e1.getMessage());
				}
			}
		});
		btnOpenPdf.setFont(new Font("Lao UI", Font.BOLD, 16));
		btnOpenPdf.setBounds(0, 139, 116, 56);
		panel_1.add(btnOpenPdf);

		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("Lao UI", Font.BOLD, 16));
		btnHome.setBounds(0, 206, 116, 56);
		panel_1.add(btnHome);
		setVisible(true);
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}

		new SpecificPeriodSalesReport();

	}

	public void load() {
		try {
			model.setRowCount(0);
			LocalDate date1 = fromDate.getDate();
			LocalDate date2 = toDate.getDate();
			System.out.println("Start Date=" + date1 + " End Date=" + date2);
			float cash = 0.0f, credit = 0.0f, totalcash = 0.0f, totalcredit = 0.0f, total = 0.0f;
			List<String> date = getDate(date1, date2);
			int row = 0, bills = 0;
			for (String i : date) {
				ResultSet rs = CommonMethods.selectQuery(
						"select sum(BillAmt),count(billno) from Bill where paymode='cash' and Date(Billdate)=Date('" + i
								+ "')");
				rs.next();
				cash = rs.getFloat(1);
				bills = rs.getInt(2);
				totalcash = totalcash + cash;
				rs = null;
				rs = CommonMethods.selectQuery(
						"select sum(BillAmt-discount),count(billno) from Bill where paymode='credit' and Date(Billdate)=Date('"
								+ i + "')");
				rs.next();
				credit = rs.getFloat(1);
				bills = bills + rs.getInt(2);
				totalcredit = totalcredit + credit;
				total = total + (cash + credit);
				model.addRow(new Object[] { ++row, i, bills, cash, credit, (cash + credit) });
				bills = 0;
			}
			model.addRow(new Object[] { "", "", "", "", "", "" });
			model.addRow(new Object[] { "", "", "Total", totalcash, totalcredit, total });
			model.addRow(new Object[] { "", "", "", "", "", "" });
		} catch (Exception e) {
			System.out.println("error in Loading data " + e.getMessage());
		}
	}

	public List<String> getDate(LocalDate stDate, LocalDate endDate) {
		String date = "";
		List<String> dates = new ArrayList<String>();
		try {

			String query = "select DATE_FORMAT(billdate,'%Y-%m-%d'),BillAmt from Bill where Date(billDate)>=Date('"
					+ stDate + "') and Date(Billdate)<= Date('" + endDate + "')";
			System.out.println(query);
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next()) {
				date = rs.getString(1);
				if (!dates.contains(date)) {
					dates.add(date);

				}
				// System.out.println("Selected date Data is "+rs.getDate(1)+" and
				// "+rs.getFloat(2));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error in get Date " + e.getMessage());
			return null;
		}
		return dates;
	}
}

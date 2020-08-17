package kush.design;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.BasicConfigurator;

import com.github.lgooddatepicker.components.DatePicker;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;

public class BankPassbook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2087602444112120949L;
	private JTextField txtBankCode;
	private JComboBox<String> cmbBankName;
	private JComboBox<String> cmbParticular;
	private JLabel lblKacaacaaTapaisala;
	private DatePicker startDate, endDate;
	private JTable table;
	private DefaultTableModel model;
	private JCheckBox chkAllDate, chkSelectAll;
	String htmstart = "<html><font face=\"kiran\" size=\"5\">";
	private JButton btnPrint;
	private JButton btnExit;

	public BankPassbook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(775, 705);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(6, 6, 735, 142);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ba^Mk kaoD");
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(25, 10, 55, 24);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ba^Mkocao naava");
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(126, 9, 68, 25);
		panel.add(lblNewLabel_1);

		txtBankCode = new JTextField();
		txtBankCode.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtBankCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtBankCode.getText().equals("")) {
						cmbBankName.setSelectedItem(CommonLogic.getBankNameUsingCode(txtBankCode.getText()));
						cmbParticular.requestFocus();
						cmbParticular.showPopup();
					}
				}
			}
		});
		txtBankCode.setBounds(18, 34, 104, 35);
		panel.add(txtBankCode);
		txtBankCode.setColumns(10);

		cmbBankName = new JComboBox<>(new Vector<>(CommonLogic.getBankName()));
		cmbBankName.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbBankName.setBounds(126, 34, 251, 35);
		panel.add(cmbBankName);

		cmbParticular = new JComboBox<>(new Vector<>(GetData.getParticularsList()));
		cmbParticular.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbParticular.setBounds(389, 34, 245, 35);
		panel.add(cmbParticular);

		lblKacaacaaTapaisala = new JLabel("tapaiSala");
		lblKacaacaaTapaisala.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblKacaacaaTapaisala.setBounds(389, 9, 53, 24);
		panel.add(lblKacaacaaTapaisala);

		chkSelectAll = new JCheckBox("sava-");
		chkSelectAll.setFont(new Font("Kiran", Font.PLAIN, 25));
		chkSelectAll.setBounds(642, 42, 46, 24);
		panel.add(chkSelectAll);

		JLabel lblPaasaunaIdnaamk = new JLabel("paasauna idnaaMk ");
		lblPaasaunaIdnaamk.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblPaasaunaIdnaamk.setBounds(18, 89, 95, 24);
		panel.add(lblPaasaunaIdnaamk);

		startDate = new DatePicker();
		startDate.setBackground(SystemColor.inactiveCaption);
		startDate.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 16));
		startDate.getComponentToggleCalendarButton().setFont(new Font("SansSerif", Font.PLAIN, 12));
		startDate.setDateToToday();
		startDate.setBounds(116, 88, 170, 30);
		panel.add(startDate);

		JLabel lblPayamtaIdnaamk = new JLabel("paya-Mta idnaaMk ");
		lblPayamtaIdnaamk.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblPayamtaIdnaamk.setBounds(292, 89, 88, 24);
		panel.add(lblPayamtaIdnaamk);

		endDate = new DatePicker();
		endDate.setBackground(SystemColor.inactiveCaption);
		endDate.getComponentToggleCalendarButton().setFont(new Font("SansSerif", Font.PLAIN, 12));
		endDate.setDateToToday();
		endDate.setBounds(389, 88, 170, 30);
		panel.add(endDate);

		chkAllDate = new JCheckBox("sava-");
		chkAllDate.setFont(new Font("Kiran", Font.PLAIN, 25));
		chkAllDate.setBounds(571, 93, 46, 24);
		panel.add(chkAllDate);

		JButton btnLoad = new JButton("laaoD");
		btnLoad.addActionListener(e -> load());
		btnLoad.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnLoad.setBounds(6, 160, 90, 35);
		getContentPane().add(btnLoad);

		JButton btnClear = new JButton("@laIAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(116, 160, 90, 35);
		getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 206, 735, 454);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "Sr.No", "Date", "Particulars", "BankName", "Cheque No", "Withdraw", "Deposit" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(84);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		JButton btnPDF = new JButton("PDF");
		btnPDF.addActionListener(e -> pdf());
		btnPDF.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnPDF.setBounds(227, 160, 90, 35);
		getContentPane().add(btnPDF);

		btnPrint = new JButton("ipa`MT");
		btnPrint.addActionListener(e -> pdf());
		btnPrint.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnPrint.setBounds(345, 160, 90, 35);
		getContentPane().add(btnPrint);

		btnExit = new JButton("baahor");
		btnExit.addActionListener(e -> dispose());
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(455, 160, 90, 35);
		getContentPane().add(btnExit);

		model = (DefaultTableModel) table.getModel();
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		new BankPassbook();
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private int validateData() {
		try {
			if (cmbBankName.getSelectedItem().equals("")) {
				showError("Select Bank Name");
				cmbBankName.requestFocus();
				cmbBankName.showPopup();
				return 0;
			} else if (cmbParticular.getSelectedItem().equals("")) {
				showError("Select Particular");
				cmbParticular.requestFocus();
				cmbParticular.showPopup();
				return 0;
			} else if (!chkAllDate.isSelected()) {
				if (startDate.getDate().equals(null)) {
					showError("Enter Start date");
					return 0;
				} else if (endDate.getDate().equals(null)) {
					showError("Enter End Date");
					return 0;
				}
			} else {
				return 1;
			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	private void load() {
		try {
			if (validateData() == 0) {
				return;
			}
			// one particular
			if (!chkSelectAll.isSelected() && !chkAllDate.isSelected()) {
				getOneParticular(cmbParticular.getSelectedItem().toString(),
						CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()),
						java.sql.Date.valueOf(startDate.getDate()), java.sql.Date.valueOf(endDate.getDate()));
			}
			if (chkSelectAll.isSelected() && !chkAllDate.isSelected()) {
				getAllParticular(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()),
						java.sql.Date.valueOf(startDate.getDate()), java.sql.Date.valueOf(endDate.getDate()));
			}
			if (!chkSelectAll.isSelected() && chkAllDate.isSelected()) {
				getOneParticularAllDate(cmbParticular.getSelectedItem().toString(),
						CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()));
			}
			if (chkSelectAll.isSelected() & chkAllDate.isSelected()) {
				getAllParticularAllDate(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()));
			}

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void getOneParticular(String particular, int bankId, Date startDate, Date endDate) {
		try {
			model.setRowCount(0);

			String sql = "SELECT banktransaction.Id,banktransaction.Particulars,"
					+ "banktransaction.Date,banktransaction.chequeNo,BankDetails.BankName,"
					+ "banktransaction.Withdraw,banktransaction.Deposite "
					+ "FROM banktransaction,BankDetails where "
					+ "BankDetails.id=BankTransaction.BankId AND " + "Date>='" + startDate + "' AND " + "Date<='"
					+ endDate + "' AND " + "Particulars='" + particular + "' And BankId=" + bankId;
			// System.out.println(sql);
			ResultSet rs = CommonMethods.selectQuery(sql);
			int sr = 0;
			Double totalDeposit = 0.0, totalWithdraw = 0.0, deposit = 0.0, withdraw = 0.0;
			while (rs.next()) {
				deposit = rs.getDouble(7);
				withdraw = rs.getDouble(6);
				totalDeposit = totalDeposit + deposit;
				totalWithdraw = totalWithdraw + withdraw;
				model.addRow(new Object[] { ++sr, rs.getDate(3), htmstart + rs.getString(2), htmstart + rs.getString(5),
						rs.getString(4), withdraw, deposit });
				deposit = 0.0;
				withdraw = 0.0;
			}
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] { "", "", "", "", htmstart + "ekuNa", totalWithdraw, totalDeposit });

			rs.close();
		} catch (Exception e) {
			showError("Error in getOneParticular " + e.getMessage());
		}
	}

	public void getAllParticular(int bankId, Date startDate, Date endDate) {
		try {
			model.setRowCount(0);

			String sql = "SELECT banktransaction.Id,banktransaction.Particulars,"
					+ "banktransaction.Date,banktransaction.chequeNo,BankDetails.BankName,"
					+ "banktransaction.Withdraw,banktransaction.Deposite "
					+ "FROM banktransaction,BankDetails where "
					+ "BankDetails.id=BankTransaction.BankId AND " + "Date>='" + startDate + "' AND " + "Date<='"
					+ endDate + "' And BankId=" + bankId;
			// System.out.println(sql);
			ResultSet rs = CommonMethods.selectQuery(sql);
			int sr = 0;
			Double totalDeposit = 0.0, totalWithdraw = 0.0, deposit = 0.0, withdraw = 0.0;
			while (rs.next()) {
				deposit = rs.getDouble(7);
				withdraw = rs.getDouble(6);
				totalDeposit = totalDeposit + deposit;
				totalWithdraw = totalWithdraw + withdraw;
				model.addRow(new Object[] { ++sr, rs.getDate(3), htmstart + rs.getString(2), htmstart + rs.getString(5),
						rs.getString(4), withdraw, deposit });

				deposit = 0.0;
				withdraw = 0.0;

				model.addRow(new Object[] {});
				model.addRow(new Object[] {});
				model.addRow(new Object[] {});
				model.addRow(new Object[] { "", "", "", "", htmstart + "ekuNa", totalWithdraw, totalDeposit });

			}
			rs.close();
		} catch (Exception e) {
			showError("Error in getOneParticular " + e.getMessage());
		}

	}

	public void getOneParticularAllDate(String particular, int bankId) {
		try {
			model.setRowCount(0);

			String sql = "SELECT banktransaction.Id,banktransaction.Particulars,"
					+ "banktransaction.Date,banktransaction.chequeNo,BankDetails.BankName,"
					+ "banktransaction.Withdraw,banktransaction.Deposite " + "FROM banktransaction,BankDetails where "
					+ "BankDetails.id=BankTransaction.BankId AND " + "Particulars='" + particular + "' And BankId="
					+ bankId;
			// System.out.println(sql);
			ResultSet rs = CommonMethods.selectQuery(sql);
			int sr = 0;
			Double totalDeposit = 0.0, totalWithdraw = 0.0, deposit = 0.0, withdraw = 0.0;
			while (rs.next()) {
				deposit = rs.getDouble(7);
				withdraw = rs.getDouble(6);
				totalDeposit = totalDeposit + deposit;
				totalWithdraw = totalWithdraw + withdraw;
				model.addRow(new Object[] { ++sr, rs.getDate(3), htmstart + rs.getString(2), htmstart + rs.getString(5),
						rs.getString(4), withdraw, deposit });
				deposit = 0.0;
				withdraw = 0.0;

			}
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] { "", "", "", "", htmstart + "ekuNa", totalWithdraw, totalDeposit });

			rs.close();
		} catch (Exception e) {
			showError("Error in getOneParticular " + e.getMessage());
		}
	}

	public void getAllParticularAllDate(int bankId) {
		try {
			model.setRowCount(0);

			String sql = "SELECT banktransaction.Id,banktransaction.Particulars,"
					+ "banktransaction.Date,banktransaction.chequeNo,BankDetails.BankName,"
					+ "banktransaction.Withdraw,banktransaction.Deposite " + "FROM banktransaction,BankDetails where "
					+ "BankDetails.id=BankTransaction.BankId  And BankId=" + bankId;
			// System.out.println(sql);
			ResultSet rs = CommonMethods.selectQuery(sql);
			int sr = 0;
			Double totalDeposit = 0.0, totalWithdraw = 0.0, deposit = 0.0, withdraw = 0.0;
			while (rs.next()) {
				deposit = rs.getDouble(7);
				withdraw = rs.getDouble(6);
				totalDeposit = totalDeposit + deposit;
				totalWithdraw = totalWithdraw + withdraw;
				model.addRow(new Object[] { ++sr, rs.getDate(3), htmstart + rs.getString(2), htmstart + rs.getString(5),
						rs.getString(4), withdraw, deposit });
			}
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] { "", "", "", "", htmstart + "ekuNa", totalWithdraw, totalDeposit });

			rs.close();
		} catch (Exception e) {
			showError("Error in getOneParticular " + e.getMessage());
		}
	}

	public void clear() {
		chkSelectAll.setSelected(false);
		chkAllDate.setSelected(false);
		model.setRowCount(0);
		txtBankCode.setText("");
		startDate.setDateToToday();
		endDate.setDateToToday();
	}

	public void pdf() {
		try {
			if (model.getRowCount() == 0) {
				showError("No Data To Load");
			}
			// Logger logger = Logger.getLogger(BankPassbook.class);
			// PropertyConfigurator.configure("Log.properties");
			// logger.debug("DEBUG MESSAGE: ");
			BasicConfigurator.configure();

			Properties prop = CommonMethods.loadPropertiesFile();
			String fontname = prop.getProperty("Bill.Font");

			// String bankName = cmbBankName.getSelectedItem().toString();
			String filePath = "D:\\Hotel Software\\BankPassbook.pdf";

			com.itextpdf.text.Font font = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 35.8f,
					Font.PLAIN, BaseColor.BLACK);

			com.itextpdf.text.Font f2 = FontFactory.getFont(fontname, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20f,
					Font.PLAIN, BaseColor.BLACK);

			com.itextpdf.text.Font f3 = FontFactory.getFont("Times new Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
					15f, Font.PLAIN, BaseColor.BLACK);
			PdfPTable headerTable = new PdfPTable(4);
			headerTable.setTotalWidth(new float[] { 100, 240, 50, 150 });
			headerTable.setLockedWidth(true);

			PdfPCell cell1 = new PdfPCell(new Phrase("ba^Mkocao naava :", f2));
			cell1.setFixedHeight(30);
			cell1.setBorder(0);
			headerTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("" + cmbBankName.getSelectedItem(), f2));
			cell2.setFixedHeight(30);
			cell2.setBorder(0);
			headerTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("Kaatao : ", f2));
			cell3.setFixedHeight(30);
			cell3.setBorder(0);
			headerTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase(""
					+ CommonLogic.getBankAccountType(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString())),
					f2));
			cell4.setFixedHeight(30);
			cell4.setBorder(0);
			headerTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("Kaatao k`. : ", f2));
			cell5.setFixedHeight(30);
			cell5.setBorder(0);
			headerTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("" + CommonLogic.getBankAccountNOName(cmbBankName.getSelectedItem().toString()), f2));
			cell6.setFixedHeight(30);
			cell6.setBorder(0);
			headerTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("IFSC : ", f3));
			cell7.setFixedHeight(30);
			cell7.setBorder(0);
			headerTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase(""
					+ CommonLogic.getBankAccountIFSC(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString())),
					f3));
			cell8.setFixedHeight(30);
			cell8.setBorder(0);
			headerTable.addCell(cell8);

			PdfPCell cell9 = new PdfPCell(new Phrase("KaataoQaarkacao naava:", f2));
			cell9.setFixedHeight(30);
			cell9.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell9);

			PdfPCell cell10 = new PdfPCell(new Phrase(
					"" + CommonLogic
							.getBankAccountHolderName(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString())),
					f2));
			cell10.setFixedHeight(30);
			cell10.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell10);

			PdfPCell cell11 = new PdfPCell(new Phrase("", f2));
			cell11.setFixedHeight(30);
			cell11.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell11);

			PdfPCell cell12 = new PdfPCell(new Phrase("", f2));
			cell12.setFixedHeight(30);
			cell12.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell12);

			PdfPCell cell13 = new PdfPCell(new Phrase("irpaaoT- kalaavaiQa :", f2));
			cell13.setFixedHeight(30);
			cell13.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell13);

			PdfPCell cell14 = new PdfPCell(new Phrase("" + startDate.getDate() + " to " + endDate.getDate(), f3));
			cell14.setFixedHeight(30);
			cell14.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell14);

			PdfPCell cell15 = new PdfPCell(new Phrase(" irpaaoT- idnaaMk:", f2));
			cell15.setFixedHeight(30);
			cell15.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell15);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			PdfPCell cell16 = new PdfPCell(new Phrase("" + dtf.format(now), f3));
			cell16.setFixedHeight(30);
			cell16.setBorder(Rectangle.BOTTOM);
			headerTable.addCell(cell16);

			// Write Report Data
			PdfPTable dataTable = new PdfPTable(6);
			dataTable.setTotalWidth(new float[] { 50, 90, 150, 70, 90, 90 });
			dataTable.setLockedWidth(true);

			// writing Headr for Data
			PdfPCell c1 = new PdfPCell(new Phrase(" Ak`.", f2));
			c1.setFixedHeight(30);
			c1.setBorder(Rectangle.BOX);
			dataTable.addCell(c1);

			PdfPCell c2 = new PdfPCell(new Phrase("idnaaMk", f2));
			c2.setFixedHeight(30);
			c2.setBorder(Rectangle.BOX);
			dataTable.addCell(c2);

			PdfPCell c3 = new PdfPCell(new Phrase("tapaiSala", f2));
			c3.setFixedHeight(30);
			c3.setBorder(Rectangle.BOX);
			dataTable.addCell(c3);

			PdfPCell c4 = new PdfPCell(new Phrase("caok  na", f2));
			c4.setFixedHeight(30);
			c4.setBorder(Rectangle.BOX);
			dataTable.addCell(c4);

			PdfPCell c5 = new PdfPCell(new Phrase("kaZlao", f2));
			c5.setFixedHeight(30);
			c5.setBorder(Rectangle.BOX);
			dataTable.addCell(c5);

			PdfPCell c6 = new PdfPCell(new Phrase("jamaa", f2));
			c6.setFixedHeight(30);
			c6.setBorder(Rectangle.BOX);
			dataTable.addCell(c6);
			int row = model.getRowCount();
			int srno = 0;
			for (int i = 0; i < row - 3; i++) {
				if (model.getValueAt(i, 0) != null) {
					PdfPCell sr = new PdfPCell(new Phrase("" + (++srno), f2));
					sr.setFixedHeight(30);
					sr.setBorder(Rectangle.BOX);
					sr.setVerticalAlignment(Element.ALIGN_CENTER);
					dataTable.addCell(sr);
				}
				if (model.getValueAt(i, 1) != null) {
					PdfPCell date = new PdfPCell(new Phrase("" + model.getValueAt(i, 1), f3));
					date.setFixedHeight(30);
					date.setBorder(Rectangle.BOX);

					dataTable.addCell(date);
				}
				if (model.getValueAt(i, 2) != null) {
					if (!model.getValueAt(i, 2).equals("")) {
						String p[] = model.getValueAt(i, 2).toString().split(">");

						// String p1 = p[2].toString();
						PdfPCell particular = new PdfPCell(new Phrase(p[2], f2));
						particular.setFixedHeight(30);
						particular.setBorder(Rectangle.BOX);

						dataTable.addCell(particular);
					}
				}

				if (model.getValueAt(i, 4) != null) {
					String cn = model.getValueAt(i, 4).toString();
					if (cn.equals("N/A"))
						cn = "";
					PdfPCell chequeno = new PdfPCell(new Phrase("" + cn, f2));
					chequeno.setFixedHeight(30);
					chequeno.setBorder(Rectangle.BOX);
					chequeno.setVerticalAlignment(Element.ALIGN_CENTER);
					dataTable.addCell(chequeno);
				}
				if (model.getValueAt(i, 5) != null) {
					PdfPCell withdraw = new PdfPCell(new Phrase("" + model.getValueAt(i, 5), f2));
					withdraw.setFixedHeight(30);
					withdraw.setBorder(Rectangle.BOX);
					withdraw.setVerticalAlignment(Element.ALIGN_CENTER);
					dataTable.addCell(withdraw);
				}
				if (model.getValueAt(i, 6) != null) {
					PdfPCell deposit = new PdfPCell(new Phrase("" + model.getValueAt(i, 6), f2));
					deposit.setFixedHeight(30);
					deposit.setBorder(Rectangle.BOX);
					deposit.setVerticalAlignment(Element.ALIGN_CENTER);
					dataTable.addCell(deposit);
				}

			}
			System.out.println("Sr =" + srno);
			for (int i = 0; i < 6; i++) {
				if (i == 3) {
					System.out.println("IN Model=" + model.getValueAt(srno + 3, 4));
					PdfPCell deposit = new PdfPCell(new Phrase(" ekuNa", f2));
					deposit.setFixedHeight(30);
					deposit.setVerticalAlignment(Element.ALIGN_CENTER);
					deposit.setBorder(0);
					dataTable.addCell(deposit);
				} else if (i == 4) {
					PdfPCell deposit = new PdfPCell(new Phrase("" + model.getValueAt(srno + 3, 5), f2));
					deposit.setFixedHeight(30);
					deposit.setBorder(0);
					deposit.setVerticalAlignment(Element.ALIGN_CENTER);
					dataTable.addCell(deposit);
				} else if (i == 5) {
					PdfPCell deposit = new PdfPCell(new Phrase("" + model.getValueAt(srno + 3, 6), f2));
					deposit.setFixedHeight(30);
					deposit.setBorder(0);
					dataTable.addCell(deposit);
				}

				else {
					PdfPCell deposit = new PdfPCell(new Phrase("", f3));
					deposit.setFixedHeight(30);
					deposit.setBorder(0);
					dataTable.addCell(deposit);
				}
			}

			// Rectangle pagesize = new Rectangle(616f, 600f+table.getTotalHeight());
			Rectangle pagesize = new Rectangle(616f, 600f + dataTable.getTotalHeight());
			Document document = new Document(pagesize, 3f, 3f, 20f, 180f);

			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			Paragraph p = new Paragraph("                               ba^Mk paasabauk", font);
			p.setLeading(15);
			document.add(p);

			document.add(headerTable);
			document.add(dataTable);

			document.close();
			File htmlFile = new File("D:\\\\Hotel Software\\\\BankPassbook.pdf");
			Desktop.getDesktop().browse(htmlFile.toURI());

		} catch (

		Exception e) {
			showError("Error in Create PDF " + e.getMessage());
			e.printStackTrace();
		}
	}
}

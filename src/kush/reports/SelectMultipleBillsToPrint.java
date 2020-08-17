package kush.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.BasicConfigurator;

import com.github.lgooddatepicker.components.DatePicker;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import kush.POJO.Bill;
import kush.print.A4Bill;
import kush.print.MultipleBills;

public class SelectMultipleBillsToPrint extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = -6782171375302008719L;
	private JTextField txtCustomername;
	private JList<String> jlist;
	private List<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private JPopupMenu popup;
	// private Font font = CommonLogic.font;
	private JTextField txtCode;
	private DatePicker fromDate, toDate;
	private DefaultTableModel tblmodel;
	private JTable table;
	// private LocalDate date;

	public SelectMultipleBillsToPrint() {
		getContentPane().setBackground(new Color(70, 130, 180));
		setTitle("Select Multiple Bills To Print");
		setSize(606, 755);
		setLocation(212, 5);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// getContentPane().setBackground(new Color(147, 112, 219));
		getContentPane().setLayout(null);

		JLabel lblChooseNameOf = new JLabel("ga`ahk  naava");
		lblChooseNameOf.setForeground(UIManager.getColor("Table[Enabled+Selected].textForeground"));
		lblChooseNameOf.setFont(new Font("Kiran", Font.BOLD, 20));
		lblChooseNameOf.setBounds(140, 10, 73, 20);
		getContentPane().add(lblChooseNameOf);
		list = CommonLogic.getAllCustomerFullName();
		model = new DefaultListModel<>();
		jlist = new JList<>(model);
		jlist.setFont(new Font("Kiran", Font.PLAIN, 20));
		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(jlist);

		txtCustomername = new JTextField();
		txtCustomername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				findItem(txtCustomername.getText());
				if (!list.isEmpty()) {
					popup.add(scroll); // your component
					popup.setPopupSize(txtCustomername.getWidth(), 300);
					popup.show(txtCustomername, 0, txtCustomername.getHeight());
					txtCustomername.requestFocus();
				}
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					jlist.requestFocus();
					jlist.setSelectedIndex(0);
				}

			}
		});
		jlist.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					txtCustomername.setText(jlist.getSelectedValue());
					// int id = CommonLogic.getCustomerIdUsingName(txtCustomername.getText());
					txtCode.setText(CommonLogic.getCustomerKeyUsingName(txtCustomername.getText()));
					// System.out.println("Got id = "+id);

					popup.setVisible(false);

				}
			}
		});
		txtCustomername.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtCustomername.setBounds(131, 35, 267, 35);
		getContentPane().add(txtCustomername);
		txtCustomername.setColumns(10);

		JLabel lblCode = new JLabel("ga`ahk  kaoD");
		lblCode.setFont(new Font("Kiran", Font.BOLD, 20));
		lblCode.setForeground(Color.WHITE);
		lblCode.setBounds(20, 10, 72, 20);
		getContentPane().add(lblCode);

		txtCode = new JTextField();
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					if (!CommonLogic.getCustomerNameUsingCode(txtCode.getText()).equals("")) {
						txtCustomername.setText(CommonLogic.getCustomerNameUsingCode(txtCode.getText()));
						txtCustomername.requestFocus();
					}
					txtCustomername.requestFocus();

				}
			}
		});
		txtCode.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		txtCode.setBounds(6, 35, 122, 35);
		getContentPane().add(txtCode);
		txtCode.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 129, 556, 525);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table.setRowHeight(25);

		scrollPane.setViewportView(table);
		// THE MODEL OF OUR TABLE
		tblmodel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;

				default:
					return String.class;
				}
			}
		};

		table.setModel(tblmodel);
		tblmodel.addColumn("Select");
		tblmodel.addColumn("BillNo");
		tblmodel.addColumn("date");
		tblmodel.addColumn("Amount");

		JButton btnPrint = new JButton("ipa`MT");
		btnPrint.addActionListener(e -> print());

		btnPrint.setFont(new Font("Kiran", Font.BOLD, 25));
		btnPrint.setBounds(23, 662, 90, 36);
		getContentPane().add(btnPrint);

		JButton btnClear = new JButton("iklaAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(239, 662, 90, 36);
		getContentPane().add(btnClear);

		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBounds(353, 662, 90, 36);
		getContentPane().add(btnHome);

		JLabel lblTaairkaInavada = new JLabel("taairKa inavaDa");
		lblTaairkaInavada.setForeground(Color.WHITE);
		lblTaairkaInavada.setFont(new Font("Kiran", Font.BOLD, 20));
		lblTaairkaInavada.setBounds(410, 10, 90, 20);
		getContentPane().add(lblTaairkaInavada);

		fromDate = new DatePicker();

		fromDate.setDateToToday();
		fromDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fromDate.setBounds(410, 35, 170, 30);
		getContentPane().add(fromDate);

		toDate = new DatePicker();
		toDate.setDateToToday();

		toDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		toDate.setBounds(410, 82, 170, 30);
		getContentPane().add(toDate);

		JButton btnLoad = new JButton("laaoD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		btnLoad.setFont(new Font("Kiran", Font.BOLD, 25));
		// btnLoad.setFont(CommonLogic.);
		btnLoad.setBounds(16, 82, 90, 35);
		getContentPane().add(btnLoad);

		JButton btnSavaInavada = new JButton("sava- ibalao");
		btnSavaInavada.addActionListener(e -> selectAll());

		btnSavaInavada.setFont(new Font("Kiran", Font.BOLD, 25));
		btnSavaInavada.setBounds(118, 82, 122, 35);
		getContentPane().add(btnSavaInavada);

		JButton btnIrsaot = new JButton("irsaoT");
		btnIrsaot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCode.setText("");
				tblmodel.setRowCount(0);
				txtCustomername.setText("");
			}
		});
		btnIrsaot.setFont(new Font("Kiran", Font.BOLD, 25));
		btnIrsaot.setBounds(257, 82, 122, 35);
		getContentPane().add(btnIrsaot);

		JButton btnPrintA = new JButton("Print A4");
		btnPrintA.addActionListener(e -> printA4());
		btnPrintA.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnPrintA.setBounds(123, 662, 90, 36);
		getContentPane().add(btnPrintA);

		setVisible(true);
	}

	private void printA4() {

		try {
			int bill = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
				if (checked) {

					bill = Integer.parseInt("" + table.getValueAt(i, 1));
				}

			}
			new A4Bill(bill);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Printing  Bill" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		BasicConfigurator.configure();

		new SelectMultipleBillsToPrint();
		// TODO Auto-generated method stub

	}

	void findItem(String find) {
		model.removeAllElements();

		try {
			for (int i = 0; i < list.size(); i++) {
				if (((String) list.get(i)).startsWith(find)) {

					model.addElement((String) list.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	void load() {
		try {
			if (txtCustomername.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Select Customer Name", "Empty Field", JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblmodel.setRowCount(0);
			showError("" + fromDate.getDate());
			String name = txtCustomername.getText();
			int id = CommonLogic.getCustomerIdUsingName(name);

			Iterator<Bill> it = GetData.getPeriodBills(fromDate.getDate(), toDate.getDate(), id).iterator();
			Bill bill;
			while (it.hasNext()) {
				bill = it.next();
				tblmodel.addRow(new Object[] { false, bill.getBillNO(), bill.getBillDate(), bill.getBillAmt() });
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			showError(e.getMessage());
		}

	}

	void print() {
		try {
			if (tblmodel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "No data to print", "Empty Table", JOptionPane.ERROR_MESSAGE);
				return;
			}

			List<Integer> bill = new ArrayList<Integer>();
			for (int i = 0; i < table.getRowCount(); i++) {
				Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
				if (checked) {

					bill.add(Integer.parseInt("" + table.getValueAt(i, 1)));

				}
			}
			System.out.println("Selected " + bill);
			new MultipleBills(bill);
			try {
				String name = "Multiple Bills";
				File htmlFile = new File("D:\\Hotel Software\\" + name + ".pdf");
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (Exception e1) {
				System.out.println("Error in printing " + e1.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			// TODO: handle exception
		}
	}

	void selectAll() {
		try {
			if (txtCustomername.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Select Customer Name", "Empty Field", JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblmodel.setRowCount(0);
			String name = txtCustomername.getText();
			int id = CommonLogic.getCustomerIdUsingName(name);
			ResultSet rs = CommonMethods.selectQuery(
					"select Billno,Date_Format(BillDate,'%d-%m-%Y'),BillAmt from Bill where CustomerID=" + id);
			while (rs.next()) {
				tblmodel.addRow(new Object[] { false, rs.getInt(1), rs.getString(2), rs.getFloat(3) });
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			return;
			// TODO: handle exception
		}
	}
}

package kush.reports;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.SetData;
import kush.POJO.BankTransaction;
import kush.POJO.EmployeeSalary;

public class CalculateEmployeeSalary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 849405029270998743L;
	private JTextField txtId;
	private JComboBox<String> cmbEmployeeName;
	private JMonthChooser month;
	private JYearChooser year;
	private JTable table;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private JTextField txtBankCode;
	private JComboBox<String> cmbBankName;
	private JTextField txtChequeNo;
	private JTextField txtAmaount;

	public CalculateEmployeeSalary() {
		// setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CalculateEmployeeSalary.class.getResource("/kush/images/Rounded.ico")));
		setType(Type.POPUP);
		setResizable(false);
		setBackground(SystemColor.inactiveCaption);
		setForeground(Color.RED);
		setTitle("Calculate Employee Salary");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(725, 753);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("naM");
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(32, 27, 11, 24);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtId.setEditable(false);
		txtId.setBounds(24, 50, 86, 35);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblKamagaaracaoNaava = new JLabel("kamagaaracao naava");
		lblKamagaaracaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblKamagaaracaoNaava.setBounds(137, 27, 103, 24);
		getContentPane().add(lblKamagaaracaoNaava);

		cmbEmployeeName = new JComboBox<>(new Vector<>(CommonLogic.getAllEmployee()));
		cmbEmployeeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(cmbEmployeeName.getSelectedItem());
			}
		});
		cmbEmployeeName.setSelectedItem("");

		cmbEmployeeName.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbEmployeeName.setBounds(122, 50, 266, 35);
		getContentPane().add(cmbEmployeeName);

		JLabel lblKamaacaaMaihnaa = new JLabel("kamaacaa maihnaa");
		lblKamaacaaMaihnaa.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblKamaacaaMaihnaa.setBounds(396, 27, 103, 24);
		getContentPane().add(lblKamaacaaMaihnaa);

		month = new JMonthChooser();
		month.setFont(new Font("Dialog", Font.PLAIN, 15));
		month.setBounds(396, 50, 125, 35);
		getContentPane().add(month);

		JLabel lblKamaacaoVaya = new JLabel("kamaacao vaYa-");
		lblKamaacaoVaya.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblKamaacaoVaya.setBounds(529, 27, 71, 24);
		getContentPane().add(lblKamaacaoVaya);

		year = new JYearChooser();
		year.setFont(new Font("Dialog", Font.BOLD, 15));
		year.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 20));
		year.setBounds(529, 50, 98, 35);
		getContentPane().add(year);

		JButton btnLoad = new JButton("laaoD");
		btnLoad.addActionListener(e -> load());
		btnLoad.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnLoad.setBounds(20, 101, 90, 35);
		getContentPane().add(btnLoad);

		JButton btnClear = new JButton("@laIAr");
		btnClear.addActionListener(e -> model.setRowCount(0));
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(132, 101, 90, 35);
		getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 158, 696, 375);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(SystemColor.menuText);
		table.setRowHeight(25);
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		table.setModel(model);
		model.addColumn("Sr.No");
		model.addColumn("Employee Name");
		model.addColumn("Basic Salary");
		model.addColumn("Working Days");
		model.addColumn("Per Day Salary");
		model.addColumn("Total Salary");
		model.addColumn("Select");

		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);

		JButton btnExit = new JButton("baahor");
		btnExit.addActionListener(e -> dispose());
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(248, 101, 90, 35);
		getContentPane().add(btnExit);

		JPanel panelBank = new JPanel();
		panelBank.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelBank.setBounds(6, 581, 696, 137);
		getContentPane().add(panelBank);
		panelBank.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ba^Mk  kaoD");
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(16, 6, 61, 24);
		panelBank.add(lblNewLabel_1);

		txtBankCode = new JTextField();
		txtBankCode.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtBankCode.setBounds(6, 25, 122, 35);
		panelBank.add(txtBankCode);
		txtBankCode.setColumns(10);

		cmbBankName = new JComboBox<>(new Vector<>(CommonLogic.getBankName()));
		cmbBankName.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbBankName.setBounds(128, 26, 262, 35);
		panelBank.add(cmbBankName);

		txtChequeNo = new JTextField();
		txtChequeNo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtChequeNo.setColumns(10);
		txtChequeNo.setBounds(390, 28, 158, 35);
		panelBank.add(txtChequeNo);

		JLabel lblBamkocaoNaava = new JLabel("ba^Mkocao naava");
		lblBamkocaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblBamkocaoNaava.setBounds(133, 6, 66, 24);
		panelBank.add(lblBamkocaoNaava);

		JLabel lblCaokNam = new JLabel("caok  naM");
		lblCaokNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblCaokNam.setBounds(393, 6, 66, 24);
		panelBank.add(lblCaokNam);

		txtAmaount = new JTextField();
		txtAmaount.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtAmaount.setColumns(10);
		txtAmaount.setBounds(550, 28, 122, 35);
		panelBank.add(txtAmaount);

		JLabel lblRkma = new JLabel("r@kma");
		lblRkma.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblRkma.setBounds(551, 6, 66, 24);
		panelBank.add(lblRkma);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnSave.setBounds(6, 72, 90, 35);
		panelBank.add(btnSave);

		JButton btnClear2 = new JButton("@laIAr");
		btnClear2.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnClear2.setBounds(109, 72, 90, 35);
		panelBank.add(btnClear2);

		JButton btnPagaar = new JButton("pagaar");
		panelBank.setVisible(false);
		btnPagaar.addActionListener(e -> {
			if (table.getSelectedRow() == -1) {
				showError("Select First");
			} else {
				panelBank.setVisible(true);
				txtAmaount.setText("" + model.getValueAt(table.getSelectedRow(), 5));

			}

		});
		btnPagaar.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnPagaar.setBounds(6, 534, 90, 35);
		getContentPane().add(btnPagaar);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		new CalculateEmployeeSalary();
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void load() {
		try {
			int row = model.getRowCount();
			System.out.println(row);
			for (int i = 0; i < row; i++) {
				System.out.println(model.getValueAt(i, 1).toString());
				String name[] = model.getValueAt(i, 1).toString().split(">");
				if (name[2].equals(cmbEmployeeName.getSelectedItem().toString())) {
					return;
				}
			}
			// model.setRowCount(0);
			int sr = 0;
			float salary = CommonLogic
					.getEmployeeDefSalary(CommonLogic.getEmployeeId(cmbEmployeeName.getSelectedItem().toString()));
			float workingDays = CommonLogic.getEmployeeAttendance(month.getMonth()
					+ 1,
					CommonLogic.getEmployeeId(cmbEmployeeName.getSelectedItem().toString()), year.getYear());

			float perDaySalary = salary / getDaysFromMonth(month.getMonth(), year.getYear());
			float totalSalary = workingDays * perDaySalary;
			model.addRow(new Object[] { ++sr, htmstart + cmbEmployeeName.getSelectedItem(), salary, workingDays,
					perDaySalary, totalSalary, false });

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private int getDaysFromMonth(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		int date = 1;
		calendar.set(year, month, date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("Number of Days: " + days);
		return days;
	}

	private int validateData() {
		try {
			if (!cmbBankName.getSelectedItem().equals("k^Sa Aka{MT")) {
				if (txtChequeNo.getText().equals("")) {
					showError("Enter Cheque No");
					txtChequeNo.requestFocus();
					return 0;
				} else {
					return 1;
				}

			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	private void save() {
		try {
			if (validateData() != 1) {
				return;
			} // <html><font face="kiran" size="6">AMkuSa saavaLorama saupanar

			BankTransaction bt = new BankTransaction();
			bt.setId(CommonMethods.getId("select Id from BankTransaction order by Id"));
			bt.setBankId(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()));
			bt.setChequeNo(txtChequeNo.getText());
			bt.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			bt.setDeposite(0.0);
			bt.setWithdraw(Double.parseDouble(txtAmaount.getText()));
			bt.setParticulars("pagaar");

			String name[] = model.getValueAt(table.getSelectedRow(), 1).toString().split(">");
			int empid = CommonLogic.getEmployeeId(name[2]);
			EmployeeSalary esal = new EmployeeSalary();
			esal.setID(CommonMethods.getId("select Id from EmployeeSalary order by Id"));
			esal.setEmpId(empid);
			esal.setMonth(month.getMonth() + 1);
			esal.setYear(year.getYear());
			esal.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			esal.setSalaryPaid(Double.parseDouble(txtAmaount.getText()));
			esal.setPayId(bt.getId());

			if (CommonLogic.getAvailableCash(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString())) >= bt
					.getWithdraw()) {
				if (SetData.saveBankTransaction(bt) == 1) {
					if (SetData.saveEmployeeSalasry(esal) == 1) {
						CommonMethods.reduceCashFromAccount(
								CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()), bt.getWithdraw());
						JOptionPane.showMessageDialog(this, "Record Save Success", "Success",
								JOptionPane.INFORMATION_MESSAGE);

					}
				}
			} else
				showError("Insufficient Bank Balance");

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}

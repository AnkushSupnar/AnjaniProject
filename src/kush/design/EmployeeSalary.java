package kush.design;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.BasicConfigurator;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import ankush.AddPOJO;
import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;

public class EmployeeSalary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4926429697489830550L;
	private JComboBox<String> cmbName;
	private JDateChooser date;
	private JMonthChooser month;
	private JYearChooser year;
	private JLabel lblMahinaa;
	private JLabel lblVaya;
	private JTextField txtAmount;
	private JLabel lblRkma;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnClear, btnSave;
	String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private JLabel lblIdlyaacaiTaairka;

	public EmployeeSalary() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Font lblFont = new Font("Kiran", Font.BOLD, 20);
		Font txtFont = new Font("Kiran", Font.BOLD, 20);
		Font btnFont = new Font("Kiran", Font.BOLD, 25);

		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("kamagaaracao naava");
		lblName.setFont(lblFont);

		lblName.setBounds(20, 118, 99, 20);
		getContentPane().add(lblName);

		cmbName = new JComboBox<String>(new Vector<>(CommonLogic.getAllEmployee()));
		cmbName.setBounds(20, 145, 254, 35);
		cmbName.setFont(txtFont);
		getContentPane().add(cmbName);

		month = new JMonthChooser();
		month.setFont(new Font("Dialog", Font.BOLD, 15));
		month.setBounds(20, 60, 130, 35);
		getContentPane().add(month);

		year = new JYearChooser();
		year.setFont(new Font("Dialog", Font.BOLD, 15));
		year.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 20));
		year.setBounds(156, 60, 98, 35);
		getContentPane().add(year);

		lblMahinaa = new JLabel("mahInaa");

		lblMahinaa.setFont(new Font("Kiran", Font.BOLD, 20));
		lblMahinaa.setBounds(20, 35, 41, 20);
		getContentPane().add(lblMahinaa);

		lblVaya = new JLabel("vaYa-");

		lblVaya.setFont(new Font("Kiran", Font.BOLD, 20));
		lblVaya.setBounds(164, 36, 41, 20);
		getContentPane().add(lblVaya);

		txtAmount = new JTextField();
		txtAmount.setFont(txtFont);
		txtAmount.setBounds(276, 145, 145, 35);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		lblRkma = new JLabel("r@kma");

		lblRkma.setFont(new Font("Kiran", Font.BOLD, 20));
		lblRkma.setBounds(276, 118, 41, 20);
		getContentPane().add(lblRkma);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 337, 736, 304);
		getContentPane().add(scrollPane);

		model = new DefaultTableModel(new Object[][] {}, new String[] { "SrNo", "Employee Name", "Month", "Year",
				"Paid Salary", "Date", "Remaining", "Total Salary" });
		table = new JTable(model);
		table.setShowVerticalLines(true);
		table.setRowHeight(25);
		table.getShowHorizontalLines();
		table.getShowVerticalLines();
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		table.setShowHorizontalLines(true);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(290);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);

		scrollPane.setViewportView(table);

		btnSave = new JButton("saova");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnSave.setFont(btnFont);
		btnSave.setBounds(6, 290, 90, 35);
		getContentPane().add(btnSave);

		btnClear = new JButton("klaIAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(121, 290, 90, 35);
		getContentPane().add(btnClear);

		date = new JDateChooser();
		date.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// String date1 = ((JTextField) date.getDateEditor().getUiComponent()).getText()
				// + "";
				// ((JTextField) date.getDateEditor().getUiComponent().setText)
			}
		});
		date.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
		date.setDateFormatString("dd/MM/YYYY");
		date.setFont(new Font("Tahoma", Font.BOLD, 15));
		// String date1 = ((JTextField) date.getDateEditor().getUiComponent()).getText()
		// + "";
		date.setBounds(428, 145, 164, 35);
		getContentPane().add(date);

		JButton btnLoad = new JButton("laaoD");
		btnLoad.addActionListener(e -> loadTable(month.getMonth() + 1, year.getYear()));
		btnLoad.setFont(new Font("Kiran", Font.BOLD, 25));
		btnLoad.setBounds(266, 60, 90, 35);
		getContentPane().add(btnLoad);

		lblIdlyaacaiTaairka = new JLabel("idlyaacaI taairKa");
		lblIdlyaacaiTaairka.setFont(new Font("Kiran", Font.BOLD, 20));
		lblIdlyaacaiTaairka.setBounds(435, 118, 104, 20);
		getContentPane().add(lblIdlyaacaiTaairka);
		setTitle("Employee payment");
		setSize(764, 686);
		// loadTable();
		setVisible(true);
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		BasicConfigurator.configure();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		new EmployeeSalary();
		// TODO Auto-generated method stub

	}

	private void clear() {
		txtAmount.setText("");
	}

	private void validateData() {
		if (txtAmount.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter Amount", "Empty", JOptionPane.ERROR_MESSAGE);
			txtAmount.requestFocus();
			return;
		}
	}

	private void save() {
		try {
			validateData();
			kush.POJO.EmployeeSalary emp = new kush.POJO.EmployeeSalary();
			emp.setID(CommonMethods.getId("Select ID from employeesalary order by ID"));
			emp.setMonth(month.getMonth() + 1);
			emp.setYear(year.getYear());
			emp.setEmpId(CommonLogic.getEmployeeId("" + cmbName.getSelectedItem()));
			// String date1 = ((JTextField) date.getDateEditor().getUiComponent()).getText()
			// + "";
			// emp.setDate(((JTextField) date.getDateEditor().getUiComponent()).getText());
			// emp.setSalaryPaid(Float.parseFloat(txtAmount.getText()));
			System.out.println(emp);

			int flag = AddPOJO.saveEmployeeSalary(emp);
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Record Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Saving Record " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void loadTable(int month, int year) {
		try {
			System.out.println("Loading Data for " + month + " " + year);
			model.setRowCount(0);
			int sr = 0;
			kush.POJO.EmployeeSalary es = null;

			Iterator<kush.POJO.EmployeeSalary> it = GetData.getEmployeeSalaryMonthWise(month, year).iterator();
			while (it.hasNext()) {
				es = it.next();
				System.out.println("Fefault Salary" + CommonLogic.getEmployeeDefSalary(es.getEmpId()));
				model.addRow(new Object[] { ++sr, htmstart + CommonLogic.getemployeeFullName(es.getEmpId()),
						es.getMonth(), es.getYear(), es.getSalaryPaid(), es.getDate(),
						(CommonLogic.getEmployeeDefSalary(es.getEmpId()) - es.getSalaryPaid()),
						CommonLogic.getEmployeeDefSalary(es.getEmpId()) });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Loadin Table " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

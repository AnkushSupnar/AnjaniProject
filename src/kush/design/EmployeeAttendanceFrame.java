package kush.design;

import java.awt.Font;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import ankush.SetData;
import kush.POJO.EmployeeAttendance;

public class EmployeeAttendanceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192607409470973399L;
	private JComboBox<String> cmbEmployee;
	private DatePicker date;

	// private int id;
	private JRadioButton rdbtnHalfDay, rdbtnPresent, rdbtnAbsent;
	private JButton btnSave;
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnExit;
	private ButtonGroup group;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNam;
	private JTextField txtId;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private JButton btnLaaod;

	public EmployeeAttendanceFrame(int id) {
		// this.id = id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(634, 620);
		setLocation(200, 50);

		// getContentPane().setBackground(new Color(204, 204, 204));
		getContentPane().setLayout(null);

		lblNam = new JLabel("naM");
		lblNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNam.setBounds(17, 29, 11, 24);
		getContentPane().add(lblNam);

		txtId = new JTextField("" + CommonMethods.getId("select Id from employeeattendance order by Id"));
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtId.setEditable(false);
		txtId.setBounds(34, 28, 94, 35);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblIdnaamk = new JLabel("idnaaMk");
		lblIdnaamk.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblIdnaamk.setBounds(151, 29, 50, 24);
		getContentPane().add(lblIdnaamk);

		date = new DatePicker();
		date.setBounds(200, 29, 215, 35);
		date.setFont(new Font("Tahoma", Font.BOLD, 15));
		date.setDateToToday();

		getContentPane().add(date);
		if (id != 1) {
			date.setEnabled(false);
		}

		JLabel lblNewLabel = new JLabel("kamagaaracao naava");
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(17, 77, 103, 24);
		getContentPane().add(lblNewLabel);

		cmbEmployee = new JComboBox<String>(new Vector<>(CommonLogic.getAllEmployee()));
		cmbEmployee.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbEmployee.setBounds(132, 74, 286, 35);
		getContentPane().add(cmbEmployee);

		rdbtnPresent = new JRadioButton("hjar ");
		rdbtnPresent.setFont(new Font("Kiran", Font.PLAIN, 25));
		rdbtnPresent.setBounds(17, 120, 61, 24);
		getContentPane().add(rdbtnPresent);

		rdbtnHalfDay = new JRadioButton("AaQaa- idvasa");
		rdbtnHalfDay.setFont(new Font("Kiran", Font.PLAIN, 25));
		rdbtnHalfDay.setBounds(125, 120, 102, 24);
		getContentPane().add(rdbtnHalfDay);

		rdbtnAbsent = new JRadioButton("gaOrhjar");
		rdbtnAbsent.setFont(new Font("Kiran", Font.PLAIN, 25));
		rdbtnAbsent.setBounds(266, 120, 76, 24);
		getContentPane().add(rdbtnAbsent);

		group = new ButtonGroup();
		group.add(rdbtnPresent);
		group.add(rdbtnHalfDay);
		group.add(rdbtnAbsent);

		btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(17, 171, 107, 36);
		getContentPane().add(btnSave);

		btnClear = new JButton("@laIAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(156, 171, 107, 36);
		getContentPane().add(btnClear);

		btnUpdate = new JButton("ApaDoT");
		btnUpdate.addActionListener(e -> update());
		btnUpdate.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnUpdate.setBounds(294, 171, 107, 36);
		getContentPane().add(btnUpdate);
		if (id != 1) {
			btnUpdate.setVisible(false);
			JLabel d = new JLabel("" + date.getDate());
			d.setBounds(date.getBounds());
			getContentPane().add(d);
		}

		btnExit = new JButton("baahor");
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(428, 171, 107, 36);
		getContentPane().add(btnExit);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 219, 580, 356);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowHeight(25);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "SrNo", "Employee Name", "Date", "Attendance" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		btnLaaod = new JButton("laaoD");
		btnLaaod.addActionListener(e -> loadTable());
		btnLaaod.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnLaaod.setBounds(427, 28, 107, 36);
		getContentPane().add(btnLaaod);

		model = (DefaultTableModel) table.getModel();

		loadTable();
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		new EmployeeAttendanceFrame(1);

	}

	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public int validateData() {
		try {
			if (date.getDate() == null) {
				showError("Select Date");
				return 0;
			} else if (cmbEmployee.getSelectedItem().equals("")) {
				showError("Select Employee Name");
				return 0;
			} else if (group.getSelection() == null) {
				showError("Select Attendance Present or Absent");
				return 0;
			} else
				return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	public void loadTable() {
		try {
			model.setRowCount(0);
			int sr = 0;
			Iterator<EmployeeAttendance> it = GetData.getEmployeeAttendanceList(date.getDate()).iterator();
			while (it.hasNext()) {
				EmployeeAttendance ea = new EmployeeAttendance();
				ea = it.next();
				model.addRow(new Object[] { ++sr, htmstart + CommonLogic.getemployeeFullName(ea.getEmployeeId()),
						ea.getDate(), htmstart + ea.getAttendance() });
			}

		} catch (Exception e) {
			showError(e.getMessage());
		}

	}

	public int checkData() {
		try {
			for (int i = 0; i < model.getRowCount(); i++) {
				String name[] = model.getValueAt(i, 1).toString().split(">");
				if (name[2].equals(cmbEmployee.getSelectedItem().toString())) {
					return 1;
				}

			}
			return 0;

		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	public void save() {
		try {
			if (validateData() != 1) {
				return;
			}
			EmployeeAttendance ea = new EmployeeAttendance();
			ea.setId(Integer.parseInt(txtId.getText()));

			ea.setDate(date.getDate());
			ea.setEmployeeId(CommonLogic.getEmployeeId(cmbEmployee.getSelectedItem().toString()));
			if (rdbtnAbsent.isSelected())
				ea.setAttendance("gaOrhjar");
			else if (rdbtnPresent.isSelected())
				ea.setAttendance("hjar");
			else
				ea.setAttendance("AaQaa- idvasa");
			if (ea.getId() == CommonMethods.getId("select Id from EmployeeAttendance order by id")) {
				// save
				if (checkData() == 1) {
					showError("Already Save Attendance for this Employee");
					return;
				}
				if (SetData.saveEmployeeAttendance(ea) == 1) {
					JOptionPane.showMessageDialog(this, "Record Save Success", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					loadTable();
					clear();
				} else
					showError("Error in Saving Record");
			} else {
				if (SetData.updateEmployeeAttendance(ea) == 1) {
					JOptionPane.showMessageDialog(this, "Record update Success", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					loadTable();
					clear();
				} else
					showError("Error in updating Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}
	}

	public void clear() {
		try {
			txtId.setText("" + CommonMethods.getId("select Id from EmployeeAttendance order by Id"));
			group.clearSelection();
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	public void update() {
		try {
			if (table.getSelectedRow() == -1) {
				return;
			}
			txtId.setText("" + model.getValueAt(table.getSelectedRow(), 0));
			String name[] = model.getValueAt(table.getSelectedRow(), 1).toString().split(">");
			cmbEmployee.setSelectedItem(name[2]);
			String pr[] = model.getValueAt(table.getSelectedRow(), 3).toString().split(">");

			if (pr[2].equals("hjar"))
				rdbtnPresent.setSelected(true);
			else if (pr[2].equals("gaOrhjar"))
				rdbtnAbsent.setSelected(true);
			else
				rdbtnHalfDay.setSelected(true);

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}

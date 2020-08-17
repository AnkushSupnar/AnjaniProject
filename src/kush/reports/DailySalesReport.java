package kush.reports;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import ankush.CommonLogic;
import ankush.CommonMethods;

public class DailySalesReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6275494195798412003L;
	private JTable table;
	private DefaultTableModel model;
	private DatePicker dateChooser;

	public DailySalesReport() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(200, 10);
		setTitle("Daily Report");
		JLabel lblAajacaiTaairka = new JLabel("AajacaI taairKa");
		lblAajacaiTaairka.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblAajacaiTaairka.setBounds(10, 21, 102, 24);
		getContentPane().add(lblAajacaiTaairka);

		dateChooser = new DatePicker();
		dateChooser.setDateToToday();
		dateChooser.setBounds(122, 11, 159, 35);
		dateChooser.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		getContentPane().add(dateChooser);
		// SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
		// final String date2 = df.format(dateChooser.getDate());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 785, 636);
		getContentPane().add(scrollPane);

		model = new DefaultTableModel();
		model.addColumn("BillNo");
		model.addColumn("Received Amount");
		model.addColumn("Discount");
		model.addColumn("Bill Amount");
		model.addColumn("Pay Mode");
		model.addColumn("User Name");
		model.addColumn("Waitor Name");
		model.addColumn("Table Name");
		table = new JTable(model);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table.setRowHeight(25);
		loadReport(dateChooser.getDate());
		scrollPane.setViewportView(table);

		JButton btnShow = new JButton("paha");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadReport(dateChooser.getDate());
			}
		});
		btnShow.setFont(new Font("Kiran", Font.BOLD, 25));
		btnShow.setBounds(305, 11, 102, 33);
		getContentPane().add(btnShow);

		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBounds(419, 11, 89, 34);
		getContentPane().add(btnHome);
		setSize(821, 760);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setVisible(true);

	}

	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		CommonMethods.openConnection();

		new DailySalesReport();

	}

	void loadReport(LocalDate Date) {
		LocalDate date = Date;
		model.setRowCount(0);
		String query = "select Billno, BillAmt, Discount,(BillAmt-Discount) as RecievedAmount, Paymode, userid,Waitorid,Tableno from Bill where status='paid' and DATE(billdate) = DATE('"
				+ date + "')";
		System.out.println("Geted Query " + query);
		try {
			String htmstart = "<html><font face=\"kiran\" size=\"6\">";
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next()) {
				String uname = htmstart + CommonLogic.getUserName(rs.getInt(6));
				String wname = htmstart + CommonLogic.getWaitorName(rs.getInt(7));
				String tname = CommonLogic.getTableName(rs.getInt(8));
				model.addRow(new Object[] { rs.getInt(1), rs.getFloat(4), rs.getFloat(3), rs.getFloat(2),
						rs.getString(5), uname, wname, tname });
			}
			rs.close();
			if (model.getRowCount() > 0) {
				int row = model.getRowCount();
				// System.out.println("Total Rows"+row);
				int billcount = 0;
				float billamtcount = 0.0f, dicountcount = 0.0f, receivedamtcount = 0.0f;

				int billcount1 = 0;
				float billamtcount1 = 0.0f, dicountcount1 = 0.0f, receivedamtcount1 = 0.0f;

				for (int i = 0; i < row; i++) {
					if (model.getValueAt(i, 4).toString().equals("Cash")) {
						// System.out.println(model.getValueAt(i, 0));
						billcount = billcount + Integer.parseInt("" + model.getValueAt(i, 0));
						billamtcount = billamtcount + Float.parseFloat("" + model.getValueAt(i, 1));
						dicountcount = dicountcount + Float.parseFloat("" + model.getValueAt(i, 2));
						receivedamtcount = receivedamtcount + Float.parseFloat("" + model.getValueAt(i, 3));
					} else {
						billcount1 = billcount1 + Integer.parseInt("" + model.getValueAt(i, 0));
						billamtcount1 = billamtcount1 + Float.parseFloat("" + model.getValueAt(i, 1));
						dicountcount1 = dicountcount1 + Float.parseFloat("" + model.getValueAt(i, 2));
						receivedamtcount1 = receivedamtcount1 + Float.parseFloat("" + model.getValueAt(i, 3));
					}
				}
				String htmstart2 = "<html><b><font face=\"Times new Roman\" size=\"5\">";
				model.addRow(new Object[] {});
				model.addRow(new Object[] { htmstart2 + "Cash", htmstart2 + billamtcount, htmstart2 + dicountcount,
						htmstart2 + receivedamtcount });
				model.addRow(new Object[] { htmstart2 + "Credit", htmstart2 + billamtcount1, htmstart2 + dicountcount1,
						htmstart2 + receivedamtcount1 });
				model.addRow(new Object[] { htmstart2 + "Total", htmstart2 + (billamtcount1 + billamtcount),
						htmstart2 + (dicountcount1 + dicountcount),
						htmstart2 + (receivedamtcount1 + receivedamtcount) });
			}
		} catch (Exception e) {
			System.out.println("Error in Load Data= " + e.getMessage());
			e.printStackTrace();
		}
	}
}

package kush.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import ankush.CommonMethods;

public class ViewStock extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7606156329957784643L;
	private JTable table;
	private DefaultTableModel model;

	public ViewStock() {
		@SuppressWarnings("unused")
		Font lblFont = new Font("Kiran", Font.BOLD, 20);
		Font txtFont = new Font("Kiran", Font.PLAIN, 20);
		@SuppressWarnings("unused")
		Font btnFont = new Font("Kiran", Font.BOLD, 25);
		setLocation(new Point(400, 100));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 102));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);

		model = new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "Item Name", "Available Stock" });

		table = new JTable(model);
		table.setFont(txtFont);
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		setSize(585, 700);
		loadStock();
		setVisible(true);

	}

	public static void main(String[] args) {
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
		new ViewStock();
	}

	private void loadStock() {
		try {

			model.setRowCount(0);
			ResultSet rs = CommonMethods.selectQuery("select Id,ItemName,Stock from itemstock order by Stock");
			while (rs.next()) {
				model.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getFloat(3) });
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error in Loading Stock");
			return;
		}
	}
}

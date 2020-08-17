package kush.design;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.jidesoft.dialog.JideOptionPane;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import ankush.SetData;

public class EnglishName extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7448245596440450379L;
	private JComboBox<String> cmbCategory;
	private JTable table;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";

	public EnglishName() {
		setTitle("English Name For Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(663, 680);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("maalaacaI k^ToigarI");
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(21, 24, 112, 24);
		getContentPane().add(lblNewLabel);

		cmbCategory = new JComboBox<>(new Vector<String>(CommonLogic.getCategory()));
		cmbCategory.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbCategory.setBounds(21, 50, 273, 35);
		getContentPane().add(cmbCategory);

		JButton btnLoad = new JButton("laaoD");
		btnLoad.addActionListener(e -> load());
		btnLoad.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnLoad.setBounds(295, 50, 90, 35);
		getContentPane().add(btnLoad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 100, 606, 473);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 18));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setRowHeight(25);
		scrollPane.setViewportView(table);

		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Sr.No", "Item Name(English)", "Item Name(Marathi)" }));

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(21, 585, 90, 35);
		getContentPane().add(btnSave);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		table.getColumnModel().getColumn(2).setPreferredWidth(263);
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
		new EnglishName();
	}

	private void showError(String msg) {
		JideOptionPane.showMessageDialog(this, msg, "Error", JideOptionPane.ERROR_MESSAGE);
	}

	private void load() {
		try {
			int sr = 0;
			model.setRowCount(0);
			Iterator<String[]> it = GetData
					.getEnglishItemName(CommonLogic.getCatId(cmbCategory.getSelectedItem().toString())).iterator();
			while (it.hasNext()) {
				String name[] = it.next();
				model.addRow(new Object[] { ++sr, htmstart + name[0], name[1] });
			}
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}

	}

	private void save() {
		try {
			// Read Table Data
			int row = table.getRowCount();
			List<Object[]> list = new ArrayList<>();
			for (int i = 0; i < row; i++) {
				Object[] obj = new Object[4];
				String name[] = model.getValueAt(i, 1).toString().split(">");
				obj[0] = model.getValueAt(i, 0);
				obj[1] = name[2];
				obj[2] = model.getValueAt(i, 2);
				obj[3] = CommonLogic.getCatId(cmbCategory.getSelectedItem().toString());
				list.add(obj);
			}
			if (SetData.saveEnglishName(list) == 1) {
				JOptionPane.showMessageDialog(this, "Record Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			// for (Object[] obj : list) {
				// System.out.print(obj[0] + " " + obj[1] + " " + obj[2] + "\n");

			// }
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

}

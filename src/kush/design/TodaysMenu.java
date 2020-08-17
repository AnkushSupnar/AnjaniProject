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

public class TodaysMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430318527053815065L;
	private JComboBox<String> cmbCategory;
	private JTable table;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";

	public TodaysMenu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(561, 701);
		setTitle("Todays Menu");
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
		scrollPane.setBounds(6, 97, 533, 504);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setRowHeight(25);
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
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		model.addColumn("Sr.No");
		model.addColumn("Item Name");
		model.addColumn("Status");

		table.setModel(model);

		JButton btnSave = new JButton("saovh ");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnSave.setBounds(6, 613, 90, 35);
		getContentPane().add(btnSave);
		table.getColumnModel().getColumn(1).setPreferredWidth(383);

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		new TodaysMenu();

	}

	private void showError(String msg) {
		JideOptionPane.showMessageDialog(this, msg, "Error", JideOptionPane.ERROR_MESSAGE);
	}

	private void load() {
		try {
			int sr = 0;
			model.setRowCount(0);
			// System.out.println(CommonLogic.getCatId(cmbCategory.getSelectedItem().toString()));
			Iterator<Object[]> it = GetData
					.getTodaysMenu(CommonLogic.getCatId(cmbCategory.getSelectedItem().toString())).iterator();
			while (it.hasNext()) {
				Object name[] = it.next();
				Boolean s;

				if (name[2].toString().equals("Y"))
					s = true;
				else
					s = false;
				// System.out.println(name[2] + " " + s);
				model.addRow(new Object[] { ++sr, htmstart + CommonLogic.getItemNameByID((int) name[0]), s });
			}
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}

	}

	private void save() {
		try {
			// read from Table
			int row = table.getRowCount();
			List<Object[]> list = new ArrayList<>();
			for (int i = 0; i < row; i++) {
				Object[] obj = new Object[3];
				String[] name = model.getValueAt(i, 1).toString().split(">");
				obj[0] = CommonLogic.getItemId(name[2]);
				obj[1] = CommonLogic.getCatId(cmbCategory.getSelectedItem().toString());

				Boolean checked = Boolean.valueOf(table.getValueAt(i, 2).toString());
				if (checked) {
					obj[2] = "Y";
				} else
					obj[2] = "N";

				list.add(obj);
			}
			if (SetData.saveTodaysMenu(list.iterator()) == 1) {
				JOptionPane.showMessageDialog(this, "Record Saved");
			}

		} catch (Exception e) {
			showError(e.getMessage());

		}
	}
}

package kush.design;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import ankush.CommonMethods;
import ankush.GetData;
import ankush.SetData;

public class CategoryEnglishName extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9101217676145248564L;
	private JTable table;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";

	public CategoryEnglishName() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 611);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 464, 514);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 18));
		table.setShowVerticalLines(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Sr.No.", "Category Name (Marathi)", "Category Name(English)" }));

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(10, 532, 90, 35);
		getContentPane().add(btnSave);
		table.getColumnModel().getColumn(1).setPreferredWidth(233);
		table.getColumnModel().getColumn(2).setPreferredWidth(309);
		model = (DefaultTableModel) table.getModel();
		load();
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		new CategoryEnglishName();

	}

	private void load() {
		try {
			model.setRowCount(0);
			Iterator<Object[]> it = GetData.getCategoryEnglishName().iterator();
			while (it.hasNext()) {
				Object[] obj = it.next();
				model.addRow(new Object[] { obj[0], htmstart + obj[1], obj[2] });
			}
		} catch (Exception e) {

		}
	}

	private void save() {
		try {
			List<Object[]> list = new ArrayList<>();
			int row = model.getRowCount();
			for (int i = 0; i < row; i++) {
				String name[] = model.getValueAt(i, 1).toString().split(">");
				Object[] obj = new Object[3];
				obj[0] = model.getValueAt(i, 0);
				obj[1] = name[2];
				obj[2] = model.getValueAt(i, 2);
				list.add(obj);
			}
			if (SetData.saveCategoryEnglishName(list) == 1) {
				JOptionPane.showMessageDialog(this, "Record Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error in Saving", JOptionPane.ERROR_MESSAGE);
		}
	}
}

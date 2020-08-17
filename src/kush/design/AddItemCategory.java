package kush.design;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ankush.AddPOJO;
import ankush.CommonLogic;
import ankush.CommonMethods;
import kush.POJO.CategoryMaster;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Point;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddItemCategory extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCategoryname;
	private JCheckBox chkStock;
	JComboBox<Object> cmbCategory;

	public AddItemCategory() {
		// getContentPane().setLocation(400,50);
		setLocation(new Point(400, 100));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				loadCategory();
			}
		});
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);

		JLabel lblAvailableCategory = new JLabel("k^TogaIrI");
		lblAvailableCategory.setFont(new Font("Kiran", Font.BOLD, 25));
		lblAvailableCategory.setBounds(83, 42, 60, 24);
		getContentPane().add(lblAvailableCategory);

		cmbCategory = new JComboBox<Object>();
		cmbCategory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				chkStock.setSelected(CommonLogic.getStockStatus("" + cmbCategory.getSelectedItem()));
				if (chkStock.isSelected())
					chkStock.setText("hao");
				else
					chkStock.setText("naahI");

			}
		});
		cmbCategory.setFont(new Font("Kiran", Font.BOLD, 25));
		cmbCategory.setBounds(178, 39, 250, 35);

		getContentPane().add(cmbCategory);

		JLabel lblNewCategoryName = new JLabel("naivana k^TogaIrI");
		lblNewCategoryName.setFont(new Font("Kiran", Font.BOLD, 25));
		lblNewCategoryName.setBounds(32, 89, 111, 24);
		getContentPane().add(lblNewCategoryName);

		txtCategoryname = new JTextField();
		txtCategoryname.setFont(new Font("Kiran", Font.BOLD, 25));
		txtCategoryname.setBounds(178, 87, 250, 35);
		getContentPane().add(txtCategoryname);
		txtCategoryname.setColumns(10);

		JButton btnAdd = new JButton("A^D");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnAdd.setFont(new Font("Kiran", Font.BOLD, 25));
		btnAdd.setBounds(32, 179, 90, 35);
		getContentPane().add(btnAdd);

		JButton btnClear = new JButton("klaIAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCategoryname.setText("");
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(264, 179, 90, 35);
		getContentPane().add(btnClear);

		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBounds(366, 179, 90, 35);
		getContentPane().add(btnHome);

		JLabel lblStak = new JLabel("sTa^k");
		lblStak.setFont(new Font("Kiran", Font.BOLD, 25));
		lblStak.setBounds(103, 143, 52, 24);
		getContentPane().add(lblStak);

		chkStock = new JCheckBox("");
		chkStock.setSelected(CommonLogic.getStockStatus("" + cmbCategory.getSelectedItem()));
		if (chkStock.isSelected())
			chkStock.setText("hao");
		else
			chkStock.setText("naahI");

		chkStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkStock.isSelected()) {
					chkStock.setText("hao");
				} else {
					chkStock.setText("naahI");
				}
			}
		});
		// chkStock.setText("naahI");
		chkStock.setFont(new Font("Kiran", Font.BOLD, 25));
		chkStock.setBounds(178, 140, 58, 24);
		getContentPane().add(chkStock);

		JButton btnUpdate = new JButton("ApaDoT");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Kiran", Font.BOLD, 25));
		btnUpdate.setBounds(146, 179, 90, 35);
		getContentPane().add(btnUpdate);
		setTitle("Add New Category");
		setSize(500, 295);
		// setLocation(0,0);
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
		new AddItemCategory();
		// TODO Auto-generated method stub

	}

	void loadCategory() {
		try {
			ResultSet rs = CommonMethods.selectQuery("select Category from categorymaster order by Category");
			while (rs.next()) {
				cmbCategory.addItem(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Loadin Category " + e);
			return;
		}
	}

	void save() {

		try {
			if (txtCategoryname.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter new Category name");
				return;
			}
			String stock = "N";
			if (chkStock.isSelected()) {
				stock = "Y";
			} else {
				stock = "N";
			}
			CategoryMaster c = new CategoryMaster();
			c.setId(CommonMethods.getId("select ID from CategoryMaster order by ID"));
			c.setName(txtCategoryname.getText());
			c.setStock(stock);

			if (AddPOJO.saveCategoryMaster(c) == 1) {
				JOptionPane.showMessageDialog(this, "Record Save Success");
				cmbCategory.removeAllItems();
				txtCategoryname.setText("");
				// loadCategory();

				return;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Adding Category " + e);
			return;
		}
	}

	void update() {
		try {
			if (txtCategoryname.getText().equals("")) {
				// JOptionPane.showMessageDialog(this, "Enter Categery Name");
				txtCategoryname.setText("" + cmbCategory.getSelectedItem());
				// return;
			}
			String stock = "";
			if (chkStock.isSelected()) {
				stock = "Y";
			} else
				stock = "N";
			int flag = CommonMethods.updateRecord("update categorymaster set Category='" + txtCategoryname.getText()
					+ "', Stock='" + stock + "' where Category='" + cmbCategory.getSelectedItem() + "'");
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Update Success");
			} else {
				System.out.println("Error " + flag);
			}
			txtCategoryname.setText("");
			chkStock.setSelected(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Update" + e.getMessage());

		}
	}
}

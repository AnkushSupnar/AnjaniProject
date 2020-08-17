package kush.design;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import com.jidesoft.swing.AutoCompletionComboBox;

import ankush.CommonLogic;
import ankush.CommonMethods;
import kush.POJO.Item;

public class AddNewItemFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AutoCompletionComboBox cmbCategory;
	private JTextField txtItemname;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtRate;
	private JTextField txtItemcode;
	private Font font = CommonLogic.font;

	public AddNewItemFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				loadCategory();
				// AutoCompletion.enable(cmbCategory);
				// AutoCompleteDecorator.decorate(cmbCategory);
				loadDataTable();
			}
		});
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel lblSekectCategory = new JLabel("Sekect category");
		lblSekectCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSekectCategory.setBounds(26, 45, 111, 20);
		getContentPane().add(lblSekectCategory);

		// cmbCategory = new JComboBox<Object>();
		cmbCategory = new AutoCompletionComboBox();
		cmbCategory.setFont(font);
		cmbCategory.setBounds(149, 43, 241, 30);
		// AutoCompleteDecorator.decorate(cmbCategory);
		// AutoCompletionComboBox(cmbCategory);
		// ComboBoxSearchable searchable = new ComboBoxSearchable(comboBox);

		getContentPane().add(cmbCategory);

		JLabel lblNewItemName = new JLabel("New Item Name");
		lblNewItemName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewItemName.setBounds(26, 99, 113, 17);
		getContentPane().add(lblNewItemName);

		txtItemname = new JTextField();
		txtItemname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					txtItemcode.requestFocus();
				}
			}
		});
		txtItemname.setFont(font);
		txtItemname.setBounds(149, 93, 241, 30);
		getContentPane().add(txtItemname);
		txtItemname.setColumns(10);

		final JButton btnAdd = new JButton("ADD");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(26, 218, 97, 30);
		getContentPane().add(btnAdd);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtItemcode.setText("");
				txtItemname.setText("");
				txtRate.setText("");
			}
		});
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnClear.setBounds(159, 218, 97, 30);
		getContentPane().add(btnClear);

		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnHome.setBounds(448, 218, 90, 30);
		getContentPane().add(btnHome);
		model = new DefaultTableModel();
		model.addColumn("Sr.No.");
		model.addColumn("ItemCode");
		model.addColumn("ItemName");
		model.addColumn("CategoryName");
		model.addColumn("Rate");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 265, 578, 344);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowHeight(20);
		table.setFont(font);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);

		JLabel lblNewItemRate = new JLabel("New Item Rate");
		lblNewItemRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewItemRate.setBounds(24, 181, 113, 17);
		getContentPane().add(lblNewItemRate);

		txtRate = new JTextField();
		txtRate.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (e.getKeyChar() == '.'))) {
					getToolkit().beep();
					e.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					btnAdd.requestFocus();
				}
			}
		});
		txtRate.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtRate.setBounds(149, 176, 122, 30);
		getContentPane().add(txtRate);
		txtRate.setColumns(10);

		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblItemCode.setBounds(26, 142, 71, 19);
		getContentPane().add(lblItemCode);

		txtItemcode = new JTextField();
		txtItemcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					txtRate.requestFocus();
				}
			}
		});
		txtItemcode.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtItemcode.setBounds(149, 135, 122, 30);
		getContentPane().add(txtItemcode);
		txtItemcode.setColumns(10);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(e -> edit());
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnEdit.setBounds(289, 220, 90, 30);
		getContentPane().add(btnEdit);

		setSize(628, 668);
		setLocation(400, 70);
		setVisible(true);
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
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
		new AddNewItemFrame();

	}

	@SuppressWarnings("unchecked")
	void loadCategory() {
		try {

			List<String> list = CommonLogic.getCategory();
			Iterator<String> i = list.iterator();
			while (i.hasNext()) {

				cmbCategory.addItem(i.next());
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Gettin Category = " + e.getMessage());

			return;
		}
	}

	void save() {
		try {
			String name = txtItemname.getText();
			int catid = CommonLogic.getCatId((String) cmbCategory.getSelectedItem());
			float rate = Float.parseFloat(txtRate.getText());
			int id = CommonMethods.getId("select ID from Itemmaster order by ID");
			int code = Integer.parseInt(txtItemcode.getText());
			if (name.equals("")) {
				JOptionPane.showMessageDialog(this, "Fill enter Items Name!", "Empty Input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (txtItemcode.getText().equals("") || txtItemcode.getText().equals("0")) {
				JOptionPane.showMessageDialog(this, "Enter Item Code!", "Empty Input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (CommonLogic.checkName(name) == 1) {
				// JOptionPane.showMessageDialog(this, "Item Name Already Exist Record
				// Updating", "Exist",
				// JOptionPane.ERROR_MESSAGE);
				// return;
			}
			if (CommonLogic.checkCode(code) == 1) {
				// JOptionPane.showMessageDialog(this, "Item Code Already Exist!", "Exist",
				// JOptionPane.ERROR_MESSAGE);
				// return;
			}
			System.out.println("Id " + id + "\n Name=" + name + "\n catid = " + catid + "\nRate " + rate);
			Item item = findItem(name, catid, code);
			System.out.println(item);
			if (item != null) {
				try {
					CommonMethods.updateRecord(
							"update ItemMaster set Rate=" + rate + " " + "where ItemName='" + item.getItemName()
									+ "' AND CatId=" + item.getCatid() + " AND " + "ItemCode=" + item.getItemCode());
					JOptionPane.showMessageDialog(this, "Record Save", "Saves", JOptionPane.INFORMATION_MESSAGE);
					loadDataTable();
					txtItemname.setText("");
					txtRate.setText("");
					return;
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
			System.out.println("insert into ItemMaster(ID,ITEMNAME,CATID,RATE,ITEMCODE) values(" + id + ",'" + name
					+ "'," + catid + "," + rate + "," + code + ")");
			int flag = CommonMethods.addRecord("insert into ItemMaster(ID,ITEMNAME,CATID,RATE,ITEMCODE) values(" + id
					+ ",'" + name + "'," + catid + "," + rate + "," + code + ")");
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Record Save", "Saves", JOptionPane.INFORMATION_MESSAGE);
				txtItemname.setText("");
				txtRate.setText("");
				return;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Fill all entry!", "Empty Input", JOptionPane.ERROR_MESSAGE);

			System.out.println("Error in save " + e.getMessage());
			return;
		}
	}

	void loadDataTable() {
		model.setRowCount(0);
		try {
			String query = "\r\n"
					+ "select ItemMaster.Id,ItemMaster.ItemCode, Itemmaster.ItemName,Categorymaster.category,Itemmaster.rate from ItemMaster,CategoryMaster where Itemmaster.CatId=CategoryMaster.Id order by ItemMaster.ID";
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next()) {
				model.addRow(
						new Object[] { rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5) });
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Item Moading In Table", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	void edit() {
		try {

			if (table.getSelectedRow() == -1) {
				return;
			}
			// JOptionPane.showMessageDialog(this, table.getSelectedRow());
			int row = table.getSelectedRow();
			Item item = new Item();
			item.setID(Integer.parseInt(model.getValueAt(row, 1).toString()));
			item.setCatid(CommonLogic.getCatId(model.getValueAt(row, 3).toString()));
			item.setItemCode(Integer.parseInt(model.getValueAt(row, 1).toString()));
			item.setItemName(model.getValueAt(row, 2).toString());
			item.setRate(Float.parseFloat(model.getValueAt(row, 4).toString()));

			cmbCategory.setSelectedItem(CommonLogic.getCategoryName(item.getCatid()));
			txtItemcode.setText("" + item.getItemCode());
			txtItemname.setText(item.getItemName());
			txtRate.setText("" + item.getRate());

			System.out.println(item);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
	}

	public Item findItem(String itemName, int catid, int itemCode) {
		try {
			Item i = new Item();
			ResultSet rs = CommonMethods.selectQuery("select ID, ItemName, Catid, Rate, ItemCode from ItemMaster "
					+ "where ItemName='" + itemName + "' AND catid=" + catid + " And ItemCode=" + itemCode);
			if (rs.next()) {
				i.setID(rs.getInt(1));
				i.setItemName(rs.getString(2));
				i.setCatid(rs.getInt(3));
				i.setRate(rs.getFloat(4));
				i.setItemCode(rs.getInt(5));
			}
			rs.close();
			return i;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Not Found", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

}

package kush.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.jidesoft.swing.AutoCompletionComboBox;

import ankush.AddPOJO;
import ankush.CommonLogic;
import ankush.CommonMethods;
import kush.POJO.TempTransaction;
import kush.print.BillPdf;
import kush.print.QuotationPDF;

public class BillingFrame2 extends JFrame implements ActionListener {

	/**
	 * 
	 */
	public JPanel panelTable, panelV, panelA, panelB, panelE, panelD, panelC, panelG, panelP;
	private static final long serialVersionUID = 1L;
	private JTextField txtQty;
	private JTextField txtRate;
	private JTextField txtamt;
	JComboBox<Object> cmbTable, cmbSelect;
	private AutoCompletionComboBox cmbCategory;
	// AutoCompletionComboBox cmbItem;
	DefaultComboBoxModel<Object> model1, model2, model3;
	JButton btnAdd, btnEdit, btnRemove, btnClear_1, btnPaid;
	private JTextField txtCode;
	private JTable table;
	private DefaultTableModel model, oldmodel;
	private JScrollPane scrollPane;
	private JTextField txtTotal, txtDiscount, txtNettotal, txtNotes, txtReturn, txtToCust;
	private JTable tblOldBill;
	private JPanel panel_2;
	private JComboBox<Object> cmbWaitor;
	private JLabel lblT;
	JList<String> Catlist, Itemlist;
	private Font font = CommonLogic.font;
	private JScrollPane scrollPane_2;
	private JButton btnCredit, btnSave, btnClose, btnoldbill, btnShift, btnClear;
	private JTextField txtCategory;
	private JScrollPane categoryScroll;
	private JPopupMenu popup, itemPopup;
	private JList<Object> clist;
	private DefaultListModel<Object> catlistmodel;
	private List<?> catlist;
	private JTextField txtItem;
	private JList<String> itemlist;
	private List<String> listItem;
	private DefaultListModel<String> itemlistmodel;
	private JScrollPane itemScroll, scrollPane_1;
	final int user;
	final static Logger logger = Logger.getLogger(BillingFrame2.class);
	private JScrollPane scrollPane_3;

	public BillingFrame2(final int user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BillingFrame2.class.getResource("/kush/images/Logo.jpg")));
		this.user = user;

		getContentPane().setBackground(SystemColor.textHighlight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {

				// loadCategory();
				// checkStatus();
				// loadBill();
				setTitle("Loading");
			}

			@Override
			public void windowOpened(WindowEvent arg0) {

				setTitle(
						"Billing Frame                                                       Developed by Ankush Contact no.9960855742   Email-ankushsupnar@gmail.com");// +CommonLogic.getUserName(user));
				// loadBill();
			}

		});
		btnClear = new JButton("eDIT");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println(tblOldBill.getSelectedRow());
				if (tblOldBill.getSelectedRow() < 0) {

					System.out.println(tblOldBill.getSelectedRow());
					return;
				} else {
					int select = tblOldBill.getSelectedRow();
					if (select < 0) {
						return;
					} else {
						int bill = Integer.parseInt("" + oldmodel.getValueAt(select, 0));
						btnClear.setVisible(false);
						btnPaid.setEnabled(false);
						// btnCredit.setEnabled(false);
						// btnSave.setEnabled(false);
						btnClose.setEnabled(false);
						btnoldbill.setEnabled(false);
						btnShift.setEnabled(false);
						// btnClose.setEna
						btnSave.setVisible(true);
						editBill(bill);
					}
				}
			}
		});
		btnClear.setBounds(835, 470, 90, 40);
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		// getContentPane().add(btnClear);

		if (user == 1) {
			getContentPane().add(btnClear);
		}

		// setTitle(
		// "Billing Frame Developed by Ankush Contact no.9960855742
		// Email-ankushsupnar@gmail.com");// +CommonLogic.getUserName(user));
		setLocation(0, 0);
		setSize(1368, 776);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);

		panelTable = new JPanel();
		panelTable.setBounds(0, 0, 424, 708);
		panelTable.setBackground(SystemColor.activeCaption);
		getContentPane().add(panelTable);
		panelTable.setLayout(null);

		panelA = new JPanel();
		panelA.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelA.setBackground(SystemColor.inactiveCaption);
		panelA.setBounds(0, 100, 407, 90);
		panelTable.add(panelA);
		panelA.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelB = new JPanel();
		panelB.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelB.setBackground(new Color(102, 204, 255));
		panelB.setBounds(0, 195, 407, 90);
		panelTable.add(panelB);
		panelB.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelE = new JPanel();
		panelE.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelE.setBackground(new Color(153, 153, 255));
		panelE.setBounds(0, 290, 200, 100);
		panelTable.add(panelE);
		panelE.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelD = new JPanel();
		panelD.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelD.setBackground(new Color(153, 204, 255));
		panelD.setBounds(201, 290, 206, 100);
		panelTable.add(panelD);
		panelD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelC = new JPanel();
		panelC.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelC.setBackground(new Color(204, 204, 255));
		panelC.setBounds(0, 394, 407, 104);
		panelTable.add(panelC);
		panelC.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelG = new JPanel();
		panelG.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelG.setBackground(new Color(153, 153, 255));
		panelG.setBounds(0, 612, 407, 90);
		panelTable.add(panelG);
		panelG.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelV = new JPanel();
		panelV.setBounds(0, 510, 407, 90);
		panelTable.add(panelV);
		panelV.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelV.setBackground(new Color(153, 255, 204));
		panelV.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelP = new JPanel();
		panelP.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelP.setBackground(SystemColor.inactiveCaption);
		panelP.setBounds(0, 5, 407, 90);
		panelTable.add(panelP);
		panelP.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(425, 0, 538, 156);
		panel_1.setBackground(new Color(102, 204, 255));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(0, 2, 56, 16);
		panel_1.add(lblCategory);

		JLabel lblItemName = new JLabel("Item name");
		lblItemName.setBounds(102, 53, 62, 16);
		panel_1.add(lblItemName);

		JLabel lblQty = new JLabel("QTY");
		lblQty.setBounds(285, 54, 56, 16);
		panel_1.add(lblQty);

		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(372, 52, 56, 16);
		panel_1.add(lblRate);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(458, 53, 56, 16);
		panel_1.add(lblAmount);

		catlistmodel = new DefaultListModel<>();
		// loadCategory();
		catlist = CommonLogic.getCategory();
		clist = new JList<>(catlistmodel);
		clist.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					if (clist.getSelectedIndex() != -1) {
						txtCategory.setText("" + clist.getSelectedValue());
						// popup.hide();
						popup.setVisible(false);
						txtCode.requestFocus();

						// popup.hide();
						// popup.setVisible(false);
					}
				}
			}

		});
		clist.setFont(font);
		categoryScroll = new JScrollPane();
		categoryScroll.setViewportView(clist);

		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());
		txtCategory = new JTextField();
		txtCategory.setFont(font);
		txtCategory.setBounds(0, 20, 244, 35);
		panel_1.add(txtCategory);
		txtCategory.setColumns(10);

		txtCategory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// catlistmodel.clear();
				// catlistmodel.removeAllElements();
				findItem("" + txtCategory.getText());
				if (!catlist.isEmpty()) {
					popup.add(categoryScroll); // your component
					popup.setPopupSize(txtCategory.getWidth(), 300);
					popup.show(txtCategory, 0, txtCategory.getHeight());
					txtCategory.requestFocus();
				}

				char c = e.getKeyChar();

				if (c == KeyEvent.VK_ENTER) {

					clist.setSelectedIndex(0);
					clist.requestFocus();
				}
			}

		});
		model1 = new DefaultComboBoxModel<>();
		cmbCategory = new AutoCompletionComboBox(model1);
		cmbCategory.setFont(font);
		//
		// Item search box
		txtItem = new JTextField();
		txtItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtCategory.getText().equals("") || txtCategory.getText().equals(null)) {
					return;
				}
				listItem = CommonLogic.getItemName(txtCategory.getText());
				findItemFilter(txtItem.getText());
				if (!listItem.isEmpty()) {
					itemPopup.add(itemScroll); // your component
					itemPopup.setPopupSize(txtItem.getWidth(), 300);
					itemPopup.show(txtItem, 0, txtItem.getHeight());
					txtItem.requestFocus();
				}

				char c = e.getKeyChar();

				if (c == KeyEvent.VK_ENTER) {

					itemlist.setSelectedIndex(0);
					itemlist.requestFocus();
				}
			}
		});

		txtItem.setFont(font);
		txtItem.setBounds(97, 66, 188, 35);
		panel_1.add(txtItem);
		itemlistmodel = new DefaultListModel<>();
		itemlist = new JList<>(itemlistmodel);
		itemlist.setFont(font);
		itemScroll = new JScrollPane();
		itemScroll.setViewportView(itemlist);
		itemPopup = new JPopupMenu();
		itemlist.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {

					if (itemlist.getSelectedIndex() != -1) {
						txtItem.setText(itemlist.getSelectedValue());
						float rate = CommonLogic.getRateUsingName(txtItem.getText());
						txtRate.setText("" + rate);
						txtCode.setText("" + CommonLogic.getCodeFromName(txtItem.getText()));
						itemPopup.setVisible(false);
						txtQty.requestFocus();
					}
					if (txtItem.getText().equals("")) {
						return;
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// AutoCompleteDecorator.decorate(cmbCategory);
		cmbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cmbCategory.getSelectedItem().equals(null) || cmbCategory.getSelectedItem().equals("")
						|| cmbCategory.getSelectedIndex() == -1) {
					return;
				}
				String cat = "" + cmbCategory.getSelectedItem();
				getItemCode(cat);
				getItemName1(cat);
			}
		});
		// AutoCompletion.enable(cmbCategory);
		// loadCategory();

		cmbCategory.setBounds(0, 20, 244, 35);
		// panel_1.add(cmbCategory);

		model2 = new DefaultComboBoxModel<>();

		txtQty = new JTextField();
		txtQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

				txtQty.selectAll();
			}

			@Override
			public void focusLost(FocusEvent arg0) {

				if (txtQty.getText().equals("") || txtQty.getText().equals(null) || txtQty.getText().equals("" + 0)
						|| txtItem.getText().equals("") || txtItem.getText().equals(null)) {
					// txtQty.requestFocus();
					txtQty.setText("" + 0);
					return;
				}
				String name = "" + txtItem.getText();
				float q = Float.parseFloat("" + txtQty.getText());
				float rate = CommonLogic.getRateUsingName(name);
				txtRate.setText("" + rate);
				float amt = q * rate;

				// cmbItem.getSelectedObjects();
				txtamt.setText("" + amt);

			}
		});
		txtQty.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtQty.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (txtQty.getText().equals("") || txtQty.getText().equals(null)) {
					return;
				}
				if (txtRate.getText().equals("") || txtRate.getText().equals(null) || txtRate.getText().equals("-")) {

					return;
				}

				try {
					float q = Float.parseFloat("" + txtQty.getText());
					float rate = Float.parseFloat("" + txtRate.getText());
					float amt = q * rate;
					txtamt.setText("" + amt);
					if ((c == KeyEvent.VK_ENTER)) {
						txtQty.nextFocus();
					}
				} catch (Exception e2) {
					return;
				}
			}
		});

		txtQty.setBounds(284, 66, 85, 35);
		panel_1.add(txtQty);
		txtQty.setColumns(10);

		txtRate = new JTextField();
		txtRate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtRate.selectAll();
			}
		});
		txtRate.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (txtQty.getText().equals("") || txtQty.getText().equals(null)) {
					return;
				}
				if (txtRate.getText().equals("") || txtRate.getText().equals(null)) {
					return;
				}

				try {

					Float.parseFloat("" + txtRate.getText());
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Enter Rate in rupees", "Wrong Input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				float rate = Float.parseFloat("" + txtRate.getText());
				float qty = Float.parseFloat(txtQty.getText());
				float amt = qty * rate;
				txtamt.setText("" + amt);
				if ((c == KeyEvent.VK_ENTER)) {
					btnAdd.setFocusable(true);
					txtamt.nextFocus();
				}
			}
		});
		txtRate.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtRate.setBounds(372, 66, 85, 35);
		panel_1.add(txtRate);
		txtRate.setColumns(10);

		txtamt = new JTextField();
		txtamt.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (txtamt.getText().equals("" + 0.0) || txtamt.getText().equals("")) {
						txtItem.requestFocus();
					}
					btnAdd.requestFocus();
				}
			}
		});
		txtamt.setEditable(false);
		txtamt.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtamt.setColumns(10);
		txtamt.setBounds(458, 67, 80, 35);
		panel_1.add(txtamt);

		btnAdd = new JButton("A^D");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!btnPaid.isEnabled()) {
					System.out.println("Edit Bill");
					addTable1();
					return;
				}
				addButton();
				txtCategory.requestFocus();
				txtCategory.setText("");
				;
				// JOptionPane.showMessageDialog(null, "Action Performed", "",
				// JOptionPane.DEFAULT_OPTION);
			}
		});
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
					cmbCategory.grabFocus();
				}

			}
		});
		// btnAdd.setFont(new Font("Candara Light", Font.PLAIN, 1));
		btnAdd.setFont(new Font("Kiran", Font.BOLD, 25));
		btnAdd.setBounds(0, 110, 112, 40);
		panel_1.add(btnAdd);

		btnEdit = new JButton("eDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edit();
			}
		});
		btnEdit.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnEdit.doClick();
					btnEdit.nextFocus();
				}
			}
		});
		btnEdit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnEdit.setBounds(245, 110, 97, 40);
		panel_1.add(btnEdit);

		btnRemove = new JButton("rImauvh");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!btnPaid.isEnabled()) {
						System.out.println("Edit Bill");
						// addTable1();
						int row = table.getSelectedRow();
						float amt = (Float) table.getValueAt(row, 4);
						float qty = (Float) table.getValueAt(row, 3);
						CommonLogic.addStock("" + table.getValueAt(row, 1), qty);
						float total = (Float.parseFloat("" + txtTotal.getText()) - amt);
						txtTotal.setText("" + total);
						txtNettotal.setText(txtTotal.getText());
						txtNotes.setText("" + 0);
						txtReturn.setText("" + 0.0f);
						txtToCust.setText("" + 0);
						txtDiscount.setText("" + 0.0f);

						System.out.println("Selected" + row);
						model.removeRow(row);
						return;
					}
					if (table.getSelectedRow() == -1) {

						return;
					}
					int row = table.getSelectedRow();
					// System.out.println("value " +table.getValueAt(row, 1));
					// cmbItem.setSelectedItem(table.getValueAt(row,1));
					// txtRate.setText(""+table.getValueAt(row,2));
					// txtQty.setText(""+table.getValueAt(row, 3));
					float amt = (Float) table.getValueAt(row, 4);
					// txtamt.setText(""+amt);
					float total = (Float.parseFloat("" + txtTotal.getText()) - amt);
					txtTotal.setText("" + total);
					txtNettotal.setText(txtTotal.getText());
					txtNotes.setText("" + 0);
					txtReturn.setText("" + 0.0f);
					txtToCust.setText("" + 0);
					txtDiscount.setText("" + 0.0f);

					// fint item in temptransactio
					int tableno = CommonLogic.getTableId(lblT.getText());
					int id = 0;
					ResultSet rs = CommonMethods
							.selectQuery("select ID,Itemname from TempTransaction where Tableno=" + tableno);
					while (rs.next()) {
						if (rs.getString(2).equals(table.getValueAt(row, 1))) {
							id = rs.getInt(1);

						}
					}
					if (id > 0) {
						CommonMethods.delete(id);

						model.removeRow(row);
					}
					if (id == 0) {
						JOptionPane.showMessageDialog(null, "Can Not Cancel This Order", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					rs.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "error in Removing", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnRemove.setFont(new Font("Kiran", Font.BOLD, 25));
		btnRemove.setBounds(340, 110, 97, 40);
		panel_1.add(btnRemove);

		btnClear_1 = new JButton("klaIAr");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCategory.setText("");
				txtItem.setText("");
				txtCode.setText("");
				txtQty.setText("" + 0);
				txtRate.setText("" + 0.0f);
				txtamt.setText("" + 0.0f);
			}
		});
		btnClear_1.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear_1.setBounds(436, 110, 102, 40);
		panel_1.add(btnClear_1);

		model3 = new DefaultComboBoxModel<>();

		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setBounds(0, 54, 57, 16);
		panel_1.add(lblItemCode);

		txtCode = new JTextField();
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if ((c == KeyEvent.VK_ENTER)) {
					if ((txtCode.getText().equals("")) || (txtCode.getText().equals(null))) {
						// txtCode.nextFocus();

						txtItem.requestFocus();
						return;
					}
					int icode = Integer.parseInt(txtCode.getText());

					if (txtCategory.getText().equals(""))// ||cmbCategory.getSelectedItem().equals(null))
					{
						return;
					}

					// System.out.println("Category "+cmbCategory.getSelectedItem());
					String name = CommonLogic.getNameusingCode(icode, "" + txtCategory.getText());
					// System.out.println("Code Get name "+name);
					if (!name.equals("")) {
						txtItem.setText(name);
						float rate = CommonLogic.getRateUsingName(txtItem.getText());
						txtRate.setText("" + rate);
						txtQty.requestFocus();
						// cmbItem.setSelectedItem(name);
					}
					if (txtItem.getText().equals("")) {
						txtItem.requestFocus();
					}
					// cmbItem.grabFocus();
					// txtItem.requestFocus();

				} else {// System.out.println("name is empty");
					txtItem.setText("");
					// txtItem.requestFocus();
				}
			}
		});

		txtCode.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCode.setBounds(0, 67, 97, 35);
		panel_1.add(txtCode);
		txtCode.setColumns(10);

		JLabel lblWaitorName = new JLabel("Waitor name");
		lblWaitorName.setBounds(245, 2, 70, 16);
		panel_1.add(lblWaitorName);

		cmbWaitor = new JComboBox<Object>();
		cmbWaitor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// txtCategory.requestFocus();
			}
		});
		cmbWaitor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					txtCategory.requestFocus();
				}
			}
		});
		cmbWaitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// txtCategory.requestFocus();

			}
		});
		cmbWaitor.setFont(new Font("Kiran", Font.BOLD, 25));
		cmbWaitor.setBounds(245, 19, 146, 35);
		getAllWaitorname();
		panel_1.add(cmbWaitor);

		JButton btnOrder = new JButton("Aa^Dr");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!lblT.getText().equals("")) {
					Order(lblT.getText());

				}
			}
		});
		btnOrder.setFont(new Font("Kiran", Font.BOLD, 25));
		btnOrder.setBounds(124, 110, 109, 40);
		panel_1.add(btnOrder);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 160, 538, 243);
		getContentPane().add(scrollPane);

		model = new DefaultTableModel();
		model.addColumn("SrNo.");
		model.addColumn("Item Name");
		model.addColumn("Rate");
		model.addColumn("QTY");
		model.addColumn("Amount");

		table = new JTable();
		table.setFont(font);
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowHeight(20);
		table.setModel(model);

		JPanel panel = new JPanel();
		panel.setBounds(425, 403, 538, 65);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(panel);
		panel.setLayout(null);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setText("0.0");
		txtTotal.setBackground(new Color(255, 255, 102));
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtTotal.setBounds(395, 5, 101, 30);
		panel.add(txtTotal);
		txtTotal.setColumns(10);

		txtDiscount = new JTextField();
		txtDiscount.setEditable(false);
		txtDiscount.setText("0.0");
		txtDiscount.setBackground(new Color(255, 153, 102));
		txtDiscount.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtDiscount.setBounds(210, 35, 90, 28);
		panel.add(txtDiscount);
		txtDiscount.setColumns(10);

		txtNettotal = new JTextField();
		txtNettotal.setEditable(false);
		txtNettotal.setText("0.0");
		txtNettotal.setBackground(new Color(51, 204, 0));
		txtNettotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtNettotal.setBounds(395, 35, 101, 28);
		panel.add(txtNettotal);
		txtNettotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(360, 16, 27, 16);
		panel.add(lblTotal);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(157, 46, 55, 16);
		panel.add(lblDiscount);

		JLabel lblNetTotal = new JLabel("Net Total");
		lblNetTotal.setBounds(332, 46, 55, 16);
		panel.add(lblNetTotal);

		JLabel lblNotes = new JLabel("Received");
		lblNotes.setBounds(6, 15, 55, 16);
		panel.add(lblNotes);

		JLabel lblReturn = new JLabel("Return");
		lblReturn.setBounds(158, 16, 37, 16);
		panel.add(lblReturn);

		JLabel lblDiscount_1 = new JLabel("To Cust");
		lblDiscount_1.setBounds(6, 46, 43, 16);
		panel.add(lblDiscount_1);

		txtNotes = new JTextField();
		txtNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					txtReturn.setText(
							"" + (Float.parseFloat(txtNotes.getText()) - Float.parseFloat(txtTotal.getText())));
					txtToCust.requestFocus();
				}
				// paid bill
				// @SuppressWarnings("unused")
				// int i = CommonMethods.updateRecord("");
			}
		});
		txtNotes.setBackground(new Color(255, 255, 102));
		txtNotes.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtNotes.setBounds(57, 5, 90, 28);
		panel.add(txtNotes);
		txtNotes.setColumns(10);

		txtReturn = new JTextField();
		txtReturn.setEditable(false);
		txtReturn.setText("0.0");
		txtReturn.setBackground(new Color(255, 153, 153));
		txtReturn.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtReturn.setBounds(210, 5, 90, 28);
		panel.add(txtReturn);
		txtReturn.setColumns(10);

		txtToCust = new JTextField("" + 0);
		txtToCust.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (c == KeyEvent.VK_ENTER) {

					if (!txtToCust.getText().isEmpty()) {
						txtDiscount.setText(
								"" + (Float.parseFloat(txtToCust.getText()) - Float.parseFloat(txtReturn.getText())));
						txtNettotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
						btnPaid.requestFocus();
					}
				}
			}
		});
		txtToCust.setBackground(new Color(255, 153, 102));
		txtToCust.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtToCust.setBounds(57, 35, 90, 28);
		panel.add(txtToCust);
		txtToCust.setColumns(10);

		oldmodel = new DefaultTableModel();
		oldmodel.addColumn("Bill No");
		oldmodel.addColumn("Amount");
		oldmodel.addColumn("Discount");
		oldmodel.addColumn("Recived");
		oldmodel.addColumn("Waitor");
		oldmodel.addColumn("Table");
		oldmodel.addColumn("User");
		oldmodel.addColumn("Date");
		oldmodel.addColumn("Pay");

		tblOldBill = new JTable(oldmodel);

		tblOldBill.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// int lastIndex = oldTable.getCellRect(oldTable.getRowCount() - 1);
				int lastIndex = tblOldBill.getRowCount();
				// int lastIndex = oldTable.getCellRect(table.getRowCount() - 1;
				tblOldBill.changeSelection(lastIndex, 0, false, false);
			}
		});
		tblOldBill.setRowHeight(20);
		tblOldBill.setShowVerticalLines(true);
		tblOldBill.setAutoscrolls(true);
		tblOldBill.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblOldBill.setShowHorizontalLines(true);

		scrollPane_1 = new JScrollPane();

		scrollPane_1.setViewportView(tblOldBill);
		scrollPane_1.setBounds(425, 560, 839, 148);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane_1);

		// loadBill();

		tblOldBill.scrollRectToVisible(tblOldBill.getCellRect(tblOldBill.getRowCount() - 1, 0, true));
		// scrollPane_1.getVerticalScrollBar().setValue(tblOldBill.getRowCount());
		// System.out.println("Scroll "+tblOldBill.getRowCount());

		panel_2 = new JPanel();
		panel_2.setBounds(965, 0, 89, 540);
		panel_2.setBackground(SystemColor.activeCaption);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btn1.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn1.setBounds(0, 69, 90, 48);
		panel_2.add(btn1);

		lblT = new JLabel("");
		lblT.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.YELLOW, new Color(75, 0, 130)));
		lblT.setForeground(new Color(128, 0, 0));
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setBounds(1, 6, 89, 64);
		panel_2.add(lblT);
		lblT.setFont(new Font("SansSerif", Font.BOLD, 50));
		lblT.setBackground(Color.WHITE);

		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn2.setBounds(0, 114, 90, 48);
		panel_2.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn3.setBounds(0, 159, 90, 48);
		panel_2.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn4.setBounds(0, 205, 90, 48);
		panel_2.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn5.setBounds(0, 252, 90, 48);
		panel_2.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn6.setBounds(0, 299, 90, 48);
		panel_2.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn7.setBounds(0, 346, 90, 48);
		panel_2.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn8.setBounds(0, 392, 90, 48);
		panel_2.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn9.setBounds(0, 439, 90, 48);
		panel_2.add(btn9);

		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("SansSerif", Font.BOLD, 40));
		btn0.setBounds(0, 486, 90, 48);
		panel_2.add(btn0);

		cmbTable = new JComboBox<Object>();
		cmbTable.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cmbTable.setBounds(427, 512, 90, 40);
		// panel_2.add(cmbTable);
		getContentPane().add(cmbTable);
		// getContentPane().add(btnShift);
		addTable();

		btnShift = new JButton("SHIFT");
		btnShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shift("" + cmbTable.getSelectedItem(), lblT.getText());

			}
		});
		btnShift.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnShift.setBounds(520, 512, 90, 40);

		// panel_2.add(btnShift);
		getContentPane().add(btnShift);

		btnClose = new JButton("klaaoja");
		btnClose.setBounds(425, 470, 90, 40);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close(user);
				JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
				vertical.setValue(vertical.getMaximum() + 1);
			}
		});
		btnClose.setFont(new Font("Kiran", Font.BOLD, 25));
		getContentPane().add(btnClose);

		btnPaid = new JButton("jamaa");
		btnPaid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					btnPaid.doClick();
				}
			}
		});
		btnPaid.setBounds(520, 470, 90, 40);
		btnPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paid();
				// loadBill();
				addnewBill();
				JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
				vertical.setValue(vertical.getMaximum() + 2);

				checkStatus();
			}
		});
		btnPaid.setFont(new Font("Kiran", Font.BOLD, 25));
		getContentPane().add(btnPaid);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14));
		tabbedPane.setBounds(1056, 0, 208, 540);
		getContentPane().add(tabbedPane);

		JPanel panelCategory = new JPanel();
		tabbedPane.addTab("Category", null, panelCategory, null);
		panelCategory.setLayout(null);

		JPanel panelItem = new JPanel();
		tabbedPane.addTab("Item", null, panelItem, null);

		final DefaultListModel<String> itemmodel = new DefaultListModel<String>();
		panelItem.setLayout(null);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 208, 501);
		panelItem.add(scrollPane_2);
		Itemlist = new JList<String>(itemmodel);
		scrollPane_2.setViewportView(Itemlist);
		Itemlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtItem.setText(Itemlist.getSelectedValue());
				txtRate.setText("" + CommonLogic.getRateUsingName(txtItem.getText()));
				txtQty.setText("" + 0);
			}
		});
		Itemlist.setBackground(new Color(135, 206, 235));
		Itemlist.setFont(new Font("Kiran", Font.BOLD, 20));

		DefaultListModel<String> listmodel = new DefaultListModel<String>();

		List<String> l = CommonLogic.getAllCategory();
		Iterator<String> i = l.iterator();
		while (i.hasNext()) {
			listmodel.addElement(i.next());
		}

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 208, 501);
		panelCategory.add(scrollPane_3);
		Catlist = new JList<String>(listmodel);
		scrollPane_3.setViewportView(Catlist);

		Catlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// JOptionPane.showMessageDialog(null, "You clicked"+Catlist.getSelectedValue(),
				// "", 0);

				cmbCategory.setSelectedItem(Catlist.getSelectedValue());
				itemmodel.removeAllElements();
				tabbedPane.setSelectedIndex(1);

				List<String> i = CommonLogic.getItemName(Catlist.getSelectedValue());
				Iterator<String> t = i.iterator();
				while (t.hasNext()) {
					System.out.println("Aded");
					itemmodel.addElement(t.next());
				}
				// Itemlist.add(itemmodel);

			}
		});

		Catlist.setFont(new Font("Kiran", Font.BOLD, 20));
		Catlist.setBackground(new Color(176, 196, 222));

		btnoldbill = new JButton("jaunao baIla");
		btnoldbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int select = tblOldBill.getSelectedRow();
				if (select < 0) {
					return;
				} else {
					new BillPdf((int) oldmodel.getValueAt(select, 0));
				}
				// System.out.println(tblOldBill.getSelectedRow());

			}
		});
		btnoldbill.setFont(new Font("Kiran", Font.BOLD, 25));
		btnoldbill.setBounds(764, 470, 109, 40);
		getContentPane().add(btnoldbill);

		btnCredit = new JButton("k`oiDT");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!btnClose.isEnabled()) {
						int select = tblOldBill.getSelectedRow();
						int bill = Integer.parseInt("" + oldmodel.getValueAt(select, 0));
						// save(bill);
						// new ChooseCreditor(bill);
						dispose();
						checkStatus();

						System.out.println("Edit is opened" + bill);

						return;
					}
					@SuppressWarnings("unused")
					int billno = 0;
					int ta = CommonLogic.getTableId(lblT.getText());
					String query = "select bill.billno from Bill where Tableno=" + ta + " and status='no'";
					ResultSet rs = CommonMethods.selectQuery(query);
					rs.next();
					billno = rs.getInt(1);
					// new ChooseCreditor(billno);
					dispose();
					checkStatus();
				} catch (Exception e) {
					System.out.println("Error in Credit Choose");
				}
			}
		});
		btnCredit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnCredit.setBounds(613, 470, 109, 40);
		getContentPane().add(btnCredit);

		btnSave = new JButton("saova");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int select = tblOldBill.getSelectedRow();
				if (select < 0) {
					return;
				}
				int bill = Integer.parseInt("" + oldmodel.getValueAt(select, 0));
				save(bill);
				btnClear.setVisible(true);
				btnPaid.setEnabled(true);
				btnCredit.setEnabled(true);
				// btnSave.setEnabled(false);
				btnClose.setEnabled(true);
				btnoldbill.setEnabled(true);
				btnShift.setEnabled(true);
				model.setRowCount(0);
				txtNotes.setText("");
				txtDiscount.setText("" + 0.f);
				txtTotal.setText("" + 0.0f);
				txtNettotal.setText("" + 0.0f);
				txtReturn.setText("" + 0.0f);
				txtToCust.setText("" + 0);
				tblOldBill.clearSelection();
				loadBill();
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblOldBill.getModel());
				tblOldBill.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);

				checkStatus();
				btnSave.setVisible(false);
			}
		});
		btnSave.setFont(new Font("Kiran", Font.BOLD, 25));
		btnSave.setBounds(843, 470, 90, 40);
		btnSave.setVisible(false);
		getContentPane().add(btnSave);

		cmbSelect = new JComboBox<>();
		cmbSelect.setBounds(622, 512, 100, 40);
		// getContentPane().add(cmbSelect);

		Component[] components = panel_2.getComponents();
		for (Component component : components) {
			if (component instanceof JButton) {
				final JButton button = (JButton) component;
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (button.getText().equals("SHIFT")) {
							return;
						}
						if (txtItem.getText().equals("")) {
							return;
						}
						txtQty.setText(txtQty.getText() + button.getText());
						txtamt.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
						btnAdd.doClick();
						// TODO Auto-generated method stub

					}
				});
			}
		}
		// model.addRow(new Object[] {1,"Abc",10,100});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		loadBill();
		loadTables();
		checkStatus();

		setVisible(true);
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		// WebLookAndFeel.install();
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
		new BillingFrame2(1);
		// TODO Auto-generated method stub

	}

	void loadTables() {
		try {
			// in Familly
			String name = "";
			ResultSet rs = CommonMethods
					.selectQuery("select TableName from TableMaster where DESCRIPTION='P' order by ID");
			while (rs.next()) {
				name = rs.getString(1);

				JButton table = new JButton("" + name);

				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelP.add(table);

			}
			rs.close();
			rs = null;
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='V' order by ID");
			while (rs.next()) {
				name = rs.getString(1);

				JButton table = new JButton("" + name);

				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelV.add(table);

			}
			rs.close();
			rs = null;
			// in A
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='A' order by ID");
			while (rs.next()) {
				name = rs.getString(1);
				JButton table = new JButton("" + name);
				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelA.add(table);

			}
			rs.close();
			rs = null;
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='B' order by ID");
			while (rs.next()) {
				name = rs.getString(1);
				JButton table = new JButton("" + name);
				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelB.add(table);

			}
			rs.close();
			rs = null;
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='E' order by ID");
			while (rs.next()) {
				name = rs.getString(1);
				JButton table = new JButton("" + name);
				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelE.add(table);

			}
			rs.close();
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='D' order by ID");
			while (rs.next()) {
				name = rs.getString(1);
				JButton table = new JButton("" + name);
				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelD.add(table);

			}
			rs.close();
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='C' order by ID");
			while (rs.next()) {
				name = rs.getString(1);
				JButton table = new JButton("" + name);
				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelC.add(table);

			}
			rs.close();
			rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='G' order by ID");
			while (rs.next()) {
				name = rs.getString(1);
				JButton table = new JButton("" + name);
				table.setName(name);
				table.setFont(new Font("Tahoma", Font.PLAIN, 18));
				table.addActionListener(this);
				panelG.add(table);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Error in Loading Tables " + e);
		}
	}

	void addnewBill() {
		try {
			String htmstart = "<html><font face=\"kiran\" size=\"5\">";
			int row = tblOldBill.getRowCount();
			int oldBillno = Integer.parseInt(("" + oldmodel.getValueAt(row - 1, 0)));
			++oldBillno;
			float billamt = 0f, discount = 0f, ramt = 0f;
			ResultSet rs = CommonMethods.selectQuery("select BillAmt,Discount,"
					+ "Waitorid,Tableno,userid,Billdate,Paymode from bill where BillNo=" + oldBillno);
			rs.next();
			billamt = rs.getFloat(1);
			discount = rs.getFloat(2);
			ramt = billamt - discount;

			oldmodel.addRow(new Object[] { oldBillno, billamt, discount, ramt,
					htmstart + CommonLogic.getWaitorName(rs.getInt(3)), rs.getInt(4),
					htmstart + CommonLogic.getUserName(rs.getInt(5)), rs.getString(6), rs.getString(7) });
			// System.out.println("Just old Bil"+oldBillno);

			JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
			vertical.setValue(vertical.getMaximum() + 2);

		} catch (Exception e) {
			System.out.println("Error adding new Bill in JTale" + e.getMessage());
		}
	}

	void loadCategory() {
		try {
			List<String> list = CommonLogic.getCategory();
			Iterator<String> i = list.iterator();
			while (i.hasNext()) {
				catlistmodel.addElement(i.next());
				// cmbCategory.addItem(i.next());
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Gettin Category");
			return;
		}
	}

	void findItem(String find) {
		catlistmodel.removeAllElements();

		try {
			for (int i = 0; i < catlist.size(); i++) {
				if (((String) catlist.get(i)).startsWith(find)) {

					catlistmodel.addElement((String) catlist.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	void getItemName1(String CatName) {
		// System.out.println("Get cat for Loading "+CatName);
		List<String> item = CommonLogic.getItemName(CatName);

		try {
			model2.removeAllElements();
			Iterator<String> i = item.iterator();
			while (i.hasNext()) {
				model2.addElement(i.next());
			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(this, "Error in item Loading", "Not Found",
			// JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	void findItemFilter(String find) {
		itemlistmodel.removeAllElements();
		itemlistmodel.clear();
		// System.out.println("i got"+find);
		try {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).startsWith(find)) {
					// MySortStrings.add(MyStrings.get(i));
					itemlistmodel.addElement(listItem.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	void getAllWaitorname() {
		List<Object> list = CommonLogic.getAllWaitorName();
		try {
			Iterator<Object> i = list.iterator();
			while (i.hasNext()) {
				cmbWaitor.addItem(i.next());
			}
		} catch (Exception e) {
			System.out.println("Error in this frame getAllWaitorname " + e.getMessage());
			return;
			// TODO: handle exception
		}
	}

	void getItemCode(String cat) {
		try {
			List<Object> code = CommonLogic.getItemCode(cat);
			model3.removeAllElements();
			Iterator<Object> i = code.iterator();
			while (i.hasNext()) {
				model3.addElement(i.next());
			}
			// cmbCode.setSelectedItem("");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void addButton() {

		if (lblT.getText().equals("") || lblT.getText().equals(null)) {
			JOptionPane.showMessageDialog(null, "Select Table First", "Empty", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (txtQty.getText().equals("")) {
			return;
		}
		if (CommonLogic.reduceStock(txtItem.getText(), Float.parseFloat(txtQty.getText())) != 1) {
			JOptionPane.showMessageDialog(this, "Stock is less than 0 Can's Sale");
			return;
		}

		try {
			checkStatus();
			if (txtItem.getText().equals("") || txtRate.getText().equals("") || txtQty.getText().equals("")
					|| txtamt.getText().equals("" + 0.0)) {
				JOptionPane.showMessageDialog(this, "Please Select Item Again", "Empty !", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int row = table.getRowCount();
			float qty = Float.parseFloat(txtQty.getText());
			float rate = Float.parseFloat(txtRate.getText());
			float amt = Float.parseFloat(txtamt.getText());
			float total = Float.parseFloat(txtTotal.getText());
			String name = "" + txtItem.getText();
			int tableno = CommonLogic.getTableId(lblT.getText());
			@SuppressWarnings("unused")
			int wid = CommonLogic.getWaitorid("" + cmbWaitor.getSelectedItem());

			TempTransaction t = new TempTransaction();
			t.setId(CommonMethods.getId("select id from TempTransaction order by id"));
			t.setItemName(txtItem.getText());
			t.setQty(Float.parseFloat(txtQty.getText()));
			t.setRate(Float.parseFloat(txtRate.getText()));
			t.setAmt(Float.parseFloat(txtamt.getText()));
			t.setTableNo(CommonLogic.getTableId(lblT.getText()));
			t.setWaitorId(CommonLogic.getWaitorid("" + cmbWaitor.getSelectedItem()));
			t.setPrintQty(Integer.parseInt(txtQty.getText()));

			if (row == 0)// First Time
			{
				// System.out.println("Row=="+row);
				// String name = ""+cmbItem.getSelectedItem();
				if (qty < 0.0f) {
					JOptionPane.showMessageDialog(this, "Cant Add Quqntity", "Error", JOptionPane.ERROR_MESSAGE);
					txtQty.requestFocus();
					return;
				}
				txtTotal.setText("" + (total + amt));
				txtNettotal
						.setText("" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
				model.addRow(new Object[] { ++row, name, rate, qty, amt });
				AddPOJO.saveTempTransaction(t);
				// int f= CommonLogic.reduceStock(name, qty);

				// CommonLogic.addTempTransaction(name, qty, rate, amt, tableno, wid, qty);
				checkStatus();
				clear();
				return;
			}
			if (row > 0) {
				// Already it have items
				int i = 0;
				int flag = -1;
				for (i = 0; i < row; i++) {
					if (table.getValueAt(i, 1).equals(name)) {
						// System.out.println("Found name"+table.getValueAt(i, 1));
						flag = i;
						// return;
					} else {
						// flag=-1;
					}
				}
				if (flag == -1) {

					// String name = ""+cmbItem.getSelectedItem();
					if (qty > 0) {
						// System.out.println("Item Not in table Fresh");
						int sr = model.getRowCount();
						txtTotal.setText("" + (total + amt));
						txtNettotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
						model.addRow(new Object[] { ++sr, name, rate, qty, amt });
						AddPOJO.saveTempTransaction(t);
						// CommonLogic.addTempTransaction(name, qty, rate, amt, tableno, wid, qty);
						clear();
					} else {
						JOptionPane.showMessageDialog(this, "Cant Add Quqntity", "Error", JOptionPane.ERROR_MESSAGE);
						txtQty.requestFocus();
					}

					return;

				}
				if (flag > -1)

					// System.out.println("Item exist "+flag);
					// System.out.println("Exist Item "+table.getValueAt(flag, 2));

					table.setValueAt((Float.parseFloat("" + table.getValueAt(flag, 3)) + qty), flag, 3);
				table.setValueAt((Float.parseFloat("" + table.getValueAt(flag, 4)) + amt), flag, 4);
				txtTotal.setText("" + (total + amt));
				txtNettotal
						.setText("" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
				// System.out.println("update Temp Transaction "+name);
				// here i have to find Record in TempTransaction
				int check = CommonLogic.checkitemInTemp(name, tableno);
				if (check == 0) {
					// System.out.println("Not in Temp add new");
					if (qty > 0) {
						AddPOJO.saveTempTransaction(t);
						// CommonLogic.addTempTransaction(name, qty, rate, amt, tableno, wid, qty);
					} else {
						JOptionPane.showMessageDialog(this, "Cant Add Quqntity", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {

					CommonLogic.updateTemp(name, tableno, Float.parseFloat("" + table.getValueAt(flag, 3)),
							Float.parseFloat("" + table.getValueAt(flag, 4)), qty);
					colorTable(lblT.getText(), Color.GREEN);
					clear();
				}
				return;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Adding Record" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	void addButton2() {
		System.out.println("Add button");
		try {
			if (validAddButton() == 0) {
				return;
			}
			TempTransaction t = new TempTransaction();
			t.setId(CommonMethods.getId("select Id from TempTransaction order by ID"));
			t.setItemName(txtItem.getText());
			t.setQty(Float.parseFloat(txtQty.getText()));
			t.setRate(Float.parseFloat(txtRate.getText()));
			t.setAmt(Float.parseFloat(txtamt.getText()));
			t.setTableNo(CommonLogic.getTableId(lblT.getText()));
			t.setWaitorId(CommonLogic.getWaitorid("" + cmbWaitor.getSelectedItem()));
			t.setPrintQty(Float.parseFloat(txtQty.getText()));
			System.out.println("Row=" + model.getRowCount());
			AddPOJO.saveTempTransaction(t);
			if (model.getRowCount() == 0)// First Row
			{
				model.addRow(new Object[] { 1, t.getItemName(), t.getRate(), t.getQty(), t.getAmt() });

			} else // not empty table
			{
				int r = -1;
				int row = table.getRowCount();
				int i = 0;
				for (i = 0; i < model.getRowCount(); i++) {
					if (model.getValueAt(i, 1).equals(txtItem.getText())) {
						r = i;
						break;
					}
				}
				if (r == -1)// addnew
				{
					model.addRow(new Object[] { ++row, txtItem.getText(), txtRate.getText(), txtQty.getText(),
							txtamt.getText() });
				} else // update
				{

					table.setValueAt((Float.parseFloat("" + model.getValueAt(i, 3)) + t.getQty()), i, 3);
					table.setValueAt((Float.parseFloat("" + model.getValueAt(i, 4)) + t.getAmt()), i, 4);
				}

			}
			System.out.println(t);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	int validAddButton() {
		try {
			if (lblT.getText().equals("") || lblT.getText().equals(null)) {
				JOptionPane.showMessageDialog(null, "Select Table First", "Empty", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			if (txtQty.getText().equals("")) {
				return 0;
			}
			if (Float.parseFloat(txtQty.getText()) < 0) {
				JOptionPane.showMessageDialog(null, "qty less than 0");
				return 0;
			}
			if (txtamt.getText().equals("") || Float.parseFloat(txtamt.getText()) == 0.0f) {
				JOptionPane.showMessageDialog(this, "Enter Item Detail again");
				return 0;
			} else
				return 1;

			// return 1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Add " + e.getMessage());
			return 0;
		}
	}

	void addInTable(TempTransaction t) {

	}

	void clear() {
		txtItem.setText("");
		txtRate.setText("" + 0.0f);
		txtQty.setText("" + 0);
		txtamt.setText("" + 0.0f);
		txtamt.setText("" + 0.0f);
		txtCode.setText("");
	}

	void edit() {
		try {
			if (table.getSelectedRow() == -1) {

				return;
			}
			int row = table.getSelectedRow();
			// System.out.println("value " +table.getValueAt(row, 1));
			txtCode.setText("" + CommonLogic.getCodeFromName("" + table.getValueAt(row, 1)));
			model2.setSelectedItem(table.getValueAt(row, 1));
			txtItem.setText("" + table.getValueAt(row, 1));
			txtRate.setText("" + table.getValueAt(row, 2));
			/// txtQty.setText(""+table.getValueAt(row, 3));
			txtQty.setText("");
			txtQty.requestFocus();
			@SuppressWarnings("unused")
			float amt = (Float) table.getValueAt(row, 4);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override

	public void actionPerformed(ActionEvent ae) {
		new JButton();
		// System.out.println("Source"+ae.getActionCommand());

		lblT.setText(ae.getActionCommand());
		getTabledata(lblT.getText());
		checkStatus();
		// if(ae.getActionCommand().getClass().getComponentType().equals(JButton))
		// System.out.println(""+panelFamily.getComponentCount());
	}

	public void checkStatus() {
		// System.out.println("Checking");
		// loadTables();
		try {
			List<String> table = CommonLogic.getRunningTableFromBill();
			for (String name : table) {
				colorTable(name, Color.RED);
			}

			List<String> ta = CommonLogic.getRunningTable();
			for (String name : ta) {
				// System.out.println("Found "+name);
				colorTable(name, Color.GREEN);
			}

		} catch (Exception e) {
			System.out.println("Error in checkStatus" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void colorTable(String name, Color col) {
		for (int i = 0; i < panelP.getComponentCount(); i++) {
			if (panelP.getComponent(i).getName().equals(name)) {
				// System.out.println("To color "+name);
				panelP.getComponent(i).setBackground(col);
			}

		}
		for (int i = 0; i < panelV.getComponentCount(); i++) {
			if (panelV.getComponent(i).getName().equals(name)) {
				// System.out.println("To color "+name);
				panelV.getComponent(i).setBackground(col);
			}

		}
		for (int i = 0; i < panelA.getComponentCount(); i++) {
			if (panelA.getComponent(i).getName().equals(name)) {
				// System.out.println("To color "+name);
				panelA.getComponent(i).setBackground(col);
			}
		}
		for (int i = 0; i < panelB.getComponentCount(); i++) {
			if (panelB.getComponent(i).getName().equals(name)) {
				// System.out.println("I found "+name);
				panelB.getComponent(i).setBackground(col);
				// return;
			}

		}
		for (int i = 0; i < panelE.getComponentCount(); i++) {
			if (panelE.getComponent(i).getName().equals(name)) {
				panelE.getComponent(i).setBackground(col);
			}
		}
		for (int i = 0; i < panelD.getComponentCount(); i++) {
			if (panelD.getComponent(i).getName().equals(name)) {
				panelD.getComponent(i).setBackground(col);
			}
		}
		for (int i = 0; i < panelC.getComponentCount(); i++) {
			// System.out.println(panelFamily.getComponent(i).getName());

			if (panelC.getComponent(i).getName().equals(name)) {
				panelC.getComponent(i).setBackground(col);
			}
		}
		for (int i = 0; i < panelG.getComponentCount(); i++) {
			if (panelG.getComponent(i).getName().equals(name)) {
				panelG.getComponent(i).setBackground(col);
			}
		}

	}

	public void getTabledata(String TableName) {
		// System.out.println(model.getRowCount());
		int row = table.getRowCount();

		if (row != 0) {

			model.setRowCount(0);
			;
			txtTotal.setText("" + 0.0f);
			txtNettotal.setText(txtTotal.getText());
		}

		//
		//
		try {
			int sr = 1;
			float amt;
			String query = "select ITEMNAME, QTY,RATE,AMT from TempTransaction,TableMaster where TempTransaction.TableNo=TableMaster.ID and Tablename='"
					+ TableName + "' order by TempTransaction.ID";
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next()) {

				amt = rs.getFloat(4);
				model.addRow(new Object[] { sr++, rs.getString(1), rs.getFloat(3), rs.getFloat(2), amt });

				txtTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + amt));
				txtNettotal.setText(txtTotal.getText());
			}
			rs.close();
			// if data not found in TempTransaction find it in Transaction
			String q = "select Transaction.ITEMNAME,Transaction.QTY,Transaction.RATE,Transaction.AMT,Bill.TABLENO,Bill.WAITORID from Transaction,Bill where\r\n"
					+ "Transaction.Bill=Bill.BILLNO and Bill.status='no'";
			rs = CommonMethods.selectQuery(q);

			while (rs.next()) {
				int tname = CommonLogic.getTableId(TableName);
				if (rs.getInt(5) == tname) {
					// System.out.println("Found Table in Transaction name="+TableName);
					amt = rs.getFloat(4);
					model.addRow(new Object[] { sr++, rs.getString(1), rs.getFloat(3), rs.getFloat(2), amt });
					cmbWaitor.setSelectedItem(CommonLogic.getWaitorName(rs.getInt(6)));
					txtTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + amt));
					txtNettotal.setText(txtTotal.getText());

				}
			}
			rs.close();

			int r = table.getRowCount();
			// System.out.println("Row ="+r);
			if (r <= 0) {
				cmbWaitor.requestFocus();
				cmbWaitor.showPopup();
			} else {
				txtCategory.requestFocus();
			}
		} catch (Exception e) {
			System.out.println("Error in getTabledata==> " + e.getMessage());
			return;
		}
	}

	void Order(String Table) {
		try {
			if (table.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(this, "No data to Print", "Empty Table", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// find table is present or not in TempTransaction
			if (CommonLogic.FindTableinTemp(Table) == 1) // Find in TempTransaction
			{
				int f = 0;
				ResultSet rs = CommonMethods.selectQuery(
						"select printqty from TempTransaction where Tableno=" + CommonLogic.getTableId(Table));
				while (rs.next()) {
					if (rs.getInt(1) > 0) {
						f = 1;
					}
				}

				rs.close();
				if (f == 1) {
					// new Quotation(CommonLogic.getTableId(Table));
					new QuotationPDF(Table);
					CommonMethods.updateRecord(
							"update TempTransaction set printqty=0 where Tableno=" + CommonLogic.getTableId(Table));
				}
				// PrintQuatation
				///// getdata from TempTransaction
				/*
				 * ResultSet rs = CommonMethods.
				 * selectQuery("select ID ,ITEMNAME,QTY,RATE,AMT,TABLENO,WAITORID from TempTransaction"
				 * ); while(rs.next()) {
				 * System.out.println(" \n"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)
				 * +" "+rs.getFloat(4)+" "+rs.getFloat(5)+" "+rs.getInt(6)+" "+rs.getInt(7)); }
				 * rs.close();
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in Order " + e.getMessage());
			return;
		}
	}

	void close(int user) {
		try {

			if (table.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(this, "No data ", "Empty Table", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (lblT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "No data ", "Empty Table", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Save Data in Bill
			int BillNo = CommonMethods.getId("select BillNo from Bill order by BillNO");
			float bamt = Float.parseFloat(txtNettotal.getText());
			int custid = 1;
			int waitor = CommonLogic.getWaitorid("" + cmbWaitor.getSelectedItem());
			int tableid = CommonLogic.getTableId(lblT.getText());
			float dis = Float.parseFloat(txtDiscount.getText());
			// String date = CommonLogic.getDateTime();
			// System.out.println(date);

			// int user= user;
			String pay = "Cash";
			// find the table already in Transaction or not
			int bill = 0;
			ResultSet rs1 = CommonMethods
					.selectQuery("select Billno from Bill where TableNO=" + tableid + " and status='no'");
			while (rs1.next()) {
				bill = rs1.getInt(1);
			}
			rs1.close();
			if (bill == 0)// not in bill table i have add fresh
			{
				String query1 = "insert into Bill(BILLNO,BILLAMT,DISCOUNT,CUSTOMERID,WAITORID,TABLENO,USERID,BILLDATE,PAYMODE,STATUS,time) values("
						+ BillNo + "," + bamt + "," + dis + "," + custid + "," + waitor + "," + tableid + "," + user
						+ ",NOW(),'" + pay + "','no',CURDATE())";

				int flag = CommonMethods.addRecord(query1);
				String tablename = lblT.getText();
				String query = "select  TempTransaction.ID,TempTransaction.ITEMNAME, TempTransaction.QTY, TempTransaction.RATE, TempTransaction.AMT,TempTransaction.TABLENO, TempTransaction.WAITORID, TempTransaction.PRINTQTY from TempTransaction,TableMaster where TempTransaction.TableNo=TableMaster.ID and TableName='"
						+ tablename + "'";
				ResultSet rs = CommonMethods.selectQuery(query);
				int flag2 = 0;
				while (rs.next()) {
					// ID ITEMNAME QTY RATE AMT BILL
					flag2 = 0;
					int id = CommonMethods.getId("select ID from Transaction order by ID");

					CommonMethods.addRecord("insert into Transaction(ID,ITEMNAME,QTY,RATE,AMT,BILL) values(" + id + ",'"
							+ rs.getString(2) + "'," + rs.getInt(3) + "," + rs.getFloat(4) + "," + rs.getFloat(5) + ","
							+ BillNo + ")");
					CommonMethods.delete(rs.getInt(1));
					flag2 = 1;
				}
				rs.close();

				if (flag == 1 && flag2 == 1) {
					JOptionPane.showMessageDialog(null, "Bill Save", "Saved", JOptionPane.OK_OPTION);
					// new Bill(BillNo);
					new BillPdf(BillNo);
					colorTable(lblT.getText(), Color.RED);

					return;
				}

			} else {
				// System.out.println("Already closed Bill no "+bill);
				// update bill set new Amount and discount
				@SuppressWarnings("unused")
				int flag = CommonMethods.updateRecord(
						"update Bill set BillAmt=" + bamt + ", Discount=" + dis + " where BillNo=" + bill);
				// update Transaction first find the item
				// read data from TempTransaction
				String name;
				int tid = 0, tempid = 0;
				Float qty = 0.0f;
				float rate = 0.0f, amt = 0.0f;
				ResultSet rs = CommonMethods
						.selectQuery("select ID, Itemname,qty,rate,amt from TempTransaction where TableNo=" + tableid);
				while (rs.next()) {
					// find this item in Transaction under this table
					tempid = rs.getInt(1);
					name = rs.getString(2);
					qty = rs.getFloat(3);
					rate = rs.getFloat(4);
					amt = rs.getFloat(5);
					tid = CommonLogic.checkitemInTrans(name, bill);

					if (tid > 0) {
						// update Transaction with new TempTransaction values
						// System.out.println("Item Also in Transaction id "+tid);
						CommonMethods.updateRecord(
								"update Transaction set qty=qty+" + qty + ",amt=amt+" + amt + " where ID =" + tid);
						CommonMethods.delete(tempid);
						colorTable(lblT.getText(), Color.red);

					} else {
						// System.out.println("Item Not in Transaction add new name="+name+" qty="+qty);
						int id = CommonMethods.getId("select ID from Transaction order by ID");
						int y = CommonMethods.addRecord("insert into Transaction(ID,ITEMNAME,QTY,RATE,AMT,BILL) values("
								+ id + ",'" + name + "'," + qty + "," + rate + "," + amt + "," + bill + ")");
						if (y == 1) {
							CommonMethods.delete(tempid);
						}
					}
					tid = 0;
					qty = 0.0f;
					qty = 0.0f;
					rate = 0.0f;
					amt = 0.0f;
					tempid = 0;
					// so not in Transaction and i just add new
//CommonMethods.addRecord("insert into Transaction( ID,ITEMNAME,QTY,RATE,AMT,BILL) Values("+CommonMethods.getId("select Id From Transaction orderby ID")
//+",'"+name+"',"+);

				}
				rs.close();

			}

			/*
			 * // BILLNO BILLAMT DISCOUNT CUSTOMERID WAITORID TABLENO USERID BILLDATE
			 * BILLTIME PAYMODE
			 */
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in Savind Data", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	void paid() {
		try {
			String table = lblT.getText();
			int ta = CommonLogic.getTableId(lblT.getText());
			// System.out.println("Table Id="+ta);
			if (table.equals("")) {
				return;
			}
			if (txtNotes.getText().equals("")) {
				// JOptionPane.showMessageDialog(null, "Enter Received note","Empty",
				// JOptionPane.ERROR_MESSAGE);
				txtNotes.requestFocus();
				return;
			}
			if (txtToCust.getText().equals("")) {
				txtToCust.requestFocus();
				return;
			}
			// ckeck Bill is closed or not

			String get = "";
			int billno = 0;

			ResultSet rs = CommonMethods.selectQuery("select id from TempTransaction where tableno=" + ta);
			if (!rs.next()) {
				get = "";
			}
			rs.close();
			// System.out.println("get = "+get.length());
			if (get.equals(null)) {
				return;
			}
			if (get.equals("")) {
				// find in bill
				String query = "select bill.billno from Bill where Tableno=" + ta + " and status='no'";
				rs = CommonMethods.selectQuery(query);
				rs.next();
				billno = rs.getInt(1);
				// closing getted billno
// 			if(txtNotes.getText().equals(""))
// 			{
// 				//JOptionPane.showMessageDialog(null, "Enter Received note","Empty", JOptionPane.ERROR_MESSAGE);
// 				txtNotes.requestFocus();
// 				return;
// 			}
// 			if(txtToCust.getText().equals(""))
// 			{
// 				txtToCust.requestFocus();
// 				return;
// 			}
				// paid
				int i = CommonMethods.updateRecord(
						"update Bill set discount=" + Float.parseFloat(txtDiscount.getText()) + ",BillAMT="
								+ Float.parseFloat(txtNettotal.getText()) + ",Status='paid' where BillNo=" + billno);
				// System.out.println("i "+i);
				if (i == 1) {
					// write this bill in
					new GetBackup();
					// System.out.println("Bill Paid");
					colorTable(table, btnAdd.getBackground());
					model.setRowCount(0);
					txtNotes.setText("");
					txtDiscount.setText("" + 0.f);
					txtTotal.setText("" + 0.0f);
					txtNettotal.setText("" + 0.0f);
					txtReturn.setText("" + 0.0f);
					txtToCust.setText("" + 0);

// 				Thread t1 = new Thread(){
// 				     public void run()
// 				     {
// 				           System.out.println(i);
// 				           loadBill();
// 				                   
// 				     }
// 				 };
// 				t1.start();
// 				
// 				checkStatus();
				}
			} else {
				// System.out.println("Table not closed");
				JOptionPane.showMessageDialog(null, "Close Table First", "Close Table", JOptionPane.ERROR_MESSAGE);
				return;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No data", "Error", JOptionPane.ERROR_MESSAGE);
			// e.printStackTrace();
			return;
		}

	}

	void addTable() // To Shifting
	{
		List<Object> table = CommonLogic.getAllTable();
		List<?> Bill = CommonLogic.getRunningTableFromBill();
		List<?> temp = CommonLogic.getRunningTable();
		for (Object name : table) {
			if (!Bill.contains(name) && !temp.contains(name)) {

				cmbTable.addItem(name);
			}
		}
	}

	void addTableToSelect() // To Shifting
	{
		List<Object> tableselect = CommonLogic.getAllTable();
		List<?> Billselect = CommonLogic.getRunningTableFromBill();
		List<?> tempselect = CommonLogic.getRunningTable();
		for (Object name : tableselect) {
			if (!Billselect.contains(name) && !tempselect.contains(name)) {

				cmbSelect.addItem(name);
			}
		}
	}

	public void shift(String newtable1, String oldtable1) {
		try {
			if (newtable1.equals("") || oldtable1.equals("")) {
				JOptionPane.showMessageDialog(null, "Select Correct Table To shift", "Empty",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			int oldtable = CommonLogic.getTableId(oldtable1);
			int newtable = CommonLogic.getTableId(newtable1);
			// find and update in Bill
			int billno = 0;
			ResultSet rs = CommonMethods.selectQuery("select tableno,BillNo from Bill where Status='no'");
			while (rs.next()) {
				if (rs.getInt(1) == oldtable) {
					billno = rs.getInt(2);
				}
			}
			if (billno > 0) {
				// System.out.println("I Found Table in Bill "+billno);
				CommonMethods.updateRecord("update Bill Set Tableno=" + newtable + " where Billno=" + billno);
				colorTable(oldtable1, btnAdd.getBackground());
				colorTable(newtable1, Color.RED);
				lblT.setText(newtable1);
				cmbTable.removeAllItems();
				addTable();
			} else {
				// System.out.println("NOt in Bill ");
			}
			rs.close();
			// find in TempTransaction
			rs = CommonMethods.selectQuery("select tableno, Id from TempTransaction");
			while (rs.next()) {
				if (rs.getInt(1) == oldtable) {
					CommonMethods.updateRecord(
							"update TempTransaction set Tableno=" + newtable + " where ID=" + rs.getInt(2));
					colorTable(newtable1, Color.GREEN);
					colorTable(oldtable1, btnAdd.getBackground());
					lblT.setText(newtable1);
					cmbTable.removeAllItems();
					addTable();

				}
			}
			rs.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error in Shifting Table", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	void loadBill() {
		oldmodel.setRowCount(0);
		float amt = 0.0f, dis = 0.0f;
		String htmstart = "<html><font face=\"kiran\" size=\"5\">";
		try {
			ResultSet rs = CommonMethods.selectQuery(
					"select BILLNO,BILLAMT,Discount,WAITORID,TABLENO,USERID,BILLDATE,PAYMODE from BILL where Status='paid' order by BillNo");
			while (rs.next()) {
				amt = rs.getFloat(2);
				dis = rs.getFloat(3);

				oldmodel.addRow(new Object[] { rs.getInt(1), (amt + dis), dis, amt,
						htmstart + CommonLogic.getWaitorName(rs.getInt(4)), CommonLogic.getTableName(rs.getInt(5)),
						htmstart + CommonLogic.getUserName(rs.getInt(6)), rs.getString(7), rs.getString(8) });
			}
			rs.close();
			// int r = model.getRowCount();

		} catch (Exception e) {
			System.out.println("Error in loadBill" + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	void editBill1(int bill) {
		try {
			// int sr=0;
			model.setRowCount(0);
			int tableid;
			ResultSet r = CommonMethods
					.selectQuery("select tableno,waitorid,Discount,BillAmt from Bill where Billno=" + bill);
			r.next();
			tableid = r.getInt(1);
			ResultSet rs = CommonMethods
					.selectQuery("SELECT ItemName,qty,rate,amt from Transaction where bill=" + bill);
			int id = CommonMethods.getId("select Id from TempTransaction order by id");
			while (rs.next()) {
				CommonMethods.addRecord(
						"insert into TempTransaction(id,Itemname,qty,rate,amt,tableno,waitorid,printqty) values("
								+ (id++) + ",'" + rs.getString(1) + "'," + rs.getFloat(2) + "," + rs.getFloat(3) + ","
								+ rs.getFloat(4) + "," + tableid + "," + r.getInt(2) + ",0)");
				// model.addRow(new Object[]
				// {++sr,rs.getString(1),rs.getFloat(3),rs.getFloat(2),rs.getFloat(4)});
			}
			rs.close();
			CommonMethods.deleteBill(bill);

			lblT.setText(CommonLogic.getTableName(tableid));
			getTabledata(lblT.getText());
			checkStatus();
			loadBill();
			addTable();
			// checkStatus();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void editBill(int bill) {
		try {

			System.out.println("To edit " + bill);
			// select Total diiscount, recieved amount from Bill

			String query = "select (BillAmt+Discount),discount from Bill where billno=" + bill;
			ResultSet rs = CommonMethods.selectQuery(query);
			while (rs.next()) {
				// System.out.println("Total Amount "+rs.getFloat(1));
				txtTotal.setText("" + rs.getFloat(1));
				// System.out.println("Discount "+rs.getFloat(2));
				txtDiscount.setText("" + rs.getFloat(2));

			}
			rs.close();
			String q = "select ItemName,rate,qty,amt,bill from transaction where bill=" + bill;
			rs = CommonMethods.selectQuery(q);
			int sr = 0;
			model.setRowCount(0);
			while (rs.next()) {

				model.addRow(new Object[] { ++sr, rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4) });
			}
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void save(int bill) {
		try {
			// System.out.println("To update"+bill);
			float amt = (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText()));
			String query = "update bill set BillAmt=" + amt + ", Discount=" + Float.parseFloat(txtDiscount.getText())
					+ ", CustomerId=0, PayMode='Cash' where billno=" + bill;
			String qq = "update passbook set Amount=0 where Tid=" + bill;
			// System.out.println(query);
			logger.info(query);
			logger.info(qq);
			int j = CommonMethods.updateRecord(query);
			CommonMethods.updateRecord(qq);
			if (j == 1) {
				System.out.println("Bill update Saved");
			}
			CommonMethods.deleteTransaction(bill);
			int sr = CommonMethods.getId("select ID from Transaction order by ID");
			int row = model.getRowCount();
			int g = 0;
			for (int i = 0; i < row; i++) {

				String q = "insert into transaction(id,ItemName,qty,rate,amt,bill) values(" + (sr++) + ",'"
						+ model.getValueAt(i, 1) + "'," + model.getValueAt(i, 3) + "," + model.getValueAt(i, 2) + ","
						+ model.getValueAt(i, 4) + "," + bill + ")";

				// System.out.println(q);

				g = CommonMethods.addRecord(q);
			}
			if (g == 1) {
				JOptionPane.showMessageDialog(this, "Bill change Success", "Update Success",
						JOptionPane.INFORMATION_MESSAGE);
				btnClear.setVisible(true);
				btnPaid.setEnabled(true);
				btnCredit.setEnabled(true);
				// btnSave.setEnabled(false);
				btnClose.setEnabled(true);
				btnoldbill.setEnabled(true);
				btnShift.setEnabled(true);
				model.setRowCount(0);
				txtNotes.setText("");
				txtDiscount.setText("" + 0.f);
				txtTotal.setText("" + 0.0f);
				txtNettotal.setText("" + 0.0f);
				txtReturn.setText("" + 0.0f);
				txtToCust.setText("" + 0);
				tblOldBill.clearSelection();
				loadBill();

				checkStatus();
				btnSave.setVisible(false);

				return;

			}
		} catch (Exception e) {
			System.out.println("Error in Save " + e.getMessage());
			// TODO: handle exception
		}
	}

	void addTable1() {
		try {
			checkStatus();
			if (txtItem.getText().equals("") || txtRate.getText().equals("") || txtQty.getText().equals("")
					|| txtamt.getText().equals("" + 0.0)) {
				JOptionPane.showMessageDialog(this, "Please Select Item Again", "Wrong !", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int row = table.getRowCount();
			float qty = Float.parseFloat(txtQty.getText());
			float rate = Float.parseFloat(txtRate.getText());
			float amt = Float.parseFloat(txtamt.getText());
			float total = Float.parseFloat(txtTotal.getText());
			String name = "" + txtItem.getText();
			if (row == 0)// First Time
			{
				if (qty < 0.0f) {
					JOptionPane.showMessageDialog(this, "Cant Add Quqntity", "Error", JOptionPane.ERROR_MESSAGE);
					txtQty.requestFocus();
					return;
				}
				txtTotal.setText("" + (total + amt));
				txtNettotal
						.setText("" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
				model.addRow(new Object[] { ++row, name, rate, qty, amt });

				clear();
				return;
			}
			if (row > 0) {
				// Already it have items
				int i = 0;
				int flag = -1;
				for (i = 0; i < row; i++) {
					if (table.getValueAt(i, 1).equals(name)) {
						// System.out.println("Found name"+table.getValueAt(i, 1));
						flag = i;
						// return;
					} else {
						// flag=-1;
					}
				}
				if (flag == -1) {

					// String name = ""+cmbItem.getSelectedItem();
					if (qty > 0) {
						// System.out.println("Item Not in table Fresh");
						int sr = model.getRowCount();
						txtTotal.setText("" + (total + amt));
						txtNettotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
						model.addRow(new Object[] { ++sr, name, rate, qty, amt });
						// CommonLogic.addTempTransaction(name, qty, rate, amt, tableno, wid,qty);
						clear();
					} else {
						JOptionPane.showMessageDialog(this, "Cant Add Quqntity", "Error", JOptionPane.ERROR_MESSAGE);
						txtQty.requestFocus();
					}

					return;

				}
				if (flag > -1) {
					// System.out.println("Item exist "+flag);
					// System.out.println("Exist Item "+table.getValueAt(flag, 2));

					table.setValueAt((Float.parseFloat("" + table.getValueAt(flag, 3)) + qty), flag, 3);
					table.setValueAt((Float.parseFloat("" + table.getValueAt(flag, 4)) + amt), flag, 4);
					txtTotal.setText("" + (total + amt));
					txtNettotal.setText(
							"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
					clear();

					return;
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Adding Record" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

	}
}
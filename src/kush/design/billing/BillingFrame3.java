package kush.design.billing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

//import com.jidesoft.dialog.JideOptionPane;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import ankush.SetData;
import kush.POJO.Bill;
import kush.POJO.TempTransaction;
import kush.POJO.Transaction;
import kush.design.ChooseCreditor;
import kush.design.GetBackup;
import kush.print.BillPdf;
import kush.print.QuotationPDF;

public class BillingFrame3 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private TablePanel tablePanel;
	private JPanel tablePanel;
	private JTextField txtSelectedTabel;
//for category Search Box
	////////////////////////////////////
	private JTextField txtCategory;
	private JPopupMenu pop;
	private List<String> list;
	private JList<String> jList;
	private DefaultListModel<String> cmodel;
	private Font font = CommonLogic.font;
	private JScrollPane scroll;
	//////////////////////////////////
	// for Item Search Box
	////////////////////////////////////
	private JTextField txtItemName;
	private JPopupMenu iPop;
	private List<String> iList;
	private JList<String> iJList;
	private DefaultListModel<String> iModel;
	private JScrollPane iScroll;
	//////////////////////////////////

	private JLabel lblMaalacaoNaava;
	private JTextField txtCode;
	private JLabel lblMaalacaaKaod;
	private JTextField txtQty, txtRate, txtAmount;
	private JButton btnAdd;
	private JLabel lblNaga, lblBaava, lblRkma;
	private DefaultTableModel model, modelOldBill;
	private JTable tblBill;
	private JButton btnClose, btnPaid, btnCredit, btnOldBill;
	private JTextField txtReturn, txtDiscount, txtReceived, txtToCust, txtTotal;
	private JLabel lblNewLabel_1, lblToCust, lblReturn, lblDiscount, lblTotal, lblGrandTotal, lblVaotr;
	private JTextField txtGrandTotal;
	private JButton btnEdit2;
	private JTable tblOldBill;
	private JScrollPane scrollPane_1;
	private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	private JComboBox<Object> cmbWaitor;
	private JComboBox<Object> cmbEmptyTable;
	private JPanel panel_1, panel;
	JList<String> Catlist, Itemlist;
	private int id;
	final static Logger logger = Logger.getLogger(BillingFrame3.class);
	private JTextField txtSearchBill;

	public BillingFrame3(int id) {

		this.id = id;
		setTitle("Billing Frame3             Developed by Ankush Supnar(8329394603)");
		setSize(1280, 800);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		getContentPane().setLayout(null);
		tablePanel = new JPanel();
		tablePanel.setBackground(new Color(70, 130, 180));
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		tablePanel.setBounds(1, 1, 424, 800);
		tablePanel.setLayout(null);
		tablePanel = new TablePanel(this);

		// tablePanel.setSize(359, 722);
		tablePanel.setLocation(1, 1);

		getContentPane().add(tablePanel);
		((TablePanel) tablePanel).refresh();
		panel = new JPanel();
		// panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panel.setBounds(430, 1, 538, 490);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("k^TogaIrI");
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewLabel.setBounds(6, 6, 41, 20);
		panel.add(lblNewLabel);

		generateCategorySearchBox();

		lblMaalacaoNaava = new JLabel("maalacao naava");
		lblMaalacaoNaava.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblMaalacaoNaava.setBounds(144, 61, 60, 20);
		panel.add(lblMaalacaoNaava);

		generateItemSearchBox();

		txtCode = new JTextField();
		txtCode.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCode.setColumns(10);
		txtCode.setBounds(5, 81, 78, 35);
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
						txtItemName.requestFocus();
						return;
					}
					int icode = Integer.parseInt(txtCode.getText());

					if (txtCategory.getText().equals(""))// ||cmbCategory.getSelectedItem().equals(null))
					{
						return;
					}
					String name = CommonLogic.getNameusingCode(icode, "" + txtCategory.getText());
					if (!name.equals("")) {
						txtItemName.setText(name);
						float rate = CommonLogic.getRateUsingName(txtItemName.getText());
						txtRate.setText("" + rate);
						txtQty.requestFocus();
					}
					if (txtItemName.getText().equals("")) {
						txtItemName.requestFocus();
					}
				} else {// System.out.println("name is empty");
					txtItemName.setText("");
				}
			}
		});

		panel.add(txtCode);

		lblMaalacaaKaod = new JLabel("maalacaa kaoD");
		lblMaalacaaKaod.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblMaalacaaKaod.setBounds(5, 63, 65, 20);
		panel.add(lblMaalacaaKaod);

		txtQty = new JTextField();
		txtQty.setColumns(10);
		txtQty.setBounds(277, 81, 88, 35);
		txtQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

				txtQty.selectAll();
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtQty.getText().equals("") || txtQty.getText().equals(null) || txtQty.getText().equals("" + 0)
						|| txtItemName.getText().equals("") || txtItemName.getText().equals(null)) {
					// txtQty.requestFocus();
					txtQty.setText("" + 0);
					return;
				}
				String name = "" + txtItemName.getText();
				float q = Float.parseFloat("" + txtQty.getText());
				float rate = CommonLogic.getRateUsingName(name);
				txtRate.setText("" + rate);
				txtRate.selectAll();
				float amt = q * rate;

				// cmbItem.getSelectedObjects();
				txtAmount.setText("" + amt);

			}
		});
		txtQty.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtQty.addKeyListener(new KeyAdapter() {
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
					txtAmount.setText("" + amt);
					if ((c == KeyEvent.VK_ENTER)) {
						// txtQty.nextFocus();
						txtRate.requestFocus();
						txtRate.selectAll();
					}
				} catch (Exception e2) {
					return;
				}
			}
		});

		panel.add(txtQty);

		txtRate = new JTextField();
		txtRate.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtRate.setColumns(10);
		txtRate.setBounds(362, 81, 88, 35);
		txtRate.addKeyListener(new KeyAdapter() {
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
				txtAmount.setText("" + amt);
				if ((c == KeyEvent.VK_ENTER)) {
					btnAdd.setFocusable(true);
					// txtAmount.nextFocus();
					btnAdd.requestFocus();
				}
			}
		});
		panel.add(txtRate);

		txtAmount = new JTextField();
		txtAmount.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtAmount.setColumns(10);
		txtAmount.setBounds(447, 81, 85, 35);
		txtAmount.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (txtAmount.getText().equals("" + 0.0) || txtAmount.getText().equals("")) {
						txtItemName.requestFocus();
					}
					btnAdd.requestFocus();
				}
			}
		});
		txtAmount.setEditable(false);
		panel.add(txtAmount);

		lblNaga = new JLabel("naga");
		lblNaga.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNaga.setBounds(304, 61, 18, 20);
		panel.add(lblNaga);

		lblBaava = new JLabel("Baava");
		lblBaava.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblBaava.setBounds(387, 61, 21, 20);
		panel.add(lblBaava);

		lblRkma = new JLabel("r@kma");
		lblRkma.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblRkma.setBounds(472, 61, 33, 20);
		panel.add(lblRkma);

		btnAdd = new JButton("A^D");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
					txtCategory.requestFocus();
				}
			}
		});
		btnAdd.addActionListener(e -> {
			addBtn();
		});
		btnAdd.setFont(new Font("Kiran", Font.BOLD, 25));
		btnAdd.setBounds(6, 120, 100, 40);
		panel.add(btnAdd);

		JButton btnOrder = new JButton("Aa^D-r");
		btnOrder.addActionListener(e -> {
			if (!txtSelectedTabel.getText().equals("")) {
				Order(txtSelectedTabel.getText());
			}
		});
		btnOrder.setFont(new Font("Kiran", Font.BOLD, 25));
		btnOrder.setBounds(132, 120, 100, 40);
		panel.add(btnOrder);

		JButton btnEdit = new JButton("eiDT");
		btnEdit.addActionListener(e -> {
			if (tblBill.getSelectedRow() >= 0) {
				// System.out.println("selected " + tblBill.getSelectedRow());
				txtItemName.setText(model.getValueAt(tblBill.getSelectedRow(), 1).toString());
				txtRate.setText(model.getValueAt(tblBill.getSelectedRow(), 3).toString());
				txtQty.requestFocus();
			}
		});
		btnEdit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnEdit.setBounds(233, 120, 100, 40);
		panel.add(btnEdit);

		JButton btnRemove = new JButton("irmauvh");

		btnRemove.addActionListener(e -> {
			if (tblBill.getSelectedRow() >= 0) {

				if (btnClose.isEnabled()) {
					// showError("" + model.getValueAt(tblBill.getSelectedRow(), 1).toString());
					CommonMethods.delete(
							GetData.getTempTransactionId(model.getValueAt(tblBill.getSelectedRow(), 1).toString(),
									CommonLogic.getTableId(txtSelectedTabel.getText()),
									Float.parseFloat(model.getValueAt(tblBill.getSelectedRow(), 3).toString())));
					loadInJTable(CommonLogic.getTableId(txtSelectedTabel.getText()));
					((TablePanel) tablePanel).refresh();
				} else {

					model.removeRow(tblBill.getSelectedRow());
					txtTotal.setText("" + 0.0f);
					int sr = 0;
					for (int i = 0; i < tblBill.getRowCount(); i++) {
						System.out.println(model.getValueAt(i, 0));
						txtTotal.setText("" + (Float.parseFloat(model.getValueAt(i, 4).toString())
								+ Float.parseFloat(txtTotal.getText())));
						model.setValueAt(++sr, i, 0);
					}

				}
			} else
				showError("No Selected");
		});
		btnRemove.setFont(new Font("Kiran", Font.BOLD, 25));
		btnRemove.setBounds(332, 120, 100, 40);
		panel.add(btnRemove);

		JButton btnClear = new JButton("iklaAr");
		btnClear.addActionListener(e -> clear1());
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(430, 120, 100, 40);
		panel.add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 160, 526, 243);
		panel.add(scrollPane);

		model = new DefaultTableModel();

		tblBill = new JTable();
		tblBill.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Sr.No", "Item Name", "Quantity", "Rate", "Amount" }));
		tblBill.getColumnModel().getColumn(0).setPreferredWidth(41);
		tblBill.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblBill.getColumnModel().getColumn(2).setPreferredWidth(90);
		tblBill.getColumnModel().getColumn(3).setPreferredWidth(90);
		tblBill.getColumnModel().getColumn(4).setPreferredWidth(90);
		tblBill.setFont(new Font("Kiran", Font.PLAIN, 20));
		tblBill.setFillsViewportHeight(true);
		tblBill.setSurrendersFocusOnKeystroke(true);
		tblBill.setShowVerticalLines(true);
		tblBill.setShowHorizontalLines(true);
		tblBill.setRowHeight(20);
		scrollPane.setViewportView(tblBill);
		model = (DefaultTableModel) tblBill.getModel();

		btnClose = new JButton("klaaoja");
		btnClose.addActionListener(e -> close(id));
		btnClose.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClose.setBounds(6, 453, 78, 36);
		panel.add(btnClose);

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
		btnPaid.addActionListener(e -> paid());
		btnPaid.setFont(new Font("Kiran", Font.BOLD, 25));
		btnPaid.setBounds(82, 453, 63, 36);
		panel.add(btnPaid);

		btnCredit = new JButton("k`oiDT");
		btnCredit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnCredit.setBounds(144, 453, 71, 36);
		btnCredit.addActionListener(e -> credit());
		panel.add(btnCredit);

		btnOldBill = new JButton("jaunao baIla");
		btnOldBill.addActionListener(e -> {
			int select = tblOldBill.getSelectedRow();
			if (select >= 0)
				new BillPdf((int) modelOldBill.getValueAt(select, 0));
		});
		btnOldBill.setFont(new Font("Kiran", Font.BOLD, 25));
		btnOldBill.setBounds(217, 453, 98, 36);
		panel.add(btnOldBill);

		txtReturn = new JTextField("" + 0.0f);
		txtReturn.setEditable(false);
		txtReturn.setColumns(10);
		txtReturn.setBounds(207, 400, 85, 28);
		panel.add(txtReturn);

		txtDiscount = new JTextField("" + 0.0f);
		txtDiscount.setEditable(false);
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(207, 425, 85, 28);
		panel.add(txtDiscount);

		lblNewLabel_1 = new JLabel("Recieved");
		lblNewLabel_1.setBounds(6, 410, 51, 16);
		panel.add(lblNewLabel_1);

		lblToCust = new JLabel("To Cust");
		lblToCust.setBounds(6, 430, 51, 16);
		panel.add(lblToCust);

		lblReturn = new JLabel("Return");
		lblReturn.setBounds(157, 410, 51, 16);
		panel.add(lblReturn);

		lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(157, 430, 51, 16);
		panel.add(lblDiscount);

		txtReceived = new JTextField();
		txtReceived.addKeyListener(new KeyAdapter() {
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
							"" + (Float.parseFloat(txtReceived.getText()) - Float.parseFloat(txtTotal.getText())));
					txtToCust.requestFocus();
				}
			}
		});
		txtReceived.setColumns(10);
		txtReceived.setBounds(60, 400, 85, 28);
		panel.add(txtReceived);

		txtToCust = new JTextField();
		txtToCust.setColumns(10);
		txtToCust.setBounds(60, 425, 85, 28);
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
						txtGrandTotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
						btnPaid.requestFocus();
					}
				}
			}
		});
		panel.add(txtToCust);

		txtTotal = new JTextField("" + 0.0f);
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtTotal.setColumns(10);
		txtTotal.setBounds(418, 400, 114, 28);
		panel.add(txtTotal);

		txtGrandTotal = new JTextField("" + 0.0f);
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setColumns(10);
		txtGrandTotal.setBounds(418, 425, 114, 28);
		panel.add(txtGrandTotal);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(390, 412, 27, 16);
		panel.add(lblTotal);

		lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(352, 436, 64, 16);
		panel.add(lblGrandTotal);
		// panel.add(label_1);

		btnEdit2 = new JButton("eiDT");
		btnEdit2.addActionListener(e -> edit());
		btnEdit2.setFont(new Font("Kiran", Font.BOLD, 25));
		btnEdit2.setBounds(315, 453, 70, 36);
		panel.add(btnEdit2);
		if (id != 1) {
			btnEdit2.setVisible(false);
		}

		cmbEmptyTable = new JComboBox<>();
		cmbEmptyTable.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cmbEmptyTable.setBounds(387, 453, 78, 36);
		panel.add(cmbEmptyTable);
		addTable();
		JButton btnShift = new JButton("Shift");
		btnShift.addActionListener(e -> {
			if (btnClose.isEnabled())
				shift(cmbEmptyTable.getSelectedItem().toString(), txtSelectedTabel.getText());
		});
		btnShift.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnShift.setBounds(462, 453, 70, 36);
		panel.add(btnShift);

		lblVaotr = new JLabel("vaoTr");
		lblVaotr.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblVaotr.setBounds(273, 6, 23, 20);
		panel.add(lblVaotr);

		cmbWaitor = new JComboBox<>(new Vector<>(CommonLogic.getAllWaitorName()));
		cmbWaitor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!cmbWaitor.getSelectedItem().equals(""))
						txtCategory.requestFocus();
				}
			}
		});
		cmbWaitor.setBounds(263, 25, 145, 35);
		cmbWaitor.setFont(new Font("Kiran", Font.PLAIN, 25));
		panel.add(cmbWaitor);

		txtSelectedTabel = new JTextField();
		txtSelectedTabel.setEditable(false);
		txtSelectedTabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		txtSelectedTabel.setHorizontalAlignment(JTextField.CENTER);

		txtSelectedTabel.setBounds(443, 5, 89, 55);
		panel.add(txtSelectedTabel);

		addOldBillTable();

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panel_1.setBounds(971, 1, 89, 485);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnPrev20 = new JButton("<< 20");
		btnPrev20.setBounds(430, 680, 90, 28);
		btnPrev20.addActionListener(e -> {
			loadOldBillData(Integer.parseInt(modelOldBill.getValueAt(0, 0).toString()), 20);
		});
		getContentPane().add(btnPrev20);

		JButton btnPrev10 = new JButton("<10");
		btnPrev10.addActionListener(e -> {
			loadOldBillData(Integer.parseInt(modelOldBill.getValueAt(0, 0).toString()), 10);
		});
		btnPrev10.setBounds(523, 680, 90, 28);
		getContentPane().add(btnPrev10);

		loadQtyButton();
		addItemList();

		setVisible(true);
	}

	public void refresh() {
		((TablePanel) tablePanel).refresh();
		// loadOldBillData(CommonMethods.getId("select BillNo from Bill order by
		// BillNo") - 1, 10);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		new BillingFrame3(2);
	}

	public JTextField getTxtSelectedTabel() {

		return txtSelectedTabel;
	}

	public JComboBox<Object> getcmbWaitor() {
		return cmbWaitor;
	}

	public JTextField getCategoryText() {
		return txtCategory;
	}

	public void generateCategorySearchBox() {
		try {
			txtCategory = new JTextField();

			txtCategory.setBounds(5, 25, 260, 35);
			txtCategory.setColumns(10);
			txtCategory.setFont(new Font("Kiran", Font.PLAIN, 25));

			pop = new JPopupMenu();
			list = CommonLogic.getCategory();
			cmodel = new DefaultListModel<>();
			jList = new JList<>(cmodel);
			jList.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					char c = ke.getKeyChar();
					if (c == KeyEvent.VK_ENTER) {
						if (jList.getSelectedIndex() != -1) {
							txtCategory.setText("" + jList.getSelectedValue());
							// popup.hide();
							pop.setVisible(false);
							txtCode.requestFocus();

							// popup.hide();
							// popup.setVisible(false);
						}
					}
				}

			});
			jList.setFont(font);
			scroll = new JScrollPane();
			scroll.setViewportView(jList);

			txtCategory.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					// catlistmodel.clear();
					// catlistmodel.removeAllElements();
					findItem("" + txtCategory.getText());
					if (!list.isEmpty()) {
						pop.add(scroll); // your component
						pop.setPopupSize(txtCategory.getWidth(), 300);
						pop.show(txtCategory, 0, txtCategory.getHeight());
						txtCategory.requestFocus();
					}

					char c = e.getKeyChar();

					if (c == KeyEvent.VK_ENTER) {

						jList.setSelectedIndex(0);
						jList.requestFocus();
					}
				}

			});
			pop.setLayout(new BorderLayout());
			panel.add(txtCategory);
		} catch (Exception e) {
			System.out.println("Error in Getting Category Search Box " + e.getMessage());

		}
	}

	void findItem(String find) {
		cmodel.removeAllElements();

		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).startsWith(find)) {

					cmodel.addElement(list.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	void generateItemSearchBox() {
		try {
			txtItemName = new JTextField();
			txtItemName.setColumns(10);
			txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
			txtItemName.setBounds(80, 81, 200, 35);
			iPop = new JPopupMenu();
			iList = CommonLogic.getItemName(txtCategory.getText());
			iModel = new DefaultListModel<>();
			iJList = new JList<>(iModel);
			iJList.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent ke) {
					char c = ke.getKeyChar();
					if (c == KeyEvent.VK_ENTER) {

						if (iJList.getSelectedIndex() != -1) {
							txtItemName.setText(iJList.getSelectedValue());
							float rate = CommonLogic.getRateUsingName(txtItemName.getText());
							txtRate.setText("" + rate);
							txtCode.setText("" + CommonLogic.getCodeFromName(txtItemName.getText()));
							iPop.setVisible(false);
							txtQty.requestFocus();
						}
						if (txtItemName.getText().equals("")) {
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

			iJList.setFont(font);

			iScroll = new JScrollPane();
			iScroll.setViewportView(iJList);

			txtItemName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (txtCategory.getText().equals("") || txtCategory.getText().equals(null)) {
						return;
					}
					iList = CommonLogic.getItemName(txtCategory.getText());
					findItemFilter(txtItemName.getText());
					if (!iList.isEmpty()) {
						iPop.add(iScroll); // your component
						iPop.setPopupSize(txtItemName.getWidth(), 300);
						iPop.show(txtItemName, 0, txtItemName.getHeight());
						txtItemName.requestFocus();
					}

					char c = e.getKeyChar();

					if (c == KeyEvent.VK_ENTER) {

						iJList.setSelectedIndex(0);
						iJList.requestFocus();
					}
				}
			});

			iPop.setLayout(new BorderLayout());

			panel.add(txtItemName);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void findItemFilter(String find) {
		iModel.removeAllElements();
		iModel.clear();
		// System.out.println("i got"+find);
		try {
			for (int i = 0; i < iList.size(); i++) {
				if (iList.get(i).startsWith(find)) {
					// MySortStrings.add(MyStrings.get(i));
					iModel.addElement(iList.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	public void setTxtSelectedTabel(String txt) {
		this.txtSelectedTabel.setText(txt);
		// System.out.println("Table Id " + CommonLogic.getTableId(txt));
		btnClose.setEnabled(true);
		btnPaid.setEnabled(true);
		tblOldBill.clearSelection();
		btnEdit2.setText("eiDT");
		loadInJTable(CommonLogic.getTableId(txt));
	}

	public void loadQtyButton() {
		btn1 = new JButton("1");
		btn1.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn1.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn1.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn1.setBounds(0, 0, 89, 50);
		panel_1.add(btn1);

		btn2 = new JButton("2");
		btn2.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn2.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn2.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn2.setBounds(0, 48, 89, 50);
		panel_1.add(btn2);

		btn3 = new JButton("3");
		btn3.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn3.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn3.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn3.setBounds(0, 97, 89, 50);
		panel_1.add(btn3);

		btn4 = new JButton("4");
		btn4.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn4.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn4.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn4.setBounds(0, 145, 89, 50);
		panel_1.add(btn4);

		btn5 = new JButton("5");
		btn5.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn5.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn5.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn5.setBounds(0, 192, 89, 50);
		panel_1.add(btn5);

		btn6 = new JButton("6");
		btn6.addActionListener(e -> {

			txtQty.setText(txtQty.getText() + btn6.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn6.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn6.setBounds(0, 240, 89, 50);
		panel_1.add(btn6);

		btn7 = new JButton("7");
		btn7.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn7.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn7.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn7.setBounds(0, 287, 89, 50);
		panel_1.add(btn7);

		btn8 = new JButton("8");
		btn8.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn8.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn8.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn8.setBounds(0, 335, 89, 50);
		panel_1.add(btn8);

		btn9 = new JButton("9");
		btn9.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn9.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn9.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn9.setBounds(0, 383, 89, 50);
		panel_1.add(btn9);

		btn0 = new JButton("0");
		btn0.addActionListener(e -> {
			txtQty.setText(txtQty.getText() + btn0.getText());
			txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
			btnAdd.doClick();
		});
		btn0.setFont(new Font("SansSerif", Font.BOLD, 25));
		btn0.setBounds(0, 430, 89, 50);
		panel_1.add(btn0);

	}

	void addTable() // To Shifting
	{
		List<Object> table = CommonLogic.getAllTable();
		List<?> Bill = CommonLogic.getRunningTableFromBill();
		List<?> temp = CommonLogic.getRunningTable();
		cmbEmptyTable.removeAllItems();
		for (Object name : table) {
			if (!Bill.contains(name) && !temp.contains(name)) {

				cmbEmptyTable.addItem(name);
			}
		}
	}

	void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	void addOldBillTable() {
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(430, 490, 832, 190);
		getContentPane().add(scrollPane_1);

		tblOldBill = new JTable();
		tblOldBill.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblOldBill.setShowVerticalLines(true);
		tblOldBill.setRowHeight(20);
		tblOldBill.setShowHorizontalLines(true);
		tblOldBill.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "BillNo", "Amount", "Dic", "Recieved", "Waitor", "Table", "User", "Date", "Pay Mode",
						"Customer" }));
		tblOldBill.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblOldBill.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblOldBill.getColumnModel().getColumn(2).setPreferredWidth(38);
		tblOldBill.getColumnModel().getColumn(3).setPreferredWidth(60);
		tblOldBill.getColumnModel().getColumn(4).setPreferredWidth(40);
		tblOldBill.getColumnModel().getColumn(5).setPreferredWidth(40);
		tblOldBill.getColumnModel().getColumn(6).setPreferredWidth(56);
		tblOldBill.getColumnModel().getColumn(7).setPreferredWidth(100);
		tblOldBill.getColumnModel().getColumn(8).setPreferredWidth(60);
		tblOldBill.getColumnModel().getColumn(9).setPreferredWidth(150);

		scrollPane_1.setViewportView(tblOldBill);
		// tblOldBill.setBounds(430, 510, 828, 224);
		// getContentPane().add(tblOldBill);
		modelOldBill = (DefaultTableModel) tblOldBill.getModel();

		loadOldBillData((CommonMethods.getId("select BillNo from Bill order by BillNo") - 1), 20);
	}

	public void loadOldBillData(int billno, int limit) {
		try {
			modelOldBill.setRowCount(0);
			Iterator<Bill> i = GetData.getAllBill(billno, limit).iterator();
			Bill bill;
			String htmstart = "<html><font face=\"kiran\" size=\"5\">";
			String customer = "";
			while (i.hasNext()) {
				bill = i.next();
				if (bill.getCUstomerID() == 1)
					customer = "";
				else
					customer = htmstart + CommonLogic.getCustomerNameUsingId(bill.getCUstomerID());
				modelOldBill.addRow(new Object[] { bill.getBillNO(), bill.getBillAmt(), bill.getDiscount(),
						(bill.getBillAmt() - bill.getDiscount()),
						htmstart + CommonLogic.getWaitorName(bill.getWaitorid()),
						CommonLogic.getTableName(bill.getTableno()),
						htmstart + CommonLogic.getUserName(bill.getUserid()), bill.getBillDate(), bill.getPaymode(),
						customer });
			}
			scrollPane_1.validate();
			scrollPane_1.repaint();

			JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
			vertical.setValue(vertical.getMaximum());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int validateTransaction() {
		try {
			if (txtSelectedTabel.getText().equals("") || txtSelectedTabel.getText().equals(null)) {
				showError("Select Table First");
				return 0;
			} else if (txtItemName.getText().equals("")) {
				showError("Select Item First");
				txtItemName.requestFocus();
				return 0;
			} else if (txtQty.getText().equals("") || txtQty.getText().equals("0")) {
				showError("Enter Quantity must be greater than 0");
				txtQty.requestFocus();
				return 0;
			} else if (txtRate.getText().equals("") || txtRate.getText().equals("0")) {
				showError("Rate Must be greater than 0");
				txtRate.requestFocus();
				return 0;
			} else if (txtAmount.getText().equals("") || txtAmount.getText().equals("")) {
				txtAmount.setText("" + (Float.parseFloat(txtRate.getText()) * Float.parseFloat(txtQty.getText())));
				// showError("Select Ite Again");
				// txtItemName.requestFocus();
				return 1;
			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	public void addBtn() {
		try {
			if (validateTransaction() != 1) {
				return;
			}
			// System.out.println("Validate");
			if (!btnClose.isEnabled()) {
				int row = tblBill.getRowCount();
				if (row == 0) {
					// First Item
					model.addRow(new Object[] { 1, txtItemName.getText(), txtQty.getText(), txtRate.getText(),
							txtAmount.getText() });
					txtTotal.setText(
							"" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtAmount.getText())));
					txtGrandTotal.setText(
							"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
					clear1();
				} else {
					int flag = -1;
					for (int i = 0; i < row; i++) {
						if (model.getValueAt(i, 1).toString().equals(txtItemName.getText())
								&& model.getValueAt(i, 3).toString().equals(txtRate.getText())) {
							flag = i;
						}
					}
					if (flag == -1) {
						model.addRow(new Object[] { ++row, txtItemName.getText(), txtQty.getText(),
								txtRate.getText(),
								txtAmount.getText() });
						txtTotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtAmount.getText())));
						txtGrandTotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
						clear1();
					} else {
						model.setValueAt(Float.parseFloat(model.getValueAt(flag, 2).toString())
								+ Float.parseFloat(txtQty.getText()), flag, 2);
						model.setValueAt(Float.parseFloat(model.getValueAt(flag, 4).toString())
								+ Float.parseFloat(txtAmount.getText()), flag, 4);
						txtTotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtAmount.getText())));
						txtGrandTotal.setText(
								"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
						clear1();
					}
				}

				return;
			}
			TempTransaction temp = new TempTransaction();
			temp.setItemName(txtItemName.getText());
			temp.setQty(Float.parseFloat(txtQty.getText()));
			temp.setRate(Float.parseFloat(txtRate.getText()));
			temp.setAmt(Float.parseFloat(txtAmount.getText()));
			temp.setTableNo(CommonLogic.getTableId(txtSelectedTabel.getText()));
			temp.setWaitorId(CommonLogic.getWaitorid(cmbWaitor.getSelectedItem().toString()));
			temp.setPrintQty(temp.getQty());
			SetData.addInTempTransaction(temp);
			loadInJTable(temp.getTableNo());
			((TablePanel) tablePanel).refresh();
			clear1();
		} catch (Exception e) {

		}
	}

	private void loadInJTable(int tid) {
		try {
			txtTotal.setText("" + 0.0f);
			txtGrandTotal.setText("" + 0.0f);
			txtDiscount.setText("" + 0.0f);
			model.setRowCount(0);
			TempTransaction temp;
			int sr = 0;
			Iterator<TempTransaction> i = GetData.getTableData(tid).iterator();
			while (i.hasNext()) {
				temp = i.next();
				model.addRow(new Object[] { ++sr, temp.getItemName(), temp.getQty(), temp.getRate(), temp.getAmt() });
				txtTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + temp.getAmt()));
				txtGrandTotal
						.setText("" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
			}
			Iterator<Transaction> it = GetData.getClosedTableData(tid).iterator();
			Transaction tr;
			sr = 0;
			while (it.hasNext()) {
				tr = it.next();
				model.addRow(new Object[] { model.getRowCount() + 1, tr.getItemName(), tr.getQty(), tr.getRate(),
						tr.getAmt() });
				txtTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + tr.getAmt()));
				txtGrandTotal
						.setText("" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat(txtDiscount.getText())));
			}

		} catch (Exception e) {
			showError("Error in Loading Table Data " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void clear1() {
		txtItemName.setText("");
		txtQty.setText("");
		txtRate.setText("" + 0.0f);
		txtAmount.setText("" + 0.0f);
		txtCode.setText("");
		txtCategory.setText("");
	}

	private void close(int id) {
		try {
			if (tblBill.getRowCount() == 0) {
				showError("NO Data to Close Table!!");
				return;
			}
			Bill bill = new Bill();

			bill.setBillNO(GetData.getNewBillNo(CommonLogic.getTableId(txtSelectedTabel.getText())));
			bill.setBillAmt(Float.parseFloat(txtTotal.getText()));
			bill.setDiscount(Float.parseFloat(txtDiscount.getText()));
			bill.setCUstomerID(1);
			bill.setWaitorid(CommonLogic.getWaitorid(cmbWaitor.getSelectedItem().toString()));
			bill.setTableno(CommonLogic.getTableId(txtSelectedTabel.getText()));
			bill.setUserid(id);
			bill.setBillDate("");
			bill.setPaymode("Cash");
			bill.setStatus("no");
			bill.setTime("");
			if (SetData.saveBill(bill) == 1) {

				SetData.saveTransaction(GetData.getTableData(bill.getTableno()), bill);
				loadInJTable(bill.getTableno());
				new BillPdf(bill.getBillNO());

				JOptionPane.showMessageDialog(this, "Bill Closed Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				refresh();
			}
		} catch (Exception e) {
			showError("Error in Closing Table " + e.getMessage());
		}
	}

	private int paidValidation() {
		try {
			if (txtReceived.getText().equals("")) {
				txtReceived.requestFocus();
				return 1;
			} else if (txtToCust.getText().equals("")) {
				txtToCust.requestFocus();
				return 1;
			} else if (model.getRowCount() == 0) {
				showError("Close Table First");
				return 0;
			} else if (GetData.checkTableClosed(CommonLogic.getTableId(txtSelectedTabel.getText())) == 1) {
				showError("Close Table First");
				return 0;
			} else
				return 1;

		} catch (Exception e) {
			showError("Error in Paid Validation " + e.getMessage());
			return 0;
		}
	}

	void paid() {
		try {
			if (paidValidation() != 1) {
				return;
			}
			int billno = GetData.getUnpaidBillNo(CommonLogic.getTableId(txtSelectedTabel.getText()));
			String sql = "update Bill set BillAmt=" + Float.parseFloat(txtTotal.getText()) + ",Discount="
					+ Float.parseFloat(txtDiscount.getText()) + ",Paymode='Cash', status='paid' where billNo=" + billno;
			if (CommonMethods.updateRecord(sql) == 1) {
				JOptionPane.showMessageDialog(this, "Bill Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				CommonMethods.addCashCashAccount(Double.parseDouble(txtTotal.getText()));
				addBillInOldBillTable(billno);
				clear2();
			}
		} catch (Exception e) {
			showError("Error in Paid Bill" + e.getMessage());

		}
	}

	private void clear2() {
		txtReceived.setText("");
		txtToCust.setText("" + 0f);
		txtDiscount.setText("" + 0.0f);
		txtReturn.setText("" + 0.0f);
		txtTotal.setText("" + 0.0f);
		txtGrandTotal.setText("" + 0.0f);
		model.setRowCount(0);
		new GetBackup();
		refresh();
	}

	void addBillInOldBillTable(int billno) {
		try {
			Bill bill = GetData.getBill(billno);
			String htmstart = "<html><font face=\"kiran\" size=\"6\">";
			String cust = "";
			if (bill.getCUstomerID() == 1) {
				cust = "";
			} else {
				cust = CommonLogic.getCustomerNameUsingId(bill.getCUstomerID());
			}
			modelOldBill.addRow(new Object[] { bill.getBillNO(), bill.getBillAmt(), bill.getDiscount(),
					(bill.getBillAmt() - bill.getDiscount()), htmstart + CommonLogic.getWaitorName(bill.getWaitorid()),
					CommonLogic.getTableName(bill.getTableno()), htmstart + CommonLogic.getUserName(bill.getUserid()),
					bill.getBillDate(), bill.getPaymode(), htmstart + cust });
			// JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
			// vertical.setValue(vertical.getMaximum() + 2);
		} catch (Exception e) {
			showError("Error in Adding Old table Bill " + e.getMessage());

		}
	}

	void addItemList() {
		try {
			final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14));
			tabbedPane.setBounds(1056, 0, 208, 492);
			getContentPane().add(tabbedPane);

			JPanel panelCategory = new JPanel();
			tabbedPane.addTab("Category", null, panelCategory, null);
			panelCategory.setLayout(null);

			JPanel panelItem = new JPanel();
			tabbedPane.addTab("Item", null, panelItem, null);

			final DefaultListModel<String> itemmodel = new DefaultListModel<String>();
			panelItem.setLayout(null);

			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(0, 0, 208, 471);
			panelItem.add(scrollPane_2);
			Itemlist = new JList<String>(itemmodel);
			scrollPane_2.setViewportView(Itemlist);
			Itemlist.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtItemName.setText(Itemlist.getSelectedValue());
					txtRate.setText("" + CommonLogic.getRateUsingName(txtItemName.getText()));
					txtQty.setText("" + 0);
				}
			});
			Itemlist.setBackground(SystemColor.control);
			Itemlist.setFont(new Font("Kiran", Font.PLAIN, 25));

			DefaultListModel<String> listmodel = new DefaultListModel<String>();

			List<String> l = CommonLogic.getAllCategory();
			Iterator<String> i = l.iterator();
			while (i.hasNext()) {
				listmodel.addElement(i.next());
			}

			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(0, 0, 208, 453);
			panelCategory.add(scrollPane_3);
			Catlist = new JList<String>(listmodel);
			Catlist.setBackground(SystemColor.control);
			scrollPane_3.setViewportView(Catlist);

			Catlist.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// JOptionPane.showMessageDialog(null, "You clicked"+Catlist.getSelectedValue(),
					// "", 0);

					// cmbCategory.setSelectedItem(Catlist.getSelectedValue());
					txtCategory.setText(Catlist.getSelectedValue());
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

			Catlist.setFont(new Font("Kiran", Font.PLAIN, 25));
			if (id == 1) {
				txtSearchBill = new JTextField();
				txtSearchBill.setBounds(614, 680, 122, 28);
				getContentPane().add(txtSearchBill);
				txtSearchBill.setColumns(10);

				JButton btnSearchBill = new JButton("SEARCH");
				btnSearchBill.addActionListener(e -> searchBill());
				btnSearchBill.setBounds(736, 680, 90, 28);
				getContentPane().add(btnSearchBill);
			}
			// Catlist.setBackground(SystemColor.inactiveCaption);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void credit() {
		try {
			if (!btnClose.isEnabled()) {
				int select = tblOldBill.getSelectedRow();
				int bill = Integer.parseInt("" + modelOldBill.getValueAt(select, 0));
				// save(bill);
				saveOldBill();
				new ChooseCreditor(bill);
				this.dispose();
				return;
			}
			int billno = 0;
			int ta = CommonLogic.getTableId(txtSelectedTabel.getText());
			String query = "select bill.billno from Bill where Tableno=" + ta + " and status='no'";
			ResultSet rs = CommonMethods.selectQuery(query);
			rs.next();
			billno = rs.getInt(1);

			new ChooseCreditor(billno);
			this.dispose();
			// dispose();
			// checkStatus();
		} catch (Exception e) {
			System.out.println("Error in Credit Choose");
		}
	}

	void Order(String Table) {
		try {
			if (tblBill.getRowCount() <= 0) {
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
					new QuotationPDF(Table);
					CommonMethods.updateRecord(
							"update TempTransaction set printqty=0 where Tableno=" + CommonLogic.getTableId(Table));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Order " + e.getMessage());
			return;
		}
	}

	void edit() {
		try {
			int select = tblOldBill.getSelectedRow();

			if (select == -1) {
				return;
			}
			if (btnEdit2.getText().equals("saova")) {
				saveOldBill();

				return;
			}
			btnClose.setEnabled(false);
			btnPaid.setEnabled(false);
			model.setRowCount(0);

			int billno = Integer.parseInt(modelOldBill.getValueAt(select, 0).toString());
			showError("selected Bill=" + billno);
			Bill bill = GetData.getBill(billno);
			Transaction tr;
			Iterator<Transaction> i = GetData.getTransactionList(billno).iterator();
			int sr = 0;
			while (i.hasNext()) {
				tr = i.next();
				model.addRow(new Object[] { ++sr, tr.getItemName(), tr.getQty(), tr.getRate(), tr.getAmt() });
			}
			txtTotal.setText("" + bill.getBillAmt());
			txtDiscount.setText("" + bill.getDiscount());
			txtGrandTotal.setText("" + (bill.getBillAmt() - bill.getDiscount()));
			cmbWaitor.setSelectedItem(CommonLogic.getWaitorName(bill.getWaitorid()));
			txtSelectedTabel.setText(CommonLogic.getTableName(bill.getTableno()));
			txtToCust.setText("" + 0.0f);
			btnEdit2.setText("saova");

		} catch (Exception e) {
			showError("Error in Editing Bill " + e.getMessage());

		}
	}

	void saveOldBill() {

		try {
			if (model.getRowCount() == 0) {
				showError("No Data to Save\n No changes Will Happen");
				btnEdit2.setText("eiDT");
				tblOldBill.clearSelection();
				btnClose.setEnabled(true);
				btnPaid.setEnabled(true);

				model.setRowCount(0);
				return;
			}
			// first upadate bill
			int billno = Integer.parseInt(modelOldBill.getValueAt(tblOldBill.getSelectedRow(), 0).toString());
			Bill bill = GetData.getBill(billno);
			bill.setBillAmt(Float.parseFloat(txtTotal.getText()));
			bill.setDiscount(Float.parseFloat(txtDiscount.getText()));
			bill.setCUstomerID(1);
			bill.setWaitorid(CommonLogic.getWaitorid(cmbWaitor.getSelectedItem().toString()));
			bill.setTableno(CommonLogic.getTableId(txtSelectedTabel.getText()));
			bill.setUserid(id);
			bill.setPaymode("Cash");
			bill.setStatus("paid");
			if (SetData.updateBill(bill) == 1) {
				// first delete its Transactions
				CommonMethods.deleteTransaction(bill.getBillNO());
				CommonMethods.reduceCashCashAccount(
						Double.parseDouble(modelOldBill.getValueAt(tblOldBill.getSelectedRow(), 1).toString()));
				CommonMethods.deleteFromPassbook(bill.getBillNO());
				// add new Transactions

				for (int i = 0; i < model.getRowCount(); i++) {
					Transaction tr = new Transaction();
					System.out.println("" + model.getValueAt(i, 0));
					tr.setId(CommonMethods.getId("select Id from Transaction order by ID"));
					tr.setItemName(model.getValueAt(i, 1).toString());
					tr.setQty(Float.parseFloat(model.getValueAt(i, 2).toString()));
					tr.setRate(Float.parseFloat(model.getValueAt(i, 3).toString()));
					tr.setAmt(Float.parseFloat(model.getValueAt(i, 4).toString()));
					tr.setBill(bill.getBillNO());
					SetData.saveTransaction(tr);
				}

				JOptionPane.showMessageDialog(this, "Bill Update Success ");
				CommonMethods.addCashCashAccount(Double.valueOf(bill.getBillAmt()));
				loadOldBillData(bill.getBillNO(), 20);
				btnEdit2.setText("eiDT");
				tblOldBill.clearSelection();
				btnClose.setEnabled(true);
				btnPaid.setEnabled(true);

				model.setRowCount(0);
				clear2();
			}

		} catch (Exception e) {
			showError("Error in Saving Bill " + e.getMessage());

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
				txtSelectedTabel.setText(newtable1);
				refresh();
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

					txtSelectedTabel.setText(newtable1);
					refresh();
					// cmbTable.removeAllItems();
					addTable();

				}
			}
			rs.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error in Shifting Table", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	public void searchBill() {
		try {
			if (txtSearchBill.getText().equals("")) {
				showError("Enter Bill No");
				txtSearchBill.requestFocus();
				return;
			}

			btnOldBill.removeAll();
			modelOldBill.setRowCount(0);
			addBillInOldBillTable(Integer.parseInt(txtSearchBill.getText()));
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}
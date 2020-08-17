package kush.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import ankush.SetData;
import ankush.design.MyComponents.NumberText;
import ankush.design.MyComponents.WhiteLabel;
import kush.POJO.BankTransaction;
import kush.POJO.PurchaseBill;
import kush.POJO.PurchaseTransaction;

public class ItemPurchaseFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6573067413666434785L;
	private JTextField txtBillNo;
	private JComboBox<String> cmbPartyName, cmbItemName, cmbBankName;
	private DatePicker date;
	private NumberText txtCode, txtRate, txtAmount;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnAdd;
	private JTextField txtTotal;
	private NumberText txtGst;
	private NumberText txtOtherTax;
	private NumberText txtGrandTotal, txtChequeNo;
	private JTextField txtReffNo, txtQty;
	private JTable table_1;
	private DefaultTableModel modelBill;
	SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
	private JCheckBox chkPaid;

	public ItemPurchaseFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Font lblFont = new Font("Kiran", Font.BOLD, 20);
		// Font txtFont = new Font("Kiran", Font.PLAIN, 20);
		Font btnFont = new Font("Kiran", Font.BOLD, 25);
		setLocation(new Point(400, 100));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.desktop, 1, true));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(30, 11, 603, 74);
		getContentPane().add(panel);
		panel.setLayout(null);

		WhiteLabel whtlblBillNo = new WhiteLabel((String) null);
		whtlblBillNo.setBounds(10, 5, 50, 20);
		panel.add(whtlblBillNo);
		whtlblBillNo.setText("ibala naM.");

		txtBillNo = new JTextField("" + CommonMethods.getId("select BillNo from purchasebill order by BillNo"));
		txtBillNo.setBounds(10, 27, 95, 35);
		panel.add(txtBillNo);
		txtBillNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtBillNo.setEditable(false);
		txtBillNo.setFont(new Font("Kiran", Font.PLAIN, 30));

		cmbPartyName = new JComboBox<>(new Vector<>(CommonLogic.getPartyNames()));
		cmbPartyName.setBounds(104, 27, 305, 35);
		panel.add(cmbPartyName);
		cmbPartyName.setFont(new Font("Kiran", Font.PLAIN, 25));

		WhiteLabel whtlblPaatiNaava = new WhiteLabel("paaTI- naava");
		whtlblPaatiNaava.setForeground(SystemColor.desktop);
		whtlblPaatiNaava.setBounds(104, 5, 61, 20);
		panel.add(whtlblPaatiNaava);

		WhiteLabel whtlblIdnaamk = new WhiteLabel("idnaaMk ");
		whtlblIdnaamk.setForeground(SystemColor.desktop);
		whtlblIdnaamk.setBounds(420, 5, 61, 20);
		panel.add(whtlblIdnaamk);

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(421, 26, 163, 35);
		panel.add(date);

		date.setToolTipText("Select date");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.desktop, 1, true));

		panel_1.setBounds(30, 96, 603, 83);
		panel_1.setBackground(SystemColor.inactiveCaption);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtCode = new NumberText();
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtCode.getText().equals("")) {
					if (!CommonLogic.getItemUsingCode(Integer.parseInt(txtCode.getText())).equals("")) {
						cmbItemName.setSelectedItem(CommonLogic.getItemUsingCode(Integer.parseInt(txtCode.getText())));

					} else {
						cmbItemName.removeAll();
					}
					if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
						cmbItemName.requestFocus();
					}
				}
			}
		});
		txtCode.setBounds(6, 40, 90, 35);
		panel_1.add(txtCode);
		txtCode.setFont(new Font("Kiran", Font.PLAIN, 25));

		WhiteLabel whtlblKaodNam = new WhiteLabel((String) null);
		whtlblKaodNam.setForeground(SystemColor.desktop);
		whtlblKaodNam.setBounds(6, 16, 50, 20);
		panel_1.add(whtlblKaodNam);
		whtlblKaodNam.setText("kaoD naM");

		cmbItemName = new JComboBox<String>(new Vector<>(CommonLogic.getItemForPurchase()));
		cmbItemName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					txtQty.requestFocus();
				}
			}
		});
		cmbItemName.setBounds(95, 40, 220, 35);
		panel_1.add(cmbItemName);
		cmbItemName.setFont(new Font("Kiran", Font.PLAIN, 25));

		txtQty = new JTextField();
		txtQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.requestFocus();
				}

			}
		});
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					ke.consume();
				}
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtQty.getText().equals(""))
						txtRate.requestFocus();

				}

			}
		});
		txtQty.setBounds(316, 40, 84, 35);
		panel_1.add(txtQty);
		txtQty.setFont(new Font("Kiran", Font.PLAIN, 25));

		WhiteLabel whtlblMaalaacaoNaava = new WhiteLabel((String) null);
		whtlblMaalaacaoNaava.setForeground(SystemColor.desktop);
		whtlblMaalaacaoNaava.setBounds(100, 16, 78, 20);
		panel_1.add(whtlblMaalaacaoNaava);
		whtlblMaalaacaoNaava.setText("maalaacao naava");

		JLabel whiteLabel = new JLabel("naga");
		whiteLabel.setBounds(327, 18, 78, 20);
		panel_1.add(whiteLabel);
		whiteLabel.setForeground(SystemColor.desktop);
		whiteLabel.setFont(lblFont);

		txtRate = new NumberText();
		txtRate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtRate.getText().equals("") && !txtQty.getText().equals("")) {
						txtAmount.setText(
								"" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
						btnAdd.requestFocus();
					}
				}
			}
		});
		txtRate.setBounds(406, 41, 84, 35);
		panel_1.add(txtRate);
		txtRate.setFont(new Font("Kiran", Font.PLAIN, 25));

		JLabel label = new JLabel("dr");
		label.setBounds(429, 17, 17, 20);
		panel_1.add(label);
		label.setForeground(SystemColor.desktop);
		label.setFont(new Font("Kiran", Font.BOLD, 20));

		txtAmount = new NumberText();
		txtAmount.setEditable(false);
		txtAmount.setBounds(496, 41, 84, 35);
		panel_1.add(txtAmount);
		txtAmount.setFont(new Font("Kiran", Font.PLAIN, 25));

		JLabel lblRkMa = new JLabel("r@kma");
		lblRkMa.setBounds(519, 18, 38, 20);
		panel_1.add(lblRkMa);
		lblRkMa.setForeground(SystemColor.desktop);
		lblRkMa.setFont(new Font("Kiran", Font.BOLD, 20));

		btnAdd = new JButton("A^D");
		btnAdd.addActionListener(e -> addButton());
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER)
					btnAdd.doClick();

			}
		});
		btnAdd.setFont(btnFont);
		btnAdd.setBounds(30, 188, 90, 35);
		getContentPane().add(btnAdd);

		JButton btnClear = new JButton("@laIAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setFont(btnFont);
		btnClear.setBounds(149, 191, 90, 35);
		getContentPane().add(btnClear);

		JButton btnRemove = new JButton("irmauvh");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		btnRemove.setFont(btnFont);
		btnRemove.setBounds(268, 191, 90, 35);
		getContentPane().add(btnRemove);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 237, 603, 219);
		getContentPane().add(scrollPane);
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Sr.No", "Item Name", "Qty", "Rate", "Amount" });
		table = new JTable(model);
		table.setRowHeight(20);
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane.setViewportView(table);

		txtTotal = new NumberText();
		txtTotal.setEditable(false);
		txtTotal.setText("" + 0.0f);
		txtTotal.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtTotal.setBounds(509, 459, 122, 35);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

		WhiteLabel whtlblEkuna = new WhiteLabel((String) null);
		whtlblEkuna.setForeground(SystemColor.desktop);
		whtlblEkuna.setText("ekuNa");
		whtlblEkuna.setBounds(461, 468, 32, 20);
		getContentPane().add(whtlblEkuna);

		WhiteLabel whtlblGst = new WhiteLabel((String) null);
		whtlblGst.setForeground(SystemColor.desktop);
		whtlblGst.setFont(new Font("Lucida Console", Font.BOLD, 16));
		whtlblGst.setText("GST");
		whtlblGst.setBounds(263, 468, 39, 21);
		getContentPane().add(whtlblGst);

		txtGst = new NumberText();
		txtGst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtGst.selectAll();
			}
		});
		txtGst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtGst.getText().equals("")) {
						try {
							System.out.println("Enter");
							txtGrandTotal.setText("" + (Float.parseFloat(txtTotal.getText())
									+ Float.parseFloat(txtGst.getText()) + Float.parseFloat(txtOtherTax.getText())));
						} catch (Exception e) {
							JOptionPane.showMessageDialog(txtGst, "Enter Correct GST Amount", "Error",
									JOptionPane.ERROR_MESSAGE);

							return;
						}
					}
				}
			}
		});
		txtGst.setText("" + 0.0f);
		txtGst.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtGst.setColumns(10);
		txtGst.setBounds(311, 459, 122, 35);
		getContentPane().add(txtGst);

		WhiteLabel whtlblOtherTax = new WhiteLabel((String) null);
		whtlblOtherTax.setForeground(SystemColor.desktop);
		whtlblOtherTax.setText("Other Tax");
		whtlblOtherTax.setFont(new Font("Lucida Console", Font.BOLD, 16));
		whtlblOtherTax.setBounds(203, 505, 99, 17);
		getContentPane().add(whtlblOtherTax);

		txtOtherTax = new NumberText();
		txtOtherTax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtOtherTax.selectAll();
			}
		});
		txtOtherTax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtOtherTax.getText().equals("")) {
						try {
							txtGrandTotal.setText("" + (Float.parseFloat(txtTotal.getText())
									+ Float.parseFloat(txtOtherTax.getText()) + Float.parseFloat(txtGst.getText())));
						} catch (Exception e) {
							JOptionPane.showMessageDialog(txtGst, "Enter Correct Other Tax Amount", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			}
		});
		txtOtherTax.setText("" + 0.0f);
		txtOtherTax.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtOtherTax.setColumns(10);
		txtOtherTax.setBounds(311, 500, 122, 35);
		getContentPane().add(txtOtherTax);

		WhiteLabel whtlblIbalaRkma = new WhiteLabel((String) null);
		whtlblIbalaRkma.setForeground(SystemColor.desktop);
		whtlblIbalaRkma.setText("ibala r@kma");
		whtlblIbalaRkma.setBounds(435, 505, 72, 20);
		getContentPane().add(whtlblIbalaRkma);

		txtGrandTotal = new NumberText();
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setText("" + 0.0f);
		txtGrandTotal.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtGrandTotal.setColumns(10);
		txtGrandTotal.setBounds(509, 500, 122, 35);
		getContentPane().add(txtGrandTotal);

		JButton btnSave = new JButton("saova");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.BOLD, 25));
		btnSave.setBounds(30, 642, 90, 35);
		getContentPane().add(btnSave);

		JButton btnClear2 = new JButton("klaIAr");
		btnClear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAll();
			}
		});
		btnClear2.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear2.setBounds(168, 642, 90, 35);
		getContentPane().add(btnClear2);

		JButton btnUpdate2 = new JButton("ApaDoT");
		btnUpdate2.addActionListener(e -> update());
		btnUpdate2.setFont(new Font("Kiran", Font.BOLD, 25));
		btnUpdate2.setBounds(314, 642, 90, 35);
		getContentPane().add(btnUpdate2);

		JButton btnHome = new JButton("haoma");
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBounds(461, 642, 90, 35);
		getContentPane().add(btnHome);

		WhiteLabel whtlblReffno = new WhiteLabel((String) null);
		whtlblReffno.setForeground(SystemColor.desktop);
		whtlblReffno.setText("Reff.No");
		whtlblReffno.setFont(new Font("Lucida Console", Font.BOLD, 16));
		whtlblReffno.setBounds(30, 467, 77, 17);
		getContentPane().add(whtlblReffno);

		txtReffNo = new JTextField();
		txtReffNo.setBounds(30, 496, 154, 35);
		getContentPane().add(txtReffNo);
		txtReffNo.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Payment Option",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(30, 535, 603, 95);
		panel_2.setBackground(panel_1.getBackground());
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		WhiteLabel whtlblBamkocaoNaava = new WhiteLabel((String) null);
		whtlblBamkocaoNaava.setForeground(SystemColor.desktop);
		whtlblBamkocaoNaava.setText("ba^Mkocao naava");
		whtlblBamkocaoNaava.setBounds(97, 23, 64, 20);
		panel_2.add(whtlblBamkocaoNaava);

		cmbBankName = new JComboBox<>(new Vector<>(CommonLogic.getBankName()));
		cmbBankName.setBounds(97, 45, 252, 35);
		cmbBankName.setFont(new Font("Kiran", Font.PLAIN, 25));
		panel_2.add(cmbBankName);

		WhiteLabel whtlblCaokNam = new WhiteLabel((String) null);
		whtlblCaokNam.setForeground(SystemColor.desktop);
		whtlblCaokNam.setText("caok naM");
		whtlblCaokNam.setBounds(364, 23, 64, 20);
		panel_2.add(whtlblCaokNam);

		txtChequeNo = new NumberText();
		txtChequeNo.setBounds(361, 44, 154, 35);
		panel_2.add(txtChequeNo);

		chkPaid = new JCheckBox("baakI");
		chkPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chkPaid.isSelected()) {
					chkPaid.setText("jamaa");
				} else
					chkPaid.setText("baakI");
			}
		});
		chkPaid.setFont(new Font("Kiran", Font.PLAIN, 25));
		chkPaid.setBounds(6, 45, 53, 24);
		panel_2.add(chkPaid);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(643, 11, 605, 685);
		getContentPane().add(scrollPane_1);

		table_1 = new JTable();
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		table_1.setRowHeight(20);
		table_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sr. No", "Bill No", "Date", "Party Name", "Amount", "Reff.No" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(75);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(75);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(50);
		scrollPane_1.setViewportView(table_1);

		modelBill = (DefaultTableModel) table_1.getModel();
		loadData();
		setSize(1270, 740);

		setVisible(true);
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		// WebLookAndFeel.install();
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
		new ItemPurchaseFrame();

	}

	private void addButton() {
		try {

			if (txtQty.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Quantity", "Empty", JOptionPane.ERROR_MESSAGE);
				txtQty.requestFocus();
				return;
			}
			if (txtRate.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Rate", "Empty", JOptionPane.ERROR_MESSAGE);
				txtRate.requestFocus();
				return;
			}
			// CHeck Table is empty or not?
			int row = table.getRowCount();
			int r = model.getRowCount();
			if (row == 0) {
				model.addRow(new Object[] { 1, (String) cmbItemName.getSelectedItem(), txtQty.getText(),
						txtRate.getText(), txtAmount.getText() });
				txtTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtAmount.getText())));
				txtGrandTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtGst.getText())
						+ Float.parseFloat(txtOtherTax.getText())));
				clear();

			} else {
				// if Table is not Empty

				int pos = -1;
				for (int i = 0; i < r; i++) {
					if (model.getValueAt(i, 1).equals("" + cmbItemName.getSelectedItem())
							&& Float.parseFloat("" + model.getValueAt(i, 3)) == Float.parseFloat(txtRate.getText())) {
						pos = i;
						break;
					}
				}
				if (pos != -1) {
					model.setValueAt(
							(Float.parseFloat(txtQty.getText()) + Float.parseFloat("" + model.getValueAt(pos, 2))), pos,
							2);
					model.setValueAt(
							(Float.parseFloat(txtAmount.getText()) + Float.parseFloat("" + model.getValueAt(pos, 4))),
							pos, 4);
					txtTotal.setText(
							"" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtAmount.getText())));
					txtGrandTotal.setText("" + (Float.parseFloat(txtTotal.getText())
							+ Float.parseFloat(txtGst.getText()) + Float.parseFloat(txtOtherTax.getText())));
					clear();
				} else {
					model.addRow(new Object[] { ++r, (String) cmbItemName.getSelectedItem(), txtQty.getText(),
							txtRate.getText(), txtAmount.getText() });
					txtTotal.setText(
							"" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtAmount.getText())));
					txtGrandTotal.setText("" + (Float.parseFloat(txtTotal.getText())
							+ Float.parseFloat(txtGst.getText()) + Float.parseFloat(txtOtherTax.getText())));
					clear();
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Adding " + e.getMessage());
			return;
		}
	}

	private void save() {
		try {
			// Save Bill
			if (validateData() == 0) {
				return;
			}

			Date date1 = Date.valueOf(date.getDate());
			PurchaseBill p = new PurchaseBill();
			// BillNo, PartyId, Amount, Date, GST, OtherTax, ReffNo, Pay, PayId
			p.setBillNo(Integer.parseInt(txtBillNo.getText()));
			p.setPartyId(CommonLogic.getPartyId("" + cmbPartyName.getSelectedItem()));
			p.setAmount(Double.parseDouble(txtTotal.getText()));
			p.setDate(date1);
			p.setGST(Double.parseDouble(txtGst.getText()));
			p.setOtherTax(Double.parseDouble(txtOtherTax.getText()));
			p.setReffNo(txtReffNo.getText());
			p.setPay(chkPaid.isSelected() ? "Y" : "N");
			System.out.println(p);

			int flag;
			// int flag = AddPOJO.savePurchaseBill(p);
			if (CommonMethods.getId("select BillNo from PurchaseBill order by BillNo") != p.getBillNo()) {
				System.out.println("For Update " + p.getBillNo());
				// first Delete Purchase Transaction
				CommonMethods.deletePurchaseTransaction(p.getBillNo());
				// then reduce Cash From Bank Account
				BankTransaction bt = GetData.getBankTransaction(GetData.getPurchaseBillPayId(p.getBillNo()));
				System.out.println("For update Bt " + bt);
				// then reduce Cash from respective Bank Account
				// CommonMethods.reduceCashFromAccount(bt.getBankId(), bt.getWithdraw());
				CommonMethods.addCashFromAccount(bt.getBankId(), bt.getWithdraw());
				// then delete Bank transaction
				CommonMethods.deleteBankTransaction(bt.getId());
				flag = SetData.updatePurchaseBill(p);
			} else {
				System.out.println("Save");
				flag = SetData.savePurchaseBill(p);
			}

			int flag1 = 0, flag3 = 0;
			PurchaseTransaction pt;
			for (int i = 0; i < model.getRowCount(); i++) {
				pt = new PurchaseTransaction();
				pt.setId(CommonMethods.getId("select Id from purchasetransaction order by ID"));
				pt.setItemName("" + model.getValueAt(i, 1));
				pt.setQty(Integer.parseInt("" + model.getValueAt(i, 2)));
				pt.setRate(Float.parseFloat("" + model.getValueAt(i, 3)));
				pt.setAmount(Float.parseFloat("" + model.getValueAt(i, 4)));
				pt.setBillNo(Integer.parseInt(txtBillNo.getText()));
				System.out.println(pt);
				flag1 = SetData.savePurchaseTransaction(pt);
				addInStock(pt.getItemName(), pt.getQty());
				pt = null;
			}
			if (p.getPay().equals("Y")) {
				BankTransaction bt = new BankTransaction();
				bt.setId(CommonMethods.getId("select Id from BankTransaction order by Id"));
				bt.setParticulars("maala KarodI ibala");
				bt.setDate(p.getDate());
				bt.setChequeNo(txtChequeNo.getText());
				bt.setBankId(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString()));
				bt.setDeposite((double) 0.0f);
				bt.setWithdraw(p.getAmount() + p.getGST() + p.getOtherTax());
				flag3 = SetData.saveBankTransaction(bt);
				SetData.setPayIdToPurchaseBill(p.getBillNo(), bt.getId());
				CommonMethods.reduceCashFromAccount(bt.getBankId(), bt.getWithdraw());
			}
			if (flag == 1 && flag1 == 1 && flag3 == 1) {

				JOptionPane.showMessageDialog(this, "Record Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
				loadData();
				clearAll();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Saving Bill " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}

	private int validateData() {
		if (cmbPartyName.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Select Part Name ", "Error", JOptionPane.ERROR_MESSAGE);
			cmbPartyName.requestFocus();
			return 0;
		}
		if (txtReffNo.getText().equals("")) {
			txtReffNo.setText("-");
		}
		if (Float.parseFloat(txtTotal.getText()) == 0.0f) {
			JOptionPane.showMessageDialog(this, "No Data", "Empty", JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
		if (date.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Select Date", "Empty", JOptionPane.ERROR_MESSAGE);
			date.requestFocus();
			return 0;
		}
		if (chkPaid.isSelected()) {
			if (cmbBankName.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(this, "Select Bank Name", "Empty", JOptionPane.ERROR_MESSAGE);
				return 0;
			} else if (!cmbBankName.getSelectedItem().equals("k^Sa Aka{MT")) {
				if (txtChequeNo.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Enter Cheque No", "Empty", JOptionPane.ERROR_MESSAGE);
					return 0;
				}
			} else if (CommonMethods
					.getAccountBalance(CommonLogic.getBankID(cmbBankName.getSelectedItem().toString())) < Double
							.parseDouble(txtGrandTotal.getText())) {
				JOptionPane.showMessageDialog(this, "Insufficient Balance In\n" + cmbBankName.getSelectedItem(),
						"Error", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
		} else if (cmbBankName.getSelectedItem().equals("k^Sa Aka{MT")) {
			txtChequeNo.setText("N/A");
			return 1;
		}
		return 1;

	}

	private void clearAll() {
		txtBillNo.setText("" + CommonMethods.getId("select BillNo from purchasebill order by BillNo"));
		model.setRowCount(0);
		txtTotal.setText("" + 0.0f);
		txtGst.setText("" + 0.0f);
		txtOtherTax.setText("" + 0.0f);
		txtChequeNo.setText("");
		txtReffNo.setText("");

	}

	private void clear() {
		txtQty.setText("");
		txtRate.setText("");
		txtAmount.setText("" + 0.0f);
		txtCode.setText("");
		txtCode.requestFocus();
	}

	private void remove() {
		int row = table.getSelectedRow();
		if (row != -1) {

			txtTotal.setText(
					"" + (Float.parseFloat(txtTotal.getText()) - Float.parseFloat("" + table.getValueAt(row, 4))));
			txtGrandTotal.setText("" + (Float.parseFloat(txtTotal.getText()) + Float.parseFloat(txtOtherTax.getText())
					+ Float.parseFloat(txtGst.getText())));

			model.removeRow(row);
			// validating table
			int sr = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				table.setValueAt(++sr, i, 0);
			}

		}
	}

	private void addInStock(String name, int qty) {
		try {

			// Find Item In Stock Table
			// int id = CommonMethods.getId("Select Id from ItemMaster where ItemName='" +
			// name + "'");
			ResultSet rs = CommonMethods.selectQuery("select Id from ItemStock where ItemName='" + name + "'");
			if (rs.next()) {
				CommonMethods.updateRecord("update ItemStock set Stock=Stock+" + qty + " where ID=" + rs.getInt(1));
			} else {
				CommonMethods.addRecord("insert into ItemStock(Id,ItemName,Stock)values("
						+ CommonMethods.getId("select Id from ItemStock order by ID") + ",'" + name + "'," + qty + ")");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Adding Stock");
			return;

		}
	}

	private void loadData() {
		try {
			modelBill.setRowCount(0);
			String htmstart = "<html><font face=\"kiran\" size=\"6\">";
			Iterator<PurchaseBill> i = GetData.getPurchaseBillList().iterator();
			PurchaseBill bill;
			int sr = 0;
			while (i.hasNext()) {
				bill = i.next();
				// System.out.println(bill);
				modelBill.addRow(new Object[] { ++sr, bill.getBillNo(), bill
						.getDate(),
						htmstart + CommonLogic.getpartyName(bill.getPartyId()),
						(bill.getAmount() + bill.getGST() + bill.getOtherTax()), bill.getReffNo() });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, e.getMessage());

		}
	}

	private void update() {
		try {
			int row = table_1.getSelectedRow();
			System.out.println();
			// JOptionPane.showMessageDialog(this, "Selected " + row);
			if (row != -1) {
				int billNo = Integer.parseInt(modelBill.getValueAt(row, 1).toString());
				PurchaseBill bill = GetData.getPurchaseBill(billNo);
				// System.out.println("purchase Bill " + bill);
				txtBillNo.setText("" + bill.getBillNo());
				cmbPartyName.setSelectedItem(CommonLogic.getpartyName(bill.getPartyId()));
				// date.setDate(formate.fobill.getDate());
				date.setDate(bill.getDate().toLocalDate());
				txtGst.setText("" + bill.getGST());
				txtOtherTax.setText("" + bill.getOtherTax());
				txtReffNo.setText(bill.getReffNo());
				txtTotal.setText("" + bill.getAmount());
				txtGrandTotal.setText("" + (bill.getAmount() + bill.getGST() + bill.getOtherTax()));
				if (bill.getPay().equals("Y")) {
					chkPaid.setText("jamaa");
					chkPaid.setSelected(true);
					BankTransaction bt = GetData.getBankTransaction(bill.getPayId());
					// System.out.println("Bank Transaction " + bt);
					// System.out.println("bank Name" +
					// CommonLogic.getBankNameUsingId(bt.getBankId()));
					cmbBankName.setSelectedItem(CommonLogic.getBankNameUsingId(bt.getBankId()));
					txtChequeNo.setText(bt.getChequeNo());
				} else {
					chkPaid.setSelected(false);
					chkPaid.setText("baakI");
				}

				// get transaction
				model.setRowCount(0);
				Iterator<PurchaseTransaction> i = CommonLogic.getPurchaseTransacton(billNo).iterator();
				PurchaseTransaction t;
				int sr = 0;
				while (i.hasNext()) {
					t = i.next();
					model.addRow(new Object[] { ++sr, t.getItemName(), t.getQty(), t.getRate(), t.getAmount() });
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

package kush.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import ankush.CommonLogic;
import ankush.CommonMethods;
//import kush.print.ReceiptPdf;
import kush.print.ReceiptPdf;

public class PaymentRecievedFromCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159736901870022781L;
	private JTextField txtCustomername;
	private JList<String> jlist;
	private List<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private JPopupMenu popup1;
	private Font font = CommonLogic.font;
	private JTextField txtCode;
	private JLabel lblAddress;
	JTextField txtChekDebit, txtTotalremaining, txtNote, txtId, txtReceiptno, txtRemaining, txtTotalCredit,
			txtTotaldebit;
	JTextArea txtrAddress;
	private JTextField txtBankCode;
	// BankSearchBOx
	private JPanel pnlBank, pnlcash;
	private JTextField txtBankName;
	private JList<String> jlistBankName;
	private List<String> listBankName;
	private DefaultListModel<String> modelBankName;
	private JScrollPane scrollBankName;
	private JPopupMenu popup;
	private JTextField txtAccountno;
	private JTextField txtChekno;
	private JTextField txtCashrecived;
	JRadioButton rdbtnCash, rdbtnBank;
	DatePicker dateChooser;
	JComboBox<String> cmbBank;
	private JTable table;
	private DefaultTableModel modelTable;
	private JScrollPane scrollPane;

	public PaymentRecievedFromCustomer() {
		getContentPane().setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Payment Recieved From Customer");
		setLocation(200, 80);
		setSize(1051, 653);
		getContentPane().setLayout(null);
		JLabel lblChooseNameOf = new JLabel("ga`ahk  naava");
		lblChooseNameOf.setBounds(281, 51, 73, 20);
		lblChooseNameOf.setForeground(Color.BLACK);
		lblChooseNameOf.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(lblChooseNameOf);
		list = CommonLogic.getAllCustomerFullName();
		model = new DefaultListModel<>();
		jlist = new JList<>(model);
		jlist.setFont(font);
		popup1 = new JPopupMenu();
		popup1.setLayout(new BorderLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(jlist);

		txtCustomername = new JTextField();
		txtCustomername.setBounds(260, 69, 278, 35);
		txtCustomername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				findItem(txtCustomername.getText());
				if (!list.isEmpty()) {
					popup1.add(scroll); // your component
					popup1.setPopupSize(txtCustomername.getWidth(), 300);
					popup1.show(txtCustomername, 0, txtCustomername.getHeight());
					txtCustomername.requestFocus();

				}
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					jlist.requestFocus();
					jlist.setSelectedIndex(0);
				}

			}
		});
		jlist.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					txtCustomername.setText(jlist.getSelectedValue());

					int id = CommonLogic.getCustomerIdUsingName(txtCustomername.getText());
					// System.out.println("Selected Name="+txtCustomername.getText()+"and id="+id);
					txtId.setText("" + id);
					loadUsingId(id);
					popup1.setVisible(false);
				}
			}
		});
		txtCustomername.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(txtCustomername);
		txtCustomername.setColumns(10);

		JLabel lblCode = new JLabel("ga`ahk  k`.");
		lblCode.setBounds(11, 51, 53, 20);
		lblCode.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblCode.setForeground(Color.BLACK);
		getContentPane().add(lblCode);

		txtCode = new JTextField();
		txtCode.setBounds(137, 69, 122, 35);
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					if (!CommonLogic.getCustomerNameUsingCode(txtCode.getText()).equals("")) {
						txtCustomername.setText(CommonLogic.getCustomerNameUsingCode(txtCode.getText()));

						txtCustomername.requestFocus();
					}
					txtCustomername.requestFocus();

				}
			}
		});
		txtCode.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		getContentPane().add(txtCode);
		txtCode.setColumns(10);

		lblAddress = new JLabel("ga`ahk  pattaa");
		lblAddress.setBounds(21, 102, 95, 24);
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(lblAddress);

		txtrAddress = new JTextArea();
		txtrAddress.setBounds(11, 124, 527, 154);
		txtrAddress.setEditable(false);

		txtrAddress.setFont(new Font("Kiran", Font.PLAIN, 25));
		getContentPane().add(txtrAddress);

		final JButton btnSave = new JButton("saovh");
		btnSave.setBounds(17, 573, 90, 36);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnSave.doClick();
				}
			}
		});
		btnSave.setFont(new Font("Kiran", Font.BOLD, 25));
		getContentPane().add(btnSave);

		JButton btnClear = new JButton("iklaAr");
		btnClear.setBounds(296, 573, 90, 36);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		getContentPane().add(btnClear);

		JButton btnHome = new JButton("haoma");
		btnHome.setBounds(440, 573, 90, 36);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		getContentPane().add(btnHome);

		JLabel lblEkunaBaaki = new JLabel("ekuNa baakI");
		lblEkunaBaaki.setBounds(11, 498, 71, 24);
		lblEkunaBaaki.setForeground(Color.BLACK);
		lblEkunaBaaki.setFont(new Font("Kiran", Font.PLAIN, 25));
		getContentPane().add(lblEkunaBaaki);

		txtTotalremaining = new JTextField();
		txtTotalremaining.setText("0.0");
		txtTotalremaining.setBounds(94, 496, 84, 31);
		txtTotalremaining.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotalremaining.setBackground(new Color(255, 160, 122));
		getContentPane().add(txtTotalremaining);
		txtTotalremaining.setColumns(10);

		JLabel lblIbalaNam = new JLabel("naaoT");
		lblIbalaNam.setBounds(21, 525, 27, 24);
		lblIbalaNam.setForeground(Color.BLACK);
		lblIbalaNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		getContentPane().add(lblIbalaNam);

		txtNote = new JTextField();
		txtNote.setBounds(94, 529, 292, 32);
		txtNote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					btnSave.requestFocus();
				}
			}
		});
		txtNote.setFont(new Font("Kiran", Font.PLAIN, 25));
		getContentPane().add(txtNote);
		txtNote.setColumns(10);

		JLabel label = new JLabel("ga`ahk  kaoD");
		label.setBounds(147, 51, 72, 20);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(label);

		txtId = new JTextField();
		txtId.setBounds(11, 69, 122, 35);
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					ke.consume();
				}

				if (c == KeyEvent.VK_ENTER) {
					if (txtId.getText().isEmpty()) {
						txtCode.requestFocus();
						return;
					}
					if (Integer.parseInt(txtId.getText()) == 0) {
						txtId.selectAll();
						getToolkit().beep();
						ke.consume();
						return;
					}
					txtCustomername.setText(CommonLogic.getCustomerNameUsingId(Integer.parseInt(txtId.getText())));
					loadUsingId(Integer.parseInt(txtId.getText()));

				}
			}
		});
		txtId.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblPaavataiK = new JLabel("paavataI k`.");
		lblPaavataiK.setBounds(11, 10, 53, 20);
		lblPaavataiK.setForeground(Color.BLACK);
		lblPaavataiK.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(lblPaavataiK);

		txtReceiptno = new JTextField("" + CommonMethods.getId("select Id from cashrecieved order by ID"));
		txtReceiptno.setBounds(65, 6, 122, 35);
		txtReceiptno.setFont(new Font("Kiran", Font.PLAIN, 25));
		getContentPane().add(txtReceiptno);
		txtReceiptno.setColumns(10);

		JLabel lblNewLabel = new JLabel("idnaaMk ");
		lblNewLabel.setBounds(204, 12, 39, 20);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel);

		LocalDate date = null;
		DatePickerSettings dateSetting = new DatePickerSettings();
		dateChooser = new DatePicker(dateSetting);
		dateChooser.setDateToToday();
		date = dateChooser.getDate();
		System.out.println("Selected date : " + date);

		dateChooser.isLightweight();
		dateChooser.setBounds(245, 10, 159, 35);
		dateChooser.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		getContentPane().add(dateChooser);

		JPanel panel = new JPanel();
		panel.setBounds(414, 5, 61, 60);
		panel.setBackground(new Color(238, 232, 170));
		getContentPane().add(panel);
		panel.setLayout(null);
		ButtonGroup group = new ButtonGroup();
		rdbtnCash = new JRadioButton("k^Sa");
		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnCash.isSelected()) {
					pnlBank.setVisible(false);
					pnlcash.setVisible(true);

				}
			}
		});
		rdbtnCash.setBounds(6, 2, 45, 29);
		rdbtnCash.setSelected(true);
		panel.add(rdbtnCash);
		rdbtnCash.setFont(new Font("Kiran", Font.PLAIN, 20));

		rdbtnBank = new JRadioButton("ba^Mk ");
		rdbtnBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnBank.isSelected()) {
					pnlBank.setVisible(true);
					txtTotalremaining.setText("" + 0.0f);
				}
			}
		});
		rdbtnBank.setBounds(6, 33, 47, 29);
		panel.add(rdbtnBank);
		rdbtnBank.setFont(new Font("Kiran", Font.PLAIN, 20));
		// setTitle("Choose Creditor Frame");
		group.add(rdbtnBank);
		group.add(rdbtnCash);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(11, 280, 527, 70);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("{QaarI");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_1.setBounds(6, 8, 48, 24);
		panel_1.add(label_1);

		txtRemaining = new JTextField();
		txtRemaining.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtRemaining.setEditable(false);
		txtRemaining.setColumns(10);
		txtRemaining.setBackground(new Color(240, 128, 128));
		txtRemaining.setBounds(255, 28, 114, 31);
		panel_1.add(txtRemaining);

		JLabel label_2 = new JLabel("jamaa");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_2.setBounds(132, 6, 35, 24);
		panel_1.add(label_2);

		txtTotalCredit = new JTextField();
		txtTotalCredit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotalCredit.setEditable(false);
		txtTotalCredit.setBackground(new Color(250, 128, 114));
		txtTotalCredit.setColumns(10);
		txtTotalCredit.setBounds(6, 28, 114, 31);
		panel_1.add(txtTotalCredit);

		JLabel label_3 = new JLabel("maaigala baakI");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_3.setBounds(255, 6, 84, 24);
		panel_1.add(label_3);

		txtTotaldebit = new JTextField();
		txtTotaldebit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotaldebit.setEditable(false);
		txtTotaldebit.setColumns(10);
		txtTotaldebit.setBackground(new Color(152, 251, 152));

		txtTotaldebit.setBounds(129, 28, 114, 31);
		panel_1.add(txtTotaldebit);

		pnlBank = new JPanel();
		pnlBank.setBounds(11, 356, 527, 119);
		pnlBank.setVisible(false);
		getContentPane().add(pnlBank);
		pnlBank.setLayout(null);

		JLabel lblBamkocaaKaod = new JLabel("ba^Mkocaa kaoD");
		lblBamkocaaKaod.setBounds(6, 3, 58, 20);
		pnlBank.add(lblBamkocaaKaod);
		lblBamkocaaKaod.setFont(new Font("Kiran", Font.PLAIN, 20));

		txtBankCode = new JTextField();
		txtBankCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					txtBankName.setText(CommonLogic.getBankNameUsingCode(txtBankCode.getText()));
					txtAccountno.setText(CommonLogic.getBankAccountNOName(txtBankName.getText()));
					txtBankName.requestFocus();
					// txtChekno.requestFocus();
				}
			}
		});
		txtBankCode.setBounds(6, 20, 86, 35);
		pnlBank.add(txtBankCode);
		txtBankCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ba^Mkocao naava");
		lblNewLabel_1.setBounds(100, 3, 53, 20);
		pnlBank.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 20));

		txtBankName = new JTextField();
		txtBankName.setBounds(105, 19, 267, 35);
		pnlBank.add(txtBankName);
		txtBankName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				findItemBankName(txtBankName.getText());
				if (!listBankName.isEmpty()) {
					popup.add(scrollBankName); // your component
					popup.setPopupSize(txtBankName.getWidth(), 100);
					popup.show(txtBankName, 0, txtBankName.getHeight());
					txtBankName.requestFocus();
					txtAccountno.setText(CommonLogic.getBankAccountNOName(txtBankName.getText()));

				}
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					jlistBankName.requestFocus();
					jlistBankName.setSelectedIndex(0);
				}

			}
		});
		txtBankName.setFont(new Font("Kiran", Font.PLAIN, 20));
		txtBankName.setColumns(10);

		JLabel lblAkamtNam = new JLabel("Aka{MT naM");
		lblAkamtNam.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblAkamtNam.setBounds(367, 5, 56, 20);
		pnlBank.add(lblAkamtNam);

		txtAccountno = new JTextField();
		txtAccountno.setEditable(false);
		txtAccountno.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtAccountno.setBounds(377, 18, 148, 35);
		pnlBank.add(txtAccountno);
		txtAccountno.setColumns(10);

		JLabel lblCaokNam = new JLabel("caok naM");
		lblCaokNam.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblCaokNam.setBounds(6, 57, 33, 20);
		pnlBank.add(lblCaokNam);

		txtChekno = new JTextField();
		txtChekno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					ke.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					if (!txtChekno.getText().isEmpty() && !txtBankName.getText().isEmpty()
							&& !txtAccountno.getText().isEmpty()) {
						txtChekDebit.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "Select Bank Account Again", "Bank Not FOund",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		txtChekno.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtChekno.setBounds(6, 73, 147, 35);
		pnlBank.add(txtChekno);
		txtChekno.setColumns(10);

		JLabel lblRkkma = new JLabel("rkkma");
		lblRkkma.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblRkkma.setBounds(375, 57, 34, 20);
		pnlBank.add(lblRkkma);

		txtChekDebit = new JTextField("");
		txtChekDebit.setBounds(377, 73, 148, 35);
		pnlBank.add(txtChekDebit);
		txtChekDebit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					ke.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					if (txtChekDebit.getText().isEmpty()) {
						System.out.println("Enter Customer");
						return;
					}
					if (Float.parseFloat(txtChekDebit.getText()) == 0) {
						txtChekDebit.selectAll();
						getToolkit().beep();
						ke.consume();
						return;
					}
					if (txtCode.getText().equals("") || txtCustomername.getText().equals("")
							|| CommonLogic.getCustomerIdUsingName(txtCustomername.getText()) == 0) {
						JOptionPane.showMessageDialog(null, "select Proper Name Of Customer", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtCode.requestFocus();
						return;
					}
					txtTotalremaining.setText(
							"" + (Float.parseFloat(txtRemaining.getText()) - Float.parseFloat(txtChekDebit.getText())));
					txtNote.requestFocus();
				}

			}
		});
		txtChekDebit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtChekDebit.setColumns(10);

		cmbBank = new JComboBox<String>();
		cmbBank.setFont(font);
		cmbBank.addItem("yauinayana ba^Mk saaona[-");
		cmbBank.addItem("ba^Mk Aa^f maharaYT/");
		cmbBank.addItem("sToT ba^Mk Aa^f [MiDyaa");
		cmbBank.addItem("yaSa malTIsToT saaona[-");
		cmbBank.addItem("A^ksaIsa ba^Mk");
		cmbBank.addItem("Aaya saI Aaya saI ba^Mk");
		cmbBank.addItem("eca DI ef saI ba^Mk");
		cmbBank.setBounds(155, 73, 215, 35);
		pnlBank.add(cmbBank);

		JLabel lblBamkocaoNaava = new JLabel("ba^Mkocao naava");
		lblBamkocaoNaava.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblBamkocaoNaava.setBounds(155, 57, 86, 20);
		pnlBank.add(lblBamkocaoNaava);

		pnlcash = new JPanel();
		pnlcash.setBounds(13, 356, 527, 118);
		getContentPane().add(pnlcash);
		pnlcash.setLayout(null);

		JLabel lblRaokaJamaa = new JLabel("raoKa jamaa");
		lblRaokaJamaa.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblRaokaJamaa.setBounds(6, 6, 58, 20);
		pnlcash.add(lblRaokaJamaa);

		txtCashrecived = new JTextField();
		txtCashrecived.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					ke.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					if (txtCashrecived.getText().isEmpty()) {
						System.out.println("Enter Customer");
						return;
					}
					if (Float.parseFloat(txtCashrecived.getText()) == 0) {
						txtCashrecived.selectAll();
						getToolkit().beep();
						ke.consume();
						return;
					}
					if (txtCode.getText().equals("") || txtCustomername.getText().equals("")
							|| CommonLogic.getCustomerIdUsingName(txtCustomername.getText()) == 0) {
						JOptionPane.showMessageDialog(null, "select Proper Name Of Customer", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtCode.requestFocus();
						return;
					}
					txtTotalremaining.setText(""
							+ (Float.parseFloat(txtRemaining.getText()) - Float.parseFloat(txtCashrecived.getText())));
					txtNote.requestFocus();
				}
			}
		});
		txtCashrecived.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCashrecived.setBounds(6, 27, 122, 35);
		pnlcash.add(txtCashrecived);
		txtCashrecived.setColumns(10);

		modelTable = new DefaultTableModel();
		modelTable.addColumn("Sr no");
		modelTable.addColumn("Date");
		modelTable.addColumn("Customer Name");
		modelTable.addColumn("Amount");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(540, 5, 495, 604);
		getContentPane().add(scrollPane);
		table = new JTable(modelTable);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		loadData();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (modelTable.getRowCount() < 1) {
					return;
				}

			}
		});
		scrollPane.setViewportView(table);

		JButton btnEdit = new JButton("eiDT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0) {

					int rno = Integer.parseInt("" + modelTable.getValueAt(table.getSelectedRow(), 0));

					edit(rno);
				}

			}
		});
		btnEdit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnEdit.setBounds(153, 573, 90, 36);
		getContentPane().add(btnEdit);

		// Bank Name Search Box
		listBankName = CommonLogic.getSearchName("select BankName from BankDetails order by BankName");
		modelBankName = new DefaultListModel<>();
		jlistBankName = new JList<>(modelBankName);
		jlistBankName.setFont(font);
		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());
		scrollBankName = new JScrollPane();
		scrollBankName.setViewportView(jlistBankName);
		jlistBankName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					txtBankName.setText(jlistBankName.getSelectedValue());
					txtAccountno.setText(CommonLogic.getBankAccountNOName(txtBankName.getText()));
					txtBankCode.setText(CommonLogic.getBankCode(txtBankName.getText()));
					// int id = CommonLogic.getCustomerIdUsingName(txtBankName.getText());
					popup.setVisible(false);
					txtChekno.requestFocus();
				}
			}
		});

		setVisible(true);
	}

	public void loadData() {
		try {
			modelTable.setRowCount(0);
			String htmstart = "<html><font face=\"kiran\" size=\"5\">";
			ResultSet rs = CommonMethods.selectQuery("select id,cid,Date,Amount from CashRecieved");
			while (rs.next()) {
				// CommonLogic.getCustomerNameUsingId(rs.getInt(2))
				modelTable.addRow(new Object[] { rs.getInt(1), rs.getDate(3),
						htmstart + CommonLogic.getCustomerNameUsingId(rs.getInt(2)), rs.getFloat(4) });
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			// TODO: handle exception
		}
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

		new PaymentRecievedFromCustomer();

	}

	void findItem(String find) {
		model.removeAllElements();

		try {
			for (int i = 0; i < list.size(); i++) {
				if (((String) list.get(i)).startsWith(find)) {

					model.addElement((String) list.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	void save() {
		// find Receipt for update or not
		try {
			int id = Integer.parseInt(txtReceiptno.getText());
			ResultSet rs = CommonMethods.selectQuery("select id from cashrecieved where  id=" + id);
			if (rs.next()) {
				System.out.println("available");
				update(id);
			} else {
				if (txtCode.getText().equals("") || txtCustomername.getText().equals("")
						|| CommonLogic.getCustomerIdUsingName(txtCustomername.getText()) == 0) {
					JOptionPane.showMessageDialog(this, "select Proper Name Of Customer", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtCode.requestFocus();
					return;
				}
				if (txtrAddress.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Select Customer Again", "error Message",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (rdbtnCash.isSelected()) {
					if (txtCashrecived.getText().equals("") || txtCashrecived.getText().equals(null)
							|| txtCashrecived.getText().equals("" + 0)) {
						JOptionPane.showMessageDialog(this, "Enter more then 0", "Empty", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (txtNote.getText().equals("") || txtNote.getText().equals(null)) {
						txtNote.setText(" ");
					}

					// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00",
					// Locale.getDefault());
					LocalDate Date = dateChooser.getDate();
					// System.out.println("Party Bank Name"+cmbBank.getSelectedItem());
					String query = "insert into cashrecieved(id,CID,Amount,Date,BankId,ChequeNo,Note,BankName) values("
							+ Integer.parseInt(txtReceiptno.getText()) + "," + Integer.parseInt(txtId.getText()) + ","
							+ Float.parseFloat(txtCashrecived.getText()) + ",'" + Date + "',0,0,'" + txtNote.getText()
							+ "','NA')";
					int i = CommonMethods.addRecord(query);
					int tit = CommonMethods.getId("select ID from Passbook order by ID");
					String query1 = "insert into passbook(id,CID,Amount,Mode,TId,Date) values(" + tit + ","
							+ Integer.parseInt(txtId.getText()) + "," + Float.parseFloat(txtCashrecived.getText())
							+ ",'Cash'," + Integer.parseInt(txtReceiptno.getText()) + ",'" + Date + "')";
					// System.out.println(query1);
					CommonMethods.addRecord(query1);
					if (i == 1) {
						new ReceiptPdf(Integer.parseInt(txtReceiptno.getText()));
						JOptionPane.showMessageDialog(this, "Record Save Success", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						File htmlFile = new File("D:\\\\Hotel Software\\\\Receipt.pdf");
						Desktop.getDesktop().browse(htmlFile.toURI());
						txtReceiptno.setText("" + CommonMethods.getId("select Id from cashrecieved order by ID"));

						loadData();
						clear();
					}
					// System.out.println(i);

				}
				if (rdbtnBank.isSelected()) {
					if (txtBankCode.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Enter Bank details", "Empty", JOptionPane.ERROR_MESSAGE);
						// txtBankCode.requestFocus();
						return;
					}
					if (txtBankName.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Enter bank Name", "Empty", JOptionPane.ERROR_MESSAGE);
						txtBankName.requestFocus();
						return;
					}
					if (txtAccountno.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Select Bank Once Agani", "Empty",
								JOptionPane.ERROR_MESSAGE);
						txtBankCode.requestFocus();
						return;
					}
					if (txtChekno.getText().equals("") || txtChekno.getText().equals(null)) {
						JOptionPane.showMessageDialog(this, "Enter Valid Cheque No", "Empty",
								JOptionPane.ERROR_MESSAGE);
						txtChekno.requestFocus();
						return;
					}
					if (txtChekDebit.getText().equals("") || txtChekDebit.getText().equals(null)
							|| txtChekDebit.getText().equals("" + 0.0f)
							|| Float.parseFloat(txtChekDebit.getText()) < 0) {
						JOptionPane.showMessageDialog(this, "Enter Valied Amount", "Error", JOptionPane.ERROR_MESSAGE);
						txtChekDebit.setText("");
						txtChekDebit.requestFocus();
						return;
					}

					// System.out.println("Bbank Payment");
					// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00",
					// Locale.getDefault());
					// System.out.println(sdf.format(dateChooser.getDate()));
					LocalDate Date = dateChooser.getDate();
					String query = "insert into CashRecieved(ID,CID,Amount,Date,BankId,ChequeNo,Note,BankName)values("
							+ Integer.parseInt(txtReceiptno.getText()) + "," + Integer.parseInt(txtId.getText()) + ","
							+ Float.parseFloat(txtChekDebit.getText()) + ",'" + Date + "',"
							+ CommonLogic.getBankID(txtBankName.getText()) + "," + Integer.parseInt(txtChekno.getText())
							+ ",'" + txtNote.getText() + "','" + cmbBank.getSelectedItem() + "')";
					// System.out.println(query);
					int i = CommonMethods.addRecord(query);
					int tit = CommonMethods.getId("select ID from Passbook order by ID");
					String query1 = "insert into passbook(id,CID,Amount,Mode,TId,Date) values(" + tit + ","
							+ Integer.parseInt(txtId.getText()) + "," + Float.parseFloat(txtChekDebit.getText())
							+ ",'Cash'," + Integer.parseInt(txtReceiptno.getText()) + ",'" + Date + "')";
					// System.out.println(query1);
					CommonMethods.addRecord(query1);

					if (i == 1) {
						JOptionPane.showMessageDialog(this, "Record Save Success", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						txtReceiptno.setText("" + CommonMethods.getId("select Id from cashrecieved order by ID"));
						new ReceiptPdf(Integer.parseInt(txtReceiptno.getText()));
						// File htmlFile = new File("D:\\\\Hotel Software\\\\Receipt.pdf");
						// Desktop.getDesktop().browse(htmlFile.toURI());

						loadData();
						clear();
					}
				}
				System.out.println("Not Ite New man");
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	void clear() {
		txtReceiptno.setText("" + CommonMethods.getId("select Id from cashrecieved order by ID"));
		txtCode.setText("");
		txtId.setText("");
		txtCashrecived.setText("" + 0.0f);
		txtrAddress.setText("");
		txtChekDebit.setText("" + 0.0f);
		txtNote.setText("");
		txtRemaining.setText("" + 0.0f);
		txtTotalCredit.setText("" + 0.0f);
		txtTotaldebit.setText("" + 0.0f);
		txtTotalremaining.setText("" + 0.0f);
		txtCustomername.setText("");
		dateChooser.setDateToToday();
		txtAccountno.setText("");
		txtBankCode.setText("");
		txtBankName.setText("");
		txtChekDebit.setText("");
		txtChekno.setText("");
		cmbBank.setSelectedItem("");
		rdbtnCash.setSelected(true);
	}

	void loadUsingId(int id) {
		try {
			txtCode.setText(CommonLogic.getCustomerKeyUsingName(txtCustomername.getText()));

			// System.out.println("Got id = "+id);
			txtrAddress.setText(CommonLogic.getCustomerAddress(id));
			txtTotalCredit.setText("" + CommonLogic.getAllCredit(id));
			txtTotaldebit.setText("" + CommonLogic.getAllRecieved(id));

			txtRemaining.setText(
					"" + (Float.parseFloat(txtTotalCredit.getText()) - Float.parseFloat(txtTotaldebit.getText())));

		} catch (Exception e) {
			System.out.println("Error in loadUsingId " + e.getMessage());
			return;
		}
	}

	void findItemBankName(String find) {
		modelBankName.removeAllElements();

		try {
			for (int i = 0; i < listBankName.size(); i++) {
				if (((String) listBankName.get(i)).startsWith(find)) {

					modelBankName.addElement((String) listBankName.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

	void edit(int rno) {
		try {
			// CommonMethods.deleteFromPassbook(rno);
			String name = "" + modelTable.getValueAt(table.getSelectedRow(), 2);
			String name2[] = name.split(">");
			txtCustomername.setText(name2[2]);
			txtReceiptno.setText("" + rno);

			ResultSet rs = CommonMethods.selectQuery(
					"select BankId,ChequeNo,Note,BankName,Amount,date,cid from CashRecieved where id=" + rno);
			rs.next();
			int bankid = rs.getInt(1);
			float amount = rs.getFloat(5);
			int cid = rs.getInt(7);
			txtId.setText("" + cid);
			loadUsingId(cid);
			if (bankid != 0) {
				rdbtnBank.setSelected(true);
				pnlcash.setVisible(false);
				pnlBank.setVisible(true);
				txtChekno.setText(rs.getString(2));
				cmbBank.setSelectedItem(rs.getString(4));
				txtBankName.setText(CommonLogic.getBankNameUsingId(bankid));
				txtAccountno.setText(CommonLogic.getBankAccountNoUsingId(bankid));
				txtBankCode.setText(CommonLogic.getBankCode(txtBankName.getText()));
				txtChekDebit.setText("" + amount);
				LocalDate date = rs.getDate(6).toLocalDate();
				dateChooser.setDate(date);
			} else {
				rdbtnCash.setSelected(true);
				pnlcash.setVisible(true);
				txtCashrecived.setText("" + amount);
				pnlBank.setVisible(false);
				LocalDate date = rs.getDate(6).toLocalDate();
				dateChooser.setDate(date);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void update(int id) {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00",
			// Locale.getDefault());
			LocalDate Date = dateChooser.getDate();
			int cid = Integer.parseInt(txtId.getText());
			if (rdbtnCash.isSelected()) {
				String query = "update cashrecieved set cid=" + cid + ",Amount="
						+ Float.parseFloat(txtCashrecived.getText()) + ",Date='" + Date + "',Note='" + txtNote.getText()
						+ "',BankName='NA'where id=" + id;
				String pass = "update Passbook set Amount=" + Float.parseFloat(txtCashrecived.getText()) + ",Date='"
						+ Date + "' where TId=" + id;
				int flag = CommonMethods.updateRecord(query);
				int flag2 = CommonMethods.updateRecord(pass);
				if (flag == 1 && flag2 == 1) {
					JOptionPane.showMessageDialog(this, "Record Update Success");
					new ReceiptPdf(Integer.parseInt(txtReceiptno.getText()));
					// modelTable.setRowCount(0);
					loadData();
					clear();
				}
			}
			if (rdbtnBank.isSelected()) {
				String query = "update cashrecieved set cid=" + cid + ",amount="
						+ Float.parseFloat(txtChekDebit.getText()) + ",Date='" + Date + "',BankId="
						+ CommonLogic.getBankID(txtBankName.getText()) + ",ChequeNo='" + txtChekno.getText()
						+ "',BankName='" + cmbBank.getSelectedItem() + "',Note='" + txtNote.getText() + "' where id="
						+ txtReceiptno.getText();
				// System.out.println(query);

				String pass = "update passbook set cid=" + cid + ", Amount=" + Float.parseFloat(txtChekDebit.getText())
						+ ", Date='" + Date + "' where tid=" + txtReceiptno.getText();
				int flag = CommonMethods.updateRecord(query);
				int flag1 = CommonMethods.updateRecord(pass);
				if (flag == 1 && flag1 == 1) {
					JOptionPane.showMessageDialog(this, "Record Update Success");
					new ReceiptPdf(Integer.parseInt(txtReceiptno.getText()));
					loadData();
					clear();
				}

				// System.out.println(pass);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Update Record");
			e.printStackTrace();
		}
	}
}

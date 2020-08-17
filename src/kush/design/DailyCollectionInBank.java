package kush.design;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.GetData;
import ankush.SetData;
import kush.POJO.BankTransaction;

public class DailyCollectionInBank extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3558826719247519034L;
	private JTextField txtBankcode;
	private JTextField txtBankname;
	private JList<String> jlist;
	private List<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private JPopupMenu popup;
	private JTextField txtAmount;
	private JTextField txtParticular;
	private JTable table;
	private DefaultTableModel tblmodel;
	private DatePicker date;
	private JTextField txtRefferenceNo;
	private JTextField txtNo;

	public DailyCollectionInBank() {
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(200, 20);

		JLabel lblNam = new JLabel("naM.");
		lblNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNam.setBounds(48, 24, 16, 24);
		getContentPane().add(lblNam);

		txtNo = new JTextField("" + CommonMethods.getId("select Id from BankTransaction order by Id"));
		txtNo.setEditable(false);
		txtNo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtNo.setColumns(10);
		txtNo.setBounds(86, 16, 162, 35);
		getContentPane().add(txtNo);

		JLabel lblIdnaamk = new JLabel("idnaaMk");
		lblIdnaamk.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblIdnaamk.setBounds(260, 66, 52, 24);
		getContentPane().add(lblIdnaamk);

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(339, 60, 214, 35);
		getContentPane().add(date);

		JLabel lblBankcode = new JLabel("ba^Mk kaoD");
		lblBankcode.setFont(CommonLogic.lblfont);
		lblBankcode.setBounds(9, 107, 55, 24);
		getContentPane().add(lblBankcode);

		txtBankcode = new JTextField();
		txtBankcode.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtBankcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {

					if (!txtBankcode.getText().equals("")) {
						System.out.println("not Empty");
						txtBankname.setText(CommonLogic.getBankNameUsingCode(txtBankcode.getText()));
						txtBankname.requestFocus();
					}
				}
			}

		});
		txtBankcode.setBounds(86, 99, 162, 35);
		getContentPane().add(txtBankcode);
		txtBankcode.setColumns(10);

		JLabel lblBankname = new JLabel("ba^Mkocao naava");
		lblBankname.setFont(CommonLogic.lblfont);
		lblBankname.setBounds(260, 107, 66, 24);
		getContentPane().add(lblBankname);

		list = CommonLogic.getBankName();
		model = new DefaultListModel<>();
		jlist = new JList<>(model);
		jlist.setFont(CommonLogic.txtfont);
		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(jlist);

		txtBankname = new JTextField();
		txtBankname.setFont(CommonLogic.txtfont);
		txtBankname.setBounds(338, 101, 238, 35);
		getContentPane().add(txtBankname);
		txtBankname.setColumns(10);
		txtBankname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				findItem(txtBankname.getText());
				if (!list.isEmpty()) {
					popup.add(scroll); // your component
					popup.setPopupSize(txtBankname.getWidth(), 150);
					popup.show(txtBankname, 0, txtBankname.getHeight());
					txtBankname.requestFocus();
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
					txtBankname.setText(jlist.getSelectedValue());
					// int id = CommonLogic.getCustomerIdUsingName(txtCustomername.getText());
					// txtCode.setText(CommonLogic.getCustomerKeyUsingName(txtCustomername.getText()));
					// System.out.println("Got id = "+id);

					txtBankcode.setText(CommonLogic.getBankCode(txtBankname.getText()));

					popup.setVisible(false);

				}
			}
		});

		JButton btnSave = new JButton("saovh");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnSave.doClick();
				}
			}
		});
		btnSave.addActionListener(e -> save());
		btnSave.setFont(CommonLogic.btnfont);
		btnSave.setBounds(9, 186, 98, 36);
		getContentPane().add(btnSave);

		JButton btnClear = new JButton("i@laAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(CommonLogic.btnfont);
		btnClear.setBounds(119, 186, 85, 36);
		getContentPane().add(btnClear);

		JLabel lblAmount = new JLabel("jamaa r@kma");
		lblAmount.setFont(CommonLogic.lblfont);

		lblAmount.setBounds(6, 139, 82, 24);
		getContentPane().add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (c == KeyEvent.VK_ENTER) {
					txtRefferenceNo.requestFocus();
					// txtRate.requestFocus();
				}
			}
		});
		txtAmount.setFont(CommonLogic.txtfont);
		txtAmount.setBounds(86, 139, 162, 35);
		getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(e -> dispose());
		btnHome.setFont(CommonLogic.btnfont);
		btnHome.setBounds(333, 186, 78, 36);
		getContentPane().add(btnHome);

		JLabel lblTapaisala = new JLabel("tapaiSala");
		lblTapaisala.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblTapaisala.setBounds(9, 61, 53, 24);
		getContentPane().add(lblTapaisala);

		txtParticular = new JTextField();
		txtParticular.setEditable(false);
		txtParticular.setText("DolaI klao@Sana");
		txtParticular.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtParticular.setColumns(10);
		txtParticular.setBounds(86, 60, 162, 35);
		getContentPane().add(txtParticular);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 234, 567, 457);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Sr.No.", "Particular", "Date", "Amount", "Bank Name" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1277175355735240208L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblmodel = (DefaultTableModel) table.getModel();
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(164);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(194);
		scrollPane.setViewportView(table);

		JButton btnEdit = new JButton("eiDT");
		btnEdit.addActionListener(e -> edit());
		btnEdit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnEdit.setBounds(227, 186, 85, 36);
		getContentPane().add(btnEdit);

		JLabel lblRofrmsaNam = new JLabel("rofrMsa naM.");
		lblRofrmsaNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblRofrmsaNam.setBounds(260, 149, 66, 24);
		getContentPane().add(lblRofrmsaNam);

		txtRefferenceNo = new JTextField();
		txtRefferenceNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnSave.requestFocus();
				}
			}
		});
		txtRefferenceNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtRefferenceNo.setColumns(10);
		txtRefferenceNo.setBounds(338, 143, 238, 35);
		getContentPane().add(txtRefferenceNo);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(599, 736);
		loadData(txtParticular.getText());
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

		new DailyCollectionInBank();
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

	void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public int validateData() {
		try {
			if (txtBankcode.getText().equals("")) {
				showError("Enter Bank Code");
				return 0;
			} else if (txtBankname.getText().equals("")) {
				showError("Enter Bank Name");
				return 0;
			} else if (txtAmount.getText().equals("")) {
				showError("Enter amount To Deposit");
				return 0;
			} else if (date.getText().equals("")) {
				showError("Select Date");
				return 0;
			} else if (txtRefferenceNo.getText().equals("")) {
				txtRefferenceNo.setText("N/A");
				return 1;
			} else
				return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	public void loadData(String particular) {
		try {
			Iterator<BankTransaction> it = GetData.getBankTransactionList(particular).iterator();
			BankTransaction bt;
			tblmodel.setRowCount(0);
			String htmstart = "<html><font face=\"kiran\" size=\"6\">";
			while (it.hasNext()) {
				bt = it.next();

				tblmodel.addRow(new Object[] { bt.getId(), htmstart + bt.getParticulars(), bt.getDate(),
						bt.getDeposite(), htmstart + CommonLogic.getBankNameUsingId(bt.getBankId()) });
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	public void save() {
		try {
			if (validateData() == 0) {
				return;
			}
			BankTransaction bt = new BankTransaction();

			bt.setParticulars(txtParticular.getText());
			bt.setDate(Date.valueOf(date.getDate()));
			bt.setChequeNo(txtRefferenceNo.getText());
			bt.setBankId(CommonLogic.getBankID(txtBankname.getText()));
			bt.setDeposite(Double.parseDouble(txtAmount.getText()));
			bt.setWithdraw(0.0);
			System.out.println(bt);
			if (CommonMethods.getId("select Id from BankTransaction order by Id") != Integer
					.parseInt(txtNo.getText())) {
				// update
				bt.setId(Integer.parseInt(txtNo.getText()));
				if (SetData.updateBankTransactiob(bt) == 1) {
					JOptionPane.showMessageDialog(this, "Record Update Success",
							"Success",
							JOptionPane.INFORMATION_MESSAGE);
					loadData(txtParticular.getText());
					clear();
				} else {
					showError("Record No Update");
				}
			} else {
				bt.setId(Integer.parseInt(txtNo.getText()));
				if (SetData.saveBankTransaction(bt) == 1) {
					JOptionPane.showMessageDialog(this, "Record Save Success", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					loadData(txtParticular.getText());
					clear();
				} else
					showError("Record Not Save");
			}

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	public void clear() {
		txtNo.setText("" + CommonMethods.getId("select Id from BankTransaction order by Id"));
		txtBankcode.setText("");
		txtAmount.setText("");
		txtBankname.setText("");
		txtRefferenceNo.setText("");
	}

	public void edit() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			BankTransaction bt = GetData.getBankTransaction(Integer.parseInt(table.getValueAt(row, 0).toString()));
			txtNo.setText("" + bt.getId());
			txtBankname.setText(CommonLogic.getBankNameUsingId(bt.getBankId()));
			txtBankcode.setText(CommonLogic.getBankCode(txtBankname.getText()));
			txtAmount.setText("" + bt.getDeposite());
			txtRefferenceNo.setText(bt.getChequeNo());

			System.out.println(bt);
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}

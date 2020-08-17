package kush.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.log4j.BasicConfigurator;

import ankush.CommonLogic;
import ankush.CommonMethods;
import kush.print.BillPdf;

public class ChooseCreditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtCustomername;
	private JList<String> jlist;
	private List<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private JPopupMenu popup;
	private Font font = CommonLogic.font;
	private JTextField txtCode;
	private JLabel lblAddress;
	private JTextArea txtrAddress;
	private JTextField txtTotalcredit;
	private JTextField txtTotaldebit;
	private JTextField txtRemaining;
	private JTextField txtNewcredit;
	private JTextField txtNewremaining;

	public ChooseCreditor(final int BillNo) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

			}
		});

		getContentPane().setBackground(new Color(70, 130, 180));
		setTitle("Choose Creditor Frame");
		setSize(457, 487);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// getContentPane().setBackground(new Color(147, 112, 219));
		getContentPane().setLayout(null);

		JLabel lblChooseNameOf = new JLabel("ga`ahk  naava");
		lblChooseNameOf.setForeground(UIManager.getColor("Table[Enabled+Selected].textForeground"));
		lblChooseNameOf.setFont(new Font("Kiran", Font.BOLD, 20));
		lblChooseNameOf.setBounds(20, 47, 73, 20);
		getContentPane().add(lblChooseNameOf);
		list = CommonLogic.getAllCustomerFullName();
		model = new DefaultListModel<>();
		jlist = new JList<>(model);
		jlist.setFont(font);
		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(jlist);

		txtCustomername = new JTextField();
		txtCustomername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				findItem(txtCustomername.getText());
				if (!list.isEmpty()) {
					popup.add(scroll); // your component
					popup.setPopupSize(txtCustomername.getWidth(), 300);
					popup.show(txtCustomername, 0, txtCustomername.getHeight());
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
					txtCode.setText(CommonLogic.getCustomerKeyUsingName(txtCustomername.getText()));
					// System.out.println("Got id = "+id);
					txtrAddress.setText(CommonLogic.getCustomerAddress(id));
					txtTotalcredit.setText("" + CommonLogic.getAllCredit(id));
					txtTotaldebit.setText("" + CommonLogic.getAllRecieved(id));
					txtRemaining.setText(""
							+ (Float.parseFloat(txtTotalcredit.getText()) - Float.parseFloat(txtTotaldebit.getText())));
					txtNewremaining.setText(
							"" + (Float.parseFloat(txtRemaining.getText()) + Float.parseFloat(txtNewcredit.getText())));
					popup.setVisible(false);

				}
			}
		});
		txtCustomername.setFont(new Font("Kiran", Font.PLAIN, 20));
		txtCustomername.setBounds(105, 42, 267, 35);
		getContentPane().add(txtCustomername);
		txtCustomername.setColumns(10);

		JLabel lblCode = new JLabel("ga`ahk  kaoD");
		lblCode.setFont(new Font("Kiran", Font.BOLD, 20));
		lblCode.setForeground(Color.WHITE);
		lblCode.setBounds(20, 10, 72, 20);
		getContentPane().add(lblCode);

		txtCode = new JTextField();
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
		txtCode.setBounds(105, 6, 122, 35);
		getContentPane().add(txtCode);
		txtCode.setColumns(10);

		lblAddress = new JLabel("ga`ahk  pattaa");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Kiran", Font.BOLD, 25));
		lblAddress.setBounds(6, 88, 95, 24);
		getContentPane().add(lblAddress);

		txtrAddress = new JTextArea();
		txtrAddress.setEditable(false);

		txtrAddress.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtrAddress.setBounds(104, 82, 268, 155);
		getContentPane().add(txtrAddress);

		JLabel lblCredit = new JLabel("{QaarI");
		lblCredit.setForeground(Color.WHITE);
		lblCredit.setFont(new Font("Kiran", Font.BOLD, 25));
		lblCredit.setBounds(103, 239, 48, 24);
		getContentPane().add(lblCredit);

		txtTotalcredit = new JTextField();
		txtTotalcredit.setEditable(false);
		txtTotalcredit.setBackground(new Color(240, 128, 128));
		txtTotalcredit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotalcredit.setBounds(103, 260, 84, 31);
		getContentPane().add(txtTotalcredit);
		txtTotalcredit.setColumns(10);

		JLabel lblDebit = new JLabel("jamaa");
		lblDebit.setForeground(Color.WHITE);
		lblDebit.setFont(new Font("Kiran", Font.BOLD, 25));
		lblDebit.setBounds(199, 239, 35, 24);
		getContentPane().add(lblDebit);

		txtTotaldebit = new JTextField();
		txtTotaldebit.setEditable(false);
		txtTotaldebit.setBackground(new Color(152, 251, 152));
		txtTotaldebit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotaldebit.setBounds(199, 260, 84, 31);
		getContentPane().add(txtTotaldebit);
		txtTotaldebit.setColumns(10);

		JLabel lblRemaining = new JLabel("eku Na baakI");
		lblRemaining.setForeground(Color.WHITE);
		lblRemaining.setFont(new Font("Kiran", Font.BOLD, 25));
		lblRemaining.setBounds(284, 239, 88, 24);
		getContentPane().add(lblRemaining);

		txtRemaining = new JTextField();
		txtRemaining.setBackground(new Color(255, 160, 122));
		txtRemaining.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtRemaining.setEditable(false);
		txtRemaining.setBounds(288, 260, 84, 31);
		getContentPane().add(txtRemaining);
		txtRemaining.setColumns(10);

		JLabel lblNewcredit = new JLabel("AajacaI baakI");
		lblNewcredit.setForeground(Color.WHITE);
		lblNewcredit.setFont(new Font("Kiran", Font.BOLD, 25));
		lblNewcredit.setBounds(6, 300, 100, 24);
		getContentPane().add(lblNewcredit);

		txtNewcredit = new JTextField("" + CommonLogic.getBillAmount(BillNo));
		txtNewcredit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtNewcredit.setEditable(false);
		txtNewcredit.setBounds(113, 297, 132, 31);
		getContentPane().add(txtNewcredit);
		txtNewcredit.setColumns(10);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(BillNo);
			}
		});
		btnSave.setFont(new Font("Kiran", Font.BOLD, 25));
		btnSave.setBounds(11, 397, 90, 36);
		getContentPane().add(btnSave);

		JButton btnClear = new JButton("iklaAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(177, 397, 90, 36);
		getContentPane().add(btnClear);

		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBounds(341, 397, 90, 36);
		getContentPane().add(btnHome);

		JLabel lblNaivanaBaaki = new JLabel("naivaNa baakI");
		lblNaivanaBaaki.setForeground(Color.WHITE);
		lblNaivanaBaaki.setFont(new Font("Kiran", Font.BOLD, 25));
		lblNaivanaBaaki.setBounds(6, 347, 100, 24);
		getContentPane().add(lblNaivanaBaaki);

		txtNewremaining = new JTextField();
		txtNewremaining.setEditable(false);
		txtNewremaining.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtNewremaining.setBounds(112, 340, 132, 31);
		getContentPane().add(txtNewremaining);
		txtNewremaining.setColumns(10);
		setVisible(true);
	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		BasicConfigurator.configure();
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
		// new ChooseCreditor(1562);
		// TODO Auto-generated method stub

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

	public void save(int billno) {
		try {
			if (txtNewremaining.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Select Creditor Correct", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// find bill is exist or not
			int b = 0;
			ResultSet r = CommonMethods
					.selectQuery("select Id from Passbook where Tid=" + billno + " and Mode='Credit'");
			while (r.next()) {
				b = r.getInt(1);
			}
			r.close();
			// System.out.println("bbbbb "+b);
			int id = CommonLogic.getCustomerIdUsingName(txtCustomername.getText());
			if (b > 0) {
				System.out.println("already exist in passbook" + b);
				int j = CommonMethods.updateRecord("update Passbook set CID=" + id + ",Amount="
						+ Float.parseFloat(txtNewcredit.getText()) + ", TID=" + billno + " where id=" + b);
				int f = CommonMethods.updateRecord(
						"update Bill set PayMode='Credit', CustomerId=" + id + ",Status='Paid' where Billno=" + billno);
				if (j == 1 && f == 1) {
					System.out.println("Bill to print " + billno);
					new BillPdf(billno);
					JOptionPane.showMessageDialog(this, "Record Update Success", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					this.dispose();

				}

				return;
			}

			int f = CommonMethods.updateRecord(
					"update Bill set PayMode='Credit', CustomerId=" + id + ",Status='Paid' where Billno=" + billno);
			int tit = CommonMethods.getId("select ID from Passbook order by ID");
			String query = "insert into passbook(id,CID,Amount,Mode,TId,Date) values(" + tit + "," + id + ","
					+ Float.parseFloat(txtNewcredit.getText()) + ",'Credit'," + billno + ",CURDATE())";
			System.out.println(query);
			CommonMethods.addRecord(query);
			if (f == 1) {
				JOptionPane.showMessageDialog(this, "Record Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);

				this.dispose();
				return;
			} else
				return;
		} catch (Exception e) {
			System.out.println("Error while Saving Record " + e.getMessage());
			// TODO: handle exception
		}

	}
}

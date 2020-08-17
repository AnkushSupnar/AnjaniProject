package kush.design;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import ankush.AddPOJO;
import ankush.CommonMethods;
import ankush.design.MyComponents.NumberText;
import kush.POJO.PurchaseParty;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PurchasePartyDetails extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7378315435676355382L;
	private JTextField txtName;
	private JTextField txtId;
	private JTextField txtAddress;
	private JLabel lblMonm;
	private JTextField txtMobileno;
	private JButton btnIlaar;
	private JButton btnHaoma;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	public PurchasePartyDetails() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				loadTable();
			}
		});
		Font lblFont = new Font("Kiran", Font.BOLD, 20);
		Font txtFont = new Font("Kiran", Font.PLAIN, 20);
		Font btnFont = new Font("Kiran", Font.BOLD, 25);
		setTitle("Add Purchase Part Details");
		getContentPane().setBackground(new Color(52, 51, 152));
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(10, 8, 46, 14);
		getContentPane().add(lblId);

		JLabel lblPartyname = new JLabel("naava");
		lblPartyname.setForeground(Color.WHITE);
		lblPartyname.setFont(lblFont);
		lblPartyname.setBounds(106, 6, 66, 20);
		getContentPane().add(lblPartyname);

		txtName = new JTextField();
		txtName.setFont(txtFont);
		txtName.setBounds(86, 33, 343, 35);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		txtId = new JTextField("" + CommonMethods.getId("select ID from purchaseparty order by ID"));
		txtId.setFont(txtFont);
		txtId.setColumns(10);
		txtId.setBounds(10, 33, 66, 35);
		getContentPane().add(txtId);

		JLabel lblPatta = new JLabel("pattaa");
		lblPatta.setForeground(Color.WHITE);
		lblPatta.setFont(new Font("Kiran", Font.BOLD, 20));
		lblPatta.setBounds(10, 80, 66, 20);
		getContentPane().add(lblPatta);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Kiran", Font.PLAIN, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(10, 100, 419, 35);
		getContentPane().add(txtAddress);

		lblMonm = new JLabel("maao.naM");
		lblMonm.setForeground(Color.WHITE);
		lblMonm.setFont(new Font("Kiran", Font.BOLD, 20));
		lblMonm.setBounds(10, 145, 66, 20);
		getContentPane().add(lblMonm);

		txtMobileno = new NumberText();
		txtMobileno.setFont(txtFont);
		txtMobileno.setBounds(10, 163, 188, 35);
		getContentPane().add(txtMobileno);
		txtMobileno.setColumns(10);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnSave.setBounds(10, 210, 90, 35);
		btnSave.setFont(btnFont);
		getContentPane().add(btnSave);

		btnIlaar = new JButton("i@laAr");
		btnIlaar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnIlaar.setFont(new Font("Kiran", Font.BOLD, 25));
		btnIlaar.setBounds(177, 214, 90, 35);
		getContentPane().add(btnIlaar);

		btnHaoma = new JButton("haoma");
		btnHaoma.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHaoma.setBounds(339, 214, 90, 35);
		getContentPane().add(btnHaoma);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 257, 449, 199);
		getContentPane().add(scrollPane);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "Name", "Address", "Contact No" });
		table = new JTable(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(229);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		scrollPane.setViewportView(table);
		setSize(481, 500);
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
		new PurchasePartyDetails();

	}

	public void validatedata() {
		try {
			if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter name", "Empty", JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus();
				return;
			}
			if (txtAddress.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Address", "Empty", JOptionPane.ERROR_MESSAGE);
				txtAddress.requestFocus();
				return;
			}
			if (txtMobileno.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Mobile no", "Empty", JOptionPane.ERROR_MESSAGE);
				txtMobileno.requestFocus();
				return;
			}
		} catch (Exception e) {
			System.out.println("Error in Validating Data " + e.getMessage());

		}
	}

	public void clear() {
		txtName.setText("");
		txtAddress.setText("");
		txtMobileno.setText("");
		txtId.setText("" + CommonMethods.getId("select ID from purchaseparty order by ID"));
	}

	public void save() {
		try {
			PurchaseParty p = new PurchaseParty();
			p.setId(Integer.parseInt(txtId.getText()));
			p.setName(txtName.getText());
			p.setContact(txtMobileno.getText());
			p.setAddress(txtAddress.getText());

			if (AddPOJO.savePurchaseparty(p) == 1) {
				JOptionPane.showMessageDialog(this, "Record Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			System.out.println(p);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in saving", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	private void loadTable() {
		try {
			model.setRowCount(0);
			ResultSet rs = CommonMethods
					.selectQuery("select Id, Name, Address, Contact from purchaseparty order by ID");
			while (rs.next()) {
				model.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) });
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

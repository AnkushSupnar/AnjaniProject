package kush.design;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ankush.CommonLogic;
import ankush.CommonMethods;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddNewBankAccount extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5281097714483613932L;
	private JTextField txtBankname;
	private JTextField txtAccountno;
	private JTextField txtIfccode;
	private JTextField txtPersonname;
	private JTextField txtBankaddress;
	private JTextField txtOpeningbalance;
	JComboBox<String> cmbType;
	JButton btnSave;
	private JTable table;
	DefaultTableModel model;
	private JTextField txtBankid;
	private JTextField txtBankcode;
	public AddNewBankAccount()
	{
		setSize(929,591);
		setLocation(200,80);
		setTitle("Add or Update Bank Details ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(107, 142, 35), new Color(0, 0, 255)));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 452, 177);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ba^Mkocao naava");
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewLabel.setBounds(121, 6, 66, 24);
		panel.add(lblNewLabel);
		
		txtBankname = new JTextField();
		txtBankname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if((!txtBankname.getText().equals(""))&& c==KeyEvent.VK_ENTER)
				{
					txtAccountno.requestFocus();
				}
			}
		});
		txtBankname.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtBankname.setBounds(121, 25, 309, 35);
		panel.add(txtBankname);
		txtBankname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Aka{MT naM");
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(6, 60, 56, 20);
		panel.add(lblNewLabel_1);
		
		txtAccountno = new JTextField();
		txtAccountno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) 
			{
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9')|| (c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE)))
				{
					 getToolkit().beep();
			          ke.consume();
				}
				if(c==KeyEvent.VK_ENTER)
				{
					cmbType.requestFocus();
				}
			}
		});
		txtAccountno.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtAccountno.setBounds(6, 80, 297, 35);
		panel.add(txtAccountno);
		txtAccountno.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aka{MT pa`kar");
		lblNewLabel_2.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(6, 115, 75, 20);
		panel.add(lblNewLabel_2);
		
		cmbType = new JComboBox<String>();
		cmbType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					txtIfccode.requestFocus();
				}
			}
		});
		cmbType.setModel(new DefaultComboBoxModel<String>(new String[] {"saovhIMga", "krMT "}));
		cmbType.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbType.setBounds(6, 135, 115, 35);
		panel.add(cmbType);
		
		JLabel lblIfccode = new JLabel("IFCCODE");
		lblIfccode.setBounds(140, 115, 55, 16);
		panel.add(lblIfccode);
		
		txtIfccode = new JTextField();
		txtIfccode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) 
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER && !txtIfccode.getText().isEmpty())
				{
					txtPersonname.requestFocus();
				}
			}
		});
		txtIfccode.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtIfccode.setBounds(125, 135, 182, 35);
		panel.add(txtIfccode);
		txtIfccode.setColumns(10);
		
		JLabel lblK = new JLabel("k`.");
		lblK.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblK.setBounds(17, 6, 13, 20);
		panel.add(lblK);
		
		txtBankid = new JTextField();
		txtBankid.setEditable(false);
		txtBankid.setText(""+CommonMethods.getId("select ID from Bankdetails order by ID"));
		txtBankid.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtBankid.setBounds(6, 25, 103, 35);
		panel.add(txtBankid);
		txtBankid.setColumns(10);
		
		JLabel lblBamkocaaKaod = new JLabel("ba^Mkocaa kaoD");
		lblBamkocaaKaod.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblBamkocaaKaod.setBounds(308, 64, 58, 20);
		panel.add(lblBamkocaaKaod);
		
		txtBankcode = new JTextField();
		txtBankcode.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtBankcode.setBounds(308, 80, 122, 35);
		panel.add(txtBankcode);
		txtBankcode.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(107, 142, 35), new Color(0, 0, 255)));
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(456, 0, 452, 177);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAkamtHaoldrcaoNaava = new JLabel("Aka{MT haolDrcao naava");
		lblAkamtHaoldrcaoNaava.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblAkamtHaoldrcaoNaava.setBounds(6, 6, 118, 20);
		panel_1.add(lblAkamtHaoldrcaoNaava);
		
		txtPersonname = new JTextField();
		txtPersonname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) 
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER && !txtPersonname.getText().isEmpty())
				{
					txtBankaddress.requestFocus();
				}
			}
		});
		txtPersonname.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtPersonname.setBounds(6, 26, 415, 35);
		panel_1.add(txtPersonname);
		txtPersonname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ba^Mkocaa pattaa");
		lblNewLabel_3.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(6, 61, 62, 20);
		panel_1.add(lblNewLabel_3);
		
		txtBankaddress = new JTextField();
		txtBankaddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER && !txtBankaddress.getText().isEmpty())
				{
					txtOpeningbalance.requestFocus();
				}
			}
		});
		txtBankaddress.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtBankaddress.setBounds(6, 75, 415, 35);
		panel_1.add(txtBankaddress);
		txtBankaddress.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("caalau ba^laMsa");
		lblNewLabel_4.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(6, 109, 60, 20);
		panel_1.add(lblNewLabel_4);
		
		txtOpeningbalance = new JTextField();
		txtOpeningbalance.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtOpeningbalance.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if (!((c >= '0') && (c <= '9')|| (c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE)))
				{
					 getToolkit().beep();
			          ke.consume();
				}
				if(c==KeyEvent.VK_ENTER)
				{
					btnSave.requestFocus();
				}
			}
		});
		txtOpeningbalance.setBounds(6, 130, 140, 35);
		panel_1.add(txtOpeningbalance);
		txtOpeningbalance.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 255, 0), new Color(0, 0, 255)));
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(0, 180, 908, 47);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnSave = new JButton("saovh");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				validation();
				save();
			}
		});
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
		
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					btnSave.doClick();
					
				}
			}
		});
		btnSave.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(238, 130, 238), new Color(0, 0, 255)));
	
		btnSave.setFont(new Font("Kiran", Font.BOLD, 25));
		btnSave.setBounds(21, 6, 90, 36);
		panel_2.add(btnSave);
		
		JButton btnClear = new JButton("iklaAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				clear();
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(238, 130, 238), new Color(0, 0, 255)));
		btnClear.setBounds(177, 6, 90, 36);
		panel_2.add(btnClear);
		
		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(238, 130, 238), new Color(0, 0, 255)));
		btnHome.setBounds(335, 6, 90, 36);
		panel_2.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GREEN, Color.BLUE));
		scrollPane.setBounds(0, 229, 908, 324);
		getContentPane().add(scrollPane);
		
		model  = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Code");
		model.addColumn("Bank Name");
		model.addColumn("Account No");
		model.addColumn("IFC CODE");
		model.addColumn("Name");
		model.addColumn("Balance");

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(model.getRowCount()<1)
				{
					return;
				}
				
				loadBankDetails(Integer.parseInt(""+model.getValueAt(table.getSelectedRow(), 0)));
				//System.out.println("Table Clicked"+model.getRowCount());
				//System.out.println("Table Clicked"+model.getValueAt(table.getSelectedRow(), 0));
			}
		});
		//table.setFont(new Font("Kiran", Font.PLAIN, 25));
		loadTable();
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
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
		
	new AddNewBankAccount();
	}
	private void loadTable()
	{
		String htmstart = "<html><font face=\"kiran\" size=\"6\">";
		model.setRowCount(0);
		try {
			
		ResultSet rs = CommonMethods.selectQuery("SELECT id,BankName,AccountNo,AccountType,IFC,PersonName,BankAddress,BankBalance,BankCode FROM bankdetails");
		while(rs.next())
		{
			model.addRow(new Object[] {rs.getInt(1),rs.getString(9),htmstart+rs.getString(2),htmstart+rs.getString(3),rs.getString(5),htmstart+rs.getString(6),rs.getFloat(8)});
		}
		rs.close();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error in Loading banks "+e.getMessage());
			return;
		}
		
	}
	void loadBankDetails(int id)
	{
		try
		{
ResultSet rs = CommonMethods.selectQuery("SELECT BankName,AccountNo,AccountType,IFC,PersonName,BankAddress,bankBalance,BankCode from BankDetails where id = "+id);
			rs.next();
				txtBankid.setText(""+id);
				txtBankname.setText(rs.getString(1));
				txtAccountno.setText(rs.getString(2));
				txtBankaddress.setText(rs.getString(6));
				txtIfccode.setText(rs.getString(4));
				txtOpeningbalance.setText(""+rs.getFloat(7));
				txtPersonname.setText(rs.getString(5));
				txtBankcode.setText(rs.getString(8));
			
		}catch(Exception e)
		{
			System.out.println("Error in loadBankDetails"+e.getMessage());
			return;
		}
	}

	void validation()
	{
		try
		{
			if(txtBankname.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Enter bank Name","Empty",JOptionPane.ERROR_MESSAGE);
				txtBankname.requestFocus();
				return;
			}
			if(txtAccountno.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Enter Account no","Empty",JOptionPane.ERROR_MESSAGE);
				txtAccountno.requestFocus();
				return;
			}
			if(txtBankcode.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Enter Code or Name Shortcut","Empty",JOptionPane.ERROR_MESSAGE);
				txtBankcode.requestFocus();
				return;
			}
			if(txtIfccode.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Enter Bank IFCCODE","Empty",JOptionPane.ERROR_MESSAGE);
				txtIfccode.requestFocus();
				return;
			}
			if(txtPersonname.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Enter Bank Holder name","Empty",JOptionPane.ERROR_MESSAGE);
				txtPersonname.requestFocus();
				return;
			}
			if(txtBankaddress.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Enter Address","Empty",JOptionPane.ERROR_MESSAGE);
				txtBankaddress.requestFocus();
				return;
			}			
			if(txtOpeningbalance.getText().isEmpty() || Float.parseFloat(txtOpeningbalance.getText())==0)
			{
				JOptionPane.showMessageDialog(this,"Enter Valid Amount in Opening Balance","Empty",JOptionPane.ERROR_MESSAGE);
				txtOpeningbalance.requestFocus();
				return;
			}
		
		}catch (Exception e) 
		{
		System.out.println("Error in validation "+e.getMessage());
		return;
		}
	}
	void save()
	{
		try 
	       {
			
			if(CommonLogic.checkAccountNo(txtAccountno.getText(),txtIfccode.getText())==1)
			{
				
				JOptionPane.showMessageDialog(this,"Bank Account Already Exist, Details Will Be Update!","Exist",JOptionPane.ERROR_MESSAGE);
				update(Integer.parseInt(""+txtBankid.getText()));
				return;
			}
			if(CommonLogic.checkAccountNo(txtAccountno.getText(),txtIfccode.getText())==0)
			{		
			int id = CommonMethods.getId("select Id from bankdetails order by ID");
int i = CommonMethods.addRecord("insert into BankDetails(id, BankName,AccountNo,AccountType, IFC,PersonName,BankAddress,BankBalance,BankCode) values("+id
		+",'"+txtBankname.getText()+"','"+txtAccountno.getText()+"','"+cmbType.getSelectedItem()+"','"
		+txtIfccode.getText()+"','"+txtPersonname.getText()+"','"+txtBankaddress.getText()+"',"+Float.parseFloat(txtOpeningbalance.getText())+",'"+txtBankcode.getText()+"')");
			
			if(i==1)
			{
				JOptionPane.showMessageDialog(this,"Record Save Success","Success",JOptionPane.ERROR_MESSAGE);
				loadTable();
				clear();
				return;
			}
			}
		
		} catch (Exception e)
		{
		JOptionPane.showMessageDialog(this, "Error in Saving Bank Details"+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		return;
		}
	}
	void update(int id)
	{
		try {
			int flag = CommonMethods.updateRecord("update BankDetails set "
					+ "BankName='"+txtBankname.getText()+
					"',AccountNo='"+txtAccountno.getText()+"',"
					+"AccountType='"+cmbType.getSelectedItem()+"',"
					+"IFC='"+txtIfccode.getText()+"',"
					+"PersonName='"+txtPersonname.getText()+"',"
					+"BankAddress='"+txtBankaddress.getText()+"',"
					+"bankBalance="+Float.parseFloat(""+txtOpeningbalance.getText())
					+",BankCode='"+txtBankcode.getText()+"' where ID="+id);
			if(flag==1)
			{
				JOptionPane.showMessageDialog(this, "Bank Record Update Success","Update",JOptionPane.ERROR_MESSAGE);
				loadTable();
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void clear() 
	{
		txtBankid.setText(""+CommonMethods.getId("select Id from BankDetails order by Id"));
		txtAccountno.setText("");
		txtBankcode.setText("");
		txtBankaddress.setText("");
		txtBankname.setText("");
		txtIfccode.setText("");
		txtOpeningbalance.setText(""+0.0f);
		txtPersonname.setText("");
		table.clearSelection();
	}
}

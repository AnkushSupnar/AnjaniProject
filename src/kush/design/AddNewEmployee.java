package kush.design;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ankush.CommonMethods;

import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

public class AddNewEmployee extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Font font = CommonLogic.font;
	private JTextField txtId;
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtSalary;
	private JTable table;
	private DefaultTableModel model;
	JComboBox<?> cmbsalary,cmbdesignation;
	private JButton btnUpdate,btnSave;
	private Font font = new Font("Kiran", Font.BOLD, 20);
	private Font lblFont = new Font("Kiran", Font.PLAIN, 20); 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public 	AddNewEmployee()
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0)
			{
			
				txtId.setText(""+CommonMethods.getId("select ID from Employee order by ID"));
			}
		});
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(10, 6, 572, 374);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		//lblEmployeeId.setFont(font);
		lblEmployeeId.setBounds(43, 26, 86, 19);
		panel.add(lblEmployeeId);
		
		JLabel lblFirstName = new JLabel("paihlao naava");
		lblFirstName.setFont(lblFont);
		lblFirstName.setBounds(43, 57, 57, 20);
		panel.add(lblFirstName);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setFont(lblFont);
		txtId.setBounds(132, 22, 122, 28);
		panel.add(txtId);
		txtId.setColumns(10);
		
		txtFname = new JTextField();
		txtFname.setFont(new Font("Kiran", Font.BOLD, 20));
		txtFname.setBounds(43, 79, 122, 30);
		panel.add(txtFname);
		txtFname.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("maQalao naava");
		lblMiddleName.setFont(lblFont);
		lblMiddleName.setBounds(199, 57, 73, 16);
		panel.add(lblMiddleName);
		
		txtMname = new JTextField();
		txtMname.setFont(font);
		txtMname.setBounds(177, 79, 122, 30);
		panel.add(txtMname);
		txtMname.setColumns(10);
		
		JLabel lblLastName = new JLabel("AaDnaava");
		lblLastName.setFont(lblFont);
		lblLastName.setBounds(334, 57, 61, 16);
		panel.add(lblLastName);
		
		txtLname = new JTextField();
		txtLname.setFont(font);
		txtLname.setBounds(311, 79, 122, 30);
		panel.add(txtLname);
		txtLname.setColumns(10);
		
		JLabel lblAddress = new JLabel("pattaa");
		lblAddress.setFont(lblFont);
		lblAddress.setBounds(43, 110, 48, 16);
		panel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(font);
		txtAddress.setBounds(43, 130, 390, 30);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblContactNo = new JLabel("maao. naM");
		lblContactNo.setFont(lblFont);
		lblContactNo.setBounds(43, 160, 65, 16);
		panel.add(lblContactNo);
		
		txtContact = new JTextField();
		txtContact.setFont(font);
		txtContact.setBounds(43, 176, 390, 30);
		panel.add(txtContact);
		txtContact.setColumns(10);
		
		JLabel lblSalary = new JLabel("pagaar");
		lblSalary.setFont(lblFont);
		lblSalary.setBounds(43, 207, 36, 16);
		panel.add(lblSalary);
		
		txtSalary = new JTextField();
		txtSalary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				char c=e.getKeyChar();
				 if (!((c >= '0') && (c <= '9')||(c=='.') || (c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE)))
			        {
			          getToolkit().beep();
			          e.consume();
			        }

			}
		});
		txtSalary.setFont(font);
		txtSalary.setBounds(43, 222, 122, 30);
		panel.add(txtSalary);
		txtSalary.setColumns(10);
		
		JLabel lblSalaryType = new JLabel("pagaaracaa pa`kar");
		lblSalaryType.setFont(lblFont);
		lblSalaryType.setBounds(199, 207, 79, 20);
		panel.add(lblSalaryType);
		
		cmbsalary = new JComboBox<Object>();
		cmbsalary.setFont(new Font("SansSerif", Font.BOLD, 14));
		cmbsalary.setModel(new DefaultComboBoxModel(new String[] {"Monthly", "Daily"}));
		cmbsalary.setBounds(188, 222, 152, 30);
		panel.add(cmbsalary);
		
	    btnSave = new JButton("saovh");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				save();
			}
		});
		btnSave.setFont(new Font("Kiran", Font.BOLD, 25));
		btnSave.setBounds(41, 318, 90, 35);
		panel.add(btnSave);

		 btnUpdate = new JButton("ApaDoT");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				update();
			}
		});
		btnUpdate.setFont(new Font("Kiran", Font.BOLD, 25));
		btnUpdate.setBounds(261, 318, 90, 35);
		panel.add(btnUpdate);
	
		
		JButton btnEdit = new JButton("eiDT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println(table.getSelectedRow());
				if(table.getSelectedRow()>=0)
				{
				int row=table.getSelectedRow();
				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				//System.out.println(table.getValueAt(row, 0));
				int id = Integer.parseInt(""+table.getValueAt(row, 0));
				edit(id);
				}
			}
		});
		btnEdit.setFont(new Font("Kiran", Font.BOLD, 25));
		btnEdit.setBounds(143, 318, 90, 35);
		panel.add(btnEdit);
		
		JButton btnClear = new JButton("iklaAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				clear();
			}
		});
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(378, 318, 90, 35);
		panel.add(btnClear);
		
		
		JLabel lblDesignation = new JLabel("pad");
		lblDesignation.setFont(lblFont);
		lblDesignation.setBounds(43, 260, 16, 20);
		panel.add(lblDesignation);
		
		cmbdesignation = new JComboBox<String>();
		cmbdesignation.setModel(new DefaultComboBoxModel(new String[] {"Manager", "Waitor", "Accountant", "Administrator"}));
		cmbdesignation.setFont(new Font("SansSerif", Font.BOLD, 14));
		cmbdesignation.setBounds(43, 276, 297, 30);
		panel.add(cmbdesignation);
		
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Address");
		model.addColumn("Contact No");
		model.addColumn("Designation");
		model.addColumn("Salary");
		model.addColumn("Salary Type");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 385, 576, 245);
		getContentPane().add(scrollPane);
		
		table = new JTable(model);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowHeight(30);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
		
		
		//table.setFont(fontToUseForAllColumnsExceptFirst);
		
		//table.setFont(new Font("Kiran", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		loadTable();
		setSize(602,674);
		setLocation(400,70);
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
		new AddNewEmployee();

		// TODO Auto-generated method stub

	}
	void save()
	{
		try 
		{
			if(txtFname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter First Name","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtMname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Middle Name","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtLname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Last Name","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtAddress.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Address","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtContact.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Contact no","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}if(txtSalary.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Salary","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
String query = "insert into Employee( ID,FNAME,MNAME,LNAME,ADDRESS,CONTACT,DESIGNATION,SALARY,SALARYTYPE) values("+
txtId.getText()+",'"+txtFname.getText()+"','"+txtMname.getText()+"','"+txtLname.getText()+"','"+
		txtAddress.getText()+"','"+txtContact.getText()+"','"+cmbdesignation.getSelectedItem()+"',"+txtSalary.getText()+",'"+
cmbsalary.getSelectedItem()+"')";

int flag = CommonMethods.addRecord(query);
System.out.println("flag "+flag);
if(flag==1)
{
	JOptionPane.showMessageDialog(this,"record Saved","Success",JOptionPane.INFORMATION_MESSAGE);
	loadTable();
	clear();
	return;
}

		} catch (Exception e) 
		{
			System.out.println("Error in Saving "+e.getMessage());
			return;
		}
	}
	void clear()
	{
		txtFname.setText("");
		txtMname.setText("");
		txtLname.setText("");
		txtContact.setText("");
		txtAddress.setText("");
		txtSalary.setText(""+0.0f);
		txtId.setText(""+CommonMethods.getId("select ID from Employee order by ID"));
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		
	}
	void loadTable()
	{
		String htmstart = "<html><font face=\"kiran\" size=\"6\">";
		
		model.setRowCount(0);
		try 
		{
			ResultSet rs = CommonMethods.selectQuery("select ID,FNAME,MNAME,LNAME,ADDRESS,CONTACT,DESIGNATION,SALARY,SALARYTYPE from Employee order by ID");
			while(rs.next())
			{
model.addRow(new Object[] {rs.getInt(1),htmstart+(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)),htmstart+rs.getString(5),htmstart+rs.getString(6),rs.getString(7),htmstart+rs.getFloat(8),rs.getString(9)});
				
			}
			rs.close();
		} catch (Exception e) 
		{
			System.out.println("Error in Loading Talbe "+e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	void edit(int id)
	{
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select ID,FNAME,MNAME,LNAME,ADDRESS,CONTACT,DESIGNATION,SALARY,SALARYTYPE from Employee where id="+id+" order by ID");
			rs.next();
			
				txtId.setText(""+rs.getInt(1));
				txtFname.setText(rs.getString(2));
				txtMname.setText(rs.getString(3));
				txtLname.setText(rs.getString(4));
				txtAddress.setText(rs.getString(5));
				txtContact.setText(rs.getString(6));
				cmbdesignation.setSelectedItem(rs.getString(7));
				txtSalary.setText(""+rs.getFloat(8));
				cmbsalary.setSelectedItem(rs.getString(9));
				
			
			rs.close();
		}catch (Exception e)
		{
			System.out.println("Error in Editing "+e.getMessage());
			return;
		}
	}
	void update()
	{
		try 
		{
			if(txtFname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter First Name","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtMname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Middle Name","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtLname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Last Name","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtAddress.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Address","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtContact.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Contact no","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}if(txtSalary.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Salary","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id = Integer.parseInt(""+txtId.getText());
String query="update employee set FNAME='"+txtFname.getText()+"',"+
			"MNAME='"+txtMname.getText()+"',"+
			"LNAME='"+txtLname.getText()+"',"+
			"ADDRESS='"+txtAddress.getText()+"',"+
			"CONTACT='"+txtContact.getText()+"',"+
			"DESIGNATION='"+cmbdesignation.getSelectedItem()+"',"+
			"SALARY="+Float.parseFloat(txtSalary.getText())+","+
			"SALARYTYPE='"+cmbsalary.getSelectedItem()+"' where ID="+id;
int flag = CommonMethods.updateRecord(query);
System.out.println("flag "+flag);
if(flag==1)
{
	JOptionPane.showMessageDialog(this,"record Saved","Success",JOptionPane.INFORMATION_MESSAGE);
	loadTable();
	clear();
	return;
}

		} catch (Exception e) 
		{
			System.out.println("Error in Saving "+e.getMessage());
			return;
		}

	}
}

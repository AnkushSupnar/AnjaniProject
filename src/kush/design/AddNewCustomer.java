package kush.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ankush.CommonMethods;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class AddNewCustomer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtRoomno,txtStreetname,txtCity,txtTaluka,txtDistrict;
	private JTextField txtMobileno,txtEmailid;
	private JTextField txtFirstname;
	private JTextField txtMiddlename;
	private JTextField txtLastname;
	private JTextField txtId;
	private JTextField txtKey;
	private Font font = new Font("Kiran", Font.PLAIN, 20);
	private Font bfont = new Font("Kiran", Font.BOLD, 25);
	private JTable table;
	private DefaultTableModel model;
	public AddNewCustomer() 
	{
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setTitle("Add New Customer");
		setSize(914,650);
		setLocation(230,75);
		setBackground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		
		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				save();
			}
		});
		btnSave.setFont(bfont);
		btnSave.setBounds(32, 516, 90, 44);
		getContentPane().add(btnSave);
		
		JButton btnClear = new JButton("iklaAr");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				clear();
			}
		});
		btnClear.setFont(bfont);
		btnClear.setBounds(174, 517, 90, 43);
		getContentPane().add(btnClear);
		
		JButton btnHome = new JButton("haoma");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnHome.setFont(bfont);
		btnHome.setBounds(315, 517, 90, 43);
		getContentPane().add(btnHome);
		
		JButton btnExit = new JButton("baahor");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		btnExit.setFont(bfont);
		btnExit.setBounds(459, 517, 90, 43);
		getContentPane().add(btnExit);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(32, 17, 540, 182);
		getContentPane().add(panel3);
		panel3.setBorder(new TitledBorder(null, "Enter Contact Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel3.setLayout(null);
		
		JLabel lblMobileNo = new JLabel("maaobaa[la k`.");
		lblMobileNo.setFont(new Font("Kiran", Font.BOLD, 25));
		lblMobileNo.setBounds(32, 103, 89, 24);
		panel3.add(lblMobileNo);
		
		txtMobileno = new JTextField();
		txtMobileno.setFont(font);
		txtMobileno.setBounds(166, 97, 122, 28);
		panel3.add(txtMobileno);
		txtMobileno.setColumns(10);
		
		JLabel lblEmail = new JLabel("[maola");
		lblEmail.setFont(new Font("Kiran", Font.BOLD, 25));
		lblEmail.setBounds(32, 141, 40, 24);
		panel3.add(lblEmail);
		
		txtEmailid = new JTextField();
		txtEmailid.setForeground(Color.BLUE);
		txtEmailid.setFont(new Font("SansSerif", Font.ITALIC, 14));
		txtEmailid.setBounds(165, 136, 233, 28);
		panel3.add(txtEmailid);
		txtEmailid.setColumns(10);
		
		JLabel lblFullName = new JLabel("saMpauNa- naava");
		lblFullName.setFont(new Font("Kiran", Font.BOLD, 25));
		lblFullName.setBounds(32, 66, 81, 24);
		panel3.add(lblFullName);
		
		txtFirstname = new JTextField();
		lblFullName.setLabelFor(txtFirstname);
		txtFirstname.setToolTipText("First Name");
		txtFirstname.setFont(font);
		txtFirstname.setBounds(166, 60, 122, 28);
		panel3.add(txtFirstname);
		txtFirstname.setColumns(10);
		
		txtMiddlename = new JTextField();
		txtMiddlename.setToolTipText("Middle Name");
		txtMiddlename.setFont(font);
		txtMiddlename.setBounds(288, 61, 122, 28);
		panel3.add(txtMiddlename);
		txtMiddlename.setColumns(10);
		
		txtLastname = new JTextField();
		txtLastname.setToolTipText("Last Name");
		txtLastname.setFont(font);
		txtLastname.setBounds(410, 61, 122, 28);
		panel3.add(txtLastname);
		txtLastname.setColumns(10);
		
		JLabel lblCustomerId = new JLabel("ga`ahk k`.");
		lblCustomerId.setFont(new Font("Kiran", Font.BOLD, 25));
		lblCustomerId.setBounds(32, 22, 68, 24);
		panel3.add(lblCustomerId);
		
		txtId = new JTextField();
		lblCustomerId.setLabelFor(txtId);
		txtId.setEditable(false);
		txtId.setFont(new Font("Kiran", Font.BOLD, 25));
		txtId.setBounds(166, 20, 122, 28);
		panel3.add(txtId);
		txtId.setColumns(10);
		txtId.setText(""+ankush.CommonMethods.getId("select Id from customer"));
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblKey.setBounds(300, 27, 55, 16);
		panel3.add(lblKey);
		
		txtKey = new JTextField();
		txtKey.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtKey.setBounds(334, 21, 122, 28);
		panel3.add(txtKey);
		txtKey.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 210, 540, 246);
		getContentPane().add(panel);
		panel.setBorder(new TitledBorder(null, "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel.setLayout(null);
		
		
		JLabel lblRoomflatNo = new JLabel("Gar k`. r]ma naM.");
		lblRoomflatNo.setFont(new Font("Kiran", Font.BOLD, 22));
		lblRoomflatNo.setBounds(17, 32, 111, 24);
		panel.add(lblRoomflatNo);
		
		txtRoomno = new JTextField();
		txtRoomno.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtRoomno.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtRoomno.setFont(font);
		txtRoomno.setBounds(113, 27, 122, 28);
		panel.add(txtRoomno);
		txtRoomno.setColumns(10);
		
		JLabel lblStreetName = new JLabel("rstaa");
		lblStreetName.setFont(new Font("Kiran", Font.BOLD, 25));
		lblStreetName.setBounds(17, 69, 95, 16);
		panel.add(lblStreetName);
		
		txtStreetname = new JTextField();
		txtStreetname.setFont(font);
		txtStreetname.setBounds(113, 64, 277, 28);
		panel.add(txtStreetname);
		txtStreetname.setColumns(10);
		
		JLabel lblCitytown = new JLabel("gaava Sahr");
		lblCitytown.setFont(new Font("Kiran", Font.BOLD, 25));
		lblCitytown.setBounds(17, 104, 95, 16);
		panel.add(lblCitytown);
		
		txtCity = new JTextField();
		txtCity.setFont(font);
		txtCity.setBounds(113, 99, 277, 28);
		panel.add(txtCity);
		txtCity.setColumns(10);
		
		JLabel lblTaluka = new JLabel("taalauka");
		lblTaluka.setFont(new Font("Kiran", Font.BOLD, 25));
		lblTaluka.setBounds(17, 144, 53, 24);
		panel.add(lblTaluka);
		
		txtTaluka = new JTextField();
		txtTaluka.setFont(font);
		txtTaluka.setBounds(113, 139, 194, 28);
		panel.add(txtTaluka);
		txtTaluka.setColumns(10);
		
		JLabel lblDistrict = new JLabel("ijalha");
		lblDistrict.setFont(new Font("Kiran", Font.BOLD, 25));
		lblDistrict.setBounds(17, 189, 49, 24);
		panel.add(lblDistrict);
		
		txtDistrict = new JTextField();
		txtDistrict.setFont(font);
		txtDistrict.setBounds(113, 179, 194, 28);
		panel.add(txtDistrict);
		txtDistrict.setColumns(10);
		
		model = new DefaultTableModel();
		model.addColumn("Sr No.");
		model.addColumn("Customer Name");
		
		table = new JTable(model);
		table.setShowHorizontalLines(true);
		table.setFont(font);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int id =Integer.parseInt(""+ model.getValueAt(table.getSelectedRow(),0));
				LoadCustomerInForm(id);
				
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.inactiveCaption);
		scrollPane.setViewportView(table);
		scrollPane.setBorder(new TitledBorder(null, "Customer Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane.setBounds(584, 17, 308, 439);
		getContentPane().add(scrollPane);
		loadCustomer();
		
		// TODO Auto-generated constructor stub
	     setUndecorated(true);
		setVisible(true);
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
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
		
		new AddNewCustomer();

	}
	
	public void save()
	{
		try
		{
			int id = Integer.parseInt(txtId.getText());
			int newId = CommonMethods.getId("select id from Customer order by id");
			if(newId==id)
			{
				if(checkFields()==1)
				{
					int flag=CommonMethods.addRecord("insert into Customer(ID,CustomerKey,fname,mname,lname,Mobileno,Emailid,Flatno,Streetname,City,Taluka,District) values("+id+",'"+txtKey.getText().trim()+"','"+txtFirstname.getText().trim()+"','"+txtMiddlename.getText().trim()+"','"+txtLastname.getText()+"','"+txtMobileno.getText().trim()+"','"+
							txtEmailid.getText()+"',"+txtRoomno.getText()+",'"+txtStreetname.getText()+"','"+txtCity.getText()+"','"+txtTaluka.getText()+"','"+txtDistrict.getText()+"')");
							if(flag==1)
							{
								JOptionPane.showMessageDialog(this, "Record Saved");
								txtId.setText(""+ankush.CommonMethods.getId("select ID from Customer"));
								clear();
							}
							else
							{
								JOptionPane.showMessageDialog(this,"Error","Wrong Information", JOptionPane.ERROR_MESSAGE);
								return;
							}
				}
	
				
			}
			else
			{
			
				
				if(checkFields()==1)
				{
int f = CommonMethods.updateRecord("update Customer set CustomerKey='"+txtKey.getText()+"',Fname='"+txtFirstname.getText()+"',Mname='"+txtMiddlename.getText()+"',LName='"+txtLastname.getText()+"',MobileNo='"+txtMobileno.getText()+"',EmailId='"+txtEmailid.getText()+"',FlatNo="+Integer.parseInt(txtRoomno.getText())+",StreetName='"+txtStreetname.getText()+"',City='"+txtCity.getText()+"',District='"+txtDistrict.getText()+"',Taluka='"+txtTaluka.getText()+"' where ID="+Integer.parseInt(txtId.getText()));
				if(f==1)
				{
					JOptionPane.showMessageDialog(this,"Old Customer Record Update","Old",JOptionPane.ERROR_MESSAGE);
					txtId.setText(""+ankush.CommonMethods.getId("select ID from Customer"));
					clear();
					return;
				}
				}
				//return;
				
			}
			
			
		}catch(Exception e)
		{
			System.out.println("Error in save "+e.getMessage());
			return;
		}
					   
		//System.out.println(id+"\n"+name+"\n"+key+"\n"+local+"\n"+permanent+"\n"+mobile+"\n"+pmobile+"\n"+email);
//Common.ConnectionDB.addRecord("insert into customer values("+id+",'"+key+"','"+name+"','"+local+"','"+"','"+permanent+"','"+mobile+"','"+pmobile+"','"+email+"')");
//System.out.println("insert into customer(ID,KEY,NAME,LOCALADDRESS,PERMANENTADDRESS,MOBILENO,PMOBILENO,EMAIL) values("+id+",'"+key+"','"+name+"','"+local+"','"+permanent+"','"+mobile+"','"+pmobile+"','"+email+"')");
		
	
	 
	}
	int checkFields()
	{
		try
		{
			
			if(txtFirstname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter First Name ","Empty", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

			if(txtMiddlename.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Middle Name","Empty", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

			if(txtLastname.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter Last Name ","Empty", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			if(txtRoomno.getText().equals(""))
			{
				txtRoomno.setText("1");
			
			}
			if(txtStreetname.getText().equals(""))
			{
				txtStreetname.setText(" ");
			}
			if(txtCity.getText().equals(""))
			{
				txtCity.setText(" ");
			}
			if(txtTaluka.getText().equals(""))
			{
				txtTaluka.setText(" ");
			}
			if(txtDistrict.getText().equals(""))
			{
				txtDistrict.setText(" ");
			}
			if(txtKey.getText().equals(""))
			{
				txtKey.setText(" ");
			}
				
			

		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error", "Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}

		
		return 1;
	}
	void clear()
	{
		 txtRoomno.setText("");
		 txtStreetname.setText("");
		 txtCity.setText("");
		 txtTaluka.setText("");
		 txtDistrict.setText("");
		
		  txtMobileno.setText("");
		 
		  txtEmailid.setText("");
		  txtFirstname.setText("");
		  txtMiddlename.setText("");
		  txtLastname.setText("");
		 txtId.setText(""+ankush.CommonMethods.getId("select ID from Customer"));
		  txtKey.setText("");
		 // model.removeRow(model.getRowCount()-1);
		 // model.setRowCount(0);
		 loadCustomer();
		  
		 
	}
	public void loadCustomer()
	{
		table.removeAll();
		model.setRowCount(0);
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select Id,fname,mname,lname from Customer");
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getInt(1),rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)});
			}
			rs.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"No Customer to Show","No Record Found",JOptionPane.ERROR_MESSAGE);
			System.out.println(""+e.getMessage());
			return;
		}
	}
	public void LoadCustomerInForm(int id)
	{
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select Customerkey,fname,mname,lname,mobileno,emailid,flatno,streetname,city, district,taluka from Customer where ID="+id);
			rs.next();
			
				txtId.setText(""+id);
				txtKey.setText(rs.getString(1));
				txtFirstname.setText(rs.getString(2));
				txtMiddlename.setText(rs.getString(3));
				txtLastname.setText(rs.getString(4));
				txtMobileno.setText(rs.getString(5));
				txtEmailid.setText(rs.getString(6));
				txtRoomno.setText(""+rs.getInt(7));
				txtStreetname.setText(rs.getString(8));
				txtCity.setText(rs.getString(9));
				txtDistrict.setText(rs.getString(10));
				txtTaluka.setText(rs.getString(11));
			
			rs.close();
		}catch(Exception e)
		{
			System.out.println("error in LoadCustomerInForm "+e.getMessage());
			return;
		}
	}
}

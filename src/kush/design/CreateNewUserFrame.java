package kush.design;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ankush.CommonLogic;
import ankush.CommonMethods;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateNewUserFrame extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4821590412708648549L;
	private JTextField txtUsername;
	private JPasswordField pwdNewpassword;
	private JPasswordField pwdReenterpassword;
	private JComboBox<String> cmbEmployee;
	private JButton btnCreate;
	public CreateNewUserFrame()
	{
		getContentPane().setBackground(new Color(230, 230, 250));
	
		setLocation(500,100);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("kamagaar inavaDa");
		lblSelectEmployee.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblSelectEmployee.setBounds(100, 38, 81, 20);
		getContentPane().add(lblSelectEmployee);
		
	    cmbEmployee = new JComboBox<>();
	    cmbEmployee.setFont(new Font("Kiran", Font.PLAIN, 25));
	    addEmployee();
		cmbEmployee.setBounds(100, 56, 303, 30);
		getContentPane().add(cmbEmployee);
		
		JLabel lblEnterUserName = new JLabel("yaujar naoma ");
		lblEnterUserName.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblEnterUserName.setBounds(100, 99, 55, 20);
		getContentPane().add(lblEnterUserName);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Kiran", Font.PLAIN, 23));
		txtUsername.setBounds(100, 118, 303, 30);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("naivana paasavaD-");
		lblNewPassword.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblNewPassword.setBounds(106, 164, 75, 20);
		getContentPane().add(lblNewPassword);
		
		pwdNewpassword = new JPasswordField();
		pwdNewpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					pwdReenterpassword.requestFocus();
				}
				 if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)))
				 {
				        getToolkit().beep();
				        ke.consume();
				 }
			}
		});
		pwdNewpassword.setBounds(100, 186, 303, 30);
		getContentPane().add(pwdNewpassword);
		
		JLabel lblReenterPassword = new JLabel("paunha paasavaD- Taka");
		lblReenterPassword.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblReenterPassword.setBounds(100, 237, 100, 20);
		getContentPane().add(lblReenterPassword);
		
		pwdReenterpassword = new JPasswordField();
		pwdReenterpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) 
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					btnCreate.requestFocus();
				}
				 if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)))
				 {
				        getToolkit().beep();
				        ke.consume();
				 }
			}
		});
		pwdReenterpassword.setBounds(100, 258, 303, 30);
		getContentPane().add(pwdReenterpassword);
		
		btnCreate = new JButton("ik`eT ");
		btnCreate.setFont(new Font("Kiran", Font.BOLD, 25));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				create();
			}
		});
		btnCreate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) 
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					btnCreate.doClick();
				}
			}
		});
		btnCreate.setBounds(102, 322, 90, 28);
		getContentPane().add(btnCreate);
		
		JButton btnClear = new JButton("iklaAr");
		btnClear.setFont(new Font("Kiran", Font.BOLD, 25));
		btnClear.setBounds(207, 322, 90, 28);
		getContentPane().add(btnClear);
		
		JButton btnCancel = new JButton("baahor");
		btnCancel.setFont(new Font("Kiran", Font.BOLD, 25));
		btnCancel.setBounds(326, 322, 90, 28);
		getContentPane().add(btnCancel);
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
	 new CreateNewUserFrame();

	}
	void addEmployee()
	{
	
		try
		{
			java.util.List<String> emp = CommonLogic.getAllEmployee();
			Iterator<String> i = emp.iterator();
			while(i.hasNext())
			{
				cmbEmployee.addItem(i.next());
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error in finding Employee Names","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	@SuppressWarnings("deprecation")
	void create()
	{
		try
		{
			pwdReenterpassword.setBackground(Color.WHITE);
			String UserName= txtUsername.getText();
			String Password =pwdNewpassword.getText();
			String RPassword = pwdReenterpassword.getText();
			if(txtUsername.getText().equals("")||UserName.equals(null))
			{
				JOptionPane.showMessageDialog(this, "Enter User Name", "Empty", JOptionPane.ERROR_MESSAGE);
				txtUsername.requestFocus();
				return;
			}
			if(Password.equals("")||Password.equals(null))
			{
				JOptionPane.showMessageDialog(this, "Enter Password", "Empty", JOptionPane.ERROR_MESSAGE);
				pwdNewpassword.requestFocus();
				return;
			}
			if(Password.equals("")||RPassword.equals(null))
			{
				JOptionPane.showMessageDialog(this, "Enter Password", "Empty", JOptionPane.ERROR_MESSAGE);
				pwdReenterpassword.requestFocus();
				return;
			}
			if(!Password.equals(RPassword))
			{
				JOptionPane.showMessageDialog(this, "New Password Must Be Same", "Empty", JOptionPane.ERROR_MESSAGE);
				pwdReenterpassword.setBackground(Color.RED);
				pwdReenterpassword.requestFocus();
				return;
			}
			if(checkUserName(UserName)==1)
			{
				JOptionPane.showMessageDialog(this, "Username Already Available", "Duplicate", JOptionPane.ERROR_MESSAGE);
				txtUsername.setBackground(Color.RED);
				txtUsername.requestFocus();
				return;
			}
			//System.out.println("Fname "+getFirstName(""+cmbEmployee.getSelectedItem()));
			String fname = ""+getFirstName(""+cmbEmployee.getSelectedItem(),0);
			String mname = ""+getFirstName(""+cmbEmployee.getSelectedItem(), 1);
			String lname = ""+getFirstName(""+cmbEmployee.getSelectedItem(), 2);
			
			//System.out.println(CommonLogic.getWaitorid(fname));
			// save data in login user
			int flag = CommonMethods.addRecord("Insert into Login(ID,UserName,Password,Employeeid)values("+CommonMethods.getId("select ID from Login order by ID")
			+",'"+UserName+"','"+Password+"',"+CommonLogic.getEmpidFullName(fname,mname,lname)+")");
			
			if(flag==1)
			{
				JOptionPane.showMessageDialog(this,"Record Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}catch(Exception e)
		{
		JOptionPane.showMessageDialog(this, "Error "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	String getFirstName(String name,int flag)
	{
		String fname="";
		int f=0;
		try
		{
			
			for(int i=0;i<name.length();i++)
			{
				if(name.charAt(i)==' ')
				{
					f++;
					
				}
				if(f==flag)
				{
					if(name.charAt(i)!=' ')
					{
					fname = fname + name.charAt(i);
					}
				}
			}
			
			return fname;
		}catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
			return "";
		}
	
	}

	int checkUserName(String name)
	{
		int flag=0;
		try
		{
			ResultSet rs = CommonMethods.selectQuery("select UserName from Login");
			while(rs.next())
			{
				if(rs.getString(1).equals(name))
				{
					flag=1;
				}
				
			}
			rs.close();
			return flag;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}
}

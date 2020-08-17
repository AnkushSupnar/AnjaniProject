package kush.design;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jidesoft.swing.AutoCompletionComboBox;

import ankush.CommonMethods;

public class AddNewTableFrame extends JFrame  
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AutoCompletionComboBox cmbTable;
	AutoCompletionComboBox cmbCategory;
	private JTextField txtNewname;
	@SuppressWarnings("unchecked")
	public AddNewTableFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.activeCaption);
		
		// TODO Auto-generated constructor stub
		setSize(500,341);
		setLocation(400,100);
		getContentPane().setLayout(null);
		
		JLabel lblAvailableTables = new JLabel("sava- Tobala");
		lblAvailableTables.setFont(new Font("Kiran", Font.BOLD, 25));
		lblAvailableTables.setBounds(129, 50, 75, 24);
		getContentPane().add(lblAvailableTables);
		
		cmbTable = new AutoCompletionComboBox();
		cmbTable.setFont(new Font("SansSerif", Font.BOLD, 14));
		cmbTable.setBounds(214, 48, 178, 30);
		getContentPane().add(cmbTable);
		
		JLabel lblNewTableName = new JLabel("naivana Tobala naava");
		lblNewTableName.setFont(new Font("Kiran", Font.BOLD, 25));
		lblNewTableName.setBounds(77, 156, 129, 24);
		getContentPane().add(lblNewTableName);
		
		txtNewname = new JTextField();
		txtNewname.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtNewname.setBounds(216, 154, 176, 30);
		getContentPane().add(txtNewname);
		txtNewname.setColumns(10);
		
		JLabel lblSelectCategory = new JLabel("saokSana inavaDa");
		lblSelectCategory.setFont(new Font("Kiran", Font.BOLD, 25));
		lblSelectCategory.setBounds(94, 105, 113, 24);
		getContentPane().add(lblSelectCategory);
		
		cmbCategory = new AutoCompletionComboBox();
		cmbCategory.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		cmbCategory.addItem("A");
		cmbCategory.addItem("B");
		cmbCategory.addItem("C");
		cmbCategory.addItem("D");
		cmbCategory.addItem("E");
		cmbCategory.addItem("G");
		cmbCategory.addItem("V");
		cmbCategory.addItem("P");
		cmbCategory.addItem("HP");

		cmbCategory.setSelectedItem("");
		cmbCategory.setBounds(216, 102, 178, 30);
		
		getContentPane().add(cmbCategory);
		
		JButton btnAdd = new JButton("A^D");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				addTable();
			}
		});
		btnAdd.setFont(new Font("Kiran", Font.BOLD, 25));
		btnAdd.setBounds(94, 230, 90, 28);
		getContentPane().add(btnAdd);
		
		JButton btnReset = new JButton("iklaAr");
		btnReset.setFont(new Font("Kiran", Font.BOLD, 25));
		btnReset.setBounds(196, 230, 90, 28);
		getContentPane().add(btnReset);
		
		JButton btnHome = new JButton("haoma");
		btnHome.setFont(new Font("Kiran", Font.BOLD, 25));
		btnHome.setBounds(302, 231, 90, 28);
		getContentPane().add(btnHome);
		loadTables();
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
		new AddNewTableFrame();
		// TODO Auto-generated method stub

	}
	@SuppressWarnings("unchecked")
	void loadTables()
	{
		try 
		{
			ResultSet rs = CommonMethods.selectQuery("select TableName From TableMaster order by id");
			while(rs.next())
			{
				cmbTable.addItem(rs.getString(1));
			}
			rs.close();
			
			
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "No Tables Available "+e);
			return;
		}
	}

	void addTable()
	{
		try 
		{
			String name = txtNewname.getText();
			String category = (String) cmbCategory.getSelectedItem();
			int id = CommonMethods.getId("select Id from TableMaster");
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(this,"Please Enter TableName");
			return;
		}
		if(category.equals(""))
		{
			JOptionPane.showMessageDialog(this,"please Select category");
			return;
			
		}
		//Check whether is already exist or not
		int f=0;
		ResultSet rs = CommonMethods.selectQuery("select TableName from TableMaster where DESCRIPTION='"+category+"'");
		while(rs.next())
		{
			if(rs.getString(1).equals(name))
			{
				f=1;
			}
		}
		rs.close();
		if(f==1)
		{
			JOptionPane.showMessageDialog(this, "Table Already Exist in The category");
			return;
		}
				
		
		
		
		
			int flag = CommonMethods.addRecord("insert into TableMaster values("+id+",'"+name+"','"+category+"')");
			if(flag==1)
			{
				JOptionPane.showMessageDialog(this, "Table Save Success");
				cmbTable.removeAllItems();
				loadTables();
				reset();
				return;
			}
			System.out.println("Return flag "+flag);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error in Adding Table "+e);
			return;
			// TODO: handle exception
		}
	}
	void reset()
	{
		txtNewname.setText("");
		//cmbCategory.setSelectedItem("");
		cmbTable.setSelectedItem("");
	}
}

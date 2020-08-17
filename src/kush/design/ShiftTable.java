package kush.design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ankush.AutoCompletion;
import ankush.CommonLogic;
import ankush.CommonMethods;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShiftTable extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox<Object> cmbname;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		CommonMethods.openConnection();
		try {
			ShiftTable dialog = new ShiftTable("A1");
		//	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShiftTable(String name) 
	{
		
		setBounds(100, 100, 344, 198);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(135, 206, 235));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 334, 126);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Current Table Name");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(12, 15, 139, 17);
		panel.add(label);
		
		textField = new JTextField(""+name);
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setColumns(10);
		textField.setBounds(155, 13, 116, 30);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("New Table Name");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(12, 72, 126, 17);
		panel.add(label_1);
		
		cmbname = new JComboBox<Object>();
		cmbname.setBounds(155, 62, 116, 30);
		AutoCompletion.enable(cmbname);
		addTable();
		panel.add(cmbname);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						shift(""+cmbname.getSelectedItem());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	void addTable()
	{
		List<Object> table = CommonLogic.getAllTable();
		List<String> Bill = CommonLogic.getRunningTableFromBill();
		List<String> temp = CommonLogic.getRunningTable();
		for(Object name:table)
		{
			if(!Bill.contains(name) && !temp.contains(name))
			{
			
			cmbname.addItem(name);
			}
		}
	}
	public int shift(String table)
	{
		try 
		{
			System.out.println("Get for Shifting "+table);
			int oldtable = CommonLogic.getTableId(""+textField.getText());
			int newtable = CommonLogic.getTableId(table);
			//update in Bill
			int f1=CommonMethods.updateRecord("update Bill set Tableno="+newtable+" where tableno="+oldtable+" and Status='no'");
			if(f1==1)
			{
				System.out.println("I found in bill and update"+f1);
				
				return 1;
			}
			int f2 = CommonMethods.updateRecord("update TempTransaction set TableNo="+newtable+" where Tableno="+oldtable);
			if(f2==1)
			{
				System.out.println("I found in TempTransaction and update"+f1);
				return 1;
			}
		} catch (Exception e)
		{
			
			JOptionPane.showMessageDialog(this,"Error in Shifting Table","Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		return 0;
	}
}

package kush.design;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ankush.CommonLogic;
import ankush.CommonMethods;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.util.Iterator;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JScrollPane;

public class Demo extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cmbDemo;
	DefaultListModel<String> model;
	private JTextField txtName;
	private JList<String> list;
	private List<String> itemlist;
	private DefaultListModel<String> listModel;
	private JScrollPane scrollPane;
	Demo()
	{
		setSize(500,500);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.scrollbar);
		panel_1.setBounds(32, 11, 388, 108);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		cmbDemo = new JComboBox<Object>();
		cmbDemo.setBounds(309, 166, 151, 43);
		getContentPane().add(cmbDemo);
		cmbDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			}
		});
		cmbDemo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{
				System.out.println("State change"+ cmbDemo.getSelectedItem());
			}
		});
		
		
		cmbDemo.setEditable(true);
		
		cmbDemo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ankush", "Ashutosh", "BBC", "CCdd", "ss", "dd", "rr", "serg", "bdg", ""}));
		loadItemInList();
		txtName = new JTextField();
		txtName.setFont(new Font("Kiran", Font.BOLD, 25));
		
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) 
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_DOWN)
				{
					System.out.println("Down Pressed");
					findItem(txtName.getText());
					if(!itemlist.isEmpty())
					{
						list.requestFocus();
						list.setSelectedIndex(1);
						return;
					}
				}
				
				if(c==KeyEvent.VK_ENTER)
				{
					if(!itemlist.isEmpty())
					{
						list.requestFocus();
						list.setSelectedIndex(0);
						return;
					}
					
				}
			findItem(txtName.getText());
				System.out.println("Typed "+c);
				scrollPane.setVisible(true);
			}
		});
		
		txtName.setBounds(32, 160, 245, 50);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		listModel = new DefaultListModel<>();
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(32, 209, 245, 227);
		getContentPane().add(scrollPane);
		list = new JList<String>(listModel);
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				char c = e.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					if(list.getSelectedIndex()!=-1)
					{
					txtName.setText(list.getSelectedValue());
					scrollPane.setVisible(false);
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		list.setFont(new Font("Kiran", Font.BOLD, 25));
		//list.setVisible(false);
	
			
		
		model  = new DefaultListModel<>();
		
		setVisible(true);
	}
	public static void main(String[] args)
	{
		CommonMethods.openConnection();
		new Demo();
		// TODO Auto-generated method stub

		@SuppressWarnings("deprecation")
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getDate());
		
		System.out.println("current date1: "+date);
		/*CommonMethods.openConnection();
		int n=1;
		for(int i=39;i<=42;i++)
		{
			CommonMethods.updateRecord("update TableMaster set TableName='G"+n+"' where Description='G' and ID="+i);
			n++;
		}
		CommonMethods.updateRecord("");*/
	}
	void loadCategory()
	{
		try
		{
		List<String> list = CommonLogic.getCategory();
		Iterator<String> i=list.iterator();
		while(i.hasNext())
		{
			cmbDemo.addItem(i.next());
		}
		//AutoCompletion.enable(cmbDemo);
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Error in Gettin Category");
			return;
		}
	}
	void loadItemInList()
	{
		itemlist = CommonLogic.getItemName("paMjaabakamajaa");
	}
	void findItem(String find)
	{
		listModel.removeAllElements();
		listModel.clear();
		System.out.println("i got"+find);
		try
		{
			for(int i = 0;i<itemlist.size();i++)
			{
				 if(((String) itemlist.get(i)).startsWith(find))
				    {
				       // MySortStrings.add(MyStrings.get(i));
					 listModel.addElement((String) itemlist.get(i));
				    }
			}
		}catch(Exception e)
		{
			System.out.println("Error in findItem "+e.getMessage());
			return;
		}
	}
}


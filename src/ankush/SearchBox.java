package ankush;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class SearchBox extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159736901870022781L;
	private JTextField txtBankName;
	private JList<String> jlistBankName;
	private List<String> listBankName;
	private DefaultListModel<String> modelBankName;
	private JScrollPane scrollBankName;
	private JPopupMenu popup;
	private Font font = CommonLogic.font;
	
	public SearchBox(String query)  
	{
		
		
		
		//setLocation(200,100);
		//setSize(267, 35);
		this.setLayout(null);
		listBankName = CommonLogic.getSearchName(query );
		modelBankName = new DefaultListModel<>();
		jlistBankName = new JList<>(modelBankName);
		jlistBankName.setFont(font);
		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());
		scrollBankName = new JScrollPane();
		scrollBankName.setViewportView(jlistBankName);
		
		txtBankName = new JTextField();
		txtBankName.setBounds(0, 0, 267, 35);
		txtBankName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				findItemBankName(txtBankName.getText());
				if(!listBankName.isEmpty())
				{
					popup.add(scrollBankName); // your component
			        popup.setPopupSize(txtBankName.getWidth(), 300);
			        popup.show(txtBankName, 0, txtBankName.getHeight());
			        txtBankName.requestFocus();
			        
				}
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					jlistBankName.requestFocus();
					jlistBankName.setSelectedIndex(0);
				}

			}
		});
		jlistBankName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					txtBankName.setText(jlistBankName.getSelectedValue());
			CommonLogic.getCustomerIdUsingName(txtBankName.getText());
						popup.setVisible(false);
					
				}
			}
		});
		txtBankName.setFont(new Font("Kiran", Font.PLAIN, 20));
		add(txtBankName);
		txtBankName.setColumns(10);
		
		new ButtonGroup();
			
		new DefaultListModel<>();
		
		
		
		popup = new JPopupMenu();
		 popup.setLayout(new BorderLayout());
		 new JScrollPane();
		
		//setSize(457,487);

		
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
		String query ="select BankName from BankDetails order by BankName";
		SearchBox b = new SearchBox(query);
		b.setSize(267, 35);
		JFrame f = new JFrame();
		f.getContentPane().setLayout(null);
		f.getContentPane().add(b);
		f.setSize(b.getSize());
		f.setVisible(true);

	}
	void findItemBankName(String find)
	{
		modelBankName.removeAllElements();
		
		try
		{
		  for(int i = 0;i<listBankName.size();i++)
			{
			  if(((String) listBankName.get(i)).startsWith(find))
				    {
				      
				  modelBankName.addElement((String) listBankName.get(i));
				    }
			}
		}catch(Exception e)
		{
			System.out.println("Error in findItem "+e.getMessage());
			return;
		}
	}
	
	
}


package Demo;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Test extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtD;
	private JTextField txtD_1;
	private JScrollPane scroll;
	JPopupMenu popup;
	private JList<String> list;
	//private JPopupMenu dpop;
	
	private DefaultListModel<String> model; 
	Test()
	{
		
		setSize(500, 500);
		getContentPane().setLayout(null);
	
		model = new DefaultListModel<>();
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		model.addElement("Ankush");
		
		scroll = new JScrollPane();
		list = new JList<>(model);
		//list.setBounds(60, 196, 209, 206);
		
		//getContentPane().add(list);
		scroll.setViewportView(list);
		
	
		 popup = new JPopupMenu();
		 popup.setLayout(new BorderLayout());
		txtD = new JTextField();
		txtD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
			        
			        popup.add(scroll); // your component
			        popup.setPopupSize(txtD.getWidth(), 100);
			        popup.show(txtD, 0, txtD.getHeight());
			        txtD.requestFocus();
			        char c = e.getKeyChar();
			        if(c==KeyEvent.VK_ENTER)
			        {
			        	list.setSelectedIndex(0);
			        	list.requestFocus();
			        }
			}
		});
		txtD.setText("d1");
		txtD.setBounds(39, 34, 207, 31);
		getContentPane().add(txtD);
		txtD.setColumns(10);
		//dpop.add(txtD);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(39, 76, 46, 14);
		getContentPane().add(lblItem);
		
		txtD_1 = new JTextField();
		txtD_1.setText("d2");
		txtD_1.setBounds(37, 103, 209, 31);
		getContentPane().add(txtD_1);
		txtD_1.setColumns(10);
		
		setVisible(true);
		
	}
	public static void main(String[] args)
	{
		new Test();
	}
}
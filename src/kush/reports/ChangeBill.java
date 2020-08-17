package kush.reports;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import ankush.CommonLogic;
import ankush.CommonMethods;
import kush.print.PDFPassbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChangeBill extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8766160984108050951L;
	private JTextField txtId;
	private JTextField txtCode;
	private JTextField txtCustomername;
	private JPopupMenu popup1;
	
	private JList<String> jlist;
	private List<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private Font font = CommonLogic.font;
	private JTextArea txtrAddress;
	private JTextField txtTotalcredit;
	private JTextField txtTotaldebit;
	private JTextField txtRemaining;
	private JTable table;
	private DefaultTableModel tmodel;
	public ChangeBill()
	{
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		setSize(782, 534);
		setLocation(200,10);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(6, 6, 461, 302);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(6, 30, 90, 35);
		panel_1.add(txtId);
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e)
			{
				char ch = e.getKeyChar();
				  if (!((ch >= '0') && (ch <= '9') ||(ch=='.')||(ch=='-')|| (ch == KeyEvent.VK_BACK_SPACE)||(ch == KeyEvent.VK_ENTER) || (ch == KeyEvent.VK_DELETE)))
			        {
			         // getToolkit().beep();
			          e.consume();
			        }
				if(ch==KeyEvent.VK_ENTER)
				{
					if(txtId.getText().equals("")||txtId.getText().equals(null))
					{
						txtCode.requestFocus();
					}
					int id = Integer.parseInt(""+txtId.getText());
					txtCode.requestFocus();
					txtCustomername.setText(CommonLogic.getCustomerNameUsingId(id));
					txtrAddress.setText(CommonLogic.getCustomerAddress(id));
					txtCode.setText(CommonLogic.getCustomerKeyUsingName(txtCustomername.getText()));
					txtTotalcredit.setText(""+CommonLogic.getAllCredit(id));
					txtTotaldebit.setText(""+CommonLogic.getAllRecieved(id));
					txtRemaining.setText(""+(Float.parseFloat(txtTotalcredit.getText())-Float.parseFloat(txtTotaldebit.getText())));
				}
			}
		});
		txtId.setFont(new Font("Kiran", Font.PLAIN, 20));
		txtId.setColumns(10);
		
		txtCode = new JTextField();
		txtCode.setBounds(96, 30, 122, 35);
		panel_1.add(txtCode);
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				char c = e.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					if(txtCode.getText().equals("")||txtCode.getText().equals(null))
					{
						txtCustomername.requestFocus();
					}
					txtCustomername.requestFocus();
					txtCustomername.setText(CommonLogic.getCustomerNameUsingCode(txtCode.getText()));
					txtId.setText(""+CommonLogic.getCustomerIdUsingName(txtCustomername.getText()));
					txtrAddress.setText(CommonLogic.getCustomerAddress(Integer.parseInt(txtId.getText())));
					txtTotalcredit.setText(""+CommonLogic.getAllCredit(Integer.parseInt(txtId.getText())));
					txtTotaldebit.setText(""+CommonLogic.getAllRecieved(Integer.parseInt(txtId.getText())));
					txtRemaining.setText(""+(Float.parseFloat(txtTotalcredit.getText())-Float.parseFloat(txtTotaldebit.getText())));
				}
			}
		});
		
		txtCode.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		txtCode.setColumns(10);
		
		txtCustomername = new JTextField();
		txtCustomername.setBounds(221, 30, 225, 35);
		panel_1.add(txtCustomername);
		txtCustomername.setFont(new Font("Kiran", Font.PLAIN, 20));
		txtCustomername.setColumns(10);
		JLabel label = new JLabel("ga`ahk  k`.");
		label.setBounds(7, 6, 53, 20);
		panel_1.add(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Kiran", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("ga`ahk  kaoD");
		label_1.setBounds(106, 6, 72, 20);
		panel_1.add(label_1);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Kiran", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("ga`ahk  naava");
		label_2.setBounds(229, 6, 225, 20);
		panel_1.add(label_2);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Kiran", Font.PLAIN, 20));
		
		JLabel label_3 = new JLabel("ga`ahk  pattaa");
		label_3.setBounds(6, 68, 66, 20);
		panel_1.add(label_3);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Kiran", Font.PLAIN, 20));
		
		txtrAddress = new JTextArea();
		txtrAddress.setBounds(6, 88, 442, 155);
		panel_1.add(txtrAddress);
		txtrAddress.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtrAddress.setEditable(false);
		
		txtTotalcredit = new JTextField();
		txtTotalcredit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotalcredit.setEditable(false);
		txtTotalcredit.setColumns(10);
		txtTotalcredit.setBackground(new Color(240, 128, 128));
		txtTotalcredit.setBounds(6, 267, 84, 31);
		panel_1.add(txtTotalcredit);
		
		JLabel label_4 = new JLabel("{QaarI");
		label_4.setBackground(new Color(0, 0, 0));
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Kiran", Font.PLAIN, 20));
		label_4.setBounds(6, 246, 48, 24);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("jamaa");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Kiran", Font.PLAIN, 20));
		label_5.setBounds(102, 246, 35, 24);
		panel_1.add(label_5);
		
		txtTotaldebit = new JTextField();
		txtTotaldebit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTotaldebit.setEditable(false);
		txtTotaldebit.setColumns(10);
		txtTotaldebit.setBackground(new Color(152, 251, 152));
		txtTotaldebit.setBounds(102, 267, 84, 31);
		panel_1.add(txtTotaldebit);
		
		JLabel label_6 = new JLabel("eku Na baakI");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Kiran", Font.PLAIN, 20));
		label_6.setBounds(187, 246, 88, 24);
		panel_1.add(label_6);
		
		txtRemaining = new JTextField();
		txtRemaining.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtRemaining.setEditable(false);
		txtRemaining.setColumns(10);
		txtRemaining.setBackground(new Color(255, 160, 122));
		txtRemaining.setBounds(191, 267, 84, 31);
		panel_1.add(txtRemaining);
		String htmstart = "<html><font face=\"kiran\" size=\"6\">";
		tmodel = new DefaultTableModel();
		tmodel.addColumn(htmstart+"A.k`");
		tmodel.addColumn(htmstart+"tapaiSala");
		tmodel.addColumn(htmstart+"taairKa");
		tmodel.addColumn(htmstart+"{QaarI");
		tmodel.addColumn(htmstart+"jamaa");
		tmodel.addColumn(htmstart+"baakI");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 320, 591, 373);
		getContentPane().add(scrollPane);
		table = new JTable(tmodel);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(479, 6, 118, 302);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				btnLoad.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnLoad.setForeground(Color.BLUE);
			}
		});
		
		btnLoad.setForeground(Color.BLUE);
		btnLoad.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtId.getText().equals("")||txtId.getText().equals(null))
				{
					return;
				}
				getAllData(Integer.parseInt(txtId.getText()));
				//GetAllDates();
			}
		});
		btnLoad.setBounds(6, 6, 106, 50);
		panel.add(btnLoad);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txtId.setText("");
				txtCode.setText("");
				txtCustomername.setText("");
				txtrAddress.setText("");
				txtRemaining.setText(""+0.0f);
				txtTotalcredit.setText(""+0.0f);
				txtTotaldebit.setText(""+0.0f);
				tmodel.setRowCount(0);
			}
		});
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				btnReset.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReset.setForeground(Color.BLUE);
			}
		});
		btnReset.setForeground(Color.BLUE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnReset.setBounds(6, 60, 106, 50);
		panel.add(btnReset);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				btnPrint.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnPrint.setForeground(Color.BLUE);
			}
		});
		btnPrint.setForeground(Color.BLUE);
		btnPrint.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		btnPrint.setBounds(6, 110, 106, 50);
		panel.add(btnPrint);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new PDFPassbook(Integer.parseInt(txtId.getText()));
			}
		});
		btnPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				btnPdf.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPdf.setForeground(Color.BLUE);
			}
		});
		btnPdf.setForeground(Color.BLUE);
		btnPdf.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnPdf.setBounds(6, 161, 106, 50);
		panel.add(btnPdf);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				btnHome.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHome.setForeground(Color.BLUE);
			}
		});
		btnHome.setForeground(Color.BLUE);
		btnHome.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnHome.setBounds(6, 214, 106, 50);
		panel.add(btnHome);
		txtCustomername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke)
			{
				findItem(txtCustomername.getText());
				if(!list.isEmpty())
				{
					popup1.add(scroll); // your component
					popup1.setPopupSize(txtCustomername.getWidth(), 300);
			        popup1.show(txtCustomername, 0, txtCustomername.getHeight());
			        txtCustomername.requestFocus();
			        
				}
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					jlist.requestFocus();
					jlist.setSelectedIndex(0);
				}

			}
		});
		list = CommonLogic.getAllCustomerFullName();
		model = new DefaultListModel<>();
		jlist = new JList<>(model);
		jlist.setFont(font);
		popup1 = new JPopupMenu();
		popup1.setLayout(new BorderLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(jlist);
		
		jlist.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke)
			{
				char c = ke.getKeyChar();
				if(c==KeyEvent.VK_ENTER)
				{
					txtCustomername.setText(jlist.getSelectedValue());
					int id = CommonLogic.getCustomerIdUsingName(txtCustomername.getText());
					txtId.setText(""+id);
					txtrAddress.setText(CommonLogic.getCustomerAddress(id));
					//loadUsingId(id);
					txtTotalcredit.setText(""+CommonLogic.getAllCredit(id));
					txtTotaldebit.setText(""+CommonLogic.getAllRecieved(id));
					txtRemaining.setText(""+(Float.parseFloat(txtTotalcredit.getText())-Float.parseFloat(txtTotaldebit.getText())));
					txtCode.setText(CommonLogic.getCustomerKeyUsingName(txtCustomername.getText()));
					popup1.setVisible(false);
				}
			}
		});

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
    	   
    	}
	new ChangeBill();

	}

	void findItem(String find)
	{
		model.removeAllElements();
		
		try
		{
		  for(int i = 0;i<list.size();i++)
			{
			  if(((String) list.get(i)).startsWith(find))
				    {
				      
					   model.addElement((String) list.get(i));
				    }
			}
		}catch(Exception e)
		{
			System.out.println("Error in findItem "+e.getMessage());
			return;
		}
	}
	public void GetAllDates()
	{
		try
		{
			
		List<Date> dates = new ArrayList<>();
		@SuppressWarnings("unused")
		Date d,d2 ;
		ResultSet rs = CommonMethods.selectQuery("select BillDate ,BillAmt+Discount from Bill where CustomerID="+1+" and Paymode='credit'");
		
		while(rs.next())
		{
			ResultSet r = CommonMethods.selectQuery("select Date from cashrecieved where CID="+1);
			while(r.next())
			{
				//d2 = r.getDate(1)
			d = rs.getDate(1);
			dates.add(d);
			System.out.println(d+"Amount "+rs.getFloat(2));
			System.out.println("Payment "+r.getDate(1));
			}
		}
		rs.close();
		//rs = CommonMethods.selectQuery("");
		
		
		
		
		
		Iterator<Date> i = dates.iterator();
		 @SuppressWarnings("unused")
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		while(i.hasNext())
		{
			//System.out.println(i.next());
		}
		}catch(Exception  e)
		{
			System.out.println("Error in GetAllDates "+e.getMessage());
		}
	}
	/**
	 * @param id
	 */
	public void getAllData(int id)
	{
		try {
			tmodel.setRowCount(0);
			String htmstart = "<html><font face=\"kiran\" size=\"5\">";
			@SuppressWarnings("unused")
			String mode,credit="",cash="";
			int i=0;
			float baki=0.0f,jama=0.0f,udhar=0.0f;
			ResultSet rs = CommonMethods.selectQuery("select * from Passbook where CID="+id);
		while(rs.next())
		{
			System.out.println(rs);
			mode = rs.getString(4);
			if(mode.equals("Cash"))
			{
				jama=rs.getFloat(3);
				cash="jamaa paavataI k`  "+rs.getInt(5);
				credit="";
				udhar=0.0f;
				baki=baki-jama;
			}
			if(mode.equals("Credit"))
			{
				udhar=rs.getFloat(3);
				cash = "ibala naM "+rs.getInt(5);
				jama=0.0f;
				baki=baki+udhar;
				//cash="";
			}
			tmodel.addRow(new Object[] {++i,htmstart+cash,rs.getDate(6),udhar,jama,baki});
			//tmodel.addRow(new Object[] {++i,"jamaa paavataI k`"+rs.getInt(5),rs.getDate(6),"",rs.getFloat(3)});
			//tmodel.addRow(new Object[] {++i});
		}
		} catch (Exception e)
		{
			System.out.println("Error in getAllData "+e.getMessage());
			// TODO: handle exception
		}
	}

}

package kush.reports;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ankush.CommonMethods;

public class MyTable extends JFrame
{
	

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
//MAIN METHOD
  public static void main(String[] args)
  {
	  CommonMethods.openConnection();
	  new MyTable().setVisible(true);
/*
       EventQueue.invokeLater(new Runnable()
       {
           public void run()
           {
               //INITIALIZE JFRAME FORM
               MyTable form=new MyTable();
               form.setVisible(true);;
           }
       });
*/
  }

  //CONSTRUCTOR
  public MyTable()
  {
    //the form
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(200,200,800,520);
    setTitle("ProgrammingWizards Channel");
    getContentPane().setLayout(null);

    //ADD SCROLLPANE
    JScrollPane scroll=new JScrollPane();
    scroll.setBounds(70,200,600,271);
    getContentPane().add(scroll);

    //THE TABLE
    final JTable table=new JTable();
    table.setShowVerticalLines(true);
	table.setShowHorizontalLines(true);
	table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	table.setRowHeight(25);
	
    scroll.setViewportView(table);

    //THE MODEL OF OUR TABLE
     model=new DefaultTableModel()
    {
      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	public Class<?> getColumnClass(int column)
      {
        switch(column)
        {
        case 0:
          return Boolean.class;
        case 1:
          return String.class;
        case 2:
         

          default:
            return String.class;
        }
      }
    };

    //ASSIGN THE MODEL TO TABLE
    table.setModel(model);

    model.addColumn("Select");
    model.addColumn("BillNo");
    model.addColumn("date");
    model.addColumn("Amount");
    //THE ROW
    addRowData(5);

    //OBTAIN SELECTED ROW
    JButton btn=new JButton("Get Selected");
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        //GET SELECTED ROW
        for(int i=0;i<table.getRowCount();i++)
        {
          Boolean checked=Boolean.valueOf(table.getValueAt(i, 0).toString());
          String col=table.getValueAt(i, 1).toString();
          table.clearSelection();
         
          //DISPLAY
          if(checked)
          {
        	  System.out.println(col);
            //JOptionPane.showMessageDialog(null, col);
          }
          else {System.out.println("Not Selected "+i);}
        }

      }
    });

    //ADD BUTTON TO FORM
    btn.setBounds(76,159,130,30);
    getContentPane().add(btn);
  }
  void addRowData(int id)
  {
	  try
	  {
		  ResultSet rs = CommonMethods.selectQuery("SELECT BillNo,DATE_Format(BillDate,'%d-%m-%Y'), BillAmt from Bill where CustomerID="+id);
		  while(rs.next())
		  {
			  model.addRow(new Object[] {false,""+rs.getInt(1),rs.getString(2),rs.getFloat(3)});
		      
		  }
		  rs.close();
	  }catch(Exception e)
	  {
		  System.out.println("Error "+e.getMessage());
	  }
  }

}
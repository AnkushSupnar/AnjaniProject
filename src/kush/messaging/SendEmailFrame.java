package kush.messaging;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ankush.CommonMethods;

public class SendEmailFrame extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2381212856499112045L;

	public SendEmailFrame()
	{
		setTitle("Send Email to Customer");
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
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
		new SendEmailFrame();

	}

}

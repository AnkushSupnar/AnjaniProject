package kush.design;

import java.awt.Font;
import java.awt.SystemColor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jidesoft.dialog.JideOptionPane;

public class ApplicationSetting extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905329024106808019L;
	private JTextField txturl;
	@SuppressWarnings("unused")
	private JComboBox<String> cmbPrinters, cmbKitchen, cmbBill;
	private Properties prop;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JComboBox<String> cmbBillingPrinter;
	private JTextField txtFontPath;

	public ApplicationSetting() {

		try {
			loadPropertiesFile();
			System.out.println("properties read success");
		} catch (Exception e1) {
			JideOptionPane.showMessageDialog(this, "Error" + e1.getMessage(), "Error", JideOptionPane.ERROR_MESSAGE);

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		setSize(500, 544);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JLabel lblLina = new JLabel("Server Address");
		lblLina.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblLina.setBounds(17, 17, 110, 19);
		getContentPane().add(lblLina);

		txturl = new JTextField(prop.getProperty("MYSQLJDBC.url"));
		txturl.setFont(new Font("SansSerif", Font.BOLD, 14));
		txturl.setBounds(17, 43, 286, 30);
		getContentPane().add(txturl);
		txturl.setColumns(10);

		JLabel lblAvailablePrinters = new JLabel("Font Path");
		lblAvailablePrinters.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblAvailablePrinters.setBounds(17, 204, 67, 20);
		getContentPane().add(lblAvailablePrinters);

		JLabel lblKitchen = new JLabel("Kitchen Printer");
		lblKitchen.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblKitchen.setBounds(28, 330, 102, 20);
		getContentPane().add(lblKitchen);

		cmbKitchen = new JComboBox<>(new Vector<>(getInstelledPrinters()));
		cmbKitchen.setSelectedItem(prop.getProperty("Kitchen.printer"));
		JLabel lblBilling = new JLabel("Billing Printer");
		lblBilling.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblBilling.setBounds(17, 270, 95, 20);
		getContentPane().add(lblBilling);

		cmbBill = new JComboBox<>();

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(e -> System.exit(0));
		btnExit.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnExit.setBounds(17, 439, 90, 28);
		getContentPane().add(btnExit);

		JButton btnChangUrl = new JButton("CHANGE");
		btnChangUrl.addActionListener(e -> {
			prop.setProperty("MYSQLJDBC.url", txturl.getText());
			saveProperty();
		});
		btnChangUrl.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnChangUrl.setBounds(319, 43, 90, 30);
		getContentPane().add(btnChangUrl);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblUserName.setBounds(17, 85, 75, 20);
		getContentPane().add(lblUserName);

		txtUserName = new JTextField(prop.getProperty("MYSQLJDBC.username"));
		txtUserName.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtUserName.setColumns(10);
		txtUserName.setBounds(17, 105, 286, 30);
		getContentPane().add(txtUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblPassword.setBounds(17, 142, 75, 20);
		getContentPane().add(lblPassword);

		txtPassword = new JTextField(prop.getProperty("MYSQLJDBC.password"));
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtPassword.setColumns(10);
		txtPassword.setBounds(17, 162, 286, 30);
		getContentPane().add(txtPassword);

		cmbBillingPrinter = new JComboBox<String>(new Vector<>(getInstelledPrinters()));
		cmbBillingPrinter.setSelectedItem(prop.getProperty("Bill.printer"));
		cmbBillingPrinter.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		cmbBillingPrinter.setBounds(17, 295, 286, 30);
		getContentPane().add(cmbBillingPrinter);

		txtFontPath = new JTextField(prop.getProperty("Bill.Font"));
		txtFontPath.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtFontPath.setColumns(10);
		txtFontPath.setBounds(17, 228, 286, 30);
		getContentPane().add(txtFontPath);

		JComboBox<String> cmbKitchenPrinter = new JComboBox<String>(new Vector<>(getInstelledPrinters()));

		JButton btnChangeUser = new JButton("CHANGE");
		btnChangeUser.addActionListener(e -> {
			prop.setProperty("MYSQLJDBC.username", txtUserName.getText());
			saveProperty();
		});
		btnChangeUser.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnChangeUser.setBounds(319, 107, 90, 30);
		getContentPane().add(btnChangeUser);

		JButton btnChangePassword = new JButton("CHANGE");
		btnChangePassword.addActionListener(e -> {
			prop.setProperty("MYSQLJDBC.password", txtPassword.getText());
			saveProperty();
		});
		btnChangePassword.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnChangePassword.setBounds(319, 164, 90, 30);
		getContentPane().add(btnChangePassword);

		JButton btnChangeFont = new JButton("CHANGE");
		btnChangeFont.addActionListener(e -> {
			prop.setProperty("Bill.Font", txtFontPath.getText());
			saveProperty();
		});
		btnChangeFont.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnChangeFont.setBounds(319, 230, 90, 30);
		getContentPane().add(btnChangeFont);

		JButton btnChangeBillingPrinter = new JButton("CHANGE");
		btnChangeBillingPrinter.addActionListener(e -> {
			prop.setProperty("Bill.printer", cmbBillingPrinter.getSelectedItem().toString());
			saveProperty();
		});
		btnChangeBillingPrinter.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnChangeBillingPrinter.setBounds(319, 296, 90, 30);
		getContentPane().add(btnChangeBillingPrinter);

		JButton btnChangeKitchenPrinter = new JButton("CHANGE");
		btnChangeKitchenPrinter.addActionListener(e -> {
			prop.setProperty("Kitchen.printer", cmbKitchenPrinter.getSelectedItem().toString());
			saveProperty();
		});
		btnChangeKitchenPrinter.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		btnChangeKitchenPrinter.setBounds(319, 351, 90, 30);
		getContentPane().add(btnChangeKitchenPrinter);

		cmbKitchenPrinter.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		cmbKitchenPrinter.setBounds(17, 350, 286, 30);
		getContentPane().add(cmbKitchenPrinter);
		System.out.println(getInstelledPrinters());
		setVisible(true);
	}

	public Properties loadPropertiesFile() throws Exception {
		prop = new Properties();
		InputStream in = new FileInputStream("D:\\HotelDemo\\hotel.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public static void main(String[] args) {
		// CommonMethods.openConnection();
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
		new ApplicationSetting();
	}

	public List<String> getInstelledPrinters() {
		List<String> installedPrinter = new ArrayList<>();
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		// System.out.println("Number of print services: " + printServices.length);

		for (PrintService printer : printServices)
			// System.out.println("Printer: " + printer.getName());
			installedPrinter.add(printer.getName());
		return installedPrinter;
	}

	public void saveProperty() {
		try {

			OutputStream out = new FileOutputStream("D:\\HotelDemo\\hotel.properties");
			prop.store(out, null);
			JOptionPane.showMessageDialog(this, "Save Setting Success", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Saving Setting" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

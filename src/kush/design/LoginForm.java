package kush.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ankush.CommonLogic;
import ankush.CommonMethods;
import kush.main.HomeFrame;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5337325414794509402L;
	private JPanel contentPane;
	private JComboBox<String> userName;
	private JTextField passwordField;
	private JButton btnLaagaina;
	int xx, xy;
	private DefaultComboBoxModel<String> model;
	private JComboBox<String> cmbYear;
	private Properties prop;
	// final static Logger logger = Logger.getLogger(LoginForm.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		int flag = CommonMethods.openConnection();

		if (flag == 1) {

			new LoginForm();

		} else {
			JOptionPane.showMessageDialog(null, "Not Connected\n Please Change Settings ");
			new ApplicationSetting();
		}
	}

	public LoginForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle("Login Form");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 346, 432);
		// contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("KeepToo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(139, 372, 84, 27);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("");

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				LoginForm.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(59, 40, 225, 225);
		label.setVerticalAlignment(SwingConstants.TOP);
		// label.setIcon(new ImageIcon("D:\\Hotel Software\\l.jpeg"));
		// label.setIcon(new ImageIcon(LoginForm.class.getResource("D:\\Hotel
		// Software\\bg.jpg")));
		panel.add(label);

		JLabel lblWeGotYou = new JLabel("....We got you....");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(240, 248, 255));
		lblWeGotYou.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWeGotYou.setBounds(111, 395, 141, 27);
		panel.add(lblWeGotYou);

		btnLaagaina = new JButton("laa^gaIna");
		btnLaagaina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLaagaina.setFont(new Font("Kiran", Font.BOLD, 25));
		btnLaagaina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					btnLaagaina.doClick();
				}
			}
		});

		btnLaagaina.setBounds(85, 338, 283, 36);
		contentPane.add(btnLaagaina);

		model = new DefaultComboBoxModel<>();
		userName = new JComboBox<>(model);
		userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});
		userName.setFont(new Font("Kiran", Font.BOLD, 25));
		loadUserName();
		// AutoCompletionComboBox
		userName.setBounds(85, 181, 283, 36);
		contentPane.add(userName);
		// userName.setColumns(10);

		JLabel lblUsername = new JLabel("yaujarcao naava");
		lblUsername.setFont(new Font("Kiran", Font.BOLD, 25));
		lblUsername.setBounds(85, 156, 93, 24);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("paasavaD-");
		lblPassword.setFont(new Font("Kiran", Font.BOLD, 25));
		lblPassword.setBounds(85, 229, 59, 24);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					btnLaagaina.requestFocus();
				}
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					// getToolkit().beep();
					e.consume();
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 25));
		passwordField.setBounds(85, 254, 283, 36);
		contentPane.add(passwordField);

		JLabel lblAkamtVaya = new JLabel("Aka{MT vaYa-");
		lblAkamtVaya.setFont(new Font("Kiran", Font.BOLD, 25));
		lblAkamtVaya.setBounds(85, 65, 93, 24);
		contentPane.add(lblAkamtVaya);

		loadProperties();

		cmbYear = new JComboBox<String>();
		cmbYear.addItem("2018-2020");
		cmbYear.addItem("2020");

		if (prop.getProperty("MYSQLJDBC.url").equals("jdbc:mysql://SERVER:3306/hotel2020_2021")) {
			cmbYear.setSelectedIndex(1);
		} else
			cmbYear.setSelectedIndex(0);
		cmbYear.setFont(new Font("Lucida Console", Font.BOLD, 20));
		cmbYear.setBounds(85, 101, 283, 36);
		contentPane.add(cmbYear);

		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(e -> load());
		btnLoad.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnLoad.setBounds(380, 101, 83, 36);
		contentPane.add(btnLoad);

		setVisible(true);

	}

	public void loadProperties() {
		try {
			prop = new Properties();
			InputStream in = new FileInputStream("D:\\Hotel Software\\hotel.properties");
			prop.load(in);
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Loading Properties" + e.getMessage());
		}
	}

	private void load() {
		try {
			// 20018-2020", "2020"

			if (cmbYear.getSelectedItem().equals("2018-2020")) {
				prop.setProperty("MYSQLJDBC.url", "jdbc:mysql://SERVER:3306/hotel");
				System.out.println("2018-2020 Selected");
			} else if (cmbYear.getSelectedItem().equals("2020"))
				prop.setProperty("MYSQLJDBC.url", "jdbc:mysql://SERVER:3306/hotel2020_2021");
			else
				return;

			OutputStream out = new FileOutputStream("D:\\Hotel Software\\hotel.properties");
			prop.store(out, null);
			JOptionPane.showMessageDialog(this, "Account Year Change Success", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			CommonMethods.closeConnections();
			CommonMethods.openConnection();
			new LoginForm();
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in Loading Properties" + e.getMessage());

		}
	}

	public void loadUserName() {
		try {

			List<String> user = CommonLogic.getAllUserName();
			Iterator<String> i = user.iterator();
			while (i.hasNext()) {
				model.addElement(i.next());
			}
			if (model.getSize() == 0) {
				JOptionPane.showMessageDialog(null, "No User to Login? \n Contact Administrator to Create user ",
						"Emoty", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No User? " + e.getMessage(), "Empty", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	void login() {
		try {
			if (userName.getItemCount() == 0) {
				return;
			}
			String user = "" + userName.getSelectedItem();
			String password = passwordField.getText();
			if (password.equals(CommonLogic.getPassword(user))) {
				JOptionPane.showMessageDialog(this, "<html><font face='Kiran' size='12' color='black'>" + user,
						"Wel Come", JOptionPane.INFORMATION_MESSAGE);

				// new Dashboard(CommonLogic.getUserId(user));
				new HomeFrame(CommonLogic.getUserId(user));
				this.dispose();
				return;
			} else
				JOptionPane.showMessageDialog(null, "Enter Correct password");

			return;

		} catch (

		Exception e) {
			// logger.error("Login Error " + e.getMessage());
			JOptionPane.showMessageDialog(this, "Error in Login ", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
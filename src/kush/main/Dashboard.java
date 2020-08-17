package kush.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;

import org.apache.log4j.BasicConfigurator;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.design.MyComponents.MenuName;
import kush.design.AddItemCategory;
import kush.design.AddNewBankAccount;
import kush.design.AddNewCustomer;
import kush.design.AddNewEmployee;
import kush.design.AddNewItemFrame;
import kush.design.AddNewTableFrame;
import kush.design.ApplicationSetting;
import kush.design.BillingFrame2;
import kush.design.CreateNewUserFrame;
import kush.design.DailyCollectionInBank;
import kush.design.LoginForm;
import kush.design.PaymentRecievedFromCustomer;
import kush.design.PurchasePartyDetails;
import kush.design.TransactionPanel;

public class Dashboard extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8435636396260759537L;
	private JLabel lblName;
	private Font font = new Font("Kiran", Font.BOLD, 25);
	private Font clickedFont = new Font("Kiran", Font.BOLD, 20);
	private JPanel panleNew;
	ReportPanel reportPanel;
	private BackupPanel backup;

	public Dashboard(final int id) {

		getContentPane().setBackground(new Color(173, 216, 230));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setTitle("Home Frame");
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(0, 0, 1264, 45);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblName = new JLabel("ha^Tola AMjanaI");
		lblName.setForeground(new Color(255, 0, 0));
		lblName.setFont(new Font("Kiran", Font.BOLD, 50));
		lblName.setBounds(250, 1, 214, 47);
		panel.add(lblName);

		JLabel lblFamilyRestaurant = new JLabel("f^imalaI rosTa^rMT");
		lblFamilyRestaurant.setFont(new Font("Kiran", Font.PLAIN, 20));
		lblFamilyRestaurant.setForeground(new Color(34, 139, 34));
		lblFamilyRestaurant.setBounds(474, 22, 83, 20);
		panel.add(lblFamilyRestaurant);

		JLabel lblDevelopedBy = new JLabel("laa^gaIna yaujar");
		lblDevelopedBy.setFont(new Font("Kiran", Font.BOLD, 25));
		lblDevelopedBy.setBounds(991, 18, 103, 26);
		panel.add(lblDevelopedBy);

		JLabel lblJustlogicSoftwarePvtl = new JLabel(CommonLogic.getUserName(id));
		lblJustlogicSoftwarePvtl.setForeground(new Color(0, 0, 255));
		lblJustlogicSoftwarePvtl.setFont(new Font("Kiran", Font.BOLD, 25));
		lblJustlogicSoftwarePvtl.setBounds(1123, 18, 131, 27);
		panel.add(lblJustlogicSoftwarePvtl);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(0, 46, 202, 700);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDashboard = new JLabel("DASHBOARD");
		lblDashboard.setForeground(new Color(255, 255, 224));
		lblDashboard.setFont(new Font("Traditional Arabic", Font.PLAIN, 30));
		lblDashboard.setBounds(5, 11, 193, 45);
		panel_1.add(lblDashboard);

		TransactionPanel transaction = new TransactionPanel(id);
		transaction.setBackground(SystemColor.activeCaption);
		transaction.setBounds(212, 56, 1042, 690);
		transaction.setVisible(false);
		getContentPane().add(transaction);
		final JLabel lblBilling = new MenuName("KarodI ivakI");
		lblBilling.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reportPanel.setVisible(false);
				panleNew.setVisible(false);
				backup.setVisible(false);
				// lblBilling.setFont(clickedFont);
				transaction.setVisible(true);
			}

		});
		lblBilling.setBounds(42, 105, 97, 24);

		final JLabel lblSetting = new MenuName("saoiTMgasa");
		lblSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ApplicationSetting();
			}
		});
		lblSetting.setBounds(42, 198, 64, 24);
		final JLabel lblExit = new MenuName("laa^ga Aa{T");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				exit();
			}
		});
		lblExit.setBounds(42, 573, 88, 24);
		panel_1.add(lblExit);

		final JLabel lblAddCustomer = new MenuName("naivana banavaa");
		lblAddCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reportPanel.setVisible(false);
				panleNew.setVisible(true);
				backup.setVisible(false);
				transaction.setVisible(false);
			}
		});
		lblAddCustomer.setBounds(42, 151, 108, 24);

		// panel_1.add(lblBilling);
		// panel_1.add(lblSetting);
		// panel_1.add(lblAddCustomer);

		final JLabel lblRipaaot = new MenuName("rIpaaoT-");
		lblRipaaot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panleNew.setVisible(false);
				reportPanel.setVisible(true);
				backup.setVisible(false);
				transaction.setVisible(false);
				// new DailySalesReport();
			}
		});
		lblRipaaot.setForeground(new Color(253, 245, 230));
		lblRipaaot.setFont(new Font("Kiran", Font.BOLD, 25));
		lblRipaaot.setBounds(42, 280, 64, 24);

		panleNew = new JPanel();
		panleNew.setBackground(SystemColor.activeCaption);
		panleNew.setBounds(212, 56, 1042, 690);
		panleNew.setVisible(false);

		getContentPane().add(panleNew);
		panleNew.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1042, 43);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panleNew.add(panel_2);
		panel_2.setLayout(null);

		// JLabel lblNewLabel = new MenuName("naivana banavaa");
		JLabel lblNewLabel = new JLabel("naivana banavaa");
		lblNewLabel.setFont(new Font("Kiran", Font.BOLD, 25));
		lblNewLabel.setBounds(23, 11, 94, 24);
		panel_2.add(lblNewLabel);

		final JLabel lblX = new JLabel(" X");
		lblX.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblX.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panleNew.setVisible(false);
			}
		});
		lblX.setForeground(new Color(255, 0, 0));
		lblX.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblX.setBounds(1009, 0, 33, 43);
		panel_2.add(lblX);

		JButton btnCategory = new JButton("maalaacaI naivana k^TogaIrI");
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddItemCategory();
				panleNew.setVisible(false);
			}
		});
		btnCategory.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnCategory.setForeground(Color.WHITE);
		btnCategory.setBackground(new Color(0, 0, 128));
		btnCategory.setBounds(50, 84, 215, 103);
		panleNew.add(btnCategory);

		JButton btnNaivanaMaala = new JButton("naivana maala");
		btnNaivanaMaala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewItemFrame();
				panleNew.setVisible(false);

			}
		});
		btnNaivanaMaala.setForeground(Color.WHITE);
		btnNaivanaMaala.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnNaivanaMaala.setBackground(new Color(0, 0, 128));
		btnNaivanaMaala.setBounds(50, 247, 215, 103);
		panleNew.add(btnNaivanaMaala);

		JButton btnNaivanaTobala = new JButton("naivana Tobala");
		btnNaivanaTobala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddNewTableFrame();
				panleNew.setVisible(false);
			}
		});
		btnNaivanaTobala.setForeground(Color.WHITE);
		btnNaivanaTobala.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnNaivanaTobala.setBackground(new Color(0, 0, 128));
		btnNaivanaTobala.setBounds(50, 409, 215, 103);
		panleNew.add(btnNaivanaTobala);

		JButton btnNaivanaKstmar = new JButton("naivana ksTmar");
		btnNaivanaKstmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddNewCustomer();
				panleNew.setVisible(false);
			}
		});
		btnNaivanaKstmar.setForeground(Color.WHITE);
		btnNaivanaKstmar.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnNaivanaKstmar.setBackground(new Color(0, 0, 128));
		btnNaivanaKstmar.setBounds(352, 84, 215, 103);
		panleNew.add(btnNaivanaKstmar);

		JButton btnNaivanaYaujarLaaigana = new JButton("naivana yaujar laa^igana");
		btnNaivanaYaujarLaaigana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateNewUserFrame();
				panleNew.setVisible(false);
			}
		});
		btnNaivanaYaujarLaaigana.setForeground(Color.WHITE);
		btnNaivanaYaujarLaaigana.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnNaivanaYaujarLaaigana.setBackground(new Color(0, 0, 128));
		btnNaivanaYaujarLaaigana.setBounds(352, 247, 215, 103);
		panleNew.add(btnNaivanaYaujarLaaigana);

		JButton btnNaivanaKamagaar = new JButton("naivana kamagaar");
		btnNaivanaKamagaar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddNewEmployee();
				panleNew.setVisible(false);
			}
		});
		btnNaivanaKamagaar.setForeground(Color.WHITE);
		btnNaivanaKamagaar.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnNaivanaKamagaar.setBackground(new Color(0, 0, 128));
		btnNaivanaKamagaar.setBounds(352, 409, 215, 103);
		panleNew.add(btnNaivanaKamagaar);

		JButton btnNaivanaBamk = new JButton("naivana ba^Mk");
		btnNaivanaBamk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddNewBankAccount();
			}
		});
		btnNaivanaBamk.setForeground(Color.WHITE);
		btnNaivanaBamk.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnNaivanaBamk.setBackground(new Color(0, 0, 128));
		btnNaivanaBamk.setBounds(727, 84, 215, 103);
		panleNew.add(btnNaivanaBamk);

		JButton btnKarodiPaati = new JButton("KarodI paaTI-");
		btnKarodiPaati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PurchasePartyDetails();
			}
		});
		btnKarodiPaati.setForeground(Color.WHITE);
		btnKarodiPaati.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnKarodiPaati.setBackground(new Color(0, 0, 128));
		btnKarodiPaati.setBounds(727, 247, 215, 103);
		panleNew.add(btnKarodiPaati);

		final JLabel lblJamaaPavatai = new JLabel("jamaa paavataI");
		lblJamaaPavatai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new PaymentRecievedFromCustomer();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblJamaaPavatai.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblJamaaPavatai.setForeground(Color.WHITE);
			}
		});
		lblJamaaPavatai.setForeground(new Color(253, 245, 230));
		lblJamaaPavatai.setFont(new Font("Kiran", Font.BOLD, 25));
		lblJamaaPavatai.setBounds(42, 240, 91, 24);

		reportPanel = new ReportPanel();
		reportPanel.setVisible(false);
		getContentPane().add(reportPanel);
		// panel_1.add(lblPaasabauk);

		panel_1.add(lblBilling);
		panel_1.add(lblSetting);
		panel_1.add(lblAddCustomer);
		panel_1.add(lblRipaaot);
		panel_1.add(lblJamaaPavatai);

		backup = new BackupPanel();
		backup.setBounds(212, 56, 1042, 690);
		getContentPane().add(backup);
		backup.setVisible(false);
		JLabel lblBackup = new JLabel("ba^k Apa");
		lblBackup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblBackup.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblBackup.setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				panleNew.setVisible(false);
				reportPanel.setVisible(false);
				backup.setVisible(true);

			}
		});
		lblBackup.setForeground(new Color(253, 245, 230));
		lblBackup.setFont(new Font("Kiran", Font.BOLD, 25));
		lblBackup.setBounds(42, 320, 64, 24);
		panel_1.add(lblBackup);

		final JLabel lblDailyCollection = new JLabel("DolaI klao@Sana");
		lblDailyCollection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblDailyCollection.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblDailyCollection.setForeground(new Color(253, 245, 230));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new DailyCollectionInBank();
			}
		});
		lblDailyCollection.setForeground(new Color(253, 245, 230));
		lblDailyCollection.setFont(font);

		// lblDailyCollection.setVisible(false);
		panel_1.add(lblDailyCollection);

		JLabel lblDolaiIbalaimga = new JLabel("DolaI ibalaIMga");

		lblDolaiIbalaimga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblDolaiIbalaimga.setFont(clickedFont);
				new BillingFrame2(id);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblDolaiIbalaimga.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblDolaiIbalaimga.setForeground(Color.WHITE);
			}
		});
		lblDolaiIbalaimga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				new BillingFrame2(id);
			}
		});

		lblDolaiIbalaimga.setForeground(new Color(253, 245, 230));
		lblDolaiIbalaimga.setFont(new Font("Kiran", Font.BOLD, 25));
		lblDolaiIbalaimga.setBounds(42, 60, 97, 24);
		panel_1.add(lblDolaiIbalaimga);
		// lblDolaiIbalaimga.setVisible(false);

		// validating user
		if (id == 1) {
			panel_1.add(lblBilling);
			panel_1.add(lblSetting);
			// lblSetting.setForeground(Color.red);
			panel_1.add(lblAddCustomer);
			panel_1.add(lblRipaaot);
			panel_1.add(lblJamaaPavatai);
			lblDailyCollection.setBounds(42, 360, 120, 24);
			lblDolaiIbalaimga.setVisible(false);
			lblBilling.setBounds(42, 90, 120, 24);
			// transaction.setVisible(true);
		}
		if (id != 1) {

			panel_1.add(lblBilling);
			panel_1.add(lblSetting);
			lblSetting.setVisible(true);
			lblSetting.setBounds(42, 198, 120, 24);
			lblDailyCollection.setBounds(42, 160, 120, 24);
			lblAddCustomer.setVisible(false);
			lblJamaaPavatai.setVisible(false);
			lblRipaaot.setVisible(false);
			lblBackup.setVisible(false);
			lblBilling.setVisible(false);
			// transaction.setVisible(true);
		}

		setVisible(true);
	}

	void exit() {
		new LoginForm().setVisible(true);
		this.dispose();
	}

	public static void main(String[] args) {
		int flag = CommonMethods.openConnection();

		BasicConfigurator.configure();
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

		if (flag == 1) {
			JOptionPane.showMessageDialog(null, "Connected");
			// WebLookAndFeel.install();
			new Dashboard(1);
		} else {
			JOptionPane.showMessageDialog(null, "Not Connected ");
		}
		// TODO Auto-generated method stub

	}
}

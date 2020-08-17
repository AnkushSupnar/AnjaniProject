package kush.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ankush.CommonLogic;
import ankush.CommonMethods;
import ankush.design.MyComponents.MenuName;
import kush.design.LoginForm;

public class HomeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -921967419401477531L;
	private JLabel lblName;
	// private Font font = new Font("Kiran", Font.BOLD, 25);
	// private Font clickedFont = new Font("Kiran", Font.BOLD, 20);
	private JPanel dailyTransactionPanel, addNewPanel, reportPanel, backupPanel, MenuPanel;
	private JLabel lblAddNew, lblReport, lblSettings, lblExit, lblDailyTransaction;

	public HomeFrame(int id) {
		getContentPane().setBackground(new Color(173, 216, 230));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setTitle("Home Frame");
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(0, 0, 1281, 45);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblName = new JLabel("ha^Tola AMjanaI");
		lblName.setForeground(new Color(255, 0, 0));
		lblName.setFont(new Font("Kiran", Font.ITALIC, 50));
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

		MenuPanel = new JPanel();
		MenuPanel.setBackground(new Color(0, 0, 128));
		MenuPanel.setBounds(0, 46, 202, 700);
		getContentPane().add(MenuPanel);
		MenuPanel.setLayout(null);

		JLabel lblDashboard = new JLabel("DASHBOARD");
		lblDashboard.setForeground(new Color(255, 255, 224));
		lblDashboard.setFont(new Font("Traditional Arabic", Font.PLAIN, 30));
		lblDashboard.setBounds(5, 11, 193, 45);
		MenuPanel.add(lblDashboard);

		initMenu(id);
		initPanel(id);

		setVisible(true);

	}

	private void initMenu(int id) {

		lblDailyTransaction = new MenuName("raojacao vyavahar");
		lblDailyTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(dailyTransactionPanel);
			}
		});
		lblDailyTransaction.setBounds(30, 80, 113, 24);
		MenuPanel.add(lblDailyTransaction);

		lblAddNew = new MenuName("naivana banavaa");
		lblAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(addNewPanel);
			}
		});
		lblAddNew.setBounds(30, 130, 94, 24);
		MenuPanel.add(lblAddNew);

		lblReport = new MenuName("irpaaoT-");
		lblReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(reportPanel);
			}
		});
		lblReport.setBounds(30, 217, 46, 24);
		MenuPanel.add(lblReport);

		lblSettings = new MenuName("saoiTMgsa");
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(backupPanel);
			}
		});
		lblSettings.setBounds(30, 340, 58, 24);
		MenuPanel.add(lblSettings);

		lblExit = new MenuName("laa^ga Aa{]T");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new LoginForm();
				dispose();
			}
		});
		lblExit.setSize(93, 24);
		lblExit.setLocation(30, 640);
		lblAddNew.setBounds(30, 150, 110, 24);
		MenuPanel.add(lblExit);

		MenuName lblBanking = new MenuName("irpaaoT-");
		lblBanking.setText("ba^Mko cao vyavahar");
		lblBanking.setBounds(30, 278, 112, 24);
		MenuPanel.add(lblBanking);
		if (id != 1) {
			lblAddNew.setVisible(false);
			lblReport.setVisible(false);
			lblBanking.setVisible(false);
			lblSettings.setBounds(lblAddNew.getBounds());

		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		CommonMethods.openConnection();
		new HomeFrame(1);

	}

	private void initPanel(int id) {
		dailyTransactionPanel = new DailyTransactionPanel(id);
		dailyTransactionPanel.setLocation(201, 45);
		getContentPane().add(dailyTransactionPanel);
		dailyTransactionPanel.setVisible(false);

		addNewPanel = new AddNewPanel();
		addNewPanel.setLocation(201, 45);
		getContentPane().add(addNewPanel);
		addNewPanel.setVisible(false);

		reportPanel = new ReportPanel();
		reportPanel.setLocation(201, 45);
		getContentPane().add(reportPanel);
		reportPanel.setVisible(false);

		backupPanel = new BackupPanel();
		backupPanel.setLocation(201, 45);
		getContentPane().add(backupPanel);
		backupPanel.setVisible(false);
	}

	public void showPanel(JPanel panel) {

		dailyTransactionPanel.setVisible(false);
		addNewPanel.setVisible(false);
		reportPanel.setVisible(false);
		backupPanel.setVisible(false);
		panel.setVisible(true);
	}
}

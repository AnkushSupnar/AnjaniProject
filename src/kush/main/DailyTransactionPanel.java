package kush.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import ankush.CommonMethods;
import ankush.design.MyComponents.MenuButton;
import kush.design.DailyCollectionInBank;
import kush.design.ItemPurchaseFrame;
import kush.design.PaymentRecievedFromCustomer;
import kush.design.billing.BillingFrame3;

public class DailyTransactionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private int id;

	public DailyTransactionPanel(int id) {
		// this.id = id;

		setBackground(SystemColor.activeCaption);
		setSize(1042, 690);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1042, 40);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("   X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(984, 0, 48, 37);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("raojacao vyavahar");
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(23, 5, 115, 29);
		panel.add(lblNewLabel_1);

		JButton btndailyBilling = new MenuButton("DolaI ibalaIMga");
		btndailyBilling.setForeground(new Color(255, 255, 255));
		btndailyBilling.setBackground(new Color(0, 0, 128));
		btndailyBilling.addActionListener(e -> new BillingFrame3(id));
		btndailyBilling.setBounds(60, 106, 243, 95);
		add(btndailyBilling);

		JButton btnDailyCollection = new MenuButton("DolaI k lao@Sana");
		btnDailyCollection.setForeground(new Color(255, 255, 255));
		btnDailyCollection.setBackground(new Color(0, 0, 128));
		btnDailyCollection.addActionListener(e -> new DailyCollectionInBank());
		btnDailyCollection.setBounds(359, 106, 243, 95);
		add(btnDailyCollection);

		JButton btnPurchase = new MenuButton("naivana maala KarodI");
		btnPurchase.setForeground(new Color(255, 255, 255));
		btnPurchase.setBackground(new Color(0, 0, 128));
		btnPurchase.addActionListener(e -> new ItemPurchaseFrame());
		btnPurchase.setBounds(665, 106, 243, 95);
		add(btnPurchase);

		JButton btnPaymentReciept = new MenuButton("jamaa paavataI");
		btnPaymentReciept.setForeground(new Color(255, 255, 255));
		btnPaymentReciept.setBackground(new Color(0, 0, 128));
		btnPaymentReciept.addActionListener(e -> new PaymentRecievedFromCustomer());
		btnPaymentReciept.setBounds(60, 255, 243, 95);
		add(btnPaymentReciept);
		if (id != 1) {
			btnPurchase.setVisible(false);
			btnPaymentReciept.setVisible(false);

		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		CommonMethods.openConnection();
		JFrame frame = new JFrame();
		frame.setSize(1050, 695);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(new DailyTransactionPanel(2));
		frame.setVisible(true);
	}
}

package kush.design;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ankush.CommonMethods;
import kush.design.billing.BillingFrame3;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransactionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5925590758476441944L;

	public TransactionPanel(int id) {
		setBackground(SystemColor.activeCaption);
		setSize(1042, 690);
		setLayout(null);

		JButton button = new JButton("DolaI ibailaMga");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// new BillingFrame3(id);
				new BillingFrame3(id);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Kiran", Font.PLAIN, 30));
		button.setBackground(new Color(0, 0, 128));
		button.setBounds(41, 155, 215, 103);
		add(button);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 1042, 43);
		add(panel);

		JLabel label = new JLabel("naivana banavaa");
		label.setFont(new Font("Kiran", Font.BOLD, 25));
		label.setBounds(23, 11, 94, 24);
		panel.add(label);

		JLabel label_1 = new JLabel(" X");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(1009, 0, 33, 43);
		panel.add(label_1);

		JButton btnMaalaKarodi = new JButton("maala KarodI");
		btnMaalaKarodi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ItemPurchaseFrame();
			}
		});
		btnMaalaKarodi.setForeground(Color.WHITE);
		btnMaalaKarodi.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnMaalaKarodi.setBackground(new Color(0, 0, 128));
		btnMaalaKarodi.setBounds(333, 155, 215, 103);
		add(btnMaalaKarodi);

		JButton btnDolaiIbailamga = new JButton("DolaI ibailaMga 2");
		btnDolaiIbailamga.addActionListener(e -> new BillingFrame3(id));
		btnDolaiIbailamga.setForeground(Color.WHITE);
		btnDolaiIbailamga.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnDolaiIbailamga.setBackground(new Color(0, 0, 128));
		btnDolaiIbailamga.setBounds(41, 287, 215, 103);
		add(btnDolaiIbailamga);
		btnMaalaKarodi.setVisible(false);
		if (id == 1) {
			btnMaalaKarodi.setVisible(true);
		}

	}

	public static void main(String[] args) {
		CommonMethods.openConnection();
		JFrame frame = new JFrame();

		frame.setSize(1042, 690);
		frame.getContentPane().add(new TransactionPanel(2));
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

	}
}

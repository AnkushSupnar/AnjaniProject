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
import kush.design.AddItemCategory;
import kush.design.AddNewBankAccount;
import kush.design.AddNewCustomer;
import kush.design.AddNewEmployee;
import kush.design.AddNewItemFrame;
import kush.design.AddNewTableFrame;
import kush.design.CreateNewUserFrame;
import kush.design.PurchasePartyDetails;

public class AddNewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4804392562619064530L;

	public AddNewPanel() {
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

		JLabel lblNewLabel_1 = new JLabel("naivana banavaa");
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(23, 5, 94, 29);
		panel.add(lblNewLabel_1);

		JButton btnAddNewCategory = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewCategory.setForeground(new Color(255, 255, 255));
		btnAddNewCategory.setBackground(new Color(0, 0, 128));
		btnAddNewCategory.addActionListener(e -> new AddItemCategory());
		btnAddNewCategory.setBounds(74, 80, 243, 95);
		add(btnAddNewCategory);

		MenuButton btnAddNewCustomer = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewCustomer.setBackground(new Color(0, 0, 128));
		btnAddNewCustomer.setForeground(new Color(255, 255, 255));
		btnAddNewCustomer.addActionListener(e -> new AddNewCustomer());
		btnAddNewCustomer.setText("naivana ksTmar");
		btnAddNewCustomer.setBounds(74, 225, 243, 95);
		add(btnAddNewCustomer);

		MenuButton btnAddNewTable = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewTable.setForeground(new Color(255, 255, 255));
		btnAddNewTable.setBackground(new Color(0, 0, 128));
		btnAddNewTable.addActionListener(e -> new AddNewTableFrame());
		btnAddNewTable.setText("naivana Tobala");
		btnAddNewTable.setBounds(74, 378, 243, 95);
		add(btnAddNewTable);

		MenuButton btnAddNewPurchaseParty = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewPurchaseParty.setBackground(new Color(0, 0, 128));
		btnAddNewPurchaseParty.setForeground(new Color(255, 255, 255));
		btnAddNewPurchaseParty.addActionListener(e -> new PurchasePartyDetails());
		btnAddNewPurchaseParty.setText("naivana KarodI paaTI-");
		btnAddNewPurchaseParty.setBounds(74, 551, 243, 95);
		add(btnAddNewPurchaseParty);

		MenuButton btnAddNewItem = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewItem.setForeground(new Color(255, 255, 255));
		btnAddNewItem.setBackground(new Color(0, 0, 128));
		btnAddNewItem.addActionListener(e -> new AddNewItemFrame());
		btnAddNewItem.setText("naivana maala");
		btnAddNewItem.setBounds(450, 80, 243, 95);
		add(btnAddNewItem);

		MenuButton btnAddNewUser = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewUser.setBackground(new Color(0, 0, 128));
		btnAddNewUser.setForeground(new Color(255, 255, 255));
		btnAddNewUser.addActionListener(e -> new CreateNewUserFrame());
		btnAddNewUser.setText("yaujar laa^gaIna");
		btnAddNewUser.setBounds(450, 225, 243, 95);
		add(btnAddNewUser);

		MenuButton btnAddNewWorker = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewWorker.setForeground(new Color(255, 255, 255));
		btnAddNewWorker.setBackground(new Color(0, 0, 128));
		btnAddNewWorker.addActionListener(e -> new AddNewEmployee());
		btnAddNewWorker.setText("naivana kamagaar");
		btnAddNewWorker.setBounds(450, 378, 243, 95);
		add(btnAddNewWorker);

		MenuButton btnAddNewBank = new MenuButton("maalaacaI naivana k^TogaIrI");
		btnAddNewBank.setForeground(new Color(255, 255, 255));
		btnAddNewBank.setBackground(new Color(0, 0, 128));
		btnAddNewBank.addActionListener(e -> new AddNewBankAccount());
		btnAddNewBank.setText("naivana ba^Mk");
		btnAddNewBank.setBounds(450, 551, 243, 95);
		add(btnAddNewBank);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CommonMethods.openConnection();
		JFrame frame = new JFrame();
		frame.setSize(1050, 695);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(new AddNewPanel());
		frame.setVisible(true);
	}
}

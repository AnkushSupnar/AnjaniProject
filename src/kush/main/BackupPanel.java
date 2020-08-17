package kush.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import kush.design.ApplicationSetting;
import kush.design.GetBackup;
import kush.setup.ImportExportData;

public class BackupPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BackupPanel() {
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		setSize(1042, 690);

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

		JLabel lblGetBackupData = new JLabel("GET BACKUP DATA");
		lblGetBackupData.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblGetBackupData.setBounds(449, 11, 182, 27);
		panel.add(lblGetBackupData);

		JButton btnLocalBackup = new JButton("LOCAL BACKUP");
		btnLocalBackup.setForeground(Color.WHITE);
		btnLocalBackup.addActionListener(e -> {
			new GetBackup();
			JOptionPane.showMessageDialog(null, "Backup Success at-" + "D:\\LocalBackup\\", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		});
		btnLocalBackup.setBounds(24, 78, 199, 103);
		btnLocalBackup.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnLocalBackup.setBackground(new Color(0, 0, 128));

		add(btnLocalBackup);

		JButton btnExternalBackup = new JButton("EXTERNAL BACKUP");
		btnExternalBackup.setForeground(Color.WHITE);
		btnExternalBackup.addActionListener(e -> new ImportExportData());

		btnExternalBackup.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnExternalBackup.setBackground(new Color(0, 0, 128));
		btnExternalBackup.setBounds(328, 78, 199, 103);
		add(btnExternalBackup);

		JButton btnApplicationSettings = new JButton("APPLICATION SETTINGS");
		btnApplicationSettings.setForeground(Color.WHITE);
		btnApplicationSettings.addActionListener(e -> new ApplicationSetting());
		btnApplicationSettings.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnApplicationSettings.setBackground(new Color(0, 0, 128));
		btnApplicationSettings.setBounds(627, 78, 238, 103);
		add(btnApplicationSettings);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.getContentPane().setLayout(null);
		BackupPanel b = new BackupPanel();
		b.setBounds(212, 56, 1042, 690);
		f.getContentPane().add(b);
		f.setVisible(true);
		// TODO Auto-generated method stub

	}
}

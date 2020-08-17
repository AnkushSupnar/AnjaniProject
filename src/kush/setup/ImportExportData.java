package kush.setup;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ankush.CommonMethods;
import kush.design.GetBackup;
import kush.design.RestoreBackup;

public class ImportExportData extends JFrame implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6970736003061193715L;
	private JTextField txtFilepath;
	private JTextField txtSelectDrive;
	private JProgressBar progress;
	private JTextArea txtProgress;

	JButton btnTakeBackup;

	public ImportExportData() {
		setSize(584, 500);
		setTitle("Import and Export Data");
		getContentPane().setLayout(null);

		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));

		JButton btnOpen = new JButton("SELECT FOLDER");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = file.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					System.out.println(selectedFile.getParentFile());
					System.out.println("Result" + result);
					txtFilepath.setText(selectedFile.getParent());
				}
			}
		});
		btnOpen.setBounds(351, 343, 142, 30);
		getContentPane().add(btnOpen);

		txtFilepath = new JTextField();
		txtFilepath.setBounds(30, 344, 309, 30);
		getContentPane().add(txtFilepath);
		txtFilepath.setColumns(10);

		progress = new JProgressBar(0, 100);
		progress.setBackground(SystemColor.activeCaption);
		progress.setBounds(22, 118, 471, 19);
		progress.setStringPainted(true);
		progress.setIndeterminate(false);

		getContentPane().add(progress);

		progress.setVisible(false);
		progress.setIndeterminate(false);
		JButton btnRestoreBackup = new JButton("RESTORE BACKUP");
		btnRestoreBackup.addActionListener(e -> new RestoreBackup(txtFilepath.getText() + "\\"));
		btnRestoreBackup.setBounds(30, 390, 142, 30);
		getContentPane().add(btnRestoreBackup);

		btnTakeBackup = new JButton("TAKE BACKUP");
		btnTakeBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					progress.setVisible(true);
					GetBackup b = new GetBackup();

					if (txtSelectDrive.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Select Drive to Take backup", "Empty",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String filepath = txtSelectDrive.getText();
					filepath = filepath + "\\Backup\\";
					txtProgress.setText("Starting backup : " + filepath);
					if (b.writeBillTable(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\BillTable"));
						progress.setValue(10);

						// Thread.sleep(10000);
						System.out.println("Success" + filepath);
					}
					if (b.writebankDetails(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Bank Details"));
						progress.setValue(15);
						// Thread.sleep(10000);
					}
					if (b.cashReceivedBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Cash Received"));
						progress.setValue(20);
						// Thread.sleep(10000);
					}
					if (b.categoryMasterBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\CategoryMaster"));
						progress.setValue(25);
						// Thread.sleep(10000);
					}
					if (b.customerBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Customer"));
						progress.setValue(40);
						// Thread.sleep(10000);
					}
					if (b.dailycollectioninbankBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Daily Collection"));
						progress.setValue(50);
						// Thread.sleep(10000);
					}
					if (b.employeeBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Employee"));
						progress.setValue(55);
						// Thread.sleep(10000);
					}
					if (b.itemBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Items"));
						progress.setValue(70);
						// Thread.sleep(10000);
					}
					if (b.loginBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Login"));
						progress.setValue(75);
						// Thread.sleep(10000);
					}
					if (b.passbookBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Login"));
						progress.setValue(80);
						// Thread.sleep(10000);
					}
					if (b.paymentrecivedBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Paymentreceived"));
						progress.setValue(85);
						// Thread.sleep(10000);
					}
					if (b.tableBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Table"));
						progress.setValue(90);
						// Thread.sleep(10000);
					}
					if (b.transactionBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Transaction"));
						progress.setValue(100);
						// Thread.sleep(10000);

					}
					if (b.ItemStockBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\ItemStock"));
						progress.setValue(100);
					}
					if (b.employeeSalaryBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Employee Salary"));
						progress.setValue(100);
					}
					if (b.purchaseBillBackUp(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Purchase Bill"));
						progress.setValue(100);
					}
					if (b.purchasePartyBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Purchase party"));
						progress.setValue(100);
					}
					if (b.purchaseTransactionBackup(filepath) == 1) {
						txtProgress.setText(txtProgress.getText() + ("\n" + filepath + "\\Purchase Transaction"));
						progress.setValue(100);
					}

					txtProgress.setText(txtProgress.getText() + ("\nBackup Completed !!!!!"));

				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Error" + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnTakeBackup.setBounds(17, 76, 110, 30);
		getContentPane().add(btnTakeBackup);

		txtSelectDrive = new JTextField();
		txtSelectDrive.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSelectDrive.setBounds(17, 18, 374, 28);
		getContentPane().add(txtSelectDrive);
		txtSelectDrive.setColumns(10);

		JButton btnBrows = new JButton("Brows");
		btnBrows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					// File dir = chooser.getCurrentDirectory();
					txtSelectDrive.setText("" + chooser.getSelectedFile());
					System.out.println("Selected Folder" + chooser.getSelectedFile());
				}
			}
		});
		btnBrows.setBounds(403, 16, 90, 30);
		getContentPane().add(btnBrows);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 135, 471, 196);
		getContentPane().add(scrollPane);

		txtProgress = new JTextArea();
		txtProgress.setEditable(false);
		txtProgress.setFont(new Font("SansSerif", Font.PLAIN, 14));
		scrollPane.setViewportView(txtProgress);
		txtProgress.setForeground(Color.WHITE);
		txtProgress.setBackground(Color.BLACK);

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
		new ImportExportData();

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}
}

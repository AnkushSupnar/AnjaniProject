package kush.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ankush.design.MyComponents.MenuButton;
import kush.reports.DailySalesReport;
import kush.reports.MonthlySalesReport;
import kush.reports.Passbook;
import kush.reports.SelectMultipleBillsToPrint;
import kush.reports.SpecificPeriodSalesReport;
import kush.reports.ViewStock;

public class ReportPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8070825613053840953L;
	private JLabel lblX;

	public ReportPanel() {
		setBackground(SystemColor.activeCaption);
		setSize(1042, 690);
		setLocation(212, 56);
		setLayout(null);

		JButton btnDailysalesreport = new MenuButton("DolaI ivak`I rIpaaoT-");
		btnDailysalesreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DailySalesReport();
			}
		});
		btnDailysalesreport.setBackground(new Color(0, 0, 128));
		btnDailysalesreport.setForeground(Color.WHITE);
		btnDailysalesreport.setBounds(12, 104, 215, 103);
		add(btnDailysalesreport);

		JButton btnPeriodSalesReport = new MenuButton("kalaavaiQa ivak`I rIpaaoT-");
		btnPeriodSalesReport.setForeground(Color.WHITE);
		btnPeriodSalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SpecificPeriodSalesReport();
			}
		});
		btnPeriodSalesReport.setBounds(534, 104, 215, 103);
		btnPeriodSalesReport.setBackground(new Color(0, 0, 128));
		add(btnPeriodSalesReport);

		JButton btnMonthlySalesReport = new MenuButton("maihnaa ivak`I rIpaaoT-");
		btnMonthlySalesReport.setForeground(Color.WHITE);
		btnMonthlySalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MonthlySalesReport();
			}
		});
		btnMonthlySalesReport.setBackground(new Color(0, 0, 128));
		btnMonthlySalesReport.setBounds(270, 104, 215, 103);
		add(btnMonthlySalesReport);

		JButton btnPassbook = new MenuButton("paasabauk");
		btnPassbook.setForeground(Color.WHITE);
		btnPassbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Passbook();
			}
		});
		btnPassbook.setBounds(12, 244, 215, 103);
		btnPassbook.setBackground(new Color(0, 0, 128));
		add(btnPassbook);

		JButton btnPrintBills = new MenuButton("pa`IMT ibalao");
		btnPrintBills.setForeground(Color.WHITE);
		btnPrintBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectMultipleBillsToPrint();
			}
		});
		btnPrintBills.setBounds(270, 244, 215, 103);
		btnPrintBills.setBackground(new Color(0, 0, 128));
		add(btnPrintBills);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1042, 46);
		add(panel);
		panel.setLayout(null);

		JLabel lblAllReport = new JLabel("sava- rIpaaoT-");
		lblAllReport.setBounds(6, 6, 82, 25);
		panel.add(lblAllReport);
		lblAllReport.setBackground(Color.WHITE);
		lblAllReport.setFont(new Font("Kiran", Font.BOLD, 26));

		lblX = new JLabel(" X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.RED);
			}
		});
		lblX.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("SansSerif", Font.BOLD, 34));
		lblX.setBounds(1000, 0, 38, 52);
		panel.add(lblX);

		JButton btnStockReport = new MenuButton("iSallak sTa^k");
		btnStockReport.setForeground(Color.WHITE);
		btnStockReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewStock();
			}
		});
		btnStockReport.setBounds(534, 244, 215, 103);
		btnStockReport.setBackground(new Color(0, 0, 128));
		add(btnStockReport);
		// setVisible(true);
	}
}

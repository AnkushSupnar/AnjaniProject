package kush.design.billing;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ankush.CommonLogic;
import kush.POJO.Table;

public class TablePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelP, panelHP;
	private JPanel panelA;
	private JPanel panelB;
	private JPanel panelE;
	private JPanel panelD;
	private JPanel panelC;
	private JPanel panelV;
	private JPanel panelG;
	private BillingFrame3 frame;
	JButton btnRefreash, table;
	// private WebButton btnRefreash, table;

	TablePanel(BillingFrame3 frame) {
		this.frame = frame;

		setBackground(new Color(70, 130, 180));
		setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		setBounds(1, 1, 424, 800);
		setLayout(null);

		btnRefreash = new JButton("REFRESH");
		btnRefreash.setMnemonic('R');
		btnRefreash.addActionListener(e -> refresh());
		btnRefreash.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		btnRefreash.setBounds(1, 1, 420, 34);
		add(btnRefreash);

		panelP = new JPanel();
		panelP.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelP.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelP.setBounds(1, 40, 420, 90);
		add(panelP);

		panelHP = new JPanel();
		panelHP.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelHP.setBounds(1, 132, 420, 135);
		add(panelHP);
		panelHP.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panelA = new JPanel();
		panelA.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelA.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelA.setBounds(1, 275, 420, 45);
		add(panelA);

		panelB = new JPanel();
		panelB.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelB.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelB.setBounds(1, 322, 210, 90);
		add(panelB);

		panelE = new JPanel();
		panelE.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelE.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelE.setBounds(1, 417, 210, 90);
		add(panelE);

		panelD = new JPanel();
		panelD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelD.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelD.setBounds(215, 417, 205, 90);
		add(panelD);

		panelC = new JPanel();
		panelC.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelC.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelC.setBounds(215, 322, 205, 90);
		add(panelC);

		panelV = new JPanel();
		panelV.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelV.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelV.setBounds(1, 510, 420, 90);
		add(panelV);

		panelG = new JPanel();
		panelG.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelG.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelG.setBounds(1, 605, 420, 90);
		add(panelG);

		JLabel lblNewLabel = new JLabel("Developed By Ankush Supnar");
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBounds(1, 695, 162, 16);
		add(lblNewLabel);

		JLabel lblContact = new JLabel("Contact- 8329394603 /  9960855742");
		lblContact.setForeground(Color.WHITE);
		lblContact.setBounds(1, 707, 201, 16);
		add(lblContact);

		JLabel lblEmailAnkushsupnargmailcom = new JLabel("Email- ankushsupnar@gmail.com");
		lblEmailAnkushsupnargmailcom.setForeground(Color.WHITE);
		lblEmailAnkushsupnargmailcom.setBounds(205, 707, 189, 16);
		add(lblEmailAnkushsupnargmailcom);

		loadTables();

	}

	public void loadTables() {
		try {

			Component[] components = panelP.getComponents();

			for (Component component : components) {
				// System.out.println("in panelP= " + component.getName());
				panelP.remove(component);
				// wait(500);
			}
			panelP.revalidate();
			panelP.repaint();

			panelP.removeAll();
			panelP.revalidate();
			panelP.repaint();

			panelHP.removeAll();
			panelHP.revalidate();
			panelHP.repaint();

			panelA.removeAll();
			panelA.revalidate();
			panelA.repaint();

			panelB.removeAll();
			panelB.revalidate();
			panelB.repaint();

			panelC.removeAll();
			panelC.revalidate();
			panelC.repaint();

			panelD.removeAll();
			panelD.revalidate();
			panelD.repaint();

			panelE.removeAll();
			panelE.revalidate();
			panelE.repaint();

			panelG.removeAll();
			panelG.revalidate();
			panelG.repaint();

			panelV.removeAll();
			panelV.revalidate();
			panelV.repaint();
			this.repaint();

			Iterator<Table> i = CommonLogic.getTable().iterator();
			Table t;
			while (i.hasNext()) {
				t = i.next();
				// for panel p
				if (t.getDESCRIPTION().equals("P")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelP.add(table);
					// wait(500);
				}
				if (t.getDESCRIPTION().equals("HP")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelHP.add(table);
					// wait(500);
				}
				if (t.getDESCRIPTION().equals("A")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelA.add(table);
				}
				if (t.getDESCRIPTION().equals("B")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelB.add(table);
				}
				if (t.getDESCRIPTION().equals("C")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelC.add(table);
				}
				if (t.getDESCRIPTION().equals("D")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelD.add(table);
				}
				if (t.getDESCRIPTION().equals("E")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelE.add(table);
				}
				if (t.getDESCRIPTION().equals("V")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelV.add(table);
				}
				if (t.getDESCRIPTION().equals("G")) {
					table = new JButton(t.getTableName());
					table.setName(t.getTableName());
					table.addActionListener(this);
					table.setFont(new Font("Tahoma", Font.PLAIN, 18));
					panelG.add(table);
				}

			}
		} catch (Exception e) {
			System.out.println("Error in Getting Tables" + e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		frame.setTxtSelectedTabel(ae.getActionCommand());
		JButton b = (JButton) ae.getSource();
		if (b.getBackground() != Color.GREEN && b.getBackground() != Color.RED) {
			frame.getcmbWaitor().requestFocus();
			frame.getcmbWaitor().showPopup();
		} else
			frame.getCategoryText().requestFocus();

	}

	public void refresh() {
		loadTables();

		setRed();
		setGreen();
		// removeColor();
	}

	public void setGreen() {
		try {
			// for green color
			// get tables from TempTransaction
			Iterator<String> it = CommonLogic.getRunningTable().iterator();
			String table;
			while (it.hasNext()) {
				table = it.next();
				// System.out.println("Running Tables=" + table);
				// read all available table buttons
				// read from PanelP
				if (table.startsWith("P") || table.startsWith("p")) {
					for (int i = 0; i < panelP.getComponentCount(); i++) {
						if (table.equals(panelP.getComponent(i).getName())) {
							panelP.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelP.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("H") || table.startsWith("h")) {
					for (int i = 0; i < panelHP.getComponentCount(); i++) {
						if (table.equals(panelHP.getComponent(i).getName())) {
							panelHP.getComponent(i).setBackground(Color.GREEN);

						} else {
							/// System.out.println("Not Running " + panelA.getComponent(i).getName());
						}
					}
				}

				else if (table.startsWith("A") || table.startsWith("a")) {
					for (int i = 0; i < panelA.getComponentCount(); i++) {
						if (table.equals(panelA.getComponent(i).getName())) {
							panelA.getComponent(i).setBackground(Color.GREEN);

						} else {
							/// System.out.println("Not Running " + panelA.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("B") || table.startsWith("b")) {
					for (int i = 0; i < panelB.getComponentCount(); i++) {
						if (table.equals(panelB.getComponent(i).getName())) {
							panelB.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelB.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("E") || table.startsWith("e")) {
					for (int i = 0; i < panelE.getComponentCount(); i++) {
						if (table.equals(panelE.getComponent(i).getName())) {
							panelE.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelE.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("D") || table.startsWith("d")) {
					for (int i = 0; i < panelD.getComponentCount(); i++) {
						if (table.equals(panelD.getComponent(i).getName())) {
							panelD.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelD.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("C") || table.startsWith("c")) {
					for (int i = 0; i < panelC.getComponentCount(); i++) {
						if (table.equals(panelC.getComponent(i).getName())) {
							panelC.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelC.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("V") || table.startsWith("v")) {
					for (int i = 0; i < panelV.getComponentCount(); i++) {
						if (table.equals(panelV.getComponent(i).getName())) {
							panelV.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelV.getComponent(i).getName());
						}
					}
				} else {
					for (int i = 0; i < panelG.getComponentCount(); i++) {
						if (table.equals(panelG.getComponent(i).getName())) {
							panelG.getComponent(i).setBackground(Color.GREEN);

						} else {
							// System.out.println("Not Running " + panelG.getComponent(i).getName());
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public void setRed() {
		try {
			Iterator<String> it = CommonLogic.getRunningTableFromBill().iterator();
			String table;
			while (it.hasNext()) {
				table = it.next();
				if (table.startsWith("P") || table.startsWith("p")) {
					for (int i = 0; i < panelP.getComponentCount(); i++) {
						if (table.equals(panelP.getComponent(i).getName())) {
							panelP.getComponent(i).setBackground(Color.RED);

						} else {
							// System.out.println("Not Running " + panelP.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("H") || table.startsWith("h")) {
					for (int i = 0; i < panelHP.getComponentCount(); i++) {
						if (table.equals(panelHP.getComponent(i).getName()))
							panelHP.getComponent(i).setBackground(Color.RED);
						else {
							/// System.out.println("Not Running " + panelA.getComponent(i).getName());
						}
					}
				}

				else if (table.startsWith("A") || table.startsWith("a")) {
					for (int i = 0; i < panelA.getComponentCount(); i++) {
						if (table.equals(panelA.getComponent(i).getName()))
							panelA.getComponent(i).setBackground(Color.RED);
						else {
							/// System.out.println("Not Running " + panelA.getComponent(i).getName());
						}
					}
				}

				else if (table.startsWith("B") || table.startsWith("b")) {
					for (int i = 0; i < panelB.getComponentCount(); i++) {
						if (table.equals(panelB.getComponent(i).getName()))
							panelB.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelB.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("E") || table.startsWith("e")) {
					for (int i = 0; i < panelE.getComponentCount(); i++) {
						if (table.equals(panelE.getComponent(i).getName()))
							panelE.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelE.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("D") || table.startsWith("d")) {
					for (int i = 0; i < panelD.getComponentCount(); i++) {
						if (table.equals(panelD.getComponent(i).getName()))
							panelD.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelD.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("C") || table.startsWith("c")) {
					for (int i = 0; i < panelC.getComponentCount(); i++) {
						if (table.equals(panelC.getComponent(i).getName()))
							panelC.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelC.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("V") || table.startsWith("v")) {
					for (int i = 0; i < panelV.getComponentCount(); i++) {
						if (table.equals(panelV.getComponent(i).getName()))
							panelV.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelV.getComponent(i).getName());
						}
					}
				} else {
					for (int i = 0; i < panelG.getComponentCount(); i++) {
						if (table.equals(panelG.getComponent(i).getName()))
							panelG.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelG.getComponent(i).getName());
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in setting red " + e.getMessage());
		}
	}

	public void removeColor() {
		try {
			List<String> list1 = CommonLogic.getRunningTable();
			List<String> list2 = CommonLogic.getRunningTableFromBill();
			list1.addAll(list2);
			Iterator<String> it = list1.iterator();
			String table;
			while (it.hasNext()) {
				table = it.next();
				if (table.startsWith("P") || table.startsWith("p")) {
					for (int i = 0; i < panelP.getComponentCount(); i++) {
						if (!table.equals(panelP.getComponent(i).getName())) {
							panelP.getComponent(i).setBackground(btnRefreash.getBackground());

						} else {
							// System.out.println("Not Running " + panelP.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("H") || table.startsWith("h")) {
					for (int i = 0; i < panelHP.getComponentCount(); i++) {
						if (!table.equals(panelHP.getComponent(i).getName()))
							panelHP.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							/// System.out.println("Not Running " + panelA.getComponent(i).getName());
						}
					}
				}

				else if (table.startsWith("A") || table.startsWith("a")) {
					for (int i = 0; i < panelA.getComponentCount(); i++) {
						if (!table.equals(panelA.getComponent(i).getName()))
							panelA.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							/// System.out.println("Not Running " + panelA.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("B") || table.startsWith("b")) {
					for (int i = 0; i < panelB.getComponentCount(); i++) {
						if (!table.equals(panelB.getComponent(i).getName()))
							panelB.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							// System.out.println("Not Running " + panelB.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("E") || table.startsWith("e")) {
					for (int i = 0; i < panelE.getComponentCount(); i++) {
						if (!table.equals(panelE.getComponent(i).getName()))
							panelE.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							// System.out.println("Not Running " + panelE.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("D") || table.startsWith("d")) {
					for (int i = 0; i < panelD.getComponentCount(); i++) {
						if (!table.equals(panelD.getComponent(i).getName()))
							panelD.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							// System.out.println("Not Running " + panelD.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("C") || table.startsWith("c")) {
					for (int i = 0; i < panelC.getComponentCount(); i++) {
						if (!table.equals(panelC.getComponent(i).getName()))
							panelC.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							// System.out.println("Not Running " + panelC.getComponent(i).getName());
						}
					}
				} else if (table.startsWith("V") || table.startsWith("v")) {
					for (int i = 0; i < panelV.getComponentCount(); i++) {
						if (!table.equals(panelV.getComponent(i).getName()))
							panelV.getComponent(i).setBackground(btnRefreash.getBackground());
						else {
							// System.out.println("Not Running " + panelV.getComponent(i).getName());
						}
					}
				} else {
					for (int i = 0; i < panelG.getComponentCount(); i++) {
						if (table.equals(panelG.getComponent(i).getName()))
							panelG.getComponent(i).setBackground(Color.RED);
						else {
							// System.out.println("Not Running " + panelG.getComponent(i).getName());
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Removing Color " + e.getMessage());

		}
	}
}

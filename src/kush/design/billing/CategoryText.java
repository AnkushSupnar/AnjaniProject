package kush.design.billing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ankush.CommonLogic;



public class CategoryText extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPopupMenu pop;
	private List<String> list;
	private JList<String> jList;
	private DefaultListModel<String> model;
	private Font font = CommonLogic.font;
	private JScrollPane scroll;
	public CategoryText(JTextField txt)
	{
		pop = new JPopupMenu();
		list = CommonLogic.getCategory();
		model = new DefaultListModel<>();
		jList = new JList<>(model);
		jList.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					if (jList.getSelectedIndex() != -1) {
						setText("" + jList.getSelectedValue());
						// popup.hide();
						pop.setVisible(false);
					}
				}
			}
		});
		jList.setFont(font);
		scroll = new JScrollPane();
		scroll.setViewportView(jList);
		pop.setLayout(new BorderLayout());
		setFont(font);
		setColumns(10);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// catlistmodel.clear();
				// catlistmodel.removeAllElements();
				findItem("" + getText());
				if (!list.isEmpty()) {
					pop.add(scroll); // your component
					pop.setPopupSize(txt.getWidth(), 300);
					pop.show(txt, 0, txt.getHeight());
					requestFocus();
				}

				char c = e.getKeyChar();

				if (c == KeyEvent.VK_ENTER) {

					jList.setSelectedIndex(0);
					jList.requestFocus();
				}
			}

		});
	}
	void findItem(String find) {
		model.removeAllElements();

		try {
			for (int i = 0; i < list.size(); i++) {
				if (((String) list.get(i)).startsWith(find)) {

					model.addElement((String) list.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}
}

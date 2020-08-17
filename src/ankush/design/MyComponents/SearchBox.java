package ankush.design.MyComponents;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchBox extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6603611275191008005L;
	private DefaultListModel<String> model;
	private Font font;
	private JList<String> jlist;
	private JPopupMenu popup;
	private JScrollPane scroll;
	private List<String> list;

	public SearchBox(List<String> namelist) {
		list = namelist;
		font = new Font("Tahoma", Font.PLAIN, 15);
		model = new DefaultListModel<>();
		jlist = new JList<>(model);
		jlist.setFont(font);
		popup = new JPopupMenu();
		popup.setLayout(new BorderLayout());

		scroll = new JScrollPane();
		scroll.setViewportView(jlist);

		addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent ke) {
				findItem(getText());
				if (!list.isEmpty()) {
					popup.add(scroll); // your component
					popup.setPopupSize(getWidth(), 300);
					setComponentPopupMenu(popup);
					popup.show();
					requestFocus();
				}
				char c = ke.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					jlist.requestFocus();
					jlist.setSelectedIndex(0);
				}
			}
		});
		setFont(font);
	}

	List<String> getList(String query) {
		List<String> list = new ArrayList<String>();
		// ResultSet rs = CommonMethods.selectQuery(query);

		return list;
	}

	void findItem(String find) {
		model.removeAllElements();

		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).startsWith(find)) {

					model.addElement(list.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}
	}

}

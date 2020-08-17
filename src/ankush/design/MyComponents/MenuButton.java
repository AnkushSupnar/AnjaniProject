package ankush.design.MyComponents;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MenuButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6122771005052194783L;

	public MenuButton(String text) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setFont(new Font("Kiran", Font.PLAIN, 25));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setFont(new Font("Kiran", Font.PLAIN, 30));
			}
		});
		setText(text);
		setFont(new Font("Kiran", Font.PLAIN, 30));
	}
}

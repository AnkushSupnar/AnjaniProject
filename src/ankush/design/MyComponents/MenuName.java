package ankush.design.MyComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class MenuName extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuName(String text) {
		setText(text);
		setFont(new Font("Kiran", Font.BOLD, 25));
		setForeground(new Color(253, 245, 230));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setFont(new Font("Kiran", Font.BOLD, 20));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setFont(new Font("Kiran", Font.BOLD, 25));
			}
		});

	}
}

package ankush.design.MyComponents;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberText extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8958102758409988258L;

	public NumberText() {
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}

			}
		});

	}

}

package ankush.design.MyComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class WhiteLabel extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3299346390267233837L;

	public WhiteLabel(String name) {
		setFont(new Font("Kiran", Font.BOLD, 20));
		setForeground(Color.WHITE);
		setText(name);
	}
	

}

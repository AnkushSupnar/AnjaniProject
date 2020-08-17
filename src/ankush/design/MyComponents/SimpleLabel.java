package ankush.design.MyComponents;

import java.awt.Font;

import javax.swing.JLabel;

public class SimpleLabel extends JLabel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1636410519227061013L;

	public SimpleLabel(String name)
	{
		setFont(new Font("Tahoma", Font.PLAIN, 16));
		setText(name);
	}

}

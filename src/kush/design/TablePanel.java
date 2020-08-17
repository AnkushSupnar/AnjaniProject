package kush.design;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class TablePanel extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TablePanel() {
		setBackground(new Color(153, 204, 255));
		setBorder(new TitledBorder(null, "Select Table", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setLayout(null);
	}
}

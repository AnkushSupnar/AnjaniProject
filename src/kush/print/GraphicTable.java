package kush.print;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
  
public class GraphicTable extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 148474556302058280L;
	String[] msgs;
    final int
        ROWS =  3,
        COLS =  2,
        PAD  = 10;
  
    public GraphicTable()
    {
        msgs = new String[] { "hello world", "graphic table" };
        setBorder(BorderFactory.createLoweredBevelBorder());
    }
  
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        Insets insets = getInsets();    // border info
        double xInc = (w - insets.left - insets.right - 2*PAD)/COLS;
        double yInc = (h - insets.top - insets.bottom - 2*PAD)/ROWS;
        g2.setPaint(Color.blue);
        // vertical lines
        double x1 = insets.left + PAD, y1 = insets.top + PAD,
               y2 = h - insets.bottom - PAD, x2;
        for(int j = 0; j <= COLS; j++)
        {
            g2.draw(new Line2D.Double(x1, y1, x1, y2));
            x1 += xInc;
        }
        // horizontal lines
        x1 = insets.left + PAD; x2 = w - insets.right - PAD;
        for(int j = 0; j <= ROWS; j++)
        {
            g2.draw(new Line2D.Double(x1, y1, x2, y1));
            y1 += yInc;
        }
        // try a couple of cell strings
        g2.setPaint(Color.red);
        Font font = g2.getFont().deriveFont(18f);
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        x1 = 0;
        for(int j = 0; j < msgs.length; j++)
        {
            float width = (float)font.getStringBounds(msgs[j], frc).getWidth();
            LineMetrics lm = font.getLineMetrics(msgs[j], frc);
            float ascent = lm.getAscent(), descent = lm.getDescent();
            float sx = (float)(insets.left + PAD + x1 + (xInc - width)/2);
            float sy = (float)(insets.top + PAD + (yInc + ascent)/2 - descent);
            g2.drawString(msgs[j], sx, sy);
            x1 += xInc;
        }
    }
  
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new GraphicTable());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
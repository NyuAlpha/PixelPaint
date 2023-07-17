package graphics;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;



public class ToolButton extends JButton {

	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	
	public ToolButton(Icon icon) {
		super(icon);
		setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
		//setMaximumSize(new Dimension(20,20));
		//setMinimumSize(new Dimension(20,20));
	}
	
}

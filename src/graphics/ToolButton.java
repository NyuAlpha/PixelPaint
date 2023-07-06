package graphics;

import java.awt.Dimension;

import javax.swing.JButton;



public class ToolButton extends JButton {

	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	
	public ToolButton(String text) {
		super(text);
		//setPreferredSize(new Dimension(20,20));
		//setMaximumSize(new Dimension(20,20));
		//setMinimumSize(new Dimension(20,20));
	}
	
}

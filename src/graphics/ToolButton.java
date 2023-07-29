package graphics;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

import tools.Tool;


//This class represent a button from the painting toolbox
public class ToolButton extends JButton {

	
	public ToolButton(Icon icon ,  Tool tool , Canvas canvas) {
		super(icon);
		//change the tool on the canvas
		addActionListener( e -> canvas.setToolUsed(tool));
	}
	
}

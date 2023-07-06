package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tools.Tool;

//Singelton class, application canvas
public class Canvas extends JPanel{

	private Canvas canvas;
	private Tool toolUsed;
	private Point point;
	private BufferedImage image;

	
	public Canvas(BufferedImage image) {
		point = null;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.image = image;
	}
	
	public void setToolUsed(Tool toolUsed) {
		this.toolUsed = toolUsed;
	}
	
	
	//Draw graphics with blue background
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = image.createGraphics();
		if(toolUsed != null && point != null) 
			toolUsed.use(g2, point);

		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
	}
	
	
	public void setPoint(Point p) {
		point = p;
	}

	
	private void draw() {
		
	}
}

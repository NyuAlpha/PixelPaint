package tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MeshLayer extends JPanel{
	
	private Dimension dimensions;
	private boolean enabled;

	public MeshLayer(Dimension dimensions) {
		this.dimensions = dimensions;
		enabled = true;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.0f));
		g2.drawRect(0, 0, dimensions.width , dimensions.height);
		
		if(!enabled) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.3f));
	        g2.setColor(Color.BLACK);
	        for (int y = 0; y < dimensions.height; y += 10) {
	            g2.drawLine(0, y, dimensions.width, y);
	        }

	        // Dibujar líneas verticales
	        for (int x = 0; x < dimensions.width; x += 10) {
	            g2.drawLine(x, 0, x, dimensions.height);
	        }
		}
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		repaint();
	}
	
}

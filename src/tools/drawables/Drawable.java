package tools.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


//This interface is useful to draw shapes and strokes stored within the objects that implement it
public interface Drawable {

	//Draws the shape or stroke stored within the instance
	public abstract void drawAll(BufferedImage image);
	
	
}

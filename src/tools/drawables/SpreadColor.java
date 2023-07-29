package tools.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class SpreadColor implements Drawable {
	
	private int newColor;
	private LinkedList <Point> pixels;
	private int clickedColor;
	
	public SpreadColor() {
		pixels =  new LinkedList<>();
	}

	
	//paints only the modified pixels 
	public void drawAll(BufferedImage image) {
		
		for (Point p : pixels ) {
			image.setRGB(p.x, p.y, newColor);
		}
	}
	
	
	

	public void draw(BufferedImage image , Point point, int newColor, int clickedColor) {
		
		this.newColor = newColor;
		this.clickedColor = clickedColor;
		spreadColor(point.x, point.y, image);
	}
	
	
	
	
    public void spreadColor(int startX, int startY, BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Queue<Point> queue = new LinkedList<>();

        if (startX >= 0 && startX < width && startY >= 0 && startY < height) {
            clickedColor = image.getRGB(startX, startY);

            if (clickedColor != newColor) {
                queue.add(new Point(startX, startY));
            }
        }

        while (!queue.isEmpty()) {
            Point pixel = queue.poll();
            int x = pixel.x;
            int y = pixel.y;

            if (x >= 0 && x < width && y >= 0 && y < height && image.getRGB(x, y) == clickedColor) {
                image.setRGB(x, y, newColor);
                pixels.add(pixel);
                // Agregar los píxeles vecinos a la cola
                queue.add(new Point(x, y - 1)); // UP
                queue.add(new Point(x, y + 1)); // DOWN
                queue.add(new Point(x - 1, y)); // LEFT
                queue.add(new Point(x + 1, y)); // RIGHT
            }
        }
    }

}

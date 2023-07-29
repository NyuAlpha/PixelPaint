package tools.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Stroke implements Drawable {

	private LinkedList<Point> points; //Guarda todos los puntos a dibujar
	private int indexLastPoints; //Establecerá los puntos desde los que dibujar en el método drawSince
	private int strokeSize;
	private Color color;
	
	public Stroke(int strokeSize , Color color) {
		points = new LinkedList<>();
		indexLastPoints = 0;
		this.strokeSize = strokeSize;
		this.color = color;
	}
	
	/*
	@Override
	public void drawAll(Graphics2D g2) {
		
		for(Point p : points) {
			g2.setPaint(color);
			g2.fillRect(p.x, p.y, strokeSize, strokeSize);
		}
	}*/
	
	@Override
	public void drawAll(BufferedImage image) {
		
		Graphics2D g2 = image.createGraphics();
		for(Point p : points) {
			g2.setPaint(color);
			g2.fillRect(p.x, p.y, strokeSize, strokeSize);
		}
	}
	
	
	//Dibuja solo los último añadido
	public void drawLast(Graphics2D g2) {
	    
		for(int i = indexLastPoints ; i < points.size() ; i++) {
			Point p = points.get(i);
			g2.setPaint(color);
			g2.fillRect(p.x, p.y, strokeSize, strokeSize);
		}
	}

	public void addPoint(Point p) {
		
		if(!points.isEmpty()) {

			//Accede al último punto
			Point prePoint = points.get(points.size()-1); 

			double distanceX = p.x - prePoint.x; //distance in X
			double distanceY = p.y - prePoint.y; //distance in Y

			int majorDistance = (int)((Math.abs(distanceX) > Math.abs(distanceY))? Math.abs(distanceX) : Math.abs(distanceY));

			if(majorDistance > 0) {
				
				//update the indexLastPoint before add more points
				indexLastPoints = points.size();

				double unitsX = distanceX / majorDistance;
				double unitsY = distanceY / majorDistance;
				double x = prePoint.x;
				double y = prePoint.y;
				for(int i = 0; i < majorDistance ; i++) {
					x +=  unitsX;
					y +=  unitsY;
					Point newPoint = new Point((int)x,(int)y);
					points.add(newPoint);
				}
				points.add(p);
			}

		}
		else {
			points.add(p);
		}
	}
	
}

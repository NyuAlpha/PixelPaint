package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.AppModel;

public class Pencil extends Tool {

	public Pencil(Color color,AppModel appModel) {
		super(appModel);
		this.color = color;
	}
	
	@Override
	public void use(Graphics2D g2 , Point point) {
		int stroke = appModel.getStrokeSize();
		g2.setPaint(color);
		g2.fillRect(point.x, point.y, stroke, stroke);
		g2.dispose();
	}

}

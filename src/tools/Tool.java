package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.AppModel;

public abstract class Tool {

	protected Color color;
	protected AppModel appModel;
	
	public Tool(AppModel appModel) {
		this.appModel = appModel;
	}
	
	public abstract void use (Graphics2D g, Point point);
	
	
	public void setColor(Color color) {
		this.color = color;
	}

}

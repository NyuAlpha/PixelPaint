package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.AppModel;
import tools.drawables.Drawable;

public abstract class Tool {

	protected AppModel appModel;
	
	public Tool(AppModel appModel) {
		this.appModel = appModel;
	}
	
	public abstract void use (BufferedImage image, Point point);
	public abstract Drawable createDrawable();

	public abstract void deleteDrawable();
}

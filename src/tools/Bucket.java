package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import model.AppModel;
import tools.drawables.Drawable;
import tools.drawables.SpreadColor;
import tools.drawables.Stroke;

public class Bucket extends Tool {

	private Drawable drawable;
	
	public Bucket(AppModel appModel) {
		super(appModel);
	}

	@Override
	public void use(BufferedImage image, Point point) {
		
		int clickedColor = image.getRGB(point.x, point.y);
		int newColor = appModel.getSelectedColor().getRGB();
		((SpreadColor)drawable).draw(image, point, newColor, clickedColor);
	}
	
	


	@Override
	public Drawable createDrawable() {
	
		drawable = new SpreadColor();
		return drawable;
	}

	@Override
	public void deleteDrawable() {
		// TODO Auto-generated method stub

	}

}

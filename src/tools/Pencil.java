package tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.AppModel;
import tools.drawables.Drawable;
import tools.drawables.Stroke;

public class Pencil extends Tool {

	
	private Drawable drawable;
	
	public Pencil(AppModel appModel) {
		super(appModel);
	}
	
	@Override
	public void use(BufferedImage image , Point point) {
		Graphics2D g2 = image.createGraphics();
		((Stroke)drawable).addPoint(point);
		((Stroke)drawable).drawLast(g2);
	}
	
	public Drawable createDrawable() {
		int strokeSize = appModel.getStrokeSize();		
		Color color  = appModel.getSelectedColor();
		drawable = new Stroke(strokeSize, color);
		return drawable;
	}

	@Override
	public void deleteDrawable() {
		drawable = null;	
	}

}

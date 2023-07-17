package model;

import java.awt.Color;

public class AppModel {

	
	private int strokeSize;
	public static final int MAX_STROKE_SIZE = 20;
	public static final int MIN_STROKE_SIZE = 1;
	private Color selectedColor;
	public static final int MIN_RGB_INT = 0;
	public static final int MAX_RGB_INT = 255;
	

	public AppModel(){
		strokeSize = MIN_STROKE_SIZE;
		selectedColor = Color.BLACK;
	}

	public int getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(int strokeSize) {
		this.strokeSize = strokeSize;
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}
	
	
	
	
}

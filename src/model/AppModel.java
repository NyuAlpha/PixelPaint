package model;

public class AppModel {

	
	private int strokeSize;
	public static final int MAX_STROKE_SIZE = 20;
	public static final int MIN_STROKE_SIZE = 1;

	public AppModel(){
		strokeSize = MIN_STROKE_SIZE;
	}

	public int getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(int strokeSize) {
		if(strokeSize > 20) strokeSize = 20;
		this.strokeSize = strokeSize;
	}
	
	
	
	
}

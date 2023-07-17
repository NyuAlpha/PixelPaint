package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import tools.Tool;
import tools.drawables.Drawable;

public class Canvas extends JPanel{
	
	private Tool toolUsed;
	private Point point;
	private BufferedImage image;
	private BufferedImage imageCopy;
	
	
	private Drawable currentDrawable;
	
	//These variables store drawables for undo and redo functions
	private Deque <Drawable> undoDrawables;
	private Stack <Drawable> redoDrawables;
	private static final int MAX_UNDO_DRAWABLES = 10;
	
	//Control variable of the paintComponent method
	private boolean repaint;
	
	
	
	
	
	public Canvas(BufferedImage image) {
		
		//there is still no point
		point = null;
		
		//"two copies are created"
		this.image = image;
		imageCopy = copyImage(image);
		
		undoDrawables  = new LinkedList<>();
		redoDrawables = new Stack();
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	
	
	
	//change the used tool.
	public void setToolUsed(Tool toolUsed) {
		this.toolUsed = toolUsed;
	}
	
	
	
	
	//undo the last drawable stored  
	public void undo() {
		if(!undoDrawables.isEmpty()) {
			Drawable deleteDrawable = undoDrawables.removeLast();
			redoDrawables.push(deleteDrawable);
			repaint = true;
			imageCopy = copyImage(image);
			repaint();		
		}
	}
	
	//redo the last lost drawable
	public void redo() {
		if(!redoDrawables.isEmpty()) {
			Drawable returnedDrawable = redoDrawables.pop();
			undoDrawables.addLast(returnedDrawable);;
			repaint = true;
			imageCopy = copyImage(image);
			repaint();		
		}
	}
	
	
	
	
	//Draw graphics
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D imageG2 =  imageCopy.createGraphics();
		
		//draw with tools
		if(point != null && !repaint) 
			toolUsed.use(imageG2, point);
		
		//draw everything saved in the drawable deque
		else if (repaint) {
			repaint = false;
			for(Drawable d : undoDrawables) {
				d.drawAll(imageG2);
			}
		}
		//Draw the image in the canvas
		g.drawImage(imageCopy, 0, 0, null);
		g.dispose();
	}
	
	
	
	
	
	
	//This method is called when the mouse is clicked over the canvas
	public void clickedPoint(Point p) {
		point = p;
		currentDrawable = toolUsed.createDrawable();
		redoDrawables = new Stack();
	}
	
	
	
	
	//This method is called when the mouse is released
	public void releasedPoint() {
		point = null;
		//when the queue is full, the oldest drawable is removed from the queue and drawn to the original image
		if(undoDrawables.size() >= MAX_UNDO_DRAWABLES) {
			Drawable latterDrawable =  undoDrawables.removeFirst();
			latterDrawable.drawAll((Graphics2D)image.getGraphics());
		}
		//the current drawable is released and added to the the undo queue
		undoDrawables.addLast(currentDrawable);
		currentDrawable = null;
		toolUsed.deleteDrawable();
	}
	
	
	
	
	//This method is called when the mouse is being dragged while it's being pressed
	public void changedPoint(Point p) {
		point = p;
	}
	
	
	
	//Return a copy of the original image passed as a parameter
	private BufferedImage copyImage(BufferedImage originalImage) {
		BufferedImage copy = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
		copy.getGraphics().drawImage(originalImage, 0, 0, null);
		return copy;
	}
	
}

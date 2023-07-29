package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import model.AppModel;

//Main application window
public class PixelPaint extends JFrame{
	
	private BufferedImage image;
	private Canvas canvas;
	private MeshLayer meshLayer;
	private AppModel appModel;
	
	public static void main(String[] args) {
		new PixelPaint ();
	}
	
	public PixelPaint() {
		
		//The Canvas and ToolsPanel are created and added to PixelPaint

		appModel = new AppModel();
		
		initVoidImage();
		Dimension dimensionsImg = new Dimension(image.getWidth(),image.getHeight());
		
		canvas = new Canvas(image);
		canvas.setBounds(0,0,dimensionsImg.width, dimensionsImg.height);
		
		meshLayer = new MeshLayer(dimensionsImg);
		meshLayer.setOpaque(false);
		meshLayer.setBounds(0,0,dimensionsImg.width, dimensionsImg.height);
		
		JLayeredPane centerPanel = new JLayeredPane();
		centerPanel.add(canvas,JLayeredPane.DEFAULT_LAYER);
		centerPanel.add(meshLayer,JLayeredPane.PALETTE_LAYER);
		centerPanel.setPreferredSize(dimensionsImg);
		
		ToolsPanel toolsPanel = new ToolsPanel(canvas,meshLayer,appModel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		this.setContentPane(mainPanel);
		setLayout(new BorderLayout(10,10));
		add(centerPanel,BorderLayout.CENTER);
		add(toolsPanel,BorderLayout.WEST);
		
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		JButton btnUndo = new JButton(new ImageIcon("src\\img\\undo.png"));
		btnUndo.addActionListener( e -> canvas.undo());
		JButton btnRedo = new JButton(new ImageIcon("src\\img\\redo.png"));
		btnRedo.addActionListener( e -> canvas.redo());
		toolbar.add(btnUndo);
		toolbar.add(btnRedo);
		add(toolbar, BorderLayout.NORTH);
		
		
		pack();
		
		MouseAdapter listener = new PainterMouseListener();
		centerPanel.addMouseListener(listener);
		centerPanel.addMouseMotionListener(listener);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		//center on screen
		setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	
	
	
	
	private void initVoidImage() {
		int width = 516;
		int height = 516;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int [] pixels = image.getRGB(0,0,width, height,null,0,width);
		for(int i = 0 ; i < pixels.length ; i++) {
			pixels[i] = Color.LIGHT_GRAY.getRGB();
		}
		image.setRGB(0, 0, width, height, pixels, 0, 0);
	}
	
	
	
	
	private class PainterMouseListener extends MouseAdapter{
		
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	if (e.getButton() == MouseEvent.BUTTON1) {
		    	canvas.clickedPoint(e.getPoint());
		    	repaint();
	    	}
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    	if (e.getButton() == MouseEvent.BUTTON1) {
	    		canvas.releasedPoint();
	    	}
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
	    	//if(e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK){
	    	if((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0) {
	    		canvas.changedPoint(e.getPoint());
	    		repaint();
	    	}
	    }
	}
}



package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import model.AppModel;

public class ColorSelector extends Box {

	//private Color selectedColor;
	private ToolsPanel toolsPanel;
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	private JPanel colorPanel;
	private NumberTextField redField;
	private NumberTextField greenField;
	private NumberTextField blueField;
	private AppModel appModel;
	
	//Control the flow and direction of the JSlider and JTextField to prevent feedback.
	private boolean flowControlEvent;
	
	public ColorSelector(AppModel appModel) {
		
		super(BoxLayout.Y_AXIS);
		this.appModel = appModel;
			
		flowControlEvent = true;
			

		colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension (60,60));
		colorPanel.setBackground(appModel.getSelectedColor());
		JPanel colorFlowPanel = new JPanel();
		colorFlowPanel.add(colorPanel);
		
		
		JPanel colorFieldPanel = new JPanel(new GridLayout(3,2,5,5));
		Color c = appModel.getSelectedColor();
		DocumentListener dl = new  DocumentListener (){

			@Override
			public void insertUpdate(DocumentEvent e) {
				changeColorWithNumberField();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changeColorWithNumberField();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		redField = new NumberTextField(Integer.toString(c.getRed()), 2 , AppModel.MIN_RGB_INT, AppModel.MAX_RGB_INT,dl);
		greenField = new NumberTextField(Integer.toString(c.getGreen()), 2, AppModel.MIN_RGB_INT, AppModel.MAX_RGB_INT,dl);
		blueField = new NumberTextField(Integer.toString(c.getBlue()), 2, AppModel.MIN_RGB_INT, AppModel.MAX_RGB_INT,dl);

		colorFieldPanel.add(new JLabel ("Rojo"));
		colorFieldPanel.add(redField);
		colorFieldPanel.add(new JLabel ("Verde"));
		colorFieldPanel.add(greenField);
		colorFieldPanel.add(new JLabel ("Azul"));
		colorFieldPanel.add(blueField);
		colorFlowPanel.add(colorFieldPanel);
		

		redSlider = new JSlider(AppModel.MIN_RGB_INT, AppModel.MAX_RGB_INT,c.getRed());
		redSlider.setPreferredSize(new Dimension (100,20));
		greenSlider = new JSlider(AppModel.MIN_RGB_INT, AppModel.MAX_RGB_INT,c.getGreen());
		greenSlider.setPreferredSize(new Dimension (100,20));
		blueSlider = new JSlider(AppModel.MIN_RGB_INT, AppModel.MAX_RGB_INT,c.getBlue());
		blueSlider.setPreferredSize(new Dimension (100,20));
		
		JPanel redBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		redBar.add(new JLabel ("Rojo"));
		redBar.add(redSlider);
		
		JPanel greenBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		greenBar.add(new JLabel ("Verde"));
		greenBar.add(greenSlider);
		
		JPanel blueBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		blueBar.add(new JLabel ("Azul"));
		blueBar.add(blueSlider);
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlePanel.add(new JLabel ("Selección de color"));
		
		add(titlePanel);
		add(redBar);
		add(greenBar);
		add(blueBar);
		add(colorFlowPanel);
			
		redSlider.addChangeListener(e -> changeColorWithSlider());
		greenSlider.addChangeListener(e -> changeColorWithSlider());
		blueSlider.addChangeListener(e -> changeColorWithSlider());
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void changeColor(Color newColor) {
		
		colorPanel.setBackground(newColor);
		appModel.setSelectedColor(newColor);

	}
	
	private void changeColorWithSlider() {
		if(flowControlEvent) {
			flowControlEvent = false;
			
			changeColor(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));			
			redField.setText(Integer.toString(redSlider.getValue()));
			greenField.setText(Integer.toString(greenSlider.getValue()));
			blueField.setText(Integer.toString(blueSlider.getValue()));
			
			flowControlEvent = true;
		}
	}

	private void changeColorWithNumberField() {
		if(flowControlEvent) {
			flowControlEvent = false;
			int red = redField.getCorrectedValue();
			int green = greenField.getCorrectedValue();
			int blue = blueField.getCorrectedValue();
			changeColor(new Color(red, green, blue));
			
			redSlider.setValue(red);
			greenSlider.setValue(green);
			blueSlider.setValue(blue);
			
			flowControlEvent = true;
		}		
	}
}

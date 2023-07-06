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

public class ColorSelector extends Box {

	private int red,green,blue;
	private Color selectedColor;
	private ToolsPanel toolsPanel;
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	private JPanel colorPanel;
	private JTextField redField;
	private JTextField greenField;
	private JTextField blueField;
	
	 //Control the flow and direction of the JSlider and JTextField to prevent feedback.
	private boolean fieldsOn;
	private boolean changeFields;
	
	public ColorSelector(ToolsPanel toolsPanel) {
		
		super(BoxLayout.Y_AXIS);

		this.toolsPanel = toolsPanel;
		this.selectedColor = toolsPanel.getSelectedColor();
			
		fieldsOn = true;
		changeFields = true;
			
		red = selectedColor.getRed();
		green = selectedColor.getGreen();
		blue = selectedColor.getBlue();
		colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension (60,60));
		colorPanel.setBackground(selectedColor);
		JPanel colorFlowPanel = new JPanel();
		colorFlowPanel.add(colorPanel);
		
		
		JPanel colorFieldPanel = new JPanel(new GridLayout(3,2,5,5));
		redField = new JTextField(Integer.toString(red), 2);
		redField.getDocument().addDocumentListener(new ColorDocumentListener());
		greenField = new JTextField(Integer.toString(green), 2);
		greenField.getDocument().addDocumentListener(new ColorDocumentListener());
		blueField = new JTextField(Integer.toString(blue), 2);
		blueField.getDocument().addDocumentListener(new ColorDocumentListener());
		colorFieldPanel.add(new JLabel ("Rojo"));
		colorFieldPanel.add(redField);
		colorFieldPanel.add(new JLabel ("Verde"));
		colorFieldPanel.add(greenField);
		colorFieldPanel.add(new JLabel ("Azul"));
		colorFieldPanel.add(blueField);
		colorFlowPanel.add(colorFieldPanel);
		
		
		int min = 0;
		int max = 255;
		redSlider = new JSlider(min,max,red);
		redSlider.setPreferredSize(new Dimension (100,20));
		greenSlider = new JSlider(min,max,green);
		greenSlider.setPreferredSize(new Dimension (100,20));
		blueSlider = new JSlider(min,max,blue);
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
		
		
		ColorChangeListener colorChangeListener = new ColorChangeListener();		
		redSlider.addChangeListener(colorChangeListener);
		greenSlider.addChangeListener(colorChangeListener);
		blueSlider.addChangeListener(colorChangeListener);
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void changeColor() {
		
		selectedColor = new Color(red,green,blue);
		colorPanel.setBackground(selectedColor);
		toolsPanel.setSelectedColor(selectedColor);

	}
	
	private void updateColorFields() {
		fieldsOn = false; // Eventos de JField desactivados temporalmente para evitar feedback 
		redField.setText(Integer.toString(red));
		greenField.setText(Integer.toString(green));
		blueField.setText(Integer.toString(blue));
	}
	
	private void uptadeSliders() {
		changeFields = false; //Para que no intente modificar los fields desde slider y evitar feedback
		redSlider.setValue(Integer.parseInt(redField.getText()));
		greenSlider.setValue(Integer.parseInt(greenField.getText()));
		blueSlider.setValue(Integer.parseInt(blueField.getText()));
	}
	
	public class ColorDocumentListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			updateField(e.getDocument());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateField(e.getDocument());
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub	
		}
		
	}
	
	private void updateField (Document d) {
		
		if(fieldsOn) {
			String s = "0";
			try {
				s = d.getText(0,d.getLength());
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			if(!s.isEmpty()) {
				int value = Integer.parseInt(s);
				if(value >= 0 && value < 256 ) {
					uptadeSliders();
				}
			}
		}
	}
	
	
	
	private class ColorChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			
			Object o = e.getSource();
			if(o==redSlider) red  = redSlider.getValue();
			else if(o==greenSlider) green  = greenSlider.getValue();
			else if(o==blueSlider) blue  = blueSlider.getValue();
			
			changeColor();
			
			if(changeFields)   
				updateColorFields();
			
			fieldsOn = true; //Vuelven a activarse los controles de feedback negativo
			changeFields = true;
		}			
	}
}

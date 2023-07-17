package graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import javax.swing.*;
import javax.swing.text.*;

import model.AppModel;


public class ResizeStrokePanel extends Box {

	private JSlider resizerSlider;
	private NumberTextField resizerTextField;
	private AppModel appModel;
	private boolean flowControlEvent;
	
	
	public ResizeStrokePanel(AppModel appModel) {
		super(BoxLayout.Y_AXIS);
		this.appModel = appModel;
		
		flowControlEvent = true;
		
		resizerSlider = new JSlider(AppModel.MIN_STROKE_SIZE, AppModel.MAX_STROKE_SIZE, appModel.getStrokeSize());
		resizerSlider.setPreferredSize(new Dimension (100,20));
		resizerSlider.addChangeListener(e -> {resizeWithSliderEvent();});
		
		
		DocumentListener dl = new  DocumentListener (){

			@Override
			public void insertUpdate(DocumentEvent e) {
				resizeWithTextFieldEvent();	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				resizeWithTextFieldEvent();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		resizerTextField = new NumberTextField(Integer.toString(appModel.getStrokeSize())
				,2,AppModel.MIN_STROKE_SIZE, AppModel.MAX_STROKE_SIZE, dl);	
		
		
		
		add(resizerSlider);
		
		JPanel flowPanelText = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flowPanelText.add(new JLabel("Grosor"));
		flowPanelText.add(resizerTextField);
		
		add(flowPanelText);
	}

	private void resizeWithSliderEvent() {
		
		if(flowControlEvent) {
			flowControlEvent = false;
			appModel.setStrokeSize(resizerSlider.getValue());
			resizerTextField.setText(Integer.toString(resizerSlider.getValue()));
			flowControlEvent = true;
		}
	}

	
	private void resizeWithTextFieldEvent() {

		if(flowControlEvent) {
			flowControlEvent = false;
			int stroke = resizerTextField.getCorrectedValue();
			
			appModel.setStrokeSize(stroke);
			resizerSlider.setValue(stroke);
			
			flowControlEvent = true;
		}
	}


	

}

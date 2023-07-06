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
	private JTextField resizerTextField;
	private AppModel appModel;
	private boolean flowControlEvent;
	private StrokeSizeDocumentListener strokeSizeDocumentListener;
	private static int temporal = 0;
	
	public ResizeStrokePanel(AppModel appModel) {
		super(BoxLayout.Y_AXIS);
		this.appModel = appModel;
		
		flowControlEvent = true;
		
		resizerSlider = new JSlider(AppModel.MIN_STROKE_SIZE, AppModel.MAX_STROKE_SIZE, appModel.getStrokeSize());
		resizerSlider.setPreferredSize(new Dimension (100,20));
		resizerSlider.addChangeListener(e -> {resizeWithSliderEvent();});
		
		
		resizerTextField = new JTextField(Integer.toString(appModel.getStrokeSize()),2);	
		((AbstractDocument)resizerTextField.getDocument()).setDocumentFilter(new NumberOnlyDocumentFilter());
		StrokeSizeDocumentListener strokeSizeDocumentListener = new StrokeSizeDocumentListener();
		resizerTextField.getDocument().addDocumentListener(strokeSizeDocumentListener);
		
		
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
			
			int stroke = (resizerTextField.getText().isEmpty()  ||  Integer.parseInt(resizerTextField.getText()) < AppModel.MIN_STROKE_SIZE)? 
					AppModel.MIN_STROKE_SIZE : Integer.parseInt(resizerTextField.getText());
			
			if(stroke > AppModel.MAX_STROKE_SIZE ) stroke = AppModel.MAX_STROKE_SIZE;
			
			appModel.setStrokeSize(stroke);
			updateTextField(stroke);
		}
	}
	
	private void updateTextField(final int stroke) {
		SwingUtilities.invokeLater( () -> {
			flowControlEvent = false;
			if( !resizerTextField.getText().isEmpty()) 
				resizerTextField.setText(Integer.toString(stroke));
			resizerSlider.setValue(stroke);
			flowControlEvent = true;
		});
	}

	
	private class  StrokeSizeDocumentListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			System.out.println(++temporal);
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
		
	}
	


	private class NumberOnlyDocumentFilter extends DocumentFilter {
	    @Override
	    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	        StringBuilder sb = new StringBuilder();
	        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
	        sb.insert(offset, string);

	        if (isNumber(sb.toString())) {
	        	//if(Integer.parseInt(sb.toString()) >= AppModel.MIN_STROKE_SIZE)
	        		super.insertString(fb, offset, string, attr);
	        }
	    }

	    @Override
	    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	        StringBuilder sb = new StringBuilder();
	        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
	        sb.replace(offset, offset + length, text);

	        if (isNumber(sb.toString())) {
	        	//if(Integer.parseInt(sb.toString()) >= AppModel.MIN_STROKE_SIZE)
	        		super.replace(fb, offset, length, text, attrs);
	        }
	    }
	    
	    /*
	    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
	    	StringBuilder sb = new StringBuilder();
	    	sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
	    	sb.delete(offset,offset + length);
	    	if (!(sb.toString().isEmpty())) {
	            super.remove(fb, offset, length);
	        }
	    }*/

	    private boolean isNumber(String text) {
	        try {
	            Integer.parseInt(text);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	}

	
	private class numberTextField extends JTextField{
		
	}
	
}

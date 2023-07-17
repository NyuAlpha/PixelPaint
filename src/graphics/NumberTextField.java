package graphics;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;



public class NumberTextField extends JTextField{
	
	private final int MIN;
	private final int MAX;
	private DocumentListener documentListener;

	public NumberTextField(String value, int col , int min , int max , DocumentListener documentListener) {
		
		super(value, col);
		MIN = min;
		MAX = max;
		((AbstractDocument)getDocument()).setDocumentFilter(new NumberOnlyDocumentFilter());
		this.documentListener = documentListener;
		getDocument().addDocumentListener(documentListener);
	}
	
	
	public int getCorrectedValue() {
		int value = MIN;
		if(!getText().isEmpty()) {
			value = Integer.parseInt(getText());
			if(value < MIN) value = MIN;
			else if(value > MAX) value = MAX;
		}
		changeValueOutEvent(value);
		return value;
	}
	
	private void changeValueOutEvent(final int value) {
		if(!getText().isEmpty()) {
			SwingUtilities.invokeLater( () -> {
				getDocument().removeDocumentListener(documentListener);
				setText(Integer.toString(value));
				getDocument().addDocumentListener(documentListener);
			});
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
	
}

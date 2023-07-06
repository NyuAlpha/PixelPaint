package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import model.AppModel;
import tools.*;

public class ToolsPanel extends JPanel{
	
	
	private Tool[] toolbox;
	private Tool toolUsed;
	private Color selectedColor;
	private AppModel appModel;

	public ToolsPanel(Canvas canvas ,  MeshLayer meshLayer ,  AppModel appModel) {
		
		this.appModel = appModel;
		
		selectedColor = new Color (25,25,25);
		
		toolUsed = new Pencil(selectedColor, appModel);
		toolbox = new Tool[1];
		toolbox[0] = toolUsed;
		canvas.setToolUsed(toolUsed);
		
		
		
		JCheckBox guideLines = new JCheckBox("Lineas guia");
		guideLines.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) meshLayer.setEnabled(false);
				else meshLayer.setEnabled(true);
			}
		});
		
		
		JPanel buttonsPanel = new JPanel();
		
		int row = 5;
		int col = 2;
		int gap = 2;
		buttonsPanel.setLayout(new GridLayout(row,col,gap ,gap));
		
		buttonsPanel.setPreferredSize(new Dimension((col * ToolButton.WIDTH) + ((col+1) * gap),
													(row * ToolButton.HEIGHT) + ((row+1) * gap)));
		buttonsPanel.setMaximumSize(buttonsPanel.getPreferredSize());
		buttonsPanel.setMinimumSize(buttonsPanel.getPreferredSize());
		
		
		buttonsPanel.add(new ToolButton("-->"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));
		buttonsPanel.add(new ToolButton("A"));


		ColorSelector colorSelector = new ColorSelector(this);
		ResizeStrokePanel resizerStrokePanel = new ResizeStrokePanel(appModel);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		// Configura las restricciones de GroupLayout
		//layout.setAutoCreateGaps(true);
		//layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(buttonsPanel)
				.addComponent(guideLines)
				.addComponent(colorSelector)
				.addComponent(resizerStrokePanel)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(buttonsPanel)
				.addGap(10)
				.addComponent(guideLines)
				.addGap(10)
				.addComponent(colorSelector)
				.addGap(10)
				.addComponent(resizerStrokePanel)
				.addGap(10)
				);
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
		
		for(Tool t : toolbox) {
			t.setColor(selectedColor);
		}
	}
	
	
	
}

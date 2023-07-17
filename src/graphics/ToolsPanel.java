package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import model.AppModel;
import tools.*;

public class ToolsPanel extends JPanel{
	
	
	private Tool[] toolbox;
	private Tool toolUsed;
	private AppModel appModel;

	public ToolsPanel(Canvas canvas ,  MeshLayer meshLayer ,  AppModel appModel) {
		
		this.appModel = appModel;
		
		
		toolUsed = new Pencil(appModel);
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
		
		
		JToolBar buttonsPanel = new JToolBar(JToolBar.VERTICAL);		
		buttonsPanel.add(new ToolButton(new ImageIcon("src\\img\\pencil.png")));


		ColorSelector colorSelector = new ColorSelector(appModel);
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
}


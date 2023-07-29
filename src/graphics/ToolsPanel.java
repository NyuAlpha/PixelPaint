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


//This class is the drawing tool box of the application 
public class ToolsPanel extends JPanel{

	public ToolsPanel(Canvas canvas ,  MeshLayer meshLayer ,  AppModel appModel) {
			
		JCheckBox guideLines = new JCheckBox("Lineas guia");
		guideLines.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) meshLayer.setEnabled(false);
				else meshLayer.setEnabled(true);
			}
		});
		
		//Create the tools , the selected tool and all the tool buttons
		JToolBar buttonsPanel = new JToolBar(JToolBar.VERTICAL);
		
		Tool pencil = new Pencil(appModel);
		canvas.setToolUsed(pencil);
		ToolButton pencilButton = new ToolButton(new ImageIcon("src\\img\\pencil.png") , pencil, canvas);
		
		ToolButton bucketButton = new ToolButton(new ImageIcon("src\\img\\paint_bucket.png"), new Bucket(appModel), canvas);
		buttonsPanel.add(pencilButton);
		buttonsPanel.add(bucketButton);
		

		//create the selection tools to modify the drawing features 
		ColorSelector colorSelector = new ColorSelector(appModel);
		ResizeStrokePanel resizerStrokePanel = new ResizeStrokePanel(appModel);

		
		//GroupLayout for this tool panel
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
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


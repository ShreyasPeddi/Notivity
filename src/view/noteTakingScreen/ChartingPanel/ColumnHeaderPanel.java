package view.noteTakingScreen.ChartingPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.AppController;

/*
 * This class creates the column header for the chart note-taking method
 */
public class ColumnHeaderPanel extends JPanel{
	
	//Stores all column headers
	private ArrayList<JTextArea> headerTextArea = new ArrayList<JTextArea>();
	
	//Constructor
	public ColumnHeaderPanel() {
		
		//Set up the panel
		setLayout(new GridLayout(0,4,1,1));
		setBounds(20, 260, 1863, 80);
		this.setBackground(new Color(254,250,224));
		setupHeader();
		
	}

	/*
	 * This method the column header GUI
	 */
	private void setupHeader() {
		
		//Iterate 4 times and add the heading
		for(int index=0;index<4;index++) {
			
			//Create and setup textareas
			JTextArea textArea = new JTextArea("Heading "+(index+1));
			textArea.setBackground(new Color(206,212,218));
			textArea.setFont(AppController.notesFont);
			headerTextArea.add(textArea);
			add(headerTextArea.get(index));
		}
	}

	//GETTERS AND SETTERS
	public ArrayList<JTextArea> getHeaderTextArea() {
		return headerTextArea;
	}

	public void setHeaderTextArea(ArrayList<JTextArea> headerTextArea) {
		this.headerTextArea = headerTextArea;
	}
	
	
}

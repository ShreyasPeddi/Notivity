package view.noteTakingScreen.ChartingPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.AppController;

/*
 * This class creates the charting panel - A table of text areas
 */
public class ChartingPanel extends JPanel{
	
	//Stores text areas which represent a chart
	private ArrayList<JTextArea> notesTextArea = new ArrayList<JTextArea>();

	public ChartingPanel() {
		
		//Grid layout allows to create a chart GUI
		setLayout(new GridLayout(0,4,1,1));
		setBounds(20, 340, 1880, 600);
		this.setBackground(new Color(254,250,224));
		setupTable();
		
	}

	//GETTERS AND SETTERS
		public ArrayList<JTextArea> getNotesTextArea() {
			return notesTextArea;
		}

		public void setNotesTextArea(ArrayList<JTextArea> notesTextArea) {
			this.notesTextArea = notesTextArea;
		}
		
	/*
	 * This method sets up the GUI for the charting screen. It creates a table of text areas
	 */
	private void setupTable() {
		
		//Iterate and add text area to the panel
		for(int index=0;index<16;index++) {
			
			JTextArea textArea = new JTextArea("Note "+(index+1));
			textArea.setBackground(new Color(233,236,239));
			textArea.setFont(AppController.notesFont);
			
			//Save the instance to class data structure of text areas
			notesTextArea.add(textArea);
			
			//Add the text area to the screen
			add(textArea);
		}
	}
	
}

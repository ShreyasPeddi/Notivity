package view.noteTakingScreen;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.AppController;

/*
 * This class creates the GUI for Outline method of note-taking. 
 */
public class OutlinePanel extends JPanel{

	//Fields
	private JTextArea notesTextArea = new JTextArea();
	
	//Constructor
	public OutlinePanel() {

		//Setup panel
		setLayout(null);
		setBounds(0, 260, 1920, 680);
		this.setBackground(new Color(254,250,224));
		setupTextArea();
		
		//Create a scrollable text area
		JScrollPane scrollNotes = new JScrollPane(notesTextArea);
		
		//Set scrolling policies and bounds
		scrollNotes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollNotes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollNotes.setBounds(40,10,1840, 660);
		
		//Add scroll pane to screen
		add(scrollNotes);
	}

	//GETTERS AND SETTERS
	public JTextArea getNotesTextArea() {
		return notesTextArea;
	}

	public void setNotesTextArea(JTextArea notesTextArea) {
		this.notesTextArea = notesTextArea;
	}
	
	//Sets up text area
	private void setupTextArea() {
		notesTextArea.setText("Enter Notes here...");
		notesTextArea.setFont(AppController.notesFont);
		notesTextArea.setBackground(new Color(233,236,239));
	}
	
	
}

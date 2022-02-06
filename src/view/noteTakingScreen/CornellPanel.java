package view.noteTakingScreen;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.AppController;

/*
 * This panel creates the cornell note taking layout.
 * Design inspired from: https://medium.goodnotes.com/the-best-note-taking-methods-for-college-students-451f412e264e
 */
public class CornellPanel extends JPanel{

	//Fields
	private JTextArea cueTextArea = new JTextArea(); //Cue text area: Make any jot notes
	private JTextArea notesTextArea = new JTextArea(); //Notes text area: Makes notes
	private JTextArea summaryTextArea = new JTextArea(); // Summary Text area
	
	//Constructor
	public CornellPanel() {

		//Set the panel
		setLayout(null);
		setBounds(0, 260, 1920, 660);
		this.setBackground(new Color(254,250,224));
		setupTextAreas();
		
		//Make all three text areas scrollable
		JScrollPane scrollCue = new JScrollPane(cueTextArea);
		//Set scrolling policies and bounds
		scrollCue.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollCue.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollCue.setBounds(40, 0, 480, 520);
        
		JScrollPane scrollNotes = new JScrollPane(notesTextArea);
		//Set scrolling policies and bounds
		scrollNotes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollNotes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollNotes.setBounds(scrollCue.getX()+scrollCue.getWidth()+1, scrollCue.getY(), 1360, 520);
        
		JScrollPane scrollSummary = new JScrollPane(summaryTextArea);
		//Set scrolling policies and bounds
		scrollSummary.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollSummary.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSummary.setBounds(scrollCue.getX(), scrollCue.getY()+scrollCue.getHeight()+1, 1840, 140);
	
		//Add each component to the screen
		add(scrollCue);
		add(scrollNotes);
		add(scrollSummary);
		
	}

	//GETTERS AND SETTERS
	public JTextArea getNotesTextArea() {
		return notesTextArea;
	}

	public void setNotesTextArea(JTextArea notesTextArea) {
		this.notesTextArea = notesTextArea;
	}

	public JTextArea getCueTextArea() {
		return cueTextArea;
	}

	public void setCueTextArea(JTextArea cueTextArea) {
		this.cueTextArea = cueTextArea;
	}

	public JTextArea getSummaryTextArea() {
		return summaryTextArea;
	}

	public void setSummaryTextArea(JTextArea summaryTextArea) {
		this.summaryTextArea = summaryTextArea;
	}
	
	/*
	 * This method sets up the cue, notes, and summary text areas
	 */
	private void setupTextAreas() {
		
		//Cue Text Area
		cueTextArea.setText("Cue Column");
		cueTextArea.setFont(AppController.notesFont);
		cueTextArea.setBackground(new Color(222,226,230));
		
		//Notes Text Area
		notesTextArea.setText("Notes Column");
		notesTextArea.setFont(AppController.notesFont);
		notesTextArea.setBackground(new Color(233, 236, 239));
		
		//Summary Text Area
		summaryTextArea.setText("Summary Column");
		summaryTextArea.setFont(AppController.notesFont);
		summaryTextArea.setBackground(new Color(206, 212, 218));
		
	}
	
}

package view.noteTakingScreen;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.AppController;

/*
 * This class creates frontend for the boxing note taking template.
 * Design inspired from https://medium.goodnotes.com/the-best-note-taking-methods-for-college-students-451f412e264e
 */
public class BoxingPanel extends JPanel{

	//Fields
	private JTextArea boxTextArea [] = new JTextArea[6];
	private JScrollPane scrollBox[] = new JScrollPane[6];
	
	//Constructor
	public BoxingPanel() {

		//Setup Panel
		setLayout(null);
		setBounds(0, 260, 1920, 800);
		this.setBackground(new Color(254,250,224));
		
		//Add Text area boxes
		setupBoxTextArea();
		
	}

	/*
	 * This method setups the GUI for Boxing Layout. It adds boxes(textareas) where user can make notes
	 */
	private void setupBoxTextArea() {
		
		//Space distribution
		int x=620;
		int y=0;
		
		//Traverse through each box and add it to the screen
		for(int index=0;index<6;index++) {
			
			//Create and setup a textarea
			boxTextArea[index] = new JTextArea();
			boxTextArea[index].setText("Notes Column");
			boxTextArea[index].setFont(AppController.notesFont);
			boxTextArea[index].setBackground(new Color(233,236,239));
			
			//Add the textarea to the scroll pane
			scrollBox[index] = new JScrollPane(boxTextArea[index]);
			
			//Set scrolling policies and bounds
			scrollBox[index].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollBox[index].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			//If the boxes overflow, add them in the next column
			if((index*x)<1300)
				scrollBox[index].setBounds(40+(index*x), y, 600, 350);
			else {
				y=370;
				scrollBox[index].setBounds(40+((index-3)*x), y, 600, 350);
			}
			
			//Add to screen
			add(scrollBox[index]);
			
		}
		
	}

	//GETTERS AND SETTERS
	public JTextArea[] getBoxTextArea() {
		return boxTextArea;
	}

	public void setBoxTextArea(JTextArea[] boxTextArea) {
		this.boxTextArea = boxTextArea;
	}

	public JScrollPane[] getScrollCue() {
		return scrollBox;
	}

	public void setScrollCue(JScrollPane[] scrollCue) {
		this.scrollBox = scrollCue;
	}

	
}

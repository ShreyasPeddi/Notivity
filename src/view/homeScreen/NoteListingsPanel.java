package view.homeScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class creates the GUI to display list of notes on the home screen
 */
public class NoteListingsPanel extends JPanel {

	// Fields
	private ArrayList<JButton> noteListingButtons = new ArrayList<JButton>(); // Creates a list of buttons to display
																				// notes
	// Constructor
	public NoteListingsPanel() {

		// Panel setup
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setOpaque(false);

	}

	//GETTERS AND SETTERS
		public ArrayList<JButton> getNoteListingButtons() {
			return noteListingButtons;
		}

		public void setNoteListingButtons(ArrayList<JButton> noteListingButtons) {
			this.noteListingButtons = noteListingButtons;
		}
		
	/*
	 * This method takes the button title as parameter. Title represents the note name. 
	 * It creates a button based on the title provided.
	 */
	public void createNewButton(String title) {
		
		// Setup the button
		JButton noteButton = new JButton(title); //Set the provided title
		noteButton.setMaximumSize(new Dimension(HomeFrame.LIST_BUTTON_WIDTH, 60));
		noteButton.setPreferredSize(new Dimension(HomeFrame.LIST_BUTTON_WIDTH, 60));
		noteButton.setBackground(new Color(221,161,94));
		noteButton.setForeground(new Color(40,54,24));
		noteButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		noteButton.setOpaque(true);
		noteListingButtons.add(noteButton);
		
		// Add the button to the panel (scrollable)
		this.add(noteButton);
		
	}
	
	/*
	 * This methods clears the panel by removing all buttons
	 */
	public void removeButtons() {
		
		//Clear only if size is more than 0
		if(noteListingButtons.size()>0) {
			
			//Iterate through list of buttons and remove each button
			for(JButton button:noteListingButtons) {
				
				//Remove component at index 0 update panel
				remove(0);
				validate();
				repaint();
			}
			
			//Reset datastuture which stores all the buttons
			noteListingButtons.removeAll(noteListingButtons);
		}
	}
}

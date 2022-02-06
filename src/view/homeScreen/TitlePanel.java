package view.homeScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AppController;

/*
 * This class creates the title panel consisting of the title, add new note, search bar, user analytics
 */
public class TitlePanel extends JPanel{

	// Fields
	private JButton addNoteButton = new JButton("Add new note"); //Creates new note
	private JButton searchButton = new JButton();	//Search button to search any note
	private JButton profileButton = new JButton();	//User analytics
	private JTextField searchField = new JTextField();	//Search field to enter note name
	private JLabel profileLabel= new JLabel();	//Hover over label
	private JLabel searchLabel= new JLabel();	//Hover over label
	
	//Constructor
	public TitlePanel() {

		//Setup panel
		setLayout(null);
		setBounds(0, 0, 1920, 500);
		setOpaque(false);

		// Setup GUI
		setupAddNoteButton();
		setupProfileButton();
		setupSearchField();
		setupSearchButton();

		// Add GUI components to panel
		add(profileLabel);
		add(searchLabel);
		add(searchField);
		add(addNoteButton);
		add(searchButton);
		add(profileButton);

	}

	// GETTERS AND SETTERS
	public JButton getAddNoteButton() {
		return addNoteButton;
	}

	public void setAddNoteButton(JButton addNoteButton) {
		this.addNoteButton = addNoteButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public void setProfileButton(JButton profileButton) {
		this.profileButton = profileButton;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}
	
	//Sets up add note button to create new note
	private void setupAddNoteButton() {
		addNoteButton.setFont(new Font("Arial", Font.PLAIN, 30));
		addNoteButton.setBackground(Color.BLACK);
		addNoteButton.setForeground(Color.white);
		
		//Implement mouse listener to change color
		addNoteButton.addMouseListener(new MouseAdapter() {
			
			//Change background to cyan
			public void mouseEntered(java.awt.event.MouseEvent event) {
				addNoteButton.setBackground(Color.cyan);
		    	addNoteButton.setForeground(Color.black);
		    }

			//Change back the color to black
		    public void mouseExited(java.awt.event.MouseEvent event) {
		    	addNoteButton.setBackground(Color.BLACK);
		    	addNoteButton.setForeground(Color.white);
		    }
			
		});
		
		//Set location and visibility
		addNoteButton.setOpaque(true);
		addNoteButton.setBounds(230, (this.getHeight() / 2) + 165, 230, 55);
	}

	//Sets up search field to enter note names
	private void setupSearchField() {
		searchField.setBounds(addNoteButton.getX() + addNoteButton.getWidth() + 980, addNoteButton.getY(), 200, 50);
		searchField.setFont(AppController.notesFont);
	}

	// Sets up the search button which displays results of programs
	private void setupSearchButton() {
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setIcon(new ImageIcon(new ImageIcon("images/search.png").getImage().getScaledInstance(50, 50, 0)));
		searchButton.setBounds(searchField.getX() + searchField.getWidth() + 30, searchField.getY(), 50, 50);
		
		//Mouse listener
		//Implement mouse listener to add a label
		searchButton.addMouseListener(new MouseAdapter() {
			
			//If mouse hovers over the button. Add a label on top of the button
			public void mouseEntered(java.awt.event.MouseEvent event) {
				searchLabel.setVisible(true);
		    }

			//If mouse doesn't hover over the button. Remove label
		    public void mouseExited(java.awt.event.MouseEvent event) {
		    	searchLabel.setVisible(false);
		    }
			
		});
		
		searchLabel.setBounds(searchButton.getBounds());
		
		//Set background and opacity
		searchLabel.setBackground(new Color(226,232,206,200));
		searchLabel.setOpaque(true);
		searchLabel.setVisible(false);
		
	}

	//Sets up analytics button
	private void setupProfileButton() {
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setBorderPainted(false);
		profileButton
				.setIcon(new ImageIcon(new ImageIcon("images/profile.png").getImage().getScaledInstance(100, 100, 0)));

		profileButton.setBounds(240, 100, 100, 100);
		
		//Mouse listener
		//Implement mouse listener to add a label
		profileButton.addMouseListener(new MouseAdapter() {
			
			//If mouse hovers over the button. Add a label on top of the button
			public void mouseEntered(java.awt.event.MouseEvent event) {
				profileLabel.setVisible(true);
		    }

			//If mouse doesn't hover over the button. Remove label
		    public void mouseExited(java.awt.event.MouseEvent event) {
		    	profileLabel.setVisible(false);
		    }
			
		});
		
		profileLabel.setBounds(profileButton.getBounds());
		
		//Set background and opacity
		profileLabel.setBackground(new Color(226,232,206,200));
		profileLabel.setOpaque(true);
		profileLabel.setVisible(false);
	
	}
	
	

}

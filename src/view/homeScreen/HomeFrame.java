package view.homeScreen;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controller.AppController;
import controller.Loader;

/*
 * This class creates a GUI for the Home Screen which consists of title panel and a note list panel
 */
public class HomeFrame extends JFrame {
	
	//Fields
	private NoteListingsPanel noteListingsPanel = new NoteListingsPanel(); //Note List panel consists of clickable buttons
	private TitlePanel titlePanel = new TitlePanel(); //Title panel consists of button to add more notes, search, calendar, profile
	private JScrollPane scrollPane; //Scroll pane object to be able to scroll through a component
	public static int LIST_BUTTON_WIDTH=1500; //Button width of note list
	private JLabel backgroundImg = new JLabel(new ImageIcon("images/homeScreen.png")); //Background image of the home screen
	private JLabel noNotesPresent = new JLabel("No Notes Present!");	//Label to indicate no notes are present
	
	//Constructor
	public HomeFrame() {
		
		//Set the frame
		setLayout(null);
		setTitle("Notivity - Home");
		setSize(1920, 1080);
		getContentPane().setBackground(Color.WHITE);
		
		//Add title panel
		add(titlePanel);
		
		//Setup no notes message label
		setupNoNotesLabel();
		add(noNotesPresent);
		
		//Setup the scroll panel, but do not add it yet
		setupScrollPane();
		
        //Set the background image
        backgroundImg.setBounds(0,0,1920,1080);
        add(backgroundImg);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	//GETTERS AND SETTERS
	public NoteListingsPanel getNoteListingsPanel() {
		return noteListingsPanel;
	}

	public void setNoteListingsPanel(NoteListingsPanel noteListingsPanel) {
		this.noteListingsPanel = noteListingsPanel;
	}

	public TitlePanel getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(TitlePanel titlePanel) {
		this.titlePanel = titlePanel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	
	public static int getLIST_BUTTON_WIDTH() {
		return LIST_BUTTON_WIDTH;
	}

	public static void setLIST_BUTTON_WIDTH(int lIST_BUTTON_WIDTH) {
		LIST_BUTTON_WIDTH = lIST_BUTTON_WIDTH;
	}

	public JLabel getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(JLabel backgroundImg) {
		this.backgroundImg = backgroundImg;
	}

	
	public JLabel getNoNotesPresent() {
		return noNotesPresent;
	}

	public void setNoNotesPresent(JLabel noNotesPresent) {
		this.noNotesPresent = noNotesPresent;
	}
	
	//Sets up GUI for scroll panel
	private void setupScrollPane() {
		//Create a scrollable panel for the note listings
		scrollPane = new JScrollPane(noteListingsPanel);
		
		//Set scrolling policies and bounds
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(220,500,LIST_BUTTON_WIDTH,400);
        scrollPane.setBorder(null);
        
        //Transparent background
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
			
		}
	
	//Sets up no notes message label
	private void setupNoNotesLabel() {
		noNotesPresent.setBounds(800,550,LIST_BUTTON_WIDTH,200);
		noNotesPresent.setFont(AppController.bigFont);
		
		//Initially set the visibility to false
		noNotesPresent.setVisible(false);
			
		}

	/*
	 * This method overrides the dispose window method. Before disposing, files are saved
	 */
	@Override
	public void dispose() {
		
		//Object data is written to file
		Loader.writeDataToFile();
		
		//Window is then disposed
	    super.dispose();
	}

	//Utlity Methods
	public void resetListingsPanel() {
		remove(noteListingsPanel);
		noteListingsPanel =new NoteListingsPanel();
		add(noteListingsPanel);
		this.revalidate();
		this.repaint();
	}
	
}

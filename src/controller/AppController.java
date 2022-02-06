package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.BoxingNote;
import model.ChartingNote;
import model.CornellNote;
import model.Note;
import model.OutlineNote;
import view.homeScreen.HomeFrame;
import view.homeScreen.NoteListingsPanel;
import view.homeScreen.NoteSettingsFrame;
import view.noteTakingScreen.NoteTakingFrame;
import view.noteTakingScreen.PomodoroHelpFrame;
import view.userProfileScreen.AnalyticsFrame;

/*
 * This class has the main logic of the Notebook Application. It controls display of screens,
 * button functionality, load up of any previous data and text files.
 */

public class AppController implements ActionListener {

	// Fields

	// Screen Instances
	private HomeFrame homeFrame; // Home Screen
	private AnalyticsFrame analyticsFrame; // User analytics
	private NoteSettingsFrame noteSettingsFrame; // Note settings screen
	private NoteTakingFrame noteTakingFrame; // Note taking frame with different templates
	private PomodoroHelpFrame pomodoroHelp; // Pomodoro help frame

	// Fonts
	public static Font bigFont = new Font("Arial", Font.PLAIN, 50);
	public static Font smallFont = new Font("Arial", Font.PLAIN, 40);
	public static Font notesFont = new Font("Arial", Font.PLAIN, 20);

	// Colors
	public static Color notesBg = Color.LIGHT_GRAY;

	// Contains all the notes(outline,cornell,boxing,charting) saved by the user
	public static ArrayList<Note> notesList = new ArrayList<Note>();

	// Stores time spent on notes (top 4)
	private HashMap<String, Integer> analyticsData = new HashMap<String, Integer>();

	// Measures how much time is elapsed to calculate analytics
	private long startTime, endTime;

	// Constructor
	public AppController() {

		// Instantiate home screen
		homeFrame = new HomeFrame();

		// Add Action listener to the home frame buttons
		addActionListenerHomeFrame();

		// Load up any previous notes if they are saved
		if (Loader.readDataFromFile()) { // Returns true if there is any saved data

			// Remove current screen and instantiate a new screen
			homeFrame.revalidate();
			homeFrame.dispose();

			displayDefaultHomeScreen();
			
			// Display a message if notes are loaded
			if(notesList.size()>0)
				JOptionPane.showMessageDialog(homeFrame, "Previous files loaded");
		}

	}

	/*
	 * This method displays GUI for the home screen. Since, notes list is constantly
	 * updated, home screen should also be updated to display updated list
	 */
	private void displayDefaultHomeScreen() {

		// If the size of the note list is greater than 0
		if (notesList.size() > 0) {

			// Traverse through the notes and set every note's visibility to true
			for (Note note : notesList) {
				note.setVisible(true);
			}

			// Instantiate new screen
			homeFrame = new HomeFrame();
			homeFrame.repaint();

			// Add Action listener to the home frame buttons
			addActionListenerHomeFrame();

			// Load up home screen by adding note listings
			loadUpHomeScreen();
		}

		// If there are no notes saved
		else {
			// Display default home screen without any notes otherwise
			homeFrame = new HomeFrame();

			// Add Action listener to the home frame buttons
			addActionListenerHomeFrame();

			// No notes message
			homeFrame.getNoNotesPresent().setVisible(true);
		}

		homeFrame.repaint();

	}

	// Adds controller's action listener to home screen components
	private void addActionListenerHomeFrame() {
		homeFrame.getTitlePanel().getProfileButton().addActionListener(this);
		homeFrame.getTitlePanel().getAddNoteButton().addActionListener(this);
		homeFrame.getTitlePanel().getSearchButton().addActionListener(this);
	}

	// Adds controller's action listener to note settings screen components
	private void addActionListenerNoteSettingsFrame() {
		noteSettingsFrame.getOutlineButton().addActionListener(this);
		noteSettingsFrame.getCornellButton().addActionListener(this);
		noteSettingsFrame.getBoxingButton().addActionListener(this);
		noteSettingsFrame.getChartingButton().addActionListener(this);
		noteSettingsFrame.getBackButton().addActionListener(this);
	}

	// Adds controller's action listener to note taking screen components
	private void addActionListenerNoteTakingFrame() {

		// Back button
		noteTakingFrame.getBackButton().addActionListener(this);

		// Menu Items
		noteTakingFrame.getTaskMenubar().getSave().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getDelete().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getStartPomodoro().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getStopPomodoro().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getWhatIsPomodoro().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getPausePomodoro().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getIncreaseFontSize().addActionListener(this);
		noteTakingFrame.getTaskMenubar().getDecreaseFontSize().addActionListener(this);

		// Only display the import content option, if it is the outline panel
		if (noteTakingFrame.getOutlinePanel() != null) {
			noteTakingFrame.getTaskMenubar().getImportContent().addActionListener(this);
		}
	}

	/*
	 * This method listens for any changes to the GUI and implements functionality
	 * based on the event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		// Functionality for buttons in the home screen
		if (homeFrame != null) {

			// If the user clicks on search option
			if (event.getSource() == homeFrame.getTitlePanel().getSearchButton()) {

				// Get the text from search field
				String searchedText = homeFrame.getTitlePanel().getSearchField().getText().toLowerCase();

				// Create a temporary list
				ArrayList<Note> tempList = new ArrayList<Note>();

				// Traverse through the notes list array
				for (Note note : notesList) {

					// If the note title contains the searched text,
					if (note.getTitle().toLowerCase().contains(searchedText)) {

						// Set the note visibility to true
						note.setVisible(true);

						// Add it to the temporary list so that it gets displayed first
						tempList.add(note);
					}

					// Set the visibility to false if the note's title does not contain the searched
					// text
					else
						note.setVisible(false);

				}

				// Iterate through the list and add the remaining notes which are not visible
				for (Note note : notesList) {

					// Add the remaining notes which were not added previously
					if (!note.isVisible())
						tempList.add(note);
				}

				// Set the temporary list values to the main list
				notesList = tempList;

				// If the search field is blank
				if (searchedText.equals(""))

					// Set every note's visibility to true
					for (Note note : notesList)
						note.setVisible(true);

				// Remove all buttons from listings panel
				homeFrame.getNoteListingsPanel().removeButtons();

				// Revalidate the panel
				homeFrame.getNoteListingsPanel().revalidate();

				homeFrame.getNoteListingsPanel().setVisible(true);

				homeFrame.repaint();

				// Load up the home screen
				loadUpHomeScreen();

			}

			// if the user clicks on profile option
			if (event.getSource() == homeFrame.getTitlePanel().getProfileButton()) {

				// Display the analytics only if there are more than 5 notes
				if (notesList.size() >= 5) {

					// Calculate updated analytics
					calculateAnalytics();
					// Display Analytics
					analyticsFrame = new AnalyticsFrame("Notivity - Analytics", "Time Spent on Notes", analyticsData);

					// Display error if 5 notes are not yet created
				} else {
					JOptionPane.showMessageDialog(noteTakingFrame, "Minimum of 5 notes required to see analytics",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}

			// If the user clicks on Add a note button
			if (event.getSource() == homeFrame.getTitlePanel().getAddNoteButton()) {

				// Remove current screen
				homeFrame.dispose();

				// Display note-settings screen
				noteSettingsFrame = new NoteSettingsFrame();
				addActionListenerNoteSettingsFrame();
			}

			// Size of notes
			int size = homeFrame.getNoteListingsPanel().getNoteListingButtons().size();

			// Load up any saved notes
			for (int index = 0; index < size; index++) {

				// Display the saved notes panel only if there are any (size greater than 0)
				if (size >= 1
						&& event.getSource() == homeFrame.getNoteListingsPanel().getNoteListingButtons().get(index)) {

					// Keep track of how much time is spent
					startTimer();

					// If the note that is being loaded is a outline note
					if (notesList.get(index) instanceof OutlineNote)
						loadOutlineScreen(notesList.get(index), index);

					// If the note that is being loaded is a Cornell note
					else if (notesList.get(index) instanceof CornellNote)
						loadCornellScreen(notesList.get(index), index);

					// If the note that is being loaded is a Boxing note
					else if (notesList.get(index) instanceof BoxingNote)
						loadBoxingScreen(notesList.get(index), index);

					// If the note that is being loaded is a Charting note
					else if (notesList.get(index) instanceof ChartingNote)
						loadChartingScreen(notesList.get(index), index);

				}
			}

		}

		// Functionality for buttons in note settings frame
		if (noteSettingsFrame != null) {

			// Back button
			if (event.getSource() == noteSettingsFrame.getBackButton()) {

				// Remove current screen
				noteSettingsFrame.dispose();

				// Display home screen
				displayDefaultHomeScreen();
			}

			// Outline note taking option
			if (event.getSource() == noteSettingsFrame.getOutlineButton()) {

				// Remove current screen
				noteSettingsFrame.dispose();

				// Create a note taking frame with an outline panel
				noteTakingFrame = new NoteTakingFrame("outline");

				// Keep track of how much time is spent
				startTimer();
				addActionListenerNoteTakingFrame();
			}

			// Cornell Note taking option
			else if (event.getSource() == noteSettingsFrame.getCornellButton()) {

				// Remove current screen
				noteSettingsFrame.dispose();

				// Create a note taking frame with cornell panel
				noteTakingFrame = new NoteTakingFrame("cornell");

				// Keep track of how much time is spent
				startTimer();
				addActionListenerNoteTakingFrame();
			}

			// Boxing Note taking option
			else if (event.getSource() == noteSettingsFrame.getBoxingButton()) {

				// Remove current screen
				noteSettingsFrame.dispose();

				// Create a note taking frame with boxing panel
				noteTakingFrame = new NoteTakingFrame("boxing");

				// Keep track of how much time is spent
				startTimer();
				addActionListenerNoteTakingFrame();
			}

			// Charting note taking option
			else if (event.getSource() == noteSettingsFrame.getChartingButton()) {

				// Remove current screen
				noteSettingsFrame.dispose();

				// Create a note taking frame with charting panel
				noteTakingFrame = new NoteTakingFrame("charting");

				// Keep track of how much time is spent
				startTimer();
				addActionListenerNoteTakingFrame();
			}
		}

		// Functionality for buttons in note taking frame
		if (noteTakingFrame != null) {

			// Back button
			if (event.getSource() == noteTakingFrame.getBackButton()) {
				noteTakingFrame.dispose();
				noteSettingsFrame = new NoteSettingsFrame();
				addActionListenerNoteSettingsFrame();

				// If the index is not saved, then the note is not loaded. It is newly created
				// note
				if (noteTakingFrame.getListIndex() == -1 && noteTakingFrame.getTaskMenubar().isReSave())

					// End the timer
					endTimer(notesList.size() - 1);

				// If the index is saved, then it is a previously created note
				else
					endTimer(noteTakingFrame.getListIndex());

			}

			// Delete functionality
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getDelete()) {

				// If the file that is being deleted is a loaded file
				if (noteTakingFrame.getTaskMenubar().isSaveLoaded()) {

					// Remove the file from the list by accessing it's index stored in the frame
					notesList.remove(noteTakingFrame.getListIndex());
					removeNoteTakingScreen();

				}

				// IF the file that is being deleted was not saved, just remove the current
				// screen
				else if (!noteTakingFrame.getTaskMenubar().isReSave())
					removeNoteTakingScreen();

				// If the file that is being deleted was saved and made changed and resaved,
				// remove the last note in the list
				else if (noteTakingFrame.getTaskMenubar().isReSave()) {

					// Remove the last note from the list
					notesList.remove(notesList.size() - 1);
					removeNoteTakingScreen();

				}

			}
			// Save functionality
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getSave()) {

				// If note name is already taken, then display error message
				if (noteNameTaken(noteTakingFrame.getTitleText().getText())) {
					JOptionPane.showMessageDialog(noteTakingFrame, "Duplicate file name exists", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				else {
					// If the user is on outline layout
					if (noteTakingFrame.getOutlinePanel() != null) {

						// If the file is a loaded file
						if (noteTakingFrame.getTaskMenubar().isSaveLoaded()) {
							System.out.println("Saved loaded file");
							resaveNotesOutline(noteTakingFrame.getListIndex());

						}

						// If the file is not saved already
						else if (!noteTakingFrame.getTaskMenubar().isReSave()) {
							System.out.println("saved outline");
							saveNotesOutline();
							noteTakingFrame.getTaskMenubar().setReSave(true);
						}

						// If the file is saved once, but made changes
						else {
							System.out.println("resaved outline");
							resaveNotesOutline(notesList.size() - 1);
						}

					}

					// If the user is on cornell layout
					else if (noteTakingFrame.getCornellPanel() != null) {

						// If the file is a loaded file
						if (noteTakingFrame.getTaskMenubar().isSaveLoaded()) {
							System.out.println("Saved loaded file");
							resaveNotesCornell(noteTakingFrame.getListIndex());
						}

						// If the file is not saved already
						else if (!noteTakingFrame.getTaskMenubar().isReSave()) {
							System.out.println("saved cornell");
							saveNotesCornell();
							noteTakingFrame.getTaskMenubar().setReSave(true);
						}

						// If the file is saved once, but made changes
						else {
							System.out.println("resaved cornell");
							resaveNotesCornell(notesList.size() - 1);
						}
					}

					// If the user is on boxing layout
					else if (noteTakingFrame.getBoxingPanel() != null) {

						// If the file is a loaded file
						if (noteTakingFrame.getTaskMenubar().isSaveLoaded()) {
							System.out.println("Saved loaded file");
							resaveNotesBoxing(noteTakingFrame.getListIndex());
						}

						// If the file is not saved already
						else if (!noteTakingFrame.getTaskMenubar().isReSave()) {
							System.out.println("saved boxing");
							saveNotesBoxing();
							noteTakingFrame.getTaskMenubar().setReSave(true);
						}

						// If the file is saved once, but made changes
						else {
							System.out.println("resaved boxing");
							resaveNotesBoxing(notesList.size() - 1);
						}

					}

					// If the user is on charting layout
					else if (noteTakingFrame.getChartingPanel() != null) {

						// If the file is a loaded file
						if (noteTakingFrame.getTaskMenubar().isSaveLoaded()) {
							System.out.println("Saved loaded file");
							resaveNotesCharting(noteTakingFrame.getListIndex());
						}

						// If the file is not saved already
						else if (!noteTakingFrame.getTaskMenubar().isReSave()) {
							System.out.println("saved charting");
							saveNotesCharting();
							noteTakingFrame.getTaskMenubar().setReSave(true);
						}

						// If the file is saved once, but made changes
						else {
							System.out.println("resaved charting");
							resaveNotesCharting(notesList.size() - 1);
						}

					}
				}
			}

			// Functionality for increase font size
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getIncreaseFontSize()) {

				System.out.println("Font size increased");

				// If the user is on outline layout
				if (noteTakingFrame.getOutlinePanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getOutlinePanel().getNotesTextArea().getFont();

					// Increase the font size
					float size = font.getSize() + 1.0f;

					// Set the new font
					noteTakingFrame.getOutlinePanel().getNotesTextArea().setFont(font.deriveFont(size)); // https://stackoverflow.com/questions/8675038/increasing-decreasing-font-size-inside-textarea-using-jbutton

				}

				// If the user is on cornell layout
				else if (noteTakingFrame.getCornellPanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getCornellPanel().getNotesTextArea().getFont();

					// Increase the font size
					float size = font.getSize() + 1.0f;

					// Set the new font
					noteTakingFrame.getCornellPanel().getNotesTextArea().setFont(font.deriveFont(size));
					noteTakingFrame.getCornellPanel().getCueTextArea().setFont(font.deriveFont(size));
					noteTakingFrame.getCornellPanel().getSummaryTextArea().setFont(font.deriveFont(size));

				}

				// If the user is on boxing layout
				else if (noteTakingFrame.getBoxingPanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getBoxingPanel().getBoxTextArea()[0].getFont();

					// Increase the font size
					float size = font.getSize() + 1.0f;

					// Set the new font
					for (int index = 0; index < noteTakingFrame.getBoxingPanel().getBoxTextArea().length; index++) {
						noteTakingFrame.getBoxingPanel().getBoxTextArea()[index].setFont(font.deriveFont(size));
					}
				}

				// If the user is on charting layout
				else if (noteTakingFrame.getChartingPanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getChartingPanel().getNotesTextArea().get(0).getFont();

					// Increase the font size
					float size = font.getSize() + 1.0f;

					// Set the new font - header
					for (int index = 0; index < noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea()
							.size(); index++) {
						noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().get(index)
								.setFont(font.deriveFont(size));
					}

					// Set the new font - notes
					for (int index = 0; index < noteTakingFrame.getChartingPanel().getNotesTextArea().size(); index++) {
						noteTakingFrame.getChartingPanel().getNotesTextArea().get(index).setFont(font.deriveFont(size));
					}
				}
			}

			// Functionality for decrease font size
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getDecreaseFontSize()) {

				System.out.println("Font size decreased");

				// If the user is on outline layout
				if (noteTakingFrame.getOutlinePanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getOutlinePanel().getNotesTextArea().getFont();

					// Decrease the font size
					float size = font.getSize() - 1.0f;

					// Set the new font
					noteTakingFrame.getOutlinePanel().getNotesTextArea().setFont(font.deriveFont(size)); // https://stackoverflow.com/questions/8675038/increasing-decreasing-font-size-inside-textarea-using-jbutton

				}

				// If the user is on cornell layout
				else if (noteTakingFrame.getCornellPanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getCornellPanel().getNotesTextArea().getFont();

					// Decrease the font size
					float size = font.getSize() - 1.0f;

					// Set the new font
					noteTakingFrame.getCornellPanel().getNotesTextArea().setFont(font.deriveFont(size));
					noteTakingFrame.getCornellPanel().getCueTextArea().setFont(font.deriveFont(size));
					noteTakingFrame.getCornellPanel().getSummaryTextArea().setFont(font.deriveFont(size));
				}

				// If the user is on boxing layout
				else if (noteTakingFrame.getBoxingPanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getBoxingPanel().getBoxTextArea()[0].getFont();

					// Decrease the font size
					float size = font.getSize() - 1.0f;

					// Set the new font
					for (int index = 0; index < noteTakingFrame.getBoxingPanel().getBoxTextArea().length; index++) {
						noteTakingFrame.getBoxingPanel().getBoxTextArea()[index].setFont(font.deriveFont(size));
					}
				}

				// If the user is on charting layout
				else if (noteTakingFrame.getChartingPanel() != null) {

					// Get the current font
					Font font = noteTakingFrame.getChartingPanel().getNotesTextArea().get(0).getFont();

					// Decrease the font size
					float size = font.getSize() - 1.0f;

					// Set the new font
					for (int index = 0; index < noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea()
							.size(); index++) {
						noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().get(index)
								.setFont(font.deriveFont(size));
					}
					for (int index = 0; index < noteTakingFrame.getChartingPanel().getNotesTextArea().size(); index++) {
						noteTakingFrame.getChartingPanel().getNotesTextArea().get(index).setFont(font.deriveFont(size));
					}
				}
			}

			// Start Pomodoro timer
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getStartPomodoro()) {
				JOptionPane.showMessageDialog(noteTakingFrame, "Work for 25 minutes");
				noteTakingFrame.getPomdoroPanel().startPomodoro();
			}

			// Pause Pomodoro timer
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getPausePomodoro()) {

				// Check if pomodoro timer is started
				if (noteTakingFrame.getPomdoroPanel().getWorkTimer() != null)
					noteTakingFrame.getPomdoroPanel().pausePomodoro();
			}

			// Stop Pomodoro timer
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getStopPomodoro()) {

				// Check if pomodoro timer is started
				if (noteTakingFrame.getPomdoroPanel().getWorkTimer() != null)
					noteTakingFrame.getPomdoroPanel().stopPomodoro();

			}

			// What is Pomodoro option
			if (event.getSource() == noteTakingFrame.getTaskMenubar().getWhatIsPomodoro()) {
				pomodoroHelp = new PomodoroHelpFrame();
			}

			if (noteTakingFrame.getOutlinePanel() != null
					&& event.getSource() == noteTakingFrame.getTaskMenubar().getImportContent()) {
				JFileChooser fileChooser = new JFileChooser();
				int response = fileChooser.showOpenDialog(homeFrame);

				// https://www.javatpoint.com/java-jfilechooser
				if (response == JFileChooser.APPROVE_OPTION) {

					// Get selected file
					File file = fileChooser.getSelectedFile();

					// Default value of file type
					String fileType = null;

					// Error checking for file type. Only Text files are supported in this
					// application
					try {
						// Get the file type of the object.
						fileType = Files.probeContentType(file.toPath()); // https://dzone.com/articles/determining-file-type-in-java
					}

					// Print the error
					catch (IOException ioException) {
						System.out.println("File type not detected");

					}

					// Proceed in reading the file only if the file type is text
					if (fileType.equals("text/plain"))
						updateNote(file);

					// Display an error message otherwise
					else {
						JOptionPane.showMessageDialog(noteTakingFrame, "Only .txt files supported", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

	}

	// This method checks if the current note name is already taken
	private boolean noteNameTaken(String text) {

		// Iterate through each note and check if the title is already taken
		for (Note note : notesList) {

			// If the title is the same and if the current note is not being resaved, then
			// return true
			if (note.getTitle().toLowerCase().equals(text.toLowerCase()) && !noteTakingFrame.getTaskMenubar().isReSave()
					&& !noteTakingFrame.getTaskMenubar().isSaveLoaded())
				return true;
		}

		// Return false if note name is not taken
		return false;
	}

	// Starts timer to measure how much time is spent on each note
	private void startTimer() {
		System.out.println("Timer started");

		// Start the timer
		startTime = System.nanoTime();
	}

	// Ends the timer and stores information in the appropriate note
	private void endTimer(int noteIndex) {

		// End the timer
		endTime = System.nanoTime();

		// If the file is deleted return
		if (noteIndex == -1) {
			return;
		}

		// Calculate elapsed time
		int elapsedTime = (int) ((endTime - startTime) / (Math.pow(10, 9)));
		System.out.println("Timer ended: " + notesList.get(noteIndex) + " " + elapsedTime);

		// Store the information of how much time is spent
		notesList.get(noteIndex).setTimeSpent(notesList.get(noteIndex).getTimeSpent() + elapsedTime);
	}

	/*
	 * This method calculates analytics by sorting all notes created based on how
	 * much time is spent on each note
	 */
	private void calculateAnalytics() {

		// Clear previous analytics
		analyticsData.clear();

		// Reverse Sort based on time spent
		Collections.sort(notesList, Comparator.comparing(Note::getTimeSpent).reversed());

		for (Note note : notesList) {
			System.out.println(note.getTitle());
		}
		// Add each note to the hash map
		for (int index = 0; index < notesList.size(); index++) {
			analyticsData.put(notesList.get(index).getTitle(), notesList.get(index).getTimeSpent());
		}

	}

	/*
	 * This method takes file object as a parameter and reads the file. After
	 * reading, it updates the content in the note taking text area.
	 */
	private void updateNote(File file) {

		// Text to store content
		String text1 = "", text2 = "";

		try {

			// Buffered Reader to read files
			BufferedReader input = new BufferedReader(new FileReader(file));

			// Do the following
			do {

				// Read the next line
				text1 = input.readLine();

				// If it is not null, then keep storing
				if (text1 != null)
					text2 += text1 + "\n";

				// If it is null, break the loop
				else
					break;
			} while (true);

			// Print the exception
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Update note taking frame GUI
		noteTakingFrame.getOutlinePanel().getNotesTextArea().setText(text2);
		noteTakingFrame.getOutlinePanel().repaint();
	}

	// This utility method disposes note taking screen when transitioning to the
	// note settings screen
	private void removeNoteTakingScreen() {
		noteTakingFrame.dispose();
		noteSettingsFrame = new NoteSettingsFrame();
		addActionListenerNoteSettingsFrame();
	}

	/*
	 * This method loads up home screen's note listings. It gets updated every time
	 * a note is saved
	 */
	private void loadUpHomeScreen() {

		// Note list instance
		NoteListingsPanel listPanel = homeFrame.getNoteListingsPanel();

		// Keeps track of number of notes
		int noteIndex = 0;

		// Traverse through the notes saved
		for (Note note : notesList) {

			if (note.isVisible()) {

				// Create a new button for every note saved
				listPanel.createNewButton(note.getTitle());

				// Add Action listener
				listPanel.getNoteListingButtons().get(noteIndex++).addActionListener(this);

				listPanel.repaint();
			}
		}

		// If there are saved notes, only then add the scrollable panel to the screen
		if (noteIndex > 0) {
			homeFrame.getNoNotesPresent().setVisible(false);
			homeFrame.add(homeFrame.getScrollPane());
			homeFrame.add(homeFrame.getBackgroundImg());
			homeFrame.repaint();
		}

		else {

			// Display a message telling the user that are no notes present
			homeFrame.getNoNotesPresent().setVisible(true);
			homeFrame.repaint();
		}
	}

	// Saves "Outline" Notes to the global notes list available to all classes
	private void saveNotesOutline() {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Create new note with the title
		OutlineNote note = new OutlineNote(title);

		// Store note data
		note.setData(noteTakingFrame.getOutlinePanel().getNotesTextArea().getText());

		// Store font size
		note.setFontSize(noteTakingFrame.getOutlinePanel().getNotesTextArea().getFont().getSize());

		// Add the note object to the public list
		notesList.add(note);
	}

	/*
	 * Resaves "outline" notes. This is necessary because save function(previous
	 * method) creates a new entry in the public list every time the button is
	 * clicked. In contrast, this method updates data in the existing entry.
	 * Parameter: note index
	 */
	private void resaveNotesOutline(int listIndex) {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Get notes data
		String updatedData = noteTakingFrame.getOutlinePanel().getNotesTextArea().getText();

		// Get updated font size
		int updatedFontSize = noteTakingFrame.getOutlinePanel().getNotesTextArea().getFont().getSize();

		// Update the title
		notesList.get(listIndex).setTitle(title);

		// Update the data by casting the note object to its child type
		((OutlineNote) notesList.get(listIndex)).setData(updatedData);

		// Update font size
		notesList.get(listIndex).setFontSize(updatedFontSize);
	}

	// Saves "Cornell" Notes to the global notes list available to all classes
	private void saveNotesCornell() {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Create new note with the title
		CornellNote note = new CornellNote(title);

		// Set note data
		note.getData()[0] = noteTakingFrame.getCornellPanel().getCueTextArea().getText();
		note.getData()[1] = noteTakingFrame.getCornellPanel().getNotesTextArea().getText();
		note.getData()[2] = noteTakingFrame.getCornellPanel().getSummaryTextArea().getText();

		// Set font size
		note.setFontSize(noteTakingFrame.getCornellPanel().getSummaryTextArea().getFont().getSize());

		// Create a new entry by adding the note to the public notes list
		notesList.add(note);
	}

	/*
	 * Resaves "cornell" notes. This is necessary because save function(previous
	 * method) creates a new entry in the public list every time the button is
	 * clicked. In contrast, this method updates data in the existing entry.
	 * Parameter: note index
	 */
	private void resaveNotesCornell(int listIndex) {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Create a temporary array to store updated data
		String updatedData[] = new String[3];
		int updatedFontSize = noteTakingFrame.getCornellPanel().getSummaryTextArea().getFont().getSize();

		// Set note data
		updatedData[0] = noteTakingFrame.getCornellPanel().getCueTextArea().getText();
		updatedData[1] = noteTakingFrame.getCornellPanel().getNotesTextArea().getText();
		updatedData[2] = noteTakingFrame.getCornellPanel().getSummaryTextArea().getText();

		// Update the title and data
		notesList.get(listIndex).setTitle(title);

		// Update the data by casting the note object to its child type
		((CornellNote) notesList.get(listIndex)).setData(updatedData);

		// Set font size
		notesList.get(listIndex).setFontSize(updatedFontSize);

	}

	// Saves "Boxing" Notes to the global notes list available to all classes
	private void saveNotesBoxing() {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Create new boxing note
		BoxingNote note = new BoxingNote(title);

		// Store font size
		note.setFontSize(noteTakingFrame.getBoxingPanel().getBoxTextArea()[0].getFont().getSize());

		// Iterate through each box and store data
		for (int index = 0; index < 6; index++)
			note.getData().add(noteTakingFrame.getBoxingPanel().getBoxTextArea()[index].getText());

		// Create a new entry by adding the note to the public notes list
		notesList.add(note);

	}

	/*
	 * Resaves "boxing" notes. This is necessary because save function(previous
	 * method) creates a new entry in the public list every time the button is
	 * clicked. In contrast, this method updates data in the existing entry.
	 * Parameter: note index
	 */
	private void resaveNotesBoxing(int listIndex) {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Get updated font size
		int updatedFontSize = noteTakingFrame.getBoxingPanel().getBoxTextArea()[0].getFont().getSize();

		// Create a temporary list to store updated data
		ArrayList<String> updatedData = new ArrayList<String>();

		// Store the updated notes
		for (int index = 0; index < 6; index++)
			updatedData.add(noteTakingFrame.getBoxingPanel().getBoxTextArea()[index].getText());

		// Update the title
		notesList.get(listIndex).setTitle(title);

		// Update the data by casting the note object to its child type
		((BoxingNote) notesList.get(listIndex)).setData(updatedData);

		// Update font size
		notesList.get(listIndex).setFontSize(updatedFontSize);

	}

	// Saves "Charting" Notes to the global notes list available to all classes
	private void saveNotesCharting() {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Create a new charting note object
		ChartingNote note = new ChartingNote(title);

		// Get number of headings and number of notes for iteration purposes
		int numOfHeadings = noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().size();
		int numOfNotes = noteTakingFrame.getChartingPanel().getNotesTextArea().size();

		// Get the font size and store it in the note object
		note.setFontSize(noteTakingFrame.getChartingPanel().getNotesTextArea().get(0).getFont().getSize());

		// Iterate through the headings and store them in the note object
		for (int index = 0; index < numOfHeadings; index++) {
			note.getData()[0].add(noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().get(index).getText());
		}

		// Iterate through the notes and store them in the note object
		for (int index = 0; index < numOfNotes; index++) {
			note.getData()[1].add(noteTakingFrame.getChartingPanel().getNotesTextArea().get(index).getText());
		}

		// Create a new entry by adding the note to the public notes list
		notesList.add(note);
	}

	/*
	 * Resaves "charting" notes. This is necessary because save function(previous
	 * method) creates a new entry in the public list every time the button is
	 * clicked. In contrast, this method updates data in the existing entry.
	 * Parameter: note index
	 */
	private void resaveNotesCharting(int listIndex) {

		// Get the title
		String title = noteTakingFrame.getTitleText().getText();

		// Create a temporary list to store updated data
		ArrayList<String> updatedData[] = new ArrayList[2];

		// Initialize each index in the list
		for (int index = 0; index < 2; index++) {
			updatedData[index] = new ArrayList<String>();
		}

		// Get updated font size
		int updatedFontSize = noteTakingFrame.getChartingPanel().getNotesTextArea().get(0).getFont().getSize();

		// Get number of headings and number of notes for iteration purposes
		int numOfHeadings = noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().size();
		int numOfNotes = noteTakingFrame.getChartingPanel().getNotesTextArea().size();

		// Store the updated heading
		for (int index = 0; index < numOfHeadings; index++) {
			updatedData[0].add(noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().get(index).getText());
		}

		// Store the updated notes
		for (int index = 0; index < numOfNotes; index++) {
			updatedData[1].add(noteTakingFrame.getChartingPanel().getNotesTextArea().get(index).getText());
		}

		// Update the title and data
		notesList.get(listIndex).setTitle(title);

		// Cast the note and set the updated information in the appropriate note object
		((ChartingNote) notesList.get(listIndex)).setData(updatedData);

		// Set the font size
		notesList.get(listIndex).setFontSize(updatedFontSize);
	}

	/*
	 * This method takes note object and it's index in the public list, to display
	 * it's content in the outline screen
	 */
	private void loadOutlineScreen(Note note, int index) {

		// Remove home screen
		homeFrame.dispose();

		// Load note taking screen
		noteTakingFrame = new NoteTakingFrame("outline");

		// Add Action listener
		addActionListenerNoteTakingFrame();

		// Load notes data
		noteTakingFrame.getOutlinePanel().getNotesTextArea().setText(((OutlineNote) note).getData());

		// Load title
		noteTakingFrame.getTitleText().setText(note.getTitle());

		// Set the font size
		noteTakingFrame.getOutlinePanel().getNotesTextArea().setFont(notesFont.deriveFont(note.getFontSize() + 0.0f)); // https://stackoverflow.com/questions/8675038/increasing-decreasing-font-size-inside-textarea-using-jbutton

		// Send current note index
		noteTakingFrame.setListIndex(index);

		// Set save loaded to true - to indicate that loaded file should be saved
		noteTakingFrame.getTaskMenubar().setSaveLoaded(true);
	}

	/*
	 * This method takes note object and it's index in the public list, to display
	 * it's content in the cornell screen
	 */
	private void loadCornellScreen(Note note, int index) {

		// Remove home screen
		homeFrame.dispose();

		// Load note taking screen
		noteTakingFrame = new NoteTakingFrame("cornell");

		// Add Action listener
		addActionListenerNoteTakingFrame();

		// Load notes data
		noteTakingFrame.getCornellPanel().getCueTextArea().setText(((CornellNote) note).getData()[0]);
		noteTakingFrame.getCornellPanel().getNotesTextArea().setText(((CornellNote) note).getData()[1]);
		noteTakingFrame.getCornellPanel().getSummaryTextArea().setText(((CornellNote) note).getData()[2]);

		// Set the font size
		noteTakingFrame.getCornellPanel().getCueTextArea().setFont(notesFont.deriveFont(note.getFontSize() + 0.0f));
		noteTakingFrame.getCornellPanel().getNotesTextArea().setFont(notesFont.deriveFont(note.getFontSize() + 0.0f));
		noteTakingFrame.getCornellPanel().getSummaryTextArea().setFont(notesFont.deriveFont(note.getFontSize() + 0.0f));

		// Load title
		noteTakingFrame.getTitleText().setText(note.getTitle());

		// Send current note index
		noteTakingFrame.setListIndex(index);

		// Set save loaded to true - to indicate that loaded file should be saved
		noteTakingFrame.getTaskMenubar().setSaveLoaded(true);
	}

	/*
	 * This method takes note object and it's index in the public list, to display
	 * it's content in the boxing screen
	 */
	private void loadBoxingScreen(Note note, int index) {

		// Remove home screen
		homeFrame.dispose();

		// Load note taking screen
		noteTakingFrame = new NoteTakingFrame("boxing");

		// Add Action listener
		addActionListenerNoteTakingFrame();

		// Load notes data
		for (int counter = 0; counter < 6; counter++) {
			noteTakingFrame.getBoxingPanel().getBoxTextArea()[counter]
					.setText(((BoxingNote) note).getData().get(index));
			noteTakingFrame.getBoxingPanel().getBoxTextArea()[counter]
					.setFont(notesFont.deriveFont(note.getFontSize() + 0.0f));
		}

		// Load title
		noteTakingFrame.getTitleText().setText(note.getTitle());

		// Send current note index
		noteTakingFrame.setListIndex(index);

		// Set save loaded to true - to indicate that loaded file should be saved
		noteTakingFrame.getTaskMenubar().setSaveLoaded(true);

	}

	/*
	 * This method takes note object and it's index in the public list, to display
	 * it's content in the charting screen
	 */
	private void loadChartingScreen(Note note, int index) {
		// Remove home screen
		homeFrame.dispose();

		// Load note taking screen
		noteTakingFrame = new NoteTakingFrame("charting");

		// Add Action listener
		addActionListenerNoteTakingFrame();

		// Loads Header data
		for (int counter = 0; counter < noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().size(); counter++) {

			// Get Column Header data
			noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().get(counter)
					.setText(((ChartingNote) note).getData()[0].get(counter));

			// Get Column Header font size
			noteTakingFrame.getColumnHeaderPanel().getHeaderTextArea().get(counter)
					.setFont(notesFont.deriveFont(note.getFontSize() + 0.0f));

		}

		// Loads Notes data
		for (int counter = 0; counter < noteTakingFrame.getChartingPanel().getNotesTextArea().size(); counter++) {

			// Gets text data from note object
			noteTakingFrame.getChartingPanel().getNotesTextArea().get(counter)
					.setText(((ChartingNote) note).getData()[1].get(counter));

			// Gets font size
			noteTakingFrame.getChartingPanel().getNotesTextArea().get(counter)
					.setFont(notesFont.deriveFont(note.getFontSize() + 0.0f));

		}
		// Load title
		noteTakingFrame.getTitleText().setText(note.getTitle());

		// Send current note index
		noteTakingFrame.setListIndex(index);

		// Set save loaded to true - to indicate that loaded file should be saved
		noteTakingFrame.getTaskMenubar().setSaveLoaded(true);
	}

}

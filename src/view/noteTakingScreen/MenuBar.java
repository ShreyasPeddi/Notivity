package view.noteTakingScreen;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/*
 * This class creates a functional menu bar with options to save, delete, import files,
 * formatting options such as changing font size, and an optional pomodoro timer with a 
 * help window explaining what pomodoro technique is
 */
public class MenuBar extends JMenuBar {

	// Create a menus
	private JMenu fileMenu = new JMenu("File");
	private JMenu pomodoroMenu = new JMenu("Pomodoro");
	private JMenu formatMenu = new JMenu("Format");

	// Create menu items - Pomodoro
	private JMenuItem startPomodoro = new JMenuItem("Start Pomodoro");
	private JMenuItem stopPomodoro = new JMenuItem("Stop Pomodoro");
	private JMenuItem pausePomodoro = new JMenuItem("Pause Pomodoro");
	private JMenuItem whatIsPomodoro = new JMenuItem("What is Pomodoro?");

	// Create menu items - Format
	private JMenuItem increaseFontSize = new JMenuItem("Increase Font Size");
	private JMenuItem decreaseFontSize = new JMenuItem("Decrease Font Size");

	// Create menu items - File
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem delete = new JMenuItem("Delete");
	private JMenuItem importContent = new JMenuItem("Import Content");

	private boolean reSave = false; // Keeps track of the first time a file is saved
	private boolean saveLoaded = false; // Keeps track of whether the file is loaded or not

	// Constructor
	public MenuBar() {

		// Add menu items to their respective menus

		// Pomodoro menu
		pomodoroMenu.add(startPomodoro);
		pomodoroMenu.add(stopPomodoro);
		pomodoroMenu.add(pausePomodoro);
		pomodoroMenu.add(whatIsPomodoro);

		// Format Menu
		formatMenu.add(increaseFontSize);
		formatMenu.add(decreaseFontSize);

		// File menu
		fileMenu.add(save);
		fileMenu.add(delete);
		// fileMenu.add(importContent);

		// Add the menus to the menu bar
		add(fileMenu);
		add(formatMenu);
		add(pomodoroMenu);

	}

	//GETTERS AND SETTERS
	public JMenu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public JMenu getPomodoroMenu() {
		return pomodoroMenu;
	}

	public void setPomodoroMenu(JMenu pomodoroMenu) {
		this.pomodoroMenu = pomodoroMenu;
	}

	public JMenu getFormatMenu() {
		return formatMenu;
	}

	public void setFormatMenu(JMenu formatMenu) {
		this.formatMenu = formatMenu;
	}

	public JMenuItem getStartPomodoro() {
		return startPomodoro;
	}

	public void setStartPomodoro(JMenuItem startPomodoro) {
		this.startPomodoro = startPomodoro;
	}

	public JMenuItem getStopPomodoro() {
		return stopPomodoro;
	}

	public void setStopPomodoro(JMenuItem stopPomodoro) {
		this.stopPomodoro = stopPomodoro;
	}

	public JMenuItem getPausePomodoro() {
		return pausePomodoro;
	}

	public void setPausePomodoro(JMenuItem pausePomodoro) {
		this.pausePomodoro = pausePomodoro;
	}

	public JMenuItem getWhatIsPomodoro() {
		return whatIsPomodoro;
	}

	public void setWhatIsPomodoro(JMenuItem whatIsPomodoro) {
		this.whatIsPomodoro = whatIsPomodoro;
	}

	public JMenuItem getIncreaseFontSize() {
		return increaseFontSize;
	}

	public void setIncreaseFontSize(JMenuItem increaseFontSize) {
		this.increaseFontSize = increaseFontSize;
	}

	public JMenuItem getDecreaseFontSize() {
		return decreaseFontSize;
	}

	public void setDecreaseFontSize(JMenuItem decreaseFontSize) {
		this.decreaseFontSize = decreaseFontSize;
	}

	public JMenuItem getSave() {
		return save;
	}

	public void setSave(JMenuItem save) {
		this.save = save;
	}


	public boolean isReSave() {
		return reSave;
	}

	public void setReSave(boolean reSave) {
		this.reSave = reSave;
	}

	public boolean isSaveLoaded() {
		return saveLoaded;
	}

	public void setSaveLoaded(boolean saveLoaded) {
		this.saveLoaded = saveLoaded;
	}

	public JMenuItem getDelete() {
		return delete;
	}

	public void setDelete(JMenuItem delete) {
		this.delete = delete;
	}

	public JMenuItem getImportContent() {
		return importContent;
	}

	public void setImportContent(JMenuItem importContent) {
		this.importContent = importContent;
	}

}

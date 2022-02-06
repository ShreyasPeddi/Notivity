package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import controller.AppController;
/*    Project Header
 * Author: Shreyas Peddi
 * Course Code: ICS4U1-01
 * Teacher: Mr. Fernandes
 * Date: 2022-01-18
 * Title: Notivity (Virtual Notebook Application)
 * 
 * Description:  Notivity provides an advanced note-taking functionality for students. Notivity has various layouts(templates) for note-taking purposes 
 * such as the Cornell method and Boxing method which are scientifically proven to be more productive and effective. Through its unique feature of 
 * implementing a note taking system combined with productivity techniques such as pomodoro and user analytics, it makes student learning experience convenient 
 * and also helps with their overall wellbeing.
 * When application is started, user can see the home screen which has sample note templates created. User can add/remove different note templates to their saved 
 * list of notes. User analytics can be accessed when at least 5 notes are created and saved. This application stores user data(notes created) on file and reads 
 * it every time the application is restarted. 
 * 
 * Added Features:
 *  
 *  Home Screen:
 *  - Note Listings: User starts with 4 notes (sample note templates). Any saved notes are added to this "note listing" area. This area is scrollable.
 *  Note Listings are loaded by reading previously stored text file information. This is done through process of serialization and deserialization.
 *  When clicked, user is taken to the note screen which was previously saved. This note can be made changes and can be saved again.
 *  - Search Function: User can search any notes that are already created. If there are no matches, then a message is displayed
 *  - Note Settings: When a new note option is selected, user is taken to new screen to show options to select a note taking layout. This includes:
 *  Boxing Layout, Outline Layout, Cornell Layout, Charting Layout
 *  - Additionally, user can save data (note objects) on one system and run the same application on a different system. Previous data will be loaded since SerializationUID
 *  was initialized in the model class (Note). This allows JVM to use the same version of class.
 *  
 *  Note Taking Screen: All layouts have a title panel which consists of the title of the note. Title corresponds to the stored file name. Therefore, 
 *  there is validation code to check if there are two notes with same note title (file name). Error message is displayed, if duplicate exists
 *  - Outline Layout: Contains a scrollable text area where notes can be written. 
 *  - Cornell Layout: Contains a cue column text area, notes text area and summary text area. All of them are scrollable
 *  - Boxing Layout: Contains boxes (text areas) where notes can be taken. All boxes are scrollable
 *  - Charting Layout: Contains a table where notes can be taken. This includes a column header for each column. All cells are scrollable
 *
 *  File Menu: 
 *  - Save Function: This option saves any type of note to the "saved list" of notes which is accessible through the home screen. Polymorphism and 
 *  inheritance concepts are used to store different types of template content into a single list of notes. Each note-taking method has its own child
 *  model class, while there is a single parent Note class to store any type of note. When clicked, there is validation to check if a file with the same
 *  name exists
 *  - Delete Function:  This option deletes any file by removing it from the "saved list" of notes.
 *  - Import Content Function: This option imports external file content from file explorer. This is implemented through a JFileChooser. This option
 *  is only available in the outline method of note-taking and supports .txt files only
 *  
 *  Format Menu: This menu has option to increase/ decrease font size of content inside the note. This includes all types of layouts.
 *  
 *  Pomodoro Menu: This menu is created for productivity purposes. It lets the user know how long to work and how long to take break.
 *  It creates a running timer on the side which keeps track of time. This timer can be started, paused and removed completly. Also, there is 
 *  a help menu item called "What is Pomodoro" which gives information about the pomodoro technique
 *  
 *  User Analytics Screen: This is a productivity feature which keeps track of how much time is spent on each note, and time spent compared to other
 *  notes. External library (JFreeChart) is used to implement bar graphs and pie charts
 *  
 *  
 * Areas of Concern: None
 * 
 * Major Skills: File reading and writing, Java swing GUI (JFileChooser, JCombobox, Grid Layout, Box Layout, Border Layout, Scroll Pane), 
 * data structures (Hash maps, ArrayList), Java Timer, using external APIs and libraries (JFreeChart library),  Object-Oriented Programming (Polymorphism and Inheritance), 
 * Prototyping, Planning, Testing, Modular Programming
 *
 * Requirements: Add jar files by selecting all the files in the "lib" folder
 *  
 */
public class VirtualNotebookApp {

	//Main
	public static void main(String args[]) {
	   
		//Call the constructor of the controller class
		new AppController();
	  }
	
}

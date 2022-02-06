package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Note;

/*
 * This class writes object data to a file, loads up sample templates and any previously 
 * saved notes (stored as arraylist of object). Uses concepts of serialization and deserialization
 */
public class Loader {

	// This method saves ArrayList of Note objects to file by converting the state
	// of the object
	public static void writeDataToFile() {

		try {
			// Create a stream
			// https://www.javatpoint.com/serialization-in-java
			FileOutputStream fout = new FileOutputStream("notes.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);

			// write the object
			out.writeObject(AppController.notesList);
			out.flush();

			// close the stream
			out.close();

			// Print any error
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * This method reads object data from text file and stores it the controller's
	 * Arraylist datastruture of Note objects
	 */
	public static boolean readDataFromFile() {

		// Open the file which consists information
		File file = new File("notes.txt");

		// If the file doesn't contain anything, then return false
		if (file.length() == 0) {
			return false;
		}

		// If the file contains object data,
		else {

			// https://www.javatpoint.com/serialization-in-java
			// Create stream to read the object
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream("notes.txt"));
				
				//Store object data in the class data structure. Explicit cast of the data structure type needed
				ArrayList<Note> notesList = (ArrayList<Note>) input.readObject();
				AppController.notesList = notesList;
				
				//Close the input
				input.close();
				
			  //Print the exception if there is any
			} catch (Exception exception) {
				System.out.println(exception);
			}
		}

		//Return true if file contains object data
		return true;

	}

}	//End of class

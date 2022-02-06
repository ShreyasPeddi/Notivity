package model;

import java.io.Serializable;

/*
 * This class represents a Note model parent class. This model class is used to store information 
 * regarding any type of note. Child class specialize into: BoxingNote, CornellNote, ChartingNote and
 * OutlineNote
 */
public class Note implements Serializable {

	// Fields
	private String title; // Title of the note
	private int fontSize; // Font size of the note
	private boolean visible = true; // Visibility on GUI
	private int timeSpent = 0; // Time spent on the note

	// JVM uses this ID to compare the versions of the class ensuring that the same
	// class was used during Serialization
	// is loaded during Deserialization:
	// https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it#:~:text=SerialVersionUID%20is%20a%20unique%20identifier,if%20you%20don't%20specify.
	private static final long serialVersionUID = -6903933977591709194L;

	// Constructor
	public Note(String title) {
		super();
		this.title = title;
	}

	// GETTERS AND SETTERS
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	@Override
	public String toString() {
		return "Note [title=" + title + ", fontSize=" + fontSize + ", visible=" + visible + "]";
	}

}

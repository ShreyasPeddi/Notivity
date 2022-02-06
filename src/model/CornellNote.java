package model;

import java.io.Serializable;
import java.util.Arrays;

/*
 * This class is a child class of Note model class. It specializes in storing cornell note template
 * information
 */
public class CornellNote extends Note implements Serializable{

	/*
	 * 0 - cue column
	 * 1 - notes column
	 * 2 - summary column
	 */
	private String[] data = new String[3]; //Stores column information in elements of the array
	
	//Constructor
	public CornellNote(String title) {
		super(title);
	}

	//GETERS AND SETTERS
	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("%s, Data: %s",super.toString(),Arrays.deepToString(data));

	}
	
	

}

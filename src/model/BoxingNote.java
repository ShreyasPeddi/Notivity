package model;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * This class is a child class of Note model class. It specializes in storing boxing note template
 * information
 */
public class BoxingNote extends Note implements Serializable{

	//Fields
	private ArrayList<String> data = new ArrayList<String>(); //Stores box content in each index of the array
	
	//Constructor
	public BoxingNote(String title) {
		super(title);
	}

	//GETTERS AND SETTERS
	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BoxingNote [data=" + data + "]";
	}
	
	
	
}

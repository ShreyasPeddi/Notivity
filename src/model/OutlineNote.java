package model;

import java.io.Serializable;

/*
 * This class is a child class of Note model class. It specializes in storing charting outline template
 * information
 */
public class OutlineNote extends Note implements Serializable{

	//Fields
	private String data; //Text information stores in a single variable
	
	//Constructor
	public OutlineNote(String title) {
		super(title);
	}

	//GETTERS AND SETTERS
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("%s, Data: %s",super.toString(),data);

	}
	
	

}

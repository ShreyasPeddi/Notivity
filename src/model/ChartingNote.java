package model;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * This class is a child class of Note model class. It specializes in storing charting note template
 * information
 */
public class ChartingNote extends Note implements Serializable{

	//Stores column information in each value of the arraylist
	private ArrayList<String> data[] = new ArrayList[2]; //0 - column header, 1 - notes
	
	//Constructor
	public ChartingNote(String title) {
		super(title);
		
		//Intialize the arraylist
		for(int index=0;index<2;index++) {
			data[index]=new ArrayList<String>();
		}
	}
	
	//GETTERS AND SETTERS
	public ArrayList<String>[] getData() {
		return data;
	}

	public void setData(ArrayList<String>[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ChartingNote [data=" + data + "]";
	}
	
	

}

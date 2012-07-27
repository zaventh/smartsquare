package br.com.condesales.models;

import java.util.ArrayList;

public class Photos {

	private int count;
	
	private String summary;

	private ArrayList<PhotoItem> items;
	
	private PhotosGroup groups;

	public PhotosGroup getGroups() {
		return groups;
	}

	public String getSummary() {
		return summary;
	}

	public int getCount() {
		return count;
	}

	public ArrayList<PhotoItem> getItems() {
		return items;
	}
}

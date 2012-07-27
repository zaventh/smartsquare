package br.com.condesales.models;

import java.util.ArrayList;


public class PhotosGroup {
	
	private int count;
	
	private String type;
	
	private ArrayList<PhotoItem> items;
	
	private String name;

	public int getCount() {
		return count;
	}

	public String getType() {
		return type;
	}

	public ArrayList<PhotoItem> getItems() {
		return items;
	}

	public String getName() {
		return name;
	}

}

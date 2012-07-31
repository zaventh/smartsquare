package br.com.condesales.models;

public class Category {

	private String id;

	private String name;

	private String pluralName;

	private String shortName;

	//TODO Icon model commented. It is currently left a side while foursquare api diverges in some venue responses.
	private Icon icon;
	public Icon getIcon() {
		return icon;
	}

	private String[] parents;

	private boolean primary;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public String[] getParents() {
		return parents;
	}

	public boolean isPrimary() {
		return primary;
	}


	public String getShortName() {
		return shortName;
	}

}

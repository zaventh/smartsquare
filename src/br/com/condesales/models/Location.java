package br.com.condesales.models;

public class Location {

	private String address;

	private String city;

	private String state;

	private String postalCode;

	private String country;

	private String cc;

	private double lat;

	private double lng;

	private double distance;

	private String postalcode;

	private String crossStreet;

	public String getPostalcode() {
		return postalcode;
	}

	public String getCrossStreet() {
		return crossStreet;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public double getDistance() {
		return distance;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

}

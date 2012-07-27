package br.com.condesales.models;

import android.graphics.Bitmap;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String homeCity;
	private String bio;
	private UserContact contact;
	private Bitmap bitmapPhoto;
	private String relationship;
	private UserPhoto photo;

	public String getRelationship() {
		return relationship;
	}

	public String getPhoto() {
		return photo.getPrefix() + photo.getSuffix();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public UserContact getContact() {
		return contact;
	}

	public void setContact(UserContact contact) {
		this.contact = contact;
	}

	public Bitmap getBitmapPhoto() {
		return bitmapPhoto;
	}

	public void setBitmapPhoto(Bitmap bitmapPhoto) {
		this.bitmapPhoto = bitmapPhoto;
	}

}

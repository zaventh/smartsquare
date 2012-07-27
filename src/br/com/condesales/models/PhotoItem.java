package br.com.condesales.models;

public class PhotoItem {

	private String id;

	private long createdAt;

	private int height;
	private int width;

	private String prefix;
	private String suffix;

	private User user;

	private Checkin checkin;
	private String visibility;

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getId() {
		return id;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public Checkin getCheckin() {
		return checkin;
	}

	public User getUser() {
		return user;
	}

	public String getVisibility() {
		return visibility;
	}

}

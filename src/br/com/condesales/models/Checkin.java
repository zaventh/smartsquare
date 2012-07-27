package br.com.condesales.models;

public class Checkin {

	private int count;

	private String id;

	private long createdAt;

	private String type;

	private String shout;

	private String timeZone;

	private long timeZoneOffset;

	private Venue venue;

	private boolean like;

	private Photos photos;

	private Score score;

	private User user;

	public Score getScore() {
		return score;
	}

	public User getUser() {
		return user;
	}

	public int getCount() {
		return count;
	}

	public String getId() {
		return id;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public String getType() {
		return type;
	}

	public String getShout() {
		return shout;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public long getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public Venue getVenue() {
		return venue;
	}

	public boolean isLike() {
		return like;
	}

	public Photos getPhotos() {
		return photos;
	}

}

package br.com.condesales.models;

import java.util.ArrayList;

public class Venue {

	private String id;

	private String name;

	private Location location;

	private ArrayList<Category> categories;

	private boolean verified;

	private Statistics stats;

	private HereNow beenHere;

	private HereNow hereNow;

	private long createdAt;

	private Mayor mayor;

	private String timeZone;

	private String canonicalUrl;

	private String shortUrl;

	private boolean dislake;

	public String getTimeZone() {
		return timeZone;
	}

	public String getCanonicalUrl() {
		return canonicalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public boolean isDislake() {
		return dislake;
	}

	public HereNow getHereNow() {
		return hereNow;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public Mayor getMayor() {
		return mayor;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public boolean isVerified() {
		return verified;
	}

	public Statistics getStats() {
		return stats;
	}

	public HereNow getBeenHere() {
		return beenHere;
	}

}

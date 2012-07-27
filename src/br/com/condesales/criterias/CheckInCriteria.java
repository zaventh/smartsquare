package br.com.condesales.criterias;

import android.location.Location;

public class CheckInCriteria {

	public enum BroadCastType {
		PRIVATE("private"), PUBLIC("public");

		private String type;

		private BroadCastType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	private String venueId;
	private BroadCastType broadcast = BroadCastType.PUBLIC;
	private String eventId;
	private String shout;
	private Location location;

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public BroadCastType getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(BroadCastType broadcast) {
		this.broadcast = broadcast;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getShout() {
		return shout;
	}

	public void setShout(String shout) {
		this.shout = shout;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}

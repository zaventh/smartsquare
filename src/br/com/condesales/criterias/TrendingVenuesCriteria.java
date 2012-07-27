package br.com.condesales.criterias;

import android.location.Location;
import android.location.LocationManager;

public class TrendingVenuesCriteria {
	
	private Integer mlimit=10;
	private Location mLocation = new Location(LocationManager.GPS_PROVIDER);
	private Integer mRadius=100;
	
	public Integer getlimit() {
		return mlimit;
	}
	public void setlimit(Integer mlimit) {
		this.mlimit = mlimit;
	}
	public Location getLocation() {
		return mLocation;
	}
	public void setLocation(Location mLocation) {
		this.mLocation = mLocation;
	}
	public Integer getRadius() {
		return mRadius;
	}
	public void setRadius(Integer mRadius) {
		this.mRadius = mRadius;
	}
	
}

package br.com.condesales.models;

import java.io.Serializable;

public class CheckinItem implements Serializable {

	private static final long serialVersionUID = -6210250259719738812L;

	public String id;
	
	public long createdAt;
	
	public String type;
	
	public String timeZone;
	
	public Venue venue;
	
}

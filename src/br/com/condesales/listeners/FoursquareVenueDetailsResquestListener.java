package br.com.condesales.listeners;

import br.com.condesales.models.Venue;

public interface FoursquareVenueDetailsResquestListener extends ErrorListener {
	
	public void onVenueDetailFetched(Venue venues);
	
}

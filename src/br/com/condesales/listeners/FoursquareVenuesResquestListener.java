package br.com.condesales.listeners;

import java.util.ArrayList;

import br.com.condesales.models.Venue;

public interface FoursquareVenuesResquestListener extends ErrorListener {
	
	public void onVenuesFetched(ArrayList<Venue> venues);
	
}

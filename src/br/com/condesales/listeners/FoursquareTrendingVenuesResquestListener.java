package br.com.condesales.listeners;

import java.util.ArrayList;

import br.com.condesales.models.Venue;

public interface FoursquareTrendingVenuesResquestListener extends ErrorListener {
	
	public void onTrendedVenuesFetched(ArrayList<Venue> venues);
	
}

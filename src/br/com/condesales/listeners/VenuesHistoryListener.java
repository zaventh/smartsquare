package br.com.condesales.listeners;

import java.util.ArrayList;

import br.com.condesales.models.Venues;

public interface VenuesHistoryListener extends ErrorListener {

	public void onGotVenuesHistory(ArrayList<Venues> list);

}

package br.com.condesales.listeners;

import br.com.condesales.models.Checkin;



public interface CheckInListener extends ErrorListener {

	public void onCheckInDone(Checkin checkin);
	
}

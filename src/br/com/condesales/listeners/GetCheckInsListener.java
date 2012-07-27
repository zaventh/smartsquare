package br.com.condesales.listeners;

import java.util.ArrayList;

import br.com.condesales.models.Checkin;



public interface GetCheckInsListener extends ErrorListener {

	public void onGotCheckIns(ArrayList<Checkin> list);
	
}

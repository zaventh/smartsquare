package br.com.condesales.listeners;

public interface AccessTokenRequestListener extends ErrorListener {

	public void onAccessGrant(String accessToken);
	
}

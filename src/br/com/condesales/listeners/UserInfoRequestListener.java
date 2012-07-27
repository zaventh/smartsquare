package br.com.condesales.listeners;

import br.com.condesales.models.User;

public interface UserInfoRequestListener extends ErrorListener {

	public void onUserInfoFetched(User user);
}

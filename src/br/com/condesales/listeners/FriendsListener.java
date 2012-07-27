package br.com.condesales.listeners;

import java.util.ArrayList;

import br.com.condesales.models.User;

public interface FriendsListener extends ErrorListener {

	public void onGotFriends(ArrayList<User> list);
	
}

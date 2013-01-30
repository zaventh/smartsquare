package br.com.condesales;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import br.com.condesales.constants.FoursquareConstants;
import br.com.condesales.criterias.CheckInCriteria;
import br.com.condesales.criterias.TrendingVenuesCriteria;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.listeners.AccessTokenRequestListener;
import br.com.condesales.listeners.CheckInListener;
import br.com.condesales.listeners.FoursquareTrendingVenuesResquestListener;
import br.com.condesales.listeners.FoursquareVenueDetailsResquestListener;
import br.com.condesales.listeners.FoursquareVenuesResquestListener;
import br.com.condesales.listeners.FriendsListener;
import br.com.condesales.listeners.GetCheckInsListener;
import br.com.condesales.listeners.UserInfoRequestListener;
import br.com.condesales.listeners.VenuesHistoryListener;
import br.com.condesales.tasks.checkins.CheckInRequest;
import br.com.condesales.tasks.users.GetCheckInsRequest;
import br.com.condesales.tasks.users.GetFriendsRequest;
import br.com.condesales.tasks.users.GetUserVenuesHistoryRequest;
import br.com.condesales.tasks.users.SelfInfoRequest;
import br.com.condesales.tasks.venues.FoursquareTrendingVenuesNearbyRequest;
import br.com.condesales.tasks.venues.FoursquareVenueDetailsRequest;
import br.com.condesales.tasks.venues.FoursquareVenuesNearbyRequest;

/**
 * Class to handle methods used to perform requests to FoursquareAPI and respond
 * ASYNChronously.
 * 
 * @author Felipe Conde <condesales@gmail.com>
 * 
 */
public class EasyFoursquareAsync
{

	private Context mActivity;
	private FoursquareDialog mDialog;
	private String mAccessToken = "";

	public EasyFoursquareAsync(Context activity)
	{
		mActivity = activity;
	}

	/**
	 * Requests the access to API
	 */
	public void requestAccess(AccessTokenRequestListener listener)
	{
		if (!hasAccessToken())
		{
			loginDialog(listener);
		}
		else
		{
			listener.onAccessGrant(getAccessToken());
		}
	}

	/**
	 * Requests logged user information asynchronously.
	 * 
	 * @param listener
	 *            As the request is asynchronous, listener used to retrieve the
	 *            User object, containing the information.
	 */
	public void getUserInfo(UserInfoRequestListener listener)
	{
		SelfInfoRequest request = new SelfInfoRequest(mActivity, listener);
		request.execute(getAccessToken());
	}

	/**
	 * Requests the nearby Venues.
	 * 
	 * @param criteria
	 *            The criteria to your search request
	 * @param listener
	 *            As the request is asynchronous, listener used to retrieve the
	 *            User object, containing the information.
	 */
	public void getVenuesNearby(FoursquareVenuesResquestListener listener, VenuesCriteria criteria)
	{
		FoursquareVenuesNearbyRequest request = new FoursquareVenuesNearbyRequest(mActivity, listener, criteria);
		request.execute(getAccessToken());
	}

	/**
	 * Requests the nearby Venus that are trending.
	 * 
	 * @param listener
	 *            As the request is asynchronous, listener used to retrieve the
	 *            User object, containing the information.
	 * @param criteria
	 *            The criteria to your search request
	 */
	public void getTrendingVenuesNearby(FoursquareTrendingVenuesResquestListener listener, TrendingVenuesCriteria criteria)
	{
		FoursquareTrendingVenuesNearbyRequest request = new FoursquareTrendingVenuesNearbyRequest(mActivity, listener, criteria);
		request.execute(getAccessToken());

	}

	public void getVenueDetail(String venueID, FoursquareVenueDetailsResquestListener listener)
	{
		FoursquareVenueDetailsRequest request = new FoursquareVenueDetailsRequest(mActivity, listener, venueID);
		request.execute(getAccessToken());
	}

	/**
	 * Checks in at a venue.
	 * 
	 * @param listener
	 *            As the request is asynchronous, listener used to retrieve the
	 *            User object, containing the information about the check in.
	 * @param criteria
	 *            The criteria to your search request
	 */
	public void checkIn(CheckInListener listener, CheckInCriteria criteria)
	{
		CheckInRequest request = new CheckInRequest(mActivity, listener, criteria);
		request.execute(getAccessToken());
	}

	public void getCheckIns(GetCheckInsListener listener)
	{
		GetCheckInsRequest request = new GetCheckInsRequest(mActivity, listener);
		request.execute(getAccessToken());
	}

	public void getCheckIns(GetCheckInsListener listener, String userID)
	{
		GetCheckInsRequest request = new GetCheckInsRequest(mActivity, listener, userID);
		request.execute(getAccessToken());
	}

	public void getFriends(FriendsListener listener)
	{
		GetFriendsRequest request = new GetFriendsRequest(mActivity, listener);
		request.execute(mAccessToken);
	}

	public void getFriends(FriendsListener listener, String userID)
	{
		GetFriendsRequest request = new GetFriendsRequest(mActivity, listener, userID);
		request.execute(getAccessToken());
	}

	public void getVenuesHistory(VenuesHistoryListener listener)
	{
		GetUserVenuesHistoryRequest request = new GetUserVenuesHistoryRequest(mActivity, listener);
		request.execute(getAccessToken());
	}

	public void getVenuesHistory(VenuesHistoryListener listener, String userID)
	{
		GetUserVenuesHistoryRequest request = new GetUserVenuesHistoryRequest(mActivity, listener, userID);
		request.execute(getAccessToken());
	}

	public boolean hasAccessToken()
	{
		String token = getAccessToken();
		return !token.equals("");
	}

	private String getAccessToken()
	{
		if (mAccessToken.equals(""))
		{
			SharedPreferences settings = mActivity.getSharedPreferences(FoursquareConstants.SHARED_PREF_FILE, 0);
			mAccessToken = settings.getString(FoursquareConstants.ACCESS_TOKEN, "");
		}
		return mAccessToken;
	}

	/**
	 * Requests the Foursquare login though a dialog.
	 */
	private void loginDialog(AccessTokenRequestListener listener)
	{
		String url = "https://foursquare.com/oauth2/authenticate" + "?client_id=" + FoursquareConstants.CLIENT_ID + "&response_type=code" + "&redirect_uri="
				+ FoursquareConstants.CALLBACK_URL;

		mDialog = new FoursquareDialog(mActivity, url, listener);
		mDialog.show();
	}

}

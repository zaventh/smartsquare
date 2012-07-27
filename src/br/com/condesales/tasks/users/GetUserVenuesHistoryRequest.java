package br.com.condesales.tasks.users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.condesales.constants.FoursquareConstants;
import br.com.condesales.listeners.VenuesHistoryListener;
import br.com.condesales.models.Venues;

import com.google.gson.Gson;

public class GetUserVenuesHistoryRequest extends
		AsyncTask<String, Integer, ArrayList<Venues>> {

	private Activity mActivity;
	private ProgressDialog mProgress;
	private VenuesHistoryListener mListener;
	private String mUserID = "self";// default value

	/**
	 * Async constructor (userID gonna be self)
	 * 
	 * @param activity
	 *            the context to show progress
	 * @param listener
	 *            the listener where the async request shoud respont to
	 */
	public GetUserVenuesHistoryRequest(Activity activity, VenuesHistoryListener listener) {
		mActivity = activity;
		mListener = listener;
	}

	/**
	 * Async constructor
	 * 
	 * @param activity
	 *            the context to show progress
	 * @param listener
	 *            the listener where the async request shoud respont to
	 * @param userID
	 *            The id from user to get information
	 */
	public GetUserVenuesHistoryRequest(Activity activity, VenuesHistoryListener listener,
			String userID) {
		mActivity = activity;
		mListener = listener;
		mUserID = userID;
	}

	/**
	 * Sync constructor (userID gonna be self)
	 * 
	 * @param activity
	 *            the context to show progress
	 */
	public GetUserVenuesHistoryRequest(Activity activity) {
		mActivity = activity;
	}

	/**
	 * Sync constructor
	 * 
	 * @param activity
	 *            the context to show progress
	 * @param userID
	 *            The id from user to get information
	 */
	public GetUserVenuesHistoryRequest(Activity activity, String userID) {
		mActivity = activity;
		mUserID = userID;
	}

	@Override
	protected void onPreExecute() {
		mProgress = new ProgressDialog(mActivity);
		mProgress.setCancelable(false);
		mProgress.setMessage("Getting Venues History ...");
		mProgress.show();
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Venues> doInBackground(String... params) {

		String access_token = params[0];
		Venues venue = null;
		ArrayList<Venues> list = new ArrayList<Venues>();
		try {
			// date required
			String apiDateVersion = FoursquareConstants.API_DATE_VERSION;
			// Call Foursquare to post checkin
			JSONObject venuesJson = executeHttpGet("https://api.foursquare.com/v2/users/"
					+ mUserID
					+ "/venuehistory"
					+ "?v="
					+ apiDateVersion
					+ "&oauth_token=" + access_token);

			// Get return code
			int returnCode = Integer.parseInt(venuesJson.getJSONObject("meta")
					.getString("code"));
			// 200 = OK
			if (returnCode == 200) {

				Gson gson = new Gson();
				JSONArray json = venuesJson.getJSONObject("response")
						.getJSONObject("venues").getJSONArray("items");
				for (int i = 0; i < json.length(); i++) {
					venue = gson.fromJson(json.get(i).toString(),
							Venues.class);
					list.add(venue);
				}
			} else {
				if (mListener != null)
					mListener.onError(venuesJson.getJSONObject("meta")
							.getString("errorDetail"));
			}

		} catch (Exception exp) {
			exp.printStackTrace();
			if (mListener != null)
				mListener.onError(exp.toString());
		}
		return list;
	}

	@Override
	protected void onPostExecute(ArrayList<Venues> friendsList) {
		mProgress.dismiss();
		if (mListener != null)
			mListener.onGotVenuesHistory(friendsList);
		super.onPostExecute(friendsList);
	}

	// Calls a URI and returns the answer as a JSON object
	private JSONObject executeHttpGet(String uri) throws Exception {
		HttpGet req = new HttpGet(uri);

		HttpClient client = new DefaultHttpClient();
		HttpResponse resLogin = client.execute(req);
		BufferedReader r = new BufferedReader(new InputStreamReader(resLogin
				.getEntity().getContent()));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while ((s = r.readLine()) != null) {
			sb.append(s);
		}

		return new JSONObject(sb.toString());
	}
}

package br.com.condesales.tasks.venues;

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
import br.com.condesales.criterias.TrendingVenuesCriteria;
import br.com.condesales.listeners.FoursquareTrendingVenuesResquestListener;
import br.com.condesales.models.Venue;

import com.google.gson.Gson;

public class FoursquareTrendingVenuesNearbyRequest extends
		AsyncTask<String, Integer, ArrayList<Venue>> {

	private Activity mActivity;
	private ProgressDialog mProgress;
	private FoursquareTrendingVenuesResquestListener mListener;
	private TrendingVenuesCriteria mCriteria;

	public FoursquareTrendingVenuesNearbyRequest(Activity activity,
			FoursquareTrendingVenuesResquestListener listener, TrendingVenuesCriteria criteria) {
		mActivity = activity;
		mListener = listener;
		mCriteria = criteria;
		
	}

	public FoursquareTrendingVenuesNearbyRequest(Activity activity, TrendingVenuesCriteria criteria) {
		mActivity = activity;
		mCriteria = criteria;
	}

	@Override
	protected void onPreExecute() {
		mProgress = new ProgressDialog(mActivity);
		mProgress.setCancelable(false);
		mProgress.setMessage("Getting trending venues nearby ...");
		mProgress.show();
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Venue> doInBackground(String... params) {

		String access_token = params[0];
		ArrayList<Venue> venues = new ArrayList<Venue>();

		try {
			
			//date required
			String apiDate = FoursquareConstants.API_DATE_VERSION;
			// Call Foursquare to get the Venues around
			JSONObject venuesJson = executeHttpGet("https://api.foursquare.com/v2/venues/trending?"
					+ "?v="
					+ apiDate
					+ "&ll="
					+ mCriteria.getLocation().getLatitude()
					+ ","
					+ mCriteria.getLocation().getLongitude()
					+ "&llAcc="
					+ mCriteria.getLocation().getAccuracy()
					+ "&limit="
					+ mCriteria.getlimit()
					+ "&radius="
					+ mCriteria.getRadius()+ "&oauth_token=" + access_token);

			
			// Get return code
			int returnCode = Integer.parseInt(venuesJson.getJSONObject("meta")
					.getString("code"));
			// 200 = OK
			if (returnCode == 200) {
				Gson gson = new Gson();
				JSONArray json = venuesJson.getJSONObject("response")
						.getJSONArray("venues");
				for (int i = 0; i < json.length(); i++) {
					Venue venue = gson.fromJson(json.getJSONObject(i)
							.toString(), Venue.class);
					venues.add(venue);
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
		return venues;
	}

	@Override
	protected void onPostExecute(ArrayList<Venue> venues) {
		mProgress.dismiss();
		if (mListener != null)
			mListener.onTrendedVenuesFetched(venues);
		super.onPostExecute(venues);
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

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
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.listeners.FoursquareVenuesResquestListener;
import br.com.condesales.models.Venue;

import com.google.gson.Gson;

public class FoursquareVenuesNearbyRequest extends
		AsyncTask<String, Integer, ArrayList<Venue>> {

	private Activity mActivity;
	private ProgressDialog mProgress;
	private FoursquareVenuesResquestListener mListener;
	private VenuesCriteria mCriteria;

	public FoursquareVenuesNearbyRequest(Activity activity,
			FoursquareVenuesResquestListener listener, VenuesCriteria criteria) {
		mActivity = activity;
		mListener = listener;
		mCriteria = criteria;
	}

	public FoursquareVenuesNearbyRequest(Activity activity, VenuesCriteria criteria) {
		mActivity = activity;
		mCriteria = criteria;
	}

	@Override
	protected void onPreExecute() {
		mProgress = new ProgressDialog(mActivity);
		mProgress.setCancelable(false);
		mProgress.setMessage("Getting venues nearby ...");
		mProgress.show();
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Venue> doInBackground(String... params) {

		String access_token = params[0];
		ArrayList<Venue> venues = new ArrayList<Venue>();

		try {
			
			//date required
			String apiDateVersion = FoursquareConstants.API_DATE_VERSION;
			// Call Foursquare to get the Venues around
			JSONObject venuesJson = executeHttpGet("https://api.foursquare.com/v2/venues/search"
					+ "?v="
					+ apiDateVersion
					+ "&ll="
					+ mCriteria.getLocation().getLatitude()
					+ ","
					+ mCriteria.getLocation().getLongitude()
					+ "&llAcc="
					+ mCriteria.getLocation().getAccuracy()
					+ "&query="
					+ mCriteria.getQuery()
					+ "&limit="
					+ mCriteria.getQuantity()
					+ "&intent="
					+ mCriteria.getIntent().getValue()
					+ "&radius="
					+ mCriteria.getRadius() + "&oauth_token=" + access_token);

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
			mListener.onVenuesFetched(venues);
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

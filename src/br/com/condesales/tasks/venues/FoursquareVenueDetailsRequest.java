package br.com.condesales.tasks.venues;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.condesales.constants.FoursquareConstants;
import br.com.condesales.listeners.FoursquareVenueDetailsResquestListener;
import br.com.condesales.models.Venue;

import com.google.gson.Gson;

public class FoursquareVenueDetailsRequest extends
		AsyncTask<String, Integer, Venue> {

	private Activity mActivity;
	private ProgressDialog mProgress;
	private FoursquareVenueDetailsResquestListener mListener;
	private String mVenueID;

	public FoursquareVenueDetailsRequest(Activity activity,
			FoursquareVenueDetailsResquestListener listener, String venueID) {
		mActivity = activity;
		mListener = listener;
		mVenueID = venueID;
	}

	public FoursquareVenueDetailsRequest(Activity activity, String venueID) {
		mActivity = activity;
		mVenueID = venueID;
	}

	@Override
	protected void onPreExecute() {
		mProgress = new ProgressDialog(mActivity);
		mProgress.setCancelable(false);
		mProgress.setMessage("Getting venue details ...");
		mProgress.show();
		super.onPreExecute();
	}

	@Override
	protected Venue doInBackground(String... params) {

		String access_token = params[0];
		Venue venue = null;

		try {
			
			//date required
			String apiDateVersion = FoursquareConstants.API_DATE_VERSION;
			// Call Foursquare to get the Venues around
			JSONObject venuesJson = executeHttpGet("https://api.foursquare.com/v2/venues/"+mVenueID
					+ "?v="
					+ apiDateVersion
					+ "&oauth_token=" + access_token);

			// Get return code
			int returnCode = Integer.parseInt(venuesJson.getJSONObject("meta")
					.getString("code"));
			// 200 = OK
			if (returnCode == 200) {
				Gson gson = new Gson();
				JSONObject json = venuesJson.getJSONObject("response")
						.getJSONObject("venue");
				venue = gson.fromJson(json.toString(), Venue.class);
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
		return venue;
	}

	@Override
	protected void onPostExecute(Venue venues) {
		mProgress.dismiss();
		if (mListener != null)
			mListener.onVenueDetailFetched(venues);
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

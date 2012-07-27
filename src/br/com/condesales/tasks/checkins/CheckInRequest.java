package br.com.condesales.tasks.checkins;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import br.com.condesales.constants.FoursquareConstants;
import br.com.condesales.criterias.CheckInCriteria;
import br.com.condesales.listeners.CheckInListener;
import br.com.condesales.models.Checkin;

import com.google.gson.Gson;
public class CheckInRequest extends AsyncTask<String, Integer, Checkin> {

	private Activity mActivity;
	private ProgressDialog mProgress;
	private CheckInListener mListener;
	private CheckInCriteria mCriteria;

	/**
	 * Async constructor
	 * 
	 * @param activity
	 *            the context to show progress
	 * @param criteria
	 *            the object containing all the params from check in
	 * @param listener
	 *            the listener where the async request shoud respont to
	 */
	public CheckInRequest(Activity activity, CheckInListener listener,
			CheckInCriteria criteria) {
		mActivity = activity;
		mListener = listener;
		mCriteria = criteria;
	}

	/**
	 * Sync constructor
	 * 
	 * @param activity
	 *            the context to show progress
	 * @param criteria
	 *            the object containing all the params from check in
	 */
	public CheckInRequest(Activity activity, CheckInCriteria criteria) {
		mActivity = activity;
		mCriteria = criteria;
	}

	@Override
	protected void onPreExecute() {
		mProgress = new ProgressDialog(mActivity);
		mProgress.setCancelable(false);
		mProgress.setMessage("Checking in ...");
		mProgress.show();
		super.onPreExecute();
	}

	@Override
	protected Checkin doInBackground(String... params) {

		String access_token = params[0];
		Checkin checkin = null;
		try {
			// Call Foursquare to post checkin
			JSONObject venuesJson = executeHttpPost(
					"https://api.foursquare.com/v2/checkins/add", access_token,
					mCriteria);

			// Get return code
			int returnCode = Integer.parseInt(venuesJson.getJSONObject("meta")
					.getString("code"));
			// 200 = OK
			if (returnCode == 200) {
				Gson gson = new Gson();
				JSONObject json = venuesJson.getJSONObject("response").getJSONObject("checkin");
				checkin = gson.fromJson(json.toString(), Checkin.class);
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
		return checkin;
	}

	@Override
	protected void onPostExecute(Checkin checkin) {
		mProgress.dismiss();
		if (mListener != null)
			mListener.onCheckInDone(checkin);
		super.onPostExecute(checkin);
	}

	/**
	 * Calls a URI and returns the answer as a JSON object
	 * 
	 * @param uri
	 *            the uri to request
	 * @param accessToken
	 *            the access token
	 * @param criteria
	 *            the object that contains all the params
	 * @return JSONObject containing the result
	 * @throws Exception
	 *             some exceptions :P
	 */
	private JSONObject executeHttpPost(String uri, String accessToken,
			CheckInCriteria criteria) throws Exception {
		HttpPost req = new HttpPost(uri);
		HttpClient client = new DefaultHttpClient();
		
		//date required
		String apiDateVersion = FoursquareConstants.API_DATE_VERSION;
		// create the params lists, an add some info on it..
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("v", apiDateVersion));
		params.add(new BasicNameValuePair("venueId", criteria.getVenueId()));
		if (criteria.getEventId() != null && !criteria.getEventId().equals("")) 
			params.add(new BasicNameValuePair("eventId", criteria.getEventId()));
		// only shout if you have something...
		if (criteria.getShout() != null && !criteria.getShout().equals(""))
			params.add(new BasicNameValuePair("shout", criteria.getShout()));
		params.add(new BasicNameValuePair("broadcast", criteria.getBroadcast()
				.getType()));
		// just send location when you have it...
		if (criteria.getLocation() != null) {
			Location location = criteria.getLocation();
			params.add(new BasicNameValuePair("ll", location.getLatitude()
					+ "," + location.getLongitude()));
			params.add(new BasicNameValuePair("llAcc", location.getAccuracy()
					+ ""));
		}
		params.add(new BasicNameValuePair("oauth_token", accessToken));
		// putting the params to the post request
		req.setEntity(new UrlEncodedFormEntity(params));

		HttpResponse resCheckin = client.execute(req);
		BufferedReader r = new BufferedReader(new InputStreamReader(resCheckin
				.getEntity().getContent()));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while ((s = r.readLine()) != null) {
			sb.append(s);
		}
		return new JSONObject(sb.toString());
	}
}

package br.com.condesales.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.com.condesales.constants.FoursquareConstants;
import br.com.condesales.listeners.AccessTokenRequestListener;

public class AccessTokenRequest extends AsyncTask<String, Integer, String> {

	private Activity mActivity;
	private ProgressDialog mProgress;
	private AccessTokenRequestListener mListener;

	public AccessTokenRequest(Activity activity, AccessTokenRequestListener listener) {
		mActivity = activity;
		mListener = listener;
	}

	@Override
	protected void onPreExecute() {
		mProgress = new ProgressDialog(mActivity);
		mProgress.setCancelable(false);
		mProgress.setMessage("Getting access token ...");
		mProgress.show();
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {

		String code = params[0];
		String token = "";
		// Check if there is a parameter called "code"
		if (code != null) {
			try {
				// Call Foursquare again to get the access token
				JSONObject tokenJson = executeHttpGet("https://foursquare.com/oauth2/access_token"
						+ "?client_id="
						+ FoursquareConstants.CLIENT_ID
						+ "&client_secret="
						+ FoursquareConstants.CLIENT_SECRET
						+ "&grant_type=authorization_code"
						+ "&redirect_uri=http://localhost:8888"
						+ "&code="
						+ code);

				token = tokenJson.getString("access_token");

				//saving token
				Log.i("Access Token", token);
				SharedPreferences settings = mActivity.getSharedPreferences(FoursquareConstants.SHARED_PREF_FILE, 0);
			    SharedPreferences.Editor editor = settings.edit();
			    editor.putString(FoursquareConstants.ACCESS_TOKEN, token);
			    // Commit the edits!
			    editor.commit();

			} catch (Exception exp) {
				Log.e("LoginTest", "Getting Access token failed", exp);
				mListener.onError(exp.toString());
			}
		} else {
			Toast.makeText(mActivity, "Unknown login error", Toast.LENGTH_SHORT)
					.show();
			mListener.onError("Unknown login error");
		}
		return token;
	}

	@Override
	protected void onPostExecute(String access_token) {
		mProgress.dismiss();
		mListener.onAccessGrant(access_token);
		super.onPostExecute(access_token);
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

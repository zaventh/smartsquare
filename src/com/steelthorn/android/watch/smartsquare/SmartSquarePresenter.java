/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.util.Log;
import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.criterias.VenuesCriteria.VenuesCriteriaIntent;
import br.com.condesales.listeners.FoursquareVenuesResquestListener;
import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 * 
 */
public class SmartSquarePresenter extends BasePresenter<ISmartSquareControlView> implements ISmartSquarePresenter
{
	private static final String TAG = "SmartSquarePresenter";

	public SmartSquarePresenter(ISmartSquareControlView view)
	{
		super(view);
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ISmartSquarePresenter#requestNearbyVenues()
	 */
	@Override
	public void requestNearbyVenues()
	{
		EasyFoursquareAsync api = new EasyFoursquareAsync(getView().getContext());

		VenuesCriteria crit = new VenuesCriteria();
		crit.getLocation().setLatitude(37.779449);
		crit.getLocation().setLongitude(-122.392073);
		crit.setIntent(VenuesCriteriaIntent.CHECKIN);

		api.getVenuesNearby(new FoursquareVenuesResquestListener()
		{

			@Override
			public void onError(String errorMsg)
			{
				Log.e(TAG, "Foursquare API returned an error: " + errorMsg);
				if (getView() != null)
					getView().onError(new Exception(errorMsg));
			}

			@Override
			public void onVenuesFetched(ArrayList<Venue> venues)
			{
				if (getView() != null)
					getView().onNearbyVenuesReceived(venues);

			}
		}, crit);
	}

	@Override
	public void fetchVenueCategoryBitmap(Venue v)
	{
		if (v == null || v.getCategories() == null || v.getCategories().size() < 1)
			return;

		new ImageDownloaderThread(v).start();
	}

	private class ImageDownloaderThread extends Thread
	{
		private final Venue _venue;

		public ImageDownloaderThread(Venue v)
		{
			_venue = v;
		}

		public void run()
		{
			String uri = _venue.getCategories().get(0).getIcon().getPrefix() + "256.png";

			Log.d(TAG, "Downloading " + uri);

			try
			{
				Bitmap b = Util.downloadImageWithCaching(getView().getContext(), uri);

				if (b == null)
					return;

				if (getView() != null)
					getView().onVenueCategoryIconRetrieved(_venue, b);

			}
			catch (Exception e)
			{
				Log.w(TAG, "An error occurred downloading the icon: " + e);
			}
		}
	}
}

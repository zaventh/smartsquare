/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.ArrayList;

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
				getView().onError(new Exception(errorMsg));

			}

			@Override
			public void onVenuesFetched(ArrayList<Venue> venues)
			{
				getView().onNearbyVenuesReceived(venues);

			}
		}, crit);
	}

}

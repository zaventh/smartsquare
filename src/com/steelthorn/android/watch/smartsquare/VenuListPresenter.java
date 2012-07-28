/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.List;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 *
 */
public class VenuListPresenter implements IVenuListPresenter
{

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.IVenuListPresenter#getNearbyVenues()
	 */
	@Override
	public Boolean getNearbyVenues()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.IVenuListPresenter#checkin(br.com.condesales.models.Venue)
	 */
	@Override
	public Boolean checkin(Venue v)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

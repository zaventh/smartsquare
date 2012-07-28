/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.List;

import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 *
 */
public interface IVenuListPresenter
{
	public Boolean getNearbyVenues();
	
	public Boolean checkin(Venue v);
}

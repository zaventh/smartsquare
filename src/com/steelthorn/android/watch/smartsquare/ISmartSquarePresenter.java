/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 *
 */
public interface ISmartSquarePresenter extends IBasePresenter<ISmartSquareControlView>
{
	public void requestNearbyVenues();
	
	public void fetchVenueCategoryBitmap(Venue v);
	
	public void requestCheckin(Venue v);
}

/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 *
 */
public interface ISmartSquareControlView extends IBaseView
{
	Context getContext();
	
	void onNearbyVenuesReceived(List<Venue> venues);
	
	void onVenueCategoryIconRetrieved(Venue v, Bitmap b);
	
	void onCheckinResponse(Boolean success);
	
	void onNotFoursquareAuthenticated();
}

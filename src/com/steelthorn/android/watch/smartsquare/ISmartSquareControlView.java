/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.List;

import br.com.condesales.models.Venue;

import android.content.Context;

/**
 * @author Jeff Mixon
 *
 */
public interface ISmartSquareControlView extends IBaseView
{
	Context getContext();
	
	void onNearbyVenuesReceived(List<Venue> venues);
}

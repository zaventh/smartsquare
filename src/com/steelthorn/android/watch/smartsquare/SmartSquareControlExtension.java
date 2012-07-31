/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import br.com.condesales.models.Venue;

import com.sonyericsson.extras.liveware.aef.control.Control;

/**
 * @author Jeff Mixon
 * 
 */
public class SmartSquareControlExtension extends BaseControlExtensionView implements ISmartSquareControlView
{
	private static final String TAG = "SmartSquareControlExtension";
	private final Context _ctx;

	private List<Venue> _venues;
	private int _venueIndex = -1;
	private final ISmartSquarePresenter _presenter;

	public SmartSquareControlExtension(Context context, String hostAppPackageName)
	{
		super(context, hostAppPackageName);

		_ctx = context;
		_presenter = new SmartSquarePresenter(this);
	}

	@Override
	public void onStart()
	{
		super.onStart();

		_presenter.requestNearbyVenues();
	}

	protected void showBitmap(ExtensionImage image)
	{
		if (image != null)
			showBitmap(image.getBitmap());
	}

	@Override
	public Context getContext()
	{
		return _ctx;
	}

	@Override
	public void onNearbyVenuesReceived(List<Venue> venues)
	{
		_venues = venues;

		if (_venues != null && _venues.size() > 0)
			showVenueFromList(0);
	}

	private void showVenueFromList(int index)
	{
		// TODO: Don't reload the image each time when swiping
		//if (index == _venueIndex)
		//	return;
		
		Venue v = _venues.get(index);
		ExtensionImage img = new VenuImage(_ctx, v);
		showBitmap(img);
		_venueIndex = index;

		_presenter.fetchVenueCategoryBitmap(v);
	}

	@Override
	public void onSwipe(int direction)
	{
		if (_venues == null)
		{
			Log.d(TAG, "Venue list is empty.");
			return;
		}

		if (direction == Control.Intents.SWIPE_DIRECTION_RIGHT) 
			showVenueFromList(++_venueIndex > (_venues.size() - 1) ? _venues.size() - 1 : _venueIndex);
		else if (direction == Control.Intents.SWIPE_DIRECTION_LEFT)
			showVenueFromList(--_venueIndex < 0 ? 0 : _venueIndex);

	}

	@Override
	public void onVenueCategoryIconRetrieved(Venue v, Bitmap b)
	{
		//Bitmap q = scaleCenterCrop(b, 200, 200);
//		
//		Bitmap q = Bitmap.createBitmap(b, 28, 28, 200, 200);
//		
//		Bitmap x = invertBitmap(q);
//		
//		x = makeBrightnessBitmap(x, -90);

		if (_venues.get(_venueIndex).equals(v))
		{
			VenuImage vi = new VenuImage(_ctx, v);
			vi.setBackgroundImage(b);
			showBitmap(vi);
		}
	}
	
	
}

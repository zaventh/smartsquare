/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.condesales.models.Venue;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;

/**
 * @author Jeff Mixon
 * 
 */
public class SmartSquareControlExtension extends BaseControlExtensionView implements ISmartSquareControlView
{
	private static final String TAG = "SmartSquareControlExtension";
	private final Context _ctx;

	private List<Venue> _venues;
	private int _venueIndex = 0;
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
		ExtensionImage img = new VenuImage(_ctx, _venues.get(index));
		showBitmap(img);
		_venueIndex = index;
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

}

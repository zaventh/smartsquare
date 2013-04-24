/*******************************************************************************
 * Copyright (c) 2013 Jeff Mixon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * (or any later version, at your option) which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Jeff Mixon - initial public release
 ******************************************************************************/
/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.condesales.models.HereNow;
import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 * 
 */
public class VenueDetailImage extends ExtensionImage
{
	private final Venue _venue;
	private final Bitmap _imgBackground;

	public VenueDetailImage(Context ctx, Venue originalVenue, Bitmap background)
	{
		super(ctx);
		_venue = originalVenue;
		_imgBackground = background;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#getLayoutId()
	 */
	@Override
	protected int getLayoutId()
	{
		return R.layout.watch_venue_detail;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#applyInnerLayout(android.widget.LinearLayout)
	 */
	@Override
	protected void applyInnerLayout(LinearLayout layout)
	{
		TextView txtName = (TextView) layout.findViewById(R.id.txtName);

		txtName.setText(_venue.getName());

		if (_imgBackground != null)
		{
			ImageView iv = (ImageView) layout.findViewById(R.id.imgBackground);
			iv.setImageBitmap(_imgBackground);
		}

		View hereNowLayout = layout.findViewById(R.id.hereNow);
		HereNow checkedNow = _venue.getHereNow();
		hereNowLayout.setVisibility(View.VISIBLE);

		TextView tvChecked = (TextView) layout.findViewById(R.id.txtHereNow);
		if (_venue.getHereNow() != null && _venue.getHereNow().getCount() > 0)
		{
			tvChecked.setText(String.valueOf(checkedNow.getCount() + 1));
		}
		else
			tvChecked.setText("1");

		View checkedInLayout = layout.findViewById(R.id.checkedIn);
		HereNow now = _venue.getBeenHere();
		checkedInLayout.setVisibility(View.VISIBLE);

		TextView tv = (TextView) layout.findViewById(R.id.txtCount);

		if (_venue.getBeenHere() != null && _venue.getBeenHere().getCount() > 0)
		{
			tv.setText(String.valueOf(now.getCount()));
		}
		else
			tv.setText("1");
	}

}

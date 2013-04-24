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

import java.text.DecimalFormat;

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
public class VenuImage extends ExtensionImage
{
	private final Venue _venue;
	private Bitmap _imgBackground;

	public VenuImage(Context ctx, Venue venue)
	{
		super(ctx);
		_venue = venue;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#getLayoutId()
	 */
	@Override
	protected int getLayoutId()
	{
		return R.layout.watch_venue;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#applyInnerLayout(android.widget.LinearLayout)
	 */
	@Override
	protected void applyInnerLayout(LinearLayout layout)
	{
		TextView txtName = (TextView) layout.findViewById(R.id.txtName);

		txtName.setText(_venue.getName());

		TextView txtDistance = (TextView) layout.findViewById(R.id.txtDistance);

		int feet = Util.ConvertMetersToFeet(_venue.getLocation().getDistance());

		if (feet < 528)
			txtDistance.setText(feet + "ft");
		else
		{
			double miles = (double)feet / (double)5280;

			DecimalFormat f = new DecimalFormat("0.#");
			txtDistance.setText(f.format(miles) + "mi");
		}

		if (_imgBackground != null)
		{
			ImageView iv = (ImageView) layout.findViewById(R.id.imgBackground);
			iv.setImageBitmap(_imgBackground);
		}

		View hereNowLayout = layout.findViewById(R.id.hereNow);
		if (_venue.getHereNow() != null && _venue.getHereNow().getCount() > 0)
		{
			HereNow now = _venue.getHereNow();
			hereNowLayout.setVisibility(View.VISIBLE);

			TextView tv = (TextView) layout.findViewById(R.id.txtHereNow);
			tv.setText(String.valueOf(now.getCount()));
		}
		else
			hereNowLayout.setVisibility(View.INVISIBLE);

		View checkedInLayout = layout.findViewById(R.id.checkedIn);

		if (_venue.getBeenHere() != null && _venue.getBeenHere().getCount() > 0)
		{
			HereNow now = _venue.getBeenHere();
			checkedInLayout.setVisibility(View.VISIBLE);

			TextView tv = (TextView) layout.findViewById(R.id.txtCount);
			tv.setText(String.valueOf(now.getCount()));
		}
		else
			checkedInLayout.setVisibility(View.INVISIBLE);
	}

	public void setBackgroundImage(Bitmap bitmap)
	{
		_imgBackground = bitmap;
	}
}

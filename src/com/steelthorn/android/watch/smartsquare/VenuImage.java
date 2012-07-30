/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import android.content.Context;
import android.widget.LinearLayout;
import br.com.condesales.models.Venue;

/**
 * @author Jeff Mixon
 *
 */
public class VenuImage extends ExtensionImage
{
	private final Context _ctx;
	private final Venue _venue;

	public VenuImage(Context ctx, Venue venue)
	{
		super(ctx);
		_ctx = ctx;
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
		// TODO Auto-generated method stub

	}

}

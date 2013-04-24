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
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Jeff Mixon
 * 
 */
public class GenericTextImage extends ExtensionImage
{
	final String _message;

	public GenericTextImage(Context ctx, int resourceId)
	{
		this(ctx, ctx.getString(resourceId));
	}

	public GenericTextImage(Context ctx, String message)
	{
		super(ctx);
		_message = message;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#getLayoutId()
	 */
	@Override
	protected int getLayoutId()
	{
		return R.layout.watch_generic_text;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#applyInnerLayout(android.widget.LinearLayout)
	 */
	@Override
	protected void applyInnerLayout(LinearLayout layout)
	{
		TextView tv = (TextView) layout.findViewById(R.id.txtGeneric);
		tv.setText(_message);
	}

}

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
package com.steelthorn.android.watch.smartsquare;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

abstract class ExtensionImage
{
	final WeakReference<Context> _weakContext;

	public ExtensionImage(Context ctx)
	{
		_weakContext = new WeakReference<Context>(ctx);
	}

	public Bitmap getBitmap()
	{
		if (getLayoutId()==0 || _weakContext.get() == null)
			return null;

		int mInnerWidth = 128;
		int mInnerHeight = 128;
		Bitmap innerBitmap = Bitmap.createBitmap(mInnerWidth, mInnerHeight, Bitmap.Config.ARGB_8888);

		// Set the density to default to avoid scaling.
		innerBitmap.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		LinearLayout root = new LinearLayout(_weakContext.get());
		root.setLayoutParams(new LinearLayout.LayoutParams(mInnerWidth, mInnerHeight));

		LinearLayout innerLayout = (LinearLayout) LinearLayout.inflate(_weakContext.get(), getLayoutId(), root);

		applyInnerLayout(innerLayout);

		innerLayout.measure(mInnerWidth, mInnerHeight);
		innerLayout.layout(0, 0, innerLayout.getMeasuredWidth(), innerLayout.getMeasuredHeight());

		Canvas innerCanvas = new Canvas(innerBitmap);
		innerLayout.draw(innerCanvas);

		return innerBitmap;

	}

	protected abstract int getLayoutId();

	protected abstract void applyInnerLayout(LinearLayout layout);
}

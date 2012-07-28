/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import android.content.Context;

import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;

/**
 * @author Jeff Mixon
 * 
 */
public class SmartSquareControlExtension extends ControlExtension implements ISmartSquareControlView
{
	private final Context _ctx;

	public SmartSquareControlExtension(Context context, String hostAppPackageName)
	{
		super(context, hostAppPackageName);

		_ctx = context;
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

	
}

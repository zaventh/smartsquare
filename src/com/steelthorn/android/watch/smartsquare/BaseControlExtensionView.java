/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import android.content.Context;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;

/**
 * @author Jeff Mixon
 *
 */
public abstract class BaseControlExtensionView extends ControlExtension implements IBaseView
{
	protected final Context _ctx;
	
	public BaseControlExtensionView(Context context, String hostAppPackageName)
	{
		super(context, hostAppPackageName);
		_ctx = context;
	}

	public void onError(Exception e)
	{
		setScreenState(Control.Intents.SCREEN_STATE_AUTO);
		showBitmap(new GenericTextImage(_ctx, e.getMessage()));
	}
	
	protected void showBitmap(ExtensionImage image)
	{
		if (image != null)
			showBitmap(image.getBitmap());
	}
}

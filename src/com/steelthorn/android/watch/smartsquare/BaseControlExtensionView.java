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
	public BaseControlExtensionView(Context context, String hostAppPackageName)
	{
		super(context, hostAppPackageName);
	}

	public void onError(Exception e)
	{
		setScreenState(Control.Intents.SCREEN_STATE_AUTO);
	}
}

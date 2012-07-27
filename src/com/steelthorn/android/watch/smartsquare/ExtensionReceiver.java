package com.steelthorn.android.watch.smartsquare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * The extension receiver receives the extension intents and starts the
 * extension service when it arrives.
 */
public class ExtensionReceiver extends BroadcastReceiver
{

	private static final String TAG = "ExtensionReceiver";
	
	@Override
	public void onReceive(final Context context, final Intent intent)
	{
		Log.d(TAG, "onReceive: " + intent.getAction());
		intent.setClass(context, SquareExtensionService.class);
		context.startService(intent);
	}
}

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

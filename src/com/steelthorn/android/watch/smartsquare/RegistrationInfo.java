package com.steelthorn.android.watch.smartsquare;

import android.content.ContentValues;
import android.content.Context;

import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

public class RegistrationInfo extends RegistrationInformation
{
	private final Context _ctx;

	public RegistrationInfo(Context ctx)
	{
		_ctx = ctx;
	}

	@Override
	public ContentValues getExtensionRegistrationConfiguration()
	{
		String iconHostapp = ExtensionUtils.getUriString(_ctx, R.drawable.squareandcompass);
		String iconExtension = ExtensionUtils.getUriString(_ctx, R.drawable.squareandcompass);

		ContentValues values = new ContentValues();

		values.put(Registration.ExtensionColumns.CONFIGURATION_ACTIVITY, MainActivity.class.getName());
		values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT, _ctx.getString(R.string.configuration_text));
		values.put(Registration.ExtensionColumns.NAME, _ctx.getString(R.string.extension_name));
		values.put(Registration.ExtensionColumns.EXTENSION_KEY, SquareExtensionService.ID);
		values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostapp);
		values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI, iconExtension);
		values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION, getRequiredNotificationApiVersion());
		values.put(Registration.ExtensionColumns.PACKAGE_NAME, _ctx.getPackageName());

		return values;
	}

	@Override
	public int getRequiredControlApiVersion()
	{
		return 1;
	}

	@Override
	public int getRequiredNotificationApiVersion()
	{
		return 0;
	}

	@Override
	public int getRequiredSensorApiVersion()
	{
		return 0;
	}

	@Override
	public int getRequiredWidgetApiVersion()
	{
		return 0;
	}

	@Override
	public boolean isDisplaySizeSupported(int width, int height)
	{
		//return true;
		return (width == _ctx.getResources().getDimensionPixelSize(R.dimen.smart_watch_control_width) && height == _ctx.getResources().getDimensionPixelSize(R.dimen.smart_watch_control_height));
	}
}

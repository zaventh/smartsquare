/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

/**
 * @author Jeff Mixon
 *
 */
public class SquareExtensionService extends ExtensionService
{
	public static final String ID = "com.steelthorn.android.smartextras.smartsquare.ID";

	public SquareExtensionService()
	{
		super(ID);
	}

	/* (non-Javadoc)
	 * @see com.sonyericsson.extras.liveware.extension.util.ExtensionService#getRegistrationInformation()
	 */
	@Override
	protected RegistrationInformation getRegistrationInformation()
	{
		return new RegistrationInfo(this);
	}

	/* (non-Javadoc)
	 * @see com.sonyericsson.extras.liveware.extension.util.ExtensionService#keepRunningWhenConnected()
	 */
	@Override
	protected boolean keepRunningWhenConnected()
	{
		return false;
	}

	@Override
	public ControlExtension createControlExtension(String hostAppPackageName)
	{
		return new SmartSquareControlExtension(this, hostAppPackageName);
	}

	
}

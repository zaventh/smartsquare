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

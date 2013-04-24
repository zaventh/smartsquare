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

import java.lang.ref.WeakReference;

/**
 * @author Jeff Mixon
 *
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter<T>
{
	private final WeakReference<T> _weakView;
	
	public BasePresenter(T view)
	{
		_weakView = new WeakReference<T>(view);
	}
	
	protected T getView()
	{
		return _weakView.get();
	}
}

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

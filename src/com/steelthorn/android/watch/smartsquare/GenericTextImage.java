/**
 * 
 */
package com.steelthorn.android.watch.smartsquare;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Jeff Mixon
 * 
 */
public class GenericTextImage extends ExtensionImage
{
	final String _message;

	public GenericTextImage(Context ctx, int resourceId)
	{
		this(ctx, ctx.getString(resourceId));
	}

	public GenericTextImage(Context ctx, String message)
	{
		super(ctx);
		_message = message;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#getLayoutId()
	 */
	@Override
	protected int getLayoutId()
	{
		return R.layout.watch_generic_text;
	}

	/* (non-Javadoc)
	 * @see com.steelthorn.android.watch.smartsquare.ExtensionImage#applyInnerLayout(android.widget.LinearLayout)
	 */
	@Override
	protected void applyInnerLayout(LinearLayout layout)
	{
		TextView tv = (TextView) layout.findViewById(R.id.txtGeneric);
		tv.setText(_message);
	}

}
